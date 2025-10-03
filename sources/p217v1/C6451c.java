package p217v1;

import java.security.MessageDigest;
import p226w1.C6516i;
import p243y0.InterfaceC6589b;

/* renamed from: v1.c */
/* loaded from: classes.dex */
public final class C6451c implements InterfaceC6589b {

    /* renamed from: b */
    public final Object f21718b;

    public C6451c(Object obj) {
        this.f21718b = C6516i.m24938d(obj);
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        messageDigest.update(this.f21718b.toString().getBytes(InterfaceC6589b.f22139a));
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (obj instanceof C6451c) {
            return this.f21718b.equals(((C6451c) obj).f21718b);
        }
        return false;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return this.f21718b.hashCode();
    }

    public String toString() {
        return "ObjectKey{object=" + this.f21718b + '}';
    }
}
