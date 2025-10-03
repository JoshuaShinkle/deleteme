package com.cyberlink.you.database;

import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.database.MessageObj;
import com.google.firebase.iid.ServiceStarter;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import org.apache.commons.lang3.StringUtils;
import p116k4.C5154j;

/* renamed from: com.cyberlink.you.database.u0 */
/* loaded from: classes.dex */
public class C2991u0 {

    /* renamed from: a */
    public final String f13234a = "SearchMessageDao";

    /* renamed from: b */
    public final int f13235b = ServiceStarter.ERROR_UNKNOWN;

    /* renamed from: c */
    public final String f13236c = "Message";

    /* renamed from: d */
    public final String f13237d = "messagesCount";

    /* renamed from: e */
    public final String f13238e = "count(*) as messagesCount";

    /* renamed from: f */
    public final String f13239f = "%";

    /* renamed from: g */
    public final String f13240g = " AND ";

    /* renamed from: h */
    public final String f13241h = " OR ";

    /* renamed from: i */
    public final String f13242i = "MessageType ='" + MessageObj.MessageType.Text.toString() + "'";

    /* renamed from: j */
    public final String f13243j = "MessageType ='" + MessageObj.MessageType.Comment.toString() + "'";

    /* renamed from: k */
    public final String f13244k = "MessageType ='" + MessageObj.MessageType.PhotoNote.toString() + "'";

    /* renamed from: l */
    public final String f13245l = "MessageType ='" + MessageObj.MessageType.ReplyText.toString() + "'";

    /* renamed from: m */
    public final String f13246m = "GroupId =? ";

    /* renamed from: n */
    public final String f13247n = "MessageContent like ? escape '\\'";

    /* renamed from: o */
    public final String f13248o = "SearchMessage like ? escape '\\'";

    /* renamed from: p */
    public final String f13249p = "SearchMessage is null ";

    /* renamed from: q */
    public final String f13250q = "SearchMessage is not null ";

    /* renamed from: r */
    public final String[] f13251r = {"MessageId", "GroupId", "SendTime", "MessageContent", "UserId", "MessageType", "SearchMessage"};

    /* renamed from: s */
    public final String[] f13252s = {"MessageId", "GroupId", "SendTime", "MessageContent", "UserId", "MessageType", "SearchMessage", "count(*) as messagesCount"};

    /* renamed from: t */
    public final String[] f13253t = {"MessageId"};

    /* renamed from: u */
    public final SQLiteDatabase f13254u = C2950b0.m14900B();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public /* synthetic */ void m15239f(Cursor cursor) {
        do {
            m15247j(cursor);
        } while (cursor.moveToNext());
    }

