package com.google.android.datatransport.runtime.dagger.internal;

/* loaded from: classes.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static <T> void checkBuilderRequirement(T t8, Class<T> cls) {
        if (t8 != null) {
            return;
        }
        throw new IllegalStateException(cls.getCanonicalName() + " must be set");
    }

    public static <T> T checkNotNull(T t8) {
        t8.getClass();
        return t8;
    }

    public static <T> T checkNotNull(T t8, String str) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(str);
    }

    public static <T> T checkNotNull(T t8, String str, Object obj) {
        String strValueOf;
        if (t8 != null) {
            return t8;
        }
        if (str.contains("%s")) {
            if (str.indexOf("%s") == str.lastIndexOf("%s")) {
                if (obj instanceof Class) {
                    strValueOf = ((Class) obj).getCanonicalName();
                } else {
                    strValueOf = String.valueOf(obj);
                }
                throw new NullPointerException(str.replace("%s", strValueOf));
            }
            throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
        }
        throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
    }
}
