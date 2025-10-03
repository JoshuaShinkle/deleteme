package com.cyberlink.uno.log;

import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p191s2.C6253a;
import p200t2.ThreadFactoryC6285a;

/* loaded from: classes.dex */
public class UNOFileLog {

    /* renamed from: a */
    public static final String f7160a = "UNOFileLog";

    /* renamed from: b */
    public static BufferedWriter f7161b = null;

    /* renamed from: c */
    public static boolean f7162c = false;

    /* renamed from: d */
    public static ExecutorService f7163d;

    public enum LogType {
        APPEVENT,
        EXCEPTIONEVENT,
        SUCCESSEVENT,
        FAILUREEVENT,
        EVENTQUEUE,
        CONNECTIONQUEUE,
        INFORMATION,
        MESSAGE
    }

    /* renamed from: b */
    public static String m7205b() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US).format(new Date());
    }

    /* renamed from: c */
    public static void m7206c() {
        if (f7161b == null || f7163d.isShutdown()) {
            return;
        }
        f7163d.shutdown();
        try {
            if (!f7163d.awaitTermination(2L, TimeUnit.SECONDS)) {
                f7161b.write("logType:" + LogType.MESSAGE + "  time:" + m7205b() + "  message:some events do not write to log file yet");
                f7161b.newLine();
                f7163d.shutdownNow();
            }
        } catch (IOException | InterruptedException unused) {
            f7163d.shutdownNow();
        }
        try {
            f7161b.close();
            f7161b = null;
            f7162c = false;
        } catch (IOException e9) {
            Log.e(f7160a, "Cannot close logWriter", e9);
        }
    }

    /* renamed from: d */
    public static File m7207d(File file) {
        String str = m7205b() + "_log.txt";
        File file2 = new File(file, str);
        Log.v(f7160a, "generateFile:" + str);
        return file2;
    }

    /* renamed from: e */
    public static boolean m7208e() {
        return f7162c;
    }

    /* renamed from: f */
    public static /* synthetic */ void m7209f(LogType logType, String str, Exception exc) throws JSONException {
        try {
            m7213j(logType, str, exc);
        } catch (IOException e9) {
            Log.e(f7160a, "Cannot log message", e9);
        }
    }

    /* renamed from: g */
    public static void m7210g(String str) {
        if (f7162c) {
            throw new IllegalStateException("You are already open file now, please close it at first");
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        } else if (file.isFile()) {
            throw new IllegalStateException("It is not a folder");
        }
        File fileM7207d = m7207d(file);
        try {
            f7163d = Executors.newSingleThreadExecutor(new ThreadFactoryC6285a("UNOLogger"));
            f7161b = new BufferedWriter(new FileWriter(fileM7207d, false), 2048);
            f7162c = true;
            C6253a.m23961g();
        } catch (IOException unused) {
            throw new IllegalStateException("Create a log file failed");
        }
    }

    /* renamed from: h */
    public static JSONArray m7211h(String str) {
        return new JSONObject(str).getJSONArray("event");
    }

    /* renamed from: i */
    public static void m7212i(LogType logType, String str, Exception exc) throws IOException {
        f7161b.write("logType:" + logType + "  time:" + m7205b() + "  message:" + str);
        f7161b.newLine();
        if (exc != null) {
            f7161b.write(exc.getMessage());
        }
        f7161b.newLine();
    }

    /* renamed from: j */
    public static void m7213j(LogType logType, String str, Exception exc) throws JSONException, IOException {
        try {
            int i9 = 0;
            if (Arrays.asList(LogType.SUCCESSEVENT, LogType.FAILUREEVENT).contains(logType)) {
                JSONArray jSONArrayM7211h = m7211h(str);
                if (jSONArrayM7211h == null || jSONArrayM7211h.length() <= 0) {
                    f7161b.write("logType:" + logType.name() + "  message:NoMessage");
                    f7161b.newLine();
                } else {
                    f7161b.write("logType:" + logType.name() + "  time:" + m7205b());
                    f7161b.newLine();
                    while (i9 < jSONArrayM7211h.length()) {
                        f7161b.write("message:" + jSONArrayM7211h.get(i9));
                        f7161b.newLine();
                        i9++;
                    }
                }
            } else if (Arrays.asList(LogType.APPEVENT, LogType.MESSAGE).contains(logType)) {
                f7161b.write("logType:" + logType + "  time:" + m7205b() + "  message:" + str);
                f7161b.newLine();
            } else if (LogType.INFORMATION == logType) {
                String[] strArrSplit = str.split(",");
                int length = strArrSplit.length;
                while (i9 < length) {
                    String str2 = strArrSplit[i9];
                    f7161b.write("logType:" + logType + "  time:" + m7205b() + "  message:" + str2);
                    f7161b.newLine();
                    i9++;
                }
            } else if (LogType.EXCEPTIONEVENT == logType) {
                m7212i(logType, str, exc);
            } else {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("event");
                if (jSONArray.length() == 0) {
                    f7161b.write("logType:" + logType.name() + "  message:NoMessage");
                    f7161b.newLine();
                } else {
                    f7161b.write("logType:" + logType.name() + "  time:" + m7205b());
                    f7161b.newLine();
                    while (i9 < jSONArray.length()) {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i9);
                        f7161b.write("message:" + jSONObject.toString());
                        f7161b.newLine();
                        i9++;
                    }
                }
            }
            f7161b.write("=================================================================================");
            f7161b.newLine();
            f7161b.flush();
        } catch (JSONException e9) {
            m7212i(logType, str, e9);
            f7161b.write("=================================================================================");
            f7161b.newLine();
            f7161b.flush();
        }
    }

    /* renamed from: k */
    public static void m7214k(LogType logType, String str) {
        m7216m(logType, str, null, false);
    }

    /* renamed from: l */
    public static void m7215l(LogType logType, String str, Exception exc) {
        m7216m(logType, str, exc, false);
    }

    /* renamed from: m */
    public static void m7216m(final LogType logType, final String str, final Exception exc, boolean z8) throws ExecutionException, InterruptedException {
        if (f7162c) {
            Future<?> futureSubmit = f7163d.submit(new Runnable() { // from class: t2.b
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    UNOFileLog.m7209f(logType, str, exc);
                }
            });
            if (z8) {
                try {
                    futureSubmit.get();
                } catch (InterruptedException | ExecutionException e9) {
                    Log.e(f7160a, "Cannot log wait for task execute finish ", e9);
                }
            }
        }
    }

    /* renamed from: n */
    public static void m7217n(LogType logType, String str, boolean z8) throws ExecutionException, InterruptedException {
        m7216m(logType, str, null, z8);
    }
}
