package com.google.android.gms.internal.measurement;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class zzdy {
    public static String zza(@NullableDecl String str, @NullableDecl Object... objArr) {
        int iIndexOf;
        String strValueOf = String.valueOf(str);
        int i9 = 0;
        for (int i10 = 0; i10 < objArr.length; i10++) {
            objArr[i10] = zza(objArr[i10]);
        }
        StringBuilder sb = new StringBuilder(strValueOf.length() + (objArr.length * 16));
        int i11 = 0;
        while (i9 < objArr.length && (iIndexOf = strValueOf.indexOf("%s", i11)) != -1) {
            sb.append((CharSequence) strValueOf, i11, iIndexOf);
            sb.append(objArr[i9]);
            i11 = iIndexOf + 2;
            i9++;
        }
        sb.append((CharSequence) strValueOf, i11, strValueOf.length());
        if (i9 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i9]);
            for (int i12 = i9 + 1; i12 < objArr.length; i12++) {
                sb.append(", ");
                sb.append(objArr[i12]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String zza(@NullableDecl Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e9) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            StringBuilder sb = new StringBuilder(name.length() + 1 + String.valueOf(hexString).length());
            sb.append(name);
            sb.append('@');
            sb.append(hexString);
            String string = sb.toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(string);
            logger.logp(level, "com.google.common.base.Strings", "lenientToString", strValueOf.length() != 0 ? "Exception during lenientFormat for ".concat(strValueOf) : new String("Exception during lenientFormat for "), (Throwable) e9);
            String name2 = e9.getClass().getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 9 + name2.length());
            sb2.append("<");
            sb2.append(string);
            sb2.append(" threw ");
            sb2.append(name2);
            sb2.append(">");
            return sb2.toString();
        }
    }
}
