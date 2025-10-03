package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
public class PrefixFileFilter extends AbstractFileFilter {
    private String[] prefixes;

    public PrefixFileFilter(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The prefix must not be null");
        }
        this.prefixes = new String[]{str};
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        int i9 = 0;
        while (true) {
            String[] strArr = this.prefixes;
            if (i9 >= strArr.length) {
                return false;
            }
            if (name.startsWith(strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public PrefixFileFilter(String[] strArr) {
        if (strArr != null) {
            this.prefixes = strArr;
            return;
        }
        throw new IllegalArgumentException("The array of prefixes must not be null");
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        int i9 = 0;
        while (true) {
            String[] strArr = this.prefixes;
            if (i9 >= strArr.length) {
                return false;
            }
            if (str.startsWith(strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public PrefixFileFilter(List list) {
        if (list != null) {
            this.prefixes = (String[]) list.toArray(new String[list.size()]);
            return;
        }
        throw new IllegalArgumentException("The list of prefixes must not be null");
    }
}
