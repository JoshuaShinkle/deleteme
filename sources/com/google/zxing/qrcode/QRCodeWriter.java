package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

/* loaded from: classes2.dex */
public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    private static BitMatrix renderResult(QRCode qRCode, int i9, int i10, int i11) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int i12 = i11 * 2;
        int i13 = width + i12;
        int i14 = i12 + height;
        int iMax = Math.max(i9, i13);
        int iMax2 = Math.max(i10, i14);
        int iMin = Math.min(iMax / i13, iMax2 / i14);
        int i15 = (iMax - (width * iMin)) / 2;
        int i16 = (iMax2 - (height * iMin)) / 2;
        BitMatrix bitMatrix = new BitMatrix(iMax, iMax2);
        int i17 = 0;
        while (i17 < height) {
            int i18 = 0;
            int i19 = i15;
            while (i18 < width) {
                if (matrix.get(i18, i17) == 1) {
                    bitMatrix.setRegion(i19, i16, iMin, iMin);
                }
                i18++;
                i19 += iMin;
            }
            i17++;
            i16 += iMin;
        }
        return bitMatrix;
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i9, int i10) {
        return encode(str, barcodeFormat, i9, i10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i9, int i10, Map<EncodeHintType, ?> map) throws NumberFormatException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        }
        if (i9 < 0 || i10 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i9 + 'x' + i10);
        }
        ErrorCorrectionLevel errorCorrectionLevelValueOf = ErrorCorrectionLevel.L;
        int i11 = 4;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
            if (map.containsKey(encodeHintType)) {
                errorCorrectionLevelValueOf = ErrorCorrectionLevel.valueOf(map.get(encodeHintType).toString());
            }
            EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
            if (map.containsKey(encodeHintType2)) {
                i11 = Integer.parseInt(map.get(encodeHintType2).toString());
            }
        }
        return renderResult(Encoder.encode(str, errorCorrectionLevelValueOf, map), i9, i10, i11);
    }
}
