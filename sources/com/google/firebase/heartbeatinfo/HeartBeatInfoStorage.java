package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
class HeartBeatInfoStorage {
    private static final String GLOBAL = "fire-global";
    private static final String HEARTBEAT_PREFERENCES_NAME = "FirebaseHeartBeat";
    private static final int HEART_BEAT_COUNT_LIMIT = 30;
    private static final String HEART_BEAT_COUNT_TAG = "fire-count";
    private static final String LAST_STORED_DATE = "last-used-date";
    private static final String PREFERENCES_NAME = "FirebaseAppHeartBeat";
    private static HeartBeatInfoStorage instance;
    private final SharedPreferences firebaseSharedPreferences;

    public HeartBeatInfoStorage(Context context, String str) {
        this.firebaseSharedPreferences = context.getSharedPreferences(HEARTBEAT_PREFERENCES_NAME + str, 0);
    }

    private synchronized void cleanUpStoredHeartBeats() {
        long j9 = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
        String key = "";
        String str = null;
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                for (String str2 : (Set) entry.getValue()) {
                    if (str == null || str.compareTo(str2) > 0) {
                        key = entry.getKey();
                        str = str2;
                    }
                }
            }
        }
        HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(key, new HashSet()));
        hashSet.remove(str);
        this.firebaseSharedPreferences.edit().putStringSet(key, hashSet).putLong(HEART_BEAT_COUNT_TAG, j9 - 1).commit();
    }

    private synchronized String getFormattedDate(long j9) {
        return new Date(j9).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private synchronized String getStoredUserAgentString(String str) {
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                Iterator it = ((Set) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (str.equals((String) it.next())) {
                        return entry.getKey();
                    }
                }
            }
        }
        return null;
    }

    private synchronized void removeStoredDate(String str) {
        String storedUserAgentString = getStoredUserAgentString(str);
        if (storedUserAgentString == null) {
            return;
        }
        HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(storedUserAgentString, new HashSet()));
        hashSet.remove(str);
        if (hashSet.isEmpty()) {
            this.firebaseSharedPreferences.edit().remove(storedUserAgentString).commit();
        } else {
            this.firebaseSharedPreferences.edit().putStringSet(storedUserAgentString, hashSet).commit();
        }
    }

    private synchronized void updateStoredUserAgent(String str, String str2) {
        removeStoredDate(str2);
        HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(str, new HashSet()));
        hashSet.add(str2);
        this.firebaseSharedPreferences.edit().putStringSet(str, hashSet).commit();
    }

    public synchronized void deleteAllHeartBeats() {
        SharedPreferences.Editor editorEdit = this.firebaseSharedPreferences.edit();
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                editorEdit.remove(entry.getKey());
            }
        }
        editorEdit.remove(HEART_BEAT_COUNT_TAG);
        editorEdit.commit();
    }

    public synchronized List<HeartBeatResult> getAllHeartBeats() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                arrayList.add(HeartBeatResult.create(entry.getKey(), new ArrayList((Set) entry.getValue())));
            }
        }
        updateGlobalHeartBeat(System.currentTimeMillis());
        return arrayList;
    }

    public int getHeartBeatCount() {
        return (int) this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
    }

    public synchronized long getLastGlobalHeartBeat() {
        return this.firebaseSharedPreferences.getLong(GLOBAL, -1L);
    }

    public synchronized boolean isSameDateUtc(long j9, long j10) {
        return getFormattedDate(j9).equals(getFormattedDate(j10));
    }

    public synchronized void postHeartBeatCleanUp() {
        String formattedDate = getFormattedDate(System.currentTimeMillis());
        this.firebaseSharedPreferences.edit().putString(LAST_STORED_DATE, formattedDate).commit();
        removeStoredDate(formattedDate);
    }

    public synchronized boolean shouldSendGlobalHeartBeat(long j9) {
        return shouldSendSdkHeartBeat(GLOBAL, j9);
    }

    public synchronized boolean shouldSendSdkHeartBeat(String str, long j9) {
        if (!this.firebaseSharedPreferences.contains(str)) {
            this.firebaseSharedPreferences.edit().putLong(str, j9).commit();
            return true;
        }
        if (isSameDateUtc(this.firebaseSharedPreferences.getLong(str, -1L), j9)) {
            return false;
        }
        this.firebaseSharedPreferences.edit().putLong(str, j9).commit();
        return true;
    }

    public synchronized void storeHeartBeat(long j9, String str) {
        String formattedDate = getFormattedDate(j9);
        if (this.firebaseSharedPreferences.getString(LAST_STORED_DATE, "").equals(formattedDate)) {
            String storedUserAgentString = getStoredUserAgentString(formattedDate);
            if (storedUserAgentString == null) {
                return;
            }
            if (storedUserAgentString.equals(str)) {
                return;
            }
            updateStoredUserAgent(str, formattedDate);
            return;
        }
        long j10 = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
        if (j10 + 1 == 30) {
            cleanUpStoredHeartBeats();
            j10 = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
        }
        HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(str, new HashSet()));
        hashSet.add(formattedDate);
        this.firebaseSharedPreferences.edit().putStringSet(str, hashSet).putLong(HEART_BEAT_COUNT_TAG, j10 + 1).putString(LAST_STORED_DATE, formattedDate).commit();
    }

    public synchronized void updateGlobalHeartBeat(long j9) {
        this.firebaseSharedPreferences.edit().putLong(GLOBAL, j9).commit();
    }

    public HeartBeatInfoStorage(SharedPreferences sharedPreferences) {
        this.firebaseSharedPreferences = sharedPreferences;
    }
}
