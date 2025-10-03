package com.google.firebase.dynamiclinks;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.DynamicLink;

/* loaded from: classes2.dex */
public abstract class FirebaseDynamicLinks {
    public static synchronized FirebaseDynamicLinks getInstance() {
        return (FirebaseDynamicLinks) FirebaseApp.getInstance().get(FirebaseDynamicLinks.class);
    }

    public abstract DynamicLink.Builder createDynamicLink();

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Intent intent);

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Uri uri);
}
