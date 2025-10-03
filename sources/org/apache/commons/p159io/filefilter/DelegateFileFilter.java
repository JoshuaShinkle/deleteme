package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
public class DelegateFileFilter extends AbstractFileFilter {
    private FileFilter fileFilter;
    private FilenameFilter filenameFilter;

    public DelegateFileFilter(FilenameFilter filenameFilter) {
        if (filenameFilter == null) {
            throw new IllegalArgumentException("The FilenameFilter must not be null");
        }
        this.filenameFilter = filenameFilter;
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        FileFilter fileFilter = this.fileFilter;
        return fileFilter != null ? fileFilter.accept(file) : super.accept(file);
    }

    public DelegateFileFilter(FileFilter fileFilter) {
        if (fileFilter != null) {
            this.fileFilter = fileFilter;
            return;
        }
        throw new IllegalArgumentException("The FileFilter must not be null");
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        FilenameFilter filenameFilter = this.filenameFilter;
        if (filenameFilter != null) {
            return filenameFilter.accept(file, str);
        }
        return super.accept(file, str);
    }
}
