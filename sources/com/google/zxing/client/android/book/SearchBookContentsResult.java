package com.google.zxing.client.android.book;

/* loaded from: classes2.dex */
final class SearchBookContentsResult {
    private static String query;
    private final String pageId;
    private final String pageNumber;
    private final String snippet;
    private final boolean validSnippet;

    public SearchBookContentsResult(String str, String str2, String str3, boolean z8) {
        this.pageId = str;
        this.pageNumber = str2;
        this.snippet = str3;
        this.validSnippet = z8;
    }

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String str) {
        query = str;
    }

    public String getPageId() {
        return this.pageId;
    }

    public String getPageNumber() {
        return this.pageNumber;
    }

    public String getSnippet() {
        return this.snippet;
    }

    public boolean getValidSnippet() {
        return this.validSnippet;
    }
}
