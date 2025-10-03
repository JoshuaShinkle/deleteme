package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@TargetApi(23)
/* loaded from: classes.dex */
public final class FrameworkMediaDrm implements ExoMediaDrm<FrameworkMediaCrypto> {
    private final MediaDrm mediaDrm;
    private final UUID uuid;

    private FrameworkMediaDrm(UUID uuid) {
        Assertions.checkNotNull(uuid);
        UUID uuid2 = C3322C.COMMON_PSSH_UUID;
        Assertions.checkArgument(!uuid2.equals(uuid), "Use C.CLEARKEY_UUID instead");
        if (Util.SDK_INT < 27 && C3322C.CLEARKEY_UUID.equals(uuid)) {
            uuid = uuid2;
        }
        this.uuid = uuid;
        this.mediaDrm = new MediaDrm(uuid);
    }

    public static FrameworkMediaDrm newInstance(UUID uuid) throws UnsupportedDrmException {
        try {
            return new FrameworkMediaDrm(uuid);
        } catch (UnsupportedSchemeException e9) {
            throw new UnsupportedDrmException(1, e9);
        } catch (Exception e10) {
            throw new UnsupportedDrmException(2, e10);
        }
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void closeSession(byte[] bArr) {
        this.mediaDrm.closeSession(bArr);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, byte[] bArr2, String str, int i9, HashMap<String, String> map) throws NotProvisionedException {
        MediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(bArr, bArr2, str, i9, map);
        return new ExoMediaDrm.DefaultKeyRequest(keyRequest.getData(), keyRequest.getDefaultUrl());
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public byte[] getPropertyByteArray(String str) {
        return this.mediaDrm.getPropertyByteArray(str);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        MediaDrm.ProvisionRequest provisionRequest = this.mediaDrm.getProvisionRequest();
        return new ExoMediaDrm.DefaultProvisionRequest(provisionRequest.getData(), provisionRequest.getDefaultUrl());
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public byte[] openSession() {
        return this.mediaDrm.openSession();
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) {
        return this.mediaDrm.provideKeyResponse(bArr, bArr2);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void provideProvisionResponse(byte[] bArr) throws DeniedByServerException {
        this.mediaDrm.provideProvisionResponse(bArr);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public Map<String, String> queryKeyStatus(byte[] bArr) {
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void release() {
        this.mediaDrm.release();
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        this.mediaDrm.restoreKeys(bArr, bArr2);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void setOnEventListener(final ExoMediaDrm.OnEventListener<? super FrameworkMediaCrypto> onEventListener) {
        this.mediaDrm.setOnEventListener(onEventListener == null ? null : new MediaDrm.OnEventListener() { // from class: com.google.android.exoplayer2.drm.FrameworkMediaDrm.1
            @Override // android.media.MediaDrm.OnEventListener
            public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i9, int i10, byte[] bArr2) {
                onEventListener.onEvent(FrameworkMediaDrm.this, bArr, i9, i10, bArr2);
            }
        });
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void setOnKeyStatusChangeListener(final ExoMediaDrm.OnKeyStatusChangeListener<? super FrameworkMediaCrypto> onKeyStatusChangeListener) {
        if (Util.SDK_INT < 23) {
            throw new UnsupportedOperationException();
        }
        this.mediaDrm.setOnKeyStatusChangeListener(onKeyStatusChangeListener == null ? null : new MediaDrm.OnKeyStatusChangeListener() { // from class: com.google.android.exoplayer2.drm.FrameworkMediaDrm.2
            @Override // android.media.MediaDrm.OnKeyStatusChangeListener
            public void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List<MediaDrm.KeyStatus> list, boolean z8) {
                ArrayList arrayList = new ArrayList();
                for (MediaDrm.KeyStatus keyStatus : list) {
                    arrayList.add(new ExoMediaDrm.DefaultKeyStatus(keyStatus.getStatusCode(), keyStatus.getKeyId()));
                }
                onKeyStatusChangeListener.onKeyStatusChange(FrameworkMediaDrm.this, bArr, arrayList, z8);
            }
        }, (Handler) null);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void setPropertyByteArray(String str, byte[] bArr) {
        this.mediaDrm.setPropertyByteArray(str, bArr);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public void setPropertyString(String str, String str2) {
        this.mediaDrm.setPropertyString(str, str2);
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm
    public FrameworkMediaCrypto createMediaCrypto(byte[] bArr) {
        return new FrameworkMediaCrypto(new MediaCrypto(this.uuid, bArr), Util.SDK_INT < 21 && C3322C.WIDEVINE_UUID.equals(this.uuid) && "L3".equals(getPropertyString("securityLevel")));
    }
}
