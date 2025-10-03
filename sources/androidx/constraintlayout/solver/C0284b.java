package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.SolverVariable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.common.IdManager;
import org.apache.commons.lang3.StringUtils;
import p141n.C5341a;

/* renamed from: androidx.constraintlayout.solver.b */
/* loaded from: classes.dex */
public class C0284b implements C0285c.a {

    /* renamed from: d */
    public final C0283a f1316d;

    /* renamed from: a */
    public SolverVariable f1313a = null;

    /* renamed from: b */
    public float f1314b = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: c */
    public boolean f1315c = false;

    /* renamed from: e */
    public boolean f1317e = false;

    public C0284b(C5341a c5341a) {
        this.f1316d = new C0283a(this, c5341a);
    }

    @Override // androidx.constraintlayout.solver.C0285c.a
    /* renamed from: a */
    public void mo1160a(SolverVariable solverVariable) {
        int i9 = solverVariable.f1289d;
        float f9 = 1.0f;
        if (i9 != 1) {
            if (i9 == 2) {
                f9 = 1000.0f;
            } else if (i9 == 3) {
                f9 = 1000000.0f;
            } else if (i9 == 4) {
                f9 = 1.0E9f;
            } else if (i9 == 5) {
                f9 = 1.0E12f;
            }
        }
        this.f1316d.m1156l(solverVariable, f9);
    }

    @Override // androidx.constraintlayout.solver.C0285c.a
    /* renamed from: b */
    public SolverVariable mo1161b(C0285c c0285c, boolean[] zArr) {
        return this.f1316d.m1151g(zArr, null);
    }

    @Override // androidx.constraintlayout.solver.C0285c.a
    /* renamed from: c */
    public void mo1162c(C0285c.a aVar) {
        if (!(aVar instanceof C0284b)) {
            return;
        }
        C0284b c0284b = (C0284b) aVar;
        this.f1313a = null;
        this.f1316d.m1147c();
        int i9 = 0;
        while (true) {
            C0283a c0283a = c0284b.f1316d;
            if (i9 >= c0283a.f1302a) {
                return;
            }
            this.f1316d.m1145a(c0283a.m1152h(i9), c0284b.f1316d.m1153i(i9), true);
            i9++;
        }
    }

