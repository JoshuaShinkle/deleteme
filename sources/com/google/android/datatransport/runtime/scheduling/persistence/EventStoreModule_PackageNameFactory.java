package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class EventStoreModule_PackageNameFactory implements Factory<String> {
    private final InterfaceC6266a<Context> contextProvider;

    public EventStoreModule_PackageNameFactory(InterfaceC6266a<Context> interfaceC6266a) {
        this.contextProvider = interfaceC6266a;
    }

    public static EventStoreModule_PackageNameFactory create(InterfaceC6266a<Context> interfaceC6266a) {
        return new EventStoreModule_PackageNameFactory(interfaceC6266a);
    }

    public static String packageName(Context context) {
        return (String) Preconditions.checkNotNull(EventStoreModule.packageName(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public String get() {
        return packageName(this.contextProvider.get());
    }
}
