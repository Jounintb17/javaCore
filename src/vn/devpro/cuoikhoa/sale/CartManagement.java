package vn.devpro.cuoikhoa.sale;

import java.util.Scanner;

import vn.devpro.cuoikhoa.saleoder.SaleoderManagement;
import vn.devpro.cuoikhoa.update.customer.Customer;
import vn.devpro.cuoikhoa.update.customer.CustomerManagement;

public class CartManagement {
	//Nhap mot gio hang - khoi tao gio hang
	//	Khach thuc hien cac thao tac
	//	+Xem thong tin gio hang
	//	+ dua hang vao gio
	//	+ Sua so luong
	//	+ Xoa hang khoi gio
	//	+ Thanh toan
	//	+ Huy gio hang
	public static int autoId = 1;
	static Scanner sc = new Scanner(System.in);
	public static void cartManagement() {
		Cart cart = new Cart();	//khoi tao mot gio hang
		System.out.println("\n\tChao mung quy khach den mua hang");
		do {
			System.out.println("Chon thao tac voi gio hang cua ban");
			System.out.println("\t1. Xem thong tin gio hang");
			System.out.println("\t2. Them hang hoa vao gio");
			System.out.println("\t3. Sua so luong hang hoa trong gio");
			System.out.println("\t4. Xoa hang hoa khoi gio");
			System.out.println("\t5. Huy gio hang");
			System.out.println("\t6. Thanh toan gio hang");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban?: ");
			int chon = Integer.parseInt(sc.nextLine());
			
			switch (chon) {
			case 1: cart.display(); break;
			case 2: cart.add(); break;
			case 3: cart.update(); break;
			case 4: cart.remove(); break;
			case 5: cart = new Cart();
					System.out.println("Da huy gio hang cua ban");
					break;// huy gio hang
			case 6://thanh toan gio hang 
				if (payment(cart) == true) {//thanh toan thanh cong
					cart = new Cart();	//xoa gio hang khi thanh toan
				}
				 break;
			case 0: return;
			default: System.out.println("lua chon khong hop le");
			}
			
		} while (true);
	}
	
	
	private static boolean payment(Cart cart) {//thanh toan gio hang
		if (cart == null || cart.getList().size() <= 0) {//gio chua co hang
			System.out.println("Ban chua mua hang");
			return false;
		}
		//cap nhat thong tin khach hang
		System.out.println("\n=======THANH TOAN GIO HANG==========");
		System.out.println("Nhap ma (id) khach hang: ");
		int customerId = Integer.parseInt(sc.nextLine());
		//kiem tra khach hang da co trong danh sach hay chua
		int index = CustomerManagement.indexOfCustomer(customerId);
		String customerName = null;
		String customerPhone = null;
		if (index == -1) {//khach hang chua co trong danh sach luu
			System.out.println("Nhap ho ten khach hang: ");
			customerName = sc.nextLine();
			if (customerName.trim().length() <= 0) {
				System.out.println("Ten khach hang khong dc de trong");
				return false;
			}
			//thm khach hang vao danh sach khach hang
			customerId = CustomerManagement.autoId++;
			CustomerManagement.getList().add(new Customer(customerId, customerName, customerPhone));
		}
		else {//khach hang da co trong danh sach
			customerName = CustomerManagement.getList().get(index).getName();
					
		}
		
		//cap nhat thong tin cho gio hang
		cart.setId(autoId++);
		cart.setCustomerId(customerId);
		//hien thi lai gio hang cho khach xem
		cart.display();
		//luu gio hang vao danh sach
		SaleoderManagement.getCarts().add(cart);
		//Xoa gio hang 
		System.out.println("Thanh toan gio hang thanh cong!");
		return true;
		
	}
}
