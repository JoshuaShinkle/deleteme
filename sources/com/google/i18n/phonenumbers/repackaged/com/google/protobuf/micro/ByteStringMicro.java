package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.micro;

import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public final class ByteStringMicro {
    public static final ByteStringMicro EMPTY = new ByteStringMicro(new byte[0]);
    private final byte[] bytes;
    private volatile int hash = 0;

    private ByteStringMicro(byte[] bArr) {
        this.bytes = bArr;
    }

    public static ByteStringMicro copyFrom(byte[] bArr, int i9, int i10) {
        byte[] bArr2 = new byte[i10];
        System.arraycopy(bArr, i9, bArr2, 0, i10);
        return new ByteStringMicro(bArr2);
    }

    public static ByteStringMicro copyFromUtf8(String str) {
        try {
            return new ByteStringMicro(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }

    public byte byteAt(int i9) {
        return this.bytes[i9];
    }

    public void copyTo(byte[] bArr, int i9) {
        byte[] bArr2 = this.bytes;
        System.arraycopy(bArr2, 0, bArr, i9, bArr2.length);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteStringMicro)) {
            return false;
        }
        byte[] bArr = this.bytes;
        int length = bArr.length;
        byte[] bArr2 = ((ByteStringMicro) obj).bytes;
        if (length != bArr2.length) {
            return false;
        }
        for (int i9 = 0; i9 < length; i9++) {
            if (bArr[i9] != bArr2[i9]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i9 = this.hash;
        if (i9 == 0) {
            byte[] bArr = this.bytes;
            int length = bArr.length;
            for (byte b9 : bArr) {
                length = (length * 31) + b9;
            }
            i9 = length == 0 ? 1 : length;
            this.hash = i9;
        }
        return i9;
    }

    public boolean isEmpty() {
        return this.bytes.length == 0;
    }

    public int size() {
        return this.bytes.length;
    }

    public byte[] toByteArray() {
        byte[] bArr = this.bytes;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public String toString(String str) {
        return new String(this.bytes, str);
    }

    public String toStringUtf8() {
        try {
            return new String(this.bytes, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }

    public void copyTo(byte[] bArr, int i9, int i10, int i11) {
        System.arraycopy(this.bytes, i9, bArr, i10, i11);
    }

    public static ByteStringMicro copyFrom(byte[] bArr) {
        return copyFrom(bArr, 0, bArr.length);
    }

    public static ByteStringMicro copyFrom(String str, String str2) {
        return new ByteStringMicro(str.getBytes(str2));
    }
}
