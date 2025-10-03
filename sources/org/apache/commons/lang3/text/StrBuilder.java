package org.apache.commons.lang3.text;

import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.Builder;

/* loaded from: classes.dex */
public class StrBuilder implements CharSequence, Appendable, Serializable, Builder<String> {
    static final int CAPACITY = 32;
    private static final long serialVersionUID = 7628716375283629643L;
    protected char[] buffer;
    private String newLine;
    private String nullText;
    protected int size;

    public class StrBuilderTokenizer extends StrTokenizer {
        public StrBuilderTokenizer() {
        }

        @Override // org.apache.commons.lang3.text.StrTokenizer
        public String getContent() {
            String content = super.getContent();
            return content == null ? StrBuilder.this.toString() : content;
        }

        @Override // org.apache.commons.lang3.text.StrTokenizer
        public List<String> tokenize(char[] cArr, int i9, int i10) {
            if (cArr != null) {
                return super.tokenize(cArr, i9, i10);
            }
            StrBuilder strBuilder = StrBuilder.this;
            return super.tokenize(strBuilder.buffer, 0, strBuilder.size());
        }
    }

    public class StrBuilderWriter extends Writer {
        public StrBuilderWriter() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(int i9) {
            StrBuilder.this.append((char) i9);
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
            StrBuilder.this.append(cArr);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i9, int i10) {
            StrBuilder.this.append(cArr, i9, i10);
        }

        @Override // java.io.Writer
        public void write(String str) {
            StrBuilder.this.append(str);
        }

        @Override // java.io.Writer
        public void write(String str, int i9, int i10) {
            StrBuilder.this.append(str, i9, i10);
        }
    }

    public StrBuilder() {
        this(32);
    }

    private void deleteImpl(int i9, int i10, int i11) {
        char[] cArr = this.buffer;
        System.arraycopy(cArr, i10, cArr, i9, this.size - i10);
        this.size -= i11;
    }

    private void replaceImpl(int i9, int i10, int i11, String str, int i12) {
        int i13 = (this.size - i11) + i12;
        if (i12 != i11) {
            ensureCapacity(i13);
            char[] cArr = this.buffer;
            System.arraycopy(cArr, i10, cArr, i9 + i12, this.size - i10);
            this.size = i13;
        }
        if (i12 > 0) {
            str.getChars(0, i12, this.buffer, i9);
        }
    }

