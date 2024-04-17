import java.io.*;
import java.net.*;

class TextClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            System.out.println("1. Uppercase message");
            System.out.println("2. Lowercase message");
            System.out.println("3. Exit");
            System.out.println("Enter a number:");

            String option = inFromUser.readLine();
            outToServer.writeBytes(option + "\n");

            switch (option) { 

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
                    clientSocket.close();
                    break;
            }
            if (option.equals("3")) {
                break;
            }
        }
    }
}

