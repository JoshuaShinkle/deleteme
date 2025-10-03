package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
interface Writer {

    public enum FieldOrder {
        ASCENDING,
        DESCENDING
    }

    FieldOrder fieldOrder();

    void writeBool(int i9, boolean z8);

    void writeBoolList(int i9, List<Boolean> list, boolean z8);

    void writeBytes(int i9, ByteString byteString);

    void writeBytesList(int i9, List<ByteString> list);

    void writeDouble(int i9, double d9);

    void writeDoubleList(int i9, List<Double> list, boolean z8);

    @Deprecated
    void writeEndGroup(int i9);

    void writeEnum(int i9, int i10);

    void writeEnumList(int i9, List<Integer> list, boolean z8);

    void writeFixed32(int i9, int i10);

    void writeFixed32List(int i9, List<Integer> list, boolean z8);

    void writeFixed64(int i9, long j9);

    void writeFixed64List(int i9, List<Long> list, boolean z8);

    void writeFloat(int i9, float f9);

    void writeFloatList(int i9, List<Float> list, boolean z8);

    @Deprecated
    void writeGroup(int i9, Object obj);

    @Deprecated
    void writeGroup(int i9, Object obj, Schema schema);

    @Deprecated
    void writeGroupList(int i9, List<?> list);

    @Deprecated
    void writeGroupList(int i9, List<?> list, Schema schema);

    void writeInt32(int i9, int i10);

    void writeInt32List(int i9, List<Integer> list, boolean z8);

    void writeInt64(int i9, long j9);

    void writeInt64List(int i9, List<Long> list, boolean z8);

    <K, V> void writeMap(int i9, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map);

    void writeMessage(int i9, Object obj);

    void writeMessage(int i9, Object obj, Schema schema);

    void writeMessageList(int i9, List<?> list);

    void writeMessageList(int i9, List<?> list, Schema schema);

    void writeMessageSetItem(int i9, Object obj);

    void writeSFixed32(int i9, int i10);

    void writeSFixed32List(int i9, List<Integer> list, boolean z8);

    void writeSFixed64(int i9, long j9);

    void writeSFixed64List(int i9, List<Long> list, boolean z8);

    void writeSInt32(int i9, int i10);

    void writeSInt32List(int i9, List<Integer> list, boolean z8);

    void writeSInt64(int i9, long j9);

    void writeSInt64List(int i9, List<Long> list, boolean z8);

    @Deprecated
    void writeStartGroup(int i9);

    void writeString(int i9, String str);

    void writeStringList(int i9, List<String> list);

    void writeUInt32(int i9, int i10);

    void writeUInt32List(int i9, List<Integer> list, boolean z8);

    void writeUInt64(int i9, long j9);

    void writeUInt64List(int i9, List<Long> list, boolean z8);
}
