package com.perfectcorp.ycl.p040bc.model.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.perfectcorp.model.Model;
import com.perfectcorp.utility.C4507b;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p047d5.C4677a;

/* loaded from: classes2.dex */
public class NetworkCommon {

    public static class ListNextResult<T extends Model> extends ListResult<T> {
        public Long next;

        public ListNextResult(Class<T> cls, String str) throws JSONException {
            this.next = null;
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.totalSize = Integer.valueOf(jSONObject.optInt("totalSize", 0));
                this.next = Long.valueOf(jSONObject.optLong("next", 0L));
                JSONArray jSONArray = jSONObject.getJSONArray("results");
                C4507b.m18110k("Success on getting result; totalSize: ", this.totalSize);
                this.results = Model.parseFromJSONArray(cls, jSONArray);
            } catch (Exception e9) {
                C4507b.m18102c(e9);
                this.totalSize = 0;
                this.results = new ArrayList<>();
            }
        }
    }

    public static class ListTimeResult<T extends Model> extends ListResult<T> {
        public Date currentTime;

        public ListTimeResult(Class<T> cls, String str) throws JSONException {
            this.currentTime = null;
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.totalSize = Integer.valueOf(jSONObject.optInt("totalSize", 0));
                long jOptLong = jSONObject.optLong("currentTime", 0L);
                if (jOptLong != 0) {
                    this.currentTime = new Date(jOptLong);
                }
                JSONArray jSONArray = jSONObject.getJSONArray("results");
                C4507b.m18110k("Success on getting result; totalSize: ", this.totalSize);
                this.results = Model.parseFromJSONArray(cls, jSONArray);
            } catch (Exception e9) {
                C4507b.m18102c(e9);
                this.totalSize = 0;
                this.results = new ArrayList<>();
            }
        }
    }

    public static class ListTrendingResult<T extends Model> extends ListResult<T> {
        public Long dNext = null;
        public Long pNext = null;
    }

    public static boolean isNetWorkFast() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) C4677a.m18710n().getBaseContext().getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            int type = activeNetworkInfo.getType();
            int subtype = activeNetworkInfo.getSubtype();
            if (type == 1) {
                return true;
            }
            if (type == 0) {
                if (subtype != 3) {
                    switch (subtype) {
                        default:
                            switch (subtype) {
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                    break;
                                default:
                                    return false;
                            }
                        case 8:
                        case 9:
                        case 10:
                            return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) C4677a.m18710n().getBaseContext().getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isConnected();
    }

    public static void registerNetworkChange(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unregisterNetworkChange(Context context, BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }

    public static class ListResult<T extends Model> {
        public String groupId;
        public ArrayList<T> results;
        public Integer totalSize;

        public ListResult() {
            this.groupId = null;
            this.totalSize = null;
            this.results = null;
        }

        public ListResult(Class<T> cls, String str) throws JSONException {
            this.groupId = null;
            this.totalSize = null;
            this.results = null;
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.groupId = jSONObject.optString("groupId", "NOT_DEFINED");
                this.totalSize = Integer.valueOf(jSONObject.optInt("totalSize", 0));
                JSONArray jSONArray = jSONObject.getJSONArray("results");
                C4507b.m18110k("Success on getting result; totalSize: ", this.totalSize);
                this.results = Model.parseFromJSONArray(cls, jSONArray);
            } catch (Exception e9) {
                C4507b.m18102c(e9);
                this.totalSize = 0;
                this.results = new ArrayList<>();
            }
        }
    }
}
