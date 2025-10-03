package org.apache.commons.p159io.filefilter;

import java.util.List;

/* loaded from: classes.dex */
public interface ConditionalFileFilter {
    void addFileFilter(IOFileFilter iOFileFilter);

    List getFileFilters();

    boolean removeFileFilter(IOFileFilter iOFileFilter);

    void setFileFilters(List list);
}
