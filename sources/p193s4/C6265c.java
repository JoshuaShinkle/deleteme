package p193s4;

import android.content.Context;
import android.util.Log;
import p068f6.C4790b;

/* renamed from: s4.c */
/* loaded from: classes.dex */
public class C6265c {
    /* renamed from: a */
    public static void m24009a(Context context, int i9) {
        try {
            C4790b.m19022a(context, i9);
            Log.d("ShortcutBadgeWrapper", "update shortcut badge=" + i9);
        } catch (Exception e9) {
            Log.d("ShortcutBadgeWrapper", "update shortcut fail. error=" + e9.toString());
        }
    }
}
