package com.cyberlink.you.feedback;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.cyberlink.you.feedback.NetworkFeedback;
import java.util.ArrayList;

/* renamed from: com.cyberlink.you.feedback.c */
/* loaded from: classes.dex */
public class C3030c {
    /* renamed from: a */
    public static void m15496a(Activity activity, NetworkFeedback.FeedbackConfig feedbackConfig, String str, String str2, ArrayList<Uri> arrayList, String str3) {
        Intent intent = new Intent();
        intent.setClass(activity, PreviewFeedbackActivity.class);
        intent.putExtra("FeedbackConfig", feedbackConfig);
        intent.putExtra("FeedbackDesc", str);
        intent.putExtra("FeedbackEmail", str2);
        if (arrayList != null) {
            intent.putExtra("FeedbackImage", Model.toJSONArray(arrayList).toString());
        }
        intent.putExtra("FeedbackProjectFile", str3);
        activity.startActivityForResult(intent, 48160);
    }
}
