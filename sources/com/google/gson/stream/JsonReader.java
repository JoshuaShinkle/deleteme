package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public class JsonReader implements Closeable {
    static final int BUFFER_SIZE = 1024;
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;

    /* renamed from: in */
    private final Reader f15590in;
    private int[] pathIndices;
    private String[] pathNames;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int[] stack;
    private boolean lenient = false;
    private final char[] buffer = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    int peeked = 0;
    private int stackSize = 0 + 1;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() { // from class: com.google.gson.stream.JsonReader.1
            @Override // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int iDoPeek = jsonReader.peeked;
                if (iDoPeek == 0) {
                    iDoPeek = jsonReader.doPeek();
                }
                if (iDoPeek == 13) {
                    jsonReader.peeked = 9;
                    return;
                }
                if (iDoPeek == 12) {
                    jsonReader.peeked = 8;
                    return;
                }
                if (iDoPeek == 14) {
                    jsonReader.peeked = 10;
                    return;
                }
                throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + jsonReader.locationString());
            }
        };
    }

    public JsonReader(Reader reader) {
        int[] iArr = new int[32];
        this.stack = iArr;
        iArr[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f15590in = reader;
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        int i9 = this.pos - 1;
        this.pos = i9;
        if (i9 + 5 <= this.limit || fillBuffer(5)) {
            int i10 = this.pos;
            char[] cArr = this.buffer;
            if (cArr[i10] == ')' && cArr[i10 + 1] == ']' && cArr[i10 + 2] == '}' && cArr[i10 + 3] == '\'' && cArr[i10 + 4] == '\n') {
                this.pos = i10 + 5;
            }
        }
    }

    private boolean fillBuffer(int i9) throws IOException {
        int i10;
        int i11;
        char[] cArr = this.buffer;
        int i12 = this.lineStart;
        int i13 = this.pos;
        this.lineStart = i12 - i13;
        int i14 = this.limit;
        if (i14 != i13) {
            int i15 = i14 - i13;
            this.limit = i15;
            System.arraycopy(cArr, i13, cArr, 0, i15);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.f15590in;
            int i16 = this.limit;
            int i17 = reader.read(cArr, i16, cArr.length - i16);
            if (i17 == -1) {
                return false;
            }
            i10 = this.limit + i17;
            this.limit = i10;
            if (this.lineNumber == 0 && (i11 = this.lineStart) == 0 && i10 > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = i11 + 1;
                i9++;
            }
        } while (i10 < i9);
        return true;
    }

    private String getPath(boolean z8) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i9 = 0;
        while (true) {
            int i10 = this.stackSize;
            if (i9 >= i10) {
                return sb.toString();
            }
            int i11 = this.stack[i9];
            if (i11 == 1 || i11 == 2) {
                int i12 = this.pathIndices[i9];
                if (z8 && i12 > 0 && i9 == i10 - 1) {
                    i12--;
                }
                sb.append('[');
                sb.append(i12);
                sb.append(']');
            } else if (i11 == 3 || i11 == 4 || i11 == 5) {
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                String str = this.pathNames[i9];
                if (str != null) {
                    sb.append(str);
                }
            }
            i9++;
        }
    }

    private boolean isLiteral(char c9) throws IOException {
        if (c9 == '\t' || c9 == '\n' || c9 == '\f' || c9 == '\r' || c9 == ' ') {
            return false;
        }
        if (c9 != '#') {
            if (c9 == ',') {
                return false;
            }
            if (c9 != '/' && c9 != '=') {
                if (c9 == '{' || c9 == '}' || c9 == ':') {
                    return false;
                }
                if (c9 != ';') {
                    switch (c9) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    private int nextNonWhitespace(boolean z8) throws IOException {
        char[] cArr = this.buffer;
        int i9 = this.pos;
        int i10 = this.limit;
        while (true) {
            if (i9 == i10) {
                this.pos = i9;
                if (!fillBuffer(1)) {
                    if (!z8) {
                        return -1;
                    }
                    throw new EOFException("End of input" + locationString());
                }
                i9 = this.pos;
                i10 = this.limit;
            }
            int i11 = i9 + 1;
            char c9 = cArr[i9];
            if (c9 == '\n') {
                this.lineNumber++;
                this.lineStart = i11;
            } else if (c9 != ' ' && c9 != '\r' && c9 != '\t') {
                if (c9 == '/') {
                    this.pos = i11;
                    if (i11 == i10) {
                        this.pos = i11 - 1;
                        boolean zFillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!zFillBuffer) {
                            return c9;
                        }
                    }
                    checkLenient();
                    int i12 = this.pos;
                    char c10 = cArr[i12];
                    if (c10 == '*') {
                        this.pos = i12 + 1;
                        if (!skipTo("*/")) {
                            throw syntaxError("Unterminated comment");
                        }
                        i9 = this.pos + 2;
                        i10 = this.limit;
                    } else {
                        if (c10 != '/') {
                            return c9;
                        }
                        this.pos = i12 + 1;
                        skipToEndOfLine();
                        i9 = this.pos;
                        i10 = this.limit;
                    }
                } else {
                    if (c9 != '#') {
                        this.pos = i11;
                        return c9;
                    }
                    this.pos = i11;
                    checkLenient();
                    skipToEndOfLine();
                    i9 = this.pos;
                    i10 = this.limit;
                }
            }
            i9 = i11;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006c, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r9.pos = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String nextQuotedValue(char c9) throws IOException {
        char[] cArr = this.buffer;
        StringBuilder sb = null;
        do {
            int i9 = this.pos;
            int i10 = this.limit;
            while (true) {
                int i11 = i10;
                int i12 = i9;
                while (i9 < i11) {
                    int i13 = i9 + 1;
                    char c10 = cArr[i9];
                    if (c10 == c9) {
                        this.pos = i13;
                        int i14 = (i13 - i12) - 1;
                        if (sb == null) {
                            return new String(cArr, i12, i14);
                        }
                        sb.append(cArr, i12, i14);
                        return sb.toString();
                    }
                    if (c10 == '\\') {
                        this.pos = i13;
                        int i15 = (i13 - i12) - 1;
                        if (sb == null) {
                            sb = new StringBuilder(Math.max((i15 + 1) * 2, 16));
                        }
                        sb.append(cArr, i12, i15);
                        sb.append(readEscapeCharacter());
                        i9 = this.pos;
                        i10 = this.limit;
                    } else {
                        if (c10 == '\n') {
                            this.lineNumber++;
                            this.lineStart = i13;
                        }
                        i9 = i13;
                    }
                }
                break;
            }
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        checkLenient();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String nextUnquotedValue() throws IOException {
        String string;
        StringBuilder sb = null;
        int i9 = 0;
        do {
            int i10 = 0;
            while (true) {
                int i11 = this.pos;
                if (i11 + i10 < this.limit) {
                    char c9 = this.buffer[i11 + i10];
                    if (c9 != '\t' && c9 != '\n' && c9 != '\f' && c9 != '\r' && c9 != ' ') {
                        if (c9 != '#') {
                            if (c9 != ',') {
                                if (c9 != '/' && c9 != '=') {
                                    if (c9 != '{' && c9 != '}' && c9 != ':') {
                                        if (c9 != ';') {
                                            switch (c9) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i10++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (i10 >= this.buffer.length) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i10, 16));
                    }
                    sb.append(this.buffer, this.pos, i10);
                    this.pos += i10;
                } else if (fillBuffer(i10 + 1)) {
                }
            }
            i9 = i10;
            if (sb != null) {
                string = new String(this.buffer, this.pos, i9);
            } else {
                sb.append(this.buffer, this.pos, i9);
                string = sb.toString();
            }
            this.pos += i9;
            return string;
        } while (fillBuffer(1));
        if (sb != null) {
        }
        this.pos += i9;
        return string;
    }

    private int peekKeyword() {
        String str;
        String str2;
        int i9;
        char c9 = this.buffer[this.pos];
        if (c9 == 't' || c9 == 'T') {
            str = "true";
            str2 = "TRUE";
            i9 = 5;
        } else if (c9 == 'f' || c9 == 'F') {
            str = "false";
            str2 = "FALSE";
            i9 = 6;
        } else {
            if (c9 != 'n' && c9 != 'N') {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i9 = 7;
        }
        int length = str.length();
        for (int i10 = 1; i10 < length; i10++) {
            if (this.pos + i10 >= this.limit && !fillBuffer(i10 + 1)) {
                return 0;
            }
            char c10 = this.buffer[this.pos + i10];
            if (c10 != str.charAt(i10) && c10 != str2.charAt(i10)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i9;
        return i9;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0091, code lost:
    
        if (isLiteral(r14) != false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0093, code lost:
    
        if (r9 != 2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0095, code lost:
    
        if (r10 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009b, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009d, code lost:
    
        if (r13 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a3, code lost:
    
        if (r11 != 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a5, code lost:
    
        if (r13 != false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a7, code lost:
    
        if (r13 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00aa, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ab, code lost:
    
        r18.peekedLong = r11;
        r18.pos += r8;
        r18.peeked = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b6, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b7, code lost:
    
        if (r9 == 2) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ba, code lost:
    
        if (r9 == 4) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00bd, code lost:
    
        if (r9 != 7) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c0, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c2, code lost:
    
        r18.peekedNumberLength = r8;
        r18.peeked = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c8, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00c9, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int peekNumber() {
        char c9;
        char c10;
        char[] cArr = this.buffer;
        int i9 = this.pos;
        int i10 = this.limit;
        int i11 = 0;
        int i12 = 0;
        char c11 = 0;
        boolean z8 = false;
        boolean z9 = true;
        long j9 = 0;
        while (true) {
            if (i9 + i12 != i10) {
                c9 = cArr[i9 + i12];
                if (c9 == '+') {
                    if (c9 == 'E' || c9 == 'e') {
                        i11 = 0;
                        if (c11 != 2 && c11 != 4) {
                            return 0;
                        }
                        c11 = 5;
                    } else if (c9 != '-') {
                        c10 = 3;
                        if (c9 == '.') {
                            i11 = 0;
                            if (c11 != 2) {
                                return 0;
                            }
                        } else {
                            if (c9 < '0' || c9 > '9') {
                                break;
                            }
                            if (c11 == 1 || c11 == 0) {
                                j9 = -(c9 - '0');
                                c11 = 2;
                            } else if (c11 == 2) {
                                if (j9 == 0) {
                                    return 0;
                                }
                                long j10 = (10 * j9) - (c9 - '0');
                                z9 &= j9 > MIN_INCOMPLETE_INTEGER || (j9 == MIN_INCOMPLETE_INTEGER && j10 < j9);
                                j9 = j10;
                            } else if (c11 == 3) {
                                i11 = 0;
                                c11 = 4;
                            } else if (c11 == 5 || c11 == 6) {
                                i11 = 0;
                                c11 = 7;
                            }
                            i11 = 0;
                        }
                    } else {
                        c10 = 6;
                        i11 = 0;
                        if (c11 == 0) {
                            c11 = 1;
                            z8 = true;
                        } else if (c11 != 5) {
                            return 0;
                        }
                    }
                    i12++;
                } else {
                    c10 = 6;
                    i11 = 0;
                    if (c11 != 5) {
                        return 0;
                    }
                }
                c11 = c10;
                i12++;
            } else {
                if (i12 == cArr.length) {
                    return i11;
                }
                if (!fillBuffer(i12 + 1)) {
                    break;
                }
                i9 = this.pos;
                i10 = this.limit;
                c9 = cArr[i9 + i12];
                if (c9 == '+') {
                }
                c11 = c10;
                i12++;
            }
        }
    }

    private void push(int i9) {
        int i10 = this.stackSize;
        int[] iArr = this.stack;
        if (i10 == iArr.length) {
            int i11 = i10 * 2;
            this.stack = Arrays.copyOf(iArr, i11);
            this.pathIndices = Arrays.copyOf(this.pathIndices, i11);
            this.pathNames = (String[]) Arrays.copyOf(this.pathNames, i11);
        }
        int[] iArr2 = this.stack;
        int i12 = this.stackSize;
        this.stackSize = i12 + 1;
        iArr2[i12] = i9;
    }

    private char readEscapeCharacter() throws IOException {
        int i9;
        int i10;
        if (this.pos == this.limit && !fillBuffer(1)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char[] cArr = this.buffer;
        int i11 = this.pos;
        int i12 = i11 + 1;
        this.pos = i12;
        char c9 = cArr[i11];
        if (c9 == '\n') {
            this.lineNumber++;
            this.lineStart = i12;
        } else if (c9 != '\"' && c9 != '\'' && c9 != '/' && c9 != '\\') {
            if (c9 == 'b') {
                return '\b';
            }
            if (c9 == 'f') {
                return '\f';
            }
            if (c9 == 'n') {
                return '\n';
            }
            if (c9 == 'r') {
                return CharUtils.f19105CR;
            }
            if (c9 == 't') {
                return '\t';
            }
            if (c9 != 'u') {
                throw syntaxError("Invalid escape sequence");
            }
            if (i12 + 4 > this.limit && !fillBuffer(4)) {
                throw syntaxError("Unterminated escape sequence");
            }
            int i13 = this.pos;
            int i14 = i13 + 4;
            char c10 = 0;
            while (i13 < i14) {
                char c11 = this.buffer[i13];
                char c12 = (char) (c10 << 4);
                if (c11 < '0' || c11 > '9') {
                    if (c11 >= 'a' && c11 <= 'f') {
                        i9 = c11 - 'a';
                    } else {
                        if (c11 < 'A' || c11 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                        }
                        i9 = c11 - 'A';
                    }
                    i10 = i9 + 10;
                } else {
                    i10 = c11 - '0';
                }
                c10 = (char) (c12 + i10);
                i13++;
            }
            this.pos += 4;
            return c10;
        }
        return c9;
    }

    private void skipQuotedValue(char c9) throws IOException {
        char[] cArr = this.buffer;
        do {
            int i9 = this.pos;
            int i10 = this.limit;
            while (i9 < i10) {
                int i11 = i9 + 1;
                char c10 = cArr[i9];
                if (c10 == c9) {
                    this.pos = i11;
                    return;
                }
                if (c10 == '\\') {
                    this.pos = i11;
                    readEscapeCharacter();
                    i9 = this.pos;
                    i10 = this.limit;
                } else {
                    if (c10 == '\n') {
                        this.lineNumber++;
                        this.lineStart = i11;
                    }
                    i9 = i11;
                }
            }
            this.pos = i9;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String str) {
        int length = str.length();
        while (true) {
            if (this.pos + length > this.limit && !fillBuffer(length)) {
                return false;
            }
            char[] cArr = this.buffer;
            int i9 = this.pos;
            if (cArr[i9] != '\n') {
                for (int i10 = 0; i10 < length; i10++) {
                    if (this.buffer[this.pos + i10] != str.charAt(i10)) {
                        break;
                    }
                }
                return true;
            }
            this.lineNumber++;
            this.lineStart = i9 + 1;
            this.pos++;
        }
    }

    private void skipToEndOfLine() {
        char c9;
        do {
            if (this.pos >= this.limit && !fillBuffer(1)) {
                return;
            }
            char[] cArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 + 1;
            this.pos = i10;
            c9 = cArr[i9];
            if (c9 == '\n') {
                this.lineNumber++;
                this.lineStart = i10;
                return;
            }
        } while (c9 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        checkLenient();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void skipUnquotedValue() throws IOException {
        do {
            int i9 = 0;
            while (true) {
                int i10 = this.pos;
                if (i10 + i9 < this.limit) {
                    char c9 = this.buffer[i10 + i9];
                    if (c9 != '\t' && c9 != '\n' && c9 != '\f' && c9 != '\r' && c9 != ' ') {
                        if (c9 != '#') {
                            if (c9 != ',') {
                                if (c9 != '/' && c9 != '=') {
                                    if (c9 != '{' && c9 != '}' && c9 != ':') {
                                        if (c9 != ';') {
                                            switch (c9) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i9++;
                                            }
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.pos = i10 + i9;
                }
            }
            this.pos += i9;
            return;
        } while (fillBuffer(1));
    }

    private IOException syntaxError(String str) throws MalformedJsonException {
        throw new MalformedJsonException(str + locationString());
    }

    public void beginArray() {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + locationString());
        }
    }

    public void beginObject() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 1) {
            push(3);
            this.peeked = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + locationString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.f15590in.close();
    }

    public int doPeek() throws IOException {
        int iNextNonWhitespace;
        int[] iArr = this.stack;
        int i9 = this.stackSize;
        int i10 = iArr[i9 - 1];
        if (i10 == 1) {
            iArr[i9 - 1] = 2;
        } else if (i10 == 2) {
            int iNextNonWhitespace2 = nextNonWhitespace(true);
            if (iNextNonWhitespace2 != 44) {
                if (iNextNonWhitespace2 != 59) {
                    if (iNextNonWhitespace2 != 93) {
                        throw syntaxError("Unterminated array");
                    }
                    this.peeked = 4;
                    return 4;
                }
                checkLenient();
            }
        } else {
            if (i10 == 3 || i10 == 5) {
                iArr[i9 - 1] = 4;
                if (i10 == 5 && (iNextNonWhitespace = nextNonWhitespace(true)) != 44) {
                    if (iNextNonWhitespace != 59) {
                        if (iNextNonWhitespace != 125) {
                            throw syntaxError("Unterminated object");
                        }
                        this.peeked = 2;
                        return 2;
                    }
                    checkLenient();
                }
                int iNextNonWhitespace3 = nextNonWhitespace(true);
                if (iNextNonWhitespace3 == 34) {
                    this.peeked = 13;
                    return 13;
                }
                if (iNextNonWhitespace3 == 39) {
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                }
                if (iNextNonWhitespace3 == 125) {
                    if (i10 == 5) {
                        throw syntaxError("Expected name");
                    }
                    this.peeked = 2;
                    return 2;
                }
                checkLenient();
                this.pos--;
                if (!isLiteral((char) iNextNonWhitespace3)) {
                    throw syntaxError("Expected name");
                }
                this.peeked = 14;
                return 14;
            }
            if (i10 == 4) {
                iArr[i9 - 1] = 5;
                int iNextNonWhitespace4 = nextNonWhitespace(true);
                if (iNextNonWhitespace4 != 58) {
                    if (iNextNonWhitespace4 != 61) {
                        throw syntaxError("Expected ':'");
                    }
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int i11 = this.pos;
                        if (cArr[i11] == '>') {
                            this.pos = i11 + 1;
                        }
                    }
                }
            } else if (i10 == 6) {
                if (this.lenient) {
                    consumeNonExecutePrefix();
                }
                this.stack[this.stackSize - 1] = 7;
            } else if (i10 == 7) {
                if (nextNonWhitespace(false) == -1) {
                    this.peeked = 17;
                    return 17;
                }
                checkLenient();
                this.pos--;
            } else if (i10 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iNextNonWhitespace5 = nextNonWhitespace(true);
        if (iNextNonWhitespace5 == 34) {
            this.peeked = 9;
            return 9;
        }
        if (iNextNonWhitespace5 == 39) {
            checkLenient();
            this.peeked = 8;
            return 8;
        }
        if (iNextNonWhitespace5 != 44 && iNextNonWhitespace5 != 59) {
            if (iNextNonWhitespace5 == 91) {
                this.peeked = 3;
                return 3;
            }
            if (iNextNonWhitespace5 != 93) {
                if (iNextNonWhitespace5 == 123) {
                    this.peeked = 1;
                    return 1;
                }
                this.pos--;
                int iPeekKeyword = peekKeyword();
                if (iPeekKeyword != 0) {
                    return iPeekKeyword;
                }
                int iPeekNumber = peekNumber();
                if (iPeekNumber != 0) {
                    return iPeekNumber;
                }
                if (!isLiteral(this.buffer[this.pos])) {
                    throw syntaxError("Expected value");
                }
                checkLenient();
                this.peeked = 10;
                return 10;
            }
            if (i10 == 1) {
                this.peeked = 4;
                return 4;
            }
        }
        if (i10 != 1 && i10 != 2) {
            throw syntaxError("Unexpected value");
        }
        checkLenient();
        this.pos--;
        this.peeked = 7;
        return 7;
    }

    public void endArray() {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + peek() + locationString());
        }
        int i9 = this.stackSize - 1;
        this.stackSize = i9;
        int[] iArr = this.pathIndices;
        int i10 = i9 - 1;
        iArr[i10] = iArr[i10] + 1;
        this.peeked = 0;
    }

    public void endObject() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + peek() + locationString());
        }
        int i9 = this.stackSize - 1;
        this.stackSize = i9;
        this.pathNames[i9] = null;
        int[] iArr = this.pathIndices;
        int i10 = i9 - 1;
        iArr[i10] = iArr[i10] + 1;
        this.peeked = 0;
    }

    public String getPreviousPath() {
        return getPath(true);
    }

    public boolean hasNext() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        return (iDoPeek == 2 || iDoPeek == 4 || iDoPeek == 17) ? false : true;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public String locationString() {
        return " at line " + (this.lineNumber + 1) + " column " + ((this.pos - this.lineStart) + 1) + " path " + getPath();
    }

    public boolean nextBoolean() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i9 = this.stackSize - 1;
            iArr[i9] = iArr[i9] + 1;
            return true;
        }
        if (iDoPeek == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i10 = this.stackSize - 1;
            iArr2[i10] = iArr2[i10] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + peek() + locationString());
    }

    public double nextDouble() throws IOException, NumberFormatException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i9 = this.stackSize - 1;
            iArr[i9] = iArr[i9] + 1;
            return this.peekedLong;
        }
        if (iDoPeek == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (iDoPeek == 8 || iDoPeek == 9) {
            this.peekedString = nextQuotedValue(iDoPeek == 8 ? '\'' : '\"');
        } else if (iDoPeek == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (iDoPeek != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + locationString());
        }
        this.peeked = 11;
        double d9 = Double.parseDouble(this.peekedString);
        if (!this.lenient && (Double.isNaN(d9) || Double.isInfinite(d9))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d9 + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr2 = this.pathIndices;
        int i10 = this.stackSize - 1;
        iArr2[i10] = iArr2[i10] + 1;
        return d9;
    }

    public int nextInt() throws IOException, NumberFormatException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 15) {
            long j9 = this.peekedLong;
            int i9 = (int) j9;
            if (j9 == i9) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i10 = this.stackSize - 1;
                iArr[i10] = iArr[i10] + 1;
                return i9;
            }
            throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
        }
        if (iDoPeek == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            if (iDoPeek != 8 && iDoPeek != 9 && iDoPeek != 10) {
                throw new IllegalStateException("Expected an int but was " + peek() + locationString());
            }
            if (iDoPeek == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(iDoPeek == 8 ? '\'' : '\"');
            }
            try {
                int i11 = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i12 = this.stackSize - 1;
                iArr2[i12] = iArr2[i12] + 1;
                return i11;
            } catch (NumberFormatException unused) {
            }
        }
        this.peeked = 11;
        double d9 = Double.parseDouble(this.peekedString);
        int i13 = (int) d9;
        if (i13 != d9) {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i14 = this.stackSize - 1;
        iArr3[i14] = iArr3[i14] + 1;
        return i13;
    }

    public long nextLong() throws IOException, NumberFormatException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i9 = this.stackSize - 1;
            iArr[i9] = iArr[i9] + 1;
            return this.peekedLong;
        }
        if (iDoPeek == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            if (iDoPeek != 8 && iDoPeek != 9 && iDoPeek != 10) {
                throw new IllegalStateException("Expected a long but was " + peek() + locationString());
            }
            if (iDoPeek == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(iDoPeek == 8 ? '\'' : '\"');
            }
            try {
                long j9 = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i10 = this.stackSize - 1;
                iArr2[i10] = iArr2[i10] + 1;
                return j9;
            } catch (NumberFormatException unused) {
            }
        }
        this.peeked = 11;
        double d9 = Double.parseDouble(this.peekedString);
        long j10 = (long) d9;
        if (j10 != d9) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i11 = this.stackSize - 1;
        iArr3[i11] = iArr3[i11] + 1;
        return j10;
    }

    public String nextName() throws IOException {
        String strNextQuotedValue;
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 14) {
            strNextQuotedValue = nextUnquotedValue();
        } else if (iDoPeek == 12) {
            strNextQuotedValue = nextQuotedValue('\'');
        } else {
            if (iDoPeek != 13) {
                throw new IllegalStateException("Expected a name but was " + peek() + locationString());
            }
            strNextQuotedValue = nextQuotedValue('\"');
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = strNextQuotedValue;
        return strNextQuotedValue;
    }

    public void nextNull() {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i9 = this.stackSize - 1;
            iArr[i9] = iArr[i9] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + peek() + locationString());
    }

    public String nextString() throws IOException {
        String str;
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 10) {
            str = nextUnquotedValue();
        } else if (iDoPeek == 8) {
            str = nextQuotedValue('\'');
        } else if (iDoPeek == 9) {
            str = nextQuotedValue('\"');
        } else if (iDoPeek == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (iDoPeek == 15) {
            str = Long.toString(this.peekedLong);
        } else {
            if (iDoPeek != 16) {
                throw new IllegalStateException("Expected a string but was " + peek() + locationString());
            }
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i9 = this.stackSize - 1;
        iArr[i9] = iArr[i9] + 1;
        return str;
    }

    public JsonToken peek() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        switch (iDoPeek) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void setLenient(boolean z8) {
        this.lenient = z8;
    }

    public void skipValue() throws IOException {
        int i9 = 0;
        do {
            int iDoPeek = this.peeked;
            if (iDoPeek == 0) {
                iDoPeek = doPeek();
            }
            if (iDoPeek == 3) {
                push(1);
            } else if (iDoPeek == 1) {
                push(3);
            } else if (iDoPeek == 4 || iDoPeek == 2) {
                this.stackSize--;
                i9--;
                this.peeked = 0;
            } else {
                if (iDoPeek == 14 || iDoPeek == 10) {
                    skipUnquotedValue();
                } else if (iDoPeek == 8 || iDoPeek == 12) {
                    skipQuotedValue('\'');
                } else if (iDoPeek == 9 || iDoPeek == 13) {
                    skipQuotedValue('\"');
                } else if (iDoPeek == 16) {
                    this.pos += this.peekedNumberLength;
                }
                this.peeked = 0;
            }
            i9++;
            this.peeked = 0;
        } while (i9 != 0);
        int[] iArr = this.pathIndices;
        int i10 = this.stackSize;
        int i11 = i10 - 1;
        iArr[i11] = iArr[i11] + 1;
        this.pathNames[i10 - 1] = "null";
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }

    public String getPath() {
        return getPath(false);
    }
}
