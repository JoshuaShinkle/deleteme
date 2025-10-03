package org.apache.commons.p159io;

import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class FileDeleteStrategy {
    private final String name;
    public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy("Normal");
    public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();

    public static class ForceFileDeleteStrategy extends FileDeleteStrategy {
        public ForceFileDeleteStrategy() {
            super("Force");
        }

        @Override // org.apache.commons.p159io.FileDeleteStrategy
        public boolean doDelete(File file) throws IOException {
            FileUtils.forceDelete(file);
            return true;
        }
    }

    public FileDeleteStrategy(String str) {
        this.name = str;
    }

    public void delete(File file) throws IOException {
        if (!file.exists() || doDelete(file)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Deletion failed: ");
        stringBuffer.append(file);
        throw new IOException(stringBuffer.toString());
    }

    public boolean deleteQuietly(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        try {
            return doDelete(file);
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean doDelete(File file) {
        return file.delete();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FileDeleteStrategy[");
        stringBuffer.append(this.name);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
