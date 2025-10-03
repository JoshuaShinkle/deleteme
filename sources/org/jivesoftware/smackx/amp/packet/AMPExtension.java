package org.jivesoftware.smackx.amp.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* loaded from: classes.dex */
public class AMPExtension implements InterfaceC5595c {

    /* renamed from: a */
    public CopyOnWriteArrayList<C5620b> f19543a = new CopyOnWriteArrayList<>();

    /* renamed from: b */
    public boolean f19544b = false;

    /* renamed from: c */
    public final String f19545c;

    /* renamed from: d */
    public final String f19546d;

    /* renamed from: e */
    public final Status f19547e;

    public enum Action {
        alert,
        drop,
        error,
        notify
    }

    public enum Status {
        alert,
        error,
        notify
    }

    /* renamed from: org.jivesoftware.smackx.amp.packet.AMPExtension$a */
    public interface InterfaceC5619a {
        String getName();

        String getValue();
    }

    /* renamed from: org.jivesoftware.smackx.amp.packet.AMPExtension$b */
    public static class C5620b {

        /* renamed from: a */
        public final Action f19557a;

        /* renamed from: b */
        public final InterfaceC5619a f19558b;

        public C5620b(Action action, InterfaceC5619a interfaceC5619a) {
            if (action == null) {
                throw new NullPointerException("Can't create Rule with null action");
            }
            if (interfaceC5619a == null) {
                throw new NullPointerException("Can't create Rule with null condition");
            }
            this.f19557a = action;
            this.f19558b = interfaceC5619a;
        }

        /* renamed from: b */
        public final String m22378b() {
            return "<rule action=\"" + this.f19557a.toString() + "\" condition=\"" + this.f19558b.getName() + "\" value=\"" + this.f19558b.getValue() + "\"/>";
        }
    }

    public AMPExtension(String str, String str2, Status status) {
        this.f19545c = str;
        this.f19546d = str2;
        this.f19547e = status;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "amp";
    }

    /* renamed from: c */
    public void m22373c(C5620b c5620b) {
        this.f19543a.add(c5620b);
    }

    /* renamed from: d */
    public Collection<C5620b> m22374d() {
        return Collections.unmodifiableList(new ArrayList(this.f19543a));
    }

    /* renamed from: e */
    public synchronized void m22375e(boolean z8) {
        this.f19544b = z8;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\"");
        if (this.f19547e != null) {
            sb.append(" status=\"");
            sb.append(this.f19547e.toString());
            sb.append("\"");
        }
        if (this.f19546d != null) {
            sb.append(" to=\"");
            sb.append(this.f19546d);
            sb.append("\"");
        }
        if (this.f19545c != null) {
            sb.append(" from=\"");
            sb.append(this.f19545c);
            sb.append("\"");
        }
        if (this.f19544b) {
            sb.append(" per-hop=\"true\"");
        }
        sb.append(">");
        Iterator<C5620b> it = m22374d().iterator();
        while (it.hasNext()) {
            sb.append(it.next().m22378b());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/amp";
    }
}
