package com.google.firebase.abt.component;

import android.content.Context;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class AbtComponent {
    private final Map<String, FirebaseABTesting> abtOriginInstances = new HashMap();
    private final AnalyticsConnector analyticsConnector;
    private final Context appContext;

    public AbtComponent(Context context, AnalyticsConnector analyticsConnector) {
        this.appContext = context;
        this.analyticsConnector = analyticsConnector;
    }

    public FirebaseABTesting createAbtInstance(String str) {
        return new FirebaseABTesting(this.appContext, this.analyticsConnector, str);
    }

    public synchronized FirebaseABTesting get(String str) {
        if (!this.abtOriginInstances.containsKey(str)) {
            this.abtOriginInstances.put(str, createAbtInstance(str));
        }
        return this.abtOriginInstances.get(str);
    }
}
