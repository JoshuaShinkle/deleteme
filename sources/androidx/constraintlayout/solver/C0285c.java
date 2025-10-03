package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.HashMap;
import p141n.C5341a;
import p141n.C5342b;
import p141n.C5343c;

/* renamed from: androidx.constraintlayout.solver.c */
/* loaded from: classes.dex */
public class C0285c {

    /* renamed from: q */
    public static int f1318q = 1000;

    /* renamed from: c */
    public a f1321c;

    /* renamed from: f */
    public C0284b[] f1324f;

    /* renamed from: l */
    public final C5341a f1330l;

    /* renamed from: p */
    public final a f1334p;

    /* renamed from: a */
    public int f1319a = 0;

    /* renamed from: b */
    public HashMap<String, SolverVariable> f1320b = null;

    /* renamed from: d */
    public int f1322d = 32;

    /* renamed from: e */
    public int f1323e = 32;

    /* renamed from: g */
    public boolean f1325g = false;

    /* renamed from: h */
    public boolean[] f1326h = new boolean[32];

    /* renamed from: i */
    public int f1327i = 1;

    /* renamed from: j */
    public int f1328j = 0;

    /* renamed from: k */
    public int f1329k = 32;

    /* renamed from: m */
    public SolverVariable[] f1331m = new SolverVariable[f1318q];

    /* renamed from: n */
    public int f1332n = 0;

    /* renamed from: o */
    public C0284b[] f1333o = new C0284b[32];

    /* renamed from: androidx.constraintlayout.solver.c$a */
    public interface a {
        /* renamed from: a */
        void mo1160a(SolverVariable solverVariable);

        /* renamed from: b */
        SolverVariable mo1161b(C0285c c0285c, boolean[] zArr);

        /* renamed from: c */
        void mo1162c(a aVar);

        void clear();

        SolverVariable getKey();
    }

    public C0285c() {
        this.f1324f = null;
        this.f1324f = new C0284b[32];
        m1189D();
        C5341a c5341a = new C5341a();
        this.f1330l = c5341a;
        this.f1321c = new C5342b(c5341a);
        this.f1334p = new C0284b(c5341a);
    }

    /* renamed from: t */
    public static C0284b m1184t(C0285c c0285c, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f9, boolean z8) {
        C0284b c0284bM1210s = c0285c.m1210s();
        if (z8) {
            c0285c.m1198g(c0284bM1210s);
        }
        return c0284bM1210s.m1168i(solverVariable, solverVariable2, solverVariable3, f9);
    }

    /* renamed from: x */
    public static C5343c m1185x() {
        return null;
    }

    /* renamed from: A */
    public void m1186A() {
        if (!this.f1325g) {
            m1187B(this.f1321c);
            return;
        }
        boolean z8 = false;
        int i9 = 0;
        while (true) {
            if (i9 >= this.f1328j) {
                z8 = true;
                break;
            } else if (!this.f1324f[i9].f1317e) {
                break;
            } else {
                i9++;
            }
        }
        if (z8) {
            m1206o();
        } else {
            m1187B(this.f1321c);
        }
    }

    /* renamed from: B */
    public void m1187B(a aVar) {
        m1191F((C0284b) aVar);
        m1212v(aVar);
        m1188C(aVar, false);
        m1206o();
    }

    /* renamed from: C */
    public final int m1188C(a aVar, boolean z8) {
        for (int i9 = 0; i9 < this.f1327i; i9++) {
            this.f1326h[i9] = false;
        }
        boolean z9 = false;
        int i10 = 0;
        while (!z9) {
            i10++;
            if (i10 >= this.f1327i * 2) {
                return i10;
            }
            if (aVar.getKey() != null) {
                this.f1326h[aVar.getKey().f1287b] = true;
            }
            SolverVariable solverVariableMo1161b = aVar.mo1161b(this, this.f1326h);
            if (solverVariableMo1161b != null) {
                boolean[] zArr = this.f1326h;
                int i11 = solverVariableMo1161b.f1287b;
                if (zArr[i11]) {
                    return i10;
                }
                zArr[i11] = true;
            }
            if (solverVariableMo1161b != null) {
                float f9 = Float.MAX_VALUE;
                int i12 = -1;
                for (int i13 = 0; i13 < this.f1328j; i13++) {
                    C0284b c0284b = this.f1324f[i13];
                    if (c0284b.f1313a.f1292g != SolverVariable.Type.UNRESTRICTED && !c0284b.f1317e && c0284b.m1178s(solverVariableMo1161b)) {
                        float fM1150f = c0284b.f1316d.m1150f(solverVariableMo1161b);
                        if (fM1150f < BitmapDescriptorFactory.HUE_RED) {
                            float f10 = (-c0284b.f1314b) / fM1150f;
                            if (f10 < f9) {
                                i12 = i13;
                                f9 = f10;
                            }
                        }
                    }
                }
                if (i12 > -1) {
                    C0284b c0284b2 = this.f1324f[i12];
                    c0284b2.f1313a.f1288c = -1;
                    c0284b2.m1181v(solverVariableMo1161b);
                    SolverVariable solverVariable = c0284b2.f1313a;
                    solverVariable.f1288c = i12;
                    solverVariable.m1144f(c0284b2);
                }
            }
            z9 = true;
        }
        return i10;
    }

