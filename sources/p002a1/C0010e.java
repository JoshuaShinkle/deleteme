package p002a1;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.C0824a;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import p022c1.InterfaceC0705b;

/* renamed from: a1.e */
/* loaded from: classes.dex */
public class C0010e {

    /* renamed from: f */
    public static final C0006a f56f = new C0006a();

    /* renamed from: a */
    public final C0006a f57a;

    /* renamed from: b */
    public final InterfaceC0009d f58b;

    /* renamed from: c */
    public final InterfaceC0705b f59c;

    /* renamed from: d */
    public final ContentResolver f60d;

    /* renamed from: e */
    public final List<ImageHeaderParser> f61e;

    public C0010e(List<ImageHeaderParser> list, InterfaceC0009d interfaceC0009d, InterfaceC0705b interfaceC0705b, ContentResolver contentResolver) {
        this(list, f56f, interfaceC0009d, interfaceC0705b, contentResolver);
    }

    /* renamed from: a */
    public int m62a(Uri uri) throws IOException {
        InputStream inputStreamOpenInputStream = null;
        try {
            try {
                inputStreamOpenInputStream = this.f60d.openInputStream(uri);
                int iM3836a = C0824a.m3836a(this.f61e, inputStreamOpenInputStream, this.f59c);
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return iM3836a;
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (IOException | NullPointerException e9) {
            if (Log.isLoggable("ThumbStreamOpener", 3)) {
                Log.d("ThumbStreamOpener", "Failed to open uri: " + uri, e9);
            }
            if (inputStreamOpenInputStream == null) {
                return -1;
            }
            try {
                inputStreamOpenInputStream.close();
                return -1;
            } catch (IOException unused3) {
                return -1;
            }
        }
    }

    /* renamed from: b */
    public final String m63b(Uri uri) {
        Cursor cursorMo61a = this.f58b.mo61a(uri);
        if (cursorMo61a != null) {
            try {
                if (cursorMo61a.moveToFirst()) {
                    return cursorMo61a.getString(0);
                }
            } finally {
                cursorMo61a.close();
            }
        }
        return cursorMo61a != null ? null : null;
    }

    /* renamed from: c */
    public final boolean m64c(File file) {
        return this.f57a.m45a(file) && 0 < this.f57a.m47c(file);
    }

    /* renamed from: d */
    public InputStream m65d(Uri uri) throws FileNotFoundException {
        String strM63b = m63b(uri);
        if (TextUtils.isEmpty(strM63b)) {
            return null;
        }
        File fileM46b = this.f57a.m46b(strM63b);
        if (!m64c(fileM46b)) {
            return null;
        }
        Uri uriFromFile = Uri.fromFile(fileM46b);
        try {
            return this.f60d.openInputStream(uriFromFile);
        } catch (NullPointerException e9) {
            throw ((FileNotFoundException) new FileNotFoundException("NPE opening uri: " + uri + " -> " + uriFromFile).initCause(e9));
        }
    }

    public C0010e(List<ImageHeaderParser> list, C0006a c0006a, InterfaceC0009d interfaceC0009d, InterfaceC0705b interfaceC0705b, ContentResolver contentResolver) {
        this.f57a = c0006a;
        this.f58b = interfaceC0009d;
        this.f59c = interfaceC0705b;
        this.f60d = contentResolver;
        this.f61e = list;
    }
}
