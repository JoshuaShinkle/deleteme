package p002a1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import p207u0.ComponentCallbacks2C6355e;
import p252z0.C6808g;
import p252z0.InterfaceC6805d;

/* renamed from: a1.c */
/* loaded from: classes.dex */
public class C0008c implements InterfaceC6805d<InputStream> {

    /* renamed from: b */
    public final Uri f49b;

    /* renamed from: c */
    public final C0010e f50c;

    /* renamed from: d */
    public InputStream f51d;

    /* renamed from: a1.c$a */
    public static class a implements InterfaceC0009d {

        /* renamed from: b */
        public static final String[] f52b = {"_data"};

        /* renamed from: a */
        public final ContentResolver f53a;

        public a(ContentResolver contentResolver) {
            this.f53a = contentResolver;
        }

        @Override // p002a1.InterfaceC0009d
        /* renamed from: a */
        public Cursor mo61a(Uri uri) {
            return this.f53a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, f52b, "kind = 1 AND image_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* renamed from: a1.c$b */
    public static class b implements InterfaceC0009d {

        /* renamed from: b */
        public static final String[] f54b = {"_data"};

        /* renamed from: a */
        public final ContentResolver f55a;

        public b(ContentResolver contentResolver) {
            this.f55a = contentResolver;
        }

        @Override // p002a1.InterfaceC0009d
        /* renamed from: a */
        public Cursor mo61a(Uri uri) {
            return this.f55a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, f54b, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    public C0008c(Uri uri, C0010e c0010e) {
        this.f49b = uri;
        this.f50c = c0010e;
    }

    /* renamed from: c */
    public static C0008c m53c(Context context, Uri uri, InterfaceC0009d interfaceC0009d) {
        return new C0008c(uri, new C0010e(ComponentCallbacks2C6355e.m24381d(context).m24395k().m3810g(), interfaceC0009d, ComponentCallbacks2C6355e.m24381d(context).m24390f(), context.getContentResolver()));
    }

    /* renamed from: d */
    public static C0008c m54d(Context context, Uri uri) {
        return m53c(context, uri, new a(context.getContentResolver()));
    }

    /* renamed from: f */
    public static C0008c m55f(Context context, Uri uri) {
        return m53c(context, uri, new b(context.getContentResolver()));
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<InputStream> mo56a() {
        return InputStream.class;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: b */
    public void mo57b() throws IOException {
        InputStream inputStream = this.f51d;
        if (inputStream != null) {
            try {
                inputStream.close();
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

    @Override // p252z0.InterfaceC6805d
    /* renamed from: g */
    public void mo59g(Priority priority, InterfaceC6805d.a<? super InputStream> aVar) {
        try {
            InputStream inputStreamM60h = m60h();
            this.f51d = inputStreamM60h;
            aVar.mo3903f(inputStreamM60h);
        } catch (FileNotFoundException e9) {
            if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e9);
            }
            aVar.mo3902c(e9);
        }
    }

    /* renamed from: h */
    public final InputStream m60h() throws FileNotFoundException {
        InputStream inputStreamM65d = this.f50c.m65d(this.f49b);
        int iM62a = inputStreamM65d != null ? this.f50c.m62a(this.f49b) : -1;
        return iM62a != -1 ? new C6808g(inputStreamM65d, iM62a) : inputStreamM65d;
    }
}
