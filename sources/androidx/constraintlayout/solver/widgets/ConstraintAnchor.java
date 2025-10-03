package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import p141n.C5341a;

/* loaded from: classes.dex */
public class ConstraintAnchor {

    /* renamed from: b */
    public final ConstraintWidget f1336b;

    /* renamed from: c */
    public final Type f1337c;

    /* renamed from: d */
    public ConstraintAnchor f1338d;

    /* renamed from: j */
    public SolverVariable f1344j;

    /* renamed from: a */
    public C0296i f1335a = new C0296i(this);

    /* renamed from: e */
    public int f1339e = 0;

    /* renamed from: f */
    public int f1340f = -1;

    /* renamed from: g */
    public Strength f1341g = Strength.NONE;

    /* renamed from: h */
    public ConnectionType f1342h = ConnectionType.RELAXED;

    /* renamed from: i */
    public int f1343i = 0;

    public enum ConnectionType {
        RELAXED,
        STRICT
    }

    public enum Strength {
        NONE,
        STRONG,
        WEAK
    }

    public enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintAnchor$a */
    public static /* synthetic */ class C0286a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f1362a;

        static {
            int[] iArr = new int[Type.values().length];
            f1362a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1362a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1362a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1362a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1362a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1362a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1362a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1362a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1362a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f1336b = constraintWidget;
        this.f1337c = type;
    }

    /* renamed from: a */
    public boolean m1216a(ConstraintAnchor constraintAnchor, int i9, int i10, Strength strength, int i11, boolean z8) {
        if (constraintAnchor == null) {
            this.f1338d = null;
            this.f1339e = 0;
            this.f1340f = -1;
            this.f1341g = Strength.NONE;
            this.f1343i = 2;
            return true;
        }
        if (!z8 && !m1227l(constraintAnchor)) {
            return false;
        }
        this.f1338d = constraintAnchor;
        if (i9 > 0) {
            this.f1339e = i9;
        } else {
            this.f1339e = 0;
        }
        this.f1340f = i10;
        this.f1341g = strength;
        this.f1343i = i11;
        return true;
    }

    /* renamed from: b */
    public boolean m1217b(ConstraintAnchor constraintAnchor, int i9, Strength strength, int i10) {
        return m1216a(constraintAnchor, i9, -1, strength, i10, false);
    }

    /* renamed from: c */
    public int m1218c() {
        return this.f1343i;
    }

    /* renamed from: d */
    public int m1219d() {
        ConstraintAnchor constraintAnchor;
        if (this.f1336b.m1234C() == 8) {
            return 0;
        }
        return (this.f1340f <= -1 || (constraintAnchor = this.f1338d) == null || constraintAnchor.f1336b.m1234C() != 8) ? this.f1339e : this.f1340f;
    }

    /* renamed from: e */
    public ConstraintWidget m1220e() {
        return this.f1336b;
    }

    /* renamed from: f */
    public C0296i m1221f() {
        return this.f1335a;
    }

    /* renamed from: g */
    public SolverVariable m1222g() {
        return this.f1344j;
    }

    /* renamed from: h */
    public Strength m1223h() {
        return this.f1341g;
    }

    /* renamed from: i */
    public ConstraintAnchor m1224i() {
        return this.f1338d;
    }

    /* renamed from: j */
    public Type m1225j() {
        return this.f1337c;
    }

    /* renamed from: k */
    public boolean m1226k() {
        return this.f1338d != null;
    }

    /* renamed from: l */
    public boolean m1227l(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeM1225j = constraintAnchor.m1225j();
        Type type = this.f1337c;
        if (typeM1225j == type) {
            return type != Type.BASELINE || (constraintAnchor.m1220e().m1246I() && m1220e().m1246I());
        }
        switch (C0286a.f1362a[type.ordinal()]) {
            case 1:
                return (typeM1225j == Type.BASELINE || typeM1225j == Type.CENTER_X || typeM1225j == Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z8 = typeM1225j == Type.LEFT || typeM1225j == Type.RIGHT;
                if (constraintAnchor.m1220e() instanceof C0294g) {
                    return z8 || typeM1225j == Type.CENTER_X;
                }
                return z8;
            case 4:
            case 5:
                boolean z9 = typeM1225j == Type.TOP || typeM1225j == Type.BOTTOM;
                if (constraintAnchor.m1220e() instanceof C0294g) {
                    return z9 || typeM1225j == Type.CENTER_Y;
                }
                return z9;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f1337c.name());
        }
    }

    /* renamed from: m */
    public void m1228m() {
        this.f1338d = null;
        this.f1339e = 0;
        this.f1340f = -1;
        this.f1341g = Strength.STRONG;
        this.f1343i = 0;
        this.f1342h = ConnectionType.RELAXED;
        this.f1335a.mo1372e();
    }

    /* renamed from: n */
    public void m1229n(C5341a c5341a) {
        SolverVariable solverVariable = this.f1344j;
        if (solverVariable == null) {
            this.f1344j = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.m1142d();
        }
    }

    public String toString() {
        return this.f1336b.m1290n() + ":" + this.f1337c.toString();
    }
}
