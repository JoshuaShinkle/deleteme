package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes.dex */
public final class FileDataSource implements DataSource {
    private long bytesRemaining;
    private RandomAccessFile file;
    private final TransferListener<? super FileDataSource> listener;
    private boolean opened;
    private Uri uri;

    public static class FileDataSourceException extends IOException {
        public FileDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public FileDataSource() {
        this(null);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        this.uri = null;
        try {
            try {
                RandomAccessFile randomAccessFile = this.file;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e9) {
                throw new FileDataSourceException(e9);
            }
        } finally {
            this.file = null;
            if (this.opened) {
                this.opened = false;
                TransferListener<? super FileDataSource> transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onTransferEnd(this);
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.uri = dataSpec.uri;
            RandomAccessFile randomAccessFile = new RandomAccessFile(dataSpec.uri.getPath(), "r");
            this.file = randomAccessFile;
            randomAccessFile.seek(dataSpec.position);
            long length = dataSpec.length;
            if (length == -1) {
                length = this.file.length() - dataSpec.position;
            }
            this.bytesRemaining = length;
            if (length < 0) {
                throw new EOFException();
            }
            this.opened = true;
            TransferListener<? super FileDataSource> transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferStart(this, dataSpec);
            }
            return this.bytesRemaining;
        } catch (IOException e9) {
            throw new FileDataSourceException(e9);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        if (i10 == 0) {
            return 0;
        }
        long j9 = this.bytesRemaining;
        if (j9 == 0) {
            return -1;
        }
        try {
            int i11 = this.file.read(bArr, i9, (int) Math.min(j9, i10));
            if (i11 > 0) {
                this.bytesRemaining -= i11;
                TransferListener<? super FileDataSource> transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onBytesTransferred(this, i11);
                }
            }
            return i11;
        } catch (IOException e9) {
            throw new FileDataSourceException(e9);
        }
    }

    public FileDataSource(TransferListener<? super FileDataSource> transferListener) {
        this.listener = transferListener;
    }
}
