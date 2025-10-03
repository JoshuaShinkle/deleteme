package net.sqlcipher.database;

/* loaded from: classes2.dex */
public class SQLiteQueryStats {
    long largestIndividualRowSize;
    long totalQueryResultSize;

    public SQLiteQueryStats(long j9, long j10) {
        this.totalQueryResultSize = j9;
        this.largestIndividualRowSize = j10;
    }

    public long getLargestIndividualRowSize() {
        return this.largestIndividualRowSize;
    }

    public long getTotalQueryResultSize() {
        return this.totalQueryResultSize;
    }
}
