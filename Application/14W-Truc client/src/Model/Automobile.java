package Model;

import java.util.ArrayList;
import java.util.Locale;


@SuppressWarnings("serial")
public class Automobile extends OptionSet  {
	
	//Data field:
	private String Make;
	private String Model;
	private double BasePrice;
	private int MaxIndex;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> User_Choice;
        
        //Final String :
	final boolean 	DEBUG 	= true;
	final String 	FIND 	= "FIND";
	final String 	DELETE 	= "DELETE";
	final String 	UPDATE 	= "UPDATE";
	final String 	INCREASE_A_OPTION = "INCREASE A OPTION";
	final String 	DELETE_A_OPTION = "DELETE A OPTION";
	final String 	UPDATE_A_OPTION = "UPDATE A OPTION";
        
        
	//	Set_User_Option_Choice_
        public void Set_User_Option_Choice_(String Op_Set_Name, String Opt_Name){
            for(OptionSet i: opset)
                if(Op_Set_Name.equalsIgnoreCase(i.getName())) {
                    if(i.set_Option_Choice(Opt_Name))   {
                        User_Choice.add(i.Get_User_Choice());
                        break;
                    } 
                }
        }
        
        //getOptionChoice("transmission") would return "standard" and
        public String Get_Option_User_Choice(String Op_Set_Name)
        {
            for(OptionSet i: opset) {
                if(Op_Set_Name.equalsIgnoreCase(i.getName()))
                    return (i.User_Choice_Name()); 
            } 
            return null;
        }
        
        //getOptionChoicePrice("transmission") would return 815.
        public double Get_Option_User_Choice_Price(String Op_Set_Name)
        {
            for(OptionSet i: opset) {
                if(Op_Set_Name.equalsIgnoreCase(i.getName()))
                    return (i.User_Choice_Price()); 
            } 
            return 0;
        }
        
	//Constructor:
	/*----------------------------------------------------------------------
	* 					CONSTRUCTOR :
	------------------------------------------------------------------------*/
	public Automobile(String model, double basePrice, 
                ArrayList<OptionSet> opset) {
		super();
		Model 		= 	model.toUpperCase(Locale.getDefault());
		BasePrice 	= 	basePrice;
		this.opset 	=	opset;
	}

	public Automobile(String make,String model,float basePrice) {
                this.Make       =       make.toUpperCase(Locale.getDefault());
                this.Model      =       model.toUpperCase(Locale.getDefault());
                this.BasePrice  =       basePrice;
                opset           =       new ArrayList<OptionSet>();
        }
        
	public Automobile(String model, double basePrice, int index) {
		super();
		Model 		= 	model.toUpperCase(Locale.getDefault());
		BasePrice 	= 	basePrice;
		MaxIndex	= 	index;
	}
	
	public Automobile(Automobile NewAuto)
	{
		this.Make		= 	NewAuto.Make;
		this.Model		=	NewAuto.Model;
		this.BasePrice  = 	NewAuto.BasePrice;
		this.opset		= 	NewAuto.opset;
	}
	
	//Default Constructor:
	public Automobile() {
		super();
		opset= new ArrayList<OptionSet>();
		User_Choice=new ArrayList<Option>();
	}
	
	/*----------------------------------------------------------------------
 	* 			METHODS OF CREATE OPTION
 	*                   Create( name : String , Cost : double)	: Option
 	* 
 	* Logic:      Allocate new Option by calling constructor in Option class
 	* 					
	------------------------------------------------------------------------*/
	public synchronized Option Create(String name, double cost)  {
		return new Option(name.toUpperCase(Locale.getDefault()), cost);
	}
	

	/*----------------------------------------------------------------------
 	* 			SET OF METHODS OF GETTER
	------------------------------------------------------------------------*/
        public synchronized OptionSet getOptSet(int i) {
	return this.opset.get(i);
        }
        
	public synchronized String getMake() {
		return Make;
	}

	public synchronized String getModel() {
		return Model;
	}

	public synchronized double getBasePrice() {
		return BasePrice;
	}

	public synchronized int getMaxIndex() {
		return MaxIndex;
	}
	
