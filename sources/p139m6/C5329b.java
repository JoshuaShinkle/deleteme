package p139m6;

import com.google.android.gms.appinvite.PreviewActivity;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ProtocolException;
import kotlin.text.C5255l;
import okhttp3.AbstractC5483a0;
import okhttp3.AbstractC5524y;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5519t;
import okhttp3.internal.connection.C5497c;
import okhttp3.internal.http2.ConnectionShutdownException;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.C6313a;
import p204t6.C6331l;
import p204t6.InterfaceC6323d;

/* renamed from: m6.b */
/* loaded from: classes.dex */
public final class C5329b implements InterfaceC5519t {

    /* renamed from: a */
    public final boolean f18162a;

    public C5329b(boolean z8) {
        this.f18162a = z8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e0 A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:39:0x00a9, B:41:0x00b2, B:42:0x00b6, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x013a, B:56:0x0148, B:63:0x015e, B:65:0x0164, B:69:0x0171, B:71:0x018b, B:72:0x0193, B:73:0x019d, B:58:0x0153, B:53:0x012a), top: B:87:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012a A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:39:0x00a9, B:41:0x00b2, B:42:0x00b6, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x013a, B:56:0x0148, B:63:0x015e, B:65:0x0164, B:69:0x0171, B:71:0x018b, B:72:0x0193, B:73:0x019d, B:58:0x0153, B:53:0x012a), top: B:87:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0153 A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:39:0x00a9, B:41:0x00b2, B:42:0x00b6, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x013a, B:56:0x0148, B:63:0x015e, B:65:0x0164, B:69:0x0171, B:71:0x018b, B:72:0x0193, B:73:0x019d, B:58:0x0153, B:53:0x012a), top: B:87:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x015e A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:39:0x00a9, B:41:0x00b2, B:42:0x00b6, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x013a, B:56:0x0148, B:63:0x015e, B:65:0x0164, B:69:0x0171, B:71:0x018b, B:72:0x0193, B:73:0x019d, B:58:0x0153, B:53:0x012a), top: B:87:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0164 A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:39:0x00a9, B:41:0x00b2, B:42:0x00b6, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x013a, B:56:0x0148, B:63:0x015e, B:65:0x0164, B:69:0x0171, B:71:0x018b, B:72:0x0193, B:73:0x019d, B:58:0x0153, B:53:0x012a), top: B:87:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0171 A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:39:0x00a9, B:41:0x00b2, B:42:0x00b6, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x013a, B:56:0x0148, B:63:0x015e, B:65:0x0164, B:69:0x0171, B:71:0x018b, B:72:0x0193, B:73:0x019d, B:58:0x0153, B:53:0x012a), top: B:87:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v21, types: [okhttp3.z$a] */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    @Override // okhttp3.InterfaceC5519t
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C5525z mo20323a(InterfaceC5519t.a aVar) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        boolean z8;
        ?? M20953a;
        C5525z.a aVarM21326q;
        int iM21853x;
        C5525z c5525zM21858c;
        AbstractC5483a0 abstractC5483a0M21849f;
        Object obj;
        C0042f.m158e(aVar, "chain");
        C5334g c5334g = (C5334g) aVar;
        C5497c c5497cM20963g = c5334g.m20963g();
        C0042f.m155b(c5497cM20963g);
        C5523x c5523xM20965i = c5334g.m20965i();
        AbstractC5524y abstractC5524yM21803a = c5523xM20965i.m21803a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            c5497cM20963g.m21330u(c5523xM20965i);
            M20953a = C5333f.m20953a(c5523xM20965i.m21809g());
            try {
                if (M20953a == 0 || abstractC5524yM21803a == null) {
                    c5497cM20963g.m21324o();
                    z8 = true;
                    M20953a = 0;
                } else {
                    if (C5255l.m20513l("100-continue", c5523xM20965i.m21806d(HttpHeaders.EXPECT), true)) {
                        c5497cM20963g.m21315f();
                        M20953a = c5497cM20963g.m21326q(true);
                        try {
                            c5497cM20963g.m21328s();
                            z8 = false;
                            obj = M20953a;
                        } catch (IOException e9) {
                            e = e9;
                            z8 = true;
                            if (e instanceof ConnectionShutdownException) {
                                throw e;
                            }
                            aVarM21326q = M20953a;
                            if (!c5497cM20963g.m21320k()) {
                                throw e;
                            }
                            if (aVarM21326q == null) {
                            }
                            C5525z c5525zM21858c2 = aVarM21326q.m21873r(c5523xM20965i).m21864i(c5497cM20963g.m21317h().m21293r()).m21874s(jCurrentTimeMillis).m21872q(System.currentTimeMillis()).m21858c();
                            iM21853x = c5525zM21858c2.m21853x();
                            if (m20933b(iM21853x)) {
                            }
                            c5497cM20963g.m21327r(c5525zM21858c2);
                            if (this.f18162a) {
                            }
                            if (!C5255l.m20513l(PreviewActivity.ON_CLICK_LISTENER_CLOSE, c5525zM21858c.m21847K().m21806d(HttpHeaders.CONNECTION), true)) {
                                c5497cM20963g.m21323n();
                            }
                            if (iM21853x != 204) {
                                abstractC5483a0M21849f = c5525zM21858c.m21849f();
                                if ((abstractC5483a0M21849f == null ? abstractC5483a0M21849f.mo20968v() : -1L) > 0) {
                                }
                            }
                            return c5525zM21858c;
                        }
                    } else {
                        z8 = true;
                        obj = null;
                    }
                    if (obj != null) {
                        c5497cM20963g.m21324o();
                        M20953a = obj;
                        if (!c5497cM20963g.m21317h().m21297v()) {
                            c5497cM20963g.m21323n();
                            M20953a = obj;
                        }
                    } else if (abstractC5524yM21803a.m21825f()) {
                        c5497cM20963g.m21315f();
                        abstractC5524yM21803a.mo21717h(C6331l.m24255a(c5497cM20963g.m21312c(c5523xM20965i, true)));
                        M20953a = obj;
                    } else {
                        InterfaceC6323d interfaceC6323dM24255a = C6331l.m24255a(c5497cM20963g.m21312c(c5523xM20965i, false));
                        abstractC5524yM21803a.mo21717h(interfaceC6323dM24255a);
                        interfaceC6323dM24255a.close();
                        M20953a = obj;
                    }
                }
                if (abstractC5524yM21803a == null || !abstractC5524yM21803a.m21825f()) {
                    c5497cM20963g.m21314e();
                }
                e = null;
                aVarM21326q = M20953a;
            } catch (IOException e10) {
                e = e10;
            }
        } catch (IOException e11) {
            e = e11;
            z8 = true;
            M20953a = 0;
        }
        if (aVarM21326q == null) {
            try {
                aVarM21326q = c5497cM20963g.m21326q(false);
                C0042f.m155b(aVarM21326q);
                if (z8) {
                    c5497cM20963g.m21328s();
                    z8 = false;
                }
            } catch (IOException e12) {
                if (e == null) {
                    throw e12;
                }
                C6313a.m24147a(e, e12);
                throw e;
            }
        }
        C5525z c5525zM21858c22 = aVarM21326q.m21873r(c5523xM20965i).m21864i(c5497cM20963g.m21317h().m21293r()).m21874s(jCurrentTimeMillis).m21872q(System.currentTimeMillis()).m21858c();
        iM21853x = c5525zM21858c22.m21853x();
        if (m20933b(iM21853x)) {
            C5525z.a aVarM21326q2 = c5497cM20963g.m21326q(false);
            C0042f.m155b(aVarM21326q2);
            if (z8) {
                c5497cM20963g.m21328s();
            }
            c5525zM21858c22 = aVarM21326q2.m21873r(c5523xM20965i).m21864i(c5497cM20963g.m21317h().m21293r()).m21874s(jCurrentTimeMillis).m21872q(System.currentTimeMillis()).m21858c();
            iM21853x = c5525zM21858c22.m21853x();
        }
        c5497cM20963g.m21327r(c5525zM21858c22);
        c5525zM21858c = (this.f18162a || iM21853x != 101) ? c5525zM21858c22.m21843G().m21857b(c5497cM20963g.m21325p(c5525zM21858c22)).m21858c() : c5525zM21858c22.m21843G().m21857b(C5057d.f17445c).m21858c();
        if (!C5255l.m20513l(PreviewActivity.ON_CLICK_LISTENER_CLOSE, c5525zM21858c.m21847K().m21806d(HttpHeaders.CONNECTION), true) || C5255l.m20513l(PreviewActivity.ON_CLICK_LISTENER_CLOSE, C5525z.m21837B(c5525zM21858c, HttpHeaders.CONNECTION, null, 2, null), true)) {
            c5497cM20963g.m21323n();
        }
        if (iM21853x != 204 || iM21853x == 205) {
            abstractC5483a0M21849f = c5525zM21858c.m21849f();
            if ((abstractC5483a0M21849f == null ? abstractC5483a0M21849f.mo20968v() : -1L) > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("HTTP ");
                sb.append(iM21853x);
                sb.append(" had non-zero Content-Length: ");
                AbstractC5483a0 abstractC5483a0M21849f2 = c5525zM21858c.m21849f();
                sb.append(abstractC5483a0M21849f2 != null ? Long.valueOf(abstractC5483a0M21849f2.mo20968v()) : null);
                throw new ProtocolException(sb.toString());
            }
        }
        return c5525zM21858c;
    }

    /* renamed from: b */
    public final boolean m20933b(int i9) {
        if (i9 == 100) {
            return true;
        }
        return 102 <= i9 && i9 < 200;
    }
}
