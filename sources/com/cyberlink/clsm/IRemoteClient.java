package com.cyberlink.clsm;

import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public interface IRemoteClient {
    /* renamed from: b */
    ArrayList<Device> mo5235b(Collection<Long> collection);

    @Keep
    ArrayList<Device> queryDeviceList(String str);

    @Keep
    Session querySessionInfo(String str);

    @Keep
    boolean register(String str, String str2);

    @Keep
    long updateSessionInfo(String str, String str2, String str3);
}
