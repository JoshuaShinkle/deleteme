package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public interface zabn {
    ConnectionResult zaa(long j9, TimeUnit timeUnit);

    ConnectionResult zaa(Api<?> api);

    <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t8);

    void zaa();

    void zaa(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    boolean zaa(SignInConnectionListener signInConnectionListener);

    ConnectionResult zab();

    <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t8);

    void zac();

    boolean zad();

    boolean zae();

    void zaf();

    void zag();
}
