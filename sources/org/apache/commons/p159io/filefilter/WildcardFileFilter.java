package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.p159io.FilenameUtils;
import org.apache.commons.p159io.IOCase;

/* loaded from: classes.dex */
public class WildcardFileFilter extends AbstractFileFilter {
    private IOCase caseSensitivity;
    private String[] wildcards;

    public WildcardFileFilter(String str) {
        this(str, (IOCase) null);
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        int i9 = 0;
        while (true) {
            String[] strArr = this.wildcards;
            if (i9 >= strArr.length) {
                return false;
            }
            if (FilenameUtils.wildcardMatch(str, strArr[i9], this.caseSensitivity)) {
                return true;
            }
            i9++;
        }
    }

    public WildcardFileFilter(String str, IOCase iOCase) {
        if (str == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.wildcards = new String[]{str};
        this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        int i9 = 0;
        while (true) {
            String[] strArr = this.wildcards;
            if (i9 >= strArr.length) {
                return false;
            }
            if (FilenameUtils.wildcardMatch(name, strArr[i9], this.caseSensitivity)) {
                return true;
            }
            i9++;
        }
    }

    public WildcardFileFilter(String[] strArr) {
        this(strArr, (IOCase) null);
    }

    public WildcardFileFilter(String[] strArr, IOCase iOCase) {
        if (strArr != null) {
            this.wildcards = strArr;
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The wildcard array must not be null");
    }

    public WildcardFileFilter(List list) {
        this(list, (IOCase) null);
    }

    public WildcardFileFilter(List list, IOCase iOCase) {
        if (list != null) {
            this.wildcards = (String[]) list.toArray(new String[list.size()]);
            this.caseSensitivity = iOCase == null ? IOCase.SENSITIVE : iOCase;
            return;
        }
        throw new IllegalArgumentException("The wildcard list must not be null");
    }
}
