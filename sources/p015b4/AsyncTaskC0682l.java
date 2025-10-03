package p015b4;

import android.content.Context;
import android.os.AsyncTask;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;

/* renamed from: b4.l */
/* loaded from: classes.dex */
public class AsyncTaskC0682l extends AsyncTask<String, String, String> {

    /* renamed from: a */
    public FriendsClient f3292a;

    /* renamed from: b */
    public Context f3293b;

    /* renamed from: c */
    public a f3294c;

    /* renamed from: b4.l$a */
    public interface a {
        void onComplete();
    }

    public AsyncTaskC0682l(FriendsClient friendsClient, Context context, a aVar) {
        this.f3292a = friendsClient;
        this.f3293b = context;
        this.f3294c = aVar;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        String str = strArr[0];
        UserInfo userInfoM15724c0 = this.f3292a.m15724c0();
        if (userInfoM15724c0 == null) {
            return null;
        }
        CLUtility.m16527e(this.f3293b, userInfoM15724c0);
        Globals.m7388i0().m7565j4(userInfoM15724c0.f13777b);
        Globals.m7388i0().m7570k3(userInfoM15724c0.f13782g);
        Globals.m7388i0().m7431G3(userInfoM15724c0.f13792q);
        XMPPManager.m14184g0().m14212L(userInfoM15724c0.f13782g, str, false, null);
        return null;
    }

    @Override // android.os.AsyncTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        this.f3294c.onComplete();
    }
}
