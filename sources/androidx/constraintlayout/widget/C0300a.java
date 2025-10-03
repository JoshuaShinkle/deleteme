package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;
import p161p.C5866a;
import p161p.C5867b;

/* renamed from: androidx.constraintlayout.widget.a */
/* loaded from: classes.dex */
public class C0300a {

    /* renamed from: b */
    public static final int[] f1641b = {0, 4, 8};

    /* renamed from: c */
    public static SparseIntArray f1642c;

    /* renamed from: a */
    public HashMap<Integer, b> f1643a = new HashMap<>();

    /* renamed from: androidx.constraintlayout.widget.a$b */
    public static class b {

        /* renamed from: A */
        public int f1644A;

        /* renamed from: B */
        public int f1645B;

        /* renamed from: C */
        public int f1646C;

        /* renamed from: D */
        public int f1647D;

        /* renamed from: E */
        public int f1648E;

        /* renamed from: F */
        public int f1649F;

        /* renamed from: G */
        public int f1650G;

        /* renamed from: H */
        public int f1651H;

        /* renamed from: I */
        public int f1652I;

        /* renamed from: J */
        public int f1653J;

        /* renamed from: K */
        public int f1654K;

        /* renamed from: L */
        public int f1655L;

        /* renamed from: M */
        public int f1656M;

        /* renamed from: N */
        public int f1657N;

        /* renamed from: O */
        public int f1658O;

        /* renamed from: P */
        public int f1659P;

        /* renamed from: Q */
        public float f1660Q;

        /* renamed from: R */
        public float f1661R;

        /* renamed from: S */
        public int f1662S;

        /* renamed from: T */
        public int f1663T;

        /* renamed from: U */
        public float f1664U;

        /* renamed from: V */
        public boolean f1665V;

        /* renamed from: W */
        public float f1666W;

        /* renamed from: X */
        public float f1667X;

        /* renamed from: Y */
        public float f1668Y;

        /* renamed from: Z */
        public float f1669Z;

        /* renamed from: a */
        public boolean f1670a;

        /* renamed from: a0 */
        public float f1671a0;

        /* renamed from: b */
        public int f1672b;

        /* renamed from: b0 */
        public float f1673b0;

        /* renamed from: c */
        public int f1674c;

        /* renamed from: c0 */
        public float f1675c0;

        /* renamed from: d */
        public int f1676d;

        /* renamed from: d0 */
        public float f1677d0;

        /* renamed from: e */
        public int f1678e;

        /* renamed from: e0 */
        public float f1679e0;

        /* renamed from: f */
        public int f1680f;

        /* renamed from: f0 */
        public float f1681f0;

        /* renamed from: g */
        public float f1682g;

        /* renamed from: g0 */
        public float f1683g0;

        /* renamed from: h */
        public int f1684h;

        /* renamed from: h0 */
        public boolean f1685h0;

        /* renamed from: i */
        public int f1686i;

        /* renamed from: i0 */
        public boolean f1687i0;

        /* renamed from: j */
        public int f1688j;

        /* renamed from: j0 */
        public int f1689j0;

        /* renamed from: k */
        public int f1690k;

        /* renamed from: k0 */
        public int f1691k0;

        /* renamed from: l */
        public int f1692l;

        /* renamed from: l0 */
        public int f1693l0;

        /* renamed from: m */
        public int f1694m;

        /* renamed from: m0 */
        public int f1695m0;

        /* renamed from: n */
        public int f1696n;

        /* renamed from: n0 */
        public int f1697n0;

        /* renamed from: o */
        public int f1698o;

        /* renamed from: o0 */
        public int f1699o0;

        /* renamed from: p */
        public int f1700p;

        /* renamed from: p0 */
        public float f1701p0;

        /* renamed from: q */
        public int f1702q;

        /* renamed from: q0 */
        public float f1703q0;

        /* renamed from: r */
        public int f1704r;

        /* renamed from: r0 */
        public boolean f1705r0;

        /* renamed from: s */
        public int f1706s;

        /* renamed from: s0 */
        public int f1707s0;

        /* renamed from: t */
        public int f1708t;

        /* renamed from: t0 */
        public int f1709t0;

        /* renamed from: u */
        public float f1710u;

        /* renamed from: u0 */
        public int[] f1711u0;

        /* renamed from: v */
        public float f1712v;

        /* renamed from: v0 */
        public String f1713v0;

        /* renamed from: w */
        public String f1714w;

        /* renamed from: x */
        public int f1715x;

        /* renamed from: y */
        public int f1716y;

        /* renamed from: z */
        public float f1717z;

