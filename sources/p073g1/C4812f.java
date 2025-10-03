package p073g1;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import net.sqlcipher.database.SQLiteDatabase;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: g1.f */
/* loaded from: classes.dex */
public class C4812f<Data> implements InterfaceC4820n<File, Data> {

    /* renamed from: a */
    public final d<Data> f16743a;

    /* renamed from: g1.f$a */
    public static class a<Data> implements InterfaceC4821o<File, Data> {

        /* renamed from: a */
        public final d<Data> f16744a;

        public a(d<Data> dVar) {
            this.f16744a = dVar;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public final InterfaceC4820n<File, Data> mo3831a(C4824r c4824r) {
            return new C4812f(this.f16744a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public final void mo3832b() {
        }
    }

    /* renamed from: g1.f$b */
    public static class b extends a<ParcelFileDescriptor> {

        /* renamed from: g1.f$b$a */
        public class a implements d<ParcelFileDescriptor> {
            @Override // p073g1.C4812f.d
            /* renamed from: a */
            public Class<ParcelFileDescriptor> mo19097a() {
                return ParcelFileDescriptor.class;
            }

            @Override // p073g1.C4812f.d
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void mo19098b(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                parcelFileDescriptor.close();
            }

            @Override // p073g1.C4812f.d
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public ParcelFileDescriptor mo19099c(File file) {
                return ParcelFileDescriptor.open(file, SQLiteDatabase.CREATE_IF_NECESSARY);
            }
        }

        public b() {
            super(new a());
        }
    }

    /* renamed from: g1.f$c */
    public static final class c<Data> implements InterfaceC6805d<Data> {

        /* renamed from: b */
        public final File f16745b;

        /* renamed from: c */
        public final d<Data> f16746c;

        /* renamed from: d */
        public Data f16747d;

        public c(File file, d<Data> dVar) {
            this.f16745b = file;
            this.f16746c = dVar;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<Data> mo56a() {
            return this.f16746c.mo19097a();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
            Data data = this.f16747d;
            if (data != null) {
                try {
                    this.f16746c.mo19098b(data);
                } catch (IOException unused) {
                }
            }
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return DataSource.LOCAL;
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [Data, java.lang.Object] */
        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super Data> aVar) {
            try {
                Data dataMo19099c = this.f16746c.mo19099c(this.f16745b);
                this.f16747d = dataMo19099c;
                aVar.mo3903f(dataMo19099c);
            } catch (FileNotFoundException e9) {
                if (Log.isLoggable("FileLoader", 3)) {
                    Log.d("FileLoader", "Failed to open file", e9);
                }
                aVar.mo3902c(e9);
            }
        }
    }

    /* renamed from: g1.f$d */
    public interface d<Data> {
        /* renamed from: a */
        Class<Data> mo19097a();

        /* renamed from: b */
        void mo19098b(Data data);

        /* renamed from: c */
        Data mo19099c(File file);
    }

    /* renamed from: g1.f$e */
    public static class e extends a<InputStream> {

        /* renamed from: g1.f$e$a */
        public class a implements d<InputStream> {
            @Override // p073g1.C4812f.d
            /* renamed from: a */
            public Class<InputStream> mo19097a() {
                return InputStream.class;
            }

            @Override // p073g1.C4812f.d
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void mo19098b(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // p073g1.C4812f.d
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public InputStream mo19099c(File file) {
                return new FileInputStream(file);
            }
        }

        public e() {
            super(new a());
        }
    }

    public C4812f(d<Data> dVar) {
        this.f16743a = dVar;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(File file, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(file), new c(file, this.f16743a));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(File file) {
        return true;
    }
}
