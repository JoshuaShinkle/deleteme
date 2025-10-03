package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int i9, int i10, int i11) {
        boolean z8;
        float f9;
        float f10;
        int i12;
        int i13;
        int i14;
        int i15;
        if (i11 > 4) {
            return;
        }
        try {
            Result resultDecode = this.delegate.decode(binaryBitmap, map);
            Iterator<Result> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getText().equals(resultDecode.getText())) {
                        z8 = true;
                        break;
                    }
                } else {
                    z8 = false;
                    break;
                }
            }
            if (!z8) {
                list.add(translateResultPoints(resultDecode, i9, i10));
            }
            ResultPoint[] resultPoints = resultDecode.getResultPoints();
            if (resultPoints == null || resultPoints.length == 0) {
                return;
            }
            int width = binaryBitmap.getWidth();
            int height = binaryBitmap.getHeight();
            float f11 = width;
            float f12 = 0.0f;
            float f13 = height;
            float f14 = 0.0f;
            for (ResultPoint resultPoint : resultPoints) {
                if (resultPoint != null) {
                    float x8 = resultPoint.getX();
                    float y8 = resultPoint.getY();
                    if (x8 < f11) {
                        f11 = x8;
                    }
                    if (y8 < f13) {
                        f13 = y8;
                    }
                    if (x8 > f14) {
                        f14 = x8;
                    }
                    if (y8 > f12) {
                        f12 = y8;
                    }
                }
            }
            if (f11 > 100.0f) {
                f9 = f14;
                f10 = f13;
                i12 = height;
                i13 = width;
                doDecodeMultiple(binaryBitmap.crop(0, 0, (int) f11, height), map, list, i9, i10, i11 + 1);
            } else {
                f9 = f14;
                f10 = f13;
                i12 = height;
                i13 = width;
            }
            if (f10 > 100.0f) {
                int i16 = (int) f10;
                i14 = i13;
                doDecodeMultiple(binaryBitmap.crop(0, 0, i14, i16), map, list, i9, i10, i11 + 1);
            } else {
                i14 = i13;
            }
            float f15 = f9;
            if (f15 < i14 - 100) {
                int i17 = (int) f15;
                i15 = i12;
                doDecodeMultiple(binaryBitmap.crop(i17, 0, i14 - i17, i15), map, list, i9 + i17, i10, i11 + 1);
            } else {
                i15 = i12;
            }
            if (f12 < i15 - 100) {
                int i18 = (int) f12;
                doDecodeMultiple(binaryBitmap.crop(0, i18, i14, i15 - i18), map, list, i9, i10 + i18, i11 + 1);
            }
        } catch (ReaderException unused) {
        }
    }

    private static Result translateResultPoints(Result result, int i9, int i10) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i11 = 0; i11 < resultPoints.length; i11++) {
            ResultPoint resultPoint = resultPoints[i11];
            if (resultPoint != null) {
                resultPointArr[i11] = new ResultPoint(resultPoint.getX() + i9, resultPoint.getY() + i10);
            }
        }
        Result result2 = new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
        result2.putAllMetadata(result.getResultMetadata());
        return result2;
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) {
        return decodeMultiple(binaryBitmap, null);
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0, 0);
        if (arrayList.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
    }
}