        public b() {
            this.f1670a = false;
            this.f1678e = -1;
            this.f1680f = -1;
            this.f1682g = -1.0f;
            this.f1684h = -1;
            this.f1686i = -1;
            this.f1688j = -1;
            this.f1690k = -1;
            this.f1692l = -1;
            this.f1694m = -1;
            this.f1696n = -1;
            this.f1698o = -1;
            this.f1700p = -1;
            this.f1702q = -1;
            this.f1704r = -1;
            this.f1706s = -1;
            this.f1708t = -1;
            this.f1710u = 0.5f;
            this.f1712v = 0.5f;
            this.f1714w = null;
            this.f1715x = -1;
            this.f1716y = 0;
            this.f1717z = BitmapDescriptorFactory.HUE_RED;
            this.f1644A = -1;
            this.f1645B = -1;
            this.f1646C = -1;
            this.f1647D = -1;
            this.f1648E = -1;
            this.f1649F = -1;
            this.f1650G = -1;
            this.f1651H = -1;
            this.f1652I = -1;
            this.f1653J = 0;
            this.f1654K = -1;
            this.f1655L = -1;
            this.f1656M = -1;
            this.f1657N = -1;
            this.f1658O = -1;
            this.f1659P = -1;
            this.f1660Q = BitmapDescriptorFactory.HUE_RED;
            this.f1661R = BitmapDescriptorFactory.HUE_RED;
            this.f1662S = 0;
            this.f1663T = 0;
            this.f1664U = 1.0f;
            this.f1665V = false;
            this.f1666W = BitmapDescriptorFactory.HUE_RED;
            this.f1667X = BitmapDescriptorFactory.HUE_RED;
            this.f1668Y = BitmapDescriptorFactory.HUE_RED;
            this.f1669Z = BitmapDescriptorFactory.HUE_RED;
            this.f1671a0 = 1.0f;
            this.f1673b0 = 1.0f;
            this.f1675c0 = Float.NaN;
            this.f1677d0 = Float.NaN;
            this.f1679e0 = BitmapDescriptorFactory.HUE_RED;
            this.f1681f0 = BitmapDescriptorFactory.HUE_RED;
            this.f1683g0 = BitmapDescriptorFactory.HUE_RED;
            this.f1685h0 = false;
            this.f1687i0 = false;
            this.f1689j0 = 0;
            this.f1691k0 = 0;
            this.f1693l0 = -1;
            this.f1695m0 = -1;
            this.f1697n0 = -1;
            this.f1699o0 = -1;
            this.f1701p0 = 1.0f;
            this.f1703q0 = 1.0f;
            this.f1705r0 = false;
            this.f1707s0 = -1;
            this.f1709t0 = -1;
        }

