package p116k4;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.you.activity.WebViewActivity;
import java.util.Locale;

/* renamed from: k4.t */
/* loaded from: classes.dex */
public class C5182t {

    /* renamed from: k4.t$a */
    public class a extends ClickableSpan {

        /* renamed from: b */
        public final /* synthetic */ Context f17753b;

        /* renamed from: c */
        public final /* synthetic */ String f17754c;

        public a(Context context, String str) {
            this.f17753b = context;
            this.f17754c = str;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            WebViewActivity.m11035y(this.f17753b, this.f17754c);
        }
    }

    /* renamed from: a */
    public static String m20257a() {
        return "https://www.cyberlink.com/prog/ap/u/redirect.jsp?type=privacy_usite&locale=" + Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }

    /* renamed from: b */
    public static String m20258b() {
        return "https://www.cyberlink.com/prog/ap/u/redirect.jsp?type=terms_usite&locale=" + Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }

    /* renamed from: c */
    public static SpannableStringBuilder m20259c(Context context, String str) {
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) Html.fromHtml(str, 0);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class)) {
            int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
            String url = uRLSpan.getURL();
            spannableStringBuilder.removeSpan(uRLSpan);
            spannableStringBuilder.setSpan(new a(context, url), spanStart, spanEnd, 17);
        }
        return spannableStringBuilder;
    }

    /* renamed from: d */
    public static void m20260d(Context context, TextView textView, String str) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(m20259c(context, str));
    }
}
