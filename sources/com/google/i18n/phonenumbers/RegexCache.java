package com.google.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class RegexCache {
    private LRUCache<String, Pattern> cache;

    public static class LRUCache<K, V> {
        private LinkedHashMap<K, V> map;
        private int size;

        public LRUCache(int i9) {
            this.size = i9;
            this.map = new LinkedHashMap<K, V>(((i9 * 4) / 3) + 1, 0.75f, true) { // from class: com.google.i18n.phonenumbers.RegexCache.LRUCache.1
                @Override // java.util.LinkedHashMap
                public boolean removeEldestEntry(Map.Entry<K, V> entry) {
                    return size() > LRUCache.this.size;
                }
            };
        }

        public synchronized boolean containsKey(K k9) {
            return this.map.containsKey(k9);
        }

        public synchronized V get(K k9) {
            return this.map.get(k9);
        }

        public synchronized void put(K k9, V v8) {
            this.map.put(k9, v8);
        }
    }

    public RegexCache(int i9) {
        this.cache = new LRUCache<>(i9);
    }

    public boolean containsRegex(String str) {
        return this.cache.containsKey(str);
    }

    public Pattern getPatternForRegex(String str) {
        Pattern pattern = this.cache.get(str);
        if (pattern != null) {
            return pattern;
        }
        Pattern patternCompile = Pattern.compile(str);
        this.cache.put(str, patternCompile);
        return patternCompile;
    }
}
