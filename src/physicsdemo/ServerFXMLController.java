/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author hamzaehsan
 */
public class ServerFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextArea text;
    
    private int playerNum=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
        Platform.runLater( () -> {
            // Display the client number
            text.appendText(" Starting lobby for player " + playerNum +
              " at " + new Date() + '\n');
            });
        while (true) {
          // Listen for a new connection request
          Socket socket;
            
                socket = serverSocket.accept();
            
    
         
          playerNum++;
          text.appendText("Player " + playerNum+ '\n');
          Platform.runLater( () -> {
            // Display the client number
            text.appendText(" Starting lobby for player " + playerNum +
              " at " + new Date() + '\n');
            });
          
          // Create and start a new thread for the connection
          new Thread(new HandleAClient(socket,text)).start();
          
          System.out.println(playerNum);
        }   // TODO
    }        catch(IOException ex) {
        System.err.println(ex);
         ex.printStackTrace();
          System.out.println(playerNum);
      }
    }).start();
    
      }
                }
    
    
   
class HandleAClient implements Runnable, Game.GameConstants {
    
    private Socket socket; // A connected socket
   
   // private List<Transcript> roomsList; // Reference to shared transcript
    private TextArea text;
    private String handle;
    private int clientNo=0;
    
public HandleAClient(Socket socket,TextArea textArea) {
      this.socket = socket;
    //  this.roomsList = roomsList;
      this.text = text;
      
    }
        
    public void run() {
      
      try {
        // Create reading and writing streams
        
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outputToClient = new PrintWriter(socket.getOutputStream());
        
        // Continuously serve the client
        while (true) {
          // Receive request code from the client
          int request = Integer.parseInt(inputFromClient.readLine());
          // Process request
          switch(request) {
//              case SEND_HANDLE: {
//                  handle = inputFromClient.readLine();
//                  break;
//              }
//              case SEND_COMMENT: {
//                  String comment = inputFromClient.readLine();
//                  transcript.addComment(handle + "> " + comment);
//                  break;
//              }
//              case SEND_ROOM: {
//                  String ROOM = inputFromClient.readLine();
//                  List<Transcript> list = transcript.returnRooms();
//                  for(int a=0;a<list.size();a++){
//                  if(list.get(a).getName().equals(ROOM)){
//                  transcript = list.get(a);
//                  transcript.incrementClients();
//                  
//                  newroom.clientEntered();}
//                  }
//                  Platform.runLater( () -> {
//            // Display the client number
//            
//            textArea.appendText("Client " + num +
//              " entered " + transcript.getName() + " at " + new Date() + "clients num ="+ transcript.getclients()+'\n');
//            });
//                  break;
//              }
//              case GET_COMMENT_COUNT: {
//                  outputToClient.println(transcript.getSize());
//                  outputToClient.flush();
//                  break;
//              }
//              case GET_COMMENT: {
//                  int n = Integer.parseInt(inputFromClient.readLine());
//                  outputToClient.println(transcript.getComment(n));
//                  outputToClient.flush();
//                  break;
//              }
//              case GET_ROOM_NUM: {
//                  int n = Integer.parseInt(transcript.Roomsize());
//                  outputToClient.println(n);
//                  outputToClient.flush();  
//                  break;
//              }
//              case GET_ROOM: {
//                  
//                  List<Transcript> list = transcript.returnRooms();
//                  outputToClient.println(list.size());
//                  for (int i=0;i<list.size();i++) {
//                      Transcript transcript = list.get(i);
//                      outputToClient.println(transcript.getName());
//                  }
//                  outputToClient.flush();  
//                  
//                  break;
//              }
              
          }
        }
      }
      catch(IOException ex) {
          // When client leaves, you will end up here.
          
            
         // Platform.runLater(()->textArea.appendText("Exception in client thread: "+ex.toString()+"\n"));
      } finally {
         // Platform.runLater(()->text.appendText("Client " + num +" Left " + transcript.getName() + " at " + new Date() + " clients num = "+ transcript.getclients()+'\n'));
          
      }
      
      
    }}
    
    
  


