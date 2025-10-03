package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;

    public ByteMatrix(int i9, int i10) {
        this.bytes = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i10, i9);
        this.width = i9;
        this.height = i10;
    }

    public void clear(byte b9) {
        for (int i9 = 0; i9 < this.height; i9++) {
            for (int i10 = 0; i10 < this.width; i10++) {
                this.bytes[i9][i10] = b9;
            }
        }
    }

    public byte get(int i9, int i10) {
        return this.bytes[i10][i9];
    }

    public byte[][] getArray() {
        return this.bytes;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void set(int i9, int i10, byte b9) {
        this.bytes[i10][i9] = b9;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int i9 = 0; i9 < this.height; i9++) {
            for (int i10 = 0; i10 < this.width; i10++) {
                byte b9 = this.bytes[i9][i10];
                if (b9 == 0) {
                    sb.append(" 0");
                } else if (b9 != 1) {
                    sb.append("  ");
                } else {
                    sb.append(" 1");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void set(int i9, int i10, int i11) {
        this.bytes[i10][i9] = (byte) i11;
    }

    public void set(int i9, int i10, boolean z8) {
        this.bytes[i10][i9] = z8 ? (byte) 1 : (byte) 0;
    }
}
