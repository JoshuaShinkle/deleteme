package p052e0;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

/* renamed from: e0.o */
/* loaded from: classes.dex */
public class C4706o {
    /* renamed from: a */
    public static void m18840a(AccessibilityRecord accessibilityRecord, int i9) {
        accessibilityRecord.setMaxScrollX(i9);
    }

    /* renamed from: b */
    public static void m18841b(AccessibilityRecord accessibilityRecord, int i9) {
        accessibilityRecord.setMaxScrollY(i9);
    }

    /* renamed from: c */
    public static void m18842c(AccessibilityRecord accessibilityRecord, View view, int i9) {
        accessibilityRecord.setSource(view, i9);
    }
}
