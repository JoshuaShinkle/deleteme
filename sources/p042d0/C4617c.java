package p042d0;

/* renamed from: d0.c */
/* loaded from: classes.dex */
public final class C4617c {

    /* renamed from: a */
    public final Object f16231a;

    public C4617c(Object obj) {
        this.f16231a = obj;
    }

    /* renamed from: a */
    public static C4617c m18397a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new C4617c(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C4617c.class != obj.getClass()) {
            return false;
        }
        C4617c c4617c = (C4617c) obj;
        Object obj2 = this.f16231a;
        return obj2 == null ? c4617c.f16231a == null : obj2.equals(c4617c.f16231a);
    }

    public int hashCode() {
        Object obj = this.f16231a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f16231a + "}";
    }
}
