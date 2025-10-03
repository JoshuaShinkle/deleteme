package com.cyberlink.you.friends;

import java.text.Collator;
import java.util.Comparator;

/* loaded from: classes.dex */
public class FriendGroup extends Friend {

    /* renamed from: B */
    public int f13670B;

    /* renamed from: com.cyberlink.you.friends.FriendGroup$a */
    public static class C3042a implements Comparator<Friend> {

        /* renamed from: b */
        public final Collator f13671b = Collator.getInstance();

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Friend friend, Friend friend2) {
            boolean z8 = friend instanceof FriendGroup;
            if (!z8 || !(friend2 instanceof FriendGroup)) {
                if (z8) {
                    return -1;
                }
                if (friend2 instanceof FriendGroup) {
                    return 1;
                }
            }
            return this.f13671b.compare(friend.m15621b(), friend2.m15621b());
        }
    }

    public FriendGroup(String str, int i9) {
        super.m15624e(str);
        this.f13670B = i9;
    }
}
