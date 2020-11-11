package Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;


//==============================================================================//
@SuppressWarnings("serial")
public class OptionSet implements Serializable{

		//Data field:
		private String Name;
		private ArrayList<Option> Options;
		private Option User_Choice_;
		final String OutOfBound="The index you enter is out of bound\n";
	
      
		//Constructor:
		/*--------------------------------------------------------------
	 	* 			CONSTRUCTOR :
		----------------------------------------------------------------*/
		protected OptionSet(){
			super();
			Options= new ArrayList<Option>();
			//for further use
		}
		
		
		protected OptionSet(String name, ArrayList<Option> options) {
			super();
			Options= new ArrayList<Option>();
			Name = name.toUpperCase(Locale.getDefault());
			Options = options;
		}
		
		
		public OptionSet(String name) {
			super();
			Options= new ArrayList<Option>();
			this.Name = name.toUpperCase(Locale.getDefault());
		}

		
		/*--------------------------------------------------------------
	 	* 			GETTER METHOD :
		----------------------------------------------------------------*/
		protected String getName() {
			return Name;
		}
		
		protected ArrayList<Option> getOptions() {
			return Options;
		}
		
		protected Option  getChoice() {
                    return  User_Choice_ ;
                }
                
		/*--------------------------------------------------------------
	 	* 			SETTER METHOD :
		----------------------------------------------------------------*/
		protected void setName(String name) {
			Name = name.toUpperCase(Locale.getDefault());
		}
		
		//Set a set of Options
		public void setOptions(ArrayList<Option> options) {
				Options=options;
		}
		
                protected void setChoice(Option choice) {
                    User_Choice_ = choice;
                }
		
		/*--------------------------------------------------------------
	 	* 			SEARCH METHOD :
	 	* #	Search(	PartName:String )	:	Option 
		----------------------------------------------------------------*/

		protected Option Search(String PartName)	{
			Option tempOption = null;
			for (Option Opn : Options){
				  if (Opn.getPartName().equals(PartName.toUpperCase(Locale.getDefault())))	{
					  tempOption=Opn;
					  break;
				  }     //end if
				}
			return tempOption;
		}
                
                
       /*--------------------------------------------------------------
	 	* 		SET OF MANIPULATE METHOD :
	 	* 
	 	* #	AssignOptions( String Name, int Price): Option
	 	* 
	 	* 
	 	* 
		----------------------------------------------------------------*/
        //Get User_Choice
        protected Option Get_User_Choice()
        {
            return User_Choice_;
        }
        
        
        //Set the Choice of the User in the OptionSet (List of Option Choice)
        boolean set_Option_Choice(String Option_Name)    {
            boolean Flag=false;
            for(Option i: Options)  {
                if(Option_Name.equalsIgnoreCase(i.getPartName()))    {
                    User_Choice_=i;
                    Flag= true;
                    break;
                } 
            } 
              return Flag;        
        }
        
        
        String User_Choice_Name()
        {
            return User_Choice_.getPartName();
        }
        
        
        double User_Choice_Price()
        {
            return User_Choice_.getPrice();
        }
        
		/*--------------------------------------------------------------
	 	* 		SET OF MANIPULATE METHOD :
	 	* 
	 	* #	AssignOptions( String Name, int Price): Option
	 	* #	Delete_Option_Manager( Index:int ): void
	 	* #	Increase_Option_Manager( Name:String , Price:int ): void
	 	* #	Update_Option_Manager( Index:int , Name:String , 
                *                                               Price:int ) : void
	 	* 
		----------------------------------------------------------------*/
        
        //FIND method return an Option 
	protected Option findReturnOption(String name)
        {
            if (!(Options==null))
            {
                for(Option opt:Options)
                {
                if(opt.getPartName().equals(name))
                    {
                        return opt;
                    }
                }
                return null;
            }
            else return null;
        }
        
