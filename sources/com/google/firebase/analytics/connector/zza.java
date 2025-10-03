package com.google.firebase.analytics.connector;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

/* loaded from: classes2.dex */
final /* synthetic */ class zza implements EventHandler {
    static final EventHandler zza = new zza();

    private zza() {
    }

    @Override // com.google.firebase.events.EventHandler
    public final void handle(Event event) {
        AnalyticsConnectorImpl.zza(event);
    }
}
