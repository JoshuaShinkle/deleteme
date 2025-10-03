package com.cyberlink.you.activity.chatdialog;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.C2337o0;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.activity.chatdialog.C2027b;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.widgetpool.common.EmojiconEditTextWithLengthFilter;
import com.google.android.exoplayer2.util.MimeTypes;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p086h4.C5015s;
import p116k4.C5169o;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p209u2.C6383t;

/* renamed from: com.cyberlink.you.activity.chatdialog.b */
/* loaded from: classes.dex */
public class C2027b extends Fragment {

    /* renamed from: d */
    public C2337o0 f10306d;

    /* renamed from: g */
    public EmojiconEditTextWithLengthFilter f10309g;

    /* renamed from: h */
    public View f10310h;

    /* renamed from: i */
    public View f10311i;

    /* renamed from: j */
    public Button f10312j;

    /* renamed from: k */
    public ImageView f10313k;

    /* renamed from: l */
    public View f10314l;

    /* renamed from: m */
    public View f10315m;

    /* renamed from: n */
    public View f10316n;

    /* renamed from: o */
    public View f10317o;

    /* renamed from: p */
    public View f10318p;

    /* renamed from: w */
    public boolean f10325w;

    /* renamed from: x */
    public Group f10326x;

    /* renamed from: y */
    public i f10327y;

    /* renamed from: z */
    public j f10328z;

    /* renamed from: b */
    public final int f10304b = 27;

    /* renamed from: c */
    public final int f10305c = 0;

    /* renamed from: e */
    public C5015s f10307e = null;

    /* renamed from: f */
    public VoiceRecordFragment f10308f = null;

    /* renamed from: q */
    public boolean f10319q = true;

    /* renamed from: r */
    public boolean f10320r = true;

    /* renamed from: s */
    public boolean f10321s = true;

    /* renamed from: t */
    public boolean f10322t = false;

    /* renamed from: u */
    public boolean f10323u = false;

    /* renamed from: v */
    public boolean f10324v = false;