	public synchronized ArrayList<OptionSet> getOpset() {
		return opset;
	}
	
        
        //get the array of string OptionSet Name
	public synchronized ArrayList<String> getNameofOptions(String optionsetName)
        {
            OptionSet Opset_=this.findOptionSetReturnOptionSet(optionsetName);
            ArrayList<String> optionNames=new ArrayList<String>();
            for(OptionSet.Option option : Opset_.getOptions()){
                	optionNames.add(option.getPartName());
            }
            return optionNames;
        }
        
        
        //get the array of string OptionSet Name
        public synchronized ArrayList<String> getNameofOptionset()
        {
        	 ArrayList<String> optionsetNames =new  ArrayList<String>();
            for (OptionSet op: this.getOpset()){
                optionsetNames.add(op.getName());}
                return optionsetNames;
        }
        
	/*----------------------------------------------------------------------
 	* 			SET OF METHODS OF SETTER
	------------------------------------------------------------------------*/
        
        public synchronized void Set_Automobile(Automobile NewAuto)
	{
		this.Make		= 	NewAuto.Make;
		this.Model		=	NewAuto.Model;
		this.BasePrice  = 	NewAuto.BasePrice;
		this.opset		= 	NewAuto.opset;
	}
        
	public void setMake(String maKe) {
		this.Make = maKe;
	}
	
	public void setModel(String model) {
		Model = model;
	}

	public synchronized void setBasePrice(double basePrice) {
		BasePrice = basePrice;
	}

	public synchronized void setOpset(ArrayList<OptionSet> Nopset) {
		this.opset= Nopset;
	}
	
        /**
         *  Set of method create to Create Object from properties file
         * @param name
         */
        public synchronized void setoptset(String name)
        {
                this.opset.add(new OptionSet(name.toUpperCase(Locale.getDefault())));
        }
        
         /**
         *  Set of method create to Create Object from properties file
         * @param optSet_Temp
         * @param name
         * @param price
         */
        public synchronized  void setOption(OptionSet optSet_Temp,String name,float price){
                optSet_Temp.Increase_Option_Manager(name.toUpperCase(Locale.getDefault()), price);
        }
        
         /**
         *  Set of method create to Create Object from properties file
         * @param optionSetName
         * @param OptionName
         */
        public synchronized  void setOptionChoice(String optionSetName, String OptionName){
                this.findOptionSetReturnOptionSet(optionSetName).setChoice
                (this.findOptionSetReturnOptionSet(optionSetName).findReturnOption(OptionName));
    }
        
	/*----------------------------------------------------------------------
 	* 			SET OF METHODS OF PRINTING
	------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------
	 * 					+	PrintDisplayList(): void
	 * 
	 * Logic: 	Simple Print All Set of Options  and OptionSet Objects by Calling
	 *  	+	OptionSet::DisplayOptionSet(): StringBuffer 	in a for loop
	 *  
	------------------------------------------------------------------------*/
	public synchronized void PrintDisplayList()	{
		System.out.print("\n\tCar Model:"+getModel()+"\n\tBase Price is:"
                        +getBasePrice());
		for(OptionSet Temp: opset)
                    System.out.print(Temp.DisplayOptionSet());
        }		//End PrintDisplayList()
	
        
	public synchronized void DisplayOneSet(int index){
		System.out.print(opset.get(index).DisplayOptionSet());
        }   //Display One Set
	
        
        public synchronized void printAutomobilwithOptionsChosen(){
	System.out.println(" and selected options are :"
                            +"Automobile make is "
                +this.getMake()+" and model is "+this.getModel());
	for(OptionSet opSet:this.opset){
            System.out.println("optionset name is "+opSet.getName()+ 
                    "and chosen option is "+ opSet.getChoice().getPartName());
	}}
	/*----------------------------------------------------------------------
	 * 	+	SearchAndPrintManager( Choice:int ): StringBuffer
	 * 
	 * Logic: 	Print A Set of Options with indicate index by Calling
	 *  	+	OptionSet::DisplayOptionSet(): StringBuffer 
	 * 
	------------------------------------------------------------------------*/
	public synchronized StringBuffer SearchAndPrintManager(String OptionSet_Name)	{
		return (opset.get(Search_List(OptionSet_Name)).DisplayOptionSet());
	}		//End SearchAndPrintManager(int Choice)
	

