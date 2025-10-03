package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class SimpleCacheSpan extends CacheSpan {
    private static final Pattern CACHE_FILE_PATTERN_V1 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v1\\.exo$", 32);
    private static final Pattern CACHE_FILE_PATTERN_V2 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v2\\.exo$", 32);
    private static final Pattern CACHE_FILE_PATTERN_V3 = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.v3\\.exo$", 32);
    private static final String SUFFIX = ".v3.exo";

    private SimpleCacheSpan(String str, long j9, long j10, long j11, File file) {
        super(str, j9, j10, j11, file);
    }

    public static SimpleCacheSpan createCacheEntry(File file, CachedContentIndex cachedContentIndex) {
        String name = file.getName();
        if (!name.endsWith(SUFFIX)) {
            file = upgradeFile(file, cachedContentIndex);
            if (file == null) {
                return null;
            }
            name = file.getName();
        }
        File file2 = file;
        Matcher matcher = CACHE_FILE_PATTERN_V3.matcher(name);
        if (!matcher.matches()) {
            return null;
        }
        long length = file2.length();
        String keyForId = cachedContentIndex.getKeyForId(Integer.parseInt(matcher.group(1)));
        if (keyForId == null) {
            return null;
        }
        return new SimpleCacheSpan(keyForId, Long.parseLong(matcher.group(2)), length, Long.parseLong(matcher.group(3)), file2);
    }

    public static SimpleCacheSpan createClosedHole(String str, long j9, long j10) {
        return new SimpleCacheSpan(str, j9, j10, C3322C.TIME_UNSET, null);
    }

    public static SimpleCacheSpan createLookup(String str, long j9) {
        return new SimpleCacheSpan(str, j9, -1L, C3322C.TIME_UNSET, null);
    }

    public static SimpleCacheSpan createOpenHole(String str, long j9) {
        return new SimpleCacheSpan(str, j9, -1L, C3322C.TIME_UNSET, null);
    }

    public static File getCacheFile(File file, int i9, long j9, long j10) {
        return new File(file, i9 + "." + j9 + "." + j10 + SUFFIX);
    }

    private static File upgradeFile(File file, CachedContentIndex cachedContentIndex) {
        String strGroup;
        String name = file.getName();
        Matcher matcher = CACHE_FILE_PATTERN_V2.matcher(name);
        if (matcher.matches()) {
            strGroup = Util.unescapeFileName(matcher.group(1));
            if (strGroup == null) {
                return null;
            }
        } else {
            matcher = CACHE_FILE_PATTERN_V1.matcher(name);
            if (!matcher.matches()) {
                return null;
            }
            strGroup = matcher.group(1);
        }
        File cacheFile = getCacheFile(file.getParentFile(), cachedContentIndex.assignIdForKey(strGroup), Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(3)));
        if (file.renameTo(cacheFile)) {
            return cacheFile;
        }
        return null;
    }

    public SimpleCacheSpan copyWithUpdatedLastAccessTime(int i9) {
        Assertions.checkState(this.isCached);
        long jCurrentTimeMillis = System.currentTimeMillis();
        return new SimpleCacheSpan(this.key, this.position, this.length, jCurrentTimeMillis, getCacheFile(this.file.getParentFile(), i9, this.position, jCurrentTimeMillis));
    }
}
