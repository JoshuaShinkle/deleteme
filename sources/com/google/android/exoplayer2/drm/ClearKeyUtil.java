package com.google.android.exoplayer2.drm;

import android.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.p159io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class ClearKeyUtil {
    private static final Pattern REQUEST_KIDS_PATTERN = Pattern.compile("\"kids\":\\[\"(.*?)\"]");
    private static final String TAG = "ClearKeyUtil";

    private ClearKeyUtil() {
    }

    public static byte[] adjustRequestData(byte[] bArr) {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        String strFromUtf8Bytes = Util.fromUtf8Bytes(bArr);
        Matcher matcher = REQUEST_KIDS_PATTERN.matcher(strFromUtf8Bytes);
        if (matcher.find()) {
            int iStart = matcher.start(1);
            int iEnd = matcher.end(1);
            StringBuilder sb = new StringBuilder(strFromUtf8Bytes);
            base64ToBase64Url(sb, iStart, iEnd);
            return Util.getUtf8Bytes(sb.toString());
        }
        Log.e(TAG, "Failed to adjust request data: " + strFromUtf8Bytes);
        return bArr;
    }

    public static byte[] adjustResponseData(byte[] bArr) throws JSONException {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        try {
            JSONObject jSONObject = new JSONObject(Util.fromUtf8Bytes(bArr));
            JSONArray jSONArray = jSONObject.getJSONArray(UserMetadata.KEYDATA_FILENAME);
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i9);
                jSONObject2.put("k", base64UrlToBase64(jSONObject2.getString("k")));
                jSONObject2.put("kid", base64UrlToBase64(jSONObject2.getString("kid")));
            }
            return Util.getUtf8Bytes(jSONObject.toString());
        } catch (JSONException e9) {
            Log.e(TAG, "Failed to adjust response data: " + Util.fromUtf8Bytes(bArr), e9);
            return bArr;
        }
    }

    private static void base64ToBase64Url(StringBuilder sb, int i9, int i10) {
        while (i9 < i10) {
            char cCharAt = sb.charAt(i9);
            if (cCharAt == '+') {
                sb.setCharAt(i9, '-');
            } else if (cCharAt == '/') {
                sb.setCharAt(i9, '_');
            }
            i9++;
        }
    }

    private static String base64UrlToBase64(String str) {
        return str.replace('-', '+').replace('_', IOUtils.DIR_SEPARATOR_UNIX);
    }
}
