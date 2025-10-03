package com.cyberlink.meeting.model;

import androidx.annotation.Keep;
import com.cyberlink.you.feedback.Model;
import java.util.List;

@Keep
/* loaded from: classes.dex */
public class SubscriptionInfo extends Model {
    public List<String> androidOnHoldProductIds;
    public String attendeeCapacity;
    public String maximumLength;
    public String plan;
    public String userId;
}
