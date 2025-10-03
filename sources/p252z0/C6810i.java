package p252z0;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: z0.i */
/* loaded from: classes.dex */
public class C6810i extends AbstractC6813l<ParcelFileDescriptor> {
    public C6810i(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<ParcelFileDescriptor> mo56a() {
        return ParcelFileDescriptor.class;
    }

    @Override // p252z0.AbstractC6813l
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void mo25358c(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        parcelFileDescriptor.close();
    }

    @Override // p252z0.AbstractC6813l
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public ParcelFileDescriptor mo25359d(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (assetFileDescriptorOpenAssetFileDescriptor != null) {
            return assetFileDescriptorOpenAssetFileDescriptor.getParcelFileDescriptor();
        }
        throw new FileNotFoundException("FileDescriptor is null for: " + uri);
    }
}
