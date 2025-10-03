package kotlin.p121io;

import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class FileSystemException extends IOException {
    private final File file;
    private final File other;
    private final String reason;
}
