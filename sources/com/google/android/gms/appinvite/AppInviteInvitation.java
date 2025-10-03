package com.google.android.gms.appinvite;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* loaded from: classes2.dex */
public final class AppInviteInvitation {
    private static final String[] zzf = {"jpg", "jpeg", "png"};

    public static final class IntentBuilder {
        public static final int MAX_CALL_TO_ACTION_TEXT_LENGTH = 20;
        public static final int MAX_EMAIL_HTML_CONTENT = 512000;
        public static final int MAX_MESSAGE_LENGTH = 100;
        public static final int MIN_CALL_TO_ACTION_TEXT_LENGTH = 2;
        private final Intent intent;
        private String zzg;
        private String zzh;

        @Retention(RetentionPolicy.SOURCE)
        public @interface PlatformMode {
            public static final int PROJECT_PLATFORM_ANDROID = 2;
            public static final int PROJECT_PLATFORM_IOS = 1;
        }

        public IntentBuilder(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            Intent intent = new Intent("com.google.android.gms.appinvite.ACTION_APP_INVITE");
            this.intent = intent;
            intent.putExtra("com.google.android.gms.appinvite.TITLE", charSequence);
            intent.setPackage("com.google.android.gms");
        }

        public final Intent build() {
            if (!TextUtils.isEmpty(this.zzg)) {
                Preconditions.checkNotEmpty(this.zzh, "Email html content must be set when email subject is set.");
                Preconditions.checkArgument(this.intent.getData() == null, "Custom image must not be set when email html content is set.");
                Preconditions.checkArgument(TextUtils.isEmpty(this.intent.getCharSequenceExtra("com.google.android.gms.appinvite.BUTTON_TEXT")), "Call to action text must not be set when email html content is set.");
                this.intent.putExtra("com.google.android.gms.appinvite.EMAIL_SUBJECT", this.zzg);
                this.intent.putExtra("com.google.android.gms.appinvite.EMAIL_CONTENT", this.zzh);
            } else if (!TextUtils.isEmpty(this.zzh)) {
                throw new IllegalArgumentException("Email subject must be set when email html content is set.");
            }
            return this.intent;
        }

        public final IntentBuilder setAccount(Account account) {
            if (account == null || !"com.google".equals(account.type)) {
                this.intent.removeExtra("com.google.android.gms.appinvite.ACCOUNT_NAME");
            } else {
                this.intent.putExtra("com.google.android.gms.appinvite.ACCOUNT_NAME", account);
            }
            return this;
        }

        public final IntentBuilder setAdditionalReferralParameters(Map<String, String> map) {
            if (map != null) {
                this.intent.putExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI", AppInviteInvitation.zza(map));
            } else {
                this.intent.removeExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI");
            }
            return this;
        }

        public final IntentBuilder setAndroidMinimumVersionCode(int i9) {
            this.intent.putExtra("com.google.android.gms.appinvite.appMinimumVersionCode", i9);
            return this;
        }

        public final IntentBuilder setCallToActionText(CharSequence charSequence) {
            if (charSequence == null || charSequence.length() < 2 || charSequence.length() > 20) {
                throw new IllegalArgumentException(String.format("Text must be between %d and %d chars in length.", 2, 20));
            }
            this.intent.putExtra("com.google.android.gms.appinvite.BUTTON_TEXT", charSequence);
            return this;
        }

        public final IntentBuilder setCustomImage(Uri uri) {
            Preconditions.checkNotNull(uri);
            Preconditions.checkArgument(uri.isAbsolute(), "Image uri is not an absolute uri. Did you forget to add a scheme to the Uri?");
            String lowerCase = uri.getScheme().toLowerCase();
            boolean z8 = lowerCase.equals("android.resource") || lowerCase.equals(FirebaseAnalytics.Param.CONTENT) || lowerCase.equals("file");
            Preconditions.checkArgument(z8 || lowerCase.equals("http") || lowerCase.equals("https"), "Image uri must be a content URI with scheme \"android.resource\", \"content\" or \"file\", or a network url with scheme \"http\" or \"https\".");
            if (!z8) {
                String lastPathSegment = uri.getLastPathSegment();
                String lowerCase2 = null;
                if (lastPathSegment != null && lastPathSegment.lastIndexOf(".") != -1) {
                    lowerCase2 = lastPathSegment.substring(lastPathSegment.lastIndexOf(".") + 1, lastPathSegment.length()).toLowerCase();
                }
                Preconditions.checkArgument(TextUtils.isEmpty(lowerCase2) || AppInviteInvitation.zza(lowerCase2), String.valueOf(lowerCase2).concat(" images are not supported. Only jpg, jpeg, or png images are supported."));
            }
            this.intent.setData(uri.buildUpon().scheme(lowerCase).build());
            if (z8) {
                this.intent.addFlags(1);
            }
            return this;
        }

        public final IntentBuilder setDeepLink(Uri uri) {
            if (uri != null) {
                this.intent.putExtra("com.google.android.gms.appinvite.DEEP_LINK_URL", uri);
            } else {
                this.intent.removeExtra("com.google.android.gms.appinvite.DEEP_LINK_URL");
            }
            return this;
        }

        public final IntentBuilder setEmailHtmlContent(String str) {
            if (str != null && str.getBytes().length > 512000) {
                throw new IllegalArgumentException(String.format("Email html content must be %d bytes or less.", Integer.valueOf(MAX_EMAIL_HTML_CONTENT)));
            }
            this.zzh = str;
            return this;
        }

        public final IntentBuilder setEmailSubject(String str) {
            this.zzg = str;
            return this;
        }

        public final IntentBuilder setGoogleAnalyticsTrackingId(String str) {
            this.intent.putExtra("com.google.android.gms.appinvite.GOOGLE_ANALYTICS_TRACKING_ID", str);
            return this;
        }

        public final IntentBuilder setMessage(CharSequence charSequence) {
            if (charSequence != null && charSequence.length() > 100) {
                throw new IllegalArgumentException(String.format("Message must be %d chars or less.", 100));
            }
            this.intent.putExtra("com.google.android.gms.appinvite.MESSAGE", charSequence);
            return this;
        }

        public final IntentBuilder setOtherPlatformsTargetApplication(int i9, String str) {
            if (i9 == 1) {
                this.intent.putExtra("com.google.android.gms.appinvite.iosTargetApplication", str);
            } else {
                if (i9 != 2) {
                    throw new IllegalArgumentException("targetPlatform must be either PROJECT_PLATFORM_IOS or PROJECT_PLATFORM_ANDROID.");
                }
                this.intent.putExtra("com.google.android.gms.appinvite.androidTargetApplication", str);
            }
            return this;
        }
    }

    private AppInviteInvitation() {
    }

    public static String[] getInvitationIds(int i9, Intent intent) {
        if (i9 == -1) {
            return intent.getStringArrayExtra("com.google.android.gms.appinvite.RESULT_INVITATION_IDS");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle zza(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            bundle.putString(str, map.get(str));
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zza(String str) {
        int i9 = 0;
        while (true) {
            String[] strArr = zzf;
            if (i9 >= strArr.length) {
                return false;
            }
            if (strArr[i9].equals(str)) {
                return true;
            }
            i9++;
        }
    }
}
