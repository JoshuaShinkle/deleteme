package p015b4;

import android.app.Activity;
import android.view.View;
import com.cyberlink.you.friends.FriendsClient;

/* renamed from: b4.e */
/* loaded from: classes.dex */
public abstract class AbstractC0675e {

    /* renamed from: a */
    public final int f3279a;

    /* renamed from: b */
    public final InterfaceC0673c f3280b;

    /* renamed from: c */
    public final FriendsClient f3281c;

    /* renamed from: d */
    public final Activity f3282d;

    /* renamed from: e */
    public final View.OnClickListener f3283e = new View.OnClickListener() { // from class: b4.d
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3278b.mo3388b(view);
        }
    };

    public AbstractC0675e(FriendsClient friendsClient, Activity activity, InterfaceC0673c interfaceC0673c, int i9) {
        this.f3280b = interfaceC0673c;
        this.f3281c = friendsClient;
        this.f3282d = activity;
        this.f3279a = i9;
    }

    /* renamed from: a */
    public void m3387a(View view) {
        view.setOnClickListener(this.f3283e);
    }

    /* renamed from: b */
    public abstract void mo3388b(View view);
}
