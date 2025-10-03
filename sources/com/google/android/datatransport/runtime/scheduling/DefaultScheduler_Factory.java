package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class DefaultScheduler_Factory implements Factory<DefaultScheduler> {
    private final InterfaceC6266a<BackendRegistry> backendRegistryProvider;
    private final InterfaceC6266a<EventStore> eventStoreProvider;
    private final InterfaceC6266a<Executor> executorProvider;
    private final InterfaceC6266a<SynchronizationGuard> guardProvider;
    private final InterfaceC6266a<WorkScheduler> workSchedulerProvider;

    public DefaultScheduler_Factory(InterfaceC6266a<Executor> interfaceC6266a, InterfaceC6266a<BackendRegistry> interfaceC6266a2, InterfaceC6266a<WorkScheduler> interfaceC6266a3, InterfaceC6266a<EventStore> interfaceC6266a4, InterfaceC6266a<SynchronizationGuard> interfaceC6266a5) {
        this.executorProvider = interfaceC6266a;
        this.backendRegistryProvider = interfaceC6266a2;
        this.workSchedulerProvider = interfaceC6266a3;
        this.eventStoreProvider = interfaceC6266a4;
        this.guardProvider = interfaceC6266a5;
    }

    public static DefaultScheduler_Factory create(InterfaceC6266a<Executor> interfaceC6266a, InterfaceC6266a<BackendRegistry> interfaceC6266a2, InterfaceC6266a<WorkScheduler> interfaceC6266a3, InterfaceC6266a<EventStore> interfaceC6266a4, InterfaceC6266a<SynchronizationGuard> interfaceC6266a5) {
        return new DefaultScheduler_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3, interfaceC6266a4, interfaceC6266a5);
    }

    public static DefaultScheduler newInstance(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        return new DefaultScheduler(executor, backendRegistry, workScheduler, eventStore, synchronizationGuard);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public DefaultScheduler get() {
        return newInstance(this.executorProvider.get(), this.backendRegistryProvider.get(), this.workSchedulerProvider.get(), this.eventStoreProvider.get(), this.guardProvider.get());
    }
}
