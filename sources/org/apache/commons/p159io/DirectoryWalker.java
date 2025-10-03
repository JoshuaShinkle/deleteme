package org.apache.commons.p159io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.p159io.filefilter.FileFilterUtils;
import org.apache.commons.p159io.filefilter.IOFileFilter;
import org.apache.commons.p159io.filefilter.TrueFileFilter;

/* loaded from: classes.dex */
public abstract class DirectoryWalker {
    private final int depthLimit;
    private final FileFilter filter;

    public static class CancelException extends IOException {
        private static final long serialVersionUID = 1347339620135041008L;
        private int depth;
        private File file;

        public CancelException(File file, int i9) {
            this("Operation Cancelled", file, i9);
        }

        public int getDepth() {
            return this.depth;
        }

        public File getFile() {
            return this.file;
        }

        public CancelException(String str, File file, int i9) {
            super(str);
            this.file = file;
            this.depth = i9;
        }
    }

    public DirectoryWalker() {
        this(null, -1);
    }

    public final void checkIfCancelled(File file, int i9, Collection collection) throws CancelException {
        if (handleIsCancelled(file, i9, collection)) {
            throw new CancelException(file, i9);
        }
    }

    public void handleCancelled(File file, Collection collection, CancelException cancelException) throws CancelException {
        throw cancelException;
    }

    public boolean handleDirectory(File file, int i9, Collection collection) {
        return true;
    }

    public void handleDirectoryEnd(File file, int i9, Collection collection) {
    }

    public void handleDirectoryStart(File file, int i9, Collection collection) {
    }

    public void handleEnd(Collection collection) {
    }

    public void handleFile(File file, int i9, Collection collection) {
    }

    public boolean handleIsCancelled(File file, int i9, Collection collection) {
        return false;
    }

    public void handleRestricted(File file, int i9, Collection collection) {
    }

    public void handleStart(File file, Collection collection) {
    }

    public final void walk(File file, Collection collection) throws CancelException {
        if (file == null) {
            throw new NullPointerException("Start Directory is null");
        }
        try {
            handleStart(file, collection);
            walk(file, 0, collection);
            handleEnd(collection);
        } catch (CancelException e9) {
            handleCancelled(file, collection, e9);
        }
    }

    public DirectoryWalker(FileFilter fileFilter, int i9) {
        this.filter = fileFilter;
        this.depthLimit = i9;
    }

    public DirectoryWalker(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2, int i9) {
        if (iOFileFilter == null && iOFileFilter2 == null) {
            this.filter = null;
        } else {
            this.filter = FileFilterUtils.orFileFilter(FileFilterUtils.makeDirectoryOnly(iOFileFilter == null ? TrueFileFilter.TRUE : iOFileFilter), FileFilterUtils.makeFileOnly(iOFileFilter2 == null ? TrueFileFilter.TRUE : iOFileFilter2));
        }
        this.depthLimit = i9;
    }

    private void walk(File file, int i9, Collection collection) throws CancelException {
        checkIfCancelled(file, i9, collection);
        if (handleDirectory(file, i9, collection)) {
            handleDirectoryStart(file, i9, collection);
            int i10 = i9 + 1;
            int i11 = this.depthLimit;
            if (i11 < 0 || i10 <= i11) {
                checkIfCancelled(file, i9, collection);
                FileFilter fileFilter = this.filter;
                File[] fileArrListFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
                if (fileArrListFiles == null) {
                    handleRestricted(file, i10, collection);
                } else {
                    for (File file2 : fileArrListFiles) {
                        if (file2.isDirectory()) {
                            walk(file2, i10, collection);
                        } else {
                            checkIfCancelled(file2, i10, collection);
                            handleFile(file2, i10, collection);
                            checkIfCancelled(file2, i10, collection);
                        }
                    }
                }
            }
            handleDirectoryEnd(file, i9, collection);
        }
        checkIfCancelled(file, i9, collection);
    }
}
