package com.perfectcorp.ycl.p040bc.model.network;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.perfectcorp.utility.C4507b;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.C4510e;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.network.Key;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p047d5.C4677a;
import p047d5.C4682f;
import p057e5.C4756a;
import p057e5.C4757b;

/* loaded from: classes2.dex */
public class NetworkManager {
    private static final String CONNECTION_HISTORY_FILE_NAME = "connectionHistory.csv";
    private static final long DAY_IN_MILLISECOND = 86400000;
    private static final String DEMO_1_SERVER_INIT_URL = "https://live-demo-api.cyberlink.com/api/live/init";
    private static final String DEMO_1_SERVER_INIT_URL_CHN = "https://biz-demo-api.presenterlink.com/api/live/init";
    private static final String DEMO_2_SERVER_INIT_URL = "http://54.249.232.57/api/live/init";
    protected static final int GET_CONNECTION_TIMEOUT = 60000;
    private static final String MAX_CONNECTIONS = "10";
    private static final long MAX_HISTORY_FILE_SIZE = 1048576;
    private static final int MAX_THREAD_POOL = 100;
    protected static final int POST_CONNECTION_TIMEOUT = 30000;
    private static final String PREF_KEY_URI_HISTORY_DATE = "UrlHistoryDate";
    private static final String PRODUCTION_SEVER_INIT_URL = "https://webinars-api.cyberlink.com/api/live/init";
    private static final String PRODUCTION_SEVER_INIT_URL_CHN = "https://biz-api.presenterlink.com/api/live/init";
    private static final int RESPONSE_UNSUPPORTED_VERSION = 409;
    private static final int RETRY_COUNT = 2;
    protected static final long SLOW_RESPONSE_TIME = 1000;
    protected static final long SLOW_STREAMING_TIME = 1000;
    static NetworkManager _instance;
    public static Key.Init.Response initResponse;
    private static Map<String, String> sDoServerInitParameters;
    boolean isInitialized = false;
    static ExecutorService FOLLOW_TASK_EXECUTOR = Executors.newSingleThreadExecutor();
    private static int CORE_POOL_SIZE = 5;
    private static long KEEP_ALIVE_TIME = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    static ExecutorService LIMITED_TASK_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, 100, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private static ArrayList<HttpLog> mUriHistoryList = new ArrayList<>();
    private static final Object lock = new Object();

    /* renamed from: com.perfectcorp.ycl.bc.model.network.NetworkManager$2 */
    public class C45432 extends PromisedTask<String, Void, Void> {
        public C45432() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onError$0() {
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            super.onError(i9);
            if (i9 == NetworkManager.RESPONSE_UNSUPPORTED_VERSION) {
                C4757b.m18885b(C4677a.f16367d, C4682f.bc_dialog_title_warning, C4682f.wbn_app_update_required, new Runnable() { // from class: com.perfectcorp.ycl.bc.model.network.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        NetworkManager.C45432.lambda$onError$0();
                    }
                });
            }
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public Void doInBackground(String str) {
            NetworkManager.this.parseInitResponse(str);
            return null;
        }
    }

    public static class BC_SERVER_MODE {
        public static final String CHINA = "PRODUCTION (CHINA)";
        public static final String DEMO1 = "DEMO1";
        public static final String DEMO2 = "DEMO2";
        public static final String EURPOE = "PRODUCTION (EURPOE)";
        public static final String JAPAN = "PRODUCTION (JAPAN)";
        public static final String PRODUCTION = "PRODUCTION";

        /* renamed from: US */
        public static final String f15988US = "PRODUCTION (US)";

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0070  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static String getRequestURL(String str) {
            char c9;
            if (C4677a.m18714r().equals("CHN")) {
                return C4677a.m18681E() ? NetworkManager.DEMO_1_SERVER_INIT_URL_CHN : NetworkManager.PRODUCTION_SEVER_INIT_URL_CHN;
            }
            if (!C4509d.m18120b(C4677a.m18709m())) {
                return C4677a.m18709m();
            }
            String strM18717u = C4677a.m18717u();
            switch (strM18717u.hashCode()) {
                case -1844865238:
                    if (!strM18717u.equals(f15988US)) {
                        c9 = 65535;
                        break;
                    } else {
                        c9 = 4;
                        break;
                    }
                case 64931502:
                    if (strM18717u.equals(DEMO1)) {
                        c9 = 0;
                        break;
                    }
                    break;
                case 64931503:
                    if (strM18717u.equals(DEMO2)) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 1791728531:
                    if (strM18717u.equals(CHINA)) {
                        c9 = 2;
                        break;
                    }
                    break;
                case 1864581572:
                    if (strM18717u.equals(EURPOE)) {
                        c9 = 5;
                        break;
                    }
                    break;
                case 1985864388:
                    if (strM18717u.equals(JAPAN)) {
                        c9 = 3;
                        break;
                    }
                    break;
            }
            return c9 != 0 ? c9 != 1 ? NetworkManager.PRODUCTION_SEVER_INIT_URL : NetworkManager.DEMO_2_SERVER_INIT_URL : NetworkManager.DEMO_1_SERVER_INIT_URL;
        }

