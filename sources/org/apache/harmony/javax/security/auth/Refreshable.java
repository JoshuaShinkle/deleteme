package org.apache.harmony.javax.security.auth;

/* loaded from: classes.dex */
public interface Refreshable {
    boolean isCurrent();

    void refresh();
}
