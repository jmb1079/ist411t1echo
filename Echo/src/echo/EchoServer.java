/* 
Project: Lab 1 - Group
Purpose Details: Client-Server echo application
Course: IST 411
Author: Ryan Urbanski, Albana Beqo, James Bristow II, Logan Pratt, Fred Aaron
Date Developed: 1/25/2020
Last Date Changed:1/26/2020
Revision: 1
*/

package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ryan
 */
public class EchoServer {
    
    public static void main (String[] args) {
        System.out.println("This is a simple echo server");
        
        // This sets up the serverSocket to receive requests from a client
        // onto the dedicated 6000 port.  It is on pause untill it gets a request
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Waitining for a connection from a client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Successfully onnected to client.");
            
            
            // Reply to the clients request
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter (
                            clientSocket.getOutputStream(), true)) {
                
                    // Reads 1 line at a time from the client and print to console
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        System.out.println("Server: " + inputLine);
                        out.println(inputLine);
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
