package com.cyberlink.you.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import p015b4.C0676f;
import p116k4.C5187v0;
import p126l3.C5284c;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p145n3.C5359a;
import p145n3.C5361c;
import p145n3.InterfaceC5360b;

/* loaded from: classes.dex */
public class RegistrationFaceIdActivity extends BaseActivity {

    /* renamed from: F */
    public SensorManager f8623F;

    /* renamed from: d */
    public FaceProcess f8628d;

    /* renamed from: e */
    public View f8629e;

    /* renamed from: f */
    public View f8630f;

    /* renamed from: g */
    public TextView f8631g;

    /* renamed from: i */
    public C5284c f8633i;

    /* renamed from: j */
    public View f8634j;

    /* renamed from: k */
    public View f8635k;

    /* renamed from: l */
    public View f8636l;

    /* renamed from: m */
    public View f8637m;

    /* renamed from: n */
    public ImageView f8638n;

    /* renamed from: o */
    public ImageView f8639o;

    /* renamed from: p */
    public TextureView f8640p;

    /* renamed from: q */
    public TextView f8641q;

    /* renamed from: c */
    public final Handler f8627c = new Handler();

    /* renamed from: h */
    public int f8632h = 0;

    /* renamed from: r */
    public final AtomicBoolean f8642r = new AtomicBoolean(false);

    /* renamed from: s */
    public final Map<Integer, Bitmap> f8643s = new HashMap();

    /* renamed from: t */
    public final int f8644t = 5000;

    /* renamed from: u */
    public long f8645u = 0;

    /* renamed from: v */
    public long f8646v = 0;

    /* renamed from: w */
    public final int f8647w = 4000;

    /* renamed from: x */
    public final int f8648x = 20;

    /* renamed from: y */
    public final int f8649y = -20;

    /* renamed from: z */
    public final int f8650z = 15;

    /* renamed from: A */
    public final int f8618A = -15;

    /* renamed from: B */
    public final int f8619B = 10;

    /* renamed from: C */
    public long f8620C = 0;

    /* renamed from: D */
    public boolean f8621D = false;

    /* renamed from: E */
    public boolean f8622E = Globals.m7388i0().m7434H1();

