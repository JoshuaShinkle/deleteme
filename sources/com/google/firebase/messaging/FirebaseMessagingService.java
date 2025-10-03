package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.MessengerIpcClient;
import com.google.firebase.iid.ServiceStarter;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes2.dex */
public class FirebaseMessagingService extends EnhancedIntentService {
    public static final String ACTION_DIRECT_BOOT_REMOTE_INTENT = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
    private static final Queue<String> recentlyReceivedMessageIds = new ArrayDeque(10);

    private Task<Void> ackMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return Tasks.forResult(null);
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constants.MessagePayloadKeys.MSGID, str);
        return MessengerIpcClient.getInstance(this).sendOneWayRequest(2, bundle);
    }

    private boolean alreadyReceivedMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Queue<String> queue = recentlyReceivedMessageIds;
        if (!queue.contains(str)) {
            if (queue.size() >= 10) {
                queue.remove();
            }
            queue.add(str);
            return false;
        }
        if (!Log.isLoggable(Constants.TAG, 3)) {
            return true;
        }
        String strValueOf = String.valueOf(str);
        Log.d(Constants.TAG, strValueOf.length() != 0 ? "Received duplicate message: ".concat(strValueOf) : new String("Received duplicate message: "));
        return true;
    }

    private void dispatchMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.remove("androidx.content.wakelockid");
        if (NotificationParams.isNotification(extras)) {
            NotificationParams notificationParams = new NotificationParams(extras);
            ExecutorService executorServiceNewNetworkIOExecutor = FcmExecutors.newNetworkIOExecutor();
            try {
                if (new DisplayNotification(this, notificationParams, executorServiceNewNetworkIOExecutor).handleNotification()) {
                    return;
                }
                executorServiceNewNetworkIOExecutor.shutdown();
                if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                    MessagingAnalytics.logNotificationForeground(intent);
                }
            } finally {
                executorServiceNewNetworkIOExecutor.shutdown();
            }
        }
        onMessageReceived(new RemoteMessage(extras));
    }

    private String getMessageId(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        return stringExtra == null ? intent.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER) : stringExtra;
    }

    private void handleMessageIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        Task<Void> taskAckMessage = ackMessage(stringExtra);
        if (!alreadyReceivedMessage(stringExtra)) {
            passMessageIntentToSdk(intent);
        }
        try {
            Tasks.await(taskAckMessage, getAckTimeoutMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e9) {
            String strValueOf = String.valueOf(e9);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 20);
            sb.append("Message ack failed: ");
            sb.append(strValueOf);
            Log.w(Constants.TAG, sb.toString());
        }
    }

    private void handleNotificationOpen(Intent intent) throws PendingIntent.CanceledException {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(Constants.IntentKeys.PENDING_INTENT);
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e(Constants.TAG, "Notification pending intent canceled");
            }
        }
        if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
            MessagingAnalytics.logNotificationOpen(intent);
        }
    }

    private void passMessageIntentToSdk(Intent intent) {
        String stringExtra;
        stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MESSAGE_TYPE);
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        switch (stringExtra) {
            case "deleted_messages":
                onDeletedMessages();
                break;
            case "gcm":
                if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                    MessagingAnalytics.logNotificationReceived(intent, null);
                }
                if (MessagingAnalytics.shouldUploadFirelogAnalytics(intent)) {
                    TransportFactory transportFactory = FirebaseMessaging.getTransportFactory();
                    if (transportFactory != null) {
                        MessagingAnalytics.logNotificationReceived(intent, transportFactory.getTransport(Constants.FirelogAnalytics.FCM_LOG_SOURCE, String.class, Encoding.m17449of("json"), FirebaseMessagingService$$Lambda$0.$instance));
                    } else {
                        Log.e(Constants.TAG, "TransportFactory is null. Skip exporting message delivery metrics to Big Query");
                    }
                }
                dispatchMessage(intent);
                break;
            case "send_error":
                onSendError(getMessageId(intent), new SendException(intent.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR)));
                break;
            case "send_event":
                onMessageSent(intent.getStringExtra(Constants.MessagePayloadKeys.MSGID));
                break;
            default:
                Log.w(Constants.TAG, stringExtra.length() != 0 ? "Received message with unknown type: ".concat(stringExtra) : new String("Received message with unknown type: "));
                break;
        }
    }

    public long getAckTimeoutMillis() {
        return TimeUnit.SECONDS.toMillis(1L);
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public Intent getStartCommandIntent(Intent intent) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public void handleIntent(Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action) || ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(action)) {
            handleMessageIntent(intent);
            return;
        }
        if (Constants.IntentActionKeys.NOTIFICATION_DISMISS.equals(action)) {
            if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                MessagingAnalytics.logNotificationDismiss(intent);
            }
        } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
            onNewToken(intent.getStringExtra("token"));
        } else {
            String strValueOf = String.valueOf(intent.getAction());
            Log.d(Constants.TAG, strValueOf.length() != 0 ? "Unknown intent action: ".concat(strValueOf) : new String("Unknown intent action: "));
        }
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public boolean handleIntentOnMainThread(Intent intent) throws PendingIntent.CanceledException {
        if (!Constants.IntentActionKeys.NOTIFICATION_OPEN.equals(intent.getAction())) {
            return false;
        }
        handleNotificationOpen(intent);
        return true;
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onSendError(String str, Exception exc) {
    }
}
