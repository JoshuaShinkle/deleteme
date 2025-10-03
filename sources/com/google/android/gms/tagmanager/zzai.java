package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Random;
import org.apache.commons.lang3.time.DateUtils;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzai {
    private final String zzaec;
    private final Random zzafj;
    private final Context zzrm;

    public zzai(Context context, String str) {
        this(context, str, new Random());
    }

    private final long zza(long j9, long j10) {
        long jMax = Math.max(0L, zzhp().getLong("FORBIDDEN_COUNT", 0L));
        return (long) (this.zzafj.nextFloat() * (j9 + ((long) ((jMax / ((jMax + Math.max(0L, r0.getLong("SUCCESSFUL_COUNT", 0L))) + 1)) * (j10 - j9)))));
    }

    private final SharedPreferences zzhp() {
        Context context = this.zzrm;
        String strValueOf = String.valueOf(this.zzaec);
        return context.getSharedPreferences(strValueOf.length() != 0 ? "_gtmContainerRefreshPolicy_".concat(strValueOf) : new String("_gtmContainerRefreshPolicy_"), 0);
    }

    public final long zzhl() {
        return zza(7200000L, 259200000L) + 43200000;
    }

    public final long zzhm() {
        return zza(600000L, DateUtils.MILLIS_PER_DAY) + DateUtils.MILLIS_PER_HOUR;
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzhn() {
        SharedPreferences sharedPreferencesZzhp = zzhp();
        long j9 = sharedPreferencesZzhp.getLong("FORBIDDEN_COUNT", 0L);
        long j10 = sharedPreferencesZzhp.getLong("SUCCESSFUL_COUNT", 0L);
        SharedPreferences.Editor editorEdit = sharedPreferencesZzhp.edit();
        long jMin = j9 == 0 ? 3L : Math.min(10L, j9 + 1);
        long jMax = Math.max(0L, Math.min(j10, 10 - jMin));
        editorEdit.putLong("FORBIDDEN_COUNT", jMin);
        editorEdit.putLong("SUCCESSFUL_COUNT", jMax);
        editorEdit.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzho() {
        SharedPreferences sharedPreferencesZzhp = zzhp();
        long j9 = sharedPreferencesZzhp.getLong("SUCCESSFUL_COUNT", 0L);
        long j10 = sharedPreferencesZzhp.getLong("FORBIDDEN_COUNT", 0L);
        long jMin = Math.min(10L, j9 + 1);
        long jMax = Math.max(0L, Math.min(j10, 10 - jMin));
        SharedPreferences.Editor editorEdit = sharedPreferencesZzhp.edit();
        editorEdit.putLong("SUCCESSFUL_COUNT", jMin);
        editorEdit.putLong("FORBIDDEN_COUNT", jMax);
        editorEdit.apply();
    }

    private zzai(Context context, String str, Random random) {
        this.zzrm = (Context) Preconditions.checkNotNull(context);
        this.zzaec = (String) Preconditions.checkNotNull(str);
        this.zzafj = random;
    }
}
