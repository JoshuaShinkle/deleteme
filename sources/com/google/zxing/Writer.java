package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes2.dex */
public interface Writer {
    BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i9, int i10);

    BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i9, int i10, Map<EncodeHintType, ?> map);
}
