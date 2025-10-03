package com.cyberlink.you.widgetpool.tokenautocomplete;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.QwertyKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.C3322C;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.database.SQLiteDatabase;
import p175q4.C6153a;
import p175q4.C6154b;
import p175q4.C6161i;

/* loaded from: classes.dex */
public abstract class TokenCompleteTextView<T> extends MultiAutoCompleteTextView implements TextView.OnEditorActionListener {

    /* renamed from: b */
    public char[] f15192b;

    /* renamed from: c */
    public MultiAutoCompleteTextView.Tokenizer f15193c;

    /* renamed from: d */
    public T f15194d;

    /* renamed from: e */
    public InterfaceC3249e f15195e;

    /* renamed from: f */
    public TokenCompleteTextView<T>.C3250f f15196f;

    /* renamed from: g */
    public TokenCompleteTextView<T>.C3251g f15197g;

    /* renamed from: h */
    public ArrayList<T> f15198h;

    /* renamed from: i */
    public List<TokenCompleteTextView<T>.C3247c> f15199i;

    /* renamed from: j */
    public TokenDeleteStyle f15200j;

    /* renamed from: k */
    public TokenClickStyle f15201k;

    /* renamed from: l */
    public String f15202l;

    /* renamed from: m */
    public boolean f15203m;

    /* renamed from: n */
    public Layout f15204n;

    /* renamed from: o */
    public boolean f15205o;

    /* renamed from: p */
    public boolean f15206p;

    /* renamed from: q */
    public boolean f15207q;

    /* renamed from: r */
    public boolean f15208r;

    /* renamed from: s */
    public boolean f15209s;

    /* renamed from: t */
    public int f15210t;

    /* renamed from: u */
    public boolean f15211u;

    public enum TokenClickStyle {
        None(false),
        Delete(false),
        Select(true),
        SelectDeselect(true);

        private boolean mIsSelectable;

        TokenClickStyle(boolean z8) {
            this.mIsSelectable = z8;
        }

        /* renamed from: a */
        public boolean m17445a() {
            return this.mIsSelectable;
        }
    }

    public enum TokenDeleteStyle {
        _Parent,
        Clear,
        PartialCompletion,
        ToString
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$a */
    public static /* synthetic */ class C3245a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f15222a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f15223b;

