package com.google.firebase.crashlytics;

import android.os.Bundle;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.annotations.DeferredApi;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.BreadcrumbAnalyticsEventReceiver;
import com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource;
import com.google.firebase.iid.ServiceStarter;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class AnalyticsDeferredProxy {
    private final Deferred<AnalyticsConnector> analyticsConnectorDeferred;
    private volatile AnalyticsEventLogger analyticsEventLogger;
    private final List<BreadcrumbHandler> breadcrumbHandlerList;
    private volatile BreadcrumbSource breadcrumbSource;

    public AnalyticsDeferredProxy(Deferred<AnalyticsConnector> deferred) {
        this(deferred, new DisabledBreadcrumbSource(), new UnavailableAnalyticsEventLogger());
    }

    private void init() {
        this.analyticsConnectorDeferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.crashlytics.c
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                this.f15529a.lambda$init$2(provider);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getAnalyticsEventLogger$1(String str, Bundle bundle) {
        this.analyticsEventLogger.logEvent(str, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getDeferredBreadcrumbSource$0(BreadcrumbHandler breadcrumbHandler) {
        synchronized (this) {
            if (this.breadcrumbSource instanceof DisabledBreadcrumbSource) {
                this.breadcrumbHandlerList.add(breadcrumbHandler);
            }
            this.breadcrumbSource.registerBreadcrumbHandler(breadcrumbHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(Provider provider) {
        Logger.getLogger().m17767d("AnalyticsConnector now available.");
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) provider.get();
        CrashlyticsOriginAnalyticsEventLogger crashlyticsOriginAnalyticsEventLogger = new CrashlyticsOriginAnalyticsEventLogger(analyticsConnector);
        CrashlyticsAnalyticsListener crashlyticsAnalyticsListener = new CrashlyticsAnalyticsListener();
        if (subscribeToAnalyticsEvents(analyticsConnector, crashlyticsAnalyticsListener) == null) {
            Logger.getLogger().m17775w("Could not register Firebase Analytics listener; a listener is already registered.");
            return;
        }
        Logger.getLogger().m17767d("Registered Firebase Analytics listener.");
        BreadcrumbAnalyticsEventReceiver breadcrumbAnalyticsEventReceiver = new BreadcrumbAnalyticsEventReceiver();
        BlockingAnalyticsEventLogger blockingAnalyticsEventLogger = new BlockingAnalyticsEventLogger(crashlyticsOriginAnalyticsEventLogger, ServiceStarter.ERROR_UNKNOWN, TimeUnit.MILLISECONDS);
        synchronized (this) {
            Iterator<BreadcrumbHandler> it = this.breadcrumbHandlerList.iterator();
            while (it.hasNext()) {
                breadcrumbAnalyticsEventReceiver.registerBreadcrumbHandler(it.next());
            }
            crashlyticsAnalyticsListener.setBreadcrumbEventReceiver(breadcrumbAnalyticsEventReceiver);
            crashlyticsAnalyticsListener.setCrashlyticsOriginEventReceiver(blockingAnalyticsEventLogger);
            this.breadcrumbSource = breadcrumbAnalyticsEventReceiver;
            this.analyticsEventLogger = blockingAnalyticsEventLogger;
        }
    }

    @DeferredApi
    private static AnalyticsConnector.AnalyticsConnectorHandle subscribeToAnalyticsEvents(AnalyticsConnector analyticsConnector, CrashlyticsAnalyticsListener crashlyticsAnalyticsListener) {
        AnalyticsConnector.AnalyticsConnectorHandle analyticsConnectorHandleRegisterAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener("clx", crashlyticsAnalyticsListener);
        if (analyticsConnectorHandleRegisterAnalyticsConnectorListener == null) {
            Logger.getLogger().m17767d("Could not register AnalyticsConnectorListener with Crashlytics origin.");
            analyticsConnectorHandleRegisterAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener("crash", crashlyticsAnalyticsListener);
            if (analyticsConnectorHandleRegisterAnalyticsConnectorListener != null) {
                Logger.getLogger().m17775w("A new version of the Google Analytics for Firebase SDK is now available. For improved performance and compatibility with Crashlytics, please update to the latest version.");
            }
        }
        return analyticsConnectorHandleRegisterAnalyticsConnectorListener;
    }

    public AnalyticsEventLogger getAnalyticsEventLogger() {
        return new AnalyticsEventLogger() { // from class: com.google.firebase.crashlytics.b
            @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
            public final void logEvent(String str, Bundle bundle) {
                this.f15528a.lambda$getAnalyticsEventLogger$1(str, bundle);
            }
        };
    }

    public BreadcrumbSource getDeferredBreadcrumbSource() {
        return new BreadcrumbSource() { // from class: com.google.firebase.crashlytics.a
            @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource
            public final void registerBreadcrumbHandler(BreadcrumbHandler breadcrumbHandler) {
                this.f15527a.lambda$getDeferredBreadcrumbSource$0(breadcrumbHandler);
            }
        };
    }

    public AnalyticsDeferredProxy(Deferred<AnalyticsConnector> deferred, BreadcrumbSource breadcrumbSource, AnalyticsEventLogger analyticsEventLogger) {
        this.analyticsConnectorDeferred = deferred;
        this.breadcrumbSource = breadcrumbSource;
        this.breadcrumbHandlerList = new ArrayList();
        this.analyticsEventLogger = analyticsEventLogger;
        init();
    }
}
