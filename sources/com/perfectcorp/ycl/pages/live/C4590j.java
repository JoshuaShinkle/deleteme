package com.perfectcorp.ycl.pages.live;

import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.TextView;
import com.rockerhieu.emojicon.EmojiconEditText;
import p047d5.C4680d;

/* renamed from: com.perfectcorp.ycl.pages.live.j */
/* loaded from: classes2.dex */
public class C4590j {

    /* renamed from: a */
    public final View f16100a;

    /* renamed from: b */
    public final View f16101b;

    /* renamed from: c */
    public final EmojiconEditText f16102c;

    /* renamed from: d */
    public final View f16103d;

    /* renamed from: e */
    public boolean f16104e;

    /* renamed from: f */
    public int f16105f = 0;

    /* renamed from: com.perfectcorp.ycl.pages.live.j$a */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            C4590j.this.m18236y(editable.toString());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    public C4590j(View view) {
        this.f16100a = view;
        View viewFindViewById = view.findViewById(C4680d.message_type_btn);
        this.f16101b = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.perfectcorp.ycl.pages.live.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f16096b.mo13764q(view2);
                }
            });
        }
        View viewFindViewById2 = view.findViewById(C4680d.send_message_btn);
        this.f16103d = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.perfectcorp.ycl.pages.live.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f16097b.m18223l(view2);
            }
        });
        EmojiconEditText emojiconEditText = (EmojiconEditText) view.findViewById(C4680d.message_edit_text);
        this.f16102c = emojiconEditText;
        m18236y(emojiconEditText.getText().toString());
        emojiconEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.perfectcorp.ycl.pages.live.g
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
                return C4590j.m18224m(textView, i9, keyEvent);
            }
        });
        emojiconEditText.setOnTouchListener(new View.OnTouchListener() { // from class: com.perfectcorp.ycl.pages.live.h
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f16098b.m18225n(view2, motionEvent);
            }
        });
        emojiconEditText.addTextChangedListener(new a());
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.perfectcorp.ycl.pages.live.i
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f16099b.m18226o();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m18223l(View view) {
        mo18201r();
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m18224m(TextView textView, int i9, KeyEvent keyEvent) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ boolean m18225n(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        mo18200p();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m18226o() {
        Rect rect = new Rect();
        this.f16100a.getWindowVisibleDisplayFrame(rect);
        int height = this.f16100a.getRootView().getHeight();
        int i9 = height - rect.bottom;
        if (i9 > height * 0.15d) {
            mo18204u();
        } else {
            mo18202s();
            i9 = 0;
        }
        if (this.f16105f != i9) {
            this.f16105f = i9;
            mo18203t(i9);
        }
    }

    /* renamed from: f */
    public void m18227f() {
        this.f16102c.clearFocus();
    }

    /* renamed from: g */
    public void m18228g() {
        this.f16102c.setText("");
    }

    /* renamed from: h */
    public View m18229h() {
        return this.f16102c;
    }

    /* renamed from: i */
    public String m18230i() {
        return this.f16102c.getText().toString();
    }

    /* renamed from: j */
    public final boolean m18231j() {
        return this.f16104e;
    }

    /* renamed from: k */
    public final boolean m18232k() {
        return this.f16100a.getVisibility() == 0;
    }

    /* renamed from: p */
    public void mo18200p() {
        throw null;
    }

    /* renamed from: q */
    public void mo13764q(View view) {
        throw null;
    }

    /* renamed from: r */
    public void mo18201r() {
        throw null;
    }

    /* renamed from: s */
    public void mo18202s() {
        throw null;
    }

    /* renamed from: t */
    public void mo18203t(int i9) {
        throw null;
    }

    /* renamed from: u */
    public void mo18204u() {
        throw null;
    }

    /* renamed from: v */
    public void m18233v(boolean z8) {
        if (z8) {
            m18236y(this.f16102c.getText().toString());
        } else {
            this.f16103d.setEnabled(false);
        }
        this.f16101b.setEnabled(z8);
        this.f16102c.setEnabled(z8);
    }

    /* renamed from: w */
    public final void m18234w(int i9) {
        this.f16100a.setVisibility(i9);
    }

    /* renamed from: x */
    public void m18235x(Animation animation) {
        this.f16100a.startAnimation(animation);
    }

    /* renamed from: y */
    public final void m18236y(String str) {
        this.f16103d.setEnabled(!str.trim().isEmpty());
    }
}
