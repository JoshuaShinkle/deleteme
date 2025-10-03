package com.google.firebase.platforminfo;

import p203t5.C6314b;

/* loaded from: classes2.dex */
public final class KotlinDetector {
    private KotlinDetector() {
    }

    public static String detectVersion() {
        try {
            return C6314b.f21307g.toString();
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }
}
