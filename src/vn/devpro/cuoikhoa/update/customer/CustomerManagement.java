package vn.devpro.cuoikhoa.update.customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManagement {
	private static ArrayList<Customer> list = new ArrayList<Customer>();

	public static ArrayList<Customer> getList() {
		return list;
	}

	public static void setList(ArrayList<Customer> list) {
		CustomerManagement.list = list;
	}
	
	public static int autoId = 1;	// no se tang tu dong auto increasement
	
//	để test các chức năng tránh phải nhập nhiều dữ liệu 
	public static void init() {
		list.add(new Customer(autoId++, "Nguyen Van A", "0978666545"));
		list.add(new Customer(autoId++, "Tran Huyen B", "0978666545"));
		list.add(new Customer(autoId++, "Nguyen Tran C", "0978666545"));
		list.add(new Customer(autoId++, "Tran Ngoc D", "0978666545"));
		list.add(new Customer(autoId++, "Phan Van F", "0978666545"));
	}
	static Scanner sc = new Scanner(System.in);
	public static void CustomerUpdate() {
		
		do {
			System.out.println("\n===========CAP NHAT THONG TIN KHACH HANG=========");
			System.out.println("Cac chuc nang cap nhat");
			System.out.println("\t1. xem danh sach khach hang");
			System.out.println("\t2. Them mot khach hang moi trong danh sach");
			System.out.println("\t3. Sua thong tin khach hang trong danh sach");
			System.out.println("\t4. Xoa mot khach hang trong danh sach");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon chuc nang quan ly: ");
			int chon = Integer.parseInt(sc.nextLine());
			
			switch (chon) {
			case 1: display(); break;
			case 2: add(); break;
			case 3: edit(); break;
			case 4: remove(); break;
			case 0: return;
				
			default: System.out.println("Lua chon khong hop le");
			}
		}
		while (true);
		
	}

	public static void display() {
		System.out.println("\n\tDANH SACH KHACH HANG");
		System.out.printf("%-5s %-30s %-10S%n", "Id", "Name", "Phone");
		for (Customer customer : list) {
			customer.display();
		}	
	}
	

	public static void add() {
		System.out.println("\n========THEM MOT KHACH HANG MOI VAO DANH SACH============");
		System.out.print("\tNhap ten khach hang: ");
		String name = sc.nextLine();
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen khach hang khong duoc de trong");
			return;
		}
		System.out.print("\tNhap SDT khach hang: ");
		String phone = sc.nextLine();
		if(phone == null|| phone.length() != 10 || phone.trim().length() == 0) {
			System.out.println("\tSo dien thoai bi trong hoac khong dung");
			return;
		}
//		Them vao danh sach
//		tao mot doi tuong Customer moi
		Customer newCustomer = new Customer(autoId++, name, phone);
//		them vao danh sach
		list.add(newCustomer);
		System.out.println("\tThem mot khach hang thanh cong");
		
		
	}

	public static void edit() {
		System.out.println("\n==============Sua thong tin khach hang===========");
		System.out.println("\tNhap id khach hang: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfCustomer(id);
		if(index == -1) {
			System.out.println("\tKhach hang khong duoc de trong");
			return;
		}
		
		System.out.print("\tNhap ten khach hang: ");
		String name = sc.nextLine();
		
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen khach hang khong duoc de trong");
			return;
		}
		
		System.out.print("\tNhap so dien thoai khach hang: ");
		String phone = sc.nextLine();
		
		if(phone == null || phone.length() != 10 || phone.trim().length() == 0) {
			System.out.println("\tSo dien thoai bi trong hoac khong dung");
			return;
		}
//		thay the ten cu thanh ten moi
		list.get(index).setName(name);
		list.get(index).setPhone(phone);
		System.out.println("\tSua ten khach hang thanh cong");
	}

	public static void remove() {
		System.out.println("\n==============XOA THONG TIN KHACH HANG===========");
		System.out.println("\tNhap id khach hang: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfCustomer(id);
		if(index == -1) {
			System.out.println("\tID khach hang khong duoc de trong");
			return;																																																																																								
		}
		// co trong danh sach thi ta xoa
		list.remove(index);
		System.out.println("\tXoa ten khach hang thanh cong");
		
	}
	
	public static boolean nameIsExist(String name) {
		for (Customer customer : list) {
			if(customer.getName().trim().equalsIgnoreCase(name.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static int indexOfCustomer(int id) {
		for (int index = 0; index < list.size(); index++) {
			if(list.get(index).getId() == id)
				return index;
		}
		return -1;
	}
	
	public static String getnameById(int id) {
		for (Customer customer : list) {
			if (customer.getId() == id) {
				return customer.getName();
			}
		}
		return null;
	}
	
	public static String getphoneById(int id) {
		for (Customer customer : list) {
			if (customer.getId() == id) {
				return customer.getPhone();
			}
		}
		return null;
	}
}
