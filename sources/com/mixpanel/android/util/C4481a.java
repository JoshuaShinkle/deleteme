package com.mixpanel.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.mixpanel.android.util.RemoteService;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import p006a5.C0035c;
import p006a5.InterfaceC0036d;

/* renamed from: com.mixpanel.android.util.a */
/* loaded from: classes2.dex */
public class C4481a implements RemoteService {

    /* renamed from: a */
    public static boolean f15796a;

    /* renamed from: com.mixpanel.android.util.a$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws UnknownHostException {
            try {
                InetAddress byName = InetAddress.getByName("api.mixpanel.com");
                boolean unused = C4481a.f15796a = byName.isLoopbackAddress() || byName.isAnyLocalAddress();
                if (C4481a.f15796a) {
                    C0035c.m147i("MixpanelAPI.Message", "AdBlocker is enabled. Won't be able to use Mixpanel services.");
                }
            } catch (Exception unused2) {
            }
        }
    }

    /* renamed from: g */
    public static byte[] m17990g(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        while (true) {
            int i9 = inputStream.read(bArr, 0, UserMetadata.MAX_INTERNAL_KEY_SIZE);
            if (i9 == -1) {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i9);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x012f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0121 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0139 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0126 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x001a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0043 A[Catch: all -> 0x00d2, IOException -> 0x00d5, EOFException -> 0x00db, TryCatch #15 {EOFException -> 0x00db, IOException -> 0x00d5, all -> 0x00d2, blocks: (B:8:0x002c, B:10:0x0030, B:11:0x0036, B:13:0x0043, B:14:0x0050, B:16:0x0056, B:17:0x006e, B:32:0x00b8), top: B:116:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8 A[Catch: all -> 0x00d2, IOException -> 0x00d5, EOFException -> 0x00db, TRY_ENTER, TRY_LEAVE, TryCatch #15 {EOFException -> 0x00db, IOException -> 0x00d5, all -> 0x00d2, blocks: (B:8:0x002c, B:10:0x0030, B:11:0x0036, B:13:0x0043, B:14:0x0050, B:16:0x0056, B:17:0x006e, B:32:0x00b8), top: B:116:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0134 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.io.BufferedOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // com.mixpanel.android.util.RemoteService
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] mo17984a(String str, Map<String, Object> map, SSLSocketFactory sSLSocketFactory) throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        OutputStream outputStream;
        ?? bufferedOutputStream;
        OutputStream outputStream2;
        C0035c.m147i("MixpanelAPI.Message", "Attempting request to " + str);
        HttpURLConnection httpURLConnection2 = null;
        outputStream = null;
        outputStream = null;
        OutputStream outputStream3 = null;
        int i9 = 0;
        byte[] bArrM17990g = null;
        boolean z8 = false;
        while (i9 < 3 && !z8) {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            } catch (EOFException unused) {
                httpURLConnection = null;
                inputStream = null;
            } catch (IOException e9) {
                e = e9;
                inputStream = null;
                outputStream = null;
                bufferedOutputStream = 0;
            } catch (Throwable th) {
                th = th;
                httpURLConnection = null;
                inputStream = null;
            }
            if (sSLSocketFactory != null) {
                try {
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLSocketFactory);
                    }
                    httpURLConnection.setConnectTimeout(2000);
                    httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
                } catch (EOFException unused2) {
                    inputStream = null;
                    outputStream = inputStream;
                    bufferedOutputStream = outputStream;
                    try {
                        C0035c.m139a("MixpanelAPI.Message", "Failure to connect, likely caused by a known issue with Android lib. Retrying.");
                        i9++;
                        if (bufferedOutputStream != 0) {
                        }
                        if (outputStream != null) {
                        }
                        if (inputStream != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream3 = bufferedOutputStream;
                        if (outputStream3 != null) {
                        }
                        if (outputStream != null) {
                        }
                        if (inputStream != null) {
                        }
                        if (httpURLConnection == null) {
                        }
                    }
                } catch (IOException e10) {
                    e = e10;
                    inputStream = null;
                    outputStream = null;
                    outputStream2 = outputStream;
                    httpURLConnection2 = httpURLConnection;
                    bufferedOutputStream = outputStream2;
                    if (httpURLConnection2 != null) {
                    }
                    throw e;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                    outputStream = inputStream;
                    if (outputStream3 != null) {
                    }
                    if (outputStream != null) {
                    }
                    if (inputStream != null) {
                    }
                    if (httpURLConnection == null) {
                    }
                }
                if (map == null) {
                    Uri.Builder builder = new Uri.Builder();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        builder.appendQueryParameter(entry.getKey(), entry.getValue().toString());
                    }
                    String encodedQuery = builder.build().getEncodedQuery();
                    httpURLConnection.setFixedLengthStreamingMode(encodedQuery.getBytes().length);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    outputStream = httpURLConnection.getOutputStream();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(outputStream);
                    } catch (EOFException unused3) {
                        inputStream = null;
                        bufferedOutputStream = 0;
                    } catch (IOException e11) {
                        e = e11;
                        inputStream = null;
                        outputStream2 = null;
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                    }
                    try {
                        bufferedOutputStream.write(encodedQuery.getBytes("UTF-8"));
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        outputStream.close();
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            bArrM17990g = m17990g(inputStream);
                            inputStream.close();
                            httpURLConnection.disconnect();
                            z8 = true;
                        } catch (EOFException unused4) {
                            outputStream = null;
                            bufferedOutputStream = outputStream;
                            C0035c.m139a("MixpanelAPI.Message", "Failure to connect, likely caused by a known issue with Android lib. Retrying.");
                            i9++;
                            if (bufferedOutputStream != 0) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException unused5) {
                                }
                            }
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException unused6) {
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused7) {
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        } catch (IOException e12) {
                            e = e12;
                            outputStream = null;
                            outputStream2 = outputStream;
                            httpURLConnection2 = httpURLConnection;
                            bufferedOutputStream = outputStream2;
                            if (httpURLConnection2 != null) {
                                try {
                                    if (httpURLConnection2.getResponseCode() >= 500 && httpURLConnection2.getResponseCode() <= 599) {
                                        throw new RemoteService.ServiceUnavailableException("Service Unavailable", httpURLConnection2.getHeaderField(HttpHeaders.RETRY_AFTER));
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    httpURLConnection = httpURLConnection2;
                                    outputStream3 = bufferedOutputStream;
                                    if (outputStream3 != null) {
                                    }
                                    if (outputStream != null) {
                                    }
                                    if (inputStream != null) {
                                    }
                                    if (httpURLConnection == null) {
                                    }
                                }
                            }
                            throw e;
                        } catch (Throwable th6) {
                            th = th6;
                            outputStream = null;
                        }
                    } catch (EOFException unused8) {
                        inputStream = null;
                        bufferedOutputStream = bufferedOutputStream;
                        C0035c.m139a("MixpanelAPI.Message", "Failure to connect, likely caused by a known issue with Android lib. Retrying.");
                        i9++;
                        if (bufferedOutputStream != 0) {
                        }
                        if (outputStream != null) {
                        }
                        if (inputStream != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                    } catch (IOException e13) {
                        e = e13;
                        inputStream = null;
                        outputStream2 = bufferedOutputStream;
                        httpURLConnection2 = httpURLConnection;
                        bufferedOutputStream = outputStream2;
                        if (httpURLConnection2 != null) {
                        }
                        throw e;
                    } catch (Throwable th7) {
                        th = th7;
                        inputStream = null;
                        outputStream3 = bufferedOutputStream;
                        if (outputStream3 != null) {
                        }
                        if (outputStream != null) {
                        }
                        if (inputStream != null) {
                        }
                        if (httpURLConnection == null) {
                        }
                    }
                } else {
                    inputStream = httpURLConnection.getInputStream();
                    bArrM17990g = m17990g(inputStream);
                    inputStream.close();
                    httpURLConnection.disconnect();
                    z8 = true;
                }
            } else {
                httpURLConnection.setConnectTimeout(2000);
                httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
                if (map == null) {
                }
            }
            if (outputStream3 != null) {
                try {
                    outputStream3.close();
                } catch (IOException unused9) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException unused10) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused11) {
                }
            }
            if (httpURLConnection == null) {
                throw th;
            }
            httpURLConnection.disconnect();
            throw th;
        }
        if (i9 >= 3) {
            C0035c.m147i("MixpanelAPI.Message", "Could not connect to Mixpanel service after three retries.");
        }
        return bArrM17990g;
    }

    @Override // com.mixpanel.android.util.RemoteService
    /* renamed from: b */
    public void mo17985b() {
        new Thread(new a()).start();
    }

    @Override // com.mixpanel.android.util.RemoteService
    @SuppressLint({"MissingPermission"})
    /* renamed from: c */
    public boolean mo17986c(Context context, InterfaceC0036d interfaceC0036d) {
        if (f15796a || m17991f(interfaceC0036d)) {
            return false;
        }
        boolean z8 = true;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                C0035c.m147i("MixpanelAPI.Message", "A default network has not been set so we cannot be certain whether we are offline");
            } else {
                boolean zIsConnectedOrConnecting = activeNetworkInfo.isConnectedOrConnecting();
                StringBuilder sb = new StringBuilder();
                sb.append("ConnectivityManager says we ");
                sb.append(zIsConnectedOrConnecting ? "are" : "are not");
                sb.append(" online");
                C0035c.m147i("MixpanelAPI.Message", sb.toString());
                z8 = zIsConnectedOrConnecting;
            }
        } catch (SecurityException unused) {
            C0035c.m147i("MixpanelAPI.Message", "Don't have permission to check connectivity, will assume we are online");
        }
        return z8;
    }

    /* renamed from: f */
    public final boolean m17991f(InterfaceC0036d interfaceC0036d) {
        if (interfaceC0036d == null) {
            return false;
        }
        try {
            return interfaceC0036d.m150a();
        } catch (Exception e9) {
            C0035c.m148j("MixpanelAPI.Message", "Client State should not throw exception, will assume is not on offline mode", e9);
            return false;
        }
    }
}
