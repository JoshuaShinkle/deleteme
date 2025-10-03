package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class CanWriteFileFilter extends AbstractFileFilter {
    public static final IOFileFilter CANNOT_WRITE;
    public static final IOFileFilter CAN_WRITE;

    static {
        CanWriteFileFilter canWriteFileFilter = new CanWriteFileFilter();
        CAN_WRITE = canWriteFileFilter;
        CANNOT_WRITE = new NotFileFilter(canWriteFileFilter);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.canWrite();
    }
}
