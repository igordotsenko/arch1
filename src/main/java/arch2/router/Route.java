package arch2.router;

import java.net.URI;

import com.sun.istack.internal.NotNull;

@SuppressWarnings("WeakerAccess")
public class Route {
    public static final String ROOT = "/";
    
    private String[] routeStringParts;
    private String templateName;
    
    public Route(@NotNull String routeString) {
        if (routeString == null || "".equals(routeString)) {
            throw new IllegalArgumentException("Route cannot be empty");
        }
        
        if ("/".equals(routeString)) {
            routeStringParts = new String[1];
            routeStringParts[0] = ROOT;
            templateName = ROOT;
            return;
        }
        
        if (routeString.charAt(routeString.length()-1) != '/') {
            routeString += "/";
        }
        
        routeStringParts = routeString.substring(1).split("/", 0);
        templateName = String.join("-", routeStringParts) + ".tpl";
    }
    
    public boolean matchesUri(URI uri) {
        String uriPath = uri.getPath();
        String[] uriParts = uriPath.substring(1).split("/");
        
        if ("/".equals(uriPath)) {
            uriParts[0] = ROOT;
        }
        
        return routeStringParts.length == uriParts.length && routeStringParts[0].equals(uriParts[0]);
    }
    
    public String getTemplateName() {
        return templateName;
    }
    
    String getRoot() {
        return routeStringParts[0];
    }
}
