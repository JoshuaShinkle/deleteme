package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import p141n.C5341a;

/* renamed from: androidx.constraintlayout.solver.a */
/* loaded from: classes.dex */
public class C0283a {

    /* renamed from: b */
    public final C0284b f1303b;

    /* renamed from: c */
    public final C5341a f1304c;

    /* renamed from: a */
    public int f1302a = 0;

    /* renamed from: d */
    public int f1305d = 8;

    /* renamed from: e */
    public SolverVariable f1306e = null;

    /* renamed from: f */
    public int[] f1307f = new int[8];

    /* renamed from: g */
    public int[] f1308g = new int[8];

    /* renamed from: h */
    public float[] f1309h = new float[8];

    /* renamed from: i */
    public int f1310i = -1;

    /* renamed from: j */
    public int f1311j = -1;

    /* renamed from: k */
    public boolean f1312k = false;

    public C0283a(C0284b c0284b, C5341a c5341a) {
        this.f1303b = c0284b;
        this.f1304c = c5341a;
    }

    /* renamed from: a */
    public final void m1145a(SolverVariable solverVariable, float f9, boolean z8) {
        if (f9 == BitmapDescriptorFactory.HUE_RED) {
            return;
        }
        int i9 = this.f1310i;
        if (i9 == -1) {
            this.f1310i = 0;
            this.f1309h[0] = f9;
            this.f1307f[0] = solverVariable.f1287b;
            this.f1308g[0] = -1;
            solverVariable.f1295j++;
            solverVariable.m1140a(this.f1303b);
            this.f1302a++;
            if (this.f1312k) {
                return;
            }
            int i10 = this.f1311j + 1;
            this.f1311j = i10;
            int[] iArr = this.f1307f;
            if (i10 >= iArr.length) {
                this.f1312k = true;
                this.f1311j = iArr.length - 1;
                return;
            }
            return;
        }
        int i11 = -1;
        for (int i12 = 0; i9 != -1 && i12 < this.f1302a; i12++) {
            int i13 = this.f1307f[i9];
            int i14 = solverVariable.f1287b;
            if (i13 == i14) {
                float[] fArr = this.f1309h;
                float f10 = fArr[i9] + f9;
                fArr[i9] = f10;
                if (f10 == BitmapDescriptorFactory.HUE_RED) {
                    if (i9 == this.f1310i) {
                        this.f1310i = this.f1308g[i9];
                    } else {
                        int[] iArr2 = this.f1308g;
                        iArr2[i11] = iArr2[i9];
                    }
                    if (z8) {
                        solverVariable.m1141c(this.f1303b);
                    }
                    if (this.f1312k) {
                        this.f1311j = i9;
                    }
                    solverVariable.f1295j--;
                    this.f1302a--;
                    return;
                }
                return;
            }
            if (i13 < i14) {
                i11 = i9;
            }
            i9 = this.f1308g[i9];
        }
        int length = this.f1311j;
        int i15 = length + 1;
        if (this.f1312k) {
            int[] iArr3 = this.f1307f;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i15;
        }
        int[] iArr4 = this.f1307f;
        if (length >= iArr4.length && this.f1302a < iArr4.length) {
            int i16 = 0;
            while (true) {
                int[] iArr5 = this.f1307f;
                if (i16 >= iArr5.length) {
                    break;
                }
                if (iArr5[i16] == -1) {
                    length = i16;
                    break;
                }
                i16++;
            }
        }
        int[] iArr6 = this.f1307f;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i17 = this.f1305d * 2;
            this.f1305d = i17;
            this.f1312k = false;
            this.f1311j = length - 1;
            this.f1309h = Arrays.copyOf(this.f1309h, i17);
            this.f1307f = Arrays.copyOf(this.f1307f, this.f1305d);
            this.f1308g = Arrays.copyOf(this.f1308g, this.f1305d);
        }
        this.f1307f[length] = solverVariable.f1287b;
        this.f1309h[length] = f9;
        if (i11 != -1) {
            int[] iArr7 = this.f1308g;
            iArr7[length] = iArr7[i11];
            iArr7[i11] = length;
        } else {
            this.f1308g[length] = this.f1310i;
            this.f1310i = length;
        }
        solverVariable.f1295j++;
        solverVariable.m1140a(this.f1303b);
        this.f1302a++;
        if (!this.f1312k) {
            this.f1311j++;
        }
        int i18 = this.f1311j;
        int[] iArr8 = this.f1307f;
        if (i18 >= iArr8.length) {
            this.f1312k = true;
            this.f1311j = iArr8.length - 1;
        }
    }

    /* renamed from: b */
    public SolverVariable m1146b(C0285c c0285c) {
        int i9 = this.f1310i;
        SolverVariable solverVariable = null;
        float f9 = 0.0f;
        float f10 = 0.0f;
        boolean z8 = false;
        boolean z9 = false;
        SolverVariable solverVariable2 = null;
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            float[] fArr = this.f1309h;
            float f11 = fArr[i9];
            SolverVariable solverVariable3 = this.f1304c.f18194c[this.f1307f[i9]];
            if (f11 < BitmapDescriptorFactory.HUE_RED) {
                if (f11 > -0.001f) {
                    fArr[i9] = 0.0f;
                    solverVariable3.m1141c(this.f1303b);
                    f11 = 0.0f;
                }
            } else if (f11 < 0.001f) {
                fArr[i9] = 0.0f;
                solverVariable3.m1141c(this.f1303b);
                f11 = 0.0f;
            }
            if (f11 != BitmapDescriptorFactory.HUE_RED) {
                if (solverVariable3.f1292g == SolverVariable.Type.UNRESTRICTED) {
                    if (solverVariable2 == null || f9 > f11) {
                        boolean zM1155k = m1155k(solverVariable3, c0285c);
                        z8 = zM1155k;
                        f9 = f11;
                        solverVariable2 = solverVariable3;
                    } else if (!z8 && m1155k(solverVariable3, c0285c)) {
                        f9 = f11;
                        solverVariable2 = solverVariable3;
                        z8 = true;
                    }
                } else if (solverVariable2 == null && f11 < BitmapDescriptorFactory.HUE_RED) {
                    if (solverVariable == null || f10 > f11) {
                        boolean zM1155k2 = m1155k(solverVariable3, c0285c);
                        z9 = zM1155k2;
                        f10 = f11;
                        solverVariable = solverVariable3;
                    } else if (!z9 && m1155k(solverVariable3, c0285c)) {
                        f10 = f11;
                        solverVariable = solverVariable3;
                        z9 = true;
                    }
                }
            }
            i9 = this.f1308g[i9];
        }
        return solverVariable2 != null ? solverVariable2 : solverVariable;
    }

    /* renamed from: c */
    public final void m1147c() {
        int i9 = this.f1310i;
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            SolverVariable solverVariable = this.f1304c.f18194c[this.f1307f[i9]];
            if (solverVariable != null) {
                solverVariable.m1141c(this.f1303b);
            }
            i9 = this.f1308g[i9];
        }
        this.f1310i = -1;
        this.f1311j = -1;
        this.f1312k = false;
        this.f1302a = 0;
    }

    /* renamed from: d */
    public final boolean m1148d(SolverVariable solverVariable) {
        int i9 = this.f1310i;
        if (i9 == -1) {
            return false;
        }
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            if (this.f1307f[i9] == solverVariable.f1287b) {
                return true;
            }
            i9 = this.f1308g[i9];
        }
        return false;
    }

    /* renamed from: e */
    public void m1149e(float f9) {
        int i9 = this.f1310i;
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            float[] fArr = this.f1309h;
            fArr[i9] = fArr[i9] / f9;
            i9 = this.f1308g[i9];
        }
    }

    /* renamed from: f */
    public final float m1150f(SolverVariable solverVariable) {
        int i9 = this.f1310i;
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            if (this.f1307f[i9] == solverVariable.f1287b) {
                return this.f1309h[i9];
            }
            i9 = this.f1308g[i9];
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: g */
    public SolverVariable m1151g(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int i9 = this.f1310i;
        SolverVariable solverVariable2 = null;
        float f9 = 0.0f;
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            float f10 = this.f1309h[i9];
            if (f10 < BitmapDescriptorFactory.HUE_RED) {
                SolverVariable solverVariable3 = this.f1304c.f18194c[this.f1307f[i9]];
                if ((zArr == null || !zArr[solverVariable3.f1287b]) && solverVariable3 != solverVariable && (((type = solverVariable3.f1292g) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && f10 < f9)) {
                    f9 = f10;
                    solverVariable2 = solverVariable3;
                }
            }
            i9 = this.f1308g[i9];
        }
        return solverVariable2;
    }

    /* renamed from: h */
    public final SolverVariable m1152h(int i9) {
        int i10 = this.f1310i;
        for (int i11 = 0; i10 != -1 && i11 < this.f1302a; i11++) {
            if (i11 == i9) {
                return this.f1304c.f18194c[this.f1307f[i10]];
            }
            i10 = this.f1308g[i10];
        }
        return null;
    }

    /* renamed from: i */
    public final float m1153i(int i9) {
        int i10 = this.f1310i;
        for (int i11 = 0; i10 != -1 && i11 < this.f1302a; i11++) {
            if (i11 == i9) {
                return this.f1309h[i10];
            }
            i10 = this.f1308g[i10];
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: j */
    public void m1154j() {
        int i9 = this.f1310i;
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            float[] fArr = this.f1309h;
            fArr[i9] = fArr[i9] * (-1.0f);
            i9 = this.f1308g[i9];
        }
    }

    /* renamed from: k */
    public final boolean m1155k(SolverVariable solverVariable, C0285c c0285c) {
        return solverVariable.f1295j <= 1;
    }

    /* renamed from: l */
    public final void m1156l(SolverVariable solverVariable, float f9) {
        if (f9 == BitmapDescriptorFactory.HUE_RED) {
            m1157m(solverVariable, true);
            return;
        }
        int i9 = this.f1310i;
        if (i9 == -1) {
            this.f1310i = 0;
            this.f1309h[0] = f9;
            this.f1307f[0] = solverVariable.f1287b;
            this.f1308g[0] = -1;
            solverVariable.f1295j++;
            solverVariable.m1140a(this.f1303b);
            this.f1302a++;
            if (this.f1312k) {
                return;
            }
            int i10 = this.f1311j + 1;
            this.f1311j = i10;
            int[] iArr = this.f1307f;
            if (i10 >= iArr.length) {
                this.f1312k = true;
                this.f1311j = iArr.length - 1;
                return;
            }
            return;
        }
        int i11 = -1;
        for (int i12 = 0; i9 != -1 && i12 < this.f1302a; i12++) {
            int i13 = this.f1307f[i9];
            int i14 = solverVariable.f1287b;
            if (i13 == i14) {
                this.f1309h[i9] = f9;
                return;
            }
            if (i13 < i14) {
                i11 = i9;
            }
            i9 = this.f1308g[i9];
        }
        int length = this.f1311j;
        int i15 = length + 1;
        if (this.f1312k) {
            int[] iArr2 = this.f1307f;
            if (iArr2[length] != -1) {
                length = iArr2.length;
            }
        } else {
            length = i15;
        }
        int[] iArr3 = this.f1307f;
        if (length >= iArr3.length && this.f1302a < iArr3.length) {
            int i16 = 0;
            while (true) {
                int[] iArr4 = this.f1307f;
                if (i16 >= iArr4.length) {
                    break;
                }
                if (iArr4[i16] == -1) {
                    length = i16;
                    break;
                }
                i16++;
            }
        }
        int[] iArr5 = this.f1307f;
        if (length >= iArr5.length) {
            length = iArr5.length;
            int i17 = this.f1305d * 2;
            this.f1305d = i17;
            this.f1312k = false;
            this.f1311j = length - 1;
            this.f1309h = Arrays.copyOf(this.f1309h, i17);
            this.f1307f = Arrays.copyOf(this.f1307f, this.f1305d);
            this.f1308g = Arrays.copyOf(this.f1308g, this.f1305d);
        }
        this.f1307f[length] = solverVariable.f1287b;
        this.f1309h[length] = f9;
        if (i11 != -1) {
            int[] iArr6 = this.f1308g;
            iArr6[length] = iArr6[i11];
            iArr6[i11] = length;
        } else {
            this.f1308g[length] = this.f1310i;
            this.f1310i = length;
        }
        solverVariable.f1295j++;
        solverVariable.m1140a(this.f1303b);
        int i18 = this.f1302a + 1;
        this.f1302a = i18;
        if (!this.f1312k) {
            this.f1311j++;
        }
        int[] iArr7 = this.f1307f;
        if (i18 >= iArr7.length) {
            this.f1312k = true;
        }
        if (this.f1311j >= iArr7.length) {
            this.f1312k = true;
            this.f1311j = iArr7.length - 1;
        }
    }

    /* renamed from: m */
    public final float m1157m(SolverVariable solverVariable, boolean z8) {
        if (this.f1306e == solverVariable) {
            this.f1306e = null;
        }
        int i9 = this.f1310i;
        if (i9 == -1) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int i10 = 0;
        int i11 = -1;
        while (i9 != -1 && i10 < this.f1302a) {
            if (this.f1307f[i9] == solverVariable.f1287b) {
                if (i9 == this.f1310i) {
                    this.f1310i = this.f1308g[i9];
                } else {
                    int[] iArr = this.f1308g;
                    iArr[i11] = iArr[i9];
                }
                if (z8) {
                    solverVariable.m1141c(this.f1303b);
                }
                solverVariable.f1295j--;
                this.f1302a--;
                this.f1307f[i9] = -1;
                if (this.f1312k) {
                    this.f1311j = i9;
                }
                return this.f1309h[i9];
            }
            i10++;
            i11 = i9;
            i9 = this.f1308g[i9];
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: n */
    public final void m1158n(C0284b c0284b, C0284b c0284b2, boolean z8) {
        int i9 = this.f1310i;
        while (true) {
            for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
                int i11 = this.f1307f[i9];
                SolverVariable solverVariable = c0284b2.f1313a;
                if (i11 == solverVariable.f1287b) {
                    float f9 = this.f1309h[i9];
                    m1157m(solverVariable, z8);
                    C0283a c0283a = c0284b2.f1316d;
                    int i12 = c0283a.f1310i;
                    for (int i13 = 0; i12 != -1 && i13 < c0283a.f1302a; i13++) {
                        m1145a(this.f1304c.f18194c[c0283a.f1307f[i12]], c0283a.f1309h[i12] * f9, z8);
                        i12 = c0283a.f1308g[i12];
                    }
                    c0284b.f1314b += c0284b2.f1314b * f9;
                    if (z8) {
                        c0284b2.f1313a.m1141c(c0284b);
                    }
                    i9 = this.f1310i;
                } else {
                    i9 = this.f1308g[i9];
                }
            }
            return;
        }
    }

    /* renamed from: o */
    public void m1159o(C0284b c0284b, C0284b[] c0284bArr) {
        int i9 = this.f1310i;
        while (true) {
            for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
                SolverVariable solverVariable = this.f1304c.f18194c[this.f1307f[i9]];
                if (solverVariable.f1288c != -1) {
                    float f9 = this.f1309h[i9];
                    m1157m(solverVariable, true);
                    C0284b c0284b2 = c0284bArr[solverVariable.f1288c];
                    if (!c0284b2.f1317e) {
                        C0283a c0283a = c0284b2.f1316d;
                        int i11 = c0283a.f1310i;
                        for (int i12 = 0; i11 != -1 && i12 < c0283a.f1302a; i12++) {
                            m1145a(this.f1304c.f18194c[c0283a.f1307f[i11]], c0283a.f1309h[i11] * f9, true);
                            i11 = c0283a.f1308g[i11];
                        }
                    }
                    c0284b.f1314b += c0284b2.f1314b * f9;
                    c0284b2.f1313a.m1141c(c0284b);
                    i9 = this.f1310i;
                } else {
                    i9 = this.f1308g[i9];
                }
            }
            return;
        }
    }

    public String toString() {
        int i9 = this.f1310i;
        String str = "";
        for (int i10 = 0; i9 != -1 && i10 < this.f1302a; i10++) {
            str = ((str + " -> ") + this.f1309h[i9] + " : ") + this.f1304c.f18194c[this.f1307f[i9]];
            i9 = this.f1308g[i9];
        }
        return str;
    }
}
