package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class SchedulingConfigModule_ConfigFactory implements Factory<SchedulerConfig> {
    private final InterfaceC6266a<Clock> clockProvider;

    public SchedulingConfigModule_ConfigFactory(InterfaceC6266a<Clock> interfaceC6266a) {
        this.clockProvider = interfaceC6266a;
    }

    public static SchedulerConfig config(Clock clock) {
        return (SchedulerConfig) Preconditions.checkNotNull(SchedulingConfigModule.config(clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SchedulingConfigModule_ConfigFactory create(InterfaceC6266a<Clock> interfaceC6266a) {
        return new SchedulingConfigModule_ConfigFactory(interfaceC6266a);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public SchedulerConfig get() {
        return config(this.clockProvider.get());
    }
}
