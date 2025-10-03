package org.jsoup;

import java.io.IOException;

/* loaded from: classes.dex */
public class HttpStatusException extends IOException {
    private int statusCode;
    private String url;

    public HttpStatusException(String str, int i9, String str2) {
        super(str);
        this.statusCode = i9;
        this.url = str2;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + ". Status=" + this.statusCode + ", URL=" + this.url;
    }
}
