package p148n6;

import android.support.v4.media.session.PlaybackStateCompat;
import okhttp3.C5517r;
import p007a6.C0040d;
import p007a6.C0042f;
import p204t6.InterfaceC6324e;

/* renamed from: n6.a */
/* loaded from: classes.dex */
public final class C5373a {

    /* renamed from: c */
    public static final a f18252c = new a(null);

    /* renamed from: a */
    public final InterfaceC6324e f18253a;

    /* renamed from: b */
    public long f18254b;

    /* renamed from: n6.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    public C5373a(InterfaceC6324e interfaceC6324e) {
        C0042f.m158e(interfaceC6324e, "source");
        this.f18253a = interfaceC6324e;
        this.f18254b = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
    }

    /* renamed from: a */
    public final C5517r m21055a() {
        C5517r.a aVar = new C5517r.a();
        while (true) {
            String strM21056b = m21056b();
            if (strM21056b.length() == 0) {
                return aVar.m21635e();
            }
            aVar.m21632b(strM21056b);
        }
    }

    /* renamed from: b */
    public final String m21056b() {
        String strMo24220i = this.f18253a.mo24220i(this.f18254b);
        this.f18254b -= strMo24220i.length();
        return strMo24220i;
    }
}
