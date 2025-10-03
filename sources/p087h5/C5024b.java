package p087h5;

import java.io.File;

/* renamed from: h5.b */
/* loaded from: classes2.dex */
public final class C5024b {
    /* renamed from: a */
    public static boolean m19593a(File file) {
        boolean z8;
        if (file.isDirectory()) {
            z8 = true;
            for (File file2 : file.listFiles()) {
                z8 = m19593a(file2) && z8;
            }
        } else {
            z8 = true;
        }
        return file.delete() && z8;
    }
}
