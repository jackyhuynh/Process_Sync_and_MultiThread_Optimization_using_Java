/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author TrucHuynh
 */
import java.net.*;
import java.io.*;


public class DefaultSocketClient extends 	Thread 		
                                implements 	SocketClientInterface,
                                			SocketClientConstants 
{
		//Data field:
        private ObjectInputStream reader;
        private ObjectOutputStream writer;
        public Socket serverSocket;
        private String strHost;
        private int iPort;
        private String userChoice;
    
    public DefaultSocketClient(String strHost, int iPort) {   
    	 	this.strHost = strHost;
    	 	this.iPort = iPort;
    }//constructor

    /**
     *	run multiple client
     */
        @Override
    public void run(){
       if (openConnection()){
          handleSession();
          closeSession();
       }
    }//run
    
    
        @Override
    public boolean openConnection()
    {
        try { 
        	serverSocket = new Socket(strHost, iPort);    
            writer	=	new ObjectOutputStream(serverSocket.getOutputStream());
            reader 	= 	new ObjectInputStream (serverSocket.getInputStream());
        }   catch (Exception e)
        {
            if (DEBUG) 
            	System.err.println
                ("\tUnable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }
    
    


    /**
     * prompt user the menu and allow the user to select what they want to do
     * base on user's choice calls a approriate 
     * method of CarModelOptionIO or SelectCarOption
     */
        @Override
    public void handleSession()
    {
        if (DEBUG) 
            System.out.println ("Handling session with Host: "+ strHost + " ,at port:" + iPort);
        
        CarModelOptionsIO carIo 	= new CarModelOptionsIO();
        SelectCarOption selectCar 	= new SelectCarOption();
        userChoice = carIo.readUserMenuInput();             //Get user choice
	
        while (!(userChoice.equalsIgnoreCase("q")))
        {
            if (userChoice.equalsIgnoreCase("u"))	
            { 
                sendOutput("upload");
                
                carIo.getFilepathfromUser();				//get file path form console
                
                //send property object to server to create an automobile  and get response form server
                carIo.sendPropertyObjectAndGetResponse(writer, reader);
                
                userChoice=carIo.readUserMenuInput();		// prompt user the menu again...
            }
	    else if (userChoice.equalsIgnoreCase("c"))
            {	
	    		sendOutput("config");
                
	    		selectCar.getAvilableModel(reader);			// get available models form the server
                
                userChoice=selectCar.getCarmodelfromUserToConfige(); // Allow user to select a model
                
                sendOutput(userChoice)	;					// send user's choice to the server
                
                selectCar.getSelectedAuto(reader);			//get the instance of the model from server
         
                selectCar.selectOptions();
	    	     
                userChoice=carIo.readUserMenuInput(); 		// Prompt  menu to user for upload a file or confige a car or quit
            }        
        }
    }       
    
    // send an object to server
    private void sendOutput(Object obj)
    {
        try {
            writer.writeObject(obj);
        }
        catch (IOException e)   {
    	    if (DEBUG) System.out.println 
    	               ("Error writing to " + strHost);
        }
    }
    
    /**
     * Simple close the session...
     */
        @Override
    public void closeSession()
    {
       try {
            writer.close();
            reader .close();
            serverSocket.close();
       }    catch (IOException e)
       {
            if (DEBUG) System.err.println
                ("Error closing socket to " + strHost);
       }       
    }
   
    
    public void setHost(String strHost){
            this.strHost = strHost;
    }

    
    public void setPort(int iPort){
            this.iPort = iPort;
    } 

}
