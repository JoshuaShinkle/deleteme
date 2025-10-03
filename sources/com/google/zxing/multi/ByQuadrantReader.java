package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ByQuadrantReader implements Reader {
    private final Reader delegate;

    public ByQuadrantReader(Reader reader) {
        this.delegate = reader;
    }

    private static void makeAbsolute(ResultPoint[] resultPointArr, int i9, int i10) {
        if (resultPointArr != null) {
            for (int i11 = 0; i11 < resultPointArr.length; i11++) {
                ResultPoint resultPoint = resultPointArr[i11];
                resultPointArr[i11] = new ResultPoint(resultPoint.getX() + i9, resultPoint.getY() + i10);
            }
        }
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public void reset() {
        this.delegate.reset();
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) {
        int width = binaryBitmap.getWidth() / 2;
        int height = binaryBitmap.getHeight() / 2;
        try {
            try {
                try {
                    try {
                        return this.delegate.decode(binaryBitmap.crop(0, 0, width, height), map);
                    } catch (NotFoundException unused) {
                        int i9 = width / 2;
                        int i10 = height / 2;
                        Result resultDecode = this.delegate.decode(binaryBitmap.crop(i9, i10, width, height), map);
                        makeAbsolute(resultDecode.getResultPoints(), i9, i10);
                        return resultDecode;
                    }
                } catch (NotFoundException unused2) {
                    Result resultDecode2 = this.delegate.decode(binaryBitmap.crop(width, height, width, height), map);
                    makeAbsolute(resultDecode2.getResultPoints(), width, height);
                    return resultDecode2;
                }
            } catch (NotFoundException unused3) {
                Result resultDecode3 = this.delegate.decode(binaryBitmap.crop(0, height, width, height), map);
                makeAbsolute(resultDecode3.getResultPoints(), 0, height);
                return resultDecode3;
            }
        } catch (NotFoundException unused4) {
            Result resultDecode4 = this.delegate.decode(binaryBitmap.crop(width, 0, width, height), map);
            makeAbsolute(resultDecode4.getResultPoints(), width, 0);
            return resultDecode4;
        }
    }
}
