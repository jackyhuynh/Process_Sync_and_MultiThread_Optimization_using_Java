package Util;

/*------------------------------------------------------------------------------
 * 			Util Class in package Util
 * 
 * Logic:	Util Have:
 * 
 * 		2.	Set of ReadInput method.
 * 		3.	Set of method To Serializable 
 *                          LinkedHashMap<String, Automobile> Object
 * 
 -------------------------------------------------------------------------------*/
import Exception_Handle.Exception_Handle_Manager;
import Exception_Handle.util_Helper.Util_Helper;
import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

	// DATA FIELD
	private LinkedHashMap<String, Automobile> Set_Of_User_ChoiceHashMap;
	private Automobile Auto;
	final boolean DEBUG = false;
	final String ITEM1 = "Color";
	final String ITEM2 = "Transmision";
	final String ITEM3 = "Brakes\\Traction Control";
	final String ITEM4 = "Side Impact Air Bags";
	final String ITEM5 = "Power Moonroof";
	final String ITEM6 = "Sun Roof";
	final String ITEM7 = "Mirror";
	final String ERROR = "\tYOU INPUT AN INVALID NUMBER, PLEASE TRY AGAIN!!!\n";
	final String DECISION = "\nPlease enter your Choice follow by the number above: ";
	final String FIND = "FIND";
	final String DELETE = "DELETE";
	final String UPDATE = "UPDATE";
	final String INCREASE = "INCREASE";
	final String INCREASE_A_OPTION = "INCREASE A OPTION";
	final String DELETE_A_OPTION = "DELETE A OPTION";
	final String UPDATE_A_OPTION = "UPDATE A OPTION";

	Scanner in = new Scanner(System.in);// Create Object Scanner

	// Constructor :
	/*--------------------------------------------------------------
	 * 			CONSTRUCTOR :
	----------------------------------------------------------------*/
	public Util(LinkedHashMap<String, Automobile> Set_auto) {
		super();
		Set_Of_User_ChoiceHashMap = Set_auto;
	}

	public Util() {
		super();
		Auto = new Automobile();
		Set_Of_User_ChoiceHashMap = new LinkedHashMap<>();
	}

	/*--------------------------------------------------------------
	 * 			GETTER AND SETTER	:
	----------------------------------------------------------------*/
	// Getter Auto:
	public Automobile getAuto() {
		return Auto;
	}

	// Setter Auto:
	public void setAuto(Automobile auto) {
		Auto = auto;
	}

	// Getter LinkedHashMap<String, Automobile>:
	public LinkedHashMap<String, Automobile> getSet_Set_Auto() {
		return Set_Of_User_ChoiceHashMap;
	}

	// Setter LinkedHashMap<String, Automobile>:
	public void setSet_Set_Auto(LinkedHashMap<String, Automobile> Set_auto) {
		Set_Of_User_ChoiceHashMap = Set_auto;
	}

	/*--------------------------------------------------------------
	 * 		START SET METHODS OF READING INPUT
	 * 
	/*--------------------------------------------------------------
	 * There are Set of three method of ReadInput:
	 * + _Build_Auto_(filename:String):LinkedHashMap<String, Automobile> 
	 * # MainReadInput	(filename : String ) : void
	 * # ReadOptionSet (bufferedReader : BufferedReader ): void
	 * # ReadOption	(bufferedReader : BufferedReader ): OptionSet
	 * # ReadName		(char choice): String 
	 *
	----------------------------------------------------------------*/
	/*
	 * # MainReadInput(filename : String ) : void
	 * 
	 * Logic: Manipulate All the Read input method Read the first three line and
	 * allocate the new Automotive Object
	 * 
	 * ----------------------------------------------------------------
	 */
	public LinkedHashMap<String, Automobile> _Build_Auto_(String filename) {
		// Data field:
		FileReader fileReader = null;
		int MaxElement = 0;

		// Start here:
		try {
			fileReader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			Exception_Handle_Manager f = new Exception_Handle_Manager(1);
			filename = f.Fix_Input_String_Problem();
		} // if not found call Exception handle to input

		// Create bufferedReader Object
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		// Read 1st line
		try {
			MaxElement = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			Exception_Handle_Manager f = new Exception_Handle_Manager(2);
			MaxElement = f.Fix_Input_Integer_Problem();
			Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
		}

		// Read the set of Automoblile here:
		for (int i = 1; i < (MaxElement + 1); i++) {
			Auto = _Build_Auto_Manager(bufferedReader);
			Set_Of_User_ChoiceHashMap.put(Auto.getModel(), Auto);
		}

		StoreInput(Set_Of_User_ChoiceHashMap);
		// Close All here
		try {
			bufferedReader.close(); // Close BufferedReader
			fileReader.close(); // Close fileReader
		} catch (IOException e) {
		}
		return Set_Of_User_ChoiceHashMap;
	}

	// _Build_Auto_Manager:
	protected Automobile _Build_Auto_Manager(BufferedReader bufferedReader) {
		// Data field
		String Model;
		int BasePrice = 0;
		int MaxIndex = 0;

		// Read 2nd Line Model-Name
		try {
			Model = bufferedReader.readLine();
		} catch (IOException e) {
			Exception_Handle_Manager f = new Exception_Handle_Manager();
			Model = f.Fix_Input_String_Problem();
		}

		// Read 3rd Line Base-Price
		try {
			try {
				BasePrice = Integer.parseInt(bufferedReader.readLine());
			} catch (IOException e) {
			}
		} catch (NumberFormatException e) {
			Exception_Handle_Manager f = new Exception_Handle_Manager(3);
			BasePrice = f.Fix_Input_Integer_Problem();
		}

		// Read 4th Line MaxIndex
		try {
			try {
				MaxIndex = Integer.parseInt(bufferedReader.readLine());
			} catch (IOException e) {

			}
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			Exception_Handle_Manager f = new Exception_Handle_Manager(4);
			MaxIndex = f.Fix_Input_Integer_Problem();
		}

		if (DEBUG) {
			System.out.print(" " + Model + " " + BasePrice + " " + MaxIndex + "\n");
		} // if Debug

		Auto = new Automobile(Model, BasePrice, MaxIndex);

		// Call Read Input to Create OptionSet Array
		ReadOptionSet(bufferedReader, MaxIndex);

		return Auto;
	} // end method

	/*--------------------------------------------------------------
	* 
	* Method name:	# ReadOptionSet (bufferedReader:BufferedReader,
	        *                               MaxIndex : int ): void
	* 
	* Logic:    ReadInput Call ReadOptionSet(bufferedReader) in a 
	*	loop and ASSIGN TO the OptionSet Array to Auto Object
	* 
	----------------------------------------------------------------*/

	protected void ReadOptionSet(BufferedReader bufferedReader, int MaxIndex) {
		ArrayList<OptionSet> ops = new ArrayList<OptionSet>();

		for (int i = 0; i < MaxIndex; ++i) {
			ops.add(ReadOptionSet(bufferedReader));
			// assign to OptionSet vector Ops OptionSet object
		}
		// Assign the optionSet Array to the Automotive.
		Auto.setOpset(ops);
	} // end method

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
	protected OptionSet ReadOptionSet(BufferedReader bufferedReader) {

		if (DEBUG) {
			System.out.print("\n\tInsideReadOptionSet\n\n");
		}

		// Use BufferedReader to read the choice and index
		String choice = null;
		String PartName = null;
		double cost = 0;
		int index;
		ArrayList<Automobile.Option> opn = new ArrayList<Automobile.Option>();
		try {
			choice = (bufferedReader.readLine()).trim();
		} catch (IOException ex) {
			Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
			Exception_Handle_Manager f = new Exception_Handle_Manager(5);
			choice = f.Fix_Input_String_Problem();
		}

		try {
			index = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
			Exception_Handle_Manager f = new Exception_Handle_Manager(6);
			index = f.Fix_Input_Integer_Problem();
		}

		// Use the ReadName member function to find the right
		// name for the parts base on the char choice
		String Name = ReadName(choice);

		if (DEBUG) {
			System.out.print("\n " + Name + " " + index + " " + "\n");
		}

		// Allocate new OptionSet with parameter String Name
		OptionSet Ops = new OptionSet(Name);

		// In a for loop use index that read form the text file
		// to stop the loop:
		for (int i = 0; i < index; ++i) {
			try {
				// Call the ReadOption to create object type Option
				PartName = bufferedReader.readLine();
			} catch (IOException ex) {
				Exception_Handle_Manager f = new Exception_Handle_Manager(7);
				cost = f.Fix_Input_Integer_Problem();
				Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
			}

			try {
				cost = Double.parseDouble(bufferedReader.readLine());
			} catch (IOException ex) {
				Logger.getLogger(Util_Helper.class.getName()).log(Level.SEVERE, null, ex);
				Exception_Handle_Manager f = new Exception_Handle_Manager(8);
				cost = f.Fix_Input_Integer_Problem();
			}
			opn.add(Auto.Create(PartName, cost));

			if (DEBUG) {
				System.out.print(" " + PartName + " " + cost + " " + "\n");
			}
		}
		Ops.setOptions(opn);
		// Return the OptionSet object
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
		switch (choice) {
		case "C":
			Name = ITEM1;
			break;
		case "T":
			Name = ITEM2;
			break;
		case "B":
			Name = ITEM3;
			break;
		case "S":
			Name = ITEM4;
			break;
		case "P":
			Name = ITEM5;
			break;
		case "R":
			Name = ITEM6;
			break;
		case "M":
			Name = ITEM7;
			break;
		default:
			Name = "";
		}
		return Name;
	}// End Method

	/*--------------------------------------------------------------
	* 		END SET METHODS OF READING INPUT
	----------------------------------------------------------------*/

	// SET METHOD:
	/*--------------------------------------------------------------
	* 		SET METHODS OF CONTROLLER
	----------------------------------------------------------------*/
	/*--------------------------------------------------------------
	* #	ControlManager(AutoNew : Automotive ):void
	*
	* Logic:	Decide which method to call by User
	* 		ControlOptionSetArray(): void
	* 		ControlOptionArray() : void
	* 		Automotive::PrintDisplayList(): void
	----------------------------------------------------------------*/

	protected void ControlManager(Automobile AutoNew) {
		int choice = 0;
		int Start = 0;
		StringBuffer Prompt = new StringBuffer("\nEnter 1 for Modify A Set of OptionSet,"
				+ " 2 for Modify A Set of Options 3 to check the List .\n" + "(One or two , three Only): \n");
		StringBuffer Choice = new StringBuffer("\nFirst Enter " + "Your Choice '1' too start the program: ");

		// Print to prompt user:
		System.out.print(Choice);
		Start = in.nextInt();

		while (Start == 1) {
			// Print to prompt user:
			System.out.print(Prompt);
			System.out.print(DECISION);
			choice = in.nextInt();

			switch (choice) {
			case 1:
				ControlOptionSetArray();
				break;

			case 2:
				ControlOptionArray();
				break;

			case 3:
				AutoNew.PrintDisplayList();
				break;

			default:
				System.out.print(ERROR);
			}
			Start = 0;
			System.out.print(Choice);
			Start = in.nextInt();
		}
	}

	/*--------------------------------------------------------------
	* #	ControlOptionSetArray(): void
	* 
	* Logic:	CONTROL ALL METHOD MODIFY OPTIONSET ARRAY
	*   Decide Which Method to Call by User:
	*   1:	#	Search_Print_A_OptionSet():void
	*   2:	#	Update_A_Option_Set(): void
	*   3:	#	Delete_A_Option_Set(): void
	*   4: 	#	Increase_A_Option_Set(): void
	* 
	----------------------------------------------------------------*/
	protected void ControlOptionSetArray() {
		int choice = 0;
		StringBuffer Prompt = new StringBuffer(
				"\n\tEnter 1 for Search A Set of Option." + "\n\tEnter 2 for Update A Set of Options."
						+ "\n\tEnter 3 for Delete A Set of Options." + "\n\tEnter 4 for Increase A new OptionSet.\n");

		// Print to prompt user:
		System.out.print(Prompt);
		System.out.print(DECISION);
		choice = in.nextInt();

		switch (choice) {
		case 1:
			Search_Print_A_OptionSet();
			break;

		case 2:
			Update_A_Option_Set();
			break;

		case 3:
			Delete_A_Option_Set();
			break;

		case 4:
			Increase_A_Option_Set();
			break;

		default:
			System.out.print(ERROR);
		}
	}

	/*--------------------------------------------------------------
	 * 	#	Search_Print_A_OptionSet():void
	 * 
	 * Logic: Simple Call 
	 * +	Automotive::SearchAndPrintManager(Choice : int):StringBuffer
	 *
	----------------------------------------------------------------*/
	protected void Search_Print_A_OptionSet() {
		// Print the display to prompt from user:
		System.out.print(Auto.Print_Text_OptionSet_Menu(FIND));

		// Validate User input
		int Choice = Validation((Auto.getMaxIndex() + 1), 1);

		System.out.print(Auto.SearchAndPrintManager(Choice));
	}

	/*--------------------------------------------------------------
	 * 	#	Update_A_Option_Set(): void
	 * 
	 * Logic: Simple Prompt Input from User and Call method 
	 *  	+	Automotive::UpdateManager(Choice : int, Name: 
	         *      String, Index:int): void
	 *
	----------------------------------------------------------------*/
	protected void Update_A_Option_Set() {
		// Print the display to prompt from user:
		System.out.print(Auto.Print_Text_OptionSet_Menu(UPDATE));

		// Validate User input
		// int Choice=Validation((Auto.getMaxIndex()+1),1);

		System.out.print("Enter Model_Name here: ");
		// String Model_Name=in.next();

		System.out.print("Enter newName here: ");
		// String Name=in.next();

		// Call UpdateManager
		// Auto.UpdateManager(Model_Name,Choice,Name);
	}

	/*--------------------------------------------------------------
	 * 	#	Delete_A_Option_Set(): void
	 * 
	 * Logic: Simple Prompt Input from User and Call method 
	 *  	+	Automotive::DeleteManager(Choice : int): void
	 *
	----------------------------------------------------------------*/
	protected void Delete_A_Option_Set() {
		// Print the display to prompt from user:
		System.out.print(Auto.Print_Text_OptionSet_Menu(DELETE));

		// Validate User input
		// int Choice=Validation((Auto.getMaxIndex()+1),1);
		// Auto.DeleteManager(Choice);
		// Assign the new OptionSet
	}

	/*--------------------------------------------------------------
	 * 		#	Increase_A_Option_Set(): void
	 * 
	 * Logic: Simple Prompt Input from User and Call method 
	 *  	+	Automotive::IncreaseManager( Name: String, Index:int): void
	 *
	----------------------------------------------------------------*/
	protected void Increase_A_Option_Set() {
		// Print the display to prompt from user:
		System.out.print(Auto.Print_Text_OptionSet_Menu(INCREASE));

		System.out.print("\nEnter newName here: ");
		String Name = in.next();

		Auto.IncreaseManager(Name); // Call IncreaseManager
	}

	/*--------------------------------------------------------------
	 * 		#	Increase_A_Option_Set(): void
	 * 
	 * Logic: 	CONTROL ALL METHOD TO MODIFY OPTION ARRAY
	 * 			Decide Which Method to Call by User:
	 * 
	 *  			1:	Increase_Option() : void
	 *  			2:	Update_A_Option() : void
	 *  			3:	Delete_A_Option() : void
	 *
	----------------------------------------------------------------*/
	protected void ControlOptionArray() {
		int choice = 0;
		StringBuffer Prompt = new StringBuffer("\n\tEnter 1 for Increase A Option,"
				+ "\n\tEnter 2 for Update.\n\tEnter 3 for Delete A Option.\n" + "(One at a time): \n");

		// Print to prompt user:
		System.out.print(Prompt);
		System.out.print(DECISION);
		choice = in.nextInt();

		switch (choice) {
		case 1:
			Increase_Option();
			break;

		case 2:
			Update_A_Option();
			break;

		case 3:
			Delete_A_Option();
			break;

		default:
			System.out.print(ERROR);
		}
	}

	/*--------------------------------------------------------------
	 * 			#	Increase_Option(): void
	 * 
	 * Logic: Simple Prompt Input from User and Call method 
	 *  	+   Automotive::IncreaseAOption(Name: String ,
	         *                              Price : int ,Choice : int ): void
	 *
	----------------------------------------------------------------*/
	protected void Increase_Option() {
		System.out.print(Auto.Print_Text_OptionSet_Menu(INCREASE_A_OPTION));

		System.out.print(DECISION);

		int Choice = Validation((Auto.getMaxIndex() + 1), 1);

		System.out.print(Auto.SearchAndPrintManager(Choice));

		System.out.print("Enter newName here: ");
		String Name = in.next();

		System.out.print("Enter newPrice here: ");
		int Price = in.nextInt();

		Auto.IncreaseAOption(Name, Price, Choice);
	}

	/*---------------------------------------------------------------------------------------
	 * 					#	Delete_A_Option(): void
	 * 
	 * Logic: Simple Prompt Input from User and Call method 
	 *  	+	Automotive::DeleteAOption(Index : int , Choice : int ): void
	 *
	---------------------------------------------------------------------------------------*/
	protected void Delete_A_Option() {
		int Choice;

		System.out.print(Auto.Print_Text_OptionSet_Menu(DELETE_A_OPTION));

		System.out.print(DECISION);
		Choice = Validation((Auto.getMaxIndex() + 1), 1);

		System.out.print(Auto.SearchAndPrintManager(Choice));

		System.out.print("Enter Number to delete here: ");
		int Index = in.nextInt();

		Auto.DeleteAOption(Index, Choice - 1);
	}

	/*---------------------------------------------------------------------------------------
	 * 					#	 Update_A_Option(): void
	 * 
	 * Logic: Simple Prompt Input from User and Call method 
	 *  	+Automotive::UpdateAOption( Index:int ,Choice:int ,Name:String ,Price:int ): void
	 *
	---------------------------------------------------------------------------------------*/
	protected void Update_A_Option() {
		System.out.print(Auto.Print_Text_OptionSet_Menu(UPDATE_A_OPTION));

		System.out.print(DECISION);
		int Choice = Validation((Auto.getMaxIndex() + 1), 1);

		System.out.print(Auto.SearchAndPrintManager(Choice));

		System.out.print(DECISION);
		// int Choice1=in.nextInt();

		System.out.print("Enter newName here: ");
		// String Name=in.next();

		System.out.print("Enter newPrice here: ");
		// int Price=in.nextInt();

		// Auto.UpdateAOption(Choice, Choice1-1, Name,Price);
	}

	// Validation function.
	/*--------------------------------------------------------------
	* 	#	Validation(Max: int , Min : int ):int
	* 
	* Logic:	Method receive Max integer, Min integer and 
	        *               check if the input user is valid or not, 
	* 		Prompt user again and again for invalid input.
	* 
	----------------------------------------------------------------*/
	protected int Validation(int Max, int Min) {
		// Data field:
		int ValidateNumber;
		StringBuffer Prompt = new StringBuffer("\n\tPlease enter the Choice you want to find below:");
		StringBuffer Error = new StringBuffer("\n\tSorry you are enter invalid number," + "Please enter again!");
		Error.append("\n\tValid Number should be smaller than ").append(Max).append(" and bigger than ").append(Min)
				.append(": ");

		// Prompt user for input here
		System.out.print(Prompt);
		ValidateNumber = in.nextInt();

		// if in valid check here until user enter valid number
		while (ValidateNumber > Max || ValidateNumber < Min) {
			System.out.print(Error);
			ValidateNumber = in.nextInt();
		}
		return (ValidateNumber - 1);
	}
	/*--------------------------------------------------------------
	 * 			END SET METHODS OF CONTROLLER
	----------------------------------------------------------------*/
	
	/*--------------------------------------------------------------
	 * 			SET METHODS OF SERIALIZABLE
	----------------------------------------------------------------*/
	
	/*--------------------------------------------------------------
	 * 			SET METHODS OF SERIALIZABLE
	----------------------------------------------------------------*/
	
	/*--------------------------------------------------------------
	 * 	#	StoreInput( Auto : Automotive ): void
	 * 
	 * Logic:	Call ObjectOutputStream::writeObject(Auto) to Store the
	 * 		Auto Object and all its properties to 
	         *              "NewIutput.dat"
	----------------------------------------------------------------*/
	protected void StoreInput(LinkedHashMap<String, Automobile> Set_Of_User_ChoiceHashMap) {
		try {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("NewInput.dat"))) {
				out.writeObject(Set_Of_User_ChoiceHashMap);
			}
		} catch (IOException e) {
			System.out.print("Error: " + e);
			System.exit(1);
		}
	} // end StoreInput

	/*--------------------------------------------------------------
	 * 		#	GetInput(  ): Automotive
	 * 
	 * Logic:	Call ObjectInputStream::readObject(): 
	         *              Automotive to Read the
	 * 		all data and assign to Automotive newStaff
	----------------------------------------------------------------*/
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, Automobile> GetInput() {
		LinkedHashMap<String, Automobile> Set_Of_User_ChoiceHashMap;
		try {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("NewInput.dat"))) {
				Set_Of_User_ChoiceHashMap = (LinkedHashMap<String, Automobile>) in.readObject();
			}
			return Set_Of_User_ChoiceHashMap;
		} catch (IOException | ClassNotFoundException e) {
			System.out.print("Error: " + e);
			System.exit(1);
			return Set_Of_User_ChoiceHashMap = null;
		}
	} // end GetInput

	/*--------------------------------------------------------------
	 * 			END SET METHODS OF SERIALIZABLE
	----------------------------------------------------------------*/

	/*---------------------------------------------------------------------------------------
	 * 					END SET METHODS OF SERIALIZABLE
	---------------------------------------------------------------------------------------*/

} // END UTIL CLASS
