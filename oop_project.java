
//importing package
import java.io.*;
import java.util.*;

//product class for details of products
class Product {
	int pid;
	String name;
	float price;

	// item creation from admin side
	ArrayList<Integer> PID = new ArrayList<Integer>();
	ArrayList<String> ProName = new ArrayList<String>();
	ArrayList<Float> ProPrice = new ArrayList<Float>();

	// at time for invoice creation
	ArrayList<Integer> OrderPID = new ArrayList<Integer>();
	ArrayList<Integer> OrderQuantity = new ArrayList<Integer>();
	ArrayList<String> OrderName = new ArrayList<String>();
	ArrayList<Float> OrderPrice = new ArrayList<Float>();
	ArrayList<Float> OrderNonCalcPrice = new ArrayList<Float>();

}

// FuncitonClass for all the functions
class FunctionsClass extends Product {
	Scanner sc = new Scanner(System.in);
	public int i = 0; // global declaration
	public int checker = 0, index = 0;

	// for placing orders
	public void PlaceOrder() {
		//variable declaration
		int ordercount = 0;
		int order;
		int quantity;
		float amt;
		float total_amt = 0;
		String ch = "y";

		// calling ItemMenu to see menu
		ItemMenu();

		// If Arraylist is empty program will exit
		if (PID.size() <= 0) {
			System.exit(0);
		}
		// else it will ask user for inputs
		else {
			while (ch.equals("y") || ch.equals("Y")) {
				System.out.print("\nEnter the PID of product you want to buy: ");
				order = sc.nextInt();

				for (int x = 0; x < PID.size(); x++) {
					if (order == PID.get(x)) {
						checker = 1;
						index = x;
					}
				}

				if (checker == 1) {
					System.out.print("\nEnter the quantity of product: ");
					quantity = sc.nextInt();
					// for calculating the total price of product
					amt = ProPrice.get(index) * quantity;
						
					// totaling the prices of all products
					total_amt += amt;
					
					// adding details of ordered items for invoice
					OrderNonCalcPrice.add(ProPrice.get(index));
					OrderPrice.add(amt);
					OrderName.add(ProName.get(index));
					OrderPID.add(PID.get(index));
					OrderQuantity.add(quantity);
					ordercount++;
				} else {
					System.out.println("\nInvalid PID entered...ENTER AGAIN !!!\n");
				}

				checker = 0;

				System.out.print("\nDo You Want To Order Another Product ? (y/n) ");
				ch = sc.next();
			}

			// Printing INVOICE
			if (ordercount > 0) {
				System.out.print("\033[H\033[2J");
				System.out.println("\n-------------------------------------------------------------------------");
				System.out.println("\t\t\t\tI N V O I C E");
				System.out.println("-------------------------------------------------------------------------\n");
				System.out.println("Product ID\t\tProduct Name\t\tQuantity\t\tM  R  P\t\t\tTotal-MRP");

				for (int j = 0; j < ordercount; j++) {
					System.out.println(OrderPID.get(j) + "\t\t\t" + OrderName.get(j) + "\t\t\t" + OrderQuantity.get(j)
							+ "\t\t\t" + OrderNonCalcPrice.get(j) + "\t\t\t" + OrderPrice.get(j));
				}

				System.out.println("\n\t\t\t\t\t\t\t\tTotal Bill:\t" + total_amt);

				System.out.println("\n\nThank You For Placing The Order!!\n");

				// making txt file of invoice for customer
				try {
					File f = new File("D:\\invoice.txt");
					if (f.exists()) {

					} else {
						f.createNewFile();
					}
					try (FileWriter fw = new FileWriter(f)) {
						fw.write("\n-------------------------------------------------------------------------");
						fw.write("\n\t\t\t\tI N V O I C E");
						fw.write("\n-------------------------------------------------------------------------\n");
						fw.write("\nProduct ID\t\tProduct Name\t\tQuantity\t\tM  R  P\t\t\tTotal-MRP\n");
						for (int j = 0; j < ordercount; j++) {
							fw.write(OrderPID.get(j) + "\t\t\t" + OrderName.get(j) + "\t\t\t" + OrderQuantity.get(j)
									+ "\t\t\t" + OrderNonCalcPrice.get(j) + "\t\t\t" + OrderPrice.get(j));
							fw.write("\n");
						}

						fw.write("\n\t\t\t\t\t\t\t\tTotal Bill:\t" + total_amt);

						fw.write("\n\nThank You For Placing The Order!!\n");
						fw.close();
					}
				} catch (Exception e) {
					System.out.println("\nException: " + e);
				}

				// making txt file for history of invoice for admin
				try {
					File f1 = new File("D:\\orderhistory.txt");
					if (f1.exists()) {

					} else {
						f1.createNewFile();
					}
					try (FileWriter fw1 = new FileWriter(f1, true)) {
						fw1.write("\n-------------------------------------------------------------------------");
						fw1.write("\n\t\t\t\tI N V O I C E");
						fw1.write("\n-------------------------------------------------------------------------\n");
						fw1.write("\nProduct ID\t\tProduct Name\t\tQuantity\t\tM  R  P\t\t\tTotal-MRP\n");
						for (int j = 0; j < ordercount; j++) {
							fw1.write(OrderPID.get(j) + "\t\t\t" + OrderName.get(j) + "\t\t\t" + OrderQuantity.get(j)
									+ "\t\t\t" + OrderNonCalcPrice.get(j) + "\t\t\t" + OrderPrice.get(j));
							fw1.write("\n");
						}

						fw1.write("\n\t\t\t\t\t\t\t\tTotal Bill:\t" + total_amt);

						fw1.write("\n\n-------------------------------------------------------------------------\n");
						fw1.close();
					}
				} catch (Exception e) {
					System.out.println("\nException: " + e);
				}
			} else {
				System.out.println("CART EMPTY!!!");
			}
		}

	}

