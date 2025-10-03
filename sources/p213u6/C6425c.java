package p213u6;

import okio.SegmentedByteString;
import p007a6.C0042f;

/* renamed from: u6.c */
/* loaded from: classes.dex */
public final class C6425c {
    /* renamed from: a */
    public static final int m24585a(int[] iArr, int i9, int i10, int i11) {
        C0042f.m158e(iArr, "<this>");
        int i12 = i11 - 1;
        while (i10 <= i12) {
            int i13 = (i10 + i12) >>> 1;
            int i14 = iArr[i13];
            if (i14 < i9) {
                i10 = i13 + 1;
            } else {
                if (i14 <= i9) {
                    return i13;
                }
                i12 = i13 - 1;
            }
        }
        return (-i10) - 1;
    }

    /* renamed from: b */
    public static final int m24586b(SegmentedByteString segmentedByteString, int i9) {
        C0042f.m158e(segmentedByteString, "<this>");
        int iM24585a = m24585a(segmentedByteString.m21904w(), i9 + 1, 0, segmentedByteString.m21905x().length);
        return iM24585a >= 0 ? iM24585a : ~iM24585a;
    }
}
