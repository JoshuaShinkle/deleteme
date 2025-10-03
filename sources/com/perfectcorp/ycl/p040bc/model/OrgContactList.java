package com.perfectcorp.ycl.p040bc.model;

import com.perfectcorp.model.Model;
import java.util.List;

/* loaded from: classes2.dex */
public class OrgContactList extends Model {
    public OrgContacts contacts;
    public OrgContacts directories;

    public class OrgContacts {
        public List<ContactInfo> results;
        public int totalSize;

        public class ContactInfo {
            public String avatar;
            public String created;
            public String directoryId;
            public String directoryKey;
            public String displayName;
            public String email;
            public Boolean hidden;
            public Long hiddenContacts;

            /* renamed from: id */
            public String f15986id;
            public String jobTitle;
            public String lastModified;
            public String name;
            public String parentDirectoryId;
            public Long totalContacts;
            public Long uid;

            public ContactInfo() {
            }
        }

        public OrgContacts() {
        }
    }
}
