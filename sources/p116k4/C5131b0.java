package p116k4;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: k4.b0 */
/* loaded from: classes.dex */
public class C5131b0 {

    /* renamed from: a */
    public static Map<String, b> f17631a = new HashMap();

    /* renamed from: b */
    public static ArrayList<HashMap<String, String>> f17632b = new ArrayList<>();

    /* renamed from: c */
    public static ArrayList<HashMap<String, String>> f17633c = new ArrayList<>();

    /* renamed from: k4.b0$a */
    public static class a implements Comparator<Map<String, String>> {

        /* renamed from: b */
        public final String f17634b;

        /* renamed from: c */
        public final Collator f17635c = Collator.getInstance();

        public a(String str) {
            this.f17634b = str;
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Map<String, String> map, Map<String, String> map2) {
            return this.f17635c.compare(map.get(this.f17634b) != null ? map.get(this.f17634b) : "", map2.get(this.f17634b) != null ? map2.get(this.f17634b) : "");
        }
    }

    /* renamed from: k4.b0$b */
    public static class b {

        /* renamed from: a */
        public String f17636a = "";

        /* renamed from: b */
        public String f17637b = "";

        /* renamed from: c */
        public String f17638c = "";

        /* renamed from: d */
        public String f17639d = "";

        /* renamed from: e */
        public Map<String, String> f17640e;

        /* renamed from: f */
        public Map<String, String> f17641f;

        public b() {
            this.f17640e = null;
            this.f17641f = null;
            this.f17640e = new HashMap();
            this.f17641f = new HashMap();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x015c  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m20003a(Context context) {
        Cursor cursorQuery;
        if (context == null) {
            return;
        }
        synchronized (f17631a) {
            f17631a.clear();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            cursorQuery = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, "mimetype = 'vnd.android.cursor.item/phone_v2' OR mimetype = 'vnd.android.cursor.item/email_v2'", null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("mimetype"));
                    if (string.equals("vnd.android.cursor.item/phone_v2") || string.equals("vnd.android.cursor.item/email_v2")) {
                        String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("contact_id"));
                        String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("account_name"));
                        String string4 = cursorQuery.getString(cursorQuery.getColumnIndex("display_name"));
                        String string5 = cursorQuery.getString(cursorQuery.getColumnIndex("photo_uri"));
                        b bVar = f17631a.get(string2);
                        if (bVar == null) {
                            bVar = new b();
                            synchronized (f17631a) {
                                f17631a.put(string2, bVar);
                            }
                        }
                        if (!bVar.f17636a.equals("") || string2 == null) {
                            string2 = bVar.f17636a;
                        }
                        bVar.f17636a = string2;
                        if (!bVar.f17637b.equals("") || string3 == null) {
                            string3 = bVar.f17637b;
                        }
                        bVar.f17637b = string3;
                        if (!bVar.f17638c.equals("") || string4 == null) {
                            string4 = bVar.f17638c;
                        }
                        bVar.f17638c = string4;
                        if (!bVar.f17639d.equals("") || string5 == null) {
                            string5 = bVar.f17639d;
                        }
                        bVar.f17639d = string5;
                        if (string.equals("vnd.android.cursor.item/phone_v2")) {
                            bVar.f17640e.put(cursorQuery.getString(cursorQuery.getColumnIndex("data1")), cursorQuery.getString(cursorQuery.getColumnIndex("data2")));
                        } else if (string.equals("vnd.android.cursor.item/email_v2")) {
                            bVar.f17641f.put(cursorQuery.getString(cursorQuery.getColumnIndex("data1")), cursorQuery.getString(cursorQuery.getColumnIndex("data2")));
                        }
                    } else {
                        Log.d("PhoneContentUtils", "[getAllContent] Other MimeType = " + string + "\n");
                    }
                } catch (Exception e9) {
                    e = e9;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[getAllContent] exception:\n");
                    for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                    Log.d("PhoneContentUtils", sb.toString());
                    if (cursorQuery != null) {
                    }
                    Log.d("PhoneContentUtils", "getAllContent " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                }
            }
        } catch (Exception e10) {
            e = e10;
            cursorQuery = null;
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        Log.d("PhoneContentUtils", "getAllContent " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
    }

    /* renamed from: b */
    public static ArrayList<HashMap<String, String>> m20004b(String str, String str2, Context context) {
        if (!f17632b.isEmpty()) {
            return f17632b;
        }
        if (f17631a.isEmpty()) {
            m20003a(context);
        }
        Iterator<Map.Entry<String, b>> it = f17631a.entrySet().iterator();
        while (it.hasNext()) {
            b value = it.next().getValue();
            for (Map.Entry<String, String> entry : value.f17641f.entrySet()) {
                HashMap<String, String> map = new HashMap<>();
                map.put(str, value.f17638c);
                map.put(str2, entry.getKey());
                f17632b.add(map);
            }
        }
        Collections.sort(f17632b, new a(str));
        return f17632b;
    }

    /* renamed from: c */
    public static ArrayList<HashMap<String, String>> m20005c(String str, String str2, Context context) {
        if (!f17633c.isEmpty()) {
            return f17633c;
        }
        if (f17631a.isEmpty()) {
            m20003a(context);
        }
        Iterator<Map.Entry<String, b>> it = f17631a.entrySet().iterator();
        while (it.hasNext()) {
            b value = it.next().getValue();
            for (Map.Entry<String, String> entry : value.f17640e.entrySet()) {
                HashMap<String, String> map = new HashMap<>();
                map.put(str, value.f17638c);
                map.put(str2, entry.getKey());
                f17633c.add(map);
            }
        }
        Collections.sort(f17633c, new a(str));
        return f17633c;
    }
}
