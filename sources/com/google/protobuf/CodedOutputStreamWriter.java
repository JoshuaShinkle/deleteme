package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class CodedOutputStreamWriter implements Writer {
    private final CodedOutputStream output;

    /* renamed from: com.google.protobuf.CodedOutputStreamWriter$1 */
    public static /* synthetic */ class C43911 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private CodedOutputStreamWriter(CodedOutputStream codedOutputStream) {
        CodedOutputStream codedOutputStream2 = (CodedOutputStream) Internal.checkNotNull(codedOutputStream, "output");
        this.output = codedOutputStream2;
        codedOutputStream2.wrapper = this;
    }

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream codedOutputStream) {
        CodedOutputStreamWriter codedOutputStreamWriter = codedOutputStream.wrapper;
        return codedOutputStreamWriter != null ? codedOutputStreamWriter : new CodedOutputStreamWriter(codedOutputStream);
    }

    private <V> void writeDeterministicBooleanMapEntry(int i9, boolean z8, V v8, MapEntryLite.Metadata<Boolean, V> metadata) {
        this.output.writeTag(i9, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Boolean.valueOf(z8), v8));
        MapEntryLite.writeTo(this.output, metadata, Boolean.valueOf(z8), v8);
    }

    private <V> void writeDeterministicIntegerMap(int i9, MapEntryLite.Metadata<Integer, V> metadata, Map<Integer, V> map) {
        int size = map.size();
        int[] iArr = new int[size];
        Iterator<Integer> it = map.keySet().iterator();
        int i10 = 0;
        while (it.hasNext()) {
            iArr[i10] = it.next().intValue();
            i10++;
        }
        Arrays.sort(iArr);
        for (int i11 = 0; i11 < size; i11++) {
            int i12 = iArr[i11];
            V v8 = map.get(Integer.valueOf(i12));
            this.output.writeTag(i9, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Integer.valueOf(i12), v8));
            MapEntryLite.writeTo(this.output, metadata, Integer.valueOf(i12), v8);
        }
    }

    private <V> void writeDeterministicLongMap(int i9, MapEntryLite.Metadata<Long, V> metadata, Map<Long, V> map) {
        int size = map.size();
        long[] jArr = new long[size];
        Iterator<Long> it = map.keySet().iterator();
        int i10 = 0;
        while (it.hasNext()) {
            jArr[i10] = it.next().longValue();
            i10++;
        }
        Arrays.sort(jArr);
        for (int i11 = 0; i11 < size; i11++) {
            long j9 = jArr[i11];
            V v8 = map.get(Long.valueOf(j9));
            this.output.writeTag(i9, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Long.valueOf(j9), v8));
            MapEntryLite.writeTo(this.output, metadata, Long.valueOf(j9), v8);
        }
    }

    private <K, V> void writeDeterministicMap(int i9, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        switch (C43911.$SwitchMap$com$google$protobuf$WireFormat$FieldType[metadata.keyType.ordinal()]) {
            case 1:
                V v8 = map.get(Boolean.FALSE);
                if (v8 != null) {
                    writeDeterministicBooleanMapEntry(i9, false, v8, metadata);
                }
                V v9 = map.get(Boolean.TRUE);
                if (v9 != null) {
                    writeDeterministicBooleanMapEntry(i9, true, v9, metadata);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                writeDeterministicIntegerMap(i9, metadata, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                writeDeterministicLongMap(i9, metadata, map);
                return;
            case 12:
                writeDeterministicStringMap(i9, metadata, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + metadata.keyType);
        }
    }

    private <V> void writeDeterministicStringMap(int i9, MapEntryLite.Metadata<String, V> metadata, Map<String, V> map) {
        int size = map.size();
        String[] strArr = new String[size];
        Iterator<String> it = map.keySet().iterator();
        int i10 = 0;
        while (it.hasNext()) {
            strArr[i10] = it.next();
            i10++;
        }
        Arrays.sort(strArr);
        for (int i11 = 0; i11 < size; i11++) {
            String str = strArr[i11];
            V v8 = map.get(str);
            this.output.writeTag(i9, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, str, v8));
            MapEntryLite.writeTo(this.output, metadata, str, v8);
        }
    }

    private void writeLazyString(int i9, Object obj) {
        if (obj instanceof String) {
            this.output.writeString(i9, (String) obj);
        } else {
            this.output.writeBytes(i9, (ByteString) obj);
        }
    }

    @Override // com.google.protobuf.Writer
    public Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.ASCENDING;
    }

    public int getTotalBytesWritten() {
        return this.output.getTotalBytesWritten();
    }

    @Override // com.google.protobuf.Writer
    public void writeBool(int i9, boolean z8) {
        this.output.writeBool(i9, z8);
    }

    @Override // com.google.protobuf.Writer
    public void writeBoolList(int i9, List<Boolean> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeBool(i9, list.get(i10).booleanValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeBoolSizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeBoolSizeNoTag += CodedOutputStream.computeBoolSizeNoTag(list.get(i11).booleanValue());
        }
        this.output.writeUInt32NoTag(iComputeBoolSizeNoTag);
        while (i10 < list.size()) {
            this.output.writeBoolNoTag(list.get(i10).booleanValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeBytes(int i9, ByteString byteString) {
        this.output.writeBytes(i9, byteString);
    }

    @Override // com.google.protobuf.Writer
    public void writeBytesList(int i9, List<ByteString> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            this.output.writeBytes(i9, list.get(i10));
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeDouble(int i9, double d9) {
        this.output.writeDouble(i9, d9);
    }

    @Override // com.google.protobuf.Writer
    public void writeDoubleList(int i9, List<Double> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeDouble(i9, list.get(i10).doubleValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeDoubleSizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeDoubleSizeNoTag += CodedOutputStream.computeDoubleSizeNoTag(list.get(i11).doubleValue());
        }
        this.output.writeUInt32NoTag(iComputeDoubleSizeNoTag);
        while (i10 < list.size()) {
            this.output.writeDoubleNoTag(list.get(i10).doubleValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeEndGroup(int i9) {
        this.output.writeTag(i9, 4);
    }

    @Override // com.google.protobuf.Writer
    public void writeEnum(int i9, int i10) {
        this.output.writeEnum(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public void writeEnumList(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeEnum(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeEnumSizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeEnumSizeNoTag += CodedOutputStream.computeEnumSizeNoTag(list.get(i11).intValue());
        }
        this.output.writeUInt32NoTag(iComputeEnumSizeNoTag);
        while (i10 < list.size()) {
            this.output.writeEnumNoTag(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed32(int i9, int i10) {
        this.output.writeFixed32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed32List(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeFixed32(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeFixed32SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeFixed32SizeNoTag += CodedOutputStream.computeFixed32SizeNoTag(list.get(i11).intValue());
        }
        this.output.writeUInt32NoTag(iComputeFixed32SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeFixed32NoTag(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed64(int i9, long j9) {
        this.output.writeFixed64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed64List(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeFixed64(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeFixed64SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeFixed64SizeNoTag += CodedOutputStream.computeFixed64SizeNoTag(list.get(i11).longValue());
        }
        this.output.writeUInt32NoTag(iComputeFixed64SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeFixed64NoTag(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeFloat(int i9, float f9) {
        this.output.writeFloat(i9, f9);
    }

    @Override // com.google.protobuf.Writer
    public void writeFloatList(int i9, List<Float> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeFloat(i9, list.get(i10).floatValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeFloatSizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeFloatSizeNoTag += CodedOutputStream.computeFloatSizeNoTag(list.get(i11).floatValue());
        }
        this.output.writeUInt32NoTag(iComputeFloatSizeNoTag);
        while (i10 < list.size()) {
            this.output.writeFloatNoTag(list.get(i10).floatValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeGroup(int i9, Object obj) {
        this.output.writeGroup(i9, (MessageLite) obj);
    }

    @Override // com.google.protobuf.Writer
    public void writeGroupList(int i9, List<?> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            writeGroup(i9, list.get(i10));
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeInt32(int i9, int i10) {
        this.output.writeInt32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public void writeInt32List(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeInt32(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeInt32SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(list.get(i11).intValue());
        }
        this.output.writeUInt32NoTag(iComputeInt32SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeInt32NoTag(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeInt64(int i9, long j9) {
        this.output.writeInt64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public void writeInt64List(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeInt64(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeInt64SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeInt64SizeNoTag += CodedOutputStream.computeInt64SizeNoTag(list.get(i11).longValue());
        }
        this.output.writeUInt32NoTag(iComputeInt64SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeInt64NoTag(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public <K, V> void writeMap(int i9, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        if (this.output.isSerializationDeterministic()) {
            writeDeterministicMap(i9, metadata, map);
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.output.writeTag(i9, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue()));
            MapEntryLite.writeTo(this.output, metadata, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeMessage(int i9, Object obj) {
        this.output.writeMessage(i9, (MessageLite) obj);
    }

    @Override // com.google.protobuf.Writer
    public void writeMessageList(int i9, List<?> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            writeMessage(i9, list.get(i10));
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageSetItem(int i9, Object obj) {
        if (obj instanceof ByteString) {
            this.output.writeRawMessageSetExtension(i9, (ByteString) obj);
        } else {
            this.output.writeMessageSetExtension(i9, (MessageLite) obj);
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed32(int i9, int i10) {
        this.output.writeSFixed32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed32List(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeSFixed32(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeSFixed32SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeSFixed32SizeNoTag += CodedOutputStream.computeSFixed32SizeNoTag(list.get(i11).intValue());
        }
        this.output.writeUInt32NoTag(iComputeSFixed32SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeSFixed32NoTag(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed64(int i9, long j9) {
        this.output.writeSFixed64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed64List(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeSFixed64(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeSFixed64SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeSFixed64SizeNoTag += CodedOutputStream.computeSFixed64SizeNoTag(list.get(i11).longValue());
        }
        this.output.writeUInt32NoTag(iComputeSFixed64SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeSFixed64NoTag(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt32(int i9, int i10) {
        this.output.writeSInt32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt32List(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeSInt32(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeSInt32SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeSInt32SizeNoTag += CodedOutputStream.computeSInt32SizeNoTag(list.get(i11).intValue());
        }
        this.output.writeUInt32NoTag(iComputeSInt32SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeSInt32NoTag(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt64(int i9, long j9) {
        this.output.writeSInt64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt64List(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeSInt64(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeSInt64SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeSInt64SizeNoTag += CodedOutputStream.computeSInt64SizeNoTag(list.get(i11).longValue());
        }
        this.output.writeUInt32NoTag(iComputeSInt64SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeSInt64NoTag(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeStartGroup(int i9) {
        this.output.writeTag(i9, 3);
    }

    @Override // com.google.protobuf.Writer
    public void writeString(int i9, String str) {
        this.output.writeString(i9, str);
    }

    @Override // com.google.protobuf.Writer
    public void writeStringList(int i9, List<String> list) {
        int i10 = 0;
        if (!(list instanceof LazyStringList)) {
            while (i10 < list.size()) {
                this.output.writeString(i9, list.get(i10));
                i10++;
            }
        } else {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i10 < list.size()) {
                writeLazyString(i9, lazyStringList.getRaw(i10));
                i10++;
            }
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt32(int i9, int i10) {
        this.output.writeUInt32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt32List(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeUInt32(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeUInt32SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(list.get(i11).intValue());
        }
        this.output.writeUInt32NoTag(iComputeUInt32SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeUInt32NoTag(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt64(int i9, long j9) {
        this.output.writeUInt64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt64List(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.output.writeUInt64(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.output.writeTag(i9, 2);
        int iComputeUInt64SizeNoTag = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iComputeUInt64SizeNoTag += CodedOutputStream.computeUInt64SizeNoTag(list.get(i11).longValue());
        }
        this.output.writeUInt32NoTag(iComputeUInt64SizeNoTag);
        while (i10 < list.size()) {
            this.output.writeUInt64NoTag(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeGroup(int i9, Object obj, Schema schema) {
        this.output.writeGroup(i9, (MessageLite) obj, schema);
    }

    @Override // com.google.protobuf.Writer
    public void writeMessage(int i9, Object obj, Schema schema) {
        this.output.writeMessage(i9, (MessageLite) obj, schema);
    }

    @Override // com.google.protobuf.Writer
    public void writeGroupList(int i9, List<?> list, Schema schema) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            writeGroup(i9, list.get(i10), schema);
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeMessageList(int i9, List<?> list, Schema schema) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            writeMessage(i9, list.get(i10), schema);
        }
    }
}