    /* renamed from: b */
    public final String m15240b(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < str.length(); i9++) {
            sb.append("\\");
            sb.append(str.charAt(i9));
        }
        return sb.toString();
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008f: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:36:0x008f */
    /* renamed from: c */
    public final List<C2993v0> m15241c(String[] strArr, String str, String[] strArr2, boolean z8) throws Throwable {
        Cursor cursor;
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            try {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    cursorQuery = this.f13254u.query("Message", strArr, str, strArr2, z8 ? "GroupId" : null, null, "SendTime DESC");
                    try {
                        if (cursorQuery == null) {
                            Log.e("SearchMessageDao", "[getResult] Failed to query: cursor is null");
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return arrayList;
                        }
                        if (!cursorQuery.moveToFirst()) {
                            Log.v("SearchMessageDao", "[getResult] Database has no records.");
                            cursorQuery.close();
                            return arrayList;
                        }
                        do {
                            arrayList.add(m15242d(cursorQuery));
                        } while (cursorQuery.moveToNext());
                        Log.v("SearchMessageDao", "[getResult] Querying takes " + ((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d) + " seconds.");
                        cursorQuery.close();
                        return arrayList;
                    } catch (Exception e9) {
                        e = e9;
                        cursor2 = cursorQuery;
                        Log.e("SearchMessageDao", "[getResult] Exception ");
                        e.printStackTrace();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return arrayList;
                    } catch (OutOfMemoryError e10) {
                        e = e10;
                        Log.e("SearchMessageDao", "[getResult] OutOfMemoryError ");
                        e.printStackTrace();
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Exception e11) {
                e = e11;
            } catch (OutOfMemoryError e12) {
                e = e12;
                cursorQuery = null;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
        }
    }

    /* renamed from: d */
    public final C2993v0 m15242d(Cursor cursor) {
        try {
            int columnIndex = cursor.getColumnIndex("MessageId");
            int columnIndex2 = cursor.getColumnIndex("GroupId");
            int columnIndex3 = cursor.getColumnIndex("SendTime");
            int columnIndex4 = cursor.getColumnIndex("MessageContent");
            int columnIndex5 = cursor.getColumnIndex("UserId");
            int columnIndex6 = cursor.getColumnIndex("MessageType");
            int columnIndex7 = cursor.getColumnIndex("messagesCount");
            int columnIndex8 = cursor.getColumnIndex("SearchMessage");
            if (columnIndex >= 0 && columnIndex2 >= 0 && columnIndex3 >= 0 && columnIndex4 >= 0 && columnIndex5 >= 0 && columnIndex6 >= 0 && columnIndex8 >= 0) {
                return new C2993v0(cursor.getString(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex6).equals(MessageObj.MessageType.Text.toString()) ? cursor.getString(columnIndex4) : cursor.getString(columnIndex8), cursor.getString(columnIndex5), columnIndex7 > 0 ? cursor.getString(columnIndex7) : "1");
            }
            Log.e("SearchMessageDao", "[getSearchMessageObj] cursor.getColumnIndex() returned negative number");
            return null;
        } catch (OutOfMemoryError e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public final Pair<String, ArrayList<String>> m15243e(String str) {
        String[] strArrSplit = str.split(StringUtils.SPACE);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArrSplit) {
            if (sb.length() != 0) {
                sb.append(" AND ");
            }
            sb.append("MessageContent like ? escape '\\'");
            if (sb2.length() != 0) {
                sb2.append(" AND ");
            }
            sb2.append("SearchMessage like ? escape '\\'");
        }
        for (String str3 : strArrSplit) {
            arrayList.add("%" + m15240b(str3) + "%");
        }
        for (String str4 : strArrSplit) {
            arrayList.add("%" + m15240b(str4) + "%");
        }
        return Pair.create(this.f13242i + " AND " + sb.toString() + " OR SearchMessage is not null  AND " + sb2.toString(), arrayList);
    }

    /* renamed from: g */
    public int m15244g() {
        android.database.Cursor cursor = null;
        try {
            try {
                final Cursor cursorQuery = this.f13254u.query("Message", this.f13253t, "(" + this.f13243j + " OR " + this.f13244k + " OR " + this.f13245l + ") AND SearchMessage is null ", null, null, null, "SendTime DESC", String.valueOf(ServiceStarter.ERROR_UNKNOWN));
                if (cursorQuery == null) {
                    Log.e("SearchMessageDao", "[parseMessageContent] Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return -1;
                }
                if (cursorQuery.moveToFirst()) {
                    int count = C2950b0.m14901C(new Runnable() { // from class: com.cyberlink.you.database.t0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f13232b.m15239f(cursorQuery);
                        }
                    }) ? cursorQuery.getCount() : -1;
                    cursorQuery.close();
                    return count;
                }
                Log.v("SearchMessageDao", "[parseMessageContent] Database has no records.");
                cursorQuery.close();
                return 0;
            } catch (Exception e9) {
                C5154j.m20076j(e9);
                if (0 != 0) {
                    cursor.close();
                }
                return -1;
            } catch (OutOfMemoryError e10) {
                C5154j.m20076j(e10);
                if (0 != 0) {
                    cursor.close();
                }
                return -1;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: h */
    public List<C2993v0> m15245h(String str) {
        Pair<String, ArrayList<String>> pairM15243e = m15243e(str);
        return m15241c(this.f13252s, (String) pairM15243e.first, (String[]) ((ArrayList) pairM15243e.second).toArray(new String[((ArrayList) pairM15243e.second).size()]), true);
    }

    /* renamed from: i */
    public List<C2993v0> m15246i(String str, String str2) {
        Pair<String, ArrayList<String>> pairM15243e = m15243e(str);
        String str3 = "(" + ((String) pairM15243e.first) + ") AND GroupId =? ";
        ((ArrayList) pairM15243e.second).add(str2);
        return m15241c(this.f13251r, str3, (String[]) ((ArrayList) pairM15243e.second).toArray(new String[((ArrayList) pairM15243e.second).size()]), false);
    }

    /* renamed from: j */
    public final void m15247j(Cursor cursor) {
        try {
            int columnIndex = cursor.getColumnIndex("MessageId");
            if (columnIndex < 0) {
                Log.e("SearchMessageDao", "[updateSearchMessageInMessageTable] cursor.getColumnIndex() returned negative number");
                return;
            }
            String string = cursor.getString(columnIndex);
            C2950b0.m14916o().m15159D(string, C2950b0.m14916o().m15179r(string), "SearchMessage");
        } catch (OutOfMemoryError e9) {
            e9.printStackTrace();
        }
    }
}
