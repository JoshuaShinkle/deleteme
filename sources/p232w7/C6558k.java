package p232w7;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smackx.pubsub.PubSubElementType;
import org.jivesoftware.smackx.pubsub.Subscription;

/* renamed from: w7.k */
/* loaded from: classes.dex */
public class C6558k extends C6554g {

    /* renamed from: c */
    public List<Subscription> f22062c;

    public C6558k(String str, List<Subscription> list) {
        super(PubSubElementType.f19864v, str);
        this.f22062c = Collections.emptyList();
        if (list != null) {
            this.f22062c = list;
        }
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        List<Subscription> list = this.f22062c;
        if (list == null || list.size() == 0) {
            return super.mo190a();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(mo191b());
        if (m25121c() != null) {
            sb.append(" node='");
            sb.append(m25121c());
            sb.append("'");
        }
        sb.append(">");
        Iterator<Subscription> it = this.f22062c.iterator();
        while (it.hasNext()) {
            sb.append(it.next().mo190a());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }
}
