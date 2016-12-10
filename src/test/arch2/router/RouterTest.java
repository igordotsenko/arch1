package arch2.router;


import java.net.URI;

import org.junit.Test;

import static arch2.router.Route.ROOT;
import static org.junit.Assert.assertEquals;

public class RouterTest {
    
    @Test
    public void routerTest() throws Exception {
        Router router = new Router("/home/idotsenko/development/devclub/arch1/resources/routes.conf");
        URI uri0 = URI.create("/");
        URI uri1 = URI.create("/news/");
        URI uri2 = URI.create("/news/england/");
        URI uri3 = URI.create("/news/england/queen-ate-baby/");
        URI uri4 = URI.create("/gallery/");
        URI uri5 = URI.create("/gallery/cats/");
    
        assertEquals(ROOT, router.getRoute(uri0).getTemplateName());
        assertEquals("news.tpl", router.getRoute(uri1).getTemplateName());
        assertEquals("news-[category].tpl", router.getRoute(uri2).getTemplateName());
        assertEquals("news-[category]-[article].tpl", router.getRoute(uri3).getTemplateName());
        assertEquals("gallery.tpl", router.getRoute(uri4).getTemplateName());
        assertEquals("gallery-[album].tpl", router.getRoute(uri5).getTemplateName());
    }
}
