package org.xbill.DNS;

import com.google.zxing.client.android.Intents;
import java.util.HashMap;
import net.sqlcipher.database.SQLiteDatabase;

/* renamed from: org.xbill.DNS.k */
/* loaded from: classes3.dex */
public final class C5865k {

    /* renamed from: a */
    public static a f20276a;

    /* renamed from: org.xbill.DNS.k$a */
    public static class a extends C5861g {

        /* renamed from: i */
        public HashMap f20277i;

        public a() {
            super("Type", 2);
            m23258i(Intents.WifiConnect.TYPE);
            this.f20277i = new HashMap();
        }

        @Override // org.xbill.DNS.C5861g
        /* renamed from: d */
        public void mo23232d(int i9) {
            C5865k.m23266a(i9);
        }

        /* renamed from: k */
        public void m23268k(int i9, String str, Record record) {
            super.m23251a(i9, str);
            this.f20277i.put(C5861g.m23250j(i9), record);
        }
    }

    static {
        a aVar = new a();
        f20276a = aVar;
        aVar.m23268k(1, "A", new ARecord());
        f20276a.m23268k(2, "NS", new NSRecord());
        f20276a.m23268k(3, "MD", new MDRecord());
        f20276a.m23268k(4, "MF", new MFRecord());
        f20276a.m23268k(5, "CNAME", new CNAMERecord());
        f20276a.m23268k(6, "SOA", new SOARecord());
        f20276a.m23268k(7, "MB", new MBRecord());
        f20276a.m23268k(8, "MG", new MGRecord());
        f20276a.m23268k(9, "MR", new MRRecord());
        f20276a.m23268k(10, "NULL", new NULLRecord());
        f20276a.m23268k(11, "WKS", new WKSRecord());
        f20276a.m23268k(12, "PTR", new PTRRecord());
        f20276a.m23268k(13, "HINFO", new HINFORecord());
        f20276a.m23268k(14, "MINFO", new MINFORecord());
        f20276a.m23268k(15, "MX", new MXRecord());
        f20276a.m23268k(16, "TXT", new TXTRecord());
        f20276a.m23268k(17, "RP", new RPRecord());
        f20276a.m23268k(18, "AFSDB", new AFSDBRecord());
        f20276a.m23268k(19, "X25", new X25Record());
        f20276a.m23268k(20, "ISDN", new ISDNRecord());
        f20276a.m23268k(21, "RT", new RTRecord());
        f20276a.m23268k(22, "NSAP", new NSAPRecord());
        f20276a.m23268k(23, "NSAP-PTR", new NSAP_PTRRecord());
        f20276a.m23268k(24, "SIG", new SIGRecord());
        f20276a.m23268k(25, "KEY", new KEYRecord());
        f20276a.m23268k(26, "PX", new PXRecord());
        f20276a.m23268k(27, "GPOS", new GPOSRecord());
        f20276a.m23268k(28, "AAAA", new AAAARecord());
        f20276a.m23268k(29, "LOC", new LOCRecord());
        f20276a.m23268k(30, "NXT", new NXTRecord());
        f20276a.m23251a(31, "EID");
        f20276a.m23251a(32, "NIMLOC");
        f20276a.m23268k(33, "SRV", new SRVRecord());
        f20276a.m23251a(34, "ATMA");
        f20276a.m23268k(35, "NAPTR", new NAPTRRecord());
        f20276a.m23268k(36, "KX", new KXRecord());
        f20276a.m23268k(37, "CERT", new CERTRecord());
        f20276a.m23268k(38, "A6", new A6Record());
        f20276a.m23268k(39, "DNAME", new DNAMERecord());
        f20276a.m23268k(41, "OPT", new OPTRecord());
        f20276a.m23268k(42, "APL", new APLRecord());
        f20276a.m23268k(43, "DS", new DSRecord());
        f20276a.m23268k(44, "SSHFP", new SSHFPRecord());
        f20276a.m23268k(45, "IPSECKEY", new IPSECKEYRecord());
        f20276a.m23268k(46, "RRSIG", new RRSIGRecord());
        f20276a.m23268k(47, "NSEC", new NSECRecord());
        f20276a.m23268k(48, "DNSKEY", new DNSKEYRecord());
        f20276a.m23268k(49, "DHCID", new DHCIDRecord());
        f20276a.m23268k(50, "NSEC3", new NSEC3Record());
        f20276a.m23268k(51, "NSEC3PARAM", new NSEC3PARAMRecord());
        f20276a.m23268k(52, "TLSA", new TLSARecord());
        f20276a.m23268k(99, "SPF", new SPFRecord());
        f20276a.m23268k(249, "TKEY", new TKEYRecord());
        f20276a.m23268k(SQLiteDatabase.MAX_SQL_CACHE_SIZE, "TSIG", new TSIGRecord());
        f20276a.m23251a(251, "IXFR");
        f20276a.m23251a(252, "AXFR");
        f20276a.m23251a(253, "MAILB");
        f20276a.m23251a(254, "MAILA");
        f20276a.m23251a(255, "ANY");
        f20276a.m23268k(32769, "DLV", new DLVRecord());
    }

    /* renamed from: a */
    public static void m23266a(int i9) {
        if (i9 < 0 || i9 > 65535) {
            throw new InvalidTypeException(i9);
        }
    }

    /* renamed from: b */
    public static String m23267b(int i9) {
        return f20276a.m23254e(i9);
    }
}
