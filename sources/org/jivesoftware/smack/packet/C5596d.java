package org.jivesoftware.smack.packet;

/* renamed from: org.jivesoftware.smack.packet.d */
/* loaded from: classes.dex */
public class C5596d {

    /* renamed from: a */
    public String f19351a;

    /* renamed from: b */
    public String f19352b;

    public C5596d(String str) {
        this.f19351a = str;
    }

    /* renamed from: a */
    public String m22168a() {
        return this.f19351a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("stream:error (");
        sb.append(this.f19351a);
        sb.append(")");
        if (this.f19352b != null) {
            sb.append(" text: ");
            sb.append(this.f19352b);
        }
        return sb.toString();
    }

    public C5596d(String str, String str2) {
        this(str);
        this.f19352b = str2;
    }
}
