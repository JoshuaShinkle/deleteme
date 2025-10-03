package com.google.firebase.emulators;

/* loaded from: classes2.dex */
public final class EmulatedServiceSettings {
    private final String host;
    private final int port;

    public EmulatedServiceSettings(String str, int i9) {
        this.host = str;
        this.port = i9;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }
}
