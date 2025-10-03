package p175q4;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.MultiAutoCompleteTextView;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* renamed from: q4.a */
/* loaded from: classes.dex */
public class C6153a implements MultiAutoCompleteTextView.Tokenizer {

    /* renamed from: a */
    public ArrayList<Character> f20781a;

    public C6153a(char[] cArr) {
        this.f20781a = new ArrayList<>(cArr.length);
        for (char c9 : cArr) {
            this.f20781a.add(Character.valueOf(c9));
        }
    }

    @Override // android.widget.MultiAutoCompleteTextView.Tokenizer
    public int findTokenEnd(CharSequence charSequence, int i9) {
        int length = charSequence.length();
        while (i9 < length) {
            if (this.f20781a.contains(Character.valueOf(charSequence.charAt(i9)))) {
                return i9;
            }
            i9++;
        }
        return length;
    }

    @Override // android.widget.MultiAutoCompleteTextView.Tokenizer
    public int findTokenStart(CharSequence charSequence, int i9) {
        int i10 = i9;
        while (i10 > 0 && !this.f20781a.contains(Character.valueOf(charSequence.charAt(i10 - 1)))) {
            i10--;
        }
        while (i10 < i9 && charSequence.charAt(i10) == ' ') {
            i10++;
        }
        return i10;
    }

    @Override // android.widget.MultiAutoCompleteTextView.Tokenizer
    public CharSequence terminateToken(CharSequence charSequence) {
        int length = charSequence.length();
        while (length > 0 && charSequence.charAt(length - 1) == ' ') {
            length--;
        }
        if (length > 0 && this.f20781a.contains(Character.valueOf(charSequence.charAt(length - 1)))) {
            return charSequence;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((this.f20781a.size() <= 1 || this.f20781a.get(0).charValue() != ' ') ? this.f20781a.get(0) : this.f20781a.get(1));
        sb.append(StringUtils.SPACE);
        String string = sb.toString();
        if (!(charSequence instanceof Spanned)) {
            return ((Object) charSequence) + string;
        }
        SpannableString spannableString = new SpannableString(((Object) charSequence) + string);
        TextUtils.copySpansFrom((Spanned) charSequence, 0, charSequence.length(), Object.class, spannableString, 0);
        return spannableString;
    }
}
