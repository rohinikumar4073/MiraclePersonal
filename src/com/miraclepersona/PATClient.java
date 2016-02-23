package com.miraclepersona;
import java.io.*;
import java.net.*;

public class PATClient {
    public static void main(String args[])
    {
        String outPut = null;
        String inPut = "banking,100-150,Texas,10";
        String Host = "113.128.165.224";
        //String Host = "localhost";
        // String Host = "scsblnx-982422.ebiz.verizon.com";
        PATClient obj = new PATClient();
        try
        {
                String b = obj.communicatePAT(outPut, inPut, Host, 5001);
        }
        catch(IOException ie)
        {

        }
        catch(Exception e)
        {
       }
    }

    public String communicatePAT(String outData, String inData,String serverHostname, int port) throws IOException {

        Socket PATSocket = null;
        PrintWriter outToServer = null;
        BufferedReader inFromServer = null;

        try {
            PATSocket = new Socket(serverHostname, port);
            outToServer = new PrintWriter(PATSocket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(
                                        PATSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            return "false";
        } catch (IOException e) {
           System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            return "false";
        }

        String input = inData;

       //outToServer.write(input);
        outToServer.println(input);

        outData = inFromServer.readLine();
        System.out.println(outData);

        outToServer.close();
        inFromServer.close();

        PATSocket.close();
        return outData;
    }

String DataFromGUI;
String DataToGUI;
}