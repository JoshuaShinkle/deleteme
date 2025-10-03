package p073g1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import p002a1.C0007b;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: g1.k */
/* loaded from: classes.dex */
public final class C4817k implements InterfaceC4820n<Uri, File> {

    /* renamed from: a */
    public final Context f16765a;

    /* renamed from: g1.k$a */
    public static final class a implements InterfaceC4821o<Uri, File> {

        /* renamed from: a */
        public final Context f16766a;

        public a(Context context) {
            this.f16766a = context;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, File> mo3831a(C4824r c4824r) {
            return new C4817k(this.f16766a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.k$b */
    public static class b implements InterfaceC6805d<File> {

        /* renamed from: d */
        public static final String[] f16767d = {"_data"};

        /* renamed from: b */
        public final Context f16768b;

        /* renamed from: c */
        public final Uri f16769c;

        public b(Context context, Uri uri) {
            this.f16768b = context;
            this.f16769c = uri;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<File> mo56a() {
            return File.class;
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
        public void mo59g(Priority priority, InterfaceC6805d.a<? super File> aVar) {
            Cursor cursorQuery = this.f16768b.getContentResolver().query(this.f16769c, f16767d, null, null, null);
            if (cursorQuery != null) {
                try {
                    string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                } finally {
                    cursorQuery.close();
                }
            }
            if (!TextUtils.isEmpty(string)) {
                aVar.mo3903f(new File(string));
                return;
            }
            aVar.mo3902c(new FileNotFoundException("Failed to find file path for: " + this.f16769c));
        }
    }

    public C4817k(Context context) {
        this.f16765a = context;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<File> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(uri), new b(this.f16765a, uri));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return C0007b.m49b(uri);
    }
}
