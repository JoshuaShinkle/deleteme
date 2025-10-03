package p252z0;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import p252z0.InterfaceC6805d;

/* renamed from: z0.l */
/* loaded from: classes.dex */
public abstract class AbstractC6813l<T> implements InterfaceC6805d<T> {

    /* renamed from: b */
    public final Uri f22566b;

    /* renamed from: c */
    public final ContentResolver f22567c;

    /* renamed from: d */
    public T f22568d;

    public AbstractC6813l(ContentResolver contentResolver, Uri uri) {
        this.f22567c = contentResolver;
        this.f22566b = uri;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: b */
    public void mo57b() {
        T t8 = this.f22568d;
        if (t8 != null) {
            try {
                mo25358c(t8);
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: c */
    public abstract void mo25358c(T t8);

    @Override // p252z0.InterfaceC6805d
    public void cancel() {
    }

    /* renamed from: d */
    public abstract T mo25359d(Uri uri, ContentResolver contentResolver);

    @Override // p252z0.InterfaceC6805d
    /* renamed from: e */
    public DataSource mo58e() {
        return DataSource.LOCAL;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: g */
    public final void mo59g(Priority priority, InterfaceC6805d.a<? super T> aVar) {
        try {
            T tMo25359d = mo25359d(this.f22566b, this.f22567c);
            this.f22568d = tMo25359d;
            aVar.mo3903f(tMo25359d);
        } catch (FileNotFoundException e9) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e9);
            }
            aVar.mo3902c(e9);
        }
    }
}
