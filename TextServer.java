import java.io.*; 
import java.net.*;

class TextServer {
	public void main (String argv[]) throws Exception {
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

              outToClient.writeBytes("Message sent successfully\n");
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
}