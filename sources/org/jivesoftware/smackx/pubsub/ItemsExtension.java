package org.jivesoftware.smackx.pubsub;

import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import p232w7.C6554g;

/* loaded from: classes.dex */
public class ItemsExtension extends C6554g {

    /* renamed from: c */
    public ItemsElementType f19838c;

    /* renamed from: d */
    public Boolean f19839d;

    /* renamed from: e */
    public List<? extends InterfaceC5595c> f19840e;

    public enum ItemsElementType {
        items(PubSubElementType.f19852j, "max_items"),
        retract(PubSubElementType.f19860r, "notify");

        private String att;
        private PubSubElementType elem;

        ItemsElementType(PubSubElementType pubSubElementType, String str) {
            this.elem = pubSubElementType;
            this.att = str;
        }

        /* renamed from: a */
        public String m22683a() {
            return this.att;
        }

        /* renamed from: b */
        public PubSubElementType m22684b() {
            return this.elem;
        }
    }

    public ItemsExtension(ItemsElementType itemsElementType, String str, List<? extends InterfaceC5595c> list) {
        super(itemsElementType.m22684b(), str);
        this.f19838c = itemsElementType;
        this.f19840e = list;
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        List<? extends InterfaceC5595c> list = this.f19840e;
        if (list == null || list.size() == 0) {
            return super.mo190a();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(mo191b());
        sb.append(" node='");
        sb.append(m25121c());
        if (this.f19839d != null) {
            sb.append("' ");
            sb.append(this.f19838c.m22683a());
            sb.append("='");
            sb.append(this.f19839d.equals(Boolean.TRUE) ? 1 : 0);
            sb.append("'>");
        } else {
            sb.append("'>");
            Iterator<? extends InterfaceC5595c> it = this.f19840e.iterator();
            while (it.hasNext()) {
                sb.append(it.next().mo190a());
            }
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // p232w7.C6554g
    public String toString() {
        return getClass().getName() + "Content [" + ((Object) mo190a()) + "]";
    }
}
