package org.apache.commons.p159io;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collection;
import java.util.Vector;

/* loaded from: classes.dex */
public class FileCleaningTracker {
    Thread reaper;

    /* renamed from: q */
    ReferenceQueue f19103q = new ReferenceQueue();
    final Collection trackers = new Vector();
    volatile boolean exitWhenFinished = false;

    public final class Reaper extends Thread {
        private final /* synthetic */ FileCleaningTracker this$0;

        public Reaper(FileCleaningTracker fileCleaningTracker) {
            super("File Reaper");
            this.this$0 = fileCleaningTracker;
            setPriority(10);
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                if (this.this$0.exitWhenFinished && this.this$0.trackers.size() <= 0) {
                    return;
                }
                try {
                    Tracker tracker = (Tracker) this.this$0.f19103q.remove();
                    if (tracker != null) {
                        tracker.delete();
                        tracker.clear();
                        this.this$0.trackers.remove(tracker);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public static final class Tracker extends PhantomReference {
        private final FileDeleteStrategy deleteStrategy;
        private final String path;

        public Tracker(String str, FileDeleteStrategy fileDeleteStrategy, Object obj, ReferenceQueue referenceQueue) {
            super(obj, referenceQueue);
            this.path = str;
            this.deleteStrategy = fileDeleteStrategy == null ? FileDeleteStrategy.NORMAL : fileDeleteStrategy;
        }

        public boolean delete() {
            return this.deleteStrategy.deleteQuietly(new File(this.path));
        }
    }

    private synchronized void addTracker(String str, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        if (this.exitWhenFinished) {
            throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
        }
        if (this.reaper == null) {
            Reaper reaper = new Reaper(this);
            this.reaper = reaper;
            reaper.start();
        }
        this.trackers.add(new Tracker(str, fileDeleteStrategy, obj, this.f19103q));
    }

    public synchronized void exitWhenFinished() {
        this.exitWhenFinished = true;
        Thread thread = this.reaper;
        if (thread != null) {
            synchronized (thread) {
                this.reaper.interrupt();
            }
        }
    }

    public int getTrackCount() {
        return this.trackers.size();
    }

    public void track(File file, Object obj) {
        track(file, obj, (FileDeleteStrategy) null);
    }

    public void track(File file, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        if (file == null) {
            throw new NullPointerException("The file must not be null");
        }
        addTracker(file.getPath(), obj, fileDeleteStrategy);
    }

    public void track(String str, Object obj) {
        track(str, obj, (FileDeleteStrategy) null);
    }

    public void track(String str, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        if (str != null) {
            addTracker(str, obj, fileDeleteStrategy);
            return;
        }
        throw new NullPointerException("The path must not be null");
    }
}
