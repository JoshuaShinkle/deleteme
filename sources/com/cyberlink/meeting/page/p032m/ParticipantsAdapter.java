package com.cyberlink.meeting.page.p032m;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.C0487p;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.meeting.model.C1262a;
import com.cyberlink.meeting.model.InviteeList;
import com.cyberlink.meeting.page.p032m.AvatarDrawableCache;
import com.cyberlink.p030U.R;
import com.rockerhieu.emojicon.EmojiconTextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p003a2.C0011a;
import p003a2.C0012b;
import p173q2.C6127a;

/* loaded from: classes.dex */
public final class ParticipantsAdapter extends BaseAdapter {

    /* renamed from: b */
    public final Context f6988b;

    /* renamed from: c */
    public final AvatarDrawableCache f6989c;

    /* renamed from: h */
    public C0011a f6994h;

    /* renamed from: i */
    public InterfaceC1367c f6995i;

    /* renamed from: f */
    public boolean f6992f = false;

    /* renamed from: g */
    public List<C1262a> f6993g = new ArrayList();

    /* renamed from: j */
    public boolean f6996j = false;

    /* renamed from: k */
    public final List<InviteeList.Invitee> f6997k = new ArrayList();

    /* renamed from: l */
    public boolean f6998l = false;

    /* renamed from: m */
    public boolean f6999m = true;

    /* renamed from: n */
    public List<Integer> f7000n = new ArrayList();

    /* renamed from: o */
    public List<Integer> f7001o = new ArrayList();

    /* renamed from: p */
    public boolean f7002p = false;

    /* renamed from: d */
    public final C0487p<C0012b> f6990d = m7039i();

    /* renamed from: e */
    public C0487p<InviteeList.Invitee> f6991e = m7038h();

    public enum ParticipantType {
        PARTICIPANT_TITLE,
        PARTICIPANT,
        NOT_JOIN_TITLE,
        NOT_JOIN_DATA,
        WAITING_ROOM_TITLE,
        WAITING_ROOM
    }

