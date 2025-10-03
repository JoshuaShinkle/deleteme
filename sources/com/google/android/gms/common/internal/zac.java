package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.base.C3455R;
import com.google.android.gms.common.C3456R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;
import p132m.C5308g;
import p251z.C6796b;

/* loaded from: classes2.dex */
public final class zac {
    private static final C5308g<String, String> zaa = new C5308g<>();
    private static Locale zab;

    public static String zaa(Context context, int i9) {
        Resources resources = context.getResources();
        switch (i9) {
            case 1:
                return resources.getString(C3455R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(C3455R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(C3455R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zaa(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zaa(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i9);
                Log.e("GoogleApiAvailability", sb.toString());
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zaa(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zaa(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String zab(Context context, int i9) {
        String strZaa = i9 == 6 ? zaa(context, "common_google_play_services_resolution_required_title") : zaa(context, i9);
        return strZaa == null ? context.getResources().getString(C3455R.string.common_google_play_services_notification_ticker) : strZaa;
    }

    public static String zac(Context context, int i9) {
        Resources resources = context.getResources();
        String strZab = zab(context);
        if (i9 == 1) {
            return resources.getString(C3455R.string.common_google_play_services_install_text, strZab);
        }
        if (i9 == 2) {
            return DeviceProperties.isWearableWithoutPlayStore(context) ? resources.getString(C3455R.string.common_google_play_services_wear_update_text) : resources.getString(C3455R.string.common_google_play_services_update_text, strZab);
        }
        if (i9 == 3) {
            return resources.getString(C3455R.string.common_google_play_services_enable_text, strZab);
        }
        if (i9 == 5) {
            return zaa(context, "common_google_play_services_invalid_account_text", strZab);
        }
        if (i9 == 7) {
            return zaa(context, "common_google_play_services_network_error_text", strZab);
        }
        if (i9 == 9) {
            return resources.getString(C3455R.string.common_google_play_services_unsupported_text, strZab);
        }
        if (i9 == 20) {
            return zaa(context, "common_google_play_services_restricted_profile_text", strZab);
        }
        switch (i9) {
            case 16:
                return zaa(context, "common_google_play_services_api_unavailable_text", strZab);
            case 17:
                return zaa(context, "common_google_play_services_sign_in_failed_text", strZab);
            case 18:
                return resources.getString(C3455R.string.common_google_play_services_updating_text, strZab);
            default:
                return resources.getString(C3456R.string.common_google_play_services_unknown_issue, strZab);
        }
    }

    public static String zad(Context context, int i9) {
        return (i9 == 6 || i9 == 19) ? zaa(context, "common_google_play_services_resolution_required_text", zab(context)) : zac(context, i9);
    }

    public static String zae(Context context, int i9) {
        Resources resources = context.getResources();
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? resources.getString(R.string.ok) : resources.getString(C3455R.string.common_google_play_services_enable_button) : resources.getString(C3455R.string.common_google_play_services_update_button) : resources.getString(C3455R.string.common_google_play_services_install_button);
    }

    private static String zab(Context context) {
        String packageName = context.getPackageName();
        try {
            return Wrappers.packageManager(context).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    private static String zaa(Context context, String str, String str2) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        String strZaa = zaa(context, str);
        if (strZaa == null) {
            strZaa = resources.getString(C3456R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, strZaa, str2);
    }

    private static String zaa(Context context, String str) {
        C5308g<String, String> c5308g = zaa;
        synchronized (c5308g) {
            Locale localeM25353b = C6796b.m25350a(context.getResources().getConfiguration()).m25353b(0);
            if (!localeM25353b.equals(zab)) {
                c5308g.clear();
                zab = localeM25353b;
            }
            String str2 = c5308g.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String strValueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", strValueOf.length() != 0 ? "Missing resource: ".concat(strValueOf) : new String("Missing resource: "));
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String strValueOf2 = String.valueOf(str);
                Log.w("GoogleApiAvailability", strValueOf2.length() != 0 ? "Got empty resource: ".concat(strValueOf2) : new String("Got empty resource: "));
                return null;
            }
            c5308g.put(str, string);
            return string;
        }
    }

    public static String zaa(Context context) {
        return context.getResources().getString(C3455R.string.common_google_play_services_notification_channel_name);
    }
}
