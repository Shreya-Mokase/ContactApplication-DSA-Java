import java.util.Scanner;

class Node{
	String name;
	String phn;
	Node next = null;
	Node prev = null;

	Node(String Name, String PhnNo) {
		name = Name;
		phn = PhnNo;
		next = null;
		prev = null;
	}
}

class Contacts {

	Scanner sc = new Scanner(System.in);

	Node head = null, curr = null, ptr = null;

	void create() {
		int ch = 0;
		do {
			System.out.println("Enter Name : ");
			String name = sc.next();
			System.out.println("Enter Phone Number : ");
			String phn = sc.next();
			if (phn.length()>10 || phn.length()<10) {
				System.out.println("Not a valid Phone number");
				
			}
			else {

			Node temp = new Node(name, phn);

			if (head == null) {
				head = temp;
			} 
			else 
			{

				ptr = head;      					//ptr will always be before of curr
				curr = head;						//curr will always ahead of ptr		just in start case both are at same place

				while (curr != null && ptr != null) {

					int p = name.compareTo(curr.name);     //will compare name you entered and at curr place 
					int q = name.compareTo(ptr.name);		//pos val=when string(name) is greater than currname,  neg val when string(name) smaller

					if (p < 0 && q > 0) {// insert at middle   //when given name if greater than one already present name and lower than the one already present node
						ptr.next = temp;
						temp.prev = ptr;
						curr.prev = temp;
						temp.next = curr;
						break;
					}

					if (p > 0 && q > 0) {// insert at last
						ptr = curr;
						curr = curr.next;
					}

					if (p < 0 && q < 0) {// insert at first
						curr = ptr;
						ptr = ptr.prev;
					}

				}
				if (curr == null && ptr.next == null) {			//while inserting at last 
					ptr.next = temp;
					temp.prev = ptr;
				}

				if (ptr == null && curr.prev == null) {			//while inserting at first (temp will be head now)
					curr.prev = temp;
					temp.next = curr;
					head = temp;
				}

			}
			}
			System.out.println("Press 1 to continue  : ");
			ch = sc.nextInt();
		} while (ch == 1);
		

	}

	void display() {

		if (head == null) {
			System.out.println(" :(  No contacts found");
			return;
		}
		curr = head;  //curr is pointing to head

		char a = curr.name.charAt(0);     //extracting the 1 char of name present in curr (already stored in sorted way)

		System.out.print("\n[ " + a + " ]\n");
		while (curr != null) {

			if (curr.name.charAt(0) != a) {    //checking if given and current has same initial char.
				a = curr.name.charAt(0);		//if no then, extract the initial char from new curr

				System.out.println("\n\n[ " + a + " ]");
			}
			System.out.println();					//if yes, then print
			System.out.println("-----------------------------------");
			System.out.println("  Name : " + curr.name + "           ");
			System.out.println("  Phone Number : " + curr.phn + "    ");
			System.out.print("-----------------------------------");
			
			curr = curr.next;    //incrementing current

		}

	}

	void delete(String name) {
		int flag=0;
		if (head == null) {
			System.out.println("\nContact list is empty.");
			flag=1;
			return;
		}

		if ((head.name).equals(name)) {				//if head contains name then point that head to 
			if (head.next == null) {										//some other name(p) make it null and increase head 
				head = null;
				return;

			} else {
				
				Node p = head;
				p = null;
				head = head.next;
				System.out.println("\nContact Deleted");
				flag=1;
			}
		}
		curr = head;     //curr is pointing to head
		ptr = null;			

		while (curr != null) {											//iterate till curr is null
			if ((curr.name).equals(name) ) {		//when name and phn equals
				System.out.println("\nContact Deleted");
				flag=1;
				if (curr.next == null) {       							//when curr is last node
					ptr.next = null;									//make both the pointers null
					curr = null;
					break;
				} 
				else {													//when curr is in bet. somewhere
					curr.prev.next=ptr.next;
					curr.next.prev = curr.prev;
					curr = null;

				}

			} else {
				ptr = curr;										//increment curr
				curr = curr.next;
			}
		}
		
		if(flag==0) {
			System.out.println("\nNo such contact exist");
		}

	}

Node search_Name(String search) {
		boolean flag = false;

		curr = head;

		while (curr != null) {
			if (search.equals(curr.name)) {
				System.out.println("\n :) Name Found!!!");
				System.out.println("-----------------------------------");
				System.out.println("  Name : " + curr.name + "           ");
				System.out.println("  Phone Number : " + curr.phn + "    ");
				System.out.println("-----------------------------------");
				System.out.println("");
				flag = true;
				return curr;

			} else {
				flag = false;
				curr = curr.next;

			}
		}

		if (flag == false) {
			System.out.println(" :(  No such contact exist...");
		}
		return null;
	}

	void insert(String name, String phn) {
		curr = null;
		ptr = null;
		Node temp = new Node(name, phn);
		System.out.println("\nContact successfully added.");
		if (head == null) {
			head = temp;
		} else {
			ptr = head;
			curr = head;

			while (curr != null && ptr != null) {

				int p = name.compareTo(curr.name);
				int q = name.compareTo(ptr.name);

				if (p < 0 && q > 0) {// insert at middle
					ptr.next = temp;
					temp.prev = ptr;
					curr.prev = temp;
					temp.next = curr;
					break;
				}

				if (p > 0 && q > 0) {// insert at last
					ptr = curr;
					curr = curr.next;
				}

				if (p < 0 && q < 0) {// insert at first
					curr = ptr;
					ptr = ptr.prev;
				}

			}
			if (curr == null && ptr.next == null) {
				ptr.next = temp;
				temp.prev = ptr;
			}

			if (ptr == null && curr.prev == null) {
				curr.prev = temp;
				temp.next = curr;
				head = temp;
			}

		}

	}

