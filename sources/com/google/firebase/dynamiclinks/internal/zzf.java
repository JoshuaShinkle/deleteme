package com.google.firebase.dynamiclinks.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* loaded from: classes2.dex */
final /* synthetic */ class zzf implements ComponentFactory {
    static final ComponentFactory zzs = new zzf();

    private zzf() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new zze((FirebaseApp) componentContainer.get(FirebaseApp.class), (AnalyticsConnector) componentContainer.get(AnalyticsConnector.class));
    }
}
