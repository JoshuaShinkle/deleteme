package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.Exception;
import java.util.LinkedList;

/* loaded from: classes.dex */
public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends OutputBuffer, E extends Exception> implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private final Thread decodeThread;
    private I dequeuedInputBuffer;
    private E exception;
    private boolean flushed;
    private final Object lock = new Object();
    private final LinkedList<I> queuedInputBuffers = new LinkedList<>();
    private final LinkedList<O> queuedOutputBuffers = new LinkedList<>();
    private boolean released;
    private int skippedOutputBufferCount;

    public SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        this.availableInputBufferCount = iArr.length;
        for (int i9 = 0; i9 < this.availableInputBufferCount; i9++) {
            ((I[]) this.availableInputBuffers)[i9] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        for (int i10 = 0; i10 < this.availableOutputBufferCount; i10++) {
            ((O[]) this.availableOutputBuffers)[i10] = createOutputBuffer();
        }
        Thread thread = new Thread() { // from class: com.google.android.exoplayer2.decoder.SimpleDecoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                SimpleDecoder.this.run();
            }
        };
        this.decodeThread = thread;
        thread.start();
    }

    private boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }

    private boolean decode() {
        synchronized (this.lock) {
            while (!this.released && !canDecodeBuffer()) {
                this.lock.wait();
            }
            if (this.released) {
                return false;
            }
            I iRemoveFirst = this.queuedInputBuffers.removeFirst();
            O[] oArr = this.availableOutputBuffers;
            int i9 = this.availableOutputBufferCount - 1;
            this.availableOutputBufferCount = i9;
            O o8 = oArr[i9];
            boolean z8 = this.flushed;
            this.flushed = false;
            if (iRemoveFirst.isEndOfStream()) {
                o8.addFlag(4);
            } else {
                if (iRemoveFirst.isDecodeOnly()) {
                    o8.addFlag(Integer.MIN_VALUE);
                }
                try {
                    this.exception = (E) decode(iRemoveFirst, o8, z8);
                } catch (OutOfMemoryError e9) {
                    this.exception = (E) createUnexpectedDecodeException(e9);
                } catch (RuntimeException e10) {
                    this.exception = (E) createUnexpectedDecodeException(e10);
                }
                if (this.exception != null) {
                    synchronized (this.lock) {
                    }
                    return false;
                }
            }
            synchronized (this.lock) {
                if (this.flushed) {
                    releaseOutputBufferInternal(o8);
                } else if (o8.isDecodeOnly()) {
                    this.skippedOutputBufferCount++;
                    releaseOutputBufferInternal(o8);
                } else {
                    o8.skippedOutputBufferCount = this.skippedOutputBufferCount;
                    this.skippedOutputBufferCount = 0;
                    this.queuedOutputBuffers.addLast(o8);
                }
                releaseInputBufferInternal(iRemoveFirst);
            }
            return true;
        }
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Exception */
    private void maybeThrowException() throws E {
        E e9 = this.exception;
        if (e9 != null) {
            throw e9;
        }
    }

    private void releaseInputBufferInternal(I i9) {
        i9.clear();
        I[] iArr = this.availableInputBuffers;
        int i10 = this.availableInputBufferCount;
        this.availableInputBufferCount = i10 + 1;
        iArr[i10] = i9;
    }

    private void releaseOutputBufferInternal(O o8) {
        o8.clear();
        O[] oArr = this.availableOutputBuffers;
        int i9 = this.availableOutputBufferCount;
        this.availableOutputBufferCount = i9 + 1;
        oArr[i9] = o8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void run() {
        do {
            try {
            } catch (InterruptedException e9) {
                throw new IllegalStateException(e9);
            }
        } while (decode());
    }

    public abstract I createInputBuffer();

    public abstract O createOutputBuffer();

    public abstract E createUnexpectedDecodeException(Throwable th);

    public abstract E decode(I i9, O o8, boolean z8);

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public final void flush() {
        synchronized (this.lock) {
            this.flushed = true;
            this.skippedOutputBufferCount = 0;
            I i9 = this.dequeuedInputBuffer;
            if (i9 != null) {
                releaseInputBufferInternal(i9);
                this.dequeuedInputBuffer = null;
            }
            while (!this.queuedInputBuffers.isEmpty()) {
                releaseInputBufferInternal(this.queuedInputBuffers.removeFirst());
            }
            while (!this.queuedOutputBuffers.isEmpty()) {
                releaseOutputBufferInternal(this.queuedOutputBuffers.removeFirst());
            }
        }
    }

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void release() throws InterruptedException {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public void releaseOutputBuffer(O o8) {
        synchronized (this.lock) {
            releaseOutputBufferInternal(o8);
            maybeNotifyDecodeLoop();
        }
    }

    public final void setInitialInputBufferSize(int i9) {
        Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length);
        for (I i10 : this.availableInputBuffers) {
            i10.ensureSpaceForWrite(i9);
        }
    }

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public final I dequeueInputBuffer() {
        I i9;
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkState(this.dequeuedInputBuffer == null);
            int i10 = this.availableInputBufferCount;
            if (i10 == 0) {
                i9 = null;
            } else {
                I[] iArr = this.availableInputBuffers;
                int i11 = i10 - 1;
                this.availableInputBufferCount = i11;
                i9 = iArr[i11];
            }
            this.dequeuedInputBuffer = i9;
        }
        return i9;
    }

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public final O dequeueOutputBuffer() {
        synchronized (this.lock) {
            maybeThrowException();
            if (this.queuedOutputBuffers.isEmpty()) {
                return null;
            }
            return this.queuedOutputBuffers.removeFirst();
        }
    }

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public final void queueInputBuffer(I i9) {
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkArgument(i9 == this.dequeuedInputBuffer);
            this.queuedInputBuffers.addLast(i9);
            maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
        }
    }
}
