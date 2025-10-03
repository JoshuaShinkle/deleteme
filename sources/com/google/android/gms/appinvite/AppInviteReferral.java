package com.google.android.gms.appinvite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.plus.PlusShare;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Deprecated
/* loaded from: classes2.dex */
public class AppInviteReferral {
    private AppInviteReferral() {
    }

    @Deprecated
    public static Intent addPlayStoreReferrerToIntent(Intent intent, Intent intent2) throws UnsupportedEncodingException {
        Bundle bundleZza = zza(intent);
        if (bundleZza != null && intent2 != null) {
            intent2.putExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE", bundleZza);
        }
        return intent2;
    }

    @Deprecated
    public static Intent addReferralDataToIntent(String str, String str2, Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.putExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE", zza(str, str2, false));
    }

    public static String getDeepLink(Intent intent) {
        Bundle bundleExtra;
        if (intent == null || (bundleExtra = intent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE")) == null) {
            return null;
        }
        return bundleExtra.getString("com.google.android.gms.appinvite.DEEP_LINK");
    }

    public static String getInvitationId(Intent intent) {
        Bundle bundleExtra;
        if (intent == null || (bundleExtra = intent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE")) == null) {
            return null;
        }
        return bundleExtra.getString("com.google.android.gms.appinvite.INVITATION_ID");
    }

    public static boolean hasReferral(Intent intent) {
        return (intent == null || intent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE") == null) ? false : true;
    }

    public static boolean isOpenedFromPlayStore(Intent intent) {
        return hasReferral(intent) && intent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE").getBoolean("com.google.android.gms.appinvite.OPENED_FROM_PLAY_STORE", false);
    }

    private static Bundle zza(String str, String str2, boolean z8) {
        Bundle bundle = new Bundle();
        bundle.putString("com.google.android.gms.appinvite.INVITATION_ID", str);
        if (str2 != null) {
            bundle.putString("com.google.android.gms.appinvite.DEEP_LINK", str2);
        }
        bundle.putBoolean("com.google.android.gms.appinvite.OPENED_FROM_PLAY_STORE", z8);
        return bundle;
    }

    private static Bundle zza(Intent intent) throws UnsupportedEncodingException {
        if (intent != null && intent.getAction().equals("com.android.vending.INSTALL_REFERRER") && intent.getStringExtra("referrer") != null) {
            String stringExtra = intent.getStringExtra("referrer");
            try {
                String strDecode = URLDecoder.decode(stringExtra, "UTF-8");
                String strValueOf = String.valueOf(strDecode);
                Uri uri = Uri.parse(strValueOf.length() != 0 ? "s://a.b.c?".concat(strValueOf) : new String("s://a.b.c?"));
                String queryParameter = uri.getQueryParameter("invitation_id");
                String queryParameter2 = uri.getQueryParameter(PlusShare.PARAM_CONTENT_DEEP_LINK_ID);
                if (queryParameter == null && queryParameter2 == null) {
                    String strValueOf2 = String.valueOf(strDecode);
                    Log.w("AppInviteRef", strValueOf2.length() != 0 ? "Missing  Referrer query params: ".concat(strValueOf2) : new String("Missing  Referrer query params: "));
                    return null;
                }
                return zza(queryParameter, queryParameter2, true);
            } catch (UnsupportedEncodingException unused) {
                String strValueOf3 = String.valueOf(stringExtra);
                Log.e("AppInviteRef", strValueOf3.length() != 0 ? "Error parsing Play Store referrer URL: ".concat(strValueOf3) : new String("Error parsing Play Store referrer URL: "));
            }
        }
        return null;
    }
}
