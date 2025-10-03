package com.cyberlink.you.widgetpool.tokenautocomplete;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView;

/* loaded from: classes.dex */
public class PeopleAndGroupCompleteView extends TokenCompleteTextView<Object> {
    public PeopleAndGroupCompleteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17389E();
    }

    /* renamed from: E */
    private void m17389E() {
        m17438t(false);
        setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select);
    }

    @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView
    /* renamed from: A */
    public boolean mo17390A(boolean z8) {
        boolean zMo17390A = super.mo17390A(z8);
        String strM17416B = m17416B(getText().toString());
        if (zMo17390A || !strM17416B.isEmpty()) {
            return zMo17390A;
        }
        m17392Z();
        return true;
    }

    @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView
    /* renamed from: C */
    public View mo17391C(Object obj) {
        FrameLayout frameLayout = (FrameLayout) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_people_complete_token, (ViewGroup) getParent(), false);
        if (obj instanceof Group) {
            ((TextView) frameLayout.findViewById(R.id.txt_token)).setText(((Group) obj).f13717d + ",");
        } else if (obj instanceof Friend) {
            ((TextView) frameLayout.findViewById(R.id.txt_token)).setText(((Friend) obj).m15621b() + ",");
        }
        return frameLayout;
    }

    /* renamed from: Z */
    public final void m17392Z() {
        Editable text = getText();
        TokenCompleteTextView.C3247c[] c3247cArr = (TokenCompleteTextView.C3247c[]) text.getSpans(0, text.length(), TokenCompleteTextView.C3247c.class);
        if (c3247cArr.length > 0) {
            c3247cArr[c3247cArr.length - 1].m17447c();
        }
    }
}
