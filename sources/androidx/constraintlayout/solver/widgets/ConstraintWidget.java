package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import p141n.C5341a;
import p150o.C5386c;

/* loaded from: classes.dex */
public class ConstraintWidget {

    /* renamed from: u0 */
    public static float f1363u0 = 0.5f;

    /* renamed from: B */
    public ConstraintAnchor f1365B;

    /* renamed from: C */
    public ConstraintAnchor[] f1366C;

    /* renamed from: D */
    public ArrayList<ConstraintAnchor> f1367D;

    /* renamed from: E */
    public DimensionBehaviour[] f1368E;

    /* renamed from: F */
    public ConstraintWidget f1369F;

    /* renamed from: G */
    public int f1370G;

    /* renamed from: H */
    public int f1371H;

    /* renamed from: I */
    public float f1372I;

    /* renamed from: J */
    public int f1373J;

    /* renamed from: K */
    public int f1374K;

    /* renamed from: L */
    public int f1375L;

    /* renamed from: M */
    public int f1376M;

    /* renamed from: N */
    public int f1377N;

    /* renamed from: O */
    public int f1378O;

    /* renamed from: P */
    public int f1379P;

    /* renamed from: Q */
    public int f1380Q;

    /* renamed from: R */
    public int f1381R;

    /* renamed from: S */
    public int f1382S;

    /* renamed from: T */
    public int f1383T;

    /* renamed from: U */
    public int f1384U;

    /* renamed from: V */
    public int f1385V;

    /* renamed from: W */
    public int f1386W;

    /* renamed from: X */
    public int f1387X;

    /* renamed from: Y */
    public int f1388Y;

    /* renamed from: Z */
    public float f1389Z;

    /* renamed from: a0 */
    public float f1391a0;

    /* renamed from: b0 */
    public Object f1393b0;

    /* renamed from: c */
    public C5386c f1394c;

    /* renamed from: c0 */
    public int f1395c0;

    /* renamed from: d */
    public C5386c f1396d;

    /* renamed from: d0 */
    public int f1397d0;

    /* renamed from: e0 */
    public String f1399e0;

    /* renamed from: f0 */
    public String f1401f0;

    /* renamed from: g0 */
    public boolean f1403g0;

    /* renamed from: h0 */
    public boolean f1405h0;

    /* renamed from: i0 */
    public boolean f1407i0;

    /* renamed from: j0 */
    public boolean f1409j0;

    /* renamed from: k0 */
    public boolean f1411k0;

    /* renamed from: l0 */
    public int f1413l0;

    /* renamed from: m0 */
    public int f1415m0;

    /* renamed from: n */
    public boolean f1416n;

    /* renamed from: n0 */
    public boolean f1417n0;

    /* renamed from: o */
    public boolean f1418o;

    /* renamed from: o0 */
    public boolean f1419o0;

    /* renamed from: p0 */
    public float[] f1421p0;

    /* renamed from: q0 */
    public ConstraintWidget[] f1423q0;

    /* renamed from: r0 */
    public ConstraintWidget[] f1425r0;

    /* renamed from: s0 */
    public ConstraintWidget f1427s0;

    /* renamed from: t0 */
    public ConstraintWidget f1429t0;

    /* renamed from: a */
    public int f1390a = -1;

    /* renamed from: b */
    public int f1392b = -1;

    /* renamed from: e */
    public int f1398e = 0;

    /* renamed from: f */
    public int f1400f = 0;

    /* renamed from: g */
    public int[] f1402g = new int[2];

    /* renamed from: h */
    public int f1404h = 0;

    /* renamed from: i */
    public int f1406i = 0;

    /* renamed from: j */
    public float f1408j = 1.0f;

    /* renamed from: k */
    public int f1410k = 0;

    /* renamed from: l */
    public int f1412l = 0;

    /* renamed from: m */
    public float f1414m = 1.0f;

    /* renamed from: p */
    public int f1420p = -1;

    /* renamed from: q */
    public float f1422q = 1.0f;

    /* renamed from: r */
    public C0293f f1424r = null;

    /* renamed from: s */
    public int[] f1426s = {Integer.MAX_VALUE, Integer.MAX_VALUE};

