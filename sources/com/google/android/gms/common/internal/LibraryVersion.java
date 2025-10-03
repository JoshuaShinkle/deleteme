package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap zzc = new ConcurrentHashMap();

    @VisibleForTesting
    public LibraryVersion() {
    }

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0099  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @KeepForSdk
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String getVersion(String str) throws Throwable {
        Object obj;
        InputStream resourceAsStream;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzc.containsKey(str)) {
            return (String) this.zzc.get(str);
        }
        Properties properties = new Properties();
        ?? r42 = 0;
        r42 = 0;
        r42 = 0;
        InputStream inputStream = null;
        try {
            try {
                resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", str));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e9) {
            e = e9;
            obj = null;
        }
        try {
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                String property = properties.getProperty("version", null);
                zza.m17515v("LibraryVersion", str + " version is " + property);
                r42 = property;
            } else {
                zza.m17517w("LibraryVersion", "Failed to get app version for libraryName: " + str);
            }
            if (resourceAsStream != null) {
                IOUtils.closeQuietly(resourceAsStream);
            }
        } catch (IOException e10) {
            e = e10;
            Object obj2 = r42;
            inputStream = resourceAsStream;
            obj = obj2;
            zza.m17512e("LibraryVersion", "Failed to get app version for libraryName: " + str, e);
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
            r42 = obj;
            if (r42 == 0) {
            }
            this.zzc.put(str, r42);
            return r42;
        } catch (Throwable th2) {
            th = th2;
            r42 = resourceAsStream;
            if (r42 != 0) {
                IOUtils.closeQuietly((Closeable) r42);
            }
            throw th;
        }
        if (r42 == 0) {
            zza.m17509d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
            r42 = "UNKNOWN";
        }
        this.zzc.put(str, r42);
        return r42;
    }
}
