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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class CarModelOptionsIO {
	
	private  String fileName;
        
        public CarModelOptionsIO()	{}
        /**
         *
         * display menu  to user and lets the user to select upload , confige or quit
         */
            public String readUserMenuInput()
            {
            	String userChoice = null;
            	BufferedReader inchooice= new BufferedReader(new InputStreamReader(System.in));  
            	System.out.println(Menu());
	  		try {
                 userChoice=inchooice.readLine();
	  			} catch (IOException e)  {
                 try {
                	System.out.println("\tPlease enter the userchoice again...");
					userChoice=inchooice.readLine();
					return userChoice;
				} catch (IOException e1) {
					System.out.println("\tManual enter the user choice\n"
							+ "\t... Stop the program here ...\n");
					return "q";
				}
	  			}
	  		return userChoice;
            }
            
        /**
         * Simple just get the file name from User input
         * Return the string filename 
         */
        public String getFilepathfromUser()
        {   
            BufferedReader fileNameIn= new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\tPlease Enter name of the text file: ");
            try {
                fileName = fileNameIn.readLine();
            } 
            catch (IOException e2) {
            	
            	try {
            		System.out.println("\tPlease enter the text file name again...\n"
            				+ "\tIf you enter wrong file name program will add the default text file...\n"
            				+ "\tEnter here:\n");
					fileName = fileNameIn.readLine();
				} catch (IOException e) {
					System.out.println("\tEnter the default choice\n"
							+ "\t... to keep the program running ...\n");
					return "corolla.txt";
				}
            }
            return fileName;
        }
        
        /**
         * Create property object , send it to the server 
         * Get response form server
         * Take the communication part...
         * @param writer
         * @param reader
         */
        public void sendPropertyObjectAndGetResponse(   ObjectOutputStream writer, 
                                                        ObjectInputStream reader){
            String fromServer = null;
            Properties props=new Properties();
			
            try {
                props.load(new FileInputStream(fileName));
            } 
            catch (IOException e1) {
            	System.out.println("\tError loading the property file...\n"
						+ "\t... tempolary stop the program here..."
						+ "\t... Please fix restart server ...\n");
            	return;
            }
            try {
                writer.writeObject(props);
                try {
                    fromServer = (String)reader.readObject();
                } catch (ClassNotFoundException e) { }
                System.out.println(fromServer);
            } 
            catch (IOException e) 
            {
            	System.out.println("\tError write the property file. to server..\n"
						+ "\t... tempolary stop the program here..."
						+ "\t... Please fix restart server ...\n");
            	return;
            }
        }
        
        /**
         * Simple display the menu
         * */
 
	 private StringBuffer Menu()
	 {
		 StringBuffer menuBuffer= new StringBuffer(
				 "\n"+
				 "\t-------------------------------------------------\n"+
                 "\t|\tTEST USER MENU CONFIGURATION...\n"+ 
                 "\t|...\n"+ 
                 "\t|a> Please enter 'u' to upload a Car.\n"+
                 "\t|b> Please enter 'c' to configure a Car.\n"+
                 "\t|c> Please enter 'q' to exit program.\n"+
                 "\t|...\n"+ 
                 "\t-------------------------------------------------\n"+
                 "\tEnter your choice here (upper  or  lower): \n"
				 );
		 return menuBuffer;
	 }
}

