package com.linkedin.urls.detection;

/* loaded from: classes2.dex */
public enum UrlDetectorOptions {
    Default(0),
    QUOTE_MATCH(1),
    SINGLE_QUOTE_MATCH(2),
    BRACKET_MATCH(4),
    JSON(5),
    JAVASCRIPT(7),
    XML(9),
    HTML(27),
    ALLOW_SINGLE_LEVEL_DOMAIN(32);

    private int _value;

    UrlDetectorOptions(int i9) {
        this._value = i9;
    }

    /* renamed from: a */
    public boolean m17869a(UrlDetectorOptions urlDetectorOptions) {
        int i9 = this._value;
        int i10 = urlDetectorOptions._value;
        return (i9 & i10) == i10;
    }
}
