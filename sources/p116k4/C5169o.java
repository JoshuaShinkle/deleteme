package p116k4;

import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.rockerhieu.emojicon.EmojiconTextView;

/* renamed from: k4.o */
/* loaded from: classes.dex */
public class C5169o {
    /* renamed from: a */
    public static float m20156a(int i9) {
        return Globals.m7388i0().getResources().getDimensionPixelSize(i9);
    }

    /* renamed from: b */
    public static void m20157b(TextView textView) {
        int iM7519a0 = Globals.m7388i0().m7519a0();
        if (iM7519a0 == 0) {
            textView.setTextSize(12.0f);
        } else if (iM7519a0 == 1) {
            textView.setTextSize(14.0f);
        } else if (iM7519a0 == 2) {
            textView.setTextSize(20.0f);
        } else if (iM7519a0 != 3) {
            textView.setTextSize(m20156a(R.dimen.chat_comment_textfontsize));
        } else {
            textView.setTextSize(25.0f);
        }
        if (textView instanceof EmojiconTextView) {
            ((EmojiconTextView) textView).setEmojiconSize((int) textView.getTextSize());
        }
    }

    /* renamed from: c */
    public static void m20158c(TextView textView) {
        int iM7519a0 = Globals.m7388i0().m7519a0();
        if (iM7519a0 == 0) {
            textView.setTextSize(7.0f);
            return;
        }
        if (iM7519a0 == 1) {
            textView.setTextSize(9.0f);
            return;
        }
        if (iM7519a0 == 2) {
            textView.setTextSize(16.0f);
        } else if (iM7519a0 != 3) {
            textView.setTextSize(m20156a(R.dimen.chat_textfontsize));
        } else {
            textView.setTextSize(22.0f);
        }
    }

    /* renamed from: d */
    public static void m20159d(TextView textView, int i9) {
        m20160e(textView, i9, false);
    }

    /* renamed from: e */
    public static void m20160e(TextView textView, int i9, boolean z8) {
        if (i9 == 0) {
            textView.setTextSize(13.0f);
        } else if (i9 == 1) {
            textView.setTextSize(15.0f);
        } else if (i9 == 2) {
            textView.setTextSize(22.0f);
        } else if (i9 != 3) {
            textView.setTextSize(m20156a(R.dimen.chat_textfontsize));
        } else {
            textView.setTextSize(z8 ? 24.0f : 28.0f);
        }
        if (textView instanceof EmojiconTextView) {
            ((EmojiconTextView) textView).setEmojiconSize((int) textView.getTextSize());
        }
    }

    /* renamed from: f */
    public static void m20161f(TextView textView) {
        m20162g(textView, false);
    }

    /* renamed from: g */
    public static void m20162g(TextView textView, boolean z8) {
        m20160e(textView, Globals.m7388i0().m7519a0(), z8);
    }

    /* renamed from: h */
    public static void m20163h(TextView textView, int i9, boolean z8) {
        if (i9 == 0 || i9 == 1) {
            textView.setTextSize(8.0f);
        } else if (i9 == 2) {
            textView.setTextSize(11.0f);
        } else if (i9 != 3) {
            textView.setTextSize(m20156a(R.dimen.chat_tabfontsize));
        } else {
            textView.setTextSize(z8 ? 12.0f : 13.0f);
        }
        if (textView instanceof EmojiconTextView) {
            ((EmojiconTextView) textView).setEmojiconSize((int) textView.getTextSize());
        }
    }

    /* renamed from: i */
    public static void m20164i(TextView textView) {
        m20165j(textView, false);
    }

    /* renamed from: j */
    public static void m20165j(TextView textView, boolean z8) {
        m20163h(textView, Globals.m7388i0().m7549g0(), z8);
    }
}
