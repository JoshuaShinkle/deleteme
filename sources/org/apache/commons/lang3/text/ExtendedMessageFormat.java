package org.apache.commons.lang3.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes.dex */
public class ExtendedMessageFormat extends MessageFormat {
    private static final String DUMMY_PATTERN = "";
    private static final char END_FE = '}';
    private static final String ESCAPED_QUOTE = "''";
    private static final int HASH_SEED = 31;
    private static final char QUOTE = '\'';
    private static final char START_FE = '{';
    private static final char START_FMT = ',';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map<String, ? extends FormatFactory> registry;
    private String toPattern;

    public ExtendedMessageFormat(String str) {
        this(str, Locale.getDefault());
    }

    private StringBuilder appendQuotedString(String str, ParsePosition parsePosition, StringBuilder sb, boolean z8) {
        int index = parsePosition.getIndex();
        char[] charArray = str.toCharArray();
        if (z8 && charArray[index] == '\'') {
            next(parsePosition);
            if (sb == null) {
                return null;
            }
            sb.append(QUOTE);
            return sb;
        }
        int index2 = index;
        for (int index3 = parsePosition.getIndex(); index3 < str.length(); index3++) {
            if (z8 && str.substring(index3).startsWith(ESCAPED_QUOTE)) {
                sb.append(charArray, index2, parsePosition.getIndex() - index2);
                sb.append(QUOTE);
                parsePosition.setIndex(index3 + 2);
                index2 = parsePosition.getIndex();
            } else {
                if (charArray[parsePosition.getIndex()] == '\'') {
                    next(parsePosition);
                    if (sb == null) {
                        return null;
                    }
                    sb.append(charArray, index2, parsePosition.getIndex() - index2);
                    return sb;
                }
                next(parsePosition);
            }
        }
        throw new IllegalArgumentException("Unterminated quoted string at position " + index);
    }

    private boolean containsElements(Collection<?> collection) {
        if (collection != null && !collection.isEmpty()) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private Format getFormat(String str) {
        String strTrim;
        if (this.registry != null) {
            int iIndexOf = str.indexOf(44);
            if (iIndexOf > 0) {
                String strTrim2 = str.substring(0, iIndexOf).trim();
                strTrim = str.substring(iIndexOf + 1).trim();
                str = strTrim2;
            } else {
                strTrim = null;
            }
            FormatFactory formatFactory = this.registry.get(str);
            if (formatFactory != null) {
                return formatFactory.getFormat(str, strTrim, getLocale());
            }
        }
        return null;
    }

    private void getQuotedString(String str, ParsePosition parsePosition, boolean z8) {
        appendQuotedString(str, parsePosition, null, z8);
    }

    private String insertFormats(String str, ArrayList<String> arrayList) {
        String str2;
        if (!containsElements(arrayList)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() * 2);
        ParsePosition parsePosition = new ParsePosition(0);
        int i9 = -1;
        int i10 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char cCharAt = str.charAt(parsePosition.getIndex());
            if (cCharAt == '\'') {
                appendQuotedString(str, parsePosition, sb, false);
            } else if (cCharAt != '{') {
                if (cCharAt == '}') {
                    i10--;
                }
                sb.append(cCharAt);
                next(parsePosition);
            } else {
                i10++;
                sb.append(START_FE);
                sb.append(readArgumentIndex(str, next(parsePosition)));
                if (i10 == 1 && (str2 = arrayList.get((i9 = i9 + 1))) != null) {
                    sb.append(START_FMT);
                    sb.append(str2);
                }
            }
        }
        return sb.toString();
    }

    private ParsePosition next(ParsePosition parsePosition) {
        parsePosition.setIndex(parsePosition.getIndex() + 1);
        return parsePosition;
    }

