package com.linkedin.urls;

/* loaded from: classes2.dex */
public enum UrlPart {
    FRAGMENT(null),
    QUERY(FRAGMENT),
    PATH(QUERY),
    PORT(PATH),
    HOST(PORT),
    USERNAME_PASSWORD(HOST),
    SCHEME(USERNAME_PASSWORD);

    private UrlPart _nextPart;

    UrlPart(UrlPart urlPart) {
        this._nextPart = urlPart;
    }

    /* renamed from: a */
    public UrlPart m17846a() {
        return this._nextPart;
    }
}
