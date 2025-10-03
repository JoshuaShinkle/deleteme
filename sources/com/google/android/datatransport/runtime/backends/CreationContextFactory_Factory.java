package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class CreationContextFactory_Factory implements Factory<CreationContextFactory> {
    private final InterfaceC6266a<Context> applicationContextProvider;
    private final InterfaceC6266a<Clock> monotonicClockProvider;
    private final InterfaceC6266a<Clock> wallClockProvider;

    public CreationContextFactory_Factory(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<Clock> interfaceC6266a2, InterfaceC6266a<Clock> interfaceC6266a3) {
        this.applicationContextProvider = interfaceC6266a;
        this.wallClockProvider = interfaceC6266a2;
        this.monotonicClockProvider = interfaceC6266a3;
    }

    public static CreationContextFactory_Factory create(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<Clock> interfaceC6266a2, InterfaceC6266a<Clock> interfaceC6266a3) {
        return new CreationContextFactory_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3);
    }

    public static CreationContextFactory newInstance(Context context, Clock clock, Clock clock2) {
        return new CreationContextFactory(context, clock, clock2);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public CreationContextFactory get() {
        return newInstance(this.applicationContextProvider.get(), this.wallClockProvider.get(), this.monotonicClockProvider.get());
    }
}
