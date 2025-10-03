package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.TransportRuntimeComponent;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_PackageNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import java.util.concurrent.Executor;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
final class DaggerTransportRuntimeComponent extends TransportRuntimeComponent {
    private InterfaceC6266a<SchedulerConfig> configProvider;
    private InterfaceC6266a creationContextFactoryProvider;
    private InterfaceC6266a<DefaultScheduler> defaultSchedulerProvider;
    private InterfaceC6266a<Executor> executorProvider;
    private InterfaceC6266a metadataBackendRegistryProvider;
    private InterfaceC6266a<String> packageNameProvider;
    private InterfaceC6266a<SQLiteEventStore> sQLiteEventStoreProvider;
    private InterfaceC6266a schemaManagerProvider;
    private InterfaceC6266a<Context> setApplicationContextProvider;
    private InterfaceC6266a<TransportRuntime> transportRuntimeProvider;
    private InterfaceC6266a<Uploader> uploaderProvider;
    private InterfaceC6266a<WorkInitializer> workInitializerProvider;
    private InterfaceC6266a<WorkScheduler> workSchedulerProvider;

    public static final class Builder implements TransportRuntimeComponent.Builder {
        private Context setApplicationContext;

        private Builder() {
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public TransportRuntimeComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            return new DaggerTransportRuntimeComponent(this.setApplicationContext);
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public Builder setApplicationContext(Context context) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }
    }

    public static TransportRuntimeComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(Context context) {
        this.executorProvider = DoubleCheck.provider(ExecutionModule_ExecutorFactory.create());
        Factory factoryCreate = InstanceFactory.create(context);
        this.setApplicationContextProvider = factoryCreate;
        CreationContextFactory_Factory creationContextFactory_FactoryCreate = CreationContextFactory_Factory.create(factoryCreate, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create());
        this.creationContextFactoryProvider = creationContextFactory_FactoryCreate;
        this.metadataBackendRegistryProvider = DoubleCheck.provider(MetadataBackendRegistry_Factory.create(this.setApplicationContextProvider, creationContextFactory_FactoryCreate));
        this.schemaManagerProvider = SchemaManager_Factory.create(this.setApplicationContextProvider, EventStoreModule_DbNameFactory.create(), EventStoreModule_SchemaVersionFactory.create());
        this.packageNameProvider = DoubleCheck.provider(EventStoreModule_PackageNameFactory.create(this.setApplicationContextProvider));
        this.sQLiteEventStoreProvider = DoubleCheck.provider(SQLiteEventStore_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), EventStoreModule_StoreConfigFactory.create(), this.schemaManagerProvider, this.packageNameProvider));
        SchedulingConfigModule_ConfigFactory schedulingConfigModule_ConfigFactoryCreate = SchedulingConfigModule_ConfigFactory.create(TimeModule_EventClockFactory.create());
        this.configProvider = schedulingConfigModule_ConfigFactoryCreate;
        SchedulingModule_WorkSchedulerFactory schedulingModule_WorkSchedulerFactoryCreate = SchedulingModule_WorkSchedulerFactory.create(this.setApplicationContextProvider, this.sQLiteEventStoreProvider, schedulingConfigModule_ConfigFactoryCreate, TimeModule_UptimeClockFactory.create());
        this.workSchedulerProvider = schedulingModule_WorkSchedulerFactoryCreate;
        InterfaceC6266a<Executor> interfaceC6266a = this.executorProvider;
        InterfaceC6266a interfaceC6266a2 = this.metadataBackendRegistryProvider;
        InterfaceC6266a<SQLiteEventStore> interfaceC6266a3 = this.sQLiteEventStoreProvider;
        this.defaultSchedulerProvider = DefaultScheduler_Factory.create(interfaceC6266a, interfaceC6266a2, schedulingModule_WorkSchedulerFactoryCreate, interfaceC6266a3, interfaceC6266a3);
        InterfaceC6266a<Context> interfaceC6266a4 = this.setApplicationContextProvider;
        InterfaceC6266a interfaceC6266a5 = this.metadataBackendRegistryProvider;
        InterfaceC6266a<SQLiteEventStore> interfaceC6266a6 = this.sQLiteEventStoreProvider;
        this.uploaderProvider = Uploader_Factory.create(interfaceC6266a4, interfaceC6266a5, interfaceC6266a6, this.workSchedulerProvider, this.executorProvider, interfaceC6266a6, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.sQLiteEventStoreProvider);
        InterfaceC6266a<Executor> interfaceC6266a7 = this.executorProvider;
        InterfaceC6266a<SQLiteEventStore> interfaceC6266a8 = this.sQLiteEventStoreProvider;
        this.workInitializerProvider = WorkInitializer_Factory.create(interfaceC6266a7, interfaceC6266a8, this.workSchedulerProvider, interfaceC6266a8);
        this.transportRuntimeProvider = DoubleCheck.provider(TransportRuntime_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.defaultSchedulerProvider, this.uploaderProvider, this.workInitializerProvider));
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    public EventStore getEventStore() {
        return this.sQLiteEventStoreProvider.get();
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    public TransportRuntime getTransportRuntime() {
        return this.transportRuntimeProvider.get();
    }

    private DaggerTransportRuntimeComponent(Context context) {
        initialize(context);
    }
}
