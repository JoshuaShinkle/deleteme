package com.google.firebase.iid;

/* loaded from: classes2.dex */
final class InstanceIdResultImpl implements InstanceIdResult {

    /* renamed from: id */
    private final String f15572id;
    private final String token;

    public InstanceIdResultImpl(String str, String str2) {
        this.f15572id = str;
        this.token = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.f15572id;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.token;
    }
}
