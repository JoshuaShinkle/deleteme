package com.google.zxing;

import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes2.dex */
public final class PlanarYUVLuminanceSource extends LuminanceSource {
    private static final int THUMBNAIL_SCALE_FACTOR = 2;
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte[] yuvData;

    public PlanarYUVLuminanceSource(byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, boolean z8) {
        super(i13, i14);
        if (i11 + i13 > i9 || i12 + i14 > i10) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.yuvData = bArr;
        this.dataWidth = i9;
        this.dataHeight = i10;
        this.left = i11;
        this.top = i12;
        if (z8) {
            reverseHorizontal(i13, i14);
        }
    }

    private void reverseHorizontal(int i9, int i10) {
        byte[] bArr = this.yuvData;
        int i11 = (this.top * this.dataWidth) + this.left;
        int i12 = 0;
        while (i12 < i10) {
            int i13 = (i9 / 2) + i11;
            int i14 = (i11 + i9) - 1;
            int i15 = i11;
            while (i15 < i13) {
                byte b9 = bArr[i15];
                bArr[i15] = bArr[i14];
                bArr[i14] = b9;
                i15++;
                i14--;
            }
            i12++;
            i11 += this.dataWidth;
        }
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int i9, int i10, int i11, int i12) {
        return new PlanarYUVLuminanceSource(this.yuvData, this.dataWidth, this.dataHeight, this.left + i9, this.top + i10, i11, i12, false);
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i9 = this.dataWidth;
        if (width == i9 && height == this.dataHeight) {
            return this.yuvData;
        }
        int i10 = width * height;
        byte[] bArr = new byte[i10];
        int i11 = (this.top * i9) + this.left;
        if (width == i9) {
            System.arraycopy(this.yuvData, i11, bArr, 0, i10);
            return bArr;
        }
        byte[] bArr2 = this.yuvData;
        for (int i12 = 0; i12 < height; i12++) {
            System.arraycopy(bArr2, i11, bArr, i12 * width, width);
            i11 += this.dataWidth;
        }
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int i9, byte[] bArr) {
        if (i9 < 0 || i9 >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i9);
        }
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            bArr = new byte[width];
        }
        System.arraycopy(this.yuvData, ((i9 + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    public int getThumbnailHeight() {
        return getHeight() / 2;
    }

    public int getThumbnailWidth() {
        return getWidth() / 2;
    }

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    public int[] renderThumbnail() {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int[] iArr = new int[width * height];
        byte[] bArr = this.yuvData;
        int i9 = (this.top * this.dataWidth) + this.left;
        for (int i10 = 0; i10 < height; i10++) {
            int i11 = i10 * width;
            for (int i12 = 0; i12 < width; i12++) {
                iArr[i11 + i12] = ((bArr[(i12 * 2) + i9] & UnsignedBytes.MAX_VALUE) * 65793) | (-16777216);
            }
            i9 += this.dataWidth * 2;
        }
        return iArr;
    }
}
