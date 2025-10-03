package p116k4;

import android.content.Context;
import android.content.SharedPreferences;
import com.cyberlink.you.Globals;
import java.util.Locale;

/* renamed from: k4.v */
/* loaded from: classes.dex */
public final class C5186v {
    /* renamed from: a */
    public static boolean m20263a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        if (sharedPreferences == null) {
            return false;
        }
        String language = Locale.getDefault().getLanguage();
        String string = sharedPreferences.getString("last_language", null);
        m20264b(context);
        if (string == null || language.equals(string)) {
            return false;
        }
        Globals.m7388i0().m7416D3(true);
        return true;
    }

    /* renamed from: b */
    public static void m20264b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putString("last_language", Locale.getDefault().getLanguage()).commit();
    }
}
