package com.mixpanel.android.util;

import android.content.Context;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import p006a5.InterfaceC0036d;

/* loaded from: classes2.dex */
public interface RemoteService {

    public static class ServiceUnavailableException extends Exception {
        private final int mRetryAfter;

        public ServiceUnavailableException(String str, String str2) throws NumberFormatException {
            int i9;
            super(str);
            try {
                i9 = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                i9 = 0;
            }
            this.mRetryAfter = i9;
        }

        /* renamed from: a */
        public int m17987a() {
            return this.mRetryAfter;
        }
    }

    /* renamed from: a */
    byte[] mo17984a(String str, Map<String, Object> map, SSLSocketFactory sSLSocketFactory);

    /* renamed from: b */
    void mo17985b();

    /* renamed from: c */
    boolean mo17986c(Context context, InterfaceC0036d interfaceC0036d);
}
