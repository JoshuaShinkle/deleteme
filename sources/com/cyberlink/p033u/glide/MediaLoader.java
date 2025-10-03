package com.cyberlink.p033u.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.C1387b;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import p012b1.AbstractC0588c;
import p103j1.C5085r;
import p116k4.C5154j;
import p173q2.C6128b;
import p173q2.C6132f;
import p173q2.C6134h;
import p173q2.C6136j;
import p173q2.C6143q;
import p173q2.RunnableC6137k;
import p173q2.RunnableC6140n;
import p173q2.RunnableC6141o;
import p173q2.RunnableC6142p;
import p190s1.InterfaceC6249f;
import p190s1.InterfaceFutureC6245b;
import p199t1.AbstractC6281f;
import p199t1.InterfaceC6283h;
import p208u1.InterfaceC6362b;
import p209u2.AbstractC6378o;
import p209u2.AbstractC6381r;
import p209u2.C6385v;

/* loaded from: classes.dex */
public final class MediaLoader {

    /* renamed from: a */
    public static final String f7094a = "MediaLoader";

    public enum Option {
        THUMBNAIL,
        ROUNDED_THUMBNAIL,
        ORIGINAL_PREFER_CACHE,
        ROUNDED_ORIGINAL_PREFER_CACHE,
        ORIGINAL
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$a */
    public class AsyncTaskC1377a extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final /* synthetic */ Context f7101a;

        /* renamed from: b */
        public final /* synthetic */ C1387b f7102b;

        /* renamed from: c */
        public final /* synthetic */ ImageView f7103c;

