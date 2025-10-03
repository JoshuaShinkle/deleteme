package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.Metadata;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;

/* loaded from: classes2.dex */
public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";

    @SuppressLint({"FirebaseUnknownNullness"})
    static TransportFactory transportFactory;
    private final Context context;
    private final FirebaseInstanceId iid;
    private final Task<TopicsSubscriber> topicsSubscriberTask;

    public FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, TransportFactory transportFactory2) {
        transportFactory = transportFactory2;
        this.iid = firebaseInstanceId;
        Context applicationContext = firebaseApp.getApplicationContext();
        this.context = applicationContext;
        Task<TopicsSubscriber> taskCreateInstance = TopicsSubscriber.createInstance(firebaseApp, firebaseInstanceId, new Metadata(applicationContext), userAgentPublisher, heartBeatInfo, firebaseInstallationsApi, applicationContext, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask = taskCreateInstance;
        taskCreateInstance.addOnSuccessListener(FcmExecutors.newTopicsSyncTriggerExecutor(), new OnSuccessListener(this) { // from class: com.google.firebase.messaging.FirebaseMessaging$$Lambda$0
            private final FirebaseMessaging arg$1;

            {
                this.arg$1 = this;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.arg$1.lambda$new$0$FirebaseMessaging((TopicsSubscriber) obj);
            }
        });
    }

    public static synchronized FirebaseMessaging getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static TransportFactory getTransportFactory() {
        return transportFactory;
    }

    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled();
    }

    public boolean isAutoInitEnabled() {
        return this.iid.isFcmAutoInitEnabled();
    }

    public final /* synthetic */ void lambda$new$0$FirebaseMessaging(TopicsSubscriber topicsSubscriber) {
        if (isAutoInitEnabled()) {
            topicsSubscriber.startTopicsSyncIfNecessary();
        }
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        Intent intent2 = new Intent();
        intent2.setPackage("com.google.example.invalidpackage");
        intent.putExtra("app", PendingIntent.getBroadcast(this.context, 0, intent2, 0));
        intent.setPackage("com.google.android.gms");
        remoteMessage.populateSendMessageIntent(intent);
        this.context.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public void setAutoInitEnabled(boolean z8) {
        this.iid.setFcmAutoInitEnabled(z8);
    }

    public void setDeliveryMetricsExportToBigQuery(boolean z8) {
        MessagingAnalytics.setDeliveryMetricsExportToBigQuery(z8);
    }

    public Task<Void> subscribeToTopic(final String str) {
        return this.topicsSubscriberTask.onSuccessTask(new SuccessContinuation(str) { // from class: com.google.firebase.messaging.FirebaseMessaging$$Lambda$1
            private final String arg$1;

            {
                this.arg$1 = str;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return ((TopicsSubscriber) obj).subscribeToTopic(this.arg$1);
            }
        });
    }

    public Task<Void> unsubscribeFromTopic(final String str) {
        return this.topicsSubscriberTask.onSuccessTask(new SuccessContinuation(str) { // from class: com.google.firebase.messaging.FirebaseMessaging$$Lambda$2
            private final String arg$1;

            {
                this.arg$1 = str;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return ((TopicsSubscriber) obj).unsubscribeFromTopic(this.arg$1);
            }
        });
    }

    @Keep
    public static synchronized FirebaseMessaging getInstance(FirebaseApp firebaseApp) {
        return (FirebaseMessaging) firebaseApp.get(FirebaseMessaging.class);
    }
}
