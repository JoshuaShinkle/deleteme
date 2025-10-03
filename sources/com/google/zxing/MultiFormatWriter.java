package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Map;

/* loaded from: classes2.dex */
public final class MultiFormatWriter implements Writer {

    /* renamed from: com.google.zxing.MultiFormatWriter$1 */
    public static /* synthetic */ class C44481 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$BarcodeFormat;

        static {
            int[] iArr = new int[BarcodeFormat.values().length];
            $SwitchMap$com$google$zxing$BarcodeFormat = iArr;
            try {
                iArr[BarcodeFormat.QR_CODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i9, int i10) {
        return encode(str, barcodeFormat, i9, i10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i9, int i10, Map<EncodeHintType, ?> map) {
        if (C44481.$SwitchMap$com$google$zxing$BarcodeFormat[barcodeFormat.ordinal()] == 1) {
            return new QRCodeWriter().encode(str, barcodeFormat, i9, i10, map);
        }
        throw new IllegalArgumentException("No encoder available for format " + barcodeFormat);
    }
}
