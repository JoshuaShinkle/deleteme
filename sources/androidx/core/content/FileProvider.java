package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sqlcipher.database.SQLiteDatabase;
import org.apache.commons.p159io.IOUtils;
import org.xmlpull.v1.XmlPullParserException;
import p197t.C6273a;

/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {

    /* renamed from: c */
    public static final String[] f1775c = {"_display_name", "_size"};

    /* renamed from: d */
    public static final File f1776d = new File("/");

    /* renamed from: e */
    public static HashMap<String, InterfaceC0321a> f1777e = new HashMap<>();

    /* renamed from: b */
    public InterfaceC0321a f1778b;

    /* renamed from: androidx.core.content.FileProvider$a */
    public interface InterfaceC0321a {
        /* renamed from: a */
        File mo1498a(Uri uri);

        /* renamed from: b */
        Uri mo1499b(File file);
    }

    /* renamed from: androidx.core.content.FileProvider$b */
    public static class C0322b implements InterfaceC0321a {

        /* renamed from: a */
        public final String f1779a;

        /* renamed from: b */
        public final HashMap<String, File> f1780b = new HashMap<>();

        public C0322b(String str) {
            this.f1779a = str;
        }

        @Override // androidx.core.content.FileProvider.InterfaceC0321a
        /* renamed from: a */
        public File mo1498a(Uri uri) throws IOException {
            String encodedPath = uri.getEncodedPath();
            int iIndexOf = encodedPath.indexOf(47, 1);
            String strDecode = Uri.decode(encodedPath.substring(1, iIndexOf));
            String strDecode2 = Uri.decode(encodedPath.substring(iIndexOf + 1));
            File file = this.f1780b.get(strDecode);
            if (file == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
            File file2 = new File(file, strDecode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
            }
        }

        @Override // androidx.core.content.FileProvider.InterfaceC0321a
        /* renamed from: b */
        public Uri mo1499b(File file) throws IOException {
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.f1780b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry == null) {
                    throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
                }
                String path2 = entry.getValue().getPath();
                return new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(this.f1779a).encodedPath(Uri.encode(entry.getKey()) + IOUtils.DIR_SEPARATOR_UNIX + Uri.encode(path2.endsWith("/") ? canonicalPath.substring(path2.length()) : canonicalPath.substring(path2.length() + 1), "/")).build();
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        /* renamed from: c */
        public void m1500c(String str, File file) throws IOException {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                this.f1780b.put(str, file.getCanonicalFile());
            } catch (IOException e9) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e9);
            }
        }
    }

    /* renamed from: a */
    public static File m1491a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    /* renamed from: b */
    public static Object[] m1492b(Object[] objArr, int i9) {
        Object[] objArr2 = new Object[i9];
        System.arraycopy(objArr, 0, objArr2, 0, i9);
        return objArr2;
    }

    /* renamed from: c */
    public static String[] m1493c(String[] strArr, int i9) {
        String[] strArr2 = new String[i9];
        System.arraycopy(strArr, 0, strArr2, 0, i9);
        return strArr2;
    }

    /* renamed from: d */
    public static InterfaceC0321a m1494d(Context context, String str) {
        InterfaceC0321a interfaceC0321aM1497g;
        synchronized (f1777e) {
            interfaceC0321aM1497g = f1777e.get(str);
            if (interfaceC0321aM1497g == null) {
                try {
                    interfaceC0321aM1497g = m1497g(context, str);
                    f1777e.put(str, interfaceC0321aM1497g);
                } catch (IOException e9) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e9);
                } catch (XmlPullParserException e10) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e10);
                }
            }
        }
        return interfaceC0321aM1497g;
    }

    /* renamed from: e */
    public static Uri m1495e(Context context, String str, File file) {
        return m1494d(context, str).mo1499b(file);
    }

    /* renamed from: f */
    public static int m1496f(String str) {
        if ("r".equals(str)) {
            return SQLiteDatabase.CREATE_IF_NECESSARY;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    /* renamed from: g */
    public static InterfaceC0321a m1497g(Context context, String str) throws XmlPullParserException, IOException {
        C0322b c0322b = new C0322b(str);
        ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider(str, 128);
        if (providerInfoResolveContentProvider == null) {
            throw new IllegalArgumentException("Couldn't find meta-data for provider with authority " + str);
        }
        XmlResourceParser xmlResourceParserLoadXmlMetaData = providerInfoResolveContentProvider.loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (xmlResourceParserLoadXmlMetaData == null) {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        while (true) {
            int next = xmlResourceParserLoadXmlMetaData.next();
            if (next == 1) {
                return c0322b;
            }
            if (next == 2) {
                String name = xmlResourceParserLoadXmlMetaData.getName();
                File externalStorageDirectory = null;
                String attributeValue = xmlResourceParserLoadXmlMetaData.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
                String attributeValue2 = xmlResourceParserLoadXmlMetaData.getAttributeValue(null, "path");
                if ("root-path".equals(name)) {
                    externalStorageDirectory = f1776d;
                } else if ("files-path".equals(name)) {
                    externalStorageDirectory = context.getFilesDir();
                } else if ("cache-path".equals(name)) {
                    externalStorageDirectory = context.getCacheDir();
                } else if ("external-path".equals(name)) {
                    externalStorageDirectory = Environment.getExternalStorageDirectory();
                } else if ("external-files-path".equals(name)) {
                    File[] fileArrM24027f = C6273a.m24027f(context, null);
                    if (fileArrM24027f.length > 0) {
                        externalStorageDirectory = fileArrM24027f[0];
                    }
                } else if ("external-cache-path".equals(name)) {
                    File[] fileArrM24026e = C6273a.m24026e(context);
                    if (fileArrM24026e.length > 0) {
                        externalStorageDirectory = fileArrM24026e[0];
                    }
                } else if ("external-media-path".equals(name)) {
                    File[] externalMediaDirs = context.getExternalMediaDirs();
                    if (externalMediaDirs.length > 0) {
                        externalStorageDirectory = externalMediaDirs[0];
                    }
                }
                if (externalStorageDirectory != null) {
                    c0322b.m1500c(attributeValue, m1491a(externalStorageDirectory, attributeValue2));
                }
            }
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        this.f1778b = m1494d(context, providerInfo.authority);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return this.f1778b.mo1498a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        File fileMo1498a = this.f1778b.mo1498a(uri);
        int iLastIndexOf = fileMo1498a.getName().lastIndexOf(46);
        if (iLastIndexOf < 0) {
            return "application/octet-stream";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileMo1498a.getName().substring(iLastIndexOf + 1));
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        return ParcelFileDescriptor.open(this.f1778b.mo1498a(uri), m1496f(str));
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i9;
        File fileMo1498a = this.f1778b.mo1498a(uri);
        if (strArr == null) {
            strArr = f1775c;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i10 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i10] = "_display_name";
                i9 = i10 + 1;
                objArr[i10] = fileMo1498a.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i10] = "_size";
                i9 = i10 + 1;
                objArr[i10] = Long.valueOf(fileMo1498a.length());
            }
            i10 = i9;
        }
        String[] strArrM1493c = m1493c(strArr3, i10);
        Object[] objArrM1492b = m1492b(objArr, i10);
        MatrixCursor matrixCursor = new MatrixCursor(strArrM1493c, 1);
        matrixCursor.addRow(objArrM1492b);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
