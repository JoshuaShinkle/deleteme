package com.perfectcorp.ycl.p040bc.model;

import androidx.annotation.Keep;
import com.perfectcorp.model.Model;
import java.util.ArrayList;

@Keep
/* loaded from: classes2.dex */
public class WatchHistoryList extends Model {
    public ArrayList<LiveHistoryInfo> results;
    public int totalSize;

    public class LiveHistoryInfo {
        public Long length;
        public String liveId;
        public String publishDate;
        public String shareAddr;
        public String state;
        public String title;

        public LiveHistoryInfo() {
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof LiveHistoryInfo) && this.liveId.equals(((LiveHistoryInfo) obj).liveId);
        }

        public int hashCode() {
            return this.liveId.hashCode();
        }
    }
}
