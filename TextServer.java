import java.io.*; 
import java.net.*;

class TextServer {
	public void main (String argv[]) throws Exception {
           String clientSentence;
           String capitalizedSentence;
           ServerSocket welcomeSocket = new ServerSocket(8000); 
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
                  clientSentence = inFromClient.readLine(); 
                  System.out.println("FROM CLIENT: " + clientSentence);
                  capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
                  outToClient.writeBytes(capitalizedSentence);     
                  break;
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
}