	// CreateItem for creating product
	public void CreateItem() {
		int num = 0;
		i = 1;
		System.out.println("\n-----------------------------------------------------");
		System.out.println("\t\tCREATE PRODUCT");
		System.out.println("-----------------------------------------------------\n");
		System.out.print("\nEnter the number of product you want to enter: ");
		num = sc.nextInt();
		while (num != 0) {

			System.out.print("\n");
			System.out.print("Enter PID for " + (i) + " product: ");
			pid = sc.nextInt();
			for (int x = 0; x < PID.size(); x++) {
				if (pid == PID.get(x)) {
					checker = 1;
				}
			}
			if (checker == 1) {
				System.out.println("Item already EXISTS. Enter new details.");
				checker = 0;
			} else {
				PID.add(pid);
				System.out.print("Enter NAME for " + (i) + " product: ");
				name = sc.next();
				ProName.add(name);

				System.out.print("Enter PRICE for " + (i) + " product: ");
				price = sc.nextFloat();
				ProPrice.add(price);
				num--;
				i++;
			}
			System.out.print("\n");
		}

	}

	// ItemMenu to show the menu
	public void ItemMenu() {

		// If Arraylist is empty program will exit
		if (PID.size() <= 0) {
			System.out.println("Menu is Empty!! Please Add products in Administrator -> Create Product...");
			System.exit(0);
		} // else it will display Menu as output
		else {
			System.out.println("\n-----------------------------------------------------");
			System.out.println("\t\t\tMENU");
			System.out.println("-----------------------------------------------------\n");
			System.out.println("Product ID\t\tProduct Name\t\tPrice ");

			for (i = 0; i < PID.size(); i++) {
				System.out.println(PID.get(i) + "\t\t\t" + ProName.get(i) + "\t\t\t" + ProPrice.get(i));
			}

		}
	}

