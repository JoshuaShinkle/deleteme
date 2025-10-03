package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;

/* loaded from: classes2.dex */
final /* synthetic */ class FirebaseMessagingService$$Lambda$0 implements Transformer {
    static final Transformer $instance = new FirebaseMessagingService$$Lambda$0();

    private FirebaseMessagingService$$Lambda$0() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object obj) {
        return ((String) obj).getBytes();
    }
}
