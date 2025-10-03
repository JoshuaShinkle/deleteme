package com.cyberlink.you.fingerpaint;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cyberlink.clgpuimage.Rotation;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.PresetEditViewActivity;
import com.cyberlink.you.fingerpaint.FingerPaintActivity;
import com.cyberlink.you.fingerpaint.kernel.C3036c;
import com.cyberlink.you.fingerpaint.kernel.FingerPaintObject;
import com.cyberlink.you.fingerpaint.view.FingerPaintImageView;
import com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p056e4.C4755b;
import p116k4.C5152i0;
import p116k4.C5187v0;
import p173q2.C6136j;
import p174q3.C6152i;
import p209u2.C6383t;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class FingerPaintActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {

    /* renamed from: a0 */
    public static final String f13499a0 = "FingerPaintActivity";

    /* renamed from: b0 */
    public static int f13500b0 = 0;

    /* renamed from: c0 */
    public static int f13501c0 = 2131297272;

    /* renamed from: A */
    public ImageButton f13502A;

    /* renamed from: B */
    public ImageButton f13503B;

    /* renamed from: C */
    public ImageButton f13504C;

    /* renamed from: D */
    public View f13505D;

    /* renamed from: E */
    public View f13506E;

    /* renamed from: F */
    public LinearLayout f13507F;

    /* renamed from: G */
    public ImageButton[] f13508G;

    /* renamed from: H */
    public View f13509H;

    /* renamed from: I */
    public View f13510I;

    /* renamed from: J */
    public View f13511J;

    /* renamed from: K */
    public ImageButton f13512K;

    /* renamed from: L */
    public ImageButton f13513L;

    /* renamed from: M */
    public ImageView f13514M;

    /* renamed from: O */
    public View f13516O;

    /* renamed from: P */
    public Button f13517P;

    /* renamed from: Q */
    public View f13518Q;

    /* renamed from: R */
    public View[] f13519R;

    /* renamed from: Y */
    public Runnable f13526Y;

    /* renamed from: d */
    public int[] f13529d;

    /* renamed from: e */
    public int f13530e;

    /* renamed from: f */
    public Bitmap f13531f;

    /* renamed from: g */
    public String f13532g;

    /* renamed from: h */
    public String f13533h;

    /* renamed from: i */
    public String f13534i;

    /* renamed from: j */
    public String f13535j;

    /* renamed from: k */
    public String f13536k;

    /* renamed from: l */
    public String f13537l;

    /* renamed from: q */
    public ProgressDialog f13542q;

    /* renamed from: r */
    public ImageButton f13543r;

    /* renamed from: s */
    public FingerPaintImageView f13544s;

    /* renamed from: t */
    public View f13545t;

    /* renamed from: u */
    public View f13546u;

    /* renamed from: v */
    public ImageView f13547v;

    /* renamed from: w */
    public ImageView f13548w;

    /* renamed from: x */
    public View f13549x;

    /* renamed from: y */
    public ImageButton f13550y;

    /* renamed from: z */
    public ImageButton f13551z;

    /* renamed from: c */
    public C3036c f13528c = C3036c.m15579m();

    /* renamed from: m */
    public boolean f13538m = false;

    /* renamed from: n */
    public boolean f13539n = true;

    /* renamed from: o */
    public boolean f13540o = false;

    /* renamed from: p */
    public boolean f13541p = false;

    /* renamed from: N */
    public EditText f13515N = null;

    /* renamed from: S */
    public float f13520S = 8.0f;

    /* renamed from: T */
    public float f13521T = 50.0f;

    /* renamed from: U */
    public final int[] f13522U = C6152i.f20778a;

    /* renamed from: V */
    public final int[] f13523V = C6152i.f20779b;

    /* renamed from: W */
    public ToolbarMode f13524W = ToolbarMode.Mode1;

    /* renamed from: X */
    public final int[] f13525X = {R.drawable.doodle_brush_size_s_p, R.drawable.doodle_brush_size_m_p, R.drawable.doodle_brush_size_l_p};

    /* renamed from: Z */
    public DialogInterface.OnClickListener f13527Z = new DialogInterface.OnClickListener() { // from class: q3.e
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i9) {
            this.f20775b.m15512T(dialogInterface, i9);
        }
    };

    public enum ToolbarMode {
        Mode1,
        Mode2
    }

    /* renamed from: com.cyberlink.you.fingerpaint.FingerPaintActivity$a */
    public class C3033a implements GLViewEngine.InterfaceC3075c<Bitmap> {
        public C3033a() {
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: b */
        public void mo15552b(Object obj, String str) {
            FingerPaintActivity.this.m15536b0(null);
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo15551a(Object obj, Bitmap bitmap) {
            if (bitmap != null) {
                FingerPaintActivity.this.m15536b0(bitmap);
            } else {
                FingerPaintActivity.this.m15536b0(null);
                Log.d(FingerPaintActivity.f13499a0, "Applied fail.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m15509O(boolean z8) {
        int i9;
        Thread.currentThread().setName("Finger Paint Save");
        this.f13528c.m15596r();
        Intent intent = new Intent();
        if (m15538d0()) {
            intent.putExtra("start_import", z8);
            intent.putExtra("handDrawImg", this.f13534i);
            if (this.f13538m) {
                intent.putExtra("doodleImg", this.f13535j);
                intent.putExtra(TtmlNode.ATTR_TTS_COLOR, "#" + Integer.toHexString(this.f13528c.m15589j()).toUpperCase(Locale.getDefault()));
            }
            CLUtility.m16455K(this.f13532g, CLUtility.m16510Z1(this.f13533h));
            i9 = -1;
        } else {
            i9 = 0;
        }
        setResult(i9, intent);
        if (!isFinishing()) {
            C5152i0.m20065b(this.f13542q);
        }
        finish();
    }

    /* renamed from: P */
    public static /* synthetic */ void m15510P(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m15511Q(DialogInterface dialogInterface, int i9) {
        this.f13528c.m15593o();
        this.f13544s.m15610u();
        m15546n0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ void m15512T(DialogInterface dialogInterface, int i9) {
        if (i9 != -1) {
            return;
        }
        this.f13528c.m15595q();
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V */
    public /* synthetic */ void m15513V() {
        boolean z8;
        FingerPaintImageView fingerPaintImageView;
        Bitmap bitmap = this.f13531f;
        if (bitmap == null || (fingerPaintImageView = this.f13544s) == null) {
            C5187v0.m20267c(R.string.error_server_response);
            z8 = true;
        } else {
            fingerPaintImageView.setImageBitmap(bitmap);
            this.f13544s.invalidate();
            float width = this.f13531f.getWidth() / 1080.0f;
            this.f13520S = 8.0f * width;
            this.f13521T = width * 50.0f;
            m15537c0(Globals.m7388i0().m7609s0());
            z8 = false;
        }
        if (!isFinishing()) {
            C5152i0.m20065b(this.f13542q);
        }
        if (z8) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X */
    public /* synthetic */ void m15514X(DialogInterface dialogInterface, int i9) {
        this.f13528c.m15581b();
        this.f13544s.m15610u();
        m15546n0();
    }

    /* renamed from: Y */
    public static /* synthetic */ void m15515Y(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: B */
    public final void m15526B() {
        if (this.f13506E.getVisibility() == 4) {
            m15544l0(0);
            m15543k0(4);
            m15545m0(4);
        }
    }

    /* renamed from: C */
    public final void m15527C() {
        if (this.f13549x.getVisibility() == 4) {
            m15545m0(0);
            m15543k0(4);
            m15544l0(4);
        }
    }

    /* renamed from: D */
    public final void m15528D() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.doodle_reset_message));
        builderM16382a.setNegativeButton(getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: q3.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                FingerPaintActivity.m15510P(dialogInterface, i9);
            }
        });
        builderM16382a.setPositiveButton(getString(R.string.reset), new DialogInterface.OnClickListener() { // from class: q3.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20771b.m15511Q(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* renamed from: E */
    public final void m15529E() {
        this.f13528c.m15602x();
        this.f13544s.m15610u();
        m15546n0();
    }

    /* renamed from: H */
    public final void m15530H(Bitmap bitmap) {
        GLViewEngine.m15970g().m15973f(bitmap, new GLViewEngine.EffectParam(PresetEditViewActivity.f8504z, 0.7d, bitmap, Rotation.NORMAL, false, false, GLViewEngine.EffectParam.ExtraFunc.None), null, null, new C3033a(), null);
    }

    /* renamed from: I */
    public final void m15531I(Bitmap bitmap) {
        if (bitmap == null || this.f13540o || this.f13537l == null) {
            return;
        }
        try {
            new Canvas(bitmap).drawBitmap(C6136j.m23586f(this, this.f13537l), BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
        } catch (Throwable th) {
            Log.e(f13499a0, "Cannot overlay doodle", th);
        }
    }

    /* renamed from: J */
    public final void m15532J() {
        String str = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date());
        String strM24518g = C6383t.m24518g(5);
        if (Globals.m7388i0().m7409C1().booleanValue()) {
            StringBuilder sb = new StringBuilder();
            sb.append(CLUtility.m16472O0(this));
            String str2 = File.separator;
            sb.append(str2);
            sb.append("U_HandDraw");
            sb.append("_");
            sb.append(str);
            sb.append("_");
            sb.append(strM24518g);
            this.f13534i = sb.toString();
            if (this.f13538m) {
                this.f13535j = CLUtility.m16472O0(this) + str2 + "U_HandDraw_" + str + "_" + strM24518g + "D";
                return;
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(CLUtility.m16472O0(this));
        String str3 = File.separator;
        sb2.append(str3);
        sb2.append("U_HandDraw");
        sb2.append("_");
        sb2.append(str);
        sb2.append("_");
        sb2.append(strM24518g);
        sb2.append(".jpg");
        this.f13534i = sb2.toString();
        if (this.f13538m) {
            this.f13535j = CLUtility.m16472O0(this) + str3 + "U_HandDraw_" + str + "_" + strM24518g + ".png";
        }
    }

    /* renamed from: L */
    public final void m15533L() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.f13542q = progressDialog;
        progressDialog.setMessage(getResources().getString(R.string.processing));
        this.f13542q.setIndeterminate(true);
        this.f13542q.setCancelable(false);
        this.f13542q.show();
        C6385v.m24526d(new Runnable() { // from class: q3.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f20772b.m15534N();
            }
        });
    }

    /* renamed from: N */
    public final void m15534N() {
        m15532J();
        Uri uriM16510Z1 = CLUtility.m16510Z1(this.f13533h);
        Bitmap bitmapM23584d = uriM16510Z1 != null ? C6136j.m23584d(this, uriM16510Z1, 1280) : null;
        if (bitmapM23584d == null) {
            bitmapM23584d = C6136j.m23587g(this, C6383t.m24517f(this.f13532g) ? this.f13536k : this.f13532g, 1280);
        }
        this.f13529d = new int[]{bitmapM23584d.getWidth(), bitmapM23584d.getHeight()};
        if (this.f13541p) {
            m15530H(bitmapM23584d);
        } else {
            m15536b0(bitmapM23584d);
        }
    }

    /* renamed from: Z */
    public final void m15535Z(int i9) {
        m15540g0(false, f13500b0);
        m15540g0(true, i9);
        this.f13548w.setImageResource(this.f13523V[i9]);
        this.f13548w.invalidate();
        C3036c c3036c = this.f13528c;
        int[] iArr = FingerPaintObject.f13556b;
        c3036c.m15598t(iArr[i9]);
        f13500b0 = i9;
        Globals.m7388i0().m7584n3(iArr[i9]);
    }

    /* renamed from: b0 */
    public final void m15536b0(Bitmap bitmap) {
        m15531I(bitmap);
        if (bitmap != null) {
            this.f13531f = bitmap;
        }
        C6385v.m24527e(new Runnable() { // from class: q3.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f20777b.m15513V();
            }
        });
    }

    /* renamed from: c0 */
    public void m15537c0(int i9) {
        if (i9 == 0) {
            m15541i0(0);
        } else if (i9 == 20) {
            m15541i0(1);
        } else if (i9 == 100) {
            m15541i0(2);
        } else {
            m15541i0(1);
            i9 = 20;
        }
        Globals.m7388i0().m7589o3(i9);
        float f9 = this.f13520S;
        this.f13528c.m15599u(f9 + ((this.f13521T - f9) * (i9 / 100.0f)));
    }

    /* renamed from: d0 */
    public final boolean m15538d0() throws IOException {
        boolean zM15539e0 = m15539e0(this.f13531f, this.f13534i, Bitmap.CompressFormat.JPEG);
        if (!this.f13538m) {
            return zM15539e0;
        }
        Bitmap bitmapCreateBitmap = null;
        if (this.f13537l != null) {
            new BitmapFactory.Options().inMutable = true;
            try {
                bitmapCreateBitmap = C6136j.m23586f(this, this.f13537l);
            } catch (OutOfMemoryError e9) {
                e9.printStackTrace();
            }
        } else {
            try {
                int[] iArr = this.f13529d;
                bitmapCreateBitmap = Bitmap.createBitmap(iArr[0], iArr[1], Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
            }
        }
        return zM15539e0 & m15539e0(bitmapCreateBitmap, this.f13535j, Bitmap.CompressFormat.PNG);
    }

    /* renamed from: e0 */
    public final boolean m15539e0(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) throws IOException {
        Canvas canvas;
        if (bitmap == null) {
            return false;
        }
        if (bitmap.isMutable()) {
            canvas = new Canvas(bitmap);
        } else {
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            canvas = new Canvas(bitmap);
        }
        this.f13528c.m15583d(canvas, this.f13530e);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(compressFormat, 60, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception unused) {
            Log.e(f13499a0, "Save painted image error");
            return false;
        }
    }

    /* renamed from: g0 */
    public final void m15540g0(boolean z8, int i9) {
        if (z8) {
            int[] iArr = FingerPaintObject.f13556b;
            this.f13508G[i9].setImageResource((i9 >= iArr.length || iArr[i9] != -1) ? R.drawable.icon_select_n : R.drawable.icon_select_white);
        } else {
            this.f13508G[i9].setImageResource(0);
        }
        this.f13508G[i9].invalidate();
    }

    /* renamed from: i0 */
    public final void m15541i0(int i9) {
        if (this.f13519R.length <= i9) {
            Log.d(f13499a0, "[selectSizeButton] Given idx = " + i9 + " and size of m_drawSizeButtons is " + this.f13519R.length);
            return;
        }
        int i10 = 0;
        while (true) {
            View[] viewArr = this.f13519R;
            if (i10 >= viewArr.length) {
                return;
            }
            ImageView imageView = (ImageView) viewArr[i10].findViewById(R.id.imgBrushSize);
            if (i10 == i9) {
                imageView.setImageResource(this.f13525X[i9]);
            } else {
                imageView.setImageResource(0);
            }
            i10++;
        }
    }

    @TargetApi(16)
    /* renamed from: j0 */
    public final void m15542j0(View view, Drawable drawable) {
        if (view != null) {
            view.setBackground(drawable);
        }
    }

    /* renamed from: k0 */
    public final void m15543k0(int i9) {
        this.f13505D.setVisibility(i9);
        this.f13510I.setVisibility(i9);
        this.f13545t.setBackgroundColor(i9 == 0 ? getResources().getColor(R.color.you_color_30_percentage_white) : 0);
    }

    /* renamed from: l0 */
    public final void m15544l0(int i9) {
        this.f13506E.setVisibility(i9);
        this.f13511J.setVisibility(i9);
        this.f13546u.setBackgroundColor(i9 == 0 ? getResources().getColor(R.color.you_color_30_percentage_white) : 0);
    }

    /* renamed from: m0 */
    public final void m15545m0(int i9) {
        this.f13549x.setVisibility(i9);
        this.f13509H.setVisibility(i9);
    }

    /* renamed from: n0 */
    public void m15546n0() {
        this.f13512K.setEnabled(this.f13528c.m15580a());
        this.f13512K.setAlpha(this.f13528c.m15580a() ? 1.0f : 0.3f);
        this.f13513L.setEnabled(this.f13528c.m15580a());
        this.f13513L.setAlpha(this.f13528c.m15580a() ? 1.0f : 0.3f);
        Button button = this.f13517P;
        if (button != null) {
            button.setEnabled(this.f13528c.m15580a());
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m15548w();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id >= 0 && id < FingerPaintObject.f13556b.length) {
            m15535Z(id);
            return;
        }
        switch (id) {
            case R.id.btnBottomDone /* 2131297219 */:
                m15549y(true);
                break;
            case R.id.btnBrushSize_l /* 2131297220 */:
                m15537c0(100);
                break;
            case R.id.btnBrushSize_m /* 2131297221 */:
                m15537c0(20);
                break;
            case R.id.btnBrushSize_s /* 2131297222 */:
                m15537c0(0);
                break;
            default:
                switch (id) {
                    case R.id.btnPaintColorContainer /* 2131297244 */:
                        m15550z();
                        break;
                    case R.id.btnPaintReset /* 2131297245 */:
                        m15528D();
                        break;
                    case R.id.btnPaintSizeContainer /* 2131297246 */:
                        m15526B();
                        break;
                    case R.id.btnPaintType /* 2131297247 */:
                        m15527C();
                        break;
                    default:
                        switch (id) {
                            case R.id.btnPaintUndo /* 2131297249 */:
                                m15529E();
                                break;
                            case R.id.btnfingerPaintCancel /* 2131297301 */:
                            case R.id.fingerPaintBackBtn /* 2131297785 */:
                                m15548w();
                                break;
                            case R.id.btnfingerPaintDone /* 2131297303 */:
                                m15549y(false);
                                break;
                            default:
                                switch (id) {
                                    case R.id.btn_drawtool_arrow /* 2131297269 */:
                                    case R.id.btn_drawtool_frame /* 2131297270 */:
                                    case R.id.btn_drawtool_marker /* 2131297271 */:
                                    case R.id.btn_drawtool_pen /* 2131297272 */:
                                    case R.id.btn_drawtool_text /* 2131297273 */:
                                        m15547v(id);
                                        m15527C();
                                        this.f13515N.setVisibility(4);
                                        break;
                                }
                        }
                }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.finger_paint_activity);
        Intent intent = getIntent();
        this.f13530e = intent.getIntExtra("RotateDegree", 0);
        this.f13539n = intent.getBooleanExtra("isLocalEditMode", true);
        this.f13538m = intent.getBooleanExtra("isDoodleOnly", false);
        if (this.f13539n) {
            this.f13532g = intent.getStringExtra("mergedImage");
            String stringExtra = intent.getStringExtra("mergedImageUri");
            this.f13533h = stringExtra;
            if (CLUtility.m16613z1(this.f13532g, CLUtility.m16510Z1(stringExtra))) {
                this.f13540o = true;
            } else {
                this.f13532g = intent.getStringExtra("src");
                this.f13533h = intent.getStringExtra("uri");
            }
        } else {
            this.f13532g = intent.getStringExtra("mergedImage");
            String stringExtra2 = intent.getStringExtra("mergedImageUri");
            this.f13533h = stringExtra2;
            if (CLUtility.m16613z1(this.f13532g, CLUtility.m16510Z1(stringExtra2))) {
                this.f13540o = true;
            }
            this.f13536k = intent.getStringExtra("srcUrl");
            this.f13537l = intent.getStringExtra("doodleUrl");
        }
        this.f13528c.m15593o();
        this.f13541p = intent.getBooleanExtra("isApplyPreset", false);
        Serializable serializableExtra = intent.getSerializableExtra("toolbarMode");
        if (serializableExtra != null) {
            this.f13524W = (ToolbarMode) serializableExtra;
        }
        this.f13531f = null;
        this.f13542q = null;
        this.f13544s = (FingerPaintImageView) findViewById(R.id.paintImageView);
        this.f13549x = findViewById(R.id.drawtoolContainer);
        this.f13505D = findViewById(R.id.drawColorContainer);
        this.f13506E = findViewById(R.id.drawSizeContainer);
        this.f13550y = (ImageButton) findViewById(R.id.btn_drawtool_pen);
        this.f13551z = (ImageButton) findViewById(R.id.btn_drawtool_marker);
        this.f13502A = (ImageButton) findViewById(R.id.btn_drawtool_arrow);
        this.f13503B = (ImageButton) findViewById(R.id.btn_drawtool_frame);
        this.f13504C = (ImageButton) findViewById(R.id.btn_drawtool_text);
        this.f13507F = (LinearLayout) findViewById(R.id.colorSeletionContainer);
        this.f13514M = (ImageView) findViewById(R.id.fingerPaintBackBtn);
        if (this.f13524W.equals(ToolbarMode.Mode1)) {
            View viewFindViewById = findViewById(R.id.fingerPaintBottomBar1);
            this.f13516O = viewFindViewById;
            this.f13517P = (Button) viewFindViewById.findViewById(R.id.btnBottomDone);
        } else {
            View viewFindViewById2 = findViewById(R.id.fingerPaintBottomBar2);
            this.f13516O = viewFindViewById2;
            this.f13518Q = viewFindViewById2.findViewById(R.id.btnfingerPaintCancel);
            this.f13514M.setVisibility(4);
        }
        this.f13516O.setVisibility(0);
        this.f13543r = (ImageButton) this.f13516O.findViewById(R.id.btnfingerPaintDone);
        this.f13545t = this.f13516O.findViewById(R.id.btnPaintColorContainer);
        this.f13546u = this.f13516O.findViewById(R.id.btnPaintSizeContainer);
        this.f13547v = (ImageView) this.f13516O.findViewById(R.id.btnPaintType);
        this.f13548w = (ImageView) this.f13516O.findViewById(R.id.btnPaintColor);
        this.f13509H = this.f13516O.findViewById(R.id.PointTypeAnchor);
        this.f13510I = this.f13516O.findViewById(R.id.PointColorAnchor);
        this.f13511J = this.f13516O.findViewById(R.id.PointSizeAnchor);
        this.f13512K = (ImageButton) this.f13516O.findViewById(R.id.btnPaintUndo);
        this.f13513L = (ImageButton) this.f13516O.findViewById(R.id.btnPaintReset);
        this.f13508G = new ImageButton[FingerPaintObject.f13556b.length];
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fingerpaint_color_palette_elem_size);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.fingerpaint_color_palette_elem_margin);
        int i9 = 0;
        while (i9 < FingerPaintObject.f13556b.length) {
            int i10 = i9 == 0 ? dimensionPixelSize2 : 0;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
            layoutParams.setMargins(i10, 0, dimensionPixelSize2, 0);
            this.f13508G[i9] = new ImageButton(this);
            this.f13508G[i9].setLayoutParams(layoutParams);
            this.f13508G[i9].setPadding(5, 5, 5, 5);
            this.f13508G[i9].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.f13508G[i9].setId(i9);
            this.f13508G[i9].setOnClickListener(this);
            this.f13507F.addView(this.f13508G[i9], i9);
            m15542j0(this.f13508G[i9], getResources().getDrawable(this.f13522U[i9]));
            this.f13508G[i9].setContentDescription("[AID]Doodle_Color" + i9);
            m15540g0(false, i9);
            i9++;
        }
        m15547v(f13501c0);
        m15535Z(C6152i.m23611c(Globals.m7388i0().m7603r0()));
        View[] viewArr = new View[3];
        this.f13519R = viewArr;
        viewArr[0] = findViewById(R.id.btnBrushSize_s);
        this.f13519R[1] = findViewById(R.id.btnBrushSize_m);
        this.f13519R[2] = findViewById(R.id.btnBrushSize_l);
        for (View view : this.f13519R) {
            view.setOnClickListener(this);
        }
        this.f13548w.setImageResource(this.f13523V[f13500b0]);
        m15546n0();
        this.f13543r.setOnClickListener(this);
        this.f13547v.setOnClickListener(this);
        this.f13545t.setOnClickListener(this);
        this.f13546u.setOnClickListener(this);
        this.f13550y.setOnClickListener(this);
        this.f13551z.setOnClickListener(this);
        this.f13502A.setOnClickListener(this);
        this.f13503B.setOnClickListener(this);
        this.f13504C.setOnClickListener(this);
        this.f13512K.setOnClickListener(this);
        this.f13512K.setOnLongClickListener(this);
        this.f13513L.setOnClickListener(this);
        this.f13514M.setOnClickListener(this);
        Button button = this.f13517P;
        if (button != null) {
            button.setOnClickListener(this);
        }
        View view2 = this.f13518Q;
        if (view2 != null) {
            view2.setOnClickListener(this);
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.paintImageContainer);
        EditText editText = new EditText(this);
        this.f13515N = editText;
        relativeLayout.addView(editText);
        this.f13515N.setVisibility(4);
        m15533L();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        if (this.f13531f != null) {
            this.f13531f = null;
            System.gc();
        }
        this.f13544s.m15611v();
        this.f13544s = null;
        this.f13543r = null;
        Button button = this.f13517P;
        if (button != null) {
            button.setOnClickListener(null);
            this.f13517P = null;
        }
        View view = this.f13518Q;
        if (view != null) {
            view.setOnClickListener(null);
            this.f13518Q = null;
        }
        this.f13514M.setOnClickListener(null);
        this.f13514M = null;
        for (View view2 : this.f13519R) {
            view2.setOnClickListener(null);
        }
        C5152i0.m20065b(this.f13542q);
        super.onDestroy();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (view.getId() != R.id.btnPaintUndo) {
            return false;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(R.string.app_name);
        builderM16382a.setMessage(R.string.confirm_reset_photo);
        builderM16382a.setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() { // from class: q3.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20776b.m15514X(dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: q3.g
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                FingerPaintActivity.m15515Y(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
        return false;
    }

    /* renamed from: v */
    public final void m15547v(int i9) {
        if (i9 == R.id.btn_drawtool_pen) {
            this.f13547v.setImageResource(R.drawable.ico_bottom_01_pen);
            this.f13528c.m15600v(1);
        } else if (i9 == R.id.btn_drawtool_marker) {
            this.f13547v.setImageResource(R.drawable.ico_bottom_02_marker);
            this.f13528c.m15600v(2);
        } else if (i9 == R.id.btn_drawtool_arrow) {
            this.f13547v.setImageResource(R.drawable.ico_bottom_03_arrow);
            this.f13528c.m15600v(3);
        } else if (i9 == R.id.btn_drawtool_frame) {
            this.f13547v.setImageResource(R.drawable.ico_bottom_04_frame);
            this.f13528c.m15600v(4);
        } else if (i9 == R.id.btn_drawtool_text) {
            this.f13547v.setImageResource(R.drawable.ico_bottom_05_text);
            this.f13528c.m15600v(5);
        }
        f13501c0 = i9;
    }

    /* renamed from: w */
    public final void m15548w() {
        if (this.f13528c.m15592n()) {
            DialogInterface.OnClickListener onClickListener = this.f13527Z;
            C4755b.m18880c(this, R.string.abandon, R.string.ask_abandon_change, R.string.abandon, R.string.cancel, onClickListener, onClickListener);
        } else {
            this.f13528c.m15595q();
            setResult(0);
            finish();
        }
    }

    /* renamed from: y */
    public final void m15549y(final boolean z8) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.f13542q = progressDialog;
        progressDialog.setMessage(getResources().getString(R.string.processing));
        this.f13542q.setIndeterminate(true);
        this.f13542q.setCancelable(false);
        this.f13542q.show();
        this.f13526Y = new Runnable() { // from class: q3.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f20773b.m15509O(z8);
            }
        };
        new Thread(this.f13526Y).start();
    }

    /* renamed from: z */
    public final void m15550z() {
        if (this.f13505D.getVisibility() == 4) {
            m15543k0(0);
            m15544l0(4);
            m15545m0(4);
        }
    }
}
