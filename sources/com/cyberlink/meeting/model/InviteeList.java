package com.cyberlink.meeting.model;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.cyberlink.you.feedback.Model;
import java.util.ArrayList;

@Keep
/* loaded from: classes.dex */
public class InviteeList extends Model {
    public ArrayList<Invitee> results;
    public int totalSize;

    @Keep
    public class Invitee implements Comparable<Invitee> {
        public String avatar;
        public String displayName;
        public String email;
        public String uid;
        public String userId;

        public Invitee() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Invitee invitee) {
            return (!TextUtils.isEmpty(this.displayName) ? this.displayName : this.email).compareTo(!TextUtils.isEmpty(invitee.displayName) ? invitee.displayName : invitee.email);
        }
    }
}
