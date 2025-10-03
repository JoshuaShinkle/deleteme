package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.List;
import org.apache.commons.p159io.FilenameUtils;

/* loaded from: classes.dex */
public class WildcardFilter extends AbstractFileFilter {
    private String[] wildcards;

    public WildcardFilter(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.wildcards = new String[]{str};
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        if (file != null && new File(file, str).isDirectory()) {
            return false;
        }
        int i9 = 0;
        while (true) {
            String[] strArr = this.wildcards;
            if (i9 >= strArr.length) {
                return false;
            }
            if (FilenameUtils.wildcardMatch(str, strArr[i9])) {
                return true;
            }
            i9++;
        }
    }

    public WildcardFilter(String[] strArr) {
        if (strArr != null) {
            this.wildcards = strArr;
            return;
        }
        throw new IllegalArgumentException("The wildcard array must not be null");
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return false;
        }
        for (int i9 = 0; i9 < this.wildcards.length; i9++) {
            if (FilenameUtils.wildcardMatch(file.getName(), this.wildcards[i9])) {
                return true;
            }
        }
        return false;
    }

    public WildcardFilter(List list) {
        if (list != null) {
            this.wildcards = (String[]) list.toArray(new String[list.size()]);
            return;
        }
        throw new IllegalArgumentException("The wildcard list must not be null");
    }
}
