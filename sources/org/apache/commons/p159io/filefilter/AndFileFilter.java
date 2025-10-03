package org.apache.commons.p159io.filefilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AndFileFilter extends AbstractFileFilter implements ConditionalFileFilter {
    private List fileFilters;

    public AndFileFilter() {
        this.fileFilters = new ArrayList();
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (this.fileFilters.size() == 0) {
            return false;
        }
        Iterator it = this.fileFilters.iterator();
        while (it.hasNext()) {
            if (!((IOFileFilter) it.next()).accept(file)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.p159io.filefilter.ConditionalFileFilter
    public void addFileFilter(IOFileFilter iOFileFilter) {
        this.fileFilters.add(iOFileFilter);
    }

    @Override // org.apache.commons.p159io.filefilter.ConditionalFileFilter
    public List getFileFilters() {
        return Collections.unmodifiableList(this.fileFilters);
    }

    @Override // org.apache.commons.p159io.filefilter.ConditionalFileFilter
    public boolean removeFileFilter(IOFileFilter iOFileFilter) {
        return this.fileFilters.remove(iOFileFilter);
    }

    @Override // org.apache.commons.p159io.filefilter.ConditionalFileFilter
    public void setFileFilters(List list) {
        this.fileFilters = new ArrayList(list);
    }

    public AndFileFilter(List list) {
        if (list == null) {
            this.fileFilters = new ArrayList();
        } else {
            this.fileFilters = new ArrayList(list);
        }
    }

    @Override // org.apache.commons.p159io.filefilter.AbstractFileFilter, org.apache.commons.p159io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        if (this.fileFilters.size() == 0) {
            return false;
        }
        Iterator it = this.fileFilters.iterator();
        while (it.hasNext()) {
            if (!((IOFileFilter) it.next()).accept(file, str)) {
                return false;
            }
        }
        return true;
    }

    public AndFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        if (iOFileFilter != null && iOFileFilter2 != null) {
            this.fileFilters = new ArrayList();
            addFileFilter(iOFileFilter);
            addFileFilter(iOFileFilter2);
            return;
        }
        throw new IllegalArgumentException("The filters must not be null");
    }
}
