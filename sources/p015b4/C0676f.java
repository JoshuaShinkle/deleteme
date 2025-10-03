package p015b4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.RegistrationFaceIdActivity;
import com.cyberlink.you.activity.ResetFaceIdActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: b4.f */
/* loaded from: classes.dex */
public class C0676f extends AbstractC0675e {

    /* renamed from: g */
    public static Bitmap f3284g;

    /* renamed from: f */
    public boolean f3285f;

    /* renamed from: b4.f$a */
    public class a extends PromisedTask.AbstractC4504d<String> {
        public a() {
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            C0676f.this.m3391d();
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(String str) throws JSONException {
            try {
                Globals.m7388i0().m7452L2(new JSONObject(str).getBoolean("hasFace"));
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            C0676f.this.m3391d();
        }
    }

    public C0676f(FriendsClient friendsClient, Activity activity, InterfaceC0673c interfaceC0673c, int i9) {
        super(friendsClient, activity, interfaceC0673c, i9);
        this.f3285f = false;
    }

    /* renamed from: g */
    public static void m3390g(Bitmap bitmap) {
        f3284g = bitmap;
    }

    @Override // p015b4.AbstractC0675e
    /* renamed from: b */
    public void mo3388b(View view) {
        if (this.f3285f) {
            return;
        }
        if (Globals.m7388i0().m7429G1()) {
            this.f3282d.startActivityForResult(new Intent(this.f3282d, (Class<?>) ResetFaceIdActivity.class), this.f3279a);
        } else {
            this.f3282d.startActivityForResult(new Intent(this.f3282d, (Class<?>) RegistrationFaceIdActivity.class), this.f3279a);
        }
    }

    /* renamed from: d */
    public final void m3391d() {
        View viewFindViewById = this.f3282d.findViewById(R.id.FaceIdItem);
        ((ProgressBar) viewFindViewById.findViewById(R.id.EditGotoLoading)).setVisibility(8);
        this.f3285f = false;
        if (Globals.m7388i0().m7429G1()) {
            ((TextView) viewFindViewById.findViewById(R.id.EditGotoTextView)).setText(R.string.done_btn);
            viewFindViewById.findViewById(R.id.EditWarningImageView).setVisibility(8);
            m3387a(viewFindViewById);
        } else {
            ((TextView) viewFindViewById.findViewById(R.id.EditGotoTextView)).setText(R.string.create);
            viewFindViewById.findViewById(R.id.EditWarningImageView).setVisibility(0);
            m3387a(viewFindViewById);
        }
    }

    /* renamed from: e */
    public boolean m3392e() {
        return this.f3285f;
    }

    /* renamed from: f */
    public void m3393f(int i9, int i10, Intent intent) {
    }

    /* renamed from: h */
    public void m3394h(String str) {
        this.f3285f = true;
        ProgressBar progressBar = (ProgressBar) this.f3282d.findViewById(R.id.FaceIdItem).findViewById(R.id.EditGotoLoading);
        progressBar.setVisibility(0);
        progressBar.setIndeterminate(true);
        NetworkLive.userFaceCollection(str).done(new a());
    }
}
