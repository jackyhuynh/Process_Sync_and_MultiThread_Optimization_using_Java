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

import Model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SelectCarOption {
	private Automobile auto;
	private String userChoice;
	private ArrayList<String> optionset;
	private ArrayList<String> options;       
	final boolean DEBUG      = true;
        Socket sock;
        public SelectCarOption()
        {
        }
        
        
    // get available model form the server and display to the user
    public void getAvilableModel(ObjectInputStream reader){
        System.out.println("-------------------------------------------\n");
	System.out.println("List of automobile : \n");
        try {
		ArrayList<String> list=(ArrayList<String>)reader.readObject();
		for (String a:list)
                {
			System.out.println(a);
		}
	} catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
	}
    }	
    
    
    // allow user to select a car for configure
    public String getCarmodelfromUserToConfige()
    { 
        String decision = null;
        System.out.println("-------------------------------------------\n");
	System.out.println("Please enter a model for configuration:\n"
                + "Enter here: \n");
        BufferedReader inchooice= new BufferedReader(new InputStreamReader(System.in));
       
        try {
		decision =inchooice.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return decision ;
    }
    
    
    // show all optionset to the user
    public void showOptionset() {
    System.out.println("-------------------------------------------\n");
	System.out.println("\tThere are "+optionset.size()+ " option: ");
	for (int index=0;index<optionset.size();index++){
            System.out.println("\t "+index+1+" . "+optionset.get(index) );
	}
	System.out.println("-------------------------------------------\n");
    }
    
    //allows user to select an optionset to confige
    public String selectOptionset()
    {
        //Data field
	int optionsetUserChoice;
	String prompt="Please enter your choice (in number):";
	
	boolean flagoptionset=false;
	do{
            showOptionset();
            getUserchoice(prompt);
            optionsetUserChoice = Integer.parseInt(this.userChoice);
            int j=0;
		
            while( (!(flagoptionset))&& (j<optionset.size())){
                if (optionsetUserChoice == j+1)
                {
                    options=this.auto.getNameofOptions(optionset.get(j));
                    flagoptionset=true;
                }
                j++;
            } 
            if (!(flagoptionset)) 
                System.out.println("you entered inavlid number please try again");
            }   while (!(flagoptionset));
        

        return optionset.get(optionsetUserChoice-1);
    }
        
    
    /**
     * allows user to select an option of option_set 
     * @param optionsetName
     */
    public void selectoption(String optionsetName)
    {
        int optionUserChoice;
        boolean flag = false;
	do {
            System.out.println("\t"+optionsetName +" available are");
            for (int i=0;i<options.size();i++)			{
			System.out.println(i+1+" . "+options.get(i) );
            }
            
            //Simple dispaly Prompt user input
            getUserchoice("for select an option please enter a number");
            optionUserChoice = Integer.parseInt(userChoice);
            for(int k=0; (!(flag ))&& (k<options.size());k++)	{
                if (optionUserChoice==k+1)	 {
                    this.auto.setOptionChoice(optionsetName, options.get(optionUserChoice-1));
                    System.out.println("you chose "+ options.get(optionUserChoice-1));
                    flag =true;
                }
            }
            if (!(flag ))  {
            	     System.out.println("\n\tYou entered inavlid number please try again...");
            }

        }   while (!(flag ));
    }
    //end function
    
    
    /**
     * 
     * allows user to select all options of the automobile
     * display the selected options for an Automobile.
     */
    public void selectOptions()
    {
        String configOtherOptions="y"; 
        String promptString="\tDo you want to configure another options (y/n) ?"
        		+ "\n\t(anything to stop, y to continue...)\n";
        do{ 
            //Get the Option Name from user
            String selectedOptionsetName=this.selectOptionset();
            this.selectoption(selectedOptionsetName);
            getUserchoice(promptString);
            configOtherOptions	=	this.userChoice;
        }   while (configOtherOptions.equalsIgnoreCase("y"));
        
	this.auto.printAutomobilwithOptionsChosen();
    }
  
  
  // get the instance of the model from server
    public void  getSelectedAuto(ObjectInputStream reader)
    {
        try {

		this.auto= (Automobile)reader.readObject();
		this.optionset=this.auto.getNameofOptionset();
		auto.PrintDisplayList();
	} catch (ClassNotFoundException | IOException e) {
		
		e.printStackTrace();
	}
    }
    
	    
	    /**
	     *get user input from the console 
	     * @param message
	     */
        public void getUserchoice(String message)
        {
            System.out.println(message);
            BufferedReader inchooice= new BufferedReader(new InputStreamReader(System.in));
            try {
                this.userChoice=inchooice.readLine();
            } catch (IOException e) 
            {
            	try {
            		System.out.println("\tFail to reconize this input\n"+message);
            		this.userChoice=inchooice.readLine();
            	} catch (IOException e1) {
            		System.out.println("\tStop the program here...\n");
					userChoice="n";
				}
            }
        }   
        //end function
	
}

