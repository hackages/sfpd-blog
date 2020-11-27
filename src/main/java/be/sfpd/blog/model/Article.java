package be.sfpd.blog.model;

import be.sfpd.blog.adapter.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Article {

    private Long id;

    private String author;

    private LocalDateTime createdDate;

    private String body;

    private Map<Long, Comment> comments = new HashMap<>();

    private List<LinkList> linkListArrayList = new ArrayList<>();

    public Article() {
    }

    public Article(Long id, String author, LocalDateTime createdDate, String body) {
        this.id = id;
        this.author = author;
        this.createdDate = createdDate;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public List<LinkList> getLinkArrayList() {
        return linkListArrayList;
    }

    public void setLinkArrayList(List<LinkList> linkListArrayList) {
        this.linkListArrayList = linkListArrayList;
    }

    public void addLink(String url, String rel) {
        LinkList linkList = new LinkList();
        linkList.setLink(url);
        linkList.setRel(rel);
        linkListArrayList.add(linkList);
    }
}
