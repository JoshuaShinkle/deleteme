package p125l2;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.File;
import java.util.Locale;
import java.util.regex.Pattern;

/* renamed from: l2.b */
/* loaded from: classes.dex */
public final class C5278b {

    /* renamed from: a */
    public static final Pattern f17882a = Pattern.compile("\\{[a-zA-Z/][^\\}]*\\}|<[a-zA-Z/][^>]*>");

    /* renamed from: a */
    public static int m20549a(int i9, int i10) {
        int i11 = i10 - 1;
        return (i9 + i11) & (~i11);
    }

    /* renamed from: b */
    public static String m20550b(File file) {
        int iLastIndexOf = file.getName().lastIndexOf(46);
        return iLastIndexOf > 0 ? file.getName().substring(iLastIndexOf + 1) : "";
    }

    /* renamed from: c */
    public static String m20551c(String str) {
        return m20550b(new File(str));
    }

    /* renamed from: d */
    public static String m20552d(Context context, Uri uri) {
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        String lowerCase = scheme.toLowerCase(Locale.US);
        if ("file".equals(lowerCase)) {
            return uri.getPath();
        }
        if ("http".equals(lowerCase) || "https".equals(lowerCase)) {
            return uri.toString();
        }
        String strM20556h = m20556h(context, uri);
        if (strM20556h != null) {
            return strM20556h;
        }
        String strM20555g = m20555g(context, uri, null, null);
        return strM20555g != null ? strM20555g : uri.getPath();
    }

    /* renamed from: e */
    public static int m20553e(int i9, int i10, int i11) {
        if (i9 == 6) {
            return i10 * i11 * 2;
        }
        if (i9 == 19 || i9 == 21) {
            return ((i10 * i11) * 3) / 2;
        }
        if (i9 != 2130741384) {
            if (i9 == 2141391872) {
                int i12 = i10 * i11;
                return m20549a(i12, 2048) + m20549a(i12 / 2, 2048);
            }
            if (i9 != 15 && i9 != 16) {
                throw new UnsupportedOperationException("Unsupported color format!");
            }
        }
        return i10 * i11 * 4;
    }

    /* renamed from: f */
    public static boolean m20554f(String str) {
        return str != null && (str.startsWith("/") || str.startsWith("file://"));
    }

    /* renamed from: g */
    public static String m20555g(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursorQuery;
        try {
            cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        } catch (Throwable unused) {
            cursorQuery = null;
        }
        try {
            cursorQuery.moveToFirst();
            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
            cursorQuery.close();
            return string;
        } catch (Throwable unused2) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        }
    }

    @TargetApi(19)
    /* renamed from: h */
    public static String m20556h(Context context, Uri uri) {
        Uri[] uriArr;
        if (!DocumentsContract.isDocumentUri(context, uri)) {
            return null;
        }
        String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
        String str = strArrSplit[0];
        String str2 = strArrSplit.length > 1 ? strArrSplit[1] : str;
        if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
            if (MimeTypes.BASE_TYPE_VIDEO.equals(str)) {
                uriArr = new Uri[]{MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MediaStore.Video.Media.INTERNAL_CONTENT_URI};
            } else if (MimeTypes.BASE_TYPE_AUDIO.equals(str)) {
                uriArr = new Uri[]{MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.INTERNAL_CONTENT_URI};
            } else {
                if (!"image".equals(str)) {
                    Log.e("CLUtility", "Could not find filesystem path of " + uri);
                    return null;
                }
                uriArr = new Uri[]{MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.INTERNAL_CONTENT_URI};
            }
            String[] strArr = {str2};
            for (Uri uri2 : uriArr) {
                String strM20555g = m20555g(context, uri2, "_id=?", strArr);
                if (strM20555g != null) {
                    return strM20555g;
                }
            }
        } else {
            if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                return m20555g(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(str2).longValue()), null, null);
            }
            if ("com.android.externalstorage.documents".equals(uri.getAuthority()) && "primary".equalsIgnoreCase(str)) {
                return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + str2;
            }
        }
        return null;
    }
}