    /* renamed from: D */
    public final void m1189D() {
        int i9 = 0;
        while (true) {
            C0284b[] c0284bArr = this.f1324f;
            if (i9 >= c0284bArr.length) {
                return;
            }
            C0284b c0284b = c0284bArr[i9];
            if (c0284b != null) {
                this.f1330l.f18192a.mo20993a(c0284b);
            }
            this.f1324f[i9] = null;
            i9++;
        }
    }

    /* renamed from: E */
    public void m1190E() {
        C5341a c5341a;
        int i9 = 0;
        while (true) {
            c5341a = this.f1330l;
            SolverVariable[] solverVariableArr = c5341a.f18194c;
            if (i9 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i9];
            if (solverVariable != null) {
                solverVariable.m1142d();
            }
            i9++;
        }
        c5341a.f18193b.mo20995c(this.f1331m, this.f1332n);
        this.f1332n = 0;
        Arrays.fill(this.f1330l.f18194c, (Object) null);
        HashMap<String, SolverVariable> map = this.f1320b;
        if (map != null) {
            map.clear();
        }
        this.f1319a = 0;
        this.f1321c.clear();
        this.f1327i = 1;
        for (int i10 = 0; i10 < this.f1328j; i10++) {
            this.f1324f[i10].f1315c = false;
        }
        m1189D();
        this.f1328j = 0;
    }

    /* renamed from: F */
    public final void m1191F(C0284b c0284b) {
        if (this.f1328j > 0) {
            c0284b.f1316d.m1159o(c0284b, this.f1324f);
            if (c0284b.f1316d.f1302a == 0) {
                c0284b.f1317e = true;
            }
        }
    }

