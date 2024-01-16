package vn.devpro.cuoikhoa.saleoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.cuoikhoa.sale.Cart;
import vn.devpro.cuoikhoa.update.customer.Customer;
import vn.devpro.cuoikhoa.update.customer.CustomerManagement;
import vn.devpro.cuoikhoa.update.product.ProductManagement;

public class SaleoderManagement {
	private static List<Cart> carts = new ArrayList<Cart>();

	public static List<Cart> getCarts() {
		return carts;
	}

	public static void setCarts(List<Cart> carts) {
		SaleoderManagement.carts = carts;
	}
	//Hien thi danh sach cac gio hang(cac don hang
	//Tinh tong doanh tu tu cac gio hang
	//Tim kiem gio hang, hien thi chi tiet gio hang neu tim thay
	
	static Scanner sc = new Scanner(System.in);
	
	public static void saleOderManagement() {
		do {
			System.out.println("\n========================QUAN LY DON HANG=====================");
			System.out.println("Chon chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach don hang (gio hang)");
			System.out.println("\t2. Xoa don hang");
			System.out.println("\t3. Xem tong doanh thu tu don hang");
			System.out.println("\t4. Tong tien thu duoc theo khach hang");
			System.out.println("\t5. Tong tien thu duoc theo san pham da ban");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban?: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1: displayCarts(); break;
			case 2: remove(); break;
			case 3: displayTotalMoney(); break;
			case 4: displayTotalMoneyIdCustomer(); break;
			case 5: displayTotalMoneyIdProduct(); break;
			case 0: return;
			default: System.out.println("Lua chon khong hop le");
			}
		}
		while (true);
	}
	
	//case 1: hien thi danh sach cac gio hang
	
	private static void displayCarts() {
		System.out.println("===============================DANH SACH CAC DON HANG===================================");
		System.out.printf("%3s %11s %-30s %13s %-15s%n", "STT", "Ma don hang", "Ten khach hang", "So dien thoai", "Tong tien hang");
		int stt = 1;
		for (Cart cart : carts) {
			System.out.printf("%3d %-11d %-30s %13s %,15.2f%n", stt++, cart.getId(),
					CustomerManagement.getnameById(cart.getCustomerId()), CustomerManagement.getphoneById(cart.getCustomerId()), cart.cartTotal());
		}
	}	
	
	//xoa mot don hang khoi danh sach
	
		public static void remove() {
			System.out.println("\n========================DANH SACH CAC DON HANG SAU KHI DA XOA========================");
			System.out.println("\tNhap ma don hang muon xoa: ");
			int id = Integer.parseInt(sc.nextLine());
			int index = indexOfCart(id);
			if(index == -1) {
				System.out.println("\tMa don hang khong duoc de trong");
				return;																																																																																								
			}
			// co trong danh sach thi ta xoa
			
			carts.remove(index);	
			System.out.println("\tXoa don hang thanh cong");
			
		}
		
	// case 3 tinh tong tien tu tat ca cac gio hang
	private static void displayTotalMoney() {
		System.out.printf("\nTong doanh thu: %,.2f%n", totalMoney());
	}
	
	// ham tinh tong tien tat ca khach hang
	public static double totalMoney() {
		double total = 0;
		for(Cart cart: carts) {
			total += cart.cartTotal();		
		}
		return total;
	}
	
	//case 4 hien thi so tien tinh duoc theo khach hang
	
	private static void displayTotalMoneyIdCustomer() { 
		System.out.println("\tNhap (ID) khach hang: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println();
		System.out.println("=======================TONG TIEN THEO KHACH HANG=======================");
		System.out.println();
		System.out.printf("%-5s %-30s %-10s%n", "Id", "Name", "Phone");
		System.out.printf("%-5d %-30s %-10s%n", id, CustomerManagement.getnameById(id), CustomerManagement.getphoneById(id));
		System.out.printf("\nDoanh thu tu khach hang la: %,.2f%n", totalMoneyIdCustomer(id));
	}
	
	// ham tinh tong tien theo id 1 khach hang
	
	public static double totalMoneyIdCustomer(int id) {
		double total = 0;
		List<Integer> listCustomer = indexOfCustomer(id);
		if(listCustomer != null || listCustomer.size() > 0) {
			for(int index: listCustomer) {
				total += carts.get(index).cartTotal();		
			}
		}
		return total;
	}
	
	//case 5 hien thi so tien tinh duoc theo khach hang
	
		private static void displayTotalMoneyIdProduct() { 
			System.out.println("\tNhap (ID) san pham: ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println();
			System.out.println("=======================TONG TIEN THU DUOC THEO SAN PHAM=======================");
			System.out.println();
			System.out.printf("%-5s %-30s %-13s%n", "Id", "Name", "Doanh thu");
			System.out.printf("%-5d %-30s %,13.2f%n", id, ProductManagement.getnameByIdProduct(id), totalMoneyIdProduct(id));
		}
		
		// ham tinh tong tien theo id 1 san pham 
		
		public static double totalMoneyIdProduct(int id) {
			double total = 0;
			List<Integer> listProduct = indexOfCustomer(id);
			if(listProduct != null || listProduct.size() > 0) {
				for(int index: listProduct) {
					total += carts.get(index).cartTotal();		
				}
			}
			return total;
		}
		
//	private static void searchCart() {
//		System.out.println("\n=================TIM KIEM DON HANG================");
//		System.out.println("Nhap ma don hang (id gio hang): ");
//		int cartId = Integer.parseInt(sc.nextLine());
//		boolean result = false;
//		
//		for (Cart cart : carts) {
//			if (cart.getId() == cartId) {
//				System.out.println("Chi tieu gio hang tim duoc");
//				cart.display();
//				result = true;
//			}
//		}
//		if (result == false) {
//			System.out.println("Khong tim thay ket qua nao");
//		}
//	}
	
	public static int indexOfCart(int id) {
		for (int index = 0; index < carts.size(); index++) {
			if(carts.get(index).getId() == id)
				return index;
		}
		return -1;
	}
	
	public static List<Integer> indexOfCustomer(int id) {
		List<Integer> listCust = new ArrayList<Integer>();
		for (int index = 0; index < carts.size(); index++) {
			if(carts.get(index).getCustomerId() == id)
				listCust.add(index);
		}
		return listCust;
	}
}
