package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class StrTokenizer implements ListIterator<String>, Cloneable {
    private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE;
    private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
    private char[] chars;
    private StrMatcher delimMatcher;
    private boolean emptyAsNull;
    private boolean ignoreEmptyTokens;
    private StrMatcher ignoredMatcher;
    private StrMatcher quoteMatcher;
    private int tokenPos;
    private String[] tokens;
    private StrMatcher trimmerMatcher;

    static {
        StrTokenizer strTokenizer = new StrTokenizer();
        CSV_TOKENIZER_PROTOTYPE = strTokenizer;
        strTokenizer.setDelimiterMatcher(StrMatcher.commaMatcher());
        strTokenizer.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        strTokenizer.setIgnoredMatcher(StrMatcher.noneMatcher());
        strTokenizer.setTrimmerMatcher(StrMatcher.trimMatcher());
        strTokenizer.setEmptyTokenAsNull(false);
        strTokenizer.setIgnoreEmptyTokens(false);
        StrTokenizer strTokenizer2 = new StrTokenizer();
        TSV_TOKENIZER_PROTOTYPE = strTokenizer2;
        strTokenizer2.setDelimiterMatcher(StrMatcher.tabMatcher());
        strTokenizer2.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        strTokenizer2.setIgnoredMatcher(StrMatcher.noneMatcher());
        strTokenizer2.setTrimmerMatcher(StrMatcher.trimMatcher());
        strTokenizer2.setEmptyTokenAsNull(false);
        strTokenizer2.setIgnoreEmptyTokens(false);
    }

    public StrTokenizer() {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = null;
    }

    private void addToken(List<String> list, String str) {
        if (StringUtils.isEmpty(str)) {
            if (isIgnoreEmptyTokens()) {
                return;
            }
            if (isEmptyTokenAsNull()) {
                str = null;
            }
        }
        list.add(str);
    }

    private void checkTokenized() {
        if (this.tokens == null) {
            char[] cArr = this.chars;
            if (cArr == null) {
                List<String> list = tokenize(null, 0, 0);
                this.tokens = (String[]) list.toArray(new String[list.size()]);
            } else {
                List<String> list2 = tokenize(cArr, 0, cArr.length);
                this.tokens = (String[]) list2.toArray(new String[list2.size()]);
            }
        }
    }

    private static StrTokenizer getCSVClone() {
        return (StrTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getCSVInstance() {
        return getCSVClone();
    }

    private static StrTokenizer getTSVClone() {
        return (StrTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getTSVInstance() {
        return getTSVClone();
    }

    private boolean isQuote(char[] cArr, int i9, int i10, int i11, int i12) {
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = i9 + i13;
            if (i14 >= i10 || cArr[i14] != cArr[i11 + i13]) {
                return false;
            }
        }
        return true;
    }

    private int readNextToken(char[] cArr, int i9, int i10, StrBuilder strBuilder, List<String> list) {
        while (i9 < i10) {
            int iMax = Math.max(getIgnoredMatcher().isMatch(cArr, i9, i9, i10), getTrimmerMatcher().isMatch(cArr, i9, i9, i10));
            if (iMax == 0 || getDelimiterMatcher().isMatch(cArr, i9, i9, i10) > 0 || getQuoteMatcher().isMatch(cArr, i9, i9, i10) > 0) {
                break;
            }
            i9 += iMax;
        }
        if (i9 >= i10) {
            addToken(list, "");
            return -1;
        }
        int iIsMatch = getDelimiterMatcher().isMatch(cArr, i9, i9, i10);
        if (iIsMatch > 0) {
            addToken(list, "");
            return i9 + iIsMatch;
        }
        int iIsMatch2 = getQuoteMatcher().isMatch(cArr, i9, i9, i10);
        return iIsMatch2 > 0 ? readWithQuotes(cArr, i9 + iIsMatch2, i10, strBuilder, list, i9, iIsMatch2) : readWithQuotes(cArr, i9, i10, strBuilder, list, 0, 0);
    }

    private int readWithQuotes(char[] cArr, int i9, int i10, StrBuilder strBuilder, List<String> list, int i11, int i12) {
        strBuilder.clear();
        boolean z8 = i12 > 0;
        int i13 = i9;
        int size = 0;
        while (i13 < i10) {
            if (z8) {
                int i14 = size;
                int i15 = i13;
                if (isQuote(cArr, i13, i10, i11, i12)) {
                    int i16 = i15 + i12;
                    if (isQuote(cArr, i16, i10, i11, i12)) {
                        strBuilder.append(cArr, i15, i12);
                        i13 = i15 + (i12 * 2);
                        size = strBuilder.size();
                    } else {
                        size = i14;
                        i13 = i16;
                        z8 = false;
                    }
                } else {
                    i13 = i15 + 1;
                    strBuilder.append(cArr[i15]);
                    size = strBuilder.size();
                }
            } else {
                int i17 = size;
                int i18 = i13;
                int iIsMatch = getDelimiterMatcher().isMatch(cArr, i18, i9, i10);
                if (iIsMatch > 0) {
                    addToken(list, strBuilder.substring(0, i17));
                    return i18 + iIsMatch;
                }
                if (i12 <= 0 || !isQuote(cArr, i18, i10, i11, i12)) {
                    int iIsMatch2 = getIgnoredMatcher().isMatch(cArr, i18, i9, i10);
                    if (iIsMatch2 <= 0) {
                        iIsMatch2 = getTrimmerMatcher().isMatch(cArr, i18, i9, i10);
                        if (iIsMatch2 > 0) {
                            strBuilder.append(cArr, i18, iIsMatch2);
                        } else {
                            i13 = i18 + 1;
                            strBuilder.append(cArr[i18]);
                            size = strBuilder.size();
                        }
                    }
                    i13 = i18 + iIsMatch2;
                    size = i17;
                } else {
                    i13 = i18 + i12;
                    size = i17;
                    z8 = true;
                }
            }
        }
        addToken(list, strBuilder.substring(0, size));
        return -1;
    }

    public Object clone() {
        try {
            return cloneReset();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Object cloneReset() {
        StrTokenizer strTokenizer = (StrTokenizer) super.clone();
        char[] cArr = strTokenizer.chars;
        if (cArr != null) {
            strTokenizer.chars = (char[]) cArr.clone();
        }
        strTokenizer.reset();
        return strTokenizer;
    }

    public String getContent() {
        char[] cArr = this.chars;
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public StrMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StrMatcher getIgnoredMatcher() {
        return this.ignoredMatcher;
    }

    public StrMatcher getQuoteMatcher() {
        return this.quoteMatcher;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return (String[]) this.tokens.clone();
    }

    public List<String> getTokenList() {
        checkTokenized();
        ArrayList arrayList = new ArrayList(this.tokens.length);
        for (String str : this.tokens) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public StrMatcher getTrimmerMatcher() {
        return this.trimmerMatcher;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        checkTokenized();
        return this.tokenPos < this.tokens.length;
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        checkTokenized();
        return this.tokenPos > 0;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public boolean isIgnoreEmptyTokens() {
        return this.ignoreEmptyTokens;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.tokenPos;
    }

    public String nextToken() {
        if (!hasNext()) {
            return null;
        }
        String[] strArr = this.tokens;
        int i9 = this.tokenPos;
        this.tokenPos = i9 + 1;
        return strArr[i9];
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.tokenPos - 1;
    }

    public String previousToken() {
        if (!hasPrevious()) {
            return null;
        }
        String[] strArr = this.tokens;
        int i9 = this.tokenPos - 1;
        this.tokenPos = i9;
        return strArr[i9];
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public StrTokenizer reset() {
        this.tokenPos = 0;
        this.tokens = null;
        return this;
    }

    public StrTokenizer setDelimiterChar(char c9) {
        return setDelimiterMatcher(StrMatcher.charMatcher(c9));
    }

    public StrTokenizer setDelimiterMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            this.delimMatcher = StrMatcher.noneMatcher();
        } else {
            this.delimMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setDelimiterString(String str) {
        return setDelimiterMatcher(StrMatcher.stringMatcher(str));
    }

    public StrTokenizer setEmptyTokenAsNull(boolean z8) {
        this.emptyAsNull = z8;
        return this;
    }

    public StrTokenizer setIgnoreEmptyTokens(boolean z8) {
        this.ignoreEmptyTokens = z8;
        return this;
    }

    public StrTokenizer setIgnoredChar(char c9) {
        return setIgnoredMatcher(StrMatcher.charMatcher(c9));
    }

    public StrTokenizer setIgnoredMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.ignoredMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setQuoteChar(char c9) {
        return setQuoteMatcher(StrMatcher.charMatcher(c9));
    }

    public StrTokenizer setQuoteMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.quoteMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setTrimmerMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.trimmerMatcher = strMatcher;
        }
        return this;
    }

    public int size() {
        checkTokenized();
        return this.tokens.length;
    }

    public String toString() {
        if (this.tokens == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        return "StrTokenizer" + getTokenList();
    }

    public List<String> tokenize(char[] cArr, int i9, int i10) {
        if (cArr == null || i10 == 0) {
            return Collections.emptyList();
        }
        StrBuilder strBuilder = new StrBuilder();
        ArrayList arrayList = new ArrayList();
        int nextToken = i9;
        while (nextToken >= 0 && nextToken < i10) {
            nextToken = readNextToken(cArr, nextToken, i10, strBuilder, arrayList);
            if (nextToken >= i10) {
                addToken(arrayList, "");
            }
        }
        return arrayList;
    }

    public static StrTokenizer getCSVInstance(String str) {
        StrTokenizer cSVClone = getCSVClone();
        cSVClone.reset(str);
        return cSVClone;
    }

    public static StrTokenizer getTSVInstance(String str) {
        StrTokenizer tSVClone = getTSVClone();
        tSVClone.reset(str);
        return tSVClone;
    }

    @Override // java.util.ListIterator
    public void add(String str) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String[] strArr = this.tokens;
        int i9 = this.tokenPos;
        this.tokenPos = i9 + 1;
        return strArr[i9];
    }

    @Override // java.util.ListIterator
    public String previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        String[] strArr = this.tokens;
        int i9 = this.tokenPos - 1;
        this.tokenPos = i9;
        return strArr[i9];
    }

    @Override // java.util.ListIterator
    public void set(String str) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public StrTokenizer reset(String str) {
        reset();
        if (str != null) {
            this.chars = str.toCharArray();
        } else {
            this.chars = null;
        }
        return this;
    }

    public static StrTokenizer getCSVInstance(char[] cArr) {
        StrTokenizer cSVClone = getCSVClone();
        cSVClone.reset(cArr);
        return cSVClone;
    }

    public static StrTokenizer getTSVInstance(char[] cArr) {
        StrTokenizer tSVClone = getTSVClone();
        tSVClone.reset(cArr);
        return tSVClone;
    }

    public StrTokenizer reset(char[] cArr) {
        reset();
        this.chars = ArrayUtils.clone(cArr);
        return this;
    }

    public StrTokenizer(String str) {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        if (str != null) {
            this.chars = str.toCharArray();
        } else {
            this.chars = null;
        }
    }

    public StrTokenizer(String str, char c9) {
        this(str);
        setDelimiterChar(c9);
    }

    public StrTokenizer(String str, String str2) {
        this(str);
        setDelimiterString(str2);
    }

    public StrTokenizer(String str, StrMatcher strMatcher) {
        this(str);
        setDelimiterMatcher(strMatcher);
    }

    public StrTokenizer(String str, char c9, char c10) {
        this(str, c9);
        setQuoteChar(c10);
    }

    public StrTokenizer(String str, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(str, strMatcher);
        setQuoteMatcher(strMatcher2);
    }

    public StrTokenizer(char[] cArr) {
        this.delimMatcher = StrMatcher.splitMatcher();
        this.quoteMatcher = StrMatcher.noneMatcher();
        this.ignoredMatcher = StrMatcher.noneMatcher();
        this.trimmerMatcher = StrMatcher.noneMatcher();
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = ArrayUtils.clone(cArr);
    }

    public StrTokenizer(char[] cArr, char c9) {
        this(cArr);
        setDelimiterChar(c9);
    }

    public StrTokenizer(char[] cArr, String str) {
        this(cArr);
        setDelimiterString(str);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher) {
        this(cArr);
        setDelimiterMatcher(strMatcher);
    }

    public StrTokenizer(char[] cArr, char c9, char c10) {
        this(cArr, c9);
        setQuoteChar(c10);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(cArr, strMatcher);
        setQuoteMatcher(strMatcher2);
    }
}
