package p022c1;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: c1.j */
/* loaded from: classes.dex */
public class C0713j implements InterfaceC0707d {

    /* renamed from: k */
    public static final Bitmap.Config f3376k = Bitmap.Config.ARGB_8888;

    /* renamed from: a */
    public final InterfaceC0714k f3377a;

    /* renamed from: b */
    public final Set<Bitmap.Config> f3378b;

    /* renamed from: c */
    public final long f3379c;

    /* renamed from: d */
    public final a f3380d;

    /* renamed from: e */
    public long f3381e;

    /* renamed from: f */
    public long f3382f;

    /* renamed from: g */
    public int f3383g;

    /* renamed from: h */
    public int f3384h;

    /* renamed from: i */
    public int f3385i;

    /* renamed from: j */
    public int f3386j;

    /* renamed from: c1.j$a */
    public interface a {
        /* renamed from: a */
        void mo3531a(Bitmap bitmap);

        /* renamed from: b */
        void mo3532b(Bitmap bitmap);
    }

    /* renamed from: c1.j$b */
    public static final class b implements a {
        @Override // p022c1.C0713j.a
        /* renamed from: a */
        public void mo3531a(Bitmap bitmap) {
        }

        @Override // p022c1.C0713j.a
        /* renamed from: b */
        public void mo3532b(Bitmap bitmap) {
        }
    }

    public C0713j(long j9, InterfaceC0714k interfaceC0714k, Set<Bitmap.Config> set) {
        this.f3379c = j9;
        this.f3381e = j9;
        this.f3377a = interfaceC0714k;
        this.f3378b = set;
        this.f3380d = new b();
    }

    @TargetApi(26)
    /* renamed from: f */
    public static void m3519f(Bitmap.Config config) {
        if (config != Bitmap.Config.HARDWARE) {
            return;
        }
        throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
    }

    /* renamed from: g */
    public static Bitmap m3520g(int i9, int i10, Bitmap.Config config) {
        if (config == null) {
            config = f3376k;
        }
        return Bitmap.createBitmap(i9, i10, config);
    }

    @TargetApi(26)
    /* renamed from: k */
    public static Set<Bitmap.Config> m3521k() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add(null);
        hashSet.remove(Bitmap.Config.HARDWARE);
        return Collections.unmodifiableSet(hashSet);
    }

    /* renamed from: l */
    public static InterfaceC0714k m3522l() {
        return new C0716m();
    }

    @TargetApi(19)
    /* renamed from: o */
    public static void m3523o(Bitmap bitmap) {
        bitmap.setPremultiplied(true);
    }

    /* renamed from: p */
    public static void m3524p(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        m3523o(bitmap);
    }

    @Override // p022c1.InterfaceC0707d
    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public void mo3485a(int i9) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "trimMemory, level=" + i9);
        }
        if (i9 >= 40) {
            mo3486b();
        } else if (i9 >= 20 || i9 == 15) {
            m3530q(m3529n() / 2);
        }
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: b */
    public void mo3486b() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        m3530q(0L);
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: c */
    public synchronized void mo3487c(Bitmap bitmap) {
        try {
            if (bitmap == null) {
                throw new NullPointerException("Bitmap must not be null");
            }
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            if (bitmap.isMutable() && this.f3377a.mo3534b(bitmap) <= this.f3381e && this.f3378b.contains(bitmap.getConfig())) {
                int iMo3534b = this.f3377a.mo3534b(bitmap);
                this.f3377a.mo3535c(bitmap);
                this.f3380d.mo3532b(bitmap);
                this.f3385i++;
                this.f3382f += iMo3534b;
                if (Log.isLoggable("LruBitmapPool", 2)) {
                    Log.v("LruBitmapPool", "Put bitmap in pool=" + this.f3377a.mo3537e(bitmap));
                }
                m3525h();
                m3527j();
                return;
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                Log.v("LruBitmapPool", "Reject bitmap from pool, bitmap: " + this.f3377a.mo3537e(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.f3378b.contains(bitmap.getConfig()));
            }
            bitmap.recycle();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: d */
    public Bitmap mo3488d(int i9, int i10, Bitmap.Config config) {
        Bitmap bitmapM3528m = m3528m(i9, i10, config);
        if (bitmapM3528m == null) {
            return m3520g(i9, i10, config);
        }
        bitmapM3528m.eraseColor(0);
        return bitmapM3528m;
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: e */
    public Bitmap mo3489e(int i9, int i10, Bitmap.Config config) {
        Bitmap bitmapM3528m = m3528m(i9, i10, config);
        return bitmapM3528m == null ? m3520g(i9, i10, config) : bitmapM3528m;
    }

    /* renamed from: h */
    public final void m3525h() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            m3526i();
        }
    }

    /* renamed from: i */
    public final void m3526i() {
        Log.v("LruBitmapPool", "Hits=" + this.f3383g + ", misses=" + this.f3384h + ", puts=" + this.f3385i + ", evictions=" + this.f3386j + ", currentSize=" + this.f3382f + ", maxSize=" + this.f3381e + "\nStrategy=" + this.f3377a);
    }

    /* renamed from: j */
    public final void m3527j() {
        m3530q(this.f3381e);
    }

    /* renamed from: m */
    public final synchronized Bitmap m3528m(int i9, int i10, Bitmap.Config config) {
        Bitmap bitmapMo3536d;
        m3519f(config);
        bitmapMo3536d = this.f3377a.mo3536d(i9, i10, config != null ? config : f3376k);
        if (bitmapMo3536d == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Missing bitmap=" + this.f3377a.mo3533a(i9, i10, config));
            }
            this.f3384h++;
        } else {
            this.f3383g++;
            this.f3382f -= this.f3377a.mo3534b(bitmapMo3536d);
            this.f3380d.mo3531a(bitmapMo3536d);
            m3524p(bitmapMo3536d);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            Log.v("LruBitmapPool", "Get bitmap=" + this.f3377a.mo3533a(i9, i10, config));
        }
        m3525h();
        return bitmapMo3536d;
    }

    /* renamed from: n */
    public long m3529n() {
        return this.f3381e;
    }

    /* renamed from: q */
    public final synchronized void m3530q(long j9) {
        while (this.f3382f > j9) {
            Bitmap bitmapRemoveLast = this.f3377a.removeLast();
            if (bitmapRemoveLast == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    m3526i();
                }
                this.f3382f = 0L;
                return;
            }
            this.f3380d.mo3531a(bitmapRemoveLast);
            this.f3382f -= this.f3377a.mo3534b(bitmapRemoveLast);
            this.f3386j++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Evicting bitmap=" + this.f3377a.mo3537e(bitmapRemoveLast));
            }
            m3525h();
            bitmapRemoveLast.recycle();
        }
    }

    public C0713j(long j9) {
        this(j9, m3522l(), m3521k());
    }
}
