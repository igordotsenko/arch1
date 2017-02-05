package arch2.data;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import arch2.model.Article;
import arch2.model.Category;

public class StubDataRepositoryImpl implements DataRepository {
//    private Set<Category> categories = new TreeSet<>(Comparator.comparing(Category::getName));
    private static final long LASTEST_ARTCILE_TIMESTAMP = System.currentTimeMillis();
    private static final int MINUTE = 60 * 1000;
    private Map<String, Category> categories = new HashMap<>();
    private Set<Article> articles = new HashSet<>();
    
    @Override
    public void initialize() {
        generatePoliticsCategory();
        generateSportsCategory();
    }
    
    @Override
    public Set<Category> getAllCategories() {
        return new HashSet<>(categories.values());
    }
    
    @Override
    public Category getCategoryByName(String name) {
        return categories.get(name);
    }
    
    private void generatePoliticsCategory() {
        Category politics = new Category("Politics");
        List<Article> politicsArticles = new ArrayList<>(2);
        politicsArticles.add(new Article("American president ate a baby",
                                         "He told it was pretty tasty",
                                         politics,
                                         new Date(LASTEST_ARTCILE_TIMESTAMP)));
        politicsArticles.add(new Article("North Korea declared war to Persia",
                                         "Korean leader guess they have good chanses",
                                         politics,
                                         new Date(LASTEST_ARTCILE_TIMESTAMP - 5 * MINUTE)));
        politicsArticles.forEach(politics::addArticle);
        categories.put("Politics", politics);
        politicsArticles.forEach(articles::add);
    }
    
    //TODO use single method for categories genetations
    private void generateSportsCategory() {
        Category sports = new Category("Sports");
        List<Article> sportsArticles = new ArrayList<>(3);
        sportsArticles.add(new Article("German biatlonists invades Poland",
                                         "Real blitzkrieg on lates Olimpics",
                                         sports,
                                         new Date(LASTEST_ARTCILE_TIMESTAMP)));
        sportsArticles.add(new Article("Ukrainian hockey player learning to ice-skate",
                                         "It's not easy but we believe!",
                                         sports,
                                         new Date(LASTEST_ARTCILE_TIMESTAMP - 5 * MINUTE)));
        sportsArticles.forEach(sports::addArticle);
        categories.put("Sports", sports);
        sportsArticles.forEach(articles::add);
    }
}
