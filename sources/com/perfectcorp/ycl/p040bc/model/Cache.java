package com.perfectcorp.ycl.p040bc.model;

import com.perfectcorp.model.Model;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes2.dex */
public class Cache extends Model {
    public String data;

    /* renamed from: id */
    public String f15979id;
    public Date lastModified;
    public String type;

    public static class CacheList extends Model implements Cacheable {
        public ArrayList<String> idList;
        private String mCacheId;
        public long totalSize;

        @Override // com.perfectcorp.ycl.bc.model.Cache.Cacheable
        public String getCacheId() {
            return this.mCacheId;
        }

        public void setKey(String str) {
            this.mCacheId = CacheList.class.getSimpleName() + "_" + str;
        }

        @Override // com.perfectcorp.ycl.bc.model.Cache.Cacheable
        public Cache toCache() {
            Cache cache = new Cache();
            cache.f15979id = getCacheId();
            cache.lastModified = new Date(System.currentTimeMillis());
            cache.type = CacheList.class.getName();
            cache.data = toString();
            return cache;
        }
    }

    public interface Cacheable {
        String getCacheId();

        Cache toCache();
    }
}
