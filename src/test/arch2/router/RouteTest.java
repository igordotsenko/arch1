package arch2.router;


import java.net.URI;

import org.junit.Test;

import static arch2.router.Route.ROOT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteTest {
    
    @Test
    public void routeTest() {
        URI uri1 = URI.create("/sport/");
        URI uri2 = URI.create("/sport/football/");
        URI uri3 = URI.create("/sport/football/england/");
        URI uri4 = URI.create("/politics/ukraine/");
    
        Route route1 = new Route("/sport");
        Route route2 = new Route("/sport/football/");
        Route route3 = new Route("/");
        Route route4 = new Route("/politics/ukraine/");
    
        assertTrue(route1.matchesUri(uri1));
        assertTrue(route2.matchesUri(uri2));
        assertFalse(route2.matchesUri(uri3));
        assertFalse(route2.matchesUri(uri4));
        
        assertEquals("sport", route1.getRoot());
        assertEquals("sport", route2.getRoot());
        assertEquals(ROOT, route3.getRoot());
        assertEquals("politics", route4.getRoot());
        
        assertEquals("sport.tpl", route1.getTemplateName());
        assertEquals("sport-football.tpl", route2.getTemplateName());
        assertEquals(ROOT, route3.getTemplateName());
        assertEquals("politics-ukraine.tpl", route4.getTemplateName());
    }
}
