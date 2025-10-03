package com.perfectcorp.ycl.network.downloader;

import p107j5.C5102a;

/* loaded from: classes2.dex */
public class DownloadingState {

    /* renamed from: c */
    public static final C5102a f16032c = new C5102a(0, 1);

    /* renamed from: a */
    public final State f16033a;

    /* renamed from: b */
    public final C5102a f16034b;

    public enum State {
        Waiting,
        Running,
        None
    }

    public DownloadingState(State state, C5102a c5102a) {
        this.f16033a = state;
        this.f16034b = c5102a;
    }
}
