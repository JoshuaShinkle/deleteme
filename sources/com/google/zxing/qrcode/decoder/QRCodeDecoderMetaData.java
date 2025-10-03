package com.google.zxing.qrcode.decoder;

import com.google.zxing.ResultPoint;

/* loaded from: classes2.dex */
public final class QRCodeDecoderMetaData {
    private final boolean mirrored;

    public QRCodeDecoderMetaData(boolean z8) {
        this.mirrored = z8;
    }

    public void applyMirroredCorrection(ResultPoint[] resultPointArr) {
        if (!this.mirrored || resultPointArr == null || resultPointArr.length < 3) {
            return;
        }
        ResultPoint resultPoint = resultPointArr[0];
        resultPointArr[0] = resultPointArr[2];
        resultPointArr[2] = resultPoint;
    }

    public boolean isMirrored() {
        return this.mirrored;
    }
}
