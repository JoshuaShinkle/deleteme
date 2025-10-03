package com.cyberlink.you.widgetpool.common;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import com.rockerhieu.emojicon.EmojiconEditText;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class EmojiconEditTextWithLengthFilter extends EmojiconEditText {

    /* renamed from: b */
    public int f15087b;

    /* renamed from: c */
    public ArrayList<InputFilter> f15088c;

    public EmojiconEditTextWithLengthFilter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15087b = -1;
        this.f15088c = new ArrayList<>();
        m17309b(attributeSet);
    }

    /* renamed from: a */
    public final int m17308a(InputFilter[] inputFilterArr) {
        int length = inputFilterArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (inputFilterArr[i9] instanceof InputFilter.LengthFilter) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public final void m17309b(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.maxLength}, 0, 0);
        try {
            setMaxLength(typedArrayObtainStyledAttributes.getInteger(0, 0));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* renamed from: c */
    public final void m17310c() throws Resources.NotFoundException {
        this.f15088c.add(new C3242b(this.f15087b));
        setFilters((InputFilter[]) this.f15088c.toArray(new InputFilter[0]));
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) throws Resources.NotFoundException {
        if (inputFilterArr.length > 0) {
            int iM17308a = m17308a(inputFilterArr);
            ArrayList<InputFilter> arrayList = new ArrayList<>(Arrays.asList(inputFilterArr));
            if (iM17308a < 0) {
                this.f15088c = arrayList;
                setMaxLength(0);
                return;
            }
            arrayList.remove(iM17308a);
            this.f15088c = arrayList;
            InputFilter inputFilter = inputFilterArr[iM17308a];
            if (!(inputFilter instanceof C3242b)) {
                setMaxLength(((InputFilter.LengthFilter) inputFilter).getMax());
                return;
            }
        } else {
            this.f15088c = new ArrayList<>();
            setMaxLength(0);
        }
        super.setFilters(inputFilterArr);
    }

    public void setMaxLength(int i9) throws Resources.NotFoundException {
        if (this.f15087b == i9) {
            return;
        }
        if (i9 <= 0) {
            i9 = getResources().getInteger(com.cyberlink.p030U.R.integer.max_input_length_default);
        }
        this.f15087b = i9;
        m17310c();
    }

    public EmojiconEditTextWithLengthFilter(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f15087b = -1;
        this.f15088c = new ArrayList<>();
        m17309b(attributeSet);
    }
}
