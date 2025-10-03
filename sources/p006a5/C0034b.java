package p006a5;

import java.io.File;

/* renamed from: a5.b */
/* loaded from: classes2.dex */
public class C0034b {
    /* renamed from: a */
    public static void m138a(File file) {
        File[] fileArrListFiles;
        try {
            if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    m138a(file2);
                }
            }
            if (file.getName().contains("MixpanelAPI.Images.") || file.getName().contains("MP_IMG_")) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }
}
