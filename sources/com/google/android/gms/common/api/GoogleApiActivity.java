package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.messaging.Constants;

@KeepName
/* loaded from: classes2.dex */
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {

    @VisibleForTesting
    private int zaa = 0;

    @RecentlyNonNull
    public static PendingIntent zaa(@RecentlyNonNull Context context, @RecentlyNonNull PendingIntent pendingIntent, @RecentlyNonNull int i9) {
        return PendingIntent.getActivity(context, 0, zaa(context, pendingIntent, i9, true), 134217728);
    }

    @Override // android.app.Activity
    public void onActivityResult(@RecentlyNonNull int i9, @RecentlyNonNull int i10, @RecentlyNonNull Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.zaa = 0;
            setResult(i10, intent);
            if (booleanExtra) {
                GoogleApiManager googleApiManagerZaa = GoogleApiManager.zaa(this);
                if (i10 == -1) {
                    googleApiManagerZaa.zac();
                } else if (i10 == 0) {
                    googleApiManagerZaa.zab(new ConnectionResult(13, null), getIntent().getIntExtra("failing_client_id", -1));
                }
            }
        } else if (i9 == 2) {
            this.zaa = 0;
            setResult(i10, intent);
        }
        finish();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(@RecentlyNonNull DialogInterface dialogInterface) {
        this.zaa = 0;
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) throws IntentSender.SendIntentException {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zaa = bundle.getInt("resolution");
        }
        if (this.zaa != 1) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Log.e("GoogleApiActivity", "Activity started without extras");
                finish();
                return;
            }
            PendingIntent pendingIntent = (PendingIntent) extras.get(Constants.IntentKeys.PENDING_INTENT);
            Integer num = (Integer) extras.get("error_code");
            if (pendingIntent == null && num == null) {
                Log.e("GoogleApiActivity", "Activity started without resolution");
                finish();
                return;
            }
            if (pendingIntent == null) {
                GoogleApiAvailability.getInstance().showErrorDialogFragment(this, ((Integer) Preconditions.checkNotNull(num)).intValue(), 2, this);
                this.zaa = 1;
                return;
            }
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                this.zaa = 1;
            } catch (ActivityNotFoundException e9) {
                if (extras.getBoolean("notify_manager", true)) {
                    GoogleApiManager.zaa(this).zab(new ConnectionResult(22, null), getIntent().getIntExtra("failing_client_id", -1));
                } else {
                    String strValueOf = String.valueOf(pendingIntent);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 36);
                    sb.append("Activity not found while launching ");
                    sb.append(strValueOf);
                    sb.append(".");
                    String string = sb.toString();
                    if (Build.FINGERPRINT.contains("generic")) {
                        string = String.valueOf(string).concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.");
                    }
                    Log.e("GoogleApiActivity", string, e9);
                }
                this.zaa = 1;
                finish();
            } catch (IntentSender.SendIntentException e10) {
                Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e10);
                finish();
            }
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(@RecentlyNonNull Bundle bundle) {
        bundle.putInt("resolution", this.zaa);
        super.onSaveInstanceState(bundle);
    }

    @RecentlyNonNull
    public static Intent zaa(@RecentlyNonNull Context context, @RecentlyNonNull PendingIntent pendingIntent, @RecentlyNonNull int i9, @RecentlyNonNull boolean z8) {
        Intent intent = new Intent(context, (Class<?>) GoogleApiActivity.class);
        intent.putExtra(Constants.IntentKeys.PENDING_INTENT, pendingIntent);
        intent.putExtra("failing_client_id", i9);
        intent.putExtra("notify_manager", z8);
        return intent;
    }
}
