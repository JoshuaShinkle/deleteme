package p054e2;

import android.content.Context;
import com.cyberlink.clsm.IKeyStore;
import java.io.File;

/* renamed from: e2.b */
/* loaded from: classes.dex */
public class C4711b extends AbstractC4710a implements IKeyStore {
    public C4711b(Context context) {
        super(new File(context.getFilesDir(), "KeyStore"));
    }

    @Override // com.cyberlink.clsm.IKeyStore
    public byte[] getKey(String str) {
        return super.m18853a(str);
    }

    @Override // com.cyberlink.clsm.IKeyStore
    public boolean setKey(String str, byte[] bArr) {
        return super.m18855c(str, bArr);
    }
}
