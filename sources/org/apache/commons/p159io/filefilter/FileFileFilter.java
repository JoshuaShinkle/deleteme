package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class FileFileFilter extends AbstractFileFilter {
    public static final IOFileFilter FILE = new FileFileFilter();

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isFile();
    }
}
