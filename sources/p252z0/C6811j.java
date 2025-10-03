package p252z0;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import p073g1.C4813g;
import p226w1.C6510c;
import p226w1.C6512e;
import p252z0.InterfaceC6805d;

/* renamed from: z0.j */
/* loaded from: classes.dex */
public class C6811j implements InterfaceC6805d<InputStream> {

    /* renamed from: h */
    public static final b f22557h = new a();

    /* renamed from: b */
    public final C4813g f22558b;

    /* renamed from: c */
    public final int f22559c;

    /* renamed from: d */
    public final b f22560d;

    /* renamed from: e */
    public HttpURLConnection f22561e;

    /* renamed from: f */
    public InputStream f22562f;

    /* renamed from: g */
    public volatile boolean f22563g;

    /* renamed from: z0.j$a */
    public static class a implements b {
        @Override // p252z0.C6811j.b
        /* renamed from: a */
        public HttpURLConnection mo25376a(URL url) {
            return (HttpURLConnection) url.openConnection();
        }
    }

    /* renamed from: z0.j$b */
    public interface b {
        /* renamed from: a */
        HttpURLConnection mo25376a(URL url);
    }

    public C6811j(C4813g c4813g, int i9) {
        this(c4813g, i9, f22557h);
    }

    /* renamed from: d */
    public static boolean m25372d(int i9) {
        return i9 / 100 == 2;
    }

    /* renamed from: f */
    public static boolean m25373f(int i9) {
        return i9 / 100 == 3;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<InputStream> mo56a() {
        return InputStream.class;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: b */
    public void mo57b() throws IOException {
        InputStream inputStream = this.f22562f;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.f22561e;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.f22561e = null;
    }

    /* renamed from: c */
    public final InputStream m25374c(HttpURLConnection httpURLConnection) {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.f22562f = C6510c.m24917u(httpURLConnection.getInputStream(), httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                Log.d("HttpUrlFetcher", "Got non empty content encoding: " + httpURLConnection.getContentEncoding());
            }
            this.f22562f = httpURLConnection.getInputStream();
        }
        return this.f22562f;
    }

    @Override // p252z0.InterfaceC6805d
    public void cancel() {
        this.f22563g = true;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: e */
    public DataSource mo58e() {
        return DataSource.REMOTE;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: g */
    public void mo59g(Priority priority, InterfaceC6805d.a<? super InputStream> aVar) {
        StringBuilder sb;
        long jM24923b = C6512e.m24923b();
        try {
            try {
                aVar.mo3903f(m25375h(this.f22558b.m19110i(), 0, null, this.f22558b.m19106e()));
            } catch (IOException e9) {
                if (Log.isLoggable("HttpUrlFetcher", 3)) {
                    Log.d("HttpUrlFetcher", "Failed to load data for url", e9);
                }
                aVar.mo3902c(e9);
                if (!Log.isLoggable("HttpUrlFetcher", 2)) {
                    return;
                } else {
                    sb = new StringBuilder();
                }
            }
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                sb = new StringBuilder();
                sb.append("Finished http url fetcher fetch in ");
                sb.append(C6512e.m24922a(jM24923b));
                Log.v("HttpUrlFetcher", sb.toString());
            }
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                Log.v("HttpUrlFetcher", "Finished http url fetcher fetch in " + C6512e.m24922a(jM24923b));
            }
            throw th;
        }
    }

    /* renamed from: h */
    public final InputStream m25375h(URL url, int i9, URL url2, Map<String, String> map) throws IOException {
        if (i9 >= 5) {
            throw new HttpException("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new HttpException("In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        this.f22561e = this.f22560d.mo25376a(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.f22561e.addRequestProperty(entry.getKey(), entry.getValue());
        }
        this.f22561e.setConnectTimeout(this.f22559c);
        this.f22561e.setReadTimeout(this.f22559c);
        this.f22561e.setUseCaches(false);
        this.f22561e.setDoInput(true);
        this.f22561e.setInstanceFollowRedirects(false);
        this.f22561e.connect();
        this.f22562f = this.f22561e.getInputStream();
        if (this.f22563g) {
            return null;
        }
        int responseCode = this.f22561e.getResponseCode();
        if (m25372d(responseCode)) {
            return m25374c(this.f22561e);
        }
        if (!m25373f(responseCode)) {
            if (responseCode == -1) {
                throw new HttpException(responseCode);
            }
            throw new HttpException(this.f22561e.getResponseMessage(), responseCode);
        }
        String headerField = this.f22561e.getHeaderField(HttpHeaders.LOCATION);
        if (TextUtils.isEmpty(headerField)) {
            throw new HttpException("Received empty or null redirect url");
        }
        URL url3 = new URL(url, headerField);
        mo57b();
        return m25375h(url3, i9 + 1, url, map);
    }

    public C6811j(C4813g c4813g, int i9, b bVar) {
        this.f22558b = c4813g;
        this.f22559c = i9;
        this.f22560d = bVar;
    }
}
