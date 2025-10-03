package com.cyberlink.you.friends;

import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5164m0;
import p116k4.C5172p;
import p136m3.C5321e;
import p201t3.C6301o;

/* renamed from: com.cyberlink.you.friends.b */
/* loaded from: classes.dex */
public class C3062b {

    /* renamed from: com.cyberlink.you.friends.b$a */
    public class a extends Thread {

        /* renamed from: b */
        public final /* synthetic */ b f13821b;

        public a(b bVar) {
            this.f13821b = bVar;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Iterator<String> it;
            JSONObject jSONObjectM20195q;
            FriendsClient friendsClient = new FriendsClient();
            String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            Pair<String, String> pairM15731j = friendsClient.m15731j("user", "userInfoV2", arrayList);
            String str = (String) pairM15731j.first;
            String str2 = (String) pairM15731j.second;
            if ("200".equals(str) && (jSONObjectM20195q = C5172p.m20195q(str2)) != null && jSONObjectM20195q.has("organizationIds")) {
                try {
                    Globals.m7388i0().m7448K3(jSONObjectM20195q.getString("organizationIds"));
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            List<String> listM7445K0 = Globals.m7388i0().m7445K0();
            if (listM7445K0.isEmpty()) {
                return;
            }
            FriendsClient friendsClient2 = new FriendsClient();
            Iterator<String> it2 = listM7445K0.iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new C6301o("token", strM7449L));
                arrayList2.add(new C6301o("organizationId", next));
                arrayList2.add(new C6301o("pageSize", String.valueOf(100)));
                Pair<String, String> pairM15731j2 = friendsClient2.m15731j("organization", "listOrgMember", arrayList2);
                String str3 = (String) pairM15731j2.first;
                String str4 = (String) pairM15731j2.second;
                if ("200".equals(str3)) {
                    int iM16553k1 = CLUtility.m16553k1(str4);
                    int iM16494U0 = CLUtility.m16494U0(str4);
                    int iM16559m = CLUtility.m16559m(iM16553k1, 100);
                    if (iM16553k1 == -1 || iM16494U0 == -1) {
                        it = it2;
                        b bVar = this.f13821b;
                        if (bVar != null) {
                            bVar.mo9343a();
                        }
                    } else if (iM16553k1 != iM16494U0) {
                        C3062b.m15819s(str4, next);
                        int i9 = 2;
                        while (i9 <= iM16559m) {
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(new C6301o("token", strM7449L));
                            arrayList3.add(new C6301o("organizationId", next));
                            Iterator<String> it3 = it2;
                            arrayList3.add(new C6301o("pageIndex", String.valueOf(i9)));
                            arrayList3.add(new C6301o("pageSize", String.valueOf(100)));
                            Pair<String, String> pairM15731j3 = friendsClient2.m15731j("organization", "listOrgMember", arrayList3);
                            String str5 = (String) pairM15731j3.first;
                            String str6 = (String) pairM15731j3.second;
                            if (str5 == null || !str5.equals("200")) {
                                b bVar2 = this.f13821b;
                                if (bVar2 != null) {
                                    bVar2.mo9343a();
                                }
                            } else if (C3062b.m15819s(str6, next)) {
                                b bVar3 = this.f13821b;
                                if (bVar3 != null) {
                                    bVar3.mo9344b(iM16559m, i9);
                                }
                            } else {
                                b bVar4 = this.f13821b;
                                if (bVar4 != null) {
                                    bVar4.mo9343a();
                                }
                            }
                            i9++;
                            it2 = it3;
                        }
                        it = it2;
                    } else {
                        it = it2;
                        if (C3062b.m15819s(str4, next)) {
                            b bVar5 = this.f13821b;
                            if (bVar5 != null) {
                                bVar5.mo9344b(iM16559m, 1);
                            }
                        } else {
                            b bVar6 = this.f13821b;
                            if (bVar6 != null) {
                                bVar6.mo9343a();
                            }
                        }
                    }
                } else {
                    it = it2;
                    Log.d("syncOrganization", "Response is null");
                    b bVar7 = this.f13821b;
                    if (bVar7 != null) {
                        bVar7.mo9343a();
                    }
                }
                it2 = it;
            }
            friendsClient2.m15717U0();
        }
    }

    /* renamed from: com.cyberlink.you.friends.b$b */
    public interface b {
        /* renamed from: a */
        void mo9343a();

