package com.cyberlink.you.activity.zxing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.client.android.CaptureActivityFactory;
import com.google.zxing.client.android.FinishListener;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.PreferencesActivity;
import com.google.zxing.client.android.ViewfinderView;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.clipboard.ClipboardInterface;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.android.result.ResultHandlerFactory;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import p085h3.C4989a;
import p085h3.C4991c;
import p116k4.C5178r;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public final class CaptureActivity extends BaseActivity {

    /* renamed from: s */
    public static final String f12318s = "CaptureActivity";

    /* renamed from: d */
    public CameraManager f12320d;

    /* renamed from: e */
    public CaptureActivityHandler f12321e;

    /* renamed from: f */
    public Result f12322f;

    /* renamed from: g */
    public ViewfinderView f12323g;

    /* renamed from: h */
    public boolean f12324h;

    /* renamed from: i */
    public boolean f12325i;

    /* renamed from: j */
    public IntentSource f12326j;

    /* renamed from: k */
    public Collection<BarcodeFormat> f12327k;

    /* renamed from: l */
    public Map<DecodeHintType, ?> f12328l;

    /* renamed from: m */
    public String f12329m;

    /* renamed from: n */
    public CaptureActivityFactory.InactivityTimerProxy f12330n;

    /* renamed from: o */
    public CaptureActivityFactory.BeepManagerProxy f12331o;

    /* renamed from: p */
    public CaptureActivityFactory.AmbientLightManagerProxy f12332p;

    /* renamed from: q */
    public SurfaceView f12333q;

    /* renamed from: c */
    public final int f12319c = 999;

    /* renamed from: r */
    public SurfaceHolder.Callback f12334r = new SurfaceHolderCallbackC2824d();

    /* renamed from: com.cyberlink.you.activity.zxing.CaptureActivity$a */
    public class ViewOnClickListenerC2821a implements View.OnClickListener {
        public ViewOnClickListenerC2821a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaptureActivity.this.setResult(0);
            CaptureActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.zxing.CaptureActivity$b */
    public class ViewOnClickListenerC2822b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.zxing.CaptureActivity$b$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Permission f12337a;

            public a(Permission permission) {
                this.f12337a = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(CaptureActivity.this, this.f12337a);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                C5178r.m20243l(CaptureActivity.this, 999);
            }
        }

        public ViewOnClickListenerC2822b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
            C5287b.m20583f(permission, new a(permission), CaptureActivity.this);
        }
    }

    /* renamed from: com.cyberlink.you.activity.zxing.CaptureActivity$c */
    public class ViewOnClickListenerC2823c implements View.OnClickListener {
        public ViewOnClickListenerC2823c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("ACTIVITY_INTERACTION_KEY", 2);
            CaptureActivity.this.setResult(-1, intent);
            CaptureActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.zxing.CaptureActivity$d */
    public class SurfaceHolderCallbackC2824d implements SurfaceHolder.Callback {
        public SurfaceHolderCallbackC2824d() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i9, int i10, int i11) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (surfaceHolder == null) {
                Log.e(CaptureActivity.f12318s, "*** WARNING *** surfaceCreated() gave us a null surface!");
            }
            if (CaptureActivity.this.f12324h) {
                return;
            }
            CaptureActivity.this.f12324h = true;
            CaptureActivity.this.m13983D(surfaceHolder);
            CaptureActivity.this.f12333q.setKeepScreenOn(true);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            CaptureActivity.this.f12324h = false;
            CaptureActivity.this.f12333q.setKeepScreenOn(false);
        }
    }

    /* renamed from: y */
    public static Intent m13980y(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) CaptureActivity.class);
        intent.setAction(Intents.Scan.ACTION);
        intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int iMin = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) - 80;
        intent.putExtra(Intents.Scan.WIDTH, iMin);
        intent.putExtra(Intents.Scan.HEIGHT, iMin);
        intent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 0L);
        return intent;
    }

    /* renamed from: B */
    public void m13981B(Result result, Bitmap bitmap, float f9) {
        this.f12330n.onActivity();
        ResultHandler resultHandlerMakeResultHandler = ResultHandlerFactory.makeResultHandler(this, result);
        if (bitmap != null) {
            this.f12331o.playBeepSoundAndVibrate();
        }
        if (this.f12326j == IntentSource.NATIVE_APP_INTENT) {
            m13982C(result, resultHandlerMakeResultHandler, bitmap);
        }
    }

    /* renamed from: C */
    public final void m13982C(Result result, ResultHandler resultHandler, Bitmap bitmap) {
        long longExtra = getIntent() != null ? getIntent().getLongExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 1500L) : 1500L;
        if (this.f12325i && !resultHandler.areContentsSecure()) {
            ClipboardInterface.setText(resultHandler.getDisplayContents(), this);
        }
        if (this.f12326j == IntentSource.NATIVE_APP_INTENT) {
            Intent intent = new Intent(getIntent().getAction());
            intent.addFlags(524288);
            intent.putExtra(Intents.Scan.RESULT, result.toString());
            intent.putExtra(Intents.Scan.RESULT_FORMAT, result.getBarcodeFormat().toString());
            byte[] rawBytes = result.getRawBytes();
            if (rawBytes != null && rawBytes.length > 0) {
                intent.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
            }
            Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
            if (resultMetadata != null) {
                ResultMetadataType resultMetadataType = ResultMetadataType.UPC_EAN_EXTENSION;
                if (resultMetadata.containsKey(resultMetadataType)) {
                    intent.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION, resultMetadata.get(resultMetadataType).toString());
                }
                Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
                if (number != null) {
                    intent.putExtra(Intents.Scan.RESULT_ORIENTATION, number.intValue());
                }
                String str = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
                if (str != null) {
                    intent.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, str);
                }
                Iterable iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
                if (iterable != null) {
                    Iterator it = iterable.iterator();
                    int i9 = 0;
                    while (it.hasNext()) {
                        intent.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i9, (byte[]) it.next());
                        i9++;
                    }
                }
            }
            m13988L(R.id.return_scan_result, intent, longExtra);
        }
    }

    /* renamed from: D */
    public final void m13983D(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (this.f12320d.isOpen()) {
            Log.w(f12318s, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            if (m13985H()) {
                int iM13989o = m13989o(540.0f);
                this.f12320d.setManualFramingRect(iM13989o, iM13989o);
            }
            this.f12320d.openDriver(surfaceHolder);
            if (this.f12321e == null) {
                this.f12321e = new CaptureActivityHandler(this, this.f12327k, this.f12328l, this.f12329m, this.f12320d);
            }
            m13990q(null, null);
        } catch (IOException e9) {
            Log.w(f12318s, e9);
            m13991r();
        } catch (RuntimeException e10) {
            Log.w(f12318s, "Unexpected error initializing camera", e10);
            m13991r();
        }
        m13984E();
    }

    /* renamed from: E */
    public final void m13984E() {
        Rect framingRect = this.f12320d.getFramingRect();
        if (framingRect == null) {
            return;
        }
        View viewFindViewById = findViewById(R.id.viewfinder_header);
        View viewFindViewById2 = findViewById(R.id.viewfinder_top_title);
        ViewGroup.LayoutParams layoutParams = viewFindViewById2.getLayoutParams();
        layoutParams.height = framingRect.top - viewFindViewById.getHeight();
        viewFindViewById2.setLayoutParams(layoutParams);
        View viewFindViewById3 = findViewById(R.id.viewfinder_region);
        ViewGroup.LayoutParams layoutParams2 = viewFindViewById3.getLayoutParams();
        layoutParams2.width = framingRect.width();
        layoutParams2.height = framingRect.height();
        viewFindViewById3.setLayoutParams(layoutParams2);
        View viewFindViewById4 = findViewById(R.id.viewfinder_region_box);
        ViewGroup.LayoutParams layoutParams3 = viewFindViewById4.getLayoutParams();
        layoutParams3.height = framingRect.height();
        viewFindViewById4.setLayoutParams(layoutParams3);
        findViewById(R.id.viewfinder_back).setOnClickListener(new ViewOnClickListenerC2821a());
        findViewById(R.id.viewfinder_from_gallery).setOnClickListener(new ViewOnClickListenerC2822b());
        findViewById(R.id.viewfinder_show_myqrcode).setOnClickListener(new ViewOnClickListenerC2823c());
    }

    /* renamed from: H */
    public final boolean m13985H() {
        return ((getResources().getConfiguration().screenLayout & 15) == 4) || ((getResources().getConfiguration().screenLayout & 15) == 3);
    }

    /* renamed from: I */
    public void m13986I(String str) {
        QRCodeReader qRCodeReader = new QRCodeReader();
        Log.d(f12318s, "[onScanQRCode] imagePath = " + str);
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        if (bitmapDecodeFile != null) {
            int[] iArr = new int[bitmapDecodeFile.getWidth() * bitmapDecodeFile.getHeight()];
            bitmapDecodeFile.getPixels(iArr, 0, bitmapDecodeFile.getWidth(), 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight());
            try {
                Result resultDecode = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), iArr))));
                Intent intent = new Intent();
                intent.putExtra(Intents.Scan.RESULT, resultDecode.getText());
                setResult(-1, intent);
                finish();
            } catch (Throwable th) {
                th.printStackTrace();
                C5187v0.m20267c(R.string.qrcode_scan_invalid_photo);
            }
        }
    }

    /* renamed from: J */
    public final void m13987J() {
        this.f12323g.setVisibility(0);
    }

    /* renamed from: L */
    public final void m13988L(int i9, Object obj, long j9) {
        CaptureActivityHandler captureActivityHandler = this.f12321e;
        if (captureActivityHandler != null) {
            Message messageObtain = Message.obtain(captureActivityHandler, i9, obj);
            if (j9 > 0) {
                this.f12321e.sendMessageDelayed(messageObtain, j9);
            } else {
                this.f12321e.sendMessage(messageObtain);
            }
        }
    }

    /* renamed from: o */
    public final int m13989o(float f9) {
        return (int) (f9 * (getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        Uri data;
        if (i10 != -1 || i9 != 999 || (data = intent.getData()) == null || data.getAuthority() == null) {
            return;
        }
        if (data.getAuthority().equals("com.google.android.apps.photos.contentprovider")) {
            Log.d(f12318s, "[onActivityResult] selectedImage path = " + data);
            data = Uri.parse(CLUtility.m16576q0(this, data));
        }
        m13986I(new File(CLUtility.m16576q0(this, data)).getAbsolutePath());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_capture);
        this.f12333q = (SurfaceView) findViewById(R.id.preview_view);
        this.f12324h = false;
        this.f12330n = new CaptureActivityFactory.InactivityTimerProxy(this);
        this.f12331o = new CaptureActivityFactory.BeepManagerProxy(this);
        this.f12332p = new CaptureActivityFactory.AmbientLightManagerProxy(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.capture, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f12330n.shutdown();
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            if (i9 != 27 && i9 != 80) {
                if (i9 == 24) {
                    this.f12320d.setTorch(true);
                } else if (i9 == 25) {
                    this.f12320d.setTorch(false);
                    return true;
                }
            }
            return true;
        }
        if (this.f12326j == IntentSource.NATIVE_APP_INTENT) {
            setResult(0);
            finish();
            return true;
        }
        return super.onKeyDown(i9, keyEvent);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        CaptureActivityHandler captureActivityHandler = this.f12321e;
        if (captureActivityHandler != null) {
            captureActivityHandler.m13997a();
            this.f12321e = null;
        }
        this.f12330n.onPause();
        this.f12332p.stop();
        this.f12331o.close();
        this.f12320d.closeDriver();
        if (!this.f12324h) {
            this.f12333q.getHolder().removeCallback(this.f12334r);
        }
        super.onPause();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        int intExtra;
        super.onResume();
        this.f12320d = new CameraManager(getApplication());
        ViewfinderView viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        this.f12323g = viewfinderView;
        viewfinderView.setCameraManager(this.f12320d);
        this.f12321e = null;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean z8 = true;
        if (defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_DISABLE_AUTO_ORIENTATION, true)) {
            setRequestedOrientation(m13994v());
        } else {
            setRequestedOrientation(6);
        }
        m13987J();
        this.f12331o.updatePrefs();
        this.f12332p.start(this.f12320d);
        this.f12330n.onResume();
        Intent intent = getIntent();
        if (!defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_COPY_TO_CLIPBOARD, true) || (intent != null && !intent.getBooleanExtra(Intents.Scan.SAVE_HISTORY, true))) {
            z8 = false;
        }
        this.f12325i = z8;
        this.f12326j = IntentSource.NONE;
        this.f12327k = null;
        this.f12329m = null;
        if (intent != null) {
            if (Intents.Scan.ACTION.equals(intent.getAction())) {
                this.f12326j = IntentSource.NATIVE_APP_INTENT;
                this.f12327k = C4989a.m19362a(intent);
                this.f12328l = C4991c.m19366a(intent);
                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int intExtra2 = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int intExtra3 = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (intExtra2 > 0 && intExtra3 > 0) {
                        this.f12320d.setManualFramingRect(intExtra2, intExtra3);
                    }
                }
                if (intent.hasExtra(Intents.Scan.CAMERA_ID) && (intExtra = intent.getIntExtra(Intents.Scan.CAMERA_ID, -1)) >= 0) {
                    this.f12320d.setManualCameraId(intExtra);
                }
            }
            this.f12329m = intent.getStringExtra(Intents.Scan.CHARACTER_SET);
        }
        SurfaceHolder holder = this.f12333q.getHolder();
        if (this.f12324h) {
            m13983D(holder);
        } else {
            holder.addCallback(this.f12334r);
        }
    }

    /* renamed from: q */
    public final void m13990q(Bitmap bitmap, Result result) {
        Log.i(f12318s, "decodeOrStoreSavedBitmap");
        CaptureActivityHandler captureActivityHandler = this.f12321e;
        if (captureActivityHandler == null) {
            this.f12322f = result;
            return;
        }
        if (result != null) {
            this.f12322f = result;
        }
        Result result2 = this.f12322f;
        if (result2 != null) {
            this.f12321e.sendMessage(Message.obtain(captureActivityHandler, R.id.decode_succeeded, result2));
        }
        this.f12322f = null;
    }

    /* renamed from: r */
    public final void m13991r() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    /* renamed from: s */
    public void m13992s() {
        this.f12323g.drawViewfinder();
    }

    /* renamed from: u */
    public CameraManager m13993u() {
        return this.f12320d;
    }

    /* renamed from: v */
    public final int m13994v() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        return getResources().getConfiguration().orientation == 2 ? (rotation == 0 || rotation == 1) ? 0 : 8 : (rotation == 0 || rotation == 3) ? 1 : 9;
    }

    /* renamed from: w */
    public Handler m13995w() {
        return this.f12321e;
    }

    /* renamed from: z */
    public ViewfinderView m13996z() {
        return this.f12323g;
    }
}
