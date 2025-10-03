package com.cyberlink.you.widgetpool.common;

import android.text.InputFilter;
import android.text.Spanned;
import com.cyberlink.p030U.R;
import p116k4.C5187v0;

/* renamed from: com.cyberlink.you.widgetpool.common.b */
/* loaded from: classes.dex */
public class C3242b extends InputFilter.LengthFilter {
    public C3242b(int i9) {
        super(i9);
    }

    @Override // android.text.InputFilter.LengthFilter, android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i9, int i10, Spanned spanned, int i11, int i12) {
        CharSequence charSequenceFilter = super.filter(charSequence, i9, i10, spanned, i11, i12);
        if (charSequenceFilter != null) {
            C5187v0.m20267c(R.string.editText_lenght_limit_notification);
        }
        return charSequenceFilter;
    }
}
