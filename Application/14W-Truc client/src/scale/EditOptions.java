//package name
package scale;				

//import library
import Model.Automobile; // import Automobile from Model package
import java.util.Locale;

public class EditOptions extends Thread {
	protected Automobile automobile;
	protected int Operation;
	protected String Model;
	protected String optionName;

	/**
	 * Constructor EditOptions
	 * 
	 * OptionName : indicate what Operation is given automobile : Automobile to
	 * modify Model : What Model is this OptionName : depend on the integer
	 * operation the OptionName take different position...
	 * 
	 * @param NewAuto
	 * @param Operation
	 * @param ModelName
	 * @param OptionName
	 * 
	 */
	public EditOptions(Automobile NewAuto, int Operation, String ModelName, String OptionName) {
		this.automobile = NewAuto;
		this.Operation = Operation;
		this.Model = ModelName.toUpperCase(Locale.getDefault());
		this.optionName = OptionName.toUpperCase(Locale.getDefault());
	}

	/**
	 * Run method Override the Thread run and call the +
	 * OptionSet_Edit_Manager(Operation : int , ModelName :String , Name : String ):
	 * void To edit ...
	 */
	@Override
	public void run() {
		synchronized (automobile) {
			OptionSet_Edit_Manager(Operation, Model, optionName);
		}
	}

	/**
	 * + OptionSet_Edit_Manager(Operation : int , ModelName :String , Name : String
	 * ): void
	 *
	 * + Receive the Operation : int to decide what moficatiuon will happen Using
	 * the same parameter field for increase , update and delete 
	 * + Process():void function to do st with the thread in this case just call the
	 * Thread to sleep and also show how many thread is active and what thread is in
	 * active 
	 * + Can extend this class to modify more method in Automotive
	 *
	 *
	 */
	public void OptionSet_Edit_Manager(int Operation, String ModelName, String Name) {
		switch (Operation) {
		case 1:
			Increase_A_OptionSet(ModelName, Name);
			Process();
			break;
		case 2:
			Delete_A_OptionSet(ModelName, Name);
			Process();
			break;
		case 3:
			Print_A_OptionSet(Name);
			Process();
			break;
		default:
		}
	}

	/**
	 * + Process(): void
	 *
	 * Call Process():void function to do st with the thread in this case just call
	 * the Thread to sleep and also show how many thread is active and what thread
	 * is in current active catch InterruptedException...
	 *
	 */
	public void Process() {
		try {
			System.out.println("Total Threads active is: " + Thread.activeCount());
			System.out.println("Thread in process is: " + Thread.currentThread() + " is in process");
			Thread.sleep((long) (500 * Math.random()));
		} catch (InterruptedException ex) {
			System.out.println("Interrupted!");
		}
	}

	/**
	 * + Delete_A_OptionSet( ModelName: String , Name : String ): void
	 * 
	 * Simple Call: Automobile::DeleteManager(ModelName: String , Name : String) to
	 * delete a OptionSet with given Name
	 * 
	 */
	public void Delete_A_OptionSet(String ModelName, String Name) {
		System.out.println("\nDelete A_OptionSet of " + automobile.getModel() + ".\n");
		automobile.DeleteManager(ModelName, Name);
	}

	/**
	 * + Increase_A_OptionSet( ModelName: String , Name : String ): void
	 * 
	 * Simple Call: Automobile::UpdateManager(ModelName: String , Name : String) to
	 * increase a OptionSet with new Name
	 * 
	 */
	public void Increase_A_OptionSet(String ModelName, String New_Name) {
		System.out.println("\nIncrease A_OptionSet of " + automobile.getModel() + ".\n");
		automobile.UpdateManager(ModelName, New_Name);
	}

	/**
	 * + Print_A_OptionSet( Option_Name : String ): void
	 * 
	 * Simple Call: Automobile::SearchAndPrintManager(Option_Name : String) to print
	 * a set of Options
	 * 
	 */
	public void Print_A_OptionSet(String Option_Name) {
		System.out.println("\nPrint A_OptionSet of " + automobile.getModel() + ".\n");
		System.out.println(automobile.SearchAndPrintManager(Option_Name));
	}

}
