package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
public interface SignInClient extends HasApiKey<SignInOptions> {
    Task<BeginSignInResult> beginSignIn(BeginSignInRequest beginSignInRequest);

    SignInCredential getSignInCredentialFromIntent(Intent intent);

    Task<Void> signOut();
}
