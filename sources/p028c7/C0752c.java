package p028c7;

/* renamed from: c7.c */
/* loaded from: classes.dex */
public class C0752c extends C0751b implements Comparable<C0752c> {

    /* renamed from: e */
    public int f3499e;

    /* renamed from: f */
    public int f3500f;

    @Override // java.lang.Comparable
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0752c c0752c) {
        int i9 = c0752c.f3500f - this.f3500f;
        return i9 == 0 ? this.f3499e - c0752c.f3499e : i9;
    }

    /* renamed from: f */
    public int m3633f() {
        return this.f3500f;
    }

    /* renamed from: g */
    public int m3634g() {
        return this.f3499e;
    }

    @Override // p028c7.C0751b
    public String toString() {
        return super.toString() + " prio:" + this.f3500f + ":w:" + this.f3499e;
    }
}
