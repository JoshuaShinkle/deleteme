package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
abstract class ListFieldSchema {
    private static final ListFieldSchema FULL_INSTANCE;
    private static final ListFieldSchema LITE_INSTANCE;

    public static final class ListFieldSchemaFull extends ListFieldSchema {
        private static final Class<?> UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();

        private ListFieldSchemaFull() {
            super();
        }

        public static <E> List<E> getList(Object obj, long j9) {
            return (List) UnsafeUtil.getObject(obj, j9);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public void makeImmutableListAt(Object obj, long j9) {
            Object objUnmodifiableList;
            List list = (List) UnsafeUtil.getObject(obj, j9);
            if (list instanceof LazyStringList) {
                objUnmodifiableList = ((LazyStringList) list).getUnmodifiableView();
            } else {
                if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                    return;
                }
                if ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof Internal.ProtobufList)) {
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) list;
                    if (protobufList.isModifiable()) {
                        protobufList.makeImmutable();
                        return;
                    }
                    return;
                }
                objUnmodifiableList = Collections.unmodifiableList(list);
            }
            UnsafeUtil.putObject(obj, j9, objUnmodifiableList);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public <E> void mergeListsAt(Object obj, Object obj2, long j9) {
            List list = getList(obj2, j9);
            List listMutableListAt = mutableListAt(obj, j9, list.size());
            int size = listMutableListAt.size();
            int size2 = list.size();
            if (size > 0 && size2 > 0) {
                listMutableListAt.addAll(list);
            }
            if (size > 0) {
                list = listMutableListAt;
            }
            UnsafeUtil.putObject(obj, j9, list);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public <L> List<L> mutableListAt(Object obj, long j9) {
            return mutableListAt(obj, j9, 10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static <L> List<L> mutableListAt(Object obj, long j9, int i9) {
            LazyStringArrayList lazyStringArrayList;
            List<L> list = getList(obj, j9);
            if (list.isEmpty()) {
                List<L> lazyStringArrayList2 = list instanceof LazyStringList ? new LazyStringArrayList(i9) : ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof Internal.ProtobufList)) ? ((Internal.ProtobufList) list).mutableCopyWithCapacity2(i9) : new ArrayList<>(i9);
                UnsafeUtil.putObject(obj, j9, lazyStringArrayList2);
                return lazyStringArrayList2;
            }
            if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                ArrayList arrayList = new ArrayList(list.size() + i9);
                arrayList.addAll(list);
                UnsafeUtil.putObject(obj, j9, arrayList);
                lazyStringArrayList = arrayList;
            } else {
                if (!(list instanceof UnmodifiableLazyStringList)) {
                    if (!(list instanceof PrimitiveNonBoxingCollection) || !(list instanceof Internal.ProtobufList)) {
                        return list;
                    }
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) list;
                    if (protobufList.isModifiable()) {
                        return list;
                    }
                    Internal.ProtobufList protobufListMutableCopyWithCapacity2 = protobufList.mutableCopyWithCapacity2(list.size() + i9);
                    UnsafeUtil.putObject(obj, j9, protobufListMutableCopyWithCapacity2);
                    return protobufListMutableCopyWithCapacity2;
                }
                LazyStringArrayList lazyStringArrayList3 = new LazyStringArrayList(list.size() + i9);
                lazyStringArrayList3.addAll((UnmodifiableLazyStringList) list);
                UnsafeUtil.putObject(obj, j9, lazyStringArrayList3);
                lazyStringArrayList = lazyStringArrayList3;
            }
            return lazyStringArrayList;
        }
    }

    public static final class ListFieldSchemaLite extends ListFieldSchema {
        private ListFieldSchemaLite() {
            super();
        }

        public static <E> Internal.ProtobufList<E> getProtobufList(Object obj, long j9) {
            return (Internal.ProtobufList) UnsafeUtil.getObject(obj, j9);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public void makeImmutableListAt(Object obj, long j9) {
            getProtobufList(obj, j9).makeImmutable();
        }

        @Override // com.google.protobuf.ListFieldSchema
        public <E> void mergeListsAt(Object obj, Object obj2, long j9) {
            Internal.ProtobufList protobufList = getProtobufList(obj, j9);
            Internal.ProtobufList protobufList2 = getProtobufList(obj2, j9);
            int size = protobufList.size();
            int size2 = protobufList2.size();
            if (size > 0 && size2 > 0) {
                if (!protobufList.isModifiable()) {
                    protobufList = protobufList.mutableCopyWithCapacity2(size2 + size);
                }
                protobufList.addAll(protobufList2);
            }
            if (size > 0) {
                protobufList2 = protobufList;
            }
            UnsafeUtil.putObject(obj, j9, protobufList2);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public <L> List<L> mutableListAt(Object obj, long j9) {
            Internal.ProtobufList protobufList = getProtobufList(obj, j9);
            if (protobufList.isModifiable()) {
                return protobufList;
            }
            int size = protobufList.size();
            Internal.ProtobufList protobufListMutableCopyWithCapacity2 = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UnsafeUtil.putObject(obj, j9, protobufListMutableCopyWithCapacity2);
            return protobufListMutableCopyWithCapacity2;
        }
    }

    static {
        FULL_INSTANCE = new ListFieldSchemaFull();
        LITE_INSTANCE = new ListFieldSchemaLite();
    }

    private ListFieldSchema() {
    }

    public static ListFieldSchema full() {
        return FULL_INSTANCE;
    }

    public static ListFieldSchema lite() {
        return LITE_INSTANCE;
    }

    public abstract void makeImmutableListAt(Object obj, long j9);

    public abstract <L> void mergeListsAt(Object obj, Object obj2, long j9);

    public abstract <L> List<L> mutableListAt(Object obj, long j9);
}
