package arch2.model;

import java.util.Set;

public class Category {
    String name;
    String url;
    Set<Article> articles;
    
    public Category(String name, String url) {
        this.name = name;
        this.url = url;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUrl() {
        return url;
    }
}
