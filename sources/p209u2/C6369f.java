package p209u2;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import com.cyberlink.you.Globals;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.p159io.FilenameUtils;

/* renamed from: u2.f */
/* loaded from: classes.dex */
public final class C6369f {

    /* renamed from: a */
    public static final String f21516a = "f";

    /* renamed from: a */
    public static boolean m24459a(File file, File file2) throws Throwable {
        BufferedInputStream bufferedInputStream;
        if (!file.exists()) {
            Log.e(f21516a, "Source does not exist: " + file);
            return false;
        }
        if (file.isDirectory()) {
            Log.e(f21516a, "Source exists but is a directory: " + file);
            return false;
        }
        try {
            if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
                Log.e(f21516a, "Source and destination are the same: " + file);
                return false;
            }
            BufferedInputStream bufferedInputStream2 = null;
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                } catch (Exception e9) {
                    e = e9;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                boolean zM24460b = m24460b(bufferedInputStream, file2);
                C6370g.m24480b(bufferedInputStream);
                return zM24460b;
            } catch (Exception e10) {
                e = e10;
                bufferedInputStream2 = bufferedInputStream;
                Log.e(f21516a, "Cannot copy file", e);
                C6370g.m24480b(bufferedInputStream2);
                return false;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                C6370g.m24480b(bufferedInputStream2);
                throw th;
            }
        } catch (IOException e11) {
            Log.e(f21516a, "", e11);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m24460b(InputStream inputStream, File file) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        if (file.getParentFile() != null && !file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            Log.e(f21516a, "Destination directory cannot be created: " + file);
            return false;
        }
        if (file.exists() && !file.canWrite()) {
            Log.e(f21516a, "Destination exists but is read-only: " + file);
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Exception e9) {
                e = e9;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            C6370g.m24481c(inputStream, bufferedOutputStream);
            C6370g.m24480b(bufferedOutputStream);
            return true;
        } catch (Exception e10) {
            e = e10;
            bufferedOutputStream2 = bufferedOutputStream;
            Log.e(f21516a, "Cannot copy file", e);
            C6370g.m24480b(bufferedOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            C6370g.m24480b(bufferedOutputStream2);
            throw th;
        }
    }

    /* renamed from: c */
    public static boolean m24461c(String str, Uri uri, File file) throws IOException {
        if (uri == null) {
            return m24459a(new File(str), file);
        }
        boolean zM24460b = false;
        try {
            InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
            try {
                if (inputStreamOpenInputStream != null) {
                    zM24460b = m24460b(inputStreamOpenInputStream, file);
                } else {
                    Log.e(f21516a, "cannot open file of null inputStream");
                }
                if (inputStreamOpenInputStream == null) {
                    return zM24460b;
                }
                inputStreamOpenInputStream.close();
                return zM24460b;
            } finally {
            }
        } catch (IOException e9) {
            Log.e(f21516a, "cannot open file inputStream", e9);
            return zM24460b;
        }
    }

    /* renamed from: d */
    public static void m24462d(File file, File file2) throws Throwable {
        if (file2.exists()) {
            if (!file2.isDirectory()) {
                return;
            }
        } else if (!file2.mkdirs()) {
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file3 : fileArrListFiles) {
            File file4 = new File(file2, file3.getName());
            if (file3.isDirectory()) {
                m24462d(file3, file4);
            } else {
                m24459a(file3, file4);
            }
        }
    }

    /* renamed from: e */
    public static boolean m24463e(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        try {
            return file.delete();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: f */
    public static boolean m24464f(File file) {
        boolean z8;
        File[] fileArrListFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length <= 0) {
            z8 = true;
        } else {
            z8 = true;
            for (File file2 : fileArrListFiles) {
                z8 = z8 && m24464f(file2);
            }
        }
        return z8 && file != null && file.delete();
    }

    /* renamed from: g */
    public static File m24465g(File file) {
        String absolutePath = file.getAbsolutePath();
        String str = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date());
        return new File(FilenameUtils.getFullPath(absolutePath) + (FilenameUtils.getBaseName(absolutePath) + str) + "." + FilenameUtils.getExtension(absolutePath));
    }

    /* renamed from: h */
    public static void m24466h(File file) {
        if (file.isFile()) {
            file.delete();
        }
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* renamed from: i */
    public static File m24467i(File file) {
        File parentFile = file.getParentFile();
        String strM24470l = m24470l(file);
        String strM24469k = m24469k(file);
        int i9 = 0;
        String str = strM24470l;
        while (true) {
            File file2 = new File(parentFile, str + "." + strM24469k);
            if (!file2.exists()) {
                return file2;
            }
            i9++;
            str = strM24470l + "-" + i9;
        }
    }

    /* renamed from: j */
    public static File m24468j(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return Environment.isExternalStorageEmulated() ? context.getExternalFilesDir(null) : context.getFilesDir();
        }
        return null;
    }

    /* renamed from: k */
    public static String m24469k(File file) {
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(".");
        return iLastIndexOf < 0 ? "" : name.substring(iLastIndexOf + 1);
    }

    /* renamed from: l */
    public static String m24470l(File file) {
        String name = file.getName();
        return name.lastIndexOf(".") >= 0 ? name.substring(0, name.lastIndexOf(".")) : name;
    }

    /* renamed from: m */
    public static Uri m24471m(File file) {
        return FileProvider.m1495e(Globals.m7372O(), "com.cyberlink.U.provider", file);
    }

    /* renamed from: n */
    public static String m24472n(File file, String str, String str2) {
        String lowerCase = TextUtils.isEmpty(str) ? null : str.toLowerCase(Locale.US);
        if (lowerCase != null && ((!TextUtils.isEmpty(str2) && lowerCase.startsWith(str2)) || lowerCase.startsWith("video/") || lowerCase.startsWith("audio/") || lowerCase.startsWith("image/"))) {
            return str;
        }
        String strM24469k = m24469k(file);
        Locale locale = Locale.US;
        String lowerCase2 = strM24469k.toLowerCase(locale);
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase2);
        if (TextUtils.isEmpty(mimeTypeFromExtension)) {
            mimeTypeFromExtension = "application/octet-stream";
        }
        if (TextUtils.isEmpty(str2) || !mimeTypeFromExtension.toLowerCase(locale).startsWith(str2)) {
            if ("mov".equals(lowerCase2)) {
                mimeTypeFromExtension = "video/quicktime";
            }
            if ("mkv".equals(lowerCase2)) {
                mimeTypeFromExtension = "video/x-matroska";
            }
        }
        return TextUtils.isEmpty(mimeTypeFromExtension) ? TextUtils.isEmpty(str) ? "application/octet-stream" : str : mimeTypeFromExtension;
    }

    /* renamed from: o */
    public static byte[] m24473o(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                bufferedInputStream.read(bArr, 0, length);
                C6370g.m24480b(bufferedInputStream);
                return bArr;
            } catch (Exception unused) {
                C6370g.m24480b(bufferedInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedInputStream2 = bufferedInputStream;
                C6370g.m24480b(bufferedInputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: p */
    public static List<String> m24474p(File file) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        if (file == null || !file.exists()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                inputStreamReader = new InputStreamReader(fileInputStream2, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            arrayList.add(line);
                        } catch (IOException unused) {
                            fileInputStream = fileInputStream2;
                            C6370g.m24480b(fileInputStream);
                            C6370g.m24480b(inputStreamReader);
                            C6370g.m24480b(bufferedReader);
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            C6370g.m24480b(fileInputStream);
                            C6370g.m24480b(inputStreamReader);
                            C6370g.m24480b(bufferedReader);
                            throw th;
                        }
                    }
                    C6370g.m24480b(fileInputStream2);
                } catch (IOException unused2) {
                    bufferedReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
            } catch (IOException unused3) {
                inputStreamReader = null;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                bufferedReader = null;
            }
        } catch (IOException unused4) {
            inputStreamReader = null;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            bufferedReader = null;
        }
        C6370g.m24480b(inputStreamReader);
        C6370g.m24480b(bufferedReader);
        return arrayList;
    }

    /* renamed from: q */
    public static boolean m24475q(File file, byte[] bArr) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        if (file == null) {
            return false;
        }
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return false;
        }
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
        } catch (IOException unused) {
            Log.v(f21516a, "create new file exception : " + file.getAbsolutePath());
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (Exception unused2) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.flush();
            C6370g.m24480b(bufferedOutputStream);
            return true;
        } catch (Exception unused3) {
            bufferedOutputStream2 = bufferedOutputStream;
            C6370g.m24480b(bufferedOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            C6370g.m24480b(bufferedOutputStream2);
            throw th;
        }
    }

    /* renamed from: r */
    public static void m24476r(ZipOutputStream zipOutputStream, File file) throws Throwable {
        if (file.isFile()) {
            File parentFile = file.getParentFile();
            m24477s(zipOutputStream, parentFile != null ? parentFile.getName() : "", file);
        } else {
            throw new IllegalArgumentException("It's not a file: " + file);
        }
    }

    /* renamed from: s */
    public static void m24477s(ZipOutputStream zipOutputStream, String str, File file) throws Throwable {
        if (str == null) {
            str = "";
        } else {
            String str2 = File.separator;
            if (!str.endsWith(str2)) {
                str = str + str2;
            }
        }
        zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
                while (true) {
                    int i9 = fileInputStream2.read(bArr);
                    if (i9 <= 0) {
                        C6370g.m24480b(fileInputStream2);
                        return;
                    }
                    zipOutputStream.write(bArr, 0, i9);
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                C6370g.m24480b(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: t */
    public static void m24478t(File file, List<File> list) {
        ZipOutputStream zipOutputStream;
        System.currentTimeMillis();
        m24466h(file.getParentFile());
        ZipOutputStream zipOutputStream2 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            Iterator<File> it = list.iterator();
            while (it.hasNext()) {
                m24476r(zipOutputStream, it.next());
            }
            C6370g.m24480b(zipOutputStream);
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream2 = zipOutputStream;
            C6370g.m24480b(zipOutputStream2);
            throw th;
        }
    }
}
