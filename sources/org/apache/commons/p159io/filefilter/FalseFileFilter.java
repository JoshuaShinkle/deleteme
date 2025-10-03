package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class FalseFileFilter implements IOFileFilter {
    public static final IOFileFilter FALSE;
    public static final IOFileFilter INSTANCE;

    static {
        FalseFileFilter falseFileFilter = new FalseFileFilter();
        FALSE = falseFileFilter;
        INSTANCE = falseFileFilter;
    }

    @Override // org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return false;
    }

    @Override // org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return false;
    }
}
