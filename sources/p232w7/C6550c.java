package p232w7;

import p009a8.C0055a;
import p259z7.C6837a;
import p259z7.C6838b;

/* renamed from: w7.c */
/* loaded from: classes.dex */
public class C6550c extends C6837a {
    public C6550c(C0055a c0055a) {
        super(c0055a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName() + " Content [");
        for (C6838b c6838b : m25545b()) {
            sb.append('(');
            sb.append(c6838b.m25555h());
            sb.append(':');
            StringBuilder sb2 = new StringBuilder();
            for (String str : c6838b.m25554g()) {
                if (sb2.length() > 0) {
                    sb.append(',');
                }
                sb2.append(str);
            }
            if (sb2.length() == 0) {
                sb2.append("NOT SET");
            }
            sb.append((CharSequence) sb2);
            sb.append(')');
        }
        sb.append(']');
        return sb.toString();
    }
}
