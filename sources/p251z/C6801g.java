package p251z;

import android.content.Context;
import android.os.UserManager;

/* renamed from: z.g */
/* loaded from: classes.dex */
public class C6801g {
    /* renamed from: a */
    public static boolean m25357a(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}
