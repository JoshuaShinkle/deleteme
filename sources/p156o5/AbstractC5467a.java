package p156o5;

import android.content.SharedPreferences;
import p047d5.C4677a;
import p077g5.C4959a;

/* renamed from: o5.a */
/* loaded from: classes2.dex */
public abstract class AbstractC5467a {

    /* renamed from: a */
    public static final SharedPreferences f18386a = C4677a.m18710n().getSharedPreferences("PreferenceHelper", 0);

    /* renamed from: a */
    public static String m21109a(String str) {
        return (String) C4959a.m19235b(str, "key can't be null");
    }

    /* renamed from: b */
    public static boolean m21110b(String str, boolean z8) {
        return f18386a.getBoolean(m21109a(str), z8);
    }
}
