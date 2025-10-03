package p022c1;

/* renamed from: c1.f */
/* loaded from: classes.dex */
public final class C0709f implements InterfaceC0704a<byte[]> {
    @Override // p022c1.InterfaceC0704a
    /* renamed from: a */
    public int mo3476a() {
        return 1;
    }

    @Override // p022c1.InterfaceC0704a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int mo3477b(byte[] bArr) {
        return bArr.length;
    }

    @Override // p022c1.InterfaceC0704a
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public byte[] newArray(int i9) {
        return new byte[i9];
    }

    @Override // p022c1.InterfaceC0704a
    public String getTag() {
        return "ByteArrayPool";
    }
}
