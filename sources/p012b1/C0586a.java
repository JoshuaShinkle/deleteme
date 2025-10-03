package p012b1;

import java.security.MessageDigest;
import p243y0.InterfaceC6589b;

/* renamed from: b1.a */
/* loaded from: classes.dex */
public final class C0586a implements InterfaceC6589b {

    /* renamed from: b */
    public final InterfaceC6589b f3102b;

    /* renamed from: c */
    public final InterfaceC6589b f3103c;

    public C0586a(InterfaceC6589b interfaceC6589b, InterfaceC6589b interfaceC6589b2) {
        this.f3102b = interfaceC6589b;
        this.f3103c = interfaceC6589b2;
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        this.f3102b.mo3265a(messageDigest);
        this.f3103c.mo3265a(messageDigest);
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (!(obj instanceof C0586a)) {
            return false;
        }
        C0586a c0586a = (C0586a) obj;
        return this.f3102b.equals(c0586a.f3102b) && this.f3103c.equals(c0586a.f3103c);
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return (this.f3102b.hashCode() * 31) + this.f3103c.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.f3102b + ", signature=" + this.f3103c + '}';
    }
}
