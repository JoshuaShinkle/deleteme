package com.google.firebase.abt.component;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* loaded from: classes2.dex */
final /* synthetic */ class AbtRegistrar$$Lambda$1 implements ComponentFactory {
    private static final AbtRegistrar$$Lambda$1 instance = new AbtRegistrar$$Lambda$1();

    private AbtRegistrar$$Lambda$1() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public Object create(ComponentContainer componentContainer) {
        return AbtRegistrar.lambda$getComponents$0(componentContainer);
    }
}