        /* renamed from: b */
        void mo9344b(int i9, int i10);
    }

    /* renamed from: h */
    public static void m15808h(List<C2973l0> list, String str, List<Boolean> list2, int i9) {
        if (list2.size() == i9) {
            Iterator<Boolean> it = list2.iterator();
            while (it.hasNext()) {
                if (!it.next().booleanValue()) {
                    return;
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator<C2973l0> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(Long.valueOf(it2.next().m15144p()));
            }
            C2950b0.m14914m().m14718o(str, arrayList);
            C2950b0.m14914m().m14713j(list);
        }
    }

    /* renamed from: i */
    public static /* synthetic */ void m15809i(FriendsClient friendsClient, String str, String str2, String str3, String str4) {
        List<StickerPackObj> listM15294l;
        friendsClient.m15717U0();
        if (str3 == null) {
            Log.d("parseAndStoreUserPack", "Response is null");
            return;
        }
        if (!str3.equals("200")) {
            Log.d("parseAndStoreUserPack", "statusCode=" + str3);
            return;
        }
        if (Globals.m7388i0().m7525b2()) {
            listM15294l = null;
        } else {
            Log.d("parseAndStoreUserPack", "[updateSticker] in ULauncherActivity, call getSortedAllStickerPackObj4UI");
            listM15294l = C2950b0.m14925x().m15294l();
        }
        C2950b0.m14925x().m15299q();
        List<StickerPackObj> listM20177D = C5172p.m20177D(C5172p.m20196r(str4), false, true);
        if (listM20177D != null) {
            for (StickerPackObj stickerPackObj : listM20177D) {
                if (C5164m0.f17692e.contains(Long.valueOf(stickerPackObj.m14803g()))) {
                    stickerPackObj.m14819w(StickerPackObj.Status.BUILTIN);
                } else {
                    stickerPackObj.m14819w(StickerPackObj.Status.NOT_DOWNLOADED);
                }
                if (listM15294l != null) {
                    Iterator<StickerPackObj> it = listM15294l.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (it.next().m14803g() == stickerPackObj.m14803g() && stickerPackObj.m14811o() == StickerPackObj.Status.NOT_DOWNLOADED) {
                            stickerPackObj.m14819w(StickerPackObj.Status.DOWNLOADED);
                            break;
                        }
                    }
                }
                if (!Globals.m7388i0().m7516Z1()) {
                    LoadImageUtils.m16641z(Globals.m7372O(), stickerPackObj, null, true, false);
                }
            }
            if (!Globals.m7388i0().m7516Z1()) {
                Globals.m7388i0().m7536d4(true);
            }
            if (Globals.m7388i0().m7525b2()) {
                C2950b0.m14925x().m15290h(listM20177D, true, true);
            } else {
                Globals.m7388i0().m7553h3(true);
                C2950b0.m14925x().m15290h(listM20177D, true, false);
            }
        }
    }

    /* renamed from: j */
    public static /* synthetic */ void m15810j(b bVar, int i9, int i10, AtomicInteger atomicInteger, FriendsClient friendsClient, String str, String str2, String str3, String str4) {
        if (str3 == null) {
            Log.d("syncFriend", "Response is null");
            if (bVar != null) {
                bVar.mo9343a();
            }
        } else if (!"200".equals(str3)) {
            Log.d("syncFriend", "statusCode=" + str3);
            if (bVar != null) {
                bVar.mo9343a();
            }
        } else if (m15817q(str4)) {
            if (bVar != null) {
                bVar.mo9344b(i9, i10);
            }
        } else if (bVar != null) {
            bVar.mo9343a();
        }
        if (atomicInteger.incrementAndGet() == i9) {
            friendsClient.m15717U0();
        }
    }

