package vn.devpro.cuoikhoa.update.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ProviderManagement {
	private static ArrayList<Provider> list = new ArrayList<Provider>();

	public static ArrayList<Provider> getList() {
		return list;
	}

	public static void setList(ArrayList<Provider> list) {
		ProviderManagement.list = list;
	}
	
	public static int autoId = 1;	// no se tang tu dong auto increasement
	
//		để test các chức năng tránh phải nhập nhiều dữ liệu 
	public static void init() {
		list.add(new Provider(autoId++, "Uniqlo", "Pham Ngoc Thach"));
		list.add(new Provider(autoId++, "Zara", "Cao Bang"));
		list.add(new Provider(autoId++, "MLB", "Son La"));
		list.add(new Provider(autoId++, "Nike", "Dien Bien"));
		list.add(new Provider(autoId++, "Chanel", "Ha Noi"));
	}
	static Scanner sc = new Scanner(System.in);
	public static void ProviderUpdate() {
		
		do {
			System.out.println("\n====================CAP NHAT THONG TIN NHA CUNG CAP================");
			System.out.println("Cac chuc nang cap nhat");
			System.out.println("\t1. xem danh sach nha cung cap");
			System.out.println("\t2. Them mot nha cung cap moi trong danh sach");
			System.out.println("\t3. Sua thong tin nha cung cap trong danh sach");
			System.out.println("\t4. Xoa mot nha cung cap trong danh sach");
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
		System.out.println("\n\tDANH SACH NHA CUNG CAP");
		System.out.printf("%-5s %-30s %-50s%n", "Id", "Name", "Adress");
		for (Provider provider : list) {
			provider.display();
		}	
	}
	

	public static void add() {
		System.out.println("\n=============THEM MOT NHA CUNG CAP MOI VAO DANH SACH===============");
		System.out.print("\tNhap ten nha cung cap: ");
		String name = sc.nextLine();
		System.out.print("\tNhap dia chi nha cung cap: ");
		String description = sc.nextLine();
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen nha cung cap khong duoc de trong");
			return;
		}
//			kiem tra ten nha cung cap moi khong trung voi ten nha cung cap da co trang danh sach
		if (nameIsExist(name) ) {
			System.out.println("\tTen nay da ton tai trong danh sach");
			return;
		}
		
		if(description == null || description.trim().length() == 0) {
			System.out.println("\tDia chi nha cung cap khong duoc de trong");
			return;
		}
//			kiem tra ten nha cung cap moi khong trung voi ten nha cung cap da co trang danh sach
		if (adressIsExist(description) ) {
			System.out.println("\tDia chi nay da ton tai trong danh sach");
			return;
		}
//			Them vao danh sach
//			tao mot doi tuong provider moi
		Provider newprovider = new Provider(autoId++, name, description);
//			them vao danh sach
		list.add(newprovider);
		System.out.println("\tThem mot nha cung cap thanh cong");
		
		
	}

	public static void edit() {
		System.out.println("\n====================SUA THONG TIN NHA CUNG CAP==================");
		System.out.println("\tNhap id nha cung cap: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProvider(id);
		if(index == -1) {
			System.out.println("\tnha cung cap khong duoc de trong");
			return;
		}
		
		System.out.print("\tNhap ten nha cung cap: ");
		String name = sc.nextLine();
		
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen nha cung cap khong duoc de trong");
			return;
		}
//			kiem tra ten nha cung cap moi khong trung voi ten nha cung cap da co trang danh sach
		if (nameIsExist(name)) {
			System.out.println("\tTen nay da ton tai trong danh sach");
			return;
		}
		
		
		System.out.print("\tNhap dia chi nha cung cap: ");
		String adress = sc.nextLine();
		
		if(adress == null || adress.trim().length() == 0) {
			System.out.println("\tDia chi nha cung cap khong duoc de trong");
			return;
		}
//			kiem tra ten nha cung cap moi khong trung voi ten nha cung cap da co trang danh sach
		if (adressIsExist(adress)) {
			System.out.println("\tTen nay da ton tai trong danh sach");
			return;
		}
//			thay the ten cu thanh ten moi
		list.get(index).setName(name);
		list.get(index).setAdress(name);
		System.out.println("\tSua ten nha cung cap thanh cong");
	}

	public static void remove() {
		System.out.println("\n=================XOA THONG TIN NHA CUNG CAP===============");
		System.out.println("\tNhap id nha cung cap: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProvider(id);
		if(index == -1) {
			System.out.println("\tnha cung cap khong duoc de trong");
			return;
		}
		// co trong danh sach thi ta xoa
		list.remove(index);
		System.out.println("\tXoa ten nha cung cap thanh cong");
		
	}
	
	public static boolean nameIsExist(String name) {
		for (Provider provider : list) {
			if(provider.getName().trim().equalsIgnoreCase(name.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean adressIsExist(String adress) {
		for (Provider provider : list) {
			if(provider.getAdress().trim().equalsIgnoreCase(adress.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static int indexOfProvider(int id) {
		for (int index = 0; index < list.size(); index++) {
			if(list.get(index).getId() == id)
				return index;
		}
		return -1;
	}
	
	public static String getnameById(int id) {
		for (Provider provider : list) {
			if (provider.getId() == id) {
				return provider.getName();
			}
		}
		return null;
	}
}
