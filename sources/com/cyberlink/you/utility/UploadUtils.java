package com.cyberlink.you.utility;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import p209u2.C6370g;

/* loaded from: classes.dex */
public class UploadUtils {

    /* renamed from: a */
    public static String f14706a = "UploadUtils";

    public enum UploadResultType {
        STEP_1_SUCCESS,
        STEP_1_FAIL,
        STEP_1_REACH_LIMIT,
        STEP_2_VOICE_FAIL,
        STEP_2_VIDEO_FAIL,
        STEP_2_SMALL_FAIL,
        STEP_2_SUCCESS,
        STEP_3_VOICE_FAIL,
        STEP_3_VIDEO_FAIL,
        STEP_3_SMALL_FAIL,
        STEP_3_BIG_FAIL,
        STEP_3_SUCCESS,
        STEP_2_FILE_FAIL,
        STEP_3_FILE_FAIL
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0082: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:33:0x0082 */
    @Deprecated
    /* renamed from: a */
    public static byte[] m16954a(String str) {
        Object obj;
        FileInputStream fileInputStream;
        boolean z8;
        File file = new File(str);
        Object obj2 = null;
        try {
            if (!file.exists()) {
                return null;
            }
            try {
                int length = (int) file.length();
                byte[] bArr = new byte[length];
                fileInputStream = new FileInputStream(file);
                int i9 = 0;
                do {
                    try {
                        int i10 = fileInputStream.read(bArr, i9, length - i9);
                        z8 = i10 == -1;
                        i9 += i10;
                        Log.d(f14706a, "[FileToByteArray] size read = " + i10);
                        if (i9 >= length) {
                            break;
                        }
                    } catch (Exception e9) {
                        e = e9;
                        e.printStackTrace();
                        C6370g.m24480b(fileInputStream);
                        return null;
                    } catch (OutOfMemoryError e10) {
                        e = e10;
                        e.printStackTrace();
                        C6370g.m24480b(fileInputStream);
                        return null;
                    }
                } while (!z8);
                Log.d(f14706a, "[FileToByteArray] file size = " + length + "; total size read = " + i9 + "; end-of-stream = " + z8);
                C6370g.m24480b(fileInputStream);
                return bArr;
            } catch (Exception e11) {
                e = e11;
                fileInputStream = null;
            } catch (OutOfMemoryError e12) {
                e = e12;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                C6370g.m24480b(obj2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            obj2 = obj;
        }
    }

    /* renamed from: b */
    public static String m16955b(String str, String str2, Uri uri) {
        if (uri != null) {
            return CLUtility.m16598v2(str + uri.toString() + CLUtility.m16568o0(str2, uri));
        }
        if (str2 != null) {
            File file = new File(str2);
            if (file.exists()) {
                return CLUtility.m16598v2(str + str2 + file.lastModified());
            }
        }
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v12, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object] */
    /* renamed from: c */
    public static byte[] m16956c(String str, Uri uri) throws Throwable {
        InputStream inputStream;
        Object obj;
        InputStream inputStream2;
        Object obj2;
        InputStream inputStream3;
        int length;
        InputStream fileInputStream;
        Object obj3;
        boolean z8;
        ?? M16613z1 = CLUtility.m16613z1(str, uri);
        Object obj4 = null;
        if (M16613z1 == 0) {
            return null;
        }
        try {
            try {
                if (uri != null) {
                    str = Globals.m7372O().getContentResolver().query(uri, null, null, null, null);
                    try {
                        str.moveToFirst();
                        length = (int) str.getLong(str.getColumnIndex("_size"));
                        fileInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
                        obj3 = str;
                    } catch (Exception e9) {
                        e = e9;
                        inputStream3 = null;
                        obj2 = str;
                        e.printStackTrace();
                        M16613z1 = inputStream3;
                        str = obj2;
                        C6370g.m24480b(str);
                        C6370g.m24480b(M16613z1);
                        return null;
                    } catch (OutOfMemoryError e10) {
                        e = e10;
                        inputStream2 = null;
                        obj = str;
                        e.printStackTrace();
                        M16613z1 = inputStream2;
                        str = obj;
                        C6370g.m24480b(str);
                        C6370g.m24480b(M16613z1);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        M16613z1 = 0;
                        obj4 = str;
                        inputStream = M16613z1;
                        C6370g.m24480b(obj4);
                        C6370g.m24480b(inputStream);
                        throw th;
                    }
                } else {
                    File file = new File((String) str);
                    length = (int) file.length();
                    fileInputStream = new FileInputStream(file);
                    obj3 = null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e11) {
            e = e11;
            obj2 = null;
            inputStream3 = null;
        } catch (OutOfMemoryError e12) {
            e = e12;
            obj = null;
            inputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[length];
            int i9 = 0;
            do {
                int i10 = fileInputStream.read(bArr, i9, length - i9);
                z8 = i10 == -1;
                i9 += i10;
                Log.d(f14706a, "[FileToByteArray] size read = " + i10);
                if (i9 >= length) {
                    break;
                }
            } while (!z8);
            Log.d(f14706a, "[FileToByteArray] file size = " + length + "; total size read = " + i9 + "; end-of-stream = " + z8);
            C6370g.m24480b(obj3);
            C6370g.m24480b(fileInputStream);
            return bArr;
        } catch (Exception e13) {
            inputStream3 = fileInputStream;
            e = e13;
            obj2 = obj3;
            e.printStackTrace();
            M16613z1 = inputStream3;
            str = obj2;
            C6370g.m24480b(str);
            C6370g.m24480b(M16613z1);
            return null;
        } catch (OutOfMemoryError e14) {
            inputStream2 = fileInputStream;
            e = e14;
            obj = obj3;
            e.printStackTrace();
            M16613z1 = inputStream2;
            str = obj;
            C6370g.m24480b(str);
            C6370g.m24480b(M16613z1);
            return null;
        } catch (Throwable th4) {
            obj4 = obj3;
            inputStream = fileInputStream;
            th = th4;
            C6370g.m24480b(obj4);
            C6370g.m24480b(inputStream);
            throw th;
        }
    }

    /* renamed from: d */
    public static String m16957d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fileSize", 0);
            jSONObject.put("hashKey", "");
            jSONObject.put("md5", "");
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: e */
    public static String m16958e(C3195a c3195a, String str, Uri uri, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!CLUtility.m16613z1(str, uri)) {
            return m16957d();
        }
        try {
            String strM16955b = m16955b(c3195a.f14722a, str, uri);
            jSONObject.put("fileSize", CLUtility.m16572p0(str, uri));
            jSONObject.put("hashKey", strM16955b);
            jSONObject.put("type", str3);
            if (str2 == null) {
                str2 = "";
            } else if (str2.startsWith("\"") && str2.endsWith("\"")) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            jSONObject.put("md5", str2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            Log.d(f14706a, "put fileSize, hashKey, or md5 fail");
            return m16957d();
        }
    }

    /* renamed from: f */
    public static String m16959f(C3195a c3195a, String str, String str2) {
        return m16960g(c3195a, str, str2, c3195a != null ? m16955b(c3195a.f14722a, str, null) : "");
    }

    /* renamed from: g */
    public static String m16960g(C3195a c3195a, String str, String str2, String str3) {
        return m16961h(c3195a, str, str2, str3, null);
    }

    /* renamed from: h */
    public static String m16961h(C3195a c3195a, String str, String str2, String str3, String str4) {
        int i9;
        JSONObject jSONObject = new JSONObject();
        if (str == null) {
            return m16957d();
        }
        File file = new File(str);
        try {
            if (!file.exists()) {
                return m16957d();
            }
            jSONObject.put("fileSize", file.length());
            jSONObject.put("hashKey", str3);
            if (str4 != null) {
                jSONObject.put(TtmlNode.ATTR_TTS_COLOR, str4);
            }
            if (c3195a != null && (i9 = c3195a.f14724c) != 0 && c3195a.f14725d != 0) {
                jSONObject.put("width", i9);
                jSONObject.put("height", c3195a.f14725d);
            }
            if (str2 == null) {
                str2 = "";
            } else if (str2.startsWith("\"") && str2.endsWith("\"")) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            jSONObject.put("md5", str2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            Log.d(f14706a, "put fileSize, hashKey, or md5 fail");
            return m16957d();
        }
    }

    /* renamed from: i */
    public static String m16962i(C3195a c3195a, String str, Uri uri, String str2, String str3, String str4, long j9, long j10) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!CLUtility.m16613z1(str, uri)) {
            return m16957d();
        }
        try {
            String strM16955b = m16955b(c3195a.f14722a, str, uri);
            jSONObject.put("fileSize", CLUtility.m16572p0(str, uri));
            jSONObject.put("hashKey", strM16955b);
            if (TextUtils.isEmpty(str3)) {
                str3 = "0";
            }
            jSONObject.put("duration", str3);
            jSONObject.put("type", str4);
            if (str2 == null) {
                str2 = "";
            } else if (str2.startsWith("\"") && str2.endsWith("\"")) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            jSONObject.put("md5", str2);
            if (j9 != 0) {
                jSONObject.put("width", j9);
            }
            if (j10 != 0) {
                jSONObject.put("height", j10);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            Log.d(f14706a, "put fileSize, hashKey, or md5 fail");
            return m16957d();
        }
    }

    /* renamed from: j */
    public static String m16963j(C3195a c3195a, String str, String str2, String str3, String str4) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        File file = new File(str);
        try {
            if (!file.exists()) {
                return m16957d();
            }
            String strM16955b = m16955b(c3195a.f14722a, str, null);
            jSONObject.put("fileSize", file.length());
            jSONObject.put("hashKey", strM16955b);
            if (str3 == null || str3.isEmpty()) {
                str3 = "0";
            }
            jSONObject.put("duration", str3);
            jSONObject.put("type", str4);
            if (str2 == null) {
                str2 = "";
            } else if (str2.startsWith("\"") && str2.endsWith("\"")) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            jSONObject.put("md5", str2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            Log.d(f14706a, "put fileSize, hashKey, or md5 fail");
            return m16957d();
        }
    }

    /* renamed from: k */
    public static void m16964k(String str, String str2, Exception exc) {
        String str3 = "";
        if (exc != null) {
            try {
                String simpleName = exc.getClass().getSimpleName();
                if (!simpleName.contains("Exception")) {
                    simpleName = exc.getClass().getName();
                }
                str3 = " (E:" + simpleName + ":" + exc.getMessage() + ")";
            } catch (Exception unused) {
                return;
            }
        }
        ULogUtility.m16661D(str, str2 + str3);
    }

    /* renamed from: l */
    public static void m16965l(String str, String str2) {
        m16964k(str, str2, null);
    }

    /* renamed from: com.cyberlink.you.utility.UploadUtils$b */
    public static class C3196b {

        /* renamed from: a */
        public boolean f14733a;

        /* renamed from: b */
        public long f14734b;

        /* renamed from: c */
        @SuppressLint({"UseSparseArrays"})
        public final HashMap<Integer, a> f14735c;

        /* renamed from: d */
        public final Object f14736d;

        public C3196b() {
            this.f14735c = new HashMap<>();
            this.f14736d = new Object();
            this.f14733a = false;
            this.f14734b = 1L;
        }

        /* renamed from: a */
        public boolean m16966a(UploadMediaHelper.MediaType mediaType, int i9, String str, long j9, long j10, boolean z8) {
            synchronized (this.f14736d) {
                if (this.f14735c.containsKey(Integer.valueOf(i9))) {
                    return false;
                }
                if (i9 <= 0) {
                    return false;
                }
                a aVar = new a();
                aVar.f14737a = mediaType;
                aVar.f14738b = i9;
                aVar.f14739c = str;
                aVar.f14740d = j9;
                aVar.f14745i = z8;
                aVar.f14741e = j10;
                synchronized (this.f14736d) {
                    this.f14735c.put(Integer.valueOf(i9), aVar);
                }
                return true;
            }
        }

        /* renamed from: b */
        public void m16967b() {
            synchronized (this.f14736d) {
                this.f14735c.clear();
            }
        }

        /* renamed from: c */
        public a m16968c(int i9) {
            synchronized (this.f14736d) {
                if (!this.f14735c.containsKey(Integer.valueOf(i9))) {
                    return null;
                }
                return this.f14735c.get(Integer.valueOf(i9));
            }
        }

        /* renamed from: d */
        public boolean m16969d() {
            boolean z8;
            synchronized (this.f14736d) {
                z8 = true;
                for (a aVar : this.f14735c.values()) {
                    if (aVar != null) {
                        z8 &= aVar.f14745i;
                    }
                }
            }
            return z8;
        }

        /* renamed from: e */
        public boolean m16970e() {
            boolean z8;
            synchronized (this.f14736d) {
                z8 = true;
                for (a aVar : this.f14735c.values()) {
                    if (aVar != null) {
                        z8 &= aVar.f14744h;
                    }
                }
            }
            return z8;
        }

        /* renamed from: f */
        public int m16971f() {
            int size;
            synchronized (this.f14736d) {
                size = this.f14735c.size();
            }
            return size;
        }

        /* renamed from: g */
        public void m16972g() {
            synchronized (this.f14736d) {
                for (a aVar : this.f14735c.values()) {
                    if (aVar != null) {
                        aVar.m16975b();
                    }
                }
            }
        }

        /* renamed from: h */
        public String m16973h() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("isMultiPart", this.f14733a);
                jSONObject.put("partAmount", this.f14734b);
                synchronized (this.f14736d) {
                    for (int i9 = 1; i9 <= this.f14734b; i9++) {
                        String str = "task_" + i9;
                        a aVar = this.f14735c.get(Integer.valueOf(i9));
                        if (aVar != null) {
                            jSONObject.put(str, aVar.m16977d());
                        } else {
                            jSONObject.put(str, "");
                        }
                    }
                }
                return jSONObject.toString();
            } catch (JSONException e9) {
                e9.printStackTrace();
                return "";
            }
        }

        public C3196b(JSONObject jSONObject) {
            this.f14735c = new HashMap<>();
            Object obj = new Object();
            this.f14736d = obj;
            try {
                this.f14733a = jSONObject.getBoolean("isMultiPart");
                this.f14734b = jSONObject.getLong("partAmount");
                synchronized (obj) {
                    for (int i9 = 1; i9 <= this.f14734b; i9++) {
                        this.f14735c.put(Integer.valueOf(i9), new a(new JSONObject(jSONObject.getString("task_" + i9))));
                    }
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: com.cyberlink.you.utility.UploadUtils$b$a */
        public static class a {

            /* renamed from: a */
            public UploadMediaHelper.MediaType f14737a;

            /* renamed from: b */
            public int f14738b;

            /* renamed from: c */
            public String f14739c;

            /* renamed from: d */
            public long f14740d;

            /* renamed from: e */
            public long f14741e;

            /* renamed from: f */
            public HttpURLConnection f14742f;

            /* renamed from: g */
            public DataOutputStream f14743g;

            /* renamed from: h */
            public boolean f14744h;

            /* renamed from: i */
            public boolean f14745i;

            /* renamed from: j */
            public long f14746j;

            /* renamed from: k */
            public final Object f14747k;

            public a() {
                this.f14742f = null;
                this.f14743g = null;
                this.f14744h = false;
                this.f14745i = false;
                this.f14747k = new Object();
                this.f14737a = UploadMediaHelper.MediaType.SMALL_IMG;
                this.f14738b = 1;
                this.f14739c = "";
                this.f14740d = 0L;
                this.f14746j = 0L;
                this.f14741e = 0L;
            }

            /* renamed from: a */
            public long m16974a() {
                long j9;
                synchronized (this.f14747k) {
                    j9 = this.f14746j;
                }
                return j9;
            }

            /* renamed from: b */
            public void m16975b() {
                HttpURLConnection httpURLConnection = this.f14742f;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    this.f14742f = null;
                }
            }

            /* renamed from: c */
            public void m16976c(long j9) {
                synchronized (this.f14747k) {
                    this.f14746j = j9;
                }
            }

            /* renamed from: d */
            public String m16977d() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("mediaType", this.f14737a.toString());
                    jSONObject.put("partIndex", this.f14738b);
                    jSONObject.put("uploadUrl", this.f14739c);
                    jSONObject.put("dataLength", this.f14740d);
                    jSONObject.put("sentLength", m16974a());
                    jSONObject.put("startBytePos", this.f14741e);
                    jSONObject.put("isSucc", this.f14744h);
                    jSONObject.put("isComplete", this.f14745i);
                    return jSONObject.toString();
                } catch (JSONException e9) {
                    e9.printStackTrace();
                    return "";
                }
            }

            public a(JSONObject jSONObject) {
                this.f14742f = null;
                this.f14743g = null;
                this.f14744h = false;
                this.f14745i = false;
                this.f14747k = new Object();
                try {
                    this.f14737a = UploadMediaHelper.MediaType.valueOf(jSONObject.getString("mediaType"));
                    this.f14738b = jSONObject.getInt("partIndex");
                    this.f14739c = jSONObject.getString("uploadUrl");
                    this.f14740d = jSONObject.getLong("dataLength");
                    this.f14741e = jSONObject.getLong("startBytePos");
                    this.f14744h = jSONObject.getBoolean("isSucc");
                    this.f14745i = jSONObject.getBoolean("isComplete");
                    m16976c(jSONObject.getLong("sentLength"));
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadUtils$a */
    public static class C3195a {

        /* renamed from: a */
        public String f14722a;

        /* renamed from: b */
        public String f14723b;

        /* renamed from: c */
        public int f14724c;

        /* renamed from: d */
        public int f14725d;

        /* renamed from: e */
        public byte[] f14726e;

        /* renamed from: f */
        public String f14727f;

        /* renamed from: g */
        public String f14728g;

        /* renamed from: h */
        public Uri f14729h;

        /* renamed from: i */
        public long f14730i;

        /* renamed from: j */
        public String f14731j;

        /* renamed from: k */
        public String f14732k;

        public C3195a() {
            this.f14731j = null;
            this.f14732k = null;
            this.f14722a = null;
            this.f14723b = null;
            this.f14724c = 0;
            this.f14725d = 0;
            this.f14726e = null;
            this.f14727f = null;
            this.f14728g = null;
            this.f14729h = null;
            this.f14730i = 0L;
        }

        public String toString() {
            return ((((((("{\"originalName\":\"" + this.f14722a + "\",") + "\"name\":\"" + this.f14723b + "\",") + "\"width\":\"" + this.f14724c + "\",") + "\"height\":\"" + this.f14725d + "\",") + "\"md5\":\"" + this.f14727f + "\",") + "\"key\":\"" + this.f14731j + "\",") + "\"mac\":\"" + this.f14732k + "\"") + "}";
        }

        public C3195a(JSONObject jSONObject) {
            this.f14731j = null;
            this.f14732k = null;
            try {
                this.f14722a = jSONObject.getString("originalName");
                this.f14723b = jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                this.f14724c = jSONObject.getInt("width");
                this.f14725d = jSONObject.getInt("height");
                this.f14727f = jSONObject.getString("md5");
                this.f14731j = jSONObject.optString("key", null);
                this.f14732k = jSONObject.optString("auth", null);
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
    }
}
