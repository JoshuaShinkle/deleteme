package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class SQLiteEventStore_Factory implements Factory<SQLiteEventStore> {
    private final InterfaceC6266a<Clock> clockProvider;
    private final InterfaceC6266a<EventStoreConfig> configProvider;
    private final InterfaceC6266a<String> packageNameProvider;
    private final InterfaceC6266a<SchemaManager> schemaManagerProvider;
    private final InterfaceC6266a<Clock> wallClockProvider;

    public SQLiteEventStore_Factory(InterfaceC6266a<Clock> interfaceC6266a, InterfaceC6266a<Clock> interfaceC6266a2, InterfaceC6266a<EventStoreConfig> interfaceC6266a3, InterfaceC6266a<SchemaManager> interfaceC6266a4, InterfaceC6266a<String> interfaceC6266a5) {
        this.wallClockProvider = interfaceC6266a;
        this.clockProvider = interfaceC6266a2;
        this.configProvider = interfaceC6266a3;
        this.schemaManagerProvider = interfaceC6266a4;
        this.packageNameProvider = interfaceC6266a5;
    }

    public static SQLiteEventStore_Factory create(InterfaceC6266a<Clock> interfaceC6266a, InterfaceC6266a<Clock> interfaceC6266a2, InterfaceC6266a<EventStoreConfig> interfaceC6266a3, InterfaceC6266a<SchemaManager> interfaceC6266a4, InterfaceC6266a<String> interfaceC6266a5) {
        return new SQLiteEventStore_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3, interfaceC6266a4, interfaceC6266a5);
    }

    public static SQLiteEventStore newInstance(Clock clock, Clock clock2, Object obj, Object obj2, InterfaceC6266a<String> interfaceC6266a) {
        return new SQLiteEventStore(clock, clock2, (EventStoreConfig) obj, (SchemaManager) obj2, interfaceC6266a);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public SQLiteEventStore get() {
        return newInstance(this.wallClockProvider.get(), this.clockProvider.get(), this.configProvider.get(), this.schemaManagerProvider.get(), this.packageNameProvider);
    }
}
