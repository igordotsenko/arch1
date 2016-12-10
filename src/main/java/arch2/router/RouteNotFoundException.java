package arch2.router;

@SuppressWarnings("WeakerAccess")
public class RouteNotFoundException extends Exception {
    
    public RouteNotFoundException(String message) {
        super(message);
    }
}
