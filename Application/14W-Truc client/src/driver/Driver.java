package driver;

/*
 * "<CIS 35B>- Lab <4>"
 * Student :	Truc Huynh
 * Program :	HW3
 * 
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import client.DefaultSocketClient;


public class Driver 
{	
	public static void main(String args[])
        {
            String LocalHost = "";
            try
            {
                //Get the LocalHost...
                 LocalHost =  InetAddress.getLocalHost().getHostName();
            }
            catch (UnknownHostException e){
                System.err.println ("Unable to find local host");
            }
            DefaultSocketClient d = new DefaultSocketClient(LocalHost, 5555);
            d.start();
 	 }
                
}
	
	
