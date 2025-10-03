package p024c3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.poll.PollVoter;
import com.cyberlink.you.activity.poll.PollVotersListActivity;
import com.cyberlink.you.friends.FriendsClient;
import java.util.List;
import p116k4.C5179r0;
import p173q2.C6127a;

/* renamed from: c3.d */
/* loaded from: classes.dex */
public class C0722d extends RecyclerView.AbstractC0446g<RecyclerView.AbstractC0442c0> {

    /* renamed from: a */
    public PollVotersListActivity f3402a;

    /* renamed from: b */
    public List<PollVoter> f3403b;

    /* renamed from: c */
    public FriendsClient f3404c;

    /* renamed from: c3.d$a */
    public class a extends RecyclerView.AbstractC0442c0 {

        /* renamed from: b */
        public TextView f3405b;

        /* renamed from: c */
        public ImageView f3406c;

        public a(View view) {
            super(view);
            m3554a(view);
        }

        /* renamed from: a */
        public final void m3554a(View view) {
            this.f3406c = (ImageView) view.findViewById(R.id.PollVoterAvatar);
            this.f3405b = (TextView) view.findViewById(R.id.PollVoterDisplayName);
        }

        /* renamed from: b */
        public void m3555b(PollVoter pollVoter) {
            if (pollVoter != null) {
                C6127a.m23474o(C0722d.this.f3402a, this.f3406c, pollVoter.f11057d, R.drawable.pic_default);
                this.f3405b.setText(pollVoter.f11056c);
            }
        }
    }

    public C0722d(PollVotersListActivity pollVotersListActivity, FriendsClient friendsClient, List<PollVoter> list) {
        this.f3402a = pollVotersListActivity;
        this.f3404c = friendsClient;
        this.f3403b = list;
    }

    /* renamed from: f */
    public void m3553f(List<PollVoter> list) {
        this.f3403b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public int getItemCount() {
        return this.f3403b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public void onBindViewHolder(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9) {
        this.f3403b.get(i9);
        abstractC0442c0.itemView.setOnClickListener(new View.OnClickListener() { // from class: c3.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C5179r0.m20246a();
            }
        });
        ((a) abstractC0442c0).m3555b(this.f3403b.get(i9));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public RecyclerView.AbstractC0442c0 onCreateViewHolder(ViewGroup viewGroup, int i9) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_poll_voters_list_item, viewGroup, false));
    }
}