	void logout(String S) {

		if (S.equals("YES") || S.equals("yes")) {
			head = null;
			System.out.println("-----------------------------");
			System.out.println("| Successfully Logged Out!!! |");
			System.out.println("-----------------------------");
			return;
		}

	}

	public void update(String nm, int choice) {
		ptr=head;
		int flag=0;
		if(choice==1) {
		while(ptr!=null) {
			if(ptr.name.equals(nm)) {
				flag=1;
				System.out.println("\nEnter new name :");
				String newnm=sc.next();
				ptr.name=newnm;
				System.out.println("\nContact Name updated Successfully");
			}
			ptr=ptr.next;
			}
		}
		if (choice==2) {
			while(ptr!=null) {
				if(ptr.name.equals(nm)) {
					flag=1;
					System.out.println("\nEnter new number for contact : "+nm);
					String newno=sc.next();
					if (newno.length()>10 || newno.length()<10) {
						System.out.println("Not a valid Phone number");
						
					}
					else {
					ptr.phn=newno;
					System.out.println("\nContact Name updated Successfully");
					}
				}
				ptr=ptr.next;
			}
		}
		if(flag==0) {
			System.out.println("No such contact Exist..");
		}
		
	}

}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Contacts c = new Contacts();
		int ab = 0;
		int ch = 0;
		
		String Password = null;
		String user = "@abc";
		int otp = 1234;

		System.out.println(
				"\n---------------------------------   WELCOME TO CONTACT APPLICATION   ---------------------------------");

		Password = "cummins@123";

		do {
			System.out.println();
			System.out.println("");
			System.out.println(
					"\n| ----------------------------------------------------------------------------------------------|\n\n|\t 1-Login\t|\t2-Logout\t|\t3-Forgot password\t|\t4-Exit\t|\n\n| ----------------------------------------------------------------------------------------------|\n\nEnter choice : ");

			int a = sc.nextInt();
			switch (a) {
			case 1:
				System.out.println("Enter password : ");
				String h = sc.next();
				if (h.equals(Password)) {
					System.out.println("-----------------------------");
					System.out.println("| Logged In Successfully!!! :) |");
					System.out.println("-----------------------------");
					ab = 1;
				} else {
					System.out.println();
					System.out.println("----------------------");
					System.out.println("| Invalid Password...|");
					System.out.println("| Try Again :(       |");
					System.out.println("----------------------");
				}
				break;

			case 2:
				ab = 2;
				break;

			case 3:
				ab = 3;
				break;

			case 4:
				ab = 4;
				break;

			}

			switch (ab) {

			case 1:
				do {
					System.out.println("\n\n***** WELCOME TO CONTACTS *****\n");
					System.out.println();
					System.out.println("--------------------------------");
					System.out.println(
							"|    1-Create contact          |\n|    2-Show contact list       |\n|    3-Search contact          |\n|    4-Delete contact          |\n|    5-Add new contact         |\n|    6-Update Existing contact |\n|    7-EXIT                    |\n|    Enter choice :            |");
					System.out.println("--------------------------------");
					ch = sc.nextInt();

					switch (ch) {
					case 1:// create
						c.create();
						break;

					case 2:// display
						c.display();
						break;


					case 3: // search
						System.out.println("Enter name to search contact : ");
						String search = sc.next();
						c.search_Name(search);
						break;

					case 4:
						// delete
						System.out.println("Enter name and phone number to delete : ");
						String name = sc.next();
						c.delete(name);
						break;

					case 5:
						System.out.println("Enter name and phone number to insert : ");
						String name1 = sc.next();
						String phn1 = sc.next();
					
						c.insert(name1, phn1);
					
						break;
					case 6:
						System.out.println("\nUpdating Existing Contact. \n1. Update Name \n2. Update phone Number\nEnter your choice :");
						ch=sc.nextInt();
						if (ch==1) {
						System.out.println("\nEnter Name that you want to update :");
						String nm=sc.next();
						c.update(nm,1);
						}
						if (ch==2) {
							System.out.println("\nEnter Name whose number that you want to update :");
							String nm=sc.next();
							c.update(nm,2);
						}
						break;

					case 7:
						System.out.println("-----------------------------");
						System.out.println("| Exited from sub window... |");
						System.out.println("-----------------------------");

					}
				} while (ch < 7);

				break;

			case 2:
				System.out.println("Do you want to logout? (yes/no)");
				String p = sc.next();
				c.logout(p);

				break;
			case 3:
				System.out.println("Enter username : ");
				String name = sc.next();
				if (!name.equals(user)) {
					System.out.println("--------------------------");
					System.out.println("|  Invalid Username !!!  |");
					System.out.println("--------------------------");
				}

				if (name.equals(user)) {
					System.out.println();
					System.out.println("Enter OTP : ");
					int OTP = sc.nextInt();

					System.out.println("Enter new Password : ");
					Password = sc.next();
					System.out.println("---------------------------------------");
					System.out.println("|          Password Updated !!!       |");
					System.out.println("|  Use your new password to log in  \nfor this session only... |");
					System.out.println("---------------------------------------");

				}

				break;

			case 4:
				System.out.println("----------------------------------");
				System.out.println("|  Exited from application !!!   |");
				System.out.println("----------------------------------");
				break;
			}

		} while (ab < 4);
	}

}