        /* renamed from: c */
        public void m1426c(ConstraintLayout.C0298a c0298a) {
            c0298a.f1590d = this.f1684h;
            c0298a.f1592e = this.f1686i;
            c0298a.f1594f = this.f1688j;
            c0298a.f1596g = this.f1690k;
            c0298a.f1598h = this.f1692l;
            c0298a.f1600i = this.f1694m;
            c0298a.f1602j = this.f1696n;
            c0298a.f1604k = this.f1698o;
            c0298a.f1606l = this.f1700p;
            c0298a.f1612p = this.f1702q;
            c0298a.f1613q = this.f1704r;
            c0298a.f1614r = this.f1706s;
            c0298a.f1615s = this.f1708t;
            ((ViewGroup.MarginLayoutParams) c0298a).leftMargin = this.f1647D;
            ((ViewGroup.MarginLayoutParams) c0298a).rightMargin = this.f1648E;
            ((ViewGroup.MarginLayoutParams) c0298a).topMargin = this.f1649F;
            ((ViewGroup.MarginLayoutParams) c0298a).bottomMargin = this.f1650G;
            c0298a.f1620x = this.f1659P;
            c0298a.f1621y = this.f1658O;
            c0298a.f1622z = this.f1710u;
            c0298a.f1558A = this.f1712v;
            c0298a.f1608m = this.f1715x;
            c0298a.f1610n = this.f1716y;
            c0298a.f1611o = this.f1717z;
            c0298a.f1559B = this.f1714w;
            c0298a.f1574Q = this.f1644A;
            c0298a.f1575R = this.f1645B;
            c0298a.f1563F = this.f1660Q;
            c0298a.f1562E = this.f1661R;
            c0298a.f1565H = this.f1663T;
            c0298a.f1564G = this.f1662S;
            c0298a.f1577T = this.f1685h0;
            c0298a.f1578U = this.f1687i0;
            c0298a.f1566I = this.f1689j0;
            c0298a.f1567J = this.f1691k0;
            c0298a.f1570M = this.f1693l0;
            c0298a.f1571N = this.f1695m0;
            c0298a.f1568K = this.f1697n0;
            c0298a.f1569L = this.f1699o0;
            c0298a.f1572O = this.f1701p0;
            c0298a.f1573P = this.f1703q0;
            c0298a.f1576S = this.f1646C;
            c0298a.f1588c = this.f1682g;
            c0298a.f1584a = this.f1678e;
            c0298a.f1586b = this.f1680f;
            ((ViewGroup.MarginLayoutParams) c0298a).width = this.f1672b;
            ((ViewGroup.MarginLayoutParams) c0298a).height = this.f1674c;
            c0298a.setMarginStart(this.f1652I);
            c0298a.setMarginEnd(this.f1651H);
            c0298a.m1410a();
        }

        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public b clone() {
            b bVar = new b();
            bVar.f1670a = this.f1670a;
            bVar.f1672b = this.f1672b;
            bVar.f1674c = this.f1674c;
            bVar.f1678e = this.f1678e;
            bVar.f1680f = this.f1680f;
            bVar.f1682g = this.f1682g;
            bVar.f1684h = this.f1684h;
            bVar.f1686i = this.f1686i;
            bVar.f1688j = this.f1688j;
            bVar.f1690k = this.f1690k;
            bVar.f1692l = this.f1692l;
            bVar.f1694m = this.f1694m;
            bVar.f1696n = this.f1696n;
            bVar.f1698o = this.f1698o;
            bVar.f1700p = this.f1700p;
            bVar.f1702q = this.f1702q;
            bVar.f1704r = this.f1704r;
            bVar.f1706s = this.f1706s;
            bVar.f1708t = this.f1708t;
            bVar.f1710u = this.f1710u;
            bVar.f1712v = this.f1712v;
            bVar.f1714w = this.f1714w;
            bVar.f1644A = this.f1644A;
            bVar.f1645B = this.f1645B;
            bVar.f1710u = this.f1710u;
            bVar.f1710u = this.f1710u;
            bVar.f1710u = this.f1710u;
            bVar.f1710u = this.f1710u;
            bVar.f1710u = this.f1710u;
            bVar.f1646C = this.f1646C;
            bVar.f1647D = this.f1647D;
            bVar.f1648E = this.f1648E;
            bVar.f1649F = this.f1649F;
            bVar.f1650G = this.f1650G;
            bVar.f1651H = this.f1651H;
            bVar.f1652I = this.f1652I;
            bVar.f1653J = this.f1653J;
            bVar.f1654K = this.f1654K;
            bVar.f1655L = this.f1655L;
            bVar.f1656M = this.f1656M;
            bVar.f1657N = this.f1657N;
            bVar.f1658O = this.f1658O;
            bVar.f1659P = this.f1659P;
            bVar.f1660Q = this.f1660Q;
            bVar.f1661R = this.f1661R;
            bVar.f1662S = this.f1662S;
            bVar.f1663T = this.f1663T;
            bVar.f1664U = this.f1664U;
            bVar.f1665V = this.f1665V;
            bVar.f1666W = this.f1666W;
            bVar.f1667X = this.f1667X;
            bVar.f1668Y = this.f1668Y;
            bVar.f1669Z = this.f1669Z;
            bVar.f1671a0 = this.f1671a0;
            bVar.f1673b0 = this.f1673b0;
            bVar.f1675c0 = this.f1675c0;
            bVar.f1677d0 = this.f1677d0;
            bVar.f1679e0 = this.f1679e0;
            bVar.f1681f0 = this.f1681f0;
            bVar.f1683g0 = this.f1683g0;
            bVar.f1685h0 = this.f1685h0;
            bVar.f1687i0 = this.f1687i0;
            bVar.f1689j0 = this.f1689j0;
            bVar.f1691k0 = this.f1691k0;
            bVar.f1693l0 = this.f1693l0;
            bVar.f1695m0 = this.f1695m0;
            bVar.f1697n0 = this.f1697n0;
            bVar.f1699o0 = this.f1699o0;
            bVar.f1701p0 = this.f1701p0;
            bVar.f1703q0 = this.f1703q0;
            bVar.f1707s0 = this.f1707s0;
            bVar.f1709t0 = this.f1709t0;
            int[] iArr = this.f1711u0;
            if (iArr != null) {
                bVar.f1711u0 = Arrays.copyOf(iArr, iArr.length);
            }
            bVar.f1715x = this.f1715x;
            bVar.f1716y = this.f1716y;
            bVar.f1717z = this.f1717z;
            bVar.f1705r0 = this.f1705r0;
            return bVar;
        }

