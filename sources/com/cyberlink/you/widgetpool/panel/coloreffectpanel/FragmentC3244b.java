package com.cyberlink.you.widgetpool.panel.coloreffectpanel;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.cyberlink.clgpuimage.Rotation;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.PresetEditViewActivity;
import com.cyberlink.you.effect.PreParsePresetSettingTask;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine;
import com.cyberlink.you.kernelctrl.gpuimage.C3097b;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView;
import com.cyberlink.you.widgetpool.clhorizontalgridview.HorizontalGridView;
import com.cyberlink.you.widgetpool.common.C3241a;
import com.cyberlink.you.widgetpool.panel.ICameraPanel$FlingDirection;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.C3243a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p056e4.C4754a;
import p155o4.InterfaceC5466a;
import p166p4.C6097a;

/* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b */
/* loaded from: classes.dex */
public class FragmentC3244b extends Fragment implements InterfaceC5466a {

    /* renamed from: H */
    public GLViewEngine.InterfaceC3075c f15153H;

    /* renamed from: b */
    public View f15158b;

    /* renamed from: c */
    public HorizontalGridView f15159c;

    /* renamed from: d */
    public C6097a f15160d;

    /* renamed from: e */
    public C3243a f15161e;

    /* renamed from: f */
    public C3097b f15162f;

    /* renamed from: g */
    public View f15163g;

    /* renamed from: h */
    public View f15164h;

    /* renamed from: i */
    public ExecutorService f15165i = Executors.newFixedThreadPool(1);

    /* renamed from: j */
    public boolean f15166j = false;

    /* renamed from: k */
    public final long f15167k = 3000;

    /* renamed from: l */
    public final long f15168l = 500;

    /* renamed from: m */
    public View f15169m = null;

    /* renamed from: n */
    public View f15170n = null;

    /* renamed from: o */
    public View f15171o = null;

    /* renamed from: p */
    public View f15172p = null;

    /* renamed from: q */
    public View f15173q = null;

    /* renamed from: r */
    public View f15174r = null;

    /* renamed from: s */
    public View f15175s = null;

    /* renamed from: t */
    public View f15176t = null;

    /* renamed from: u */
    public int f15177u = -1;

    /* renamed from: v */
    public final int f15178v = 0;

    /* renamed from: w */
    public final int f15179w = 1;

    /* renamed from: x */
    public final int f15180x = 2;

    /* renamed from: y */
    public final int f15181y = 3;

    /* renamed from: z */
    public final int f15182z = 10;

    /* renamed from: A */
    public final int f15146A = 11;

    /* renamed from: B */
    public final int f15147B = 22;

    /* renamed from: C */
    public final int f15148C = 23;

    /* renamed from: D */
    public final int f15149D = 33;

    /* renamed from: E */
    public final int f15150E = 34;

    /* renamed from: F */
    public int f15151F = 0;

    /* renamed from: G */
    public GestureDetector f15152G = null;

    /* renamed from: I */
    public AdapterView.InterfaceC3229e f15154I = new d();

    /* renamed from: J */
    public View.OnTouchListener f15155J = new e();

