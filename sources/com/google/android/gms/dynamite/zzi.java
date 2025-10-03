package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes2.dex */
final class zzi implements DynamiteModule.VersionPolicy {
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c A[PHI: r4
      0x001c: PHI (r4v2 int) = (r4v1 int), (r4v3 int) binds: [B:3:0x0014, B:5:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.zza(context, str);
        int iZzb = iVersions.zzb(context, str, true);
        selectionResult.remoteVersion = iZzb;
        int i9 = selectionResult.localVersion;
        if (i9 == 0) {
            i9 = 0;
            if (iZzb == 0) {
                selectionResult.selection = 0;
            } else if (i9 >= iZzb) {
                selectionResult.selection = -1;
            } else {
                selectionResult.selection = 1;
            }
        }
        return selectionResult;
    }
}
