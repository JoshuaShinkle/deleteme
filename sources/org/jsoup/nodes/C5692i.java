package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;
import p060e8.C4771c;
import p080g8.AbstractC4974c;

/* renamed from: org.jsoup.nodes.i */
/* loaded from: classes.dex */
public class C5692i extends AbstractC4974c {
    public C5692i(String str) {
        this.f17075d = str;
    }

    /* renamed from: V */
    public static boolean m22958V(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ';
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: A */
    public void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings) {
    }

    /* renamed from: T */
    public String m22959T() {
        return m19252R();
    }

    /* renamed from: U */
    public boolean m22960U() {
        return C4771c.m18983e(m19252R());
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
        return "#text";
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: z */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.m22840k()) {
            if (m22946O() == 0) {
                AbstractC5690g abstractC5690g = this.f20011b;
                if (!(abstractC5690g instanceof Element) || !((Element) abstractC5690g).m22873u0().m19653a() || m22960U()) {
                    if (outputSettings.m22838i() && m22947P().size() > 0 && !m22960U()) {
                        m22955t(appendable, i9, outputSettings);
                    }
                }
            }
        }
        Entities.m22883e(appendable, m19252R(), outputSettings, false, outputSettings.m22840k() && (m22935C() instanceof Element) && !Element.m22848q0(m22935C()), false);
    }
}
