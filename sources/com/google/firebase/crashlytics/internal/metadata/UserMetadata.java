package com.google.firebase.crashlytics.internal.metadata;

import com.google.android.gms.common.api.internal.C3457a;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class UserMetadata {
    public static final String INTERNAL_KEYDATA_FILENAME = "internal-keys";
    public static final String KEYDATA_FILENAME = "keys";
    public static final int MAX_ATTRIBUTES = 64;
    public static final int MAX_ATTRIBUTE_SIZE = 1024;
    public static final int MAX_INTERNAL_KEY_SIZE = 8192;
    public static final String USERDATA_FILENAME = "user-data";
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private final MetaDataStore metaDataStore;
    private final String sessionIdentifier;
    private final SerializeableKeysMap customKeys = new SerializeableKeysMap(false);
    private final SerializeableKeysMap internalKeys = new SerializeableKeysMap(true);
    private final AtomicMarkableReference<String> userId = new AtomicMarkableReference<>(null, false);

    public class SerializeableKeysMap {
        private final boolean isInternal;
        final AtomicMarkableReference<KeysMap> map;
        private final AtomicReference<Callable<Void>> queuedSerializer = new AtomicReference<>(null);

        public SerializeableKeysMap(boolean z8) {
            this.isInternal = z8;
            this.map = new AtomicMarkableReference<>(new KeysMap(64, z8 ? UserMetadata.MAX_INTERNAL_KEY_SIZE : UserMetadata.MAX_ATTRIBUTE_SIZE), false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Void lambda$scheduleSerializationTaskIfNeeded$0() throws Throwable {
            this.queuedSerializer.set(null);
            serializeIfMarked();
            return null;
        }

        private void scheduleSerializationTaskIfNeeded() {
            Callable callable = new Callable() { // from class: com.google.firebase.crashlytics.internal.metadata.b
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f15547b.lambda$scheduleSerializationTaskIfNeeded$0();
                }
            };
            if (C3457a.m17508a(this.queuedSerializer, null, callable)) {
                UserMetadata.this.backgroundWorker.submit(callable);
            }
        }

        private void serializeIfMarked() throws Throwable {
            Map<String, String> keys;
            synchronized (this) {
                if (this.map.isMarked()) {
                    keys = this.map.getReference().getKeys();
                    AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                    atomicMarkableReference.set(atomicMarkableReference.getReference(), false);
                } else {
                    keys = null;
                }
            }
            if (keys != null) {
                UserMetadata.this.metaDataStore.writeKeyData(UserMetadata.this.sessionIdentifier, keys, this.isInternal);
            }
        }

        public Map<String, String> getKeys() {
            return this.map.getReference().getKeys();
        }

        public boolean setKey(String str, String str2) {
            synchronized (this) {
                if (!this.map.getReference().setKey(str, str2)) {
                    return false;
                }
                AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
                scheduleSerializationTaskIfNeeded();
                return true;
            }
        }

        public void setKeys(Map<String, String> map) {
            synchronized (this) {
                this.map.getReference().setKeys(map);
                AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
            }
            scheduleSerializationTaskIfNeeded();
        }
    }

    public UserMetadata(String str, FileStore fileStore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker) {
        this.sessionIdentifier = str;
        this.metaDataStore = new MetaDataStore(fileStore);
        this.backgroundWorker = crashlyticsBackgroundWorker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$setUserId$0() throws Throwable {
        serializeUserDataIfNeeded();
        return null;
    }

    public static UserMetadata loadFromExistingSession(String str, FileStore fileStore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker) {
        MetaDataStore metaDataStore = new MetaDataStore(fileStore);
        UserMetadata userMetadata = new UserMetadata(str, fileStore, crashlyticsBackgroundWorker);
        userMetadata.customKeys.map.getReference().setKeys(metaDataStore.readKeyData(str, false));
        userMetadata.internalKeys.map.getReference().setKeys(metaDataStore.readKeyData(str, true));
        userMetadata.userId.set(metaDataStore.readUserId(str), false);
        return userMetadata;
    }

    public static String readUserId(String str, FileStore fileStore) {
        return new MetaDataStore(fileStore).readUserId(str);
    }

    private void serializeUserDataIfNeeded() throws Throwable {
        boolean z8;
        String userId;
        synchronized (this.userId) {
            z8 = false;
            if (this.userId.isMarked()) {
                userId = getUserId();
                this.userId.set(userId, false);
                z8 = true;
            } else {
                userId = null;
            }
        }
        if (z8) {
            this.metaDataStore.writeUserData(this.sessionIdentifier, userId);
        }
    }

    public Map<String, String> getCustomKeys() {
        return this.customKeys.getKeys();
    }

    public Map<String, String> getInternalKeys() {
        return this.internalKeys.getKeys();
    }

    public String getUserId() {
        return this.userId.getReference();
    }

    public boolean setCustomKey(String str, String str2) {
        return this.customKeys.setKey(str, str2);
    }

    public void setCustomKeys(Map<String, String> map) {
        this.customKeys.setKeys(map);
    }

    public boolean setInternalKey(String str, String str2) {
        return this.internalKeys.setKey(str, str2);
    }

    public void setUserId(String str) {
        String strSanitizeString = KeysMap.sanitizeString(str, MAX_ATTRIBUTE_SIZE);
        synchronized (this.userId) {
            if (CommonUtils.nullSafeEquals(strSanitizeString, this.userId.getReference())) {
                return;
            }
            this.userId.set(strSanitizeString, true);
            this.backgroundWorker.submit(new Callable() { // from class: com.google.firebase.crashlytics.internal.metadata.a
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f15546b.lambda$setUserId$0();
                }
            });
        }
    }
}