        public static String getShortName(String str) {
            str.hashCode();
            switch (str) {
                case "PRODUCTION (US)":
                    return "US";
                case "DEMO1":
                    return "D1";
                case "DEMO2":
                    return "D2";
                case "PRODUCTION (CHINA)":
                    return "CN";
                case "PRODUCTION (EURPOE)":
                    return "EU";
                case "PRODUCTION (JAPAN)":
                    return "JP";
                default:
                    return "PD";
            }
        }

        public static String[] getSupportedServers() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(PRODUCTION);
            arrayList.add(DEMO1);
            arrayList.add(DEMO2);
            String[] strArr = new String[arrayList.size()];
            arrayList.toArray(strArr);
            return strArr;
        }
    }

    public static class COMMENT_TARGET_TYPE {
        public static final String COMMENT = "Comment";
        public static final String POST = "Post";
        public static final String SUBPOST = "SubPost";
    }

    public static class ConnectionDuration {
        private static int mConnectionCount;
        private final long mBeginTime;
        private long mResponseTime;
        private final Uri mUri;

        public ConnectionDuration(String str) {
            Uri uri = Uri.parse(str);
            this.mUri = uri;
            this.mBeginTime = System.currentTimeMillis();
            mConnectionCount++;
            if (C4507b.f15929a && C4507b.f15930b) {
                C4677a.m18693T("[REQUEST] #" + mConnectionCount + "; [" + C4510e.f15951j.f15961a + "]; " + uri.getPath());
            }
        }

        public void eos() {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mResponseTime;
            if (jCurrentTimeMillis > 1000 && C4507b.f15929a && C4507b.f15930b) {
                C4677a.m18693T("[STREAMING SLOW] response: " + ((this.mResponseTime - this.mBeginTime) / 1000.0f) + "sec; streaming: " + (jCurrentTimeMillis / 1000.0f) + "sec; " + this.mUri.getPath());
            }
        }

        public long response() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.mResponseTime = jCurrentTimeMillis;
            long j9 = jCurrentTimeMillis - this.mBeginTime;
            if (j9 > 1000 && C4507b.f15929a && C4507b.f15930b) {
                C4677a.m18693T("[RESPONSE SLOW] " + (j9 / 1000.0f) + "sec; " + this.mUri.getPath());
            }
            return j9;
        }
    }

    public static class DownloadTask extends PromisedTask<C4510e, Float, byte[]> {
        private void fail(int i9, String str) {
            if (C4507b.f15929a && C4507b.f15930b) {
                C4677a.m18693T("Network Fail: " + i9 + StringUtils.SPACE + str);
            }
            reportError(i9);
        }

        /* JADX WARN: Removed duplicated region for block: B:74:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // com.perfectcorp.utility.PromisedTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public byte[] doInBackground(C4510e c4510e) throws Throwable {
            InputStream inputStream;
            InputStream inputStream2 = null;
            if (isCancelled()) {
                NetworkErrorCode networkErrorCode = NetworkErrorCode.E_CONNECT_CANCELLED;
                fail(networkErrorCode.getValue(), networkErrorCode.toString());
                return null;
            }
            try {
                String strM18126f = c4510e.m18126f();
                if (strM18126f == null) {
                    NetworkErrorCode networkErrorCode2 = NetworkErrorCode.E_BAD_REQUEST;
                    fail(networkErrorCode2.getValue(), networkErrorCode2.toString());
                    return null;
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strM18126f).openConnection();
                if (isCancelled()) {
                    NetworkErrorCode networkErrorCode3 = NetworkErrorCode.E_CONNECT_CANCELLED;
                    fail(networkErrorCode3.getValue(), networkErrorCode3.toString());
                    return null;
                }
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Key.Init.Parameter.userAgent);
                String hexString = Long.toHexString(System.currentTimeMillis());
                httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + hexString);
                httpURLConnection.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                PrintWriter printWriter = new PrintWriter(dataOutputStream);
                c4510e.m18130j(printWriter, dataOutputStream, hexString);
                printWriter.flush();
                printWriter.close();
                int responseCode = httpURLConnection.getResponseCode();
                if (isCancelled()) {
                    NetworkErrorCode networkErrorCode4 = NetworkErrorCode.E_CONNECT_CANCELLED;
                    fail(networkErrorCode4.getValue(), networkErrorCode4.toString());
                    return null;
                }
                if (responseCode >= 400 && responseCode < 600) {
                    fail(responseCode, httpURLConnection.getResponseMessage());
                    return null;
                }
                InputStream inputStream3 = httpURLConnection.getInputStream();
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                    while (true) {
                        int i9 = inputStream3.read(bArr, 0, UserMetadata.MAX_ATTRIBUTE_SIZE);
                        if (i9 == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i9);
                    }
                    if (isCancelled()) {
                        NetworkErrorCode networkErrorCode5 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        fail(networkErrorCode5.getValue(), networkErrorCode5.toString());
                        try {
                            inputStream3.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                        return null;
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    try {
                        inputStream3.close();
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                    return byteArray;
                } catch (IOException e11) {
                    inputStream = inputStream3;
                    e = e11;
                    try {
                        e.printStackTrace();
                        NetworkErrorCode networkErrorCode6 = NetworkErrorCode.E_BAD_REQUEST;
                        fail(networkErrorCode6.getValue(), networkErrorCode6.toString());
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e12) {
                                e12.printStackTrace();
                            }
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    inputStream2 = inputStream3;
                    th = th2;
                    if (inputStream2 != null) {
                    }
                    throw th;
                }
            } catch (IOException e14) {
                e = e14;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static class GetTask extends PromisedTask<C4510e, Float, String> {
        private int retry;

        private GetTask() {
            this.retry = 2;
        }

        private void fail(int i9, String str) {
            if (C4507b.f15929a && C4507b.f15930b) {
                C4677a.m18693T("Network Fail: " + i9 + StringUtils.SPACE + str);
            }
            C4507b.m18102c(Integer.valueOf(i9), StringUtils.SPACE, str);
            reportError(i9);
        }

        /* JADX WARN: Removed duplicated region for block: B:158:0x0352  */
        /* JADX WARN: Removed duplicated region for block: B:160:0x0356 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:174:0x036e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:182:0x0338 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:198:? A[SYNTHETIC] */
        @Override // com.perfectcorp.utility.PromisedTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String doInBackground(C4510e c4510e) throws Throwable {
            InputStream errorStream;
            Throwable th;
            InputStream inputStream = null;
            if (isCancelled()) {
                NetworkErrorCode networkErrorCode = NetworkErrorCode.E_CONNECT_CANCELLED;
                fail(networkErrorCode.getValue(), networkErrorCode.toString());
                C4510e.f15951j.f15961a--;
                NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                return null;
            }
            int i9 = -1;
            long jResponse = -1;
            try {
                if (c4510e == null) {
                    NetworkErrorCode networkErrorCode2 = NetworkErrorCode.E_BAD_REQUEST;
                    fail(networkErrorCode2.getValue(), networkErrorCode2.toString());
                    C4510e.f15951j.f15961a--;
                    NetworkManager.sendHTTPFailEvent(null, "E_BAD_REQUEST");
                    boolean z8 = C4507b.f15929a;
                    if (c4510e != null) {
                        NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), -1, -1L));
                    }
                    return null;
                }
                try {
                    String strM18129i = c4510e.m18129i();
                    StringBuilder sb = new StringBuilder();
                    URL url = new URL(strM18129i);
                    C4507b.m18101b("[WebRequest] GET URL:" + url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(NetworkManager.GET_CONNECTION_TIMEOUT);
                    httpURLConnection.setReadTimeout(NetworkManager.GET_CONNECTION_TIMEOUT);
                    if (isCancelled()) {
                        NetworkErrorCode networkErrorCode3 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        fail(networkErrorCode3.getValue(), networkErrorCode3.toString());
                        C4510e.f15951j.f15961a--;
                        NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                        boolean z9 = C4507b.f15929a;
                        NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), -1, -1L));
                        return null;
                    }
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Key.Init.Parameter.userAgent);
                    Iterator<Pair<String, String>> it = c4510e.m18127g().iterator();
                    while (it.hasNext()) {
                        try {
                            Pair<String, String> next = it.next();
                            httpURLConnection.setRequestProperty((String) next.first, (String) next.second);
                        } catch (IOException e9) {
                            e = e9;
                            errorStream = null;
                            i9 = -1;
                            try {
                                e.printStackTrace();
                                NetworkErrorCode networkErrorCode4 = NetworkErrorCode.E_BAD_REQUEST;
                                fail(networkErrorCode4.getValue(), networkErrorCode4.toString());
                                if ((C4507b.f15929a || i9 != 200) && c4510e != null) {
                                    NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), i9, jResponse));
                                }
                                if (errorStream != null) {
                                    try {
                                        errorStream.close();
                                    } catch (IOException e10) {
                                        e10.printStackTrace();
                                    }
                                }
                                C4510e.f15951j.f15961a--;
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = errorStream;
                                th = th;
                                if ((C4507b.f15929a || i9 != 200) && c4510e != null) {
                                    NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), i9, jResponse));
                                }
                                if (inputStream != null) {
                                    throw th;
                                }
                                try {
                                    inputStream.close();
                                    throw th;
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                    throw th;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            i9 = -1;
                            if (C4507b.f15929a) {
                            }
                            if (inputStream != null) {
                            }
                        }
                    }
                    ConnectionDuration connectionDuration = new ConnectionDuration(strM18129i);
                    int responseCode = httpURLConnection.getResponseCode();
                    try {
                        jResponse = connectionDuration.response();
                        try {
                            if (isCancelled()) {
                                NetworkErrorCode networkErrorCode5 = NetworkErrorCode.E_CONNECT_CANCELLED;
                                fail(networkErrorCode5.getValue(), networkErrorCode5.toString());
                                C4510e.f15951j.f15961a--;
                                NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                                if (C4507b.f15929a || responseCode != 200) {
                                    NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                }
                                return null;
                            }
                            NetworkManager.sendHTTPFailEvent(c4510e, Integer.toString(responseCode));
                            if (responseCode >= 400 && responseCode < 600) {
                                C4507b.m18101b("[WebRequest] GET fail:" + responseCode);
                                C4510e.b bVar = C4510e.f15951j;
                                bVar.f15961a = bVar.f15961a - 1;
                                String string = "";
                                errorStream = httpURLConnection.getErrorStream();
                                if (errorStream != null) {
                                    try {
                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "utf-8"));
                                        while (true) {
                                            String line = bufferedReader.readLine();
                                            if (line == null || isCancelled()) {
                                                break;
                                            }
                                            sb.append(line);
                                        }
                                        bufferedReader.close();
                                        try {
                                            string = new JSONObject(sb.toString()).getString("message");
                                        } catch (JSONException unused) {
                                            C4507b.m18111l("[WebRequest] Get parse error message fail : " + errorStream.toString());
                                        }
                                    } catch (IOException e12) {
                                        e = e12;
                                        i9 = responseCode;
                                        e.printStackTrace();
                                        NetworkErrorCode networkErrorCode42 = NetworkErrorCode.E_BAD_REQUEST;
                                        fail(networkErrorCode42.getValue(), networkErrorCode42.toString());
                                        if (C4507b.f15929a) {
                                            NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), i9, jResponse));
                                        } else {
                                            NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), i9, jResponse));
                                        }
                                        if (errorStream != null) {
                                        }
                                        C4510e.f15951j.f15961a--;
                                        return null;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        inputStream = errorStream;
                                        i9 = responseCode;
                                        th = th;
                                        if (C4507b.f15929a) {
                                        }
                                        if (inputStream != null) {
                                        }
                                    }
                                }
                                reportError(responseCode, string);
                                if (C4507b.f15929a || responseCode != 200) {
                                    NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                }
                                if (errorStream == null) {
                                    return null;
                                }
                                try {
                                    errorStream.close();
                                    return null;
                                } catch (IOException e13) {
                                    e13.printStackTrace();
                                    return null;
                                }
                            }
                            InputStream inputStream2 = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, "utf-8"));
                            while (true) {
                                String line2 = bufferedReader2.readLine();
                                if (line2 == null || isCancelled()) {
                                    break;
                                }
                                sb.append(line2);
                            }
                            connectionDuration.eos();
                            bufferedReader2.close();
                            if (isCancelled()) {
                                NetworkErrorCode networkErrorCode6 = NetworkErrorCode.E_CONNECT_CANCELLED;
                                fail(networkErrorCode6.getValue(), networkErrorCode6.toString());
                                NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                                C4510e.f15951j.f15961a--;
                                if (C4507b.f15929a || responseCode != 200) {
                                    NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                }
                                if (inputStream2 == null) {
                                    return null;
                                }
                                try {
                                    inputStream2.close();
                                    return null;
                                } catch (IOException e14) {
                                    e14.printStackTrace();
                                    return null;
                                }
                            }
                            String string2 = sb.toString();
                            if (string2.length() != 0) {
                                C4510e.f15951j.f15961a--;
                                if (C4507b.f15929a || responseCode != 200) {
                                    NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                }
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e15) {
                                        e15.printStackTrace();
                                    }
                                }
                                return string2;
                            }
                            NetworkErrorCode networkErrorCode7 = NetworkErrorCode.E_EMPTY_RESPONSE;
                            fail(networkErrorCode7.getValue(), networkErrorCode7.toString());
                            NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                            C4510e.f15951j.f15961a--;
                            if (C4507b.f15929a || responseCode != 200) {
                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                            }
                            if (inputStream2 == null) {
                                return null;
                            }
                            try {
                                inputStream2.close();
                                return null;
                            } catch (IOException e16) {
                                e16.printStackTrace();
                                return null;
                            }
                        } catch (IOException e17) {
                            e = e17;
                            errorStream = null;
                        } catch (Throwable th5) {
                            th = th5;
                            i9 = responseCode;
                            if (C4507b.f15929a) {
                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), i9, jResponse));
                            } else {
                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), i9, jResponse));
                            }
                            if (inputStream != null) {
                            }
                        }
                    } catch (IOException e18) {
                        e = e18;
                        i9 = responseCode;
                        errorStream = null;
                    } catch (Throwable th6) {
                        th = th6;
                        i9 = responseCode;
                        inputStream = null;
                    }
                } catch (IOException e19) {
                    e = e19;
                    errorStream = null;
                } catch (Throwable th7) {
                    th = th7;
                    inputStream = null;
                }
            } catch (IOException e20) {
                e = e20;
                errorStream = null;
            } catch (Throwable th8) {
                th = th8;
            }
        }
    }

    public static class HttpLog {
        public Uri httpUri;
        public int responseCode;
        public long responseDuration;

        public HttpLog(Uri uri, int i9, long j9) {
            this.httpUri = uri;
            this.responseCode = i9;
            this.responseDuration = j9;
        }
    }

    public static class LIKE_TARGET_TYPE {
        public static final String COMMENT = "Comment";
        public static final String POST = "Post";
        public static final String SUBPOST = "SubPost";
    }

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

        public int getValue() {
            return this.value;
        }
    }

    public static class PostTask extends PromisedTask<C4510e, Float, String> {
        private int retry;

        private PostTask() {
            this.retry = 2;
        }

        private void fail(int i9, String str) {
            if (str == null) {
                str = "Unknown reason";
            }
            if (C4507b.f15929a && C4507b.f15930b) {
                C4677a.m18693T("Network Fail: " + i9 + StringUtils.SPACE + str);
            }
            reportError(i9);
            C4507b.m18102c("Network Fail: " + i9 + StringUtils.SPACE + str);
        }

        /* JADX WARN: Removed duplicated region for block: B:171:0x0421  */
        /* JADX WARN: Removed duplicated region for block: B:173:0x0425  */
        /* JADX WARN: Removed duplicated region for block: B:185:0x0453  */
        /* JADX WARN: Removed duplicated region for block: B:187:0x0457  */
        /* JADX WARN: Removed duplicated region for block: B:194:0x046d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:198:0x043b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:233:? A[SYNTHETIC] */
        @Override // com.perfectcorp.utility.PromisedTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String doInBackground(C4510e c4510e) throws Throwable {
            int i9;
            int i10;
            Throwable th;
            ConnectionDuration connectionDuration;
            InputStream inputStream = null;
            if (isCancelled()) {
                C4510e.f15951j.f15961a--;
                NetworkErrorCode networkErrorCode = NetworkErrorCode.E_CONNECT_CANCELLED;
                fail(networkErrorCode.getValue(), networkErrorCode.toString());
                NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                return null;
            }
            int responseCode = -1;
            long j9 = -1;
            try {
                try {
                    String strM18126f = c4510e.m18126f();
                    if (strM18126f == null) {
                        try {
                            NetworkErrorCode networkErrorCode2 = NetworkErrorCode.E_BAD_REQUEST;
                            fail(networkErrorCode2.getValue(), networkErrorCode2.toString());
                            C4510e.f15951j.f15961a--;
                            NetworkManager.sendHTTPFailEvent(c4510e, "E_BAD_REQUEST");
                            boolean z8 = C4507b.f15929a;
                            NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), -1, -1L));
                            return null;
                        } catch (IOException e9) {
                            e = e9;
                        }
                    } else {
                        StringBuilder sb = new StringBuilder();
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strM18126f).openConnection();
                        httpURLConnection.setConnectTimeout(30000);
                        httpURLConnection.setReadTimeout(30000);
                        System.setProperty("http.maxConnections", NetworkManager.MAX_CONNECTIONS);
                        if (isCancelled()) {
                            try {
                                NetworkErrorCode networkErrorCode3 = NetworkErrorCode.E_CONNECT_CANCELLED;
                                fail(networkErrorCode3.getValue(), networkErrorCode3.toString());
                                C4510e.f15951j.f15961a--;
                                NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                                boolean z9 = C4507b.f15929a;
                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), -1, -1L));
                                return null;
                            } catch (IOException e10) {
                                e = e10;
                                inputStream = null;
                                e.printStackTrace();
                                NetworkErrorCode networkErrorCode4 = NetworkErrorCode.E_BAD_REQUEST;
                                fail(networkErrorCode4.getValue(), networkErrorCode4.toString());
                                if (C4507b.f15929a) {
                                }
                                if (inputStream != null) {
                                }
                                C4510e.f15951j.f15961a--;
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = null;
                                if (!C4507b.f15929a) {
                                }
                                if (inputStream != null) {
                                }
                            }
                        } else {
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Key.Init.Parameter.userAgent);
                            Iterator<Pair<String, String>> it = c4510e.m18127g().iterator();
                            while (it.hasNext()) {
                                try {
                                    Pair<String, String> next = it.next();
                                    httpURLConnection.setRequestProperty((String) next.first, (String) next.second);
                                    responseCode = -1;
                                } catch (IOException e11) {
                                    e = e11;
                                    inputStream = null;
                                    responseCode = -1;
                                } catch (Throwable th3) {
                                    th = th3;
                                    inputStream = null;
                                    responseCode = -1;
                                    if (!C4507b.f15929a || responseCode != 200) {
                                        NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, j9));
                                    }
                                    if (inputStream != null) {
                                        throw th;
                                    }
                                    try {
                                        inputStream.close();
                                        throw th;
                                    } catch (IOException e12) {
                                        e12.printStackTrace();
                                        throw th;
                                    }
                                }
                            }
                            try {
                                connectionDuration = new ConnectionDuration(strM18126f);
                                try {
                                    if (c4510e.f15957f.equals("application/x-www-form-urlencoded")) {
                                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                                        httpURLConnection.setDoOutput(true);
                                        String strM18128h = c4510e.m18128h();
                                        try {
                                            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                                            dataOutputStream.writeBytes(strM18128h);
                                            dataOutputStream.flush();
                                            dataOutputStream.close();
                                        } catch (UnknownHostException e13) {
                                            C4507b.m18102c("UnknownHostException :" + e13.getMessage());
                                            NetworkErrorCode networkErrorCode5 = NetworkErrorCode.E_BAD_REQUEST;
                                            fail(networkErrorCode5.getValue(), networkErrorCode5.toString());
                                            C4510e.f15951j.f15961a--;
                                            NetworkManager.sendHTTPFailEvent(c4510e, "E_BAD_REQUEST");
                                            boolean z10 = C4507b.f15929a;
                                            NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), -1, -1L));
                                            return null;
                                        }
                                    } else {
                                        try {
                                            String str = "====" + Long.toHexString(System.currentTimeMillis()) + "====";
                                            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + str);
                                            httpURLConnection.setDoOutput(true);
                                            try {
                                                DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                                                c4510e.m18130j(new PrintWriter(dataOutputStream2), dataOutputStream2, str);
                                            } catch (UnknownHostException e14) {
                                                C4507b.m18102c("UnknownHostException :" + e14.getMessage());
                                                NetworkErrorCode networkErrorCode6 = NetworkErrorCode.E_BAD_REQUEST;
                                                fail(networkErrorCode6.getValue(), networkErrorCode6.toString());
                                                C4510e.f15951j.f15961a--;
                                                NetworkManager.sendHTTPFailEvent(c4510e, "E_BAD_REQUEST");
                                                boolean z11 = C4507b.f15929a;
                                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), -1, -1L));
                                                return null;
                                            }
                                        } catch (IOException e15) {
                                            e = e15;
                                            i10 = -1;
                                            j9 = -1;
                                            inputStream = null;
                                            responseCode = i10;
                                            e.printStackTrace();
                                            NetworkErrorCode networkErrorCode42 = NetworkErrorCode.E_BAD_REQUEST;
                                            fail(networkErrorCode42.getValue(), networkErrorCode42.toString());
                                            if (C4507b.f15929a) {
                                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, j9));
                                            }
                                            if (inputStream != null) {
                                            }
                                            C4510e.f15951j.f15961a--;
                                            return null;
                                        } catch (Throwable th4) {
                                            i9 = -1;
                                            th = th4;
                                            j9 = -1;
                                            inputStream = null;
                                            responseCode = i9;
                                            if (!C4507b.f15929a) {
                                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, j9));
                                            }
                                            if (inputStream != null) {
                                            }
                                        }
                                    }
                                    if (C4507b.f15929a) {
                                        C4507b.m18101b("[WebRequest] POST URL:" + c4510e.m18129i());
                                    }
                                    responseCode = httpURLConnection.getResponseCode();
                                } catch (IOException e16) {
                                    e = e16;
                                    inputStream = null;
                                    responseCode = -1;
                                } catch (Throwable th5) {
                                    th = th5;
                                    inputStream = null;
                                    responseCode = -1;
                                }
                            } catch (IOException e17) {
                                e = e17;
                                i10 = -1;
                            } catch (Throwable th6) {
                                th = th6;
                                i9 = -1;
                                th = th;
                                inputStream = null;
                                responseCode = i9;
                                if (!C4507b.f15929a) {
                                }
                                if (inputStream != null) {
                                }
                            }
                            try {
                                long jResponse = connectionDuration.response();
                                try {
                                    if (isCancelled()) {
                                        NetworkErrorCode networkErrorCode7 = NetworkErrorCode.E_CONNECT_CANCELLED;
                                        fail(networkErrorCode7.getValue(), networkErrorCode7.toString());
                                        C4510e.f15951j.f15961a--;
                                        NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                                        if (!C4507b.f15929a && responseCode == 200) {
                                            return null;
                                        }
                                        NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                        return null;
                                    }
                                    NetworkManager.sendHTTPFailEvent(c4510e, Integer.toString(responseCode));
                                    if (responseCode >= 400 && responseCode < 600) {
                                        C4507b.m18111l("[WebRequest] Post Fail:" + responseCode);
                                        C4510e.b bVar = C4510e.f15951j;
                                        bVar.f15961a = bVar.f15961a - 1;
                                        String string = "";
                                        InputStream errorStream = httpURLConnection.getErrorStream();
                                        if (errorStream != null) {
                                            try {
                                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "utf-8"));
                                                while (true) {
                                                    String line = bufferedReader.readLine();
                                                    if (line == null || isCancelled()) {
                                                        break;
                                                    }
                                                    sb.append(line);
                                                }
                                                bufferedReader.close();
                                                try {
                                                    string = new JSONObject(sb.toString()).getString("message");
                                                } catch (JSONException unused) {
                                                    C4507b.m18111l("[WebRequest] Post parse error message fail : " + errorStream.toString());
                                                }
                                            } catch (IOException e18) {
                                                e = e18;
                                                inputStream = errorStream;
                                                j9 = jResponse;
                                                e.printStackTrace();
                                                NetworkErrorCode networkErrorCode422 = NetworkErrorCode.E_BAD_REQUEST;
                                                fail(networkErrorCode422.getValue(), networkErrorCode422.toString());
                                                if (C4507b.f15929a) {
                                                }
                                                if (inputStream != null) {
                                                }
                                                C4510e.f15951j.f15961a--;
                                                return null;
                                            } catch (Throwable th7) {
                                                th = th7;
                                                inputStream = errorStream;
                                                j9 = jResponse;
                                                if (!C4507b.f15929a) {
                                                }
                                                if (inputStream != null) {
                                                }
                                            }
                                        }
                                        reportError(responseCode, string);
                                        if (C4507b.f15929a || responseCode != 200) {
                                            NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                        }
                                        if (errorStream == null) {
                                            return null;
                                        }
                                        try {
                                            errorStream.close();
                                            return null;
                                        } catch (IOException e19) {
                                            e19.printStackTrace();
                                            return null;
                                        }
                                    }
                                    inputStream = httpURLConnection.getInputStream();
                                    try {
                                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                                        while (true) {
                                            String line2 = bufferedReader2.readLine();
                                            if (line2 == null || isCancelled()) {
                                                break;
                                            }
                                            sb.append(line2);
                                        }
                                        connectionDuration.eos();
                                        bufferedReader2.close();
                                        if (!isCancelled()) {
                                            C4510e.f15951j.f15961a--;
                                            String string2 = sb.toString();
                                            if (C4507b.f15929a || responseCode != 200) {
                                                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                            }
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e20) {
                                                    e20.printStackTrace();
                                                }
                                            }
                                            return string2;
                                        }
                                        NetworkErrorCode networkErrorCode8 = NetworkErrorCode.E_CONNECT_CANCELLED;
                                        fail(networkErrorCode8.getValue(), networkErrorCode8.toString());
                                        C4510e.f15951j.f15961a--;
                                        NetworkManager.sendHTTPFailEvent(c4510e, "E_CONNECT_CANCELLED");
                                        if (C4507b.f15929a || responseCode != 200) {
                                            NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, jResponse));
                                        }
                                        if (inputStream == null) {
                                            return null;
                                        }
                                        try {
                                            inputStream.close();
                                            return null;
                                        } catch (IOException e21) {
                                            e21.printStackTrace();
                                            return null;
                                        }
                                    } catch (IOException e22) {
                                        e = e22;
                                        j9 = jResponse;
                                        e.printStackTrace();
                                        NetworkErrorCode networkErrorCode4222 = NetworkErrorCode.E_BAD_REQUEST;
                                        fail(networkErrorCode4222.getValue(), networkErrorCode4222.toString());
                                        if (C4507b.f15929a) {
                                        }
                                        if (inputStream != null) {
                                        }
                                        C4510e.f15951j.f15961a--;
                                        return null;
                                    } catch (Throwable th8) {
                                        th = th8;
                                        j9 = jResponse;
                                        if (!C4507b.f15929a) {
                                        }
                                        if (inputStream != null) {
                                        }
                                    }
                                } catch (IOException e23) {
                                    e = e23;
                                    j9 = jResponse;
                                    inputStream = null;
                                    e.printStackTrace();
                                    NetworkErrorCode networkErrorCode42222 = NetworkErrorCode.E_BAD_REQUEST;
                                    fail(networkErrorCode42222.getValue(), networkErrorCode42222.toString());
                                    if (C4507b.f15929a) {
                                    }
                                    if (inputStream != null) {
                                    }
                                    C4510e.f15951j.f15961a--;
                                    return null;
                                } catch (Throwable th9) {
                                    th = th9;
                                    j9 = jResponse;
                                    inputStream = null;
                                    if (!C4507b.f15929a) {
                                    }
                                    if (inputStream != null) {
                                    }
                                }
                            } catch (IOException e24) {
                                e = e24;
                                inputStream = null;
                                j9 = -1;
                                e.printStackTrace();
                                NetworkErrorCode networkErrorCode422222 = NetworkErrorCode.E_BAD_REQUEST;
                                fail(networkErrorCode422222.getValue(), networkErrorCode422222.toString());
                                if (C4507b.f15929a) {
                                }
                                if (inputStream != null) {
                                }
                                C4510e.f15951j.f15961a--;
                                return null;
                            } catch (Throwable th10) {
                                th = th10;
                                inputStream = null;
                                j9 = -1;
                                if (!C4507b.f15929a) {
                                }
                                if (inputStream != null) {
                                }
                            }
                        }
                    }
                } catch (Throwable th11) {
                    th = th11;
                }
            } catch (IOException e25) {
                e = e25;
                i10 = responseCode;
            } catch (Throwable th12) {
                th = th12;
                i9 = responseCode;
            }
            e.printStackTrace();
            NetworkErrorCode networkErrorCode4222222 = NetworkErrorCode.E_BAD_REQUEST;
            fail(networkErrorCode4222222.getValue(), networkErrorCode4222222.toString());
            if (C4507b.f15929a || responseCode != 200) {
                NetworkManager.mUriHistoryList.add(new HttpLog(Uri.parse(c4510e.m18129i()), responseCode, j9));
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e26) {
                    e26.printStackTrace();
                }
            }
            C4510e.f15951j.f15961a--;
            return null;
        }
    }

    public static class REPORT_TARGET_RESON {
        public static final String COPYRIGHT = "Copyright";
        public static final String INAPPROPRIATE = "Inappropriate";
        public static final String OTHER = "Other";
    }

    public static class REPORT_TARGET_TYPE {
        public static final String COMMENT = "Comment";
        public static final String POST = "Post";
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0074 A[Catch: all -> 0x0062, Exception -> 0x00be, LOOP:0: B:18:0x006e->B:20:0x0074, LOOP_END, TryCatch #6 {Exception -> 0x00be, all -> 0x0062, blocks: (B:14:0x0050, B:17:0x0065, B:18:0x006e, B:20:0x0074, B:21:0x009f), top: B:46:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String dumpUriHistory() {
        FileWriter fileWriter;
        Iterator<HttpLog> it;
        FileWriter fileWriter2 = null;
        path = null;
        path = null;
        String path = null;
        try {
            boolean z8 = true;
            File file = new File(C4507b.m18107h(true), CONNECTION_HISTORY_FILE_NAME);
            if (file.exists()) {
                SharedPreferences sharedPreferencesM18705i = C4677a.m18705i();
                long jCurrentTimeMillis = System.currentTimeMillis() / 86400000;
                try {
                    if (jCurrentTimeMillis == sharedPreferencesM18705i.getLong(PREF_KEY_URI_HISTORY_DATE, 0L) / 86400000) {
                        if (file.length() < 1048576) {
                        }
                        fileWriter = new FileWriter(file, z8);
                        if (!z8) {
                            try {
                                fileWriter.write("Code,Duration,URI@");
                                fileWriter.write(C4756a.m18883a(new Date()));
                            } catch (Exception unused) {
                                if (fileWriter != null) {
                                    try {
                                        fileWriter.close();
                                    } catch (IOException unused2) {
                                        C4507b.m18102c(new Object[0]);
                                    }
                                }
                                return path;
                            } catch (Throwable th) {
                                th = th;
                                fileWriter2 = fileWriter;
                                if (fileWriter2 != null) {
                                    try {
                                        fileWriter2.close();
                                    } catch (IOException unused3) {
                                        C4507b.m18102c(new Object[0]);
                                    }
                                }
                                throw th;
                            }
                        }
                        fileWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                        it = mUriHistoryList.iterator();
                        while (it.hasNext()) {
                            HttpLog next = it.next();
                            fileWriter.write(String.valueOf(next.responseCode));
                            fileWriter.write(",");
                            fileWriter.write(String.valueOf(next.responseDuration));
                            fileWriter.write(",");
                            fileWriter.write(next.httpUri.toString());
                            fileWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                        }
                        fileWriter.flush();
                        path = file.getPath();
                        fileWriter.close();
                    } else {
                        sharedPreferencesM18705i.edit().putLong(PREF_KEY_URI_HISTORY_DATE, jCurrentTimeMillis).apply();
                    }
                    fileWriter.close();
                } catch (IOException unused4) {
                    C4507b.m18102c(new Object[0]);
                }
                z8 = false;
                fileWriter = new FileWriter(file, z8);
                if (!z8) {
                }
                fileWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                it = mUriHistoryList.iterator();
                while (it.hasNext()) {
                }
                fileWriter.flush();
                path = file.getPath();
            } else {
                z8 = false;
                fileWriter = new FileWriter(file, z8);
                if (!z8) {
                }
                fileWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                it = mUriHistoryList.iterator();
                while (it.hasNext()) {
                }
                fileWriter.flush();
                path = file.getPath();
                fileWriter.close();
            }
        } catch (Exception unused5) {
            fileWriter = null;
        } catch (Throwable th2) {
            th = th2;
        }
        return path;
    }

    public static ArrayList<HttpLog> getHistoryUri() {
        return mUriHistoryList;
    }

    public static void init() {
        NetworkManager networkManager = _instance;
        if (networkManager != null) {
            networkManager.isInitialized = false;
        }
        instance();
    }

    public static PromisedTask<?, ?, NetworkManager> instance() {
        return instance(LIMITED_TASK_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean parseInitResponse(String str) {
        Key.Init.Response response = (Key.Init.Response) Key.Init.GSON.fromJson(str, Key.Init.Response.class);
        initResponse = response;
        if (response == null) {
            return false;
        }
        this.isInitialized = true;
        return true;
    }

    public static PromisedTask<C4510e, Float, byte[]> sendDownload() {
        return new DownloadTask();
    }

    public static GetTask sendGet() {
        C4510e.b bVar = C4510e.f15951j;
        bVar.f15962b++;
        bVar.f15961a++;
        return new GetTask();
    }

    public static void sendHTTPFailEvent(C4510e c4510e, String str) {
    }

    public static PostTask sendPost() {
        C4510e.b bVar = C4510e.f15951j;
        bVar.f15963c++;
        bVar.f15961a++;
        return new PostTask();
    }

    public static void setServerInitParameter(Map<String, String> map) {
        sDoServerInitParameters = map;
    }

    public static PromisedTask<?, ?, NetworkManager> instance(ExecutorService executorService) {
        return new PromisedTask<Void, Void, NetworkManager>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkManager.1
            @Override // com.perfectcorp.utility.PromisedTask
            public NetworkManager doInBackground(Void r32) {
                synchronized (NetworkManager.lock) {
                    if (NetworkManager._instance == null) {
                        NetworkManager._instance = new NetworkManager();
                    }
                    if (!NetworkManager._instance.isInitialized) {
                        String str = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                        Key.Init.initParameter();
                        NetworkManager.init(NetworkManager._instance, str);
                    }
                }
                if (!NetworkManager._instance.isInitialized) {
                    reportError(NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                }
                return NetworkManager._instance;
            }
        }.executeOnExecutor(executorService, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void init(NetworkManager networkManager, String str) {
        C4510e c4510e = new C4510e(BC_SERVER_MODE.getRequestURL(str));
        for (String str2 : sDoServerInitParameters.keySet()) {
            c4510e.m18125d(str2, sDoServerInitParameters.get(str2));
        }
        c4510e.f15957f = "application/x-www-form-urlencoded";
        try {
            sendPost().execute(c4510e).then(networkManager.new C45432()).get();
        } catch (NullPointerException unused) {
            C4507b.m18108i("NetworkManager is not ready");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }
}
