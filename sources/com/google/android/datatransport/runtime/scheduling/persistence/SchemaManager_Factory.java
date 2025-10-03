package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {
    private final InterfaceC6266a<Context> contextProvider;
    private final InterfaceC6266a<String> dbNameProvider;
    private final InterfaceC6266a<Integer> schemaVersionProvider;

    public SchemaManager_Factory(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<String> interfaceC6266a2, InterfaceC6266a<Integer> interfaceC6266a3) {
        this.contextProvider = interfaceC6266a;
        this.dbNameProvider = interfaceC6266a2;
        this.schemaVersionProvider = interfaceC6266a3;
    }

    public static SchemaManager_Factory create(InterfaceC6266a<Context> interfaceC6266a, InterfaceC6266a<String> interfaceC6266a2, InterfaceC6266a<Integer> interfaceC6266a3) {
        return new SchemaManager_Factory(interfaceC6266a, interfaceC6266a2, interfaceC6266a3);
    }

    public static SchemaManager newInstance(Context context, String str, int i9) {
        return new SchemaManager(context, str, i9);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public SchemaManager get() {
        return newInstance(this.contextProvider.get(), this.dbNameProvider.get(), this.schemaVersionProvider.get().intValue());
    }
}
