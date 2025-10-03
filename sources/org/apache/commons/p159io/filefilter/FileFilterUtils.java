package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;

/* loaded from: classes.dex */
public class FileFilterUtils {
    private static IOFileFilter cvsFilter;
    private static IOFileFilter svnFilter;

    public static IOFileFilter ageFileFilter(long j9) {
        return new AgeFileFilter(j9);
    }

    public static IOFileFilter andFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return new AndFileFilter(iOFileFilter, iOFileFilter2);
    }

    public static IOFileFilter asFileFilter(FileFilter fileFilter) {
        return new DelegateFileFilter(fileFilter);
    }

    public static IOFileFilter directoryFileFilter() {
        return DirectoryFileFilter.DIRECTORY;
    }

    public static IOFileFilter falseFileFilter() {
        return FalseFileFilter.FALSE;
    }

    public static IOFileFilter fileFileFilter() {
        return FileFileFilter.FILE;
    }

    public static IOFileFilter makeCVSAware(IOFileFilter iOFileFilter) {
        if (cvsFilter == null) {
            cvsFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter("CVS")));
        }
        return iOFileFilter == null ? cvsFilter : andFileFilter(iOFileFilter, cvsFilter);
    }

    public static IOFileFilter makeDirectoryOnly(IOFileFilter iOFileFilter) {
        return iOFileFilter == null ? DirectoryFileFilter.DIRECTORY : new AndFileFilter(DirectoryFileFilter.DIRECTORY, iOFileFilter);
    }

    public static IOFileFilter makeFileOnly(IOFileFilter iOFileFilter) {
        return iOFileFilter == null ? FileFileFilter.FILE : new AndFileFilter(FileFileFilter.FILE, iOFileFilter);
    }

    public static IOFileFilter makeSVNAware(IOFileFilter iOFileFilter) {
        if (svnFilter == null) {
            svnFilter = notFileFilter(andFileFilter(directoryFileFilter(), nameFileFilter(".svn")));
        }
        return iOFileFilter == null ? svnFilter : andFileFilter(iOFileFilter, svnFilter);
    }

    public static IOFileFilter nameFileFilter(String str) {
        return new NameFileFilter(str);
    }

    public static IOFileFilter notFileFilter(IOFileFilter iOFileFilter) {
        return new NotFileFilter(iOFileFilter);
    }

    public static IOFileFilter orFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return new OrFileFilter(iOFileFilter, iOFileFilter2);
    }

    public static IOFileFilter prefixFileFilter(String str) {
        return new PrefixFileFilter(str);
    }

    public static IOFileFilter sizeFileFilter(long j9) {
        return new SizeFileFilter(j9);
    }

    public static IOFileFilter sizeRangeFileFilter(long j9, long j10) {
        return new AndFileFilter(new SizeFileFilter(j9, true), new SizeFileFilter(j10 + 1, false));
    }

    public static IOFileFilter suffixFileFilter(String str) {
        return new SuffixFileFilter(str);
    }

    public static IOFileFilter trueFileFilter() {
        return TrueFileFilter.TRUE;
    }

    public static IOFileFilter ageFileFilter(long j9, boolean z8) {
        return new AgeFileFilter(j9, z8);
    }

    public static IOFileFilter asFileFilter(FilenameFilter filenameFilter) {
        return new DelegateFileFilter(filenameFilter);
    }

    public static IOFileFilter sizeFileFilter(long j9, boolean z8) {
        return new SizeFileFilter(j9, z8);
    }

    public static IOFileFilter ageFileFilter(Date date) {
        return new AgeFileFilter(date);
    }

    public static IOFileFilter ageFileFilter(Date date, boolean z8) {
        return new AgeFileFilter(date, z8);
    }

    public static IOFileFilter ageFileFilter(File file) {
        return new AgeFileFilter(file);
    }

    public static IOFileFilter ageFileFilter(File file, boolean z8) {
        return new AgeFileFilter(file, z8);
    }
}
