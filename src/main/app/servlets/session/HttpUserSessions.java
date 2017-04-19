package servlets.session;


import jdbc.UsersDataSet;

import java.util.HashMap;

public class HttpUserSessions {
    private static final HashMap<String, UsersDataSet> sessions = new HashMap<>();

    private HttpUserSessions() {

    }

    public static HttpUserSessions getInstance(){
        return new HttpUserSessions();
    }

    public static void addSession(String sessionId, UsersDataSet user){
        sessions.put(sessionId,user);
    }

    public static boolean hasSession(String session) {
        return sessions.containsKey(session);
    }

    public static void deleteSession(String session) {
        sessions.remove(session);
    }

    public static UsersDataSet getSessionData(String session) {return sessions.get(session);}
}
