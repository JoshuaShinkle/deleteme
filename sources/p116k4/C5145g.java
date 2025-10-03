package p116k4;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.cyberlink.you.Globals;

/* renamed from: k4.g */
/* loaded from: classes.dex */
public class C5145g {
    /* renamed from: a */
    public static void m20042a(String str) {
        ((ClipboardManager) Globals.m7388i0().getBaseContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("U", str));
    }
}
