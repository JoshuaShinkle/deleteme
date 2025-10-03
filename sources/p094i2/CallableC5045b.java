package p094i2;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.utility.CLUtility;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import p050d8.C4686a;
import p190s1.C6250g;
import p207u0.ComponentCallbacks2C6355e;

/* renamed from: i2.b */
/* loaded from: classes.dex */
public class CallableC5045b implements Callable<C5047d> {

    /* renamed from: b */
    public final int f17413b = 10000;

    /* renamed from: c */
    public final String f17414c = "PreviewDataTask";

    /* renamed from: d */
    public final String f17415d;

    public CallableC5045b(String str) {
        this.f17415d = str;
    }

    /* renamed from: d */
    public static Bitmap m19701d(String str) throws ExecutionException, InterruptedException, TimeoutException {
        Bitmap bitmap;
        Bitmap bitmap2 = null;
        if (TextUtils.isEmpty(str) || str.endsWith(".svg")) {
            return null;
        }
        try {
            bitmap = ComponentCallbacks2C6355e.m24387u(Globals.m7388i0().getApplicationContext()).mo23575d().mo23570t(str).mo23566b(new C6250g().mo23526k(R.drawable.url_default_img)).m24428w().get(20000L, TimeUnit.SECONDS);
        } catch (Exception e9) {
            e = e9;
        }
        try {
            if (bitmap.getWidth() >= 150 && bitmap.getHeight() >= 150) {
                if (bitmap.getWidth() <= 1280 && bitmap.getHeight() <= 1280) {
                    return bitmap;
                }
                Matrix matrix = new Matrix();
                float fM16436F0 = CLUtility.m16436F0(bitmap.getWidth(), bitmap.getHeight(), 1280);
                matrix.postScale(fM16436F0, fM16436F0);
                try {
                    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                } catch (OutOfMemoryError unused) {
                    return null;
                }
            }
            return null;
        } catch (Exception e10) {
            e = e10;
            bitmap2 = bitmap;
            e.printStackTrace();
            return bitmap2;
        }
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C5047d call() {
        Thread.currentThread().setName("PreviewDataTask");
        return m19706f(false);
    }

    /* renamed from: b */
    public final Connection m19703b(String str, String str2) {
        if (str.contains("www.ptt.cc")) {
            return C4686a.m18743a(str).mo18921a(str2).mo18923c("over18", "1");
        }
        String country = LocaleList.getDefault().get(0).getCountry();
        String language = LocaleList.getDefault().get(0).getLanguage();
        return C4686a.m18743a(str).mo18922b(HttpHeaders.ACCEPT_LANGUAGE, (language + "-" + country) + "," + language + ";q=0.9,en-US;q=0.8,en;q=0.7").mo18921a(str2).mo18924d(10000);
    }

    /* renamed from: c */
    public final String m19704c(Document document) {
        String strM23151a = document.m22871s0("meta[property=og:description]").m23151a(FirebaseAnalytics.Param.CONTENT);
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[name=description]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[name=Description]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        return TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a)) ? document.m22871s0("meta[name=og:description]").m23151a(FirebaseAnalytics.Param.CONTENT) : strM23151a;
    }

    /* renamed from: e */
    public final String m19705e(Document document) {
        String strM23151a = document.m22871s0("meta[property=og:image]").m23151a(FirebaseAnalytics.Param.CONTENT);
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[name=item-image]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[itemprop=image]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("link[rel=apple-touch-icon]").m23151a("href");
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("link[rel=apple-touch-icon-precomposed]").m23151a("href");
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[name=msapplication-TileImage]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("link[rel=icon]").m23151a("href");
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("img[title=Costco_logo.png]").m23151a("src");
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("img[title=costco central main logo]").m23151a("src");
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("link[rel=shortcut icon]").m23151a("href");
        }
        return m19707g(strM23151a);
    }

    /* renamed from: f */
    public final C5047d m19706f(boolean z8) {
        C5047d c5047d = new C5047d(this.f17415d);
        c5047d.m19718j(new Date().getTime());
        try {
            Document document = z8 ? m19703b(this.f17415d, "Mozilla/5.0").get() : m19703b(this.f17415d, "facebookexternalhit/1.1").get();
            c5047d.m19719k(m19708h(document));
            c5047d.m19715g(m19704c(document));
            c5047d.m19717i(m19705e(document));
            c5047d.m19716h(m19701d(c5047d.m19711c()));
            C2950b0.m14927z().m14963c(c5047d);
            return c5047d;
        } catch (UnknownHostException unused) {
            C2950b0.m14927z().m14963c(c5047d);
            return c5047d;
        } catch (Exception e9) {
            Log.d("PreviewDataTask", e9.getMessage());
            return (z8 || !(e9 instanceof HttpStatusException)) ? c5047d : m19706f(true);
        }
    }

    /* renamed from: g */
    public final String m19707g(String str) {
        if (URLUtil.isNetworkUrl(str)) {
            return str;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return new URI(this.f17415d).resolve(str).toString();
            } catch (URISyntaxException e9) {
                e9.printStackTrace();
            }
        }
        return "";
    }

    /* renamed from: h */
    public final String m19708h(Document document) {
        String strM23151a = document.m22871s0("meta[property=og:title]").m23151a(FirebaseAnalytics.Param.CONTENT);
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22824C0();
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[name=title]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        if (TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a))) {
            strM23151a = document.m22871s0("meta[property=og:site_name]").m23151a(FirebaseAnalytics.Param.CONTENT);
        }
        return TextUtils.isEmpty(StringUtils.trimToEmpty(strM23151a)) ? document.m22871s0("meta[name=application-name]").m23151a(FirebaseAnalytics.Param.CONTENT) : strM23151a;
    }
}
