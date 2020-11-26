package be.sfpd.blog.resource.beans;

import javax.ws.rs.QueryParam;

public class ArticleFilterBean {

    private @QueryParam("year") int year;
    private @QueryParam("offset") int offset;
    private @QueryParam("limit") int limit;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