    /* renamed from: a */
    public final SolverVariable m1192a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableMo20994b = this.f1330l.f18193b.mo20994b();
        if (solverVariableMo20994b == null) {
            solverVariableMo20994b = new SolverVariable(type, str);
            solverVariableMo20994b.m1143e(type, str);
        } else {
            solverVariableMo20994b.m1142d();
            solverVariableMo20994b.m1143e(type, str);
        }
        int i9 = this.f1332n;
        int i10 = f1318q;
        if (i9 >= i10) {
            int i11 = i10 * 2;
            f1318q = i11;
            this.f1331m = (SolverVariable[]) Arrays.copyOf(this.f1331m, i11);
        }
        SolverVariable[] solverVariableArr = this.f1331m;
        int i12 = this.f1332n;
        this.f1332n = i12 + 1;
        solverVariableArr[i12] = solverVariableMo20994b;
        return solverVariableMo20994b;
    }

    /* renamed from: b */
    public void m1193b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f9, int i9) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable solverVariableM1209r = m1209r(constraintWidget.mo1278h(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable solverVariableM1209r2 = m1209r(constraintWidget.mo1278h(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable solverVariableM1209r3 = m1209r(constraintWidget.mo1278h(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable solverVariableM1209r4 = m1209r(constraintWidget.mo1278h(type4));
        SolverVariable solverVariableM1209r5 = m1209r(constraintWidget2.mo1278h(type));
        SolverVariable solverVariableM1209r6 = m1209r(constraintWidget2.mo1278h(type2));
        SolverVariable solverVariableM1209r7 = m1209r(constraintWidget2.mo1278h(type3));
        SolverVariable solverVariableM1209r8 = m1209r(constraintWidget2.mo1278h(type4));
        C0284b c0284bM1210s = m1210s();
        double d9 = f9;
        double d10 = i9;
        c0284bM1210s.m1175p(solverVariableM1209r2, solverVariableM1209r4, solverVariableM1209r6, solverVariableM1209r8, (float) (Math.sin(d9) * d10));
        m1195d(c0284bM1210s);
        C0284b c0284bM1210s2 = m1210s();
        c0284bM1210s2.m1175p(solverVariableM1209r, solverVariableM1209r3, solverVariableM1209r5, solverVariableM1209r7, (float) (Math.cos(d9) * d10));
        m1195d(c0284bM1210s2);
    }

    /* renamed from: c */
    public void m1194c(SolverVariable solverVariable, SolverVariable solverVariable2, int i9, float f9, SolverVariable solverVariable3, SolverVariable solverVariable4, int i10, int i11) {
        C0284b c0284bM1210s = m1210s();
        c0284bM1210s.m1166g(solverVariable, solverVariable2, i9, f9, solverVariable3, solverVariable4, i10);
        if (i11 != 6) {
            c0284bM1210s.m1163d(this, i11);
        }
        m1195d(c0284bM1210s);
    }

    /* renamed from: d */
    public void m1195d(C0284b c0284b) {
        SolverVariable solverVariableM1180u;
        if (c0284b == null) {
            return;
        }
        boolean z8 = true;
        if (this.f1328j + 1 >= this.f1329k || this.f1327i + 1 >= this.f1323e) {
            m1215z();
        }
        boolean z9 = false;
        if (!c0284b.f1317e) {
            m1191F(c0284b);
            if (c0284b.m1179t()) {
                return;
            }
            c0284b.m1176q();
            if (c0284b.m1165f(this)) {
                SolverVariable solverVariableM1208q = m1208q();
                c0284b.f1313a = solverVariableM1208q;
                m1204m(c0284b);
                this.f1334p.mo1162c(c0284b);
                m1188C(this.f1334p, true);
                if (solverVariableM1208q.f1288c == -1) {
                    if (c0284b.f1313a == solverVariableM1208q && (solverVariableM1180u = c0284b.m1180u(solverVariableM1208q)) != null) {
                        c0284b.m1181v(solverVariableM1180u);
                    }
                    if (!c0284b.f1317e) {
                        c0284b.f1313a.m1144f(c0284b);
                    }
                    this.f1328j--;
                }
            } else {
                z8 = false;
            }
            if (!c0284b.m1177r()) {
                return;
            } else {
                z9 = z8;
            }
        }
        if (z9) {
            return;
        }
        m1204m(c0284b);
    }

    /* renamed from: e */
    public C0284b m1196e(SolverVariable solverVariable, SolverVariable solverVariable2, int i9, int i10) {
        C0284b c0284bM1210s = m1210s();
        c0284bM1210s.m1172m(solverVariable, solverVariable2, i9);
        if (i10 != 6) {
            c0284bM1210s.m1163d(this, i10);
        }
        m1195d(c0284bM1210s);
        return c0284bM1210s;
    }

    /* renamed from: f */
    public void m1197f(SolverVariable solverVariable, int i9) {
        int i10 = solverVariable.f1288c;
        if (i10 == -1) {
            C0284b c0284bM1210s = m1210s();
            c0284bM1210s.m1167h(solverVariable, i9);
            m1195d(c0284bM1210s);
            return;
        }
        C0284b c0284b = this.f1324f[i10];
        if (c0284b.f1317e) {
            c0284b.f1314b = i9;
            return;
        }
        if (c0284b.f1316d.f1302a == 0) {
            c0284b.f1317e = true;
            c0284b.f1314b = i9;
        } else {
            C0284b c0284bM1210s2 = m1210s();
            c0284bM1210s2.m1171l(solverVariable, i9);
            m1195d(c0284bM1210s2);
        }
    }

    /* renamed from: g */
    public final void m1198g(C0284b c0284b) {
        c0284b.m1163d(this, 0);
    }

    /* renamed from: h */
    public void m1199h(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z8) {
        C0284b c0284bM1210s = m1210s();
        SolverVariable solverVariableM1211u = m1211u();
        solverVariableM1211u.f1289d = 0;
        c0284bM1210s.m1173n(solverVariable, solverVariable2, solverVariableM1211u, 0);
        if (z8) {
            m1205n(c0284bM1210s, (int) (c0284bM1210s.f1316d.m1150f(solverVariableM1211u) * (-1.0f)), 1);
        }
        m1195d(c0284bM1210s);
    }

    /* renamed from: i */
    public void m1200i(SolverVariable solverVariable, SolverVariable solverVariable2, int i9, int i10) {
        C0284b c0284bM1210s = m1210s();
        SolverVariable solverVariableM1211u = m1211u();
        solverVariableM1211u.f1289d = 0;
        c0284bM1210s.m1173n(solverVariable, solverVariable2, solverVariableM1211u, i9);
        if (i10 != 6) {
            m1205n(c0284bM1210s, (int) (c0284bM1210s.f1316d.m1150f(solverVariableM1211u) * (-1.0f)), i10);
        }
        m1195d(c0284bM1210s);
    }

    /* renamed from: j */
    public void m1201j(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z8) {
        C0284b c0284bM1210s = m1210s();
        SolverVariable solverVariableM1211u = m1211u();
        solverVariableM1211u.f1289d = 0;
        c0284bM1210s.m1174o(solverVariable, solverVariable2, solverVariableM1211u, 0);
        if (z8) {
            m1205n(c0284bM1210s, (int) (c0284bM1210s.f1316d.m1150f(solverVariableM1211u) * (-1.0f)), 1);
        }
        m1195d(c0284bM1210s);
    }

    /* renamed from: k */
    public void m1202k(SolverVariable solverVariable, SolverVariable solverVariable2, int i9, int i10) {
        C0284b c0284bM1210s = m1210s();
        SolverVariable solverVariableM1211u = m1211u();
        solverVariableM1211u.f1289d = 0;
        c0284bM1210s.m1174o(solverVariable, solverVariable2, solverVariableM1211u, i9);
        if (i10 != 6) {
            m1205n(c0284bM1210s, (int) (c0284bM1210s.f1316d.m1150f(solverVariableM1211u) * (-1.0f)), i10);
        }
        m1195d(c0284bM1210s);
    }

    /* renamed from: l */
    public void m1203l(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f9, int i9) {
        C0284b c0284bM1210s = m1210s();
        c0284bM1210s.m1169j(solverVariable, solverVariable2, solverVariable3, solverVariable4, f9);
        if (i9 != 6) {
            c0284bM1210s.m1163d(this, i9);
        }
        m1195d(c0284bM1210s);
    }

    /* renamed from: m */
    public final void m1204m(C0284b c0284b) {
        C0284b c0284b2 = this.f1324f[this.f1328j];
        if (c0284b2 != null) {
            this.f1330l.f18192a.mo20993a(c0284b2);
        }
        C0284b[] c0284bArr = this.f1324f;
        int i9 = this.f1328j;
        c0284bArr[i9] = c0284b;
        SolverVariable solverVariable = c0284b.f1313a;
        solverVariable.f1288c = i9;
        this.f1328j = i9 + 1;
        solverVariable.m1144f(c0284b);
    }

    /* renamed from: n */
    public void m1205n(C0284b c0284b, int i9, int i10) {
        c0284b.m1164e(m1207p(i10, null), i9);
    }

    /* renamed from: o */
    public final void m1206o() {
        for (int i9 = 0; i9 < this.f1328j; i9++) {
            C0284b c0284b = this.f1324f[i9];
            c0284b.f1313a.f1290e = c0284b.f1314b;
        }
    }

    /* renamed from: p */
    public SolverVariable m1207p(int i9, String str) {
        if (this.f1327i + 1 >= this.f1323e) {
            m1215z();
        }
        SolverVariable solverVariableM1192a = m1192a(SolverVariable.Type.ERROR, str);
        int i10 = this.f1319a + 1;
        this.f1319a = i10;
        this.f1327i++;
        solverVariableM1192a.f1287b = i10;
        solverVariableM1192a.f1289d = i9;
        this.f1330l.f18194c[i10] = solverVariableM1192a;
        this.f1321c.mo1160a(solverVariableM1192a);
        return solverVariableM1192a;
    }

    /* renamed from: q */
    public SolverVariable m1208q() {
        if (this.f1327i + 1 >= this.f1323e) {
            m1215z();
        }
        SolverVariable solverVariableM1192a = m1192a(SolverVariable.Type.SLACK, null);
        int i9 = this.f1319a + 1;
        this.f1319a = i9;
        this.f1327i++;
        solverVariableM1192a.f1287b = i9;
        this.f1330l.f18194c[i9] = solverVariableM1192a;
        return solverVariableM1192a;
    }

    /* renamed from: r */
    public SolverVariable m1209r(Object obj) {
        SolverVariable solverVariableM1222g = null;
        if (obj == null) {
            return null;
        }
        if (this.f1327i + 1 >= this.f1323e) {
            m1215z();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariableM1222g = constraintAnchor.m1222g();
            if (solverVariableM1222g == null) {
                constraintAnchor.m1229n(this.f1330l);
                solverVariableM1222g = constraintAnchor.m1222g();
            }
            int i9 = solverVariableM1222g.f1287b;
            if (i9 == -1 || i9 > this.f1319a || this.f1330l.f18194c[i9] == null) {
                if (i9 != -1) {
                    solverVariableM1222g.m1142d();
                }
                int i10 = this.f1319a + 1;
                this.f1319a = i10;
                this.f1327i++;
                solverVariableM1222g.f1287b = i10;
                solverVariableM1222g.f1292g = SolverVariable.Type.UNRESTRICTED;
                this.f1330l.f18194c[i10] = solverVariableM1222g;
            }
        }
        return solverVariableM1222g;
    }

    /* renamed from: s */
    public C0284b m1210s() {
        C0284b c0284bMo20994b = this.f1330l.f18192a.mo20994b();
        if (c0284bMo20994b == null) {
            c0284bMo20994b = new C0284b(this.f1330l);
        } else {
            c0284bMo20994b.m1182w();
        }
        SolverVariable.m1139b();
        return c0284bMo20994b;
    }

    /* renamed from: u */
    public SolverVariable m1211u() {
        if (this.f1327i + 1 >= this.f1323e) {
            m1215z();
        }
        SolverVariable solverVariableM1192a = m1192a(SolverVariable.Type.SLACK, null);
        int i9 = this.f1319a + 1;
        this.f1319a = i9;
        this.f1327i++;
        solverVariableM1192a.f1287b = i9;
        this.f1330l.f18194c[i9] = solverVariableM1192a;
        return solverVariableM1192a;
    }

    /* renamed from: v */
    public final int m1212v(a aVar) {
        float f9;
        boolean z8;
        int i9 = 0;
        while (true) {
            int i10 = this.f1328j;
            f9 = BitmapDescriptorFactory.HUE_RED;
            if (i9 >= i10) {
                z8 = false;
                break;
            }
            C0284b c0284b = this.f1324f[i9];
            if (c0284b.f1313a.f1292g != SolverVariable.Type.UNRESTRICTED && c0284b.f1314b < BitmapDescriptorFactory.HUE_RED) {
                z8 = true;
                break;
            }
            i9++;
        }
        if (!z8) {
            return 0;
        }
        boolean z9 = false;
        int i11 = 0;
        while (!z9) {
            i11++;
            float f10 = Float.MAX_VALUE;
            int i12 = -1;
            int i13 = -1;
            int i14 = 0;
            int i15 = 0;
            while (i14 < this.f1328j) {
                C0284b c0284b2 = this.f1324f[i14];
                if (c0284b2.f1313a.f1292g != SolverVariable.Type.UNRESTRICTED && !c0284b2.f1317e && c0284b2.f1314b < f9) {
                    int i16 = 1;
                    while (i16 < this.f1327i) {
                        SolverVariable solverVariable = this.f1330l.f18194c[i16];
                        float fM1150f = c0284b2.f1316d.m1150f(solverVariable);
                        if (fM1150f > f9) {
                            for (int i17 = 0; i17 < 7; i17++) {
                                float f11 = solverVariable.f1291f[i17] / fM1150f;
                                if ((f11 < f10 && i17 == i15) || i17 > i15) {
                                    i15 = i17;
                                    f10 = f11;
                                    i12 = i14;
                                    i13 = i16;
                                }
                            }
                        }
                        i16++;
                        f9 = BitmapDescriptorFactory.HUE_RED;
                    }
                }
                i14++;
                f9 = BitmapDescriptorFactory.HUE_RED;
            }
            if (i12 != -1) {
                C0284b c0284b3 = this.f1324f[i12];
                c0284b3.f1313a.f1288c = -1;
                c0284b3.m1181v(this.f1330l.f18194c[i13]);
                SolverVariable solverVariable2 = c0284b3.f1313a;
                solverVariable2.f1288c = i12;
                solverVariable2.m1144f(c0284b3);
            } else {
                z9 = true;
            }
            if (i11 > this.f1327i / 2) {
                z9 = true;
            }
            f9 = BitmapDescriptorFactory.HUE_RED;
        }
        return i11;
    }

    /* renamed from: w */
    public C5341a m1213w() {
        return this.f1330l;
    }

    /* renamed from: y */
    public int m1214y(Object obj) {
        SolverVariable solverVariableM1222g = ((ConstraintAnchor) obj).m1222g();
        if (solverVariableM1222g != null) {
            return (int) (solverVariableM1222g.f1290e + 0.5f);
        }
        return 0;
    }

    /* renamed from: z */
    public final void m1215z() {
        int i9 = this.f1322d * 2;
        this.f1322d = i9;
        this.f1324f = (C0284b[]) Arrays.copyOf(this.f1324f, i9);
        C5341a c5341a = this.f1330l;
        c5341a.f18194c = (SolverVariable[]) Arrays.copyOf(c5341a.f18194c, this.f1322d);
        int i10 = this.f1322d;
        this.f1326h = new boolean[i10];
        this.f1323e = i10;
        this.f1329k = i10;
    }
}
