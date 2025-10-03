package net.sqlcipher.database;

import p151o0.InterfaceC5390b;

/* loaded from: classes2.dex */
public class SupportFactory {
    private final boolean clearPassphrase;
    private final SQLiteDatabaseHook hook;
    private final byte[] passphrase;

    public SupportFactory(byte[] bArr) {
        this(bArr, null);
    }

    public InterfaceC5390b create(InterfaceC5390b.a aVar) {
        return new SupportHelper(aVar, this.passphrase, this.hook, this.clearPassphrase);
    }

    public SupportFactory(byte[] bArr, SQLiteDatabaseHook sQLiteDatabaseHook) {
        this(bArr, sQLiteDatabaseHook, true);
    }

    public SupportFactory(byte[] bArr, SQLiteDatabaseHook sQLiteDatabaseHook, boolean z8) {
        this.passphrase = bArr;
        this.hook = sQLiteDatabaseHook;
        this.clearPassphrase = z8;
    }
}
