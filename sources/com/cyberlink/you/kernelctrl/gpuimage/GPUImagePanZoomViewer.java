package com.cyberlink.you.kernelctrl.gpuimage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine;
import com.cyberlink.you.kernelctrl.gpuimage.C3097b;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.C3322C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import p116k4.C5177q1;
import p218v2.C6478z;
import p219v3.InterfaceC6479a;
import p255z3.C6823a;
import p255z3.C6824b;

/* loaded from: classes.dex */
public class GPUImagePanZoomViewer extends C3097b {

    /* renamed from: D */
    public static final UUID f14057D = UUID.randomUUID();

    /* renamed from: A */
    public Boolean f14058A;

    /* renamed from: B */
    public Boolean f14059B;

    /* renamed from: C */
    public Timer f14060C;

    /* renamed from: l */
    public ScaleGestureDetector f14061l;

    /* renamed from: m */
    public ScaleGestureDetectorOnScaleGestureListenerC3094j f14062m;

    /* renamed from: n */
    public GestureDetector f14063n;

    /* renamed from: o */
    public C3093i f14064o;

    /* renamed from: p */
    public Matrix f14065p;

    /* renamed from: q */
    public ViewerMode f14066q;

    /* renamed from: r */
    public ValueAnimator f14067r;

    /* renamed from: s */
    public ValueAnimator f14068s;

    /* renamed from: t */
    public boolean f14069t;

    /* renamed from: u */
    public boolean f14070u;

    /* renamed from: v */
    public boolean f14071v;

    /* renamed from: w */
    public HashMap<UUID, InterfaceC6479a> f14072w;

    /* renamed from: x */
    public UUID f14073x;

    /* renamed from: y */
    public boolean f14074y;

    /* renamed from: z */
    public final long f14075z;

    public enum ScaleMode {
        doubleTap,
        centerFocus
    }

