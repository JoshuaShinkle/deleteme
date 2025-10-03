package com.google.android.exoplayer2.text.tx3g;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.charset.Charset;
import java.util.List;

/* loaded from: classes.dex */
public final class Tx3gDecoder extends SimpleSubtitleDecoder {
    private static final char BOM_UTF16_BE = 65279;
    private static final char BOM_UTF16_LE = 65534;
    private static final int DEFAULT_COLOR = -1;
    private static final int DEFAULT_FONT_FACE = 0;
    private static final String DEFAULT_FONT_FAMILY = "sans-serif";
    private static final float DEFAULT_VERTICAL_PLACEMENT = 0.85f;
    private static final int FONT_FACE_BOLD = 1;
    private static final int FONT_FACE_ITALIC = 2;
    private static final int FONT_FACE_UNDERLINE = 4;
    private static final int SIZE_ATOM_HEADER = 8;
    private static final int SIZE_BOM_UTF16 = 2;
    private static final int SIZE_SHORT = 2;
    private static final int SIZE_STYLE_RECORD = 12;
    private static final int SPAN_PRIORITY_HIGH = 0;
    private static final int SPAN_PRIORITY_LOW = 16711680;
    private static final String TX3G_SERIF = "Serif";
    private static final int TYPE_STYL = Util.getIntegerCodeForString("styl");
    private static final int TYPE_TBOX = Util.getIntegerCodeForString("tbox");
    private int calculatedVideoTrackHeight;
    private boolean customVerticalPlacement;
    private int defaultColorRgba;
    private int defaultFontFace;
    private String defaultFontFamily;
    private float defaultVerticalPlacement;
    private final ParsableByteArray parsableByteArray;

    public Tx3gDecoder(List<byte[]> list) {
        super("Tx3gDecoder");
        this.parsableByteArray = new ParsableByteArray();
        decodeInitializationData(list);
    }

    private void applyStyleRecord(ParsableByteArray parsableByteArray, SpannableStringBuilder spannableStringBuilder) throws SubtitleDecoderException {
        assertTrue(parsableByteArray.bytesLeft() >= 12);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.skipBytes(1);
        int i9 = parsableByteArray.readInt();
        attachFontFace(spannableStringBuilder, unsignedByte, this.defaultFontFace, unsignedShort, unsignedShort2, 0);
        attachColor(spannableStringBuilder, i9, this.defaultColorRgba, unsignedShort, unsignedShort2, 0);
    }

    private static void assertTrue(boolean z8) throws SubtitleDecoderException {
        if (!z8) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }

