package be.sfpd.blog.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LinkList {

    private String link;

    private String rel;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
