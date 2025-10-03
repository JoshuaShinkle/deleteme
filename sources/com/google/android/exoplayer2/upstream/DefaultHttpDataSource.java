package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;
import com.google.common.net.HttpHeaders;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class DefaultHttpDataSource implements HttpDataSource {
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "DefaultHttpDataSource";
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    private final int connectTimeoutMillis;
    private HttpURLConnection connection;
    private final Predicate<String> contentTypePredicate;
    private DataSpec dataSpec;
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private InputStream inputStream;
    private final TransferListener<? super DefaultHttpDataSource> listener;
    private boolean opened;
    private final int readTimeoutMillis;
    private final HttpDataSource.RequestProperties requestProperties;
    private final String userAgent;
    private static final Pattern CONTENT_RANGE_HEADER = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> skipBufferReference = new AtomicReference<>();

    public DefaultHttpDataSource(String str, Predicate<String> predicate) {
        this(str, predicate, null);
    }

    private void closeConnectionQuietly() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e9) {
                Log.e(TAG, "Unexpected error while disconnecting", e9);
            }
            this.connection = null;
        }
    }

    private static long getContentLength(HttpURLConnection httpURLConnection) throws NumberFormatException {
        long j9;
        String headerField = httpURLConnection.getHeaderField(HttpHeaders.CONTENT_LENGTH);
        if (TextUtils.isEmpty(headerField)) {
            j9 = -1;
        } else {
            try {
                j9 = Long.parseLong(headerField);
            } catch (NumberFormatException unused) {
                Log.e(TAG, "Unexpected Content-Length [" + headerField + "]");
            }
        }
        String headerField2 = httpURLConnection.getHeaderField(HttpHeaders.CONTENT_RANGE);
        if (TextUtils.isEmpty(headerField2)) {
            return j9;
        }
        Matcher matcher = CONTENT_RANGE_HEADER.matcher(headerField2);
        if (!matcher.find()) {
            return j9;
        }
        try {
            long j10 = (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            if (j9 < 0) {
                return j10;
            }
            if (j9 == j10) {
                return j9;
            }
            Log.w(TAG, "Inconsistent headers [" + headerField + "] [" + headerField2 + "]");
            return Math.max(j9, j10);
        } catch (NumberFormatException unused2) {
            Log.e(TAG, "Unexpected Content-Range [" + headerField2 + "]");
            return j9;
        }
    }

    private static URL handleRedirect(URL url, String str) throws ProtocolException {
        if (str == null) {
            throw new ProtocolException("Null location redirect");
        }
        URL url2 = new URL(url, str);
        String protocol = url2.getProtocol();
        if ("https".equals(protocol) || "http".equals(protocol)) {
            return url2;
        }
        throw new ProtocolException("Unsupported protocol redirect: " + protocol);
    }

    private HttpURLConnection makeConnection(DataSpec dataSpec) throws IOException {
        HttpURLConnection httpURLConnectionMakeConnection;
        URL url = new URL(dataSpec.uri.toString());
        byte[] bArr = dataSpec.postBody;
        long j9 = dataSpec.position;
        long j10 = dataSpec.length;
        boolean zIsFlagSet = dataSpec.isFlagSet(1);
        if (!this.allowCrossProtocolRedirects) {
            return makeConnection(url, bArr, j9, j10, zIsFlagSet, true);
        }
        int i9 = 0;
        while (true) {
            int i10 = i9 + 1;
            if (i9 > 20) {
                throw new NoRouteToHostException("Too many redirects: " + i10);
            }
            long j11 = j9;
            httpURLConnectionMakeConnection = makeConnection(url, bArr, j9, j10, zIsFlagSet, false);
            int responseCode = httpURLConnectionMakeConnection.getResponseCode();
            if (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303 && (bArr != null || (responseCode != 307 && responseCode != 308))) {
                break;
            }
            String headerField = httpURLConnectionMakeConnection.getHeaderField(HttpHeaders.LOCATION);
            httpURLConnectionMakeConnection.disconnect();
            url = handleRedirect(url, headerField);
            bArr = null;
            i9 = i10;
            j9 = j11;
        }
        return httpURLConnectionMakeConnection;
    }

    private static void maybeTerminateInputStream(HttpURLConnection httpURLConnection, long j9) throws IllegalAccessException, NoSuchMethodException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int i9 = Util.SDK_INT;
        if (i9 == 19 || i9 == 20) {
            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                if (j9 == -1) {
                    if (inputStream.read() == -1) {
                        return;
                    }
                } else if (j9 <= 2048) {
                    return;
                }
                String name = inputStream.getClass().getName();
                if (name.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream") || name.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream")) {
                    Method declaredMethod = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(inputStream, new Object[0]);
                }
            } catch (Exception unused) {
            }
        }
    }

    private int readInternal(byte[] bArr, int i9, int i10) throws IOException {
        if (i10 == 0) {
            return 0;
        }
        long j9 = this.bytesToRead;
        if (j9 != -1) {
            long j10 = j9 - this.bytesRead;
            if (j10 == 0) {
                return -1;
            }
            i10 = (int) Math.min(i10, j10);
        }
        int i11 = this.inputStream.read(bArr, i9, i10);
        if (i11 == -1) {
            if (this.bytesToRead == -1) {
                return -1;
            }
            throw new EOFException();
        }
        this.bytesRead += i11;
        TransferListener<? super DefaultHttpDataSource> transferListener = this.listener;
        if (transferListener != null) {
            transferListener.onBytesTransferred(this, i11);
        }
        return i11;
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped == this.bytesToSkip) {
            return;
        }
        byte[] andSet = skipBufferReference.getAndSet(null);
        if (andSet == null) {
            andSet = new byte[4096];
        }
        while (true) {
            long j9 = this.bytesSkipped;
            long j10 = this.bytesToSkip;
            if (j9 == j10) {
                skipBufferReference.set(andSet);
                return;
            }
            int i9 = this.inputStream.read(andSet, 0, (int) Math.min(j10 - j9, andSet.length));
            if (Thread.interrupted()) {
                throw new InterruptedIOException();
            }
            if (i9 == -1) {
                throw new EOFException();
            }
            this.bytesSkipped += i9;
            TransferListener<? super DefaultHttpDataSource> transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onBytesTransferred(this, i9);
            }
        }
    }

    public final long bytesRead() {
        return this.bytesRead;
    }

    public final long bytesRemaining() {
        long j9 = this.bytesToRead;
        return j9 == -1 ? j9 : j9 - this.bytesRead;
    }

    public final long bytesSkipped() {
        return this.bytesSkipped;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource, com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        try {
            if (this.inputStream != null) {
                maybeTerminateInputStream(this.connection, bytesRemaining());
                try {
                    this.inputStream.close();
                } catch (IOException e9) {
                    throw new HttpDataSource.HttpDataSourceException(e9, this.dataSpec, 3);
                }
            }
        } finally {
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                TransferListener<? super DefaultHttpDataSource> transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onTransferEnd(this);
                }
            }
        }
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return null;
        }
        return httpURLConnection.getHeaderFields();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource, com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException, NumberFormatException {
        this.dataSpec = dataSpec;
        long j9 = 0;
        this.bytesRead = 0L;
        this.bytesSkipped = 0L;
        try {
            HttpURLConnection httpURLConnectionMakeConnection = makeConnection(dataSpec);
            this.connection = httpURLConnectionMakeConnection;
            try {
                int responseCode = httpURLConnectionMakeConnection.getResponseCode();
                if (responseCode < 200 || responseCode > 299) {
                    Map<String, List<String>> headerFields = this.connection.getHeaderFields();
                    closeConnectionQuietly();
                    HttpDataSource.InvalidResponseCodeException invalidResponseCodeException = new HttpDataSource.InvalidResponseCodeException(responseCode, headerFields, dataSpec);
                    if (responseCode != 416) {
                        throw invalidResponseCodeException;
                    }
                    invalidResponseCodeException.initCause(new DataSourceException(0));
                    throw invalidResponseCodeException;
                }
                String contentType = this.connection.getContentType();
                Predicate<String> predicate = this.contentTypePredicate;
                if (predicate != null && !predicate.evaluate(contentType)) {
                    closeConnectionQuietly();
                    throw new HttpDataSource.InvalidContentTypeException(contentType, dataSpec);
                }
                if (responseCode == 200) {
                    long j10 = dataSpec.position;
                    if (j10 != 0) {
                        j9 = j10;
                    }
                }
                this.bytesToSkip = j9;
                if (dataSpec.isFlagSet(1)) {
                    this.bytesToRead = dataSpec.length;
                } else {
                    long j11 = dataSpec.length;
                    if (j11 != -1) {
                        this.bytesToRead = j11;
                    } else {
                        long contentLength = getContentLength(this.connection);
                        this.bytesToRead = contentLength != -1 ? contentLength - this.bytesToSkip : -1L;
                    }
                }
                try {
                    this.inputStream = this.connection.getInputStream();
                    this.opened = true;
                    TransferListener<? super DefaultHttpDataSource> transferListener = this.listener;
                    if (transferListener != null) {
                        transferListener.onTransferStart(this, dataSpec);
                    }
                    return this.bytesToRead;
                } catch (IOException e9) {
                    closeConnectionQuietly();
                    throw new HttpDataSource.HttpDataSourceException(e9, dataSpec, 1);
                }
            } catch (IOException e10) {
                closeConnectionQuietly();
                throw new HttpDataSource.HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(), e10, dataSpec, 1);
            }
        } catch (IOException e11) {
            throw new HttpDataSource.HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(), e11, dataSpec, 1);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource, com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) throws HttpDataSource.HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(bArr, i9, i10);
        } catch (IOException e9) {
            throw new HttpDataSource.HttpDataSourceException(e9, this.dataSpec, 2);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    public DefaultHttpDataSource(String str, Predicate<String> predicate, TransferListener<? super DefaultHttpDataSource> transferListener) {
        this(str, predicate, transferListener, 8000, 8000);
    }

    public DefaultHttpDataSource(String str, Predicate<String> predicate, TransferListener<? super DefaultHttpDataSource> transferListener, int i9, int i10) {
        this(str, predicate, transferListener, i9, i10, false, null);
    }

    public DefaultHttpDataSource(String str, Predicate<String> predicate, TransferListener<? super DefaultHttpDataSource> transferListener, int i9, int i10, boolean z8, HttpDataSource.RequestProperties requestProperties) {
        this.userAgent = Assertions.checkNotEmpty(str);
        this.contentTypePredicate = predicate;
        this.listener = transferListener;
        this.requestProperties = new HttpDataSource.RequestProperties();
        this.connectTimeoutMillis = i9;
        this.readTimeoutMillis = i10;
        this.allowCrossProtocolRedirects = z8;
        this.defaultRequestProperties = requestProperties;
    }

    private HttpURLConnection makeConnection(URL url, byte[] bArr, long j9, long j10, boolean z8, boolean z9) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.connectTimeoutMillis);
        httpURLConnection.setReadTimeout(this.readTimeoutMillis);
        HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
        if (requestProperties != null) {
            for (Map.Entry<String, String> entry : requestProperties.getSnapshot().entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry2 : this.requestProperties.getSnapshot().entrySet()) {
            httpURLConnection.setRequestProperty(entry2.getKey(), entry2.getValue());
        }
        if (j9 != 0 || j10 != -1) {
            String str = "bytes=" + j9 + "-";
            if (j10 != -1) {
                str = str + ((j9 + j10) - 1);
            }
            httpURLConnection.setRequestProperty(HttpHeaders.RANGE, str);
        }
        httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, this.userAgent);
        if (!z8) {
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z9);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod("POST");
            if (bArr.length == 0) {
                httpURLConnection.connect();
            } else {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
            }
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }
}
