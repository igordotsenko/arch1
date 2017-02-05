package arch2.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Category {
    long id;
    String name;
    // TODO make reverse comparing
    Set<Article> articles = new TreeSet<>(Comparator.comparing(Article::getPubDate));
    
    public Category(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    
    public long getId() {
        return id;
    }
    
    public boolean addArticle(Article article) {
        return articles.add(article);
    }
    
    public Set<Article> getArticles() {
        return articles;
    }
}
