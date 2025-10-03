package p027c6;

import org.apache.commons.lang3.ClassUtils;
import p007a6.C0042f;

/* renamed from: c6.d */
/* loaded from: classes2.dex */
public class C0748d {
    /* renamed from: a */
    public static final void m3620a(boolean z8, Number number) {
        C0042f.m158e(number, "step");
        if (z8) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + number + ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }
}
