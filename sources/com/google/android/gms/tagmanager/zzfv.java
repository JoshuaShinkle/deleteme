package com.google.android.gms.tagmanager;

import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes2.dex */
final class zzfv implements zzfx {
    @Override // com.google.android.gms.tagmanager.zzfx
    public final HttpURLConnection zzc(URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
