package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class StrSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
    public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher("}");
    public static final StrMatcher DEFAULT_VALUE_DELIMITER = StrMatcher.stringMatcher(":-");
    private boolean enableSubstitutionInVariables;
    private char escapeChar;
    private StrMatcher prefixMatcher;
    private StrMatcher suffixMatcher;
    private StrMatcher valueDelimiterMatcher;
    private StrLookup<?> variableResolver;

    public StrSubstitutor() {
        this((StrLookup<?>) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    private void checkCyclicSubstitution(String str, List<String> list) {
        if (list.contains(str)) {
            StrBuilder strBuilder = new StrBuilder(256);
            strBuilder.append("Infinite loop in property interpolation of ");
            strBuilder.append(list.remove(0));
            strBuilder.append(": ");
            strBuilder.appendWithSeparators(list, "->");
            throw new IllegalStateException(strBuilder.toString());
        }
    }

    public static <V> String replace(Object obj, Map<String, V> map) {
        return new StrSubstitutor(map).replace(obj);
    }

    public static String replaceSystemProperties(Object obj) {
        return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(obj);
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public StrMatcher getValueDelimiterMatcher() {
        return this.valueDelimiterMatcher;
    }

    public StrMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StrLookup<?> getVariableResolver() {
        return this.variableResolver;
    }

    public StrMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public boolean replaceIn(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return false;
        }
        return replaceIn(stringBuffer, 0, stringBuffer.length());
    }

    public String resolveVariable(String str, StrBuilder strBuilder, int i9, int i10) {
        StrLookup<?> variableResolver = getVariableResolver();
        if (variableResolver == null) {
            return null;
        }
        return variableResolver.lookup(str);
    }

    public void setEnableSubstitutionInVariables(boolean z8) {
        this.enableSubstitutionInVariables = z8;
    }

    public void setEscapeChar(char c9) {
        this.escapeChar = c9;
    }

    public StrSubstitutor setValueDelimiter(char c9) {
        return setValueDelimiterMatcher(StrMatcher.charMatcher(c9));
    }

    public StrSubstitutor setValueDelimiterMatcher(StrMatcher strMatcher) {
        this.valueDelimiterMatcher = strMatcher;
        return this;
    }

    public StrSubstitutor setVariablePrefix(char c9) {
        return setVariablePrefixMatcher(StrMatcher.charMatcher(c9));
    }

    public StrSubstitutor setVariablePrefixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable prefix matcher must not be null!");
        }
        this.prefixMatcher = strMatcher;
        return this;
    }

    public void setVariableResolver(StrLookup<?> strLookup) {
        this.variableResolver = strLookup;
    }

    public StrSubstitutor setVariableSuffix(char c9) {
        return setVariableSuffixMatcher(StrMatcher.charMatcher(c9));
    }

    public StrSubstitutor setVariableSuffixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable suffix matcher must not be null!");
        }
        this.suffixMatcher = strMatcher;
        return this;
    }

    public boolean substitute(StrBuilder strBuilder, int i9, int i10) {
        return substitute(strBuilder, i9, i10, null) > 0;
    }

    public <V> StrSubstitutor(Map<String, V> map) {
        this((StrLookup<?>) StrLookup.mapLookup(map), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public static <V> String replace(Object obj, Map<String, V> map, String str, String str2) {
        return new StrSubstitutor(map, str, str2).replace(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int substitute(StrBuilder strBuilder, int i9, int i10, List<String> list) {
        StrMatcher strMatcher;
        StrMatcher strMatcher2;
        char c9;
        boolean z8;
        String strSubstring;
        int iIsMatch;
        StrMatcher variablePrefixMatcher = getVariablePrefixMatcher();
        StrMatcher variableSuffixMatcher = getVariableSuffixMatcher();
        char escapeChar = getEscapeChar();
        StrMatcher valueDelimiterMatcher = getValueDelimiterMatcher();
        boolean zIsEnableSubstitutionInVariables = isEnableSubstitutionInVariables();
        boolean z9 = list == null;
        int i11 = i9;
        int i12 = i9 + i10;
        int i13 = 0;
        int i14 = 0;
        char[] cArr = strBuilder.buffer;
        List<String> arrayList = list;
        while (i11 < i12) {
            int iIsMatch2 = variablePrefixMatcher.isMatch(cArr, i11, i9, i12);
            if (iIsMatch2 == 0) {
                i11++;
                strMatcher = variablePrefixMatcher;
                strMatcher2 = variableSuffixMatcher;
                c9 = escapeChar;
                z8 = z9;
            } else if (i11 > i9) {
                int i15 = i11 - 1;
                if (cArr[i15] != escapeChar) {
                    int i16 = i11 + iIsMatch2;
                    int i17 = i16;
                    int i18 = 0;
                    while (true) {
                        if (i17 >= i12) {
                            strMatcher = variablePrefixMatcher;
                            strMatcher2 = variableSuffixMatcher;
                            c9 = escapeChar;
                            z8 = z9;
                            i11 = i17;
                            break;
                        }
                        if (!zIsEnableSubstitutionInVariables || (iIsMatch = variablePrefixMatcher.isMatch(cArr, i17, i9, i12)) == 0) {
                            int iIsMatch3 = variableSuffixMatcher.isMatch(cArr, i17, i9, i12);
                            if (iIsMatch3 == 0) {
                                i17++;
                            } else if (i18 == 0) {
                                strMatcher2 = variableSuffixMatcher;
                                c9 = escapeChar;
                                String str = new String(cArr, i16, (i17 - i11) - iIsMatch2);
                                if (zIsEnableSubstitutionInVariables) {
                                    StrBuilder strBuilder2 = new StrBuilder(str);
                                    substitute(strBuilder2, 0, strBuilder2.length());
                                    str = strBuilder2.toString();
                                }
                                int i19 = i17 + iIsMatch3;
                                if (valueDelimiterMatcher != null) {
                                    char[] charArray = str.toCharArray();
                                    z8 = z9;
                                    int i20 = 0;
                                    while (i20 < charArray.length && (zIsEnableSubstitutionInVariables || variablePrefixMatcher.isMatch(charArray, i20, i20, charArray.length) == 0)) {
                                        int iIsMatch4 = valueDelimiterMatcher.isMatch(charArray, i20);
                                        if (iIsMatch4 != 0) {
                                            strMatcher = variablePrefixMatcher;
                                            String strSubstring2 = str.substring(0, i20);
                                            strSubstring = str.substring(i20 + iIsMatch4);
                                            str = strSubstring2;
                                            break;
                                        }
                                        i20++;
                                        variablePrefixMatcher = variablePrefixMatcher;
                                    }
                                    strMatcher = variablePrefixMatcher;
                                } else {
                                    strMatcher = variablePrefixMatcher;
                                    z8 = z9;
                                }
                                strSubstring = null;
                                if (arrayList == null) {
                                    arrayList = new ArrayList<>();
                                    arrayList.add(new String(cArr, i9, i10));
                                }
                                checkCyclicSubstitution(str, arrayList);
                                arrayList.add(str);
                                String strResolveVariable = resolveVariable(str, strBuilder, i11, i19);
                                if (strResolveVariable != null) {
                                    strSubstring = strResolveVariable;
                                }
                                if (strSubstring != null) {
                                    int length = strSubstring.length();
                                    strBuilder.replace(i11, i19, strSubstring);
                                    int iSubstitute = (substitute(strBuilder, i11, length, arrayList) + length) - (i19 - i11);
                                    i12 += iSubstitute;
                                    i13 += iSubstitute;
                                    cArr = strBuilder.buffer;
                                    i11 = i19 + iSubstitute;
                                    i14 = 1;
                                } else {
                                    i11 = i19;
                                }
                                arrayList.remove(arrayList.size() - 1);
                            } else {
                                i18--;
                                i17 += iIsMatch3;
                                escapeChar = escapeChar;
                                variablePrefixMatcher = variablePrefixMatcher;
                            }
                        } else {
                            i18++;
                            i17 += iIsMatch;
                        }
                    }
                } else {
                    strBuilder.deleteCharAt(i15);
                    i13--;
                    i12--;
                    strMatcher = variablePrefixMatcher;
                    strMatcher2 = variableSuffixMatcher;
                    c9 = escapeChar;
                    cArr = strBuilder.buffer;
                    z8 = z9;
                    i14 = 1;
                }
            }
            variableSuffixMatcher = strMatcher2;
            escapeChar = c9;
            z9 = z8;
            variablePrefixMatcher = strMatcher;
        }
        return z9 ? i14 : i13;
    }

    public boolean replaceIn(StringBuffer stringBuffer, int i9, int i10) {
        if (stringBuffer == null) {
            return false;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(stringBuffer, i9, i10);
        if (!substitute(strBuilderAppend, 0, i10)) {
            return false;
        }
        stringBuffer.replace(i9, i10 + i9, strBuilderAppend.toString());
        return true;
    }

    public StrSubstitutor setValueDelimiter(String str) {
        if (!StringUtils.isEmpty(str)) {
            return setValueDelimiterMatcher(StrMatcher.stringMatcher(str));
        }
        setValueDelimiterMatcher(null);
        return this;
    }

    public StrSubstitutor setVariablePrefix(String str) {
        if (str != null) {
            return setVariablePrefixMatcher(StrMatcher.stringMatcher(str));
        }
        throw new IllegalArgumentException("Variable prefix must not be null!");
    }

    public StrSubstitutor setVariableSuffix(String str) {
        if (str != null) {
            return setVariableSuffixMatcher(StrMatcher.stringMatcher(str));
        }
        throw new IllegalArgumentException("Variable suffix must not be null!");
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2) {
        this((StrLookup<?>) StrLookup.mapLookup(map), str, str2, '$');
    }

    public static String replace(Object obj, Properties properties) {
        if (properties == null) {
            return obj.toString();
        }
        HashMap map = new HashMap();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            map.put(str, properties.getProperty(str));
        }
        return replace(obj, map);
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2, char c9) {
        this((StrLookup<?>) StrLookup.mapLookup(map), str, str2, c9);
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2, char c9, String str3) {
        this((StrLookup<?>) StrLookup.mapLookup(map), str, str2, c9, str3);
    }

    public boolean replaceIn(StringBuilder sb) {
        if (sb == null) {
            return false;
        }
        return replaceIn(sb, 0, sb.length());
    }

    public StrSubstitutor(StrLookup<?> strLookup) {
        this(strLookup, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public boolean replaceIn(StringBuilder sb, int i9, int i10) {
        if (sb == null) {
            return false;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(sb, i9, i10);
        if (!substitute(strBuilderAppend, 0, i10)) {
            return false;
        }
        sb.replace(i9, i10 + i9, strBuilderAppend.toString());
        return true;
    }

    public StrSubstitutor(StrLookup<?> strLookup, String str, String str2, char c9) {
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c9);
        setValueDelimiterMatcher(DEFAULT_VALUE_DELIMITER);
    }

    public boolean replaceIn(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, 0, strBuilder.length());
    }

    public boolean replaceIn(StrBuilder strBuilder, int i9, int i10) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, i9, i10);
    }

    public String replace(String str) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilder = new StrBuilder(str);
        return !substitute(strBuilder, 0, str.length()) ? str : strBuilder.toString();
    }

    public StrSubstitutor(StrLookup<?> strLookup, String str, String str2, char c9, String str3) {
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c9);
        setValueDelimiter(str3);
    }

    public String replace(String str, int i9, int i10) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(str, i9, i10);
        if (!substitute(strBuilderAppend, 0, i10)) {
            return str.substring(i9, i10 + i9);
        }
        return strBuilderAppend.toString();
    }

    public String replace(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(cArr.length).append(cArr);
        substitute(strBuilderAppend, 0, cArr.length);
        return strBuilderAppend.toString();
    }

    public StrSubstitutor(StrLookup<?> strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c9) {
        this(strLookup, strMatcher, strMatcher2, c9, DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c9, StrMatcher strMatcher3) {
        setVariableResolver(strLookup);
        setVariablePrefixMatcher(strMatcher);
        setVariableSuffixMatcher(strMatcher2);
        setEscapeChar(c9);
        setValueDelimiterMatcher(strMatcher3);
    }

    public String replace(char[] cArr, int i9, int i10) {
        if (cArr == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(cArr, i9, i10);
        substitute(strBuilderAppend, 0, i10);
        return strBuilderAppend.toString();
    }

    public String replace(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(stringBuffer.length()).append(stringBuffer);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public String replace(StringBuffer stringBuffer, int i9, int i10) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(stringBuffer, i9, i10);
        substitute(strBuilderAppend, 0, i10);
        return strBuilderAppend.toString();
    }

    public String replace(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return replace(charSequence, 0, charSequence.length());
    }

    public String replace(CharSequence charSequence, int i9, int i10) {
        if (charSequence == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(charSequence, i9, i10);
        substitute(strBuilderAppend, 0, i10);
        return strBuilderAppend.toString();
    }

    public String replace(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(strBuilder.length()).append(strBuilder);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public String replace(StrBuilder strBuilder, int i9, int i10) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i10).append(strBuilder, i9, i10);
        substitute(strBuilderAppend, 0, i10);
        return strBuilderAppend.toString();
    }

    public String replace(Object obj) {
        if (obj == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder().append(obj);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }
}
