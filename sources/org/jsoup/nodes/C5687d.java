package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;
import p080g8.AbstractC4974c;

/* renamed from: org.jsoup.nodes.d */
/* loaded from: classes.dex */
public class C5687d extends AbstractC4974c {
    public C5687d(String str) {
        this.f17075d = str;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: A */
    public void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings) {
    }

    /* renamed from: T */
    public String m22930T() {
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
        return "#comment";
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: z */
    public void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.m22840k()) {
            m22955t(appendable, i9, outputSettings);
        }
        appendable.append("<!--").append(m22930T()).append("-->");
    }
}
