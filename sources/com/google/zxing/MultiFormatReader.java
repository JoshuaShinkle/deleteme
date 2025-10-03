package com.google.zxing;

import com.google.zxing.qrcode.QRCodeReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public final class MultiFormatReader implements Reader {
    private Map<DecodeHintType, ?> hints;
    private Reader[] readers;

    private Result decodeInternal(BinaryBitmap binaryBitmap) throws NotFoundException {
        Reader[] readerArr = this.readers;
        if (readerArr != null) {
            for (Reader reader : readerArr) {
                try {
                    return reader.decode(binaryBitmap, this.hints);
                } catch (ReaderException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) {
        setHints(null);
        return decodeInternal(binaryBitmap);
    }

    public Result decodeWithState(BinaryBitmap binaryBitmap) {
        if (this.readers == null) {
            setHints(null);
        }
        return decodeInternal(binaryBitmap);
    }

    @Override // com.google.zxing.Reader
    public void reset() {
        Reader[] readerArr = this.readers;
        if (readerArr != null) {
            for (Reader reader : readerArr) {
                reader.reset();
            }
        }
    }

    public void setHints(Map<DecodeHintType, ?> map) {
        this.hints = map;
        if (map != null) {
            map.containsKey(DecodeHintType.TRY_HARDER);
        }
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null && collection.contains(BarcodeFormat.QR_CODE)) {
            arrayList.add(new QRCodeReader());
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new QRCodeReader());
        }
        this.readers = (Reader[]) arrayList.toArray(new Reader[arrayList.size()]);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) {
        setHints(map);
        return decodeInternal(binaryBitmap);
    }
}
