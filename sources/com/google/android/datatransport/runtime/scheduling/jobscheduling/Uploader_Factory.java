package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.concurrent.Executor;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class Uploader_Factory implements Factory<Uploader> {
    private final InterfaceC6266a<BackendRegistry> backendRegistryProvider;
    private final InterfaceC6266a<ClientHealthMetricsStore> clientHealthMetricsStoreProvider;
    private final InterfaceC6266a<Clock> clockProvider;
    private final InterfaceC6266a<Context> contextProvider;
    private final InterfaceC6266a<EventStore> eventStoreProvider;
    private final InterfaceC6266a<Executor> executorProvider;
    private final InterfaceC6266a<SynchronizationGuard> guardProvider;
    private final InterfaceC6266a<Clock> uptimeClockProvider;
    private final InterfaceC6266a<WorkScheduler> workSchedulerProvider;

    public Uploader_Factory(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<BackendRegistry> interfaceC6266a2, InterfaceC6266a<EventStore> interfaceC6266a3, InterfaceC6266a<WorkScheduler> interfaceC6266a4, InterfaceC6266a<Executor> interfaceC6266a5, InterfaceC6266a<SynchronizationGuard> interfaceC6266a6, InterfaceC6266a<Clock> interfaceC6266a7, InterfaceC6266a<Clock> interfaceC6266a8, InterfaceC6266a<ClientHealthMetricsStore> interfaceC6266a9) {
        this.contextProvider = interfaceC6266a;
        this.backendRegistryProvider = interfaceC6266a2;
        this.eventStoreProvider = interfaceC6266a3;
        this.workSchedulerProvider = interfaceC6266a4;
        this.executorProvider = interfaceC6266a5;
        this.guardProvider = interfaceC6266a6;
        this.clockProvider = interfaceC6266a7;
        this.uptimeClockProvider = interfaceC6266a8;
        this.clientHealthMetricsStoreProvider = interfaceC6266a9;
    }

    public static Uploader_Factory create(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<BackendRegistry> interfaceC6266a2, InterfaceC6266a<EventStore> interfaceC6266a3, InterfaceC6266a<WorkScheduler> interfaceC6266a4, InterfaceC6266a<Executor> interfaceC6266a5, InterfaceC6266a<SynchronizationGuard> interfaceC6266a6, InterfaceC6266a<Clock> interfaceC6266a7, InterfaceC6266a<Clock> interfaceC6266a8, InterfaceC6266a<ClientHealthMetricsStore> interfaceC6266a9) {
        return new Uploader_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3, interfaceC6266a4, interfaceC6266a5, interfaceC6266a6, interfaceC6266a7, interfaceC6266a8, interfaceC6266a9);
    }

    public static Uploader newInstance(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, Clock clock, Clock clock2, ClientHealthMetricsStore clientHealthMetricsStore) {
        return new Uploader(context, backendRegistry, eventStore, workScheduler, executor, synchronizationGuard, clock, clock2, clientHealthMetricsStore);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public Uploader get() {
        return newInstance(this.contextProvider.get(), this.backendRegistryProvider.get(), this.eventStoreProvider.get(), this.workSchedulerProvider.get(), this.executorProvider.get(), this.guardProvider.get(), this.clockProvider.get(), this.uptimeClockProvider.get(), this.clientHealthMetricsStoreProvider.get());
    }
}
