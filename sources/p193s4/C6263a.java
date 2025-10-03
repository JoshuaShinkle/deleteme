package p193s4;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteReferral;

/* renamed from: s4.a */
/* loaded from: classes.dex */
public class C6263a {
    /* renamed from: a */
    public static Intent m24003a(CharSequence charSequence, Uri uri, CharSequence charSequence2) {
        return new AppInviteInvitation.IntentBuilder(charSequence).setDeepLink(uri).setMessage(charSequence2).build();
    }

    /* renamed from: b */
    public static Intent m24004b(Intent intent, Intent intent2) {
        return AppInviteReferral.addPlayStoreReferrerToIntent(intent, intent2);
    }

    /* renamed from: c */
    public static String m24005c(Intent intent) {
        return AppInviteReferral.getDeepLink(intent);
    }

    /* renamed from: d */
    public static String[] m24006d(int i9, Intent intent) {
        return AppInviteInvitation.getInvitationIds(i9, intent);
    }

    /* renamed from: e */
    public static boolean m24007e(Intent intent) {
        return AppInviteReferral.hasReferral(intent);
    }
}
