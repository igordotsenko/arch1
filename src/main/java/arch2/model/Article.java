package arch2.model;


import java.util.Date;

public class Article {
    private long id;
    private String title;
    private String content;
    private Category category;
    private Date pubDate;
    
    public Article(String title, String content, Category category, Date pubDate) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.pubDate = pubDate;
    }
    
    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public Date getPubDate() {
        return pubDate;
    }
}
