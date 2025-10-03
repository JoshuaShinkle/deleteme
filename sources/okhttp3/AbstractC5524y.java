package okhttp3;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import kotlin.text.C5246c;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p204t6.C6331l;
import p204t6.InterfaceC6323d;
import p204t6.InterfaceC6342w;
import p248y5.C6791a;

/* renamed from: okhttp3.y */
/* loaded from: classes2.dex */
public abstract class AbstractC5524y {

    /* renamed from: a */
    public static final a f19061a = new a(null);

    /* renamed from: okhttp3.y$a */
    public static final class a {

        /* renamed from: okhttp3.y$a$a, reason: collision with other inner class name */
        public static final class C6879a extends AbstractC5524y {

            /* renamed from: b */
            public final /* synthetic */ C5520u f19062b;

            /* renamed from: c */
            public final /* synthetic */ File f19063c;

            public C6879a(C5520u c5520u, File file) {
                this.f19062b = c5520u;
                this.f19063c = file;
            }

            @Override // okhttp3.AbstractC5524y
            /* renamed from: a */
            public long mo21715a() {
                return this.f19063c.length();
            }

            @Override // okhttp3.AbstractC5524y
            /* renamed from: b */
            public C5520u mo21716b() {
                return this.f19062b;
            }

            @Override // okhttp3.AbstractC5524y
            /* renamed from: h */
            public void mo21717h(InterfaceC6323d interfaceC6323d) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
                C0042f.m158e(interfaceC6323d, "sink");
                InterfaceC6342w interfaceC6342wM24259e = C6331l.m24259e(this.f19063c);
                try {
                    interfaceC6323d.mo24219h(interfaceC6342wM24259e);
                    C6791a.m25347a(interfaceC6342wM24259e, null);
                } finally {
                }
            }
        }

        /* renamed from: okhttp3.y$a$b */
        public static final class b extends AbstractC5524y {

            /* renamed from: b */
            public final /* synthetic */ C5520u f19064b;

            /* renamed from: c */
            public final /* synthetic */ int f19065c;

            /* renamed from: d */
            public final /* synthetic */ byte[] f19066d;

            /* renamed from: e */
            public final /* synthetic */ int f19067e;

            public b(C5520u c5520u, int i9, byte[] bArr, int i10) {
                this.f19064b = c5520u;
                this.f19065c = i9;
                this.f19066d = bArr;
                this.f19067e = i10;
            }

            @Override // okhttp3.AbstractC5524y
            /* renamed from: a */
            public long mo21715a() {
                return this.f19065c;
            }

            @Override // okhttp3.AbstractC5524y
            /* renamed from: b */
            public C5520u mo21716b() {
                return this.f19064b;
            }

            @Override // okhttp3.AbstractC5524y
            /* renamed from: h */
            public void mo21717h(InterfaceC6323d interfaceC6323d) {
                C0042f.m158e(interfaceC6323d, "sink");
                interfaceC6323d.write(this.f19066d, this.f19067e, this.f19065c);
            }
        }

        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: h */
        public static /* synthetic */ AbstractC5524y m21827h(a aVar, String str, C5520u c5520u, int i9, Object obj) {
            if ((i9 & 1) != 0) {
                c5520u = null;
            }
            return aVar.m21831b(str, c5520u);
        }

        /* renamed from: i */
        public static /* synthetic */ AbstractC5524y m21828i(a aVar, C5520u c5520u, byte[] bArr, int i9, int i10, int i11, Object obj) {
            if ((i11 & 4) != 0) {
                i9 = 0;
            }
            if ((i11 & 8) != 0) {
                i10 = bArr.length;
            }
            return aVar.m21835f(c5520u, bArr, i9, i10);
        }

        /* renamed from: j */
        public static /* synthetic */ AbstractC5524y m21829j(a aVar, byte[] bArr, C5520u c5520u, int i9, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                c5520u = null;
            }
            if ((i11 & 2) != 0) {
                i9 = 0;
            }
            if ((i11 & 4) != 0) {
                i10 = bArr.length;
            }
            return aVar.m21836g(bArr, c5520u, i9, i10);
        }

        /* renamed from: a */
        public final AbstractC5524y m21830a(File file, C5520u c5520u) {
            C0042f.m158e(file, "<this>");
            return new C6879a(c5520u, file);
        }

        /* renamed from: b */
        public final AbstractC5524y m21831b(String str, C5520u c5520u) {
            C0042f.m158e(str, "<this>");
            Charset charset = C5246c.f17846b;
            if (c5520u != null) {
                Charset charsetM21708d = C5520u.m21708d(c5520u, null, 1, null);
                if (charsetM21708d == null) {
                    c5520u = C5520u.f18962e.m21714b(c5520u + "; charset=utf-8");
                } else {
                    charset = charsetM21708d;
                }
            }
            byte[] bytes = str.getBytes(charset);
            C0042f.m157d(bytes, "this as java.lang.String).getBytes(charset)");
            return m21836g(bytes, c5520u, 0, bytes.length);
        }

        /* renamed from: c */
        public final AbstractC5524y m21832c(C5520u c5520u, File file) {
            C0042f.m158e(file, "file");
            return m21830a(file, c5520u);
        }

        /* renamed from: d */
        public final AbstractC5524y m21833d(C5520u c5520u, String str) {
            C0042f.m158e(str, FirebaseAnalytics.Param.CONTENT);
            return m21831b(str, c5520u);
        }

        /* renamed from: e */
        public final AbstractC5524y m21834e(C5520u c5520u, byte[] bArr) {
            C0042f.m158e(bArr, FirebaseAnalytics.Param.CONTENT);
            return m21828i(this, c5520u, bArr, 0, 0, 12, null);
        }

        /* renamed from: f */
        public final AbstractC5524y m21835f(C5520u c5520u, byte[] bArr, int i9, int i10) {
            C0042f.m158e(bArr, FirebaseAnalytics.Param.CONTENT);
            return m21836g(bArr, c5520u, i9, i10);
        }

        /* renamed from: g */
        public final AbstractC5524y m21836g(byte[] bArr, C5520u c5520u, int i9, int i10) {
            C0042f.m158e(bArr, "<this>");
            C5057d.m19798l(bArr.length, i9, i10);
            return new b(c5520u, i10, bArr, i9);
        }
    }

    /* renamed from: c */
    public static final AbstractC5524y m21822c(C5520u c5520u, File file) {
        return f19061a.m21832c(c5520u, file);
    }

    /* renamed from: d */
    public static final AbstractC5524y m21823d(C5520u c5520u, String str) {
        return f19061a.m21833d(c5520u, str);
    }

    /* renamed from: e */
    public static final AbstractC5524y m21824e(C5520u c5520u, byte[] bArr) {
        return f19061a.m21834e(c5520u, bArr);
    }

    /* renamed from: a */
    public abstract long mo21715a();

    /* renamed from: b */
    public abstract C5520u mo21716b();

    /* renamed from: f */
    public boolean m21825f() {
        return false;
    }

    /* renamed from: g */
    public boolean m21826g() {
        return false;
    }

    /* renamed from: h */
    public abstract void mo21717h(InterfaceC6323d interfaceC6323d);
}
