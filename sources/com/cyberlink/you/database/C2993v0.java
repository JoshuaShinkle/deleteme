package com.cyberlink.you.database;

import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;

/* renamed from: com.cyberlink.you.database.v0 */
/* loaded from: classes.dex */
public class C2993v0 {

    /* renamed from: a */
    public String f13255a;

    /* renamed from: b */
    public String f13256b;

    /* renamed from: c */
    public String f13257c;

    /* renamed from: d */
    public String f13258d;

    /* renamed from: e */
    public String f13259e;

    /* renamed from: f */
    public String f13260f;

    public C2993v0(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f13255a = str;
        this.f13256b = str2;
        this.f13257c = str3;
        this.f13258d = str4;
        this.f13259e = str5;
        this.f13260f = str6;
    }

    /* renamed from: a */
    public String m15248a() {
        return m15249b();
    }

    /* renamed from: b */
    public final String m15249b() {
        Group groupM15077n = C2950b0.m14912k().m15077n(this.f13256b);
        return groupM15077n != null ? groupM15077n.f13717d : "";
    }

    /* renamed from: c */
    public String m15250c() {
        return this.f13256b;
    }

    /* renamed from: d */
    public long m15251d() {
        Group groupM15077n = C2950b0.m14912k().m15077n(this.f13256b);
        if (groupM15077n != null) {
            return groupM15077n.f13728o;
        }
        return 0L;
    }

    /* renamed from: e */
    public String m15252e() {
        return this.f13258d;
    }

    /* renamed from: f */
    public String m15253f() {
        return this.f13260f;
    }

    /* renamed from: g */
    public String m15254g() {
        return this.f13255a;
    }

    /* renamed from: h */
    public String m15255h() {
        return this.f13257c;
    }

    /* renamed from: i */
    public String m15256i() {
        return m15257j();
    }

    /* renamed from: j */
    public final String m15257j() {
        Friend friendM15003C = C2950b0.m14899A().m15003C(this.f13259e);
        return friendM15003C != null ? friendM15003C.m15620a() : "";
    }

    /* renamed from: k */
    public String m15258k() {
        return this.f13259e;
    }

    /* renamed from: l */
    public boolean m15259l() {
        Group groupM15077n = C2950b0.m14912k().m15077n(this.f13256b);
        return groupM15077n != null && groupM15077n.f13716c.equals("Dual");
    }
}
