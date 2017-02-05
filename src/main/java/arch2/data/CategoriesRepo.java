package arch2.data;


import java.util.Set;

import arch2.model.Category;

public interface CategoriesRepo {
    Set<Category> getAllCategories();
    
    Category getCategoryByName(String name);
}
