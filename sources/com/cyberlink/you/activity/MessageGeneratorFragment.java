package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.PhotoImportActivity;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import p086h4.C4999d;
import p086h4.C5002f;
import p086h4.C5015s;
import p086h4.C5018v;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public class MessageGeneratorFragment extends Fragment {

    /* renamed from: u */
    public static Uri f8047u;

    /* renamed from: b */
    public View.OnClickListener f8048b = new ViewOnClickListenerC1549a();

    /* renamed from: c */
    public View.OnClickListener f8049c = new ViewOnClickListenerC1550b();

    /* renamed from: d */
    public View.OnClickListener f8050d = new ViewOnClickListenerC1551c();

    /* renamed from: e */
    public ViewGroup f8051e = null;

    /* renamed from: f */
    public ViewGroup f8052f = null;

    /* renamed from: g */
    public ViewGroup f8053g = null;

    /* renamed from: h */
    public ViewGroup f8054h = null;

    /* renamed from: i */
    public EditText f8055i = null;

    /* renamed from: j */
    public String f8056j = "";

    /* renamed from: k */
    public boolean f8057k = false;

    /* renamed from: l */
    public View f8058l = null;

    /* renamed from: m */
    public View f8059m = null;

    /* renamed from: n */
    public View f8060n = null;

    /* renamed from: o */
    public View f8061o = null;

    /* renamed from: p */
    public C5015s f8062p = null;

    /* renamed from: q */
    public VoiceRecordFragment f8063q = null;

    /* renamed from: r */
    public InputType f8064r = null;

    /* renamed from: s */
    public InterfaceC1555g f8065s = null;

    /* renamed from: t */
    public VoiceRecordFragment.InterfaceC1861g f8066t = new C1554f();

    public enum InputType {
        Text,
        Photo,
        Sticker,
        Voice
    }

    public enum PhotoType {
        Camera,
        Gallery
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$a */
    public class ViewOnClickListenerC1549a implements View.OnClickListener {

        /* renamed from: b */
        public InterfaceC5288c f8075b = new a();

        /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$a$a */
        public class a implements InterfaceC5288c {
            public a() {
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(MessageGeneratorFragment.this.getActivity(), Permission.MICROPHONE);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                ViewOnClickListenerC1549a.this.m8748c();
            }
        }

        public ViewOnClickListenerC1549a() {
        }

        /* renamed from: b */
        public final void m8747b() {
            C5287b.m20583f(Permission.MICROPHONE, this.f8075b, MessageGeneratorFragment.this.getActivity());
        }

        /* renamed from: c */
        public final void m8748c() {
            MessageGeneratorFragment.this.f8064r = InputType.Voice;
            MessageGeneratorFragment messageGeneratorFragment = MessageGeneratorFragment.this;
            messageGeneratorFragment.m8743u(messageGeneratorFragment.f8064r);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16589t1(MessageGeneratorFragment.this.getActivity());
            switch (view.getId()) {
                case R.id.photoMessage /* 2131298323 */:
                    MessageGeneratorFragment.this.f8064r = InputType.Photo;
                    MessageGeneratorFragment messageGeneratorFragment = MessageGeneratorFragment.this;
                    messageGeneratorFragment.m8743u(messageGeneratorFragment.f8064r);
                    break;
                case R.id.stickerMessage /* 2131298690 */:
                    MessageGeneratorFragment.this.f8064r = InputType.Sticker;
                    MessageGeneratorFragment messageGeneratorFragment2 = MessageGeneratorFragment.this;
                    messageGeneratorFragment2.m8743u(messageGeneratorFragment2.f8064r);
                    break;
                case R.id.textMessage /* 2131298753 */:
                    MessageGeneratorFragment.this.f8064r = InputType.Text;
                    MessageGeneratorFragment messageGeneratorFragment3 = MessageGeneratorFragment.this;
                    messageGeneratorFragment3.m8743u(messageGeneratorFragment3.f8064r);
                    break;
                case R.id.voiceMessage /* 2131298961 */:
                    m8747b();
                    break;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$b */
    public class ViewOnClickListenerC1550b implements View.OnClickListener {

        /* renamed from: b */
        public InterfaceC5288c f8078b = new a();

        /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$b$a */
        public class a implements InterfaceC5288c {
            public a() {
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (!z8 || MessageGeneratorFragment.this.getActivity() == null) {
                    C5187v0.m20267c(R.string.permission_denied);
                } else if (Build.VERSION.SDK_INT >= 33) {
                    C5183t0.m20261a(MessageGeneratorFragment.this.getActivity(), R.string.permission_ask_photo_video_camera);
                } else {
                    C5183t0.m20261a(MessageGeneratorFragment.this.getActivity(), R.string.permission_ask_storage_camera);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                ViewOnClickListenerC1550b.this.m8750b();
            }
        }

        public ViewOnClickListenerC1550b() {
        }

        /* renamed from: b */
        public final void m8750b() {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            if (intent.resolveActivity(MessageGeneratorFragment.this.getActivity().getPackageManager()) != null) {
                Uri unused = MessageGeneratorFragment.f8047u = CLUtility.m16468N0();
                if (MessageGeneratorFragment.f8047u == null) {
                    return;
                }
                intent.putExtra("output", MessageGeneratorFragment.f8047u);
                MessageGeneratorFragment.this.startActivityForResult(intent, 0);
                Globals.m7388i0().m7526b3(true);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C5287b.m20584g(Build.VERSION.SDK_INT >= 33 ? new Permission[]{Permission.IMAGE, Permission.CAMERA} : new Permission[]{Permission.STORAGE, Permission.CAMERA}, this.f8078b, MessageGeneratorFragment.this.getActivity());
        }
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$c */
    public class ViewOnClickListenerC1551c implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$c$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Permission f8082a;

            public a(Permission permission) {
                this.f8082a = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(MessageGeneratorFragment.this.getActivity(), this.f8082a);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                Intent intent = new Intent(MessageGeneratorFragment.this.getActivity(), (Class<?>) PhotoImportActivity.class);
                intent.putExtra("isSelfDestruct", MessageGeneratorFragment.this.f8057k);
                MessageGeneratorFragment.this.startActivityForResult(intent, 1);
            }
        }

        public ViewOnClickListenerC1551c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MessageGeneratorFragment.this.getActivity() == null) {
                return;
            }
            Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
            C5287b.m20583f(permission, new a(permission), MessageGeneratorFragment.this.getActivity());
        }
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$d */
    public class C1552d implements C5015s.g {
        public C1552d() {
        }

        @Override // p086h4.C5015s.g
        /* renamed from: a */
        public void mo7979a(StickerObj stickerObj) {
            if (MessageGeneratorFragment.this.f8065s != null) {
                Log.i("test", "onSelectSticker");
                new C5002f(MessageGeneratorFragment.this.getActivity()).m19426a(stickerObj);
                MessageGeneratorFragment.this.f8065s.mo8753c(stickerObj);
            }
        }

        @Override // p086h4.C5015s.g
        /* renamed from: b */
        public void mo7980b(Emojicon emojicon) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$e */
    public class C1553e implements TextWatcher {
        public C1553e() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (MessageGeneratorFragment.this.f8065s != null) {
                MessageGeneratorFragment.this.f8065s.mo8751a(charSequence);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$f */
    public class C1554f implements VoiceRecordFragment.InterfaceC1861g {
        public C1554f() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: a */
        public void mo7976a() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: b */
        public void mo7977b(boolean z8) {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: c */
        public void mo7978c(String str, String str2) throws JSONException {
            Log.d("MsgGeneratorFragment", "Record Complete : path = " + str + " duration : " + str2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", str);
                jSONObject.put("duration", str2);
            } catch (Exception e9) {
                Log.d("MsgGeneratorFragment", "Prepare Voice Info Exception = " + e9.getMessage());
            }
            if (MessageGeneratorFragment.this.f8065s != null) {
                MessageGeneratorFragment.this.f8065s.mo8752b(jSONObject.toString());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.MessageGeneratorFragment$g */
    public interface InterfaceC1555g {
        /* renamed from: a */
        void mo8751a(CharSequence charSequence);

        /* renamed from: b */
        void mo8752b(String str);

        /* renamed from: c */
        void mo8753c(StickerObj stickerObj);

        /* renamed from: d */
        void mo8754d(PhotoType photoType, ArrayList<ImageItem> arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ boolean m8737q(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (this.f8055i.canScrollVertically(1) || this.f8055i.canScrollVertically(-1)) {
                this.f8055i.getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (motionEvent.getAction() == 1) {
            this.f8055i.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    /* renamed from: o */
    public Object m8738o() {
        if (m8739p().equals(InputType.Text)) {
            return this.f8055i.getText().toString();
        }
        if (m8739p().equals(InputType.Photo) || !m8739p().equals(InputType.Sticker)) {
            return null;
        }
        if (!(this.f8062p.m19496Q() instanceof C5018v) && !(this.f8062p.m19496Q() instanceof C4999d)) {
            return null;
        }
        Log.i("test", "getMessageContent");
        StickerObj stickerObjM19498S = this.f8062p.m19498S();
        new C5002f(getActivity()).m19426a(stickerObjM19498S);
        return stickerObjM19498S;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            Uri uriM16510Z1 = CLUtility.m16510Z1(bundle.getString("cameraUri"));
            f8047u = uriM16510Z1;
            m8740r(uriM16510Z1);
        }
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        ArrayList<ImageItem> arrayList;
        InterfaceC1555g interfaceC1555g;
        super.onActivityResult(i9, i10, intent);
        Iterator<Fragment> it = getChildFragmentManager().mo1852i().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i9, i10, intent);
        }
        if (i9 == 1) {
            if (i10 != -1 || (arrayList = (ArrayList) intent.getExtras().getSerializable("import_images")) == null || arrayList.isEmpty() || (interfaceC1555g = this.f8065s) == null) {
                return;
            }
            interfaceC1555g.mo8754d(PhotoType.Gallery, arrayList);
            return;
        }
        if (i9 == 0) {
            Globals.m7388i0().m7526b3(false);
            if (i10 == -1) {
                m8740r(f8047u);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_messge_generator, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Uri uri = f8047u;
        if (uri != null) {
            bundle.putString("cameraUri", uri.toString());
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Bundle arguments;
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.textMessage);
        this.f8051e = viewGroup;
        viewGroup.setOnClickListener(this.f8048b);
        ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R.id.photoMessage);
        this.f8052f = viewGroup2;
        viewGroup2.setOnClickListener(this.f8048b);
        ViewGroup viewGroup3 = (ViewGroup) view.findViewById(R.id.stickerMessage);
        this.f8053g = viewGroup3;
        viewGroup3.setOnClickListener(this.f8048b);
        ViewGroup viewGroup4 = (ViewGroup) view.findViewById(R.id.voiceMessage);
        this.f8054h = viewGroup4;
        viewGroup4.setOnClickListener(this.f8048b);
        Fragment fragmentMo1848e = getChildFragmentManager().mo1848e("voiceFragment");
        if (fragmentMo1848e != null) {
            this.f8063q = (VoiceRecordFragment) fragmentMo1848e;
        } else {
            this.f8063q = new VoiceRecordFragment();
            getChildFragmentManager().mo1844a().m1981c(R.id.voiceFragmentContainer, this.f8063q, "voiceFragment").mo1799n(this.f8063q).mo1794h();
        }
        this.f8063q.m11003X(this.f8066t);
        Fragment fragmentMo1848e2 = getChildFragmentManager().mo1848e("stickerFrag");
        if (fragmentMo1848e2 != null) {
            this.f8062p = (C5015s) fragmentMo1848e2;
        } else {
            this.f8062p = new C5015s();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("Emoji", false);
            bundle2.putBoolean("Shop", false);
            bundle2.putInt("Mode", 1);
            this.f8062p.setArguments(bundle2);
            getChildFragmentManager().mo1844a().m1981c(R.id.stickerFragmentContainer, this.f8062p, "stickerFrag").mo1799n(this.f8062p).mo1794h();
        }
        this.f8062p.m19511m0(new C1552d());
        if (bundle == null && (arguments = getArguments()) != null) {
            this.f8057k = arguments.getBoolean("isSelfDestruct", false);
        }
        EditText editText = (EditText) view.findViewById(R.id.textInput);
        this.f8055i = editText;
        editText.addTextChangedListener(new C1553e());
        this.f8055i.setText(this.f8056j);
        this.f8055i.setSelection(this.f8056j.length());
        this.f8058l = view.findViewById(R.id.textInputLayout);
        this.f8059m = view.findViewById(R.id.photoInputLayout);
        this.f8060n = view.findViewById(R.id.stickerFragmentContainer);
        this.f8061o = view.findViewById(R.id.voiceFragmentContainer);
        this.f8055i.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.m7
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f10885b.m8737q(view2, motionEvent);
            }
        });
        view.findViewById(R.id.cameraInput).setOnClickListener(this.f8049c);
        view.findViewById(R.id.galleryInput).setOnClickListener(this.f8050d);
        InputType inputType = InputType.Text;
        this.f8064r = inputType;
        m8743u(inputType);
    }

    /* renamed from: p */
    public InputType m8739p() {
        return this.f8064r;
    }

    /* renamed from: r */
    public final void m8740r(Uri uri) {
        if (this.f8065s == null || uri == null) {
            return;
        }
        try {
            ArrayList<ImageItem> arrayList = new ArrayList<>();
            arrayList.add(CLUtility.m16432E0(Globals.m7372O(), uri));
            this.f8065s.mo8754d(PhotoType.Camera, arrayList);
        } catch (Exception unused) {
            Log.e("MsgGeneratorFragment", "TODO: Handle Exception");
        }
    }

    /* renamed from: s */
    public void m8741s() {
        f8047u = null;
    }

    /* renamed from: t */
    public void m8742t(String str) {
        this.f8056j = str;
    }

    /* renamed from: u */
    public final void m8743u(InputType inputType) {
        if (inputType.equals(InputType.Text)) {
            m8745w(this.f8051e, true);
            m8745w(this.f8052f, false);
            m8745w(this.f8053g, false);
            m8745w(this.f8054h, false);
            this.f8058l.setVisibility(0);
            this.f8059m.setVisibility(8);
            this.f8060n.setVisibility(8);
            this.f8061o.setVisibility(8);
            getChildFragmentManager().mo1844a().mo1799n(this.f8062p).mo1794h();
            getChildFragmentManager().mo1844a().mo1799n(this.f8063q).mo1794h();
            InterfaceC1555g interfaceC1555g = this.f8065s;
            if (interfaceC1555g != null) {
                interfaceC1555g.mo8751a(this.f8055i.getText().toString());
                return;
            }
            return;
        }
        if (inputType.equals(InputType.Photo)) {
            m8745w(this.f8051e, false);
            m8745w(this.f8052f, true);
            m8745w(this.f8053g, false);
            m8745w(this.f8054h, false);
            this.f8058l.setVisibility(8);
            this.f8059m.setVisibility(0);
            this.f8060n.setVisibility(8);
            this.f8061o.setVisibility(8);
            getChildFragmentManager().mo1844a().mo1799n(this.f8062p).mo1794h();
            getChildFragmentManager().mo1844a().mo1799n(this.f8063q).mo1794h();
            InterfaceC1555g interfaceC1555g2 = this.f8065s;
            if (interfaceC1555g2 != null) {
                interfaceC1555g2.mo8754d(null, null);
                return;
            }
            return;
        }
        if (inputType.equals(InputType.Sticker)) {
            m8745w(this.f8051e, false);
            m8745w(this.f8052f, false);
            m8745w(this.f8053g, true);
            m8745w(this.f8054h, false);
            this.f8058l.setVisibility(8);
            this.f8059m.setVisibility(8);
            this.f8060n.setVisibility(0);
            this.f8061o.setVisibility(8);
            getChildFragmentManager().mo1844a().mo1802r(this.f8062p).mo1794h();
            getChildFragmentManager().mo1844a().mo1799n(this.f8063q).mo1794h();
            if (this.f8065s != null) {
                this.f8065s.mo8753c(this.f8062p.m19498S());
                return;
            }
            return;
        }
        if (inputType.equals(InputType.Voice)) {
            m8745w(this.f8051e, false);
            m8745w(this.f8052f, false);
            m8745w(this.f8053g, false);
            m8745w(this.f8054h, true);
            this.f8058l.setVisibility(8);
            this.f8059m.setVisibility(8);
            this.f8060n.setVisibility(8);
            this.f8061o.setVisibility(0);
            getChildFragmentManager().mo1844a().mo1799n(this.f8062p).mo1794h();
            getChildFragmentManager().mo1844a().mo1802r(this.f8063q).mo1794h();
            InterfaceC1555g interfaceC1555g3 = this.f8065s;
            if (interfaceC1555g3 != null) {
                interfaceC1555g3.mo8752b("");
            }
        }
    }

    /* renamed from: v */
    public void m8744v(InterfaceC1555g interfaceC1555g) {
        this.f8065s = interfaceC1555g;
    }

    /* renamed from: w */
    public final void m8745w(ViewGroup viewGroup, boolean z8) {
        for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
            viewGroup.getChildAt(i9).setSelected(z8);
        }
    }
}