	// ModifyItem to modify the prices of Products entered
	public void ModifyItem() {
		int oldPID;
		float newPrice;
		String newName;
		int index = 0;
		ItemMenu();

		if (PID.size() <= 0) {
			System.out.println("Menu is Empty!! Please Add products in Administrator -> Create Product...");
		} else {
			System.out.print("Enter the Product ID(PID) which you want to modify: ");
			oldPID = sc.nextInt();
			for (i = 0; i < PID.size(); i++) {
				if (oldPID == PID.get(i)) {
					index = i;

					System.out.print("Enter New Name for " + ProName.get(i) + " : ");
					newName = sc.next();
					ProName.set(index, newName);

					System.out.print("Enter New Price for " + ProName.get(i) + " : ");
					newPrice = sc.nextFloat();
					ProPrice.set(index, newPrice);

				} else {
					continue;
				}
			}
			System.out.println("\nDATA MODIFIED !!!");
		}
	}

	// DeleteItem for deleting existing product from menu
	public void DeleteItem() {
		int delPID;
		int index = 0;
		ItemMenu();
		if (PID.size() <= 0) {
			System.out.println("Menu is Empty!! Please Add products in Administrator -> Create Product...");

		} else {
			System.out.print("\nEnter the Product ID(PID) which you want to delete: ");
			delPID = sc.nextInt();

			for (int i = 0; i < PID.size(); i++) {
				if (delPID == PID.get(i)) {
					index = i;
					System.out.println("\n" + ProName.get(index) + " Deleted Successfully!!");
					PID.remove(index);
					ProPrice.remove(index);
					ProName.remove(index);
					
					checker = 0;
					break;
				} else {
					checker = 1;
				}
			}
			if (checker == 1) {
				System.out.println("PID doesn't exist. Enter again!!!");
				DeleteItem();
				checker = 0;
			}
		}
	}
}

class MenuConsole extends FunctionsClass {
	Scanner sc = new Scanner(System.in);

	void DisplayMenu() {
		System.out.println("\n-----------------------------------------------------");
		System.out.println("\t\t\tMENU");
		System.out.println("-----------------------------------------------------\n");
		System.out.println("1. Administrator");
		System.out.println("2. Customer");
		System.out.println("3. Exit");
		System.out.print("\nEnter Your Choice: ");
		int number = sc.nextInt();
		System.out.print("\033[H\033[2J");
		switch (number) {
		case 1:
			DisplayAdmin();
			break;
		case 2:
			PlaceOrder();
			System.exit(0);
			break;
		case 3:
			System.exit(0);
			break;

		default:
			System.out.println("\nInvalid Choice...");
			DisplayMenu();
		}
	}

	void DisplayAdmin() {
		System.out.println("\n-----------------------------------------------------");
		System.out.println("\t\tADMINISTRATION");
		System.out.println("-----------------------------------------------------\n");
		System.out.println("1. Create Product");
		System.out.println("2. Display All Products");
		System.out.println("3. Modify Product");
		System.out.println("4. Delete Product");
		System.out.println("5. Back To Main Menu");
		System.out.print("\nEnter Your Choice: ");
		int ch = sc.nextInt();
		System.out.print("\033[H\033[2J");
		switch (ch) {
		case 1:
			CreateItem();
			DisplayAdmin();
			break;
		case 2:
			ItemMenu();
			DisplayAdmin();
			break;
		case 3:
			ModifyItem();
			DisplayAdmin();
			break;
		case 4:
			DeleteItem();
			DisplayAdmin();
			break;
		case 5:
			DisplayMenu();
		default:
			System.out.println("\nInvalid Choice...");
			DisplayAdmin();
			break;
		}
	}
}

public class oop_project {

	void Intro() {
		System.out.println("\nProject Name : Grocery Management System");
		System.out.println(
				"\nMade By:\n\t\tYash Prajapati\t-\t20162121023\n\t\tNitin Prajapati\t-\t20162121022\n\t\tUjjval Patel\t-\t20162121021\n\t\tTanishk Patel\t-\t20162121020");
	}

	public static void main(String[] args) throws Exception {
		oop_project obj = new oop_project();
		MenuConsole menu = new MenuConsole();
		obj.Intro();
		menu.DisplayMenu();
	}

}