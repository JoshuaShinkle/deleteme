package p252z0;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

/* renamed from: z0.h */
/* loaded from: classes.dex */
public class C6809h extends AbstractC6803b<ParcelFileDescriptor> {
    public C6809h(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<ParcelFileDescriptor> mo56a() {
        return ParcelFileDescriptor.class;
    }

    @Override // p252z0.AbstractC6803b
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void mo25362c(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        parcelFileDescriptor.close();
    }

    @Override // p252z0.AbstractC6803b
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public ParcelFileDescriptor mo25363d(AssetManager assetManager, String str) {
        return assetManager.openFd(str).getParcelFileDescriptor();
    }
}
