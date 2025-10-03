package com.cyberlink.you.activity.friend;

import com.cyberlink.p030U.R;

/* loaded from: classes.dex */
public enum FRIEND_ADD_TYPE {
    DEPARTMENT(1, R.string.organization, R.layout.view_item_department_list_item),
    ORG_CONTACTS(2, R.string.org_contacts, R.layout.view_item_department_list_item),
    ORG_CONTACTS_SEARCH(3, R.string.org_contacts, R.layout.view_item_department_member_list_item),
    SUGGESTION(4, R.string.friends_invitation_string_suggestion, R.layout.view_item_suggestion_list_item),
    SUGGESTION_ON_U(5, R.string.friends_invitation_string_suggestion_on_U, R.layout.view_item_suggestion_list_item);

    private int key;
    private int resListItemView;
    private int resTitle;

    FRIEND_ADD_TYPE(int i9, int i10, int i11) {
        this.key = i9;
        this.resTitle = i10;
        this.resListItemView = i11;
    }

    /* renamed from: a */
    public static FRIEND_ADD_TYPE m12201a(int i9) {
        for (FRIEND_ADD_TYPE friend_add_type : values()) {
            if (friend_add_type.key == i9) {
                return friend_add_type;
            }
        }
        return null;
    }

    /* renamed from: b */
    public int m12202b() {
        return this.key;
    }

    /* renamed from: c */
    public int m12203c() {
        return this.resListItemView;
    }

    /* renamed from: d */
    public int m12204d() {
        return this.resTitle;
    }
}