	/*----------------------------------------------------------------------
 	* Method name:	
        *       +   Print_Find_Text_OptionSet_Menu(Text:String):StringBuffer
 	* 		
 	* Logic:    - Method return StringBuffer, contain instruction for user to 
 	*           enter the choice
 	*           - Support printing to prompt from user...
 	---------------------------------------------------------------------------------------*/
	public synchronized StringBuffer Print_Text_OptionSet_Menu(String Text){
		int i=0;
		StringBuffer Menu= new StringBuffer("\n\t\t"+Text.toUpperCase(Locale.getDefault())
                        +" OPTIONSET MENU\n"
				+ "\tPLEASE ENTER YOUR CHOICE AS THE INSTRUCTION BELOW:");
                 for (OptionSet Temp : opset) {
                Menu.append("\n\t\t-Enter ").append(i+1)
                        .append(" for ").append(Temp.getName()).append(" .");
            }
		return Menu;
	}	//End method
	
	
	@Override
		public String toString() {
			return "Automobile [Model=" + Model + ", BasePrice=" 
					+ BasePrice + ", opset=" + opset + "]";
		}
		
	//------------------------END SET OF METHODS OF PRINTING----------------
	
	
	/*----------------------------------------------------------------------
 	* 			SET OF METHODS OF MANIPULATE OPTIONSET ARRAY
 	* 
 	* 	+	DeleteManager ( Choice:int ): void
 	* 	+	UpdateManager ( Choice:int,Name:String ,Index:int): void
 	* 	+	IncreaseManager ( Name:String ,Index:int ): void
	------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------
	 * 	+	DeleteManager ( Choice:int ): void
	 * 
	 * Logic: 	Update the opset : Option [] , also call :
	 * 	+	setOpset(Ops : Option [] , opset.length-1: int): void
	------------------------------------------------------------------------*/
	public synchronized boolean DeleteManager(String Model_Name,String Option_Name_){
		if(this.Model.equalsIgnoreCase(Model_Name.toUpperCase(Locale.getDefault()))){
			try{
				opset.remove(Search_List(Option_Name_));
				MaxIndex-=1;		//Decrease TotalIndex 1
                                return true;
				}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("The index you are looking for is invalid\n");
			}
		}
		return false;
	}
	
	
	/*----------------------------------------------------------------------
	 * 	+	UpdateManager ( Choice:int,Name:String ,Index:int): void
	 * 
	 * Logic: 	Assign new Option set to opset:Option []
	 * 
	------------------------------------------------------------------------*/
	public synchronized void UpdateManager(String ModelName, String Name){
		if(this.Model.equalsIgnoreCase(ModelName.toUpperCase(Locale.getDefault())))
				opset.add( new OptionSet(Name.toUpperCase(Locale.getDefault())));
	}
	
	
	public synchronized void Update_A_OptionSet_Name(String ModelName, int Choice,
                String newName){
            try{
		if(this.Model.equalsIgnoreCase(ModelName))
				opset.get(Choice).setName(newName);
                }
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("The index you are looking for is invalid\n");
	}
	}
	
	/*----------------------------------------------------------------------
	 * 	+	Update_A_OptionSet_By_Name_ ( Model_Name : String ,
	 * 									Option_Set_Name : String
	 * 									newName  : String): void
	 * 
	 * Logic: 	Compare the Model Name and OptionSetName, return index
	 * 			then set the name of the Options Set with new Name if found
	------------------------------------------------------------------------*/
	public synchronized void Update_A_OptionSet_By_Name_(String Model_Name, String Option_Set_Name,
            String newName)
        {
            try{
		if(Model_Name.equalsIgnoreCase(Model))
		{int k=Search_List(Option_Set_Name.toUpperCase(Locale.getDefault()));
			if(k>=0)
				opset.get(k).setName(newName);
		}
                }
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("The index you are looking for is invalid\n");
	}
	}
	/*----------------------------------------------------------------------
	 * 	+	IncreaseManager ( Name:String ,Index:int ): void
	 * 
	 * Logic: 	Update the opset : Option [] , also call :
	 * 	+	setOpset(Ops : Option [] , opset.length-1: int): void
	------------------------------------------------------------------------*/
	public synchronized void IncreaseManager( String Name)
	{
		opset.add(new OptionSet(Name));
		MaxIndex+=1;	//Decrease TotalIndex 1
	}
	//----------END SET OF METHODS OF MANIPULATING OPTIONS SET ARRAY--------
	
	/*----------------------------------------------------------------------
 	* 	SET OF METHODS OF MANIPULATING OPTION ARRAY
 	* 
 	*	+	DeleteAOption ( Choice:int ,Choice1:int  ): void
 	* 	+	UpdateAOption ( Choice:int ,Choice1:int, Name:String ,
        *                                   Price:int ): void
 	* 	+	IncreaseAOption(Name:String , Price:int, Choice:int): void
 	* 
	------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------
	 * 	+	DeleteAOption ( Choice:int ,Choice1:int  ): void
	 * 
	 * Logic: Call :
	 * 	+	Validation( Max:int , Min:int , Choice1:int ): int
	 * 	#	Option::Delete_Option_Manager(Choice1:int) : void
	------------------------------------------------------------------------*/
	public synchronized void DeleteAOption(int Choice,int Choice1)
	{
            try{
                Choice1=Validation(((opset.get(Choice).getOptions().size())+1),
                        1,Choice1);//Validate User input
		opset.get(Choice).Delete_Option_Manager(Choice1);
                }
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("The index you are looking for is invalid\n");
	}
        }
	
	/*----------------------------------------------------------------------
	 * 	+	UpdateAOption( Model_Name : String , Option_Set_Name : String
	 * 						Option_Name : String , New_Price : float): void
	 * 
	 * Logic: 	Compare the Model Name and OptionSetName, return index
	 * 			then set the price of the Options price with new Price if found
	------------------------------------------------------------------------*/
	public synchronized void UpdateAOption(String Model_Name, String Option_Set_Name ,
											String Option_Name,float New_Price)
	{
            try{
		if(Model_Name.equalsIgnoreCase(Model))
		{
			int k=Search_List(Option_Set_Name.toUpperCase(Locale.getDefault()));
			if( k>=0)
				opset.get(k).Update_Option_Price(Option_Name, New_Price);
		}
                }
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("The index you are looking for is invalid\n");
	}
	}
	
	/*----------------------------------------------------------------------
	 * 	+	Search_List( Search_Name : String ): int
	 * 
	 * Logic: 	Search for the Option Set with approriate Name and return the
	 * 			index of the Value in the List
	------------------------------------------------------------------------*/

        @Override
	int Search_List(String Search_Name)
	{
		int k=0;
		for(OptionSet Op:opset)	{
			if(Op.getName().equalsIgnoreCase(Search_Name.toUpperCase(Locale.getDefault())))
				return k;
			k++;
		}
		return -1;
	}
	
        
        //This Search function return an OptionSet...
	public synchronized OptionSet findOptionSetReturnOptionSet(String name)
        {
	for (OptionSet T_opset: opset){
		if (T_opset.getName().equals(name)){
			return T_opset;
		}}
	 System.out.println(name+" is not exist in find");
	return null;
	}
        
	/*----------------------------------------------------------------------
	 * 	+	UpdateAOption ( Choice:int ,Choice1:int, Name:String , 
         *                                                     Price:int ): void
	 * 
	 * Logic: Call :
	 * 	+	Validation( Max:int , Min:int , Choice1:int ): int
	 * 	#	Option::Update_Option_Manager( Choice1:int , 
         *                                      Name:String, Price:int) : void
	------------------------------------------------------------------------*/
	public synchronized void IncreaseAOption(String Name,int Price,int Choice)
	{
			opset.get(Choice).Increase_Option_Manager( Name, Price);
	}
	//------------END SET OF METHODS OF MANIPULATING OPTION ARRAY-----------
	
	
	/*----------------------------------------------------------------------
 	* Method name:	+	FindOptionSet ( name : String ) : OptionSet
 	* 
 	* Logic:    Method receive the String name and compare the String with
 	*           the Name of the OptionSet. Return the OptionSet with
 	*           the information User looking for
 	------------------------------------------------------------------------*/
	public synchronized OptionSet FindOptionSet(String Name){
		OptionSet Ops = null;
		for (OptionSet Opn: opset){	
			if(Name.toUpperCase(Locale.getDefault()).compareToIgnoreCase(Opn.getName())==0)	{
					Ops=Opn;break;
				}	//	IF
			}	//FOR
		return Ops;
	}	//END FIND_OPTION_SET 


	/*----------------------------------------------------------------------
 	* Method name:	Validation( Max:int, Min:int , ValidateNumber:int ):int 
 	* 
	* Logic:
        *   Method receive Max int, Min int and check if the input user is valid
	* 		or not, Prompt user again and again for invalid input.
	* 
	------------------------------------------------------------------------*/
	public synchronized int Validation(int Max, int Min, int ValidateNumber)	{
		
		//if in valid check here until user enter valid number
		if(ValidateNumber>Max||ValidateNumber<Min)
			ValidateNumber=1;
		
		return (ValidateNumber-1);
	}
	
	//Set of new method

    public synchronized boolean SearchAndPrintManager(int Choice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
}	//End ClassAutomotive
