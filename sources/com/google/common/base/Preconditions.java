package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.analytics.FirebaseAnalytics;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Preconditions {
    private Preconditions() {
    }

    private static String badElementIndex(int i9, int i10, String str) {
        if (i9 < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i9));
        }
        if (i10 >= 0) {
            return format("%s (%s) must be less than size (%s)", str, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        throw new IllegalArgumentException("negative size: " + i10);
    }

    private static String badPositionIndex(int i9, int i10, String str) {
        if (i9 < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i9));
        }
        if (i10 >= 0) {
            return format("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        throw new IllegalArgumentException("negative size: " + i10);
    }

    private static String badPositionIndexes(int i9, int i10, int i11) {
        return (i9 < 0 || i9 > i11) ? badPositionIndex(i9, i11, "start index") : (i10 < 0 || i10 > i11) ? badPositionIndex(i10, i11, "end index") : format("end index (%s) must not be less than start index (%s)", Integer.valueOf(i10), Integer.valueOf(i9));
    }

    public static void checkArgument(boolean z8) {
        if (!z8) {
            throw new IllegalArgumentException();
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i9, int i10) {
        return checkElementIndex(i9, i10, FirebaseAnalytics.Param.INDEX);
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8) {
        t8.getClass();
        return t8;
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i9, int i10) {
        return checkPositionIndex(i9, i10, FirebaseAnalytics.Param.INDEX);
    }

    public static void checkPositionIndexes(int i9, int i10, int i11) {
        if (i9 < 0 || i10 < i9 || i10 > i11) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i9, i10, i11));
        }
    }

    public static void checkState(boolean z8) {
        if (!z8) {
            throw new IllegalStateException();
        }
    }

    public static String format(String str, Object... objArr) {
        int iIndexOf;
        String strValueOf = String.valueOf(str);
        StringBuilder sb = new StringBuilder(strValueOf.length() + (objArr.length * 16));
        int i9 = 0;
        int i10 = 0;
        while (i9 < objArr.length && (iIndexOf = strValueOf.indexOf("%s", i10)) != -1) {
            sb.append((CharSequence) strValueOf, i10, iIndexOf);
            sb.append(objArr[i9]);
            i10 = iIndexOf + 2;
            i9++;
        }
        sb.append((CharSequence) strValueOf, i10, strValueOf.length());
        if (i9 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i9]);
            for (int i11 = i9 + 1; i11 < objArr.length; i11++) {
                sb.append(", ");
                sb.append(objArr[i11]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static void checkArgument(boolean z8, Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i9, int i10, String str) {
        if (i9 < 0 || i9 >= i10) {
            throw new IndexOutOfBoundsException(badElementIndex(i9, i10, str));
        }
        return i9;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i9, int i10, String str) {
        if (i9 < 0 || i9 > i10) {
            throw new IndexOutOfBoundsException(badPositionIndex(i9, i10, str));
        }
        return i9;
    }

    public static void checkState(boolean z8, Object obj) {
        if (!z8) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z8, String str, Object... objArr) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object... objArr) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, objArr));
    }

    public static void checkState(boolean z8, String str, Object... objArr) {
        if (!z8) {
            throw new IllegalStateException(format(str, objArr));
        }
    }

    public static void checkArgument(boolean z8, String str, char c9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, char c9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Character.valueOf(c9)));
    }

    public static void checkState(boolean z8, String str, char c9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Character.valueOf(c9)));
        }
    }

    public static void checkArgument(boolean z8, String str, int i9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, int i9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i9)));
    }

    public static void checkState(boolean z8, String str, int i9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Integer.valueOf(i9)));
        }
    }

    public static void checkArgument(boolean z8, String str, long j9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, long j9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Long.valueOf(j9)));
    }

    public static void checkState(boolean z8, String str, long j9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Long.valueOf(j9)));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj));
    }

    public static void checkState(boolean z8, String str, Object obj) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj));
        }
    }

    public static void checkArgument(boolean z8, String str, char c9, char c10) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c9), Character.valueOf(c10)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, char c9, char c10) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Character.valueOf(c9), Character.valueOf(c10)));
    }

    public static void checkState(boolean z8, String str, char c9, char c10) {
        if (!z8) {
            throw new IllegalStateException(format(str, Character.valueOf(c9), Character.valueOf(c10)));
        }
    }

    public static void checkArgument(boolean z8, String str, char c9, int i9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c9), Integer.valueOf(i9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, char c9, int i9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Character.valueOf(c9), Integer.valueOf(i9)));
    }

    public static void checkState(boolean z8, String str, char c9, int i9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Character.valueOf(c9), Integer.valueOf(i9)));
        }
    }

    public static void checkArgument(boolean z8, String str, char c9, long j9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c9), Long.valueOf(j9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, char c9, long j9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Character.valueOf(c9), Long.valueOf(j9)));
    }

    public static void checkState(boolean z8, String str, char c9, long j9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Character.valueOf(c9), Long.valueOf(j9)));
        }
    }

    public static void checkArgument(boolean z8, String str, char c9, Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c9), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, char c9, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Character.valueOf(c9), obj));
    }

    public static void checkState(boolean z8, String str, char c9, Object obj) {
        if (!z8) {
            throw new IllegalStateException(format(str, Character.valueOf(c9), obj));
        }
    }

    public static void checkArgument(boolean z8, String str, int i9, char c9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i9), Character.valueOf(c9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, int i9, char c9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i9), Character.valueOf(c9)));
    }

    public static void checkState(boolean z8, String str, int i9, char c9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Integer.valueOf(i9), Character.valueOf(c9)));
        }
    }

    public static void checkArgument(boolean z8, String str, int i9, int i10) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i9), Integer.valueOf(i10)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, int i9, int i10) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i9), Integer.valueOf(i10)));
    }

    public static void checkState(boolean z8, String str, int i9, int i10) {
        if (!z8) {
            throw new IllegalStateException(format(str, Integer.valueOf(i9), Integer.valueOf(i10)));
        }
    }

    public static void checkArgument(boolean z8, String str, int i9, long j9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i9), Long.valueOf(j9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, int i9, long j9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i9), Long.valueOf(j9)));
    }

    public static void checkState(boolean z8, String str, int i9, long j9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Integer.valueOf(i9), Long.valueOf(j9)));
        }
    }

    public static void checkArgument(boolean z8, String str, int i9, Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i9), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, int i9, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i9), obj));
    }

    public static void checkState(boolean z8, String str, int i9, Object obj) {
        if (!z8) {
            throw new IllegalStateException(format(str, Integer.valueOf(i9), obj));
        }
    }

    public static void checkArgument(boolean z8, String str, long j9, char c9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j9), Character.valueOf(c9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, long j9, char c9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Long.valueOf(j9), Character.valueOf(c9)));
    }

    public static void checkState(boolean z8, String str, long j9, char c9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Long.valueOf(j9), Character.valueOf(c9)));
        }
    }

    public static void checkArgument(boolean z8, String str, long j9, int i9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j9), Integer.valueOf(i9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, long j9, int i9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Long.valueOf(j9), Integer.valueOf(i9)));
    }

    public static void checkState(boolean z8, String str, long j9, int i9) {
        if (!z8) {
            throw new IllegalStateException(format(str, Long.valueOf(j9), Integer.valueOf(i9)));
        }
    }

    public static void checkArgument(boolean z8, String str, long j9, long j10) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j9), Long.valueOf(j10)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, long j9, long j10) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Long.valueOf(j9), Long.valueOf(j10)));
    }

    public static void checkState(boolean z8, String str, long j9, long j10) {
        if (!z8) {
            throw new IllegalStateException(format(str, Long.valueOf(j9), Long.valueOf(j10)));
        }
    }

    public static void checkArgument(boolean z8, String str, long j9, Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j9), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, long j9, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, Long.valueOf(j9), obj));
    }

    public static void checkState(boolean z8, String str, long j9, Object obj) {
        if (!z8) {
            throw new IllegalStateException(format(str, Long.valueOf(j9), obj));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj, char c9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj, Character.valueOf(c9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj, char c9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj, Character.valueOf(c9)));
    }

    public static void checkState(boolean z8, String str, Object obj, char c9) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj, Character.valueOf(c9)));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj, int i9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj, Integer.valueOf(i9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj, int i9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj, Integer.valueOf(i9)));
    }

    public static void checkState(boolean z8, String str, Object obj, int i9) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj, Integer.valueOf(i9)));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj, long j9) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj, Long.valueOf(j9)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj, long j9) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj, Long.valueOf(j9)));
    }

    public static void checkState(boolean z8, String str, Object obj, long j9) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj, Long.valueOf(j9)));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj, Object obj2) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj, obj2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj, Object obj2) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj, obj2));
    }

    public static void checkState(boolean z8, String str, Object obj, Object obj2) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj, Object obj2, Object obj3) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj, Object obj2, Object obj3) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3));
    }

    public static void checkState(boolean z8, String str, Object obj, Object obj2, Object obj3) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj, obj2, obj3));
        }
    }

    public static void checkArgument(boolean z8, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z8) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3, obj4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3, obj4));
    }

    public static void checkState(boolean z8, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z8) {
            throw new IllegalStateException(format(str, obj, obj2, obj3, obj4));
        }
    }
}
