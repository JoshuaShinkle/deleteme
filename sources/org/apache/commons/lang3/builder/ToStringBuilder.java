package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.ObjectUtils;

/* loaded from: classes.dex */
public class ToStringBuilder implements Builder<String> {
    private static volatile ToStringStyle defaultStyle = ToStringStyle.DEFAULT_STYLE;
    private final StringBuffer buffer;
    private final Object object;
    private final ToStringStyle style;

    public ToStringBuilder(Object obj) {
        this(obj, null, null);
    }

    public static ToStringStyle getDefaultStyle() {
        return defaultStyle;
    }

    public static String reflectionToString(Object obj) {
        return ReflectionToStringBuilder.toString(obj);
    }

    public static void setDefaultStyle(ToStringStyle toStringStyle) {
        if (toStringStyle == null) {
            throw new IllegalArgumentException("The style must not be null");
        }
        defaultStyle = toStringStyle;
    }

    public ToStringBuilder append(boolean z8) {
        this.style.append(this.buffer, (String) null, z8);
        return this;
    }

    public ToStringBuilder appendAsObjectToString(Object obj) {
        ObjectUtils.identityToString(getStringBuffer(), obj);
        return this;
    }

    public ToStringBuilder appendSuper(String str) {
        if (str != null) {
            this.style.appendSuper(this.buffer, str);
        }
        return this;
    }

    public ToStringBuilder appendToString(String str) {
        if (str != null) {
            this.style.appendToString(this.buffer, str);
        }
        return this;
    }

    public Object getObject() {
        return this.object;
    }

    public StringBuffer getStringBuffer() {
        return this.buffer;
    }

    public ToStringStyle getStyle() {
        return this.style;
    }

    public String toString() {
        if (getObject() == null) {
            getStringBuffer().append(getStyle().getNullText());
        } else {
            this.style.appendEnd(getStringBuffer(), getObject());
        }
        return getStringBuffer().toString();
    }

    public ToStringBuilder(Object obj, ToStringStyle toStringStyle) {
        this(obj, toStringStyle, null);
    }

    public static String reflectionToString(Object obj, ToStringStyle toStringStyle) {
        return ReflectionToStringBuilder.toString(obj, toStringStyle);
    }

    public ToStringBuilder append(boolean[] zArr) {
        this.style.append(this.buffer, (String) null, zArr, (Boolean) null);
        return this;
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public String build() {
        return toString();
    }

    public ToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer) {
        toStringStyle = toStringStyle == null ? getDefaultStyle() : toStringStyle;
        stringBuffer = stringBuffer == null ? new StringBuffer(512) : stringBuffer;
        this.buffer = stringBuffer;
        this.style = toStringStyle;
        this.object = obj;
        toStringStyle.appendStart(stringBuffer, obj);
    }

    public static String reflectionToString(Object obj, ToStringStyle toStringStyle, boolean z8) {
        return ReflectionToStringBuilder.toString(obj, toStringStyle, z8, false, null);
    }

    public ToStringBuilder append(byte b9) {
        this.style.append(this.buffer, (String) null, b9);
        return this;
    }

    public static <T> String reflectionToString(T t8, ToStringStyle toStringStyle, boolean z8, Class<? super T> cls) {
        return ReflectionToStringBuilder.toString(t8, toStringStyle, z8, false, cls);
    }

    public ToStringBuilder append(byte[] bArr) {
        this.style.append(this.buffer, (String) null, bArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(char c9) {
        this.style.append(this.buffer, (String) null, c9);
        return this;
    }

    public ToStringBuilder append(char[] cArr) {
        this.style.append(this.buffer, (String) null, cArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(double d9) {
        this.style.append(this.buffer, (String) null, d9);
        return this;
    }

    public ToStringBuilder append(double[] dArr) {
        this.style.append(this.buffer, (String) null, dArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(float f9) {
        this.style.append(this.buffer, (String) null, f9);
        return this;
    }

    public ToStringBuilder append(float[] fArr) {
        this.style.append(this.buffer, (String) null, fArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(int i9) {
        this.style.append(this.buffer, (String) null, i9);
        return this;
    }

    public ToStringBuilder append(int[] iArr) {
        this.style.append(this.buffer, (String) null, iArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(long j9) {
        this.style.append(this.buffer, (String) null, j9);
        return this;
    }

    public ToStringBuilder append(long[] jArr) {
        this.style.append(this.buffer, (String) null, jArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(Object obj) {
        this.style.append(this.buffer, (String) null, obj, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(Object[] objArr) {
        this.style.append(this.buffer, (String) null, objArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(short s8) {
        this.style.append(this.buffer, (String) null, s8);
        return this;
    }

    public ToStringBuilder append(short[] sArr) {
        this.style.append(this.buffer, (String) null, sArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, boolean z8) {
        this.style.append(this.buffer, str, z8);
        return this;
    }

    public ToStringBuilder append(String str, boolean[] zArr) {
        this.style.append(this.buffer, str, zArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, boolean[] zArr, boolean z8) {
        this.style.append(this.buffer, str, zArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, byte b9) {
        this.style.append(this.buffer, str, b9);
        return this;
    }

    public ToStringBuilder append(String str, byte[] bArr) {
        this.style.append(this.buffer, str, bArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, byte[] bArr, boolean z8) {
        this.style.append(this.buffer, str, bArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, char c9) {
        this.style.append(this.buffer, str, c9);
        return this;
    }

    public ToStringBuilder append(String str, char[] cArr) {
        this.style.append(this.buffer, str, cArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, char[] cArr, boolean z8) {
        this.style.append(this.buffer, str, cArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, double d9) {
        this.style.append(this.buffer, str, d9);
        return this;
    }

    public ToStringBuilder append(String str, double[] dArr) {
        this.style.append(this.buffer, str, dArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, double[] dArr, boolean z8) {
        this.style.append(this.buffer, str, dArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, float f9) {
        this.style.append(this.buffer, str, f9);
        return this;
    }

    public ToStringBuilder append(String str, float[] fArr) {
        this.style.append(this.buffer, str, fArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, float[] fArr, boolean z8) {
        this.style.append(this.buffer, str, fArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, int i9) {
        this.style.append(this.buffer, str, i9);
        return this;
    }

    public ToStringBuilder append(String str, int[] iArr) {
        this.style.append(this.buffer, str, iArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, int[] iArr, boolean z8) {
        this.style.append(this.buffer, str, iArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, long j9) {
        this.style.append(this.buffer, str, j9);
        return this;
    }

    public ToStringBuilder append(String str, long[] jArr) {
        this.style.append(this.buffer, str, jArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, long[] jArr, boolean z8) {
        this.style.append(this.buffer, str, jArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, Object obj) {
        this.style.append(this.buffer, str, obj, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, Object obj, boolean z8) {
        this.style.append(this.buffer, str, obj, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, Object[] objArr) {
        this.style.append(this.buffer, str, objArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, Object[] objArr, boolean z8) {
        this.style.append(this.buffer, str, objArr, Boolean.valueOf(z8));
        return this;
    }

    public ToStringBuilder append(String str, short s8) {
        this.style.append(this.buffer, str, s8);
        return this;
    }

    public ToStringBuilder append(String str, short[] sArr) {
        this.style.append(this.buffer, str, sArr, (Boolean) null);
        return this;
    }

    public ToStringBuilder append(String str, short[] sArr, boolean z8) {
        this.style.append(this.buffer, str, sArr, Boolean.valueOf(z8));
        return this;
    }
}
