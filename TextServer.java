import java.io.*; 
import java.net.*;
import java.util.*;

public class TextServer {
   private static Map<String, List<String>> messageStore = new HashMap<>();
	public static void main (String argv[]) throws Exception {
      String clientSentence;
      String capitalizedSentence;
      ServerSocket welcomeSocket = new ServerSocket(6789); 
      System.out.println("SERVER is running ... ");

      while(true) {
         Socket connectionSocket = welcomeSocket.accept();
         BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
         DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
         String option;
         
         while (true) {
            System.out.println("waiting...");
            option = inFromClient.readLine();
            switch (option) {
            case "0":
              //Handling client login
              String username = inFromClient.readLine();
              String password = inFromClient.readLine();
              if(isValidUser(username, password)) {
                 outToClient.writeBytes("Access Granted" + '\n');
               } else {
                 outToClient.writeBytes("Access Denied - Incorrect Username/Password" + '\n');
               }
               break;
            case "1":
              outToClient.writeBytes("User list: Alice, Bob\n");     
              break;
            case "2":
              String recipient = inFromClient.readLine();
              String message = inFromClient.readLine();
              storeMessage(recipient, message);
              outToClient.writeBytes("Message sent successfully\n");
              break;
            case "3":
               username = inFromClient.readLine();
               String userMessages = getUserMessages(username);
               outToClient.writeBytes(userMessages + '\n');
               break;
            case "4":    
               connectionSocket.close();
                 break;
            }
            if (option.equals("3")) {
               break;
            }
         }
      }
   }
   private static boolean isValidUser(String username, String password) {
        return (username.equals("Alice") && password.equals("1234")) || (username.equals("Bob") && password.equals("5678"));
   }

   private static void storeMessage(String recipient, String message) {
      // Store the message for the recipient
      List<String> messages = messageStore.getOrDefault(recipient, new ArrayList<>());
      messages.add(message);
      messageStore.put(recipient, messages);
      System.out.println("Message stored for " + recipient + ": " + message);
   }

   private static String getUserMessages(String username) {
      List<String> messages = messageStore.getOrDefault(username, new ArrayList<>());
      StringBuilder sb = new StringBuilder();
      for (String message : messages) {
          sb.append(message).append("\n");
      }
      return sb.toString();
   }
}