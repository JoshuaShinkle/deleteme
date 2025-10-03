package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes2.dex */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);


    /* renamed from: id */
    private final int f15536id;

    DeliveryMechanism(int i9) {
        this.f15536id = i9;
    }

    public static DeliveryMechanism determineFrom(String str) {
        return str != null ? APP_STORE : DEVELOPER;
    }

    public int getId() {
        return this.f15536id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(this.f15536id);
    }
}