    public <T> StrBuilder appendAll(T... tArr) {
        if (tArr != null && tArr.length > 0) {
            for (T t8 : tArr) {
                append(t8);
            }
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadLeft(Object obj, int i9, char c9) {
        if (i9 > 0) {
            ensureCapacity(this.size + i9);
            String nullText = obj == null ? getNullText() : obj.toString();
            if (nullText == null) {
                nullText = "";
            }
            int length = nullText.length();
            if (length >= i9) {
                nullText.getChars(length - i9, length, this.buffer, this.size);
            } else {
                int i10 = i9 - length;
                for (int i11 = 0; i11 < i10; i11++) {
                    this.buffer[this.size + i11] = c9;
                }
                nullText.getChars(0, length, this.buffer, this.size + i10);
            }
            this.size += i9;
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadRight(Object obj, int i9, char c9) {
        if (i9 > 0) {
            ensureCapacity(this.size + i9);
            String nullText = obj == null ? getNullText() : obj.toString();
            if (nullText == null) {
                nullText = "";
            }
            int length = nullText.length();
            if (length >= i9) {
                nullText.getChars(0, i9, this.buffer, this.size);
            } else {
                int i10 = i9 - length;
                nullText.getChars(0, length, this.buffer, this.size);
                for (int i11 = 0; i11 < i10; i11++) {
                    this.buffer[this.size + length + i11] = c9;
                }
            }
            this.size += i9;
        }
        return this;
    }

    public StrBuilder appendNewLine() {
        String str = this.newLine;
        if (str != null) {
            return append(str);
        }
        append(SystemUtils.LINE_SEPARATOR);
        return this;
    }

    public StrBuilder appendNull() {
        String str = this.nullText;
        return str == null ? this : append(str);
    }

    public StrBuilder appendPadding(int i9, char c9) {
        if (i9 >= 0) {
            ensureCapacity(this.size + i9);
            for (int i10 = 0; i10 < i9; i10++) {
                char[] cArr = this.buffer;
                int i11 = this.size;
                this.size = i11 + 1;
                cArr[i11] = c9;
            }
        }
        return this;
    }

    public StrBuilder appendSeparator(String str) {
        return appendSeparator(str, (String) null);
    }

    public StrBuilder appendWithSeparators(Object[] objArr, String str) {
        if (objArr != null && objArr.length > 0) {
            String string = ObjectUtils.toString(str);
            append(objArr[0]);
            for (int i9 = 1; i9 < objArr.length; i9++) {
                append(string);
                append(objArr[i9]);
            }
        }
        return this;
    }

    public StrBuilder appendln(Object obj) {
        return append(obj).appendNewLine();
    }

    public Reader asReader() {
        return new StrBuilderReader();
    }

    public StrTokenizer asTokenizer() {
        return new StrBuilderTokenizer();
    }

    public Writer asWriter() {
        return new StrBuilderWriter();
    }

    public int capacity() {
        return this.buffer.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i9) {
        if (i9 < 0 || i9 >= length()) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        return this.buffer[i9];
    }

    public StrBuilder clear() {
        this.size = 0;
        return this;
    }

    public boolean contains(char c9) {
        char[] cArr = this.buffer;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (cArr[i9] == c9) {
                return true;
            }
        }
        return false;
    }

    public StrBuilder delete(int i9, int i10) {
        int iValidateRange = validateRange(i9, i10);
        int i11 = iValidateRange - i9;
        if (i11 > 0) {
            deleteImpl(i9, iValidateRange, i11);
        }
        return this;
    }

    public StrBuilder deleteAll(char c9) {
        int i9 = 0;
        while (i9 < this.size) {
            if (this.buffer[i9] == c9) {
                int i10 = i9;
                do {
                    i10++;
                    if (i10 >= this.size) {
                        break;
                    }
                } while (this.buffer[i10] == c9);
                int i11 = i10 - i9;
                deleteImpl(i9, i10, i11);
                i9 = i10 - i11;
            }
            i9++;
        }
        return this;
    }

    public StrBuilder deleteCharAt(int i9) {
        if (i9 < 0 || i9 >= this.size) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        deleteImpl(i9, i9 + 1, 1);
        return this;
    }

    public StrBuilder deleteFirst(char c9) {
        int i9 = 0;
        while (true) {
            if (i9 >= this.size) {
                break;
            }
            if (this.buffer[i9] == c9) {
                deleteImpl(i9, i9 + 1, 1);
                break;
            }
            i9++;
        }
        return this;
    }

    public boolean endsWith(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        int i9 = this.size;
        if (length > i9) {
            return false;
        }
        int i10 = i9 - length;
        int i11 = 0;
        while (i11 < length) {
            if (this.buffer[i10] != str.charAt(i11)) {
                return false;
            }
            i11++;
            i10++;
        }
        return true;
    }

    public StrBuilder ensureCapacity(int i9) {
        char[] cArr = this.buffer;
        if (i9 > cArr.length) {
            char[] cArr2 = new char[i9 * 2];
            this.buffer = cArr2;
            System.arraycopy(cArr, 0, cArr2, 0, this.size);
        }
        return this;
    }

    public boolean equals(StrBuilder strBuilder) {
        if (this == strBuilder) {
            return true;
        }
        int i9 = this.size;
        if (i9 != strBuilder.size) {
            return false;
        }
        char[] cArr = this.buffer;
        char[] cArr2 = strBuilder.buffer;
        for (int i10 = i9 - 1; i10 >= 0; i10--) {
            if (cArr[i10] != cArr2[i10]) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIgnoreCase(StrBuilder strBuilder) {
        if (this == strBuilder) {
            return true;
        }
        int i9 = this.size;
        if (i9 != strBuilder.size) {
            return false;
        }
        char[] cArr = this.buffer;
        char[] cArr2 = strBuilder.buffer;
        for (int i10 = i9 - 1; i10 >= 0; i10--) {
            char c9 = cArr[i10];
            char c10 = cArr2[i10];
            if (c9 != c10 && Character.toUpperCase(c9) != Character.toUpperCase(c10)) {
                return false;
            }
        }
        return true;
    }

    public char[] getChars(char[] cArr) {
        int length = length();
        if (cArr == null || cArr.length < length) {
            cArr = new char[length];
        }
        System.arraycopy(this.buffer, 0, cArr, 0, length);
        return cArr;
    }

    public String getNewLineText() {
        return this.newLine;
    }

    public String getNullText() {
        return this.nullText;
    }

    public int hashCode() {
        char[] cArr = this.buffer;
        int i9 = 0;
        for (int i10 = this.size - 1; i10 >= 0; i10--) {
            i9 = (i9 * 31) + cArr[i10];
        }
        return i9;
    }

    public int indexOf(char c9) {
        return indexOf(c9, 0);
    }

    public StrBuilder insert(int i9, Object obj) {
        return obj == null ? insert(i9, this.nullText) : insert(i9, obj.toString());
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int lastIndexOf(char c9) {
        return lastIndexOf(c9, this.size - 1);
    }

    public String leftString(int i9) {
        if (i9 <= 0) {
            return "";
        }
        int i10 = this.size;
        return i9 >= i10 ? new String(this.buffer, 0, i10) : new String(this.buffer, 0, i9);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.size;
    }

    public String midString(int i9, int i10) {
        int i11;
        if (i9 < 0) {
            i9 = 0;
        }
        return (i10 <= 0 || i9 >= (i11 = this.size)) ? "" : i11 <= i9 + i10 ? new String(this.buffer, i9, i11 - i9) : new String(this.buffer, i9, i10);
    }

    public StrBuilder minimizeCapacity() {
        if (this.buffer.length > length()) {
            char[] cArr = this.buffer;
            char[] cArr2 = new char[length()];
            this.buffer = cArr2;
            System.arraycopy(cArr, 0, cArr2, 0, this.size);
        }
        return this;
    }

    public StrBuilder replace(int i9, int i10, String str) {
        int iValidateRange = validateRange(i9, i10);
        replaceImpl(i9, iValidateRange, iValidateRange - i9, str, str == null ? 0 : str.length());
        return this;
    }

    public StrBuilder replaceAll(char c9, char c10) {
        if (c9 != c10) {
            for (int i9 = 0; i9 < this.size; i9++) {
                char[] cArr = this.buffer;
                if (cArr[i9] == c9) {
                    cArr[i9] = c10;
                }
            }
        }
        return this;
    }

    public StrBuilder replaceFirst(char c9, char c10) {
        if (c9 != c10) {
            int i9 = 0;
            while (true) {
                if (i9 >= this.size) {
                    break;
                }
                char[] cArr = this.buffer;
                if (cArr[i9] == c9) {
                    cArr[i9] = c10;
                    break;
                }
                i9++;
            }
        }
        return this;
    }

    public StrBuilder reverse() {
        int i9 = this.size;
        if (i9 == 0) {
            return this;
        }
        int i10 = i9 / 2;
        char[] cArr = this.buffer;
        int i11 = i9 - 1;
        int i12 = 0;
        while (i12 < i10) {
            char c9 = cArr[i12];
            cArr[i12] = cArr[i11];
            cArr[i11] = c9;
            i12++;
            i11--;
        }
        return this;
    }

    public String rightString(int i9) {
        if (i9 <= 0) {
            return "";
        }
        int i10 = this.size;
        return i9 >= i10 ? new String(this.buffer, 0, i10) : new String(this.buffer, i10 - i9, i9);
    }

    public StrBuilder setCharAt(int i9, char c9) {
        if (i9 < 0 || i9 >= length()) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        this.buffer[i9] = c9;
        return this;
    }

    public StrBuilder setLength(int i9) {
        if (i9 < 0) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        int i10 = this.size;
        if (i9 < i10) {
            this.size = i9;
        } else if (i9 > i10) {
            ensureCapacity(i9);
            this.size = i9;
            for (int i11 = this.size; i11 < i9; i11++) {
                this.buffer[i11] = 0;
            }
        }
        return this;
    }

    public StrBuilder setNewLineText(String str) {
        this.newLine = str;
        return this;
    }

    public StrBuilder setNullText(String str) {
        if (str != null && str.isEmpty()) {
            str = null;
        }
        this.nullText = str;
        return this;
    }

    public int size() {
        return this.size;
    }

    public boolean startsWith(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        if (length > this.size) {
            return false;
        }
        for (int i9 = 0; i9 < length; i9++) {
            if (this.buffer[i9] != str.charAt(i9)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i9, int i10) {
        if (i9 < 0) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        if (i10 > this.size) {
            throw new StringIndexOutOfBoundsException(i10);
        }
        if (i9 <= i10) {
            return substring(i9, i10);
        }
        throw new StringIndexOutOfBoundsException(i10 - i9);
    }

    public String substring(int i9) {
        return substring(i9, this.size);
    }

    public char[] toCharArray() {
        int i9 = this.size;
        if (i9 == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[i9];
        System.arraycopy(this.buffer, 0, cArr, 0, i9);
        return cArr;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.buffer, 0, this.size);
    }

    public StringBuffer toStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer(this.size);
        stringBuffer.append(this.buffer, 0, this.size);
        return stringBuffer;
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder(this.size);
        sb.append(this.buffer, 0, this.size);
        return sb;
    }

    public StrBuilder trim() {
        int i9 = this.size;
        if (i9 == 0) {
            return this;
        }
        char[] cArr = this.buffer;
        int i10 = 0;
        while (i10 < i9 && cArr[i10] <= ' ') {
            i10++;
        }
        while (i10 < i9 && cArr[i9 - 1] <= ' ') {
            i9--;
        }
        int i11 = this.size;
        if (i9 < i11) {
            delete(i9, i11);
        }
        if (i10 > 0) {
            delete(0, i10);
        }
        return this;
    }

    public void validateIndex(int i9) {
        if (i9 < 0 || i9 > this.size) {
            throw new StringIndexOutOfBoundsException(i9);
        }
    }

    public int validateRange(int i9, int i10) {
        if (i9 < 0) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        int i11 = this.size;
        if (i10 > i11) {
            i10 = i11;
        }
        if (i9 <= i10) {
            return i10;
        }
        throw new StringIndexOutOfBoundsException("end < start");
    }

    public class StrBuilderReader extends Reader {
        private int mark;
        private int pos;

        public StrBuilderReader() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Reader
        public void mark(int i9) {
            this.mark = this.pos;
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.Reader
        public int read() {
            if (!ready()) {
                return -1;
            }
            StrBuilder strBuilder = StrBuilder.this;
            int i9 = this.pos;
            this.pos = i9 + 1;
            return strBuilder.charAt(i9);
        }

        @Override // java.io.Reader
        public boolean ready() {
            return this.pos < StrBuilder.this.size();
        }

        @Override // java.io.Reader
        public void reset() {
            this.pos = this.mark;
        }

        @Override // java.io.Reader
        public long skip(long j9) {
            if (this.pos + j9 > StrBuilder.this.size()) {
                j9 = StrBuilder.this.size() - this.pos;
            }
            if (j9 < 0) {
                return 0L;
            }
            this.pos = (int) (this.pos + j9);
            return j9;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i9, int i10) {
            int i11;
            if (i9 < 0 || i10 < 0 || i9 > cArr.length || (i11 = i9 + i10) > cArr.length || i11 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i10 == 0) {
                return 0;
            }
            if (this.pos >= StrBuilder.this.size()) {
                return -1;
            }
            if (this.pos + i10 > StrBuilder.this.size()) {
                i10 = StrBuilder.this.size() - this.pos;
            }
            StrBuilder strBuilder = StrBuilder.this;
            int i12 = this.pos;
            strBuilder.getChars(i12, i12 + i10, cArr, i9);
            this.pos += i10;
            return i10;
        }
    }

    public StrBuilder(int i9) {
        this.buffer = new char[i9 <= 0 ? 32 : i9];
    }

    public StrBuilder appendSeparator(String str, String str2) {
        if (isEmpty()) {
            str = str2;
        }
        if (str != null) {
            append(str);
        }
        return this;
    }

    public StrBuilder appendln(String str) {
        return append(str).appendNewLine();
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public String build() {
        return toString();
    }

    public int indexOf(char c9, int i9) {
        if (i9 < 0) {
            i9 = 0;
        }
        if (i9 >= this.size) {
            return -1;
        }
        char[] cArr = this.buffer;
        while (i9 < this.size) {
            if (cArr[i9] == c9) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public int lastIndexOf(char c9, int i9) {
        int i10 = this.size;
        if (i9 >= i10) {
            i9 = i10 - 1;
        }
        if (i9 < 0) {
            return -1;
        }
        while (i9 >= 0) {
            if (this.buffer[i9] == c9) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public String substring(int i9, int i10) {
        return new String(this.buffer, i9, validateRange(i9, i10) - i9);
    }

    public StrBuilder appendln(String str, int i9, int i10) {
        return append(str, i9, i10).appendNewLine();
    }

    public StrBuilder insert(int i9, String str) {
        int length;
        validateIndex(i9);
        if (str == null) {
            str = this.nullText;
        }
        if (str != null && (length = str.length()) > 0) {
            int i10 = this.size + length;
            ensureCapacity(i10);
            char[] cArr = this.buffer;
            System.arraycopy(cArr, i9, cArr, i9 + length, this.size - i9);
            this.size = i10;
            str.getChars(0, length, this.buffer, i9);
        }
        return this;
    }

    public StrBuilder(String str) {
        if (str == null) {
            this.buffer = new char[32];
        } else {
            this.buffer = new char[str.length() + 32];
            append(str);
        }
    }

    public StrBuilder append(Object obj) {
        if (obj == null) {
            return appendNull();
        }
        return append(obj.toString());
    }

    public StrBuilder appendAll(Iterable<?> iterable) {
        if (iterable != null) {
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                append(it.next());
            }
        }
        return this;
    }

    public StrBuilder appendSeparator(char c9) {
        if (size() > 0) {
            append(c9);
        }
        return this;
    }

    public StrBuilder appendln(String str, Object... objArr) {
        return append(str, objArr).appendNewLine();
    }

    public boolean contains(String str) {
        return indexOf(str, 0) >= 0;
    }

    public StrBuilder deleteFirst(String str) {
        int iIndexOf;
        int length = str == null ? 0 : str.length();
        if (length > 0 && (iIndexOf = indexOf(str, 0)) >= 0) {
            deleteImpl(iIndexOf, iIndexOf + length, length);
        }
        return this;
    }

    public int lastIndexOf(String str) {
        return lastIndexOf(str, this.size - 1);
    }

    public StrBuilder replace(StrMatcher strMatcher, String str, int i9, int i10, int i11) {
        return replaceImpl(strMatcher, str, i9, validateRange(i9, i10), i11);
    }

    public StrBuilder replaceAll(String str, String str2) {
        int length = str == null ? 0 : str.length();
        if (length > 0) {
            int length2 = str2 == null ? 0 : str2.length();
            int iIndexOf = indexOf(str, 0);
            while (iIndexOf >= 0) {
                replaceImpl(iIndexOf, iIndexOf + length, length, str2, length2);
                iIndexOf = indexOf(str, iIndexOf + length2);
            }
        }
        return this;
    }

    public StrBuilder replaceFirst(String str, String str2) {
        int iIndexOf;
        int length = str == null ? 0 : str.length();
        if (length > 0 && (iIndexOf = indexOf(str, 0)) >= 0) {
            replaceImpl(iIndexOf, iIndexOf + length, length, str2, str2 != null ? str2.length() : 0);
        }
        return this;
    }

    public StrBuilder appendln(StringBuffer stringBuffer) {
        return append(stringBuffer).appendNewLine();
    }

    public boolean contains(StrMatcher strMatcher) {
        return indexOf(strMatcher, 0) >= 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof StrBuilder) {
            return equals((StrBuilder) obj);
        }
        return false;
    }

    public void getChars(int i9, int i10, char[] cArr, int i11) {
        if (i9 >= 0) {
            if (i10 < 0 || i10 > length()) {
                throw new StringIndexOutOfBoundsException(i10);
            }
            if (i9 <= i10) {
                System.arraycopy(this.buffer, i9, cArr, i11, i10 - i9);
                return;
            }
            throw new StringIndexOutOfBoundsException("end < start");
        }
        throw new StringIndexOutOfBoundsException(i9);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0036, code lost:
    
        r9 = r9 - 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int lastIndexOf(String str, int i9) {
        int i10 = this.size;
        if (i9 >= i10) {
            i9 = i10 - 1;
        }
        if (str != null && i9 >= 0) {
            int length = str.length();
            if (length > 0 && length <= this.size) {
                if (length == 1) {
                    return lastIndexOf(str.charAt(0), i9);
                }
                int i11 = (i9 - length) + 1;
                while (i11 >= 0) {
                    for (int i12 = 0; i12 < length; i12++) {
                        if (str.charAt(i12) != this.buffer[i11 + i12]) {
                            break;
                        }
                    }
                    return i11;
                }
            }
            if (length == 0) {
                return i9;
            }
        }
        return -1;
    }

    public char[] toCharArray(int i9, int i10) {
        int iValidateRange = validateRange(i9, i10) - i9;
        if (iValidateRange == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[iValidateRange];
        System.arraycopy(this.buffer, i9, cArr, 0, iValidateRange);
        return cArr;
    }

    private StrBuilder replaceImpl(StrMatcher strMatcher, String str, int i9, int i10, int i11) {
        if (strMatcher != null && this.size != 0) {
            int length = str == null ? 0 : str.length();
            char[] cArr = this.buffer;
            int i12 = i9;
            while (i12 < i10 && i11 != 0) {
                int iIsMatch = strMatcher.isMatch(cArr, i12, i9, i10);
                if (iIsMatch > 0) {
                    replaceImpl(i12, i12 + iIsMatch, iIsMatch, str, length);
                    i10 = (i10 - iIsMatch) + length;
                    i12 = (i12 + length) - 1;
                    if (i11 > 0) {
                        i11--;
                    }
                }
                i12++;
            }
        }
        return this;
    }

    @Override // java.lang.Appendable
    public StrBuilder append(CharSequence charSequence) {
        if (charSequence == null) {
            return appendNull();
        }
        return append(charSequence.toString());
    }

    public StrBuilder appendAll(Iterator<?> it) {
        if (it != null) {
            while (it.hasNext()) {
                append(it.next());
            }
        }
        return this;
    }

    public StrBuilder appendSeparator(char c9, char c10) {
        if (size() > 0) {
            append(c9);
        } else {
            append(c10);
        }
        return this;
    }

    public StrBuilder appendln(StringBuilder sb) {
        return append(sb).appendNewLine();
    }

    public StrBuilder deleteAll(String str) {
        int length = str == null ? 0 : str.length();
        if (length > 0) {
            int iIndexOf = indexOf(str, 0);
            while (iIndexOf >= 0) {
                deleteImpl(iIndexOf, iIndexOf + length, length);
                iIndexOf = indexOf(str, iIndexOf);
            }
        }
        return this;
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public StrBuilder appendWithSeparators(Iterable<?> iterable, String str) {
        if (iterable != null) {
            String string = ObjectUtils.toString(str);
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(string);
                }
            }
        }
        return this;
    }

    public StrBuilder appendln(StringBuilder sb, int i9, int i10) {
        return append(sb, i9, i10).appendNewLine();
    }

    public StrBuilder deleteFirst(StrMatcher strMatcher) {
        return replace(strMatcher, null, 0, this.size, 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0037, code lost:
    
        r10 = r10 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int indexOf(String str, int i9) {
        if (i9 < 0) {
            i9 = 0;
        }
        if (str != null && i9 < this.size) {
            int length = str.length();
            if (length == 1) {
                return indexOf(str.charAt(0), i9);
            }
            if (length == 0) {
                return i9;
            }
            int i10 = this.size;
            if (length > i10) {
                return -1;
            }
            char[] cArr = this.buffer;
            int i11 = (i10 - length) + 1;
            while (i9 < i11) {
                for (int i12 = 0; i12 < length; i12++) {
                    if (str.charAt(i12) != cArr[i9 + i12]) {
                        break;
                    }
                }
                return i9;
            }
        }
        return -1;
    }

    @Override // java.lang.Appendable
    public StrBuilder append(CharSequence charSequence, int i9, int i10) {
        if (charSequence == null) {
            return appendNull();
        }
        return append(charSequence.toString(), i9, i10);
    }

    public StrBuilder appendFixedWidthPadLeft(int i9, int i10, char c9) {
        return appendFixedWidthPadLeft(String.valueOf(i9), i10, c9);
    }

    public StrBuilder appendFixedWidthPadRight(int i9, int i10, char c9) {
        return appendFixedWidthPadRight(String.valueOf(i9), i10, c9);
    }

    public StrBuilder appendln(StringBuffer stringBuffer, int i9, int i10) {
        return append(stringBuffer, i9, i10).appendNewLine();
    }

    public StrBuilder replaceFirst(StrMatcher strMatcher, String str) {
        return replace(strMatcher, str, 0, this.size, 1);
    }

    public StrBuilder appendSeparator(String str, int i9) {
        if (str != null && i9 > 0) {
            append(str);
        }
        return this;
    }

    public StrBuilder appendln(StrBuilder strBuilder) {
        return append(strBuilder).appendNewLine();
    }

    public StrBuilder replaceAll(StrMatcher strMatcher, String str) {
        return replace(strMatcher, str, 0, this.size, -1);
    }

    public StrBuilder append(String str) {
        if (str == null) {
            return appendNull();
        }
        int length = str.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            str.getChars(0, length, this.buffer, length2);
            this.size += length;
        }
        return this;
    }

    public StrBuilder appendSeparator(char c9, int i9) {
        if (i9 > 0) {
            append(c9);
        }
        return this;
    }

    public StrBuilder appendln(StrBuilder strBuilder, int i9, int i10) {
        return append(strBuilder, i9, i10).appendNewLine();
    }

    public StrBuilder deleteAll(StrMatcher strMatcher) {
        return replace(strMatcher, null, 0, this.size, -1);
    }

    public int lastIndexOf(StrMatcher strMatcher) {
        return lastIndexOf(strMatcher, this.size);
    }

    public StrBuilder appendln(char[] cArr) {
        return append(cArr).appendNewLine();
    }

    public StrBuilder insert(int i9, char[] cArr) {
        validateIndex(i9);
        if (cArr == null) {
            return insert(i9, this.nullText);
        }
        int length = cArr.length;
        if (length > 0) {
            ensureCapacity(this.size + length);
            char[] cArr2 = this.buffer;
            System.arraycopy(cArr2, i9, cArr2, i9 + length, this.size - i9);
            System.arraycopy(cArr, 0, this.buffer, i9, length);
            this.size += length;
        }
        return this;
    }

    public int lastIndexOf(StrMatcher strMatcher, int i9) {
        int i10 = this.size;
        if (i9 >= i10) {
            i9 = i10 - 1;
        }
        if (strMatcher != null && i9 >= 0) {
            char[] cArr = this.buffer;
            int i11 = i9 + 1;
            while (i9 >= 0) {
                if (strMatcher.isMatch(cArr, i9, 0, i11) > 0) {
                    return i9;
                }
                i9--;
            }
        }
        return -1;
    }

    public StrBuilder appendln(char[] cArr, int i9, int i10) {
        return append(cArr, i9, i10).appendNewLine();
    }

    public StrBuilder appendWithSeparators(Iterator<?> it, String str) {
        if (it != null) {
            String string = ObjectUtils.toString(str);
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(string);
                }
            }
        }
        return this;
    }

    public StrBuilder appendln(boolean z8) {
        return append(z8).appendNewLine();
    }

    public int indexOf(StrMatcher strMatcher) {
        return indexOf(strMatcher, 0);
    }

    public StrBuilder appendln(char c9) {
        return append(c9).appendNewLine();
    }

    public int indexOf(StrMatcher strMatcher, int i9) {
        int i10;
        if (i9 < 0) {
            i9 = 0;
        }
        if (strMatcher != null && i9 < (i10 = this.size)) {
            char[] cArr = this.buffer;
            for (int i11 = i9; i11 < i10; i11++) {
                if (strMatcher.isMatch(cArr, i11, i9, i10) > 0) {
                    return i11;
                }
            }
        }
        return -1;
    }

    public StrBuilder appendln(int i9) {
        return append(i9).appendNewLine();
    }

    public StrBuilder append(String str, int i9, int i10) {
        int i11;
        if (str == null) {
            return appendNull();
        }
        if (i9 >= 0 && i9 <= str.length()) {
            if (i10 < 0 || (i11 = i9 + i10) > str.length()) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            if (i10 > 0) {
                int length = length();
                ensureCapacity(length + i10);
                str.getChars(i9, i11, this.buffer, length);
                this.size += i10;
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
    }

    public StrBuilder appendln(long j9) {
        return append(j9).appendNewLine();
    }

    public StrBuilder appendln(float f9) {
        return append(f9).appendNewLine();
    }

    public StrBuilder appendln(double d9) {
        return append(d9).appendNewLine();
    }

    public StrBuilder insert(int i9, char[] cArr, int i10, int i11) {
        validateIndex(i9);
        if (cArr == null) {
            return insert(i9, this.nullText);
        }
        if (i10 >= 0 && i10 <= cArr.length) {
            if (i11 < 0 || i10 + i11 > cArr.length) {
                throw new StringIndexOutOfBoundsException("Invalid length: " + i11);
            }
            if (i11 > 0) {
                ensureCapacity(this.size + i11);
                char[] cArr2 = this.buffer;
                System.arraycopy(cArr2, i9, cArr2, i9 + i11, this.size - i9);
                System.arraycopy(cArr, i10, this.buffer, i9, i11);
                this.size += i11;
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException("Invalid offset: " + i10);
    }

    public StrBuilder append(String str, Object... objArr) {
        return append(String.format(str, objArr));
    }

    public StrBuilder append(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return appendNull();
        }
        int length = stringBuffer.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            stringBuffer.getChars(0, length, this.buffer, length2);
            this.size += length;
        }
        return this;
    }

    public StrBuilder insert(int i9, boolean z8) {
        validateIndex(i9);
        if (z8) {
            ensureCapacity(this.size + 4);
            char[] cArr = this.buffer;
            System.arraycopy(cArr, i9, cArr, i9 + 4, this.size - i9);
            char[] cArr2 = this.buffer;
            int i10 = i9 + 1;
            cArr2[i9] = 't';
            int i11 = i10 + 1;
            cArr2[i10] = 'r';
            cArr2[i11] = 'u';
            cArr2[i11 + 1] = 'e';
            this.size += 4;
        } else {
            ensureCapacity(this.size + 5);
            char[] cArr3 = this.buffer;
            System.arraycopy(cArr3, i9, cArr3, i9 + 5, this.size - i9);
            char[] cArr4 = this.buffer;
            int i12 = i9 + 1;
            cArr4[i9] = 'f';
            int i13 = i12 + 1;
            cArr4[i12] = 'a';
            int i14 = i13 + 1;
            cArr4[i13] = 'l';
            cArr4[i14] = 's';
            cArr4[i14 + 1] = 'e';
            this.size += 5;
        }
        return this;
    }

    public StrBuilder append(StringBuffer stringBuffer, int i9, int i10) {
        int i11;
        if (stringBuffer == null) {
            return appendNull();
        }
        if (i9 >= 0 && i9 <= stringBuffer.length()) {
            if (i10 < 0 || (i11 = i9 + i10) > stringBuffer.length()) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            if (i10 > 0) {
                int length = length();
                ensureCapacity(length + i10);
                stringBuffer.getChars(i9, i11, this.buffer, length);
                this.size += i10;
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
    }

    public StrBuilder append(StringBuilder sb) {
        if (sb == null) {
            return appendNull();
        }
        int length = sb.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            sb.getChars(0, length, this.buffer, length2);
            this.size += length;
        }
        return this;
    }

    public StrBuilder insert(int i9, char c9) {
        validateIndex(i9);
        ensureCapacity(this.size + 1);
        char[] cArr = this.buffer;
        System.arraycopy(cArr, i9, cArr, i9 + 1, this.size - i9);
        this.buffer[i9] = c9;
        this.size++;
        return this;
    }

    public StrBuilder append(StringBuilder sb, int i9, int i10) {
        int i11;
        if (sb == null) {
            return appendNull();
        }
        if (i9 >= 0 && i9 <= sb.length()) {
            if (i10 < 0 || (i11 = i9 + i10) > sb.length()) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            if (i10 > 0) {
                int length = length();
                ensureCapacity(length + i10);
                sb.getChars(i9, i11, this.buffer, length);
                this.size += i10;
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
    }

    public StrBuilder insert(int i9, int i10) {
        return insert(i9, String.valueOf(i10));
    }

    public StrBuilder insert(int i9, long j9) {
        return insert(i9, String.valueOf(j9));
    }

    public StrBuilder insert(int i9, float f9) {
        return insert(i9, String.valueOf(f9));
    }

    public StrBuilder insert(int i9, double d9) {
        return insert(i9, String.valueOf(d9));
    }

    public StrBuilder append(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return appendNull();
        }
        int length = strBuilder.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            System.arraycopy(strBuilder.buffer, 0, this.buffer, length2, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(StrBuilder strBuilder, int i9, int i10) {
        int i11;
        if (strBuilder == null) {
            return appendNull();
        }
        if (i9 >= 0 && i9 <= strBuilder.length()) {
            if (i10 < 0 || (i11 = i9 + i10) > strBuilder.length()) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            if (i10 > 0) {
                int length = length();
                ensureCapacity(length + i10);
                strBuilder.getChars(i9, i11, this.buffer, length);
                this.size += i10;
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
    }

    public StrBuilder append(char[] cArr) {
        if (cArr == null) {
            return appendNull();
        }
        int length = cArr.length;
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            System.arraycopy(cArr, 0, this.buffer, length2, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(char[] cArr, int i9, int i10) {
        if (cArr == null) {
            return appendNull();
        }
        if (i9 >= 0 && i9 <= cArr.length) {
            if (i10 < 0 || i9 + i10 > cArr.length) {
                throw new StringIndexOutOfBoundsException("Invalid length: " + i10);
            }
            if (i10 > 0) {
                int length = length();
                ensureCapacity(length + i10);
                System.arraycopy(cArr, i9, this.buffer, length, i10);
                this.size += i10;
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException("Invalid startIndex: " + i10);
    }

    public StrBuilder append(boolean z8) {
        if (z8) {
            ensureCapacity(this.size + 4);
            char[] cArr = this.buffer;
            int i9 = this.size;
            int i10 = i9 + 1;
            cArr[i9] = 't';
            int i11 = i10 + 1;
            cArr[i10] = 'r';
            int i12 = i11 + 1;
            cArr[i11] = 'u';
            this.size = i12 + 1;
            cArr[i12] = 'e';
        } else {
            ensureCapacity(this.size + 5);
            char[] cArr2 = this.buffer;
            int i13 = this.size;
            int i14 = i13 + 1;
            cArr2[i13] = 'f';
            int i15 = i14 + 1;
            cArr2[i14] = 'a';
            int i16 = i15 + 1;
            cArr2[i15] = 'l';
            int i17 = i16 + 1;
            cArr2[i16] = 's';
            this.size = i17 + 1;
            cArr2[i17] = 'e';
        }
        return this;
    }

    @Override // java.lang.Appendable
    public StrBuilder append(char c9) {
        ensureCapacity(length() + 1);
        char[] cArr = this.buffer;
        int i9 = this.size;
        this.size = i9 + 1;
        cArr[i9] = c9;
        return this;
    }

    public StrBuilder append(int i9) {
        return append(String.valueOf(i9));
    }

    public StrBuilder append(long j9) {
        return append(String.valueOf(j9));
    }

    public StrBuilder append(float f9) {
        return append(String.valueOf(f9));
    }

    public StrBuilder append(double d9) {
        return append(String.valueOf(d9));
    }
}
