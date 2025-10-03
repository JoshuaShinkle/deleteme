package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.android.billingclient.api.C0774h;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing.zzb;

@UsedByReflection("PlatformActivityProxy")
/* loaded from: classes.dex */
public class ProxyBillingActivity extends Activity {

    /* renamed from: b */
    public ResultReceiver f3502b;

    /* renamed from: c */
    public ResultReceiver f3503c;

    /* renamed from: d */
    public boolean f3504d;

    /* renamed from: e */
    public boolean f3505e;

    /* renamed from: a */
    public final Intent m3638a(String str) {
        Intent intent = new Intent("com.android.vending.billing.ALTERNATIVE_BILLING");
        intent.setPackage(getApplicationContext().getPackageName());
        intent.putExtra("ALTERNATIVE_BILLING_USER_CHOICE_DATA", str);
        return intent;
    }

    /* renamed from: b */
    public final Intent m3639b() {
        Intent intent = new Intent("com.android.vending.billing.PURCHASES_UPDATED");
        intent.setPackage(getApplicationContext().getPackageName());
        return intent;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0080  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onActivityResult(int i9, int i10, Intent intent) {
        ResultReceiver resultReceiver;
        Intent intentM3639b;
        super.onActivityResult(i9, i10, intent);
        if (i9 == 100 || i9 == 110) {
            int iM3760b = zzb.zzd(intent, "ProxyBillingActivity").m3760b();
            if (i10 != -1) {
                zzb.zzj("ProxyBillingActivity", "Activity finished with resultCode " + i10 + " and billing's responseCode: " + iM3760b);
                resultReceiver = this.f3502b;
                if (resultReceiver != null) {
                    resultReceiver.send(iM3760b, intent != null ? intent.getExtras() : null);
                } else {
                    if (intent == null) {
                        intentM3639b = m3639b();
                    } else if (intent.getExtras() != null) {
                        String string = intent.getExtras().getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
                        if (string != null) {
                            intentM3639b = m3638a(string);
                            intentM3639b.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                        } else {
                            intentM3639b = m3639b();
                            intentM3639b.putExtras(intent.getExtras());
                            intentM3639b.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                        }
                    } else {
                        intentM3639b = m3639b();
                        zzb.zzj("ProxyBillingActivity", "Got null bundle!");
                        intentM3639b.putExtra("RESPONSE_CODE", 6);
                        intentM3639b.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                        C0774h.a aVarM3756c = C0774h.m3756c();
                        aVarM3756c.m3763c(6);
                        aVarM3756c.m3762b("An internal error occurred.");
                        intentM3639b.putExtra("FAILURE_LOGGING_PAYLOAD", C0810z.m3799a(22, 2, aVarM3756c.m3761a()).zzc());
                        intentM3639b.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                    }
                    if (i9 == 110) {
                        intentM3639b.putExtra("IS_FIRST_PARTY_PURCHASE", true);
                    }
                    sendBroadcast(intentM3639b);
                }
            } else if (iM3760b != 0) {
                i10 = -1;
                zzb.zzj("ProxyBillingActivity", "Activity finished with resultCode " + i10 + " and billing's responseCode: " + iM3760b);
                resultReceiver = this.f3502b;
                if (resultReceiver != null) {
                }
            } else {
                iM3760b = 0;
                resultReceiver = this.f3502b;
                if (resultReceiver != null) {
                }
            }
        } else if (i9 == 101) {
            int iZza = zzb.zza(intent, "ProxyBillingActivity");
            ResultReceiver resultReceiver2 = this.f3503c;
            if (resultReceiver2 != null) {
                resultReceiver2.send(iZza, intent != null ? intent.getExtras() : null);
            }
        } else {
            zzb.zzj("ProxyBillingActivity", "Got onActivityResult with wrong requestCode: " + i9 + "; skipping...");
        }
        this.f3504d = false;
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) throws IntentSender.SendIntentException {
        int i9;
        PendingIntent pendingIntent;
        int i10;
        super.onCreate(bundle);
        if (bundle != null) {
            zzb.zzi("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
            this.f3504d = bundle.getBoolean("send_cancelled_broadcast_if_finished", false);
            if (bundle.containsKey("result_receiver")) {
                this.f3502b = (ResultReceiver) bundle.getParcelable("result_receiver");
            } else if (bundle.containsKey("in_app_message_result_receiver")) {
                this.f3503c = (ResultReceiver) bundle.getParcelable("in_app_message_result_receiver");
            }
            this.f3505e = bundle.getBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false);
            return;
        }
        zzb.zzi("ProxyBillingActivity", "Launching Play Store billing flow");
        if (getIntent().hasExtra("BUY_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("BUY_INTENT");
            if (getIntent().hasExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT") && getIntent().getBooleanExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false)) {
                this.f3505e = true;
                i10 = 110;
                i9 = i10;
            }
            i9 = 100;
        } else if (getIntent().hasExtra("SUBS_MANAGEMENT_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("SUBS_MANAGEMENT_INTENT");
            this.f3502b = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
            i9 = 100;
        } else if (getIntent().hasExtra("IN_APP_MESSAGE_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("IN_APP_MESSAGE_INTENT");
            this.f3503c = (ResultReceiver) getIntent().getParcelableExtra("in_app_message_result_receiver");
            i10 = 101;
            i9 = i10;
        } else {
            i9 = 100;
            pendingIntent = null;
        }
        try {
            this.f3504d = true;
            startIntentSenderForResult(pendingIntent.getIntentSender(), i9, new Intent(), 0, 0, 0);
        } catch (IntentSender.SendIntentException e9) {
            zzb.zzk("ProxyBillingActivity", "Got exception while trying to start a purchase flow.", e9);
            ResultReceiver resultReceiver = this.f3502b;
            if (resultReceiver != null) {
                resultReceiver.send(6, null);
            } else {
                ResultReceiver resultReceiver2 = this.f3503c;
                if (resultReceiver2 != null) {
                    resultReceiver2.send(0, null);
                } else {
                    Intent intentM3639b = m3639b();
                    if (this.f3505e) {
                        intentM3639b.putExtra("IS_FIRST_PARTY_PURCHASE", true);
                    }
                    intentM3639b.putExtra("RESPONSE_CODE", 6);
                    intentM3639b.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    sendBroadcast(intentM3639b);
                }
            }
            this.f3504d = false;
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing() && this.f3504d) {
            Intent intentM3639b = m3639b();
            intentM3639b.putExtra("RESPONSE_CODE", 1);
            intentM3639b.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
            sendBroadcast(intentM3639b);
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        ResultReceiver resultReceiver = this.f3502b;
        if (resultReceiver != null) {
            bundle.putParcelable("result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.f3503c;
        if (resultReceiver2 != null) {
            bundle.putParcelable("in_app_message_result_receiver", resultReceiver2);
        }
        bundle.putBoolean("send_cancelled_broadcast_if_finished", this.f3504d);
        bundle.putBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", this.f3505e);
    }
}
