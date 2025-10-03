package p085h3;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.zxing.CaptureActivity;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.android.DecodeThread;
import com.google.zxing.common.HybridBinarizer;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/* renamed from: h3.b */
/* loaded from: classes.dex */
public final class HandlerC4990b extends Handler {

    /* renamed from: d */
    public static final String f17185d = "b";

    /* renamed from: a */
    public final CaptureActivity f17186a;

    /* renamed from: b */
    public final MultiFormatReader f17187b;

    /* renamed from: c */
    public boolean f17188c = true;

    public HandlerC4990b(CaptureActivity captureActivity, Map<DecodeHintType, Object> map) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.f17187b = multiFormatReader;
        multiFormatReader.setHints(map);
        this.f17186a = captureActivity;
    }

    /* renamed from: a */
    public static void m19364a(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] iArrRenderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArrRenderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray(DecodeThread.BARCODE_BITMAP, byteArrayOutputStream.toByteArray());
        bundle.putFloat(DecodeThread.BARCODE_SCALED_FACTOR, thumbnailWidth / planarYUVLuminanceSource.getWidth());
    }

    /* renamed from: b */
    public final void m19365b(byte[] bArr, int i9, int i10) {
        Result resultDecodeWithState;
        long jCurrentTimeMillis = System.currentTimeMillis();
        PlanarYUVLuminanceSource planarYUVLuminanceSourceBuildLuminanceSource = this.f17186a.m13993u().buildLuminanceSource(bArr, i9, i10);
        if (planarYUVLuminanceSourceBuildLuminanceSource != null) {
            try {
                resultDecodeWithState = this.f17187b.decodeWithState(new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSourceBuildLuminanceSource)));
            } catch (ReaderException unused) {
            } finally {
                this.f17187b.reset();
            }
        } else {
            resultDecodeWithState = null;
        }
        Handler handlerM13995w = this.f17186a.m13995w();
        if (resultDecodeWithState == null) {
            if (handlerM13995w != null) {
                Message.obtain(handlerM13995w, R.id.decode_failed).sendToTarget();
                return;
            }
            return;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        Log.d(f17185d, "Found barcode in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " ms");
        if (handlerM13995w != null) {
            Message messageObtain = Message.obtain(handlerM13995w, R.id.decode_succeeded, resultDecodeWithState);
            Bundle bundle = new Bundle();
            m19364a(planarYUVLuminanceSourceBuildLuminanceSource, bundle);
            messageObtain.setData(bundle);
            messageObtain.sendToTarget();
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.f17188c) {
            int i9 = message.what;
            if (i9 == R.id.decode) {
                m19365b((byte[]) message.obj, message.arg1, message.arg2);
            } else if (i9 == R.id.quit) {
                this.f17188c = false;
                Looper.myLooper().quit();
            }
        }
    }
}
