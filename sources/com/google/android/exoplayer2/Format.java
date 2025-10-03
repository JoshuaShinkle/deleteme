package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class Format implements Parcelable {
    public static final Parcelable.Creator<Format> CREATOR = new Parcelable.Creator<Format>() { // from class: com.google.android.exoplayer2.Format.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Format createFromParcel(Parcel parcel) {
            return new Format(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Format[] newArray(int i9) {
            return new Format[i9];
        }
    };
    public static final int NO_VALUE = -1;
    public static final long OFFSET_SAMPLE_RELATIVE = Long.MAX_VALUE;
    public final int accessibilityChannel;
    public final int bitrate;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    private int hashCode;
    public final int height;

    /* renamed from: id */
    public final String f15298id;
    public final List<byte[]> initializationData;
    public final String language;
    public final int maxInputSize;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int width;

    public Format(String str, String str2, String str3, String str4, int i9, int i10, int i11, int i12, float f9, int i13, float f10, byte[] bArr, int i14, ColorInfo colorInfo, int i15, int i16, int i17, int i18, int i19, int i20, String str5, int i21, long j9, List<byte[]> list, DrmInitData drmInitData, Metadata metadata) {
        this.f15298id = str;
        this.containerMimeType = str2;
        this.sampleMimeType = str3;
        this.codecs = str4;
        this.bitrate = i9;
        this.maxInputSize = i10;
        this.width = i11;
        this.height = i12;
        this.frameRate = f9;
        this.rotationDegrees = i13;
        this.pixelWidthHeightRatio = f10;
        this.projectionData = bArr;
        this.stereoMode = i14;
        this.colorInfo = colorInfo;
        this.channelCount = i15;
        this.sampleRate = i16;
        this.pcmEncoding = i17;
        this.encoderDelay = i18;
        this.encoderPadding = i19;
        this.selectionFlags = i20;
        this.language = str5;
        this.accessibilityChannel = i21;
        this.subsampleOffsetUs = j9;
        this.initializationData = list == null ? Collections.emptyList() : list;
        this.drmInitData = drmInitData;
        this.metadata = metadata;
    }

    public static Format createAudioContainerFormat(String str, String str2, String str3, String str4, int i9, int i10, int i11, List<byte[]> list, int i12, String str5) {
        return new Format(str, str2, str3, str4, i9, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i10, i11, -1, -1, -1, i12, str5, -1, Long.MAX_VALUE, list, null, null);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i9, int i10, int i11, int i12, List<byte[]> list, DrmInitData drmInitData, int i13, String str4) {
        return createAudioSampleFormat(str, str2, str3, i9, i10, i11, i12, -1, list, drmInitData, i13, str4);
    }

    public static Format createContainerFormat(String str, String str2, String str3, String str4, int i9, int i10, String str5) {
        return new Format(str, str2, str3, str4, i9, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i10, str5, -1, Long.MAX_VALUE, null, null, null);
    }

    public static Format createImageSampleFormat(String str, String str2, String str3, int i9, int i10, List<byte[]> list, String str4, DrmInitData drmInitData) {
        return new Format(str, null, str2, str3, i9, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i10, str4, -1, Long.MAX_VALUE, list, drmInitData, null);
    }

    public static Format createSampleFormat(String str, String str2, long j9) {
        return new Format(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, j9, null, null, null);
    }

    public static Format createTextContainerFormat(String str, String str2, String str3, String str4, int i9, int i10, String str5) {
        return createTextContainerFormat(str, str2, str3, str4, i9, i10, str5, -1);
    }

    public static Format createTextSampleFormat(String str, String str2, int i9, String str3) {
        return createTextSampleFormat(str, str2, i9, str3, null);
    }

    public static Format createVideoContainerFormat(String str, String str2, String str3, String str4, int i9, int i10, int i11, float f9, List<byte[]> list, int i12) {
        return new Format(str, str2, str3, str4, i9, -1, i10, i11, f9, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i12, null, -1, Long.MAX_VALUE, list, null, null);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i9, int i10, int i11, int i12, float f9, List<byte[]> list, DrmInitData drmInitData) {
        return createVideoSampleFormat(str, str2, str3, i9, i10, i11, i12, f9, list, -1, -1.0f, drmInitData);
    }

    @TargetApi(16)
    private static void maybeSetByteBufferV16(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    @TargetApi(24)
    private static void maybeSetColorInfoV24(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo == null) {
            return;
        }
        maybeSetIntegerV16(mediaFormat, "color-transfer", colorInfo.colorTransfer);
        maybeSetIntegerV16(mediaFormat, "color-standard", colorInfo.colorSpace);
        maybeSetIntegerV16(mediaFormat, "color-range", colorInfo.colorRange);
        maybeSetByteBufferV16(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
    }

    @TargetApi(16)
    private static void maybeSetFloatV16(MediaFormat mediaFormat, String str, float f9) {
        if (f9 != -1.0f) {
            mediaFormat.setFloat(str, f9);
        }
    }

    @TargetApi(16)
    private static void maybeSetIntegerV16(MediaFormat mediaFormat, String str, int i9) {
        if (i9 != -1) {
            mediaFormat.setInteger(str, i9);
        }
    }

    @TargetApi(16)
    private static void maybeSetStringV16(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    public static String toLogString(Format format) {
        if (format == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("id=");
        sb.append(format.f15298id);
        sb.append(", mimeType=");
        sb.append(format.sampleMimeType);
        if (format.bitrate != -1) {
            sb.append(", bitrate=");
            sb.append(format.bitrate);
        }
        if (format.width != -1 && format.height != -1) {
            sb.append(", res=");
            sb.append(format.width);
            sb.append("x");
            sb.append(format.height);
        }
        if (format.frameRate != -1.0f) {
            sb.append(", fps=");
            sb.append(format.frameRate);
        }
        if (format.channelCount != -1) {
            sb.append(", channels=");
            sb.append(format.channelCount);
        }
        if (format.sampleRate != -1) {
            sb.append(", sample_rate=");
            sb.append(format.sampleRate);
        }
        if (format.language != null) {
            sb.append(", language=");
            sb.append(format.language);
        }
        return sb.toString();
    }

    public Format copyWithContainerInfo(String str, String str2, String str3, int i9, int i10, int i11, int i12, String str4) {
        return new Format(str, this.containerMimeType, str2, str3, i9, this.maxInputSize, i10, i11, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, i12, str4, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithDrmInitData(DrmInitData drmInitData) {
        return new Format(this.f15298id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, drmInitData, this.metadata);
    }

    public Format copyWithGaplessInfo(int i9, int i10) {
        return new Format(this.f15298id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, i9, i10, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithManifestFormatInfo(Format format) {
        if (this == format) {
            return this;
        }
        String str = format.f15298id;
        String str2 = this.codecs;
        if (str2 == null) {
            str2 = format.codecs;
        }
        String str3 = str2;
        int i9 = this.bitrate;
        if (i9 == -1) {
            i9 = format.bitrate;
        }
        int i10 = i9;
        float f9 = this.frameRate;
        if (f9 == -1.0f) {
            f9 = format.frameRate;
        }
        float f10 = f9;
        int i11 = this.selectionFlags | format.selectionFlags;
        String str4 = this.language;
        if (str4 == null) {
            str4 = format.language;
        }
        return new Format(str, this.containerMimeType, this.sampleMimeType, str3, i10, this.maxInputSize, this.width, this.height, f10, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, i11, str4, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, DrmInitData.createSessionCreationData(format.drmInitData, this.drmInitData), this.metadata);
    }

    public Format copyWithMaxInputSize(int i9) {
        return new Format(this.f15298id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, i9, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithMetadata(Metadata metadata) {
        return new Format(this.f15298id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, metadata);
    }

    public Format copyWithRotationDegrees(int i9) {
        return new Format(this.f15298id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, i9, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithSubsampleOffsetUs(long j9) {
        return new Format(this.f15298id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, j9, this.initializationData, this.drmInitData, this.metadata);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Format.class == obj.getClass()) {
            Format format = (Format) obj;
            if (this.bitrate == format.bitrate && this.maxInputSize == format.maxInputSize && this.width == format.width && this.height == format.height && this.frameRate == format.frameRate && this.rotationDegrees == format.rotationDegrees && this.pixelWidthHeightRatio == format.pixelWidthHeightRatio && this.stereoMode == format.stereoMode && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.subsampleOffsetUs == format.subsampleOffsetUs && this.selectionFlags == format.selectionFlags && Util.areEqual(this.f15298id, format.f15298id) && Util.areEqual(this.language, format.language) && this.accessibilityChannel == format.accessibilityChannel && Util.areEqual(this.containerMimeType, format.containerMimeType) && Util.areEqual(this.sampleMimeType, format.sampleMimeType) && Util.areEqual(this.codecs, format.codecs) && Util.areEqual(this.drmInitData, format.drmInitData) && Util.areEqual(this.metadata, format.metadata) && Util.areEqual(this.colorInfo, format.colorInfo) && Arrays.equals(this.projectionData, format.projectionData) && this.initializationData.size() == format.initializationData.size()) {
                for (int i9 = 0; i9 < this.initializationData.size(); i9++) {
                    if (!Arrays.equals(this.initializationData.get(i9), format.initializationData.get(i9))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final MediaFormat getFrameworkMediaFormatV16() {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", this.sampleMimeType);
        maybeSetStringV16(mediaFormat, "language", this.language);
        maybeSetIntegerV16(mediaFormat, "max-input-size", this.maxInputSize);
        maybeSetIntegerV16(mediaFormat, "width", this.width);
        maybeSetIntegerV16(mediaFormat, "height", this.height);
        maybeSetFloatV16(mediaFormat, "frame-rate", this.frameRate);
        maybeSetIntegerV16(mediaFormat, "rotation-degrees", this.rotationDegrees);
        maybeSetIntegerV16(mediaFormat, "channel-count", this.channelCount);
        maybeSetIntegerV16(mediaFormat, "sample-rate", this.sampleRate);
        for (int i9 = 0; i9 < this.initializationData.size(); i9++) {
            mediaFormat.setByteBuffer("csd-" + i9, ByteBuffer.wrap(this.initializationData.get(i9)));
        }
        maybeSetColorInfoV24(mediaFormat, this.colorInfo);
        return mediaFormat;
    }

    public int getPixelCount() {
        int i9;
        int i10 = this.width;
        if (i10 == -1 || (i9 = this.height) == -1) {
            return -1;
        }
        return i10 * i9;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.f15298id;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.containerMimeType;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.sampleMimeType;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.codecs;
            int iHashCode4 = (((((((((((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.bitrate) * 31) + this.width) * 31) + this.height) * 31) + this.channelCount) * 31) + this.sampleRate) * 31;
            String str5 = this.language;
            int iHashCode5 = (((iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.accessibilityChannel) * 31;
            DrmInitData drmInitData = this.drmInitData;
            int iHashCode6 = (iHashCode5 + (drmInitData == null ? 0 : drmInitData.hashCode())) * 31;
            Metadata metadata = this.metadata;
            this.hashCode = iHashCode6 + (metadata != null ? metadata.hashCode() : 0);
        }
        return this.hashCode;
    }

    public String toString() {
        return "Format(" + this.f15298id + ", " + this.containerMimeType + ", " + this.sampleMimeType + ", " + this.bitrate + ", " + this.language + ", [" + this.width + ", " + this.height + ", " + this.frameRate + "], [" + this.channelCount + ", " + this.sampleRate + "])";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f15298id);
        parcel.writeString(this.containerMimeType);
        parcel.writeString(this.sampleMimeType);
        parcel.writeString(this.codecs);
        parcel.writeInt(this.bitrate);
        parcel.writeInt(this.maxInputSize);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeFloat(this.frameRate);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeFloat(this.pixelWidthHeightRatio);
        parcel.writeInt(this.projectionData != null ? 1 : 0);
        byte[] bArr = this.projectionData;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.stereoMode);
        parcel.writeParcelable(this.colorInfo, i9);
        parcel.writeInt(this.channelCount);
        parcel.writeInt(this.sampleRate);
        parcel.writeInt(this.pcmEncoding);
        parcel.writeInt(this.encoderDelay);
        parcel.writeInt(this.encoderPadding);
        parcel.writeInt(this.selectionFlags);
        parcel.writeString(this.language);
        parcel.writeInt(this.accessibilityChannel);
        parcel.writeLong(this.subsampleOffsetUs);
        int size = this.initializationData.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeByteArray(this.initializationData.get(i10));
        }
        parcel.writeParcelable(this.drmInitData, 0);
        parcel.writeParcelable(this.metadata, 0);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i9, int i10, int i11, int i12, int i13, List<byte[]> list, DrmInitData drmInitData, int i14, String str4) {
        return createAudioSampleFormat(str, str2, str3, i9, i10, i11, i12, i13, -1, -1, list, drmInitData, i14, str4, null);
    }

    public static Format createSampleFormat(String str, String str2, String str3, int i9, DrmInitData drmInitData) {
        return new Format(str, null, str2, str3, i9, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, drmInitData, null);
    }

    public static Format createTextContainerFormat(String str, String str2, String str3, String str4, int i9, int i10, String str5, int i11) {
        return new Format(str, str2, str3, str4, i9, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i10, str5, i11, Long.MAX_VALUE, null, null, null);
    }

    public static Format createTextSampleFormat(String str, String str2, int i9, String str3, DrmInitData drmInitData) {
        return createTextSampleFormat(str, str2, null, -1, i9, str3, -1, drmInitData, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i9, int i10, int i11, int i12, float f9, List<byte[]> list, int i13, float f10, DrmInitData drmInitData) {
        return createVideoSampleFormat(str, str2, str3, i9, i10, i11, i12, f9, list, i13, f10, null, -1, null, drmInitData);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i9, int i10, int i11, int i12, int i13, int i14, int i15, List<byte[]> list, DrmInitData drmInitData, int i16, String str4, Metadata metadata) {
        return new Format(str, null, str2, str3, i9, i10, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i11, i12, i13, i14, i15, i16, str4, -1, Long.MAX_VALUE, list, drmInitData, metadata);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i9, int i10, int i11, int i12, float f9, List<byte[]> list, int i13, float f10, byte[] bArr, int i14, ColorInfo colorInfo, DrmInitData drmInitData) {
        return new Format(str, null, str2, str3, i9, i10, i11, i12, f9, i13, f10, bArr, i14, colorInfo, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, list, drmInitData, null);
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i9, int i10, String str4, int i11, DrmInitData drmInitData) {
        return createTextSampleFormat(str, str2, str3, i9, i10, str4, i11, drmInitData, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i9, int i10, String str4, DrmInitData drmInitData, long j9) {
        return createTextSampleFormat(str, str2, str3, i9, i10, str4, -1, drmInitData, j9, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i9, int i10, String str4, int i11, DrmInitData drmInitData, long j9, List<byte[]> list) {
        return new Format(str, null, str2, str3, i9, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i10, str4, i11, j9, list, drmInitData, null);
    }

    public Format(Parcel parcel) {
        this.f15298id = parcel.readString();
        this.containerMimeType = parcel.readString();
        this.sampleMimeType = parcel.readString();
        this.codecs = parcel.readString();
        this.bitrate = parcel.readInt();
        this.maxInputSize = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.frameRate = parcel.readFloat();
        this.rotationDegrees = parcel.readInt();
        this.pixelWidthHeightRatio = parcel.readFloat();
        this.projectionData = parcel.readInt() != 0 ? parcel.createByteArray() : null;
        this.stereoMode = parcel.readInt();
        this.colorInfo = (ColorInfo) parcel.readParcelable(ColorInfo.class.getClassLoader());
        this.channelCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.pcmEncoding = parcel.readInt();
        this.encoderDelay = parcel.readInt();
        this.encoderPadding = parcel.readInt();
        this.selectionFlags = parcel.readInt();
        this.language = parcel.readString();
        this.accessibilityChannel = parcel.readInt();
        this.subsampleOffsetUs = parcel.readLong();
        int i9 = parcel.readInt();
        this.initializationData = new ArrayList(i9);
        for (int i10 = 0; i10 < i9; i10++) {
            this.initializationData.add(parcel.createByteArray());
        }
        this.drmInitData = (DrmInitData) parcel.readParcelable(DrmInitData.class.getClassLoader());
        this.metadata = (Metadata) parcel.readParcelable(Metadata.class.getClassLoader());
    }
}
