package com.cyberlink.you.feedback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.p036ui.C3123g;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.common.net.HttpHeaders;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class NetworkManager {

    /* renamed from: a */
    public static NetworkManager f13375a = null;

    /* renamed from: b */
    public static int f13376b = 5;

    /* renamed from: c */
    public static long f13377c = 5000;

    /* renamed from: d */
    public static ExecutorService f13378d = new ThreadPoolExecutor(f13376b, 100, f13377c, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    /* renamed from: e */
    public static C3017d f13379e = new C3017d();

    public enum NetworkErrorCode {
        E_CONNECT_FAIL(-1),
        E_BAD_REQUEST(-2),
        E_NOT_INITIALIZED(-3),
        E_EMPTY_RESPONSE(-4),
        E_CONNECT_CANCELLED(-5);

        private final int value;

        NetworkErrorCode(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public int m15410a() {
            return this.value;
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkManager$a */
    public class C3014a extends PromisedTask<Void, Void, NetworkManager> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public NetworkManager mo5659d(Void r12) {
            if (NetworkManager.f13375a == null) {
                NetworkManager unused = NetworkManager.f13375a = new NetworkManager();
            }
            return NetworkManager.f13375a;
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkManager$b */
    public class DialogInterfaceOnClickListenerC3015b implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkManager$c */
    public static class C3016c extends PromisedTask<C3031d, Float, String> {

        /* renamed from: j */
        public int f13386j = 2;

        /* renamed from: k */
        public Activity f13387k;

        public C3016c(Activity activity) {
            this.f13387k = activity;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v0 */
        /* JADX WARN: Type inference failed for: r8v1 */
        /* JADX WARN: Type inference failed for: r8v2, types: [java.io.InputStream] */
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public String mo5659d(C3031d c3031d) throws Throwable {
            InterruptedException interruptedException;
            InputStream inputStream;
            IOException iOException;
            ?? r8 = 0;
            if (m15443i()) {
                NetworkManager.f13379e.f13388a--;
                NetworkErrorCode networkErrorCode = NetworkErrorCode.E_CONNECT_CANCELLED;
                m15413r(networkErrorCode.m15410a(), networkErrorCode.toString());
                return null;
            }
            try {
            } catch (Throwable th) {
                th = th;
                r8 = c3031d;
            }
            try {
                try {
                    String strM15502f = c3031d.m15502f();
                    if (strM15502f == null) {
                        NetworkErrorCode networkErrorCode2 = NetworkErrorCode.E_BAD_REQUEST;
                        m15413r(networkErrorCode2.m15410a(), networkErrorCode2.toString());
                        NetworkManager.f13379e.f13388a--;
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strM15502f).openConnection();
                    httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
                    System.setProperty("http.maxConnections", "10");
                    if (m15443i()) {
                        NetworkErrorCode networkErrorCode3 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m15413r(networkErrorCode3.m15410a(), networkErrorCode3.toString());
                        NetworkManager.f13379e.f13388a--;
                        return null;
                    }
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/5.0");
                    if (c3031d.f13487f.equals("application/x-www-form-urlencoded")) {
                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                        httpURLConnection.setDoOutput(true);
                        String strM15505i = c3031d.m15505i();
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                            Log.d("FeedbackNetworkMgr", "urlParameters = " + strM15505i);
                            dataOutputStream.writeBytes(strM15505i);
                            dataOutputStream.flush();
                            dataOutputStream.close();
                        } catch (UnknownHostException e9) {
                            Log.e("FeedbackNetworkMgr", "UnknownHostException :" + e9.getMessage());
                            NetworkErrorCode networkErrorCode4 = NetworkErrorCode.E_BAD_REQUEST;
                            m15413r(networkErrorCode4.m15410a(), networkErrorCode4.toString());
                            NetworkManager.f13379e.f13388a--;
                            return null;
                        }
                    } else {
                        String str = "====" + Long.toHexString(System.currentTimeMillis()) + "====";
                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + str);
                        httpURLConnection.setDoOutput(true);
                        try {
                            DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                            c3031d.m15507k(new PrintWriter(dataOutputStream2), dataOutputStream2, str);
                        } catch (UnknownHostException e10) {
                            Log.e("FeedbackNetworkMgr", "UnknownHostException :" + e10.getMessage());
                            NetworkErrorCode networkErrorCode5 = NetworkErrorCode.E_BAD_REQUEST;
                            m15413r(networkErrorCode5.m15410a(), networkErrorCode5.toString());
                            NetworkManager.f13379e.f13388a--;
                            return null;
                        }
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    int responseCode = httpURLConnection.getResponseCode();
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (jCurrentTimeMillis2 > DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
                        Log.w("FeedbackNetworkMgr", "Feedback response is too slow and took " + jCurrentTimeMillis2 + "ms");
                    }
                    if (m15443i()) {
                        NetworkErrorCode networkErrorCode6 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m15413r(networkErrorCode6.m15410a(), networkErrorCode6.toString());
                        NetworkManager.f13379e.f13388a--;
                        return null;
                    }
                    if (responseCode >= 400 && responseCode < 600) {
                        Log.w("FeedbackNetworkMgr", "[WebRequest] Post Fail:" + responseCode);
                        C3017d c3017d = NetworkManager.f13379e;
                        c3017d.f13388a = c3017d.f13388a - 1;
                        String responseMessage = httpURLConnection.getResponseMessage();
                        if (responseCode == 420) {
                            int i9 = this.f13386j - 1;
                            this.f13386j = i9;
                            if (i9 >= 0) {
                                Thread.sleep(3000L);
                                return mo5659d(c3031d);
                            }
                            NetworkManager.m15409f(Globals.m7375Z0(R.string.feedback_error), Globals.m7375Z0(R.string.feedback_unknown_error) + Integer.toString(responseCode), this.f13387k);
                            m15413r(responseCode, responseMessage);
                        } else {
                            m15413r(responseCode, responseMessage);
                        }
                        return null;
                    }
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null || m15443i()) {
                                break;
                            }
                            sb.append(line);
                        }
                        bufferedReader.close();
                        if (!m15443i()) {
                            NetworkManager.f13379e.f13388a--;
                            String string = sb.toString();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                }
                            }
                            return string;
                        }
                        NetworkErrorCode networkErrorCode7 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m15413r(networkErrorCode7.m15410a(), networkErrorCode7.toString());
                        NetworkManager.f13379e.f13388a--;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e12) {
                                e12.printStackTrace();
                            }
                        }
                        return null;
                    } catch (IOException e13) {
                        iOException = e13;
                        iOException.printStackTrace();
                        NetworkErrorCode networkErrorCode8 = NetworkErrorCode.E_BAD_REQUEST;
                        m15413r(networkErrorCode8.m15410a(), networkErrorCode8.toString());
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        NetworkManager.f13379e.f13388a--;
                        return null;
                    } catch (InterruptedException e14) {
                        interruptedException = e14;
                        interruptedException.printStackTrace();
                        m15438c(true);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        NetworkManager.f13379e.f13388a--;
                        return null;
                    }
                } catch (IOException e15) {
                    e15.printStackTrace();
                    NetworkManager.f13379e.f13388a--;
                    return null;
                }
            } catch (IOException e16) {
                iOException = e16;
                inputStream = null;
            } catch (InterruptedException e17) {
                interruptedException = e17;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                Throwable th3 = th;
                if (r8 == 0) {
                    throw th3;
                }
                try {
                    r8.close();
                    throw th3;
                } catch (IOException e18) {
                    e18.printStackTrace();
                    throw th3;
                }
            }
        }

        /* renamed from: r */
        public final void m15413r(int i9, String str) {
            if (str == null) {
                str = "Unknown reason";
            }
            Log.e("FeedbackNetworkMgr", "Network Fail: " + i9 + StringUtils.SPACE + str);
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkManager$d */
    public static class C3017d {

        /* renamed from: a */
        public long f13388a = 0;

        /* renamed from: b */
        public long f13389b = 0;

        /* renamed from: c */
        public TreeMap<String, Long> f13390c = new TreeMap<>();
    }

    /* renamed from: d */
    public static PromisedTask<?, ?, NetworkManager> m15407d(ExecutorService executorService) {
        return new C3014a().m15441g(executorService, null);
    }

    /* renamed from: e */
    public static C3016c m15408e(Activity activity) {
        C3017d c3017d = f13379e;
        c3017d.f13389b++;
        c3017d.f13388a++;
        return new C3016c(activity);
    }

    /* renamed from: f */
    public static void m15409f(String str, String str2, Activity activity) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        builderM16382a.setTitle(str);
        builderM16382a.setMessage(str2);
        builderM16382a.setPositiveButton(activity.getString(R.string.ok), new DialogInterfaceOnClickListenerC3015b());
        builderM16382a.create().show();
    }
}
