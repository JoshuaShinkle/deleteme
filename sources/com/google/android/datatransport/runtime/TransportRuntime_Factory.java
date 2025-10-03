package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class TransportRuntime_Factory implements Factory<TransportRuntime> {
    private final InterfaceC6266a<Clock> eventClockProvider;
    private final InterfaceC6266a<WorkInitializer> initializerProvider;
    private final InterfaceC6266a<Scheduler> schedulerProvider;
    private final InterfaceC6266a<Uploader> uploaderProvider;
    private final InterfaceC6266a<Clock> uptimeClockProvider;

    public TransportRuntime_Factory(InterfaceC6266a<Clock> interfaceC6266a, InterfaceC6266a<Clock> interfaceC6266a2, InterfaceC6266a<Scheduler> interfaceC6266a3, InterfaceC6266a<Uploader> interfaceC6266a4, InterfaceC6266a<WorkInitializer> interfaceC6266a5) {
        this.eventClockProvider = interfaceC6266a;
        this.uptimeClockProvider = interfaceC6266a2;
        this.schedulerProvider = interfaceC6266a3;
        this.uploaderProvider = interfaceC6266a4;
        this.initializerProvider = interfaceC6266a5;
    }

    public static TransportRuntime_Factory create(InterfaceC6266a<Clock> interfaceC6266a, InterfaceC6266a<Clock> interfaceC6266a2, InterfaceC6266a<Scheduler> interfaceC6266a3, InterfaceC6266a<Uploader> interfaceC6266a4, InterfaceC6266a<WorkInitializer> interfaceC6266a5) {
        return new TransportRuntime_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3, interfaceC6266a4, interfaceC6266a5);
    }

    public static TransportRuntime newInstance(Clock clock, Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        return new TransportRuntime(clock, clock2, scheduler, uploader, workInitializer);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public TransportRuntime get() {
        return newInstance(this.eventClockProvider.get(), this.uptimeClockProvider.get(), this.schedulerProvider.get(), this.uploaderProvider.get(), this.initializerProvider.get());
    }
}
