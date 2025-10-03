package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.proto.ConfigPersistence;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p185r5.C6223c;

/* loaded from: classes2.dex */
public class LegacyConfigsHandler {
    static final String ACTIVATE_FILE_NAME = "activate";
    static final String DEFAULTS_FILE_NAME = "defaults";
    public static final String EXPERIMENT_ID_KEY = "experimentId";
    public static final String EXPERIMENT_START_TIME_KEY = "experimentStartTime";
    public static final String EXPERIMENT_TIME_TO_LIVE_KEY = "timeToLiveMillis";
    public static final String EXPERIMENT_TRIGGER_EVENT_KEY = "triggerEvent";
    public static final String EXPERIMENT_TRIGGER_TIMEOUT_KEY = "triggerTimeoutMillis";
    public static final String EXPERIMENT_VARIANT_ID_KEY = "variantId";
    static final String FETCH_FILE_NAME = "fetch";
    private static final String FRC_3P_NAMESPACE = "firebase";
    static final String LEGACY_CONFIGS_FILE_NAME = "persisted_config";
    static final String LEGACY_FRC_NAMESPACE_PREFIX = "configns:";
    private static final String LEGACY_SETTINGS_FILE_NAME = "com.google.firebase.remoteconfig_legacy_settings";
    private static final String SAVE_LEGACY_CONFIGS_FLAG_NAME = "save_legacy_configs";
    private final String appId;
    private final Context context;
    private final SharedPreferences legacySettings;
    private static final Charset PROTO_BYTE_ARRAY_ENCODING = Charset.forName("UTF-8");
    static final ThreadLocal<DateFormat> protoTimestampStringParser = new ThreadLocal<DateFormat>() { // from class: com.google.firebase.remoteconfig.internal.LegacyConfigsHandler.1
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        }
    };

    public static class NamespaceLegacyConfigs {
        private ConfigContainer activatedConfigs;
        private ConfigContainer defaultsConfigs;
        private ConfigContainer fetchedConfigs;

        private NamespaceLegacyConfigs() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigContainer getActivatedConfigs() {
            return this.activatedConfigs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigContainer getDefaultsConfigs() {
            return this.defaultsConfigs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ConfigContainer getFetchedConfigs() {
            return this.fetchedConfigs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActivatedConfigs(ConfigContainer configContainer) {
            this.activatedConfigs = configContainer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDefaultsConfigs(ConfigContainer configContainer) {
            this.defaultsConfigs = configContainer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFetchedConfigs(ConfigContainer configContainer) {
            this.fetchedConfigs = configContainer;
        }
    }

    public LegacyConfigsHandler(Context context, String str) {
        this.context = context;
        this.appId = str;
        this.legacySettings = context.getSharedPreferences(LEGACY_SETTINGS_FILE_NAME, 0);
    }

    private Map<String, ConfigContainer> convertConfigHolder(ConfigPersistence.ConfigHolder configHolder) {
        HashMap map = new HashMap();
        Date date = new Date(configHolder.getTimestamp());
        JSONArray jSONArrayConvertLegacyAbtExperiments = convertLegacyAbtExperiments(configHolder.getExperimentPayloadList());
        for (ConfigPersistence.NamespaceKeyValue namespaceKeyValue : configHolder.getNamespaceKeyValueList()) {
            String namespace = namespaceKeyValue.getNamespace();
            if (namespace.startsWith(LEGACY_FRC_NAMESPACE_PREFIX)) {
                namespace = namespace.substring(9);
            }
            ConfigContainer.Builder builderWithFetchTime = ConfigContainer.newBuilder().replaceConfigsWith(convertKeyValueList(namespaceKeyValue.getKeyValueList())).withFetchTime(date);
            if (namespace.equals("firebase")) {
                builderWithFetchTime.withAbtExperiments(jSONArrayConvertLegacyAbtExperiments);
            }
            try {
                map.put(namespace, builderWithFetchTime.build());
            } catch (JSONException unused) {
                Log.d(FirebaseRemoteConfig.TAG, "A set of legacy configs could not be converted.");
            }
        }
        return map;
    }

    private Map<String, String> convertKeyValueList(List<ConfigPersistence.KeyValue> list) {
        HashMap map = new HashMap();
        for (ConfigPersistence.KeyValue keyValue : list) {
            map.put(keyValue.getKey(), keyValue.getValue().toString(PROTO_BYTE_ARRAY_ENCODING));
        }
        return map;
    }

    private JSONObject convertLegacyAbtExperiment(C6223c c6223c) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("experimentId", c6223c.m23776b());
        jSONObject.put("variantId", c6223c.m23781g());
        jSONObject.put(EXPERIMENT_START_TIME_KEY, protoTimestampStringParser.get().format(new Date(c6223c.m23777c())));
        jSONObject.put(EXPERIMENT_TRIGGER_EVENT_KEY, c6223c.m23779e());
        jSONObject.put(EXPERIMENT_TRIGGER_TIMEOUT_KEY, c6223c.m23780f());
        jSONObject.put(EXPERIMENT_TIME_TO_LIVE_KEY, c6223c.m23778d());
        return jSONObject;
    }

    private JSONArray convertLegacyAbtExperiments(List<ByteString> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<ByteString> it = list.iterator();
        while (it.hasNext()) {
            C6223c c6223cDeserializePayload = deserializePayload(it.next());
            if (c6223cDeserializePayload != null) {
                try {
                    jSONArray.put(convertLegacyAbtExperiment(c6223cDeserializePayload));
                } catch (JSONException e9) {
                    Log.d(FirebaseRemoteConfig.TAG, "A legacy ABT experiment could not be parsed.", e9);
                }
            }
        }
        return jSONArray;
    }

    private C6223c deserializePayload(ByteString byteString) {
        try {
            Iterator<Byte> itIterator2 = byteString.iterator2();
            int size = byteString.size();
            byte[] bArr = new byte[size];
            for (int i9 = 0; i9 < size; i9++) {
                bArr[i9] = itIterator2.next().byteValue();
            }
            return C6223c.m23775h(bArr);
        } catch (InvalidProtocolBufferException e9) {
            Log.d(FirebaseRemoteConfig.TAG, "Payload was not defined or could not be deserialized.", e9);
            return null;
        }
    }

    private Map<String, NamespaceLegacyConfigs> getConvertedLegacyConfigs() throws Throwable {
        ConfigPersistence.PersistedConfig persistedConfig = readPersistedConfig();
        HashMap map = new HashMap();
        if (persistedConfig == null) {
            return map;
        }
        Map<String, ConfigContainer> mapConvertConfigHolder = convertConfigHolder(persistedConfig.getActiveConfigHolder());
        Map<String, ConfigContainer> mapConvertConfigHolder2 = convertConfigHolder(persistedConfig.getFetchedConfigHolder());
        Map<String, ConfigContainer> mapConvertConfigHolder3 = convertConfigHolder(persistedConfig.getDefaultsConfigHolder());
        HashSet<String> hashSet = new HashSet();
        hashSet.addAll(mapConvertConfigHolder.keySet());
        hashSet.addAll(mapConvertConfigHolder2.keySet());
        hashSet.addAll(mapConvertConfigHolder3.keySet());
        for (String str : hashSet) {
            NamespaceLegacyConfigs namespaceLegacyConfigs = new NamespaceLegacyConfigs();
            if (mapConvertConfigHolder.containsKey(str)) {
                namespaceLegacyConfigs.setActivatedConfigs(mapConvertConfigHolder.get(str));
            }
            if (mapConvertConfigHolder2.containsKey(str)) {
                namespaceLegacyConfigs.setFetchedConfigs(mapConvertConfigHolder2.get(str));
            }
            if (mapConvertConfigHolder3.containsKey(str)) {
                namespaceLegacyConfigs.setDefaultsConfigs(mapConvertConfigHolder3.get(str));
            }
            map.put(str, namespaceLegacyConfigs);
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    private ConfigPersistence.PersistedConfig readPersistedConfig() throws Throwable {
        FileInputStream fileInputStreamOpenFileInput;
        ?? r22 = this.context;
        try {
            if (r22 == 0) {
                return null;
            }
            try {
                fileInputStreamOpenFileInput = r22.openFileInput(LEGACY_CONFIGS_FILE_NAME);
                try {
                    ConfigPersistence.PersistedConfig from = ConfigPersistence.PersistedConfig.parseFrom(fileInputStreamOpenFileInput);
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e9) {
                            Log.d(FirebaseRemoteConfig.TAG, "Failed to close persisted config file.", e9);
                        }
                    }
                    return from;
                } catch (FileNotFoundException e10) {
                    e = e10;
                    Log.d(FirebaseRemoteConfig.TAG, "Persisted config file was not found.", e);
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e11) {
                            Log.d(FirebaseRemoteConfig.TAG, "Failed to close persisted config file.", e11);
                        }
                    }
                    return null;
                } catch (IOException e12) {
                    e = e12;
                    Log.d(FirebaseRemoteConfig.TAG, "Cannot initialize from persisted config.", e);
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e13) {
                            Log.d(FirebaseRemoteConfig.TAG, "Failed to close persisted config file.", e13);
                        }
                    }
                    return null;
                }
            } catch (FileNotFoundException e14) {
                e = e14;
                fileInputStreamOpenFileInput = null;
            } catch (IOException e15) {
                e = e15;
                fileInputStreamOpenFileInput = null;
            } catch (Throwable th) {
                th = th;
                r22 = 0;
                if (r22 != 0) {
                    try {
                        r22.close();
                    } catch (IOException e16) {
                        Log.d(FirebaseRemoteConfig.TAG, "Failed to close persisted config file.", e16);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void saveLegacyConfigs(Map<String, NamespaceLegacyConfigs> map) {
        for (Map.Entry<String, NamespaceLegacyConfigs> entry : map.entrySet()) {
            String key = entry.getKey();
            NamespaceLegacyConfigs value = entry.getValue();
            ConfigCacheClient cacheClient = getCacheClient(key, "fetch");
            ConfigCacheClient cacheClient2 = getCacheClient(key, "activate");
            ConfigCacheClient cacheClient3 = getCacheClient(key, "defaults");
            if (value.getFetchedConfigs() != null) {
                cacheClient.put(value.getFetchedConfigs());
            }
            if (value.getActivatedConfigs() != null) {
                cacheClient2.put(value.getActivatedConfigs());
            }
            if (value.getDefaultsConfigs() != null) {
                cacheClient3.put(value.getDefaultsConfigs());
            }
        }
    }

    public ConfigCacheClient getCacheClient(String str, String str2) {
        return RemoteConfigComponent.getCacheClient(this.context, this.appId, str, str2);
    }

    public boolean saveLegacyConfigsIfNecessary() {
        if (!this.legacySettings.getBoolean(SAVE_LEGACY_CONFIGS_FLAG_NAME, true)) {
            return false;
        }
        saveLegacyConfigs(getConvertedLegacyConfigs());
        this.legacySettings.edit().putBoolean(SAVE_LEGACY_CONFIGS_FLAG_NAME, false).commit();
        return true;
    }
}
