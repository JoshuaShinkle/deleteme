package p080g8;

import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.C5685b;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import p090h8.C5034e;

/* renamed from: g8.b */
/* loaded from: classes.dex */
public class C4973b extends Element {

    /* renamed from: k */
    public final Elements f17073k;

    public C4973b(C5034e c5034e, String str, C5685b c5685b) {
        super(c5034e, str, c5685b);
        this.f17073k = new Elements();
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: G */
    public void mo19250G(AbstractC5690g abstractC5690g) {
        super.mo19250G(abstractC5690g);
        this.f17073k.remove(abstractC5690g);
    }

    /* renamed from: y0 */
    public C4973b m19251y0(Element element) {
        this.f17073k.add(element);
        return this;
    }
}
