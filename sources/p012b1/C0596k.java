package p012b1;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import p022c1.InterfaceC0705b;
import p226w1.C6513f;
import p226w1.C6517j;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6595h;

/* renamed from: b1.k */
/* loaded from: classes.dex */
public final class C0596k implements InterfaceC6589b {

    /* renamed from: j */
    public static final C6513f<Class<?>, byte[]> f3128j = new C6513f<>(50);

    /* renamed from: b */
    public final InterfaceC0705b f3129b;

    /* renamed from: c */
    public final InterfaceC6589b f3130c;

    /* renamed from: d */
    public final InterfaceC6589b f3131d;

    /* renamed from: e */
    public final int f3132e;

    /* renamed from: f */
    public final int f3133f;

    /* renamed from: g */
    public final Class<?> f3134g;

    /* renamed from: h */
    public final C6592e f3135h;

    /* renamed from: i */
    public final InterfaceC6595h<?> f3136i;

    public C0596k(InterfaceC0705b interfaceC0705b, InterfaceC6589b interfaceC6589b, InterfaceC6589b interfaceC6589b2, int i9, int i10, InterfaceC6595h<?> interfaceC6595h, Class<?> cls, C6592e c6592e) {
        this.f3129b = interfaceC0705b;
        this.f3130c = interfaceC6589b;
        this.f3131d = interfaceC6589b2;
        this.f3132e = i9;
        this.f3133f = i10;
        this.f3136i = interfaceC6595h;
        this.f3134g = cls;
        this.f3135h = c6592e;
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f3129b.mo3480c(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.f3132e).putInt(this.f3133f).array();
        this.f3131d.mo3265a(messageDigest);
        this.f3130c.mo3265a(messageDigest);
        messageDigest.update(bArr);
        InterfaceC6595h<?> interfaceC6595h = this.f3136i;
        if (interfaceC6595h != null) {
            interfaceC6595h.mo3265a(messageDigest);
        }
        this.f3135h.mo3265a(messageDigest);
        messageDigest.update(m3289c());
        this.f3129b.put(bArr);
    }

    /* renamed from: c */
    public final byte[] m3289c() {
        C6513f<Class<?>, byte[]> c6513f = f3128j;
        byte[] bArrM24926g = c6513f.m24926g(this.f3134g);
        if (bArrM24926g != null) {
            return bArrM24926g;
        }
        byte[] bytes = this.f3134g.getName().getBytes(InterfaceC6589b.f22139a);
        c6513f.m24928k(this.f3134g, bytes);
        return bytes;
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (!(obj instanceof C0596k)) {
            return false;
        }
        C0596k c0596k = (C0596k) obj;
        return this.f3133f == c0596k.f3133f && this.f3132e == c0596k.f3132e && C6517j.m24943d(this.f3136i, c0596k.f3136i) && this.f3134g.equals(c0596k.f3134g) && this.f3130c.equals(c0596k.f3130c) && this.f3131d.equals(c0596k.f3131d) && this.f3135h.equals(c0596k.f3135h);
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        int iHashCode = (((((this.f3130c.hashCode() * 31) + this.f3131d.hashCode()) * 31) + this.f3132e) * 31) + this.f3133f;
        InterfaceC6595h<?> interfaceC6595h = this.f3136i;
        if (interfaceC6595h != null) {
            iHashCode = (iHashCode * 31) + interfaceC6595h.hashCode();
        }
        return (((iHashCode * 31) + this.f3134g.hashCode()) * 31) + this.f3135h.hashCode();
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.f3130c + ", signature=" + this.f3131d + ", width=" + this.f3132e + ", height=" + this.f3133f + ", decodedResourceClass=" + this.f3134g + ", transformation='" + this.f3136i + "', options=" + this.f3135h + '}';
    }
}
