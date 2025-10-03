package p173q2;

import java.io.File;
import p116k4.C5154j;
import p190s1.C6250g;
import p209u2.C6383t;

/* renamed from: q2.d */
/* loaded from: classes.dex */
public class C6130d {
    /* renamed from: a */
    public static boolean m23488a(String str) {
        if (C6383t.m24517f(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (str.startsWith("file://")) {
            return true;
        }
        return str.startsWith(File.separator);
    }

    /* renamed from: b */
    public static C6250g m23489b(C6250g c6250g, String str) {
        if (m23488a(str)) {
            try {
                File file = new File(str);
                if (file.isFile()) {
                    c6250g.mo23520d0(new C6131e(file));
                }
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        }
        return c6250g;
    }
}
