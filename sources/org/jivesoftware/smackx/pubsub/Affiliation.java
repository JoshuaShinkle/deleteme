package org.jivesoftware.smackx.pubsub;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* loaded from: classes.dex */
public class Affiliation implements InterfaceC5595c {

    /* renamed from: a */
    public String f19818a;

    /* renamed from: b */
    public Type f19819b;

    public enum Type {
        member,
        none,
        outcast,
        owner,
        publisher
    }

    public Affiliation(String str, Type type) {
        this.f19818a = str;
        this.f19819b = type;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "subscription";
    }

    /* renamed from: c */
    public final void m22679c(StringBuilder sb, String str, String str2) {
        sb.append(StringUtils.SPACE);
        sb.append(str);
        sb.append("='");
        sb.append(str2);
        sb.append("'");
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(mo191b());
        m22679c(sb, "node", this.f19818a);
        m22679c(sb, FirebaseAnalytics.Param.AFFILIATION, this.f19819b.toString());
        sb.append("/>");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return null;
    }
}
