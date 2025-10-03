package com.google.firebase.crashlytics.internal.persistence;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class CrashlyticsReportPersistence {
    private static final String EVENT_COUNTER_FORMAT = "%010d";
    private static final int EVENT_COUNTER_WIDTH = 10;
    private static final String EVENT_FILE_NAME_PREFIX = "event";
    private static final int MAX_OPEN_SESSIONS = 8;
    private static final String NORMAL_EVENT_SUFFIX = "";
    private static final String PRIORITY_EVENT_SUFFIX = "_";
    private static final String REPORT_FILE_NAME = "report";
    private static final String SESSION_START_TIMESTAMP_FILE_NAME = "start-time";
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    private final SettingsProvider settingsProvider;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final int EVENT_NAME_LENGTH = 15;
    private static final CrashlyticsReportJsonTransform TRANSFORM = new CrashlyticsReportJsonTransform();
    private static final Comparator<? super File> LATEST_SESSION_ID_FIRST_COMPARATOR = new Comparator() { // from class: v4.c
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CrashlyticsReportPersistence.lambda$static$0((File) obj, (File) obj2);
        }
    };
    private static final FilenameFilter EVENT_FILE_FILTER = new FilenameFilter() { // from class: v4.d
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return CrashlyticsReportPersistence.lambda$static$1(file, str);
        }
    };

    public CrashlyticsReportPersistence(FileStore fileStore, SettingsProvider settingsProvider) {
        this.fileStore = fileStore;
        this.settingsProvider = settingsProvider;
    }

    private SortedSet<String> capAndGetOpenSessions(String str) {
        this.fileStore.cleanupPreviousFileSystems();
        SortedSet<String> openSessionIds = getOpenSessionIds();
        if (str != null) {
            openSessionIds.remove(str);
        }
        if (openSessionIds.size() <= 8) {
            return openSessionIds;
        }
        while (openSessionIds.size() > 8) {
            String strLast = openSessionIds.last();
            Logger.getLogger().m17767d("Removing session over cap: " + strLast);
            this.fileStore.deleteSessionFiles(strLast);
            openSessionIds.remove(strLast);
        }
        return openSessionIds;
    }

    private static int capFilesCount(List<File> list, int i9) {
        int size = list.size();
        for (File file : list) {
            if (size <= i9) {
                return size;
            }
            FileStore.recursiveDelete(file);
            size--;
        }
        return size;
    }

    private void capFinalizedReports() {
        int i9 = this.settingsProvider.getSettingsSync().sessionData.maxCompleteSessionsCount;
        List<File> allFinalizedReportFiles = getAllFinalizedReportFiles();
        int size = allFinalizedReportFiles.size();
        if (size <= i9) {
            return;
        }
        Iterator<File> it = allFinalizedReportFiles.subList(i9, size).iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
    }

    private static long convertTimestampFromSecondsToMs(long j9) {
        return j9 * 1000;
    }

    private void deleteFiles(List<File> list) {
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
    }

    private static String generateEventFilename(int i9, boolean z8) {
        return "event" + String.format(Locale.US, EVENT_COUNTER_FORMAT, Integer.valueOf(i9)) + (z8 ? PRIORITY_EVENT_SUFFIX : "");
    }

    private List<File> getAllFinalizedReportFiles() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.fileStore.getPriorityReports());
        arrayList.addAll(this.fileStore.getNativeReports());
        Comparator<? super File> comparator = LATEST_SESSION_ID_FIRST_COMPARATOR;
        Collections.sort(arrayList, comparator);
        List<File> reports = this.fileStore.getReports();
        Collections.sort(reports, comparator);
        arrayList.addAll(reports);
        return arrayList;
    }

    private static String getEventNameWithoutPriority(String str) {
        return str.substring(0, EVENT_NAME_LENGTH);
    }

    private static boolean isHighPriorityEventFile(String str) {
        return str.startsWith("event") && str.endsWith(PRIORITY_EVENT_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNormalPriorityEventFile(File file, String str) {
        return str.startsWith("event") && !str.endsWith(PRIORITY_EVENT_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(File file, File file2) {
        return file2.getName().compareTo(file.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$1(File file, String str) {
        return str.startsWith("event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int oldestEventFileFirst(File file, File file2) {
        return getEventNameWithoutPriority(file.getName()).compareTo(getEventNameWithoutPriority(file2.getName()));
    }

    private static String readTextFile(File file) throws IOException {
        byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int i9 = fileInputStream.read(bArr);
                if (i9 <= 0) {
                    String str = new String(byteArrayOutputStream.toByteArray(), UTF_8);
                    fileInputStream.close();
                    return str;
                }
                byteArrayOutputStream.write(bArr, 0, i9);
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    private void synthesizeNativeReportFile(File file, CrashlyticsReport.FilesPayload filesPayload, String str, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        try {
            CrashlyticsReportJsonTransform crashlyticsReportJsonTransform = TRANSFORM;
            writeTextFile(this.fileStore.getNativeReport(str), crashlyticsReportJsonTransform.reportToJson(crashlyticsReportJsonTransform.reportFromJson(readTextFile(file)).withNdkPayload(filesPayload).withApplicationExitInfo(applicationExitInfo)));
        } catch (IOException e9) {
            Logger.getLogger().m17776w("Could not synthesize final native report file for " + file, e9);
        }
    }

    private void synthesizeReport(String str, long j9) {
        boolean z8;
        List<File> sessionFiles = this.fileStore.getSessionFiles(str, EVENT_FILE_FILTER);
        if (sessionFiles.isEmpty()) {
            Logger.getLogger().m17773v("Session " + str + " has no events.");
            return;
        }
        Collections.sort(sessionFiles);
        ArrayList arrayList = new ArrayList();
        loop0: while (true) {
            z8 = false;
            for (File file : sessionFiles) {
                try {
                    arrayList.add(TRANSFORM.eventFromJson(readTextFile(file)));
                } catch (IOException e9) {
                    Logger.getLogger().m17776w("Could not add event to report for " + file, e9);
                }
                if (z8 || isHighPriorityEventFile(file.getName())) {
                    z8 = true;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            synthesizeReportFile(this.fileStore.getSessionFile(str, REPORT_FILE_NAME), arrayList, j9, z8, UserMetadata.readUserId(str, this.fileStore));
            return;
        }
        Logger.getLogger().m17775w("Could not parse event files for session " + str);
    }

    private void synthesizeReportFile(File file, List<CrashlyticsReport.Session.Event> list, long j9, boolean z8, String str) {
        try {
            CrashlyticsReportJsonTransform crashlyticsReportJsonTransform = TRANSFORM;
            CrashlyticsReport crashlyticsReportWithEvents = crashlyticsReportJsonTransform.reportFromJson(readTextFile(file)).withSessionEndFields(j9, z8, str).withEvents(ImmutableList.from(list));
            CrashlyticsReport.Session session = crashlyticsReportWithEvents.getSession();
            if (session == null) {
                return;
            }
            writeTextFile(z8 ? this.fileStore.getPriorityReport(session.getIdentifier()) : this.fileStore.getReport(session.getIdentifier()), crashlyticsReportJsonTransform.reportToJson(crashlyticsReportWithEvents));
        } catch (IOException e9) {
            Logger.getLogger().m17776w("Could not synthesize final report file for " + file, e9);
        }
    }

    private int trimEvents(String str, int i9) {
        List<File> sessionFiles = this.fileStore.getSessionFiles(str, new FilenameFilter() { // from class: v4.a
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str2) {
                return CrashlyticsReportPersistence.isNormalPriorityEventFile(file, str2);
            }
        });
        Collections.sort(sessionFiles, new Comparator() { // from class: v4.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return CrashlyticsReportPersistence.oldestEventFileFirst((File) obj, (File) obj2);
            }
        });
        return capFilesCount(sessionFiles, i9);
    }

    private static void writeTextFile(File file, String str) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), UTF_8);
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void deleteAllReports() {
        deleteFiles(this.fileStore.getReports());
        deleteFiles(this.fileStore.getPriorityReports());
        deleteFiles(this.fileStore.getNativeReports());
    }

    public void finalizeReports(String str, long j9) {
        for (String str2 : capAndGetOpenSessions(str)) {
            Logger.getLogger().m17773v("Finalizing report for session " + str2);
            synthesizeReport(str2, j9);
            this.fileStore.deleteSessionFiles(str2);
        }
        capFinalizedReports();
    }

    public void finalizeSessionWithNativeEvent(String str, CrashlyticsReport.FilesPayload filesPayload, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        File sessionFile = this.fileStore.getSessionFile(str, REPORT_FILE_NAME);
        Logger.getLogger().m17767d("Writing native session report for " + str + " to file: " + sessionFile);
        synthesizeNativeReportFile(sessionFile, filesPayload, str, applicationExitInfo);
    }

    public SortedSet<String> getOpenSessionIds() {
        return new TreeSet(this.fileStore.getAllOpenSessionIds()).descendingSet();
    }

    public long getStartTimestampMillis(String str) {
        return this.fileStore.getSessionFile(str, SESSION_START_TIMESTAMP_FILE_NAME).lastModified();
    }

    public boolean hasFinalizedReports() {
        return (this.fileStore.getReports().isEmpty() && this.fileStore.getPriorityReports().isEmpty() && this.fileStore.getNativeReports().isEmpty()) ? false : true;
    }

    public List<CrashlyticsReportWithSessionId> loadFinalizedReports() {
        List<File> allFinalizedReportFiles = getAllFinalizedReportFiles();
        ArrayList arrayList = new ArrayList();
        for (File file : allFinalizedReportFiles) {
            try {
                arrayList.add(CrashlyticsReportWithSessionId.create(TRANSFORM.reportFromJson(readTextFile(file)), file.getName(), file));
            } catch (IOException e9) {
                Logger.getLogger().m17776w("Could not load report file " + file + "; deleting", e9);
                file.delete();
            }
        }
        return arrayList;
    }

    public void persistEvent(CrashlyticsReport.Session.Event event, String str) {
        persistEvent(event, str, false);
    }

    public void persistReport(CrashlyticsReport crashlyticsReport) {
        CrashlyticsReport.Session session = crashlyticsReport.getSession();
        if (session == null) {
            Logger.getLogger().m17767d("Could not get session for report");
            return;
        }
        String identifier = session.getIdentifier();
        try {
            writeTextFile(this.fileStore.getSessionFile(identifier, REPORT_FILE_NAME), TRANSFORM.reportToJson(crashlyticsReport));
            writeTextFile(this.fileStore.getSessionFile(identifier, SESSION_START_TIMESTAMP_FILE_NAME), "", session.getStartedAt());
        } catch (IOException e9) {
            Logger.getLogger().m17768d("Could not persist report for session " + identifier, e9);
        }
    }

    public void persistEvent(CrashlyticsReport.Session.Event event, String str, boolean z8) {
        int i9 = this.settingsProvider.getSettingsSync().sessionData.maxCustomExceptionEvents;
        try {
            writeTextFile(this.fileStore.getSessionFile(str, generateEventFilename(this.eventCounter.getAndIncrement(), z8)), TRANSFORM.eventToJson(event));
        } catch (IOException e9) {
            Logger.getLogger().m17776w("Could not persist event for session " + str, e9);
        }
        trimEvents(str, i9);
    }

    private static void writeTextFile(File file, String str, long j9) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), UTF_8);
        try {
            outputStreamWriter.write(str);
            file.setLastModified(convertTimestampFromSecondsToMs(j9));
            outputStreamWriter.close();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
