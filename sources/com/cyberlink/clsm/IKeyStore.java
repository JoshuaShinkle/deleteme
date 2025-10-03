package com.cyberlink.clsm;

import androidx.annotation.Keep;

/* loaded from: classes.dex */
public interface IKeyStore {
    void clear();

    @Keep
    byte[] getKey(String str);

    @Keep
    boolean setKey(String str, byte[] bArr);
}
