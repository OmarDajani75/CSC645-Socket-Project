import java.io.*; 
import java.net.*;

public class TextClient {
	public static void main (String argv[]) throws Exception {
	   String sentence;
     String modifiedSentence;
     Socket clientSocket = new Socket("127.0.0.1", 6789);
     BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
     BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
     DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

     while (true) {
      System.out.println("Here are your options:"); 
      System.out.println("0. Connect to the server");
      System.out.println("1. Get the user list");
      System.out.println("2. Send a message");
	    System.out.println("3. Get my messages");
	    System.out.println("4. Exit");
      System.out.println("Please enter a number from the list:");
      String input = inFromUser.readLine();

      switch (input) { 
        case "0":
          //Logging into the program and starting the process with TextServer
          String username, password;
          boolean isAuthenticated = false;
          do {
            System.out.println("Please Enter Your Username: ");
            username = inFromUser.readLine();
            System.out.println("Please Enter Your Password: ");
            password = inFromUser.readLine();
            outToServer.writeBytes(username);
            outToServer.writeBytes(password);
            String response = inFromServer.readLine();
            if (response.equals("Access Granted")) {
              isAuthenticated = true;
            } else {
              System.out.println("Access Denied - Incorrect Username/Password");
            }
          } while (!isAuthenticated);
          break;
        case "1":
          //Getting the list of users from TextServer
          String userList = inFromServer.readLine();
          System.out.println("User list " + userList);
          break;
        case "2":
          //Sending a message
          System.out.print("Enter recipient: ");
          String recipient = inFromUser.readLine();
          System.out.print("Enter message: ");
          String message = inFromUser.readLine();

          outToServer.writeBytes(recipient);
          outToServer.writeBytes(message);

          String confirmation = inFromServer.readLine();
          System.out.println("Server response: " + confirmation);
          break;
		    case "3":
          //Checking messages
          System.out.println("Please Enter Your Username: ");
          username = inFromUser.readLine();
          outToServer.writeBytes(username);
          String userMessages = inFromServer.readLine();
          System.out.println("Your messages:\n" + userMessages);
		      break;
		    case "4":    
          //Exiting program
          clientSocket.close();
          break;
      }
        if (input.equals("4")) {
          break;
        }
    }
	}
}