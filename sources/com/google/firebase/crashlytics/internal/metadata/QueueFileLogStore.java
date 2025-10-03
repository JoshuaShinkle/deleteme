package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.metadata.QueueFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
class QueueFileLogStore implements FileLogStore {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    public static class LogBytes {
        public final byte[] bytes;
        public final int offset;

        public LogBytes(byte[] bArr, int i9) {
            this.bytes = bArr;
            this.offset = i9;
        }
    }

    public QueueFileLogStore(File file, int i9) {
        this.workingFile = file;
        this.maxLogSize = i9;
    }

    private void doWriteToLog(long j9, String str) {
        if (this.logFile == null) {
            return;
        }
        if (str == null) {
            str = "null";
        }
        try {
            int i9 = this.maxLogSize / 4;
            if (str.length() > i9) {
                str = "..." + str.substring(str.length() - i9);
            }
            this.logFile.add(String.format(Locale.US, "%d %s%n", Long.valueOf(j9), str.replaceAll(StringUtils.f19107CR, StringUtils.SPACE).replaceAll("\n", StringUtils.SPACE)).getBytes(UTF_8));
            while (!this.logFile.isEmpty() && this.logFile.usedBytes() > this.maxLogSize) {
                this.logFile.remove();
            }
        } catch (IOException e9) {
            Logger.getLogger().m17770e("There was a problem writing to the Crashlytics log.", e9);
        }
    }

    private LogBytes getLogBytes() {
        if (!this.workingFile.exists()) {
            return null;
        }
        openLogFile();
        QueueFile queueFile = this.logFile;
        if (queueFile == null) {
            return null;
        }
        final int[] iArr = {0};
        final byte[] bArr = new byte[queueFile.usedBytes()];
        try {
            this.logFile.forEach(new QueueFile.ElementReader() { // from class: com.google.firebase.crashlytics.internal.metadata.QueueFileLogStore.1
                @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
                public void read(InputStream inputStream, int i9) throws IOException {
                    try {
                        inputStream.read(bArr, iArr[0], i9);
                        int[] iArr2 = iArr;
                        iArr2[0] = iArr2[0] + i9;
                    } finally {
                        inputStream.close();
                    }
                }
            });
        } catch (IOException e9) {
            Logger.getLogger().m17770e("A problem occurred while reading the Crashlytics log file.", e9);
        }
        return new LogBytes(bArr, iArr[0]);
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e9) {
                Logger.getLogger().m17770e("Could not open log file: " + this.workingFile, e9);
            }
        }
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void closeLogFile() throws IOException {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void deleteLogFile() throws IOException {
        closeLogFile();
        this.workingFile.delete();
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        int i9 = logBytes.offset;
        byte[] bArr = new byte[i9];
        System.arraycopy(logBytes.bytes, 0, bArr, 0, i9);
        return bArr;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public String getLogAsString() {
        byte[] logAsBytes = getLogAsBytes();
        if (logAsBytes != null) {
            return new String(logAsBytes, UTF_8);
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void writeToLog(long j9, String str) {
        openLogFile();
        doWriteToLog(j9, str);
    }
}