    /* renamed from: t */
    public float f1428t = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: u */
    public ConstraintAnchor f1430u = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);

    /* renamed from: v */
    public ConstraintAnchor f1431v = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);

    /* renamed from: w */
    public ConstraintAnchor f1432w = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);

    /* renamed from: x */
    public ConstraintAnchor f1433x = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);

    /* renamed from: y */
    public ConstraintAnchor f1434y = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);

    /* renamed from: z */
    public ConstraintAnchor f1435z = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);

    /* renamed from: A */
    public ConstraintAnchor f1364A = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$a */
    public static /* synthetic */ class C0287a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f1441a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f1442b;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            f1442b = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1442b[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1442b[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1442b[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            f1441a = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1441a[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1441a[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1441a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1441a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1441a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1441a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1441a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1441a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.f1365B = constraintAnchor;
        this.f1366C = new ConstraintAnchor[]{this.f1430u, this.f1432w, this.f1431v, this.f1433x, this.f1434y, constraintAnchor};
        this.f1367D = new ArrayList<>();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.f1368E = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.f1369F = null;
        this.f1370G = 0;
        this.f1371H = 0;
        this.f1372I = BitmapDescriptorFactory.HUE_RED;
        this.f1373J = -1;
        this.f1374K = 0;
        this.f1375L = 0;
        this.f1376M = 0;
        this.f1377N = 0;
        this.f1378O = 0;
        this.f1379P = 0;
        this.f1380Q = 0;
        this.f1381R = 0;
        this.f1382S = 0;
        this.f1383T = 0;
        this.f1384U = 0;
        float f9 = f1363u0;
        this.f1389Z = f9;
        this.f1391a0 = f9;
        this.f1395c0 = 0;
        this.f1397d0 = 0;
        this.f1399e0 = null;
        this.f1401f0 = null;
        this.f1407i0 = false;
        this.f1409j0 = false;
        this.f1411k0 = false;
        this.f1413l0 = 0;
        this.f1415m0 = 0;
        this.f1421p0 = new float[]{-1.0f, -1.0f};
        this.f1423q0 = new ConstraintWidget[]{null, null};
        this.f1425r0 = new ConstraintWidget[]{null, null};
        this.f1427s0 = null;
        this.f1429t0 = null;
        m1264a();
    }

    /* renamed from: A */
    public int m1230A() {
        return this.f1375L + this.f1383T;
    }

    /* renamed from: A0 */
    public void m1231A0(int i9) {
        this.f1388Y = i9;
    }

    /* renamed from: B */
    public DimensionBehaviour m1232B() {
        return this.f1368E[1];
    }

    /* renamed from: B0 */
    public void m1233B0(int i9) {
        this.f1387X = i9;
    }

    /* renamed from: C */
    public int m1234C() {
        return this.f1397d0;
    }

    /* renamed from: C0 */
    public void m1235C0(int i9) {
        this.f1374K = i9;
    }

    /* renamed from: D */
    public int m1236D() {
        if (this.f1397d0 == 8) {
            return 0;
        }
        return this.f1370G;
    }

    /* renamed from: D0 */
    public void m1237D0(int i9) {
        this.f1375L = i9;
    }

    /* renamed from: E */
    public int m1238E() {
        return this.f1388Y;
    }

    /* renamed from: E0 */
    public void m1239E0(boolean z8, boolean z9, boolean z10, boolean z11) {
        if (this.f1420p == -1) {
            if (z10 && !z11) {
                this.f1420p = 0;
            } else if (!z10 && z11) {
                this.f1420p = 1;
                if (this.f1373J == -1) {
                    this.f1422q = 1.0f / this.f1422q;
                }
            }
        }
        if (this.f1420p == 0 && (!this.f1431v.m1226k() || !this.f1433x.m1226k())) {
            this.f1420p = 1;
        } else if (this.f1420p == 1 && (!this.f1430u.m1226k() || !this.f1432w.m1226k())) {
            this.f1420p = 0;
        }
        if (this.f1420p == -1 && (!this.f1431v.m1226k() || !this.f1433x.m1226k() || !this.f1430u.m1226k() || !this.f1432w.m1226k())) {
            if (this.f1431v.m1226k() && this.f1433x.m1226k()) {
                this.f1420p = 0;
            } else if (this.f1430u.m1226k() && this.f1432w.m1226k()) {
                this.f1422q = 1.0f / this.f1422q;
                this.f1420p = 1;
            }
        }
        if (this.f1420p == -1) {
            if (z8 && !z9) {
                this.f1420p = 0;
            } else if (!z8 && z9) {
                this.f1422q = 1.0f / this.f1422q;
                this.f1420p = 1;
            }
        }
        if (this.f1420p == -1) {
            int i9 = this.f1404h;
            if (i9 > 0 && this.f1410k == 0) {
                this.f1420p = 0;
            } else if (i9 == 0 && this.f1410k > 0) {
                this.f1422q = 1.0f / this.f1422q;
                this.f1420p = 1;
            }
        }
        if (this.f1420p == -1 && z8 && z9) {
            this.f1422q = 1.0f / this.f1422q;
            this.f1420p = 1;
        }
    }

    /* renamed from: F */
    public int m1240F() {
        return this.f1387X;
    }

    /* renamed from: F0 */
    public void mo1241F0() {
        int i9 = this.f1374K;
        int i10 = this.f1375L;
        int i11 = this.f1370G + i9;
        int i12 = this.f1371H + i10;
        this.f1378O = i9;
        this.f1379P = i10;
        this.f1380Q = i11 - i9;
        this.f1381R = i12 - i10;
    }

    /* renamed from: G */
    public int m1242G() {
        return this.f1374K;
    }

    /* renamed from: G0 */
    public void mo1243G0(C0285c c0285c) {
        int iM1214y = c0285c.m1214y(this.f1430u);
        int iM1214y2 = c0285c.m1214y(this.f1431v);
        int iM1214y3 = c0285c.m1214y(this.f1432w);
        int iM1214y4 = c0285c.m1214y(this.f1433x);
        int i9 = iM1214y4 - iM1214y2;
        if (iM1214y3 - iM1214y < 0 || i9 < 0 || iM1214y == Integer.MIN_VALUE || iM1214y == Integer.MAX_VALUE || iM1214y2 == Integer.MIN_VALUE || iM1214y2 == Integer.MAX_VALUE || iM1214y3 == Integer.MIN_VALUE || iM1214y3 == Integer.MAX_VALUE || iM1214y4 == Integer.MIN_VALUE || iM1214y4 == Integer.MAX_VALUE) {
            iM1214y = 0;
            iM1214y4 = 0;
            iM1214y2 = 0;
            iM1214y3 = 0;
        }
        m1265a0(iM1214y, iM1214y2, iM1214y3, iM1214y4);
    }

    /* renamed from: H */
    public int m1244H() {
        return this.f1375L;
    }

    /* renamed from: H0 */
    public void m1245H0() {
        for (int i9 = 0; i9 < 6; i9++) {
            this.f1366C[i9].m1221f().m1384q();
        }
    }

    /* renamed from: I */
    public boolean m1246I() {
        return this.f1384U > 0;
    }

    /* renamed from: J */
    public void m1247J(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i9, int i10) {
        mo1278h(type).m1216a(constraintWidget.mo1278h(type2), i9, i10, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    /* renamed from: K */
    public final boolean m1248K(int i9) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        int i10 = i9 * 2;
        ConstraintAnchor[] constraintAnchorArr = this.f1366C;
        ConstraintAnchor constraintAnchor3 = constraintAnchorArr[i10];
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1338d;
        return (constraintAnchor4 == null || constraintAnchor4.f1338d == constraintAnchor3 || (constraintAnchor2 = (constraintAnchor = constraintAnchorArr[i10 + 1]).f1338d) == null || constraintAnchor2.f1338d != constraintAnchor) ? false : true;
    }

    /* renamed from: L */
    public boolean m1249L() {
        return this.f1430u.m1221f().f18287b == 1 && this.f1432w.m1221f().f18287b == 1 && this.f1431v.m1221f().f18287b == 1 && this.f1433x.m1221f().f18287b == 1;
    }

    /* renamed from: M */
    public boolean m1250M() {
        ConstraintAnchor constraintAnchor = this.f1430u;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1338d;
        if (constraintAnchor2 != null && constraintAnchor2.f1338d == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.f1432w;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1338d;
        return constraintAnchor4 != null && constraintAnchor4.f1338d == constraintAnchor3;
    }

    /* renamed from: N */
    public boolean m1251N() {
        ConstraintAnchor constraintAnchor = this.f1431v;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1338d;
        if (constraintAnchor2 != null && constraintAnchor2.f1338d == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.f1433x;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1338d;
        return constraintAnchor4 != null && constraintAnchor4.f1338d == constraintAnchor3;
    }

    /* renamed from: O */
    public boolean m1252O() {
        return this.f1400f == 0 && this.f1372I == BitmapDescriptorFactory.HUE_RED && this.f1410k == 0 && this.f1412l == 0 && this.f1368E[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    /* renamed from: P */
    public boolean m1253P() {
        return this.f1398e == 0 && this.f1372I == BitmapDescriptorFactory.HUE_RED && this.f1404h == 0 && this.f1406i == 0 && this.f1368E[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    /* renamed from: Q */
    public void mo1254Q() {
        this.f1430u.m1228m();
        this.f1431v.m1228m();
        this.f1432w.m1228m();
        this.f1433x.m1228m();
        this.f1434y.m1228m();
        this.f1435z.m1228m();
        this.f1364A.m1228m();
        this.f1365B.m1228m();
        this.f1369F = null;
        this.f1428t = BitmapDescriptorFactory.HUE_RED;
        this.f1370G = 0;
        this.f1371H = 0;
        this.f1372I = BitmapDescriptorFactory.HUE_RED;
        this.f1373J = -1;
        this.f1374K = 0;
        this.f1375L = 0;
        this.f1378O = 0;
        this.f1379P = 0;
        this.f1380Q = 0;
        this.f1381R = 0;
        this.f1382S = 0;
        this.f1383T = 0;
        this.f1384U = 0;
        this.f1385V = 0;
        this.f1386W = 0;
        this.f1387X = 0;
        this.f1388Y = 0;
        float f9 = f1363u0;
        this.f1389Z = f9;
        this.f1391a0 = f9;
        DimensionBehaviour[] dimensionBehaviourArr = this.f1368E;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.f1393b0 = null;
        this.f1395c0 = 0;
        this.f1397d0 = 0;
        this.f1401f0 = null;
        this.f1403g0 = false;
        this.f1405h0 = false;
        this.f1413l0 = 0;
        this.f1415m0 = 0;
        this.f1417n0 = false;
        this.f1419o0 = false;
        float[] fArr = this.f1421p0;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.f1390a = -1;
        this.f1392b = -1;
        int[] iArr = this.f1426s;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.f1398e = 0;
        this.f1400f = 0;
        this.f1408j = 1.0f;
        this.f1414m = 1.0f;
        this.f1406i = Integer.MAX_VALUE;
        this.f1412l = Integer.MAX_VALUE;
        this.f1404h = 0;
        this.f1410k = 0;
        this.f1420p = -1;
        this.f1422q = 1.0f;
        C5386c c5386c = this.f1394c;
        if (c5386c != null) {
            c5386c.mo1372e();
        }
        C5386c c5386c2 = this.f1396d;
        if (c5386c2 != null) {
            c5386c2.mo1372e();
        }
        this.f1424r = null;
        this.f1407i0 = false;
        this.f1409j0 = false;
        this.f1411k0 = false;
    }

    /* renamed from: R */
    public void m1255R() {
        ConstraintWidget constraintWidgetM1304u = m1304u();
        if (constraintWidgetM1304u != null && (constraintWidgetM1304u instanceof C0292e) && ((C0292e) m1304u()).m1341S0()) {
            return;
        }
        int size = this.f1367D.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f1367D.get(i9).m1228m();
        }
    }

    /* renamed from: S */
    public void mo1256S() {
        for (int i9 = 0; i9 < 6; i9++) {
            this.f1366C[i9].m1221f().mo1372e();
        }
    }

    /* renamed from: T */
    public void mo1257T(C5341a c5341a) {
        this.f1430u.m1229n(c5341a);
        this.f1431v.m1229n(c5341a);
        this.f1432w.m1229n(c5341a);
        this.f1433x.m1229n(c5341a);
        this.f1434y.m1229n(c5341a);
        this.f1365B.m1229n(c5341a);
        this.f1435z.m1229n(c5341a);
        this.f1364A.m1229n(c5341a);
    }

    /* renamed from: U */
    public void mo1258U() {
    }

    /* renamed from: V */
    public void m1259V(int i9) {
        this.f1384U = i9;
    }

    /* renamed from: W */
    public void m1260W(Object obj) {
        this.f1393b0 = obj;
    }

    /* renamed from: X */
    public void m1261X(String str) {
        this.f1399e0 = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0086 A[PHI: r0
      0x0086: PHI (r0v2 int) = (r0v1 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int) binds: [B:46:0x0086, B:36:0x007f, B:24:0x0051, B:26:0x0057, B:28:0x0063, B:30:0x0067] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0086 -> B:40:0x0087). Please report as a decompilation issue!!! */
    /* renamed from: Y */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m1262Y(String str) throws NumberFormatException {
        float fAbs;
        int i9 = 0;
        if (str == null || str.length() == 0) {
            this.f1372I = BitmapDescriptorFactory.HUE_RED;
            return;
        }
        int length = str.length();
        int iIndexOf = str.indexOf(44);
        int i10 = -1;
        if (iIndexOf > 0 && iIndexOf < length - 1) {
            String strSubstring = str.substring(0, iIndexOf);
            i10 = strSubstring.equalsIgnoreCase("W") ? 0 : strSubstring.equalsIgnoreCase("H") ? 1 : -1;
            i = iIndexOf + 1;
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
            String strSubstring2 = str.substring(i);
            fAbs = strSubstring2.length() > 0 ? Float.parseFloat(strSubstring2) : i9;
        } else {
            String strSubstring3 = str.substring(i, iIndexOf2);
            String strSubstring4 = str.substring(iIndexOf2 + 1);
            if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                float f9 = Float.parseFloat(strSubstring3);
                float f10 = Float.parseFloat(strSubstring4);
                if (f9 > BitmapDescriptorFactory.HUE_RED && f10 > BitmapDescriptorFactory.HUE_RED) {
                    fAbs = i10 == 1 ? Math.abs(f10 / f9) : Math.abs(f9 / f10);
                }
            }
        }
        i9 = (fAbs > i9 ? 1 : (fAbs == i9 ? 0 : -1));
        if (i9 > 0) {
            this.f1372I = fAbs;
            this.f1373J = i10;
        }
    }

    /* renamed from: Z */
    public void m1263Z(int i9, int i10, int i11) {
        if (i11 == 0) {
            m1275f0(i9, i10);
        } else if (i11 == 1) {
            m1303t0(i9, i10);
        }
        this.f1409j0 = true;
    }

    /* renamed from: a */
    public final void m1264a() {
        this.f1367D.add(this.f1430u);
        this.f1367D.add(this.f1431v);
        this.f1367D.add(this.f1432w);
        this.f1367D.add(this.f1433x);
        this.f1367D.add(this.f1435z);
        this.f1367D.add(this.f1364A);
        this.f1367D.add(this.f1365B);
        this.f1367D.add(this.f1434y);
    }

    /* renamed from: a0 */
    public void m1265a0(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15 = i11 - i9;
        int i16 = i12 - i10;
        this.f1374K = i9;
        this.f1375L = i10;
        if (this.f1397d0 == 8) {
            this.f1370G = 0;
            this.f1371H = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.f1368E;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i15 < (i14 = this.f1370G)) {
            i15 = i14;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i16 < (i13 = this.f1371H)) {
            i16 = i13;
        }
        this.f1370G = i15;
        this.f1371H = i16;
        int i17 = this.f1386W;
        if (i16 < i17) {
            this.f1371H = i17;
        }
        int i18 = this.f1385V;
        if (i15 < i18) {
            this.f1370G = i18;
        }
        this.f1409j0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x017a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0217 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:168:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0170  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo1266b(C0285c c0285c) {
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z12;
        DimensionBehaviour dimensionBehaviour;
        boolean z13;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        C0285c c0285c2;
        boolean z14;
        SolverVariable solverVariable4;
        ConstraintWidget constraintWidget;
        int i13;
        C0285c c0285c3;
        SolverVariable solverVariableM1209r;
        int i14;
        boolean zM1250M;
        boolean zM1251N;
        SolverVariable solverVariableM1209r2 = c0285c.m1209r(this.f1430u);
        SolverVariable solverVariableM1209r3 = c0285c.m1209r(this.f1432w);
        SolverVariable solverVariableM1209r4 = c0285c.m1209r(this.f1431v);
        SolverVariable solverVariableM1209r5 = c0285c.m1209r(this.f1433x);
        SolverVariable solverVariableM1209r6 = c0285c.m1209r(this.f1434y);
        ConstraintWidget constraintWidget2 = this.f1369F;
        if (constraintWidget2 != null) {
            boolean z15 = constraintWidget2 != null && constraintWidget2.f1368E[0] == DimensionBehaviour.WRAP_CONTENT;
            boolean z16 = constraintWidget2 != null && constraintWidget2.f1368E[1] == DimensionBehaviour.WRAP_CONTENT;
            if (m1248K(0)) {
                ((C0292e) this.f1369F).m1336N0(this, 0);
                zM1250M = true;
            } else {
                zM1250M = m1250M();
            }
            if (m1248K(1)) {
                ((C0292e) this.f1369F).m1336N0(this, 1);
                zM1251N = true;
            } else {
                zM1251N = m1251N();
            }
            if (z15 && this.f1397d0 != 8 && this.f1430u.f1338d == null && this.f1432w.f1338d == null) {
                c0285c.m1200i(c0285c.m1209r(this.f1369F.f1432w), solverVariableM1209r3, 0, 1);
            }
            if (z16 && this.f1397d0 != 8 && this.f1431v.f1338d == null && this.f1433x.f1338d == null && this.f1434y == null) {
                c0285c.m1200i(c0285c.m1209r(this.f1369F.f1433x), solverVariableM1209r5, 0, 1);
            }
            z9 = z16;
            z8 = z15;
            z10 = zM1250M;
            z11 = zM1251N;
        } else {
            z8 = false;
            z9 = false;
            z10 = false;
            z11 = false;
        }
        int i15 = this.f1370G;
        int i16 = this.f1385V;
        if (i15 >= i16) {
            i16 = i15;
        }
        int i17 = this.f1371H;
        int i18 = this.f1386W;
        if (i17 >= i18) {
            i18 = i17;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.f1368E;
        DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour3 = DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z17 = dimensionBehaviour2 != dimensionBehaviour3;
        DimensionBehaviour dimensionBehaviour4 = dimensionBehaviourArr[1];
        boolean z18 = dimensionBehaviour4 != dimensionBehaviour3;
        int i19 = this.f1373J;
        this.f1420p = i19;
        float f9 = this.f1372I;
        this.f1422q = f9;
        int i20 = i16;
        int i21 = this.f1398e;
        int i22 = i18;
        int i23 = this.f1400f;
        if (f9 <= BitmapDescriptorFactory.HUE_RED || this.f1397d0 == 8) {
            i9 = i21;
            i10 = i23;
            i11 = i20;
        } else {
            if (dimensionBehaviour2 == dimensionBehaviour3 && i21 == 0) {
                i21 = 3;
            }
            if (dimensionBehaviour4 == dimensionBehaviour3 && i23 == 0) {
                i23 = 3;
            }
            if (dimensionBehaviour2 == dimensionBehaviour3 && dimensionBehaviour4 == dimensionBehaviour3 && i21 == 3 && i23 == 3) {
                m1239E0(z8, z9, z17, z18);
            } else if (dimensionBehaviour2 == dimensionBehaviour3 && i21 == 3) {
                this.f1420p = 0;
                i11 = (int) (f9 * i17);
                if (dimensionBehaviour4 != dimensionBehaviour3) {
                    i9 = 4;
                    i10 = i23;
                } else {
                    i9 = i21;
                    i10 = i23;
                    i12 = i22;
                    z12 = true;
                    int[] iArr = this.f1402g;
                    iArr[0] = i9;
                    iArr[1] = i10;
                    if (z12) {
                    }
                    DimensionBehaviour dimensionBehaviour5 = this.f1368E[0];
                    DimensionBehaviour dimensionBehaviour6 = DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour5 == dimensionBehaviour6) {
                    }
                    boolean z19 = !this.f1365B.m1226k();
                    if (this.f1390a != 2) {
                    }
                    if (this.f1392b == 2) {
                    }
                }
            } else if (dimensionBehaviour4 == dimensionBehaviour3 && i23 == 3) {
                this.f1420p = 1;
                if (i19 == -1) {
                    this.f1422q = 1.0f / f9;
                }
                int i24 = (int) (this.f1422q * i15);
                if (dimensionBehaviour2 != dimensionBehaviour3) {
                    i10 = 4;
                    i12 = i24;
                    i9 = i21;
                    i11 = i20;
                    z12 = false;
                    int[] iArr2 = this.f1402g;
                    iArr2[0] = i9;
                    iArr2[1] = i10;
                    boolean z20 = z12 && ((i14 = this.f1420p) == 0 || i14 == -1);
                    DimensionBehaviour dimensionBehaviour52 = this.f1368E[0];
                    DimensionBehaviour dimensionBehaviour62 = DimensionBehaviour.WRAP_CONTENT;
                    boolean z21 = dimensionBehaviour52 == dimensionBehaviour62 && (this instanceof C0292e);
                    boolean z192 = !this.f1365B.m1226k();
                    if (this.f1390a != 2) {
                        ConstraintWidget constraintWidget3 = this.f1369F;
                        if (constraintWidget3 != null) {
                            c0285c3 = c0285c;
                            solverVariableM1209r = c0285c3.m1209r(constraintWidget3.f1432w);
                        } else {
                            c0285c3 = c0285c;
                            solverVariableM1209r = null;
                        }
                        ConstraintWidget constraintWidget4 = this.f1369F;
                        z13 = z9;
                        solverVariable = solverVariableM1209r6;
                        dimensionBehaviour = dimensionBehaviour62;
                        solverVariable2 = solverVariableM1209r4;
                        solverVariable3 = solverVariableM1209r3;
                        m1272e(c0285c, z8, constraintWidget4 != null ? c0285c3.m1209r(constraintWidget4.f1430u) : null, solverVariableM1209r, this.f1368E[0], z21, this.f1430u, this.f1432w, this.f1374K, i11, this.f1385V, this.f1426s[0], this.f1389Z, z20, z10, i9, this.f1404h, this.f1406i, this.f1408j, z192);
                    } else {
                        dimensionBehaviour = dimensionBehaviour62;
                        z13 = z9;
                        solverVariable = solverVariableM1209r6;
                        solverVariable2 = solverVariableM1209r4;
                        solverVariable3 = solverVariableM1209r3;
                    }
                    if (this.f1392b == 2) {
                        return;
                    }
                    boolean z22 = this.f1368E[1] == dimensionBehaviour && (this instanceof C0292e);
                    boolean z23 = z12 && ((i13 = this.f1420p) == 1 || i13 == -1);
                    if (this.f1384U <= 0) {
                        c0285c2 = c0285c;
                    } else {
                        if (this.f1434y.m1221f().f18287b != 1) {
                            c0285c2 = c0285c;
                            SolverVariable solverVariable5 = solverVariable;
                            solverVariable4 = solverVariable2;
                            c0285c2.m1196e(solverVariable5, solverVariable4, m1282j(), 6);
                            ConstraintAnchor constraintAnchor = this.f1434y.f1338d;
                            if (constraintAnchor != null) {
                                c0285c2.m1196e(solverVariable5, c0285c2.m1209r(constraintAnchor), 0, 6);
                                z14 = false;
                            } else {
                                z14 = z192;
                            }
                            ConstraintWidget constraintWidget5 = this.f1369F;
                            SolverVariable solverVariableM1209r7 = constraintWidget5 == null ? c0285c2.m1209r(constraintWidget5.f1433x) : null;
                            ConstraintWidget constraintWidget6 = this.f1369F;
                            SolverVariable solverVariable6 = solverVariable4;
                            m1272e(c0285c, z13, constraintWidget6 == null ? c0285c2.m1209r(constraintWidget6.f1431v) : null, solverVariableM1209r7, this.f1368E[1], z22, this.f1431v, this.f1433x, this.f1375L, i12, this.f1386W, this.f1426s[1], this.f1391a0, z23, z11, i10, this.f1410k, this.f1412l, this.f1414m, z14);
                            if (z12) {
                                constraintWidget = this;
                            } else {
                                constraintWidget = this;
                                if (constraintWidget.f1420p == 1) {
                                    c0285c.m1203l(solverVariableM1209r5, solverVariable6, solverVariable3, solverVariableM1209r2, constraintWidget.f1422q, 6);
                                } else {
                                    c0285c.m1203l(solverVariable3, solverVariableM1209r2, solverVariableM1209r5, solverVariable6, constraintWidget.f1422q, 6);
                                }
                            }
                            if (constraintWidget.f1365B.m1226k()) {
                                return;
                            }
                            c0285c.m1193b(constraintWidget, constraintWidget.f1365B.m1224i().m1220e(), (float) Math.toRadians(constraintWidget.f1428t + 90.0f), constraintWidget.f1365B.m1219d());
                            return;
                        }
                        c0285c2 = c0285c;
                        this.f1434y.m1221f().m1374g(c0285c2);
                    }
                    solverVariable4 = solverVariable2;
                    z14 = z192;
                    ConstraintWidget constraintWidget52 = this.f1369F;
                    if (constraintWidget52 == null) {
                    }
                    ConstraintWidget constraintWidget62 = this.f1369F;
                    SolverVariable solverVariable62 = solverVariable4;
                    m1272e(c0285c, z13, constraintWidget62 == null ? c0285c2.m1209r(constraintWidget62.f1431v) : null, solverVariableM1209r7, this.f1368E[1], z22, this.f1431v, this.f1433x, this.f1375L, i12, this.f1386W, this.f1426s[1], this.f1391a0, z23, z11, i10, this.f1410k, this.f1412l, this.f1414m, z14);
                    if (z12) {
                    }
                    if (constraintWidget.f1365B.m1226k()) {
                    }
                } else {
                    i12 = i24;
                    i9 = i21;
                    i10 = i23;
                    i11 = i20;
                    z12 = true;
                    int[] iArr22 = this.f1402g;
                    iArr22[0] = i9;
                    iArr22[1] = i10;
                    if (z12) {
                    }
                    DimensionBehaviour dimensionBehaviour522 = this.f1368E[0];
                    DimensionBehaviour dimensionBehaviour622 = DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour522 == dimensionBehaviour622) {
                    }
                    boolean z1922 = !this.f1365B.m1226k();
                    if (this.f1390a != 2) {
                    }
                    if (this.f1392b == 2) {
                    }
                }
            }
            i9 = i21;
            i10 = i23;
            i11 = i20;
            i12 = i22;
            z12 = true;
            int[] iArr222 = this.f1402g;
            iArr222[0] = i9;
            iArr222[1] = i10;
            if (z12) {
            }
            DimensionBehaviour dimensionBehaviour5222 = this.f1368E[0];
            DimensionBehaviour dimensionBehaviour6222 = DimensionBehaviour.WRAP_CONTENT;
            if (dimensionBehaviour5222 == dimensionBehaviour6222) {
            }
            boolean z19222 = !this.f1365B.m1226k();
            if (this.f1390a != 2) {
            }
            if (this.f1392b == 2) {
            }
        }
        i12 = i22;
        z12 = false;
        int[] iArr2222 = this.f1402g;
        iArr2222[0] = i9;
        iArr2222[1] = i10;
        if (z12) {
        }
        DimensionBehaviour dimensionBehaviour52222 = this.f1368E[0];
        DimensionBehaviour dimensionBehaviour62222 = DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour52222 == dimensionBehaviour62222) {
        }
        boolean z192222 = !this.f1365B.m1226k();
        if (this.f1390a != 2) {
        }
        if (this.f1392b == 2) {
        }
    }

    /* renamed from: b0 */
    public void m1267b0(int i9) {
        this.f1371H = i9;
        int i10 = this.f1386W;
        if (i9 < i10) {
            this.f1371H = i10;
        }
    }

    /* renamed from: c */
    public boolean mo1268c() {
        return this.f1397d0 != 8;
    }

    /* renamed from: c0 */
    public void m1269c0(boolean z8) {
        this.f1418o = z8;
    }

    /* renamed from: d */
    public void mo1270d(int i9) {
        C0295h.m1367a(i9, this);
    }

    /* renamed from: d0 */
    public void m1271d0(float f9) {
        this.f1389Z = f9;
    }

    /* JADX WARN: Removed duplicated region for block: B:165:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02df A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02e1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:189:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01ac A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01c1  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m1272e(C0285c c0285c, boolean z8, SolverVariable solverVariable, SolverVariable solverVariable2, DimensionBehaviour dimensionBehaviour, boolean z9, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i9, int i10, int i11, int i12, float f9, boolean z10, boolean z11, int i13, int i14, int i15, float f10, boolean z12) {
        boolean z13;
        int iMin;
        int i16;
        int i17;
        boolean z14;
        int i18;
        SolverVariable solverVariableM1209r;
        SolverVariable solverVariableM1209r2;
        int i19;
        int i20;
        SolverVariable solverVariable3;
        int i21;
        int i22;
        SolverVariable solverVariable4;
        boolean z15;
        int i23;
        boolean z16;
        int i24;
        SolverVariable solverVariable5;
        SolverVariable solverVariable6;
        char c9;
        int i25;
        boolean z17;
        boolean z18;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        SolverVariable solverVariable9;
        boolean z19;
        boolean z20;
        int i26;
        int i27;
        int i28;
        int i29;
        boolean z21;
        int i30;
        SolverVariable solverVariableM1209r3 = c0285c.m1209r(constraintAnchor);
        SolverVariable solverVariableM1209r4 = c0285c.m1209r(constraintAnchor2);
        SolverVariable solverVariableM1209r5 = c0285c.m1209r(constraintAnchor.m1224i());
        SolverVariable solverVariableM1209r6 = c0285c.m1209r(constraintAnchor2.m1224i());
        if (c0285c.f1325g && constraintAnchor.m1221f().f18287b == 1 && constraintAnchor2.m1221f().f18287b == 1) {
            C0285c.m1185x();
            constraintAnchor.m1221f().m1374g(c0285c);
            constraintAnchor2.m1221f().m1374g(c0285c);
            if (z11 || !z8) {
                return;
            }
            c0285c.m1200i(solverVariable2, solverVariableM1209r4, 0, 6);
            return;
        }
        C0285c.m1185x();
        boolean zM1226k = constraintAnchor.m1226k();
        boolean zM1226k2 = constraintAnchor2.m1226k();
        boolean zM1226k3 = this.f1365B.m1226k();
        int i31 = zM1226k2 ? (zM1226k ? 1 : 0) + 1 : zM1226k ? 1 : 0;
        if (zM1226k3) {
            i31++;
        }
        int i32 = z10 ? 3 : i13;
        int i33 = C0287a.f1442b[dimensionBehaviour.ordinal()];
        boolean z22 = (i33 == 1 || i33 == 2 || i33 == 3 || i33 != 4 || i32 == 4) ? false : true;
        if (this.f1397d0 == 8) {
            iMin = 0;
            z13 = false;
        } else {
            z13 = z22;
            iMin = i10;
        }
        if (z12) {
            if (!zM1226k && !zM1226k2 && !zM1226k3) {
                c0285c.m1197f(solverVariableM1209r3, i9);
            } else if (zM1226k && !zM1226k2) {
                i16 = 6;
                c0285c.m1196e(solverVariableM1209r3, solverVariableM1209r5, constraintAnchor.m1219d(), 6);
            }
            i16 = 6;
        } else {
            i16 = 6;
        }
        if (z13) {
            i17 = i14;
            int i34 = i15;
            if (i17 == -2) {
                i17 = iMin;
            }
            if (i34 == -2) {
                i34 = iMin;
            }
            if (i17 > 0) {
                c0285c.m1200i(solverVariableM1209r4, solverVariableM1209r3, i17, 6);
                iMin = Math.max(iMin, i17);
            }
            if (i34 > 0) {
                c0285c.m1202k(solverVariableM1209r4, solverVariableM1209r3, i34, 6);
                iMin = Math.min(iMin, i34);
            }
            if (i32 != 1) {
                z14 = z13;
                if (i32 == 2) {
                    ConstraintAnchor.Type typeM1225j = constraintAnchor.m1225j();
                    ConstraintAnchor.Type type = ConstraintAnchor.Type.TOP;
                    if (typeM1225j != type) {
                        i18 = i31;
                        if (constraintAnchor.m1225j() != ConstraintAnchor.Type.BOTTOM) {
                            solverVariableM1209r = c0285c.m1209r(this.f1369F.mo1278h(ConstraintAnchor.Type.LEFT));
                            solverVariableM1209r2 = c0285c.m1209r(this.f1369F.mo1278h(ConstraintAnchor.Type.RIGHT));
                        }
                        i19 = i18;
                        i20 = i32;
                        solverVariable3 = solverVariableM1209r5;
                        i21 = iMin;
                        i22 = i34;
                        solverVariable4 = solverVariableM1209r6;
                        c0285c.m1195d(c0285c.m1210s().m1169j(solverVariableM1209r4, solverVariableM1209r3, solverVariableM1209r2, solverVariableM1209r, f10));
                        z15 = false;
                        i23 = 2;
                        if (z15) {
                            z16 = z15;
                        }
                    } else {
                        i18 = i31;
                    }
                    solverVariableM1209r = c0285c.m1209r(this.f1369F.mo1278h(type));
                    solverVariableM1209r2 = c0285c.m1209r(this.f1369F.mo1278h(ConstraintAnchor.Type.BOTTOM));
                    i19 = i18;
                    i20 = i32;
                    solverVariable3 = solverVariableM1209r5;
                    i21 = iMin;
                    i22 = i34;
                    solverVariable4 = solverVariableM1209r6;
                    c0285c.m1195d(c0285c.m1210s().m1169j(solverVariableM1209r4, solverVariableM1209r3, solverVariableM1209r2, solverVariableM1209r, f10));
                    z15 = false;
                    i23 = 2;
                    if (z15) {
                    }
                }
            } else if (z8) {
                c0285c.m1196e(solverVariableM1209r4, solverVariableM1209r3, iMin, 6);
                i19 = i31;
                i20 = i32;
                solverVariable4 = solverVariableM1209r6;
                solverVariable3 = solverVariableM1209r5;
                z14 = z13;
                i21 = iMin;
                i22 = i34;
                z15 = z14;
                i23 = 2;
                if (z15 || i19 == 2 || z10) {
                    z16 = z15;
                } else {
                    int iMax = Math.max(i17, i21);
                    if (i22 > 0) {
                        iMax = Math.min(i22, iMax);
                    }
                    c0285c.m1196e(solverVariableM1209r4, solverVariableM1209r3, iMax, 6);
                    z16 = false;
                }
            } else if (z11) {
                z14 = z13;
                c0285c.m1196e(solverVariableM1209r4, solverVariableM1209r3, iMin, 4);
            } else {
                z14 = z13;
                c0285c.m1196e(solverVariableM1209r4, solverVariableM1209r3, iMin, 1);
            }
            i19 = i31;
            i20 = i32;
            i22 = i34;
            solverVariable4 = solverVariableM1209r6;
            solverVariable3 = solverVariableM1209r5;
            i21 = iMin;
            z15 = z14;
            i23 = 2;
            if (z15) {
            }
        } else {
            if (z9) {
                c0285c.m1196e(solverVariableM1209r4, solverVariableM1209r3, 0, 3);
                if (i11 > 0) {
                    c0285c.m1200i(solverVariableM1209r4, solverVariableM1209r3, i11, 6);
                }
                if (i12 < Integer.MAX_VALUE) {
                    c0285c.m1202k(solverVariableM1209r4, solverVariableM1209r3, i12, 6);
                }
            } else {
                c0285c.m1196e(solverVariableM1209r4, solverVariableM1209r3, iMin, i16);
            }
            i17 = i14;
            i19 = i31;
            i20 = i32;
            solverVariable4 = solverVariableM1209r6;
            solverVariable3 = solverVariableM1209r5;
            z16 = z13;
            i23 = 2;
            i22 = i15;
        }
        if (!z12 || z11) {
            if (i19 >= i23 || !z8) {
                return;
            }
            c0285c.m1200i(solverVariableM1209r3, solverVariable, 0, 6);
            c0285c.m1200i(solverVariable2, solverVariableM1209r4, 0, 6);
            return;
        }
        if (zM1226k || zM1226k2 || zM1226k3) {
            i24 = 0;
            if (!zM1226k || zM1226k2) {
                if (!zM1226k && zM1226k2) {
                    c0285c.m1196e(solverVariableM1209r4, solverVariable4, -constraintAnchor2.m1219d(), 6);
                    if (z8) {
                        c0285c.m1200i(solverVariableM1209r3, solverVariable, 0, 5);
                    }
                } else if (zM1226k && zM1226k2) {
                    if (z16) {
                        solverVariable5 = solverVariable4;
                        c9 = 6;
                        if (z8 && i11 == 0) {
                            c0285c.m1200i(solverVariableM1209r4, solverVariableM1209r3, 0, 6);
                        }
                        int i35 = i20;
                        if (i35 == 0) {
                            if (i22 > 0 || i17 > 0) {
                                z21 = true;
                                i30 = 4;
                            } else {
                                i30 = 6;
                                z21 = false;
                            }
                            solverVariable6 = solverVariable3;
                            c0285c.m1196e(solverVariableM1209r3, solverVariable6, constraintAnchor.m1219d(), i30);
                            c0285c.m1196e(solverVariableM1209r4, solverVariable5, -constraintAnchor2.m1219d(), i30);
                            z18 = i22 > 0 || i17 > 0;
                            i25 = 5;
                            z17 = z21;
                        } else {
                            solverVariable6 = solverVariable3;
                            if (i35 == 1) {
                                i25 = 6;
                            } else if (i35 == 3) {
                                int i36 = (z10 || this.f1420p == -1 || i22 > 0) ? 4 : 6;
                                c0285c.m1196e(solverVariableM1209r3, solverVariable6, constraintAnchor.m1219d(), i36);
                                c0285c.m1196e(solverVariableM1209r4, solverVariable5, -constraintAnchor2.m1219d(), i36);
                                i25 = 5;
                            } else {
                                i25 = 5;
                                z18 = false;
                                z17 = z18;
                            }
                            z18 = true;
                            z17 = z18;
                        }
                    } else {
                        solverVariable5 = solverVariable4;
                        solverVariable6 = solverVariable3;
                        c9 = 6;
                        i25 = 5;
                        z17 = false;
                        z18 = true;
                    }
                    if (z18) {
                        solverVariable8 = solverVariable5;
                        solverVariable7 = solverVariable6;
                        solverVariable9 = solverVariableM1209r4;
                        c0285c.m1194c(solverVariableM1209r3, solverVariable6, constraintAnchor.m1219d(), f9, solverVariable5, solverVariableM1209r4, constraintAnchor2.m1219d(), i25);
                        boolean z23 = constraintAnchor.f1338d.f1336b instanceof C0289b;
                        boolean z24 = constraintAnchor2.f1338d.f1336b instanceof C0289b;
                        if (z23 && !z24) {
                            z19 = z8;
                            i26 = 6;
                            i27 = 5;
                            z20 = true;
                        } else if (!z23 && z24) {
                            z20 = z8;
                            i26 = 5;
                            i27 = 6;
                            z19 = true;
                        }
                        if (z17) {
                            i26 = 6;
                            i27 = 6;
                        }
                        if ((z16 && z19) || z17) {
                            c0285c.m1200i(solverVariableM1209r3, solverVariable7, constraintAnchor.m1219d(), i27);
                        }
                        if ((z16 && z20) || z17) {
                            c0285c.m1202k(solverVariable9, solverVariable8, -constraintAnchor2.m1219d(), i26);
                        }
                        i28 = 0;
                        i29 = 6;
                        if (z8) {
                            c0285c.m1200i(solverVariableM1209r3, solverVariable, 0, 6);
                        }
                        if (z8) {
                            return;
                        }
                        c0285c.m1200i(solverVariable2, solverVariable9, i28, i29);
                        return;
                    }
                    solverVariable7 = solverVariable6;
                    solverVariable8 = solverVariable5;
                    solverVariable9 = solverVariableM1209r4;
                    z19 = z8;
                    z20 = z19;
                    i26 = 5;
                    i27 = 5;
                    if (z17) {
                    }
                    if (z16) {
                        c0285c.m1200i(solverVariableM1209r3, solverVariable7, constraintAnchor.m1219d(), i27);
                        if (z16) {
                            c0285c.m1202k(solverVariable9, solverVariable8, -constraintAnchor2.m1219d(), i26);
                            i28 = 0;
                            i29 = 6;
                            if (z8) {
                            }
                        } else {
                            c0285c.m1202k(solverVariable9, solverVariable8, -constraintAnchor2.m1219d(), i26);
                            i28 = 0;
                            i29 = 6;
                            if (z8) {
                            }
                        }
                    } else {
                        c0285c.m1200i(solverVariableM1209r3, solverVariable7, constraintAnchor.m1219d(), i27);
                        if (z16) {
                        }
                    }
                    if (z8) {
                    }
                }
            } else if (z8) {
                c0285c.m1200i(solverVariable2, solverVariableM1209r4, 0, 5);
            }
        } else if (z8) {
            i24 = 0;
            c0285c.m1200i(solverVariable2, solverVariableM1209r4, 0, 5);
        } else {
            solverVariable9 = solverVariableM1209r4;
            i28 = 0;
            i29 = 6;
            if (z8) {
            }
        }
        i28 = i24;
        solverVariable9 = solverVariableM1209r4;
        i29 = 6;
        if (z8) {
        }
    }

    /* renamed from: e0 */
    public void m1273e0(int i9) {
        this.f1413l0 = i9;
    }

    /* renamed from: f */
    public void m1274f(ConstraintWidget constraintWidget, float f9, int i9) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        m1247J(type, constraintWidget, type, i9, 0);
        this.f1428t = f9;
    }

    /* renamed from: f0 */
    public void m1275f0(int i9, int i10) {
        this.f1374K = i9;
        int i11 = i10 - i9;
        this.f1370G = i11;
        int i12 = this.f1385V;
        if (i11 < i12) {
            this.f1370G = i12;
        }
    }

    /* renamed from: g */
    public void m1276g(C0285c c0285c) {
        c0285c.m1209r(this.f1430u);
        c0285c.m1209r(this.f1431v);
        c0285c.m1209r(this.f1432w);
        c0285c.m1209r(this.f1433x);
        if (this.f1384U > 0) {
            c0285c.m1209r(this.f1434y);
        }
    }

    /* renamed from: g0 */
    public void m1277g0(DimensionBehaviour dimensionBehaviour) {
        this.f1368E[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            m1313y0(this.f1387X);
        }
    }

    /* renamed from: h */
    public ConstraintAnchor mo1278h(ConstraintAnchor.Type type) {
        switch (C0287a.f1441a[type.ordinal()]) {
            case 1:
                return this.f1430u;
            case 2:
                return this.f1431v;
            case 3:
                return this.f1432w;
            case 4:
                return this.f1433x;
            case 5:
                return this.f1434y;
            case 6:
                return this.f1365B;
            case 7:
                return this.f1435z;
            case 8:
                return this.f1364A;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    /* renamed from: h0 */
    public void m1279h0(int i9, int i10, int i11, float f9) {
        this.f1398e = i9;
        this.f1404h = i10;
        this.f1406i = i11;
        this.f1408j = f9;
        if (f9 >= 1.0f || i9 != 0) {
            return;
        }
        this.f1398e = 2;
    }

    /* renamed from: i */
    public ArrayList<ConstraintAnchor> mo1280i() {
        return this.f1367D;
    }

    /* renamed from: i0 */
    public void m1281i0(float f9) {
        this.f1421p0[0] = f9;
    }

    /* renamed from: j */
    public int m1282j() {
        return this.f1384U;
    }

    /* renamed from: j0 */
    public void m1283j0(int i9) {
        this.f1426s[1] = i9;
    }

    /* renamed from: k */
    public float m1284k(int i9) {
        if (i9 == 0) {
            return this.f1389Z;
        }
        if (i9 == 1) {
            return this.f1391a0;
        }
        return -1.0f;
    }

    /* renamed from: k0 */
    public void m1285k0(int i9) {
        this.f1426s[0] = i9;
    }

    /* renamed from: l */
    public int m1286l() {
        return m1244H() + this.f1371H;
    }

    /* renamed from: l0 */
    public void m1287l0(int i9) {
        if (i9 < 0) {
            this.f1386W = 0;
        } else {
            this.f1386W = i9;
        }
    }

    /* renamed from: m */
    public Object m1288m() {
        return this.f1393b0;
    }

    /* renamed from: m0 */
    public void m1289m0(int i9) {
        if (i9 < 0) {
            this.f1385V = 0;
        } else {
            this.f1385V = i9;
        }
    }

    /* renamed from: n */
    public String m1290n() {
        return this.f1399e0;
    }

    /* renamed from: n0 */
    public void mo1291n0(int i9, int i10) {
        this.f1382S = i9;
        this.f1383T = i10;
    }

    /* renamed from: o */
    public DimensionBehaviour m1292o(int i9) {
        if (i9 == 0) {
            return m1300s();
        }
        if (i9 == 1) {
            return m1232B();
        }
        return null;
    }

    /* renamed from: o0 */
    public void m1293o0(int i9, int i10) {
        this.f1374K = i9;
        this.f1375L = i10;
    }

    /* renamed from: p */
    public int m1294p() {
        return this.f1378O + this.f1382S;
    }

    /* renamed from: p0 */
    public void m1295p0(ConstraintWidget constraintWidget) {
        this.f1369F = constraintWidget;
    }

    /* renamed from: q */
    public int m1296q() {
        return this.f1379P + this.f1383T;
    }

    /* renamed from: q0 */
    public void m1297q0(int i9, int i10) {
        if (i10 == 0) {
            this.f1376M = i9;
        } else if (i10 == 1) {
            this.f1377N = i9;
        }
    }

    /* renamed from: r */
    public int m1298r() {
        if (this.f1397d0 == 8) {
            return 0;
        }
        return this.f1371H;
    }

    /* renamed from: r0 */
    public void m1299r0(float f9) {
        this.f1391a0 = f9;
    }

    /* renamed from: s */
    public DimensionBehaviour m1300s() {
        return this.f1368E[0];
    }

    /* renamed from: s0 */
    public void m1301s0(int i9) {
        this.f1415m0 = i9;
    }

    /* renamed from: t */
    public int m1302t(int i9) {
        if (i9 == 0) {
            return m1236D();
        }
        if (i9 == 1) {
            return m1298r();
        }
        return 0;
    }

    /* renamed from: t0 */
    public void m1303t0(int i9, int i10) {
        this.f1375L = i9;
        int i11 = i10 - i9;
        this.f1371H = i11;
        int i12 = this.f1386W;
        if (i11 < i12) {
            this.f1371H = i12;
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.f1401f0 != null) {
            str = "type: " + this.f1401f0 + StringUtils.SPACE;
        } else {
            str = "";
        }
        sb.append(str);
        if (this.f1399e0 != null) {
            str2 = "id: " + this.f1399e0 + StringUtils.SPACE;
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.f1374K);
        sb.append(", ");
        sb.append(this.f1375L);
        sb.append(") - (");
        sb.append(this.f1370G);
        sb.append(" x ");
        sb.append(this.f1371H);
        sb.append(") wrap: (");
        sb.append(this.f1387X);
        sb.append(" x ");
        sb.append(this.f1388Y);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: u */
    public ConstraintWidget m1304u() {
        return this.f1369F;
    }

    /* renamed from: u0 */
    public void m1305u0(DimensionBehaviour dimensionBehaviour) {
        this.f1368E[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            m1267b0(this.f1388Y);
        }
    }

    /* renamed from: v */
    public int m1306v(int i9) {
        if (i9 == 0) {
            return this.f1376M;
        }
        if (i9 == 1) {
            return this.f1377N;
        }
        return 0;
    }

    /* renamed from: v0 */
    public void m1307v0(int i9, int i10, int i11, float f9) {
        this.f1400f = i9;
        this.f1410k = i10;
        this.f1412l = i11;
        this.f1414m = f9;
        if (f9 >= 1.0f || i9 != 0) {
            return;
        }
        this.f1400f = 2;
    }

    /* renamed from: w */
    public C5386c m1308w() {
        if (this.f1396d == null) {
            this.f1396d = new C5386c();
        }
        return this.f1396d;
    }

    /* renamed from: w0 */
    public void m1309w0(float f9) {
        this.f1421p0[1] = f9;
    }

    /* renamed from: x */
    public C5386c m1310x() {
        if (this.f1394c == null) {
            this.f1394c = new C5386c();
        }
        return this.f1394c;
    }

    /* renamed from: x0 */
    public void m1311x0(int i9) {
        this.f1397d0 = i9;
    }

    /* renamed from: y */
    public int m1312y() {
        return m1242G() + this.f1370G;
    }

    /* renamed from: y0 */
    public void m1313y0(int i9) {
        this.f1370G = i9;
        int i10 = this.f1385V;
        if (i9 < i10) {
            this.f1370G = i10;
        }
    }

    /* renamed from: z */
    public int m1314z() {
        return this.f1374K + this.f1382S;
    }

    /* renamed from: z0 */
    public void m1315z0(boolean z8) {
        this.f1416n = z8;
    }
}
