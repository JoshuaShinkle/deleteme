package org.webrtc;

/* loaded from: classes3.dex */
public class StatsReport {

    /* renamed from: id */
    public final String f20238id;
    public final double timestamp;
    public final String type;
    public final Value[] values;

    public static class Value {
        public final String name;
        public final String value;

        public Value(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public String toString() {
            return "[" + this.name + ": " + this.value + "]";
        }
    }

    public StatsReport(String str, String str2, double d9, Value[] valueArr) {
        this.f20238id = str;
        this.type = str2;
        this.timestamp = d9;
        this.values = valueArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(this.f20238id);
        sb.append(", type: ");
        sb.append(this.type);
        sb.append(", timestamp: ");
        sb.append(this.timestamp);
        sb.append(", values: ");
        int i9 = 0;
        while (true) {
            Value[] valueArr = this.values;
            if (i9 >= valueArr.length) {
                return sb.toString();
            }
            sb.append(valueArr[i9].toString());
            sb.append(", ");
            i9++;
        }
    }
}
