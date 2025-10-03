package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/* loaded from: classes2.dex */
class CrossProcessLock {
    private static final String TAG = "CrossProcessLock";
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel fileChannel, FileLock fileLock) {
        this.channel = fileChannel;
        this.lock = fileLock;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x003c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CrossProcessLock acquire(Context context, String str) throws IOException {
        FileChannel channel;
        FileLock fileLockLock;
        try {
            channel = new RandomAccessFile(new File(context.getFilesDir(), str), "rw").getChannel();
            try {
                fileLockLock = channel.lock();
                try {
                    return new CrossProcessLock(channel, fileLockLock);
                } catch (IOException e9) {
                    e = e9;
                    Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        try {
                            fileLockLock.release();
                        } catch (IOException unused) {
                        }
                    }
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                } catch (Error e10) {
                    e = e10;
                    Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                    }
                    if (channel != null) {
                    }
                    return null;
                } catch (OverlappingFileLockException e11) {
                    e = e11;
                    Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                    }
                    if (channel != null) {
                    }
                    return null;
                }
            } catch (IOException | Error | OverlappingFileLockException e12) {
                e = e12;
                fileLockLock = null;
            }
        } catch (IOException | Error | OverlappingFileLockException e13) {
            e = e13;
            channel = null;
            fileLockLock = null;
        }
    }

    public void releaseAndClose() throws IOException {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e9) {
            Log.e(TAG, "encountered error while releasing, ignoring", e9);
        }
    }
}
