package p240x6;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.util.C5616j;

/* renamed from: x6.c */
/* loaded from: classes.dex */
public class C6578c implements InterfaceC6582g {

    /* renamed from: g */
    public static final Logger f22121g = Logger.getLogger(C6578c.class.getName());

    /* renamed from: a */
    public final InterfaceC6582g f22122a;

    /* renamed from: b */
    public final C6580e f22123b;

    /* renamed from: c */
    public final String f22124c;

    /* renamed from: d */
    public final String f22125d;

    /* renamed from: e */
    public final String f22126e;

    /* renamed from: f */
    public final String f22127f;

    public C6578c(AbstractC5586IQ abstractC5586IQ, XMPPConnection xMPPConnection) {
        String strM22162l = abstractC5586IQ.m22162l();
        this.f22124c = strM22162l;
        if (xMPPConnection.mo21967D() == null) {
            this.f22125d = null;
        } else {
            this.f22125d = xMPPConnection.mo21967D().toLowerCase(Locale.US);
        }
        String strM21966C = xMPPConnection.m21966C();
        Locale locale = Locale.US;
        String lowerCase = strM21966C.toLowerCase(locale);
        this.f22126e = lowerCase;
        this.f22127f = abstractC5586IQ.m22161k();
        this.f22122a = new C6576a(new C6580e(new C6579d(AbstractC5586IQ.a.f19234e), new C6579d(AbstractC5586IQ.a.f19233d)), new C6583h(abstractC5586IQ));
        C6580e c6580e = new C6580e();
        this.f22123b = c6580e;
        c6580e.m25195b(C6577b.m25194c(strM22162l));
        if (strM22162l == null) {
            String str = this.f22125d;
            if (str != null) {
                c6580e.m25195b(C6577b.m25193b(str));
            }
            c6580e.m25195b(C6577b.m25194c(lowerCase));
            return;
        }
        if (this.f22125d == null || !strM22162l.toLowerCase(locale).equals(C5616j.m22345j(this.f22125d))) {
            return;
        }
        c6580e.m25195b(C6577b.m25194c(null));
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        if (!this.f22122a.mo25192a(abstractC5594b)) {
            return false;
        }
        if (this.f22123b.mo25192a(abstractC5594b)) {
            return true;
        }
        f22121g.log(Level.WARNING, String.format("Rejected potentially spoofed reply to IQ-packet. Filter settings: packetId=%s, to=%s, local=%s, server=%s. Received packet with from=%s", this.f22127f, this.f22124c, this.f22125d, this.f22126e, abstractC5594b.m22160j()), abstractC5594b);
        return false;
    }
}
