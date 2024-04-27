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
            case "1":
              //Handling client login
              String username = inFromClient.readLine();
              String password = inFromClient.readLine();
              if(isValidUser(username, password)) {
                 outToClient.writeBytes("Access Granted" + '\n');
               } else {
                 outToClient.writeBytes("Access Denied - Incorrect Username/Password" + '\n');
               }
            case "2":
              clientSentence = inFromClient.readLine(); 
              System.out.println("FROM CLIENT: " + clientSentence);
              capitalizedSentence = clientSentence.toLowerCase() + '\n'; 
              outToClient.writeBytes(capitalizedSentence);     
              break;
            case "3":
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
}