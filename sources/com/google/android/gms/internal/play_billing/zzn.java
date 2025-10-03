package com.google.android.gms.internal.play_billing;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public final class zzn {
    public static String zza(String str, Object... objArr) {
        int length;
        int length2;
        int iIndexOf;
        String string;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            length = objArr.length;
            if (i10 >= length) {
                break;
            }
            Object obj = objArr[i10];
            if (obj == null) {
                string = "null";
            } else {
                try {
                    string = obj.toString();
                } catch (Exception e9) {
                    String str2 = obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(str2), (Throwable) e9);
                    string = "<" + str2 + " threw " + e9.getClass().getName() + ">";
                }
            }
            objArr[i10] = string;
            i10++;
        }
        StringBuilder sb = new StringBuilder(str.length() + (length * 16));
        int i11 = 0;
        while (true) {
            length2 = objArr.length;
            if (i9 >= length2 || (iIndexOf = str.indexOf("%s", i11)) == -1) {
                break;
            }
            sb.append((CharSequence) str, i11, iIndexOf);
            sb.append(objArr[i9]);
            i11 = iIndexOf + 2;
            i9++;
        }
        sb.append((CharSequence) str, i11, str.length());
        if (i9 < length2) {
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
}
