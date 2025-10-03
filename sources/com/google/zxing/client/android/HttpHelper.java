package com.google.zxing.client.android;

import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class HttpHelper {
    private static final Collection<String> REDIRECTOR_DOMAINS = new HashSet(Arrays.asList("amzn.to", "bit.ly", "bitly.com", "fb.me", "goo.gl", "is.gd", "j.mp", "lnkd.in", "ow.ly", "R.BEETAGG.COM", "r.beetagg.com", "SCN.BY", "su.pr", "t.co", "tinyurl.com", "tr.im"));
    private static final String TAG = "HttpHelper";

    /* renamed from: com.google.zxing.client.android.HttpHelper$1 */
    public static /* synthetic */ class C44501 {

        /* renamed from: $SwitchMap$com$google$zxing$client$android$HttpHelper$ContentType */
        static final /* synthetic */ int[] f15650x2f4dd40b;

        static {
            int[] iArr = new int[ContentType.values().length];
            f15650x2f4dd40b = iArr;
            try {
                iArr[ContentType.HTML.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15650x2f4dd40b[ContentType.JSON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15650x2f4dd40b[ContentType.XML.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15650x2f4dd40b[ContentType.TEXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum ContentType {
        HTML,
        JSON,
        XML,
        TEXT
    }

    private HttpHelper() {
    }

    private static CharSequence consume(URLConnection uRLConnection, int i9) throws Throwable {
        int i10;
        String encoding = getEncoding(uRLConnection);
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        try {
            InputStreamReader inputStreamReader2 = new InputStreamReader(uRLConnection.getInputStream(), encoding);
            try {
                char[] cArr = new char[UserMetadata.MAX_ATTRIBUTE_SIZE];
                while (sb.length() < i9 && (i10 = inputStreamReader2.read(cArr)) > 0) {
                    sb.append(cArr, 0, i10);
                }
                try {
                    inputStreamReader2.close();
                } catch (IOException | NullPointerException unused) {
                }
                return sb;
            } catch (Throwable th) {
                th = th;
                inputStreamReader = inputStreamReader2;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException | NullPointerException unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static CharSequence downloadViaHttp(String str, ContentType contentType) {
        return downloadViaHttp(str, contentType, Integer.MAX_VALUE);
    }

    private static String getEncoding(URLConnection uRLConnection) {
        int iIndexOf;
        String headerField = uRLConnection.getHeaderField(HttpHeaders.CONTENT_TYPE);
        return (headerField == null || (iIndexOf = headerField.indexOf("charset=")) < 0) ? "UTF-8" : headerField.substring(iIndexOf + 8);
    }

    private static int safelyConnect(HttpURLConnection httpURLConnection) throws IOException {
        try {
            httpURLConnection.connect();
            try {
                return httpURLConnection.getResponseCode();
            } catch (IllegalArgumentException | NullPointerException | StringIndexOutOfBoundsException e9) {
                throw new IOException(e9);
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException | SecurityException e10) {
            throw new IOException(e10);
        }
    }

    private static HttpURLConnection safelyOpenConnection(URL url) throws IOException {
        try {
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
                return (HttpURLConnection) uRLConnectionOpenConnection;
            }
            throw new IOException();
        } catch (NullPointerException e9) {
            Log.w(TAG, "Bad URI? " + url);
            throw new IOException(e9);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034 A[Catch: all -> 0x0049, TRY_LEAVE, TryCatch #0 {all -> 0x0049, blocks: (B:6:0x0028, B:8:0x0030, B:10:0x0034, B:12:0x003c), top: B:21:0x0028 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static URI unredirect(URI uri) throws IOException {
        if (!REDIRECTOR_DOMAINS.contains(uri.getHost())) {
            return uri;
        }
        HttpURLConnection httpURLConnectionSafelyOpenConnection = safelyOpenConnection(uri.toURL());
        httpURLConnectionSafelyOpenConnection.setInstanceFollowRedirects(false);
        httpURLConnectionSafelyOpenConnection.setDoInput(false);
        httpURLConnectionSafelyOpenConnection.setRequestMethod("HEAD");
        httpURLConnectionSafelyOpenConnection.setRequestProperty(HttpHeaders.USER_AGENT, "ZXing (Android)");
        try {
            int iSafelyConnect = safelyConnect(httpURLConnectionSafelyOpenConnection);
            if (iSafelyConnect != 307) {
                switch (iSafelyConnect) {
                    case 300:
                    case 301:
                    case 302:
                    case 303:
                        String headerField = httpURLConnectionSafelyOpenConnection.getHeaderField(HttpHeaders.LOCATION);
                        if (headerField != null) {
                            try {
                                return new URI(headerField);
                            } catch (URISyntaxException unused) {
                                break;
                            }
                        }
                        break;
                }
            }
            return uri;
        } finally {
            httpURLConnectionSafelyOpenConnection.disconnect();
        }
    }

    public static CharSequence downloadViaHttp(String str, ContentType contentType, int i9) {
        int i10 = C44501.f15650x2f4dd40b[contentType.ordinal()];
        return downloadViaHttp(str, i10 != 1 ? i10 != 2 ? i10 != 3 ? "text/*,*/*" : "application/xml,text/*,*/*" : "application/json,text/*,*/*" : "application/xhtml+xml,text/html,text/*,*/*", i9);
    }

    private static CharSequence downloadViaHttp(String str, String str2, int i9) throws IOException {
        int i10 = 0;
        while (i10 < 5) {
            HttpURLConnection httpURLConnectionSafelyOpenConnection = safelyOpenConnection(new URL(str));
            httpURLConnectionSafelyOpenConnection.setInstanceFollowRedirects(true);
            httpURLConnectionSafelyOpenConnection.setRequestProperty(HttpHeaders.ACCEPT, str2);
            httpURLConnectionSafelyOpenConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "utf-8,*");
            httpURLConnectionSafelyOpenConnection.setRequestProperty(HttpHeaders.USER_AGENT, "ZXing (Android)");
            try {
                int iSafelyConnect = safelyConnect(httpURLConnectionSafelyOpenConnection);
                if (iSafelyConnect == 200) {
                    return consume(httpURLConnectionSafelyOpenConnection, i9);
                }
                if (iSafelyConnect == 302) {
                    String headerField = httpURLConnectionSafelyOpenConnection.getHeaderField(HttpHeaders.LOCATION);
                    if (headerField != null) {
                        i10++;
                        httpURLConnectionSafelyOpenConnection.disconnect();
                        str = headerField;
                    } else {
                        throw new IOException("No Location");
                    }
                } else {
                    throw new IOException("Bad HTTP response: " + iSafelyConnect);
                }
            } finally {
                httpURLConnectionSafelyOpenConnection.disconnect();
            }
        }
        throw new IOException("Too many redirects");
    }
}
