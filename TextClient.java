import java.io.*; 
import java.net.*;

class TextClient {
	public void main (String argv[]) throws Exception {
	   String sentence;
     String modifiedSentence;
     BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
     Socket clientSocket = new Socket("127.0.0.1", 8000);
     DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
     BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

     while (true) {
      System.out.println("0. Connect to the server");
      System.out.println("1. Get the user list");
      System.out.println("2. Send a message");
	    System.out.println("3. Get my messages");
	    System.out.println("4. Exit");
      System.out.println("Enter a number:");

      String option = inFromUser.readLine();
      outToServer.writeBytes(option + "\n");
      switch (option) { 
        case "0": 
		      System.out.println("Please Enter Your Username: " + '\n');
		      System.out.println("Please Enter Your Password: " + '\n');
		      //Add if statement here
		      System.out.println("Access Granted" + '\n');
		      System.out.println("Access Denied - Incorrect Username/Password" + '\n');
          sentence = inFromUser.readLine(); 
          outToServer.writeBytes(sentence + '\n'); 
          modifiedSentence = inFromServer.readLine(); 
          System.out.println("FROM SERVER: " + modifiedSentence);
          break;
        case "1": 
          sentence = inFromUser.readLine(); 
          outToServer.writeBytes(sentence + '\n'); 
          modifiedSentence = inFromServer.readLine(); 
          System.out.println("FROM SERVER: " + modifiedSentence);
          break;
        case "2":
		      sentence = inFromUser.readLine(); 
		      outToServer.writeBytes(sentence + '\n'); 
		      modifiedSentence = inFromServer.readLine(); 
		      System.out.println("FROM SERVER: " + modifiedSentence);
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
        if (option.equals("4")) {
          break;
        }
    }
	}
}