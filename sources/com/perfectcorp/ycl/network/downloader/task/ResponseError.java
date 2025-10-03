package com.perfectcorp.ycl.network.downloader.task;

import com.perfectcorp.ycl.network.NetworkManager;

/* loaded from: classes2.dex */
public class ResponseError extends RuntimeException {
    private final NetworkManager.ResponseStatus mStatus;

    public ResponseError(NetworkManager.ResponseStatus responseStatus, Exception exc) {
        super("status: " + responseStatus, exc);
        this.mStatus = responseStatus;
    }
}
