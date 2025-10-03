package org.webrtc;

import java.util.LinkedList;
import java.util.List;

/* loaded from: classes3.dex */
public class MediaConstraints {
    public final List<KeyValuePair> mandatory = new LinkedList();
    public final List<KeyValuePair> optional = new LinkedList();

    public static class KeyValuePair {
        private final String key;
        private final String value;

        public KeyValuePair(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            KeyValuePair keyValuePair = (KeyValuePair) obj;
            return this.key.equals(keyValuePair.key) && this.value.equals(keyValuePair.value);
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.key.hashCode() + this.value.hashCode();
        }

        public String toString() {
            return this.key + ": " + this.value;
        }
    }

    private static String stringifyKeyValuePairList(List<KeyValuePair> list) {
        StringBuilder sb = new StringBuilder("[");
        for (KeyValuePair keyValuePair : list) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(keyValuePair.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        return "mandatory: " + stringifyKeyValuePairList(this.mandatory) + ", optional: " + stringifyKeyValuePairList(this.optional);
    }
}
