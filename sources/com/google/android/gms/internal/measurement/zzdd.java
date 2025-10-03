package com.google.android.gms.internal.measurement;

import android.net.Uri;
import p132m.C5302a;

/* loaded from: classes2.dex */
public final class zzdd {
    private static final C5302a<String, Uri> zza = new C5302a<>();

    public static synchronized Uri zza(String str) {
        Uri uri;
        C5302a<String, Uri> c5302a = zza;
        uri = c5302a.get(str);
        if (uri == null) {
            String strValueOf = String.valueOf(Uri.encode(str));
            uri = Uri.parse(strValueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(strValueOf) : new String("content://com.google.android.gms.phenotype/"));
            c5302a.put(str, uri);
        }
        return uri;
    }
}
