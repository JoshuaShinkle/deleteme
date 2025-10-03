package p090h8;

/* renamed from: h8.b */
/* loaded from: classes.dex */
public class C5031b {

    /* renamed from: a */
    public int f17361a;

    /* renamed from: b */
    public String f17362b;

    public C5031b(int i9, String str) {
        this.f17361a = i9;
        this.f17362b = str;
    }

    public String toString() {
        return this.f17361a + ": " + this.f17362b;
    }

    public C5031b(int i9, String str, Object... objArr) {
        this.f17362b = String.format(str, objArr);
        this.f17361a = i9;
    }
}
