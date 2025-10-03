package org.apache.commons.p159io;

import java.io.File;

/* loaded from: classes.dex */
public class FileCleaner {
    static final FileCleaningTracker theInstance = new FileCleaningTracker();

    public static synchronized void exitWhenFinished() {
        theInstance.exitWhenFinished();
    }

    public static FileCleaningTracker getInstance() {
        return theInstance;
    }

    public static int getTrackCount() {
        return theInstance.getTrackCount();
    }

    public static void track(File file, Object obj) {
        theInstance.track(file, obj);
    }

    public static void track(File file, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        theInstance.track(file, obj, fileDeleteStrategy);
    }

    public static void track(String str, Object obj) {
        theInstance.track(str, obj);
    }

    public static void track(String str, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        theInstance.track(str, obj, fileDeleteStrategy);
    }
}
