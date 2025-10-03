package com.cyberlink.you.utility;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.util.Size;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.util.DeviceCapability;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.splash.SplashActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.ScheduleSendObj;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2971k0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendGroup;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.messaging.Constants;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.p159io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p056e4.C4754a;
import p056e4.C4755b;
import p072g0.AbstractC4804a;
import p082h0.C4978a;
import p084h2.C4986c;
import p116k4.AbstractC5146g0;
import p116k4.C5127a;
import p116k4.C5154j;
import p116k4.C5155j0;
import p116k4.C5164m0;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5176q0;
import p116k4.C5187v0;
import p182r2.C6202j;
import p201t3.C6301o;
import p209u2.C6369f;
import p209u2.C6370g;
import p218v2.C6478z;
import p227w2.C6518a;
import p236x2.C6566a;

/* loaded from: classes.dex */
public final class CLUtility {

    /* renamed from: a */
    public static final Bitmap.Config f14425a = Bitmap.Config.ARGB_8888;

    /* renamed from: b */
    public static final Bitmap.CompressFormat f14426b = Bitmap.CompressFormat.JPEG;

    /* renamed from: c */
    public static final String[] f14427c = {"U: Futuristic (Default)", "U: Urgent", "U: Bubble", "U: Two-tone"};

    /* renamed from: d */
    public static final String[] f14428d = {"futuristic", "urgent", "bubble", "twotone"};

    /* renamed from: e */
    public static boolean f14429e = false;

    public enum InviteType {
        EMail,
        SMS
    }

    /* renamed from: com.cyberlink.you.utility.CLUtility$a */
    public class C3134a extends Thread {

        /* renamed from: b */
        public final /* synthetic */ Context f14433b;

        /* renamed from: c */
        public final /* synthetic */ Uri f14434c;

        /* renamed from: d */
        public final /* synthetic */ InterfaceC3137d f14435d;

        /* renamed from: e */
        public final /* synthetic */ String f14436e;

        public C3134a(Context context, Uri uri, InterfaceC3137d interfaceC3137d, String str) {
            this.f14433b = context;
            this.f14434c = uri;
            this.f14435d = interfaceC3137d;
            this.f14436e = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Throwable {
            Throwable th;
            InputStream inputStreamOpenInputStream;
            FileNotFoundException e9;
            FileDescriptor fileDescriptor;
            try {
                try {
                    inputStreamOpenInputStream = this.f14433b.getContentResolver().openInputStream(this.f14434c);
                    if (inputStreamOpenInputStream == null) {
                        try {
                            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = this.f14433b.getContentResolver().openFileDescriptor(this.f14434c, "r");
                            if (parcelFileDescriptorOpenFileDescriptor != null && (fileDescriptor = parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()) != null) {
                                inputStreamOpenInputStream = new FileInputStream(fileDescriptor);
                            }
                        } catch (FileNotFoundException e10) {
                            e9 = e10;
                            e9.printStackTrace();
                            C6370g.m24480b(inputStreamOpenInputStream);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    C6370g.m24480b(null);
                    throw th;
                }
            } catch (FileNotFoundException e11) {
                inputStreamOpenInputStream = null;
                e9 = e11;
            } catch (Throwable th3) {
                th = th3;
                C6370g.m24480b(null);
                throw th;
            }
            if (inputStreamOpenInputStream == null) {
                this.f14435d.mo7014a();
                C6370g.m24480b(inputStreamOpenInputStream);
                return;
            }
            File file = new File(this.f14433b.getExternalFilesDir(null) + "/" + this.f14436e);
            if (C6369f.m24460b(inputStreamOpenInputStream, file)) {
                this.f14435d.mo7015b(Uri.fromFile(file));
            } else {
                this.f14435d.mo7014a();
            }
            C6370g.m24480b(inputStreamOpenInputStream);
        }
    }

    /* renamed from: com.cyberlink.you.utility.CLUtility$b */
    public class C3135b extends PromisedTask.AbstractC4504d<String> {

        /* renamed from: a */
        public final /* synthetic */ boolean f14437a;

        public C3135b(boolean z8) {
            this.f14437a = z8;
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            Globals.m7388i0().m7444J3("");
            C6566a.m25167z();
            if (this.f14437a) {
                C6566a.m25162u("Launch_App");
            }
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(String str) {
            if (C5170o0.m20170e(str)) {
                str = "";
            }
            Globals.m7388i0().m7444J3(str);
            C6566a.m25167z();
            if (this.f14437a) {
                C6566a.m25162u("Launch_App");
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.CLUtility$c */
    public class C3136c extends PromisedTask.AbstractC4504d<JSONArray> {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC3139f f14438a;

        public C3136c(InterfaceC3139f interfaceC3139f) {
            this.f14438a = interfaceC3139f;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(JSONArray jSONArray) {
            StringBuilder sb = new StringBuilder();
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                try {
                    sb.append(jSONArray.getString(i9));
                    sb.append(",");
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            String strSubstring = sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) : "";
            ULogUtility.m16680p("CLUtility", "queryPermissions : " + strSubstring);
            Globals.m7388i0().m7458M3(strSubstring);
            InterfaceC3139f interfaceC3139f = this.f14438a;
            if (interfaceC3139f != null) {
                interfaceC3139f.onSuccess();
            }
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            InterfaceC3139f interfaceC3139f = this.f14438a;
            if (interfaceC3139f != null) {
                interfaceC3139f.mo12947a();
            }
            Log.d("CLUtility", "Permissions errorCode:" + i9);
        }
    }

    /* renamed from: com.cyberlink.you.utility.CLUtility$d */
    public interface InterfaceC3137d {
        /* renamed from: a */
        void mo7014a();

        /* renamed from: b */
        void mo7015b(Uri uri);
    }

    /* renamed from: com.cyberlink.you.utility.CLUtility$e */
    public static class C3138e {

        /* renamed from: a */
        public int f14439a;

        /* renamed from: b */
        public int f14440b = -1;

        /* renamed from: c */
        public int f14441c = -1;

        /* renamed from: d */
        public int f14442d = 0;
    }

    /* renamed from: com.cyberlink.you.utility.CLUtility$f */
    public interface InterfaceC3139f {
        /* renamed from: a */
        void mo12947a();

        void onSuccess();
    }

    /* renamed from: A */
    public static void m16415A(String str) throws IOException {
        File file = new File(str + File.separator + ".nomedia");
        if (file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: A0 */
    public static String m16416A0(String str, boolean z8) {
        String name = new File(str).getName();
        if (!z8) {
            if (name.contains("_720.jpg")) {
                return name.replaceFirst("_720.jpg", "");
            }
            return null;
        }
        if (name.contains("user_") && name.contains("_720.jpg")) {
            return name.replaceFirst("user_", "").replaceFirst("_720.jpg", "");
        }
        return null;
    }

    /* renamed from: A1 */
    public static boolean m16417A1(Uri uri) {
        return uri != null && "file".equals(uri.getScheme());
    }

    /* renamed from: A2 */
    public static void m16418A2(final Activity activity, final Group group, final InviteType inviteType, final String[] strArr) {
        final UserInfo userInfoM16497V0;
        String string;
        String str;
        if (activity == null || (userInfoM16497V0 = m16497V0(activity.getApplicationContext())) == null) {
            return;
        }
        final SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("U", 0);
        if (group != null) {
            string = null;
            try {
                Group groupM15077n = C2950b0.m14912k().m15077n(Long.toString(group.f13727n));
                if (groupM15077n != null) {
                    string = groupM15077n.f13707F;
                    Log.d("CLUtility", "[getFriendLink] inviteGroupLink = " + string);
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        } else {
            string = sharedPreferences.getString("inviteFriendLink", "");
        }
        if (!C5170o0.m20170e(string)) {
            if (inviteType == InviteType.EMail) {
                m16570o2(activity, userInfoM16497V0, group, string, strArr);
                return;
            } else {
                m16574p2(activity, userInfoM16497V0, group, string);
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (group != null) {
            arrayList.add(new C6301o("groupId", Long.toString(group.f13727n)));
            str = "group";
        } else {
            str = "invite";
        }
        new FriendsClient().m15734m(str, "genInviteURL", arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.f
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) throws JSONException {
                CLUtility.m16469N1(group, sharedPreferences, inviteType, activity, userInfoM16497V0, strArr, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: B */
    public static void m16419B() throws IOException {
        File file = new File(m16529e1());
        if (file.exists()) {
            Log.d("CLUtility", "Snapshot folder is already exists");
        } else {
            file.mkdir();
        }
    }

    /* renamed from: B0 */
    public static C3138e m16420B0(String str, Uri uri) throws IOException {
        C4978a c4978a;
        C3138e c3138e = new C3138e();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            m16509Z0(str, uri, options);
            int i9 = options.outWidth;
            c3138e.f14440b = i9;
            int i10 = options.outHeight;
            c3138e.f14441c = i10;
            if (i9 <= 0 || i10 <= 0) {
                Log.d("CLUtility", "[getImageInfo] Decode File - 1 : width=" + c3138e.f14440b + ", height=" + c3138e.f14441c + ", srcImg=" + str);
                return c3138e;
            }
            try {
                C4978a c4978a2 = null;
                if (m16597v1(uri)) {
                    try {
                        InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
                        if (inputStreamOpenInputStream != null) {
                            try {
                                c4978a = new C4978a(inputStreamOpenInputStream);
                            } finally {
                            }
                        } else {
                            c4978a = null;
                        }
                        if (inputStreamOpenInputStream != null) {
                            inputStreamOpenInputStream.close();
                        }
                        c4978a2 = c4978a;
                    } catch (Exception unused) {
                        ULogUtility.m16670f("CLUtility", "[copyImageAttribute] Exception : sourceUri=" + uri);
                    }
                }
                if (!TextUtils.isEmpty(str) && c4978a2 == null) {
                    c4978a2 = new C4978a(str);
                }
                if (c4978a2 != null) {
                    c3138e.f14442d = c4978a2.m19288f("Orientation", 0);
                }
            } catch (IOException e9) {
                Log.e("CLUtility", "cannot get exif attribute", e9);
            }
            c3138e.f14439a = m16555l(options, 1280, 1280);
            return c3138e;
        } catch (OutOfMemoryError e10) {
            e10.printStackTrace();
            return c3138e;
        }
    }

    /* renamed from: B1 */
    public static boolean m16421B1(Window window) {
        return (window.getAttributes().flags & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0;
    }

    /* renamed from: B2 */
    public static String m16422B2(Date date) {
        return m16426C2(date, false);
    }

    /* renamed from: C */
    public static void m16423C() throws IOException {
        File file = new File(m16537g1());
        if (file.exists()) {
            return;
        }
        file.mkdir();
    }

    /* renamed from: C0 */
    public static ImageItem m16424C0(Context context, long j9) {
        ImageItem imageItem = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "bucket_display_name", "bucket_id", "orientation"}, "_id = ?", new String[]{String.valueOf(j9)}, "");
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() == 1) {
                        cursorQuery.moveToFirst();
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("bucket_id"));
                        String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("bucket_display_name"));
                        String string3 = Build.VERSION.SDK_INT < 29 ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                        Uri uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, j9);
                        imageItem = new ImageItem(string, j9, string2, string3, uriWithAppendedId.toString(), m16552k0(string3, uriWithAppendedId), -1, cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("orientation")), "");
                    } else {
                        Log.e("CLUtility", "Cursor count = " + cursorQuery.getCount());
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return imageItem;
    }

    /* renamed from: C1 */
    public static boolean m16425C1(Activity activity) throws SecurityException {
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) activity.getSystemService("activity")).getRunningTasks(10);
            if (runningTasks.get(0).numActivities == 1) {
                return runningTasks.get(0).topActivity.getClassName().equals(activity.getClass().getName());
            }
            return false;
        } catch (Exception e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: C2 */
    public static String m16426C2(Date date, boolean z8) {
        Locale locale = Locale.getDefault();
        return (z8 || !m16449I1(date)) ? new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, "EEE MMM d yyyy"), locale).format(date) : new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, "EEE MMM d"), locale).format(date);
    }

    /* renamed from: D */
    public static String m16427D(String str, Uri uri) {
        return m16431E(str, uri, 1);
    }

    /* renamed from: D0 */
    public static ImageItem m16428D0(Context context, String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ImageItem imageItemM16563n;
        Cursor cursor = null;
        try {
            String strM16612z0 = m16612z0(str, false);
            long j9 = -1;
            try {
                if (!C5170o0.m20169d(strM16612z0)) {
                    j9 = Long.parseLong(strM16612z0);
                }
            } catch (NumberFormatException e9) {
                if (e9.getMessage() != null) {
                    Log.e("CLUtility", e9.getMessage());
                }
                e9.printStackTrace();
            }
            long j10 = j9;
            if (j10 > 0) {
                imageItemM16563n = m16424C0(context, j10);
                if (imageItemM16563n == null) {
                    try {
                        imageItemM16563n = m16563n(context, new ImageItem("", j10, "", str, "-", 0, -1, ""));
                    } catch (Throwable th) {
                        th = th;
                        try {
                            th.printStackTrace();
                            return imageItemM16563n;
                        } finally {
                            C6370g.m24480b(cursor);
                        }
                    }
                }
            } else {
                imageItemM16563n = null;
            }
            if (imageItemM16563n == null) {
                Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, "_data = ? ", new String[]{str}, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            imageItemM16563n = m16563n(context, new ImageItem("", cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_id")), "", cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")), "-", 0, -1, ""));
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = cursorQuery;
                        th.printStackTrace();
                        return imageItemM16563n;
                    }
                }
                cursor = cursorQuery;
            }
        } catch (Throwable th3) {
            th = th3;
            imageItemM16563n = null;
        }
        return imageItemM16563n;
    }

    /* renamed from: D1 */
    public static boolean m16429D1(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /* renamed from: D2 */
    public static String m16430D2(Date date) {
        return m16422B2(date) + StringUtils.SPACE + m16454J2(date);
    }

    /* renamed from: E */
    public static String m16431E(String str, Uri uri, int i9) {
        return m16435F(str, uri, null, i9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009c A[Catch: all -> 0x0069, TryCatch #5 {all -> 0x0069, blocks: (B:23:0x0050, B:25:0x005c, B:27:0x0062, B:34:0x0076, B:37:0x0087, B:47:0x0095, B:50:0x009c, B:52:0x00a6, B:56:0x00c6, B:57:0x00c9), top: B:76:0x0050 }] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.io.BufferedOutputStream, java.io.OutputStream] */
    /* renamed from: E0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ImageItem m16432E0(Context context, Uri uri) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Cursor cursorQuery;
        ImageItem imageItemM16424C0;
        FileDescriptor fileDescriptor;
        String strM16512a0;
        ?? bufferedOutputStream;
        String strM16608y0;
        InputStream inputStream = null;
        try {
            InputStream inputStream2 = null;
            cursorQuery = context.getContentResolver().query(uri, new String[]{"_id", "_data"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        cursorQuery.moveToFirst();
                        int columnIndex = cursorQuery.getColumnIndex("_id");
                        long j9 = columnIndex != -1 ? cursorQuery.getLong(columnIndex) : 0L;
                        imageItemM16424C0 = j9 != 0 ? m16424C0(context, j9) : null;
                        if (j9 == 0 || imageItemM16424C0 == null) {
                            try {
                                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                                if (inputStreamOpenInputStream == null) {
                                    try {
                                        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
                                        if (parcelFileDescriptorOpenFileDescriptor != null && (fileDescriptor = parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()) != null) {
                                            inputStreamOpenInputStream = new FileInputStream(fileDescriptor);
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        inputStream = inputStreamOpenInputStream;
                                        try {
                                            th.printStackTrace();
                                            return imageItemM16424C0;
                                        } finally {
                                            C6370g.m24480b(inputStream);
                                            C6370g.m24480b(cursorQuery);
                                        }
                                    }
                                }
                                if (inputStreamOpenInputStream != null) {
                                    try {
                                        strM16512a0 = m16512a0();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        inputStream = inputStream2;
                                    }
                                    try {
                                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(strM16512a0));
                                        try {
                                            C6370g.m24481c(inputStreamOpenInputStream, bufferedOutputStream);
                                            inputStream2 = bufferedOutputStream;
                                        } catch (Exception e9) {
                                            e = e9;
                                            C5154j.m20076j(e);
                                            inputStream2 = bufferedOutputStream;
                                            C6370g.m24480b(inputStream2);
                                            strM16608y0 = m16608y0(strM16512a0);
                                            if (strM16608y0 == null) {
                                            }
                                        }
                                    } catch (Exception e10) {
                                        e = e10;
                                        bufferedOutputStream = 0;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        C6370g.m24480b(inputStream);
                                        throw th;
                                    }
                                    C6370g.m24480b(inputStream2);
                                    strM16608y0 = m16608y0(strM16512a0);
                                    if (strM16608y0 == null) {
                                        long j10 = NumberUtils.toLong(strM16608y0, -1L);
                                        if (j10 != -1) {
                                            imageItemM16424C0 = m16563n(context, new ImageItem("", j10, "", strM16512a0, uri.toString(), "-", 0, -1, ""));
                                        }
                                        inputStream = inputStreamOpenInputStream;
                                    }
                                }
                                return null;
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    imageItemM16424C0 = null;
                    th.printStackTrace();
                    return imageItemM16424C0;
                }
            }
        } catch (Throwable th6) {
            th = th6;
            cursorQuery = null;
            imageItemM16424C0 = null;
        }
        return imageItemM16424C0;
    }

    /* renamed from: E1 */
    public static boolean m16433E1() {
        return Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0).getBoolean("setting_notification", UserInfo.f13769G);
    }

    /* renamed from: E2 */
    public static String m16434E2(Date date) {
        return DateFormat.is24HourFormat(Globals.m7388i0().getApplicationContext()) ? new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault()).format(date) : new SimpleDateFormat("MM/dd hh:mm a", Locale.getDefault()).format(date);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* renamed from: F */
    public static String m16435F(String str, Uri uri, String str2, int i9) throws IOException {
        String str3;
        String str4 = "";
        if (TextUtils.isEmpty(str) && uri == null) {
            return "";
        }
        try {
            String str5 = Globals.m7388i0().m7409C1().booleanValue() ? "" : ".jpg";
            if (TextUtils.isEmpty(str2)) {
                str2 = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date());
            }
            str3 = m16472O0(Globals.m7372O()) + File.separator + str2 + str5;
        } catch (Exception e9) {
            e = e9;
        }
        try {
            ContentResolver contentResolver = Globals.m7388i0().getContentResolver();
            if (Build.VERSION.SDK_INT >= 29) {
                Size size = new Size(0, 0);
                if (i9 == 1) {
                    size = new Size(512, 384);
                }
                try {
                    str = uri != null ? contentResolver.loadThumbnail(uri, size, null) : m16439G(str, uri, size);
                } catch (IOException unused) {
                    str = m16439G(str, uri, size);
                }
            } else {
                str = ThumbnailUtils.createVideoThumbnail(str, i9);
            }
            if (str == 0) {
                ULogUtility.m16670f("CLUtility", "createVideoThumbPath bitmap null");
                return "";
            }
            ULogUtility.m16670f("CLUtility", "createVideoThumbPath bitmap success");
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            boolean zCompress = str.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
            fileOutputStream.close();
            if (!zCompress) {
                Log.e("CLUtility", "[getVideoThumbPath] Compress failed to create video thumb path !!!");
                return "";
            }
            try {
                C4754a.m18877g(str3, str.getWidth());
                C4754a.m18875e(str3, str.getHeight());
                C4754a.m18876f(str3, 0);
            } catch (IOException e10) {
                Log.e("CLUtility", "cannot set exif attribute", e10);
            }
            return str3;
        } catch (Exception e11) {
            e = e11;
            str4 = str3;
            e.printStackTrace();
            return str4;
        }
    }

    /* renamed from: F0 */
    public static float m16436F0(int i9, int i10, int i11) {
        float f9 = i9 > i10 ? i11 / i9 : i11 / i10;
        if (f9 > 1.0f) {
            f9 = 1.0f;
        }
        return f9 < BitmapDescriptorFactory.HUE_RED ? BitmapDescriptorFactory.HUE_RED : f9;
    }

    /* renamed from: F1 */
    public static boolean m16437F1() {
        String str = "armeabi-v7a";
        try {
            Process processStart = new ProcessBuilder("/system/bin/getprop", "ro.product.cpu.abilist").redirectErrorStream(true).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                str = line;
            }
            processStart.destroy();
            if (str.equalsIgnoreCase(str)) {
                return true;
            }
        } catch (IOException e9) {
            e9.printStackTrace();
            if (str.equalsIgnoreCase(str)) {
                return true;
            }
        } finally {
            str.equalsIgnoreCase("x86");
        }
        return false;
    }

    /* renamed from: F2 */
    public static String m16438F2(Date date) {
        return new SimpleDateFormat("MM/dd", Locale.getDefault()).format(date);
    }

    /* renamed from: G */
    public static Bitmap m16439G(String str, Uri uri, Size size) throws SecurityException, IOException, IllegalArgumentException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(Globals.m7372O(), uri);
        } catch (Exception e9) {
            Log.e("CLUtility", "createVideoThumbnail from uri fail:" + uri + " ex:" + e9);
            try {
                mediaMetadataRetriever.setDataSource(str);
            } catch (Exception e10) {
                Log.e("CLUtility", "createVideoThumbnail from path fail:" + str + " ex:" + e10);
            }
        }
        int i9 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
        int i10 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
        try {
            try {
            } catch (Throwable th) {
                try {
                    Log.e("CLUtility", "createVideoThumbnail from uri fail", th);
                    mediaMetadataRetriever.release();
                } finally {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (IOException e11) {
                        e11.printStackTrace();
                    } catch (RuntimeException e12) {
                        Log.e("CLUtility", "createVideoThumbnail release fail", e12);
                    }
                }
            }
            if (size.getWidth() > i9 && size.getHeight() > i10) {
                return mediaMetadataRetriever.getFrameAtTime(-1L, 2);
            }
            if (Build.VERSION.SDK_INT < 27) {
                ThumbnailUtils.extractThumbnail(mediaMetadataRetriever.getFrameAtTime(-1L, 2), size.getWidth(), size.getHeight(), 2);
                mediaMetadataRetriever.release();
                return null;
            }
            Bitmap scaledFrameAtTime = mediaMetadataRetriever.getScaledFrameAtTime(-1L, 2, size.getWidth(), size.getHeight());
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e13) {
                e13.printStackTrace();
            } catch (RuntimeException e14) {
                Log.e("CLUtility", "createVideoThumbnail release fail", e14);
            }
            return scaledFrameAtTime;
        } catch (IOException e15) {
            e15.printStackTrace();
            return null;
        } catch (RuntimeException e16) {
            Log.e("CLUtility", "createVideoThumbnail release fail", e16);
            return null;
        }
    }

