package p157o6;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import okio.ByteString;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: o6.a */
/* loaded from: classes.dex */
public final class C5469a {

    /* renamed from: d */
    public static final a f18389d = new a(null);

    /* renamed from: e */
    public static final ByteString f18390e;

    /* renamed from: f */
    public static final ByteString f18391f;

    /* renamed from: g */
    public static final ByteString f18392g;

    /* renamed from: h */
    public static final ByteString f18393h;

    /* renamed from: i */
    public static final ByteString f18394i;

    /* renamed from: j */
    public static final ByteString f18395j;

    /* renamed from: a */
    public final ByteString f18396a;

    /* renamed from: b */
    public final ByteString f18397b;

    /* renamed from: c */
    public final int f18398c;

    /* renamed from: o6.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    static {
        ByteString.C5526a c5526a = ByteString.f19095d;
        f18390e = c5526a.m21901d(":");
        f18391f = c5526a.m21901d(":status");
        f18392g = c5526a.m21901d(":method");
        f18393h = c5526a.m21901d(":path");
        f18394i = c5526a.m21901d(":scheme");
        f18395j = c5526a.m21901d(":authority");
    }

    public C5469a(ByteString byteString, ByteString byteString2) {
        C0042f.m158e(byteString, AppMeasurementSdk.ConditionalUserProperty.NAME);
        C0042f.m158e(byteString2, "value");
        this.f18396a = byteString;
        this.f18397b = byteString2;
        this.f18398c = byteString.m21892r() + 32 + byteString2.m21892r();
    }

    /* renamed from: a */
    public final ByteString m21114a() {
        return this.f18396a;
    }

    /* renamed from: b */
    public final ByteString m21115b() {
        return this.f18397b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C5469a)) {
            return false;
        }
        C5469a c5469a = (C5469a) obj;
        return C0042f.m154a(this.f18396a, c5469a.f18396a) && C0042f.m154a(this.f18397b, c5469a.f18397b);
    }

    public int hashCode() {
        return (this.f18396a.hashCode() * 31) + this.f18397b.hashCode();
    }

    public String toString() {
        return this.f18396a.m21895u() + ": " + this.f18397b.m21895u();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C5469a(String str, String str2) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        C0042f.m158e(str2, "value");
        ByteString.C5526a c5526a = ByteString.f19095d;
        this(c5526a.m21901d(str), c5526a.m21901d(str2));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C5469a(ByteString byteString, String str) {
        this(byteString, ByteString.f19095d.m21901d(str));
        C0042f.m158e(byteString, AppMeasurementSdk.ConditionalUserProperty.NAME);
        C0042f.m158e(str, "value");
    }
}
