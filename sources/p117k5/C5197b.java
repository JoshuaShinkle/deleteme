package p117k5;

import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.AbstractFuture;
import com.perfectcorp.utility.C4506a;
import com.perfectcorp.ycl.network.downloader.DownloadingState;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.CancellationException;

/* renamed from: k5.b */
/* loaded from: classes2.dex */
public class C5197b extends AbstractC5196a<File> {

    /* renamed from: f */
    public final URI f17805f;

    /* renamed from: g */
    public final File f17806g;

    public C5197b(URI uri, File file) {
        this.f17805f = uri;
        this.f17806g = file;
    }

    /* JADX WARN: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01f8  */
    @Override // p117k5.AbstractC5198c
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo20319c() throws Throwable {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        HttpURLConnection httpURLConnection;
        Throwable e9;
        AbstractFuture abstractFuture;
        CancellationException cancellationException;
        String string;
        int i9;
        m20318g(new DownloadingState(DownloadingState.State.Running, DownloadingState.f16032c));
        InputStream inputStream = null;
        try {
            string = this.f17805f.toString();
        } catch (Exception e10) {
            fileOutputStream = null;
            bufferedOutputStream = null;
            e9 = e10;
            httpURLConnection = null;
        } catch (Throwable th2) {
            fileOutputStream = null;
            bufferedOutputStream = null;
            th = th2;
            httpURLConnection = null;
        }
        if (string == null) {
            this.f17807a.setException(new RuntimeException("Bad request url."));
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            if (this.f17807a.isCancelled()) {
                this.f17807a.setException(new CancellationException());
                return;
            }
            return;
        }
        httpURLConnection = (HttpURLConnection) new URL(string).openConnection();
        try {
        } catch (Exception e11) {
            e9 = e11;
            fileOutputStream = null;
            bufferedOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            bufferedOutputStream = null;
        }
        if (this.f17807a.isCancelled()) {
            this.f17807a.setException(new CancellationException());
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (this.f17807a.isCancelled()) {
                this.f17807a.setException(new CancellationException());
                return;
            }
            return;
        }
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/5.0");
        int responseCode = httpURLConnection.getResponseCode();
        if (this.f17807a.isCancelled()) {
            this.f17807a.setException(new CancellationException());
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            httpURLConnection.disconnect();
            if (this.f17807a.isCancelled()) {
                this.f17807a.setException(new CancellationException());
                return;
            }
            return;
        }
        if (responseCode >= 400 && responseCode < 600) {
            this.f17807a.setException(new RuntimeException(httpURLConnection.getResponseMessage()));
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            C4506a.m18099b(null);
            httpURLConnection.disconnect();
            if (this.f17807a.isCancelled()) {
                this.f17807a.setException(new CancellationException());
                return;
            }
            return;
        }
        InputStream inputStream2 = httpURLConnection.getInputStream();
        try {
            byte[] bArr = new byte[Math.max(Math.min(4096, httpURLConnection.getContentLength() / 100), 512)];
            fileOutputStream = new FileOutputStream(this.f17806g);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int i10 = 0;
                do {
                    try {
                        if (this.f17807a.isCancelled()) {
                            this.f17807a.setException(new CancellationException());
                            C4506a.m18099b(inputStream2);
                            C4506a.m18099b(bufferedOutputStream);
                            C4506a.m18099b(fileOutputStream);
                            httpURLConnection.disconnect();
                            if (this.f17807a.isCancelled()) {
                                this.f17807a.setException(new CancellationException());
                                return;
                            }
                            return;
                        }
                        i9 = inputStream2.read(bArr);
                        if (i9 < 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, i9);
                        i10 += i9;
                        m20316e(i10 / r3);
                    } catch (Exception e12) {
                        e9 = e12;
                        inputStream = inputStream2;
                        try {
                            this.f17806g.delete();
                            this.f17807a.setException(e9);
                            C4506a.m18099b(inputStream);
                            C4506a.m18099b(bufferedOutputStream);
                            C4506a.m18099b(fileOutputStream);
                            if (httpURLConnection != null) {
                            }
                            if (this.f17807a.isCancelled()) {
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            C4506a.m18099b(inputStream);
                            C4506a.m18099b(bufferedOutputStream);
                            C4506a.m18099b(fileOutputStream);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            if (this.f17807a.isCancelled()) {
                                this.f17807a.setException(new CancellationException());
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        inputStream = inputStream2;
                        C4506a.m18099b(inputStream);
                        C4506a.m18099b(bufferedOutputStream);
                        C4506a.m18099b(fileOutputStream);
                        if (httpURLConnection != null) {
                        }
                        if (this.f17807a.isCancelled()) {
                        }
                        throw th;
                    }
                } while (i9 > 0);
                bufferedOutputStream.flush();
                m20317f();
                this.f17807a.set(this.f17806g);
                C4506a.m18099b(inputStream2);
                C4506a.m18099b(bufferedOutputStream);
                C4506a.m18099b(fileOutputStream);
                httpURLConnection.disconnect();
            } catch (Exception e13) {
                e = e13;
                bufferedOutputStream = null;
                inputStream = inputStream2;
                e9 = e;
                this.f17806g.delete();
                this.f17807a.setException(e9);
                C4506a.m18099b(inputStream);
                C4506a.m18099b(bufferedOutputStream);
                C4506a.m18099b(fileOutputStream);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (this.f17807a.isCancelled()) {
                    return;
                }
                abstractFuture = this.f17807a;
                cancellationException = new CancellationException();
                abstractFuture.setException(cancellationException);
            } catch (Throwable th6) {
                th = th6;
                bufferedOutputStream = null;
                inputStream = inputStream2;
                th = th;
                C4506a.m18099b(inputStream);
                C4506a.m18099b(bufferedOutputStream);
                C4506a.m18099b(fileOutputStream);
                if (httpURLConnection != null) {
                }
                if (this.f17807a.isCancelled()) {
                }
                throw th;
            }
        } catch (Exception e14) {
            e = e14;
            fileOutputStream = null;
            bufferedOutputStream = null;
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
            bufferedOutputStream = null;
        }
        if (this.f17807a.isCancelled()) {
            abstractFuture = this.f17807a;
            cancellationException = new CancellationException();
            abstractFuture.setException(cancellationException);
        }
    }
}
