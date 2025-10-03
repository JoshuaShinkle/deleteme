package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class HiddenFileFilter extends AbstractFileFilter {
    public static final IOFileFilter HIDDEN;
    public static final IOFileFilter VISIBLE;

    static {
        HiddenFileFilter hiddenFileFilter = new HiddenFileFilter();
        HIDDEN = hiddenFileFilter;
        VISIBLE = new NotFileFilter(hiddenFileFilter);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isHidden();
    }
}
