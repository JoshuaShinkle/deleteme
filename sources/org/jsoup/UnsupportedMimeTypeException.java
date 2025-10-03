package org.jsoup;

import java.io.IOException;

/* loaded from: classes.dex */
public class UnsupportedMimeTypeException extends IOException {
    private String mimeType;
    private String url;

    public UnsupportedMimeTypeException(String str, String str2, String str3) {
        super(str);
        this.mimeType = str2;
        this.url = str3;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + ". Mimetype=" + this.mimeType + ", URL=" + this.url;
    }
}
