package p250y7;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: y7.b */
/* loaded from: classes.dex */
public class C6794b implements InterfaceC5595c {

    /* renamed from: a */
    public Collection<C6793a> f22533a;

    public C6794b(Collection<C6793a> collection) {
        this.f22533a = Collections.emptyList();
        if (collection != null) {
            this.f22533a = collection;
        }
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "headers";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder("<" + mo191b() + " xmlns='" + getNamespace() + "'>");
        Iterator<C6793a> it = this.f22533a.iterator();
        while (it.hasNext()) {
            sb.append(it.next().mo190a());
        }
        sb.append("</" + mo191b() + '>');
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/shim";
    }
}
