/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception_Handle;

/**
 *
 * @author TrucHuynh
 */
import java.util.Scanner;




@SuppressWarnings("serial")
public final class Exception_Handle_Manager extends Exception {

	Scanner inScanner=new Scanner(System.in);
	
	private int errorno;
	private String errormsg;
	private String error_Name;
        
	/*----------------------------------------------------------------------
	 * 				SET OF CONSTRUCTOR
	------------------------------------------------------------------------*/
	//Constructor:
	public Exception_Handle_Manager() {
		super();
		printmyproblem();
	}

	// Receive the interger error number
	public Exception_Handle_Manager(int errorno) {
		super();
		this.errorno = errorno;
		Get_Error_Massage();
		Get_Error_Name();
		printmyproblem();
	}
	
	
	/*----------------------------------------------------------------------
	 * 				SET METHOD OF SET ERROR NAME 
	 * 					AND ERROR MESSAGE
	 *
	------------------------------------------------------------------------*/
	
	/*----------------------------------------------------------------------
	 * 			SET METHOD OF SET ERROR NAME AND ERROR MESSAGE
	 * 
	 *Logic:		Get_Error_Name Base on the integer error number 
	 *					by using switch statement
	------------------------------------------------------------------------*/
	public final void Get_Error_Name()
	{
		switch (errorno)
		{
		case 1:
			error_Name="File Name"; break;
		case 2:
			error_Name="Model_Name"; break;
		case 3:
			error_Name="Base_Price"; break;
		case 4:
			error_Name="Total_OptionSet_Elements (less than 5)"; break;
		case 5:
			error_Name="Option_Set_Name"; break;
		case 6:
			error_Name="Options_Set_Total_Choice"; break;
		case 7:
			error_Name="Option_Price_"; break;
		case 8:
			error_Name="Options_Name_"; break;
                case 9:
                        error_Name="Array_Index out of bound";break;
		default: 	
                        error_Name="New Error";
		}
	}

	public final void Get_Error_Massage()
	{
		switch (errorno)
		{
		case 1:
			errormsg="\tYou are missing a File Name, or the file "
                                + "name you enter are in correct.\n"; 
                        break;
		case 2:
			errormsg="\tYou are missing Model_Name in your text file"
                                + ", or the file name in the text "
                                + "is in invalid format";
                        break;
		case 3:
			errormsg="\tYou are missing Base Price in your text "
                                + "file, or the base price is in invalid format";
                        break;
		case 4:
			errormsg="\tYou are Missing the Total Elements in yours "
                                + "text file, or the total elements is invalid"
                                + " format ";
                        break;
		case 5:
			errormsg="\tYou are missing Option_Set_Name in your text"
                                + "file, or the total Option_Set_Name is invalid"
                                + " format "; 
                        break;
		case 6:
			errormsg="\tYou are missing Option_Set_Total_Choice in "
                                + "your text file, or the total "
                                + "Option_Set_Total_Choice "
                                + "is invalid format "; 
                        break;
		case 7:
			errormsg="\tYou are missing Option_Set_Total_Choice in "
                                + "your text file, or the total "
                                + "Option_Set_Total_Choice"
                                + " is invalid format "; 
                        break;
		case 8:
			errormsg="\tYou are missing Option_Set_Total_Choice in "
                                + "your text file, or the total "
                                + "Option_Set_Total_Choice"
                                + " is invalid format ";
                        break;
                case 9:
			errormsg="\tYou are trying delete an invalid Option or "
                                + "Option_Set, or the index "
                                + "you enter is invalid";
                        break;
		default: 	
			errormsg="A new error just occur, Please wait for "
                                + "technicaian update the list.";
		}
	}
	
	/*----------------------------------------------------------------------
	 * 				SET METHOD OF SETTER AND GETTER
	------------------------------------------------------------------------*/
	
	protected String getError_Name() {
		return error_Name;
	}

	
	protected void setError_Name(String error_Name) {
		this.error_Name = error_Name;
	}

	
	public int getErrorno() {
		return errorno;
	}
	
	
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	
	
	public String getErrormsg() {
		return errormsg;
	}
	
	
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	
	public String Fix_Input_String_Problem()	{
		System.out.println("Please enter the correct "+ error_Name 
                        +" for Your Automotive: ");
                String _NameString= inScanner.next();
		return _NameString;
		
	}
	
	public int Fix_Input_Integer_Problem()	{
		System.out.println("Please enter the correct "+ error_Name 
                        +" for Your Automotive: ");
                int _Price= inScanner.nextInt();
		return _Price;
	}
	
	/*----------------------------------------------------------------------
	 * 				SET OF PRINT METHOD
	------------------------------------------------------------------------*/
	public void printmyproblem() {
		System.out.println("FixProblems [errorno=" + errorno 
                        + ", errormsg=\n" + errormsg +" ]"); 
	}

	
}

