package Adapter;


import Model.Automobile;
import Util.Util;
import java.util.*;
import scale.EditOptions;

public abstract class proxyAutomobile {
    
	//Data field:
    private static LinkedHashMap<String,Automobile> Set_Auto;
	
    public proxyAutomobile()
    {
        Set_Auto = new LinkedHashMap<> ();
    }
     /*---------------------------------------------------------------------------------------
	 * 		+	printAuto( Index : int ) : void 
	 * 	
	 * Logic: Simple Prompt text to display and Call method 
	 *  	+	Automotive::UpdateAOption(OptionSet_Index : int , Option_Index :int
	 *  					,New_Name : string ,New_Price : int);
	 *
	---------------------------------------------------------------------------------------*/
        public void BuildAuto(String Name) {
        	Util Build= new Util();	//Create Object Util to build the Auto Set
            Set_Auto = Build._Build_Auto_(Name);
        }
        
	
	/*---------------------------------------------------------------------------------------
	 * 		+	updateOptionSetName( Model_Name:String , Index : int, Option_Set_Name: String,
	 *			New_Set_Total:int ): void
	 * 
	 * Logic: Simple Prompt text to display and Call method 
	 *  	+	Automotive::Update_A_OptionSet_Name(Model_Name:String ,Index : int ,
	 *  								Option_Set_Name: String );	
	 *
	---------------------------------------------------------------------------------------*/
        public void updateOptionSetName(String Model_Name,String Option_Set_Name, 
        		String Option_Set_New_Name) {
		Set_Auto.get(Model_Name.toUpperCase(Locale.getDefault())).Update_A_OptionSet_By_Name_(
				Model_Name.toUpperCase(Locale.getDefault()),Option_Set_Name,Option_Set_New_Name);
	}
        
        
	/*---------------------------------------------------------------------------------------
	 * 		+	updateOptionPrice(OptionSet_Index : int , Option_Index :  int 
	 * 									,New_Name :String  ,New_Price : int ): void
	 * 	
	 * Logic: Simple Prompt text to display and Call method 
	 *  	+	Automotive::UpdateAOption(OptionSet_Index : int , Option_Index :int
	 *  								 ,New_Name : string ,New_Price : int);
	 *
	---------------------------------------------------------------------------------------*/
	public void updateOptionPrice (String Model_Name, String Option_Set_Name ,
			String Option_Name,float New_Price){
		Set_Auto.get(Model_Name.toUpperCase(Locale.getDefault())).UpdateAOption(
				Model_Name.toUpperCase(Locale.getDefault()),Option_Set_Name.toUpperCase(Locale.getDefault()) ,
				Option_Name.toUpperCase(Locale.getDefault()),New_Price);
	}
	
	
	/*---------------------------------------------------------------------------------------
	 * 		+	printAuto( Model : String ) : void 
	 * 	
	 * 		+	Logic: Simple Print all list
	 *  	+	Automotive::PrintDisplayList()
	 *
	---------------------------------------------------------------------------------------*/
	public void printAuto(String Model_Name) {
		Set_Auto.get(Model_Name.toUpperCase(Locale.getDefault())).PrintDisplayList();
	}
	
	
	/**---------------------------------------------------------------------------------------
	 * 		+	OptionSet_Edit_Manager(Operation: int
     *                              ModelName : String , Option_Name : String ) : void 
	 * 	
	 * Logic:	Create a set of Edit an these OptionSet of each Model Package
     *              base on the ModelName (Name of the Automotive) and Operation: int
     *              (indicate the number of which operation will happen)
     *                  See menu instruction in EditOptions class...
	 *              
     *              Call:
     *              EditOptions::EditOptions(et_Auto.get(ModelName): Automotive, Operation:int
     *                                  Model_Name : String, Name : String)
     *
	---------------------------------------------------------------------------------------*/
	public void OptionSet_Edit_Manager(int Operation,String ModelName, String Name) {
            EditOptions Editor= new EditOptions(Set_Auto.get(ModelName.toUpperCase
                                (Locale.getDefault())),Operation,ModelName,Name);
            Editor.start();
        }
        
}
