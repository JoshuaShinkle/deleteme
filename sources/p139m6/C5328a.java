package p139m6;

import com.google.common.net.HttpHeaders;
import java.util.List;
import kotlin.collections.C5226i;
import kotlin.text.C5255l;
import okhttp3.AbstractC5483a0;
import okhttp3.AbstractC5524y;
import okhttp3.C5511l;
import okhttp3.C5520u;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5512m;
import okhttp3.InterfaceC5519t;
import p007a6.C0042f;
import p098i6.C5057d;
import p204t6.C6328i;
import p204t6.C6331l;

/* renamed from: m6.a */
/* loaded from: classes.dex */
public final class C5328a implements InterfaceC5519t {

    /* renamed from: a */
    public final InterfaceC5512m f18161a;

    public C5328a(InterfaceC5512m interfaceC5512m) {
        C0042f.m158e(interfaceC5512m, "cookieJar");
        this.f18161a = interfaceC5512m;
    }

    @Override // okhttp3.InterfaceC5519t
    /* renamed from: a */
    public C5525z mo20323a(InterfaceC5519t.a aVar) {
        AbstractC5483a0 abstractC5483a0M21849f;
        C0042f.m158e(aVar, "chain");
        C5523x c5523xMo20959b = aVar.mo20959b();
        C5523x.a aVarM21810h = c5523xMo20959b.m21810h();
        AbstractC5524y abstractC5524yM21803a = c5523xMo20959b.m21803a();
        if (abstractC5524yM21803a != null) {
            C5520u c5520uMo21716b = abstractC5524yM21803a.mo21716b();
            if (c5520uMo21716b != null) {
                aVarM21810h.m21815d(HttpHeaders.CONTENT_TYPE, c5520uMo21716b.toString());
            }
            long jMo21715a = abstractC5524yM21803a.mo21715a();
            if (jMo21715a != -1) {
                aVarM21810h.m21815d(HttpHeaders.CONTENT_LENGTH, String.valueOf(jMo21715a));
                aVarM21810h.m21819h(HttpHeaders.TRANSFER_ENCODING);
            } else {
                aVarM21810h.m21815d(HttpHeaders.TRANSFER_ENCODING, "chunked");
                aVarM21810h.m21819h(HttpHeaders.CONTENT_LENGTH);
            }
        }
        boolean z8 = false;
        if (c5523xMo20959b.m21806d(HttpHeaders.HOST) == null) {
            aVarM21810h.m21815d(HttpHeaders.HOST, C5057d.m19778R(c5523xMo20959b.m21811i(), false, 1, null));
        }
        if (c5523xMo20959b.m21806d(HttpHeaders.CONNECTION) == null) {
            aVarM21810h.m21815d(HttpHeaders.CONNECTION, "Keep-Alive");
        }
        if (c5523xMo20959b.m21806d(HttpHeaders.ACCEPT_ENCODING) == null && c5523xMo20959b.m21806d(HttpHeaders.RANGE) == null) {
            aVarM21810h.m21815d(HttpHeaders.ACCEPT_ENCODING, "gzip");
            z8 = true;
        }
        List<C5511l> listMo21586b = this.f18161a.mo21586b(c5523xMo20959b.m21811i());
        if (!listMo21586b.isEmpty()) {
            aVarM21810h.m21815d(HttpHeaders.COOKIE, m20932b(listMo21586b));
        }
        if (c5523xMo20959b.m21806d(HttpHeaders.USER_AGENT) == null) {
            aVarM21810h.m21815d(HttpHeaders.USER_AGENT, "okhttp/4.12.0");
        }
        C5525z c5525zMo20958a = aVar.mo20958a(aVarM21810h.m21813b());
        C5332e.m20950f(this.f18161a, c5523xMo20959b.m21811i(), c5525zMo20958a.m21839C());
        C5525z.a aVarM21873r = c5525zMo20958a.m21843G().m21873r(c5523xMo20959b);
        if (z8 && C5255l.m20513l("gzip", C5525z.m21837B(c5525zMo20958a, HttpHeaders.CONTENT_ENCODING, null, 2, null), true) && C5332e.m20946b(c5525zMo20958a) && (abstractC5483a0M21849f = c5525zMo20958a.m21849f()) != null) {
            C6328i c6328i = new C6328i(abstractC5483a0M21849f.mo20970x());
            aVarM21873r.m21866k(c5525zMo20958a.m21839C().m21628c().m21637g(HttpHeaders.CONTENT_ENCODING).m21637g(HttpHeaders.CONTENT_LENGTH).m21635e());
            aVarM21873r.m21857b(new C5335h(C5525z.m21837B(c5525zMo20958a, HttpHeaders.CONTENT_TYPE, null, 2, null), -1L, C6331l.m24256b(c6328i)));
        }
        return aVarM21873r.m21858c();
    }

    /* renamed from: b */
    public final String m20932b(List<C5511l> list) {
        StringBuilder sb = new StringBuilder();
        int i9 = 0;
        for (Object obj : list) {
            int i10 = i9 + 1;
            if (i9 < 0) {
                C5226i.m20407m();
            }
            C5511l c5511l = (C5511l) obj;
            if (i9 > 0) {
                sb.append("; ");
            }
            sb.append(c5511l.m21574e());
            sb.append('=');
            sb.append(c5511l.m21576g());
            i9 = i10;
        }
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
