package agendaescolar.service;

public class LogUser {
    
    public static final String KEY = "loggedUser";
    
    public static void logInUser(String userPrimaryProperty) {
        System.setProperty(KEY, userPrimaryProperty);
    }
    
    public static String getLoggedUser() {
        return System.getProperty(KEY);
    }
    
    public static void logOutUser() {
        System.clearProperty(KEY);
    }
    
}
