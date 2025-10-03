package com.cyberlink.clsm;

import androidx.annotation.Keep;

/* loaded from: classes.dex */
public interface ISessionCacheStore {
    void clear();

    @Keep
    byte[] getChatInfo(String str);

    @Keep
    byte[] getSessionKey(String str);

    @Keep
    boolean removeChatInfo(String str);

    @Keep
    boolean removeSessionKey(String str);

    @Keep
    boolean setChatInfo(String str, byte[] bArr);

    @Keep
    boolean setSessionKey(String str, byte[] bArr);
}
