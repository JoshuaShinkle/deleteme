package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes2.dex */
public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int i9) {
        if (i9 < 200 || i9 > 299) {
            return ((i9 < 300 || i9 > 399) && i9 >= 400 && i9 <= 499) ? 0 : 1;
        }
        return 0;
    }
}
