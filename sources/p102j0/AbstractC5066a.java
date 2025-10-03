package p102j0;

import android.os.Bundle;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.InterfaceC0397r;
import androidx.loader.content.C0406b;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: j0.a */
/* loaded from: classes.dex */
public abstract class AbstractC5066a {

    /* renamed from: j0.a$a */
    public interface a<D> {
        C0406b<D> onCreateLoader(int i9, Bundle bundle);

        void onLoadFinished(C0406b<D> c0406b, D d9);

        void onLoaderReset(C0406b<D> c0406b);
    }

    /* renamed from: b */
    public static <T extends InterfaceC0385f & InterfaceC0397r> AbstractC5066a m19833b(T t8) {
        return new C5067b(t8, t8.getViewModelStore());
    }

    @Deprecated
    /* renamed from: a */
    public abstract void mo19834a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    /* renamed from: c */
    public abstract <D> C0406b<D> mo19835c(int i9, Bundle bundle, a<D> aVar);

    /* renamed from: d */
    public abstract void mo19836d();
}
