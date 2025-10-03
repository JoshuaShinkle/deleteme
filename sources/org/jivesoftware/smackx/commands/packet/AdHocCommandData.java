package org.jivesoftware.smackx.commands.packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.AdHocCommandNote;
import p009a8.C0055a;

/* loaded from: classes.dex */
public class AdHocCommandData extends AbstractC5586IQ {

    /* renamed from: p */
    public String f19665p;

    /* renamed from: q */
    public String f19666q;

    /* renamed from: r */
    public String f19667r;

    /* renamed from: s */
    public String f19668s;

    /* renamed from: u */
    public C0055a f19670u;

    /* renamed from: v */
    public AdHocCommand.Action f19671v;

    /* renamed from: w */
    public AdHocCommand.Status f19672w;

    /* renamed from: y */
    public AdHocCommand.Action f19674y;

    /* renamed from: z */
    public String f19675z;

    /* renamed from: t */
    public List<AdHocCommandNote> f19669t = new ArrayList();

    /* renamed from: x */
    public ArrayList<AdHocCommand.Action> f19673x = new ArrayList<>();

    /* renamed from: org.jivesoftware.smackx.commands.packet.AdHocCommandData$a */
    public static class C5632a implements InterfaceC5595c {

        /* renamed from: a */
        public AdHocCommand.SpecificErrorCondition f19676a;

        public C5632a(AdHocCommand.SpecificErrorCondition specificErrorCondition) {
            this.f19676a = specificErrorCondition;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: b */
        public String mo191b() {
            return this.f19676a.toString();
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public String mo190a() {
            return "<" + mo191b() + " xmlns=\"" + getNamespace() + "\"/>";
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        public String getNamespace() {
            return "http://jabber.org/protocol/commands";
        }
    }

    /* renamed from: G */
    public void m22465G(AdHocCommand.Action action) {
        this.f19673x.add(action);
    }

    /* renamed from: H */
    public void m22466H(AdHocCommandNote adHocCommandNote) {
        this.f19669t.add(adHocCommandNote);
    }

    /* renamed from: I */
    public AdHocCommand.Action m22467I() {
        return this.f19671v;
    }

    /* renamed from: J */
    public List<AdHocCommand.Action> m22468J() {
        return this.f19673x;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<command xmlns=\"http://jabber.org/protocol/commands\"");
        sb.append(" node=\"");
        sb.append(this.f19667r);
        sb.append("\"");
        String str = this.f19668s;
        if (str != null && !str.equals("")) {
            sb.append(" sessionid=\"");
            sb.append(this.f19668s);
            sb.append("\"");
        }
        if (this.f19672w != null) {
            sb.append(" status=\"");
            sb.append(this.f19672w);
            sb.append("\"");
        }
        if (this.f19671v != null) {
            sb.append(" action=\"");
            sb.append(this.f19671v);
            sb.append("\"");
        }
        String str2 = this.f19675z;
        if (str2 != null && !str2.equals("")) {
            sb.append(" lang=\"");
            sb.append(this.f19675z);
            sb.append("\"");
        }
        sb.append(">");
        if (m22066B() == AbstractC5586IQ.a.f19233d) {
            sb.append("<actions");
            if (this.f19674y != null) {
                sb.append(" execute=\"");
                sb.append(this.f19674y);
                sb.append("\"");
            }
            if (this.f19673x.size() == 0) {
                sb.append("/>");
            } else {
                sb.append(">");
                Iterator<AdHocCommand.Action> it = this.f19673x.iterator();
                while (it.hasNext()) {
                    AdHocCommand.Action next = it.next();
                    sb.append("<");
                    sb.append(next);
                    sb.append("/>");
                }
                sb.append("</actions>");
            }
        }
        C0055a c0055a = this.f19670u;
        if (c0055a != null) {
            sb.append((CharSequence) c0055a.mo190a());
        }
        for (AdHocCommandNote adHocCommandNote : this.f19669t) {
            sb.append("<note type=\"");
            sb.append(adHocCommandNote.m22463a().toString());
            sb.append("\">");
            sb.append(adHocCommandNote.m22464b());
            sb.append("</note>");
        }
        sb.append("</command>");
        return sb.toString();
    }

    /* renamed from: L */
    public AdHocCommand.Action m22470L() {
        return this.f19674y;
    }

    /* renamed from: M */
    public C0055a m22471M() {
        return this.f19670u;
    }

    /* renamed from: N */
    public String m22472N() {
        return this.f19667r;
    }

    /* renamed from: O */
    public String m22473O() {
        return this.f19668s;
    }

    /* renamed from: P */
    public void m22474P(AdHocCommand.Action action) {
        this.f19671v = action;
    }

    /* renamed from: Q */
    public void m22475Q(AdHocCommand.Action action) {
        this.f19674y = action;
    }

    /* renamed from: R */
    public void m22476R(C0055a c0055a) {
        this.f19670u = c0055a;
    }

    /* renamed from: S */
    public void m22477S(String str) {
        this.f19665p = str;
    }

    /* renamed from: T */
    public void m22478T(String str) {
        this.f19666q = str;
    }

    /* renamed from: U */
    public void m22479U(String str) {
        this.f19667r = str;
    }

    /* renamed from: V */
    public void m22480V(String str) {
        this.f19668s = str;
    }

    /* renamed from: W */
    public void m22481W(AdHocCommand.Status status) {
        this.f19672w = status;
    }
}