    @Override // androidx.constraintlayout.solver.C0285c.a
    public void clear() {
        this.f1316d.m1147c();
        this.f1313a = null;
        this.f1314b = BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: d */
    public C0284b m1163d(C0285c c0285c, int i9) {
        this.f1316d.m1156l(c0285c.m1207p(i9, "ep"), 1.0f);
        this.f1316d.m1156l(c0285c.m1207p(i9, "em"), -1.0f);
        return this;
    }

    /* renamed from: e */
    public C0284b m1164e(SolverVariable solverVariable, int i9) {
        this.f1316d.m1156l(solverVariable, i9);
        return this;
    }

    /* renamed from: f */
    public boolean m1165f(C0285c c0285c) {
        boolean z8;
        SolverVariable solverVariableM1146b = this.f1316d.m1146b(c0285c);
        if (solverVariableM1146b == null) {
            z8 = true;
        } else {
            m1181v(solverVariableM1146b);
            z8 = false;
        }
        if (this.f1316d.f1302a == 0) {
            this.f1317e = true;
        }
        return z8;
    }

    /* renamed from: g */
    public C0284b m1166g(SolverVariable solverVariable, SolverVariable solverVariable2, int i9, float f9, SolverVariable solverVariable3, SolverVariable solverVariable4, int i10) {
        if (solverVariable2 == solverVariable3) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable4, 1.0f);
            this.f1316d.m1156l(solverVariable2, -2.0f);
            return this;
        }
        if (f9 == 0.5f) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
            this.f1316d.m1156l(solverVariable3, -1.0f);
            this.f1316d.m1156l(solverVariable4, 1.0f);
            if (i9 > 0 || i10 > 0) {
                this.f1314b = (-i9) + i10;
            }
        } else if (f9 <= BitmapDescriptorFactory.HUE_RED) {
            this.f1316d.m1156l(solverVariable, -1.0f);
            this.f1316d.m1156l(solverVariable2, 1.0f);
            this.f1314b = i9;
        } else if (f9 >= 1.0f) {
            this.f1316d.m1156l(solverVariable3, -1.0f);
            this.f1316d.m1156l(solverVariable4, 1.0f);
            this.f1314b = i10;
        } else {
            float f10 = 1.0f - f9;
            this.f1316d.m1156l(solverVariable, f10 * 1.0f);
            this.f1316d.m1156l(solverVariable2, f10 * (-1.0f));
            this.f1316d.m1156l(solverVariable3, (-1.0f) * f9);
            this.f1316d.m1156l(solverVariable4, 1.0f * f9);
            if (i9 > 0 || i10 > 0) {
                this.f1314b = ((-i9) * f10) + (i10 * f9);
            }
        }
        return this;
    }

    @Override // androidx.constraintlayout.solver.C0285c.a
    public SolverVariable getKey() {
        return this.f1313a;
    }

    /* renamed from: h */
    public C0284b m1167h(SolverVariable solverVariable, int i9) {
        this.f1313a = solverVariable;
        float f9 = i9;
        solverVariable.f1290e = f9;
        this.f1314b = f9;
        this.f1317e = true;
        return this;
    }

    /* renamed from: i */
    public C0284b m1168i(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f9) {
        this.f1316d.m1156l(solverVariable, -1.0f);
        this.f1316d.m1156l(solverVariable2, 1.0f - f9);
        this.f1316d.m1156l(solverVariable3, f9);
        return this;
    }

    /* renamed from: j */
    public C0284b m1169j(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f9) {
        this.f1316d.m1156l(solverVariable, -1.0f);
        this.f1316d.m1156l(solverVariable2, 1.0f);
        this.f1316d.m1156l(solverVariable3, f9);
        this.f1316d.m1156l(solverVariable4, -f9);
        return this;
    }

    /* renamed from: k */
    public C0284b m1170k(float f9, float f10, float f11, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f1314b = BitmapDescriptorFactory.HUE_RED;
        if (f10 == BitmapDescriptorFactory.HUE_RED || f9 == f11) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
            this.f1316d.m1156l(solverVariable4, 1.0f);
            this.f1316d.m1156l(solverVariable3, -1.0f);
        } else if (f9 == BitmapDescriptorFactory.HUE_RED) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
        } else if (f11 == BitmapDescriptorFactory.HUE_RED) {
            this.f1316d.m1156l(solverVariable3, 1.0f);
            this.f1316d.m1156l(solverVariable4, -1.0f);
        } else {
            float f12 = (f9 / f10) / (f11 / f10);
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
            this.f1316d.m1156l(solverVariable4, f12);
            this.f1316d.m1156l(solverVariable3, -f12);
        }
        return this;
    }

    /* renamed from: l */
    public C0284b m1171l(SolverVariable solverVariable, int i9) {
        if (i9 < 0) {
            this.f1314b = i9 * (-1);
            this.f1316d.m1156l(solverVariable, 1.0f);
        } else {
            this.f1314b = i9;
            this.f1316d.m1156l(solverVariable, -1.0f);
        }
        return this;
    }

    /* renamed from: m */
    public C0284b m1172m(SolverVariable solverVariable, SolverVariable solverVariable2, int i9) {
        boolean z8 = false;
        if (i9 != 0) {
            if (i9 < 0) {
                i9 *= -1;
                z8 = true;
            }
            this.f1314b = i9;
        }
        if (z8) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
        } else {
            this.f1316d.m1156l(solverVariable, -1.0f);
            this.f1316d.m1156l(solverVariable2, 1.0f);
        }
        return this;
    }

    /* renamed from: n */
    public C0284b m1173n(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i9) {
        boolean z8 = false;
        if (i9 != 0) {
            if (i9 < 0) {
                i9 *= -1;
                z8 = true;
            }
            this.f1314b = i9;
        }
        if (z8) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
            this.f1316d.m1156l(solverVariable3, -1.0f);
        } else {
            this.f1316d.m1156l(solverVariable, -1.0f);
            this.f1316d.m1156l(solverVariable2, 1.0f);
            this.f1316d.m1156l(solverVariable3, 1.0f);
        }
        return this;
    }

    /* renamed from: o */
    public C0284b m1174o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i9) {
        boolean z8 = false;
        if (i9 != 0) {
            if (i9 < 0) {
                i9 *= -1;
                z8 = true;
            }
            this.f1314b = i9;
        }
        if (z8) {
            this.f1316d.m1156l(solverVariable, 1.0f);
            this.f1316d.m1156l(solverVariable2, -1.0f);
            this.f1316d.m1156l(solverVariable3, 1.0f);
        } else {
            this.f1316d.m1156l(solverVariable, -1.0f);
            this.f1316d.m1156l(solverVariable2, 1.0f);
            this.f1316d.m1156l(solverVariable3, -1.0f);
        }
        return this;
    }

    /* renamed from: p */
    public C0284b m1175p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f9) {
        this.f1316d.m1156l(solverVariable3, 0.5f);
        this.f1316d.m1156l(solverVariable4, 0.5f);
        this.f1316d.m1156l(solverVariable, -0.5f);
        this.f1316d.m1156l(solverVariable2, -0.5f);
        this.f1314b = -f9;
        return this;
    }

    /* renamed from: q */
    public void m1176q() {
        float f9 = this.f1314b;
        if (f9 < BitmapDescriptorFactory.HUE_RED) {
            this.f1314b = f9 * (-1.0f);
            this.f1316d.m1154j();
        }
    }

    /* renamed from: r */
    public boolean m1177r() {
        SolverVariable solverVariable = this.f1313a;
        return solverVariable != null && (solverVariable.f1292g == SolverVariable.Type.UNRESTRICTED || this.f1314b >= BitmapDescriptorFactory.HUE_RED);
    }

    /* renamed from: s */
    public boolean m1178s(SolverVariable solverVariable) {
        return this.f1316d.m1148d(solverVariable);
    }

    /* renamed from: t */
    public boolean m1179t() {
        return this.f1313a == null && this.f1314b == BitmapDescriptorFactory.HUE_RED && this.f1316d.f1302a == 0;
    }

    public String toString() {
        return m1183x();
    }

    /* renamed from: u */
    public SolverVariable m1180u(SolverVariable solverVariable) {
        return this.f1316d.m1151g(null, solverVariable);
    }

    /* renamed from: v */
    public void m1181v(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f1313a;
        if (solverVariable2 != null) {
            this.f1316d.m1156l(solverVariable2, -1.0f);
            this.f1313a = null;
        }
        float fM1157m = this.f1316d.m1157m(solverVariable, true) * (-1.0f);
        this.f1313a = solverVariable;
        if (fM1157m == 1.0f) {
            return;
        }
        this.f1314b /= fM1157m;
        this.f1316d.m1149e(fM1157m);
    }

    /* renamed from: w */
    public void m1182w() {
        this.f1313a = null;
        this.f1316d.m1147c();
        this.f1314b = BitmapDescriptorFactory.HUE_RED;
        this.f1317e = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cd  */
    /* renamed from: x */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String m1183x() {
        boolean z8;
        String str = (this.f1313a == null ? "0" : "" + this.f1313a) + " = ";
        if (this.f1314b != BitmapDescriptorFactory.HUE_RED) {
            str = str + this.f1314b;
            z8 = true;
        } else {
            z8 = false;
        }
        int i9 = this.f1316d.f1302a;
        for (int i10 = 0; i10 < i9; i10++) {
            SolverVariable solverVariableM1152h = this.f1316d.m1152h(i10);
            if (solverVariableM1152h != null) {
                float fM1153i = this.f1316d.m1153i(i10);
                if (fM1153i != BitmapDescriptorFactory.HUE_RED) {
                    String string = solverVariableM1152h.toString();
                    if (!z8) {
                        if (fM1153i < BitmapDescriptorFactory.HUE_RED) {
                            str = str + "- ";
                            fM1153i *= -1.0f;
                        }
                        str = fM1153i == 1.0f ? str + string : str + fM1153i + StringUtils.SPACE + string;
                        z8 = true;
                    } else if (fM1153i > BitmapDescriptorFactory.HUE_RED) {
                        str = str + " + ";
                        if (fM1153i == 1.0f) {
                        }
                        z8 = true;
                    } else {
                        str = str + " - ";
                        fM1153i *= -1.0f;
                        if (fM1153i == 1.0f) {
                        }
                        z8 = true;
                    }
                }
            }
        }
        if (z8) {
            return str;
        }
        return str + IdManager.DEFAULT_VERSION_NAME;
    }
}
