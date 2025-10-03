package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/* loaded from: classes2.dex */
final class DecodeHandler extends Handler {
    private static final String TAG = "DecodeHandler";
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader;
    private boolean running = true;

    public DecodeHandler(CaptureActivity captureActivity, Map<DecodeHintType, Object> map) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.multiFormatReader = multiFormatReader;
        multiFormatReader.setHints(map);
        this.activity = captureActivity;
    }

    private static void bundleThumbnail(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] iArrRenderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArrRenderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray(DecodeThread.BARCODE_BITMAP, byteArrayOutputStream.toByteArray());
        bundle.putFloat(DecodeThread.BARCODE_SCALED_FACTOR, thumbnailWidth / planarYUVLuminanceSource.getWidth());
    }

    private void decode(byte[] bArr, int i9, int i10) {
        Result resultDecodeWithState;
        long jCurrentTimeMillis = System.currentTimeMillis();
        PlanarYUVLuminanceSource planarYUVLuminanceSourceBuildLuminanceSource = this.activity.getCameraManager().buildLuminanceSource(bArr, i9, i10);
        if (planarYUVLuminanceSourceBuildLuminanceSource != null) {
            try {
                resultDecodeWithState = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSourceBuildLuminanceSource)));
            } catch (ReaderException unused) {
            } finally {
                this.multiFormatReader.reset();
            }
        } else {
            resultDecodeWithState = null;
        }
        Handler handler = this.activity.getHandler();
        if (resultDecodeWithState == null) {
            if (handler != null) {
                Message.obtain(handler, C4453R.id.decode_failed).sendToTarget();
                return;
            }
            return;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        Log.d(TAG, "Found barcode in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " ms");
        if (handler != null) {
            Message messageObtain = Message.obtain(handler, C4453R.id.decode_succeeded, resultDecodeWithState);
            Bundle bundle = new Bundle();
            bundleThumbnail(planarYUVLuminanceSourceBuildLuminanceSource, bundle);
            messageObtain.setData(bundle);
            messageObtain.sendToTarget();
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
    }
}
