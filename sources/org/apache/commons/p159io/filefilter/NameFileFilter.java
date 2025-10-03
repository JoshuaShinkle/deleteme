package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.p159io.IOCase;

/* loaded from: classes.dex */
public class NameFileFilter extends AbstractFileFilter {
    private IOCase caseSensitivity;
    private String[] names;

    public NameFileFilter(String str) {
        this(str, (IOCase) null);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        int i9 = 0;
        while (true) {
            String[] strArr = this.names;
            if (i9 >= strArr.length) {
                return false;
            }
            if (this.caseSensitivity.checkEquals(name, strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public NameFileFilter(String str, IOCase iOCase) {
        if (str == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.names = new String[]{str};
        this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        int i9 = 0;
        while (true) {
            String[] strArr = this.names;
            if (i9 >= strArr.length) {
                return false;
            }
            if (this.caseSensitivity.checkEquals(str, strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public NameFileFilter(String[] strArr) {
        this(strArr, (IOCase) null);
    }

    public NameFileFilter(String[] strArr, IOCase iOCase) {
        if (strArr != null) {
            this.names = strArr;
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The array of names must not be null");
    }

    public NameFileFilter(List list) {
        this(list, (IOCase) null);
    }

    public NameFileFilter(List list, IOCase iOCase) {
        if (list != null) {
            this.names = (String[]) list.toArray(new String[list.size()]);
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The list of names must not be null");
    }
}
