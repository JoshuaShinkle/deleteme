package com.cyberlink.you.activity.selectfromfriendgroup;

import android.util.Pair;
import com.cyberlink.you.friends.Friend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.d */
/* loaded from: classes.dex */
public class C2455d {

    /* renamed from: a */
    public Map<Friend, Boolean> f11228a = new HashMap();

    /* renamed from: b */
    public List<a> f11229b = new ArrayList();

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.d$a */
    public interface a {
        /* renamed from: a */
        void mo12617a(List<Pair<Friend, Boolean>> list);
    }

    /* renamed from: a */
    public Map<Friend, Boolean> m12611a() {
        return this.f11228a;
    }

    /* renamed from: b */
    public final void m12612b(List<Pair<Friend, Boolean>> list) {
        Iterator<a> it = this.f11229b.iterator();
        while (it.hasNext()) {
            it.next().mo12617a(list);
        }
    }

    /* renamed from: c */
    public void m12613c(a aVar) {
        this.f11229b.add(aVar);
    }

    /* renamed from: d */
    public void m12614d() {
        this.f11229b.clear();
    }

    /* renamed from: e */
    public void m12615e(List<Pair<Friend, Boolean>> list) {
        for (Pair<Friend, Boolean> pair : list) {
            this.f11228a.put((Friend) pair.first, (Boolean) pair.second);
        }
        m12612b(list);
    }

    /* renamed from: f */
    public void m12616f(boolean z8, Friend friend) {
        this.f11228a.put(friend, Boolean.valueOf(z8));
        m12612b(Collections.singletonList(new Pair(friend, Boolean.valueOf(z8))));
    }
}
