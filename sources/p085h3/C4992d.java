package p085h3;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.cyberlink.you.activity.zxing.CaptureActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.client.android.PreferencesActivity;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* renamed from: h3.d */
/* loaded from: classes.dex */
public final class C4992d extends Thread {

    /* renamed from: b */
    public final CaptureActivity f17191b;

    /* renamed from: c */
    public final Map<DecodeHintType, Object> f17192c;

    /* renamed from: d */
    public Handler f17193d;

    /* renamed from: e */
    public final CountDownLatch f17194e = new CountDownLatch(1);

    public C4992d(CaptureActivity captureActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, ResultPointCallback resultPointCallback) {
        this.f17191b = captureActivity;
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        this.f17192c = enumMap;
        if (map != null) {
            enumMap.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(captureActivity);
            collection = EnumSet.noneOf(BarcodeFormat.class);
            if (defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_DECODE_QR, true)) {
                collection.addAll(C4989a.f17183b);
            }
        }
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) collection);
        if (str != null) {
            enumMap.put((EnumMap) DecodeHintType.CHARACTER_SET, (DecodeHintType) str);
        }
        enumMap.put((EnumMap) DecodeHintType.NEED_RESULT_POINT_CALLBACK, (DecodeHintType) resultPointCallback);
        Log.i("DecodeThread", "Hints: " + enumMap);
    }

    /* renamed from: a */
    public Handler m19367a() throws InterruptedException {
        try {
            this.f17194e.await();
        } catch (InterruptedException unused) {
        }
        return this.f17193d;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f17193d = new HandlerC4990b(this.f17191b, this.f17192c);
        this.f17194e.countDown();
        Looper.loop();
    }
}