    /* renamed from: G0 */
    public static long m16440G0(String str, Uri uri) throws SecurityException, IOException, IllegalArgumentException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        long jLongValue = 0;
        try {
            if (uri != null) {
                mediaMetadataRetriever.setDataSource(Globals.m7372O(), uri);
            } else {
                if (TextUtils.isEmpty(str)) {
                    return 0L;
                }
                mediaMetadataRetriever.setDataSource(str);
            }
            jLongValue = Long.valueOf(mediaMetadataRetriever.extractMetadata(9)).longValue();
        } catch (Exception e9) {
            ULogUtility.m16670f("CLUtility", "getMediaDuration error:" + e9.getMessage());
        }
        try {
            mediaMetadataRetriever.release();
        } catch (IOException e10) {
            e10.printStackTrace();
        }
        return jLongValue;
    }

    /* renamed from: G1 */
    public static boolean m16441G1(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar.get(3) == calendar2.get(3);
    }

    /* renamed from: G2 */
    public static String m16442G2(Date date) {
        return (m16441G1(date) || m16461L1(date)) ? m16596v0(Globals.m7372O(), date, "EEEE") : m16449I1(date) ? new SimpleDateFormat("MM/dd", Locale.getDefault()).format(date) : new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date);
    }

    /* renamed from: H */
    public static String m16443H() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date());
    }

    /* renamed from: H0 */
    public static String m16444H0(String str) {
        if (Build.VERSION.SDK_INT < 29) {
            if ("image".equals(str)) {
                return m16561m1(C6518a.f21931c);
            }
            if (MimeTypes.BASE_TYPE_VIDEO.equals(str)) {
                return m16561m1(C6518a.f21932d);
            }
            if ("download".equals(str)) {
                return m16561m1(C6518a.f21933e);
            }
        } else {
            if ("image".equals(str)) {
                return Environment.DIRECTORY_PICTURES + File.separator + "U_PHOTO";
            }
            if (MimeTypes.BASE_TYPE_VIDEO.equals(str)) {
                return Environment.DIRECTORY_MOVIES + File.separator + "U_VIDEO";
            }
            if ("download".equals(str)) {
                return Environment.DIRECTORY_DOWNLOADS + File.separator + "U_DOWNLOAD";
            }
        }
        return "";
    }

    /* renamed from: H1 */
    public static boolean m16445H1(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return false;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int i9 = point.x;
        int i10 = point.y;
        float f9 = displayMetrics.xdpi;
        float f10 = displayMetrics.ydpi;
        ULogUtility.m16670f("CLUtility", "[isTablet] width : " + i9 + " widthDpi : " + f9);
        ULogUtility.m16670f("CLUtility", "[isTablet] height : " + i10 + " heightDpi : " + f10);
        double dSqrt = Math.sqrt(Math.pow((double) (((float) i9) / f9), 2.0d) + Math.pow((double) (((float) i10) / f10), 2.0d));
        StringBuilder sb = new StringBuilder();
        sb.append("[isTablet] screenDiagonal : ");
        sb.append(dSqrt);
        ULogUtility.m16670f("CLUtility", sb.toString());
        return dSqrt >= 6.9d;
    }

    /* renamed from: H2 */
    public static String m16446H2(Context context, long j9, boolean z8) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j10 = (jCurrentTimeMillis - j9) / 1000;
        if (j10 < 600) {
            return context.getString(R.string.just_now);
        }
        if (j10 < 3600) {
            return context.getString(z8 ? R.string.D_m_ago : R.string.D_m, Long.valueOf((j10 / 600) * 10));
        }
        if (j10 < 7200) {
            return context.getString(z8 ? R.string.one_hr_ago : R.string.one_hr);
        }
        if (j10 < 86400) {
            return context.getString(z8 ? R.string.D_hrs_ago : R.string.D_hrs, Long.valueOf(j10 / 3600));
        }
        if (j10 < 691200) {
            long j11 = j10 / 86400;
            return context.getResources().getQuantityString(z8 ? R.plurals.D_days_ago : R.plurals.D_days, (int) j11, Long.valueOf(j11));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jCurrentTimeMillis);
        int i9 = calendar.get(1);
        calendar.setTimeInMillis(j9);
        return i9 == calendar.get(1) ? m16438F2(new Date(j9)) : m16458K2(new Date(j9));
    }

    /* renamed from: I */
    public static boolean m16447I(File file) {
        String[] list;
        if (file == null) {
            return false;
        }
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                if (!m16447I(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x006c, code lost:
    
        return r9;
     */
    /* renamed from: I0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Uri m16448I0(String str, String str2, Uri[] uriArr) {
        String str3;
        String[] strArr;
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        if (!TextUtils.isEmpty(str)) {
            strArr = new String[]{str};
            str3 = "_id=?";
        } else if (TextUtils.isEmpty(str2)) {
            str3 = null;
            strArr = null;
        } else {
            strArr = new String[]{str2};
            str3 = "_data=?";
        }
        int length = uriArr.length;
        int i9 = 0;
        Uri uriWithAppendedId = null;
        while (true) {
            if (i9 >= length) {
                break;
            }
            Uri uri = uriArr[i9];
            try {
                Cursor cursorQuery = contentResolver.query(uri, null, str3, strArr, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            uriWithAppendedId = ContentUris.withAppendedId(uri, cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_id")));
                            cursorQuery.close();
                            break;
                        }
                    } finally {
                        try {
                            break;
                        } catch (Throwable th) {
                        }
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            i9++;
        }
    }

    /* renamed from: I1 */
    public static boolean m16449I1(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        return simpleDateFormat.format(new Date()).equals(simpleDateFormat.format(date));
    }

    /* renamed from: I2 */
    public static String m16450I2(Date date) {
        return m16453J1(date) ? m16454J2(date) : m16449I1(date) ? new SimpleDateFormat("MM/dd", Locale.getDefault()).format(date) : new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date);
    }

    /* renamed from: J */
    public static boolean m16451J(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            return false;
        } catch (Exception e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: J0 */
    public static String m16452J0(String str, Uri uri) {
        String fileExtensionFromUrl;
        String type = m16597v1(uri) ? Globals.m7372O().getContentResolver().getType(uri) : "";
        return (TextUtils.isEmpty(str) || !"".equals(type) || (fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str)) == null) ? type : MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
    }

    /* renamed from: J1 */
    public static boolean m16453J1(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return simpleDateFormat.format(new Date()).equals(simpleDateFormat.format(date));
    }

    /* renamed from: J2 */
    public static String m16454J2(Date date) {
        return f14429e ? new SimpleDateFormat("mm:ss.SSS", Locale.getDefault()).format(date) : DateFormat.is24HourFormat(Globals.m7388i0().getApplicationContext()) ? new SimpleDateFormat("H:mm", Locale.getDefault()).format(date) : new SimpleDateFormat("h:mm a", Locale.getDefault()).format(date);
    }

    /* renamed from: K */
    public static void m16455K(String str, Uri uri) {
        String strM16520c0 = m16520c0();
        if (TextUtils.isEmpty(str) && m16417A1(uri)) {
            str = uri.getPath();
        }
        if (TextUtils.isEmpty(str) || !str.startsWith(strM16520c0)) {
            return;
        }
        new File(str).delete();
    }

    /* renamed from: K0 */
    public static String m16456K0(Context context) throws JSONException {
        String strSubstring = "";
        if (context == null) {
            return "";
        }
        String strM7450L0 = Globals.m7388i0().m7450L0();
        if (!C5170o0.m20170e(strM7450L0)) {
            String country = LocaleList.getDefault().get(0).getCountry();
            try {
                JSONArray jSONArray = new JSONArray(strM7450L0);
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    String string = jSONArray.getJSONObject(i9).getString("displayName");
                    ULogUtility.m16670f("CLUtility", "phone info " + string);
                    String strSubstring2 = string.substring(0, string.indexOf(":"));
                    strSubstring = string.substring(string.indexOf(":"));
                    Pair<String, Integer> pairM16460L0 = m16460L0(Integer.valueOf(strSubstring2).intValue());
                    if (i9 == 0 || ((String) pairM16460L0.first).contains(country)) {
                        strSubstring = context.getString(((Integer) pairM16460L0.second).intValue()) + strSubstring;
                    }
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        return strSubstring;
    }

    /* renamed from: K1 */
    public static boolean m16457K1(String str, Uri uri) throws FileNotFoundException {
        Pair<Integer, Integer> pairM16503X0 = m16503X0(str, uri);
        return ((Integer) pairM16503X0.first).intValue() > 0 && ((Integer) pairM16503X0.second).intValue() > 0;
    }

    /* renamed from: K2 */
    public static String m16458K2(Date date) {
        return new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date);
    }

    /* renamed from: L */
    public static void m16459L(Context context, String str) {
        m16451J(m16521c1(context, str, false));
        m16451J(m16490T(context, str, false));
    }

    /* renamed from: L0 */
    public static Pair<String, Integer> m16460L0(int i9) {
        String str;
        int i10;
        if (i9 == 33) {
            str = "FR";
            i10 = R.string.France;
        } else if (i9 == 34) {
            str = "ES";
            i10 = R.string.Spain;
        } else if (i9 == 81) {
            str = "JP";
            i10 = R.string.Japan;
        } else if (i9 != 82) {
            switch (i9) {
                case 1:
                    str = "US/CA";
                    i10 = R.string.US_Canada;
                    break;
                case 31:
                    str = "NL";
                    i10 = R.string.Netherlands;
                    break;
                case 39:
                    str = "IT";
                    i10 = R.string.Italy;
                    break;
                case 41:
                    str = "CH";
                    i10 = R.string.Switzerland;
                    break;
                case 44:
                    str = "GB";
                    i10 = R.string.United_Kingdom;
                    break;
                case 49:
                    str = "DE";
                    i10 = R.string.Germany;
                    break;
                case 52:
                    str = "MX";
                    i10 = R.string.Mexico;
                    break;
                case 55:
                    str = "BR";
                    i10 = R.string.Brazil;
                    break;
                case 84:
                    str = "VN";
                    i10 = R.string.Vietnam;
                    break;
                case 86:
                    str = "CN";
                    i10 = R.string.China;
                    break;
                case 91:
                    str = "IN";
                    i10 = R.string.India;
                    break;
                case 351:
                    str = "PT";
                    i10 = R.string.Portugal;
                    break;
                case 852:
                    str = "HK";
                    i10 = R.string.Hong_Kong;
                    break;
                case 886:
                    str = "TW";
                    i10 = R.string.Taiwan;
                    break;
                case 966:
                    str = "SA";
                    i10 = R.string.Saudi_Arabia;
                    break;
                case 971:
                    str = "AE";
                    i10 = R.string.United_Arab_Emirates;
                    break;
                default:
                    switch (i9) {
                        case 60:
                            str = "MY";
                            i10 = R.string.Malaysia;
                            break;
                        case 61:
                            str = "AU";
                            i10 = R.string.Australia;
                            break;
                        case 62:
                            str = "ID";
                            i10 = R.string.Indonesia;
                            break;
                        case 63:
                            str = "PH";
                            i10 = R.string.Philippines;
                            break;
                        case 64:
                            str = "NZ";
                            i10 = R.string.New_Zealand;
                            break;
                        case 65:
                            str = "SG";
                            i10 = R.string.Singapore;
                            break;
                        case 66:
                            str = "TH";
                            i10 = R.string.Thailand;
                            break;
                        default:
                            str = "";
                            i10 = 0;
                            break;
                    }
            }
        } else {
            str = "KR";
            i10 = R.string.South_Korea;
        }
        return new Pair<>(str, Integer.valueOf(i10));
    }

    /* renamed from: L1 */
    public static boolean m16461L1(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(5, -1);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        return date.before(calendar.getTime()) && date.after(calendar2.getTime());
    }

    /* renamed from: L2 */
    public static String m16462L2(Date date) {
        return DateFormat.is24HourFormat(Globals.m7388i0().getApplicationContext()) ? new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(date) : new SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.getDefault()).format(date);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0030 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Deprecated
    /* renamed from: M */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m16463M(String str, String str2) throws IOException {
        InputStream inputStream;
        boolean z8;
        FileOutputStream fileOutputStream;
        if (str2.isEmpty()) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = new URL(str).openConnection().getInputStream();
            z8 = false;
        } catch (MalformedURLException e9) {
            Log.d("CLUtility", Log.getStackTraceString(e9));
            inputStream = null;
            z8 = true;
            if (z8) {
            }
        } catch (IOException e10) {
            Log.d("CLUtility", Log.getStackTraceString(e10));
            inputStream = null;
            z8 = true;
            if (z8) {
            }
        }
        if (z8) {
            return false;
        }
        byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int i9 = inputStream.read(bArr);
                if (i9 == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i9);
            } catch (IOException e11) {
                Log.d("CLUtility", Log.getStackTraceString(e11));
                z8 = true;
                if (z8) {
                }
            } catch (OutOfMemoryError e12) {
                Log.d("CLUtility", Log.getStackTraceString(e12));
                z8 = true;
                if (z8) {
                }
            }
        }
        if (z8) {
            return false;
        }
        try {
            fileOutputStream = new FileOutputStream(str2);
        } catch (IOException e13) {
            e = e13;
        }
        try {
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (IOException e14) {
            e = e14;
            fileOutputStream2 = fileOutputStream;
            Log.d("CLUtility", Log.getStackTraceString(e));
            fileOutputStream = fileOutputStream2;
            z8 = true;
            if (fileOutputStream != null) {
            }
            return !z8;
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e15) {
                Log.d("CLUtility", Log.getStackTraceString(e15));
                z8 = true;
            }
        }
        return !z8;
    }

    /* renamed from: M0 */
    public static String m16464M0() {
        return m16512a0();
    }

    /* renamed from: M1 */
    public static /* synthetic */ boolean m16465M1(String str, File file) {
        return file.getPath().contains(str);
    }

    /* renamed from: M2 */
    public static String m16466M2(Date date) {
        return java.text.DateFormat.getDateInstance(2).format(date);
    }

    /* renamed from: N */
    public static String m16467N(String str) {
        if (str != null) {
            return str.replace("\n", "&#10;");
        }
        return null;
    }

    /* renamed from: N0 */
    public static Uri m16468N0() {
        ContentValues contentValues = new ContentValues();
        String strM16444H0 = m16444H0("image");
        String str = "U_" + m16443H() + ".jpg";
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", strM16444H0);
        } else {
            contentValues.put("_data", strM16444H0 + File.separator + str);
        }
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("_display_name", str);
        return Globals.m7372O().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    /* renamed from: N1 */
    public static /* synthetic */ void m16469N1(Group group, SharedPreferences sharedPreferences, InviteType inviteType, Activity activity, UserInfo userInfo, String[] strArr, String str, String str2, String str3, String str4) throws JSONException {
        if (str3 == null) {
            ULogUtility.m16670f("CLUtility", "Get invite url error");
            return;
        }
        if (!str3.equals("200")) {
            ULogUtility.m16670f("CLUtility", "Get invite url error, status : " + str3);
            return;
        }
        String string = null;
        try {
            string = new JSONObject(str4).getString("inviteURL");
            if (group != null) {
                group.f13707F = string;
                C2950b0.m14912k().m15062A(String.valueOf(group.f13727n), group, "inviteGroupLink");
            } else {
                sharedPreferences.edit().putString("inviteFriendLink", string).apply();
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        if (inviteType == InviteType.EMail) {
            m16570o2(activity, userInfo, group, string, strArr);
        } else {
            m16574p2(activity, userInfo, group, string);
        }
    }

    /* renamed from: N2 */
    public static String m16470N2(Date date) {
        return m16466M2(date) + StringUtils.SPACE + m16454J2(date);
    }

    /* renamed from: O */
    public static List<Friend> m16471O(List<Friend> list) {
        ArrayList arrayList = new ArrayList();
        for (Friend friend : list) {
            if (friend != null) {
                if (friend.f13645c == Globals.m7388i0().m7568k1().longValue()) {
                    C2950b0.m14899A().m15019k(friend, false, true);
                } else {
                    arrayList.add(friend);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: O0 */
    public static String m16472O0(Context context) {
        return context.getCacheDir().toString();
    }

    /* renamed from: O1 */
    public static void m16473O1(Activity activity, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "https://play.google.com/store/account/subscriptions";
        } else {
            str2 = "https://play.google.com/store/account/subscriptions?sku=" + str + "&package=" + Globals.m7387h1();
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str2));
        activity.startActivity(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: O2 */
    public static boolean m16474O2(String str, String str2) throws Throwable {
        ZipInputStream zipInputStream = null;
        try {
            try {
                ZipInputStream zipInputStream2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream2.getNextEntry();
                        if (nextEntry == null) {
                            C6370g.m24480b(zipInputStream2);
                            return true;
                        }
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                        String name = nextEntry.getName();
                        if (name.endsWith("/")) {
                            File file = new File(str2 + name);
                            if (file.getCanonicalPath().startsWith(str2)) {
                                if (file.exists() && !file.isDirectory()) {
                                    file.delete();
                                }
                                file.mkdirs();
                                zipInputStream2.closeEntry();
                            } else {
                                ULogUtility.m16683s("CLUtility", "[unZipStickerPack] exit illegal path : " + file.getAbsolutePath());
                            }
                        } else {
                            File file2 = new File(str2 + name);
                            if (file2.getCanonicalPath().startsWith(str2)) {
                                if (file2.exists()) {
                                    if (file2.isDirectory()) {
                                        m16447I(file2);
                                    } else {
                                        continue;
                                    }
                                }
                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + name);
                                    while (true) {
                                        try {
                                            int i9 = zipInputStream2.read(bArr);
                                            if (i9 == -1) {
                                                break;
                                            }
                                            byteArrayOutputStream.write(bArr, 0, i9);
                                            fileOutputStream.write(byteArrayOutputStream.toByteArray());
                                            byteArrayOutputStream.reset();
                                        } catch (Throwable th) {
                                            th = th;
                                            zipInputStream = fileOutputStream;
                                            C6370g.m24480b(zipInputStream);
                                            throw th;
                                        }
                                    }
                                    C6370g.m24480b(fileOutputStream);
                                    zipInputStream2.closeEntry();
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } else {
                                ULogUtility.m16683s("CLUtility", "[unZipStickerPack] exit illegal path : " + file2.getAbsolutePath());
                            }
                        }
                    } catch (IOException e9) {
                        e = e9;
                        zipInputStream = zipInputStream2;
                        e.printStackTrace();
                        C6370g.m24480b(zipInputStream);
                        return false;
                    } catch (OutOfMemoryError e10) {
                        e = e10;
                        zipInputStream = zipInputStream2;
                        e.printStackTrace();
                        C6370g.m24480b(zipInputStream);
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        zipInputStream = zipInputStream2;
                        C6370g.m24480b(zipInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e11) {
            e = e11;
        } catch (OutOfMemoryError e12) {
            e = e12;
        }
    }

    /* renamed from: P */
    public static FriendGroup m16475P(String str, int i9) {
        return new FriendGroup(str, i9);
    }

    /* renamed from: P0 */
    public static String m16476P0(Context context) {
        File file = new File(context.getFilesDir(), "DebugLog");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString();
    }

    /* renamed from: P1 */
    public static void m16477P1(Activity activity) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.cyberlink.U")));
        } catch (ActivityNotFoundException unused) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.cyberlink.U")));
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: P2 */
    public static String m16478P2(String str) {
        if (str != null) {
            return str.replaceAll("&#10;", "\n");
        }
        return null;
    }

    /* renamed from: Q */
    public static Activity m16479Q(View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    /* renamed from: Q0 */
    public static String m16480Q0(Context context) {
        return context == null ? "" : context.getDir("U", 0).toString();
    }

    /* renamed from: Q1 */
    public static ArrayList<File> m16481Q1(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        if (file.isFile()) {
            arrayList.add(file);
        } else if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                arrayList.addAll(m16481Q1(file2));
            }
        }
        return arrayList;
    }

    /* renamed from: Q2 */
    public static void m16482Q2(ZipInputStream zipInputStream) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        if (Globals.m7388i0().getApplicationContext() == null || zipInputStream == null) {
            return;
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                fileOutputStream2 = null;
                while (nextEntry != null) {
                    try {
                        if (!nextEntry.isDirectory()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(m16508Z());
                            String str = File.separator;
                            sb.append(str);
                            sb.append("black_list");
                            sb.append(str);
                            sb.append(nextEntry.getName());
                            sb.append(".tmp");
                            String string = sb.toString();
                            File file = new File(string);
                            if (!file.getCanonicalPath().startsWith(string)) {
                                ULogUtility.m16683s("CLUtility", "[updateBlackList] exit illegal path : " + string);
                                C6370g.m24480b(bufferedReader);
                                C6370g.m24480b(zipInputStream);
                                C6370g.m24480b(fileOutputStream2);
                                return;
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                            FileOutputStream fileOutputStream3 = new FileOutputStream(file);
                            while (true) {
                                try {
                                    int i9 = zipInputStream.read(bArr);
                                    if (i9 == -1) {
                                        break;
                                    } else {
                                        fileOutputStream3.write(bArr, 0, i9);
                                    }
                                } catch (Exception e9) {
                                    e = e9;
                                } catch (Throwable th) {
                                    th = th;
                                }
                            }
                            fileOutputStream3.flush();
                            fileOutputStream3.close();
                            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                            try {
                                boolean zM7309c = DeviceCapability.m7309c(bufferedReader2);
                                ULogUtility.m16683s("CLUtility", "[updateBlackList] new black list isValid : " + zM7309c);
                                if (zM7309c) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(m16508Z());
                                    String str2 = File.separator;
                                    sb2.append(str2);
                                    sb2.append("black_list");
                                    sb2.append(str2);
                                    sb2.append("devices_list.json");
                                    File file2 = new File(sb2.toString());
                                    if (file2.exists()) {
                                        file2.delete();
                                    }
                                    if (file.renameTo(file2)) {
                                        file.delete();
                                    }
                                }
                                bufferedReader = bufferedReader2;
                                fileOutputStream2 = fileOutputStream3;
                            } catch (Exception e10) {
                                e = e10;
                                bufferedReader = bufferedReader2;
                                fileOutputStream2 = fileOutputStream3;
                                e.printStackTrace();
                                C6370g.m24480b(bufferedReader);
                                C6370g.m24480b(zipInputStream);
                                C6370g.m24480b(fileOutputStream2);
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = bufferedReader2;
                                fileOutputStream = fileOutputStream3;
                                C6370g.m24480b(bufferedReader);
                                C6370g.m24480b(zipInputStream);
                                C6370g.m24480b(fileOutputStream);
                                throw th;
                            }
                        }
                        zipInputStream.closeEntry();
                        nextEntry = zipInputStream.getNextEntry();
                    } catch (Exception e11) {
                        e = e11;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e12) {
            e = e12;
            fileOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
        C6370g.m24480b(bufferedReader);
        C6370g.m24480b(zipInputStream);
        C6370g.m24480b(fileOutputStream2);
    }

    /* renamed from: R */
    public static ArrayList<Pair<Integer, Integer>> m16483R(String str) {
        ArrayList<Pair<Integer, Integer>> arrayList = new ArrayList<>();
        if (!C5170o0.m20170e(str)) {
            Matcher matcher = Pattern.compile("(?:[\\u2700-\\u27bf]|(?:[\\ud83c\\udde6-\\ud83c\\uddff]){2}|[\\ud800\\udc00-\\uDBFF\\uDFFF]|[\\u2600-\\u26FF])[\\ufe0e\\ufe0f]?(?:[\\u0300-\\u036f\\ufe20-\\ufe23\\u20d0-\\u20f0]|[\\ud83c\\udffb-\\ud83c\\udfff])?(?:\\u200d(?:[^\\ud800-\\udfff]|(?:[\\ud83c\\udde6-\\ud83c\\uddff]){2}|[\\ud800\\udc00-\\uDBFF\\uDFFF]|[\\u2600-\\u26FF])[\\ufe0e\\ufe0f]?(?:[\\u0300-\\u036f\\ufe20-\\ufe23\\u20d0-\\u20f0]|[\\ud83c\\udffb-\\ud83c\\udfff])?)*|[\\u0023-\\u0039]\\ufe0f?\\u20e3|\\u3299|\\u3297|\\u303d|\\u3030|\\u24c2|[\\ud83c\\udd70-\\ud83c\\udd71]|[\\ud83c\\udd7e-\\ud83c\\udd7f]|\\ud83c\\udd8e|[\\ud83c\\udd91-\\ud83c\\udd9a]|[\\ud83c\\udde6-\\ud83c\\uddff]|[\\ud83c\\ude01-\\ud83c\\ude02]|\\ud83c\\ude1a|\\ud83c\\ude2f|[\\ud83c\\ude32-\\ud83c\\ude3a]|[\\ud83c\\ude50-\\ud83c\\ude51]|\\u203c|\\u2049|[\\u25aa-\\u25ab]|\\u25b6|\\u25c0|[\\u25fb-\\u25fe]|\\u00a9|\\u00ae|\\u2122|\\u2139|\\ud83c\\udc04|[\\u2600-\\u26FF]|\\u2b05|\\u2b06|\\u2b07|\\u2b1b|\\u2b1c|\\u2b50|\\u2b55|\\u231a|\\u231b|\\u2328|\\u23cf|[\\u23e9-\\u23f3]|[\\u23f8-\\u23fa]|\\ud83c\\udccf|\\u2934|\\u2935|[\\u2190-\\u21ff]").matcher(str);
            while (matcher.find()) {
                arrayList.add(new Pair<>(Integer.valueOf(matcher.start()), Integer.valueOf(matcher.end())));
            }
        }
        return arrayList;
    }

    /* renamed from: R0 */
    public static Pair<String, String> m16484R0() {
        String property = System.getProperty("http.proxyHost");
        String property2 = System.getProperty("http.proxyPort");
        ULogUtility.m16670f("CLUtility", "proxyAddress : " + property + " proxyPort : " + property2);
        return new Pair<>(property, property2);
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03d2  */
    /* renamed from: R1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Pair<String, String> m16485R1(String str, String str2, Uri uri, int i9, boolean z8) throws Throwable {
        Bitmap bitmapM16493U;
        int i10;
        int i11;
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmapCreateBitmap;
        C4978a c4978a;
        Bitmap bitmapCreateBitmap2;
        if (!m16613z1(str2, uri)) {
            return null;
        }
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        String strM16521c1 = m16521c1(applicationContext, str, z8);
        String strM16490T = m16490T(applicationContext, str, z8);
        File file = new File(strM16521c1);
        File file2 = new File(strM16490T);
        String str3 = file.exists() ? strM16521c1 : null;
        String str4 = file2.exists() ? strM16490T : null;
        if (str3 != null && str4 != null) {
            return Pair.create(str3, str4);
        }
        boolean zEquals = "image/jpeg".equals(m16452J0(str2, uri));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            m16509Z0(str2, uri, options);
            int i12 = options.outWidth;
            int i13 = options.outHeight;
            int iMax = Math.max(i12, i13);
            if (i12 <= 0 || i13 <= 0) {
                Log.d("obtainImages", "[obtainImages] Decode File - 1 : width=" + i12 + ", height=" + i13 + ", srcImg=" + str2);
                return null;
            }
            if (zEquals) {
                if (iMax <= 1280) {
                    File file3 = new File(strM16490T + ".tmp");
                    if (C6369f.m24461c(str2, uri, file3)) {
                        file3.renameTo(new File(strM16490T));
                    }
                    str4 = strM16490T;
                }
                if (iMax <= 720) {
                    File file4 = new File(strM16521c1 + ".tmp");
                    if (C6369f.m24461c(str2, uri, file4)) {
                        file4.renameTo(new File(strM16521c1));
                    }
                    str3 = strM16521c1;
                }
                if (str3 != null && str4 != null) {
                    return Pair.create(str3, str4);
                }
            }
            String str5 = str4;
            String str6 = str3;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = f14425a;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inSampleSize = m16555l(options, 1280, 1280);
            try {
                bitmapM16493U = m16493U(str2, uri, options);
            } catch (OutOfMemoryError e9) {
                if (e9.getMessage() != null) {
                    Log.e("obtainImages", e9.getMessage());
                }
                bitmapM16493U = null;
            }
            if (bitmapM16493U == null) {
                Log.e("obtainImages", "failed to decode " + str2 + " inSampleSize= " + options.inSampleSize);
                return null;
            }
            int i14 = options.outWidth;
            int i15 = options.outHeight;
            int iM18873c = i9;
            if (iM18873c == -1) {
                try {
                    iM18873c = C4754a.m18873c(str2, uri);
                } catch (IOException e10) {
                    Log.e("obtainImages", "cannot get exif attribute", e10);
                }
            }
            if (str6 == null) {
                Matrix matrix = new Matrix();
                matrix.postRotate(iM18873c);
                try {
                    String str7 = strM16521c1 + ".tmp";
                    if (z8) {
                        float fM16500W0 = m16500W0(i14, i15, 720);
                        matrix.postScale(fM16500W0, fM16500W0);
                        boolean z9 = i14 < i15;
                        int i16 = z9 ? i14 : i15;
                        i11 = i16;
                        bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapM16493U, z9 ? 0 : (i14 - i15) / 2, z9 ? (i15 - i14) / 2 : 0, i11, i16, matrix, true);
                    } else {
                        float fM16436F0 = m16436F0(i14, i15, 720);
                        matrix.postScale(fM16436F0, fM16436F0);
                        i11 = i14;
                        bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapM16493U, 0, 0, i11, i15, matrix, true);
                    }
                    bitmap = bitmapCreateBitmap2;
                    i10 = i15;
                    try {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(str7);
                            try {
                                i11 = i14;
                                try {
                                    boolean zCompress = bitmap.compress(f14426b, 70, fileOutputStream);
                                    fileOutputStream.close();
                                    if (!zCompress) {
                                        Log.e("obtainImages", "failed to compress " + str2 + " bitmap into small image !!!");
                                        return null;
                                    }
                                    m16586s2(str2, uri, str7, bitmap);
                                    File file5 = new File(str7);
                                    File file6 = new File(strM16521c1);
                                    if (file5.exists()) {
                                        if (file6.exists()) {
                                            file6.delete();
                                        }
                                        file5.renameTo(file6);
                                    }
                                    str6 = strM16521c1;
                                } catch (Throwable th) {
                                    th = th;
                                    Throwable th2 = th;
                                    try {
                                        fileOutputStream.close();
                                        throw th2;
                                    } catch (Throwable th3) {
                                        th2.addSuppressed(th3);
                                        throw th2;
                                    }
                                }
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (FileNotFoundException unused) {
                            Log.d("obtainImages", "[obtainImages] FileNotFoundException : srcImg=" + str2);
                            Log.d("obtainImages", "[obtainImages] FileNotFoundException : srcImgUri=" + uri);
                            Log.d("obtainImages", "[obtainImages] failed to save bitmap into " + strM16521c1);
                            str6 = null;
                            if (str5 == null) {
                            }
                            bitmapM16493U.recycle();
                            if (bitmap != null) {
                            }
                            if (bitmap2 != null) {
                            }
                            m16455K(str2, uri);
                            return Pair.create(str6, str5);
                        } catch (Exception e11) {
                            e = e11;
                            Log.d("obtainImages", "[obtainImages] Exception : srcImg=" + str2);
                            Log.d("obtainImages", "[obtainImages] Exception : srcImgUri=" + uri);
                            e.printStackTrace();
                            str6 = null;
                            if (str5 == null) {
                            }
                            bitmapM16493U.recycle();
                            if (bitmap != null) {
                            }
                            if (bitmap2 != null) {
                            }
                            m16455K(str2, uri);
                            return Pair.create(str6, str5);
                        } catch (OutOfMemoryError e12) {
                            e = e12;
                            e.printStackTrace();
                            str6 = null;
                            if (str5 == null) {
                            }
                            bitmapM16493U.recycle();
                            if (bitmap != null) {
                            }
                            if (bitmap2 != null) {
                            }
                            m16455K(str2, uri);
                            return Pair.create(str6, str5);
                        }
                    } catch (FileNotFoundException unused2) {
                        i11 = i14;
                        Log.d("obtainImages", "[obtainImages] FileNotFoundException : srcImg=" + str2);
                        Log.d("obtainImages", "[obtainImages] FileNotFoundException : srcImgUri=" + uri);
                        Log.d("obtainImages", "[obtainImages] failed to save bitmap into " + strM16521c1);
                        str6 = null;
                        if (str5 == null) {
                        }
                        bitmapM16493U.recycle();
                        if (bitmap != null) {
                        }
                        if (bitmap2 != null) {
                        }
                        m16455K(str2, uri);
                        return Pair.create(str6, str5);
                    } catch (Exception e13) {
                        e = e13;
                        i11 = i14;
                        Log.d("obtainImages", "[obtainImages] Exception : srcImg=" + str2);
                        Log.d("obtainImages", "[obtainImages] Exception : srcImgUri=" + uri);
                        e.printStackTrace();
                        str6 = null;
                        if (str5 == null) {
                        }
                        bitmapM16493U.recycle();
                        if (bitmap != null) {
                        }
                        if (bitmap2 != null) {
                        }
                        m16455K(str2, uri);
                        return Pair.create(str6, str5);
                    } catch (OutOfMemoryError e14) {
                        e = e14;
                        i11 = i14;
                        e.printStackTrace();
                        str6 = null;
                        if (str5 == null) {
                        }
                        bitmapM16493U.recycle();
                        if (bitmap != null) {
                        }
                        if (bitmap2 != null) {
                        }
                        m16455K(str2, uri);
                        return Pair.create(str6, str5);
                    }
                } catch (FileNotFoundException unused3) {
                    i10 = i15;
                    i11 = i14;
                    bitmap = null;
                } catch (Exception e15) {
                    e = e15;
                    i10 = i15;
                    i11 = i14;
                    bitmap = null;
                } catch (OutOfMemoryError e16) {
                    e = e16;
                    i10 = i15;
                    i11 = i14;
                    bitmap = null;
                }
            } else {
                i10 = i15;
                i11 = i14;
                bitmap = null;
            }
            if (str5 == null) {
                Matrix matrix2 = new Matrix();
                matrix2.postRotate(iM18873c);
                try {
                    String str8 = strM16490T + ".tmp";
                    int i17 = i11;
                    int i18 = i10;
                    float fM16436F02 = m16436F0(i17, i18, 1280);
                    matrix2.postScale(fM16436F02, fM16436F02);
                    bitmapCreateBitmap = Bitmap.createBitmap(bitmapM16493U, 0, 0, i17, i18, matrix2, true);
                    try {
                        try {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(str8);
                            boolean zCompress2 = bitmapCreateBitmap.compress(f14426b, 60, fileOutputStream2);
                            fileOutputStream2.close();
                            if (!zCompress2) {
                                Log.e("obtainImages", "failed to compress " + str2 + " bitmap into big image !!!");
                                return null;
                            }
                            if (uri != null) {
                                try {
                                    InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
                                    if (inputStreamOpenInputStream != null) {
                                        try {
                                            c4978a = new C4978a(inputStreamOpenInputStream);
                                        } finally {
                                        }
                                    } else {
                                        c4978a = null;
                                    }
                                    if (inputStreamOpenInputStream != null) {
                                        inputStreamOpenInputStream.close();
                                    }
                                } catch (IOException e17) {
                                    Log.e("obtainImages", "cannot set exif attribute", e17);
                                }
                            } else {
                                c4978a = null;
                            }
                            if (c4978a == null) {
                                c4978a = new C4978a(str2);
                            }
                            C4978a c4978a2 = new C4978a(str8);
                            C4754a.m18871a(c4978a, c4978a2);
                            c4978a2.m19280H("ImageWidth", String.valueOf(bitmapCreateBitmap.getWidth()));
                            c4978a2.m19280H("ImageLength", String.valueOf(bitmapCreateBitmap.getHeight()));
                            c4978a2.m19280H("Orientation", Integer.toString(0));
                            c4978a2.m19278F();
                            File file7 = new File(str8);
                            File file8 = new File(strM16490T);
                            if (file7.exists()) {
                                if (file8.exists()) {
                                    file8.delete();
                                }
                                file7.renameTo(file8);
                            }
                            str5 = strM16490T;
                            bitmap2 = bitmapCreateBitmap;
                        } catch (FileNotFoundException unused4) {
                            Log.d("obtainImages", "[obtainImages] FileNotFoundException : srcImg=" + str2);
                            Log.d("obtainImages", "[obtainImages] failed to save bitmap into " + strM16521c1);
                            bitmap2 = bitmapCreateBitmap;
                            str5 = null;
                            bitmapM16493U.recycle();
                            if (bitmap != null) {
                            }
                            if (bitmap2 != null) {
                            }
                            m16455K(str2, uri);
                            return Pair.create(str6, str5);
                        }
                    } catch (Exception e18) {
                        e = e18;
                        Log.d("obtainImages", "[obtainImages] Exception : srcImg=" + str2);
                        e.printStackTrace();
                        bitmap2 = bitmapCreateBitmap;
                        str5 = null;
                        bitmapM16493U.recycle();
                        if (bitmap != null) {
                        }
                        if (bitmap2 != null) {
                        }
                        m16455K(str2, uri);
                        return Pair.create(str6, str5);
                    } catch (OutOfMemoryError e19) {
                        e = e19;
                        e.printStackTrace();
                        bitmap2 = bitmapCreateBitmap;
                        str5 = null;
                        bitmapM16493U.recycle();
                        if (bitmap != null) {
                        }
                        if (bitmap2 != null) {
                        }
                        m16455K(str2, uri);
                        return Pair.create(str6, str5);
                    }
                } catch (FileNotFoundException unused5) {
                    bitmapCreateBitmap = null;
                } catch (Exception e20) {
                    e = e20;
                    bitmapCreateBitmap = null;
                } catch (OutOfMemoryError e21) {
                    e = e21;
                    bitmapCreateBitmap = null;
                }
            } else {
                bitmap2 = null;
            }
            bitmapM16493U.recycle();
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (bitmap2 != null) {
                bitmap2.recycle();
            }
            m16455K(str2, uri);
            return Pair.create(str6, str5);
        } catch (OutOfMemoryError e22) {
            e22.printStackTrace();
            return null;
        }
    }

    /* renamed from: R2 */
    public static void m16486R2(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (Globals.m7388i0().m7494U1()) {
            return;
        }
        powerManager.newWakeLock(805306378, "UScreenLock").acquire(3000L);
    }

    /* renamed from: S */
    public static String m16487S(String str, boolean z8) {
        String str2 = Globals.m7388i0().m7409C1().booleanValue() ? "" : ".jpg";
        if (!z8) {
            return str + "_1280" + str2;
        }
        return "user_" + str + "_1280" + str2;
    }

    /* renamed from: S0 */
    public static String m16488S0(Context context, String str) throws IOException {
        Log.d("playhelper", "the android version is above 4.1");
        return m16573p1(str + "_", ".m4a", new File(m16472O0(context)));
    }

    /* renamed from: S1 */
    public static String m16489S1(String str, String str2, Uri uri) {
        return m16492T1(str, str2, uri, -1);
    }

    /* renamed from: T */
    public static String m16490T(Context context, String str, boolean z8) {
        return m16472O0(context) + File.separator + m16487S(str, z8);
    }

    /* renamed from: T0 */
    public static int m16491T0(Context context, String str, String str2, String str3) {
        return context.getResources().getIdentifier(str, str2, str3);
    }

    /* renamed from: T1 */
    public static String m16492T1(String str, String str2, Uri uri, int i9) {
        return m16495U1(str, str2, uri, i9, false);
    }

    /* renamed from: U */
    public static Bitmap m16493U(String str, Uri uri, BitmapFactory.Options options) throws IOException {
        Bitmap bitmapDecodeStream = null;
        if (!m16597v1(uri)) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return BitmapFactory.decodeFile(str, options);
        }
        try {
            InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
            try {
                bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                if (inputStreamOpenInputStream == null) {
                    return bitmapDecodeStream;
                }
                inputStreamOpenInputStream.close();
                return bitmapDecodeStream;
            } finally {
            }
        } catch (IOException e9) {
            e9.printStackTrace();
            return bitmapDecodeStream;
        }
    }

    /* renamed from: U0 */
    public static int m16494U0(String str) {
        if (str == null) {
            return -1;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("results")) {
                return jSONObject.getJSONArray("results").length();
            }
            return -1;
        } catch (JSONException e9) {
            e9.printStackTrace();
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x0280  */
    /* renamed from: U1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m16495U1(String str, String str2, Uri uri, int i9, boolean z8) throws Throwable {
        Bitmap bitmapM16493U;
        Bitmap bitmap;
        String str3;
        Bitmap bitmapCreateBitmap;
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        String strM16521c1 = m16521c1(applicationContext, str, z8);
        if (!new File(strM16521c1).exists()) {
            if (!m16613z1(str2, uri)) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                m16509Z0(str2, uri, options);
                int i10 = options.outWidth;
                int i11 = options.outHeight;
                if (i10 <= 0 || i11 <= 0) {
                    Log.d("obtainSmallImage", "[obtainSmallImage] Decode File - 1 : width=" + i10 + ", height=" + i11 + ", srcImg=" + str2 + ", srcImgUri=" + uri);
                    return null;
                }
                if (Math.max(i10, i11) <= 720 && "image/jpeg".equals(m16452J0(str2, uri))) {
                    try {
                        File file = new File(strM16521c1 + ".tmp");
                        if (!C6369f.m24461c(str2, uri, file)) {
                            return strM16521c1;
                        }
                        file.renameTo(new File(strM16521c1));
                        return strM16521c1;
                    } catch (OutOfMemoryError e9) {
                        e9.printStackTrace();
                        return null;
                    }
                }
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = f14425a;
                options.inPurgeable = true;
                options.inInputShareable = true;
                options.inSampleSize = m16555l(options, 720, 720);
                try {
                    bitmapM16493U = m16493U(str2, uri, options);
                } catch (OutOfMemoryError e10) {
                    if (e10.getMessage() != null) {
                        Log.e("obtainSmallImage", e10.getMessage());
                    }
                    bitmapM16493U = null;
                }
                if (bitmapM16493U == null) {
                    Log.e("obtainSmallImage", "failed to decode " + str2 + " inSampleSize= " + options.inSampleSize);
                    return null;
                }
                int i12 = options.outWidth;
                int i13 = options.outHeight;
                if (i12 <= 0 || i13 <= 0) {
                    Log.d("obtainSmallImage", "[obtainSmallImage] Decode File - 2 : width=" + i12 + ", height=" + i13 + ", srcImg=" + str2);
                    bitmapM16493U.recycle();
                    return null;
                }
                int iM18872b = i9;
                if (iM18872b == -1) {
                    try {
                        iM18872b = C4754a.m18872b(str2);
                    } catch (IOException e11) {
                        Log.e("obtainSmallImage", "cannot get exif attribute", e11);
                    }
                }
                Matrix matrix = new Matrix();
                matrix.postRotate(iM18872b);
                try {
                    str3 = strM16521c1 + ".tmp";
                    if (z8) {
                        float fM16500W0 = m16500W0(i12, i13, 720);
                        matrix.postScale(fM16500W0, fM16500W0);
                        boolean z9 = i12 < i13;
                        int i14 = z9 ? i12 : i13;
                        bitmapCreateBitmap = Bitmap.createBitmap(bitmapM16493U, z9 ? 0 : (i12 - i13) / 2, z9 ? (i13 - i12) / 2 : 0, i14, i14, matrix, true);
                    } else {
                        float fM16436F0 = m16436F0(i12, i13, 720);
                        matrix.postScale(fM16436F0, fM16436F0);
                        bitmapCreateBitmap = Bitmap.createBitmap(bitmapM16493U, 0, 0, i12, i13, matrix, true);
                    }
                    bitmap = bitmapCreateBitmap;
                } catch (FileNotFoundException unused) {
                    bitmap = null;
                } catch (Exception e12) {
                    e = e12;
                    bitmap = null;
                } catch (OutOfMemoryError e13) {
                    e = e13;
                    bitmap = null;
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(str3);
                    try {
                        Bitmap.CompressFormat compressFormat = f14426b;
                        boolean zCompress = bitmap.compress(compressFormat, 70, fileOutputStream);
                        fileOutputStream.close();
                        if (!zCompress) {
                            Log.d("obtainSmallImage", "[obtainSmallImage] Compress image failed(" + str + ")");
                            String strM16525d1 = m16525d1(applicationContext, str, z8);
                            File file2 = new File(str3);
                            File file3 = new File(strM16525d1);
                            if (file2.exists()) {
                                if (file3.exists()) {
                                    file3.delete();
                                }
                                file2.renameTo(file3);
                                Log.d("obtainSmallImage", "[obtainSmallImage] The failed image path =" + strM16525d1);
                            }
                            fileOutputStream = new FileOutputStream(str3);
                            try {
                                boolean zCompress2 = bitmap.compress(compressFormat, 70, fileOutputStream);
                                fileOutputStream.close();
                                if (zCompress2) {
                                    Log.d("obtainSmallImage", "[obtainSmallImage] Retry to compress success.");
                                } else {
                                    Log.d("obtainSmallImage", "[obtainSmallImage] Retry to compress failed then set small image path to null.");
                                    str3 = null;
                                }
                            } finally {
                            }
                        }
                    } finally {
                    }
                } catch (FileNotFoundException unused2) {
                    Log.d("obtainSmallImage", "[obtainSmallImage] FileNotFoundException : srcImg=" + str2);
                    Log.d("obtainSmallImage", "[obtainSmallImage] FileNotFoundException : srcImgUri=" + uri);
                    Log.d("obtainSmallImage", "[obtainSmallImage] failed to save bitmap into " + strM16521c1);
                    strM16521c1 = null;
                    bitmapM16493U.recycle();
                    if (bitmap != null) {
                    }
                    return strM16521c1;
                } catch (Exception e14) {
                    e = e14;
                    Log.d("obtainSmallImage", "[obtainSmallImage] Exception : srcImg=" + str2);
                    Log.d("obtainSmallImage", "[obtainSmallImage] Exception : srcImgUri=" + uri);
                    e.printStackTrace();
                    strM16521c1 = null;
                    bitmapM16493U.recycle();
                    if (bitmap != null) {
                    }
                    return strM16521c1;
                } catch (OutOfMemoryError e15) {
                    e = e15;
                    e.printStackTrace();
                    strM16521c1 = null;
                    bitmapM16493U.recycle();
                    if (bitmap != null) {
                    }
                    return strM16521c1;
                }
                if (str3 != null) {
                    m16586s2(str2, uri, str3, bitmap);
                    File file4 = new File(str3);
                    File file5 = new File(strM16521c1);
                    if (file4.exists()) {
                        if (file5.exists()) {
                            file5.delete();
                        }
                        file4.renameTo(file5);
                    }
                } else {
                    strM16521c1 = null;
                }
                bitmapM16493U.recycle();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            } catch (OutOfMemoryError e16) {
                e16.printStackTrace();
                return null;
            }
        }
        return strM16521c1;
    }

    /* renamed from: V */
    public static String m16496V() {
        return m16508Z() + File.separator + "/tmp/CL_U_DATA.zip";
    }

    /* renamed from: V0 */
    public static UserInfo m16497V0(Context context) {
        SharedPreferences sharedPreferences;
        UserInfo userInfo = new UserInfo();
        if (context == null || (sharedPreferences = context.getSharedPreferences("U", 0)) == null) {
            return null;
        }
        if (userInfo.m15776g(Globals.m7388i0().m7409C1().booleanValue() ? C5127a.m19997a(sharedPreferences.getString("selfInfo", "")) : sharedPreferences.getString("selfInfo", ""))) {
            return userInfo;
        }
        return null;
    }

    /* renamed from: V1 */
    public static void m16498V1(Activity activity) throws JSONException, IOException {
        String str;
        String[] strArr;
        int i9;
        String str2;
        String[] strArr2;
        int i10;
        int i11 = 0;
        if (AbstractC5146g0.m20046d(SplashActivity.f11362p, false, activity)) {
            Log.d("CLUtility", "[parseAndStoreBuildInStickerInfo] Had copied.");
            return;
        }
        Log.d("CLUtility", "[parseAndStoreBuildInStickerInfo] before get assets");
        AssetManager assets = activity.getAssets();
        int i12 = 1;
        try {
            try {
                if (assets == null) {
                    C6478z.m24811c("CLUtility", "parseAndStoreBuildInStickerInfo: Null Assets.");
                    return;
                }
                Log.d("CLUtility", "[parseAndStoreBuildInStickerInfo] ready to store");
                m16423C();
                String str3 = "data" + File.separator + "StickerPack";
                String[] list = assets.list(str3);
                if (list == null) {
                    C6478z.m24813e("CLUtility", "parseAndStoreBuildInStickerInfo: No build in stickers.");
                    return;
                }
                Log.d("CLUtility", "[parseAndStoreBuildInStickerInfo] Parsing Start");
                int length = list.length;
                int i13 = 0;
                while (i13 < length) {
                    String str4 = list[i13];
                    long jLongValue = Long.valueOf(str4).longValue();
                    String str5 = str3 + File.separator + str4;
                    String strM16541h1 = m16541h1(jLongValue);
                    String[] list2 = assets.list(str5);
                    if (list2 != null) {
                        List listAsList = Arrays.asList(list2);
                        if (listAsList.contains("pack.info") && listAsList.contains("sticker.list")) {
                            m16567o(Long.valueOf(str4).longValue());
                            int length2 = list2.length;
                            while (i11 < length2) {
                                String str6 = list2[i11];
                                if (str6.equals("pack.info") || str6.equals("sticker.list")) {
                                    str2 = str3;
                                    strArr2 = list;
                                    i10 = length;
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(str5);
                                    str2 = str3;
                                    String str7 = File.separator;
                                    sb.append(str7);
                                    sb.append(str6);
                                    String string = sb.toString();
                                    strArr2 = list;
                                    i10 = length;
                                    File file = new File(strM16541h1 + str7 + str6);
                                    if (file.exists() && file.isDirectory()) {
                                        m16447I(file);
                                    }
                                    if (!file.exists()) {
                                        InputStream inputStreamOpen = assets.open(string);
                                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                                        try {
                                            m16591u(inputStreamOpen, fileOutputStream);
                                        } catch (IOException e9) {
                                            e9.printStackTrace();
                                        }
                                        inputStreamOpen.close();
                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                    }
                                }
                                i11++;
                                str3 = str2;
                                list = strArr2;
                                length = i10;
                            }
                            str = str3;
                            strArr = list;
                            i9 = length;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str5);
                            String str8 = File.separator;
                            sb2.append(str8);
                            sb2.append("pack.info");
                            InputStream inputStreamOpen2 = assets.open(sb2.toString());
                            StickerPackObj stickerPackObjM16507Y1 = m16507Y1(inputStreamOpen2);
                            inputStreamOpen2.close();
                            if (stickerPackObjM16507Y1 == null) {
                                Log.d("CLUtility", "[parsePackInfo] return null");
                            } else {
                                InputStream inputStreamOpen3 = assets.open(str5 + str8 + "sticker.list");
                                m16504X1(inputStreamOpen3, stickerPackObjM16507Y1);
                                inputStreamOpen3.close();
                            }
                        } else {
                            str = str3;
                            strArr = list;
                            i9 = length;
                            Log.d("CLUtility", "[parseAndStoreBuildInStickerInfo] build in sticker info does not exist: " + str4);
                        }
                    } else {
                        str = str3;
                        strArr = list;
                        i9 = length;
                        Object[] objArr = new Object[i12];
                        objArr[0] = "parseAndStoreBuildInStickerInfo: No sticker data in pack folder.";
                        C6478z.m24813e("CLUtility", objArr);
                    }
                    i13++;
                    str3 = str;
                    list = strArr;
                    length = i9;
                    i11 = 0;
                    i12 = 1;
                }
                Log.d("CLUtility", "[parseAndStoreBuildInStickerInfo] Parsing End");
                AbstractC5146g0.m20050h(SplashActivity.f11362p, Boolean.TRUE, activity);
            } catch (IOException e10) {
                e10.printStackTrace();
                C6478z.m24811c("CLUtility", "parseAndStoreBuildInStickerInfo: parse and store build in sticker info fail.");
            }
        } catch (OutOfMemoryError e11) {
            e11.printStackTrace();
        }
    }

    /* renamed from: W */
    public static String m16499W() {
        return m16508Z() + File.separator + "/tmp/CL_U_DB.zip";
    }

    /* renamed from: W0 */
    public static float m16500W0(int i9, int i10, int i11) {
        float f9 = i9 < i10 ? i11 / i9 : i11 / i10;
        if (f9 > 1.0f) {
            f9 = 1.0f;
        }
        return f9 < BitmapDescriptorFactory.HUE_RED ? BitmapDescriptorFactory.HUE_RED : f9;
    }

    /* renamed from: W1 */
    public static List<C2973l0> m16501W1(String str, String str2) throws JSONException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        JSONArray jSONArrayM20196r = C5172p.m20196r(str2);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    C2973l0 c2973l0M20188j = C5172p.m20188j(str, jSONArrayM20196r.getJSONObject(i9));
                    if (c2973l0M20188j != null) {
                        arrayList.add(c2973l0M20188j);
                        C3061a.a aVarM20192n = C5172p.m20192n(jSONArrayM20196r.getJSONObject(i9));
                        if (!aVarM20192n.m15800a()) {
                            arrayList2.add(new C2971k0(String.valueOf(c2973l0M20188j.m15144p()), aVarM20192n));
                        }
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            C2950b0.m14914m().m14713j(arrayList);
            C2950b0.m14915n().m15109h(arrayList2);
        }
        return arrayList;
    }

    /* renamed from: X */
    public static String m16502X() {
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        return applicationContext != null ? m16476P0(applicationContext) : "";
    }

    /* renamed from: X0 */
    public static Pair<Integer, Integer> m16503X0(String str, Uri uri) throws FileNotFoundException {
        return m16506Y0(str, uri, true);
    }

    /* renamed from: X1 */
    public static void m16504X1(InputStream inputStream, StickerPackObj stickerPackObj) throws JSONException, IOException {
        String strM16530e2 = m16530e2(inputStream);
        if (strM16530e2.isEmpty()) {
            Log.d("CLUtility", "[parseAndStoreStickersInfo] sticker info is null or empty");
        }
        List<StickerObj> listM20200v = C5172p.m20200v(C5172p.m20196r(strM16530e2), stickerPackObj);
        if (listM20200v == null) {
            Log.d("CLUtility", "[parseAndStoreStickersInfo] sticker list is null");
            return;
        }
        for (StickerObj stickerObj : listM20200v) {
            Pair<Integer, Integer> pairM16545i1 = m16545i1(stickerObj.m16282g());
            stickerObj.m16295t(((Integer) pairM16545i1.first).intValue());
            stickerObj.m16292q(((Integer) pairM16545i1.second).intValue());
        }
        if (C5164m0.m20107l() == stickerPackObj.m14803g()) {
            stickerPackObj.m14818v(true);
        }
        C2950b0.m14924w().m15277e(listM20200v);
        C2950b0.m14925x().m15288f(stickerPackObj, false, true);
    }

    /* renamed from: Y */
    public static String m16505Y(String str) {
        return Globals.m7372O().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + str;
    }

    /* renamed from: Y0 */
    public static Pair<Integer, Integer> m16506Y0(String str, Uri uri, boolean z8) throws FileNotFoundException {
        int iM18873c;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (m16597v1(uri)) {
            try {
                BitmapFactory.decodeStream(Globals.m7372O().getContentResolver().openInputStream(uri), null, options);
            } catch (FileNotFoundException e9) {
                e9.printStackTrace();
            }
        } else {
            if (TextUtils.isEmpty(str)) {
                return Pair.create(-1, -1);
            }
            BitmapFactory.decodeFile(str, options);
        }
        int i9 = options.outWidth;
        int i10 = options.outHeight;
        if (i9 == -1 || i10 == -1) {
            return Pair.create(Integer.valueOf(i9), Integer.valueOf(i10));
        }
        if (z8) {
            try {
                iM18873c = C4754a.m18873c(str, uri);
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        } else {
            iM18873c = 0;
        }
        return (iM18873c == 90 || iM18873c == 270) ? Pair.create(Integer.valueOf(i10), Integer.valueOf(i9)) : Pair.create(Integer.valueOf(i9), Integer.valueOf(i10));
    }

    /* renamed from: Y1 */
    public static StickerPackObj m16507Y1(InputStream inputStream) throws IOException {
        String strM16534f2 = m16534f2(inputStream, "UTF-8");
        if (strM16534f2.isEmpty()) {
            Log.d("CLUtility", "[parsePackInfo] pack info is null or empty");
        }
        List<StickerPackObj> listM20177D = C5172p.m20177D(C5172p.m20196r(strM16534f2), true, false);
        if (listM20177D == null || listM20177D.size() != 1) {
            return null;
        }
        return listM20177D.get(0);
    }

    /* renamed from: Z */
    public static String m16508Z() throws IOException {
        File externalFilesDir = Globals.m7372O().getExternalFilesDir(null);
        if (externalFilesDir == null) {
            return null;
        }
        String parent = externalFilesDir.getParent();
        m16415A(parent);
        return parent;
    }

    /* renamed from: Z0 */
    public static void m16509Z0(String str, Uri uri, BitmapFactory.Options options) {
        if (uri == null) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            BitmapFactory.decodeFile(str, options);
        } else {
            try {
                BitmapFactory.decodeStream(Globals.m7372O().getContentResolver().openInputStream(uri), null, options);
            } catch (FileNotFoundException e9) {
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: Z1 */
    public static Uri m16510Z1(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str);
    }

    /* renamed from: a0 */
    public static String m16512a0() {
        return m16516b0(m16443H());
    }

    /* renamed from: a1 */
    public static String m16513a1(String str, boolean z8) {
        String str2 = Globals.m7388i0().m7409C1().booleanValue() ? "" : ".jpg";
        if (!z8) {
            return str + "_720" + str2;
        }
        return "user_" + str + "_720" + str2;
    }

    /* renamed from: a2 */
    public static String m16514a2(Context context, Uri uri, String str, String[] strArr) {
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            try {
                cursorQuery.moveToFirst();
                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                cursorQuery.close();
                return string;
            } finally {
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b0 */
    public static String m16516b0(String str) {
        return Globals.m7372O().getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "U_" + str + ".jpg";
    }

    /* renamed from: b1 */
    public static String m16517b1(Context context, String str) {
        return m16521c1(context, str, false);
    }

    /* renamed from: b2 */
    public static void m16518b2(boolean z8) {
        NetworkLive.getOrganizationId(Globals.m7388i0().m7506X()).done(new C3135b(z8));
    }

    /* renamed from: c */
    public static String m16519c(String str) {
        String strValueOf;
        try {
            strValueOf = String.valueOf(C4755b.m18879b(Long.valueOf(str).longValue()) / 1000);
        } catch (Exception e9) {
            e9.printStackTrace();
            strValueOf = "0";
        }
        return m16531f(strValueOf);
    }

    /* renamed from: c0 */
    public static String m16520c0() {
        File file = new File(m16508Z(), "shareFile");
        if (!file.exists()) {
            file.mkdir();
        }
        Log.d("CLUtility", "ShareFileFolder = " + file);
        return file.toString();
    }

    /* renamed from: c1 */
    public static String m16521c1(Context context, String str, boolean z8) {
        if (z8) {
            return m16480Q0(context) + File.separator + m16513a1(str, z8);
        }
        return m16472O0(context) + File.separator + m16513a1(str, z8);
    }

    /* renamed from: c2 */
    public static void m16522c2(InterfaceC3139f interfaceC3139f) {
        NetworkLive.getPermissions(Globals.m7388i0().m7506X()).done(new C3136c(interfaceC3139f));
    }

    /* renamed from: d */
    public static String m16523d(String str) throws NumberFormatException {
        String strValueOf;
        try {
            strValueOf = String.valueOf(Long.valueOf(str).longValue() / 1000);
        } catch (Exception e9) {
            e9.printStackTrace();
            strValueOf = "0";
        }
        return m16531f(strValueOf);
    }

    /* renamed from: d0 */
    public static String m16524d0() {
        return m16528e0(m16443H());
    }

    /* renamed from: d1 */
    public static String m16525d1(Context context, String str, boolean z8) {
        if (z8) {
            return m16480Q0(context) + File.separator + "COMPRESS_FAIL_" + m16513a1(str, z8);
        }
        return m16472O0(context) + File.separator + "COMPRESS_FAIL_" + m16513a1(str, z8);
    }

    /* renamed from: d2 */
    public static String m16526d2(String str) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            String strM16530e2 = m16530e2(fileInputStream);
            try {
                fileInputStream.close();
            } catch (IOException e9) {
                e9.printStackTrace();
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
            }
            return strM16530e2;
        } catch (FileNotFoundException e11) {
            e11.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public static boolean m16527e(Context context, UserInfo userInfo) {
        if (context != null && userInfo != null) {
            String strM15775f = userInfo.m15775f();
            SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
            if (sharedPreferences != null && strM15775f != null && !strM15775f.isEmpty()) {
                if (!Globals.m7388i0().m7409C1().booleanValue()) {
                    return sharedPreferences.edit().putString("selfInfo", strM15775f).commit();
                }
                return sharedPreferences.edit().putString("selfInfo", C5127a.m19998b(strM15775f)).commit();
            }
        }
        return false;
    }

    /* renamed from: e0 */
    public static String m16528e0(String str) {
        return Globals.m7372O().getExternalFilesDir(Environment.DIRECTORY_MOVIES) + File.separator + "U_" + str + ".mp4";
    }

    /* renamed from: e1 */
    public static String m16529e1() throws IOException {
        String str = m16508Z() + File.separator + "Snapshot";
        Log.d("CLUtility", "snapshotFolder = " + str);
        return str;
    }

    /* renamed from: e2 */
    public static String m16530e2(InputStream inputStream) {
        return m16534f2(inputStream, null);
    }

    /* renamed from: f */
    public static String m16531f(String str) throws NumberFormatException {
        int iFloor;
        try {
            iFloor = (int) Math.floor(Double.valueOf(str).doubleValue());
        } catch (Exception e9) {
            e9.printStackTrace();
            iFloor = 0;
        }
        return String.valueOf(iFloor / 60) + ":" + String.format(Locale.getDefault(), "%02d", Integer.valueOf(iFloor % 60));
    }

    /* renamed from: f0 */
    public static String m16532f0(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(m16480Q0(context));
        String str = File.separator;
        sb.append(str);
        sb.append("FaceId");
        sb.append(str);
        sb.append("image");
        return sb.toString();
    }

    /* renamed from: f1 */
    public static int m16533f1(Context context) {
        int iM16491T0 = m16491T0(context, "status_bar_height", "dimen", "android");
        if (iM16491T0 > 0) {
            return context.getResources().getDimensionPixelSize(iM16491T0);
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029 A[Catch: OutOfMemoryError -> 0x002d, IOException -> 0x0032, UnsupportedEncodingException -> 0x0037, LOOP:0: B:9:0x0023->B:11:0x0029, LOOP_END, TRY_LEAVE, TryCatch #2 {UnsupportedEncodingException -> 0x0037, IOException -> 0x0032, OutOfMemoryError -> 0x002d, blocks: (B:4:0x0007, B:7:0x000e, B:9:0x0023, B:11:0x0029, B:8:0x0019), top: B:21:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b A[EDGE_INSN: B:23:0x003b->B:19:0x003b BREAK  A[LOOP:0: B:9:0x0023->B:11:0x0029], SYNTHETIC] */
    /* renamed from: f2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m16534f2(InputStream inputStream, String str) throws IOException {
        BufferedReader bufferedReader;
        String line;
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            try {
                if (str.isEmpty()) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
                    while (true) {
                        line = bufferedReader.readLine();
                        if (line == null) {
                        }
                        sb.append(line);
                    }
                }
            } catch (UnsupportedEncodingException e9) {
                e9.printStackTrace();
            } catch (IOException e10) {
                e10.printStackTrace();
            } catch (OutOfMemoryError e11) {
                e11.printStackTrace();
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                }
                sb.append(line);
            }
        }
        return sb.toString();
    }

    /* renamed from: g */
    public static String m16535g(String str) {
        return "https://www.cyberlink.com/prog/ap/u/redirect.jsp?" + str + "&locale=" + Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }

    /* renamed from: g0 */
    public static String m16536g0() {
        return m16508Z() + File.separator + "FaceId";
    }

    /* renamed from: g1 */
    public static String m16537g1() throws IOException {
        String str = m16508Z() + File.separator + "StickerPack";
        Log.d("CLUtility", "stickerFolder = " + str);
        return str;
    }

    /* renamed from: g2 */
    public static JSONArray m16538g2(JSONArray jSONArray, int i9) {
        int length = jSONArray.length();
        JSONArray jSONArray2 = new JSONArray();
        for (int i10 = 0; i10 < length; i10++) {
            if (i10 != i9) {
                try {
                    jSONArray2.put(jSONArray.get(i10));
                } catch (JSONException unused) {
                }
            }
        }
        return jSONArray2;
    }

    /* renamed from: h */
    public static void m16539h(File file, String str, ZipOutputStream zipOutputStream) throws Throwable {
        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                if (str != null) {
                    zipOutputStream.putNextEntry(new ZipEntry(str + "/" + file.getName()));
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                }
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

    /* renamed from: h0 */
    public static long m16540h0() {
        return Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date()));
    }

    /* renamed from: h1 */
    public static String m16541h1(long j9) {
        StringBuilder sb = new StringBuilder();
        sb.append(m16508Z());
        String str = File.separator;
        sb.append(str);
        sb.append("StickerPack");
        sb.append(str);
        sb.append(j9);
        String string = sb.toString();
        Log.d("CLUtility", "stickerFolder = " + string);
        return string;
    }

    /* renamed from: h2 */
    public static String m16542h2(String str) {
        if (C5170o0.m20170e(str)) {
            return str;
        }
        int i9 = 0;
        while (true) {
            String[] strArr = f14428d;
            if (i9 >= strArr.length) {
                return str;
            }
            if (("messagenotify_" + strArr[i9]).equalsIgnoreCase(str)) {
                str = f14427c[i9];
            }
            i9++;
        }
    }

    /* renamed from: i */
    public static List<String> m16543i(TextView textView) {
        return m16547j(textView, 15);
    }

    /* renamed from: i0 */
    public static String m16544i0() {
        return m16535g("type=faq_chat3");
    }

    /* renamed from: i1 */
    public static Pair<Integer, Integer> m16545i1(String str) throws JSONException, IOException {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(str, options);
                    return Pair.create(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
                } catch (OutOfMemoryError e9) {
                    e9.printStackTrace();
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                String str2 = File.separator;
                sb.append(str2);
                sb.append("info.json");
                String strM16526d2 = m16526d2(sb.toString());
                if (strM16526d2 != null) {
                    try {
                        JSONArray jSONArray = new JSONObject(strM16526d2).getJSONArray("images");
                        if (jSONArray.length() > 0) {
                            BitmapFactory.Options options2 = new BitmapFactory.Options();
                            options2.inJustDecodeBounds = true;
                            BitmapFactory.decodeFile(str + str2 + jSONArray.getString(0), options2);
                            return Pair.create(Integer.valueOf(options2.outWidth), Integer.valueOf(options2.outHeight));
                        }
                    } catch (OutOfMemoryError e10) {
                        e10.printStackTrace();
                    } catch (JSONException e11) {
                        e11.printStackTrace();
                    }
                }
            }
        }
        return Pair.create(0, 0);
    }

    @TargetApi(19)
    /* renamed from: i2 */
    public static String m16546i2(Context context, Uri uri) throws NumberFormatException {
        Uri[] uriArr;
        int i9 = Build.VERSION.SDK_INT;
        if (!DocumentsContract.isDocumentUri(context, uri)) {
            return null;
        }
        String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
        String str = strArrSplit[0];
        String str2 = strArrSplit.length > 1 ? strArrSplit[1] : str;
        if (m16429D1(uri)) {
            str.hashCode();
            switch (str) {
                case "audio":
                    uriArr = new Uri[]{MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.INTERNAL_CONTENT_URI};
                    break;
                case "image":
                    uriArr = new Uri[]{MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.INTERNAL_CONTENT_URI};
                    break;
                case "video":
                    uriArr = new Uri[]{MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MediaStore.Video.Media.INTERNAL_CONTENT_URI};
                    break;
                default:
                    Log.e("CLUtility", "Could not find filesystem path of " + uri);
                    return null;
            }
            String[] strArr = {str2};
            for (Uri uri2 : uriArr) {
                String strM16514a2 = m16514a2(context, uri2, "_id=?", strArr);
                if (strM16514a2 != null) {
                    return strM16514a2;
                }
            }
        } else if (m16601w1(uri)) {
            try {
                if (str.equals("raw")) {
                    return DocumentsContract.getDocumentId(uri).replaceFirst("raw:", "");
                }
                if (i9 < 29) {
                    return m16514a2(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(str2).longValue()), null, null);
                }
            } catch (NumberFormatException e9) {
                C5154j.m20076j(e9);
                return null;
            }
        } else if (m16609y1(uri) && "primary".equalsIgnoreCase(str)) {
            return Environment.getExternalStorageDirectory() + File.separator + str2;
        }
        return null;
    }

    /* renamed from: j */
    public static List<String> m16547j(TextView textView, int i9) {
        ArrayList arrayList = new ArrayList();
        if (textView == null) {
            return arrayList;
        }
        try {
            return C4986c.m19352e(textView, i9);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return arrayList;
        }
    }

    /* renamed from: j0 */
    public static String m16548j0() {
        return m16535g("type=faq_usite");
    }

    /* renamed from: j1 */
    public static String m16549j1(long j9) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int days = (int) timeUnit.toDays(j9);
        long hours = timeUnit.toHours(j9) - (days * 24);
        long minutes = timeUnit.toMinutes(j9) - (timeUnit.toHours(j9) * 60);
        long seconds = timeUnit.toSeconds(j9) - (timeUnit.toMinutes(j9) * 60);
        return days > 0 ? String.format(Locale.getDefault(), "%dd:%02d:%02d:%02d", Integer.valueOf(days), Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)) : hours > 0 ? String.format(Locale.getDefault(), "%02d:%02d:%02d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)) : String.format(Locale.getDefault(), "%02d:%02d", Long.valueOf(minutes), Long.valueOf(seconds));
    }

    /* renamed from: j2 */
    public static Uri m16550j2(Bitmap bitmap, boolean z8, String str) throws IOException {
        Uri uriInsert;
        if (bitmap == null) {
            return null;
        }
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        try {
            String str2 = str + "_qrcode.jpg";
            if (!z8) {
                String str3 = m16508Z() + File.separator + str2;
                FileOutputStream fileOutputStream = new FileOutputStream(str3);
                try {
                    bitmap.compress(f14426b, 90, fileOutputStream);
                    fileOutputStream.close();
                    return Uri.fromFile(new File(str3));
                } finally {
                }
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", str2);
            contentValues.put("date_added", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("date_modified", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("mime_type", "image/jpeg");
            String strM16444H0 = m16444H0("image");
            if (Build.VERSION.SDK_INT < 29) {
                String str4 = strM16444H0 + File.separator + str2;
                String[] strArr = {str4};
                Cursor cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_data=?", strArr, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            contentResolver.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data=?", strArr);
                        }
                    } finally {
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                contentValues.put("_data", str4);
            } else {
                contentValues.put("relative_path", strM16444H0);
            }
            uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (uriInsert != null) {
                try {
                    OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                    try {
                        bitmap.compress(f14426b, 90, outputStreamOpenOutputStream);
                        if (outputStreamOpenOutputStream != null) {
                            outputStreamOpenOutputStream.close();
                        }
                    } finally {
                    }
                } catch (Exception e9) {
                    e = e9;
                }
            }
            return uriInsert;
        } catch (Exception e10) {
            e = e10;
            uriInsert = null;
        }
        e = e10;
        uriInsert = null;
        Log.d("CLUtility", "[saveGroupQRcodeImage] " + e.getMessage());
        if (uriInsert == null || !z8) {
            return null;
        }
        contentResolver.delete(uriInsert, null, null);
        return null;
    }

    /* renamed from: k */
    public static String m16551k(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b9 : bArr) {
            String hexString = Integer.toHexString(b9 & UnsignedBytes.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString().toLowerCase();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: k0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m16552k0(String str, Uri uri) {
        String string;
        File file;
        if (m16597v1(uri)) {
            try {
                Cursor cursorQuery = Globals.m7372O().getContentResolver().query(uri, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            cursorQuery.moveToNext();
                            string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                        } else {
                            string = "";
                        }
                        if (cursorQuery != null) {
                            try {
                                cursorQuery.close();
                            } catch (Exception e9) {
                                e = e9;
                                e.printStackTrace();
                                if (m16417A1(uri)) {
                                    file = new File(uri.getPath());
                                    if (file.exists()) {
                                    }
                                }
                                return !TextUtils.isEmpty(str) ? string : string;
                            }
                        }
                    } finally {
                    }
                }
            } catch (Exception e10) {
                e = e10;
                string = "";
                e.printStackTrace();
                if (m16417A1(uri)) {
                }
                if (!TextUtils.isEmpty(str)) {
                }
            }
        } else {
            string = "";
        }
        if (m16417A1(uri) && !TextUtils.isEmpty(uri.getPath()) && "".equals(string)) {
            file = new File(uri.getPath());
            if (file.exists()) {
                string = file.getName();
            }
        }
        if (!TextUtils.isEmpty(str) && "".equals(string)) {
            File file2 = new File(str);
            return file2.exists() ? file2.getName() : string;
        }
    }

    /* renamed from: k1 */
    public static int m16553k1(String str) {
        if (str != null) {
            try {
                return new JSONObject(str).getInt("totalSize");
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        return -1;
    }

    /* renamed from: k2 */
    public static Uri m16554k2(Bitmap bitmap, boolean z8) throws IOException {
        Uri uriInsert;
        if (bitmap == null) {
            return null;
        }
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        try {
            if (!z8) {
                String str = m16508Z() + File.separator + "qrcode.jpg";
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                try {
                    bitmap.compress(f14426b, 90, fileOutputStream);
                    fileOutputStream.close();
                    return Uri.fromFile(new File(str));
                } finally {
                }
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", "qrcode.jpg");
            contentValues.put("date_added", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("date_modified", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("mime_type", "image/jpeg");
            String strM16444H0 = m16444H0("image");
            if (Build.VERSION.SDK_INT < 29) {
                String str2 = strM16444H0 + File.separator + "qrcode.jpg";
                String[] strArr = {str2};
                Cursor cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_data=?", strArr, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            contentResolver.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data=?", strArr);
                        }
                    } finally {
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                contentValues.put("_data", str2);
            } else {
                contentValues.put("relative_path", strM16444H0);
            }
            uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (uriInsert != null) {
                try {
                    OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                    try {
                        bitmap.compress(f14426b, 90, outputStreamOpenOutputStream);
                        if (outputStreamOpenOutputStream != null) {
                            outputStreamOpenOutputStream.close();
                        }
                    } finally {
                    }
                } catch (Exception e9) {
                    e = e9;
                }
            }
            return uriInsert;
        } catch (Exception e10) {
            e = e10;
            uriInsert = null;
        }
        e = e10;
        uriInsert = null;
        Log.d("CLUtility", "[saveQRcodeImage] " + e.getMessage());
        if (!z8 || uriInsert == null) {
            return null;
        }
        contentResolver.delete(uriInsert, null, null);
        return null;
    }

    /* renamed from: l */
    public static int m16555l(BitmapFactory.Options options, int i9, int i10) {
        int i11 = options.outHeight;
        int i12 = options.outWidth;
        int i13 = 1;
        if (i11 > i10 && i12 > i9) {
            int i14 = i11 / 2;
            int i15 = i12 / 2;
            while (i14 / i13 > i10 && i15 / i13 > i9) {
                i13 *= 2;
            }
        }
        return i13;
    }

    /* renamed from: l0 */
    public static String m16556l0(String str) {
        return m16505Y(str);
    }

    /* renamed from: l1 */
    public static String m16557l1(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(m16508Z());
        String str2 = File.separator;
        sb.append(str2);
        sb.append("Video_Transcode");
        String string = sb.toString();
        File file = new File(string);
        if (!file.exists()) {
            file.mkdir();
        }
        return string + str2 + FilenameUtils.getBaseName(str) + ".mp4";
    }

    /* renamed from: l2 */
    public static void m16558l2(String str, ArrayList<ScheduleSendObj> arrayList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        Iterator<ScheduleSendObj> it = arrayList.iterator();
        while (it.hasNext()) {
            ScheduleSendObj next = it.next();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE, next.f12447b);
                jSONObject2.put(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, next.f12448c);
                jSONObject2.put("sendTime", next.f12450e.getTime());
                jSONObject2.put(Constants.FirelogAnalytics.PARAM_TTL, next.f12449d);
                jSONObject2.put("sendStatus", next.f12451f);
                jSONObject2.put(FirebaseAnalytics.Param.CONTENT, next.f12452g);
                jSONArray.put(jSONObject2);
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        try {
            jSONObject.put("totalSize", arrayList.size());
            jSONObject.put("results", jSONArray);
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        SharedPreferences sharedPreferences = Globals.m7388i0().getSharedPreferences("cached_schedule_send_map", 0);
        if (jSONObject.length() != 0) {
            sharedPreferences.edit().putString(str, jSONObject.toString()).apply();
        } else {
            sharedPreferences.edit().putString(str, "").apply();
        }
    }

    /* renamed from: m */
    public static int m16559m(int i9, int i10) {
        return (int) Math.ceil((i9 * 1.0f) / i10);
    }

    /* renamed from: m0 */
    public static int m16560m0(String str) {
        return m16564n0(str, false);
    }

    /* renamed from: m1 */
    public static String m16561m1(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* renamed from: m2 */
    public static Pair<Integer, Long> m16562m2(long j9) {
        int i9;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long days = timeUnit.toDays(j9);
        if (days == 0) {
            days = timeUnit.toHours(j9);
            if (days == 0) {
                long minutes = timeUnit.toMinutes(j9);
                if (minutes == 0) {
                    i9 = 3;
                } else {
                    i9 = 2;
                    j9 = minutes;
                }
                return Pair.create(Integer.valueOf(i9), Long.valueOf(j9));
            }
            i9 = 1;
        } else {
            i9 = 0;
        }
        j9 = days;
        return Pair.create(Integer.valueOf(i9), Long.valueOf(j9));
    }

    /* renamed from: n */
    public static ImageItem m16563n(Context context, ImageItem imageItem) {
        int iM18873c;
        if (context == null || imageItem == null) {
            return null;
        }
        String strM16141n = imageItem.m16141n();
        if (strM16141n != null && strM16141n.length() > 0) {
            return imageItem;
        }
        long jM16142o = imageItem.m16142o();
        ImageItem imageItemM16424C0 = m16424C0(context, jM16142o);
        if (imageItemM16424C0 != null) {
            int iM16133f = imageItem.m16133f();
            if (iM16133f <= 0) {
                return imageItemM16424C0;
            }
            imageItemM16424C0.m16152y(iM16133f);
            return imageItemM16424C0;
        }
        try {
            String strM16144q = imageItem.m16144q();
            Uri uriM16510Z1 = m16510Z1(imageItem.m16145r());
            if (uriM16510Z1 == null && !TextUtils.isEmpty(strM16144q)) {
                uriM16510Z1 = m16448I0(null, strM16144q, new Uri[]{MediaStore.Images.Media.EXTERNAL_CONTENT_URI});
            }
            if (!m16613z1(strM16144q, uriM16510Z1)) {
                return imageItemM16424C0;
            }
            int iM16133f2 = imageItem.m16133f();
            String strM16552k0 = m16552k0(strM16144q, uriM16510Z1);
            try {
                iM18873c = C4754a.m18873c(strM16144q, uriM16510Z1);
            } catch (IOException e9) {
                Log.e("CLUtility", "cannot get exif attribute", e9);
                iM18873c = 0;
            }
            return new ImageItem("", jM16142o, strM16552k0, strM16144q, uriM16510Z1 != null ? uriM16510Z1.toString() : null, strM16552k0, iM16133f2, iM18873c, "");
        } catch (Exception e10) {
            e10.printStackTrace();
            return imageItemM16424C0;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: n0 */
    public static int m16564n0(String str, boolean z8) {
        String extension = FilenameUtils.getExtension(str);
        if (extension == null || extension.isEmpty()) {
            return z8 ? R.drawable.icon_file_default_upload : R.drawable.icon_file_default;
        }
        String lowerCase = extension.toLowerCase(Locale.US);
        lowerCase.hashCode();
        char c9 = 65535;
        switch (lowerCase.hashCode()) {
            case 96980:
                if (lowerCase.equals("avi")) {
                    c9 = 0;
                    break;
                }
                break;
            case 99640:
                if (lowerCase.equals("doc")) {
                    c9 = 1;
                    break;
                }
                break;
            case 105441:
                if (lowerCase.equals("jpg")) {
                    c9 = 2;
                    break;
                }
                break;
            case 108184:
                if (lowerCase.equals("mkv")) {
                    c9 = 3;
                    break;
                }
                break;
            case 108272:
                if (lowerCase.equals("mp3")) {
                    c9 = 4;
                    break;
                }
                break;
            case 108273:
                if (lowerCase.equals("mp4")) {
                    c9 = 5;
                    break;
                }
                break;
            case 108417:
                if (lowerCase.equals("msg")) {
                    c9 = 6;
                    break;
                }
                break;
            case 110834:
                if (lowerCase.equals("pdf")) {
                    c9 = 7;
                    break;
                }
                break;
            case 111145:
                if (lowerCase.equals("png")) {
                    c9 = '\b';
                    break;
                }
                break;
            case 111220:
                if (lowerCase.equals("ppt")) {
                    c9 = '\t';
                    break;
                }
                break;
            case 115312:
                if (lowerCase.equals("txt")) {
                    c9 = '\n';
                    break;
                }
                break;
            case 117484:
                if (lowerCase.equals("wav")) {
                    c9 = 11;
                    break;
                }
                break;
            case 117835:
                if (lowerCase.equals("wma")) {
                    c9 = '\f';
                    break;
                }
                break;
            case 118783:
                if (lowerCase.equals("xls")) {
                    c9 = CharUtils.f19105CR;
                    break;
                }
                break;
            case 118807:
                if (lowerCase.equals("xml")) {
                    c9 = 14;
                    break;
                }
                break;
            case 3088960:
                if (lowerCase.equals("docx")) {
                    c9 = 15;
                    break;
                }
                break;
            case 3351329:
                if (lowerCase.equals("midi")) {
                    c9 = 16;
                    break;
                }
                break;
            case 3447940:
                if (lowerCase.equals("pptx")) {
                    c9 = 17;
                    break;
                }
                break;
            case 3682393:
                if (lowerCase.equals("xlsx")) {
                    c9 = 18;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
            case 3:
            case 5:
                return z8 ? R.drawable.icon_file_video_upload : R.drawable.icon_file_video;
            case 1:
            case 15:
                return z8 ? R.drawable.icon_file_word_upload : R.drawable.icon_file_word;
            case 2:
            case '\b':
                return R.drawable.icon_file_photo;
            case 4:
            case 11:
            case '\f':
            case 16:
                return z8 ? R.drawable.icon_file_music_upload : R.drawable.icon_file_music;
            case 6:
            case '\n':
            case 14:
                return z8 ? R.drawable.icon_file_note_upload : R.drawable.icon_file_note;
            case 7:
                return z8 ? R.drawable.icon_file_pdf_upload : R.drawable.icon_file_pdf;
            case '\t':
            case 17:
                return z8 ? R.drawable.icon_file_point_upload : R.drawable.icon_file_point;
            case '\r':
            case 18:
                return z8 ? R.drawable.icon_file_excel_upload : R.drawable.icon_file_excel;
            default:
                return z8 ? R.drawable.icon_file_default_upload : R.drawable.icon_file_default;
        }
    }

    /* renamed from: n1 */
    public static String m16565n1() {
        String language = LocaleList.getDefault().get(0).getLanguage();
        return "zh".equalsIgnoreCase(language) ? "https://u.cyberlink.com/support/faq/24767" : "ja".equalsIgnoreCase(language) ? "https://u.cyberlink.com/support/faq/24768" : "https://u.cyberlink.com/support/faq/24766";
    }

    /* renamed from: n2 */
    public static void m16566n2(MessageObj messageObj) {
        Date dateM23723b;
        if (!C2925v.m14600i0(messageObj) || (dateM23723b = C6202j.m23723b(messageObj.m14777o())) == null) {
            return;
        }
        Log.d("CLUtility", "[sendFlurryMsgRoundTripTimeEvent]   startSendingTime = " + dateM23723b);
        Log.d("CLUtility", "[sendFlurryMsgRoundTripTimeEvent]   System.currentTimeMillis() = " + System.currentTimeMillis());
        long jCurrentTimeMillis = System.currentTimeMillis() - dateM23723b.getTime();
        Log.d("CLUtility", "[sendFlurryMsgRoundTripTimeEvent]   MsgRoundTripTime = " + jCurrentTimeMillis);
        C6566a.m25160s(C5176q0.m20225d(jCurrentTimeMillis), messageObj.m14778p());
        C6566a.m25152k(false, messageObj.m14778p());
    }

    /* renamed from: o */
    public static void m16567o(long j9) {
        if (Globals.m7388i0().getApplicationContext() != null) {
            File file = new File(m16541h1(j9));
            if (file.exists()) {
                return;
            }
            file.mkdir();
        }
    }

    /* renamed from: o0 */
    public static long m16568o0(String str, Uri uri) {
        AbstractC4804a abstractC4804aM19072a;
        long jMo19073b = (uri == null || (abstractC4804aM19072a = AbstractC4804a.m19072a(Globals.m7372O(), uri)) == null) ? 0L : abstractC4804aM19072a.mo19073b();
        if (TextUtils.isEmpty(str) || jMo19073b != 0) {
            return jMo19073b;
        }
        File file = new File(str);
        return file.exists() ? file.lastModified() : jMo19073b;
    }

    /* renamed from: o1 */
    public static String m16569o1() {
        return m16508Z() + File.separator + "UNO";
    }

    /* renamed from: o2 */
    public static void m16570o2(Activity activity, UserInfo userInfo, Group group, String str, String[] strArr) {
        String str2;
        String str3;
        if (group != null) {
            str3 = String.format(activity.getApplicationContext().getString(R.string.group_invite_email_title_new), userInfo.f13778c, group.f13717d);
            str2 = String.format(activity.getApplicationContext().getString(R.string.group_invite_email_new), group.f13717d, str);
        } else {
            String str4 = String.format(activity.getApplicationContext().getString(R.string.invite_email_title_new), userInfo.f13778c);
            str2 = String.format(activity.getApplicationContext().getString(R.string.invite_email_new), userInfo.f13778c, "<a href=\"" + str + "\">" + str + "</a>");
            str3 = str4;
        }
        try {
            activity.startActivity(C5155j0.m20083a(str3, str2, str, strArr));
        } catch (ActivityNotFoundException unused) {
            C5187v0.m20267c(R.string.invite_email_no_client);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v35 */
    /* JADX WARN: Type inference failed for: r10v36 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.lang.Object] */
    /* renamed from: p */
    public static void m16571p(Context context, boolean z8) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        ULogUtility.m16674j();
        String parent = context.getFilesDir().getParent();
        if (TextUtils.isEmpty(parent)) {
            return;
        }
        File file = new File(parent + "/shared_prefs");
        File file2 = new File(parent + "/databases");
        File file3 = new File(parent + "/files/DebugLog");
        File file4 = new File(m16508Z(), "tmp");
        File file5 = new File(m16508Z(), "/tmp/CL_U_DATA.zip");
        ZipOutputStream zipOutputStream = null;
        try {
            try {
                if (!file4.isDirectory()) {
                    file4.mkdir();
                }
                if (file5.exists()) {
                    file5.delete();
                }
                FileOutputStream fileOutputStream3 = new FileOutputStream(file5);
                try {
                    ZipOutputStream zipOutputStream2 = new ZipOutputStream(fileOutputStream3);
                    if (z8 != 0) {
                        try {
                            if (file2.isDirectory()) {
                                for (File file6 : file2.listFiles(m16611z(".sqlite"))) {
                                    m16539h(file6, "Databases", zipOutputStream2);
                                }
                            }
                        } catch (FileNotFoundException e9) {
                            e = e9;
                            zipOutputStream = zipOutputStream2;
                            FileNotFoundException fileNotFoundException = e;
                            fileOutputStream2 = fileOutputStream3;
                            e = fileNotFoundException;
                            e.printStackTrace();
                            z8 = fileOutputStream2;
                            C6370g.m24480b(zipOutputStream);
                            C6370g.m24480b(z8);
                        } catch (IOException e10) {
                            e = e10;
                            zipOutputStream = zipOutputStream2;
                            Throwable th = e;
                            fileOutputStream = fileOutputStream3;
                            e = th;
                            e.printStackTrace();
                            z8 = fileOutputStream;
                            C6370g.m24480b(zipOutputStream);
                            C6370g.m24480b(z8);
                        } catch (SecurityException e11) {
                            e = e11;
                            zipOutputStream = zipOutputStream2;
                            Throwable th2 = e;
                            fileOutputStream = fileOutputStream3;
                            e = th2;
                            e.printStackTrace();
                            z8 = fileOutputStream;
                            C6370g.m24480b(zipOutputStream);
                            C6370g.m24480b(z8);
                        } catch (Throwable th3) {
                            th = th3;
                            zipOutputStream = zipOutputStream2;
                            Throwable th4 = th;
                            z8 = fileOutputStream3;
                            th = th4;
                            C6370g.m24480b(zipOutputStream);
                            C6370g.m24480b(z8);
                            throw th;
                        }
                    }
                    if (file.isDirectory()) {
                        for (File file7 : file.listFiles(m16611z(".xml"))) {
                            m16539h(file7, "SharedPrefs", zipOutputStream2);
                        }
                    }
                    if (file3.isDirectory()) {
                        String parent2 = file3.getCanonicalFile().getParent();
                        Iterator<File> it = m16481Q1(file3).iterator();
                        while (it.hasNext()) {
                            File next = it.next();
                            m16539h(next, next.getCanonicalFile().getParent().substring(parent2.length() + 1), zipOutputStream2);
                        }
                    }
                    zipOutputStream2.close();
                    fileOutputStream3.close();
                    C6370g.m24480b(zipOutputStream2);
                    C6370g.m24480b(fileOutputStream3);
                } catch (FileNotFoundException e12) {
                    e = e12;
                } catch (IOException e13) {
                    e = e13;
                } catch (SecurityException e14) {
                    e = e14;
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (FileNotFoundException e15) {
            e = e15;
            fileOutputStream2 = null;
        } catch (IOException e16) {
            e = e16;
            fileOutputStream = null;
            e.printStackTrace();
            z8 = fileOutputStream;
            C6370g.m24480b(zipOutputStream);
            C6370g.m24480b(z8);
        } catch (SecurityException e17) {
            e = e17;
            fileOutputStream = null;
            e.printStackTrace();
            z8 = fileOutputStream;
            C6370g.m24480b(zipOutputStream);
            C6370g.m24480b(z8);
        } catch (Throwable th7) {
            th = th7;
            z8 = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: p0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long m16572p0(String str, Uri uri) {
        long length;
        File file;
        if (m16597v1(uri)) {
            try {
                Cursor cursorQuery = Globals.m7372O().getContentResolver().query(uri, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            cursorQuery.moveToNext();
                            length = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_size"));
                        } else {
                            length = 0;
                        }
                        if (cursorQuery != null) {
                            try {
                                cursorQuery.close();
                            } catch (Exception e9) {
                                e = e9;
                                e.printStackTrace();
                                if (m16417A1(uri)) {
                                    file = new File(uri.getPath());
                                    if (file.exists()) {
                                    }
                                }
                                return !TextUtils.isEmpty(str) ? length : length;
                            }
                        }
                    } finally {
                    }
                }
            } catch (Exception e10) {
                e = e10;
                length = 0;
                e.printStackTrace();
                if (m16417A1(uri)) {
                }
                if (!TextUtils.isEmpty(str)) {
                }
            }
        } else {
            length = 0;
        }
        if (m16417A1(uri) && !TextUtils.isEmpty(uri.getPath()) && length == 0) {
            file = new File(uri.getPath());
            if (file.exists()) {
                length = file.length();
            }
        }
        if (!TextUtils.isEmpty(str) && length == 0) {
            File file2 = new File(str);
            return file2.exists() ? file2.length() : length;
        }
    }

    /* renamed from: p1 */
    public static String m16573p1(String str, String str2, File file) throws IOException {
        m16415A(file.getAbsolutePath());
        try {
            return File.createTempFile(str, str2, file).getAbsolutePath();
        } catch (IOException unused) {
            return "_";
        }
    }

    /* renamed from: p2 */
    public static void m16574p2(Activity activity, UserInfo userInfo, Group group, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:"));
        intent.putExtra("sms_body", group != null ? String.format(activity.getApplicationContext().getString(R.string.group_invite_sms_new), group.f13717d, str) : String.format(activity.getApplicationContext().getString(R.string.invite_sms_new), userInfo.f13778c, str));
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            C5187v0.m20267c(R.string.error_no_sms_client);
        }
    }

    /* renamed from: q */
    public static void m16575q(Context context) {
        String parent = context.getFilesDir().getParent();
        if (TextUtils.isEmpty(parent)) {
            return;
        }
        File file = new File(parent + "/databases");
        File file2 = new File(m16508Z(), "/tmp/CL_U_DB.zip");
        try {
            File[] fileArrListFiles = file.listFiles(m16611z(".sqlite"));
            ArrayList arrayList = new ArrayList();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                arrayList.addAll(Arrays.asList(fileArrListFiles));
            }
            C6369f.m24478t(file2, arrayList);
        } catch (IOException e9) {
            e9.printStackTrace();
        } catch (SecurityException e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: q0 */
    public static String m16576q0(Context context, Uri uri) {
        if ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme())) {
            return uri.toString();
        }
        String strM16546i2 = m16546i2(context, uri);
        if (strM16546i2 != null) {
            return strM16546i2;
        }
        String strM16514a2 = m16514a2(context, uri, null, null);
        return strM16514a2 != null ? strM16514a2 : uri.getPath();
    }

    /* renamed from: q1 */
    public static String m16577q1(String str) {
        return str.split("@")[0];
    }

    /* renamed from: q2 */
    public static void m16578q2(Activity activity, Dialog dialog) {
        activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        dialog.getWindow().getAttributes().width = (int) (930 * (r0.widthPixels / (activity.getResources().getConfiguration().orientation == 1 ? 1080.0f : 1920.0f)));
    }

    /* renamed from: r */
    public static Point m16579r(int i9, int i10) {
        if (Math.max(i9, i10) > 1280) {
            if (i9 > i10) {
                i10 = (int) Math.floor(i10 * (1280 / i9));
                i9 = 1280;
            } else {
                i9 = (int) Math.floor(i9 * (1280 / i10));
                i10 = 1280;
            }
        }
        return new Point(Math.max(i9, 1), Math.max(i10, 1));
    }

    /* renamed from: r0 */
    public static List<Friend> m16580r0(List<Friend> list) {
        List<Group> listM15084u = C2950b0.m14912k().m15084u();
        ArrayList arrayList = new ArrayList();
        Iterator<Group> it = listM15084u.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().f13723j);
        }
        return m16471O(list);
    }

    /* renamed from: r1 */
    public static String m16581r1() {
        return m16524d0();
    }

    /* renamed from: r2 */
    public static void m16582r2(boolean z8, Window window) {
        if (z8 == m16421B1(window)) {
            return;
        }
        if (z8) {
            window.addFlags(UserMetadata.MAX_ATTRIBUTE_SIZE);
        } else {
            window.clearFlags(UserMetadata.MAX_ATTRIBUTE_SIZE);
        }
    }

    /* renamed from: s */
    public static int m16583s(float f9) {
        return (int) (f9 * (Globals.m7374X0().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* renamed from: s0 */
    public static List<Group> m16584s0() {
        boolean z8;
        List<Group> listM15083t = C2950b0.m14912k().m15083t("Circle");
        List<Group> listM15084u = C2950b0.m14912k().m15084u();
        ArrayList arrayList = new ArrayList();
        for (Group group : listM15083t) {
            Iterator<Group> it = listM15084u.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z8 = false;
                    break;
                }
                if (it.next().f13727n == group.f13727n) {
                    z8 = true;
                    break;
                }
            }
            if (!z8) {
                arrayList.add(group);
            }
        }
        return arrayList;
    }

    /* renamed from: s1 */
    public static void m16585s1(Group group, boolean z8) {
        if (group == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        group.f13706E = z8;
        arrayList.add("isArchive");
        C2950b0.m14912k().m15063B(String.valueOf(group.f13727n), group, arrayList);
    }

    /* renamed from: s2 */
    public static void m16586s2(String str, Uri uri, String str2, Bitmap bitmap) throws Throwable {
        C4978a c4978a;
        try {
            C4978a c4978a2 = null;
            if (m16597v1(uri)) {
                try {
                    InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
                    if (inputStreamOpenInputStream != null) {
                        try {
                            c4978a = new C4978a(inputStreamOpenInputStream);
                        } finally {
                        }
                    } else {
                        c4978a = null;
                    }
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                    c4978a2 = c4978a;
                } catch (Exception unused) {
                    ULogUtility.m16670f("CLUtility", "[setImageFileAttribute] Exception : sourceUri=" + uri);
                }
            }
            if (c4978a2 == null) {
                c4978a2 = new C4978a(str);
            }
            C4978a c4978a3 = new C4978a(str2);
            C4754a.m18871a(c4978a2, c4978a3);
            c4978a3.m19280H("ImageWidth", String.valueOf(bitmap.getWidth()));
            c4978a3.m19280H("ImageLength", String.valueOf(bitmap.getHeight()));
            c4978a3.m19280H("Orientation", Integer.toString(0));
            c4978a3.m19278F();
        } catch (IOException e9) {
            Log.e("CLUtility", "cannot set exif attribute", e9);
        } catch (UnsupportedOperationException unused2) {
            ULogUtility.m16670f("CLUtility", "[copyImageAttribute] Can't save exif information in this device");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00cc  */
    @TargetApi(19)
    /* renamed from: t */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Uri m16587t(Uri uri) throws NumberFormatException {
        Uri uriM16448I0;
        Cursor cursorQuery;
        Uri[] uriArr;
        if (uri == null) {
            return null;
        }
        Uri[] uriArr2 = {MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MediaStore.Video.Media.INTERNAL_CONTENT_URI, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.INTERNAL_CONTENT_URI, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.INTERNAL_CONTENT_URI};
        if (DocumentsContract.isDocumentUri(Globals.m7372O(), uri)) {
            String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
            String str = strArrSplit[0];
            String str2 = strArrSplit.length > 1 ? strArrSplit[1] : str;
            if (m16429D1(uri)) {
                str.hashCode();
                switch (str) {
                    case "audio":
                        uriArr = new Uri[]{MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.INTERNAL_CONTENT_URI};
                        break;
                    case "image":
                        uriArr = new Uri[]{MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.INTERNAL_CONTENT_URI};
                        break;
                    case "video":
                        uriArr = new Uri[]{MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MediaStore.Video.Media.INTERNAL_CONTENT_URI};
                        break;
                    default:
                        Log.e("CLUtility", "Could not find filesystem path of " + uri);
                        return null;
                }
                uriM16448I0 = m16448I0(str2, null, uriArr);
            } else if (m16601w1(uri)) {
                String strM16546i2 = m16546i2(Globals.m7372O(), uri);
                uriM16448I0 = !TextUtils.isEmpty(strM16546i2) ? m16448I0(null, strM16546i2, uriArr2) : null;
            }
        }
        if (uriM16448I0 == null) {
            ContentResolver contentResolver = Globals.m7372O().getContentResolver();
            if (m16601w1(uri)) {
                String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                String str3 = strArrSplit2.length > 1 ? strArrSplit2[1] : strArrSplit2[0];
                try {
                    cursorQuery = contentResolver.query(uri, null, null, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                uriM16448I0 = m16448I0(str3, null, uriArr2);
                            }
                        } finally {
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            } else {
                try {
                    cursorQuery = contentResolver.query(uri, null, null, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                int columnIndex = cursorQuery.getColumnIndex("_data");
                                String string = columnIndex > 0 ? cursorQuery.getString(columnIndex) : "";
                                if (!TextUtils.isEmpty(string)) {
                                    uriM16448I0 = m16448I0(null, string, uriArr2);
                                }
                            }
                        } finally {
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            }
        }
        return uriM16448I0;
    }

    /* renamed from: t0 */
    public static String m16588t0(long j9, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j9);
        return simpleDateFormat.format(calendar.getTime());
    }

    /* renamed from: t1 */
    public static void m16589t1(Activity activity) {
        if (activity == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            return;
        }
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        window.setSoftInputMode(3);
    }

    /* renamed from: t2 */
    public static void m16590t2(Activity activity, Dialog dialog) {
        activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        dialog.getWindow().getAttributes().width = (int) (UserMetadata.MAX_ATTRIBUTE_SIZE * (r0.widthPixels / 1080.0f));
    }

    /* renamed from: u */
    public static void m16591u(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i9);
            }
        }
    }

    /* renamed from: u0 */
    public static String m16592u0(Context context, long j9) {
        if (j9 <= 0) {
            return "0";
        }
        String[] strArr = {context.getResources().getString(R.string.file_bytes), context.getResources().getString(R.string.file_KB), context.getResources().getString(R.string.file_MB), context.getResources().getString(R.string.file_GB), context.getResources().getString(R.string.file_TB)};
        double d9 = j9;
        int iLog10 = (int) (Math.log10(d9) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(d9 / Math.pow(1024.0d, iLog10)) + StringUtils.SPACE + strArr[iLog10];
    }

    /* renamed from: u1 */
    public static boolean m16593u1() {
        UserInfo userInfoM16497V0 = m16497V0(Globals.m7372O());
        if (userInfoM16497V0 == null) {
            return false;
        }
        return (userInfoM16497V0.m15770a("Phone") == null && userInfoM16497V0.m15770a("Facebook") == null && userInfoM16497V0.m15770a("Google") == null && (C5170o0.m20170e(Globals.m7388i0().m7498V0()) ^ true)) ? false : true;
    }

    /* renamed from: u2 */
    public static void m16594u2(String str) {
        Globals.m7388i0().getSharedPreferences("cached_schedule_send_map", 0).edit().putBoolean(str + "_is_schedule_send_list_update", true).apply();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004b  */
    /* renamed from: v */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m16595v(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            if (Environment.getExternalStorageDirectory().canWrite()) {
                File file = new File(str);
                File file2 = new File(str2);
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                if (file.exists()) {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (Exception e9) {
                        e = e9;
                        fileOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                    }
                    try {
                        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                        while (true) {
                            int i9 = fileInputStream2.read(bArr);
                            if (i9 <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i9);
                        }
                        fileInputStream = fileInputStream2;
                    } catch (Exception e10) {
                        e = e10;
                        fileInputStream = fileInputStream2;
                        try {
                            e.printStackTrace();
                            C6370g.m24480b(fileInputStream);
                            C6370g.m24480b(fileOutputStream);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            C6370g.m24480b(fileInputStream);
                            C6370g.m24480b(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = fileInputStream2;
                        C6370g.m24480b(fileInputStream);
                        C6370g.m24480b(fileOutputStream);
                        throw th;
                    }
                } else {
                    fileOutputStream = null;
                }
            }
            C6370g.m24480b(fileInputStream);
            C6370g.m24480b(fileOutputStream);
            return true;
        } catch (Exception e11) {
            e = e11;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    /* renamed from: v0 */
    public static String m16596v0(Context context, Date date, String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(5, 1);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(5, -1);
        calendar3.set(11, 0);
        calendar3.set(12, 0);
        calendar3.set(13, 0);
        return (date.before(calendar.getTime()) && date.after(calendar3.getTime())) ? context.getString(R.string.file_list_activity_file_date_yesterday) : (date.after(calendar.getTime()) && date.before(calendar2.getTime())) ? context.getString(R.string.file_list_activity_file_date_today) : m16588t0(date.getTime(), str);
    }

    /* renamed from: v1 */
    public static boolean m16597v1(Uri uri) {
        return uri != null && FirebaseAnalytics.Param.CONTENT.equals(uri.getScheme());
    }

    /* renamed from: v2 */
    public static String m16598v2(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes());
            return m16551k(messageDigest.digest());
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: w */
    public static void m16599w(Context context, Uri uri, InterfaceC3137d interfaceC3137d) {
        long jM16572p0 = m16572p0(null, uri);
        String strM16552k0 = m16552k0(null, uri);
        if (jM16572p0 <= 52428800) {
            try {
                if (!C5170o0.m20169d(strM16552k0)) {
                    new C3134a(context, uri, interfaceC3137d, strM16552k0).start();
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                interfaceC3137d.mo7014a();
                return;
            }
        }
        interfaceC3137d.mo7014a();
    }

    /* renamed from: w0 */
    public static String m16600w0(String str, boolean z8) {
        String name = new File(str).getName();
        if (!z8) {
            if (name.contains("_1280.jpg")) {
                return name.replaceFirst("_1280.jpg", "");
            }
            return null;
        }
        if (name.contains("user_") && name.contains("_1280.jpg")) {
            return name.replaceFirst("user_", "").replaceFirst("_1280.jpg", "");
        }
        return null;
    }

    /* renamed from: w1 */
    public static boolean m16601w1(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    @Deprecated
    /* renamed from: w2 */
    public static void m16602w2(Activity activity, boolean z8) {
        if (z8) {
            m16606x2(activity);
        } else {
            m16589t1(activity);
        }
    }

    /* renamed from: x */
    public static String m16603x(double d9) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(d9);
    }

    /* renamed from: x0 */
    public static String m16604x0(String str) {
        String name = new File(str).getName();
        if (name.contains("U_") && name.contains(".jpg")) {
            return name.replaceFirst("U_", "").replaceFirst(".jpg", "");
        }
        return null;
    }

    /* renamed from: x1 */
    public static boolean m16605x1(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    /* renamed from: x2 */
    public static void m16606x2(Activity activity) {
        Window window;
        if (activity == null) {
            return;
        }
        View currentFocus = activity.getCurrentFocus();
        if ((currentFocus == null || !((InputMethodManager) activity.getSystemService("input_method")).showSoftInput(currentFocus, 1)) && (window = activity.getWindow()) != null) {
            window.setSoftInputMode(5);
        }
    }

    /* renamed from: y */
    public static MessageObj.MessageType m16607y(String str) {
        str.hashCode();
        return !str.equals("Static") ? !str.equals("Animation") ? MessageObj.MessageType.AnimPngSticker : MessageObj.MessageType.AnimSticker : MessageObj.MessageType.Sticker;
    }

    /* renamed from: y0 */
    public static String m16608y0(String str) {
        return m16612z0(str, false);
    }

    /* renamed from: y1 */
    public static boolean m16609y1(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /* renamed from: y2 */
    public static void m16610y2() {
        C5187v0.m20270f(R.string.verified_code_success);
    }

    /* renamed from: z */
    public static FileFilter m16611z(final String str) {
        return new FileFilter() { // from class: k4.e
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                return CLUtility.m16465M1(str, file);
            }
        };
    }

    /* renamed from: z0 */
    public static String m16612z0(String str, boolean z8) {
        String strM16416A0 = m16416A0(str, z8);
        if (strM16416A0 != null) {
            return strM16416A0;
        }
        String strM16600w0 = m16600w0(str, z8);
        if (strM16600w0 != null) {
            return strM16600w0;
        }
        String strM16604x0 = m16604x0(str);
        if (strM16604x0 != null) {
            return strM16604x0;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* renamed from: z1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m16613z1(String str, Uri uri) throws IOException {
        boolean z8;
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        if (m16597v1(uri)) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
                z8 = assetFileDescriptorOpenAssetFileDescriptor != null;
                if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                    try {
                        assetFileDescriptorOpenAssetFileDescriptor.close();
                    } catch (IOException e9) {
                        e = e9;
                        e.printStackTrace();
                        if (m16417A1(uri)) {
                            File file = new File(uri.getPath());
                            if (file.canRead()) {
                            }
                        }
                        return TextUtils.isEmpty(str) ? z8 : z8;
                    }
                }
            } catch (IOException e10) {
                e = e10;
                z8 = false;
            }
        } else {
            z8 = false;
        }
        if (m16417A1(uri) && !TextUtils.isEmpty(uri.getPath()) && !z8) {
            File file2 = new File(uri.getPath());
            z8 = (file2.canRead() || file2.isDirectory()) ? false : true;
        }
        if (TextUtils.isEmpty(str) && !z8) {
            File file3 = new File(str);
            return file3.canRead() && !file3.isDirectory();
        }
    }

    /* renamed from: z2 */
    public static void m16614z2(Activity activity, Group group, InviteType inviteType) {
        m16418A2(activity, group, inviteType, null);
    }
}
