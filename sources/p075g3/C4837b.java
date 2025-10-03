package p075g3;

import android.text.TextUtils;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.ChatListGroup;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: g3.b */
/* loaded from: classes.dex */
public class C4837b {

    /* renamed from: a */
    public Map<Long, List<Friend>> f16834a = new HashMap();

    /* renamed from: b */
    public Comparator<Object> m19173b(final String str) {
        return new Comparator() { // from class: g3.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f16822b.m19172g(str, obj, obj2);
            }
        };
    }

    /* renamed from: c */
    public final List<Friend> m19174c(long j9) {
        if (this.f16834a.get(Long.valueOf(j9)) == null) {
            this.f16834a.put(Long.valueOf(j9), C2950b0.m14899A().m15029u(Long.valueOf(j9)));
        }
        return this.f16834a.get(Long.valueOf(j9));
    }

    /* renamed from: d */
    public List<Object> m19175d(String str, List<Object> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i9 = 0; i9 < 8; i9++) {
            arrayList2.add(new ArrayList());
        }
        for (Object obj : list) {
            ChatListGroup chatListGroup = (ChatListGroup) obj;
            String lowerCase = chatListGroup.f13717d.toLowerCase();
            if (!chatListGroup.m15750g()) {
                boolean zM19177f = m19177f(m19174c(chatListGroup.f13727n), str);
                if ("ChatRoom".equals(chatListGroup.f13716c)) {
                    ((List) arrayList2.get(zM19177f ? 6 : 7)).add(obj);
                } else if (lowerCase.startsWith(str)) {
                    ((List) arrayList2.get(2)).add(obj);
                } else if (lowerCase.contains(str)) {
                    ((List) arrayList2.get(3)).add(obj);
                } else {
                    ((List) arrayList2.get(zM19177f ? 4 : 5)).add(obj);
                }
            } else if (lowerCase.startsWith(str)) {
                ((List) arrayList2.get(0)).add(obj);
            } else if (lowerCase.contains(str)) {
                ((List) arrayList2.get(1)).add(obj);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.addAll((List) it.next());
        }
        return arrayList;
    }

    /* renamed from: e */
    public List<Object> m19176e(String str, List<Object> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i9 = 0; i9 < 4; i9++) {
            arrayList2.add(new ArrayList());
        }
        for (Object obj : list) {
            Group group = (Group) obj;
            String lowerCase = group.f13717d.toLowerCase();
            if (lowerCase.startsWith(str)) {
                ((List) arrayList2.get(0)).add(obj);
            } else if (lowerCase.contains(str)) {
                ((List) arrayList2.get(1)).add(obj);
            } else {
                ((List) arrayList2.get(m19177f(m19174c(group.f13727n), str) ? 2 : 3)).add(obj);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.addAll((List) it.next());
        }
        return arrayList;
    }

    /* renamed from: f */
    public final boolean m19177f(List<Friend> list, String str) {
        for (Friend friend : list) {
            if (friend.m15622c().toLowerCase().startsWith(str) || friend.m15620a().toLowerCase().startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: h */
    public void m19178h() {
        this.f16834a.clear();
    }

    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final int m19172g(Friend friend, Friend friend2, String str) {
        String lowerCase = !TextUtils.isEmpty(friend.m15622c()) ? friend.m15622c().toLowerCase() : "";
        String lowerCase2 = !TextUtils.isEmpty(friend2.m15622c()) ? friend2.m15622c().toLowerCase() : "";
        String lowerCase3 = !TextUtils.isEmpty(friend.m15620a()) ? friend.m15620a().toLowerCase() : "";
        String lowerCase4 = TextUtils.isEmpty(friend2.m15620a()) ? "" : friend2.m15620a().toLowerCase();
        if (!TextUtils.isEmpty(lowerCase) && !TextUtils.isEmpty(lowerCase2)) {
            boolean zStartsWith = lowerCase.startsWith(str);
            boolean zStartsWith2 = lowerCase2.startsWith(str);
            boolean zContains = lowerCase.contains(str);
            boolean zContains2 = lowerCase2.contains(str);
            if (zStartsWith && zStartsWith2) {
                return lowerCase.compareTo(lowerCase2);
            }
            if (zStartsWith) {
                return -1;
            }
            if (zStartsWith2) {
                return 1;
            }
            if (zContains && zContains2) {
                return lowerCase.compareTo(lowerCase2);
            }
            if (zContains) {
                return -1;
            }
            if (zContains2) {
                return 1;
            }
        } else {
            if (!TextUtils.isEmpty(lowerCase) && lowerCase.contains(str)) {
                return -1;
            }
            if (!TextUtils.isEmpty(lowerCase2) && lowerCase2.contains(str)) {
                return 1;
            }
        }
        boolean zStartsWith3 = lowerCase3.startsWith(str);
        boolean zStartsWith4 = lowerCase4.startsWith(str);
        if (zStartsWith3 && zStartsWith4) {
            return lowerCase3.compareTo(lowerCase4);
        }
        if (zStartsWith3) {
            return -1;
        }
        if (zStartsWith4) {
            return 1;
        }
        return lowerCase3.compareTo(lowerCase4);
    }
}