		/*--------------------------------------------------------------
	 	* #	AssignOptions( String Name, int Price): Option
	 	* Logic:			Allocate and return a new Option 
		----------------------------------------------------------------*/
		private Option AssignOptions( String Name, double Price){
			//Assign the new Option to the list;
			return new Option(Name.toUpperCase(Locale.getDefault()),Price);
		}

		
		/*--------------------------------------------------------------
	 	*	#	Delete_Option_Manager( Index:int ): void
	 	*
	 	* Logic: Delete Manager Delete An Option provide by Index Value
	 	* 					Call setOptions ()
		----------------------------------------------------------------*/
		protected void Delete_Option_Manager(int Index){		
				Options.remove(Index);									//Decrease TotalIndex 1	
		}
		
		
		/*--------------------------------------------------------------
	 	*   #	Increase_Option_Manager( Name:String , Price:int ): void
	 	*
	 	* Logic: Increase Manager Increase An Option to the 
                *                   last in the array
		----------------------------------------------------------------*/
		protected void Increase_Option_Manager( String Name,double Price) {
                        //Assign Option to the last elements in the Array
			Options.add(AssignOptions(Name,Price));
		}
		

		/*--------------------------------------------------------------
	 	* #	Update_Option_Manager( Index:int , Name:String ,
                *                                       Price:int ) : void
	 	*
	 	* Logic:        Update Manager Update the list with the 
                *                           appropriate Index:
		----------------------------------------------------------------*/
		protected void Update_Option_Manager(int Index,String Name, int Price){
			Options.add(AssignOptions(Name,Price));						
		}
		
		protected void Update_Option_Price(String Name, double Price){
				Options.get(Search_List(Name)).setPrice(Price);
		}
		
		int Search_List(String Option_Name)
		{
			int k=0;
			for(Option Op:Options)	{
				if(Op.getPartName().equalsIgnoreCase(Option_Name))
					return k;
				k++;
			}
			return -1;
		}
				
		/*--------------------------------------------------------------
	 	* 		METHODS OF PRINTING
	 	*   # DisplayOptionSet(): StringBuffer
		----------------------------------------------------------------*/
		protected StringBuffer DisplayOptionSet()
		{
			int i=0;
			StringBuffer Str;
                    Str = new StringBuffer
                ("\n\tDISPLAY SET OF OPTION " + Name + ".\n");
			for (Option Opn : Options){
				Str.append("Option ").
                                        append(i+1).
                                        append(" is :").append(Opn).append("\n");
				i++;
			}
			return Str;
		}
		
//==============================================================================//
		//Inner Class Option
		public class Option implements Serializable{
		
			//Data field:
			private String PartName;
			private double price;
			
			/*------------------------------------------------------
		 	* 			CONSTRUCTOR :
			--------------------------------------------------------*/
			protected Option(String partName, double price) {
				super();
				this.PartName = partName.toUpperCase();
				this.price = price;
			}
			
                        protected Option(String partName) {
				super();
				this.PartName = partName.toUpperCase();
			}
                        
			protected Option(){
				super();
				PartName="No Part ";
				price=0;
				//For further use
			}
			
			/*------------------------------------------------------
		 	* 			GETTER METHOD :
			--------------------------------------------------------*/
			protected String getPartName() {
				return PartName;
			}
			
			protected double getPrice() {
				return price;
			}
			

			/*------------------------------------------------------
		 	* 			SETTER METHOD :
			--------------------------------------------------------*/
			protected void setPartName(String partName) {
				PartName = partName.toUpperCase();
			}
			
			protected void setPrice(double price2) {
				this.price = price2;
			}
			
			
			@Override
			/*------------------------------------------------------
		 	* 			METHODS OF PRINTING
		 	* 		+	toString(): String
			--------------------------------------------------------*/
			public String toString() {
				return "Option [ PartName = " + PartName 
                                        + ", price = " + price + " ]";
			}
	}//End Class Option
//==============================================================================//
}	//End OptionSet Class
