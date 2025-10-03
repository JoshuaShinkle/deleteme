package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.AbstractC5799a;
import org.jsoup.select.AbstractC5800b;
import org.jsoup.select.AbstractC5802d;
import org.jsoup.select.Selector;
import p060e8.C4771c;
import p060e8.C4772d;
import p070f8.C4794b;
import p090h8.C5035f;

/* renamed from: org.jsoup.select.c */
/* loaded from: classes.dex */
public class C5801c {

    /* renamed from: d */
    public static final String[] f20229d = {",", ">", "+", "~", StringUtils.SPACE};

    /* renamed from: e */
    public static final String[] f20230e = {"=", "!=", "^=", "$=", "*=", "~="};

    /* renamed from: f */
    public static final Pattern f20231f = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);

    /* renamed from: g */
    public static final Pattern f20232g = Pattern.compile("([+-])?(\\d+)");

    /* renamed from: a */
    public C5035f f20233a;

    /* renamed from: b */
    public String f20234b;

    /* renamed from: c */
    public List<AbstractC5800b> f20235c = new ArrayList();

    public C5801c(String str) {
        this.f20234b = str;
        this.f20233a = new C5035f(str);
    }

    /* renamed from: t */
    public static AbstractC5800b m23164t(String str) {
        try {
            return new C5801c(str).m23183s();
        } catch (IllegalArgumentException e9) {
            throw new Selector.SelectorParseException(e9.getMessage(), new Object[0]);
        }
    }

    /* renamed from: a */
    public final void m23165a() {
        this.f20235c.add(new AbstractC5800b.a());
    }

    /* renamed from: b */
    public final void m23166b() {
        C5035f c5035f = new C5035f(this.f20233a.m19663a('[', ']'));
        String strM19670h = c5035f.m19670h(f20230e);
        C4772d.m19002h(strM19670h);
        c5035f.m19671i();
        if (c5035f.m19672j()) {
            if (strM19670h.startsWith("^")) {
                this.f20235c.add(new AbstractC5800b.d(strM19670h.substring(1)));
                return;
            } else {
                this.f20235c.add(new AbstractC5800b.b(strM19670h));
                return;
            }
        }
        if (c5035f.m19673k("=")) {
            this.f20235c.add(new AbstractC5800b.e(strM19670h, c5035f.m19679q()));
            return;
        }
        if (c5035f.m19673k("!=")) {
            this.f20235c.add(new AbstractC5800b.i(strM19670h, c5035f.m19679q()));
            return;
        }
        if (c5035f.m19673k("^=")) {
            this.f20235c.add(new AbstractC5800b.j(strM19670h, c5035f.m19679q()));
            return;
        }
        if (c5035f.m19673k("$=")) {
            this.f20235c.add(new AbstractC5800b.g(strM19670h, c5035f.m19679q()));
        } else if (c5035f.m19673k("*=")) {
            this.f20235c.add(new AbstractC5800b.f(strM19670h, c5035f.m19679q()));
        } else {
            if (!c5035f.m19673k("~=")) {
                throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.f20234b, c5035f.m19679q());
            }
            this.f20235c.add(new AbstractC5800b.h(strM19670h, Pattern.compile(c5035f.m19679q())));
        }
    }

    /* renamed from: c */
    public final void m23167c() {
        String strM19667e = this.f20233a.m19667e();
        C4772d.m19002h(strM19667e);
        this.f20235c.add(new AbstractC5800b.k(strM19667e.trim()));
    }

    /* renamed from: d */
    public final void m23168d() {
        String strM19667e = this.f20233a.m19667e();
        C4772d.m19002h(strM19667e);
        this.f20235c.add(new AbstractC5800b.p(strM19667e));
    }

    /* renamed from: e */
    public final void m23169e() {
        String strM19668f = this.f20233a.m19668f();
        C4772d.m19002h(strM19668f);
        if (strM19668f.startsWith("*|")) {
            this.f20235c.add(new AbstractC5799a.b(new AbstractC5800b.j0(C4794b.m19031b(strM19668f)), new AbstractC5800b.k0(C4794b.m19031b(strM19668f.replace("*|", ":")))));
            return;
        }
        if (strM19668f.contains("|")) {
            strM19668f = strM19668f.replace("|", ":");
        }
        this.f20235c.add(new AbstractC5800b.j0(strM19668f.trim()));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b7  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m23170f(char c9) {
        AbstractC5800b aVar;
        AbstractC5800b abstractC5800b;
        boolean z8;
        AbstractC5800b aVar2;
        this.f20233a.m19671i();
        AbstractC5800b abstractC5800bM23164t = m23164t(m23172h());
        if (this.f20235c.size() == 1) {
            aVar = this.f20235c.get(0);
            if ((aVar instanceof AbstractC5799a.b) && c9 != ',') {
                z8 = true;
                abstractC5800b = aVar;
                aVar = ((AbstractC5799a.b) aVar).m23158c();
            }
            this.f20235c.clear();
            if (c9 != '>') {
                aVar2 = new AbstractC5799a.a(abstractC5800bM23164t, new AbstractC5802d.b(aVar));
            } else if (c9 == ' ') {
                aVar2 = new AbstractC5799a.a(abstractC5800bM23164t, new AbstractC5802d.e(aVar));
            } else if (c9 == '+') {
                aVar2 = new AbstractC5799a.a(abstractC5800bM23164t, new AbstractC5802d.c(aVar));
            } else if (c9 == '~') {
                aVar2 = new AbstractC5799a.a(abstractC5800bM23164t, new AbstractC5802d.f(aVar));
            } else {
                if (c9 != ',') {
                    throw new Selector.SelectorParseException("Unknown combinator: " + c9, new Object[0]);
                }
                if (aVar instanceof AbstractC5799a.b) {
                    AbstractC5799a.b bVar = (AbstractC5799a.b) aVar;
                    bVar.m23161e(abstractC5800bM23164t);
                    aVar2 = bVar;
                } else {
                    AbstractC5799a.b bVar2 = new AbstractC5799a.b();
                    bVar2.m23161e(aVar);
                    bVar2.m23161e(abstractC5800bM23164t);
                    aVar2 = bVar2;
                }
            }
            if (z8) {
                abstractC5800b = aVar2;
            } else {
                ((AbstractC5799a.b) abstractC5800b).m23157b(aVar2);
            }
            this.f20235c.add(abstractC5800b);
        }
        aVar = new AbstractC5799a.a(this.f20235c);
        abstractC5800b = aVar;
        z8 = false;
        this.f20235c.clear();
        if (c9 != '>') {
        }
        if (z8) {
        }
        this.f20235c.add(abstractC5800b);
    }

    /* renamed from: g */
    public final int m23171g() {
        String strTrim = this.f20233a.m19664b(")").trim();
        C4772d.m18999e(C4771c.m18985g(strTrim), "Index must be numeric");
        return Integer.parseInt(strTrim);
    }

    /* renamed from: h */
    public final String m23172h() {
        StringBuilder sb = new StringBuilder();
        while (!this.f20233a.m19672j()) {
            if (this.f20233a.m19674l("(")) {
                sb.append("(");
                sb.append(this.f20233a.m19663a('(', ')'));
                sb.append(")");
            } else if (this.f20233a.m19674l("[")) {
                sb.append("[");
                sb.append(this.f20233a.m19663a('[', ']'));
                sb.append("]");
            } else {
                if (this.f20233a.m19676n(f20229d)) {
                    break;
                }
                sb.append(this.f20233a.m19665c());
            }
        }
        return sb.toString();
    }

    /* renamed from: i */
    public final void m23173i(boolean z8) {
        this.f20233a.m19666d(z8 ? ":containsOwn" : ":contains");
        String strM19662s = C5035f.m19662s(this.f20233a.m19663a('(', ')'));
        C4772d.m19003i(strM19662s, ":contains(text) query must not be empty");
        if (z8) {
            this.f20235c.add(new AbstractC5800b.m(strM19662s));
        } else {
            this.f20235c.add(new AbstractC5800b.n(strM19662s));
        }
    }

    /* renamed from: j */
    public final void m23174j() {
        this.f20233a.m19666d(":containsData");
        String strM19662s = C5035f.m19662s(this.f20233a.m19663a('(', ')'));
        C4772d.m19003i(strM19662s, ":containsData(text) query must not be empty");
        this.f20235c.add(new AbstractC5800b.l(strM19662s));
    }

    /* renamed from: k */
    public final void m23175k(boolean z8, boolean z9) throws NumberFormatException {
        String strM19031b = C4794b.m19031b(this.f20233a.m19664b(")"));
        Matcher matcher = f20231f.matcher(strM19031b);
        Matcher matcher2 = f20232g.matcher(strM19031b);
        int i9 = 2;
        int i10 = 1;
        if (!"odd".equals(strM19031b)) {
            if ("even".equals(strM19031b)) {
                i10 = 0;
            } else if (matcher.matches()) {
                int i11 = matcher.group(3) != null ? Integer.parseInt(matcher.group(1).replaceFirst("^\\+", "")) : 1;
                i10 = matcher.group(4) != null ? Integer.parseInt(matcher.group(4).replaceFirst("^\\+", "")) : 0;
                i9 = i11;
            } else {
                if (!matcher2.matches()) {
                    throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", strM19031b);
                }
                i10 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
                i9 = 0;
            }
        }
        if (z9) {
            if (z8) {
                this.f20235c.add(new AbstractC5800b.b0(i9, i10));
                return;
            } else {
                this.f20235c.add(new AbstractC5800b.c0(i9, i10));
                return;
            }
        }
        if (z8) {
            this.f20235c.add(new AbstractC5800b.a0(i9, i10));
        } else {
            this.f20235c.add(new AbstractC5800b.z(i9, i10));
        }
    }

    /* renamed from: l */
    public final void m23176l() throws NumberFormatException {
        if (this.f20233a.m19673k("#")) {
            m23168d();
            return;
        }
        if (this.f20233a.m19673k(".")) {
            m23167c();
            return;
        }
        if (this.f20233a.m19678p() || this.f20233a.m19674l("*|")) {
            m23169e();
            return;
        }
        if (this.f20233a.m19674l("[")) {
            m23166b();
            return;
        }
        if (this.f20233a.m19673k("*")) {
            m23165a();
            return;
        }
        if (this.f20233a.m19673k(":lt(")) {
            m23180p();
            return;
        }
        if (this.f20233a.m19673k(":gt(")) {
            m23179o();
            return;
        }
        if (this.f20233a.m19673k(":eq(")) {
            m23178n();
            return;
        }
        if (this.f20233a.m19674l(":has(")) {
            m23177m();
            return;
        }
        if (this.f20233a.m19674l(":contains(")) {
            m23173i(false);
            return;
        }
        if (this.f20233a.m19674l(":containsOwn(")) {
            m23173i(true);
            return;
        }
        if (this.f20233a.m19674l(":containsData(")) {
            m23174j();
            return;
        }
        if (this.f20233a.m19674l(":matches(")) {
            m23181q(false);
            return;
        }
        if (this.f20233a.m19674l(":matchesOwn(")) {
            m23181q(true);
            return;
        }
        if (this.f20233a.m19674l(":not(")) {
            m23182r();
            return;
        }
        if (this.f20233a.m19673k(":nth-child(")) {
            m23175k(false, false);
            return;
        }
        if (this.f20233a.m19673k(":nth-last-child(")) {
            m23175k(true, false);
            return;
        }
        if (this.f20233a.m19673k(":nth-of-type(")) {
            m23175k(false, true);
            return;
        }
        if (this.f20233a.m19673k(":nth-last-of-type(")) {
            m23175k(true, true);
            return;
        }
        if (this.f20233a.m19673k(":first-child")) {
            this.f20235c.add(new AbstractC5800b.v());
            return;
        }
        if (this.f20233a.m19673k(":last-child")) {
            this.f20235c.add(new AbstractC5800b.x());
            return;
        }
        if (this.f20233a.m19673k(":first-of-type")) {
            this.f20235c.add(new AbstractC5800b.w());
            return;
        }
        if (this.f20233a.m19673k(":last-of-type")) {
            this.f20235c.add(new AbstractC5800b.y());
            return;
        }
        if (this.f20233a.m19673k(":only-child")) {
            this.f20235c.add(new AbstractC5800b.d0());
            return;
        }
        if (this.f20233a.m19673k(":only-of-type")) {
            this.f20235c.add(new AbstractC5800b.e0());
            return;
        }
        if (this.f20233a.m19673k(":empty")) {
            this.f20235c.add(new AbstractC5800b.u());
        } else if (this.f20233a.m19673k(":root")) {
            this.f20235c.add(new AbstractC5800b.f0());
        } else {
            if (!this.f20233a.m19673k(":matchText")) {
                throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.f20234b, this.f20233a.m19679q());
            }
            this.f20235c.add(new AbstractC5800b.g0());
        }
    }

    /* renamed from: m */
    public final void m23177m() {
        this.f20233a.m19666d(":has");
        String strM19663a = this.f20233a.m19663a('(', ')');
        C4772d.m19003i(strM19663a, ":has(el) subselect must not be empty");
        this.f20235c.add(new AbstractC5802d.a(m23164t(strM19663a)));
    }

    /* renamed from: n */
    public final void m23178n() {
        this.f20235c.add(new AbstractC5800b.q(m23171g()));
    }

    /* renamed from: o */
    public final void m23179o() {
        this.f20235c.add(new AbstractC5800b.s(m23171g()));
    }

    /* renamed from: p */
    public final void m23180p() {
        this.f20235c.add(new AbstractC5800b.t(m23171g()));
    }

    /* renamed from: q */
    public final void m23181q(boolean z8) {
        this.f20233a.m19666d(z8 ? ":matchesOwn" : ":matches");
        String strM19663a = this.f20233a.m19663a('(', ')');
        C4772d.m19003i(strM19663a, ":matches(regex) query must not be empty");
        if (z8) {
            this.f20235c.add(new AbstractC5800b.i0(Pattern.compile(strM19663a)));
        } else {
            this.f20235c.add(new AbstractC5800b.h0(Pattern.compile(strM19663a)));
        }
    }

    /* renamed from: r */
    public final void m23182r() {
        this.f20233a.m19666d(":not");
        String strM19663a = this.f20233a.m19663a('(', ')');
        C4772d.m19003i(strM19663a, ":not(selector) subselect must not be empty");
        this.f20235c.add(new AbstractC5802d.d(m23164t(strM19663a)));
    }

    /* renamed from: s */
    public AbstractC5800b m23183s() throws NumberFormatException {
        this.f20233a.m19671i();
        if (this.f20233a.m19676n(f20229d)) {
            this.f20235c.add(new AbstractC5802d.g());
            m23170f(this.f20233a.m19665c());
        } else {
            m23176l();
        }
        while (!this.f20233a.m19672j()) {
            boolean zM19671i = this.f20233a.m19671i();
            if (this.f20233a.m19676n(f20229d)) {
                m23170f(this.f20233a.m19665c());
            } else if (zM19671i) {
                m23170f(' ');
            } else {
                m23176l();
            }
        }
        return this.f20235c.size() == 1 ? this.f20235c.get(0) : new AbstractC5799a.a(this.f20235c);
    }
}