    /* renamed from: K */
    public View.OnClickListener f15156K = new View.OnClickListener() { // from class: p4.b
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) throws IOException {
            this.f20704b.m17337L(view);
        }
    };

    /* renamed from: L */
    public final Runnable f15157L = new Runnable() { // from class: p4.c
        @Override // java.lang.Runnable
        public final void run() {
            this.f20705b.m17362C();
        }
    };

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b$a */
    public class a implements GLViewEngine.InterfaceC3075c<Bitmap> {
        public a() {
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: b */
        public void mo15552b(Object obj, String str) {
            FragmentC3244b.this.m17381f("IGLViewEngineCallback onCancel. msg=" + str);
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo15551a(Object obj, Bitmap bitmap) throws IOException {
            boolean zM17360A;
            FragmentC3244b.this.m17381f("IGLViewEngineCallback onComplete. ");
            String strM16464M0 = CLUtility.m16464M0();
            boolean z8 = false;
            if (bitmap != null) {
                String doodleImagePath = FragmentC3244b.this.f15162f.getDoodleImagePath();
                if (doodleImagePath != null) {
                    zM17360A = FragmentC3244b.this.m17360A(bitmap, doodleImagePath, strM16464M0);
                } else {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(strM16464M0);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        zM17360A = true;
                    } catch (IOException e9) {
                        Log.d("CaptureColorEffectPanel", Log.getStackTraceString(e9));
                    }
                }
                z8 = zM17360A;
            } else {
                Log.d("CaptureColorEffectPanel", "Applied fail.");
            }
            Uri uriFromFile = Uri.fromFile(new File(strM16464M0));
            if (FragmentC3244b.this.getActivity() != null) {
                ((PresetEditViewActivity) FragmentC3244b.this.getActivity()).m9361W0(z8, strM16464M0, uriFromFile);
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b$b */
    public class b extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final /* synthetic */ ImageItem f15184a;

        /* renamed from: b */
        public final /* synthetic */ int f15185b;

        /* renamed from: c */
        public final /* synthetic */ int f15186c;

        public b(ImageItem imageItem, int i9, int i10) {
            this.f15184a = imageItem;
            this.f15185b = i9;
            this.f15186c = i10;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            ImageItem imageItem;
            Bitmap bitmapM17363D = (FragmentC3244b.this.getActivity() == null || (imageItem = this.f15184a) == null) ? null : FragmentC3244b.this.m17363D(imageItem.m16144q(), CLUtility.m16510Z1(this.f15184a.m16145r()));
            if (bitmapM17363D != null || FragmentC3244b.this.getActivity() == null) {
                return bitmapM17363D;
            }
            FragmentC3244b.this.getActivity().finish();
            cancel(true);
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            FragmentC3244b.this.m17385z(bitmap, new GLViewEngine.EffectParam(FragmentC3244b.this.f15161e.m17329b(this.f15185b, this.f15186c), 0.7d, bitmap, Rotation.NORMAL, false, false, FragmentC3244b.this.f15166j ? GLViewEngine.EffectParam.ExtraFunc.AutoToneCapture : GLViewEngine.EffectParam.ExtraFunc.None));
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b$c */
    public class c extends GestureDetector.SimpleOnGestureListener {
        public c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) throws IOException {
            FragmentC3244b.this.m17370O(Math.abs(f9) > Math.abs(f10) ? f9 < BitmapDescriptorFactory.HUE_RED ? ICameraPanel$FlingDirection.LEFT : ICameraPanel$FlingDirection.RIGHT : f10 < BitmapDescriptorFactory.HUE_RED ? ICameraPanel$FlingDirection.UP : ICameraPanel$FlingDirection.DOWN);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b$d */
    public class d implements AdapterView.InterfaceC3229e {
        public d() {
        }

        @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView.InterfaceC3229e
        /* renamed from: a */
        public void mo17210a(AdapterView<?> adapterView, View view, int i9, long j9) throws IOException {
            if (i9 == FragmentC3244b.this.f15160d.m23396b()) {
                return;
            }
            FragmentC3244b.this.m17384y();
            ((C3241a) view).setCheckd(true);
            FragmentC3244b.this.f15160d.m23398d(i9);
            if (FragmentC3244b.this.f15163g != null) {
                if (i9 != 0 || FragmentC3244b.this.f15177u == 0) {
                    FragmentC3244b.this.f15163g.setVisibility(0);
                } else {
                    FragmentC3244b.this.f15163g.setVisibility(4);
                }
            }
            FragmentC3244b.this.f15159c.m17116F0(FragmentC3244b.this.m17373R(i9) + i9);
            FragmentC3244b.this.m17382w(i9);
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b$e */
    public class e implements View.OnTouchListener {
        public e() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0014  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                FragmentC3244b.this.m17381f("Compare Button Down");
                FragmentC3244b.this.f15162f.m16103s(DevelopSetting.EffectMode.ALL, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                FragmentC3244b.this.m17376U(false);
            } else if (actionMasked == 1 || actionMasked == 3) {
                FragmentC3244b.this.m17381f("Compare Button Up");
                FragmentC3244b.this.f15162f.m16102r(DevelopSetting.EffectMode.ALL, 0.7d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                FragmentC3244b.this.m17376U(true);
            } else if (actionMasked != 5) {
                if (actionMasked == 6) {
                }
            }
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.b$f */
    public class f implements AbsListView.InterfaceC3218h {
        public f() {
        }

        @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView.InterfaceC3218h
        /* renamed from: a */
        public void mo17170a(AbsListView absListView, int i9) {
        }

        @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView.InterfaceC3218h
        /* renamed from: b */
        public void mo17171b(AbsListView absListView, int i9, int i10, int i11) {
            if (i9 == 10 && FragmentC3244b.this.f15177u != 0) {
                FragmentC3244b.this.m17378W(0);
                return;
            }
            if ((i9 == 11 || i9 == 22) && FragmentC3244b.this.f15177u != 1) {
                FragmentC3244b.this.m17378W(1);
                return;
            }
            if ((i9 == 23 || i9 == 33) && FragmentC3244b.this.f15177u != 2) {
                FragmentC3244b.this.m17378W(2);
            } else {
                if (i9 != 34 || FragmentC3244b.this.f15177u == 3) {
                    return;
                }
                FragmentC3244b.this.m17378W(3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ boolean m17335J(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        m17361B();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K */
    public /* synthetic */ void m17336K() throws IOException {
        m17383x(0, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m17337L(View view) throws IOException {
        if (view == null) {
        }
        switch (view.getId()) {
            case R.id.CameraArtisticBtn /* 2131296349 */:
                m17383x(3, false);
                break;
            case R.id.CameraFoodBtn /* 2131296351 */:
                m17383x(2, false);
                break;
            case R.id.CameraSceneryBtn /* 2131296353 */:
                m17383x(1, false);
                break;
            case R.id.CameraSelfieBtn /* 2131296355 */:
                m17383x(0, false);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ boolean m17338M(View view, MotionEvent motionEvent) {
        GestureDetector gestureDetector;
        if (!((PresetEditViewActivity) getActivity()).m9360V0() || (gestureDetector = this.f15152G) == null) {
            return true;
        }
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    /* renamed from: A */
    public final boolean m17360A(Bitmap bitmap, String str, String str2) throws IOException {
        boolean z8 = false;
        try {
            if (bitmap != null) {
                Canvas canvas = new Canvas(bitmap);
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
                canvas.drawBitmap(bitmapDecodeFile, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
                bitmapDecodeFile.recycle();
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                z8 = true;
            } else {
                Log.d("CaptureColorEffectPanel", "drawDoodle fail by srcBitmap is null.");
            }
        } catch (Exception e9) {
            Log.d("CaptureColorEffectPanel", Log.getStackTraceString(e9));
        } catch (OutOfMemoryError e10) {
            e10.printStackTrace();
        }
        return z8;
    }

    /* renamed from: B */
    public final void m17361B() {
        this.f15158b.removeCallbacks(this.f15157L);
        this.f15158b.animate().cancel();
        if (this.f15158b.getAlpha() != 1.0f) {
            this.f15158b.animate().alpha(1.0f).setDuration(500L).start();
        }
        m17372Q();
    }

    /* renamed from: C */
    public final void m17362C() {
        this.f15158b.animate().cancel();
        if (this.f15158b.getAlpha() != 0.1f) {
            this.f15158b.animate().alpha(0.1f).setDuration(500L).start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0075  */
    /* renamed from: D */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bitmap m17363D(String str, Uri uri) throws IOException {
        int i9;
        Bitmap bitmapM16493U;
        int i10;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        CLUtility.m16509Z0(str, uri, options);
        int iM18873c = 0;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inMutable = true;
        options.inSampleSize = CLUtility.m16555l(options, 1280, 1280);
        try {
            bitmapM16493U = CLUtility.m16493U(str, uri, options);
            try {
                i9 = options.outWidth;
                try {
                    i10 = options.outHeight;
                } catch (OutOfMemoryError e9) {
                    e = e9;
                    if (e.getMessage() != null) {
                        Log.e("CaptureColorEffectPanel", e.getMessage());
                    }
                    i10 = 0;
                    int i11 = i9;
                    if (bitmapM16493U != null) {
                    }
                }
            } catch (OutOfMemoryError e10) {
                e = e10;
                i9 = 0;
            }
        } catch (OutOfMemoryError e11) {
            e = e11;
            i9 = 0;
            bitmapM16493U = null;
        }
        int i112 = i9;
        if (bitmapM16493U != null) {
            Log.e("CaptureColorEffectPanel", "failed to decode imagePath=" + str + " imageUri= " + uri + " inSampleSize= " + String.valueOf(options.inSampleSize));
            return null;
        }
        Matrix matrix = new Matrix();
        float fM16436F0 = CLUtility.m16436F0(i112, i10, 1280);
        matrix.postScale(fM16436F0, fM16436F0);
        try {
            iM18873c = C4754a.m18873c(str, uri);
        } catch (IOException e12) {
            Log.e("CaptureColorEffectPanel", "cannot get exif attribute", e12);
        }
        matrix.postRotate(iM18873c);
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapM16493U, 0, 0, i112, i10, matrix, true);
            if (!bitmapCreateBitmap.equals(bitmapM16493U) && !bitmapM16493U.isRecycled()) {
                bitmapM16493U.recycle();
                System.gc();
            }
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    /* renamed from: E */
    public DevelopSetting m17364E() {
        C6097a c6097a = this.f15160d;
        if (c6097a == null || c6097a.m23396b() == 0 || this.f15161e == null) {
            return null;
        }
        int iM17317d = ColorEffectUtility.m17317d(this.f15160d.m23396b());
        return this.f15161e.m17329b(iM17317d, ColorEffectUtility.m17316c(iM17317d, this.f15160d.m23396b()));
    }

    /* renamed from: F */
    public final void m17365F() {
        String[] strArr = new String[this.f15161e.m17330c()];
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(strArr));
        this.f15160d = new C6097a(this.f15159c.getContext(), arrayList, this.f15161e);
    }

    /* renamed from: G */
    public final void m17366G() {
        this.f15159c.setOnItemClickListener(this.f15154I);
        this.f15169m.setOnClickListener(this.f15156K);
        this.f15170n.setOnClickListener(this.f15156K);
        this.f15171o.setOnClickListener(this.f15156K);
        this.f15172p.setOnClickListener(this.f15156K);
    }

    /* renamed from: H */
    public final void m17367H() {
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: p4.f
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f20708b.m17335J(view, motionEvent);
            }
        };
        this.f15159c.setOnTouchListener(onTouchListener);
        this.f15169m.setOnTouchListener(onTouchListener);
        this.f15170n.setOnTouchListener(onTouchListener);
        this.f15171o.setOnTouchListener(onTouchListener);
        this.f15172p.setOnTouchListener(onTouchListener);
        this.f15173q.setOnTouchListener(onTouchListener);
        this.f15174r.setOnTouchListener(onTouchListener);
        this.f15175s.setOnTouchListener(onTouchListener);
        this.f15176t.setOnTouchListener(onTouchListener);
    }

    /* renamed from: I */
    public final void m17368I() {
        this.f15159c = (HorizontalGridView) this.f15158b.findViewById(R.id.cameraEffectGridArea);
        this.f15169m = this.f15158b.findViewById(R.id.CameraSelfieBtn);
        this.f15170n = this.f15158b.findViewById(R.id.CameraSceneryBtn);
        this.f15171o = this.f15158b.findViewById(R.id.CameraFoodBtn);
        this.f15172p = this.f15158b.findViewById(R.id.CameraArtisticBtn);
        this.f15173q = this.f15158b.findViewById(R.id.CameraSelfieDot);
        this.f15174r = this.f15158b.findViewById(R.id.CameraSceneryDot);
        this.f15175s = this.f15158b.findViewById(R.id.CameraFoodDot);
        this.f15176t = this.f15158b.findViewById(R.id.CameraArtisticDot);
        this.f15161e = new C3243a();
        PreParsePresetSettingTask.m15311i().m15312j(PreParsePresetSettingTask.EffectApplicationMode.Capture, new PreParsePresetSettingTask.InterfaceC3004c() { // from class: p4.e
            @Override // com.cyberlink.you.effect.PreParsePresetSettingTask.InterfaceC3004c
            public final void onComplete() throws IOException {
                this.f20707a.m17336K();
            }
        });
        this.f15152G = new GestureDetector(getActivity(), new c());
    }

    /* renamed from: N */
    public void m17369N() {
        boolean zM17360A;
        String strM16144q;
        C6097a c6097a = this.f15160d;
        if (c6097a == null) {
            return;
        }
        ImageItem imageItem = PresetEditViewActivity.f8503y;
        int iM23396b = c6097a.m23396b();
        if (iM23396b != 0 || this.f15177u == 0) {
            int iM17317d = ColorEffectUtility.m17317d(iM23396b);
            int iM17316c = ColorEffectUtility.m17316c(iM17317d, iM23396b);
            C3243a.a aVarM17331d = this.f15161e.m17331d(iM17317d, iM17316c);
            Log.d("CaptureColorEffectPanel", "effectDefName=" + aVarM17331d.f15143a + ", effectGUID=" + aVarM17331d.f15144b);
            new b(imageItem, iM17317d, iM17316c).executeOnExecutor(this.f15165i, new Void[0]);
            return;
        }
        if (getActivity() != null) {
            if (imageItem != null) {
                strM16144q = imageItem.m16144q();
                Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem.m16145r());
                String doodleImagePath = this.f15162f.getDoodleImagePath();
                if (doodleImagePath != null) {
                    String strM16464M0 = CLUtility.m16464M0();
                    zM17360A = m17360A(m17363D(strM16144q, uriM16510Z1), doodleImagePath, strM16464M0);
                    strM16144q = strM16464M0;
                } else {
                    zM17360A = true;
                }
            } else {
                zM17360A = false;
                strM16144q = null;
            }
            ((PresetEditViewActivity) getActivity()).m9361W0(zM17360A, strM16144q, strM16144q != null ? Uri.fromFile(new File(strM16144q)) : null);
        }
    }

    /* renamed from: O */
    public void m17370O(ICameraPanel$FlingDirection iCameraPanel$FlingDirection) throws IOException {
        C6097a c6097a = this.f15160d;
        if (c6097a == null) {
            return;
        }
        int iM23396b = c6097a.m23396b();
        if (iCameraPanel$FlingDirection == ICameraPanel$FlingDirection.LEFT) {
            iM23396b++;
        } else if (iCameraPanel$FlingDirection == ICameraPanel$FlingDirection.RIGHT) {
            iM23396b--;
        }
        int iMin = Math.min(Math.max(iM23396b, 0), this.f15160d.getCount() - 1);
        m17384y();
        int firstVisiblePosition = this.f15159c.getFirstVisiblePosition();
        if (iMin >= firstVisiblePosition && iMin < this.f15159c.getChildCount() + firstVisiblePosition) {
            ((C3241a) this.f15159c.getChildAt(iMin - firstVisiblePosition)).setCheckd(true);
        }
        this.f15160d.m23398d(iMin);
        View view = this.f15163g;
        if (view != null) {
            if (iMin == 0) {
                view.setVisibility(4);
            } else {
                view.setVisibility(0);
            }
        }
        this.f15159c.m17116F0(m17373R(iMin) + iMin);
        PresetEditViewActivity.m9353Q0(this.f15160d.m23397c(ColorEffectUtility.m17317d(iMin), iMin));
        m17361B();
        m17382w(iMin);
    }

    /* renamed from: P */
    public final void m17371P() {
        this.f15169m.setSelected(false);
        this.f15173q.setVisibility(4);
        this.f15170n.setSelected(false);
        this.f15174r.setVisibility(4);
        this.f15171o.setSelected(false);
        this.f15175s.setVisibility(4);
        this.f15172p.setSelected(false);
        this.f15176t.setVisibility(4);
    }

    /* renamed from: Q */
    public final void m17372Q() {
        this.f15158b.removeCallbacks(this.f15157L);
        this.f15158b.postDelayed(this.f15157L, 3000L);
    }

    /* renamed from: R */
    public final int m17373R(int i9) {
        int i10 = this.f15151F;
        if (i10 < i9) {
            return 2;
        }
        return i10 > i9 ? -2 : 0;
    }

    /* renamed from: S */
    public void m17374S(C3097b c3097b) {
        m17381f("setCurrentViewer");
        C3097b c3097b2 = this.f15162f;
        if (c3097b2 != null) {
            c3097b2.setOnTouchListener(null);
        }
        this.f15162f = c3097b;
        if (c3097b != null) {
            c3097b.setOnTouchListener(new View.OnTouchListener() { // from class: p4.d
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.f20706b.m17338M(view, motionEvent);
                }
            });
        }
    }

    /* renamed from: T */
    public void m17375T(String str) {
        this.f15162f.setDoodleImage(str);
    }

    /* renamed from: U */
    public final void m17376U(boolean z8) {
        this.f15160d.f20703e = z8;
        if (z8) {
            return;
        }
        for (int i9 = 0; i9 < this.f15159c.getChildCount(); i9++) {
            C3241a c3241a = (C3241a) this.f15159c.getChildAt(i9);
            if (c3241a.isPressed()) {
                c3241a.setPressed(false);
            }
        }
    }

    /* renamed from: V */
    public void m17377V(View view) {
        this.f15163g = view;
        View viewFindViewById = view.findViewById(R.id.presetCompare);
        this.f15164h = viewFindViewById;
        viewFindViewById.setOnTouchListener(this.f15155J);
    }

    /* renamed from: W */
    public final void m17378W(int i9) {
        m17371P();
        this.f15177u = i9;
        if (i9 == 1) {
            this.f15170n.setSelected(true);
            this.f15174r.setVisibility(0);
        } else if (i9 == 2) {
            this.f15171o.setSelected(true);
            this.f15175s.setVisibility(0);
        } else if (i9 != 3) {
            this.f15169m.setSelected(true);
            this.f15173q.setVisibility(0);
        } else {
            this.f15172p.setSelected(true);
            this.f15176t.setVisibility(0);
        }
    }

    /* renamed from: X */
    public final void m17379X() {
        HorizontalGridView horizontalGridView = this.f15159c;
        if (horizontalGridView != null) {
            horizontalGridView.setOnItemClickListener(null);
        }
        View view = this.f15169m;
        if (view != null) {
            view.setOnClickListener(null);
        }
        View view2 = this.f15170n;
        if (view2 != null) {
            view2.setOnClickListener(null);
        }
        View view3 = this.f15171o;
        if (view3 != null) {
            view3.setOnClickListener(null);
        }
        View view4 = this.f15172p;
        if (view4 != null) {
            view4.setOnClickListener(null);
        }
    }

    /* renamed from: Y */
    public final void m17380Y() {
        C3097b c3097b = this.f15162f;
        if (c3097b != null) {
            c3097b.setOnTouchListener(null);
        }
        this.f15165i.shutdown();
        C6097a c6097a = this.f15160d;
        if (c6097a != null) {
            c6097a.m23395a();
        }
    }

    /* renamed from: f */
    public final void m17381f(String str) {
        Log.d("CaptureColorEffectPanel", str);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m17368I();
        m17366G();
        m17367H();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.panel_color_effect, viewGroup, false);
        this.f15158b = viewInflate;
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        m17380Y();
        m17379X();
        this.f15158b.removeCallbacks(this.f15157L);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z8) {
        if (z8) {
            return;
        }
        m17361B();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        m17372Q();
    }

    /* renamed from: w */
    public final void m17382w(int i9) throws IOException {
        ImageItem imageItem;
        this.f15151F = i9;
        int iM17317d = ColorEffectUtility.m17317d(i9);
        DevelopSetting developSettingM17329b = this.f15161e.m17329b(iM17317d, ColorEffectUtility.m17316c(iM17317d, i9));
        if (developSettingM17329b == null) {
            return;
        }
        developSettingM17329b.m15965e(Boolean.TRUE);
        C3097b c3097b = this.f15162f;
        if (c3097b == null || (imageItem = PresetEditViewActivity.f8503y) == null) {
            return;
        }
        c3097b.m16101p(imageItem.m16144q(), PresetEditViewActivity.f8503y.m16145r(), developSettingM17329b, developSettingM17329b, developSettingM17329b, (this.f15177u == 0 || i9 != 0) ? 0.7d : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, GLViewEngine.EffectParam.ExtraFunc.None);
    }

    /* renamed from: x */
    public final void m17383x(int i9, boolean z8) throws IOException {
        int iM17326m;
        View view;
        Log.d("CaptureColorEffectPanel", "[ColorEffectPanel] changeTab  = " + i9);
        if (i9 != this.f15177u) {
            this.f15177u = i9;
            if (this.f15160d == null) {
                m17365F();
                this.f15159c.setAdapter((ListAdapter) this.f15160d);
            }
            m17371P();
            int i10 = this.f15177u;
            int i11 = 0;
            if (i10 == 0) {
                iM17326m = !z8 ? ColorEffectUtility.m17326m() : 0;
                this.f15169m.setSelected(true);
                this.f15173q.setVisibility(0);
            } else if (i10 == 1) {
                iM17326m = ColorEffectUtility.m17327n();
                this.f15170n.setSelected(true);
                this.f15174r.setVisibility(0);
            } else if (i10 == 2) {
                iM17326m = ColorEffectUtility.m17323j();
                this.f15171o.setSelected(true);
                this.f15175s.setVisibility(0);
            } else {
                if (i10 != 3) {
                    this.f15169m.setSelected(true);
                    this.f15173q.setVisibility(0);
                    this.f15160d.m23398d(i11);
                    view = this.f15163g;
                    if (view != null && this.f15177u != 0) {
                        view.setVisibility(4);
                    }
                    this.f15159c.setOnScrollListener(new f());
                    this.f15159c.m17116F0(m17373R(i11) + i11);
                    m17382w(i11);
                }
                iM17326m = ColorEffectUtility.m17319f();
                this.f15172p.setSelected(true);
                this.f15176t.setVisibility(0);
            }
            i11 = iM17326m;
            this.f15160d.m23398d(i11);
            view = this.f15163g;
            if (view != null) {
                view.setVisibility(4);
            }
            this.f15159c.setOnScrollListener(new f());
            this.f15159c.m17116F0(m17373R(i11) + i11);
            m17382w(i11);
        }
    }

    /* renamed from: y */
    public final void m17384y() {
        for (int i9 = 0; i9 < this.f15159c.getChildCount(); i9++) {
            ((C3241a) this.f15159c.getChildAt(i9)).setCheckd(false);
        }
        this.f15160d.m23398d(-1);
        View view = this.f15163g;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* renamed from: z */
    public final void m17385z(Bitmap bitmap, GLViewEngine.EffectParam effectParam) {
        this.f15153H = new a();
        GLViewEngine.m15970g().m15973f(bitmap, effectParam, null, null, this.f15153H, null);
    }
}
