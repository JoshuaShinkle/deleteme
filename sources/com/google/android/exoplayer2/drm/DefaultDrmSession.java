package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
/* loaded from: classes.dex */
class DefaultDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private static final int MAX_LICENSE_DURATION_TO_RENEW = 60;
    private static final int MSG_KEYS = 1;
    private static final int MSG_PROVISION = 0;
    private static final String TAG = "DefaultDrmSession";
    final MediaDrmCallback callback;
    private final Handler eventHandler;
    private final DefaultDrmSessionManager.EventListener eventListener;
    private final byte[] initData;
    private final int initialDrmRequestRetryCount;
    private DrmSession.DrmSessionException lastException;
    private T mediaCrypto;
    private final ExoMediaDrm<T> mediaDrm;
    private final String mimeType;
    private final int mode;
    private byte[] offlineLicenseKeySetId;
    private int openCount;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private DefaultDrmSession<T>.PostRequestHandler postRequestHandler;
    final DefaultDrmSession<T>.PostResponseHandler postResponseHandler;
    private final ProvisioningManager<T> provisioningManager;
    private HandlerThread requestHandlerThread;
    private byte[] sessionId;
    private int state = 2;
    final UUID uuid;

    @SuppressLint({"HandlerLeak"})
    public class PostRequestHandler extends Handler {
        public PostRequestHandler(Looper looper) {
            super(looper);
        }

        private long getRetryDelayMillis(int i9) {
            return Math.min((i9 - 1) * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, 5000);
        }

        private boolean maybeRetryRequest(Message message) {
            int i9;
            if (!(message.arg1 == 1) || (i9 = message.arg2 + 1) > DefaultDrmSession.this.initialDrmRequestRetryCount) {
                return false;
            }
            Message messageObtain = Message.obtain(message);
            messageObtain.arg2 = i9;
            sendMessageDelayed(messageObtain, getRetryDelayMillis(i9));
            return true;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                int i9 = message.what;
                if (i9 == 0) {
                    DefaultDrmSession defaultDrmSession = DefaultDrmSession.this;
                    e = defaultDrmSession.callback.executeProvisionRequest(defaultDrmSession.uuid, (ExoMediaDrm.ProvisionRequest) message.obj);
                } else {
                    if (i9 != 1) {
                        throw new RuntimeException();
                    }
                    DefaultDrmSession defaultDrmSession2 = DefaultDrmSession.this;
                    e = defaultDrmSession2.callback.executeKeyRequest(defaultDrmSession2.uuid, (ExoMediaDrm.KeyRequest) message.obj);
                }
            } catch (Exception e9) {
                e = e9;
                if (maybeRetryRequest(message)) {
                    return;
                }
            }
            DefaultDrmSession.this.postResponseHandler.obtainMessage(message.what, e).sendToTarget();
        }

        public Message obtainMessage(int i9, Object obj, boolean z8) {
            return obtainMessage(i9, z8 ? 1 : 0, 0, obj);
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class PostResponseHandler extends Handler {
        public PostResponseHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == 0) {
                DefaultDrmSession.this.onProvisionResponse(message.obj);
            } else {
                if (i9 != 1) {
                    return;
                }
                DefaultDrmSession.this.onKeyResponse(message.obj);
            }
        }
    }

    public interface ProvisioningManager<T extends ExoMediaCrypto> {
        void onProvisionCompleted();

        void onProvisionError(Exception exc);

        void provisionRequired(DefaultDrmSession<T> defaultDrmSession);
    }

    public DefaultDrmSession(UUID uuid, ExoMediaDrm<T> exoMediaDrm, ProvisioningManager<T> provisioningManager, byte[] bArr, String str, int i9, byte[] bArr2, HashMap<String, String> map, MediaDrmCallback mediaDrmCallback, Looper looper, Handler handler, DefaultDrmSessionManager.EventListener eventListener, int i10) {
        this.uuid = uuid;
        this.provisioningManager = provisioningManager;
        this.mediaDrm = exoMediaDrm;
        this.mode = i9;
        this.offlineLicenseKeySetId = bArr2;
        this.optionalKeyRequestParameters = map;
        this.callback = mediaDrmCallback;
        this.initialDrmRequestRetryCount = i10;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.postResponseHandler = new PostResponseHandler(looper);
        HandlerThread handlerThread = new HandlerThread("DrmRequestHandler");
        this.requestHandlerThread = handlerThread;
        handlerThread.start();
        this.postRequestHandler = new PostRequestHandler(this.requestHandlerThread.getLooper());
        if (bArr2 == null) {
            this.initData = bArr;
            this.mimeType = str;
        } else {
            this.initData = null;
            this.mimeType = null;
        }
    }

    private void doLicense(boolean z8) {
        int i9 = this.mode;
        if (i9 != 0 && i9 != 1) {
            if (i9 != 2) {
                if (i9 == 3 && restoreKeys()) {
                    postKeyRequest(3, z8);
                    return;
                }
                return;
            }
            if (this.offlineLicenseKeySetId == null) {
                postKeyRequest(2, z8);
                return;
            } else {
                if (restoreKeys()) {
                    postKeyRequest(2, z8);
                    return;
                }
                return;
            }
        }
        if (this.offlineLicenseKeySetId == null) {
            postKeyRequest(1, z8);
            return;
        }
        if (this.state == 4 || restoreKeys()) {
            long licenseDurationRemainingSec = getLicenseDurationRemainingSec();
            if (this.mode == 0 && licenseDurationRemainingSec <= 60) {
                Log.d(TAG, "Offline license has expired or will expire soon. Remaining seconds: " + licenseDurationRemainingSec);
                postKeyRequest(2, z8);
                return;
            }
            if (licenseDurationRemainingSec <= 0) {
                onError(new KeysExpiredException());
                return;
            }
            this.state = 4;
            Handler handler = this.eventHandler;
            if (handler == null || this.eventListener == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSession.1
                @Override // java.lang.Runnable
                public void run() {
                    DefaultDrmSession.this.eventListener.onDrmKeysRestored();
                }
            });
        }
    }

    private long getLicenseDurationRemainingSec() {
        if (!C3322C.WIDEVINE_UUID.equals(this.uuid)) {
            return Long.MAX_VALUE;
        }
        Pair<Long, Long> licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(this);
        return Math.min(((Long) licenseDurationRemainingSec.first).longValue(), ((Long) licenseDurationRemainingSec.second).longValue());
    }

    private boolean isOpen() {
        int i9 = this.state;
        return i9 == 3 || i9 == 4;
    }

    private void onError(final Exception exc) {
        this.lastException = new DrmSession.DrmSessionException(exc);
        Handler handler = this.eventHandler;
        if (handler != null && this.eventListener != null) {
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSession.4
                @Override // java.lang.Runnable
                public void run() {
                    DefaultDrmSession.this.eventListener.onDrmSessionManagerError(exc);
                }
            });
        }
        if (this.state != 4) {
            this.state = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onKeyResponse(Object obj) {
        if (isOpen()) {
            if (obj instanceof Exception) {
                onKeysError((Exception) obj);
                return;
            }
            try {
                byte[] bArrAdjustResponseData = (byte[]) obj;
                if (C3322C.CLEARKEY_UUID.equals(this.uuid)) {
                    bArrAdjustResponseData = ClearKeyUtil.adjustResponseData(bArrAdjustResponseData);
                }
                if (this.mode == 3) {
                    this.mediaDrm.provideKeyResponse(this.offlineLicenseKeySetId, bArrAdjustResponseData);
                    Handler handler = this.eventHandler;
                    if (handler == null || this.eventListener == null) {
                        return;
                    }
                    handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSession.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DefaultDrmSession.this.eventListener.onDrmKeysRemoved();
                        }
                    });
                    return;
                }
                byte[] bArrProvideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, bArrAdjustResponseData);
                int i9 = this.mode;
                if ((i9 == 2 || (i9 == 0 && this.offlineLicenseKeySetId != null)) && bArrProvideKeyResponse != null && bArrProvideKeyResponse.length != 0) {
                    this.offlineLicenseKeySetId = bArrProvideKeyResponse;
                }
                this.state = 4;
                Handler handler2 = this.eventHandler;
                if (handler2 == null || this.eventListener == null) {
                    return;
                }
                handler2.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSession.3
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultDrmSession.this.eventListener.onDrmKeysLoaded();
                    }
                });
            } catch (Exception e9) {
                onKeysError(e9);
            }
        }
    }

    private void onKeysError(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            this.provisioningManager.provisionRequired(this);
        } else {
            onError(exc);
        }
    }

    private void onKeysExpired() {
        if (this.state == 4) {
            this.state = 3;
            onError(new KeysExpiredException());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProvisionResponse(Object obj) {
        if (this.state == 2 || isOpen()) {
            if (obj instanceof Exception) {
                this.provisioningManager.onProvisionError((Exception) obj);
                return;
            }
            try {
                this.mediaDrm.provideProvisionResponse((byte[]) obj);
                this.provisioningManager.onProvisionCompleted();
            } catch (Exception e9) {
                this.provisioningManager.onProvisionError(e9);
            }
        }
    }

    private boolean openInternal(boolean z8) {
        if (isOpen()) {
            return true;
        }
        try {
            byte[] bArrOpenSession = this.mediaDrm.openSession();
            this.sessionId = bArrOpenSession;
            this.mediaCrypto = (T) this.mediaDrm.createMediaCrypto(bArrOpenSession);
            this.state = 3;
            return true;
        } catch (NotProvisionedException e9) {
            if (z8) {
                this.provisioningManager.provisionRequired(this);
                return false;
            }
            onError(e9);
            return false;
        } catch (Exception e10) {
            onError(e10);
            return false;
        }
    }

    private void postKeyRequest(int i9, boolean z8) {
        try {
            ExoMediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(i9 == 3 ? this.offlineLicenseKeySetId : this.sessionId, this.initData, this.mimeType, i9, this.optionalKeyRequestParameters);
            if (C3322C.CLEARKEY_UUID.equals(this.uuid)) {
                keyRequest = new ExoMediaDrm.DefaultKeyRequest(ClearKeyUtil.adjustRequestData(keyRequest.getData()), keyRequest.getDefaultUrl());
            }
            this.postRequestHandler.obtainMessage(1, keyRequest, z8).sendToTarget();
        } catch (Exception e9) {
            onKeysError(e9);
        }
    }

    private boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return true;
        } catch (Exception e9) {
            Log.e(TAG, "Error trying to restore Widevine keys.", e9);
            onError(e9);
            return false;
        }
    }

    public void acquire() {
        int i9 = this.openCount + 1;
        this.openCount = i9;
        if (i9 == 1 && this.state != 1 && openInternal(true)) {
            doLicense(true);
        }
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final DrmSession.DrmSessionException getError() {
        if (this.state == 1) {
            return this.lastException;
        }
        return null;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final T getMediaCrypto() {
        return this.mediaCrypto;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public byte[] getOfflineLicenseKeySetId() {
        return this.offlineLicenseKeySetId;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final int getState() {
        return this.state;
    }

    public boolean hasInitData(byte[] bArr) {
        return Arrays.equals(this.initData, bArr);
    }

    public boolean hasSessionId(byte[] bArr) {
        return Arrays.equals(this.sessionId, bArr);
    }

    public void onMediaDrmEvent(int i9) {
        if (isOpen()) {
            if (i9 == 1) {
                this.state = 3;
                this.provisioningManager.provisionRequired(this);
            } else if (i9 == 2) {
                doLicense(false);
            } else {
                if (i9 != 3) {
                    return;
                }
                onKeysExpired();
            }
        }
    }

    public void onProvisionCompleted() {
        if (openInternal(false)) {
            doLicense(true);
        }
    }

    public void onProvisionError(Exception exc) {
        onError(exc);
    }

    public void provision() {
        this.postRequestHandler.obtainMessage(0, (Object) this.mediaDrm.getProvisionRequest(), true).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public Map<String, String> queryKeyStatus() {
        byte[] bArr = this.sessionId;
        if (bArr == null) {
            return null;
        }
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    public boolean release() {
        int i9 = this.openCount - 1;
        this.openCount = i9;
        if (i9 != 0) {
            return false;
        }
        this.state = 0;
        this.postResponseHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler = null;
        this.requestHandlerThread.quit();
        this.requestHandlerThread = null;
        this.mediaCrypto = null;
        this.lastException = null;
        byte[] bArr = this.sessionId;
        if (bArr != null) {
            this.mediaDrm.closeSession(bArr);
            this.sessionId = null;
        }
        return true;
    }
}
