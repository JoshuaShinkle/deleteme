package androidx.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import p133m0.C5311b;

/* loaded from: classes.dex */
public final class MultiDexExtractor implements Closeable {

    /* renamed from: b */
    public final File f2360b;

    /* renamed from: c */
    public final long f2361c;

    /* renamed from: d */
    public final File f2362d;

    /* renamed from: e */
    public final RandomAccessFile f2363e;

    /* renamed from: f */
    public final FileChannel f2364f;

    /* renamed from: g */
    public final FileLock f2365g;

    public static class ExtractedDex extends File {
        public long crc;

        public ExtractedDex(File file, String str) {
            super(file, str);
            this.crc = -1L;
        }
    }

    /* renamed from: androidx.multidex.MultiDexExtractor$a */
    public class C0428a implements FileFilter {
        public C0428a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return !file.getName().equals("MultiDex.lock");
        }
    }

    public MultiDexExtractor(File file, File file2) throws Throwable {
        Log.i("MultiDex", "MultiDexExtractor(" + file.getPath() + ", " + file2.getPath() + ")");
        this.f2360b = file;
        this.f2362d = file2;
        this.f2361c = m2203y(file);
        File file3 = new File(file2, "MultiDex.lock");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
        this.f2363e = randomAccessFile;
        try {
            FileChannel channel = randomAccessFile.getChannel();
            this.f2364f = channel;
            try {
                Log.i("MultiDex", "Blocking on lock " + file3.getPath());
                this.f2365g = channel.lock();
                Log.i("MultiDex", file3.getPath() + " locked");
            } catch (IOException e9) {
                e = e9;
                m2199u(this.f2364f);
                throw e;
            } catch (Error e10) {
                e = e10;
                m2199u(this.f2364f);
                throw e;
            } catch (RuntimeException e11) {
                e = e11;
                m2199u(this.f2364f);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e12) {
            m2199u(this.f2363e);
            throw e12;
        }
    }

    /* renamed from: D */
    public static void m2198D(Context context, String str, long j9, long j10, List<ExtractedDex> list) {
        SharedPreferences.Editor editorEdit = m2201w(context).edit();
        editorEdit.putLong(str + "timestamp", j9);
        editorEdit.putLong(str + "crc", j10);
        editorEdit.putInt(str + "dex.number", list.size() + 1);
        int i9 = 2;
        for (ExtractedDex extractedDex : list) {
            editorEdit.putLong(str + "dex.crc." + i9, extractedDex.crc);
            editorEdit.putLong(str + "dex.time." + i9, extractedDex.lastModified());
            i9++;
        }
        editorEdit.commit();
    }

    /* renamed from: u */
    public static void m2199u(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException e9) {
            Log.w("MultiDex", "Failed to close resource", e9);
        }
    }

    /* renamed from: v */
    public static void m2200v(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File fileCreateTempFile = File.createTempFile("tmp-" + str, ".zip", file.getParentFile());
        Log.i("MultiDex", "Extracting " + fileCreateTempFile.getPath());
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileCreateTempFile)));
            try {
                ZipEntry zipEntry2 = new ZipEntry("classes.dex");
                zipEntry2.setTime(zipEntry.getTime());
                zipOutputStream.putNextEntry(zipEntry2);
                byte[] bArr = new byte[16384];
                for (int i9 = inputStream.read(bArr); i9 != -1; i9 = inputStream.read(bArr)) {
                    zipOutputStream.write(bArr, 0, i9);
                }
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                if (!fileCreateTempFile.setReadOnly()) {
                    throw new IOException("Failed to mark readonly \"" + fileCreateTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
                }
                Log.i("MultiDex", "Renaming to " + file.getPath());
                if (fileCreateTempFile.renameTo(file)) {
                    return;
                }
                throw new IOException("Failed to rename \"" + fileCreateTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
            } catch (Throwable th) {
                zipOutputStream.close();
                throw th;
            }
        } finally {
            m2199u(inputStream);
            fileCreateTempFile.delete();
        }
    }

    /* renamed from: w */
    public static SharedPreferences m2201w(Context context) {
        return context.getSharedPreferences("multidex.version", 4);
    }

    /* renamed from: x */
    public static long m2202x(File file) {
        long jLastModified = file.lastModified();
        return jLastModified == -1 ? jLastModified - 1 : jLastModified;
    }

    /* renamed from: y */
    public static long m2203y(File file) throws IOException {
        long jM20788c = C5311b.m20788c(file);
        return jM20788c == -1 ? jM20788c - 1 : jM20788c;
    }

    /* renamed from: z */
    public static boolean m2204z(Context context, File file, long j9, String str) {
        SharedPreferences sharedPreferencesM2201w = m2201w(context);
        if (sharedPreferencesM2201w.getLong(str + "timestamp", -1L) == m2202x(file)) {
            if (sharedPreferencesM2201w.getLong(str + "crc", -1L) == j9) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: A */
    public List<? extends File> m2205A(Context context, String str, boolean z8) throws IOException {
        List<ExtractedDex> listM2207C;
        List<ExtractedDex> listM2206B;
        Log.i("MultiDex", "MultiDexExtractor.load(" + this.f2360b.getPath() + ", " + z8 + ", " + str + ")");
        if (!this.f2365g.isValid()) {
            throw new IllegalStateException("MultiDexExtractor was closed");
        }
        if (!z8 && !m2204z(context, this.f2360b, this.f2361c, str)) {
            try {
                listM2206B = m2206B(context, str);
            } catch (IOException e9) {
                Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e9);
                listM2207C = m2207C();
                m2198D(context, str, m2202x(this.f2360b), this.f2361c, listM2207C);
            }
            Log.i("MultiDex", "load found " + listM2206B.size() + " secondary dex files");
            return listM2206B;
        }
        if (z8) {
            Log.i("MultiDex", "Forced extraction must be performed.");
        } else {
            Log.i("MultiDex", "Detected that extraction must be performed.");
        }
        listM2207C = m2207C();
        m2198D(context, str, m2202x(this.f2360b), this.f2361c, listM2207C);
        listM2206B = listM2207C;
        Log.i("MultiDex", "load found " + listM2206B.size() + " secondary dex files");
        return listM2206B;
    }

    /* renamed from: B */
    public final List<ExtractedDex> m2206B(Context context, String str) throws IOException {
        Log.i("MultiDex", "loading existing secondary dex files");
        String str2 = this.f2360b.getName() + ".classes";
        SharedPreferences sharedPreferencesM2201w = m2201w(context);
        int i9 = sharedPreferencesM2201w.getInt(str + "dex.number", 1);
        ArrayList arrayList = new ArrayList(i9 + (-1));
        int i10 = 2;
        while (i10 <= i9) {
            ExtractedDex extractedDex = new ExtractedDex(this.f2362d, str2 + i10 + ".zip");
            if (!extractedDex.isFile()) {
                throw new IOException("Missing extracted secondary dex file '" + extractedDex.getPath() + "'");
            }
            extractedDex.crc = m2203y(extractedDex);
            long j9 = sharedPreferencesM2201w.getLong(str + "dex.crc." + i10, -1L);
            long j10 = sharedPreferencesM2201w.getLong(str + "dex.time." + i10, -1L);
            long jLastModified = extractedDex.lastModified();
            if (j10 == jLastModified) {
                String str3 = str2;
                SharedPreferences sharedPreferences = sharedPreferencesM2201w;
                if (j9 == extractedDex.crc) {
                    arrayList.add(extractedDex);
                    i10++;
                    sharedPreferencesM2201w = sharedPreferences;
                    str2 = str3;
                }
            }
            throw new IOException("Invalid extracted dex: " + extractedDex + " (key \"" + str + "\"), expected modification time: " + j10 + ", modification time: " + jLastModified + ", expected crc: " + j9 + ", file crc: " + extractedDex.crc);
        }
        return arrayList;
    }

    /* renamed from: C */
    public final List<ExtractedDex> m2207C() throws IOException {
        boolean z8;
        String str = this.f2360b.getName() + ".classes";
        m2208f();
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.f2360b);
        try {
            int i9 = 2;
            ZipEntry entry = zipFile.getEntry("classes2.dex");
            while (entry != null) {
                ExtractedDex extractedDex = new ExtractedDex(this.f2362d, str + i9 + ".zip");
                arrayList.add(extractedDex);
                Log.i("MultiDex", "Extraction is needed for file " + extractedDex);
                int i10 = 0;
                boolean z9 = false;
                while (i10 < 3 && !z9) {
                    int i11 = i10 + 1;
                    m2200v(zipFile, entry, extractedDex, str);
                    try {
                        extractedDex.crc = m2203y(extractedDex);
                        z8 = true;
                    } catch (IOException e9) {
                        Log.w("MultiDex", "Failed to read crc from " + extractedDex.getAbsolutePath(), e9);
                        z8 = false;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Extraction ");
                    sb.append(z8 ? "succeeded" : "failed");
                    sb.append(" '");
                    sb.append(extractedDex.getAbsolutePath());
                    sb.append("': length ");
                    sb.append(extractedDex.length());
                    sb.append(" - crc: ");
                    sb.append(extractedDex.crc);
                    Log.i("MultiDex", sb.toString());
                    if (!z8) {
                        extractedDex.delete();
                        if (extractedDex.exists()) {
                            Log.w("MultiDex", "Failed to delete corrupted secondary dex '" + extractedDex.getPath() + "'");
                        }
                    }
                    z9 = z8;
                    i10 = i11;
                }
                if (!z9) {
                    throw new IOException("Could not create zip file " + extractedDex.getAbsolutePath() + " for secondary dex (" + i9 + ")");
                }
                i9++;
                entry = zipFile.getEntry("classes" + i9 + ".dex");
            }
            try {
                zipFile.close();
            } catch (IOException e10) {
                Log.w("MultiDex", "Failed to close resource", e10);
            }
            return arrayList;
        } finally {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2365g.release();
        this.f2364f.close();
        this.f2363e.close();
    }

    /* renamed from: f */
    public final void m2208f() {
        File[] fileArrListFiles = this.f2362d.listFiles(new C0428a());
        if (fileArrListFiles == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + this.f2362d.getPath() + ").");
            return;
        }
        for (File file : fileArrListFiles) {
            Log.i("MultiDex", "Trying to delete old file " + file.getPath() + " of size " + file.length());
            if (file.delete()) {
                Log.i("MultiDex", "Deleted old file " + file.getPath());
            } else {
                Log.w("MultiDex", "Failed to delete old file " + file.getPath());
            }
        }
    }
}
