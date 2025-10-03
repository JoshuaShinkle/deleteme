package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {
    private final InterfaceC6266a<Context> applicationContextProvider;
    private final InterfaceC6266a<CreationContextFactory> creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<CreationContextFactory> interfaceC6266a2) {
        this.applicationContextProvider = interfaceC6266a;
        this.creationContextFactoryProvider = interfaceC6266a2;
    }

    public static MetadataBackendRegistry_Factory create(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<CreationContextFactory> interfaceC6266a2) {
        return new MetadataBackendRegistry_Factory(interfaceC6266a, interfaceC6266a2);
    }

    public static MetadataBackendRegistry newInstance(Context context, Object obj) {
        return new MetadataBackendRegistry(context, (CreationContextFactory) obj);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public MetadataBackendRegistry get() {
        return newInstance(this.applicationContextProvider.get(), this.creationContextFactoryProvider.get());
    }
}