        /* renamed from: e */
        public final void m1428e(int i9, ConstraintLayout.C0298a c0298a) {
            this.f1676d = i9;
            this.f1684h = c0298a.f1590d;
            this.f1686i = c0298a.f1592e;
            this.f1688j = c0298a.f1594f;
            this.f1690k = c0298a.f1596g;
            this.f1692l = c0298a.f1598h;
            this.f1694m = c0298a.f1600i;
            this.f1696n = c0298a.f1602j;
            this.f1698o = c0298a.f1604k;
            this.f1700p = c0298a.f1606l;
            this.f1702q = c0298a.f1612p;
            this.f1704r = c0298a.f1613q;
            this.f1706s = c0298a.f1614r;
            this.f1708t = c0298a.f1615s;
            this.f1710u = c0298a.f1622z;
            this.f1712v = c0298a.f1558A;
            this.f1714w = c0298a.f1559B;
            this.f1715x = c0298a.f1608m;
            this.f1716y = c0298a.f1610n;
            this.f1717z = c0298a.f1611o;
            this.f1644A = c0298a.f1574Q;
            this.f1645B = c0298a.f1575R;
            this.f1646C = c0298a.f1576S;
            this.f1682g = c0298a.f1588c;
            this.f1678e = c0298a.f1584a;
            this.f1680f = c0298a.f1586b;
            this.f1672b = ((ViewGroup.MarginLayoutParams) c0298a).width;
            this.f1674c = ((ViewGroup.MarginLayoutParams) c0298a).height;
            this.f1647D = ((ViewGroup.MarginLayoutParams) c0298a).leftMargin;
            this.f1648E = ((ViewGroup.MarginLayoutParams) c0298a).rightMargin;
            this.f1649F = ((ViewGroup.MarginLayoutParams) c0298a).topMargin;
            this.f1650G = ((ViewGroup.MarginLayoutParams) c0298a).bottomMargin;
            this.f1660Q = c0298a.f1563F;
            this.f1661R = c0298a.f1562E;
            this.f1663T = c0298a.f1565H;
            this.f1662S = c0298a.f1564G;
            boolean z8 = c0298a.f1577T;
            this.f1687i0 = c0298a.f1578U;
            this.f1689j0 = c0298a.f1566I;
            this.f1691k0 = c0298a.f1567J;
            this.f1685h0 = z8;
            this.f1693l0 = c0298a.f1570M;
            this.f1695m0 = c0298a.f1571N;
            this.f1697n0 = c0298a.f1568K;
            this.f1699o0 = c0298a.f1569L;
            this.f1701p0 = c0298a.f1572O;
            this.f1703q0 = c0298a.f1573P;
            this.f1651H = c0298a.getMarginEnd();
            this.f1652I = c0298a.getMarginStart();
        }

        /* renamed from: f */
        public final void m1429f(int i9, Constraints.C0299a c0299a) {
            m1428e(i9, c0299a);
            this.f1664U = c0299a.f1625n0;
            this.f1667X = c0299a.f1628q0;
            this.f1668Y = c0299a.f1629r0;
            this.f1669Z = c0299a.f1630s0;
            this.f1671a0 = c0299a.f1631t0;
            this.f1673b0 = c0299a.f1632u0;
            this.f1675c0 = c0299a.f1633v0;
            this.f1677d0 = c0299a.f1634w0;
            this.f1679e0 = c0299a.f1635x0;
            this.f1681f0 = c0299a.f1636y0;
            this.f1683g0 = c0299a.f1637z0;
            this.f1666W = c0299a.f1627p0;
            this.f1665V = c0299a.f1626o0;
        }

