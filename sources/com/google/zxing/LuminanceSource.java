package com.google.zxing;

import com.google.common.primitives.UnsignedBytes;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public abstract class LuminanceSource {
    private final int height;
    private final int width;

    public LuminanceSource(int i9, int i10) {
        this.width = i9;
        this.height = i10;
    }

    public LuminanceSource crop(int i9, int i10, int i11, int i12) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    public final int getHeight() {
        return this.height;
    }

    public abstract byte[] getMatrix();

    public abstract byte[] getRow(int i9, byte[] bArr);

    public final int getWidth() {
        return this.width;
    }

    public LuminanceSource invert() {
        return new InvertedLuminanceSource(this);
    }

    public boolean isCropSupported() {
        return false;
    }

    public boolean isRotateSupported() {
        return false;
    }

    public LuminanceSource rotateCounterClockwise() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public LuminanceSource rotateCounterClockwise45() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
    }

    public final String toString() {
        int i9 = this.width;
        byte[] row = new byte[i9];
        StringBuilder sb = new StringBuilder(this.height * (i9 + 1));
        for (int i10 = 0; i10 < this.height; i10++) {
            row = getRow(i10, row);
            for (int i11 = 0; i11 < this.width; i11++) {
                int i12 = row[i11] & UnsignedBytes.MAX_VALUE;
                sb.append(i12 < 64 ? '#' : i12 < 128 ? '+' : i12 < 192 ? ClassUtils.PACKAGE_SEPARATOR_CHAR : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
