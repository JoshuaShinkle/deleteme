package p209u2;

import android.view.View;

/* renamed from: u2.b */
/* loaded from: classes.dex */
public class C6365b {

    /* renamed from: a */
    public static long f21503a;

    /* renamed from: u2.b$a */
    public static abstract class a<Listener> {

        /* renamed from: b */
        public final Listener f21504b;

        /* renamed from: c */
        public final int f21505c;

        /* renamed from: d */
        public final long f21506d;

        /* renamed from: e */
        public long f21507e = 0;

        public a(Listener listener, int i9, long j9) {
            this.f21504b = listener;
            this.f21505c = i9;
            this.f21506d = j9;
        }

        /* renamed from: a */
        public final long m24455a() {
            return this.f21505c == 0 ? C6365b.f21503a : this.f21507e;
        }

        /* renamed from: b */
        public final void m24456b(long j9) {
            if (this.f21505c == 0) {
                long unused = C6365b.f21503a = j9;
            } else {
                this.f21507e = j9;
            }
        }

        /* renamed from: c */
        public boolean m24457c() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean z8 = jCurrentTimeMillis - m24455a() <= this.f21506d;
            if (!z8) {
                m24456b(jCurrentTimeMillis);
            }
            return z8;
        }
    }

    /* renamed from: u2.b$b */
    public static class b extends a<View.OnClickListener> implements View.OnClickListener {
        public b(View.OnClickListener onClickListener, int i9, long j9) {
            super(onClickListener, i9, j9);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (m24457c()) {
                return;
            }
            ((View.OnClickListener) this.f21504b).onClick(view);
        }
    }

    /* renamed from: c */
    public static View.OnClickListener m24452c(View.OnClickListener onClickListener) {
        return m24453d(onClickListener, 0);
    }

    /* renamed from: d */
    public static View.OnClickListener m24453d(View.OnClickListener onClickListener, int i9) {
        return m24454e(onClickListener, i9, 700L);
    }

    /* renamed from: e */
    public static View.OnClickListener m24454e(View.OnClickListener onClickListener, int i9, long j9) {
        return new b(onClickListener, i9, j9);
    }
}