    /* renamed from: G */
    public final View.OnClickListener f8624G = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ad
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9721b.m9531L(view);
        }
    };

    /* renamed from: H */
    public final InterfaceC5360b<Bitmap> f8625H = new C1651c();

    /* renamed from: I */
    public final SensorEventListener f8626I = new C1652d();

    public enum FaceProcess {
        CAMERA(R.string.face_camera_title, R.string.face_camera_content, 1),
        FACE_RIGHT(R.string.face_right_title, R.string.face_right_content, 2),
        FACE_LEFT(R.string.face_left_title, R.string.face_left_content, 3),
        FACE_UP(R.string.face_up_title, R.string.face_up_content, 4),
        FACE_DOWN(R.string.face_down_title, R.string.face_down_content, 5);

        private final int resContent;
        private final int resTitle;
        private final int step;

        FaceProcess(int i9, int i10, int i11) {
            this.resTitle = i9;
            this.resContent = i10;
            this.step = i11;
        }

        /* renamed from: d */
        public static FaceProcess m9558d(int i9) {
            for (FaceProcess faceProcess : values()) {
                if (faceProcess.step == i9) {
                    return faceProcess;
                }
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.RegistrationFaceIdActivity$a */
    public class C1649a implements InterfaceC5288c {
        public C1649a() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
            RegistrationFaceIdActivity.this.m9548O();
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() throws IOException {
            RegistrationFaceIdActivity.this.m9552V();
        }
    }

    /* renamed from: com.cyberlink.you.activity.RegistrationFaceIdActivity$b */
    public class C1650b implements InterfaceC5288c {
        public C1650b() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
            RegistrationFaceIdActivity.this.m9548O();
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.RegistrationFaceIdActivity$c */
    public class C1651c implements InterfaceC5360b<Bitmap> {
        public C1651c() {
        }

        @Override // p145n3.InterfaceC5360b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo8198a(Bitmap bitmap) {
            RegistrationFaceIdActivity.m9541s(RegistrationFaceIdActivity.this);
        }
    }

    /* renamed from: com.cyberlink.you.activity.RegistrationFaceIdActivity$d */
    public class C1652d implements SensorEventListener {
        public C1652d() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i9) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 3) {
                float f9 = sensorEvent.values[2];
                if (-10.0f > f9 || f9 > 10.0f) {
                    if (RegistrationFaceIdActivity.this.f8622E) {
                        RegistrationFaceIdActivity.this.m9553y(sensorEvent.values);
                    }
                    RegistrationFaceIdActivity.this.m9551T("not rotate your phone, please", System.currentTimeMillis());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m9527E(StringBuilder sb) {
        this.f8641q.setVisibility(0);
        this.f8641q.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m9528H() {
        Bitmap bitmap;
        if (this.f8628d != null && this.f8630f.getVisibility() == 0) {
            this.f8630f.findViewById(R.id.recognizeLoading).setVisibility(8);
            this.f8630f.findViewById(R.id.recognizeDone).setVisibility(0);
        }
        if (this.f8632h < 6) {
            this.f8639o.setVisibility(8);
            ((AnimationDrawable) this.f8638n.getBackground()).stop();
            bitmap = this.f8643s.get(Integer.valueOf(this.f8632h));
            FaceProcess faceProcess = FaceProcess.FACE_RIGHT;
            FaceProcess faceProcess2 = this.f8628d;
            if (faceProcess == faceProcess2) {
                ImageView imageView = (ImageView) this.f8634j.findViewById(R.id.face_right_arrow);
                ((AnimationDrawable) imageView.getBackground()).stop();
                imageView.setVisibility(8);
                ((ImageView) findViewById(R.id.right_dot)).setVisibility(4);
            } else if (FaceProcess.FACE_LEFT == faceProcess2) {
                ImageView imageView2 = (ImageView) this.f8634j.findViewById(R.id.face_left_arrow);
                ((AnimationDrawable) imageView2.getBackground()).stop();
                imageView2.setVisibility(8);
                ((ImageView) findViewById(R.id.left_dot)).setVisibility(4);
            } else if (FaceProcess.FACE_UP == faceProcess2) {
                ImageView imageView3 = (ImageView) this.f8634j.findViewById(R.id.face_up_arrow);
                ((AnimationDrawable) imageView3.getBackground()).stop();
                imageView3.setVisibility(8);
                ((ImageView) findViewById(R.id.up_dot)).setVisibility(8);
            } else if (FaceProcess.FACE_DOWN == faceProcess2) {
                ImageView imageView4 = (ImageView) this.f8634j.findViewById(R.id.face_down_arrow);
                ((AnimationDrawable) imageView4.getBackground()).stop();
                imageView4.setVisibility(8);
                ((ImageView) findViewById(R.id.down_dot)).setVisibility(4);
            }
        } else {
            bitmap = this.f8643s.get(Integer.valueOf(FaceProcess.CAMERA.step));
        }
        if (this.f8622E) {
            this.f8641q.setVisibility(8);
        }
        if (bitmap != null) {
            this.f8638n.setVisibility(0);
            this.f8638n.setImageBitmap(bitmap);
            this.f8640p.setVisibility(8);
            this.f8639o.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m9529I() throws IOException {
        this.f8638n.setVisibility(8);
        this.f8640p.setVisibility(0);
        m9554z();
        this.f8645u = System.currentTimeMillis();
        this.f8642r.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m9530J() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ed
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                this.f10406b.m9529I();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m9531L(View view) {
        m9548O();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m9532N(View view) {
        if (!m9547D()) {
            C5187v0.m20267c(R.string.video_record_no_available_camera);
            m9548O();
        }
        m9545B();
    }

    /* renamed from: s */
    public static /* synthetic */ C5361c m9541s(RegistrationFaceIdActivity registrationFaceIdActivity) {
        registrationFaceIdActivity.getClass();
        return null;
    }

    /* renamed from: B */
    public final void m9545B() {
        C5287b.m20583f(Permission.CAMERA, new C1649a(), this);
        if (this.f8622E) {
            C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C1650b(), this);
        }
    }

    /* renamed from: C */
    public final void m9546C() throws IOException {
        int i9;
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bd
            @Override // java.lang.Runnable
            public final void run() {
                this.f9750b.m9528H();
            }
        });
        int i10 = 0;
        this.f8621D = false;
        if (this.f8622E && 1 <= (i9 = this.f8632h) && i9 <= 5) {
            File file = new File(CLUtility.m16536g0());
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US).format(new Date()) + "-" + this.f8628d.name() + ".jpg");
            Bitmap bitmap = this.f8643s.get(Integer.valueOf(this.f8632h));
            if (bitmap != null) {
                C5359a.m21054d(bitmap, file2);
            }
        }
        if (this.f8632h == 0) {
            SensorManager sensorManager = this.f8623F;
            sensorManager.registerListener(this.f8626I, sensorManager.getDefaultSensor(3), 2);
        } else {
            i10 = 2000;
        }
        if (this.f8632h < 6) {
            this.f8627c.postDelayed(new Runnable() { // from class: com.cyberlink.you.activity.cd
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9783b.m9530J();
                }
            }, i10);
            return;
        }
        this.f8623F.unregisterListener(this.f8626I);
        Bitmap bitmap2 = this.f8643s.get(Integer.valueOf(FaceProcess.CAMERA.step));
        C0676f.m3390g(bitmap2);
        File file3 = new File(CLUtility.m16532f0(Globals.m7388i0().getApplicationContext()));
        if (!file3.exists()) {
            file3.mkdirs();
        }
        C5359a.m21054d(bitmap2, new File(file3, Globals.m7388i0().m7568k1() + ".jpg"));
        this.f8633i.m20571e();
        new ArrayList();
    }

    /* renamed from: D */
    public final boolean m9547D() {
        return Globals.m7372O().getPackageManager().hasSystemFeature("android.hardware.camera.front");
    }

    /* renamed from: O */
    public final void m9548O() {
        m9549P();
        Intent intent = new Intent();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        intent.putExtras(extras);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: P */
    public final void m9549P() {
        C5284c c5284c = this.f8633i;
        if (c5284c != null) {
            c5284c.m20571e();
        }
    }

    /* renamed from: Q */
    public final void m9550Q() {
        this.f8632h = 0;
        this.f8628d = null;
        this.f8629e.setVisibility(0);
        this.f8630f.setVisibility(8);
        this.f8635k.setVisibility(0);
        this.f8636l.setVisibility(0);
        this.f8637m.setVisibility(8);
        this.f8638n.setImageBitmap(null);
        this.f8638n.setBackgroundResource(R.drawable.anim_face_sample);
        ((AnimationDrawable) this.f8638n.getBackground()).start();
    }

    /* renamed from: T */
    public final void m9551T(String str, long j9) {
        if (!this.f8622E || j9 - this.f8646v <= 4000) {
            return;
        }
        this.f8646v = j9;
        C5187v0.m20271g(str);
    }

    /* renamed from: V */
    public final void m9552V() throws IOException {
        ImageView imageView = (ImageView) findViewById(R.id.face_mask_circle);
        this.f8638n.setVisibility(8);
        this.f8640p.setVisibility(0);
        HashMap map = new HashMap();
        map.put("maskWidth", Integer.valueOf(imageView.getWidth()));
        map.put("maskHeight", Integer.valueOf(imageView.getHeight()));
        map.put("maskMarginTop", Integer.valueOf(this.f8634j.getHeight() - this.f8634j.getWidth()));
        C5284c c5284c = new C5284c(this.f8640p, map, 1, this.f8625H);
        this.f8633i = c5284c;
        c5284c.m20574h();
        m9546C();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9548O();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_registration_face_id);
        findViewById(R.id.RegistrationBackBtn).setOnClickListener(this.f8624G);
        this.f8631g = (TextView) findViewById(R.id.processTitle);
        this.f8629e = findViewById(R.id.beforeSetUp);
        this.f8630f = findViewById(R.id.setUpProcess);
        this.f8634j = findViewById(R.id.face_layout);
        this.f8638n = (ImageView) findViewById(R.id.face_OK);
        this.f8640p = (TextureView) findViewById(R.id.face_preview);
        this.f8639o = (ImageView) findViewById(R.id.face_detect);
        this.f8641q = (TextView) findViewById(R.id.faceInformation);
        this.f8635k = findViewById(R.id.face_me_layout);
        this.f8636l = findViewById(R.id.face_id_scroll);
        this.f8637m = findViewById(R.id.verifyResultContent);
        this.f8629e.findViewById(R.id.startFace).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.zc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12304b.m9532N(view);
            }
        });
        m9550Q();
        ((AnimationDrawable) this.f8638n.getBackground()).start();
        this.f8623F = (SensorManager) getSystemService("sensor");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m9549P();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f8623F.unregisterListener(this.f8626I);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        int i9 = this.f8632h;
        if (1 > i9 || i9 > 5) {
            return;
        }
        SensorManager sensorManager = this.f8623F;
        sensorManager.registerListener(this.f8626I, sensorManager.getDefaultSensor(3), 2);
    }

    /* renamed from: y */
    public final void m9553y(float[] fArr) {
        final StringBuilder sb = new StringBuilder();
        sb.append("value 2:");
        sb.append(String.format(Locale.US, "%.2f", Float.valueOf(fArr[2])));
        sb.append(" limit");
        sb.append("[");
        sb.append("-5");
        sb.append(",");
        sb.append("5");
        sb.append("]\n");
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.dd
            @Override // java.lang.Runnable
            public final void run() {
                this.f10371b.m9527E(sb);
            }
        });
    }

    /* renamed from: z */
    public final void m9554z() throws IOException {
        int i9 = this.f8632h + 1;
        this.f8632h = i9;
        FaceProcess faceProcessM9558d = FaceProcess.m9558d(i9);
        this.f8628d = faceProcessM9558d;
        if (faceProcessM9558d != null) {
            this.f8629e.setVisibility(8);
            this.f8630f.setVisibility(0);
            this.f8631g.setText(this.f8628d.resTitle);
            ((TextView) this.f8630f.findViewById(R.id.processContent)).setText(this.f8628d.resContent);
            this.f8630f.findViewById(R.id.recognizeLoading).setVisibility(0);
            this.f8630f.findViewById(R.id.recognizeDone).setVisibility(8);
            FaceProcess faceProcess = FaceProcess.FACE_RIGHT;
            FaceProcess faceProcess2 = this.f8628d;
            if (faceProcess == faceProcess2) {
                ImageView imageView = (ImageView) this.f8634j.findViewById(R.id.face_right_arrow);
                ((AnimationDrawable) imageView.getBackground()).start();
                imageView.setVisibility(0);
                ((ImageView) findViewById(R.id.right_dot)).setVisibility(0);
            } else if (FaceProcess.FACE_LEFT == faceProcess2) {
                ImageView imageView2 = (ImageView) this.f8634j.findViewById(R.id.face_left_arrow);
                ((AnimationDrawable) imageView2.getBackground()).start();
                imageView2.setVisibility(0);
                ((ImageView) findViewById(R.id.left_dot)).setVisibility(0);
            } else if (FaceProcess.FACE_UP == faceProcess2) {
                ImageView imageView3 = (ImageView) this.f8634j.findViewById(R.id.face_up_arrow);
                ((AnimationDrawable) imageView3.getBackground()).start();
                imageView3.setVisibility(0);
                ((ImageView) findViewById(R.id.up_dot)).setVisibility(0);
            } else if (FaceProcess.FACE_DOWN == faceProcess2) {
                ImageView imageView4 = (ImageView) this.f8634j.findViewById(R.id.face_down_arrow);
                ((AnimationDrawable) imageView4.getBackground()).start();
                imageView4.setVisibility(0);
                ((ImageView) findViewById(R.id.down_dot)).setVisibility(0);
            }
        }
        if (this.f8632h == 6) {
            m9546C();
        }
    }
}
