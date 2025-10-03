package org.jsoup.nodes;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.nodes.Document;
import p060e8.C4772d;
import p080g8.AbstractC4974c;

/* renamed from: org.jsoup.nodes.j */
/* loaded from: classes.dex */
public class C5693j extends AbstractC4974c {

    /* renamed from: f */
    public final boolean f20017f;

    public C5693j(String str, boolean z8) {
        C4772d.m19004j(str);
        this.f17075d = str;
        this.f20017f = z8;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: A */
    public void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings) {
    }

    /* renamed from: T */
    public final void m22961T(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        Iterator<C5684a> it = mo19257e().iterator();
        while (it.hasNext()) {
            C5684a next = it.next();
            if (!next.getKey().equals(mo22827v())) {
                appendable.append(' ');
                next.m22905e(appendable, outputSettings);
            }
        }
    }

    /* renamed from: U */
    public String m22962U() {
        return m19252R();
    }

    @Override // p080g8.AbstractC4974c, org.jsoup.nodes.AbstractC5690g
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String mo19254a(String str) {
        return super.mo19254a(str);
    }

    @Override // p080g8.AbstractC4974c, org.jsoup.nodes.AbstractC5690g
    /* renamed from: c */
    public /* bridge */ /* synthetic */ String mo19255c(String str) {
        return super.mo19255c(str);
    }

    @Override // p080g8.AbstractC4974c, org.jsoup.nodes.AbstractC5690g
    /* renamed from: g */
    public /* bridge */ /* synthetic */ String mo19258g() {
        return super.mo19258g();
    }

    @Override // p080g8.AbstractC4974c, org.jsoup.nodes.AbstractC5690g
    /* renamed from: j */
    public /* bridge */ /* synthetic */ int mo19259j() {
        return super.mo19259j();
    }

    @Override // p080g8.AbstractC4974c, org.jsoup.nodes.AbstractC5690g
    /* renamed from: q */
    public /* bridge */ /* synthetic */ boolean mo19262q(String str) {
        return super.mo19262q(str);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    public String toString() {
        return mo22828x();
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: v */
    public String mo22827v() {
        return "#declaration";
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: z */
    public void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("<").append(this.f20017f ? "!" : "?").append(m19252R());
        m22961T(appendable, outputSettings);
        appendable.append(this.f20017f ? "!" : "?").append(">");
    }
}
