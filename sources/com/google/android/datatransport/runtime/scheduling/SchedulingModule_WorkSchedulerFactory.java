package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {
    private final InterfaceC6266a<Clock> clockProvider;
    private final InterfaceC6266a<SchedulerConfig> configProvider;
    private final InterfaceC6266a<Context> contextProvider;
    private final InterfaceC6266a<EventStore> eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<EventStore> interfaceC6266a2, InterfaceC6266a<SchedulerConfig> interfaceC6266a3, InterfaceC6266a<Clock> interfaceC6266a4) {
        this.contextProvider = interfaceC6266a;
        this.eventStoreProvider = interfaceC6266a2;
        this.configProvider = interfaceC6266a3;
        this.clockProvider = interfaceC6266a4;
    }

    public static SchedulingModule_WorkSchedulerFactory create(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<EventStore> interfaceC6266a2, InterfaceC6266a<SchedulerConfig> interfaceC6266a3, InterfaceC6266a<Clock> interfaceC6266a4) {
        return new SchedulingModule_WorkSchedulerFactory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3, interfaceC6266a4);
    }

    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig schedulerConfig, Clock clock) {
        return (WorkScheduler) Preconditions.checkNotNull(SchedulingModule.workScheduler(context, eventStore, schedulerConfig, clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public WorkScheduler get() {
        return workScheduler(this.contextProvider.get(), this.eventStoreProvider.get(), this.configProvider.get(), this.clockProvider.get());
    }
}