        public AsyncTaskC1377a(Context context, C1387b c1387b, ImageView imageView) {
            this.f7101a = context;
            this.f7102b = c1387b;
            this.f7103c = imageView;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            C6134h<Bitmap> c6134hM23547F = C6132f.m23492b(this.f7101a).mo23575d().mo23569s(this.f7102b.f7145d).m23545D(AbstractC0588c.f3107a).m23547F(this.f7102b.f7142a);
            if (this.f7102b.f7143b) {
                c6134hM23547F.m23564W(new C5085r(C6128b.f20758a));
            }
            try {
                return c6134hM23547F.m24428w().get();
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                this.f7103c.setImageBitmap(bitmap);
            }
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$b */
    public class C1378b extends AbstractC6378o<Void, Void, Void> {

        /* renamed from: d */
        public final /* synthetic */ AbstractC6378o f7104d;

        /* renamed from: e */
        public final /* synthetic */ Context f7105e;

        /* renamed from: f */
        public final /* synthetic */ ImageView f7106f;

        /* renamed from: g */
        public final /* synthetic */ C1387b f7107g;

        public C1378b(AbstractC6378o abstractC6378o, Context context, ImageView imageView, C1387b c1387b) {
            this.f7104d = abstractC6378o;
            this.f7105e = context;
            this.f7106f = imageView;
            this.f7107g = c1387b;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r12) {
            AbstractC6378o abstractC6378o = this.f7104d;
            if (abstractC6378o != null) {
                abstractC6378o.m24505c();
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r12) {
            AbstractC6378o abstractC6378o = this.f7104d;
            if (abstractC6378o != null) {
                abstractC6378o.m24507e();
            }
        }

        @Override // p209u2.AbstractC6378o
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void m24498l(Void r42) {
            AbstractC6378o abstractC6378o = this.f7104d;
            if (abstractC6378o != null) {
                abstractC6378o.m24499n();
            }
            MediaLoader.m7158q(this.f7105e, this.f7106f, this.f7107g, this.f7104d);
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$c */
    public class C1379c implements InterfaceC6249f<Drawable> {

        /* renamed from: b */
        public final /* synthetic */ Object f7108b;

        /* renamed from: c */
        public final /* synthetic */ AbstractC6378o f7109c;

        public C1379c(Object obj, AbstractC6378o abstractC6378o) {
            this.f7108b = obj;
            this.f7109c = abstractC6378o;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean mo7171d(Drawable drawable, Object obj, InterfaceC6283h<Drawable> interfaceC6283h, DataSource dataSource, boolean z8) {
            AbstractC6378o abstractC6378o;
            if (this.f7108b.equals(obj) && (abstractC6378o = this.f7109c) != null) {
                Objects.requireNonNull(abstractC6378o);
                C6385v.m24524b(new RunnableC6140n(abstractC6378o));
            }
            return false;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: c */
        public boolean mo7170c(GlideException glideException, Object obj, InterfaceC6283h<Drawable> interfaceC6283h, boolean z8) {
            AbstractC6378o abstractC6378o;
            if (this.f7108b.equals(obj) && (abstractC6378o = this.f7109c) != null) {
                Objects.requireNonNull(abstractC6378o);
                C6385v.m24524b(new RunnableC6141o(abstractC6378o));
            }
            return false;
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$d */
    public class AsyncTaskC1380d extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final /* synthetic */ Context f7110a;

        /* renamed from: b */
        public final /* synthetic */ C1387b f7111b;

        /* renamed from: c */
        public final /* synthetic */ AtomicBoolean f7112c;

        /* renamed from: d */
        public final /* synthetic */ ImageView f7113d;

        public AsyncTaskC1380d(Context context, C1387b c1387b, AtomicBoolean atomicBoolean, ImageView imageView) {
            this.f7110a = context;
            this.f7111b = c1387b;
            this.f7112c = atomicBoolean;
            this.f7113d = imageView;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            try {
                return C6132f.m23492b(this.f7110a).mo23575d().mo23569s(this.f7111b.f7145d).m23545D(AbstractC0588c.f3107a).m23547F(this.f7111b.f7142a).m24428w().get();
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null || this.f7112c.get()) {
                return;
            }
            this.f7113d.setImageBitmap(bitmap);
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$e */
    public class AsyncTaskC1381e extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final /* synthetic */ Context f7114a;

        /* renamed from: b */
        public final /* synthetic */ C1387b f7115b;

        /* renamed from: c */
        public final /* synthetic */ AtomicBoolean f7116c;

        /* renamed from: d */
        public final /* synthetic */ ImageView f7117d;

        /* renamed from: e */
        public final /* synthetic */ AbstractC6378o f7118e;

        public AsyncTaskC1381e(Context context, C1387b c1387b, AtomicBoolean atomicBoolean, ImageView imageView, AbstractC6378o abstractC6378o) {
            this.f7114a = context;
            this.f7115b = c1387b;
            this.f7116c = atomicBoolean;
            this.f7117d = imageView;
            this.f7118e = abstractC6378o;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            try {
                return C6132f.m23492b(this.f7114a).mo23575d().mo23569s(this.f7115b.f7144c).m23545D(AbstractC0588c.f3107a).m24428w().get();
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                AbstractC6378o abstractC6378o = this.f7118e;
                if (abstractC6378o != null) {
                    Objects.requireNonNull(abstractC6378o);
                    C6385v.m24524b(new RunnableC6141o(abstractC6378o));
                    return;
                }
                return;
            }
            this.f7116c.set(true);
            this.f7117d.setImageBitmap(bitmap);
            AbstractC6378o abstractC6378o2 = this.f7118e;
            if (abstractC6378o2 != null) {
                Objects.requireNonNull(abstractC6378o2);
                C6385v.m24524b(new RunnableC6140n(abstractC6378o2));
            }
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$f */
    public class C1382f implements InterfaceC6249f<Drawable> {

        /* renamed from: b */
        public int f7119b = 0;

        /* renamed from: c */
        public final /* synthetic */ Object f7120c;

        /* renamed from: d */
        public final /* synthetic */ AbstractC6378o f7121d;

        public C1382f(Object obj, AbstractC6378o abstractC6378o) {
            this.f7120c = obj;
            this.f7121d = abstractC6378o;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean mo7171d(Drawable drawable, Object obj, InterfaceC6283h<Drawable> interfaceC6283h, DataSource dataSource, boolean z8) {
            AbstractC6378o abstractC6378o;
            if (!this.f7120c.equals(obj) || (abstractC6378o = this.f7121d) == null) {
                return false;
            }
            Objects.requireNonNull(abstractC6378o);
            C6385v.m24524b(new RunnableC6140n(abstractC6378o));
            return false;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: c */
        public boolean mo7170c(GlideException glideException, Object obj, InterfaceC6283h<Drawable> interfaceC6283h, boolean z8) {
            AbstractC6378o abstractC6378o;
            if (this.f7120c.equals(obj) && (abstractC6378o = this.f7121d) != null) {
                Objects.requireNonNull(abstractC6378o);
                C6385v.m24524b(new RunnableC6142p(abstractC6378o));
            }
            int i9 = this.f7119b + 1;
            this.f7119b = i9;
            return i9 < 2;
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$g */
    public class AsyncTaskC1383g extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final /* synthetic */ Context f7122a;

        /* renamed from: b */
        public final /* synthetic */ C1387b f7123b;

        /* renamed from: c */
        public final /* synthetic */ AtomicBoolean f7124c;

        /* renamed from: d */
        public final /* synthetic */ ImageView f7125d;

        public AsyncTaskC1383g(Context context, C1387b c1387b, AtomicBoolean atomicBoolean, ImageView imageView) {
            this.f7122a = context;
            this.f7123b = c1387b;
            this.f7124c = atomicBoolean;
            this.f7125d = imageView;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            C6134h<Bitmap> c6134hM23547F = C6132f.m23492b(this.f7122a).mo23575d().mo23569s(this.f7123b.f7145d).m23545D(AbstractC0588c.f3107a).m23547F(this.f7123b.f7142a);
            if (this.f7123b.f7143b) {
                c6134hM23547F.m23564W(new C5085r(C6128b.f20758a));
            }
            try {
                return c6134hM23547F.m24428w().get();
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null || this.f7124c.get()) {
                return;
            }
            this.f7125d.setImageBitmap(bitmap);
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$h */
    public class AsyncTaskC1384h extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final /* synthetic */ Context f7126a;

        /* renamed from: b */
        public final /* synthetic */ C1387b f7127b;

        /* renamed from: c */
        public final /* synthetic */ AtomicBoolean f7128c;

        /* renamed from: d */
        public final /* synthetic */ ImageView f7129d;

        /* renamed from: e */
        public final /* synthetic */ AbstractC6378o f7130e;

        public AsyncTaskC1384h(Context context, C1387b c1387b, AtomicBoolean atomicBoolean, ImageView imageView, AbstractC6378o abstractC6378o) {
            this.f7126a = context;
            this.f7127b = c1387b;
            this.f7128c = atomicBoolean;
            this.f7129d = imageView;
            this.f7130e = abstractC6378o;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            C6134h<Bitmap> c6134hM23557P = C6132f.m23492b(this.f7126a).mo23575d().mo23569s(this.f7127b.f7144c).m23545D(AbstractC0588c.f3107a).m23557P(true);
            if (this.f7127b.f7143b) {
                c6134hM23557P.m23564W(new C5085r(C6128b.f20758a));
            }
            try {
                return c6134hM23557P.m24428w().get();
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                AbstractC6378o abstractC6378o = this.f7130e;
                if (abstractC6378o != null) {
                    Objects.requireNonNull(abstractC6378o);
                    C6385v.m24524b(new RunnableC6142p(abstractC6378o));
                    return;
                }
                return;
            }
            this.f7128c.set(true);
            this.f7129d.setImageBitmap(bitmap);
            AbstractC6378o abstractC6378o2 = this.f7130e;
            if (abstractC6378o2 != null) {
                Objects.requireNonNull(abstractC6378o2);
                C6385v.m24524b(new RunnableC6140n(abstractC6378o2));
            }
        }
    }

    /* renamed from: com.cyberlink.u.glide.MediaLoader$i */
    public class C1385i extends AbstractC6281f<Bitmap> {

        /* renamed from: e */
        public final /* synthetic */ boolean f7131e;

        /* renamed from: f */
        public final /* synthetic */ AbstractC6378o f7132f;

        /* renamed from: g */
        public final /* synthetic */ Context f7133g;

        /* renamed from: h */
        public final /* synthetic */ C1387b f7134h;

        /* renamed from: com.cyberlink.u.glide.MediaLoader$i$a */
        public class a extends AbstractC6281f<Bitmap> {
            public a() {
            }

            @Override // p199t1.InterfaceC6283h
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void mo7183f(Bitmap bitmap, InterfaceC6362b<? super Bitmap> interfaceC6362b) {
                C1385i.this.f7132f.m24506d(bitmap);
            }

            @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
            /* renamed from: e */
            public void mo7182e(Drawable drawable) {
                C1385i.this.f7132f.m24507e();
            }
        }

        public C1385i(boolean z8, AbstractC6378o abstractC6378o, Context context, C1387b c1387b) {
            this.f7131e = z8;
            this.f7132f = abstractC6378o;
            this.f7133g = context;
            this.f7134h = c1387b;
        }

        @Override // p199t1.InterfaceC6283h
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo7183f(Bitmap bitmap, InterfaceC6362b<? super Bitmap> interfaceC6362b) {
            this.f7132f.m24506d(bitmap);
        }

        @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
        /* renamed from: e */
        public void mo7182e(Drawable drawable) {
            if (this.f7131e) {
                this.f7132f.m24507e();
            } else {
                this.f7132f.m24499n();
                C6132f.m23492b(this.f7133g).mo23575d().mo23569s(this.f7134h.f7144c).m23544C().m23545D(AbstractC0588c.f3107a).m23546E(DownsampleStrategy.f3882e).m23559R(1280).m24419k(new a());
            }
        }
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: d */
    public static Bitmap m7145d(Context context, C2973l0 c2973l0, boolean z8) {
        C1387b c1387bM7148g;
        try {
            c1387bM7148g = m7148g(context, c2973l0, false);
        } catch (IllegalArgumentException unused) {
            if (TextUtils.isEmpty(c2973l0.m15148t().f13200d)) {
                c2973l0.m15148t().f13200d = c2973l0.m15149u().f13200d;
                C1387b c1387bM7148g2 = m7148g(context, c2973l0, false);
                c2973l0.m15148t().f13200d = null;
                c1387bM7148g = c1387bM7148g2;
            } else {
                c1387bM7148g = null;
            }
        }
        if (c1387bM7148g == null) {
            return null;
        }
        C6134h<Bitmap> c6134hM23555N = z8 ? C6132f.m23492b(context).mo23575d().mo23569s(c1387bM7148g.f7145d) : C6132f.m23492b(context).mo23575d().mo23569s(c1387bM7148g.f7144c);
        c6134hM23555N.m23544C().m23545D(AbstractC0588c.f3107a).m23546E(DownsampleStrategy.f3882e).m23559R(1280);
        try {
            return c6134hM23555N.m24428w().get();
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: e */
    public static void m7146e(Context context, C2973l0 c2973l0, boolean z8, AbstractC6378o<Bitmap, Void, Void> abstractC6378o) {
        C1387b c1387bM7148g;
        if (abstractC6378o == null) {
            return;
        }
        try {
            c1387bM7148g = m7148g(context, c2973l0, false);
        } catch (IllegalArgumentException unused) {
            c2973l0.m15148t().f13200d = c2973l0.m15149u().f13200d;
            C1387b c1387bM7148g2 = m7148g(context, c2973l0, false);
            c2973l0.m15148t().f13200d = null;
            c1387bM7148g = c1387bM7148g2;
        }
        if (c1387bM7148g == null) {
            abstractC6378o.m24507e();
        } else {
            (z8 ? C6132f.m23492b(context).mo23575d().mo23569s(c1387bM7148g.f7145d) : C6132f.m23492b(context).mo23575d().mo23569s(c1387bM7148g.f7144c).m23557P(true)).m23544C().m23545D(AbstractC0588c.f3107a).m23546E(DownsampleStrategy.f3882e).m23559R(1280).m24419k(new C1385i(z8, abstractC6378o, context, c1387bM7148g));
        }
    }

    /* renamed from: f */
    public static C1387b m7147f(Context context, ImageView imageView, C2973l0 c2973l0, boolean z8) {
        if (context == null || imageView == null || c2973l0 == null || C6143q.m23608a(context)) {
            return null;
        }
        return m7148g(context, c2973l0, z8);
    }

    /* renamed from: g */
    public static C1387b m7148g(Context context, C2973l0 c2973l0, boolean z8) {
        if (context == null || c2973l0 == null || C6143q.m23608a(context)) {
            return null;
        }
        C1387b c1387bM7194a = new C1387b.b(c2973l0).m7195b(R.drawable.icon_photo_default).m7200g(z8).m7194a();
        if (c1387bM7194a.m7193c() && !c1387bM7194a.m7192b()) {
            throw new IllegalArgumentException("Specify original instead of thumbnail");
        }
        if (c1387bM7194a.m7191a()) {
            return null;
        }
        return c1387bM7194a;
    }

    /* renamed from: h */
    public static void m7149h(Context context, ImageView... imageViewArr) {
        C6136j.m23591k(context, imageViewArr);
    }

    /* renamed from: i */
    public static void m7150i(Context context, C2973l0 c2973l0, boolean z8) {
        m7151j(context, c2973l0, z8, null);
    }

    /* renamed from: j */
    public static void m7151j(Context context, C2973l0 c2973l0, boolean z8, final AbstractC6381r<File, Void> abstractC6381r) {
        C1387b c1387bM7148g;
        try {
            c1387bM7148g = m7148g(context, c2973l0, false);
        } catch (IllegalArgumentException unused) {
            if (z8 && TextUtils.isEmpty(c2973l0.m15148t().f13200d)) {
                c2973l0.m15148t().f13200d = c2973l0.m15149u().f13200d;
                C1387b c1387bM7148g2 = m7148g(context, c2973l0, false);
                c2973l0.m15148t().f13200d = null;
                c1387bM7148g = c1387bM7148g2;
            } else {
                c1387bM7148g = null;
            }
        }
        if (c1387bM7148g != null) {
            final InterfaceFutureC6245b<File> interfaceFutureC6245bM24428w = (z8 ? C6132f.m23492b(context).m23573B(c1387bM7148g.f7145d).m23545D(AbstractC0588c.f3107a).m23551J() : C6132f.m23492b(context).m23573B(c1387bM7148g.f7144c).m23545D(AbstractC0588c.f3107a).m23551J()).m23562U(Priority.HIGH).m24428w();
            C6385v.m24526d(new Runnable() { // from class: q2.l
                @Override // java.lang.Runnable
                public final void run() {
                    MediaLoader.m7154m(interfaceFutureC6245bM24428w, abstractC6381r);
                }
            });
        } else if (abstractC6381r != null) {
            C6385v.m24527e(new RunnableC6137k(abstractC6381r));
        }
    }

    /* renamed from: k */
    public static boolean m7152k(C1387b c1387b) {
        int i9;
        int i10 = c1387b.f7147f;
        if (i10 == 0 || (i9 = c1387b.f7146e) == 0) {
            return false;
        }
        return (i10 > i9 ? (((float) i10) * 1.0f) / ((float) i9) : (((float) i9) * 1.0f) / ((float) i10)) > 10.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: m */
    public static /* synthetic */ void m7154m(InterfaceFutureC6245b interfaceFutureC6245b, final AbstractC6381r abstractC6381r) {
        try {
            final File file = (File) interfaceFutureC6245b.get();
            if (abstractC6381r != null) {
                C6385v.m24527e(new Runnable() { // from class: q2.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        abstractC6381r.m24506d(file);
                    }
                });
            }
        } catch (Exception unused) {
            if (abstractC6381r != null) {
                C6385v.m24527e(new RunnableC6137k(abstractC6381r));
            }
        }
    }

    /* renamed from: n */
    public static void m7155n(Context context, ImageView imageView, C1387b c1387b, Option option) {
        if (Option.THUMBNAIL == option || Option.ROUNDED_THUMBNAIL == option) {
            m7162u(context, imageView, c1387b);
            return;
        }
        if (Option.ORIGINAL_PREFER_CACHE == option || Option.ROUNDED_ORIGINAL_PREFER_CACHE == option) {
            m7160s(context, imageView, c1387b, null);
        } else {
            if (Option.ORIGINAL == option) {
                m7157p(context, imageView, c1387b, null);
                return;
            }
            throw new IllegalArgumentException("No implementation: " + option);
        }
    }

    /* renamed from: o */
    public static void m7156o(Context context, ImageView imageView, C2973l0 c2973l0, Option option) {
        C1387b c1387bM7147f;
        boolean z8 = Option.ROUNDED_THUMBNAIL == option || Option.ROUNDED_ORIGINAL_PREFER_CACHE == option;
        try {
            c1387bM7147f = m7147f(context, imageView, c2973l0, z8);
        } catch (IllegalArgumentException unused) {
            if (TextUtils.isEmpty(c2973l0.m15148t().f13200d)) {
                c2973l0.m15148t().f13200d = c2973l0.m15149u().f13200d;
                c1387bM7147f = m7147f(context, imageView, c2973l0, z8);
                c2973l0.m15148t().f13200d = null;
            } else {
                c1387bM7147f = null;
            }
        }
        if (c1387bM7147f != null) {
            m7155n(context, imageView, c1387bM7147f, option);
        } else if (CLUtility.m16613z1(c2973l0.m15149u().f13201e, null)) {
            C6136j.m23599s(context, imageView, c2973l0.m15149u().f13201e);
        } else {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            C6136j.m23592l(context, imageView, R.drawable.icon_photo_default);
        }
    }

    /* renamed from: p */
    public static void m7157p(Context context, ImageView imageView, C1387b c1387b, AbstractC6378o<Void, Void, Void> abstractC6378o) {
        m7160s(context, imageView, c1387b, new C1378b(abstractC6378o, context, imageView, c1387b));
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: q */
    public static void m7158q(Context context, ImageView imageView, C1387b c1387b, AbstractC6378o<Void, Void, Void> abstractC6378o) {
        if (!c1387b.m7193c()) {
            C5154j.m20075i("[showOriginalInternal] Have no thumbnail : " + c1387b.f7148g);
            ULogUtility.m16676l(f7094a, "[showOriginalInternal] Have no thumbnail : " + c1387b.f7148g);
        }
        if (m7152k(c1387b)) {
            m7159r(context, imageView, c1387b, abstractC6378o);
            return;
        }
        C6134h<Drawable> c6134hM23573B = C6132f.m23492b(context).m23573B(c1387b.f7145d);
        AbstractC0588c abstractC0588c = AbstractC0588c.f3107a;
        C6132f.m23492b(context).m23573B(c1387b.f7144c).m23545D(abstractC0588c).m23563V(c6134hM23573B.m23545D(abstractC0588c).m23547F(c1387b.f7142a)).m23552K(new C1379c(c1387b.f7144c, abstractC6378o)).m24422n(imageView);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: r */
    public static void m7159r(Context context, ImageView imageView, C1387b c1387b, AbstractC6378o<Void, Void, Void> abstractC6378o) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AsyncTaskC1380d asyncTaskC1380d = new AsyncTaskC1380d(context, c1387b, atomicBoolean, imageView);
        ExecutorService executorService = C6385v.f21553a;
        asyncTaskC1380d.executeOnExecutor(executorService, new Void[0]);
        new AsyncTaskC1381e(context, c1387b, atomicBoolean, imageView, abstractC6378o).executeOnExecutor(executorService, new Void[0]);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: s */
    public static void m7160s(Context context, ImageView imageView, C1387b c1387b, AbstractC6378o<Void, Void, Void> abstractC6378o) {
        if (!c1387b.m7193c()) {
            C5154j.m20075i("[showOriginalPreferCache] Have no thumbnail : " + c1387b.f7148g);
            ULogUtility.m16676l(f7094a, "[showOriginalPreferCache] Have no thumbnail : " + c1387b.f7148g);
        }
        if (m7152k(c1387b)) {
            m7161t(context, imageView, c1387b, abstractC6378o);
            return;
        }
        C6134h<Drawable> c6134hM23573B = C6132f.m23492b(context).m23573B(c1387b.f7145d);
        AbstractC0588c abstractC0588c = AbstractC0588c.f3107a;
        C6134h<Drawable> c6134hM23547F = c6134hM23573B.m23545D(abstractC0588c).m23547F(c1387b.f7142a);
        if (c1387b.f7143b) {
            c6134hM23547F.m23564W(new C5085r(C6128b.f20758a));
        }
        C6134h<Drawable> c6134hM23552K = C6132f.m23492b(context).m23573B(c1387b.f7144c).m23545D(abstractC0588c).m23557P(true).m23563V(c6134hM23547F).m23548G(c6134hM23547F).m23552K(new C1382f(c1387b.f7144c, abstractC6378o));
        if (c1387b.f7143b) {
            c6134hM23552K.m23564W(new C5085r(C6128b.f20758a));
        }
        c6134hM23552K.m24422n(imageView);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: t */
    public static void m7161t(Context context, ImageView imageView, C1387b c1387b, AbstractC6378o<Void, Void, Void> abstractC6378o) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AsyncTaskC1383g asyncTaskC1383g = new AsyncTaskC1383g(context, c1387b, atomicBoolean, imageView);
        ExecutorService executorService = C6385v.f21553a;
        asyncTaskC1383g.executeOnExecutor(executorService, new Void[0]);
        new AsyncTaskC1384h(context, c1387b, atomicBoolean, imageView, abstractC6378o).executeOnExecutor(executorService, new Void[0]);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: u */
    public static void m7162u(Context context, ImageView imageView, C1387b c1387b) {
        if (!c1387b.m7193c()) {
            C5154j.m20075i("[showThumbnail] Have no thumbnail : " + c1387b.f7148g);
            ULogUtility.m16676l(f7094a, "[showThumbnail] Have no thumbnail : " + c1387b.f7148g);
        }
        if (m7152k(c1387b)) {
            new AsyncTaskC1377a(context, c1387b, imageView).executeOnExecutor(C6385v.f21553a, new Void[0]);
            return;
        }
        C6134h<Drawable> c6134hM23547F = C6132f.m23492b(context).m23573B(c1387b.f7145d).m23545D(AbstractC0588c.f3107a).m23547F(c1387b.f7142a);
        if (c1387b.f7143b) {
            c6134hM23547F.m23564W(new C5085r(C6128b.f20758a));
        }
        c6134hM23547F.m24422n(imageView);
    }
}
