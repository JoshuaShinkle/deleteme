package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class CharSet implements Serializable {
    public static final CharSet ASCII_ALPHA;
    public static final CharSet ASCII_ALPHA_LOWER;
    public static final CharSet ASCII_ALPHA_UPPER;
    public static final CharSet ASCII_NUMERIC;
    protected static final Map<String, CharSet> COMMON;
    public static final CharSet EMPTY;
    private static final long serialVersionUID = 5947847346149275958L;
    private final Set<CharRange> set = Collections.synchronizedSet(new HashSet());

    static {
        CharSet charSet = new CharSet(null);
        EMPTY = charSet;
        CharSet charSet2 = new CharSet("a-zA-Z");
        ASCII_ALPHA = charSet2;
        CharSet charSet3 = new CharSet("a-z");
        ASCII_ALPHA_LOWER = charSet3;
        CharSet charSet4 = new CharSet("A-Z");
        ASCII_ALPHA_UPPER = charSet4;
        CharSet charSet5 = new CharSet("0-9");
        ASCII_NUMERIC = charSet5;
        Map<String, CharSet> mapSynchronizedMap = Collections.synchronizedMap(new HashMap());
        COMMON = mapSynchronizedMap;
        mapSynchronizedMap.put(null, charSet);
        mapSynchronizedMap.put("", charSet);
        mapSynchronizedMap.put("a-zA-Z", charSet2);
        mapSynchronizedMap.put("A-Za-z", charSet2);
        mapSynchronizedMap.put("a-z", charSet3);
        mapSynchronizedMap.put("A-Z", charSet4);
        mapSynchronizedMap.put("0-9", charSet5);
    }

    public CharSet(String... strArr) {
        for (String str : strArr) {
            add(str);
        }
    }

    public static CharSet getInstance(String... strArr) {
        CharSet charSet;
        if (strArr == null) {
            return null;
        }
        return (strArr.length != 1 || (charSet = COMMON.get(strArr[0])) == null) ? new CharSet(strArr) : charSet;
    }

    public void add(String str) {
        if (str == null) {
            return;
        }
        int length = str.length();
        int i9 = 0;
        while (i9 < length) {
            int i10 = length - i9;
            if (i10 >= 4 && str.charAt(i9) == '^' && str.charAt(i9 + 2) == '-') {
                this.set.add(CharRange.isNotIn(str.charAt(i9 + 1), str.charAt(i9 + 3)));
                i9 += 4;
            } else if (i10 >= 3 && str.charAt(i9 + 1) == '-') {
                this.set.add(CharRange.isIn(str.charAt(i9), str.charAt(i9 + 2)));
                i9 += 3;
            } else if (i10 < 2 || str.charAt(i9) != '^') {
                this.set.add(CharRange.m21913is(str.charAt(i9)));
                i9++;
            } else {
                this.set.add(CharRange.isNot(str.charAt(i9 + 1)));
                i9 += 2;
            }
        }
    }

    public boolean contains(char c9) {
        Iterator<CharRange> it = this.set.iterator();
        while (it.hasNext()) {
            if (it.next().contains(c9)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CharSet) {
            return this.set.equals(((CharSet) obj).set);
        }
        return false;
    }

    public CharRange[] getCharRanges() {
        Set<CharRange> set = this.set;
        return (CharRange[]) set.toArray(new CharRange[set.size()]);
    }

    public int hashCode() {
        return this.set.hashCode() + 89;
    }

    public String toString() {
        return this.set.toString();
    }
}