    private String parseFormatDescription(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        int index2 = parsePosition.getIndex();
        int i9 = 1;
        while (parsePosition.getIndex() < str.length()) {
            char cCharAt = str.charAt(parsePosition.getIndex());
            if (cCharAt == '\'') {
                getQuotedString(str, parsePosition, false);
            } else if (cCharAt == '{') {
                i9++;
            } else if (cCharAt == '}' && i9 - 1 == 0) {
                return str.substring(index2, parsePosition.getIndex());
            }
            next(parsePosition);
        }
        throw new IllegalArgumentException("Unterminated format element at position " + index);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d A[PHI: r2
      0x003d: PHI (r2v5 char) = (r2v4 char), (r2v9 char), (r2v9 char) binds: [B:7:0x002a, B:9:0x0037, B:10:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int readArgumentIndex(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        StringBuilder sb = new StringBuilder();
        boolean z8 = false;
        while (!z8 && parsePosition.getIndex() < str.length()) {
            char cCharAt = str.charAt(parsePosition.getIndex());
            if (Character.isWhitespace(cCharAt)) {
                seekNonWs(str, parsePosition);
                cCharAt = str.charAt(parsePosition.getIndex());
                if (cCharAt == ',' || cCharAt == '}') {
                    if ((cCharAt == ',' || cCharAt == '}') && sb.length() > 0) {
                        try {
                            return Integer.parseInt(sb.toString());
                        } catch (NumberFormatException unused) {
                        }
                    }
                    boolean z9 = !Character.isDigit(cCharAt);
                    sb.append(cCharAt);
                    z8 = z9;
                } else {
                    z8 = true;
                }
            }
            next(parsePosition);
        }
        if (!z8) {
            throw new IllegalArgumentException("Unterminated format element at position " + index);
        }
        throw new IllegalArgumentException("Invalid format argument index at position " + index + ": " + str.substring(index, parsePosition.getIndex()));
    }

    private void seekNonWs(String str, ParsePosition parsePosition) {
        char[] charArray = str.toCharArray();
        do {
            int iIsMatch = StrMatcher.splitMatcher().isMatch(charArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + iIsMatch);
            if (iIsMatch <= 0) {
                return;
            }
        } while (parsePosition.getIndex() < str.length());
    }

    @Override // java.text.MessageFormat
    public final void applyPattern(String str) {
        String formatDescription;
        Format format;
        if (this.registry == null) {
            super.applyPattern(str);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList<>();
        StringBuilder sb = new StringBuilder(str.length());
        int i9 = 0;
        ParsePosition parsePosition = new ParsePosition(0);
        char[] charArray = str.toCharArray();
        int i10 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char c9 = charArray[parsePosition.getIndex()];
            if (c9 != '\'') {
                if (c9 == '{') {
                    i10++;
                    seekNonWs(str, parsePosition);
                    int index = parsePosition.getIndex();
                    int argumentIndex = readArgumentIndex(str, next(parsePosition));
                    sb.append(START_FE);
                    sb.append(argumentIndex);
                    seekNonWs(str, parsePosition);
                    if (charArray[parsePosition.getIndex()] == ',') {
                        formatDescription = parseFormatDescription(str, next(parsePosition));
                        format = getFormat(formatDescription);
                        if (format == null) {
                            sb.append(START_FMT);
                            sb.append(formatDescription);
                        }
                    } else {
                        formatDescription = null;
                        format = null;
                    }
                    arrayList.add(format);
                    arrayList2.add(format != null ? formatDescription : null);
                    Validate.isTrue(arrayList.size() == i10);
                    Validate.isTrue(arrayList2.size() == i10);
                    if (charArray[parsePosition.getIndex()] != '}') {
                        throw new IllegalArgumentException("Unreadable format element at position " + index);
                    }
                }
                sb.append(charArray[parsePosition.getIndex()]);
                next(parsePosition);
            } else {
                appendQuotedString(str, parsePosition, sb, true);
            }
        }
        super.applyPattern(sb.toString());
        this.toPattern = insertFormats(super.toPattern(), arrayList2);
        if (containsElements(arrayList)) {
            Format[] formats = getFormats();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Format format2 = (Format) it.next();
                if (format2 != null) {
                    formats[i9] = format2;
                }
                i9++;
            }
            super.setFormats(formats);
        }
    }

    @Override // java.text.MessageFormat
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !super.equals(obj) || ObjectUtils.notEqual(getClass(), obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat extendedMessageFormat = (ExtendedMessageFormat) obj;
        return (ObjectUtils.notEqual(this.toPattern, extendedMessageFormat.toPattern) || ObjectUtils.notEqual(this.registry, extendedMessageFormat.registry)) ? false : true;
    }

    @Override // java.text.MessageFormat
    public int hashCode() {
        return (((super.hashCode() * 31) + ObjectUtils.hashCode(this.registry)) * 31) + ObjectUtils.hashCode(this.toPattern);
    }

    @Override // java.text.MessageFormat
    public void setFormat(int i9, Format format) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormatByArgumentIndex(int i9, Format format) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormats(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormatsByArgumentIndex(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public String toPattern() {
        return this.toPattern;
    }

    public ExtendedMessageFormat(String str, Locale locale) {
        this(str, locale, null);
    }

    public ExtendedMessageFormat(String str, Map<String, ? extends FormatFactory> map) {
        this(str, Locale.getDefault(), map);
    }

    public ExtendedMessageFormat(String str, Locale locale, Map<String, ? extends FormatFactory> map) {
        super("");
        setLocale(locale);
        this.registry = map;
        applyPattern(str);
    }
}