    /* renamed from: com.cyberlink.meeting.page.m.ParticipantsAdapter$a */
    public class C1365a extends C0487p.b<C0012b> {
        public C1365a() {
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: a */
        public void mo2761a(int i9, int i10) {
            ParticipantsAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: b */
        public void mo2762b(int i9, int i10) {
            if (ParticipantsAdapter.this.f6996j) {
                ParticipantsAdapter.this.m7049x();
            }
            ParticipantsAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: c */
        public void mo2763c(int i9, int i10) {
            if (ParticipantsAdapter.this.f6996j) {
                ParticipantsAdapter.this.m7049x();
            }
            ParticipantsAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: h */
        public void mo2933h(int i9, int i10) {
            ParticipantsAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public boolean mo2930e(C0012b c0012b, C0012b c0012b2) {
            return false;
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public boolean mo2931f(C0012b c0012b, C0012b c0012b2) {
            return c0012b.f66b.f63b == c0012b2.f66b.f63b;
        }

        @Override // androidx.recyclerview.widget.C0487p.b, java.util.Comparator
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public int compare(C0012b c0012b, C0012b c0012b2) {
            return c0012b.compareTo(c0012b2);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.ParticipantsAdapter$b */
    public class C1366b extends C0487p.b<InviteeList.Invitee> {
        public C1366b() {
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: a */
        public void mo2761a(int i9, int i10) {
            ParticipantsAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: b */
        public void mo2762b(int i9, int i10) {
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: c */
        public void mo2763c(int i9, int i10) {
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: h */
        public void mo2933h(int i9, int i10) {
            ParticipantsAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public boolean mo2930e(InviteeList.Invitee invitee, InviteeList.Invitee invitee2) {
            return false;
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public boolean mo2931f(InviteeList.Invitee invitee, InviteeList.Invitee invitee2) {
            String str = invitee.uid;
            return str != null && str.equals(invitee2.uid);
        }

        @Override // androidx.recyclerview.widget.C0487p.b, java.util.Comparator
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public int compare(InviteeList.Invitee invitee, InviteeList.Invitee invitee2) {
            return invitee.compareTo(invitee2);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.ParticipantsAdapter$c */
    public interface InterfaceC1367c {
        /* renamed from: a */
        void mo7004a(int i9);

        /* renamed from: b */
        void mo7005b(C0012b c0012b);

        /* renamed from: c */
        void mo7006c(C0012b c0012b);

        /* renamed from: d */
        void mo7007d();

        /* renamed from: e */
        void mo7008e(int i9);
    }

    /* renamed from: com.cyberlink.meeting.page.m.ParticipantsAdapter$d */
    public static final class C1368d {

        /* renamed from: a */
        public final View f7012a;

        /* renamed from: b */
        public final TextView f7013b;

        /* renamed from: c */
        public final TextView f7014c;

        /* renamed from: d */
        public final View f7015d;

        /* renamed from: e */
        public final View f7016e;

        /* renamed from: f */
        public final ImageView f7017f;

        /* renamed from: g */
        public final TextView f7018g;

        /* renamed from: h */
        public final EmojiconTextView f7019h;

        /* renamed from: i */
        public final ImageView f7020i;

        /* renamed from: j */
        public final ImageView f7021j;

        /* renamed from: k */
        public final ImageView f7022k;

        /* renamed from: l */
        public final ImageView f7023l;

        /* renamed from: m */
        public final ImageView f7024m;

        /* renamed from: n */
        public final View f7025n;

        /* renamed from: o */
        public final ImageView f7026o;

        /* renamed from: p */
        public final EmojiconTextView f7027p;

        /* renamed from: q */
        public final TextView f7028q;

        /* renamed from: r */
        public final ImageView f7029r;

        /* renamed from: s */
        public final ImageView f7030s;

        /* renamed from: t */
        public final ImageView f7031t;

        /* renamed from: u */
        public final View f7032u;

        /* renamed from: v */
        public final ImageView f7033v;

        public C1368d(View view) {
            this.f7012a = view.findViewById(R.id.meetingParticipantTitle);
            this.f7013b = (TextView) view.findViewById(R.id.participant_title);
            this.f7015d = view.findViewById(R.id.participant_title_raise_hand_area);
            this.f7014c = (TextView) view.findViewById(R.id.participant_waiting_room_admit);
            this.f7016e = view.findViewById(R.id.meetingParticipantContent);
            this.f7017f = (ImageView) view.findViewById(R.id.meetingParticipantAvatar);
            this.f7018g = (TextView) view.findViewById(R.id.participantTag);
            this.f7019h = (EmojiconTextView) view.findViewById(R.id.meetingParticipantNickName);
            this.f7020i = (ImageView) view.findViewById(R.id.btnMeetingParticipantCamera);
            this.f7021j = (ImageView) view.findViewById(R.id.btn_meetingParticipantSpeaker);
            this.f7022k = (ImageView) view.findViewById(R.id.btn_meetingParticipantRecoding);
            this.f7023l = (ImageView) view.findViewById(R.id.btn_meetingParticipantPhoneLine);
            this.f7024m = (ImageView) view.findViewById(R.id.btn_meetingParticipantRaiseHand);
            this.f7025n = view.findViewById(R.id.meetingWaitingRoomContent);
            this.f7026o = (ImageView) view.findViewById(R.id.waitingRoomAvatar);
            this.f7027p = (EmojiconTextView) view.findViewById(R.id.waitingRoomNickName);
            this.f7028q = (TextView) view.findViewById(R.id.waitingRoomEmail);
            this.f7029r = (ImageView) view.findViewById(R.id.btnEnterMeeting);
            this.f7030s = (ImageView) view.findViewById(R.id.btnRemoveFromWaitingRoom);
            this.f7031t = (ImageView) view.findViewById(R.id.btnMeetingPin);
            this.f7032u = view.findViewById(R.id.moreActionLayout);
            this.f7033v = (ImageView) view.findViewById(R.id.btnMoreAction);
        }
    }

    public ParticipantsAdapter(Context context, AvatarDrawableCache avatarDrawableCache, InterfaceC1367c interfaceC1367c) {
        this.f6988b = context;
        this.f6989c = avatarDrawableCache;
        this.f6995i = interfaceC1367c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m7026r(View view) {
        InterfaceC1367c interfaceC1367c = this.f6995i;
        if (interfaceC1367c != null) {
            interfaceC1367c.mo7007d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m7027s(int i9, View view) {
        InterfaceC1367c interfaceC1367c = this.f6995i;
        if (interfaceC1367c != null) {
            interfaceC1367c.mo7004a(i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public /* synthetic */ void m7028t(C1262a c1262a, View view) {
        InterfaceC1367c interfaceC1367c = this.f6995i;
        if (interfaceC1367c != null) {
            interfaceC1367c.mo7008e(c1262a.m5730b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public /* synthetic */ void m7029u(C0012b c0012b, View view) {
        InterfaceC1367c interfaceC1367c = this.f6995i;
        if (interfaceC1367c != null) {
            interfaceC1367c.mo7006c(c0012b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m7030v(C0012b c0012b, View view) {
        InterfaceC1367c interfaceC1367c = this.f6995i;
        if (interfaceC1367c != null) {
            interfaceC1367c.mo7005b(c0012b);
        }
    }

    /* renamed from: A */
    public void m7031A(boolean z8) {
        if (this.f6996j != z8) {
            this.f6996j = z8;
            notifyDataSetChanged();
        }
    }

    /* renamed from: B */
    public void m7032B(boolean z8) {
        this.f7002p = z8;
    }

    /* renamed from: C */
    public void m7033C(boolean z8) {
        this.f6998l = z8;
    }

    /* renamed from: D */
    public void m7034D(List<Integer> list) {
        this.f7000n = list;
    }

    /* renamed from: E */
    public void m7035E(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < this.f6990d.m2926u(); i9++) {
            C0012b c0012bM2918m = this.f6990d.m2918m(i9);
            c0012bM2918m.m77G(false);
            c0012bM2918m.m76F(-1);
            int i10 = 0;
            while (true) {
                if (i10 >= list.size()) {
                    break;
                }
                if (list.get(i10).intValue() == c0012bM2918m.f66b.f63b) {
                    c0012bM2918m.m77G(true);
                    c0012bM2918m.m76F(i10);
                    break;
                }
                i10++;
            }
            arrayList.add(c0012bM2918m);
        }
        this.f6990d.m2913h();
        this.f6990d.m2908c(arrayList);
        this.f7001o = list;
    }

    /* renamed from: F */
    public void m7036F(boolean z8) {
        this.f6992f = z8;
    }

    /* renamed from: g */
    public void m7037g(List<InviteeList.Invitee> list) {
        this.f6997k.addAll(list);
        m7049x();
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int iM2926u = this.f6990d.m2926u();
        if (this.f6992f && this.f6998l && this.f6993g.size() > 0) {
            iM2926u = iM2926u + this.f6993g.size() + 1;
        }
        if (this.f6996j) {
            iM2926u = iM2926u + this.f6991e.m2926u() + 1;
        }
        return iM2926u + 1;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i9) {
        int iM7045o = m7045o(i9);
        ParticipantType participantTypeM7041k = m7041k(i9);
        if (participantTypeM7041k == ParticipantType.WAITING_ROOM) {
            return this.f6993g.get(iM7045o);
        }
        if (participantTypeM7041k == ParticipantType.PARTICIPANT) {
            return this.f6990d.m2918m(iM7045o);
        }
        if (participantTypeM7041k == ParticipantType.NOT_JOIN_DATA) {
            return this.f6991e.m2918m(iM7045o);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        int iM5730b;
        Object item = getItem(i9);
        if (item == null) {
            return -1L;
        }
        ParticipantType participantTypeM7041k = m7041k(i9);
        if (participantTypeM7041k == ParticipantType.WAITING_ROOM) {
            iM5730b = ((C1262a) item).m5730b();
        } else {
            if (participantTypeM7041k != ParticipantType.PARTICIPANT) {
                if (participantTypeM7041k != ParticipantType.NOT_JOIN_DATA) {
                    return -1L;
                }
                String str = ((InviteeList.Invitee) item).userId;
                if (TextUtils.isEmpty(str)) {
                    return -1L;
                }
                return Long.valueOf(str).longValue();
            }
            iM5730b = ((C0012b) item).f66b.f63b;
        }
        return iM5730b;
    }

    @Override // android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        C1368d c1368d;
        boolean z8;
        ParticipantType participantTypeM7041k = m7041k(i9);
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(this.f6988b).inflate(R.layout.view_item_meeting_particitant, (ViewGroup) null);
            c1368d = new C1368d(view);
            view.setTag(c1368d);
        } else {
            c1368d = (C1368d) view.getTag();
        }
        c1368d.f7012a.setVisibility(8);
        c1368d.f7016e.setVisibility(8);
        c1368d.f7025n.setVisibility(8);
        if (ParticipantType.WAITING_ROOM_TITLE == participantTypeM7041k) {
            c1368d.f7012a.setVisibility(0);
            c1368d.f7014c.setVisibility(0);
            c1368d.f7015d.setVisibility(8);
            c1368d.f7013b.setText(this.f6988b.getString(R.string.clm_meeting_waiting_room_people) + " (" + this.f6993g.size() + ")");
            SpannableString spannableString = new SpannableString(this.f6988b.getResources().getString(R.string.clm_meeting_waiting_room_admit));
            spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
            c1368d.f7014c.setText(spannableString);
            c1368d.f7014c.setOnClickListener(new View.OnClickListener() { // from class: p2.j6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f20491b.m7026r(view2);
                }
            });
        } else if (ParticipantType.WAITING_ROOM == participantTypeM7041k) {
            final C1262a c1262a = (C1262a) getItem(i9);
            if (c1262a != null) {
                final int iM5730b = c1262a.m5730b();
                c1368d.f7025n.setVisibility(0);
                c1368d.f7029r.setOnClickListener(new View.OnClickListener() { // from class: p2.k6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f20502b.m7027s(iM5730b, view2);
                    }
                });
                c1368d.f7030s.setOnClickListener(new View.OnClickListener() { // from class: p2.l6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f20512b.m7028t(c1262a, view2);
                    }
                });
                this.f6989c.m6013r(new C0011a(c1262a.m5730b(), c1262a.m5731c(), String.valueOf(c1262a.m5732d())), c1368d.f7026o, AvatarDrawableCache.AvatarSize.MIDDLE);
                c1368d.f7027p.setText(c1262a.m5731c());
                c1368d.f7028q.setText(c1262a.m5729a());
            }
        } else if (ParticipantType.PARTICIPANT_TITLE == participantTypeM7041k) {
            c1368d.f7012a.setVisibility(0);
            c1368d.f7014c.setVisibility(8);
            if (this.f7001o.size() > 0) {
                c1368d.f7015d.setVisibility(0);
                ((TextView) c1368d.f7015d.findViewById(R.id.participant_title_raise_hand_count)).setText(String.valueOf(this.f7001o.size()));
            } else {
                c1368d.f7015d.setVisibility(8);
            }
            c1368d.f7013b.setText(this.f6988b.getString(R.string.clm_meeting_participant_people) + " (" + this.f6990d.m2926u() + ")");
        } else if (ParticipantType.PARTICIPANT == participantTypeM7041k) {
            c1368d.f7016e.setVisibility(0);
            final C0012b c0012b = (C0012b) getItem(i9);
            if (c0012b != null) {
                this.f6989c.m6013r(c0012b.f66b, c1368d.f7017f, AvatarDrawableCache.AvatarSize.MIDDLE);
                c1368d.f7017f.setOnClickListener(new View.OnClickListener() { // from class: p2.m6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f20524b.m7029u(c0012b, view2);
                    }
                });
                Iterator<Integer> it = this.f7000n.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z8 = false;
                        break;
                    }
                    if (c0012b.f66b.f63b == it.next().intValue()) {
                        z8 = true;
                        break;
                    }
                }
                c1368d.f7017f.setSelected(z8);
                if (z8) {
                    c1368d.f7018g.setVisibility(0);
                } else {
                    c1368d.f7018g.setVisibility(8);
                }
                c1368d.f7019h.setText(c0012b.m86e());
                if (c0012b.m97p()) {
                    c1368d.f7024m.setVisibility(0);
                } else {
                    c1368d.f7024m.setVisibility(8);
                }
                boolean z9 = this.f6994h.f63b == c0012b.f66b.f63b;
                int i10 = z9 ? 4 : 0;
                c1368d.f7020i.setVisibility(i10);
                c1368d.f7021j.setVisibility(i10);
                c1368d.f7031t.setVisibility(i10);
                c1368d.f7032u.setVisibility(i10);
                if (c0012b.m98q()) {
                    c1368d.f7022k.setVisibility(0);
                } else {
                    c1368d.f7022k.setVisibility(8);
                }
                if (!z9) {
                    c1368d.f7020i.setSelected(!c0012b.m99r());
                    if (c0012b.m90i()) {
                        c1368d.f7021j.setImageResource(R.drawable.meeting_participants_speaker_presenting);
                    } else {
                        c1368d.f7021j.setImageResource(R.drawable.icon_meeting_participants_speaker);
                        c1368d.f7021j.setSelected(!c0012b.m92k());
                    }
                    if (!this.f7002p || this.f6999m) {
                        c1368d.f7033v.setEnabled(true);
                    } else {
                        c1368d.f7033v.setEnabled(false);
                    }
                    c1368d.f7033v.setOnClickListener(new View.OnClickListener() { // from class: p2.n6
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f20535b.m7030v(c0012b, view2);
                        }
                    });
                    c1368d.f7031t.setVisibility((!c0012b.m95n() || this.f7002p) ? 8 : 0);
                }
                NileNetwork.PlatformType platformTypeM87f = c0012b.m87f();
                NileNetwork.PlatformType platformType = NileNetwork.PlatformType.Platform_PhoneLine;
                if (platformTypeM87f == platformType) {
                    if (!(c0012b.f66b.f65d > 0) && c0012b.m87f() == platformType) {
                        C6127a.m23464e(this.f6988b, c1368d.f7017f, R.drawable.pic_default);
                    }
                    c1368d.f7020i.setVisibility(8);
                    c1368d.f7021j.setVisibility(8);
                    c1368d.f7022k.setVisibility(8);
                    c1368d.f7024m.setVisibility(8);
                    c1368d.f7023l.setVisibility(0);
                    c1368d.f7031t.setVisibility(8);
                } else {
                    c1368d.f7023l.setVisibility(8);
                }
            } else {
                C6127a.m23464e(this.f6988b, c1368d.f7017f, R.drawable.pic_default);
                c1368d.f7019h.setText("");
                c1368d.f7020i.setVisibility(8);
                c1368d.f7021j.setVisibility(8);
                c1368d.f7022k.setVisibility(8);
                c1368d.f7023l.setVisibility(8);
                c1368d.f7024m.setVisibility(8);
                c1368d.f7031t.setVisibility(8);
            }
        } else if (ParticipantType.NOT_JOIN_TITLE == participantTypeM7041k) {
            c1368d.f7012a.setVisibility(0);
            c1368d.f7014c.setVisibility(8);
            c1368d.f7015d.setVisibility(8);
            c1368d.f7013b.setText(this.f6988b.getString(R.string.clm_meeting_not_join_people) + " (" + this.f6991e.m2926u() + ")");
        } else if (ParticipantType.NOT_JOIN_DATA == participantTypeM7041k) {
            c1368d.f7016e.setVisibility(0);
            InviteeList.Invitee invitee = (InviteeList.Invitee) getItem(i9);
            c1368d.f7018g.setVisibility(8);
            c1368d.f7020i.setVisibility(8);
            c1368d.f7021j.setVisibility(8);
            c1368d.f7022k.setVisibility(8);
            c1368d.f7023l.setVisibility(8);
            c1368d.f7024m.setVisibility(8);
            c1368d.f7031t.setVisibility(8);
            c1368d.f7032u.setVisibility(8);
            c1368d.f7017f.setSelected(false);
            if (invitee != null) {
                String str = !TextUtils.isEmpty(invitee.displayName) ? invitee.displayName : invitee.email;
                c1368d.f7019h.setText(str);
                if (TextUtils.isEmpty(invitee.userId) || TextUtils.isEmpty(invitee.uid)) {
                    C6127a.m23464e(c1368d.f7017f.getContext(), c1368d.f7017f, R.drawable.pic_default);
                } else {
                    this.f6989c.m6013r(new C0011a(Integer.valueOf(invitee.userId).intValue(), str, invitee.uid), c1368d.f7017f, AvatarDrawableCache.AvatarSize.MIDDLE);
                }
            } else {
                C6127a.m23464e(this.f6988b, c1368d.f7017f, R.drawable.pic_default);
                c1368d.f7019h.setText("");
            }
        }
        return view;
    }

    /* renamed from: h */
    public final C0487p<InviteeList.Invitee> m7038h() {
        return new C0487p<>(InviteeList.Invitee.class, new C1366b());
    }

    /* renamed from: i */
    public final C0487p<C0012b> m7039i() {
        return new C0487p<>(C0012b.class, new C1365a());
    }

    /* renamed from: j */
    public Object m7040j(ParticipantType participantType, int i9) {
        int i10 = 0;
        if (participantType == ParticipantType.WAITING_ROOM) {
            while (i10 < this.f6993g.size()) {
                if (this.f6993g.get(i10).m5730b() == i9) {
                    return this.f6993g.get(i10);
                }
                i10++;
            }
            return null;
        }
        if (participantType != ParticipantType.PARTICIPANT) {
            return null;
        }
        while (i10 < this.f6990d.m2926u()) {
            if (this.f6990d.m2918m(i10).f66b.f63b == i9) {
                return this.f6990d.m2918m(i10);
            }
            i10++;
        }
        return null;
    }

    /* renamed from: k */
    public ParticipantType m7041k(int i9) {
        if (this.f6992f && this.f6998l && this.f6993g.size() > 0) {
            if (i9 == 0) {
                return ParticipantType.WAITING_ROOM_TITLE;
            }
            if (i9 <= this.f6993g.size()) {
                return ParticipantType.WAITING_ROOM;
            }
            i9 = (i9 - this.f6993g.size()) - 1;
        }
        if (i9 == 0) {
            return ParticipantType.PARTICIPANT_TITLE;
        }
        if (i9 <= this.f6990d.m2926u()) {
            return ParticipantType.PARTICIPANT;
        }
        int iM2926u = (i9 - this.f6990d.m2926u()) - 1;
        if (this.f6996j) {
            if (iM2926u == 0) {
                return ParticipantType.NOT_JOIN_TITLE;
            }
            if (iM2926u <= this.f6991e.m2926u()) {
                return ParticipantType.NOT_JOIN_DATA;
            }
        }
        return ParticipantType.PARTICIPANT;
    }

    /* renamed from: l */
    public C0012b m7042l(int i9) {
        for (int i10 = 0; i10 < this.f6990d.m2926u(); i10++) {
            C0012b c0012bM2918m = this.f6990d.m2918m(i10);
            if (c0012bM2918m.f66b.f63b == i9) {
                return c0012bM2918m;
            }
        }
        return null;
    }

    /* renamed from: m */
    public C0487p<C0012b> m7043m() {
        return this.f6990d;
    }

    /* renamed from: n */
    public int m7044n(ParticipantType participantType) {
        if (participantType == ParticipantType.WAITING_ROOM_TITLE || participantType == ParticipantType.PARTICIPANT_TITLE || participantType == ParticipantType.NOT_JOIN_TITLE) {
            return 1;
        }
        if (participantType == ParticipantType.PARTICIPANT) {
            return this.f6990d.m2926u();
        }
        if (participantType == ParticipantType.NOT_JOIN_DATA) {
            return this.f6991e.m2926u();
        }
        if (participantType == ParticipantType.WAITING_ROOM) {
            return this.f6993g.size();
        }
        return 0;
    }

    /* renamed from: o */
    public final int m7045o(int i9) {
        int iM7044n;
        ParticipantType participantTypeM7041k = m7041k(i9);
        ParticipantType participantType = ParticipantType.WAITING_ROOM;
        if (participantTypeM7041k == participantType) {
            iM7044n = m7044n(ParticipantType.WAITING_ROOM_TITLE);
        } else {
            ParticipantType participantType2 = ParticipantType.PARTICIPANT;
            if (participantTypeM7041k == participantType2) {
                if (this.f6992f && this.f6998l && this.f6993g.size() > 0) {
                    i9 = (i9 - m7044n(ParticipantType.WAITING_ROOM_TITLE)) - m7044n(participantType);
                }
                iM7044n = m7044n(ParticipantType.PARTICIPANT_TITLE);
            } else {
                if (participantTypeM7041k != ParticipantType.NOT_JOIN_DATA) {
                    if (participantTypeM7041k == ParticipantType.WAITING_ROOM_TITLE || participantTypeM7041k == ParticipantType.PARTICIPANT_TITLE || participantTypeM7041k == ParticipantType.NOT_JOIN_TITLE) {
                        return -1;
                    }
                    return i9;
                }
                if (this.f6992f && this.f6998l && this.f6993g.size() > 0) {
                    i9 = (i9 - m7044n(ParticipantType.WAITING_ROOM_TITLE)) - m7044n(participantType);
                }
                i9 = (i9 - m7044n(ParticipantType.PARTICIPANT_TITLE)) - m7044n(participantType2);
                iM7044n = m7044n(ParticipantType.NOT_JOIN_TITLE);
            }
        }
        return i9 - iM7044n;
    }

    /* renamed from: p */
    public List<C1262a> m7046p() {
        return this.f6993g;
    }

    /* renamed from: q */
    public boolean m7047q(C0012b c0012b) {
        return (c0012b.m96o() || c0012b.m88g() == NileNetwork.ParticipantState.ParticipantState_Lobby) ? false : true;
    }

    /* renamed from: w */
    public boolean m7048w(ParticipantType participantType, int i9) {
        boolean z8 = true;
        if (participantType == ParticipantType.WAITING_ROOM) {
            for (int i10 = 0; i10 < this.f6993g.size(); i10++) {
                if (this.f6993g.get(i10).m5730b() == i9) {
                    this.f6993g.remove(i10);
                    break;
                }
            }
            z8 = false;
        } else if (participantType == ParticipantType.PARTICIPANT) {
            for (int i11 = 0; i11 < this.f6990d.m2926u(); i11++) {
                if (this.f6990d.m2918m(i11).f66b.f63b == i9) {
                    this.f6990d.m2924s(i11);
                    break;
                }
            }
            z8 = false;
        } else {
            z8 = false;
        }
        if (this.f6996j) {
            m7049x();
        }
        return z8;
    }

    /* renamed from: x */
    public void m7049x() {
        boolean z8;
        this.f6991e.m2913h();
        for (InviteeList.Invitee invitee : this.f6997k) {
            boolean z9 = false;
            int i9 = 0;
            while (true) {
                if (i9 >= this.f6990d.m2926u()) {
                    z8 = true;
                    break;
                } else {
                    if (String.valueOf(this.f6990d.m2918m(i9).f66b.f65d).equals(invitee.uid)) {
                        z8 = false;
                        break;
                    }
                    i9++;
                }
            }
            int i10 = 0;
            while (true) {
                if (i10 >= this.f6993g.size()) {
                    z9 = z8;
                    break;
                } else if (String.valueOf(this.f6993g.get(i10).m5732d()).equals(invitee.uid)) {
                    break;
                } else {
                    i10++;
                }
            }
            if (z9) {
                this.f6991e.m2906a(invitee);
            }
        }
        notifyDataSetChanged();
    }

    /* renamed from: y */
    public void m7050y(boolean z8) {
        this.f6999m = z8;
    }

    /* renamed from: z */
    public void m7051z(C0011a c0011a) {
        this.f6994h = c0011a;
    }
}
