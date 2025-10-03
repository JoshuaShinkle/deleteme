package p252z0;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: z0.a */
/* loaded from: classes.dex */
public final class C6802a extends AbstractC6813l<AssetFileDescriptor> {
    public C6802a(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<AssetFileDescriptor> mo56a() {
        return AssetFileDescriptor.class;
    }

    @Override // p252z0.AbstractC6813l
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void mo25358c(AssetFileDescriptor assetFileDescriptor) throws IOException {
        assetFileDescriptor.close();
    }

    @Override // p252z0.AbstractC6813l
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public AssetFileDescriptor mo25359d(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (assetFileDescriptorOpenAssetFileDescriptor != null) {
            return assetFileDescriptorOpenAssetFileDescriptor;
        }
        throw new FileNotFoundException("FileDescriptor is null for: " + uri);
    }
}
