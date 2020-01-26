/* 
Project: Lab 1 - Group
Purpose Details: Client-Server echo application
Course: IST 411
Author: Ryan Urbanski, Albana Beqo, James Bristow II, Logan Pratt, Fred Aaron
Date Developed: 1/25/2020
Last Date Changed:1/26/2020
Revision: 2
*/

package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ryan
 */
public class EchoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Simple Echo Client");
        
        try {                                                               
            System.out.println("Waiting for connection...");
            InetAddress localAddress = InetAddress.getLocalHost();
            
            try(Socket clientSocket = new Socket(localAddress, 6000);
                    PrintWriter out = new PrintWriter( 
                            clientSocket.getOutputStream(),true);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                            clientSocket.getInputStream()))) {
                
                // User will now be prompted to enter text
                System.out.println("Connected to Server");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Enter text: ");
                    String inputLine = scanner.nextLine();
                    if ("quit".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                    out.println(inputLine);
                    String response = br.readLine();
                    System.out.println("Server response: " + response);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
