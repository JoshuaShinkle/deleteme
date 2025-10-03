package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Keep;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.BuildConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ConfigFetchHttpClient {
    private static final String API_KEY_HEADER = "X-Goog-Api-Key";
    private static final String ETAG_HEADER = "ETag";
    private static final Pattern GMP_APP_ID_PATTERN = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    private static final String IF_NONE_MATCH_HEADER = "If-None-Match";
    private static final String INSTALLATIONS_AUTH_TOKEN_HEADER = "X-Goog-Firebase-Installations-Auth";
    private static final String X_ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String X_ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final String X_GOOGLE_GFE_CAN_RETRY = "X-Google-GFE-Can-Retry";
    private final String apiKey;
    private final String appId;
    private final long connectTimeoutInSeconds;
    private final Context context;
    private final String namespace;
    private final String projectNumber;
    private final long readTimeoutInSeconds;

    public ConfigFetchHttpClient(Context context, String str, String str2, String str3, long j9, long j10) {
        this.context = context;
        this.appId = str;
        this.apiKey = str2;
        this.projectNumber = extractProjectNumberFromAppId(str);
        this.namespace = str3;
        this.connectTimeoutInSeconds = j9;
        this.readTimeoutInSeconds = j10;
    }

    private boolean backendHasUpdates(JSONObject jSONObject) {
        try {
            return !jSONObject.get(RemoteConfigConstants.ResponseFieldKey.STATE).equals("NO_CHANGE");
        } catch (JSONException unused) {
            return true;
        }
    }

    private JSONObject createFetchRequestBody(String str, String str2, Map<String, String> map) throws PackageManager.NameNotFoundException, FirebaseRemoteConfigClientException {
        HashMap map2 = new HashMap();
        if (str == null) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: Firebase installation id is null.");
        }
        map2.put(RemoteConfigConstants.RequestFieldKey.INSTANCE_ID, str);
        map2.put(RemoteConfigConstants.RequestFieldKey.INSTANCE_ID_TOKEN, str2);
        map2.put(RemoteConfigConstants.RequestFieldKey.APP_ID, this.appId);
        Locale locale = this.context.getResources().getConfiguration().locale;
        map2.put(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, locale.getCountry());
        int i9 = Build.VERSION.SDK_INT;
        map2.put(RemoteConfigConstants.RequestFieldKey.LANGUAGE_CODE, locale.toLanguageTag());
        map2.put(RemoteConfigConstants.RequestFieldKey.PLATFORM_VERSION, Integer.toString(i9));
        map2.put(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, TimeZone.getDefault().getID());
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            if (packageInfo != null) {
                map2.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, packageInfo.versionName);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        map2.put("packageName", this.context.getPackageName());
        map2.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, BuildConfig.VERSION_NAME);
        map2.put(RemoteConfigConstants.RequestFieldKey.ANALYTICS_USER_PROPERTIES, new JSONObject(map));
        return new JSONObject(map2);
    }

    private static ConfigContainer extractConfigs(JSONObject jSONObject, Date date) throws JSONException, FirebaseRemoteConfigClientException {
        JSONObject jSONObject2;
        try {
            ConfigContainer.Builder builderWithFetchTime = ConfigContainer.newBuilder().withFetchTime(date);
            JSONArray jSONArray = null;
            try {
                jSONObject2 = jSONObject.getJSONObject(RemoteConfigConstants.ResponseFieldKey.ENTRIES);
            } catch (JSONException unused) {
                jSONObject2 = null;
            }
            if (jSONObject2 != null) {
                builderWithFetchTime.replaceConfigsWith(jSONObject2);
            }
            try {
                jSONArray = jSONObject.getJSONArray(RemoteConfigConstants.ResponseFieldKey.EXPERIMENT_DESCRIPTIONS);
            } catch (JSONException unused2) {
            }
            if (jSONArray != null) {
                builderWithFetchTime.withAbtExperiments(jSONArray);
            }
            return builderWithFetchTime.build();
        } catch (JSONException e9) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: fetch response could not be parsed.", e9);
        }
    }

    private static String extractProjectNumberFromAppId(String str) {
        Matcher matcher = GMP_APP_ID_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private JSONObject getFetchResponseBody(URLConnection uRLConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i9 = bufferedReader.read();
            if (i9 == -1) {
                return new JSONObject(sb.toString());
            }
            sb.append((char) i9);
        }
    }

    private String getFetchUrl(String str, String str2) {
        return String.format(RemoteConfigConstants.FETCH_REGEX_URL, str, str2);
    }

    private String getFingerprintHashForPackage() {
        try {
            Context context = this.context;
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, context.getPackageName());
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            Log.e(FirebaseRemoteConfig.TAG, "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException e9) {
            Log.e(FirebaseRemoteConfig.TAG, "No such package: " + this.context.getPackageName(), e9);
            return null;
        }
    }

    private void setCommonRequestHeaders(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty(API_KEY_HEADER, this.apiKey);
        httpURLConnection.setRequestProperty(X_ANDROID_PACKAGE_HEADER, this.context.getPackageName());
        httpURLConnection.setRequestProperty(X_ANDROID_CERT_HEADER, getFingerprintHashForPackage());
        httpURLConnection.setRequestProperty(X_GOOGLE_GFE_CAN_RETRY, "yes");
        httpURLConnection.setRequestProperty(INSTALLATIONS_AUTH_TOKEN_HEADER, str);
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, "application/json");
    }

    private void setCustomRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private void setFetchRequestBody(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    private void setUpUrlConnection(HttpURLConnection httpURLConnection, String str, String str2, Map<String, String> map) {
        httpURLConnection.setDoOutput(true);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        httpURLConnection.setConnectTimeout((int) timeUnit.toMillis(this.connectTimeoutInSeconds));
        httpURLConnection.setReadTimeout((int) timeUnit.toMillis(this.readTimeoutInSeconds));
        httpURLConnection.setRequestProperty("If-None-Match", str);
        setCommonRequestHeaders(httpURLConnection, str2);
        setCustomRequestHeaders(httpURLConnection, map);
    }

    public HttpURLConnection createHttpURLConnection() {
        try {
            return (HttpURLConnection) new URL(getFetchUrl(this.projectNumber, this.namespace)).openConnection();
        } catch (IOException e9) {
            throw new FirebaseRemoteConfigException(e9.getMessage());
        }
    }

    @Keep
    public ConfigFetchHandler.FetchResponse fetch(HttpURLConnection httpURLConnection, String str, String str2, Map<String, String> map, String str3, Map<String, String> map2, Date date) {
        setUpUrlConnection(httpURLConnection, str3, str2, map2);
        try {
            try {
                setFetchRequestBody(httpURLConnection, createFetchRequestBody(str, str2, map).toString().getBytes("utf-8"));
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    throw new FirebaseRemoteConfigServerException(responseCode, httpURLConnection.getResponseMessage());
                }
                String headerField = httpURLConnection.getHeaderField("ETag");
                JSONObject fetchResponseBody = getFetchResponseBody(httpURLConnection);
                try {
                    httpURLConnection.getInputStream().close();
                } catch (IOException unused) {
                }
                return !backendHasUpdates(fetchResponseBody) ? ConfigFetchHandler.FetchResponse.forBackendHasNoUpdates(date) : ConfigFetchHandler.FetchResponse.forBackendUpdatesFetched(extractConfigs(fetchResponseBody, date), headerField);
            } finally {
                httpURLConnection.disconnect();
                try {
                    httpURLConnection.getInputStream().close();
                } catch (IOException unused2) {
                }
            }
        } catch (IOException | JSONException e9) {
            throw new FirebaseRemoteConfigClientException("The client had an error while calling the backend!", e9);
        }
    }

    public long getConnectTimeoutInSeconds() {
        return this.connectTimeoutInSeconds;
    }

    public long getReadTimeoutInSeconds() {
        return this.readTimeoutInSeconds;
    }
}
