package p232w7;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smackx.pubsub.Affiliation;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

/* renamed from: w7.a */
/* loaded from: classes.dex */
public class C6548a extends C6554g {

    /* renamed from: c */
    public List<Affiliation> f22049c;

    public C6548a(List<Affiliation> list) {
        super(PubSubElementType.f19861s);
        Collections.emptyList();
        this.f22049c = list;
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        List<Affiliation> list = this.f22049c;
        if (list == null || list.size() == 0) {
            return super.mo190a();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(mo191b());
        sb.append(">");
        Iterator<Affiliation> it = this.f22049c.iterator();
        while (it.hasNext()) {
            sb.append(it.next().mo190a());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }
}