    /* renamed from: A */
    public View.OnClickListener f10292A = new View.OnClickListener() { // from class: y2.o5
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22390b.m12034I(view);
        }
    };

    /* renamed from: B */
    public View.OnClickListener f10293B = new View.OnClickListener() { // from class: y2.p5
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22396b.m12035J(view);
        }
    };

    /* renamed from: C */
    public View.OnClickListener f10294C = new a();

    /* renamed from: D */
    public View.OnDragListener f10295D = new View.OnDragListener() { // from class: y2.q5
        @Override // android.view.View.OnDragListener
        public final boolean onDrag(View view, DragEvent dragEvent) {
            return C2027b.m12036K(view, dragEvent);
        }
    };

    /* renamed from: E */
    public View.OnClickListener f10296E = new View.OnClickListener() { // from class: y2.r5
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22417b.m12037L(view);
        }
    };

    /* renamed from: F */
    public C5015s.g f10297F = new b();

    /* renamed from: G */
    public VoiceRecordFragment.InterfaceC1861g f10298G = new c();

    /* renamed from: H */
    public View.OnClickListener f10299H = new d();

    /* renamed from: I */
    public View.OnClickListener f10300I = new e();

    /* renamed from: J */
    public View.OnClickListener f10301J = new f();

    /* renamed from: K */
    public View.OnClickListener f10302K = new g();

    /* renamed from: L */
    public C2337o0.l f10303L = new C2337o0.l() { // from class: y2.s5
        @Override // com.cyberlink.you.activity.C2337o0.l
        /* renamed from: a */
        public final void mo12491a(String str, Map map) {
            this.f22428a.m12065Q(str, map);
        }
    };

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2027b.this.f10309g == null) {
                return;
            }
            String strM12084z = C2027b.this.m12084z();
            HashMap map = new HashMap();
            map.put(MimeTypes.BASE_TYPE_TEXT, strM12084z);
            ULogUtility.m16670f("MessageInputFragment", "[send] start to send message : " + strM12084z);
            C2027b.this.m12065Q("sendText", map);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$b */
    public class b implements C5015s.g {
        public b() {
        }

        @Override // p086h4.C5015s.g
        /* renamed from: a */
        public void mo7979a(StickerObj stickerObj) {
            if (stickerObj != null) {
                HashMap map = new HashMap();
                map.put("sticer", stickerObj);
                C2027b.this.m12065Q("sendSticker", map);
            }
        }

        @Override // p086h4.C5015s.g
        /* renamed from: b */
        public void mo7980b(Emojicon emojicon) {
            if (emojicon != null) {
                HashMap map = new HashMap();
                map.put("sticer", emojicon);
                C2027b.this.m12065Q("sendSticker", map);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$c */
    public class c implements VoiceRecordFragment.InterfaceC1861g {
        public c() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: a */
        public void mo7976a() {
            C2027b.this.m12065Q("recordVoice", new HashMap());
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: b */
        public void mo7977b(boolean z8) {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: c */
        public void mo7978c(String str, String str2) {
            Log.v("MessageInputFragment", "Send record result : path = " + str + " duration : " + str2);
            if (str == null || str.isEmpty()) {
                return;
            }
            HashMap map = new HashMap();
            map.put("voicePath", str);
            map.put("duration", str2);
            C2027b.this.m12065Q("sendVoice", map);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16589t1(C2027b.this.getActivity());
            C2027b c2027b = C2027b.this;
            Boolean bool = Boolean.FALSE;
            c2027b.m12075a0(bool, bool, bool);
            C2027b.this.f10314l.setVisibility(4);
            C2027b.this.f10315m.setVisibility(0);
            C2027b.this.m12066R("meetingInvite");
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$e */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2027b.this.getActivity() != null && C2027b.this.f10315m.getVisibility() == 0) {
                C2027b.this.m12078d0(MimeTypes.BASE_TYPE_AUDIO, "chatRoom inputBar audio meeting button");
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$f */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2027b.this.getActivity() != null && C2027b.this.f10315m.getVisibility() == 0) {
                C2027b.this.m12078d0(MimeTypes.BASE_TYPE_VIDEO, "chatRoom inputBar video meeting button");
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$g */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2027b.this.f10314l.setVisibility(0);
            C2027b.this.f10315m.setVisibility(8);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$h */
    public class h implements TextWatcher {
        public h() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            boolean zM24517f = C6383t.m24517f(editable.toString().trim());
            C2027b.this.f10312j.setEnabled(!zM24517f);
            if (C2027b.this.f10321s) {
                if (zM24517f) {
                    C2027b.this.f10312j.setVisibility(8);
                    C2027b.this.f10313k.setVisibility(0);
                } else {
                    C2027b.this.f10312j.setVisibility(0);
                    C2027b.this.f10313k.setVisibility(8);
                }
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$i */
    public interface i {
        /* renamed from: a */
        void mo7011a(String str, Map<String, Object> map);
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.b$j */
    public interface j {
        /* renamed from: a */
        void mo11646a(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m12034I(View view) {
        Object tag = view.getTag(R.drawable.icon_keyboard);
        if (tag == null || !((Boolean) tag).booleanValue()) {
            Boolean bool = Boolean.TRUE;
            Boolean bool2 = Boolean.FALSE;
            m12075a0(bool, bool2, bool2);
            CLUtility.m16589t1(getActivity());
        } else {
            Boolean bool3 = Boolean.FALSE;
            m12075a0(bool3, bool3, bool3);
            CLUtility.m16606x2(getActivity());
        }
        m12066R("chatPlus");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m12035J(View view) {
        Object tag = view.getTag(R.drawable.icon_keyboard);
        if (tag == null || !((Boolean) tag).booleanValue()) {
            Boolean bool = Boolean.FALSE;
            m12075a0(bool, Boolean.TRUE, bool);
            CLUtility.m16589t1(getActivity());
        } else {
            Boolean bool2 = Boolean.FALSE;
            m12075a0(bool2, bool2, bool2);
            CLUtility.m16606x2(getActivity());
        }
        m12066R("stickerButton");
    }

    /* renamed from: K */
    public static /* synthetic */ boolean m12036K(View view, DragEvent dragEvent) {
        Log.v("MessageInputFragment", "onDrag");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m12037L(View view) {
        Boolean bool = Boolean.FALSE;
        m12075a0(bool, bool, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ void m12038M(int i9) {
        if (i9 == 4) {
            m12066R("backKey");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ boolean m12039N(View view, MotionEvent motionEvent) {
        Boolean bool = Boolean.FALSE;
        m12075a0(bool, bool, bool);
        m12066R("chatMessage");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m12040O() {
        this.f10309g.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m12041P(Dialog dialog, String str, String str2, View view) {
        dialog.dismiss();
        MeetingActivity.m6370f9(getActivity(), this.f10326x, true, str, str2);
        this.f10314l.setVisibility(0);
        this.f10315m.setVisibility(8);
    }

    /* renamed from: A */
    public void m12057A() {
        getView().setVisibility(8);
    }

    /* renamed from: B */
    public final void m12058B() {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(this.f10311i.getLayoutParams());
        marginLayoutParams.setMargins(27, 0, 27, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginLayoutParams);
        layoutParams.addRule(15, -1);
        this.f10311i.setLayoutParams(layoutParams);
        this.f10310h.setVisibility(8);
    }

    /* renamed from: C */
    public final void m12059C() {
        this.f10311i.setVisibility(8);
    }

    /* renamed from: D */
    public final void m12060D() {
        C2337o0 c2337o0 = (C2337o0) getChildFragmentManager().mo1848e("ChatPlus");
        this.f10306d = c2337o0;
        if (c2337o0 != null) {
            c2337o0.m12466I(this.f10303L);
            return;
        }
        this.f10306d = new C2337o0();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f10326x);
        bundle.putBoolean("enableExtraOp", this.f10325w);
        bundle.putBoolean("hideVoice4ImportPhoto", this.f10323u);
        bundle.putBoolean("singleSelect4ImportPhoto", this.f10324v);
        bundle.putBoolean("enableMeetingShareDoc", this.f10322t);
        this.f10306d.setArguments(bundle);
        this.f10306d.m12466I(this.f10303L);
        getChildFragmentManager().mo1844a().m1981c(R.id.chatPlusFragmentContainer, this.f10306d, "ChatPlus").mo1799n(this.f10306d).mo1794h();
    }

    /* renamed from: E */
    public final void m12061E() {
        C5015s c5015s = (C5015s) getChildFragmentManager().mo1848e("Sticker");
        this.f10307e = c5015s;
        if (c5015s != null) {
            c5015s.m19511m0(this.f10297F);
            return;
        }
        this.f10307e = new C5015s();
        Bundle bundle = new Bundle();
        bundle.putBoolean("Emoji", true);
        bundle.putBoolean("Shop", true);
        bundle.putBoolean("Setting", true);
        bundle.putInt("Mode", 0);
        this.f10307e.setArguments(bundle);
        this.f10307e.m19511m0(this.f10297F);
        getChildFragmentManager().mo1844a().m1981c(R.id.stickerFragmentContainer, this.f10307e, "Sticker").mo1799n(this.f10307e).mo1794h();
    }

    /* renamed from: F */
    public final void m12062F() {
        VoiceRecordFragment voiceRecordFragment = (VoiceRecordFragment) getChildFragmentManager().mo1848e("VoiceRecord");
        this.f10308f = voiceRecordFragment;
        if (voiceRecordFragment != null) {
            voiceRecordFragment.m11003X(this.f10298G);
            return;
        }
        VoiceRecordFragment voiceRecordFragment2 = new VoiceRecordFragment();
        this.f10308f = voiceRecordFragment2;
        voiceRecordFragment2.m11003X(this.f10298G);
        getChildFragmentManager().mo1844a().m1981c(R.id.voiceRecordFragmentContainer, this.f10308f, "VoiceRecord").mo1799n(this.f10308f).mo1794h();
    }

    /* renamed from: G */
    public void m12063G(Emojicon emojicon) {
        EmojiconEditTextWithLengthFilter emojiconEditTextWithLengthFilter = this.f10309g;
        if (emojiconEditTextWithLengthFilter == null || emojicon == null) {
            return;
        }
        int selectionStart = emojiconEditTextWithLengthFilter.getSelectionStart();
        int selectionEnd = this.f10309g.getSelectionEnd();
        if (selectionStart < 0) {
            this.f10309g.append(emojicon.getEmoji());
        } else {
            this.f10309g.getText().replace(Math.min(selectionStart, selectionEnd), Math.max(selectionStart, selectionEnd), emojicon.getEmoji(), 0, emojicon.getEmoji().length());
        }
    }

    /* renamed from: H */
    public boolean m12064H() {
        return this.f10312j.isEnabled();
    }

    /* renamed from: Q */
    public void m12065Q(String str, Map<String, Object> map) {
        i iVar = this.f10327y;
        if (iVar != null) {
            iVar.mo7011a(str, map);
        }
    }

    /* renamed from: R */
    public void m12066R(String str) {
        j jVar = this.f10328z;
        if (jVar != null) {
            jVar.mo11646a(str);
        }
    }

    /* renamed from: S */
    public void m12067S() {
        this.f10309g.post(new Runnable() { // from class: y2.n5
            @Override // java.lang.Runnable
            public final void run() {
                this.f22377b.m12040O();
            }
        });
    }

    /* renamed from: T */
    public void m12068T() {
        this.f10309g.setText("");
    }

    /* renamed from: U */
    public void m12069U(i iVar) {
        this.f10327y = iVar;
    }

    /* renamed from: V */
    public void m12070V(j jVar) {
        this.f10328z = jVar;
    }

    /* renamed from: W */
    public void m12071W(int i9) {
        this.f10309g.setSelection(i9);
    }

    /* renamed from: X */
    public void m12072X(String str) {
        this.f10309g.setText(str);
    }

    /* renamed from: Y */
    public void m12073Y() {
        getView().setVisibility(0);
    }

    /* renamed from: Z */
    public final void m12074Z(boolean z8) {
        if (this.f10306d == null) {
            m12060D();
        }
        if (z8) {
            getChildFragmentManager().mo1844a().mo1802r(this.f10306d).mo1794h();
        } else {
            getChildFragmentManager().mo1844a().mo1799n(this.f10306d).mo1794h();
        }
    }

    /* renamed from: a0 */
    public boolean m12075a0(Boolean bool, Boolean bool2, Boolean bool3) {
        if (bool.booleanValue()) {
            m12066R("chatPlus");
        }
        if (bool2.booleanValue()) {
            m12066R("stickerButton");
        }
        return m12076b0(bool.booleanValue(), bool2.booleanValue(), bool3.booleanValue(), false);
    }

    /* renamed from: b0 */
    public boolean m12076b0(boolean z8, boolean z9, boolean z10, boolean z11) {
        boolean z12;
        C2337o0 c2337o0 = this.f10306d;
        if (!(c2337o0 == null && z8) && ((c2337o0 == null || c2337o0.isVisible() == z8) && !z11)) {
            z12 = false;
        } else {
            m12074Z(z8);
            z12 = true;
        }
        if (z8) {
            ((ImageView) this.f10310h).setImageResource(R.drawable.icon_keyboard);
            this.f10310h.setTag(R.drawable.icon_keyboard, Boolean.TRUE);
        } else {
            ((ImageView) this.f10310h).setImageResource(R.drawable.chat_input_01);
            this.f10310h.setTag(R.drawable.icon_keyboard, Boolean.FALSE);
        }
        C5015s c5015s = this.f10307e;
        if ((c5015s == null && z9) || ((c5015s != null && c5015s.isVisible() != z9) || z11)) {
            m12077c0(z9);
            z12 = true;
        }
        if (z9) {
            ((ImageView) this.f10311i).setImageResource(R.drawable.icon_keyboard);
            this.f10311i.setTag(R.drawable.icon_keyboard, Boolean.TRUE);
        } else {
            ((ImageView) this.f10311i).setImageResource(R.drawable.chat_input_02);
            this.f10311i.setTag(R.drawable.icon_keyboard, Boolean.FALSE);
        }
        VoiceRecordFragment voiceRecordFragment = this.f10308f;
        if (!(voiceRecordFragment == null && z10) && ((voiceRecordFragment == null || voiceRecordFragment.isVisible() == z10) && !z11)) {
            return z12;
        }
        m12079e0(z10);
        return true;
    }

    /* renamed from: c0 */
    public final void m12077c0(boolean z8) {
        if (this.f10307e == null) {
            m12061E();
        }
        if (z8) {
            getChildFragmentManager().mo1844a().mo1802r(this.f10307e).mo1794h();
        } else {
            getChildFragmentManager().mo1844a().mo1799n(this.f10307e).mo1794h();
        }
    }

    /* renamed from: d0 */
    public final void m12078d0(final String str, final String str2) {
        if (getActivity() == null) {
            return;
        }
        if (C2925v.m14620s0()) {
            C2925v.m14569N0(getActivity(), this.f10326x, true, str, str2);
        } else {
            if (!C2925v.m14622t0()) {
                MeetingActivity.m6370f9(getActivity(), this.f10326x, true, str, str2);
                return;
            }
            final Dialog dialogM14586b0 = C2925v.m14586b0(getActivity(), this.f10326x, true, str, str2);
            ((TextView) dialogM14586b0.findViewById(R.id.v_btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: y2.v5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f22461b.m12041P(dialogM14586b0, str, str2, view);
                }
            });
            dialogM14586b0.show();
        }
    }

    /* renamed from: e0 */
    public final void m12079e0(boolean z8) {
        CLUtility.m16589t1(getActivity());
        if (this.f10308f == null) {
            m12062F();
        }
        if (z8) {
            getChildFragmentManager().mo1844a().mo1802r(this.f10308f).mo1794h();
        } else {
            getChildFragmentManager().mo1844a().mo1799n(this.f10308f).mo1794h();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (getActivity() == null) {
            return;
        }
        if (i9 != 99) {
            List<Fragment> listMo1852i = getChildFragmentManager().mo1852i();
            if (listMo1852i != null) {
                Iterator<Fragment> it = listMo1852i.iterator();
                while (it.hasNext()) {
                    it.next().onActivityResult(i9, i10, intent);
                }
                return;
            }
            return;
        }
        if (i10 != -1 || intent == null) {
            return;
        }
        ArrayList arrayList = (ArrayList) intent.getExtras().getSerializable("import_images");
        HashMap map = new HashMap();
        map.put("importImages", arrayList);
        m12065Q("sendPhoto", map);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.f10319q = getArguments().getBoolean("enableChatPlus", false);
        this.f10320r = getArguments().getBoolean("enableChatSticker", true);
        this.f10321s = getArguments().getBoolean("enableChatMeeting", true);
        this.f10322t = getArguments().getBoolean("enableMeetingShareDoc", false);
        this.f10325w = getArguments().getBoolean("enableExtraOp", true);
        this.f10326x = (Group) getArguments().getParcelable("Group");
        this.f10323u = getArguments().getBoolean("hideVoice4ImportPhoto", false);
        this.f10324v = getArguments().getBoolean("singleSelect4ImportPhoto", false);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_message_input, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        Button button = this.f10312j;
        if (button != null) {
            button.setOnClickListener(null);
        }
        ImageView imageView = this.f10313k;
        if (imageView != null) {
            imageView.setOnClickListener(null);
        }
        View view = this.f10311i;
        if (view != null) {
            view.setOnClickListener(null);
        }
        View view2 = this.f10310h;
        if (view2 != null) {
            view2.setOnClickListener(null);
        }
        EmojiconEditTextWithLengthFilter emojiconEditTextWithLengthFilter = this.f10309g;
        if (emojiconEditTextWithLengthFilter != null) {
            emojiconEditTextWithLengthFilter.setOnDragListener(null);
            this.f10309g.setOnFocusChangeListener(null);
        }
        C5015s c5015s = this.f10307e;
        if (c5015s != null) {
            c5015s.m19511m0(null);
        }
        VoiceRecordFragment voiceRecordFragment = this.f10308f;
        if (voiceRecordFragment != null) {
            voiceRecordFragment.m11003X(null);
        }
        C2337o0 c2337o0 = this.f10306d;
        if (c2337o0 != null) {
            c2337o0.m12466I(null);
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) throws Resources.NotFoundException {
        Button button = (Button) view.findViewById(R.id.sendBtn);
        this.f10312j = button;
        button.setOnClickListener(this.f10294C);
        ImageView imageView = (ImageView) view.findViewById(R.id.sendMeetingInviteBtn);
        this.f10313k = imageView;
        imageView.setOnClickListener(this.f10299H);
        View viewFindViewById = view.findViewById(R.id.stickerBtn);
        this.f10311i = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f10293B);
        View viewFindViewById2 = view.findViewById(R.id.chatPlusBtn);
        this.f10310h = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f10292A);
        EmojiconEditTextWithLengthFilter emojiconEditTextWithLengthFilter = (EmojiconEditTextWithLengthFilter) view.findViewById(R.id.inputEditText);
        this.f10309g = emojiconEditTextWithLengthFilter;
        C5169o.m20162g(emojiconEditTextWithLengthFilter, true);
        if (getActivity() instanceof MeetingActivity) {
            this.f10309g.setMaxLength(getResources().getInteger(R.integer.max_input_length_in_meeting));
        } else if (getActivity() instanceof ChatDialogActivity) {
            this.f10309g.setMaxLength(getResources().getInteger(R.integer.max_input_length_in_chatting));
        } else {
            this.f10309g.setMaxLength(getResources().getInteger(R.integer.max_input_length_default));
        }
        this.f10309g.setOnDragListener(this.f10295D);
        this.f10309g.setOnClickListener(this.f10296E);
        this.f10314l = view.findViewById(R.id.inputBarContainer);
        this.f10315m = view.findViewById(R.id.CallerContainer);
        View viewFindViewById3 = view.findViewById(R.id.voiceCallBtn);
        this.f10316n = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f10300I);
        View viewFindViewById4 = view.findViewById(R.id.videoCallBtn);
        this.f10317o = viewFindViewById4;
        viewFindViewById4.setOnClickListener(this.f10301J);
        View viewFindViewById5 = view.findViewById(R.id.voiceCancelBtn);
        this.f10318p = viewFindViewById5;
        viewFindViewById5.setOnClickListener(this.f10302K);
        this.f10309g.setOnPreImeKeyListener(new EmojiconEditText.OnKeyEventPreImeListener() { // from class: y2.t5
            @Override // com.rockerhieu.emojicon.EmojiconEditText.OnKeyEventPreImeListener
            public final void onPreImeKeyEvent(int i9) {
                this.f22437a.m12038M(i9);
            }
        });
        this.f10309g.addTextChangedListener(new h());
        if (bundle == null) {
            m12061E();
            m12062F();
        } else {
            m12076b0(false, false, false, true);
        }
        if (!this.f10319q) {
            m12058B();
        }
        if (!this.f10320r) {
            m12059C();
        }
        if (!this.f10321s) {
            this.f10312j.setVisibility(0);
            this.f10313k.setVisibility(8);
        }
        this.f10309g.setOnTouchListener(new View.OnTouchListener() { // from class: y2.u5
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f22446b.m12039N(view2, motionEvent);
            }
        });
        C5179r0.m20247b(this.f10312j, 1);
        m12080v();
    }

    /* renamed from: v */
    public void m12080v() {
        Group group;
        if (!(getActivity() instanceof ChatDialogActivity) || (group = this.f10326x) == null) {
            return;
        }
        if (group.f13711J) {
            this.f10313k.setImageDrawable(null);
            this.f10313k.setImageResource(R.drawable.btn_meeting_call_secure);
            this.f10312j.setBackgroundResource(R.drawable.image_selector_send_button_secure);
            this.f10316n.setBackgroundResource(R.drawable.image_selector_send_button_secure);
            this.f10317o.setBackgroundResource(R.drawable.image_selector_send_button_secure);
            this.f10318p.setBackgroundResource(R.drawable.image_selector_send_button_secure);
            return;
        }
        this.f10313k.setImageDrawable(null);
        this.f10313k.setImageResource(R.drawable.btn_meeting_call);
        this.f10312j.setBackgroundResource(R.drawable.image_selector_send_button);
        this.f10316n.setBackgroundResource(R.drawable.image_selector_send_button);
        this.f10317o.setBackgroundResource(R.drawable.image_selector_send_button);
        this.f10318p.setBackgroundResource(R.drawable.image_selector_send_button);
    }

    /* renamed from: w */
    public void m12081w() {
        this.f10310h.setEnabled(false);
    }

    /* renamed from: x */
    public void m12082x() {
        this.f10310h.setEnabled(true);
    }

    /* renamed from: y */
    public C5015s m12083y() {
        return this.f10307e;
    }

    /* renamed from: z */
    public String m12084z() {
        return C5170o0.m20166a(this.f10309g.getText().toString());
    }
}
