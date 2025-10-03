package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class TrueFileFilter implements IOFileFilter {
    public static final IOFileFilter INSTANCE;
    public static final IOFileFilter TRUE;

    static {
        TrueFileFilter trueFileFilter = new TrueFileFilter();
        TRUE = trueFileFilter;
        INSTANCE = trueFileFilter;
    }

    @Override // org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return true;
    }

    @Override // org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return true;
    }
}
