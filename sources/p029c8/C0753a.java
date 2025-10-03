package p029c8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: c8.a */
/* loaded from: classes.dex */
public class C0753a implements InterfaceC5595c {

    /* renamed from: a */
    public List<String> f3501a = new ArrayList();

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "html";
    }

    /* renamed from: c */
    public void m3635c(String str) {
        synchronized (this.f3501a) {
            this.f3501a.add(str);
        }
    }

    /* renamed from: d */
    public List<String> m3636d() {
        List<String> listUnmodifiableList;
        synchronized (this.f3501a) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f3501a));
        }
        return listUnmodifiableList;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        Iterator<String> it = m3636d().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/xhtml-im";
    }
}
