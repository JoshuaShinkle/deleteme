package org.apache.commons.p159io.filefilter;

import java.io.File;

/* loaded from: classes.dex */
public class SizeFileFilter extends AbstractFileFilter {
    private boolean acceptLarger;
    private long size;

    public SizeFileFilter(long j9) {
        this(j9, true);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        boolean z8 = file.length() < this.size;
        return this.acceptLarger ? !z8 : z8;
    }

    public SizeFileFilter(long j9, boolean z8) {
        if (j9 < 0) {
            throw new IllegalArgumentException("The size must be non-negative");
        }
        this.size = j9;
        this.acceptLarger = z8;
    }
}