        static {
            int[] iArr = new int[TokenClickStyle.values().length];
            f15223b = iArr;
            try {
                iArr[TokenClickStyle.Select.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15223b[TokenClickStyle.SelectDeselect.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15223b[TokenClickStyle.Delete.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15223b[TokenClickStyle.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[TokenDeleteStyle.values().length];
            f15222a = iArr2;
            try {
                iArr2[TokenDeleteStyle.Clear.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15222a[TokenDeleteStyle.PartialCompletion.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15222a[TokenDeleteStyle.ToString.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f15222a[TokenDeleteStyle._Parent.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$b */
    public interface InterfaceC3246b {
        void onComplete();
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$c */
    public class C3247c extends C6161i {

        /* renamed from: d */
        public T f15224d;

        public C3247c(View view, T t8, int i9) {
            super(view, i9);
            this.f15224d = t8;
        }

        /* renamed from: b */
        public T m17446b() {
            return this.f15224d;
        }

        /* renamed from: c */
        public void m17447c() {
            Editable text = TokenCompleteTextView.this.getText();
            if (text == null) {
                return;
            }
            int i9 = C3245a.f15223b[TokenCompleteTextView.this.f15201k.ordinal()];
            if (i9 == 1 || i9 == 2) {
                if (!this.f20796b.isSelected()) {
                    TokenCompleteTextView.this.m17443y();
                    this.f20796b.setSelected(true);
                    CLUtility.m16606x2((Activity) TokenCompleteTextView.this.getContext());
                    return;
                } else if (TokenCompleteTextView.this.f15201k == TokenClickStyle.SelectDeselect) {
                    this.f20796b.setSelected(false);
                    TokenCompleteTextView.this.invalidate();
                    return;
                }
            } else if (i9 != 3) {
                if (TokenCompleteTextView.this.getSelectionStart() != text.getSpanEnd(this) + 1) {
                    TokenCompleteTextView.this.setSelection(text.getSpanEnd(this) + 1);
                    return;
                }
                return;
            }
            TokenCompleteTextView.this.m17428U(this);
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$d */
    public class C3248d extends InputConnectionWrapper {
        public C3248d(InputConnection inputConnection, boolean z8) {
            super(inputConnection, z8);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i9, int i10) {
            if (TokenCompleteTextView.this.getSelectionStart() <= TokenCompleteTextView.this.f15202l.length() && i9 > 0) {
                TokenCompleteTextView.this.m17425R();
                TokenCompleteTextView tokenCompleteTextView = TokenCompleteTextView.this;
                tokenCompleteTextView.setText(tokenCompleteTextView.f15202l);
                TokenCompleteTextView.this.m17433o();
                i9 = 0;
            }
            return TokenCompleteTextView.this.mo17390A(false) || super.deleteSurroundingText(i9, i10);
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$e */
    public interface InterfaceC3249e<T> {
        /* renamed from: a */
        void mo5801a(String str);

        /* renamed from: b */
        void mo5802b(T t8);

        /* renamed from: c */
        void mo5803c(T t8);
    }

    public TokenCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15192b = new char[]{',', ';'};
        this.f15200j = TokenDeleteStyle._Parent;
        this.f15201k = TokenClickStyle.None;
        this.f15202l = "";
        this.f15203m = false;
        this.f15204n = null;
        this.f15205o = true;
        this.f15206p = false;
        this.f15207q = true;
        this.f15208r = false;
        this.f15209s = true;
        this.f15210t = -1;
        this.f15211u = false;
        m17418E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: J */
    public /* synthetic */ void m17396J(Object obj, CharSequence charSequence) {
        if (obj == 0) {
            return;
        }
        if (this.f15205o || !this.f15198h.contains(obj)) {
            if (this.f15210t == -1 || this.f15198h.size() != this.f15210t) {
                m17419F(obj, charSequence, true);
                if (getText() == null || !isFocused()) {
                    return;
                }
                setSelection(getText().length());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K */
    public /* synthetic */ void m17397K(List list, InterfaceC3246b interfaceC3246b) {
        if ((this.f15210t != -1 && this.f15198h.size() == this.f15210t) || list.isEmpty()) {
            if (interfaceC3246b != null) {
                interfaceC3246b.onComplete();
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (this.f15205o || !this.f15198h.contains(obj)) {
                arrayList.add(obj);
            }
        }
        m17421H(arrayList, true);
        if (getText() != null && isFocused()) {
            setSelection(getText().length());
        }
        if (interfaceC3246b != null) {
            interfaceC3246b.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m17398L(InterfaceC3246b interfaceC3246b) {
        System.currentTimeMillis();
        Editable text = getText();
        if (text == null) {
            return;
        }
        m17430W((C3247c[]) text.getSpans(0, text.length(), C3247c.class));
        if (interfaceC3246b != null) {
            interfaceC3246b.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ CharSequence m17399M(CharSequence charSequence, int i9, int i10, Spanned spanned, int i11, int i12) {
        if (this.f15210t != -1 && this.f15198h.size() == this.f15210t) {
            return "";
        }
        if (charSequence.length() == 1 && m17422I(charSequence.charAt(0))) {
            performCompletion();
            return "";
        }
        if (i11 >= this.f15202l.length() || i12 != this.f15202l.length()) {
            return null;
        }
        return this.f15202l.substring(i11, i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m17400N(Editable editable) {
        setSelection(editable.length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m17401O(List list) {
        int i9;
        Editable text = getText();
        if (text == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (TokenCompleteTextView<T>.C3247c c3247c : this.f15199i) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (c3247c.m17446b().equals(it.next())) {
                    arrayList.add(c3247c);
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            C3247c c3247c2 = (C3247c) it2.next();
            this.f15199i.remove(c3247c2);
            this.f15196f.onSpanRemoved(text, c3247c2, 0, 0);
        }
        m17431X();
        TokenCompleteTextView<T>.C3247c[] c3247cArr = (C3247c[]) text.getSpans(0, text.length(), C3247c.class);
        ArrayList arrayList2 = new ArrayList();
        for (TokenCompleteTextView<T>.C3247c c3247c3 : c3247cArr) {
            Iterator it3 = list.iterator();
            while (true) {
                if (it3.hasNext()) {
                    if (c3247c3.m17446b().equals(it3.next())) {
                        arrayList2.add(c3247c3);
                        break;
                    }
                }
            }
        }
        m17429V(arrayList2);
    }

    /* renamed from: A */
    public boolean mo17390A(boolean z8) {
        Editable text;
        TokenClickStyle tokenClickStyle = this.f15201k;
        if (tokenClickStyle == null || !tokenClickStyle.m17445a() || (text = getText()) == null) {
            return z8;
        }
        for (TokenCompleteTextView<T>.C3247c c3247c : (C3247c[]) text.getSpans(0, text.length(), C3247c.class)) {
            if (c3247c.f20796b.isSelected()) {
                m17428U(c3247c);
                return true;
            }
        }
        return z8;
    }

    /* renamed from: B */
    public String m17416B(String str) {
        return str.replaceAll("[^:]*[:：](,, )*", "");
    }

    /* renamed from: C */
    public abstract View mo17391C(T t8);

    /* renamed from: D */
    public final void m17417D() {
        performCompletion();
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
    }

    /* renamed from: E */
    public final void m17418E() {
        if (this.f15206p) {
            return;
        }
        setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        this.f15198h = new ArrayList<>();
        getText();
        C3245a c3245a = null;
        this.f15196f = new C3250f(this, c3245a);
        this.f15197g = new C3251g(this, c3245a);
        this.f15199i = new ArrayList();
        m17433o();
        requestFocus();
        setTextIsSelectable(false);
        setLongClickable(false);
        setInputType(getInputType() | 524288 | C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
        setHorizontallyScrolling(false);
        setOnEditorActionListener(this);
        setFilters(new InputFilter[]{new InputFilter() { // from class: q4.d
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i9, int i10, Spanned spanned, int i11, int i12) {
                return this.f20785a.m17399M(charSequence, i9, i10, spanned, i11, i12);
            }
        }});
        setDeletionStyle(TokenDeleteStyle.Clear);
        this.f15206p = true;
    }

    /* renamed from: F */
    public final void m17419F(T t8, CharSequence charSequence, boolean z8) {
        m17420G(Collections.singletonList(t8), charSequence, z8);
    }

    /* renamed from: G */
    public final void m17420G(List<T> list, CharSequence charSequence, boolean z8) {
        Editable text = getText();
        if (text == null) {
            return;
        }
        String string = m17441w(charSequence).toString();
        int length = string.length();
        StringBuilder sb = new StringBuilder(list.size() * length);
        for (int i9 = 0; i9 < list.size(); i9++) {
            sb.append(string);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.toString());
        ArrayList<TokenCompleteTextView<T>.C3247c> arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(m17440v(it.next()));
        }
        if (this.f15209s && !isFocused() && !this.f15199i.isEmpty() && !z8) {
            for (TokenCompleteTextView<T>.C3247c c3247c : arrayList) {
                this.f15199i.add(c3247c);
                this.f15196f.onSpanAdded(text, c3247c, 0, 0);
            }
            m17431X();
            return;
        }
        int length2 = text.length();
        if (this.f15203m) {
            int length3 = this.f15202l.length();
            text.insert(length3, spannableStringBuilder);
            this.f15196f.f15227b = true;
            SpannableString spannableString = new SpannableString(text);
            this.f15196f.f15227b = false;
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                spannableString.setSpan((C3247c) it2.next(), length3, (spannableStringBuilder.length() + length3) - 1, 33);
                length3 += length;
            }
            m17425R();
            setText(spannableString);
            m17433o();
        } else {
            String strM17444z = m17444z();
            if (strM17444z != null && strM17444z.length() > 0) {
                length2 = TextUtils.indexOf(text, strM17444z, this.f15202l.length());
                Log.d("TokenAutoComplete", " [insertSpan] offset = " + length2);
            }
            text.delete(length2, text.length());
            text.insert(length2, spannableStringBuilder);
            this.f15196f.f15227b = true;
            SpannableString spannableString2 = new SpannableString(text);
            this.f15196f.f15227b = false;
            Log.d("TokenAutoComplete", " [insertSpan] editable length 1 = " + text.length());
            int i10 = 0;
            while (i10 < list.size()) {
                int i11 = length2 + length;
                spannableString2.setSpan((C3247c) arrayList.get(i10), length2, i11 - 1, 33);
                i10++;
                length2 = i11;
            }
            m17425R();
            setText(spannableString2);
            m17433o();
            Log.d("TokenAutoComplete", " [insertSpan] editable length 2 = " + text.length());
        }
        if (!isFocused() && this.f15209s && z8) {
            m17424Q(false);
        }
    }

    /* renamed from: H */
    public final void m17421H(List<T> list, boolean z8) {
        m17420G(list, "", z8);
    }

    /* renamed from: I */
    public final boolean m17422I(char c9) {
        for (char c10 : this.f15192b) {
            if (c9 == c10) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: P */
    public float m17423P() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    /* renamed from: Q */
    public void m17424Q(boolean z8) {
        Layout layout;
        this.f15196f.f15227b = true;
        if (z8) {
            final Editable text = getText();
            if (text != null) {
                for (C6154b c6154b : (C6154b[]) text.getSpans(0, text.length(), C6154b.class)) {
                    text.delete(text.getSpanStart(c6154b), text.getSpanEnd(c6154b));
                    text.removeSpan(c6154b);
                }
                for (TokenCompleteTextView<T>.C3247c c3247c : this.f15199i) {
                    m17419F(c3247c.m17446b(), c3247c.m17446b().toString(), false);
                }
                this.f15199i.clear();
                if (this.f15203m) {
                    setSelection(this.f15202l.length());
                } else {
                    postDelayed(new Runnable() { // from class: q4.c
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f20783b.m17400N(text);
                        }
                    }, 10L);
                }
                if (((C3250f[]) getText().getSpans(0, getText().length(), C3250f.class)).length == 0) {
                    text.setSpan(this.f15196f, 0, text.length(), 18);
                }
            }
        } else {
            Editable text2 = getText();
            if (text2 != null && (layout = this.f15204n) != null) {
                int lineVisibleEnd = layout.getLineVisibleEnd(0);
                C3247c[] c3247cArr = (C3247c[]) text2.getSpans(0, lineVisibleEnd, C3247c.class);
                int size = this.f15198h.size() - c3247cArr.length;
                C6154b[] c6154bArr = (C6154b[]) text2.getSpans(0, lineVisibleEnd, C6154b.class);
                if (size > 0 && c6154bArr.length == 0) {
                    int length = lineVisibleEnd + 1;
                    C6154b c6154b2 = new C6154b(size, getContext(), getCurrentTextColor(), (int) getTextSize(), (int) m17423P());
                    text2.insert(length, c6154b2.f20782d);
                    if (Layout.getDesiredWidth(text2, 0, c6154b2.f20782d.length() + length, this.f15204n.getPaint()) > m17423P()) {
                        text2.delete(length, c6154b2.f20782d.length() + length);
                        if (c3247cArr.length > 0) {
                            length = text2.getSpanStart(c3247cArr[c3247cArr.length - 1]);
                            c6154b2.m23612b(size + 1);
                        } else {
                            length = this.f15202l.length();
                        }
                        text2.insert(length, c6154b2.f20782d);
                    }
                    text2.setSpan(c6154b2, length, c6154b2.f20782d.length() + length, 33);
                    List<TokenCompleteTextView<T>.C3247c> arrayList = new ArrayList<>(Arrays.asList((C3247c[]) text2.getSpans(length + c6154b2.f20782d.length(), text2.length(), C3247c.class)));
                    this.f15199i = arrayList;
                    m17429V(arrayList);
                }
            }
        }
        this.f15196f.f15227b = false;
    }

    /* renamed from: R */
    public void m17425R() {
        Editable text = getText();
        if (text != null) {
            for (C3250f c3250f : (C3250f[]) text.getSpans(0, text.length(), C3250f.class)) {
                text.removeSpan(c3250f);
            }
            removeTextChangedListener(this.f15197g);
        }
    }

    /* renamed from: S */
    public void m17426S(T t8) {
        m17427T(Collections.singletonList(t8));
    }

    /* renamed from: T */
    public void m17427T(final List<T> list) {
        post(new Runnable() { // from class: q4.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f20794b.m17401O(list);
            }
        });
    }

    /* renamed from: U */
    public final void m17428U(TokenCompleteTextView<T>.C3247c c3247c) {
        TokenCompleteTextView<T>.C3247c[] c3247cArr = (C3247c[]) Array.newInstance((Class<?>) C3247c.class, 1);
        c3247cArr[0] = c3247c;
        m17430W(c3247cArr);
    }

    /* renamed from: V */
    public final void m17429V(List<TokenCompleteTextView<T>.C3247c> list) {
        TokenCompleteTextView<T>.C3247c[] c3247cArr = (C3247c[]) Array.newInstance((Class<?>) C3247c.class, list.size());
        for (int i9 = 0; i9 < list.size(); i9++) {
            c3247cArr[i9] = list.get(i9);
        }
        m17430W(c3247cArr);
    }

    /* renamed from: W */
    public final void m17430W(TokenCompleteTextView<T>.C3247c[] c3247cArr) {
        Editable text = getText();
        if (text == null || c3247cArr == null || c3247cArr.length == 0) {
            return;
        }
        String strSubstring = text.toString().substring(text.getSpanEnd(((C3247c[]) text.getSpans(0, text.length(), C3247c.class))[r1.length - 1]) + 1);
        for (TokenCompleteTextView<T>.C3247c c3247c : c3247cArr) {
            this.f15196f.onSpanRemoved(text, c3247c, text.getSpanStart(c3247c), text.getSpanEnd(c3247c));
        }
        ArrayList arrayList = new ArrayList(this.f15198h);
        setText(this.f15202l);
        m17432Y();
        m17421H(arrayList, false);
        getText().insert(getText().length(), strSubstring);
        setSelection(getText().length());
        if (!this.f15209s || isFocused()) {
            return;
        }
        m17431X();
    }

    /* renamed from: X */
    public final void m17431X() {
        Editable text = getText();
        C6154b[] c6154bArr = (C6154b[]) text.getSpans(0, text.length(), C6154b.class);
        int size = this.f15199i.size();
        for (C6154b c6154b : c6154bArr) {
            if (size == 0) {
                text.delete(text.getSpanStart(c6154b), text.getSpanEnd(c6154b));
                text.removeSpan(c6154b);
            } else {
                c6154b.m23612b(this.f15199i.size());
                text.setSpan(c6154b, text.getSpanStart(c6154b), text.getSpanEnd(c6154b), 33);
            }
        }
    }

    /* renamed from: Y */
    public final void m17432Y() {
        HintSpan hintSpan;
        Editable text = getText();
        CharSequence hint = getHint();
        if (hint == null) {
            hint = "";
        }
        if (text == null || "".contentEquals(hint) || this.f15202l.length() <= 0) {
            return;
        }
        HintSpan[] hintSpanArr = (HintSpan[]) text.getSpans(0, text.length(), HintSpan.class);
        int length = this.f15202l.length();
        if (hintSpanArr.length > 0) {
            hintSpan = hintSpanArr[0];
            length += text.getSpanEnd(hintSpan) - text.getSpanStart(hintSpan);
        } else {
            hintSpan = null;
        }
        if (text.length() != length) {
            if (hintSpan == null) {
                return;
            }
            int spanStart = text.getSpanStart(hintSpan);
            int spanEnd = text.getSpanEnd(hintSpan);
            text.removeSpan(hintSpan);
            text.replace(spanStart, spanEnd, "");
            this.f15203m = false;
            return;
        }
        this.f15203m = true;
        if (hintSpan != null) {
            return;
        }
        Typeface typeface = getTypeface();
        int style = typeface != null ? typeface.getStyle() : 0;
        ColorStateList hintTextColors = getHintTextColors();
        HintSpan hintSpan2 = new HintSpan(null, style, (int) getTextSize(), hintTextColors, hintTextColors);
        text.insert(this.f15202l.length(), hint);
        text.setSpan(hintSpan2, this.f15202l.length(), this.f15202l.length() + hint.length(), 33);
        setSelection(this.f15202l.length());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.AutoCompleteTextView
    public CharSequence convertSelectionToString(Object obj) {
        this.f15194d = obj;
        int i9 = C3245a.f15222a[this.f15200j.ordinal()];
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? super.convertSelectionToString(obj) : obj != 0 ? obj.toString() : "" : m17444z() : "";
    }

    @Override // android.widget.MultiAutoCompleteTextView, android.widget.AutoCompleteTextView
    public boolean enoughToFilter() {
        MultiAutoCompleteTextView.Tokenizer tokenizer;
        Editable text = getText();
        int selectionEnd = getSelectionEnd();
        if (selectionEnd < 0 || (tokenizer = this.f15193c) == null) {
            return false;
        }
        int iFindTokenStart = tokenizer.findTokenStart(text, selectionEnd);
        if (iFindTokenStart < this.f15202l.length()) {
            iFindTokenStart = this.f15202l.length();
        }
        return selectionEnd - iFindTokenStart >= Math.max(getThreshold(), 1);
    }

    @Override // android.widget.TextView
    public boolean extractText(ExtractedTextRequest extractedTextRequest, ExtractedText extractedText) {
        try {
            return super.extractText(extractedTextRequest, extractedText);
        } catch (IndexOutOfBoundsException e9) {
            Log.d("TokenAutoComplete", "extractText hit IndexOutOfBoundsException. This may be normal.", e9);
            return false;
        }
    }

    public String getFilterSearchText() {
        return m17416B(getText().toString());
    }

    public List<T> getObjects() {
        return this.f15198h;
    }

    @Override // android.view.View
    public void invalidate() {
        m17439u();
        super.invalidate();
    }

    /* renamed from: o */
    public void m17433o() {
        Editable text = getText();
        if (text != null) {
            text.setSpan(this.f15196f, 0, text.length(), 18);
            addTextChangedListener(this.f15197g);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        C3248d c3248d = new C3248d(super.onCreateInputConnection(editorInfo), true);
        editorInfo.imeOptions = (editorInfo.imeOptions & (-1073741825)) | SQLiteDatabase.CREATE_IF_NECESSARY;
        return c3248d;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 6) {
            return false;
        }
        m17417D();
        return true;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z8, int i9, Rect rect) {
        super.onFocusChanged(z8, i9, rect);
        if (!z8) {
            performCompletion();
        }
        if (this.f15209s) {
            m17424Q(z8);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0022  */
    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        boolean zMo17390A;
        if (i9 == 23 || i9 == 61 || i9 == 66) {
            if (keyEvent.hasNoModifiers()) {
                this.f15208r = true;
                zMo17390A = true;
            } else {
                zMo17390A = false;
            }
        } else if (i9 == 67) {
            zMo17390A = mo17390A(false);
        }
        return zMo17390A || super.onKeyDown(i9, keyEvent);
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        boolean zOnKeyUp = super.onKeyUp(i9, keyEvent);
        if (this.f15208r) {
            this.f15208r = false;
            m17417D();
        }
        return zOnKeyUp;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        this.f15204n = getLayout();
    }

    @Override // android.widget.TextView
    public void onSelectionChanged(int i9, int i10) {
        if (this.f15203m) {
            i9 = 0;
        }
        TokenClickStyle tokenClickStyle = this.f15201k;
        if (tokenClickStyle != null && tokenClickStyle.m17445a() && getText() != null) {
            m17443y();
        }
        String str = this.f15202l;
        if (str != null && i9 < str.length()) {
            try {
                setSelection(this.f15202l.length());
                return;
            } catch (IndexOutOfBoundsException e9) {
                e9.printStackTrace();
                Log.e("TokenAutoComplete", "There is IndexOutOfBoundsException: " + e9.getMessage());
                return;
            }
        }
        Editable text = getText();
        if (text != null) {
            for (C3247c c3247c : (C3247c[]) text.getSpans(i9, i9, C3247c.class)) {
                int spanEnd = text.getSpanEnd(c3247c);
                if (i9 <= spanEnd && text.getSpanStart(c3247c) < i9) {
                    if (spanEnd == text.length()) {
                        setSelection(spanEnd);
                        return;
                    } else {
                        setSelection(spanEnd + 1);
                        return;
                    }
                }
            }
        }
        super.onSelectionChanged(i9, i9);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int offsetForPosition;
        int actionMasked = motionEvent.getActionMasked();
        Editable text = getText();
        TokenClickStyle tokenClickStyle = this.f15201k;
        TokenClickStyle tokenClickStyle2 = TokenClickStyle.None;
        boolean zOnTouchEvent = tokenClickStyle == tokenClickStyle2 ? super.onTouchEvent(motionEvent) : false;
        if (isFocused() && text != null && this.f15204n != null && actionMasked == 1 && (offsetForPosition = getOffsetForPosition(motionEvent.getX(), motionEvent.getY())) != -1) {
            C3247c[] c3247cArr = (C3247c[]) text.getSpans(offsetForPosition, offsetForPosition, C3247c.class);
            if (c3247cArr.length > 0) {
                c3247cArr[0].m17447c();
                zOnTouchEvent = true;
            } else {
                m17443y();
            }
        }
        return (zOnTouchEvent || this.f15201k == tokenClickStyle2) ? zOnTouchEvent : super.onTouchEvent(motionEvent);
    }

    /* renamed from: p */
    public void m17434p(T t8) {
        m17435q(t8, "");
    }

    @Override // android.widget.AutoCompleteTextView
    public void performCompletion() {
        if (getListSelection() == -1 && enoughToFilter()) {
            return;
        }
        super.performCompletion();
    }

    @Override // android.widget.MultiAutoCompleteTextView
    public void performFiltering(CharSequence charSequence, int i9, int i10, int i11) {
        if (i9 < this.f15202l.length()) {
            i9 = this.f15202l.length();
        }
        Filter filter = getFilter();
        if (filter != null) {
            filter.filter(charSequence.subSequence(i9, i10), this);
        }
    }

    /* renamed from: q */
    public void m17435q(final T t8, final CharSequence charSequence) {
        post(new Runnable() { // from class: q4.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f20788b.m17396J(t8, charSequence);
            }
        });
    }

    /* renamed from: r */
    public void m17436r(final List<T> list, final InterfaceC3246b interfaceC3246b) {
        postDelayed(new Runnable() { // from class: q4.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f20791b.m17397K(list, interfaceC3246b);
            }
        }, interfaceC3246b != null ? 50 : 0);
    }

    @Override // android.widget.MultiAutoCompleteTextView, android.widget.AutoCompleteTextView
    public void replaceText(CharSequence charSequence) {
        clearComposingText();
        T t8 = this.f15194d;
        if (t8 == null || t8.toString().equals("")) {
            return;
        }
        SpannableStringBuilder spannableStringBuilderM17441w = m17441w(charSequence);
        TokenCompleteTextView<T>.C3247c c3247cM17440v = m17440v(this.f15194d);
        Editable text = getText();
        int selectionEnd = getSelectionEnd();
        int iFindTokenStart = this.f15193c.findTokenStart(text, selectionEnd);
        if (iFindTokenStart < this.f15202l.length()) {
            iFindTokenStart = this.f15202l.length();
        }
        String strSubstring = TextUtils.substring(text, iFindTokenStart, selectionEnd);
        if (text != null) {
            if (c3247cM17440v == null) {
                text.replace(iFindTokenStart, selectionEnd, "");
                return;
            }
            if (!this.f15205o && this.f15198h.contains(c3247cM17440v.m17446b())) {
                text.replace(iFindTokenStart, selectionEnd, "");
                return;
            }
            QwertyKeyListener.markAsReplaced(text, iFindTokenStart, selectionEnd, strSubstring);
            text.replace(iFindTokenStart, selectionEnd, spannableStringBuilderM17441w);
            text.setSpan(c3247cM17440v, iFindTokenStart, (spannableStringBuilderM17441w.length() + iFindTokenStart) - 1, 33);
        }
    }

    /* renamed from: s */
    public void m17437s(boolean z8) {
        this.f15209s = z8;
    }

    public void setDeletionStyle(TokenDeleteStyle tokenDeleteStyle) {
        this.f15200j = tokenDeleteStyle;
    }

    public void setPrefix(String str) {
        if (str == null) {
            str = "";
        }
        if (this.f15202l.equals(str)) {
            return;
        }
        String str2 = this.f15202l;
        this.f15202l = str;
        Editable text = getText();
        if (text != null) {
            if ("".equals(str2)) {
                text.insert(0, str);
            } else {
                text.replace(0, str2.length(), str);
            }
        }
        m17432Y();
    }

    public void setSplitChar(char[] cArr) {
        if (cArr[0] == ' ') {
            char[] cArr2 = new char[2];
            cArr2[0] = cArr.length > 1 ? cArr[1] : (char) 167;
            cArr2[1] = cArr[0];
            cArr = cArr2;
        }
        this.f15192b = cArr;
        setTokenizer(new C6153a(cArr));
    }

    public void setTokenClickStyle(TokenClickStyle tokenClickStyle) {
        this.f15201k = tokenClickStyle;
    }

    public void setTokenLimit(int i9) {
        this.f15210t = i9;
    }

    public void setTokenListener(InterfaceC3249e interfaceC3249e) {
        this.f15195e = interfaceC3249e;
    }

    @Override // android.widget.MultiAutoCompleteTextView
    public void setTokenizer(MultiAutoCompleteTextView.Tokenizer tokenizer) {
        super.setTokenizer(tokenizer);
        this.f15193c = tokenizer;
    }

    /* renamed from: t */
    public void m17438t(boolean z8) {
        this.f15205o = z8;
    }

    @TargetApi(16)
    /* renamed from: u */
    public final void m17439u() {
        if (!this.f15206p || this.f15211u) {
            return;
        }
        this.f15211u = true;
        setShadowLayer(getShadowRadius(), getShadowDx(), getShadowDy(), getShadowColor());
        this.f15211u = false;
    }

    /* renamed from: v */
    public TokenCompleteTextView<T>.C3247c m17440v(T t8) {
        if (t8 == null) {
            return null;
        }
        return new C3247c(mo17391C(t8), t8, (int) m17423P());
    }

    /* renamed from: w */
    public final SpannableStringBuilder m17441w(CharSequence charSequence) {
        return new SpannableStringBuilder(String.valueOf(this.f15192b[0]) + ((Object) this.f15193c.terminateToken(charSequence)));
    }

    /* renamed from: x */
    public void m17442x(final InterfaceC3246b interfaceC3246b) {
        postDelayed(new Runnable() { // from class: q4.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f20786b.m17398L(interfaceC3246b);
            }
        }, interfaceC3246b != null ? 50 : 0);
    }

    /* renamed from: y */
    public final void m17443y() {
        Editable text;
        TokenClickStyle tokenClickStyle = this.f15201k;
        if (tokenClickStyle == null || !tokenClickStyle.m17445a() || (text = getText()) == null) {
            return;
        }
        for (C3247c c3247c : (C3247c[]) text.getSpans(0, text.length(), C3247c.class)) {
            c3247c.f20796b.setSelected(false);
        }
        invalidate();
    }

    /* renamed from: z */
    public String m17444z() {
        if (this.f15203m) {
            return "";
        }
        Editable text = getText();
        int selectionEnd = getSelectionEnd();
        int iFindTokenStart = this.f15193c.findTokenStart(text, selectionEnd);
        if (iFindTokenStart < this.f15202l.length()) {
            iFindTokenStart = this.f15202l.length();
        }
        return TextUtils.substring(text, iFindTokenStart, selectionEnd);
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$f */
    public class C3250f implements SpanWatcher {

        /* renamed from: b */
        public boolean f15227b;

        public C3250f() {
            this.f15227b = false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.text.SpanWatcher
        public void onSpanAdded(Spannable spannable, Object obj, int i9, int i10) {
            if (!(obj instanceof C3247c) || this.f15227b) {
                return;
            }
            C3247c c3247c = (C3247c) obj;
            TokenCompleteTextView.this.f15198h.add(c3247c.m17446b());
            if (TokenCompleteTextView.this.f15195e != null) {
                TokenCompleteTextView.this.f15195e.mo5802b(c3247c.m17446b());
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanChanged(Spannable spannable, Object obj, int i9, int i10, int i11, int i12) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.text.SpanWatcher
        public void onSpanRemoved(Spannable spannable, Object obj, int i9, int i10) {
            if (!(obj instanceof C3247c) || this.f15227b) {
                return;
            }
            C3247c c3247c = (C3247c) obj;
            if (TokenCompleteTextView.this.f15198h.contains(c3247c.m17446b())) {
                TokenCompleteTextView.this.f15198h.remove(c3247c.m17446b());
                if (TokenCompleteTextView.this.f15195e != null) {
                    TokenCompleteTextView.this.f15195e.mo5803c(c3247c.m17446b());
                }
            }
        }

        public /* synthetic */ C3250f(TokenCompleteTextView tokenCompleteTextView, C3245a c3245a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView$g */
    public class C3251g implements TextWatcher {

        /* renamed from: b */
        public ArrayList<TokenCompleteTextView<T>.C3247c> f15229b;

        public C3251g() {
            this.f15229b = new ArrayList<>();
        }

        /* renamed from: a */
        public void m17448a(TokenCompleteTextView<T>.C3247c c3247c, Editable editable) {
            editable.removeSpan(c3247c);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            Iterator it = new ArrayList(this.f15229b).iterator();
            while (it.hasNext()) {
                TokenCompleteTextView<T>.C3247c c3247c = (C3247c) it.next();
                int spanStart = editable.getSpanStart(c3247c);
                int spanEnd = editable.getSpanEnd(c3247c);
                m17448a(c3247c, editable);
                int i9 = spanEnd - 1;
                if (i9 >= 0 && TokenCompleteTextView.this.m17422I(editable.charAt(i9))) {
                    editable.delete(i9, i9 + 1);
                }
                if (spanStart >= 0 && TokenCompleteTextView.this.m17422I(editable.charAt(spanStart))) {
                    editable.delete(spanStart, spanStart + 1);
                }
            }
            TokenCompleteTextView.this.m17443y();
            TokenCompleteTextView.this.m17432Y();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (i10 <= 0 || TokenCompleteTextView.this.getText() == null) {
                return;
            }
            Editable text = TokenCompleteTextView.this.getText();
            int i12 = i10 + i9;
            if (text.charAt(i9) == ' ') {
                i9--;
            }
            TokenCompleteTextView<T>.C3247c[] c3247cArr = (C3247c[]) text.getSpans(i9, i12, C3247c.class);
            this.f15229b = new ArrayList<>();
            for (TokenCompleteTextView<T>.C3247c c3247c : c3247cArr) {
                if (text.getSpanStart(c3247c) < i12 && i9 < text.getSpanEnd(c3247c)) {
                    this.f15229b.add(c3247c);
                }
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            String strM17416B = TokenCompleteTextView.this.m17416B(charSequence.toString());
            if (TokenCompleteTextView.this.f15195e != null) {
                TokenCompleteTextView.this.f15195e.mo5801a(strM17416B);
            }
        }

        public /* synthetic */ C3251g(TokenCompleteTextView tokenCompleteTextView, C3245a c3245a) {
            this();
        }
    }

    public void setSplitChar(char c9) {
        if (c9 == ' ') {
            setSplitChar(new char[]{167, c9});
        } else {
            setSplitChar(new char[]{c9});
        }
    }

    public TokenCompleteTextView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f15192b = new char[]{',', ';'};
        this.f15200j = TokenDeleteStyle._Parent;
        this.f15201k = TokenClickStyle.None;
        this.f15202l = "";
        this.f15203m = false;
        this.f15204n = null;
        this.f15205o = true;
        this.f15206p = false;
        this.f15207q = true;
        this.f15208r = false;
        this.f15209s = true;
        this.f15210t = -1;
        this.f15211u = false;
        m17418E();
    }
}
