package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class QRCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (topLeftOnBit == null || bottomRightOnBit == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float fModuleSize = moduleSize(topLeftOnBit, bitMatrix);
        int i9 = topLeftOnBit[1];
        int i10 = bottomRightOnBit[1];
        int i11 = topLeftOnBit[0];
        int i12 = bottomRightOnBit[0];
        if (i11 >= i12 || i9 >= i10) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i13 = i10 - i9;
        if (i13 != i12 - i11) {
            i12 = i11 + i13;
        }
        int iRound = Math.round(((i12 - i11) + 1) / fModuleSize);
        int iRound2 = Math.round((i13 + 1) / fModuleSize);
        if (iRound <= 0 || iRound2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (iRound2 != iRound) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i14 = (int) (fModuleSize / 2.0f);
        int i15 = i9 + i14;
        int i16 = i11 + i14;
        int i17 = (((int) ((iRound - 1) * fModuleSize)) + i16) - i12;
        if (i17 > 0) {
            if (i17 > i14) {
                throw NotFoundException.getNotFoundInstance();
            }
            i16 -= i17;
        }
        int i18 = (((int) ((iRound2 - 1) * fModuleSize)) + i15) - i10;
        if (i18 > 0) {
            if (i18 > i14) {
                throw NotFoundException.getNotFoundInstance();
            }
            i15 -= i18;
        }
        BitMatrix bitMatrix2 = new BitMatrix(iRound, iRound2);
        for (int i19 = 0; i19 < iRound2; i19++) {
            int i20 = ((int) (i19 * fModuleSize)) + i15;
            for (int i21 = 0; i21 < iRound; i21++) {
                if (bitMatrix.get(((int) (i21 * fModuleSize)) + i16, i20)) {
                    bitMatrix2.set(i21, i19);
                }
            }
        }
        return bitMatrix2;
    }

    private static float moduleSize(int[] iArr, BitMatrix bitMatrix) throws NotFoundException {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        int i9 = iArr[0];
        boolean z8 = true;
        int i10 = iArr[1];
        int i11 = 0;
        while (i9 < width && i10 < height) {
            if (z8 != bitMatrix.get(i9, i10)) {
                i11++;
                if (i11 == 5) {
                    break;
                }
                z8 = !z8;
            }
            i9++;
            i10++;
        }
        if (i9 == width || i10 == height) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (i9 - iArr[0]) / 7.0f;
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) {
        return decode(binaryBitmap, null);
    }

    public final Decoder getDecoder() {
        return this.decoder;
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public final Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] points;
        DecoderResult decoderResultDecode;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detectorResultDetect = new Detector(binaryBitmap.getBlackMatrix()).detect(map);
            DecoderResult decoderResultDecode2 = this.decoder.decode(detectorResultDetect.getBits(), map);
            points = detectorResultDetect.getPoints();
            decoderResultDecode = decoderResultDecode2;
        } else {
            decoderResultDecode = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), map);
            points = NO_POINTS;
        }
        if (decoderResultDecode.getOther() instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) decoderResultDecode.getOther()).applyMirroredCorrection(points);
        }
        Result result = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), points, BarcodeFormat.QR_CODE);
        List<byte[]> byteSegments = decoderResultDecode.getByteSegments();
        if (byteSegments != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        String eCLevel = decoderResultDecode.getECLevel();
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        if (decoderResultDecode.hasStructuredAppend()) {
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decoderResultDecode.getStructuredAppendSequenceNumber()));
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decoderResultDecode.getStructuredAppendParity()));
        }
        return result;
    }
}
