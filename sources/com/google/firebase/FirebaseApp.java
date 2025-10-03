package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.api.internal.C3457a;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentDiscoveryService;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.UiExecutor;
import com.google.firebase.events.Publisher;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.DataCollectionConfigStorage;
import com.google.firebase.provider.FirebaseInitProvider;
import com.google.firebase.tracing.ComponentMonitor;
import com.google.firebase.tracing.FirebaseTrace;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p132m.C5302a;
import p251z.C6801g;

/* loaded from: classes2.dex */
public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final String LOG_TAG = "FirebaseApp";
    private final Context applicationContext;
    private final ComponentRuntime componentRuntime;
    private final Lazy<DataCollectionConfigStorage> dataCollectionConfigStorage;
    private final Provider<DefaultHeartBeatController> defaultHeartBeatController;
    private final String name;
    private final FirebaseOptions options;
    private static final Object LOCK = new Object();
    static final Map<String, FirebaseApp> INSTANCES = new C5302a();
    private final AtomicBoolean automaticResourceManagementEnabled = new AtomicBoolean(false);
    private final AtomicBoolean deleted = new AtomicBoolean();
    private final List<BackgroundStateChangeListener> backgroundStateChangeListeners = new CopyOnWriteArrayList();
    private final List<FirebaseAppLifecycleListener> lifecycleListeners = new CopyOnWriteArrayList();

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z8);
    }

    @TargetApi(14)
    public static class GlobalBackgroundStateListener implements BackgroundDetector.BackgroundStateChangeListener {
        private static AtomicReference<GlobalBackgroundStateListener> INSTANCE = new AtomicReference<>();

        private GlobalBackgroundStateListener() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void ensureBackgroundStateListenerRegistered(Context context) {
            if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
                Application application = (Application) context.getApplicationContext();
                if (INSTANCE.get() == null) {
                    GlobalBackgroundStateListener globalBackgroundStateListener = new GlobalBackgroundStateListener();
                    if (C3457a.m17508a(INSTANCE, null, globalBackgroundStateListener)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener(globalBackgroundStateListener);
                    }
                }
            }
        }

        @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
        public void onBackgroundStateChanged(boolean z8) {
            synchronized (FirebaseApp.LOCK) {
                Iterator it = new ArrayList(FirebaseApp.INSTANCES.values()).iterator();
                while (it.hasNext()) {
                    FirebaseApp firebaseApp = (FirebaseApp) it.next();
                    if (firebaseApp.automaticResourceManagementEnabled.get()) {
                        firebaseApp.notifyBackgroundStateChangeListeners(z8);
                    }
                }
            }
        }
    }

    @TargetApi(24)
    public static class UserUnlockReceiver extends BroadcastReceiver {
        private static AtomicReference<UserUnlockReceiver> INSTANCE = new AtomicReference<>();
        private final Context applicationContext;

        public UserUnlockReceiver(Context context) {
            this.applicationContext = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void ensureReceiverRegistered(Context context) {
            if (INSTANCE.get() == null) {
                UserUnlockReceiver userUnlockReceiver = new UserUnlockReceiver(context);
                if (C3457a.m17508a(INSTANCE, null, userUnlockReceiver)) {
                    context.registerReceiver(userUnlockReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.LOCK) {
                Iterator<FirebaseApp> it = FirebaseApp.INSTANCES.values().iterator();
                while (it.hasNext()) {
                    it.next().initializeAllApis();
                }
            }
            unregister();
        }

        public void unregister() {
            this.applicationContext.unregisterReceiver(this);
        }
    }

    public FirebaseApp(final Context context, String str, FirebaseOptions firebaseOptions) {
        this.applicationContext = (Context) Preconditions.checkNotNull(context);
        this.name = Preconditions.checkNotEmpty(str);
        this.options = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
        StartupTime startupTime = FirebaseInitProvider.getStartupTime();
        FirebaseTrace.pushTrace("Firebase");
        FirebaseTrace.pushTrace("ComponentDiscovery");
        List<Provider<ComponentRegistrar>> listDiscoverLazy = ComponentDiscovery.forContext(context, ComponentDiscoveryService.class).discoverLazy();
        FirebaseTrace.popTrace();
        FirebaseTrace.pushTrace("Runtime");
        ComponentRuntime.Builder processor = ComponentRuntime.builder(UiExecutor.INSTANCE).addLazyComponentRegistrars(listDiscoverLazy).addComponentRegistrar(new FirebaseCommonRegistrar()).addComponentRegistrar(new ExecutorsRegistrar()).addComponent(Component.m17728of(context, (Class<Context>) Context.class, (Class<? super Context>[]) new Class[0])).addComponent(Component.m17728of(this, (Class<FirebaseApp>) FirebaseApp.class, (Class<? super FirebaseApp>[]) new Class[0])).addComponent(Component.m17728of(firebaseOptions, (Class<FirebaseOptions>) FirebaseOptions.class, (Class<? super FirebaseOptions>[]) new Class[0])).setProcessor(new ComponentMonitor());
        if (C6801g.m25357a(context) && FirebaseInitProvider.isCurrentlyInitializing()) {
            processor.addComponent(Component.m17728of(startupTime, (Class<StartupTime>) StartupTime.class, (Class<? super StartupTime>[]) new Class[0]));
        }
        ComponentRuntime componentRuntimeBuild = processor.build();
        this.componentRuntime = componentRuntimeBuild;
        FirebaseTrace.popTrace();
        this.dataCollectionConfigStorage = new Lazy<>(new Provider() { // from class: com.google.firebase.a
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return this.f15466a.lambda$new$0(context);
            }
        });
        this.defaultHeartBeatController = componentRuntimeBuild.getProvider(DefaultHeartBeatController.class);
        addBackgroundStateChangeListener(new BackgroundStateChangeListener() { // from class: com.google.firebase.b
            @Override // com.google.firebase.FirebaseApp.BackgroundStateChangeListener
            public final void onBackgroundStateChanged(boolean z8) {
                this.f15468a.lambda$new$1(z8);
            }
        });
        FirebaseTrace.popTrace();
    }

    private void checkNotDeleted() {
        Preconditions.checkState(!this.deleted.get(), "FirebaseApp was deleted");
    }

    public static void clearInstancesForTest() {
        synchronized (LOCK) {
            INSTANCES.clear();
        }
    }

    private static List<String> getAllAppNames() {
        ArrayList arrayList = new ArrayList();
        synchronized (LOCK) {
            Iterator<FirebaseApp> it = INSTANCES.values().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getName());
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static List<FirebaseApp> getApps(Context context) {
        ArrayList arrayList;
        synchronized (LOCK) {
            arrayList = new ArrayList(INSTANCES.values());
        }
        return arrayList;
    }

    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (LOCK) {
            firebaseApp = INSTANCES.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
            }
        }
        return firebaseApp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeAllApis() {
        if (!C6801g.m25357a(this.applicationContext)) {
            Log.i(LOG_TAG, "Device in Direct Boot Mode: postponing initialization of Firebase APIs for app " + getName());
            UserUnlockReceiver.ensureReceiverRegistered(this.applicationContext);
            return;
        }
        Log.i(LOG_TAG, "Device unlocked: initializing all Firebase APIs for app " + getName());
        this.componentRuntime.initializeEagerComponents(isDefaultApp());
        this.defaultHeartBeatController.get().registerHeartBeat();
    }

    public static FirebaseApp initializeApp(Context context) {
        synchronized (LOCK) {
            if (INSTANCES.containsKey(DEFAULT_APP_NAME)) {
                return getInstance();
            }
            FirebaseOptions firebaseOptionsFromResource = FirebaseOptions.fromResource(context);
            if (firebaseOptionsFromResource == null) {
                Log.w(LOG_TAG, "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                return null;
            }
            return initializeApp(context, firebaseOptionsFromResource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DataCollectionConfigStorage lambda$new$0(Context context) {
        return new DataCollectionConfigStorage(context, getPersistenceKey(), (Publisher) this.componentRuntime.get(Publisher.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(boolean z8) {
        if (z8) {
            return;
        }
        this.defaultHeartBeatController.get().registerHeartBeat();
    }

    private static String normalize(String str) {
        return str.trim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBackgroundStateChangeListeners(boolean z8) {
        Log.d(LOG_TAG, "Notifying background state change listeners.");
        Iterator<BackgroundStateChangeListener> it = this.backgroundStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onBackgroundStateChanged(z8);
        }
    }

    private void notifyOnAppDeleted() {
        Iterator<FirebaseAppLifecycleListener> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onDeleted(this.name, this.options);
        }
    }

    @KeepForSdk
    public void addBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        checkNotDeleted();
        if (this.automaticResourceManagementEnabled.get() && BackgroundDetector.getInstance().isInBackground()) {
            backgroundStateChangeListener.onBackgroundStateChanged(true);
        }
        this.backgroundStateChangeListeners.add(backgroundStateChangeListener);
    }

    @KeepForSdk
    public void addLifecycleEventListener(FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        checkNotDeleted();
        Preconditions.checkNotNull(firebaseAppLifecycleListener);
        this.lifecycleListeners.add(firebaseAppLifecycleListener);
    }

    public void delete() {
        if (this.deleted.compareAndSet(false, true)) {
            synchronized (LOCK) {
                INSTANCES.remove(this.name);
            }
            notifyOnAppDeleted();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof FirebaseApp) {
            return this.name.equals(((FirebaseApp) obj).getName());
        }
        return false;
    }

    @KeepForSdk
    public <T> T get(Class<T> cls) {
        checkNotDeleted();
        return (T) this.componentRuntime.get(cls);
    }

    public Context getApplicationContext() {
        checkNotDeleted();
        return this.applicationContext;
    }

    public String getName() {
        checkNotDeleted();
        return this.name;
    }

    public FirebaseOptions getOptions() {
        checkNotDeleted();
        return this.options;
    }

    @KeepForSdk
    public String getPersistenceKey() {
        return Base64Utils.encodeUrlSafeNoPadding(getName().getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes(Charset.defaultCharset()));
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public void initializeAllComponents() {
        this.componentRuntime.initializeAllComponentsForTests();
    }

    @KeepForSdk
    public boolean isDataCollectionDefaultEnabled() {
        checkNotDeleted();
        return this.dataCollectionConfigStorage.get().isEnabled();
    }

    @KeepForSdk
    public boolean isDefaultApp() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    @KeepForSdk
    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        checkNotDeleted();
        this.backgroundStateChangeListeners.remove(backgroundStateChangeListener);
    }

    @KeepForSdk
    public void removeLifecycleEventListener(FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        checkNotDeleted();
        Preconditions.checkNotNull(firebaseAppLifecycleListener);
        this.lifecycleListeners.remove(firebaseAppLifecycleListener);
    }

    public void setAutomaticResourceManagementEnabled(boolean z8) {
        checkNotDeleted();
        if (this.automaticResourceManagementEnabled.compareAndSet(!z8, z8)) {
            boolean zIsInBackground = BackgroundDetector.getInstance().isInBackground();
            if (z8 && zIsInBackground) {
                notifyBackgroundStateChangeListeners(true);
            } else {
                if (z8 || !zIsInBackground) {
                    return;
                }
                notifyBackgroundStateChangeListeners(false);
            }
        }
    }

    @KeepForSdk
    public void setDataCollectionDefaultEnabled(Boolean bool) {
        checkNotDeleted();
        this.dataCollectionConfigStorage.get().setEnabled(bool);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).add("options", this.options).toString();
    }

    @KeepForSdk
    @Deprecated
    public void setDataCollectionDefaultEnabled(boolean z8) {
        setDataCollectionDefaultEnabled(Boolean.valueOf(z8));
    }

    @KeepForSdk
    public static String getPersistenceKey(String str, FirebaseOptions firebaseOptions) {
        return Base64Utils.encodeUrlSafeNoPadding(str.getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(firebaseOptions.getApplicationId().getBytes(Charset.defaultCharset()));
    }

    public static FirebaseApp getInstance(String str) {
        FirebaseApp firebaseApp;
        String str2;
        synchronized (LOCK) {
            firebaseApp = INSTANCES.get(normalize(str));
            if (firebaseApp != null) {
                firebaseApp.defaultHeartBeatController.get().registerHeartBeat();
            } else {
                List<String> allAppNames = getAllAppNames();
                if (allAppNames.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = "Available app names: " + TextUtils.join(", ", allAppNames);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", str, str2));
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        GlobalBackgroundStateListener.ensureBackgroundStateListenerRegistered(context);
        String strNormalize = normalize(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (LOCK) {
            Map<String, FirebaseApp> map = INSTANCES;
            Preconditions.checkState(!map.containsKey(strNormalize), "FirebaseApp name " + strNormalize + " already exists!");
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, strNormalize, firebaseOptions);
            map.put(strNormalize, firebaseApp);
        }
        firebaseApp.initializeAllApis();
        return firebaseApp;
    }
}
