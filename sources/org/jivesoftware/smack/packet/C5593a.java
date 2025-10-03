package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.util.C5618l;

/* renamed from: org.jivesoftware.smack.packet.a */
/* loaded from: classes.dex */
public class C5593a implements InterfaceC5595c {

    /* renamed from: a */
    public String f19338a;

    /* renamed from: b */
    public String f19339b;

    /* renamed from: c */
    public Map<String, String> f19340c;

    public C5593a(String str, String str2) {
        this.f19338a = str;
        this.f19339b = str2;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o(this.f19338a).m22372w(this.f19339b).m22370u();
        for (String str : m22148c()) {
            c5618l.m22361l(str, m22149d(str));
        }
        c5618l.m22356g(this.f19338a);
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return this.f19338a;
    }

    /* renamed from: c */
    public synchronized Collection<String> m22148c() {
        if (this.f19340c == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashMap(this.f19340c).keySet());
    }

    /* renamed from: d */
    public synchronized String m22149d(String str) {
        Map<String, String> map = this.f19340c;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: e */
    public synchronized void m22150e(String str, String str2) {
        if (this.f19340c == null) {
            this.f19340c = new HashMap();
        }
        this.f19340c.put(str, str2);
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return this.f19339b;
    }
}