        /* renamed from: g */
        public final void m1430g(ConstraintHelper constraintHelper, int i9, Constraints.C0299a c0299a) {
            m1429f(i9, c0299a);
            if (constraintHelper instanceof Barrier) {
                this.f1709t0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                this.f1707s0 = barrier.getType();
                this.f1711u0 = barrier.getReferencedIds();
            }
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f1642c = sparseIntArray;
        sparseIntArray.append(C5867b.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        f1642c.append(C5867b.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        f1642c.append(C5867b.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        f1642c.append(C5867b.ConstraintSet_layout_constraintRight_toRightOf, 30);
        f1642c.append(C5867b.ConstraintSet_layout_constraintTop_toTopOf, 36);
        f1642c.append(C5867b.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        f1642c.append(C5867b.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        f1642c.append(C5867b.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        f1642c.append(C5867b.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        f1642c.append(C5867b.ConstraintSet_layout_editor_absoluteX, 6);
        f1642c.append(C5867b.ConstraintSet_layout_editor_absoluteY, 7);
        f1642c.append(C5867b.ConstraintSet_layout_constraintGuide_begin, 17);
        f1642c.append(C5867b.ConstraintSet_layout_constraintGuide_end, 18);
        f1642c.append(C5867b.ConstraintSet_layout_constraintGuide_percent, 19);
        f1642c.append(C5867b.ConstraintSet_android_orientation, 27);
        f1642c.append(C5867b.ConstraintSet_layout_constraintStart_toEndOf, 32);
        f1642c.append(C5867b.ConstraintSet_layout_constraintStart_toStartOf, 33);
        f1642c.append(C5867b.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        f1642c.append(C5867b.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        f1642c.append(C5867b.ConstraintSet_layout_goneMarginLeft, 13);
        f1642c.append(C5867b.ConstraintSet_layout_goneMarginTop, 16);
        f1642c.append(C5867b.ConstraintSet_layout_goneMarginRight, 14);
        f1642c.append(C5867b.ConstraintSet_layout_goneMarginBottom, 11);
        f1642c.append(C5867b.ConstraintSet_layout_goneMarginStart, 15);
        f1642c.append(C5867b.ConstraintSet_layout_goneMarginEnd, 12);
        f1642c.append(C5867b.ConstraintSet_layout_constraintVertical_weight, 40);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHorizontal_weight, 39);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        f1642c.append(C5867b.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHorizontal_bias, 20);
        f1642c.append(C5867b.ConstraintSet_layout_constraintVertical_bias, 37);
        f1642c.append(C5867b.ConstraintSet_layout_constraintDimensionRatio, 5);
        f1642c.append(C5867b.ConstraintSet_layout_constraintLeft_creator, 75);
        f1642c.append(C5867b.ConstraintSet_layout_constraintTop_creator, 75);
        f1642c.append(C5867b.ConstraintSet_layout_constraintRight_creator, 75);
        f1642c.append(C5867b.ConstraintSet_layout_constraintBottom_creator, 75);
        f1642c.append(C5867b.ConstraintSet_layout_constraintBaseline_creator, 75);
        f1642c.append(C5867b.ConstraintSet_android_layout_marginLeft, 24);
        f1642c.append(C5867b.ConstraintSet_android_layout_marginRight, 28);
        f1642c.append(C5867b.ConstraintSet_android_layout_marginStart, 31);
        f1642c.append(C5867b.ConstraintSet_android_layout_marginEnd, 8);
        f1642c.append(C5867b.ConstraintSet_android_layout_marginTop, 34);
        f1642c.append(C5867b.ConstraintSet_android_layout_marginBottom, 2);
        f1642c.append(C5867b.ConstraintSet_android_layout_width, 23);
        f1642c.append(C5867b.ConstraintSet_android_layout_height, 21);
        f1642c.append(C5867b.ConstraintSet_android_visibility, 22);
        f1642c.append(C5867b.ConstraintSet_android_alpha, 43);
        f1642c.append(C5867b.ConstraintSet_android_elevation, 44);
        f1642c.append(C5867b.ConstraintSet_android_rotationX, 45);
        f1642c.append(C5867b.ConstraintSet_android_rotationY, 46);
        f1642c.append(C5867b.ConstraintSet_android_rotation, 60);
        f1642c.append(C5867b.ConstraintSet_android_scaleX, 47);
        f1642c.append(C5867b.ConstraintSet_android_scaleY, 48);
        f1642c.append(C5867b.ConstraintSet_android_transformPivotX, 49);
        f1642c.append(C5867b.ConstraintSet_android_transformPivotY, 50);
        f1642c.append(C5867b.ConstraintSet_android_translationX, 51);
        f1642c.append(C5867b.ConstraintSet_android_translationY, 52);
        f1642c.append(C5867b.ConstraintSet_android_translationZ, 53);
        f1642c.append(C5867b.ConstraintSet_layout_constraintWidth_default, 54);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHeight_default, 55);
        f1642c.append(C5867b.ConstraintSet_layout_constraintWidth_max, 56);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHeight_max, 57);
        f1642c.append(C5867b.ConstraintSet_layout_constraintWidth_min, 58);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHeight_min, 59);
        f1642c.append(C5867b.ConstraintSet_layout_constraintCircle, 61);
        f1642c.append(C5867b.ConstraintSet_layout_constraintCircleRadius, 62);
        f1642c.append(C5867b.ConstraintSet_layout_constraintCircleAngle, 63);
        f1642c.append(C5867b.ConstraintSet_android_id, 38);
        f1642c.append(C5867b.ConstraintSet_layout_constraintWidth_percent, 69);
        f1642c.append(C5867b.ConstraintSet_layout_constraintHeight_percent, 70);
        f1642c.append(C5867b.ConstraintSet_chainUseRtl, 71);
        f1642c.append(C5867b.ConstraintSet_barrierDirection, 72);
        f1642c.append(C5867b.ConstraintSet_constraint_referenced_ids, 73);
        f1642c.append(C5867b.ConstraintSet_barrierAllowsGoneWidgets, 74);
    }

    /* renamed from: f */
    public static int m1417f(TypedArray typedArray, int i9, int i10) {
        int resourceId = typedArray.getResourceId(i9, i10);
        return resourceId == -1 ? typedArray.getInt(i9, -1) : resourceId;
    }

    /* renamed from: a */
    public void m1418a(ConstraintLayout constraintLayout) throws IllegalAccessException, IllegalArgumentException {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.f1643a.keySet());
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = constraintLayout.getChildAt(i9);
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (this.f1643a.containsKey(Integer.valueOf(id))) {
                hashSet.remove(Integer.valueOf(id));
                b bVar = this.f1643a.get(Integer.valueOf(id));
                if (childAt instanceof Barrier) {
                    bVar.f1709t0 = 1;
                }
                int i10 = bVar.f1709t0;
                if (i10 != -1 && i10 == 1) {
                    Barrier barrier = (Barrier) childAt;
                    barrier.setId(id);
                    barrier.setType(bVar.f1707s0);
                    barrier.setAllowsGoneWidget(bVar.f1705r0);
                    int[] iArr = bVar.f1711u0;
                    if (iArr != null) {
                        barrier.setReferencedIds(iArr);
                    } else {
                        String str = bVar.f1713v0;
                        if (str != null) {
                            int[] iArrM1420c = m1420c(barrier, str);
                            bVar.f1711u0 = iArrM1420c;
                            barrier.setReferencedIds(iArrM1420c);
                        }
                    }
                }
                ConstraintLayout.C0298a c0298a = (ConstraintLayout.C0298a) childAt.getLayoutParams();
                bVar.m1426c(c0298a);
                childAt.setLayoutParams(c0298a);
                childAt.setVisibility(bVar.f1653J);
                childAt.setAlpha(bVar.f1664U);
                childAt.setRotation(bVar.f1667X);
                childAt.setRotationX(bVar.f1668Y);
                childAt.setRotationY(bVar.f1669Z);
                childAt.setScaleX(bVar.f1671a0);
                childAt.setScaleY(bVar.f1673b0);
                if (!Float.isNaN(bVar.f1675c0)) {
                    childAt.setPivotX(bVar.f1675c0);
                }
                if (!Float.isNaN(bVar.f1677d0)) {
                    childAt.setPivotY(bVar.f1677d0);
                }
                childAt.setTranslationX(bVar.f1679e0);
                childAt.setTranslationY(bVar.f1681f0);
                childAt.setTranslationZ(bVar.f1683g0);
                if (bVar.f1665V) {
                    childAt.setElevation(bVar.f1666W);
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            b bVar2 = this.f1643a.get(num);
            int i11 = bVar2.f1709t0;
            if (i11 != -1 && i11 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                int[] iArr2 = bVar2.f1711u0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = bVar2.f1713v0;
                    if (str2 != null) {
                        int[] iArrM1420c2 = m1420c(barrier2, str2);
                        bVar2.f1711u0 = iArrM1420c2;
                        barrier2.setReferencedIds(iArrM1420c2);
                    }
                }
                barrier2.setType(bVar2.f1707s0);
                ConstraintLayout.C0298a c0298aM1395a = constraintLayout.generateDefaultLayoutParams();
                barrier2.m1394f();
                bVar2.m1426c(c0298aM1395a);
                constraintLayout.addView(barrier2, c0298aM1395a);
            }
            if (bVar2.f1670a) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.C0298a c0298aM1395a2 = constraintLayout.generateDefaultLayoutParams();
                bVar2.m1426c(c0298aM1395a2);
                constraintLayout.addView(guideline, c0298aM1395a2);
            }
        }
    }

    /* renamed from: b */
    public void m1419b(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f1643a.clear();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = constraints.getChildAt(i9);
            Constraints.C0299a c0299a = (Constraints.C0299a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1643a.containsKey(Integer.valueOf(id))) {
                this.f1643a.put(Integer.valueOf(id), new b());
            }
            b bVar = this.f1643a.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                bVar.m1430g((ConstraintHelper) childAt, id, c0299a);
            }
            bVar.m1429f(id, c0299a);
        }
    }

    /* renamed from: c */
    public final int[] m1420c(View view, String str) throws IllegalAccessException, IllegalArgumentException {
        int iIntValue;
        Object objM1397c;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i9 = 0;
        int i10 = 0;
        while (i9 < strArrSplit.length) {
            String strTrim = strArrSplit[i9].trim();
            try {
                iIntValue = C5866a.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, TtmlNode.ATTR_ID, context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objM1397c = ((ConstraintLayout) view.getParent()).m1397c(0, strTrim)) != null && (objM1397c instanceof Integer)) {
                iIntValue = ((Integer) objM1397c).intValue();
            }
            iArr[i10] = iIntValue;
            i9++;
            i10++;
        }
        return i10 != strArrSplit.length ? Arrays.copyOf(iArr, i10) : iArr;
    }

