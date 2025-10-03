package androidx.recyclerview.widget;

import androidx.recyclerview.widget.C0472a;
import java.util.List;

/* renamed from: androidx.recyclerview.widget.k */
/* loaded from: classes.dex */
public class C0482k {

    /* renamed from: a */
    public final a f2716a;

    /* renamed from: androidx.recyclerview.widget.k$a */
    public interface a {
        /* renamed from: a */
        void mo2734a(C0472a.b bVar);

        /* renamed from: b */
        C0472a.b mo2735b(int i9, int i10, int i11, Object obj);
    }

    public C0482k(a aVar) {
        this.f2716a = aVar;
    }

    /* renamed from: a */
    public final int m2859a(List<C0472a.b> list) {
        boolean z8 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f2581a != 8) {
                z8 = true;
            } else if (z8) {
                return size;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public void m2860b(List<C0472a.b> list) {
        while (true) {
            int iM2859a = m2859a(list);
            if (iM2859a == -1) {
                return;
            } else {
                m2862d(list, iM2859a, iM2859a + 1);
            }
        }
    }

    /* renamed from: c */
    public final void m2861c(List<C0472a.b> list, int i9, C0472a.b bVar, int i10, C0472a.b bVar2) {
        int i11 = bVar.f2584d;
        int i12 = bVar2.f2582b;
        int i13 = i11 < i12 ? -1 : 0;
        int i14 = bVar.f2582b;
        if (i14 < i12) {
            i13++;
        }
        if (i12 <= i14) {
            bVar.f2582b = i14 + bVar2.f2584d;
        }
        int i15 = bVar2.f2582b;
        if (i15 <= i11) {
            bVar.f2584d = i11 + bVar2.f2584d;
        }
        bVar2.f2582b = i15 + i13;
        list.set(i9, bVar2);
        list.set(i10, bVar);
    }

    /* renamed from: d */
    public final void m2862d(List<C0472a.b> list, int i9, int i10) {
        C0472a.b bVar = list.get(i9);
        C0472a.b bVar2 = list.get(i10);
        int i11 = bVar2.f2581a;
        if (i11 == 1) {
            m2861c(list, i9, bVar, i10, bVar2);
        } else if (i11 == 2) {
            m2863e(list, i9, bVar, i10, bVar2);
        } else {
            if (i11 != 4) {
                return;
            }
            m2864f(list, i9, bVar, i10, bVar2);
        }
    }

    /* renamed from: e */
    public void m2863e(List<C0472a.b> list, int i9, C0472a.b bVar, int i10, C0472a.b bVar2) {
        boolean z8;
        int i11 = bVar.f2582b;
        int i12 = bVar.f2584d;
        boolean z9 = false;
        if (i11 < i12) {
            if (bVar2.f2582b == i11 && bVar2.f2584d == i12 - i11) {
                z8 = false;
                z9 = true;
            } else {
                z8 = false;
            }
        } else if (bVar2.f2582b == i12 + 1 && bVar2.f2584d == i11 - i12) {
            z8 = true;
            z9 = true;
        } else {
            z8 = true;
        }
        int i13 = bVar2.f2582b;
        if (i12 < i13) {
            bVar2.f2582b = i13 - 1;
        } else {
            int i14 = bVar2.f2584d;
            if (i12 < i13 + i14) {
                bVar2.f2584d = i14 - 1;
                bVar.f2581a = 2;
                bVar.f2584d = 1;
                if (bVar2.f2584d == 0) {
                    list.remove(i10);
                    this.f2716a.mo2734a(bVar2);
                    return;
                }
                return;
            }
        }
        int i15 = bVar.f2582b;
        int i16 = bVar2.f2582b;
        C0472a.b bVarMo2735b = null;
        if (i15 <= i16) {
            bVar2.f2582b = i16 + 1;
        } else {
            int i17 = bVar2.f2584d;
            if (i15 < i16 + i17) {
                bVarMo2735b = this.f2716a.mo2735b(2, i15 + 1, (i16 + i17) - i15, null);
                bVar2.f2584d = bVar.f2582b - bVar2.f2582b;
            }
        }
        if (z9) {
            list.set(i9, bVar2);
            list.remove(i10);
            this.f2716a.mo2734a(bVar);
            return;
        }
        if (z8) {
            if (bVarMo2735b != null) {
                int i18 = bVar.f2582b;
                if (i18 > bVarMo2735b.f2582b) {
                    bVar.f2582b = i18 - bVarMo2735b.f2584d;
                }
                int i19 = bVar.f2584d;
                if (i19 > bVarMo2735b.f2582b) {
                    bVar.f2584d = i19 - bVarMo2735b.f2584d;
                }
            }
            int i20 = bVar.f2582b;
            if (i20 > bVar2.f2582b) {
                bVar.f2582b = i20 - bVar2.f2584d;
            }
            int i21 = bVar.f2584d;
            if (i21 > bVar2.f2582b) {
                bVar.f2584d = i21 - bVar2.f2584d;
            }
        } else {
            if (bVarMo2735b != null) {
                int i22 = bVar.f2582b;
                if (i22 >= bVarMo2735b.f2582b) {
                    bVar.f2582b = i22 - bVarMo2735b.f2584d;
                }
                int i23 = bVar.f2584d;
                if (i23 >= bVarMo2735b.f2582b) {
                    bVar.f2584d = i23 - bVarMo2735b.f2584d;
                }
            }
            int i24 = bVar.f2582b;
            if (i24 >= bVar2.f2582b) {
                bVar.f2582b = i24 - bVar2.f2584d;
            }
            int i25 = bVar.f2584d;
            if (i25 >= bVar2.f2582b) {
                bVar.f2584d = i25 - bVar2.f2584d;
            }
        }
        list.set(i9, bVar2);
        if (bVar.f2582b != bVar.f2584d) {
            list.set(i10, bVar);
        } else {
            list.remove(i10);
        }
        if (bVarMo2735b != null) {
            list.add(i9, bVarMo2735b);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m2864f(List<C0472a.b> list, int i9, C0472a.b bVar, int i10, C0472a.b bVar2) {
        C0472a.b bVarMo2735b;
        int i11;
        int i12;
        int i13 = bVar.f2584d;
        int i14 = bVar2.f2582b;
        C0472a.b bVarMo2735b2 = null;
        if (i13 >= i14) {
            int i15 = bVar2.f2584d;
            if (i13 < i14 + i15) {
                bVar2.f2584d = i15 - 1;
                bVarMo2735b = this.f2716a.mo2735b(4, bVar.f2582b, 1, bVar2.f2583c);
            }
            i11 = bVar.f2582b;
            i12 = bVar2.f2582b;
            if (i11 > i12) {
                bVar2.f2582b = i12 + 1;
            } else {
                int i16 = bVar2.f2584d;
                if (i11 < i12 + i16) {
                    int i17 = (i12 + i16) - i11;
                    bVarMo2735b2 = this.f2716a.mo2735b(4, i11 + 1, i17, bVar2.f2583c);
                    bVar2.f2584d -= i17;
                }
            }
            list.set(i10, bVar);
            if (bVar2.f2584d <= 0) {
                list.set(i9, bVar2);
            } else {
                list.remove(i9);
                this.f2716a.mo2734a(bVar2);
            }
            if (bVarMo2735b != null) {
                list.add(i9, bVarMo2735b);
            }
            if (bVarMo2735b2 == null) {
                list.add(i9, bVarMo2735b2);
                return;
            }
            return;
        }
        bVar2.f2582b = i14 - 1;
        bVarMo2735b = null;
        i11 = bVar.f2582b;
        i12 = bVar2.f2582b;
        if (i11 > i12) {
        }
        list.set(i10, bVar);
        if (bVar2.f2584d <= 0) {
        }
        if (bVarMo2735b != null) {
        }
        if (bVarMo2735b2 == null) {
        }
    }
}
