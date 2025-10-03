package com.cyberlink.uno.unocore;

import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Event {

    /* renamed from: a */
    public final String f7173a;

    /* renamed from: b */
    public final String f7174b;

    /* renamed from: c */
    public final int f7175c;

    /* renamed from: d */
    public final long f7176d;

    public enum EventField {
        KEY("key", AppMeasurementSdk.ConditionalUserProperty.NAME),
        VALUE("value", "value"),
        ID(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID),
        TIMESTAMP("timestamp", "time");

        private final String field;
        private final String jsonKey;

        EventField(String str, String str2) {
            this.field = str;
            this.jsonKey = str2;
        }
    }

    public Event(String str, String str2, int i9, long j9) {
        this.f7173a = str;
        this.f7174b = str2;
        this.f7175c = i9;
        this.f7176d = j9;
    }

    /* renamed from: b */
    public static int m7218b() {
        return 18;
    }

    /* renamed from: d */
    public static Event m7219d(JSONObject jSONObject) throws JSONException {
        try {
            EventField eventField = EventField.KEY;
            if (jSONObject.isNull(eventField.field)) {
                return null;
            }
            String string = jSONObject.getString(eventField.field);
            String string2 = jSONObject.getString(EventField.VALUE.field);
            int iOptInt = jSONObject.optInt(EventField.ID.field);
            if (string.isEmpty()) {
                return null;
            }
            return new Event(string, string2, iOptInt, jSONObject.optLong(EventField.TIMESTAMP.field));
        } catch (JSONException e9) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get exception convert JSON to Event", e9);
            Log.e("UNOCoreEvent", "Got exception converting JSON to an Event", e9);
            return null;
        }
    }

    /* renamed from: a */
    public int m7220a() {
        int iM7222e = 2;
        for (EventField eventField : EventField.values()) {
            iM7222e += m7222e(eventField);
        }
        return iM7222e;
    }

    /* renamed from: c */
    public JSONObject m7221c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(EventField.KEY.jsonKey, this.f7173a);
            jSONObject.put(EventField.VALUE.jsonKey, this.f7174b);
            jSONObject.put(EventField.ID.jsonKey, this.f7175c);
            jSONObject.put(EventField.TIMESTAMP.jsonKey, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS Z", Locale.US) { // from class: com.cyberlink.uno.unocore.Event.1
                @Override // java.text.SimpleDateFormat, java.text.DateFormat
                public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
                    return super.format(date, stringBuffer, fieldPosition).insert(r1.length() - 2, ":");
                }

                @Override // java.text.SimpleDateFormat, java.text.DateFormat
                public Date parse(String str, ParsePosition parsePosition) {
                    if (str.length() > 3) {
                        str = str.substring(0, str.length() - 3) + str.substring(str.length() - 2);
                    }
                    return super.parse(str, parsePosition);
                }
            }.format(new Date(this.f7176d)));
        } catch (JSONException e9) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get exception when convert Event to JSON", e9);
            Log.e("UNOCoreEvent", "Got exception converting an Event to JSON", e9);
        }
        return jSONObject;
    }

    /* renamed from: e */
    public final int m7222e(EventField eventField) {
        int length;
        int length2 = eventField.jsonKey.length() + 2 + 0 + 2 + 2;
        if (eventField == EventField.KEY) {
            length = this.f7173a.length();
        } else if (eventField == EventField.VALUE) {
            length = this.f7174b.length();
        } else {
            if (eventField != EventField.ID) {
                return eventField == EventField.TIMESTAMP ? length2 + 30 : length2;
            }
            length = String.valueOf(this.f7175c).length();
        }
        return length2 + length;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        String str = this.f7173a;
        if (str == null) {
            if (event.f7173a != null) {
                return false;
            }
        } else if (!str.equals(event.f7173a)) {
            return false;
        }
        String str2 = this.f7174b;
        if (str2 == null) {
            if (event.f7174b != null) {
                return false;
            }
        } else if (!str2.equals(event.f7174b)) {
            return false;
        }
        return this.f7175c == event.f7175c && this.f7176d == event.f7176d;
    }

    /* renamed from: f */
    public JSONObject m7223f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(EventField.KEY.field, this.f7173a);
            jSONObject.put(EventField.VALUE.field, this.f7174b);
            jSONObject.put(EventField.ID.field, this.f7175c);
            jSONObject.put(EventField.TIMESTAMP.field, this.f7176d);
        } catch (JSONException e9) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get exception when convert Event to JSON", e9);
            Log.e("UNOCoreEvent", "Got exception converting an Event to JSON", e9);
        }
        return jSONObject;
    }

    public int hashCode() {
        String str = this.f7173a;
        int iHashCode = str != null ? str.hashCode() : 1;
        String str2 = this.f7174b;
        int iHashCode2 = iHashCode ^ (str2 != null ? str2.hashCode() : 1);
        int i9 = this.f7175c;
        if (i9 == 0) {
            i9 = 1;
        }
        int i10 = iHashCode2 ^ i9;
        long j9 = this.f7176d;
        return i10 ^ (j9 != 0 ? (int) j9 : 1);
    }
}