    /* renamed from: k */
    public static /* synthetic */ void m15811k(FriendsClient friendsClient, final b bVar, String str, String str2, String str3, String str4, String str5) {
        friendsClient.m15717U0();
        if (str4 == null) {
            Log.d("syncFriend", "Response is null");
            if (bVar != null) {
                bVar.mo9343a();
                return;
            }
            return;
        }
        if (!str4.equals("200")) {
            Log.d("syncFriend", "statusCode=" + str4);
            if (bVar != null) {
                bVar.mo9343a();
                return;
            }
            return;
        }
        int iM16553k1 = CLUtility.m16553k1(str5);
        int iM16494U0 = CLUtility.m16494U0(str5);
        final int iM16559m = CLUtility.m16559m(iM16553k1, 100);
        if (iM16553k1 == -1 || iM16494U0 == -1) {
            return;
        }
        if (iM16553k1 == iM16494U0) {
            if (m15817q(str5)) {
                if (bVar != null) {
                    bVar.mo9344b(iM16559m, 1);
                    return;
                }
                return;
            } else {
                if (bVar != null) {
                    bVar.mo9343a();
                    return;
                }
                return;
            }
        }
        if (m15817q(str5)) {
            if (bVar != null) {
                bVar.mo9344b(iM16559m, 1);
            }
        } else if (bVar != null) {
            bVar.mo9343a();
        }
        final FriendsClient friendsClient2 = new FriendsClient();
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        for (int i9 = 2; i9 <= iM16559m; i9++) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", str));
            arrayList.add(new C6301o("pageIndex", String.valueOf(i9)));
            arrayList.add(new C6301o("pageSize", String.valueOf(100)));
            final int i10 = i9;
            friendsClient2.m15734m("friend", "listFriends", arrayList, new FriendsClient.InterfaceC3051i() { // from class: t3.u
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str6, String str7, String str8, String str9) {
                    C3062b.m15810j(bVar, iM16559m, i10, atomicInteger, friendsClient2, str6, str7, str8, str9);
                }
            });
        }
    }

    /* renamed from: l */
    public static /* synthetic */ void m15812l(b bVar, List list, int i9, int i10, AtomicInteger atomicInteger, FriendsClient friendsClient, String str, String str2, String str3, String str4) {
        if (str3 == null) {
            Log.d("syncGroup", "Response is null");
            if (bVar != null) {
                bVar.mo9343a();
            }
        } else if (!str3.equals("200")) {
            Log.d("syncGroup", "statusCode=" + str3);
            if (bVar != null) {
                bVar.mo9343a();
            }
        } else if (m15818r(str4, list)) {
            if (bVar != null) {
                bVar.mo9344b(i9, i10);
            }
        } else if (bVar != null) {
            bVar.mo9343a();
        }
        if (atomicInteger.incrementAndGet() == i9) {
            friendsClient.m15717U0();
            m15821u(list);
        }
    }

    /* renamed from: m */
    public static /* synthetic */ void m15813m(FriendsClient friendsClient, final b bVar, String str, String str2, String str3, String str4, String str5) {
        friendsClient.m15717U0();
        if (str4 == null) {
            Log.d("syncGroup", "Response is null");
            if (bVar != null) {
                bVar.mo9343a();
                return;
            }
            return;
        }
        if (!str4.equals("200")) {
            Log.d("syncGroup", "statusCode=" + str4);
            if (bVar != null) {
                bVar.mo9343a();
                return;
            }
            return;
        }
        int iM16553k1 = CLUtility.m16553k1(str5);
        int iM16494U0 = CLUtility.m16494U0(str5);
        int i9 = 100;
        final int iM16559m = CLUtility.m16559m(iM16553k1, 100);
        if (iM16553k1 == -1 || iM16494U0 == -1) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        if (iM16553k1 == iM16494U0) {
            if (m15818r(str5, arrayList)) {
                if (bVar != null) {
                    bVar.mo9344b(iM16559m, 1);
                }
            } else if (bVar != null) {
                bVar.mo9343a();
            }
            m15821u(arrayList);
            return;
        }
        if (m15818r(str5, arrayList)) {
            if (bVar != null) {
                bVar.mo9344b(iM16559m, 1);
            }
        } else if (bVar != null) {
            bVar.mo9343a();
        }
        if (iM16559m == 1) {
            return;
        }
        final FriendsClient friendsClient2 = new FriendsClient(1);
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        int i10 = 2;
        while (i10 <= iM16559m) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new C6301o("token", str));
            arrayList2.add(new C6301o("pageIndex", String.valueOf(i10)));
            arrayList2.add(new C6301o("pageSize", String.valueOf(i9)));
            final int i11 = i10;
            friendsClient2.m15734m("group", "list", arrayList2, new FriendsClient.InterfaceC3051i() { // from class: t3.x
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str6, String str7, String str8, String str9) {
                    C3062b.m15812l(bVar, arrayList, iM16559m, i11, atomicInteger, friendsClient2, str6, str7, str8, str9);
                }
            });
            i10++;
            i9 = 100;
        }
    }

    /* renamed from: n */
    public static /* synthetic */ void m15814n(b bVar, List list, List list2, String str, int i9, int i10, String str2, String str3, String str4, String str5) {
        boolean zAddAll;
        if (str4 == null) {
            Log.d("syncMedia", "Response is null");
            if (bVar != null) {
                list.add(Boolean.FALSE);
                bVar.mo9343a();
                return;
            }
            return;
        }
        if (!str4.equals("200")) {
            Log.d("syncMedia", "statusCode=" + str4);
            if (bVar != null) {
                list.add(Boolean.FALSE);
                bVar.mo9343a();
                return;
            }
            return;
        }
        synchronized (list2) {
            zAddAll = list2.addAll(CLUtility.m16501W1(str, str5));
        }
        if (!zAddAll) {
            if (bVar != null) {
                list.add(Boolean.FALSE);
                bVar.mo9343a();
                return;
            }
            return;
        }
        if (bVar != null) {
            list.add(Boolean.TRUE);
            m15808h(list2, str, list, i9);
            bVar.mo9344b(i9, i10);
        }
    }

    /* renamed from: o */
    public static /* synthetic */ void m15815o(FriendsClient friendsClient, final b bVar, final String str, String str2, String str3, String str4, String str5, String str6) {
        friendsClient.m15717U0();
        if (str5 == null) {
            Log.d("syncMedia", "[media.listMedia] Response is null");
            if (bVar != null) {
                bVar.mo9343a();
                return;
            }
            return;
        }
        if (!str5.equals("200")) {
            Log.d("syncMedia", "[media.listMedia] statusCode=" + str5);
            if (bVar != null) {
                bVar.mo9343a();
                return;
            }
            return;
        }
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        int iMin = Math.min(CLUtility.m16553k1(str6), 10000);
        int iM16494U0 = CLUtility.m16494U0(str6);
        int i9 = 100;
        final int iM16559m = CLUtility.m16559m(iMin, 100);
        if (iMin == -1 || iM16494U0 == -1) {
            return;
        }
        if (iMin == iM16494U0) {
            if (arrayList.addAll(CLUtility.m16501W1(str, str6))) {
                if (bVar != null) {
                    arrayList2.add(Boolean.TRUE);
                    m15808h(arrayList, str, arrayList2, iM16559m);
                    bVar.mo9344b(iM16559m, 1);
                    return;
                }
                return;
            }
            if (iMin == 0) {
                m15808h(arrayList, str, arrayList2, iM16559m);
            }
            if (bVar != null) {
                arrayList2.add(Boolean.FALSE);
                bVar.mo9343a();
                return;
            }
            return;
        }
        if (arrayList.addAll(CLUtility.m16501W1(str, str6))) {
            if (bVar != null) {
                arrayList2.add(Boolean.TRUE);
                m15808h(arrayList, str, arrayList2, iM16559m);
                bVar.mo9344b(iM16559m, 1);
            }
        } else if (bVar != null) {
            arrayList2.add(Boolean.FALSE);
            bVar.mo9343a();
        }
        FriendsClient friendsClient2 = new FriendsClient();
        int i10 = 2;
        while (i10 <= iM16559m) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new C6301o("token", str2));
            arrayList3.add(new C6301o("albumId", str));
            arrayList3.add(new C6301o("pageIndex", String.valueOf(i10)));
            arrayList3.add(new C6301o("pageSize", String.valueOf(i9)));
            final int i11 = i10;
            friendsClient2.m15734m("media", "listMedia", arrayList3, new FriendsClient.InterfaceC3051i() { // from class: t3.w
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str7, String str8, String str9, String str10) {
                    C3062b.m15814n(bVar, arrayList2, arrayList, str, iM16559m, i11, str7, str8, str9, str10);
                }
            });
            i10++;
            i9 = 100;
        }
    }

    /* renamed from: p */
    public static boolean m15816p(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayM20196r = C5172p.m20196r(str2);
        if (jSONArrayM20196r == null) {
            return false;
        }
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                C3061a c3061aM20183e = C5172p.m20183e(jSONArrayM20196r.getJSONObject(i9), Long.valueOf(str).longValue());
                if (c3061aM20183e != null) {
                    arrayList.add(c3061aM20183e);
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        C2950b0.m14920s().m15220i(arrayList);
        return true;
    }

    /* renamed from: q */
    public static boolean m15817q(String str) {
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        if (jSONArrayM20196r == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                arrayList.add(C5172p.m20184f(jSONArrayM20196r.getJSONObject(i9)));
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        C2950b0.m14899A().m15020l(arrayList, true);
        return true;
    }

    /* renamed from: r */
    public static boolean m15818r(String str, List<Group> list) {
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        if (jSONArrayM20196r == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                Group groupM20186h = C5172p.m20186h(jSONArrayM20196r.getJSONObject(i9));
                if (groupM20186h != null) {
                    Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(groupM20186h.f13727n));
                    if (groupM15077n != null) {
                        groupM20186h.f13714M = groupM15077n.f13714M;
                    }
                    arrayList.add(groupM20186h);
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        list.addAll(arrayList);
        C2950b0.m14912k().m15071h(arrayList);
        return true;
    }

    /* renamed from: s */
    public static boolean m15819s(String str, String str2) {
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        if (jSONArrayM20196r == null) {
            return false;
        }
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                Friend friendM20184f = C5172p.m20184f(jSONArrayM20196r.getJSONObject(i9));
                if (friendM20184f != null) {
                    C2950b0.m14899A().m15016h(friendM20184f);
                    C2950b0.m14918q().m15204k(Long.valueOf(str2), Long.valueOf(friendM20184f.f13645c));
                }
            } catch (NumberFormatException e9) {
                e9.printStackTrace();
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        }
        return true;
    }

    /* renamed from: t */
    public static void m15820t(List<Long> list) {
        final FriendsClient friendsClient = new FriendsClient();
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        Iterator<Long> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C6301o("packId", Long.toString(it.next().longValue())));
        }
        friendsClient.m15734m("sticker", "pack.info", arrayList, new FriendsClient.InterfaceC3051i() { // from class: t3.v
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                C3062b.m15809i(friendsClient, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: u */
    public static void m15821u(List<Group> list) {
        ArrayList arrayList = new ArrayList();
        for (Group group : list) {
            if (group != null) {
                arrayList.add(Long.toString(group.f13727n));
            }
        }
        FriendsClient friendsClient = new FriendsClient();
        for (Group group2 : C2950b0.m14912k().m15076m(arrayList)) {
            List<Friend> listM15714S = friendsClient.m15714S(group2.f13727n, false);
            if (listM15714S != null) {
                if (listM15714S.size() == 0) {
                    ULogUtility.m16684t("removeGroupsIfNeeded", "Delete GID[" + group2.f13727n + "] " + group2.f13717d);
                    C5321e.m20824o().m20893v0(group2, group2.f13727n);
                } else {
                    ULogUtility.m16684t("removeGroupsIfNeeded", "Unexpected GID[" + group2.f13727n + "] " + group2.f13717d);
                }
            }
        }
        friendsClient.m15717U0();
    }

    /* renamed from: v */
    public static void m15822v(final b bVar) {
        final FriendsClient friendsClient = new FriendsClient();
        final String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("pageSize", String.valueOf(100)));
        friendsClient.m15734m("friend", "listFriends", arrayList, new FriendsClient.InterfaceC3051i() { // from class: t3.r
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                C3062b.m15811k(friendsClient, bVar, strM7449L, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: w */
    public static void m15823w(final b bVar) {
        final FriendsClient friendsClient = new FriendsClient();
        final String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("pageSize", String.valueOf(100)));
        friendsClient.m15734m("group", "list", arrayList, new FriendsClient.InterfaceC3051i() { // from class: t3.t
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                C3062b.m15813m(friendsClient, bVar, strM7449L, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: x */
    public static void m15824x(final String str, final b bVar) {
        final String strM7449L = Globals.m7388i0().m7449L();
        final FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", str));
        arrayList.add(new C6301o("pageSize", String.valueOf(100)));
        friendsClient.m15734m("media", "listMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: t3.s
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                C3062b.m15815o(friendsClient, bVar, str, strM7449L, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: y */
    public static void m15825y(b bVar) {
        new a(bVar).start();
    }
}
