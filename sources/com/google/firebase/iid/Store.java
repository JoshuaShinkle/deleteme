package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import p132m.C5302a;
import p197t.C6273a;

/* loaded from: classes2.dex */
class Store {
    final Context context;
    final SharedPreferences store;
    private final Map<String, Long> subtypeCreationTimes = new C5302a();

    public static class Token {
        private static final long REFRESH_PERIOD_MILLIS = TimeUnit.DAYS.toMillis(7);
        final String appVersion;
        final long timestamp;
        final String token;

        private Token(String str, String str2, long j9) {
            this.token = str;
            this.appVersion = str2;
            this.timestamp = j9;
        }

        public static String encode(String str, String str2, long j9) throws JSONException {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("token", str);
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str2);
                jSONObject.put("timestamp", j9);
                return jSONObject.toString();
            } catch (JSONException e9) {
                String strValueOf = String.valueOf(e9);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 24);
                sb.append("Failed to encode token: ");
                sb.append(strValueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }

        public static String getTokenOrNull(Token token) {
            if (token == null) {
                return null;
            }
            return token.token;
        }

        public static Token parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!str.startsWith("{")) {
                return new Token(str, null, 0L);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new Token(jSONObject.getString("token"), jSONObject.getString(RemoteConfigConstants.RequestFieldKey.APP_VERSION), jSONObject.getLong("timestamp"));
            } catch (JSONException e9) {
                String strValueOf = String.valueOf(e9);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 23);
                sb.append("Failed to parse token: ");
                sb.append(strValueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }

        public boolean needsRefresh(String str) {
            return System.currentTimeMillis() > this.timestamp + REFRESH_PERIOD_MILLIS || !str.equals(this.appVersion);
        }
    }

    public Store(Context context) {
        this.context = context;
        this.store = context.getSharedPreferences("com.google.android.gms.appid", 0);
        checkForRestore("com.google.android.gms.appid-no-backup");
    }

    private void checkForRestore(String str) {
        File file = new File(C6273a.m24028g(this.context), str);
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || isEmpty()) {
                return;
            }
            Log.i("FirebaseInstanceId", "App restored, clearing state");
            deleteAll();
            FirebaseInstanceId.getInstance().resetStorageAndScheduleSync();
        } catch (IOException e9) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(e9.getMessage());
                Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(strValueOf) : new String("Error creating file in no backup dir: "));
            }
        }
    }

    public static String createSubtypeInfoKey(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    private String createTokenKey(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    private void deletePrefix(String str) {
        SharedPreferences.Editor editorEdit = this.store.edit();
        for (String str2 : this.store.getAll().keySet()) {
            if (str2.startsWith(str)) {
                editorEdit.remove(str2);
            }
        }
        editorEdit.commit();
    }

    private long getCreationTimeFromSharedPreferences(String str) {
        String string = this.store.getString(createSubtypeInfoKey(str, "cre"), null);
        if (string == null) {
            return 0L;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    private long writeCreationTimeToSharedPreferences(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.store.contains(createSubtypeInfoKey(str, "cre"))) {
            return getCreationTimeFromSharedPreferences(str);
        }
        SharedPreferences.Editor editorEdit = this.store.edit();
        editorEdit.putString(createSubtypeInfoKey(str, "cre"), String.valueOf(jCurrentTimeMillis));
        editorEdit.commit();
        return jCurrentTimeMillis;
    }

    public synchronized void deleteAll() {
        this.subtypeCreationTimes.clear();
        this.store.edit().clear().commit();
    }

    public synchronized void deleteToken(String str, String str2, String str3) {
        String strCreateTokenKey = createTokenKey(str, str2, str3);
        SharedPreferences.Editor editorEdit = this.store.edit();
        editorEdit.remove(strCreateTokenKey);
        editorEdit.commit();
    }

    public synchronized void deleteTokens(String str) {
        deletePrefix(String.valueOf(str).concat("|T|"));
    }

    public synchronized long getCreationTime(String str) {
        Long l9 = this.subtypeCreationTimes.get(str);
        if (l9 != null) {
            return l9.longValue();
        }
        return getCreationTimeFromSharedPreferences(str);
    }

    public synchronized Token getToken(String str, String str2, String str3) {
        return Token.parse(this.store.getString(createTokenKey(str, str2, str3), null));
    }

    public synchronized boolean isEmpty() {
        return this.store.getAll().isEmpty();
    }

    public synchronized void saveToken(String str, String str2, String str3, String str4, String str5) {
        String strEncode = Token.encode(str4, str5, System.currentTimeMillis());
        if (strEncode == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.store.edit();
        editorEdit.putString(createTokenKey(str, str2, str3), strEncode);
        editorEdit.commit();
    }

    public synchronized long setCreationTime(String str) {
        long jWriteCreationTimeToSharedPreferences;
        jWriteCreationTimeToSharedPreferences = writeCreationTimeToSharedPreferences(str);
        this.subtypeCreationTimes.put(str, Long.valueOf(jWriteCreationTimeToSharedPreferences));
        return jWriteCreationTimeToSharedPreferences;
    }
}
