package org.apache.commons.p159io.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes.dex */
public class DeferredFileOutputStream extends ThresholdingOutputStream {
    private boolean closed;
    private OutputStream currentOutputStream;
    private ByteArrayOutputStream memoryOutputStream;
    private File outputFile;

    public DeferredFileOutputStream(int i9, File file) {
        super(i9);
        this.closed = false;
        this.outputFile = file;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.memoryOutputStream = byteArrayOutputStream;
        this.currentOutputStream = byteArrayOutputStream;
    }

    @Override // org.apache.commons.p159io.output.ThresholdingOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.closed = true;
    }

    public byte[] getData() {
        ByteArrayOutputStream byteArrayOutputStream = this.memoryOutputStream;
        if (byteArrayOutputStream != null) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public File getFile() {
        return this.outputFile;
    }

    @Override // org.apache.commons.p159io.output.ThresholdingOutputStream
    public OutputStream getStream() {
        return this.currentOutputStream;
    }

    public boolean isInMemory() {
        return !isThresholdExceeded();
    }

    @Override // org.apache.commons.p159io.output.ThresholdingOutputStream
    public void thresholdReached() {
        FileOutputStream fileOutputStream = new FileOutputStream(this.outputFile);
        this.memoryOutputStream.writeTo(fileOutputStream);
        this.currentOutputStream = fileOutputStream;
        this.memoryOutputStream = null;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (!this.closed) {
            throw new IOException("Stream not closed");
        }
        if (isInMemory()) {
            this.memoryOutputStream.writeTo(outputStream);
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(this.outputFile);
        try {
            IOUtils.copy(fileInputStream, outputStream);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
    }
}
