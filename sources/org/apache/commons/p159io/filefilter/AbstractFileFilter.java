package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public abstract class AbstractFileFilter implements IOFileFilter {
    @Override // org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return accept(file.getParentFile(), file.getName());
    }

    @Override // org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return accept(new File(file, str));
    }
}
