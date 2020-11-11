/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

/**
 *
 * @author TrucHuynh
 */
import Exception_Handle.Exception_Handle_Manager;
import Exception_Handle.util_Helper.Util_Helper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;
import Model.Automobile;
import Model.OptionSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadTextFile {//1s
	private Automobile      Auto;
	final boolean DEBUG     = false;
	final String ITEM1      = "Color";
	final String ITEM2      = "Transmision";
	final String ITEM3      = "Brakes\\Traction Control";
	final String ITEM4      = "Side Impact Air Bags";
	final String ITEM5      = "Power Moonroof";
        final String ITEM6      = "Sun Roof";
        final String ITEM7      = "Mirror";
	final String ERROR      = "\tYOU INPUT AN INVALID NUMBER, PLEASE TRY AGAIN!!!\n";
	final String DECISION   = "\nPlease enter your Choice follow by the number above: ";
	final String FIND       = "FIND";
	final String DELETE     = "DELETE";
	final String UPDATE     = "UPDATE";
	final String INCREASE   = "INCREASE";
	final String INCREASE_A_OPTION  = "INCREASE A OPTION";
	final String DELETE_A_OPTION    = "DELETE A OPTION";
	final String UPDATE_A_OPTION    = "UPDATE A OPTION";
        
	public Automobile buildObjectProperties(Properties props) throws IOException{

                //get the Model of the Car:
		String carModel = props.getProperty("CarModel");
		if (!(carModel == null)){
                        //Create a Local BuildAuto
                        //Build an Object Automobile with these parameter:
			Automobile auto1;
                        auto1 = new Automobile(    props.getProperty("CarMake")   
                            ,carModel
                            ,Float.parseFloat(props.getProperty("CarBasePrice")));
                        
                        //Use this decide to stop the loop
			int optionsetNumber=Integer.parseInt(props.getProperty("OptionSet"));
                        
                        //In loop Set Up the Options Set And Options Objects..
			for (int i=0;i<optionsetNumber;i++)
			{   
                            int numOfOptionset=i+1;
                            String Optionset="Option"+numOfOptionset;     //For set up a Option Set
                            
                            auto1.setoptset(props.getProperty(Optionset));//Create a Set of OptionSet
                            
                            Optionset=Optionset+"n";                      //Get total of OptionSet
                            
                            for (int j=0;j<Integer.parseInt(props.getProperty(Optionset));j++)
                            {      
                                int numofOption=j+1;
				String optionvalue="OptionValue"+numOfOptionset+numofOption;
                                //Create a Set of Option with full detail base on this
				auto1.setOption(auto1.getOptSet(i), props.getProperty(optionvalue),Float.parseFloat(props.getProperty(optionvalue+"p")));
			     }
			}
			return auto1;
		}
		else {
			Automobile auto1=null;
			return auto1;
		}
	}
        
	public Automobile _Build_Auto_Manager(
                        BufferedReader bufferedReader)
		{
                    //Data field
                    String Model;
                    int BasePrice = 0;
                    int MaxIndex = 0;
			
			//Read 2nd Line Model-Name
			try {
				Model=bufferedReader.readLine();
			} catch (IOException e) {
				Exception_Handle_Manager f=new Exception_Handle_Manager();
				Model=f.Fix_Input_String_Problem();
			}
			
                        //Read 3rd Line Base-Price
			try {
				try {
					BasePrice=Integer.parseInt( bufferedReader.readLine());
				} catch (IOException e) {
				}
			} catch (NumberFormatException e) {
				Exception_Handle_Manager f=new Exception_Handle_Manager(3);
				BasePrice=f.Fix_Input_Integer_Problem();
			} 
			
                        //Read 4th Line MaxIndex
			try {
				try {
					MaxIndex = Integer.parseInt(
                                                bufferedReader.readLine());
				} catch (IOException e) {
                                    
				}
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				Exception_Handle_Manager f=new Exception_Handle_Manager(4);
				MaxIndex =f.Fix_Input_Integer_Problem();
			} 			
			
			if(DEBUG){System.out.print(" "+Model+" "+
                                BasePrice+" "+MaxIndex+"\n");}	//if Debug
				
			
			Auto=new Automobile(Model, BasePrice, MaxIndex);		
			
                        //Call Read Input to Create OptionSet Array
			ReadOptionSet(bufferedReader, MaxIndex);		

							
			return Auto;
		}	//end method
		
		/*--------------------------------------------------------------
	 	* 
	 	* Method name:	# ReadOptionSet (bufferedReader:BufferedReader,
                *                               MaxIndex : int ): void
	 	* 
	 	* Logic:    ReadInput Call ReadOptionSet(bufferedReader) in a 
	 	*	loop and ASSIGN TO the OptionSet Array to Auto Object
	 	* 
	 	----------------------------------------------------------------*/
		

		protected void ReadOptionSet(BufferedReader bufferedReader, int MaxIndex) 
		{
			ArrayList<OptionSet> ops= new ArrayList<OptionSet>();
			
			for(int i=0;i<MaxIndex;++i){
                            ops.add(ReadOptionSet(bufferedReader));
			//assign to OptionSet vector Ops OptionSet object
			}
                        //Assign the optionSet Array to the Automotive.
			Auto.setOpset(ops);
		}	//end method
		
	/*----------------------------------------------------------------------
	 * 
	 * Method name:		# ReadOption( bufferedReader : BufferedReader):
         *                     OptionSet
	 * 
	 * Logic:1.Method receive BufferedReader buffereredReader 
	 * 	and read data from file : 
	 * 		a. read string choice: indicate the name of the parts
	 * 		b. read int index : indicate  the max elements  of Option object
	 * 	2.Call constructor in OptionSet class to build OptionSet object with 
	 * 	char choice and int index.
	 * 	3.Call Automotive.Create(String PartName,double cost)to build Option 
	 * 	object for the array of Option(OptionSet member function)
	 * 
	 -----------------------------------------------------------------------*/
		protected OptionSet ReadOptionSet(BufferedReader bufferedReader){
																												
                    if(DEBUG){System.out.print("\n\tInsideReadOptionSet\n\n");}
			
			//Use BufferedReader to read the choice and index
			String choice=null;
            String PartName=null;
            double cost=0;
            int index;
            ArrayList<Automobile.Option> opn= new ArrayList<Automobile.Option>();     
            try {
                choice = (bufferedReader.readLine()).trim();
            } catch (IOException ex) {
                Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
                Exception_Handle_Manager f=new Exception_Handle_Manager(5);
				choice=f.Fix_Input_String_Problem();
            }
			
            try {
                index = Integer.parseInt(bufferedReader.readLine());
            } catch (IOException ex) {
                Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
                Exception_Handle_Manager f=new Exception_Handle_Manager(6);
				index=f.Fix_Input_Integer_Problem();
            }
			
			//Use the ReadName member function to find the right 
                        //name for the parts base on the char choice
			String Name=ReadName(choice);																			
                        
                        if(DEBUG){System.out.print("\n "+Name+" "+index+" "+"\n");}
			
			//Allocate new OptionSet with parameter String Name
			OptionSet Ops= new OptionSet(Name);
			
			//In a for loop use index that read form the text file 
                        //to stop the loop:
			for(int i=0; i<index; ++i)
			{
                            try {
                                    //Call the ReadOption to create object type Option
                                    PartName=bufferedReader.readLine();
                            }
                            catch (IOException ex) {
                            	Exception_Handle_Manager f=new Exception_Handle_Manager(7);
                                    cost=f.Fix_Input_Integer_Problem();
                                    Logger.getLogger(Util_Helper.class.getName())
                                            .log(Level.SEVERE, null, ex);
                            } 
                             
                            try {
                                    cost=Double.parseDouble(bufferedReader.readLine());
                              }
                            catch (IOException ex) {
                                    Logger.getLogger(Util_Helper.class.getName())
                                            .log(Level.SEVERE, null, ex);
                                    Exception_Handle_Manager f=new Exception_Handle_Manager(8);
                                    cost=f.Fix_Input_Integer_Problem();
                            }
                                opn.add(Auto.Create(PartName, cost));	
                                
                                if(DEBUG){System.out.print(" "+PartName+" "+cost+" "+"\n");}
			}
			Ops.setOptions(opn);
                        //Return the OptionSet object
			return Ops;
		}

		
		/*--------------------------------------------------------------
	 	* Method name:		# ReadName: String
	 	* 
	 	* Logic:Method receive the character choice and return the string
	 	* 			name that suitable with the choice.
	 	----------------------------------------------------------------*/
		protected String ReadName(String choice) {
			String Name = null;
			switch(choice)	{
				case "C":		
                                    Name=ITEM1;	
                                    break;
				case "T":		
                                    Name=ITEM2;	
                                    break;
				case "B":		
                                    Name=ITEM3;	
                                    break;
				case "S":		
                                    Name=ITEM4;	
                                    break;
				case "P":		
                                    Name=ITEM5;	
                                    break;
                                case "R":                    
                                    Name=ITEM6;
                                    break;
                                case "M":
                                    Name=ITEM7;
                                    break;
				default:Name="";
			}
			return Name;
		}//End Method
        
		}

	
	
	
	

