package p252z0;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: z0.m */
/* loaded from: classes.dex */
public class C6814m extends AbstractC6803b<InputStream> {
    public C6814m(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<InputStream> mo56a() {
        return InputStream.class;
    }

    @Override // p252z0.AbstractC6803b
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void mo25362c(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @Override // p252z0.AbstractC6803b
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public InputStream mo25363d(AssetManager assetManager, String str) {
        return assetManager.open(str);
    }
}
