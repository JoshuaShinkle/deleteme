package com.perfectcorp.ycl.p040bc.model;

import com.perfectcorp.model.Model;
import java.util.List;

/* loaded from: classes2.dex */
public class DepartmentMemberList extends Model {
    public List<UserInfo> results;
    public int totalSize;

    public class UserInfo {
        public String avatar;
        public String displayName;
        public String uId;

        public UserInfo() {
        }
    }
}
