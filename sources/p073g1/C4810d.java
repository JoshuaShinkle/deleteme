package p073g1;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p226w1.C6508a;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: g1.d */
/* loaded from: classes.dex */
public class C4810d implements InterfaceC4820n<File, ByteBuffer> {

    /* renamed from: g1.d$a */
    public static final class a implements InterfaceC6805d<ByteBuffer> {

        /* renamed from: b */
        public final File f16736b;

        public a(File file) {
            this.f16736b = file;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<ByteBuffer> mo56a() {
            return ByteBuffer.class;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return DataSource.LOCAL;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super ByteBuffer> aVar) {
            try {
                aVar.mo3903f(C6508a.m24912a(this.f16736b));
            } catch (IOException e9) {
                if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                    Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e9);
                }
                aVar.mo3902c(e9);
            }
        }
    }

    /* renamed from: g1.d$b */
    public static class b implements InterfaceC4821o<File, ByteBuffer> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<File, ByteBuffer> mo3831a(C4824r c4824r) {
            return new C4810d();
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<ByteBuffer> mo3827b(File file, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(file), new a(file));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(File file) {
        return true;
    }
}