    public enum ViewerMode {
        unknown,
        imageViewing,
        imageBouncing,
        imageFling,
        imageDoubleTaping
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$a */
    public class C3085a implements GLViewEngine.InterfaceC3075c<Void> {
        public C3085a() {
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: b */
        public void mo15552b(Object obj, String str) {
            Log.d("GPUImagePanZoomViewer", "updateGPUImageView onCancel");
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo15551a(Object obj, Void r22) {
            Log.d("GPUImagePanZoomViewer", "updateGPUImageView onComplete");
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$b */
    public class C3086b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a */
        public float f14086a = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: b */
        public float f14087b = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: c */
        public final /* synthetic */ float f14088c;

        /* renamed from: d */
        public final /* synthetic */ float f14089d;

        /* renamed from: e */
        public final /* synthetic */ float f14090e;

        /* renamed from: f */
        public final /* synthetic */ float f14091f;

        /* renamed from: g */
        public final /* synthetic */ long f14092g;

        /* renamed from: h */
        public final /* synthetic */ long f14093h;

        public C3086b(float f9, float f10, float f11, float f12, long j9, long j10) {
            this.f14088c = f9;
            this.f14089d = f10;
            this.f14090e = f11;
            this.f14091f = f12;
            this.f14092g = j9;
            this.f14093h = j10;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            long currentPlayTime = valueAnimator.getCurrentPlayTime();
            float f9 = currentPlayTime;
            float f10 = ((this.f14088c / 1000.0f) * f9) + (((this.f14089d * f9) * f9) / 2.0f);
            float f11 = ((this.f14090e / 1000.0f) * f9) + (((this.f14091f * f9) * f9) / 2.0f);
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.imageFling;
            GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
            PointF pointF = new PointF(gPUImagePanZoomViewer.f14158c / 2.0f, gPUImagePanZoomViewer.f14159d / 2.0f);
            PointF pointF2 = new PointF(f10 - this.f14086a, f11 - this.f14087b);
            long j9 = this.f14092g;
            float f12 = BitmapDescriptorFactory.HUE_RED;
            pointF2.x = currentPlayTime < j9 ? pointF2.x : 0.0f;
            if (currentPlayTime < this.f14093h) {
                f12 = pointF2.y;
            }
            pointF2.y = f12;
            GPUImagePanZoomViewer.this.m16071n0(pointF, pointF2, 1.0f);
            this.f14086a = f10;
            this.f14087b = f11;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$c */
    public class C3087c implements Animator.AnimatorListener {
        public C3087c() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.unknown;
            GPUImagePanZoomViewer.this.f14067r = null;
            GPUImagePanZoomViewer.this.m16094c("Fling cancel");
            GPUImagePanZoomViewer.this.f14074y = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.unknown;
            GPUImagePanZoomViewer.this.m16047S();
            GPUImagePanZoomViewer.this.f14067r = null;
            GPUImagePanZoomViewer.this.m16094c("Fling end");
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$d */
    public class C3088d implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a */
        public float f14096a;

        /* renamed from: b */
        public float f14097b;

        /* renamed from: c */
        public final /* synthetic */ float f14098c;

        /* renamed from: d */
        public final /* synthetic */ float f14099d;

        /* renamed from: e */
        public final /* synthetic */ float f14100e;

        /* renamed from: f */
        public final /* synthetic */ float f14101f;

        public C3088d(float f9, float f10, float f11, float f12) {
            this.f14098c = f9;
            this.f14099d = f10;
            this.f14100e = f11;
            this.f14101f = f12;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.imageBouncing;
            float animatedFraction = valueAnimator.getAnimatedFraction();
            float f9 = this.f14098c * animatedFraction;
            float f10 = this.f14099d * animatedFraction;
            GPUImagePanZoomViewer.this.m16071n0(new PointF(this.f14100e, this.f14101f), new PointF(f9 - this.f14096a, f10 - this.f14097b), 1.0f);
            this.f14096a = f9;
            this.f14097b = f10;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$e */
    public class C3089e implements Animator.AnimatorListener {

        /* renamed from: a */
        public boolean f14103a = false;

        /* renamed from: b */
        public final /* synthetic */ float f14104b;

        /* renamed from: c */
        public final /* synthetic */ float f14105c;

        public C3089e(float f9, float f10) {
            this.f14104b = f9;
            this.f14105c = f10;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.imageBouncing;
            GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
            float fM16051W = gPUImagePanZoomViewer.m16051W(gPUImagePanZoomViewer.f14065p);
            GPUImagePanZoomViewer gPUImagePanZoomViewer2 = GPUImagePanZoomViewer.this;
            float fM16052X = gPUImagePanZoomViewer2.m16052X(gPUImagePanZoomViewer2.f14065p);
            GPUImagePanZoomViewer.this.m16071n0(new PointF(this.f14104b, this.f14105c), new PointF(fM16051W, fM16052X), 1.0f);
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.unknown;
            GPUImagePanZoomViewer.this.m16094c("Bouncing cancel");
            GPUImagePanZoomViewer.this.f14068s = null;
            GPUImagePanZoomViewer.this.f14069t = false;
            this.f14103a = true;
            GPUImagePanZoomViewer.this.f14074y = false;
            GPUImagePanZoomViewer.this.m16070m0();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f14103a) {
                return;
            }
            GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
            float fM16051W = gPUImagePanZoomViewer.m16051W(gPUImagePanZoomViewer.f14065p);
            GPUImagePanZoomViewer gPUImagePanZoomViewer2 = GPUImagePanZoomViewer.this;
            float fM16052X = gPUImagePanZoomViewer2.m16052X(gPUImagePanZoomViewer2.f14065p);
            GPUImagePanZoomViewer.this.m16071n0(new PointF(this.f14104b, this.f14105c), new PointF(fM16051W, fM16052X), 1.0f);
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.unknown;
            GPUImagePanZoomViewer.this.f14074y = false;
            GPUImagePanZoomViewer.this.m16094c("Bouncing end");
            GPUImagePanZoomViewer.this.f14068s = null;
            GPUImagePanZoomViewer.this.f14069t = false;
            GPUImagePanZoomViewer.this.m16070m0();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$f */
    public class C3090f extends TimerTask {

        /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$f$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (GPUImagePanZoomViewer.this.f14072w == null || GPUImagePanZoomViewer.this.f14073x == null || !GPUImagePanZoomViewer.this.f14072w.containsKey(GPUImagePanZoomViewer.this.f14073x)) {
                    return;
                }
                GPUImagePanZoomViewer.this.f14058A = Boolean.valueOf(!r0.f14059B.booleanValue());
                InterfaceC6479a interfaceC6479a = (InterfaceC6479a) GPUImagePanZoomViewer.this.f14072w.get(GPUImagePanZoomViewer.this.f14073x);
                if (interfaceC6479a == null || GPUImagePanZoomViewer.this.f14074y) {
                    return;
                }
                interfaceC6479a.m24817b(Boolean.TRUE);
            }
        }

        public C3090f() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            new Handler(Looper.getMainLooper()).post(new a());
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$g */
    public class C3091g implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a */
        public final /* synthetic */ ScaleMode f14109a;

        /* renamed from: b */
        public final /* synthetic */ float f14110b;

        /* renamed from: c */
        public final /* synthetic */ C6823a f14111c;

        /* renamed from: d */
        public final /* synthetic */ float f14112d;

        /* renamed from: e */
        public final /* synthetic */ float f14113e;

        /* renamed from: f */
        public final /* synthetic */ float f14114f;

        /* renamed from: g */
        public final /* synthetic */ PointF f14115g;

        /* renamed from: h */
        public final /* synthetic */ float f14116h;

        /* renamed from: i */
        public final /* synthetic */ float f14117i;

        /* renamed from: j */
        public final /* synthetic */ PointF f14118j;

        /* renamed from: k */
        public final /* synthetic */ float f14119k;

        /* renamed from: l */
        public final /* synthetic */ float f14120l;

        /* renamed from: m */
        public final /* synthetic */ float f14121m;

        public C3091g(ScaleMode scaleMode, float f9, C6823a c6823a, float f10, float f11, float f12, PointF pointF, float f13, float f14, PointF pointF2, float f15, float f16, float f17) {
            this.f14109a = scaleMode;
            this.f14110b = f9;
            this.f14111c = c6823a;
            this.f14112d = f10;
            this.f14113e = f11;
            this.f14114f = f12;
            this.f14115g = pointF;
            this.f14116h = f13;
            this.f14117i = f14;
            this.f14118j = pointF2;
            this.f14119k = f15;
            this.f14120l = f16;
            this.f14121m = f17;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.imageDoubleTaping;
            if (this.f14109a == ScaleMode.doubleTap && this.f14110b == 1.0f) {
                GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
                C6823a c6823a = this.f14111c;
                C6824b c6824bM16062g0 = gPUImagePanZoomViewer.m16062g0(c6823a.f22659a, c6823a.f22660b, false);
                float f9 = this.f14110b;
                float f10 = this.f14112d;
                float f11 = (((f9 - f10) * animatedFraction) + f10) / GPUImagePanZoomViewer.this.f14161f.f14195n;
                PointF pointF = new PointF(this.f14113e - c6824bM16062g0.f22661a, this.f14114f - c6824bM16062g0.f22662b);
                C3095k c3095kM16056b0 = GPUImagePanZoomViewer.this.m16056b0(pointF, this.f14115g, f11);
                GPUImagePanZoomViewer.this.m16071n0(pointF, new PointF(GPUImagePanZoomViewer.this.m16051W(c3095kM16056b0.f14136a), GPUImagePanZoomViewer.this.m16052X(c3095kM16056b0.f14136a)), f11);
                return;
            }
            float f12 = this.f14116h * animatedFraction;
            float f13 = this.f14117i * animatedFraction;
            float[] fArr = new float[9];
            GPUImagePanZoomViewer.this.f14065p.getValues(fArr);
            float f14 = fArr[0];
            C3097b.d dVar = GPUImagePanZoomViewer.this.f14161f;
            float f15 = dVar.f14187f * f14;
            float f16 = (fArr[2] * f14) + (f15 / 2.0f);
            float f17 = (fArr[5] * f14) + ((dVar.f14188g * f14) / 2.0f);
            PointF pointF2 = this.f14118j;
            PointF pointF3 = new PointF(pointF2.x - f16, (pointF2.y + this.f14119k) - f17);
            PointF pointF4 = new PointF(f12 - (f16 - this.f14120l), f13 - (f17 - this.f14121m));
            float f18 = this.f14110b;
            float f19 = this.f14112d;
            float f20 = ((f18 - f19) * animatedFraction) + f19;
            GPUImagePanZoomViewer gPUImagePanZoomViewer2 = GPUImagePanZoomViewer.this;
            float f21 = f20 / gPUImagePanZoomViewer2.f14161f.f14195n;
            if (f18 < 1.0f) {
                gPUImagePanZoomViewer2.m16075q0(pointF3, pointF4, f21);
            } else {
                gPUImagePanZoomViewer2.m16071n0(pointF3, pointF4, f21);
            }
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$h */
    public class C3092h implements Animator.AnimatorListener {

        /* renamed from: a */
        public boolean f14123a = false;

        /* renamed from: b */
        public final /* synthetic */ float f14124b;

        /* renamed from: c */
        public final /* synthetic */ float f14125c;

        /* renamed from: d */
        public final /* synthetic */ C5177q1.b f14126d;

        public C3092h(float f9, float f10, C5177q1.b bVar) {
            this.f14124b = f9;
            this.f14125c = f10;
            this.f14126d = bVar;
        }

        /* renamed from: a */
        public final void m16080a() {
            if (this.f14124b < 1.0f) {
                float[] fArr = new float[9];
                GPUImagePanZoomViewer.this.f14065p.getValues(fArr);
                float f9 = fArr[0];
                C3097b.d dVar = GPUImagePanZoomViewer.this.f14161f;
                float f10 = dVar.f14187f * f9;
                float f11 = (fArr[2] * f9) + (f10 / 2.0f);
                float f12 = (fArr[5] * f9) + ((dVar.f14188g * f9) / 2.0f);
                if (f11 < 1.0f && f11 > -1.0f) {
                    f11 = 0.0f;
                }
                if (f12 < 1.0f && f12 > -1.0f) {
                    f12 = 0.0f;
                }
                PointF pointF = new PointF(BitmapDescriptorFactory.HUE_RED, this.f14125c);
                PointF pointF2 = new PointF(BitmapDescriptorFactory.HUE_RED - f11, this.f14125c - f12);
                float f13 = this.f14124b;
                GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
                float f14 = f13 / gPUImagePanZoomViewer.f14161f.f14195n;
                if (f14 > BitmapDescriptorFactory.HUE_RED) {
                    f14 = 1.0f;
                }
                if (pointF2.x != BitmapDescriptorFactory.HUE_RED || pointF2.y != BitmapDescriptorFactory.HUE_RED || f14 != 1.0f) {
                    gPUImagePanZoomViewer.m16075q0(pointF, pointF2, f14);
                }
            }
            if (this.f14124b == 1.0f) {
                GPUImagePanZoomViewer.this.m16075q0(new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED), new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED), this.f14124b);
            }
            GPUImagePanZoomViewer.this.f14066q = ViewerMode.unknown;
            GPUImagePanZoomViewer.this.m16048T();
            GPUImagePanZoomViewer.this.f14069t = false;
            GPUImagePanZoomViewer.this.f14074y = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            m16080a();
            GPUImagePanZoomViewer.this.m16094c("double tap valueAnimator cancel");
            this.f14123a = true;
            C5177q1.b bVar = this.f14126d;
            if (bVar != null) {
                bVar.onAnimationCancel(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f14123a) {
                return;
            }
            m16080a();
            GPUImagePanZoomViewer.this.m16094c("double tap valueAnimator end");
            C5177q1.b bVar = this.f14126d;
            if (bVar != null) {
                bVar.onAnimationEnd(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            C5177q1.b bVar = this.f14126d;
            if (bVar != null) {
                bVar.onAnimationRepeat(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C5177q1.b bVar = this.f14126d;
            if (bVar != null) {
                bVar.onAnimationStart(animator);
            }
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$i */
    public class C3093i extends GestureDetector.SimpleOnGestureListener {

        /* renamed from: b */
        public float f14128b = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: c */
        public float f14129c = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: d */
        public int f14130d = 0;

        /* renamed from: e */
        public float f14131e = BitmapDescriptorFactory.HUE_RED;

        public C3093i() {
        }

        /* renamed from: a */
        public void m16081a(MotionEvent motionEvent) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            float f9 = x8 - this.f14128b;
            float f10 = y8 - this.f14129c;
            GPUImagePanZoomViewer.this.m16094c("onActionUp focus x: " + x8 + " focux y: " + y8 + " translate x: " + f9 + " translate y: " + f10 + " scale: 1.0");
            GPUImagePanZoomViewer.this.m16069l0(new PointF((((float) GPUImagePanZoomViewer.this.getWidth()) / 2.0f) - x8, (((float) GPUImagePanZoomViewer.this.getHeight()) / 2.0f) - y8), new PointF(f9, f10), 1.0f);
            this.f14128b = BitmapDescriptorFactory.HUE_RED;
            this.f14129c = BitmapDescriptorFactory.HUE_RED;
        }

        /* renamed from: b */
        public void m16082b(MotionEvent motionEvent) {
            if (motionEvent.getPointerCount() == 2) {
                int actionIndex = motionEvent.getActionIndex();
                for (int i9 = 0; i9 < motionEvent.getPointerCount(); i9++) {
                    if (i9 != actionIndex) {
                        float x8 = motionEvent.getX(i9);
                        float y8 = motionEvent.getY(i9);
                        this.f14128b = x8;
                        this.f14129c = y8;
                        GPUImagePanZoomViewer.this.m16094c("onPointerUp focus X: " + this.f14128b + " focus Y: " + this.f14129c);
                    }
                }
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            GPUImagePanZoomViewer.this.m16094c("onDoubleTap");
            if (GPUImagePanZoomViewer.this.f14069t) {
                return false;
            }
            GPUImagePanZoomViewer.this.f14074y = true;
            if (GPUImagePanZoomViewer.this.f14066q == ViewerMode.unknown) {
                GPUImagePanZoomViewer.this.f14066q = ViewerMode.imageDoubleTaping;
            } else if (GPUImagePanZoomViewer.this.f14066q == ViewerMode.imageBouncing) {
                GPUImagePanZoomViewer.this.m16058d0();
            }
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
            C3097b.d dVar = gPUImagePanZoomViewer.f14161f;
            gPUImagePanZoomViewer.m16072o0(ScaleMode.doubleTap, x8, y8, dVar.f14195n < 0.9999f ? 1.0f : dVar.f14193l, null);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            if (motionEvent.getPointerCount() > 1) {
                return true;
            }
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            this.f14128b = x8;
            this.f14129c = y8;
            this.f14130d = motionEvent.getPointerId(0);
            GPUImagePanZoomViewer gPUImagePanZoomViewer = GPUImagePanZoomViewer.this;
            this.f14131e = gPUImagePanZoomViewer.f14161f.f14195n;
            gPUImagePanZoomViewer.m16094c("onDown focus X: " + this.f14128b + " focus Y: " + this.f14129c);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (GPUImagePanZoomViewer.this.f14066q != ViewerMode.imageViewing) {
                return true;
            }
            if (GPUImagePanZoomViewer.this.f14072w.containsKey(GPUImagePanZoomViewer.this.f14073x) && (motionEvent == null || motionEvent2 == null || motionEvent.getPointerCount() <= 1 || motionEvent2.getPointerCount() <= 1)) {
                return true;
            }
            new PointF(motionEvent2.getX() + (f9 * 0.3f), motionEvent2.getY() + (0.3f * f10));
            GPUImagePanZoomViewer.this.m16066j0(f9, f10);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (motionEvent == null || motionEvent2 == null || motionEvent.getPointerCount() > 1 || motionEvent2.getPointerCount() > 1 || GPUImagePanZoomViewer.this.f14072w.containsKey(GPUImagePanZoomViewer.this.f14073x)) {
                return true;
            }
            float x8 = motionEvent2.getX();
            float y8 = motionEvent2.getY();
            float x9 = motionEvent2.getX() - this.f14128b;
            float y9 = motionEvent2.getY() - this.f14129c;
            if (motionEvent.getPointerCount() > 1 || motionEvent2.getPointerCount() > 1) {
                int iFindPointerIndex = motionEvent2.findPointerIndex(this.f14130d);
                if (iFindPointerIndex > 0) {
                    x8 = motionEvent2.getX(iFindPointerIndex);
                    y8 = motionEvent2.getY(iFindPointerIndex);
                    x9 = x8 - this.f14128b;
                    y9 = y8 - this.f14129c;
                }
                if (Math.abs(this.f14131e - GPUImagePanZoomViewer.this.f14161f.f14195n) > BitmapDescriptorFactory.HUE_RED) {
                    this.f14131e = GPUImagePanZoomViewer.this.f14161f.f14195n;
                    this.f14128b = x8;
                    this.f14129c = y8;
                    return true;
                }
            }
            GPUImagePanZoomViewer.this.m16094c("onScroll focus x: " + x8 + " focux y: " + y8 + " translate x: " + x9 + " translate y: " + y9 + " scale: 1.0");
            GPUImagePanZoomViewer.this.m16068k0(new PointF((((float) GPUImagePanZoomViewer.this.getWidth()) / 2.0f) - x8, (((float) GPUImagePanZoomViewer.this.getHeight()) / 2.0f) - y8), new PointF(x9, y9), 1.0f);
            this.f14128b = x8;
            this.f14129c = y8;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return GPUImagePanZoomViewer.this.f14066q == ViewerMode.unknown;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$j */
    public class ScaleGestureDetectorOnScaleGestureListenerC3094j implements ScaleGestureDetector.OnScaleGestureListener {

        /* renamed from: a */
        public float f14133a = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: b */
        public float f14134b = BitmapDescriptorFactory.HUE_RED;

        public ScaleGestureDetectorOnScaleGestureListenerC3094j() {
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            float f9 = focusX - this.f14133a;
            float f10 = focusY - this.f14134b;
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            GPUImagePanZoomViewer.this.m16094c("onScale focus x: " + focusX + " focux y: " + focusY + " translate x: " + f9 + " translate y: " + f10 + " scale: " + scaleFactor);
            GPUImagePanZoomViewer.this.m16068k0(new PointF((((float) GPUImagePanZoomViewer.this.getWidth()) / 2.0f) - focusX, (((float) GPUImagePanZoomViewer.this.getHeight()) / 2.0f) - focusY), new PointF(f9, f10), scaleFactor);
            this.f14133a = focusX;
            this.f14134b = focusY;
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            this.f14133a = focusX;
            this.f14134b = focusY;
            GPUImagePanZoomViewer.this.m16094c("onScaleBegin focus x: " + focusX + " focux y: " + focusY + " scale: " + scaleFactor);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            float f9 = focusX - this.f14133a;
            float f10 = focusY - this.f14134b;
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            GPUImagePanZoomViewer.this.m16094c("onScaleEnd focus x: " + focusX + " focux y: " + focusY + " translate x: " + f9 + " translate y: " + f10 + " scale: " + scaleFactor);
            GPUImagePanZoomViewer.this.m16069l0(new PointF((((float) GPUImagePanZoomViewer.this.getWidth()) / 2.0f) - focusX, (((float) GPUImagePanZoomViewer.this.getHeight()) / 2.0f) - focusY), new PointF(f9, f10), scaleFactor);
            this.f14133a = BitmapDescriptorFactory.HUE_RED;
            this.f14134b = BitmapDescriptorFactory.HUE_RED;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer$k */
    public class C3095k {

        /* renamed from: a */
        public Matrix f14136a;

        /* renamed from: b */
        public boolean f14137b;

        public C3095k(Matrix matrix, boolean z8) {
            this.f14136a = null;
            this.f14137b = true;
            this.f14136a = new Matrix(matrix);
            this.f14137b = z8;
        }
    }

    public GPUImagePanZoomViewer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14061l = null;
        this.f14062m = null;
        this.f14063n = null;
        this.f14064o = null;
        this.f14065p = null;
        this.f14066q = ViewerMode.unknown;
        this.f14067r = null;
        this.f14068s = null;
        this.f14069t = false;
        this.f14070u = false;
        this.f14071v = false;
        this.f14072w = null;
        this.f14073x = null;
        this.f14074y = false;
        this.f14075z = 100L;
        Boolean bool = Boolean.FALSE;
        this.f14058A = bool;
        this.f14059B = bool;
        this.f14060C = null;
        if (isInEditMode()) {
            return;
        }
        m16064h0(context);
    }

    private void setTouchStatus(boolean z8) {
    }

    /* renamed from: S */
    public final void m16047S() {
        this.f14074y = true;
        float fM16051W = m16051W(this.f14065p);
        float fM16052X = m16052X(this.f14065p);
        float width = getWidth() / 2;
        float height = getHeight() / 2;
        if (fM16051W == BitmapDescriptorFactory.HUE_RED && fM16052X == BitmapDescriptorFactory.HUE_RED) {
            this.f14074y = false;
            this.f14066q = ViewerMode.unknown;
            return;
        }
        this.f14069t = true;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new C3088d(fM16051W, fM16052X, width, height));
        valueAnimatorOfFloat.addListener(new C3089e(width, height));
        m16094c("Bouncing start");
        valueAnimatorOfFloat.start();
        this.f14068s = valueAnimatorOfFloat;
        this.f14066q = ViewerMode.imageBouncing;
    }

    /* renamed from: T */
    public final void m16048T() {
        float fM16051W = m16051W(this.f14065p);
        float fM16052X = m16052X(this.f14065p);
        float width = getWidth() / 2;
        float height = getHeight() / 2;
        if (fM16051W != BitmapDescriptorFactory.HUE_RED || fM16052X != BitmapDescriptorFactory.HUE_RED) {
            m16071n0(new PointF(width, height), new PointF(m16051W(this.f14065p), m16052X(this.f14065p)), 1.0f);
        }
        this.f14066q = ViewerMode.unknown;
    }

    /* renamed from: U */
    public final float m16049U(float f9) {
        if (f9 == BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return f9 > BitmapDescriptorFactory.HUE_RED ? -0.003f : 0.003f;
    }

    /* renamed from: V */
    public final long m16050V(float f9, float f10) {
        if (f9 == BitmapDescriptorFactory.HUE_RED) {
            return 0L;
        }
        return (long) (((-f10) / 1000.0f) / f9);
    }

    /* renamed from: W */
    public final float m16051W(Matrix matrix) {
        m16094c("[calculateBouncingTX]");
        float width = getWidth() / 2;
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float f9 = this.f14161f.f14187f;
        float f10 = fArr[0];
        float f11 = fArr[2] * f10;
        float f12 = f11 - (-width);
        float f13 = width - (f11 + (f9 * f10));
        if (f12 >= BitmapDescriptorFactory.HUE_RED || f13 <= BitmapDescriptorFactory.HUE_RED) {
            return (f12 <= BitmapDescriptorFactory.HUE_RED || f13 >= BitmapDescriptorFactory.HUE_RED) ? (f12 <= BitmapDescriptorFactory.HUE_RED || f13 <= BitmapDescriptorFactory.HUE_RED) ? BitmapDescriptorFactory.HUE_RED : (f13 - f12) / 2.0f : f12 + f13 < BitmapDescriptorFactory.HUE_RED ? -f12 : (-(f12 - f13)) / 2.0f;
        }
        if (f12 + f13 >= BitmapDescriptorFactory.HUE_RED) {
            f13 = (f13 - f12) / 2.0f;
        }
        return f13;
    }

    /* renamed from: X */
    public final float m16052X(Matrix matrix) {
        float height = getHeight() / 2;
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float f9 = this.f14161f.f14188g;
        float f10 = fArr[0];
        float f11 = f9 * f10;
        float f12 = fArr[5] * f10;
        float f13 = f12 - (-height);
        float f14 = height - (f12 + f11);
        if (f13 >= BitmapDescriptorFactory.HUE_RED || f14 <= BitmapDescriptorFactory.HUE_RED) {
            return (f13 <= BitmapDescriptorFactory.HUE_RED || f14 >= BitmapDescriptorFactory.HUE_RED) ? (f13 <= BitmapDescriptorFactory.HUE_RED || f14 <= BitmapDescriptorFactory.HUE_RED) ? BitmapDescriptorFactory.HUE_RED : (f14 - f13) / 2.0f : f13 + f14 < BitmapDescriptorFactory.HUE_RED ? -f13 : (-(f13 - f14)) / 2.0f;
        }
        if (f13 + f14 >= BitmapDescriptorFactory.HUE_RED) {
            f14 = (f14 - f13) / 2.0f;
        }
        return f14;
    }

    /* renamed from: Y */
    public float m16053Y(float f9) {
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: Z */
    public final C3095k m16054Z(PointF pointF, PointF pointF2, float f9) {
        m16094c("[_calculateTransformResult]");
        if (pointF2.x == BitmapDescriptorFactory.HUE_RED && pointF2.y == BitmapDescriptorFactory.HUE_RED && f9 == 1.0f) {
            return new C3095k(this.f14065p, this.f14161f.f14192k);
        }
        ViewerMode viewerMode = this.f14066q;
        return (viewerMode == ViewerMode.imageViewing || viewerMode == ViewerMode.imageFling || viewerMode == ViewerMode.imageDoubleTaping) ? m16056b0(pointF, pointF2, f9) : m16055a0(pointF2);
    }

    /* renamed from: a0 */
    public C3095k m16055a0(PointF pointF) {
        m16094c("[_calculateTransformResultForImageBouncingMode]");
        float f9 = this.f14161f.f14195n;
        Matrix matrix = new Matrix(this.f14065p);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        m16094c("before translate matrix left: " + (fArr[2] * fArr[0]) + " top: " + (fArr[5] * fArr[0]));
        float f10 = f9 * f9;
        float f11 = 1.0f / f10;
        matrix.preScale(f11, f11);
        matrix.preTranslate(pointF.x, pointF.y);
        matrix.preScale(f10, f10);
        matrix.getValues(fArr);
        m16094c("after translate matrix left: " + (fArr[2] * fArr[0]) + " top: " + (fArr[5] * fArr[0]));
        return new C3095k(matrix, this.f14161f.f14192k);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x015e  */
    /* renamed from: b0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C3095k m16056b0(PointF pointF, PointF pointF2, float f9) {
        float f10;
        boolean z8;
        int i9;
        boolean z9;
        int i10;
        boolean z10;
        m16094c("[_calculateTransformResultForImageViewingMode]");
        C3097b.d dVar = this.f14161f;
        int i11 = dVar.f14187f;
        int i12 = dVar.f14188g;
        PointF pointF3 = new PointF(pointF2.x, pointF2.y);
        float[] fArr = new float[9];
        this.f14065p.getValues(fArr);
        float f11 = fArr[0];
        Matrix matrix = new Matrix(this.f14065p);
        float f12 = f11 * f11;
        float f13 = 1.0f / f12;
        matrix.preScale(f13, f13);
        matrix.preTranslate(-pointF.x, -pointF.y);
        matrix.preTranslate(pointF2.x, pointF2.y);
        matrix.preScale(f9, f9);
        matrix.preTranslate(pointF.x, pointF.y);
        matrix.preScale(f12, f12);
        float[] fArr2 = new float[9];
        matrix.getValues(fArr2);
        float f14 = fArr2[0];
        int i13 = (int) (i11 * f14);
        int i14 = (int) (i12 * f14);
        int i15 = (int) (fArr2[2] * f14);
        int i16 = (int) (fArr2[5] * f14);
        int i17 = i15 + i13;
        int i18 = i16 + i14;
        ViewerMode viewerMode = this.f14066q;
        ViewerMode viewerMode2 = ViewerMode.imageDoubleTaping;
        int i19 = viewerMode != viewerMode2 ? this.f14158c / 8 : 0;
        int i20 = viewerMode != viewerMode2 ? this.f14159d / 8 : 0;
        C3097b.d dVar2 = this.f14161f;
        float f15 = dVar2.f14194m;
        if (f14 <= f15) {
            float f16 = dVar2.f14193l;
            if (f14 <= f16) {
                f10 = f16 / dVar2.f14195n;
            } else if (f14 >= 1.0f || !dVar2.f14192k) {
                f10 = f9;
            } else {
                f10 = f9;
            }
            z8 = true;
            int i21 = this.f14158c;
            float f17 = f10;
            i9 = ((-i21) / 2) + i19;
            int i22 = (i21 / 2) - i19;
            if (i15 <= i9) {
                if (pointF2.x > BitmapDescriptorFactory.HUE_RED) {
                    pointF2.set((int) Math.floor(r11 + (i9 - i15)), pointF2.y);
                } else {
                    if (i17 < i22) {
                        if (pointF2.x < BitmapDescriptorFactory.HUE_RED) {
                            pointF2.set((int) Math.floor(r7 + (i22 - i17)), pointF2.y);
                        }
                    }
                    z9 = false;
                }
                z9 = true;
            }
            int i23 = this.f14159d;
            i10 = ((-i23) / 2) + i20;
            int i24 = (i23 / 2) - i20;
            if (i16 <= i10) {
                if (pointF2.y > BitmapDescriptorFactory.HUE_RED) {
                    pointF2.set(pointF2.x, (int) Math.floor(r10 + (i10 - i16)));
                } else {
                    if (i18 < i24) {
                        if (pointF2.y < BitmapDescriptorFactory.HUE_RED) {
                            pointF2.set(pointF2.x, (int) Math.floor(r6 + (i24 - i18)));
                        }
                    }
                    z10 = false;
                }
                z10 = true;
            }
            if (Math.floor(i13) <= this.f14158c) {
                pointF2.set(BitmapDescriptorFactory.HUE_RED, pointF2.y);
                pointF.set(BitmapDescriptorFactory.HUE_RED, pointF.y);
            }
            if (Math.floor(i14) <= this.f14159d) {
                pointF2.set(pointF2.x, BitmapDescriptorFactory.HUE_RED);
                pointF.set(pointF.x, BitmapDescriptorFactory.HUE_RED);
            }
            if (this.f14067r != null) {
                if ((z8 && z9) || (!z8 && z9 && z10)) {
                    pointF2.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                    this.f14067r.cancel();
                } else if (z9 && pointF3.y == BitmapDescriptorFactory.HUE_RED) {
                    pointF2.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                    this.f14067r.cancel();
                } else if (z10 && pointF3.x == BitmapDescriptorFactory.HUE_RED) {
                    pointF2.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                    this.f14067r.cancel();
                }
            }
            Matrix matrix2 = new Matrix(this.f14065p);
            matrix2.preScale(f13, f13);
            matrix2.preTranslate(-pointF.x, -pointF.y);
            matrix2.preTranslate(pointF2.x, pointF2.y);
            matrix2.preScale(f17, f17);
            matrix2.preTranslate(pointF.x, pointF.y);
            matrix2.preScale(f12, f12);
            return new C3095k(matrix2, z8);
        }
        f10 = f15 / dVar2.f14195n;
        z8 = false;
        int i212 = this.f14158c;
        float f172 = f10;
        i9 = ((-i212) / 2) + i19;
        int i222 = (i212 / 2) - i19;
        if (i15 <= i9) {
        }
        int i232 = this.f14159d;
        i10 = ((-i232) / 2) + i20;
        int i242 = (i232 / 2) - i20;
        if (i16 <= i10) {
        }
        if (Math.floor(i13) <= this.f14158c) {
        }
        if (Math.floor(i14) <= this.f14159d) {
        }
        if (this.f14067r != null) {
        }
        Matrix matrix22 = new Matrix(this.f14065p);
        matrix22.preScale(f13, f13);
        matrix22.preTranslate(-pointF.x, -pointF.y);
        matrix22.preTranslate(pointF2.x, pointF2.y);
        matrix22.preScale(f172, f172);
        matrix22.preTranslate(pointF.x, pointF.y);
        matrix22.preScale(f12, f12);
        return new C3095k(matrix22, z8);
    }

    /* renamed from: c0 */
    public final void m16057c0() {
        if (this.f14072w.containsKey(this.f14073x)) {
            this.f14072w.get(this.f14073x).m24817b(Boolean.FALSE);
            m16059e0();
        }
    }

    /* renamed from: d0 */
    public final void m16058d0() {
        ValueAnimator valueAnimator = this.f14068s;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* renamed from: e0 */
    public final void m16059e0() {
        Timer timer = this.f14060C;
        if (timer != null) {
            timer.cancel();
            this.f14060C.purge();
            this.f14060C = null;
        }
    }

    /* renamed from: f0 */
    public final ViewerMode m16060f0() {
        return ViewerMode.imageViewing;
    }

    @Override // com.cyberlink.you.kernelctrl.gpuimage.C3097b
    /* renamed from: g */
    public FrameLayout.LayoutParams mo16061g(boolean z8) {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    /* renamed from: g0 */
    public C6824b m16062g0(float f9, float f10, boolean z8) {
        float f11;
        m16094c("[engineUnitSpaceToViewSpace] unitX: " + f9 + " unitY:" + f10);
        float[] fArr = new float[9];
        new Matrix(this.f14065p).getValues(fArr);
        float f12 = fArr[0];
        C3097b.d dVar = this.f14161f;
        float f13 = ((float) dVar.f14187f) * f12;
        float f14 = dVar.f14188g * f12;
        float f15 = fArr[2] * f12;
        float f16 = fArr[5] * f12;
        int i9 = dVar.f14184c;
        int i10 = dVar.f14185d;
        float f17 = f9 * i9 * f12;
        float f18 = f10 * i10 * f12;
        int i11 = dVar.f14186e;
        float f19 = i11 == 6 ? 1.5707964f : i11 == 3 ? 3.1415927f : i11 == 8 ? 4.712389f : 0.0f;
        float fCos = f17 - ((i9 * f12) / 2.0f);
        float fSin = f18 - ((i10 * f12) / 2.0f);
        if (f19 != BitmapDescriptorFactory.HUE_RED) {
            double d9 = fCos;
            double d10 = f19;
            f11 = f15;
            double d11 = fSin;
            fCos = ((float) (d9 * Math.cos(d10))) - ((float) (d11 * Math.sin(d10)));
            fSin = ((float) (d9 * Math.sin(d10))) + ((float) (d11 * Math.cos(d10)));
        } else {
            f11 = f15;
        }
        int i12 = this.f14161f.f14186e;
        if (i12 == 2) {
            fCos = -fCos;
        }
        if (i12 == 4) {
            fSin = -fSin;
        }
        C6824b c6824b = new C6824b();
        c6824b.f22661a = fCos + f11 + (f13 / 2.0f) + (this.f14158c / 2.0f);
        c6824b.f22662b = fSin + f16 + (f14 / 2.0f) + (this.f14159d / 2.0f);
        m16094c("[engineUnitSpaceToViewSpace] viewSpaceX: " + c6824b.f22661a + " viewSpaceY:" + c6824b.f22662b);
        return c6824b;
    }

    public UUID getCurrentBehavior() {
        return this.f14073x;
    }

    public UUID getViewID() {
        return f14057D;
    }

    @Override // com.cyberlink.you.kernelctrl.gpuimage.C3097b
    /* renamed from: h */
    public FrameLayout.LayoutParams mo16063h(boolean z8) {
        C3097b.d dVar = this.f14161f;
        float f9 = dVar.f14187f;
        float f10 = dVar.f14195n;
        return new FrameLayout.LayoutParams((int) (f9 * f10), (int) (dVar.f14188g * f10), 17);
    }

    /* renamed from: h0 */
    public final void m16064h0(Context context) {
        this.f14062m = new ScaleGestureDetectorOnScaleGestureListenerC3094j();
        this.f14061l = new ScaleGestureDetector(context, this.f14062m);
        this.f14064o = new C3093i();
        this.f14063n = new GestureDetector(context, this.f14064o);
        this.f14072w = new HashMap<>();
    }

    /* renamed from: i0 */
    public final boolean m16065i0() {
        return true;
    }

    /* renamed from: j0 */
    public final void m16066j0(float f9, float f10) {
        float f11 = getResources().getDisplayMetrics().density;
        float f12 = f9 / f11;
        float f13 = f10 / f11;
        if (Math.abs(f12) >= 500.0f || Math.abs(f13) >= 500.0f) {
            this.f14066q = ViewerMode.unknown;
            if (this.f14069t) {
                return;
            }
            this.f14074y = true;
            int iAbs = (int) (f9 / Math.abs(f9));
            int iAbs2 = (int) (f10 / Math.abs(f10));
            float f14 = Math.abs(f9) < 4000.0f ? f9 : iAbs * 4000;
            float f15 = Math.abs(f10) < 4000.0f ? f10 : iAbs2 * 4000;
            float fM16049U = m16049U(f14);
            float fM16049U2 = m16049U(f15);
            long jM16050V = m16050V(fM16049U, f14);
            long jM16050V2 = m16050V(fM16049U2, f15);
            long j9 = jM16050V > jM16050V2 ? jM16050V : jM16050V2;
            this.f14066q = ViewerMode.imageFling;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
            valueAnimatorOfFloat.setDuration(j9);
            valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat.addUpdateListener(new C3086b(f14, fM16049U, f15, fM16049U2, jM16050V, jM16050V2));
            valueAnimatorOfFloat.addListener(new C3087c());
            m16094c("Fling start");
            valueAnimatorOfFloat.start();
            this.f14067r = valueAnimatorOfFloat;
        }
    }

    @Override // com.cyberlink.you.kernelctrl.gpuimage.C3097b
    /* renamed from: k */
    public void mo16067k(C3097b.d dVar, CLUtility.C3138e c3138e, boolean z8) {
        super.mo16067k(dVar, c3138e, z8);
        m16078t("[initImageInfo]");
        Matrix matrix = new Matrix();
        this.f14065p = matrix;
        matrix.preTranslate((-dVar.f14187f) / 2.0f, (-dVar.f14188g) / 2);
        Matrix matrix2 = this.f14065p;
        float f9 = dVar.f14193l;
        matrix2.preScale(f9, f9);
    }

    /* renamed from: k0 */
    public final void m16068k0(PointF pointF, PointF pointF2, float f9) {
        m16094c("[onGestureChange]");
        if (this.f14066q == ViewerMode.unknown) {
            this.f14066q = m16060f0();
        }
        if (this.f14069t) {
            return;
        }
        m16071n0(pointF, pointF2, f9);
    }

    /* renamed from: l0 */
    public final void m16069l0(PointF pointF, PointF pointF2, float f9) {
        m16094c("[onGestureEnd]");
        ViewerMode viewerMode = this.f14066q;
        this.f14066q = ViewerMode.unknown;
        if (this.f14069t) {
            return;
        }
        if (viewerMode == ViewerMode.imageViewing) {
            m16047S();
        } else {
            m16094c("[onGestureEnd] Error: get unknown state");
        }
    }

    /* renamed from: m0 */
    public final void m16070m0() {
        if (this.f14072w.containsKey(this.f14073x)) {
            this.f14063n = null;
            this.f14064o = null;
            this.f14061l = null;
            this.f14062m = null;
            this.f14072w.get(this.f14073x).m24817b(Boolean.TRUE);
        }
    }

    /* renamed from: n0 */
    public final void m16071n0(PointF pointF, PointF pointF2, float f9) {
        m16094c("[renderCachedImage]");
        if (pointF2.x == BitmapDescriptorFactory.HUE_RED && pointF2.y == BitmapDescriptorFactory.HUE_RED && f9 == 1.0f) {
            m16094c("[renderCachedImage] No scale and translate, return");
            return;
        }
        C3095k c3095kM16054Z = m16054Z(pointF, pointF2, f9);
        float[] fArr = new float[9];
        c3095kM16054Z.f14136a.getValues(fArr);
        float f10 = fArr[0];
        C3097b.d dVar = this.f14161f;
        int i9 = (int) (fArr[2] * f10);
        int i10 = (int) (fArr[5] * f10);
        m16094c("transform result: left = " + i9 + " top = " + i10 + " width = " + ((int) (dVar.f14187f * f10)) + " height= " + ((int) (dVar.f14188g * f10)));
        this.f14065p = new Matrix(c3095kM16054Z.f14136a);
        C3097b.d dVar2 = this.f14161f;
        dVar2.f14195n = f10;
        dVar2.f14192k = c3095kM16054Z.f14137b;
        m16076r0();
    }

    /* renamed from: o0 */
    public void m16072o0(ScaleMode scaleMode, float f9, float f10, float f11, C5177q1.b bVar) {
        float f12 = this.f14161f.f14195n;
        float f13 = f11 / f12;
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        float fM16053Y = m16053Y(f11);
        PointF pointF = new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        PointF pointF2 = scaleMode == ScaleMode.doubleTap ? new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED) : new PointF((width - f9) * f13, (height - f10) * f13);
        C6823a c6823aM16077s0 = m16077s0(f9, f10, false);
        float[] fArr = new float[9];
        this.f14065p.getValues(fArr);
        float f14 = fArr[0];
        C3097b.d dVar = this.f14161f;
        float f15 = dVar.f14187f * f14;
        float f16 = (fArr[2] * f14) + (f15 / 2.0f);
        float f17 = (fArr[5] * f14) + ((dVar.f14188g * f14) / 2.0f);
        float f18 = pointF2.x - f16;
        float f19 = (pointF2.y + fM16053Y) - f17;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
        valueAnimatorOfFloat.setDuration(300L);
        valueAnimatorOfFloat.addUpdateListener(new C3091g(scaleMode, f11, c6823aM16077s0, f12, width, height, pointF, f18, f19, pointF2, fM16053Y, f16, f17));
        valueAnimatorOfFloat.addListener(new C3092h(f11, fM16053Y, bVar));
        m16094c("double tap valueAnimator start");
        this.f14069t = true;
        valueAnimatorOfFloat.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        C3093i c3093i;
        if (!isEnabled() || !super.m16100m()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            Boolean bool = Boolean.FALSE;
            this.f14059B = bool;
            if (this.f14072w.containsKey(this.f14073x)) {
                m16057c0();
                this.f14058A = bool;
                m16073p0();
            }
            setTouchStatus(true);
        } else if (actionMasked == 1) {
            if (motionEvent.getPointerCount() == 1) {
                setTouchStatus(false);
            }
            if (!this.f14058A.booleanValue() && motionEvent.getActionMasked() == 1 && !this.f14074y && !this.f14059B.booleanValue()) {
                m16070m0();
                m16057c0();
            }
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                setTouchStatus(false);
                m16057c0();
            } else if (actionMasked != 5) {
                if (actionMasked == 6) {
                }
            } else if (!this.f14058A.booleanValue()) {
                this.f14059B = Boolean.TRUE;
                m16057c0();
            }
        } else if ((System.nanoTime() / C3322C.MICROS_PER_SECOND) - motionEvent.getDownTime() > 100) {
            this.f14058A = Boolean.valueOf(!this.f14059B.booleanValue());
        }
        if (this.f14072w.containsKey(this.f14073x)) {
            InterfaceC6479a interfaceC6479a = this.f14072w.get(this.f14073x);
            if (this.f14058A.booleanValue()) {
                if (this.f14074y || this.f14070u) {
                    interfaceC6479a.m24816a(motionEvent, Boolean.valueOf(motionEvent.getActionMasked() != 1));
                    if (this.f14070u && (c3093i = this.f14064o) != null) {
                        this.f14070u = false;
                        c3093i.m16081a(motionEvent);
                    }
                } else {
                    interfaceC6479a.m24817b(Boolean.TRUE);
                    interfaceC6479a.m24816a(motionEvent, Boolean.FALSE);
                }
                return true;
            }
            if (!this.f14058A.booleanValue() && !this.f14059B.booleanValue()) {
                interfaceC6479a.m24816a(motionEvent, Boolean.valueOf(motionEvent.getActionMasked() != 1));
            }
        }
        if (this.f14062m == null) {
            this.f14062m = new ScaleGestureDetectorOnScaleGestureListenerC3094j();
        }
        if (this.f14061l == null) {
            this.f14061l = new ScaleGestureDetector(getContext(), this.f14062m);
        }
        if (this.f14064o == null) {
            this.f14064o = new C3093i();
        }
        if (this.f14063n == null) {
            this.f14063n = new GestureDetector(getContext(), this.f14064o);
        }
        if (!m16065i0()) {
            this.f14071v = true;
        }
        if (this.f14071v) {
            if (motionEvent.getActionMasked() == 1) {
                this.f14071v = false;
            }
            return true;
        }
        this.f14063n.onTouchEvent(motionEvent);
        this.f14061l.onTouchEvent(motionEvent);
        int actionMasked2 = motionEvent.getActionMasked();
        if (actionMasked2 == 0) {
            this.f14070u = true;
            this.f14065p.getValues(new float[9]);
            ValueAnimator valueAnimator = this.f14067r;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        } else if (actionMasked2 == 1 || actionMasked2 == 3) {
            this.f14070u = false;
            this.f14064o.m16081a(motionEvent);
        } else if (actionMasked2 == 6) {
            this.f14064o.m16082b(motionEvent);
        }
        return true;
    }

    /* renamed from: p0 */
    public final void m16073p0() {
        m16059e0();
        Timer timer = new Timer();
        this.f14060C = timer;
        timer.schedule(new C3090f(), 100L);
    }

    @Override // com.cyberlink.you.kernelctrl.gpuimage.C3097b
    /* renamed from: q */
    public void mo16074q(DevelopSetting developSetting, DevelopSetting developSetting2, DevelopSetting developSetting3, double d9, double d10, double d11, GLViewEngine.EffectParam.ExtraFunc extraFunc) {
        C3097b.d dVar = this.f14161f;
        Bitmap bitmap = dVar.f14199r;
        if (bitmap == null || ((dVar.f14189h == null && dVar.f14190i == null && dVar.f14191j == null) || bitmap.isRecycled())) {
            Log.e("GPUImagePanZoomViewer", "updateGPUImageView failed");
            return;
        }
        C3097b.d dVar2 = this.f14161f;
        GLViewEngine.EffectParam effectParam = new GLViewEngine.EffectParam(developSetting, d9, dVar2.f14199r, dVar2.f14200s, dVar2.f14201t, dVar2.f14202u, extraFunc);
        C3097b.d dVar3 = this.f14161f;
        GLViewEngine.EffectParam effectParam2 = new GLViewEngine.EffectParam(developSetting2, d10, dVar3.f14199r, dVar3.f14200s, dVar3.f14201t, dVar3.f14202u, extraFunc);
        C3097b.d dVar4 = this.f14161f;
        try {
            GLViewEngine.m15970g().m15971d(this.f14162g, this.f14161f.f14199r, effectParam, effectParam2, new GLViewEngine.EffectParam(developSetting3, d11, dVar4.f14199r, dVar4.f14200s, dVar4.f14201t, dVar4.f14202u, extraFunc), this.f14065p, new C3085a(), null);
        } catch (IllegalArgumentException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: q0 */
    public void m16075q0(PointF pointF, PointF pointF2, float f9) {
        float f10 = this.f14161f.f14195n;
        Matrix matrix = new Matrix(this.f14065p);
        float f11 = f10 * f10;
        float f12 = 1.0f / f11;
        matrix.preScale(f12, f12);
        matrix.preTranslate(-pointF.x, -pointF.y);
        matrix.preTranslate(pointF2.x, pointF2.y);
        matrix.preTranslate(pointF.x, pointF.y);
        matrix.preScale(f11, f11);
        Matrix matrix2 = new Matrix(matrix);
        matrix2.preScale(f12, f12);
        matrix2.preTranslate(-pointF.x, -pointF.y);
        matrix2.preScale(f9, f9);
        matrix2.preTranslate(pointF.x, pointF.y);
        matrix2.preScale(f11, f11);
        float[] fArr = new float[9];
        matrix2.getValues(fArr);
        float f13 = fArr[0];
        this.f14065p = new Matrix(matrix2);
        C3097b.d dVar = this.f14161f;
        dVar.f14195n = f13;
        dVar.f14192k = false;
        if (f13 <= dVar.f14193l) {
            dVar.f14192k = true;
        }
        m16076r0();
    }

    /* renamed from: r0 */
    public final void m16076r0() {
        C3097b.d dVar = this.f14161f;
        mo16074q(dVar.f14189h, dVar.f14190i, dVar.f14191j, dVar.f14196o, dVar.f14197p, dVar.f14198q, dVar.f14203v);
    }

    /* renamed from: s0 */
    public C6823a m16077s0(float f9, float f10, boolean z8) {
        m16094c("[viewSpaceToEngineUnitSpace] x: " + f9 + " y:" + f10);
        float f11 = (((float) this.f14158c) / 2.0f) - f9;
        float f12 = (((float) this.f14159d) / 2.0f) - f10;
        float[] fArr = new float[9];
        new Matrix(this.f14065p).getValues(fArr);
        float f13 = fArr[0];
        C3097b.d dVar = this.f14161f;
        float f14 = dVar.f14187f * f13;
        float f15 = dVar.f14188g * f13;
        float f16 = (-f11) - ((fArr[2] * f13) + (f14 / 2.0f));
        float f17 = (-f12) - ((fArr[5] * f13) + (f15 / 2.0f));
        int i9 = dVar.f14186e;
        if (i9 == 2) {
            f16 = -f16;
        }
        if (i9 == 4) {
            f17 = -f17;
        }
        float f18 = i9 == 6 ? 1.5707964f : i9 == 3 ? 3.1415927f : i9 == 8 ? 4.712389f : 0.0f;
        if (f18 != BitmapDescriptorFactory.HUE_RED) {
            double d9 = f16;
            double d10 = f18 * (-1.0f);
            double d11 = f17;
            float fCos = ((float) (Math.cos(d10) * d9)) - ((float) (Math.sin(d10) * d11));
            float fCos2 = ((float) (d11 * Math.cos(d10))) + ((float) (d9 * Math.sin(d10)));
            C3097b.d dVar2 = this.f14161f;
            f14 = dVar2.f14184c * f13;
            f15 = dVar2.f14185d * f13;
            f16 = fCos;
            f17 = fCos2;
        }
        float f19 = (f16 + (f14 / 2.0f)) / f13;
        float f20 = (f17 + (f15 / 2.0f)) / f13;
        C6823a c6823a = new C6823a();
        C3097b.d dVar3 = this.f14161f;
        c6823a.f22659a = f19 / dVar3.f14184c;
        c6823a.f22660b = f20 / dVar3.f14185d;
        m16094c("[viewSpaceToEngineUnitSpace] engine unit x: " + c6823a.f22659a + " engine unit y:" + c6823a.f22660b);
        return c6823a;
    }

    /* renamed from: t */
    public final void m16078t(String str) {
        C6478z.m24810b("GPUImagePanZoomViewer", str);
    }
}
