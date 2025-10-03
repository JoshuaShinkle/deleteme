package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class CanReadFileFilter extends AbstractFileFilter {
    public static final IOFileFilter CANNOT_READ;
    public static final IOFileFilter CAN_READ;
    public static final IOFileFilter READ_ONLY;

    static {
        CanReadFileFilter canReadFileFilter = new CanReadFileFilter();
        CAN_READ = canReadFileFilter;
        CANNOT_READ = new NotFileFilter(canReadFileFilter);
        READ_ONLY = new AndFileFilter(canReadFileFilter, CanWriteFileFilter.CANNOT_WRITE);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.canRead();
    }
}
