package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class DirectoryFileFilter extends AbstractFileFilter {
    public static final IOFileFilter DIRECTORY;
    public static final IOFileFilter INSTANCE;

    static {
        DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();
        DIRECTORY = directoryFileFilter;
        INSTANCE = directoryFileFilter;
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
