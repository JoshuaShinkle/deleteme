package com.google.android.exoplayer2.text.cea;

import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public final class Cea708Decoder extends CeaDecoder {
    private static final int CC_VALID_FLAG = 4;
    private static final int CHARACTER_BIG_CARONS = 42;
    private static final int CHARACTER_BIG_OE = 44;
    private static final int CHARACTER_BOLD_BULLET = 53;
    private static final int CHARACTER_CLOSE_DOUBLE_QUOTE = 52;
    private static final int CHARACTER_CLOSE_SINGLE_QUOTE = 50;
    private static final int CHARACTER_DIAERESIS_Y = 63;
    private static final int CHARACTER_ELLIPSIS = 37;
    private static final int CHARACTER_FIVE_EIGHTHS = 120;
    private static final int CHARACTER_HORIZONTAL_BORDER = 125;
    private static final int CHARACTER_LOWER_LEFT_BORDER = 124;
    private static final int CHARACTER_LOWER_RIGHT_BORDER = 126;
    private static final int CHARACTER_MN = 127;
    private static final int CHARACTER_NBTSP = 33;
    private static final int CHARACTER_ONE_EIGHTH = 118;
    private static final int CHARACTER_OPEN_DOUBLE_QUOTE = 51;
    private static final int CHARACTER_OPEN_SINGLE_QUOTE = 49;
    private static final int CHARACTER_SEVEN_EIGHTHS = 121;
    private static final int CHARACTER_SM = 61;
    private static final int CHARACTER_SMALL_CARONS = 58;
    private static final int CHARACTER_SMALL_OE = 60;
    private static final int CHARACTER_SOLID_BLOCK = 48;
    private static final int CHARACTER_THREE_EIGHTHS = 119;
    private static final int CHARACTER_TM = 57;
    private static final int CHARACTER_TSP = 32;
    private static final int CHARACTER_UPPER_LEFT_BORDER = 127;
    private static final int CHARACTER_UPPER_RIGHT_BORDER = 123;
    private static final int CHARACTER_VERTICAL_BORDER = 122;
    private static final int COMMAND_BS = 8;
    private static final int COMMAND_CLW = 136;
    private static final int COMMAND_CR = 13;
    private static final int COMMAND_CW0 = 128;
    private static final int COMMAND_CW1 = 129;
    private static final int COMMAND_CW2 = 130;
    private static final int COMMAND_CW3 = 131;
    private static final int COMMAND_CW4 = 132;
    private static final int COMMAND_CW5 = 133;
    private static final int COMMAND_CW6 = 134;
    private static final int COMMAND_CW7 = 135;
    private static final int COMMAND_DF0 = 152;
    private static final int COMMAND_DF1 = 153;
    private static final int COMMAND_DF2 = 154;
    private static final int COMMAND_DF3 = 155;
    private static final int COMMAND_DF4 = 156;
    private static final int COMMAND_DF5 = 157;
    private static final int COMMAND_DF6 = 158;
    private static final int COMMAND_DF7 = 159;
    private static final int COMMAND_DLC = 142;
    private static final int COMMAND_DLW = 140;
    private static final int COMMAND_DLY = 141;
    private static final int COMMAND_DSW = 137;
    private static final int COMMAND_ETX = 3;
    private static final int COMMAND_EXT1 = 16;
    private static final int COMMAND_EXT1_END = 23;
    private static final int COMMAND_EXT1_START = 17;
    private static final int COMMAND_FF = 12;
    private static final int COMMAND_HCR = 14;
    private static final int COMMAND_HDW = 138;
    private static final int COMMAND_NUL = 0;
    private static final int COMMAND_P16_END = 31;
    private static final int COMMAND_P16_START = 24;
    private static final int COMMAND_RST = 143;
    private static final int COMMAND_SPA = 144;
    private static final int COMMAND_SPC = 145;
    private static final int COMMAND_SPL = 146;
    private static final int COMMAND_SWA = 151;
    private static final int COMMAND_TGW = 139;
    private static final int DTVCC_PACKET_DATA = 2;
    private static final int DTVCC_PACKET_START = 3;
    private static final int GROUP_C0_END = 31;
    private static final int GROUP_C1_END = 159;
    private static final int GROUP_C2_END = 31;
    private static final int GROUP_C3_END = 159;
    private static final int GROUP_G0_END = 127;
    private static final int GROUP_G1_END = 255;
    private static final int GROUP_G2_END = 127;
    private static final int GROUP_G3_END = 255;
    private static final int NUM_WINDOWS = 8;
    private static final String TAG = "Cea708Decoder";
    private final CueBuilder[] cueBuilders;
    private List<Cue> cues;
    private CueBuilder currentCueBuilder;
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private List<Cue> lastCues;
    private final int selectedServiceNumber;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ParsableBitArray serviceBlockPacket = new ParsableBitArray();

    public static final class CueBuilder {
        private static final int BORDER_AND_EDGE_TYPE_NONE = 0;
        private static final int BORDER_AND_EDGE_TYPE_UNIFORM = 3;
        public static final int COLOR_SOLID_BLACK;
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT;
        private static final int DEFAULT_PRIORITY = 4;
        private static final int DIRECTION_BOTTOM_TO_TOP = 3;
        private static final int DIRECTION_LEFT_TO_RIGHT = 0;
        private static final int DIRECTION_RIGHT_TO_LEFT = 1;
        private static final int DIRECTION_TOP_TO_BOTTOM = 2;
        private static final int HORIZONTAL_SIZE = 209;
        private static final int JUSTIFICATION_CENTER = 2;
        private static final int JUSTIFICATION_FULL = 3;
        private static final int JUSTIFICATION_LEFT = 0;
        private static final int JUSTIFICATION_RIGHT = 1;
        private static final int MAXIMUM_ROW_COUNT = 15;
        private static final int PEN_FONT_STYLE_DEFAULT = 0;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITHOUT_SERIFS = 3;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITH_SERIFS = 1;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITHOUT_SERIFS = 4;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITH_SERIFS = 2;
        private static final int PEN_OFFSET_NORMAL = 1;
        private static final int PEN_SIZE_STANDARD = 1;
        private static final int[] PEN_STYLE_BACKGROUND;
        private static final int[] PEN_STYLE_EDGE_TYPE;
        private static final int[] PEN_STYLE_FONT_STYLE;
        private static final int RELATIVE_CUE_SIZE = 99;
        private static final int VERTICAL_SIZE = 74;
        private static final int[] WINDOW_STYLE_FILL;
        private static final int[] WINDOW_STYLE_JUSTIFICATION;
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION;
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION;
        private static final boolean[] WINDOW_STYLE_WORD_WRAP;
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private int row;
        private int rowCount;
        private boolean rowLock;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;
        private final List<SpannableString> rolledUpCaptions = new LinkedList();
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();

        static {
            int argbColorFromCeaColor = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = argbColorFromCeaColor;
            int argbColorFromCeaColor2 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = argbColorFromCeaColor2;
            WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 0, 0, 0, 2, 0};
            WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            WINDOW_STYLE_FILL = new int[]{argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor};
            PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            PEN_STYLE_BACKGROUND = new int[]{argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor2};
        }

        public CueBuilder() {
            reset();
        }

        public static int getArgbColorFromCeaColor(int i9, int i10, int i11) {
            return getArgbColorFromCeaColor(i9, i10, i11, 0);
        }

        public void append(char c9) {
            if (c9 != '\n') {
                this.captionStringBuilder.append(c9);
                return;
            }
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.clear();
            if (this.italicsStartPosition != -1) {
                this.italicsStartPosition = 0;
            }
            if (this.underlineStartPosition != -1) {
                this.underlineStartPosition = 0;
            }
            if (this.foregroundColorStartPosition != -1) {
                this.foregroundColorStartPosition = 0;
            }
            if (this.backgroundColorStartPosition != -1) {
                this.backgroundColorStartPosition = 0;
            }
            while (true) {
                if ((!this.rowLock || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                    return;
                } else {
                    this.rolledUpCaptions.remove(0);
                }
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        public Cea708Cue build() {
            Layout.Alignment alignment;
            float f9;
            float f10;
            if (isEmpty()) {
                return null;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i9 = 0; i9 < this.rolledUpCaptions.size(); i9++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i9));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) buildSpannableString());
            int i10 = this.justification;
            if (i10 == 0) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (i10 == 1) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else if (i10 != 2) {
                if (i10 != 3) {
                    throw new IllegalArgumentException("Unexpected justification value: " + this.justification);
                }
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            Layout.Alignment alignment2 = alignment;
            if (this.relativePositioning) {
                f9 = this.horizontalAnchor / 99.0f;
                f10 = this.verticalAnchor / 99.0f;
            } else {
                f9 = this.horizontalAnchor / 209.0f;
                f10 = this.verticalAnchor / 74.0f;
            }
            float f11 = (f9 * 0.9f) + 0.05f;
            float f12 = (f10 * 0.9f) + 0.05f;
            int i11 = this.anchorId;
            int i12 = i11 % 3 == 0 ? 0 : i11 % 3 == 1 ? 1 : 2;
            int i13 = i11 / 3 == 0 ? 0 : i11 / 3 == 1 ? 1 : 2;
            int i14 = this.windowFillColor;
            return new Cea708Cue(spannableStringBuilder, alignment2, f12, 0, i12, f11, i13, Float.MIN_VALUE, i14 != COLOR_SOLID_BLACK, i14, this.priority);
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.italicsStartPosition != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                if (this.underlineStartPosition != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                if (this.foregroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                if (this.backgroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public void defineWindow(boolean z8, boolean z9, boolean z10, int i9, boolean z11, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            this.defined = true;
            this.visible = z8;
            this.rowLock = z9;
            this.priority = i9;
            this.relativePositioning = z11;
            this.verticalAnchor = i10;
            this.horizontalAnchor = i11;
            this.anchorId = i14;
            int i17 = i12 + 1;
            if (this.rowCount != i17) {
                this.rowCount = i17;
                while (true) {
                    if ((!z9 || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        break;
                    } else {
                        this.rolledUpCaptions.remove(0);
                    }
                }
            }
            if (i15 != 0 && this.windowStyleId != i15) {
                this.windowStyleId = i15;
                int i18 = i15 - 1;
                setWindowAttributes(WINDOW_STYLE_FILL[i18], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[i18], 0, WINDOW_STYLE_PRINT_DIRECTION[i18], WINDOW_STYLE_SCROLL_DIRECTION[i18], WINDOW_STYLE_JUSTIFICATION[i18]);
            }
            if (i16 == 0 || this.penStyleId == i16) {
                return;
            }
            this.penStyleId = i16;
            int i19 = i16 - 1;
            setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[i19], PEN_STYLE_FONT_STYLE[i19]);
            setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[i19], COLOR_SOLID_BLACK);
        }

        public boolean isDefined() {
            return this.defined;
        }

        public boolean isEmpty() {
            return !isDefined() || (this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0);
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int i9 = COLOR_SOLID_BLACK;
            this.windowFillColor = i9;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = i9;
        }

        public void setPenAttributes(int i9, int i10, int i11, boolean z8, boolean z9, int i12, int i13) {
            if (this.italicsStartPosition != -1) {
                if (!z8) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z8) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition == -1) {
                if (z9) {
                    this.underlineStartPosition = this.captionStringBuilder.length();
                }
            } else {
                if (z9) {
                    return;
                }
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                this.underlineStartPosition = -1;
            }
        }

        public void setPenColor(int i9, int i10, int i11) {
            if (this.foregroundColorStartPosition != -1 && this.foregroundColor != i9) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i9 != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = i9;
            }
            if (this.backgroundColorStartPosition != -1 && this.backgroundColor != i10) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i10 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = i10;
            }
        }

        public void setPenLocation(int i9, int i10) {
            if (this.row != i9) {
                append('\n');
            }
            this.row = i9;
        }

        public void setVisibility(boolean z8) {
            this.visible = z8;
        }

        public void setWindowAttributes(int i9, int i10, boolean z8, int i11, int i12, int i13, int i14) {
            this.windowFillColor = i9;
            this.justification = i14;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static int getArgbColorFromCeaColor(int i9, int i10, int i11, int i12) {
            int i13;
            Assertions.checkIndex(i9, 0, 4);
            Assertions.checkIndex(i10, 0, 4);
            Assertions.checkIndex(i11, 0, 4);
            Assertions.checkIndex(i12, 0, 4);
            if (i12 == 0 || i12 == 1) {
                i13 = 255;
            } else if (i12 == 2) {
                i13 = 127;
            } else if (i12 == 3) {
                i13 = 0;
            }
            return Color.argb(i13, i9 > 1 ? 255 : 0, i10 > 1 ? 255 : 0, i11 > 1 ? 255 : 0);
        }
    }

    public static final class DtvCcPacket {
        int currentIndex = 0;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int i9, int i10) {
            this.sequenceNumber = i9;
            this.packetSize = i10;
            this.packetData = new byte[(i10 * 2) - 1];
        }
    }

    public Cea708Decoder(int i9) {
        this.selectedServiceNumber = i9 == -1 ? 1 : i9;
        this.cueBuilders = new CueBuilder[8];
        for (int i10 = 0; i10 < 8; i10++) {
            this.cueBuilders[i10] = new CueBuilder();
        }
        this.currentCueBuilder = this.cueBuilders[0];
        resetCueBuilders();
    }

    private void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket == null) {
            return;
        }
        processCurrentPacket();
        this.currentDtvCcPacket = null;
    }

    private List<Cue> getDisplayCues() {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < 8; i9++) {
            if (!this.cueBuilders[i9].isEmpty() && this.cueBuilders[i9].isVisible()) {
                arrayList.add(this.cueBuilders[i9].build());
            }
        }
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    private void handleC0Command(int i9) {
        if (i9 != 0) {
            if (i9 == 3) {
                this.cues = getDisplayCues();
            }
            if (i9 == 8) {
                this.currentCueBuilder.backspace();
                return;
            }
            switch (i9) {
                case 12:
                    resetCueBuilders();
                    break;
                case 13:
                    this.currentCueBuilder.append('\n');
                    break;
                case 14:
                    break;
                default:
                    if (i9 >= 17 && i9 <= 23) {
                        Log.w(TAG, "Currently unsupported COMMAND_EXT1 Command: " + i9);
                        this.serviceBlockPacket.skipBits(8);
                        break;
                    } else if (i9 >= 24 && i9 <= 31) {
                        Log.w(TAG, "Currently unsupported COMMAND_P16 Command: " + i9);
                        this.serviceBlockPacket.skipBits(16);
                        break;
                    } else {
                        Log.w(TAG, "Invalid C0 command: " + i9);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void handleC1Command(int i9) {
        int i10 = 1;
        switch (i9) {
            case COMMAND_CW0 /* 128 */:
            case 129:
            case 130:
            case COMMAND_CW3 /* 131 */:
            case COMMAND_CW4 /* 132 */:
            case COMMAND_CW5 /* 133 */:
            case 134:
            case 135:
                int i11 = i9 - 128;
                if (this.currentWindow != i11) {
                    this.currentWindow = i11;
                    this.currentCueBuilder = this.cueBuilders[i11];
                    break;
                }
                break;
            case COMMAND_CLW /* 136 */:
                while (i10 <= 8) {
                    if (this.serviceBlockPacket.readBit()) {
                        this.cueBuilders[8 - i10].clear();
                    }
                    i10++;
                }
                break;
            case COMMAND_DSW /* 137 */:
                for (int i12 = 1; i12 <= 8; i12++) {
                    if (this.serviceBlockPacket.readBit()) {
                        this.cueBuilders[8 - i12].setVisibility(true);
                    }
                }
                break;
            case 138:
                while (i10 <= 8) {
                    if (this.serviceBlockPacket.readBit()) {
                        this.cueBuilders[8 - i10].setVisibility(false);
                    }
                    i10++;
                }
                break;
            case COMMAND_TGW /* 139 */:
                for (int i13 = 1; i13 <= 8; i13++) {
                    if (this.serviceBlockPacket.readBit()) {
                        this.cueBuilders[8 - i13].setVisibility(!r0.isVisible());
                    }
                }
                break;
            case COMMAND_DLW /* 140 */:
                while (i10 <= 8) {
                    if (this.serviceBlockPacket.readBit()) {
                        this.cueBuilders[8 - i10].reset();
                    }
                    i10++;
                }
                break;
            case COMMAND_DLY /* 141 */:
                this.serviceBlockPacket.skipBits(8);
                break;
            case COMMAND_DLC /* 142 */:
                break;
            case COMMAND_RST /* 143 */:
                resetCueBuilders();
                break;
            case COMMAND_SPA /* 144 */:
                if (this.currentCueBuilder.isDefined()) {
                    handleSetPenAttributes();
                    break;
                } else {
                    this.serviceBlockPacket.skipBits(16);
                    break;
                }
            case COMMAND_SPC /* 145 */:
                if (this.currentCueBuilder.isDefined()) {
                    handleSetPenColor();
                    break;
                } else {
                    this.serviceBlockPacket.skipBits(24);
                    break;
                }
            case COMMAND_SPL /* 146 */:
                if (this.currentCueBuilder.isDefined()) {
                    handleSetPenLocation();
                    break;
                } else {
                    this.serviceBlockPacket.skipBits(16);
                    break;
                }
            case 147:
            case 148:
            case 149:
            case 150:
            default:
                Log.w(TAG, "Invalid C1 command: " + i9);
                break;
            case COMMAND_SWA /* 151 */:
                if (this.currentCueBuilder.isDefined()) {
                    handleSetWindowAttributes();
                    break;
                } else {
                    this.serviceBlockPacket.skipBits(32);
                    break;
                }
            case COMMAND_DF0 /* 152 */:
            case COMMAND_DF1 /* 153 */:
            case COMMAND_DF2 /* 154 */:
            case COMMAND_DF3 /* 155 */:
            case COMMAND_DF4 /* 156 */:
            case COMMAND_DF5 /* 157 */:
            case COMMAND_DF6 /* 158 */:
            case 159:
                int i14 = i9 - 152;
                handleDefineWindow(i14);
                if (this.currentWindow != i14) {
                    this.currentWindow = i14;
                    this.currentCueBuilder = this.cueBuilders[i14];
                    break;
                }
                break;
        }
    }

    private void handleC2Command(int i9) {
        if (i9 <= 7) {
            return;
        }
        if (i9 <= 15) {
            this.serviceBlockPacket.skipBits(8);
        } else if (i9 <= 23) {
            this.serviceBlockPacket.skipBits(16);
        } else if (i9 <= 31) {
            this.serviceBlockPacket.skipBits(24);
        }
    }

    private void handleC3Command(int i9) {
        if (i9 <= 135) {
            this.serviceBlockPacket.skipBits(32);
            return;
        }
        if (i9 <= COMMAND_RST) {
            this.serviceBlockPacket.skipBits(40);
        } else if (i9 <= 159) {
            this.serviceBlockPacket.skipBits(2);
            this.serviceBlockPacket.skipBits(this.serviceBlockPacket.readBits(6) * 8);
        }
    }

    private void handleDefineWindow(int i9) {
        CueBuilder cueBuilder = this.cueBuilders[i9];
        this.serviceBlockPacket.skipBits(2);
        boolean bit = this.serviceBlockPacket.readBit();
        boolean bit2 = this.serviceBlockPacket.readBit();
        boolean bit3 = this.serviceBlockPacket.readBit();
        int bits = this.serviceBlockPacket.readBits(3);
        boolean bit4 = this.serviceBlockPacket.readBit();
        int bits2 = this.serviceBlockPacket.readBits(7);
        int bits3 = this.serviceBlockPacket.readBits(8);
        int bits4 = this.serviceBlockPacket.readBits(4);
        int bits5 = this.serviceBlockPacket.readBits(4);
        this.serviceBlockPacket.skipBits(2);
        int bits6 = this.serviceBlockPacket.readBits(6);
        this.serviceBlockPacket.skipBits(2);
        cueBuilder.defineWindow(bit, bit2, bit3, bits, bit4, bits2, bits3, bits5, bits6, bits4, this.serviceBlockPacket.readBits(3), this.serviceBlockPacket.readBits(3));
    }

    private void handleG0Character(int i9) {
        if (i9 == 127) {
            this.currentCueBuilder.append((char) 9835);
        } else {
            this.currentCueBuilder.append((char) (i9 & 255));
        }
    }

    private void handleG1Character(int i9) {
        this.currentCueBuilder.append((char) (i9 & 255));
    }

    private void handleG2Character(int i9) {
        if (i9 == 32) {
            this.currentCueBuilder.append(' ');
        }
        if (i9 == 33) {
            this.currentCueBuilder.append((char) 160);
            return;
        }
        if (i9 == 37) {
            this.currentCueBuilder.append((char) 8230);
            return;
        }
        if (i9 == 42) {
            this.currentCueBuilder.append((char) 352);
            return;
        }
        if (i9 == 44) {
            this.currentCueBuilder.append((char) 338);
            return;
        }
        if (i9 == 63) {
            this.currentCueBuilder.append((char) 376);
            return;
        }
        if (i9 == 57) {
            this.currentCueBuilder.append((char) 8482);
            return;
        }
        if (i9 == 58) {
            this.currentCueBuilder.append((char) 353);
            return;
        }
        if (i9 == 60) {
            this.currentCueBuilder.append((char) 339);
            return;
        }
        if (i9 == 61) {
            this.currentCueBuilder.append((char) 8480);
            return;
        }
        switch (i9) {
            case 48:
                this.currentCueBuilder.append((char) 9608);
                break;
            case 49:
                this.currentCueBuilder.append((char) 8216);
                break;
            case 50:
                this.currentCueBuilder.append((char) 8217);
                break;
            case 51:
                this.currentCueBuilder.append((char) 8220);
                break;
            case 52:
                this.currentCueBuilder.append((char) 8221);
                break;
            case 53:
                this.currentCueBuilder.append((char) 8226);
                break;
            default:
                switch (i9) {
                    case 118:
                        this.currentCueBuilder.append((char) 8539);
                        break;
                    case 119:
                        this.currentCueBuilder.append((char) 8540);
                        break;
                    case 120:
                        this.currentCueBuilder.append((char) 8541);
                        break;
                    case 121:
                        this.currentCueBuilder.append((char) 8542);
                        break;
                    case 122:
                        this.currentCueBuilder.append((char) 9474);
                        break;
                    case 123:
                        this.currentCueBuilder.append((char) 9488);
                        break;
                    case 124:
                        this.currentCueBuilder.append((char) 9492);
                        break;
                    case CHARACTER_HORIZONTAL_BORDER /* 125 */:
                        this.currentCueBuilder.append((char) 9472);
                        break;
                    case CHARACTER_LOWER_RIGHT_BORDER /* 126 */:
                        this.currentCueBuilder.append((char) 9496);
                        break;
                    case 127:
                        this.currentCueBuilder.append((char) 9484);
                        break;
                    default:
                        Log.w(TAG, "Invalid G2 character: " + i9);
                        break;
                }
        }
    }

    private void handleG3Character(int i9) {
        if (i9 == 160) {
            this.currentCueBuilder.append((char) 13252);
            return;
        }
        Log.w(TAG, "Invalid G3 character: " + i9);
        this.currentCueBuilder.append('_');
    }

    private void handleSetPenAttributes() {
        this.currentCueBuilder.setPenAttributes(this.serviceBlockPacket.readBits(4), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBit(), this.serviceBlockPacket.readBit(), this.serviceBlockPacket.readBits(3), this.serviceBlockPacket.readBits(3));
    }

    private void handleSetPenColor() {
        int argbColorFromCeaColor = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        int argbColorFromCeaColor2 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        this.serviceBlockPacket.skipBits(2);
        this.currentCueBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2)));
    }

    private void handleSetPenLocation() {
        this.serviceBlockPacket.skipBits(4);
        int bits = this.serviceBlockPacket.readBits(4);
        this.serviceBlockPacket.skipBits(2);
        this.currentCueBuilder.setPenLocation(bits, this.serviceBlockPacket.readBits(6));
    }

    private void handleSetWindowAttributes() {
        int argbColorFromCeaColor = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        int bits = this.serviceBlockPacket.readBits(2);
        int argbColorFromCeaColor2 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        if (this.serviceBlockPacket.readBit()) {
            bits |= 4;
        }
        boolean bit = this.serviceBlockPacket.readBit();
        int bits2 = this.serviceBlockPacket.readBits(2);
        int bits3 = this.serviceBlockPacket.readBits(2);
        int bits4 = this.serviceBlockPacket.readBits(2);
        this.serviceBlockPacket.skipBits(8);
        this.currentCueBuilder.setWindowAttributes(argbColorFromCeaColor, argbColorFromCeaColor2, bit, bits, bits2, bits3, bits4);
    }

    private void processCurrentPacket() {
        DtvCcPacket dtvCcPacket = this.currentDtvCcPacket;
        int i9 = dtvCcPacket.currentIndex;
        if (i9 != (dtvCcPacket.packetSize * 2) - 1) {
            Log.w(TAG, "DtvCcPacket ended prematurely; size is " + ((this.currentDtvCcPacket.packetSize * 2) - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + "); ignoring packet");
            return;
        }
        this.serviceBlockPacket.reset(dtvCcPacket.packetData, i9);
        int bits = this.serviceBlockPacket.readBits(3);
        int bits2 = this.serviceBlockPacket.readBits(5);
        if (bits == 7) {
            this.serviceBlockPacket.skipBits(2);
            bits += this.serviceBlockPacket.readBits(6);
        }
        if (bits2 == 0) {
            if (bits != 0) {
                Log.w(TAG, "serviceNumber is non-zero (" + bits + ") when blockSize is 0");
                return;
            }
            return;
        }
        if (bits != this.selectedServiceNumber) {
            return;
        }
        boolean z8 = false;
        while (this.serviceBlockPacket.bitsLeft() > 0) {
            int bits3 = this.serviceBlockPacket.readBits(8);
            if (bits3 == 16) {
                int bits4 = this.serviceBlockPacket.readBits(8);
                if (bits4 <= 31) {
                    handleC2Command(bits4);
                } else {
                    if (bits4 <= 127) {
                        handleG2Character(bits4);
                    } else if (bits4 <= 159) {
                        handleC3Command(bits4);
                    } else if (bits4 <= 255) {
                        handleG3Character(bits4);
                    } else {
                        Log.w(TAG, "Invalid extended command: " + bits4);
                    }
                    z8 = true;
                }
            } else if (bits3 <= 31) {
                handleC0Command(bits3);
            } else {
                if (bits3 <= 127) {
                    handleG0Character(bits3);
                } else if (bits3 <= 159) {
                    handleC1Command(bits3);
                } else if (bits3 <= 255) {
                    handleG1Character(bits3);
                } else {
                    Log.w(TAG, "Invalid base command: " + bits3);
                }
                z8 = true;
            }
        }
        if (z8) {
            this.cues = getDisplayCues();
        }
    }

    private void resetCueBuilders() {
        for (int i9 = 0; i9 < 8; i9++) {
            this.cueBuilders[i9].reset();
        }
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle(list);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public void decode(SubtitleInputBuffer subtitleInputBuffer) {
        this.ccData.reset(subtitleInputBuffer.data.array(), subtitleInputBuffer.data.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int unsignedByte = this.ccData.readUnsignedByte() & 7;
            int i9 = unsignedByte & 3;
            boolean z8 = (unsignedByte & 4) == 4;
            byte unsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte unsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if (i9 == 2 || i9 == 3) {
                if (z8) {
                    if (i9 == 3) {
                        finalizeCurrentPacket();
                        int i10 = (unsignedByte2 & 192) >> 6;
                        int i11 = unsignedByte2 & 63;
                        if (i11 == 0) {
                            i11 = 64;
                        }
                        DtvCcPacket dtvCcPacket = new DtvCcPacket(i10, i11);
                        this.currentDtvCcPacket = dtvCcPacket;
                        byte[] bArr = dtvCcPacket.packetData;
                        int i12 = dtvCcPacket.currentIndex;
                        dtvCcPacket.currentIndex = i12 + 1;
                        bArr[i12] = unsignedByte3;
                    } else {
                        Assertions.checkArgument(i9 == 2);
                        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
                        if (dtvCcPacket2 == null) {
                            Log.e(TAG, "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr2 = dtvCcPacket2.packetData;
                            int i13 = dtvCcPacket2.currentIndex;
                            int i14 = i13 + 1;
                            bArr2[i13] = unsignedByte2;
                            dtvCcPacket2.currentIndex = i14 + 1;
                            bArr2[i14] = unsignedByte3;
                        }
                    }
                    DtvCcPacket dtvCcPacket3 = this.currentDtvCcPacket;
                    if (dtvCcPacket3.currentIndex == (dtvCcPacket3.packetSize * 2) - 1) {
                        finalizeCurrentPacket();
                    }
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() {
        return super.dequeueInputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() {
        return super.dequeueOutputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueBuilder = this.cueBuilders[0];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public String getName() {
        return TAG;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j9) {
        super.setPositionUs(j9);
    }
}