    /* renamed from: d */
    public final b m1421d(Context context, AttributeSet attributeSet) {
        b bVar = new b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5867b.ConstraintSet);
        m1423g(bVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return bVar;
    }

    /* renamed from: e */
    public void m1422e(Context context, int i9) throws XmlPullParserException, Resources.NotFoundException, IOException {
        XmlResourceParser xml = context.getResources().getXml(i9);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    b bVarM1421d = m1421d(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        bVarM1421d.f1670a = true;
                    }
                    this.f1643a.put(Integer.valueOf(bVarM1421d.f1676d), bVarM1421d);
                }
            }
        } catch (IOException e9) {
            e9.printStackTrace();
        } catch (XmlPullParserException e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: g */
    public final void m1423g(b bVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i9 = 0; i9 < indexCount; i9++) {
            int index = typedArray.getIndex(i9);
            int i10 = f1642c.get(index);
            switch (i10) {
                case 1:
                    bVar.f1700p = m1417f(typedArray, index, bVar.f1700p);
                    break;
                case 2:
                    bVar.f1650G = typedArray.getDimensionPixelSize(index, bVar.f1650G);
                    break;
                case 3:
                    bVar.f1698o = m1417f(typedArray, index, bVar.f1698o);
                    break;
                case 4:
                    bVar.f1696n = m1417f(typedArray, index, bVar.f1696n);
                    break;
                case 5:
                    bVar.f1714w = typedArray.getString(index);
                    break;
                case 6:
                    bVar.f1644A = typedArray.getDimensionPixelOffset(index, bVar.f1644A);
                    break;
                case 7:
                    bVar.f1645B = typedArray.getDimensionPixelOffset(index, bVar.f1645B);
                    break;
                case 8:
                    bVar.f1651H = typedArray.getDimensionPixelSize(index, bVar.f1651H);
                    break;
                case 9:
                    bVar.f1708t = m1417f(typedArray, index, bVar.f1708t);
                    break;
                case 10:
                    bVar.f1706s = m1417f(typedArray, index, bVar.f1706s);
                    break;
                case 11:
                    bVar.f1657N = typedArray.getDimensionPixelSize(index, bVar.f1657N);
                    break;
                case 12:
                    bVar.f1658O = typedArray.getDimensionPixelSize(index, bVar.f1658O);
                    break;
                case 13:
                    bVar.f1654K = typedArray.getDimensionPixelSize(index, bVar.f1654K);
                    break;
                case 14:
                    bVar.f1656M = typedArray.getDimensionPixelSize(index, bVar.f1656M);
                    break;
                case 15:
                    bVar.f1659P = typedArray.getDimensionPixelSize(index, bVar.f1659P);
                    break;
                case 16:
                    bVar.f1655L = typedArray.getDimensionPixelSize(index, bVar.f1655L);
                    break;
                case 17:
                    bVar.f1678e = typedArray.getDimensionPixelOffset(index, bVar.f1678e);
                    break;
                case 18:
                    bVar.f1680f = typedArray.getDimensionPixelOffset(index, bVar.f1680f);
                    break;
                case 19:
                    bVar.f1682g = typedArray.getFloat(index, bVar.f1682g);
                    break;
                case 20:
                    bVar.f1710u = typedArray.getFloat(index, bVar.f1710u);
                    break;
                case 21:
                    bVar.f1674c = typedArray.getLayoutDimension(index, bVar.f1674c);
                    break;
                case 22:
                    bVar.f1653J = f1641b[typedArray.getInt(index, bVar.f1653J)];
                    break;
                case 23:
                    bVar.f1672b = typedArray.getLayoutDimension(index, bVar.f1672b);
                    break;
                case 24:
                    bVar.f1647D = typedArray.getDimensionPixelSize(index, bVar.f1647D);
                    break;
                case 25:
                    bVar.f1684h = m1417f(typedArray, index, bVar.f1684h);
                    break;
                case 26:
                    bVar.f1686i = m1417f(typedArray, index, bVar.f1686i);
                    break;
                case 27:
                    bVar.f1646C = typedArray.getInt(index, bVar.f1646C);
                    break;
                case 28:
                    bVar.f1648E = typedArray.getDimensionPixelSize(index, bVar.f1648E);
                    break;
                case 29:
                    bVar.f1688j = m1417f(typedArray, index, bVar.f1688j);
                    break;
                case 30:
                    bVar.f1690k = m1417f(typedArray, index, bVar.f1690k);
                    break;
                case 31:
                    bVar.f1652I = typedArray.getDimensionPixelSize(index, bVar.f1652I);
                    break;
                case 32:
                    bVar.f1702q = m1417f(typedArray, index, bVar.f1702q);
                    break;
                case 33:
                    bVar.f1704r = m1417f(typedArray, index, bVar.f1704r);
                    break;
                case 34:
                    bVar.f1649F = typedArray.getDimensionPixelSize(index, bVar.f1649F);
                    break;
                case 35:
                    bVar.f1694m = m1417f(typedArray, index, bVar.f1694m);
                    break;
                case 36:
                    bVar.f1692l = m1417f(typedArray, index, bVar.f1692l);
                    break;
                case 37:
                    bVar.f1712v = typedArray.getFloat(index, bVar.f1712v);
                    break;
                case 38:
                    bVar.f1676d = typedArray.getResourceId(index, bVar.f1676d);
                    break;
                case 39:
                    bVar.f1661R = typedArray.getFloat(index, bVar.f1661R);
                    break;
                case 40:
                    bVar.f1660Q = typedArray.getFloat(index, bVar.f1660Q);
                    break;
                case 41:
                    bVar.f1662S = typedArray.getInt(index, bVar.f1662S);
                    break;
                case 42:
                    bVar.f1663T = typedArray.getInt(index, bVar.f1663T);
                    break;
                case 43:
                    bVar.f1664U = typedArray.getFloat(index, bVar.f1664U);
                    break;
                case 44:
                    bVar.f1665V = true;
                    bVar.f1666W = typedArray.getDimension(index, bVar.f1666W);
                    break;
                case 45:
                    bVar.f1668Y = typedArray.getFloat(index, bVar.f1668Y);
                    break;
                case 46:
                    bVar.f1669Z = typedArray.getFloat(index, bVar.f1669Z);
                    break;
                case 47:
                    bVar.f1671a0 = typedArray.getFloat(index, bVar.f1671a0);
                    break;
                case 48:
                    bVar.f1673b0 = typedArray.getFloat(index, bVar.f1673b0);
                    break;
                case 49:
                    bVar.f1675c0 = typedArray.getFloat(index, bVar.f1675c0);
                    break;
                case 50:
                    bVar.f1677d0 = typedArray.getFloat(index, bVar.f1677d0);
                    break;
                case 51:
                    bVar.f1679e0 = typedArray.getDimension(index, bVar.f1679e0);
                    break;
                case 52:
                    bVar.f1681f0 = typedArray.getDimension(index, bVar.f1681f0);
                    break;
                case 53:
                    bVar.f1683g0 = typedArray.getDimension(index, bVar.f1683g0);
                    break;
                default:
                    switch (i10) {
                        case 60:
                            bVar.f1667X = typedArray.getFloat(index, bVar.f1667X);
                            break;
                        case 61:
                            bVar.f1715x = m1417f(typedArray, index, bVar.f1715x);
                            break;
                        case 62:
                            bVar.f1716y = typedArray.getDimensionPixelSize(index, bVar.f1716y);
                            break;
                        case 63:
                            bVar.f1717z = typedArray.getFloat(index, bVar.f1717z);
                            break;
                        default:
                            switch (i10) {
                                case 69:
                                    bVar.f1701p0 = typedArray.getFloat(index, 1.0f);
                                    break;
                                case 70:
                                    bVar.f1703q0 = typedArray.getFloat(index, 1.0f);
                                    break;
                                case 71:
                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                    break;
                                case 72:
                                    bVar.f1707s0 = typedArray.getInt(index, bVar.f1707s0);
                                    break;
                                case 73:
                                    bVar.f1713v0 = typedArray.getString(index);
                                    break;
                                case 74:
                                    bVar.f1705r0 = typedArray.getBoolean(index, bVar.f1705r0);
                                    break;
                                case 75:
                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1642c.get(index));
                                    break;
                                default:
                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1642c.get(index));
                                    break;
                            }
                    }
            }
        }
    }
}