    private static void attachColor(SpannableStringBuilder spannableStringBuilder, int i9, int i10, int i11, int i12, int i13) {
        if (i9 != i10) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i9 >>> 8) | ((i9 & 255) << 24)), i11, i12, i13 | 33);
        }
    }

    private static void attachFontFace(SpannableStringBuilder spannableStringBuilder, int i9, int i10, int i11, int i12, int i13) {
        if (i9 != i10) {
            int i14 = i13 | 33;
            boolean z8 = (i9 & 1) != 0;
            boolean z9 = (i9 & 2) != 0;
            if (z8) {
                if (z9) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i11, i12, i14);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i11, i12, i14);
                }
            } else if (z9) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i11, i12, i14);
            }
            boolean z10 = (i9 & 4) != 0;
            if (z10) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i11, i12, i14);
            }
            if (z10 || z8 || z9) {
                return;
            }
            spannableStringBuilder.setSpan(new StyleSpan(0), i11, i12, i14);
        }
    }

    private static void attachFontFamily(SpannableStringBuilder spannableStringBuilder, String str, String str2, int i9, int i10, int i11) {
        if (str != str2) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i9, i10, i11 | 33);
        }
    }

    private void decodeInitializationData(List<byte[]> list) {
        if (list == null || list.size() != 1 || (list.get(0).length != 48 && list.get(0).length != 53)) {
            this.defaultFontFace = 0;
            this.defaultColorRgba = -1;
            this.defaultFontFamily = "sans-serif";
            this.customVerticalPlacement = false;
            this.defaultVerticalPlacement = DEFAULT_VERTICAL_PLACEMENT;
            return;
        }
        byte[] bArr = list.get(0);
        this.defaultFontFace = bArr[24];
        this.defaultColorRgba = ((bArr[26] & UnsignedBytes.MAX_VALUE) << 24) | ((bArr[27] & UnsignedBytes.MAX_VALUE) << 16) | ((bArr[28] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[29] & UnsignedBytes.MAX_VALUE);
        this.defaultFontFamily = TX3G_SERIF.equals(new String(bArr, 43, bArr.length - 43)) ? C3322C.SERIF_NAME : "sans-serif";
        int i9 = bArr[25] * Ascii.DC4;
        this.calculatedVideoTrackHeight = i9;
        boolean z8 = (bArr[0] & 32) != 0;
        this.customVerticalPlacement = z8;
        if (!z8) {
            this.defaultVerticalPlacement = DEFAULT_VERTICAL_PLACEMENT;
            return;
        }
        float f9 = ((bArr[11] & UnsignedBytes.MAX_VALUE) | ((bArr[10] & UnsignedBytes.MAX_VALUE) << 8)) / i9;
        this.defaultVerticalPlacement = f9;
        this.defaultVerticalPlacement = Util.constrainValue(f9, BitmapDescriptorFactory.HUE_RED, 0.95f);
    }

    private static String readSubtitleText(ParsableByteArray parsableByteArray) throws SubtitleDecoderException {
        char cPeekChar;
        assertTrue(parsableByteArray.bytesLeft() >= 2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return unsignedShort == 0 ? "" : (parsableByteArray.bytesLeft() < 2 || !((cPeekChar = parsableByteArray.peekChar()) == 65279 || cPeekChar == 65534)) ? parsableByteArray.readString(unsignedShort, Charset.forName("UTF-8")) : parsableByteArray.readString(unsignedShort, Charset.forName("UTF-16"));
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i9, boolean z8) throws SubtitleDecoderException {
        this.parsableByteArray.reset(bArr, i9);
        String subtitleText = readSubtitleText(this.parsableByteArray);
        if (subtitleText.isEmpty()) {
            return Tx3gSubtitle.EMPTY;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(subtitleText);
        attachFontFace(spannableStringBuilder, this.defaultFontFace, 0, 0, spannableStringBuilder.length(), SPAN_PRIORITY_LOW);
        attachColor(spannableStringBuilder, this.defaultColorRgba, -1, 0, spannableStringBuilder.length(), SPAN_PRIORITY_LOW);
        attachFontFamily(spannableStringBuilder, this.defaultFontFamily, "sans-serif", 0, spannableStringBuilder.length(), SPAN_PRIORITY_LOW);
        float fConstrainValue = this.defaultVerticalPlacement;
        while (this.parsableByteArray.bytesLeft() >= 8) {
            int position = this.parsableByteArray.getPosition();
            int i10 = this.parsableByteArray.readInt();
            int i11 = this.parsableByteArray.readInt();
            if (i11 == TYPE_STYL) {
                assertTrue(this.parsableByteArray.bytesLeft() >= 2);
                int unsignedShort = this.parsableByteArray.readUnsignedShort();
                for (int i12 = 0; i12 < unsignedShort; i12++) {
                    applyStyleRecord(this.parsableByteArray, spannableStringBuilder);
                }
            } else if (i11 == TYPE_TBOX && this.customVerticalPlacement) {
                assertTrue(this.parsableByteArray.bytesLeft() >= 2);
                fConstrainValue = Util.constrainValue(this.parsableByteArray.readUnsignedShort() / this.calculatedVideoTrackHeight, BitmapDescriptorFactory.HUE_RED, 0.95f);
            }
            this.parsableByteArray.setPosition(position + i10);
        }
        return new Tx3gSubtitle(new Cue(spannableStringBuilder, null, fConstrainValue, 0, 0, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE));
    }
}
