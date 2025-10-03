package com.google.common.p039io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public abstract class BaseEncoding {
    private static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");

    public static final class Alphabet extends CharMatcher {
        final int bitsPerChar;
        final int bytesPerChunk;
        private final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        public Alphabet(String str, char[] cArr) {
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                int iLog2 = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                this.bitsPerChar = iLog2;
                int iMin = Math.min(8, Integer.lowestOneBit(iLog2));
                try {
                    this.charsPerChunk = 8 / iMin;
                    this.bytesPerChunk = iLog2 / iMin;
                    this.mask = cArr.length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    for (int i9 = 0; i9 < cArr.length; i9++) {
                        char c9 = cArr[i9];
                        Preconditions.checkArgument(CharMatcher.ascii().matches(c9), "Non-ASCII character: %s", c9);
                        Preconditions.checkArgument(bArr[c9] == -1, "Duplicate character: %s", c9);
                        bArr[c9] = (byte) i9;
                    }
                    this.decodabet = bArr;
                    boolean[] zArr = new boolean[this.charsPerChunk];
                    for (int i10 = 0; i10 < this.bytesPerChunk; i10++) {
                        zArr[IntMath.divide(i10 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                    }
                    this.validPadding = zArr;
                } catch (ArithmeticException e9) {
                    throw new IllegalArgumentException("Illegal alphabet " + new String(cArr), e9);
                }
            } catch (ArithmeticException e10) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e10);
            }
        }

        private boolean hasLowerCase() {
            for (char c9 : this.chars) {
                if (Ascii.isLowerCase(c9)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char c9 : this.chars) {
                if (Ascii.isUpperCase(c9)) {
                    return true;
                }
            }
            return false;
        }

        public boolean canDecode(char c9) {
            return c9 <= 127 && this.decodabet[c9] != -1;
        }

        public int decode(char c9) throws DecodingException {
            Object objValueOf;
            byte b9;
            if (c9 <= 127 && (b9 = this.decodabet[c9]) != -1) {
                return b9;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unrecognized character: ");
            if (CharMatcher.invisible().matches(c9)) {
                objValueOf = "0x" + Integer.toHexString(c9);
            } else {
                objValueOf = Character.valueOf(c9);
            }
            sb.append(objValueOf);
            throw new DecodingException(sb.toString());
        }

        public char encode(int i9) {
            return this.chars[i9];
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof Alphabet) {
                return Arrays.equals(this.chars, ((Alphabet) obj).chars);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars);
        }

        public boolean isValidPaddingStartPosition(int i9) {
            return this.validPadding[i9 % this.charsPerChunk];
        }

        public Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i9 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i9 >= cArr2.length) {
                    return new Alphabet(this.name + ".lowerCase()", cArr);
                }
                cArr[i9] = Ascii.toLowerCase(cArr2[i9]);
                i9++;
            }
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return CharMatcher.ascii().matches(c9) && this.decodabet[c9] != -1;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.name;
        }

        public Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i9 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i9 >= cArr2.length) {
                    return new Alphabet(this.name + ".upperCase()", cArr);
                }
                cArr[i9] = Ascii.toUpperCase(cArr2[i9]);
                i9++;
            }
        }
    }

    public static final class Base16Encoding extends StandardBaseEncoding {
        final char[] encoding;

        public Base16Encoding(String str, String str2) {
            this(new Alphabet(str, str2.toCharArray()));
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.p039io.BaseEncoding
        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            if (charSequence.length() % 2 == 1) {
                throw new DecodingException("Invalid input length " + charSequence.length());
            }
            int i9 = 0;
            int i10 = 0;
            while (i9 < charSequence.length()) {
                bArr[i10] = (byte) ((this.alphabet.decode(charSequence.charAt(i9)) << 4) | this.alphabet.decode(charSequence.charAt(i9 + 1)));
                i9 += 2;
                i10++;
            }
            return i10;
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.p039io.BaseEncoding
        public void encodeTo(Appendable appendable, byte[] bArr, int i9, int i10) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
            for (int i11 = 0; i11 < i10; i11++) {
                int i12 = bArr[i9 + i11] & UnsignedBytes.MAX_VALUE;
                appendable.append(this.encoding[i12]);
                appendable.append(this.encoding[i12 | 256]);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        public BaseEncoding newInstance(Alphabet alphabet, Character ch) {
            return new Base16Encoding(alphabet);
        }

        private Base16Encoding(Alphabet alphabet) {
            super(alphabet, null);
            this.encoding = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int i9 = 0; i9 < 256; i9++) {
                this.encoding[i9] = alphabet.encode(i9 >>> 4);
                this.encoding[i9 | 256] = alphabet.encode(i9 & 15);
            }
        }
    }

    public static final class Base64Encoding extends StandardBaseEncoding {
        public Base64Encoding(String str, String str2, Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.p039io.BaseEncoding
        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            String strTrimTrailingFrom = padding().trimTrailingFrom(charSequence);
            if (!this.alphabet.isValidPaddingStartPosition(strTrimTrailingFrom.length())) {
                throw new DecodingException("Invalid input length " + strTrimTrailingFrom.length());
            }
            int i9 = 0;
            int i10 = 0;
            while (i9 < strTrimTrailingFrom.length()) {
                int i11 = i9 + 1;
                int i12 = i11 + 1;
                int iDecode = (this.alphabet.decode(strTrimTrailingFrom.charAt(i9)) << 18) | (this.alphabet.decode(strTrimTrailingFrom.charAt(i11)) << 12);
                int i13 = i10 + 1;
                bArr[i10] = (byte) (iDecode >>> 16);
                if (i12 < strTrimTrailingFrom.length()) {
                    int i14 = i12 + 1;
                    int iDecode2 = iDecode | (this.alphabet.decode(strTrimTrailingFrom.charAt(i12)) << 6);
                    i10 = i13 + 1;
                    bArr[i13] = (byte) ((iDecode2 >>> 8) & 255);
                    if (i14 < strTrimTrailingFrom.length()) {
                        i12 = i14 + 1;
                        i13 = i10 + 1;
                        bArr[i10] = (byte) ((iDecode2 | this.alphabet.decode(strTrimTrailingFrom.charAt(i14))) & 255);
                    } else {
                        i9 = i14;
                    }
                }
                i10 = i13;
                i9 = i12;
            }
            return i10;
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.p039io.BaseEncoding
        public void encodeTo(Appendable appendable, byte[] bArr, int i9, int i10) throws IOException {
            Preconditions.checkNotNull(appendable);
            int i11 = i9 + i10;
            Preconditions.checkPositionIndexes(i9, i11, bArr.length);
            while (i10 >= 3) {
                int i12 = i9 + 1;
                int i13 = i12 + 1;
                int i14 = ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 16) | ((bArr[i12] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[i13] & UnsignedBytes.MAX_VALUE);
                appendable.append(this.alphabet.encode(i14 >>> 18));
                appendable.append(this.alphabet.encode((i14 >>> 12) & 63));
                appendable.append(this.alphabet.encode((i14 >>> 6) & 63));
                appendable.append(this.alphabet.encode(i14 & 63));
                i10 -= 3;
                i9 = i13 + 1;
            }
            if (i9 < i11) {
                encodeChunkTo(appendable, bArr, i9, i11 - i9);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        public BaseEncoding newInstance(Alphabet alphabet, Character ch) {
            return new Base64Encoding(alphabet, ch);
        }

        private Base64Encoding(Alphabet alphabet, Character ch) {
            super(alphabet, ch);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }
    }

    public static final class DecodingException extends IOException {
        public DecodingException(String str) {
            super(str);
        }

        public DecodingException(Throwable th) {
            super(th);
        }
    }

    public static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;
        private final CharMatcher separatorChars;

        public SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int i9) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = i9;
            Preconditions.checkArgument(i9 > 0, "Cannot add a separator after every %s chars", i9);
            this.separatorChars = CharMatcher.anyOf(str).precomputed();
        }

        @Override // com.google.common.p039io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            return this.delegate.canDecode(this.separatorChars.removeFrom(charSequence));
        }

        @Override // com.google.common.p039io.BaseEncoding
        public int decodeTo(byte[] bArr, CharSequence charSequence) {
            return this.delegate.decodeTo(bArr, this.separatorChars.removeFrom(charSequence));
        }

        @Override // com.google.common.p039io.BaseEncoding
        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(BaseEncoding.ignoringReader(reader, this.separatorChars));
        }

        @Override // com.google.common.p039io.BaseEncoding
        public void encodeTo(Appendable appendable, byte[] bArr, int i9, int i10) {
            this.delegate.encodeTo(BaseEncoding.separatingAppendable(appendable, this.separator, this.afterEveryChars), bArr, i9, i10);
        }

        @Override // com.google.common.p039io.BaseEncoding
        @GwtIncompatible
        public OutputStream encodingStream(Writer writer) {
            return this.delegate.encodingStream(BaseEncoding.separatingWriter(writer, this.separator, this.afterEveryChars));
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public int maxDecodedSize(int i9) {
            return this.delegate.maxDecodedSize(i9);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public int maxEncodedSize(int i9) {
            int iMaxEncodedSize = this.delegate.maxEncodedSize(i9);
            return iMaxEncodedSize + (this.separator.length() * IntMath.divide(Math.max(0, iMaxEncodedSize - 1), this.afterEveryChars, RoundingMode.FLOOR));
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public CharMatcher padding() {
            return this.delegate.padding();
        }

        public String toString() {
            return this.delegate + ".withSeparator(\"" + this.separator + "\", " + this.afterEveryChars + ")";
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding withPadChar(char c9) {
            return this.delegate.withPadChar(c9).withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding withSeparator(String str, int i9) {
            throw new UnsupportedOperationException("Already have a separator");
        }
    }

    public static class StandardBaseEncoding extends BaseEncoding {
        final Alphabet alphabet;
        private transient BaseEncoding lowerCase;
        final Character paddingChar;
        private transient BaseEncoding upperCase;

        public StandardBaseEncoding(String str, String str2, Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            String strTrimTrailingFrom = padding().trimTrailingFrom(charSequence);
            if (!this.alphabet.isValidPaddingStartPosition(strTrimTrailingFrom.length())) {
                return false;
            }
            for (int i9 = 0; i9 < strTrimTrailingFrom.length(); i9++) {
                if (!this.alphabet.canDecode(strTrimTrailingFrom.charAt(i9))) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.p039io.BaseEncoding
        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Alphabet alphabet;
            Preconditions.checkNotNull(bArr);
            String strTrimTrailingFrom = padding().trimTrailingFrom(charSequence);
            if (!this.alphabet.isValidPaddingStartPosition(strTrimTrailingFrom.length())) {
                throw new DecodingException("Invalid input length " + strTrimTrailingFrom.length());
            }
            int i9 = 0;
            int i10 = 0;
            while (i9 < strTrimTrailingFrom.length()) {
                long jDecode = 0;
                int i11 = 0;
                int i12 = 0;
                while (true) {
                    alphabet = this.alphabet;
                    if (i11 >= alphabet.charsPerChunk) {
                        break;
                    }
                    jDecode <<= alphabet.bitsPerChar;
                    if (i9 + i11 < strTrimTrailingFrom.length()) {
                        jDecode |= this.alphabet.decode(strTrimTrailingFrom.charAt(i12 + i9));
                        i12++;
                    }
                    i11++;
                }
                int i13 = alphabet.bytesPerChunk;
                int i14 = (i13 * 8) - (i12 * alphabet.bitsPerChar);
                int i15 = (i13 - 1) * 8;
                while (i15 >= i14) {
                    bArr[i10] = (byte) ((jDecode >>> i15) & 255);
                    i15 -= 8;
                    i10++;
                }
                i9 += this.alphabet.charsPerChunk;
            }
            return i10;
        }

        @Override // com.google.common.p039io.BaseEncoding
        @GwtIncompatible
        public InputStream decodingStream(final Reader reader) {
            Preconditions.checkNotNull(reader);
            return new InputStream() { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.2
                final CharMatcher paddingMatcher;
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int readChars = 0;
                boolean hitPadding = false;

                {
                    this.paddingMatcher = StandardBaseEncoding.this.padding();
                }

                @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    reader.close();
                }

                /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
                
                    throw new com.google.common.io.BaseEncoding.DecodingException("Padding cannot start at index " + r4.readChars);
                 */
                @Override // java.io.InputStream
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public int read() throws IOException {
                    int i9;
                    while (true) {
                        int i10 = reader.read();
                        if (i10 == -1) {
                            if (this.hitPadding || StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars)) {
                                return -1;
                            }
                            throw new DecodingException("Invalid input length " + this.readChars);
                        }
                        this.readChars++;
                        char c9 = (char) i10;
                        if (this.paddingMatcher.matches(c9)) {
                            if (!this.hitPadding && ((i9 = this.readChars) == 1 || !StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(i9 - 1))) {
                                break;
                            }
                            this.hitPadding = true;
                        } else {
                            if (this.hitPadding) {
                                throw new DecodingException("Expected padding character but found '" + c9 + "' at index " + this.readChars);
                            }
                            int i11 = this.bitBuffer;
                            Alphabet alphabet = StandardBaseEncoding.this.alphabet;
                            int i12 = i11 << alphabet.bitsPerChar;
                            this.bitBuffer = i12;
                            int iDecode = alphabet.decode(c9) | i12;
                            this.bitBuffer = iDecode;
                            int i13 = this.bitBufferLength + StandardBaseEncoding.this.alphabet.bitsPerChar;
                            this.bitBufferLength = i13;
                            if (i13 >= 8) {
                                int i14 = i13 - 8;
                                this.bitBufferLength = i14;
                                return (iDecode >> i14) & 255;
                            }
                        }
                    }
                }
            };
        }

        public void encodeChunkTo(Appendable appendable, byte[] bArr, int i9, int i10) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
            int i11 = 0;
            Preconditions.checkArgument(i10 <= this.alphabet.bytesPerChunk);
            long j9 = 0;
            for (int i12 = 0; i12 < i10; i12++) {
                j9 = (j9 | (bArr[i9 + i12] & UnsignedBytes.MAX_VALUE)) << 8;
            }
            int i13 = ((i10 + 1) * 8) - this.alphabet.bitsPerChar;
            while (i11 < i10 * 8) {
                Alphabet alphabet = this.alphabet;
                appendable.append(alphabet.encode(((int) (j9 >>> (i13 - i11))) & alphabet.mask));
                i11 += this.alphabet.bitsPerChar;
            }
            if (this.paddingChar != null) {
                while (i11 < this.alphabet.bytesPerChunk * 8) {
                    appendable.append(this.paddingChar.charValue());
                    i11 += this.alphabet.bitsPerChar;
                }
            }
        }

        @Override // com.google.common.p039io.BaseEncoding
        public void encodeTo(Appendable appendable, byte[] bArr, int i9, int i10) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
            int i11 = 0;
            while (i11 < i10) {
                encodeChunkTo(appendable, bArr, i9 + i11, Math.min(this.alphabet.bytesPerChunk, i10 - i11));
                i11 += this.alphabet.bytesPerChunk;
            }
        }

        @Override // com.google.common.p039io.BaseEncoding
        @GwtIncompatible
        public OutputStream encodingStream(final Writer writer) {
            Preconditions.checkNotNull(writer);
            return new OutputStream() { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.1
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    int i9 = this.bitBufferLength;
                    if (i9 > 0) {
                        int i10 = this.bitBuffer;
                        Alphabet alphabet = StandardBaseEncoding.this.alphabet;
                        writer.write(alphabet.encode((i10 << (alphabet.bitsPerChar - i9)) & alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (true) {
                                int i11 = this.writtenChars;
                                StandardBaseEncoding standardBaseEncoding = StandardBaseEncoding.this;
                                if (i11 % standardBaseEncoding.alphabet.charsPerChunk == 0) {
                                    break;
                                }
                                writer.write(standardBaseEncoding.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    writer.close();
                }

                @Override // java.io.OutputStream, java.io.Flushable
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override // java.io.OutputStream
                public void write(int i9) throws IOException {
                    this.bitBuffer = (i9 & 255) | (this.bitBuffer << 8);
                    this.bitBufferLength += 8;
                    while (true) {
                        int i10 = this.bitBufferLength;
                        Alphabet alphabet = StandardBaseEncoding.this.alphabet;
                        int i11 = alphabet.bitsPerChar;
                        if (i10 < i11) {
                            return;
                        }
                        writer.write(alphabet.encode((this.bitBuffer >> (i10 - i11)) & alphabet.mask));
                        this.writtenChars++;
                        this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                    }
                }
            };
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof StandardBaseEncoding)) {
                return false;
            }
            StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) obj;
            return this.alphabet.equals(standardBaseEncoding.alphabet) && Objects.equal(this.paddingChar, standardBaseEncoding.paddingChar);
        }

        public int hashCode() {
            return this.alphabet.hashCode() ^ Objects.hashCode(this.paddingChar);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding lowerCase() {
            BaseEncoding baseEncodingNewInstance = this.lowerCase;
            if (baseEncodingNewInstance == null) {
                Alphabet alphabetLowerCase = this.alphabet.lowerCase();
                baseEncodingNewInstance = alphabetLowerCase == this.alphabet ? this : newInstance(alphabetLowerCase, this.paddingChar);
                this.lowerCase = baseEncodingNewInstance;
            }
            return baseEncodingNewInstance;
        }

        @Override // com.google.common.p039io.BaseEncoding
        public int maxDecodedSize(int i9) {
            return (int) (((this.alphabet.bitsPerChar * i9) + 7) / 8);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public int maxEncodedSize(int i9) {
            Alphabet alphabet = this.alphabet;
            return alphabet.charsPerChunk * IntMath.divide(i9, alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        public BaseEncoding newInstance(Alphabet alphabet, Character ch) {
            return new StandardBaseEncoding(alphabet, ch);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : newInstance(this.alphabet, null);
        }

        @Override // com.google.common.p039io.BaseEncoding
        public CharMatcher padding() {
            Character ch = this.paddingChar;
            return ch == null ? CharMatcher.none() : CharMatcher.m17547is(ch.charValue());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.paddingChar);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding upperCase() {
            BaseEncoding baseEncodingNewInstance = this.upperCase;
            if (baseEncodingNewInstance == null) {
                Alphabet alphabetUpperCase = this.alphabet.upperCase();
                baseEncodingNewInstance = alphabetUpperCase == this.alphabet ? this : newInstance(alphabetUpperCase, this.paddingChar);
                this.upperCase = baseEncodingNewInstance;
            }
            return baseEncodingNewInstance;
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding withPadChar(char c9) {
            Character ch;
            return (8 % this.alphabet.bitsPerChar == 0 || ((ch = this.paddingChar) != null && ch.charValue() == c9)) ? this : newInstance(this.alphabet, Character.valueOf(c9));
        }

        @Override // com.google.common.p039io.BaseEncoding
        public BaseEncoding withSeparator(String str, int i9) {
            Preconditions.checkArgument(padding().mo17548or(this.alphabet).matchesNoneOf(str), "Separator (%s) cannot contain alphabet or padding characters", str);
            return new SeparatedBaseEncoding(this, str, i9);
        }

        public StandardBaseEncoding(Alphabet alphabet, Character ch) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet);
            Preconditions.checkArgument(ch == null || !alphabet.matches(ch.charValue()), "Padding character %s was already in alphabet", ch);
            this.paddingChar = ch;
        }
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    private static byte[] extract(byte[] bArr, int i9) {
        if (i9 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i9];
        System.arraycopy(bArr, 0, bArr2, 0, i9);
        return bArr2;
    }

    @GwtIncompatible
    public static Reader ignoringReader(final Reader reader, final CharMatcher charMatcher) {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(charMatcher);
        return new Reader() { // from class: com.google.common.io.BaseEncoding.3
            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                reader.close();
            }

            @Override // java.io.Reader
            public int read() throws IOException {
                int i9;
                do {
                    i9 = reader.read();
                    if (i9 == -1) {
                        break;
                    }
                } while (charMatcher.matches((char) i9));
                return i9;
            }

            @Override // java.io.Reader
            public int read(char[] cArr, int i9, int i10) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static Appendable separatingAppendable(Appendable appendable, String str, int i9) {
        Preconditions.checkNotNull(appendable);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i9 > 0);
        return new Appendable(i9, appendable, str) { // from class: com.google.common.io.BaseEncoding.4
            int charsUntilSeparator;
            final /* synthetic */ int val$afterEveryChars;
            final /* synthetic */ Appendable val$delegate;
            final /* synthetic */ String val$separator;

            {
                this.val$afterEveryChars = i9;
                this.val$delegate = appendable;
                this.val$separator = str;
                this.charsUntilSeparator = i9;
            }

            @Override // java.lang.Appendable
            public Appendable append(char c9) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    this.val$delegate.append(this.val$separator);
                    this.charsUntilSeparator = this.val$afterEveryChars;
                }
                this.val$delegate.append(c9);
                this.charsUntilSeparator--;
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence charSequence, int i10, int i11) {
                throw new UnsupportedOperationException();
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence charSequence) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @GwtIncompatible
    public static Writer separatingWriter(final Writer writer, String str, int i9) {
        final Appendable appendableSeparatingAppendable = separatingAppendable(writer, str, i9);
        return new Writer() { // from class: com.google.common.io.BaseEncoding.5
            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                writer.close();
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() throws IOException {
                writer.flush();
            }

            @Override // java.io.Writer
            public void write(int i10) throws IOException {
                appendableSeparatingAppendable.append((char) i10);
            }

            @Override // java.io.Writer
            public void write(char[] cArr, int i10, int i11) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public abstract boolean canDecode(CharSequence charSequence);

    public final byte[] decode(CharSequence charSequence) {
        try {
            return decodeChecked(charSequence);
        } catch (DecodingException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public final byte[] decodeChecked(CharSequence charSequence) {
        String strTrimTrailingFrom = padding().trimTrailingFrom(charSequence);
        byte[] bArr = new byte[maxDecodedSize(strTrimTrailingFrom.length())];
        return extract(bArr, decodeTo(bArr, strTrimTrailingFrom));
    }

    public abstract int decodeTo(byte[] bArr, CharSequence charSequence);

    @GwtIncompatible
    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() { // from class: com.google.common.io.BaseEncoding.2
            @Override // com.google.common.p039io.ByteSource
            public InputStream openStream() {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    @GwtIncompatible
    public abstract InputStream decodingStream(Reader reader);

    public String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public abstract void encodeTo(Appendable appendable, byte[] bArr, int i9, int i10);

    @GwtIncompatible
    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() { // from class: com.google.common.io.BaseEncoding.1
            @Override // com.google.common.p039io.ByteSink
            public OutputStream openStream() {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    @GwtIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    public abstract BaseEncoding lowerCase();

    public abstract int maxDecodedSize(int i9);

    public abstract int maxEncodedSize(int i9);

    public abstract BaseEncoding omitPadding();

    public abstract CharMatcher padding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c9);

    public abstract BaseEncoding withSeparator(String str, int i9);

    public final String encode(byte[] bArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        StringBuilder sb = new StringBuilder(maxEncodedSize(i10));
        try {
            encodeTo(sb, bArr, i9, i10);
            return sb.toString();
        } catch (IOException e9) {
            throw new AssertionError(e9);
        }
    }
}
