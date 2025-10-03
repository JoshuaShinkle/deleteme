package p232w7;

import org.jivesoftware.smackx.pubsub.FormNodeType;
import p259z7.C6837a;

/* renamed from: w7.e */
/* loaded from: classes.dex */
public class C6552e extends C6554g {

    /* renamed from: c */
    public C6837a f22053c;

    public C6552e(FormNodeType formNodeType, String str, C6837a c6837a) {
        super(formNodeType.m22682a(), str);
        if (c6837a == null) {
            throw new IllegalArgumentException("Submit form cannot be null");
        }
        this.f22053c = c6837a;
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        if (this.f22053c == null) {
            return super.mo190a();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(mo191b());
        if (m25121c() != null) {
            sb.append(" node='");
            sb.append(m25121c());
            sb.append("'>");
        } else {
            sb.append('>');
        }
        sb.append((CharSequence) this.f22053c.m25544a().mo190a());
        sb.append("</");
        sb.append(mo191b() + '>');
        return sb.toString();
    }
}
