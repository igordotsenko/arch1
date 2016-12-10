package arch2.router;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static arch2.router.Route.ROOT;


@SuppressWarnings("WeakerAccess")
public class Router {
    Map<String, List<Route>> routes = new TreeMap<>();
    
    public Router(String configPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(configPath));
        
        while (scanner.hasNextLine()) {
            Route route = new Route(scanner.nextLine());
            routes.computeIfAbsent(route.getRoot(), root -> new ArrayList<>()).add(route);
        }
    }
    
    public Route getRoute(URI uri) throws RouteNotFoundException {
        String path = uri.getPath();
        String pathRoot = ROOT;
        
        if (!pathRoot.equals(path)) {
            String[] parts = path.substring(1).split("/");
            pathRoot = parts[0];
        }
        if (!routes.containsKey(pathRoot)) {
            throw new RouteNotFoundException("Cannot find route for " + path);
        }
        for (Route route : routes.get(pathRoot)) {
            if (route.matchesUri(uri)) {
                return route;
            }
        }
        throw new RouteNotFoundException("Cannot find route for " + path);
    }
}
