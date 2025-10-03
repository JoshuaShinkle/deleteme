package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.Date;
import org.apache.commons.p159io.FileUtils;

/* loaded from: classes.dex */
public class AgeFileFilter extends AbstractFileFilter {
    private boolean acceptOlder;
    private long cutoff;

    public AgeFileFilter(long j9) {
        this(j9, true);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        boolean zIsFileNewer = FileUtils.isFileNewer(file, this.cutoff);
        return this.acceptOlder ? !zIsFileNewer : zIsFileNewer;
    }

    public AgeFileFilter(long j9, boolean z8) {
        this.acceptOlder = z8;
        this.cutoff = j9;
    }

    public AgeFileFilter(Date date) {
        this(date, true);
    }

    public AgeFileFilter(Date date, boolean z8) {
        this(date.getTime(), z8);
    }

    public AgeFileFilter(File file) {
        this(file, true);
    }

    public AgeFileFilter(File file, boolean z8) {
        this(file.lastModified(), z8);
    }
}
