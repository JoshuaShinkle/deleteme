package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* loaded from: classes2.dex */
final class zzg implements PendingResultUtil.ResultConverter<WorkAccountApi.AddAccountResult, Account> {
    public zzg(WorkAccountClient workAccountClient) {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Account convert(Result result) {
        return ((WorkAccountApi.AddAccountResult) result).getAccount();
    }
}
