package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class WorkInitializer_Factory implements Factory<WorkInitializer> {
    private final InterfaceC6266a<Executor> executorProvider;
    private final InterfaceC6266a<SynchronizationGuard> guardProvider;
    private final InterfaceC6266a<WorkScheduler> schedulerProvider;
    private final InterfaceC6266a<EventStore> storeProvider;

    public WorkInitializer_Factory(InterfaceC6266a<Executor> interfaceC6266a, InterfaceC6266a<EventStore> interfaceC6266a2, InterfaceC6266a<WorkScheduler> interfaceC6266a3, InterfaceC6266a<SynchronizationGuard> interfaceC6266a4) {
        this.executorProvider = interfaceC6266a;
        this.storeProvider = interfaceC6266a2;
        this.schedulerProvider = interfaceC6266a3;
        this.guardProvider = interfaceC6266a4;
    }

    public static WorkInitializer_Factory create(InterfaceC6266a<Executor> interfaceC6266a, InterfaceC6266a<EventStore> interfaceC6266a2, InterfaceC6266a<WorkScheduler> interfaceC6266a3, InterfaceC6266a<SynchronizationGuard> interfaceC6266a4) {
        return new WorkInitializer_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3, interfaceC6266a4);
    }

    public static WorkInitializer newInstance(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        return new WorkInitializer(executor, eventStore, workScheduler, synchronizationGuard);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public WorkInitializer get() {
        return newInstance(this.executorProvider.get(), this.storeProvider.get(), this.schedulerProvider.get(), this.guardProvider.get());
    }
}
