package org.jsoup.nodes;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import p060e8.C4771c;
import p060e8.C4772d;
import p080g8.AbstractC4974c;

/* renamed from: org.jsoup.nodes.f */
/* loaded from: classes.dex */
public class C5689f extends AbstractC4974c {
    public C5689f(String str, String str2, String str3) {
        C4772d.m19004j(str);
        C4772d.m19004j(str2);
        C4772d.m19004j(str3);
        mo19256d(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        mo19256d("publicId", str2);
        if (m22932T("publicId")) {
            mo19256d("pubSysKey", "PUBLIC");
        }
        mo19256d("systemId", str3);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: A */
    public void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings) {
    }

    /* renamed from: T */
    public final boolean m22932T(String str) {
        return !C4771c.m18983e(mo19255c(str));
    }

    /* renamed from: U */
    public void m22933U(String str) {
        if (str != null) {
            mo19256d("pubSysKey", str);
        }
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
    /* renamed from: d */
    public /* bridge */ /* synthetic */ AbstractC5690g mo19256d(String str, String str2) {
        return super.mo19256d(str, str2);
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
    /* renamed from: v */
    public String mo22827v() {
        return "#doctype";
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: z */
    public void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.m22841l() != Document.OutputSettings.Syntax.html || m22932T("publicId") || m22932T("systemId")) {
            appendable.append("<!DOCTYPE");
        } else {
            appendable.append("<!doctype");
        }
        if (m22932T(AppMeasurementSdk.ConditionalUserProperty.NAME)) {
            appendable.append(StringUtils.SPACE).append(mo19255c(AppMeasurementSdk.ConditionalUserProperty.NAME));
        }
        if (m22932T("pubSysKey")) {
            appendable.append(StringUtils.SPACE).append(mo19255c("pubSysKey"));
        }
        if (m22932T("publicId")) {
            appendable.append(" \"").append(mo19255c("publicId")).append('\"');
        }
        if (m22932T("systemId")) {
            appendable.append(" \"").append(mo19255c("systemId")).append('\"');
        }
        appendable.append('>');
    }
}
