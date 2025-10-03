package com.cyberlink.you.kernelctrl.glviewengine;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import com.cyberlink.clgpuimage.C0913n1;
import com.cyberlink.clgpuimage.C0936w0;
import com.cyberlink.clgpuimage.GPUImage;
import com.cyberlink.clgpuimage.Rotation;
import com.cyberlink.you.Globals;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomFilter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class GLViewEngine {

    /* renamed from: a */
    public C3079g f13944a;

    /* renamed from: b */
    public C3080a f13945b;

    /* renamed from: c */
    public C3080a f13946c;

    /* renamed from: d */
    public Bitmap f13947d;

    /* renamed from: e */
    public int f13948e;

    /* renamed from: f */
    public Bitmap f13949f;

    public static class EffectParam {

        /* renamed from: a */
        public final DevelopSetting f13950a;

        /* renamed from: b */
        public final double f13951b;

        /* renamed from: c */
        public final Bitmap f13952c;

        /* renamed from: d */
        public final Rotation f13953d;

        /* renamed from: e */
        public final boolean f13954e;

        /* renamed from: f */
        public final boolean f13955f;

        /* renamed from: g */
        public ExtraFunc f13956g;

        public enum ExtraFunc {
            None,
            AutoToneCapture,
            AutoToneEdit
        }

        public EffectParam(DevelopSetting developSetting, double d9, Bitmap bitmap, Rotation rotation, boolean z8, boolean z9, ExtraFunc extraFunc) {
            this.f13956g = ExtraFunc.None;
            if (d9 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d9 > 1.0d) {
                throw new IllegalArgumentException("Strength should be [0, 1]");
            }
            if (bitmap == null) {
                throw new IllegalArgumentException("RefBitmap cannot be null");
            }
            if (rotation == null) {
                throw new IllegalArgumentException("Rotation cannot be null");
            }
            this.f13950a = developSetting;
            this.f13951b = d9;
            this.f13952c = bitmap;
            this.f13953d = rotation;
            this.f13954e = z8;
            this.f13955f = z9;
            this.f13956g = extraFunc;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$b */
    public abstract class AbstractC3074b<T> {

        /* renamed from: a */
        public final Bitmap f13961a;

        /* renamed from: b */
        public EffectParam f13962b;

        /* renamed from: c */
        public EffectParam f13963c;

        /* renamed from: d */
        public EffectParam f13964d;

        /* renamed from: e */
        public final InterfaceC3075c<T> f13965e;

        /* renamed from: f */
        public final Object f13966f;

        /* renamed from: c */
        public final void m15978c(String str) {
            InterfaceC3075c<T> interfaceC3075c = this.f13965e;
            if (interfaceC3075c != null) {
                interfaceC3075c.mo15552b(this.f13966f, str);
            }
        }

        /* renamed from: d */
        public abstract T mo15979d();

        /* renamed from: e */
        public final void m15980e() {
            T tMo15979d = mo15979d();
            InterfaceC3075c<T> interfaceC3075c = this.f13965e;
            if (interfaceC3075c != null) {
                interfaceC3075c.mo15551a(this.f13966f, tMo15979d);
            }
        }

        public AbstractC3074b(Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, InterfaceC3075c<T> interfaceC3075c, Object obj) {
            this.f13961a = bitmap;
            this.f13962b = effectParam;
            this.f13963c = effectParam2;
            this.f13964d = effectParam3;
            this.f13965e = interfaceC3075c;
            this.f13966f = obj;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$c */
    public interface InterfaceC3075c<T> {
        /* renamed from: a */
        void mo15551a(Object obj, T t8);

        /* renamed from: b */
        void mo15552b(Object obj, String str);
    }

    /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$d */
    public static class C3076d {

        /* renamed from: a */
        public static GLViewEngine f13968a = new GLViewEngine();
    }

    /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$f */
    public class C3078f extends AbstractC3074b<Bitmap> {
        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.AbstractC3074b
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public Bitmap mo15979d() {
            EffectParam effectParam = this.f13962b;
            if (effectParam == null || effectParam.f13950a == null) {
                return null;
            }
            C0936w0 c0936w0M15993c = GLViewEngine.this.f13946c.m15993c(this.f13962b);
            if (this.f13962b.f13956g == EffectParam.ExtraFunc.AutoToneCapture) {
                c0936w0M15993c = GLViewEngine.this.f13946c.m15991a(this.f13962b);
            }
            try {
                return GPUImage.m4144h(this.f13961a, c0936w0M15993c);
            } catch (OutOfMemoryError e9) {
                e9.printStackTrace();
                return null;
            }
        }

        public C3078f(Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, InterfaceC3075c<Bitmap> interfaceC3075c, Object obj) {
            super(bitmap, effectParam, effectParam2, effectParam3, interfaceC3075c, obj);
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$g */
    public class C3079g {

        /* renamed from: a */
        public BlockingQueue<AbstractC3074b<?>> f13974a;

        /* renamed from: b */
        public Thread f13975b;

        /* renamed from: c */
        public boolean f13976c;

        /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$g$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ GLViewEngine f13978b;

            public a(GLViewEngine gLViewEngine) {
                this.f13978b = gLViewEngine;
            }

            @Override // java.lang.Runnable
            public void run() {
                while (!C3079g.this.f13976c) {
                    try {
                        ((AbstractC3074b) C3079g.this.f13974a.take()).m15980e();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        /* renamed from: a */
        public final void m15988a(String str) {
            Log.d("GLViewEngine.TaskMgr", str);
        }

        /* renamed from: f */
        public final synchronized void m15989f(AbstractC3074b<?> abstractC3074b) {
            ArrayList arrayList = new ArrayList();
            for (AbstractC3074b<?> abstractC3074b2 : this.f13974a) {
                if (abstractC3074b2.getClass() == abstractC3074b.getClass()) {
                    arrayList.add(abstractC3074b2);
                    m15988a("Cancel waiting task by pushTask. task class: " + abstractC3074b2.getClass().getSimpleName());
                    abstractC3074b2.m15978c("Cancelled by GLViewEngine pushTask");
                }
            }
            this.f13974a.removeAll(arrayList);
            this.f13974a.offer(abstractC3074b);
        }

        /* renamed from: g */
        public final void m15990g() {
            this.f13976c = true;
            this.f13975b.interrupt();
        }

        public C3079g() {
            this.f13976c = false;
            this.f13974a = new LinkedBlockingQueue();
            Thread thread = new Thread(new a(GLViewEngine.this));
            this.f13975b = thread;
            thread.setName("[GLViewEngine.TaskMgr]");
            this.f13975b.start();
        }
    }

    /* renamed from: g */
    public static GLViewEngine m15970g() {
        return C3076d.f13968a;
    }

    /* renamed from: d */
    public void m15971d(C0913n1 c0913n1, Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, Matrix matrix, InterfaceC3075c<Void> interfaceC3075c, Object obj) {
        if (c0913n1 == null) {
            throw new IllegalArgumentException("GPUImageView cannot be null");
        }
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap cannot be null");
        }
        if (effectParam == null && effectParam2 == null && effectParam3 == null) {
            throw new IllegalArgumentException("EffectParam cannot be null");
        }
        if (matrix == null) {
            throw new IllegalArgumentException("Transform matrix cannot be null");
        }
        this.f13944a.m15989f(new C3077e(c0913n1, bitmap, effectParam, effectParam2, effectParam3, matrix, interfaceC3075c, obj));
    }

    /* renamed from: e */
    public void m15972e(C0913n1 c0913n1, Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, InterfaceC3075c<Void> interfaceC3075c, Object obj) {
        if (c0913n1 == null) {
            throw new IllegalArgumentException("GPUImageView cannot be null");
        }
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap cannot be null");
        }
        if (effectParam == null && effectParam2 == null && effectParam3 == null) {
            throw new IllegalArgumentException("EffectParam cannot be null");
        }
        this.f13944a.m15989f(new C3077e(c0913n1, bitmap, effectParam, effectParam2, effectParam3, interfaceC3075c, obj));
    }

    /* renamed from: f */
    public void m15973f(Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, InterfaceC3075c<Bitmap> interfaceC3075c, Object obj) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap cannot be null");
        }
        if (effectParam == null && effectParam2 == null && effectParam3 == null) {
            throw new IllegalArgumentException("EffectParam cannot be null");
        }
        this.f13944a.m15989f(new C3078f(bitmap, effectParam, effectParam2, effectParam3, interfaceC3075c, obj));
    }

    public void finalize() {
        this.f13944a.m15990g();
    }

    /* renamed from: h */
    public int m15974h(int i9) {
        return i9 + ((int) (i9 * 0.3f));
    }

    /* renamed from: i */
    public void m15975i() {
        this.f13945b = new C3080a();
        this.f13946c = new C3080a();
    }

    /* renamed from: com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine$e */
    public class C3077e extends AbstractC3074b<Void> {

        /* renamed from: h */
        public C0913n1 f13969h;

        /* renamed from: i */
        public final Matrix f13970i;

        /* renamed from: j */
        public boolean f13971j;

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.AbstractC3074b
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public Void mo15979d() {
            EffectParam effectParam;
            DevelopSetting developSetting;
            DevelopSetting developSetting2;
            C0936w0 filter = this.f13969h.getFilter();
            EffectParam effectParam2 = this.f13962b;
            if (effectParam2 == null || effectParam2.f13950a == null) {
                return null;
            }
            C0936w0 c0936w0M15994d = GLViewEngine.this.f13945b.m15994d(this.f13962b, this.f13971j);
            if (this.f13962b.f13956g == EffectParam.ExtraFunc.AutoToneCapture) {
                c0936w0M15994d = GLViewEngine.this.f13945b.m15991a(this.f13962b);
            }
            C0936w0 c0936w0M15995e = c0936w0M15994d;
            if (this.f13970i != null) {
                c0936w0M15995e = GLViewEngine.this.f13945b.m15995e(c0936w0M15995e, this.f13962b.f13951b, this.f13961a.getWidth(), this.f13961a.getHeight(), this.f13970i, Globals.m7388i0().m7510Y(), this.f13962b.f13956g);
                if (this.f13971j) {
                    ((GPUImagePanZoomFilter) c0936w0M15995e).m16018L();
                }
            }
            if (filter == c0936w0M15995e) {
                this.f13969h.requestRender();
                return null;
            }
            EffectParam effectParam3 = this.f13963c;
            if ((effectParam3 == null || (developSetting2 = effectParam3.f13950a) == null || developSetting2.f13894a != DevelopSetting.EffectMode.FOREGROUND) && ((effectParam = this.f13964d) == null || (developSetting = effectParam.f13950a) == null || developSetting.f13894a != DevelopSetting.EffectMode.BACKGROUND)) {
                this.f13969h.m4361b(this.f13961a, true);
                this.f13969h.setFilter(c0936w0M15995e);
                return null;
            }
            this.f13969h.setImage(GLViewEngine.this.f13947d);
            this.f13969h.setFilter(c0936w0M15995e);
            return null;
        }

        public C3077e(C0913n1 c0913n1, Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, InterfaceC3075c<Void> interfaceC3075c, Object obj) {
            super(bitmap, effectParam, effectParam2, effectParam3, interfaceC3075c, obj);
            this.f13969h = c0913n1;
            this.f13970i = null;
        }

        public C3077e(C0913n1 c0913n1, Bitmap bitmap, EffectParam effectParam, EffectParam effectParam2, EffectParam effectParam3, Matrix matrix, InterfaceC3075c<Void> interfaceC3075c, Object obj) {
            super(bitmap, effectParam, effectParam2, effectParam3, interfaceC3075c, obj);
            this.f13969h = c0913n1;
            this.f13970i = new Matrix(matrix);
        }
    }

    public GLViewEngine() {
        this.f13947d = null;
        this.f13948e = 0;
        this.f13949f = null;
        this.f13944a = new C3079g();
        this.f13945b = new C3080a();
        this.f13946c = new C3080a();
    }
}
