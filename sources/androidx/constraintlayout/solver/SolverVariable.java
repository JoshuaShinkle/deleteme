package androidx.constraintlayout.solver;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

/* loaded from: classes.dex */
public class SolverVariable {

    /* renamed from: k */
    public static int f1285k = 1;

    /* renamed from: a */
    public String f1286a;

    /* renamed from: e */
    public float f1290e;

    /* renamed from: g */
    public Type f1292g;

    /* renamed from: b */
    public int f1287b = -1;

    /* renamed from: c */
    public int f1288c = -1;

    /* renamed from: d */
    public int f1289d = 0;

    /* renamed from: f */
    public float[] f1291f = new float[7];

    /* renamed from: h */
    public C0284b[] f1293h = new C0284b[8];

    /* renamed from: i */
    public int f1294i = 0;

    /* renamed from: j */
    public int f1295j = 0;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.f1292g = type;
    }

    /* renamed from: b */
    public static void m1139b() {
        f1285k++;
    }

    /* renamed from: a */
    public final void m1140a(C0284b c0284b) {
        int i9 = 0;
        while (true) {
            int i10 = this.f1294i;
            if (i9 >= i10) {
                C0284b[] c0284bArr = this.f1293h;
                if (i10 >= c0284bArr.length) {
                    this.f1293h = (C0284b[]) Arrays.copyOf(c0284bArr, c0284bArr.length * 2);
                }
                C0284b[] c0284bArr2 = this.f1293h;
                int i11 = this.f1294i;
                c0284bArr2[i11] = c0284b;
                this.f1294i = i11 + 1;
                return;
            }
            if (this.f1293h[i9] == c0284b) {
                return;
            } else {
                i9++;
            }
        }
    }

    /* renamed from: c */
    public final void m1141c(C0284b c0284b) {
        int i9 = this.f1294i;
        for (int i10 = 0; i10 < i9; i10++) {
            if (this.f1293h[i10] == c0284b) {
                for (int i11 = 0; i11 < (i9 - i10) - 1; i11++) {
                    C0284b[] c0284bArr = this.f1293h;
                    int i12 = i10 + i11;
                    c0284bArr[i12] = c0284bArr[i12 + 1];
                }
                this.f1294i--;
                return;
            }
        }
    }

    /* renamed from: d */
    public void m1142d() {
        this.f1286a = null;
        this.f1292g = Type.UNKNOWN;
        this.f1289d = 0;
        this.f1287b = -1;
        this.f1288c = -1;
        this.f1290e = BitmapDescriptorFactory.HUE_RED;
        this.f1294i = 0;
        this.f1295j = 0;
    }

    /* renamed from: e */
    public void m1143e(Type type, String str) {
        this.f1292g = type;
    }

    /* renamed from: f */
    public final void m1144f(C0284b c0284b) {
        int i9 = this.f1294i;
        for (int i10 = 0; i10 < i9; i10++) {
            C0284b c0284b2 = this.f1293h[i10];
            c0284b2.f1316d.m1158n(c0284b2, c0284b, false);
        }
        this.f1294i = 0;
    }

    public String toString() {
        return "" + this.f1286a;
    }
}
