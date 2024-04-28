import java.io.*; 
import java.net.*;

class TextClient {
	public void main (String argv[]) throws Exception {
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
          String username, password;
          boolean isAuthenticated = false;
          do {
            System.out.println("Please Enter Your Username: " + '\n');
            username = inFromUser.readLine();
            System.out.println("Please Enter Your Password: " + '\n');
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
          String userList = inFromServer.readLine();
          System.out.println("User list " + userList);
          break;
        case "2":
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
		      sentence = inFromUser.readLine(); 
		      outToServer.writeBytes(sentence + '\n'); 
		      modifiedSentence = inFromServer.readLine(); 
		      System.out.println("FROM SERVER: " + modifiedSentence);
		      break;
		    case "4":    
          clientSocket.close();
          break;
      }
        if (input.equals("4")) {
          break;
        }
    }
	}
  private static boolean isValidUser(String username, String password) {
    return (username.equals("Alice") && password.equals("1234")) || (username.equals("Bob") && password.equals("5678"));
  }
}