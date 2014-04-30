
package org.glassfish.samples.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoEndpoint {

     private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<>());
    
     @OnOpen
     public void onOpen(Session peer){
         System.out.println("Adding client: " + peer.getId());
         peers.add(peer);
     }
     
    @OnMessage
    public void onMessage(String message) {
        for (Session peer : peers){
            try{
                System.out.println("Sendint to: " + peer.getId());
                peer.getBasicRemote().sendText(message);    
            }catch(IOException ex){
                System.err.println(ex.getMessage());
            }
            
        }
    }
}
