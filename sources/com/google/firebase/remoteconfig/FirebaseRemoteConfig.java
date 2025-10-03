package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.DefaultsXmlParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final String TAG = "FirebaseRemoteConfig";
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    private final ConfigCacheClient activatedConfigsCache;
    private final Context context;
    private final ConfigCacheClient defaultConfigsCache;
    private final Executor executor;
    private final ConfigFetchHandler fetchHandler;
    private final ConfigCacheClient fetchedConfigsCache;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final ConfigMetadataClient frcMetadata;
    private final ConfigGetParameterHandler getHandler;

    public FirebaseRemoteConfig(Context context, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        this.context = context;
        this.firebaseApp = firebaseApp;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.firebaseAbt = firebaseABTesting;
        this.executor = executor;
        this.fetchedConfigsCache = configCacheClient;
        this.activatedConfigsCache = configCacheClient2;
        this.defaultConfigsCache = configCacheClient3;
        this.fetchHandler = configFetchHandler;
        this.getHandler = configGetParameterHandler;
        this.frcMetadata = configMetadataClient;
    }

    public static FirebaseRemoteConfig getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    private static boolean isFetchedFresh(ConfigContainer configContainer, ConfigContainer configContainer2) {
        return configContainer2 == null || !configContainer.getFetchTime().equals(configContainer2.getFetchTime());
    }

    public static /* synthetic */ Task lambda$activate$3(FirebaseRemoteConfig firebaseRemoteConfig, Task task, Task task2, Task task3) {
        if (!task.isSuccessful() || task.getResult() == null) {
            return Tasks.forResult(Boolean.FALSE);
        }
        ConfigContainer configContainer = (ConfigContainer) task.getResult();
        return (!task2.isSuccessful() || isFetchedFresh(configContainer, (ConfigContainer) task2.getResult())) ? firebaseRemoteConfig.activatedConfigsCache.put(configContainer).continueWith(firebaseRemoteConfig.executor, FirebaseRemoteConfig$$Lambda$11.lambdaFactory$(firebaseRemoteConfig)) : Tasks.forResult(Boolean.FALSE);
    }

    public static /* synthetic */ void lambda$activateFetched$2(FirebaseRemoteConfig firebaseRemoteConfig, ConfigContainer configContainer) {
        firebaseRemoteConfig.fetchedConfigsCache.clear();
        firebaseRemoteConfig.updateAbtWithActivatedExperiments(configContainer.getAbtExperiments());
    }

    public static /* synthetic */ FirebaseRemoteConfigInfo lambda$ensureInitialized$0(Task task, Task task2) {
        return (FirebaseRemoteConfigInfo) task.getResult();
    }

    public static /* synthetic */ Task lambda$fetch$4(ConfigFetchHandler.FetchResponse fetchResponse) {
        return Tasks.forResult(null);
    }

    public static /* synthetic */ Task lambda$fetch$5(ConfigFetchHandler.FetchResponse fetchResponse) {
        return Tasks.forResult(null);
    }

    public static /* synthetic */ Task lambda$fetchAndActivate$1(FirebaseRemoteConfig firebaseRemoteConfig, Void r12) {
        return firebaseRemoteConfig.activate();
    }

    public static /* synthetic */ Void lambda$reset$7(FirebaseRemoteConfig firebaseRemoteConfig) {
        firebaseRemoteConfig.activatedConfigsCache.clear();
        firebaseRemoteConfig.fetchedConfigsCache.clear();
        firebaseRemoteConfig.defaultConfigsCache.clear();
        firebaseRemoteConfig.frcMetadata.clear();
        return null;
    }

    public static /* synthetic */ Void lambda$setConfigSettingsAsync$6(FirebaseRemoteConfig firebaseRemoteConfig, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        firebaseRemoteConfig.frcMetadata.setConfigSettings(firebaseRemoteConfigSettings);
        return null;
    }

    public static /* synthetic */ Task lambda$setDefaultsWithStringsMapAsync$8(ConfigContainer configContainer) {
        return Tasks.forResult(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processActivatePutTask(Task<ConfigContainer> task) {
        if (!task.isSuccessful()) {
            return false;
        }
        this.fetchedConfigsCache.clear();
        if (task.getResult() != null) {
            updateAbtWithActivatedExperiments(task.getResult().getAbtExperiments());
            return true;
        }
        Log.e(TAG, "Activated configs written to disk are null.");
        return true;
    }

    private void setDefaultsWithStringsMap(Map<String, String> map) {
        try {
            this.defaultConfigsCache.putWithoutWaitingForDiskWrite(ConfigContainer.newBuilder().replaceConfigsWith(map).build());
        } catch (JSONException e9) {
            Log.e(TAG, "The provided defaults map could not be processed.", e9);
        }
    }

    private Task<Void> setDefaultsWithStringsMapAsync(Map<String, String> map) {
        try {
            return this.defaultConfigsCache.put(ConfigContainer.newBuilder().replaceConfigsWith(map).build()).onSuccessTask(FirebaseRemoteConfig$$Lambda$10.instance);
        } catch (JSONException e9) {
            Log.e(TAG, "The provided defaults map could not be processed.", e9);
            return Tasks.forResult(null);
        }
    }

    public static List<Map<String, String>> toExperimentInfoMaps(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            HashMap map = new HashMap();
            JSONObject jSONObject = jSONArray.getJSONObject(i9);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.getString(next));
            }
            arrayList.add(map);
        }
        return arrayList;
    }

    public Task<Boolean> activate() {
        Task<ConfigContainer> task = this.fetchedConfigsCache.get();
        Task<ConfigContainer> task2 = this.activatedConfigsCache.get();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2}).continueWithTask(this.executor, FirebaseRemoteConfig$$Lambda$5.lambdaFactory$(this, task, task2));
    }

    @Deprecated
    public boolean activateFetched() {
        ConfigContainer blocking = this.fetchedConfigsCache.getBlocking();
        if (blocking == null || !isFetchedFresh(blocking, this.activatedConfigsCache.getBlocking())) {
            return false;
        }
        this.activatedConfigsCache.putWithoutWaitingForDiskWrite(blocking).addOnSuccessListener(this.executor, FirebaseRemoteConfig$$Lambda$4.lambdaFactory$(this));
        return true;
    }

    public Task<FirebaseRemoteConfigInfo> ensureInitialized() {
        Task<ConfigContainer> task = this.activatedConfigsCache.get();
        Task<ConfigContainer> task2 = this.defaultConfigsCache.get();
        Task<ConfigContainer> task3 = this.fetchedConfigsCache.get();
        Task taskCall = Tasks.call(this.executor, FirebaseRemoteConfig$$Lambda$1.lambdaFactory$(this));
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2, task3, taskCall, this.firebaseInstallations.getId(), this.firebaseInstallations.getToken(false)}).continueWith(this.executor, FirebaseRemoteConfig$$Lambda$2.lambdaFactory$(taskCall));
    }

    public Task<Void> fetch() {
        return this.fetchHandler.fetch().onSuccessTask(FirebaseRemoteConfig$$Lambda$6.instance);
    }

    public Task<Boolean> fetchAndActivate() {
        return fetch().onSuccessTask(this.executor, FirebaseRemoteConfig$$Lambda$3.lambdaFactory$(this));
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        return this.getHandler.getAll();
    }

    public boolean getBoolean(String str) {
        return this.getHandler.getBoolean(str);
    }

    @Deprecated
    public byte[] getByteArray(String str) {
        return this.getHandler.getByteArray(str);
    }

    public double getDouble(String str) {
        return this.getHandler.getDouble(str);
    }

    public FirebaseRemoteConfigInfo getInfo() {
        return this.frcMetadata.getInfo();
    }

    public Set<String> getKeysByPrefix(String str) {
        return this.getHandler.getKeysByPrefix(str);
    }

    public long getLong(String str) {
        return this.getHandler.getLong(str);
    }

    public String getString(String str) {
        return this.getHandler.getString(str);
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        return this.getHandler.getValue(str);
    }

    public Task<Void> reset() {
        return Tasks.call(this.executor, FirebaseRemoteConfig$$Lambda$9.lambdaFactory$(this));
    }

    @Deprecated
    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.frcMetadata.setConfigSettingsWithoutWaitingOnDiskWrite(firebaseRemoteConfigSettings);
    }

    public Task<Void> setConfigSettingsAsync(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return Tasks.call(this.executor, FirebaseRemoteConfig$$Lambda$8.lambdaFactory$(this, firebaseRemoteConfigSettings));
    }

    @Deprecated
    public void setDefaults(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                map2.put(entry.getKey(), new String((byte[]) value));
            } else {
                map2.put(entry.getKey(), value.toString());
            }
        }
        setDefaultsWithStringsMap(map2);
    }

    public Task<Void> setDefaultsAsync(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                map2.put(entry.getKey(), new String((byte[]) value));
            } else {
                map2.put(entry.getKey(), value.toString());
            }
        }
        return setDefaultsWithStringsMapAsync(map2);
    }

    public void startLoadingConfigsFromDisk() {
        this.activatedConfigsCache.get();
        this.defaultConfigsCache.get();
        this.fetchedConfigsCache.get();
    }

    public void updateAbtWithActivatedExperiments(JSONArray jSONArray) {
        if (this.firebaseAbt == null) {
            return;
        }
        try {
            this.firebaseAbt.replaceAllExperiments(toExperimentInfoMaps(jSONArray));
        } catch (AbtException e9) {
            Log.w(TAG, "Could not update ABT experiments.", e9);
        } catch (JSONException e10) {
            Log.e(TAG, "Could not parse ABT experiments from the JSON response.", e10);
        }
    }

    public static FirebaseRemoteConfig getInstance(FirebaseApp firebaseApp) {
        return ((RemoteConfigComponent) firebaseApp.get(RemoteConfigComponent.class)).getDefault();
    }

    public Task<Void> fetch(long j9) {
        return this.fetchHandler.fetch(j9).onSuccessTask(FirebaseRemoteConfig$$Lambda$7.instance);
    }

    @Deprecated
    public void setDefaults(int i9) {
        setDefaultsWithStringsMap(DefaultsXmlParser.getDefaultsFromXml(this.context, i9));
    }

    public Task<Void> setDefaultsAsync(int i9) {
        return setDefaultsWithStringsMapAsync(DefaultsXmlParser.getDefaultsFromXml(this.context, i9));
    }
}
