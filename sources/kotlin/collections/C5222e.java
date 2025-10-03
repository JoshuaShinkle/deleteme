package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.List;
import p007a6.C0042f;

/* renamed from: kotlin.collections.e */
/* loaded from: classes2.dex */
public class C5222e extends C5221d {
    /* renamed from: b */
    public static final <T> List<T> m20379b(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        List<T> listM20394a = C5224g.m20394a(tArr);
        C0042f.m157d(listM20394a, "asList(...)");
        return listM20394a;
    }

    /* renamed from: c */
    public static final byte[] m20380c(byte[] bArr, byte[] bArr2, int i9, int i10, int i11) {
        C0042f.m158e(bArr, "<this>");
        C0042f.m158e(bArr2, FirebaseAnalytics.Param.DESTINATION);
        System.arraycopy(bArr, i10, bArr2, i9, i11 - i10);
        return bArr2;
    }

    /* renamed from: d */
    public static /* synthetic */ byte[] m20381d(byte[] bArr, byte[] bArr2, int i9, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i9 = 0;
        }
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        if ((i12 & 8) != 0) {
            i11 = bArr.length;
        }
        return m20380c(bArr, bArr2, i9, i10, i11);
    }

    /* renamed from: e */
    public static final byte[] m20382e(byte[] bArr, int i9, int i10) {
        C0042f.m158e(bArr, "<this>");
        C5220c.m20378a(i10, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i9, i10);
        C0042f.m157d(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    /* renamed from: f */
    public static final <T> void m20383f(T[] tArr, T t8, int i9, int i10) {
        C0042f.m158e(tArr, "<this>");
        Arrays.fill(tArr, i9, i10, t8);
    }

    /* renamed from: g */
    public static /* synthetic */ void m20384g(Object[] objArr, Object obj, int i9, int i10, int i11, Object obj2) {
        if ((i11 & 2) != 0) {
            i9 = 0;
        }
        if ((i11 & 4) != 0) {
            i10 = objArr.length;
        }
        m20383f(objArr, obj, i9, i10);
    }
}
