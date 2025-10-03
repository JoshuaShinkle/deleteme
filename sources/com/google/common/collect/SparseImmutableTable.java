package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.LinkedHashMap;
import java.util.Map;

@GwtCompatible
/* loaded from: classes2.dex */
final class SparseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    static final ImmutableTable<Object, Object, Object> EMPTY = new SparseImmutableTable(ImmutableList.m17573of(), ImmutableSet.m17615of(), ImmutableSet.m17615of());
    private final int[] cellColumnInRowIndices;
    private final int[] cellRowIndices;
    private final ImmutableMap<C, Map<R, V>> columnMap;
    private final ImmutableMap<R, Map<C, V>> rowMap;

    public SparseImmutableTable(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        ImmutableMap immutableMapIndexMap = Maps.indexMap(immutableSet);
        LinkedHashMap linkedHashMapNewLinkedHashMap = Maps.newLinkedHashMap();
        UnmodifiableIterator<R> it = immutableSet.iterator();
        while (it.hasNext()) {
            linkedHashMapNewLinkedHashMap.put(it.next(), new LinkedHashMap());
        }
        LinkedHashMap linkedHashMapNewLinkedHashMap2 = Maps.newLinkedHashMap();
        UnmodifiableIterator<C> it2 = immutableSet2.iterator();
        while (it2.hasNext()) {
            linkedHashMapNewLinkedHashMap2.put(it2.next(), new LinkedHashMap());
        }
        int[] iArr = new int[immutableList.size()];
        int[] iArr2 = new int[immutableList.size()];
        for (int i9 = 0; i9 < immutableList.size(); i9++) {
            Table.Cell<R, C, V> cell = immutableList.get(i9);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            V value = cell.getValue();
            iArr[i9] = ((Integer) immutableMapIndexMap.get(rowKey)).intValue();
            Map map = (Map) linkedHashMapNewLinkedHashMap.get(rowKey);
            iArr2[i9] = map.size();
            Object objPut = map.put(columnKey, value);
            if (objPut != null) {
                throw new IllegalArgumentException("Duplicate value for row=" + rowKey + ", column=" + columnKey + ": " + value + ", " + objPut);
            }
            ((Map) linkedHashMapNewLinkedHashMap2.get(columnKey)).put(rowKey, value);
        }
        this.cellRowIndices = iArr;
        this.cellColumnInRowIndices = iArr2;
        ImmutableMap.Builder builder = new ImmutableMap.Builder(linkedHashMapNewLinkedHashMap.size());
        for (Map.Entry entry : linkedHashMapNewLinkedHashMap.entrySet()) {
            builder.put(entry.getKey(), ImmutableMap.copyOf((Map) entry.getValue()));
        }
        this.rowMap = builder.build();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder(linkedHashMapNewLinkedHashMap2.size());
        for (Map.Entry entry2 : linkedHashMapNewLinkedHashMap2.entrySet()) {
            builder2.put(entry2.getKey(), ImmutableMap.copyOf((Map) entry2.getValue()));
        }
        this.columnMap = builder2.build();
    }

    @Override // com.google.common.collect.ImmutableTable
    public ImmutableTable.SerializedForm createSerializedForm() {
        ImmutableMap immutableMapIndexMap = Maps.indexMap(columnKeySet());
        int[] iArr = new int[cellSet().size()];
        UnmodifiableIterator<Table.Cell<R, C, V>> it = cellSet().iterator();
        int i9 = 0;
        while (it.hasNext()) {
            iArr[i9] = ((Integer) immutableMapIndexMap.get(it.next().getColumnKey())).intValue();
            i9++;
        }
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, iArr);
    }

    @Override // com.google.common.collect.RegularImmutableTable
    public Table.Cell<R, C, V> getCell(int i9) {
        Map.Entry<R, Map<C, V>> entry = this.rowMap.entrySet().asList().get(this.cellRowIndices[i9]);
        ImmutableMap immutableMap = (ImmutableMap) entry.getValue();
        Map.Entry entry2 = (Map.Entry) immutableMap.entrySet().asList().get(this.cellColumnInRowIndices[i9]);
        return ImmutableTable.cellOf(entry.getKey(), entry2.getKey(), entry2.getValue());
    }

    @Override // com.google.common.collect.RegularImmutableTable
    public V getValue(int i9) {
        ImmutableMap immutableMap = (ImmutableMap) this.rowMap.values().asList().get(this.cellRowIndices[i9]);
        return immutableMap.values().asList().get(this.cellColumnInRowIndices[i9]);
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return this.columnMap;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return this.rowMap;
    }
}
