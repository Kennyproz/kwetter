package resources.websockets;

import dal.interfaces.UserDAO;
import models.User;
import resources.UserResource;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value="/websocket/kweet",
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class)
public class WebsocketResource {

    private Session session;
    private static Set<WebsocketResource> kweetEndpoints = new CopyOnWriteArraySet<>();
    private static Map<String, Session> currentSessions = new ConcurrentHashMap<>();

    @Inject
    UserDAO userDAO;


    @OnOpen
    public void onOpen(Session session, @PathParam("user") String userId)
    {
        this.session = session;
        kweetEndpoints.add(this);
        currentSessions.put(userId,session);
        System.out.println("New user connection: "+ userId);

        Message message = new Message();
        message.setFrom(userId);
        message.setContent("Connected!");
        try {
            broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(Message message) throws IOException {
        System.out.println("reached onmessage");
        sendToFollowers(message);
    }


    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("reached onclose. user is leaving");

        kweetEndpoints.remove(this);
        Message message = new Message();
        message.setContent("Disconnected!");
        try {
            broadcast(message);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        Message message = new Message();
        message.setFrom("Server");
        message.setContent(throwable.toString());
        try {
            broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }


    private void broadcast(Message message)
            throws IOException, EncodeException {
        kweetEndpoints.forEach(endpoint -> {
            synchronized (endpoint){
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendToFollowers(Message message){
            List<User> followers = userDAO.getFollowers(Long.parseLong(message.getFrom()));
            currentSessions.get(message.getFrom()).getAsyncRemote().sendObject(message);
            System.out.println("REached here with sout");
            System.out.println("This man has: " +followers.size() + " Followers");
            followers.forEach( f -> {
                System.out.println("entered here boy, i gots followers");
                if(currentSessions.containsKey(f.getId())){
                    synchronized (currentSessions) {
                        if(currentSessions.containsKey(f.getId())) {
                            currentSessions.get(f.getId()).getAsyncRemote().sendObject(message);
                        }
                    }
                }
            });
    }

}
