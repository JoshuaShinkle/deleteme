package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
public class SuffixFileFilter extends AbstractFileFilter {
    private String[] suffixes;

    public SuffixFileFilter(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The suffix must not be null");
        }
        this.suffixes = new String[]{str};
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        int i9 = 0;
        while (true) {
            String[] strArr = this.suffixes;
            if (i9 >= strArr.length) {
                return false;
            }
            if (name.endsWith(strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public SuffixFileFilter(String[] strArr) {
        if (strArr != null) {
            this.suffixes = strArr;
            return;
        }
        throw new IllegalArgumentException("The array of suffixes must not be null");
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        int i9 = 0;
        while (true) {
            String[] strArr = this.suffixes;
            if (i9 >= strArr.length) {
                return false;
            }
            if (str.endsWith(strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public SuffixFileFilter(List list) {
        if (list != null) {
            this.suffixes = (String[]) list.toArray(new String[list.size()]);
            return;
        }
        throw new IllegalArgumentException("The list of suffixes must not be null");
    }
}
