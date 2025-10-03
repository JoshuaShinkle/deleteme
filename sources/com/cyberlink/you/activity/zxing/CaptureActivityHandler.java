package com.cyberlink.you.activity.zxing;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.google.android.exoplayer2.C3322C;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.android.DecodeThread;
import com.google.zxing.client.android.ViewfinderResultPointCallback;
import com.google.zxing.client.android.camera.CameraManager;
import java.util.Collection;
import java.util.Map;
import net.sqlcipher.database.SQLiteDatabase;
import p085h3.C4992d;

/* loaded from: classes.dex */
public final class CaptureActivityHandler extends Handler {

    /* renamed from: e */
    public static final String f12341e = "CaptureActivityHandler";

    /* renamed from: a */
    public final CaptureActivity f12342a;

    /* renamed from: b */
    public final C4992d f12343b;

    /* renamed from: c */
    public State f12344c;

    /* renamed from: d */
    public final CameraManager f12345d;

    public enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureActivity captureActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, CameraManager cameraManager) {
        this.f12342a = captureActivity;
        C4992d c4992d = new C4992d(captureActivity, collection, map, str, new ViewfinderResultPointCallback(captureActivity.m13996z()));
        this.f12343b = c4992d;
        c4992d.start();
        this.f12344c = State.SUCCESS;
        this.f12345d = cameraManager;
        cameraManager.startPreview();
        m13998b();
    }

    /* renamed from: a */
    public void m13997a() {
        this.f12344c = State.DONE;
        this.f12345d.stopPreview();
        Message.obtain(this.f12343b.m19367a(), R.id.quit).sendToTarget();
        try {
            this.f12343b.join(500L);
        } catch (InterruptedException unused) {
        }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    /* renamed from: b */
    public final void m13998b() {
        if (this.f12344c == State.SUCCESS) {
            this.f12344c = State.PREVIEW;
            this.f12345d.requestPreviewFrame(this.f12343b.m19367a(), R.id.decode);
            this.f12342a.m13992s();
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        ActivityInfo activityInfo;
        float f9;
        int i9 = message.what;
        if (i9 == R.id.restart_preview) {
            m13998b();
            return;
        }
        String str = null;
        Bitmap bitmapCopy = null;
        str = null;
        if (i9 == R.id.decode_succeeded) {
            this.f12344c = State.SUCCESS;
            Bundle data = message.getData();
            if (data != null) {
                byte[] byteArray = data.getByteArray(DecodeThread.BARCODE_BITMAP);
                bitmapCopy = byteArray != null ? BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, null).copy(Bitmap.Config.ARGB_8888, true) : null;
                f9 = data.getFloat(DecodeThread.BARCODE_SCALED_FACTOR);
            } else {
                f9 = 1.0f;
            }
            this.f12342a.m13981B((Result) message.obj, bitmapCopy, f9);
            return;
        }
        if (i9 == R.id.decode_failed) {
            this.f12344c = State.PREVIEW;
            this.f12345d.requestPreviewFrame(this.f12343b.m19367a(), R.id.decode);
            return;
        }
        if (i9 == R.id.return_scan_result) {
            this.f12342a.setResult(-1, (Intent) message.obj);
            this.f12342a.finish();
            return;
        }
        if (i9 == R.id.launch_product_query) {
            String str2 = (String) message.obj;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(524288);
            intent.setData(Uri.parse(str2));
            ResolveInfo resolveInfoResolveActivity = this.f12342a.getPackageManager().resolveActivity(intent, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
            if (resolveInfoResolveActivity != null && (activityInfo = resolveInfoResolveActivity.activityInfo) != null) {
                str = activityInfo.packageName;
                Log.d(f12341e, "Using browser in package " + str);
            }
            if ("com.android.browser".equals(str) || "com.android.chrome".equals(str)) {
                intent.setPackage(str);
                intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                intent.putExtra("com.android.browser.application_id", str);
            }
            try {
                this.f12342a.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Log.w(f12341e, "Can't find anything to handle VIEW of URI " + str2);
            }
        }
    }
}
