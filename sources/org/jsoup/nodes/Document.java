package org.jsoup.nodes;

import com.google.android.gms.plus.PlusShare;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.jsoup.nodes.Entities;
import p060e8.C4771c;
import p090h8.C5032c;
import p090h8.C5034e;

/* loaded from: classes.dex */
public class Document extends Element {

    /* renamed from: k */
    public OutputSettings f19961k;

    /* renamed from: l */
    public QuirksMode f19962l;

    /* renamed from: m */
    public String f19963m;

    /* renamed from: n */
    public boolean f19964n;

    public static class OutputSettings implements Cloneable {

        /* renamed from: c */
        public Charset f19966c;

        /* renamed from: e */
        public Entities.CoreCharset f19968e;

        /* renamed from: b */
        public Entities.EscapeMode f19965b = Entities.EscapeMode.base;

        /* renamed from: d */
        public ThreadLocal<CharsetEncoder> f19967d = new ThreadLocal<>();

        /* renamed from: f */
        public boolean f19969f = true;

        /* renamed from: g */
        public boolean f19970g = false;

        /* renamed from: h */
        public int f19971h = 1;

        /* renamed from: i */
        public Syntax f19972i = Syntax.html;

        public enum Syntax {
            html,
            xml
        }

        public OutputSettings() {
            m22833c(Charset.forName("UTF8"));
        }

        /* renamed from: a */
        public Charset m22831a() {
            return this.f19966c;
        }

        /* renamed from: b */
        public OutputSettings m22832b(String str) {
            m22833c(Charset.forName(str));
            return this;
        }

        /* renamed from: c */
        public OutputSettings m22833c(Charset charset) {
            this.f19966c = charset;
            return this;
        }

        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public OutputSettings clone() {
            try {
                OutputSettings outputSettings = (OutputSettings) super.clone();
                outputSettings.m22832b(this.f19966c.name());
                outputSettings.f19965b = Entities.EscapeMode.valueOf(this.f19965b.name());
                return outputSettings;
            } catch (CloneNotSupportedException e9) {
                throw new RuntimeException(e9);
            }
        }

        /* renamed from: e */
        public CharsetEncoder m22835e() {
            CharsetEncoder charsetEncoder = this.f19967d.get();
            return charsetEncoder != null ? charsetEncoder : m22839j();
        }

        /* renamed from: g */
        public Entities.EscapeMode m22836g() {
            return this.f19965b;
        }

        /* renamed from: h */
        public int m22837h() {
            return this.f19971h;
        }

        /* renamed from: i */
        public boolean m22838i() {
            return this.f19970g;
        }

        /* renamed from: j */
        public CharsetEncoder m22839j() {
            CharsetEncoder charsetEncoderNewEncoder = this.f19966c.newEncoder();
            this.f19967d.set(charsetEncoderNewEncoder);
            this.f19968e = Entities.CoreCharset.m22887a(charsetEncoderNewEncoder.charset().name());
            return charsetEncoderNewEncoder;
        }

        /* renamed from: k */
        public boolean m22840k() {
            return this.f19969f;
        }

        /* renamed from: l */
        public Syntax m22841l() {
            return this.f19972i;
        }

        /* renamed from: m */
        public OutputSettings m22842m(Syntax syntax) {
            this.f19972i = syntax;
            return this;
        }
    }

    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks
    }

    public Document(String str) {
        super(C5034e.m19652l("#root", C5032c.f17363c), str);
        this.f19961k = new OutputSettings();
        this.f19962l = QuirksMode.noQuirks;
        this.f19964n = false;
        this.f19963m = str;
    }

    /* renamed from: A0 */
    public QuirksMode m22822A0() {
        return this.f19962l;
    }

    /* renamed from: B0 */
    public Document m22823B0(QuirksMode quirksMode) {
        this.f19962l = quirksMode;
        return this;
    }

    /* renamed from: C0 */
    public String m22824C0() {
        Element elementM23153c = m22860g0(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE).m23153c();
        return elementM23153c != null ? C4771c.m18989k(elementM23153c.m22876w0()).trim() : "";
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.AbstractC5690g
    /* renamed from: v */
    public String mo22827v() {
        return "#document";
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: x */
    public String mo22828x() {
        return super.m22862i0();
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.AbstractC5690g
    /* renamed from: y0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Document mo22826l() {
        Document document = (Document) super.mo22826l();
        document.f19961k = this.f19961k.clone();
        return document;
    }

    /* renamed from: z0 */
    public OutputSettings m22830z0() {
        return this.f19961k;
    }
}
