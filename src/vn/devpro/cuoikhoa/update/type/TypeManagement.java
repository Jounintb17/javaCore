package vn.devpro.cuoikhoa.update.type;

import java.util.ArrayList;
import java.util.Scanner;

import jdk.jfr.Description;

public class TypeManagement {
	
	private static ArrayList<Type> list = new ArrayList<Type>();

	public static ArrayList<Type> getList() {
		return list;
	}

	public static void setList(ArrayList<Type> list) {
		TypeManagement.list = list;
	}
	
	public static int autoId = 1;	// no se tang tu dong auto increasement
	
//		để test các chức năng tránh phải nhập nhiều dữ liệu 
	public static void init() {
		list.add(new Type(autoId++, "Ao", "Gom ca ngan tay va dai tay"));
		list.add(new Type(autoId++, "Quan", "abc abc"));
		list.add(new Type(autoId++, "Mu", "abc abc"));
		list.add(new Type(autoId++, "Giay", "abc abc"));
		list.add(new Type(autoId++, "Tui", "abc abc"));
	}
	static Scanner sc = new Scanner(System.in);
	public static void TypeUpdate() {
		
		do {
			System.out.println("\n===========CAP NHAT THONG TIN LOAI HANG=========");
			System.out.println("Cac chuc nang cap nhat");
			System.out.println("\t1. xem danh sach loai hang");
			System.out.println("\t2. Them mot loai hang moi trong danh sach");
			System.out.println("\t3. Sua thong tin loai hang trong danh sach");
			System.out.println("\t4. Xoa mot loai hang trong danh sach");
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
		System.out.println("\n\tDANH SACH LOAI HANG");
		System.out.printf("%-5s %-30s %-100s%n", "Id", "Name", "Description");
		for (Type type : list) {
			type.display();
		}	
	}
	

	public static void add() {
		System.out.println("\n========THEM MOT HANG MOI VAO DANH SACH============");
		System.out.print("\tNhap ten loai hang: ");
		String name = sc.nextLine();
		System.out.print("\tMo ta loai hang");
		String description = sc.nextLine();
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen loai hang khong duoc de trong");
			return;
		}
//			kiem tra ten loai hang moi khong trung voi ten loai hang da co trang danh sach
		if (nameIsExist(name) ) {
			System.out.println("\tTen nay da ton tai trong danh sach");
			return;
		}
		
		if(description == null || description.trim().length() == 0) {
			System.out.println("\tMo ta loai hang khong duoc de trong");
			return; 
		}
//			kiem tra ten loai hang moi khong trung voi description loai hang da co trang danh sach
		if (descriptionIsExist(description) ) {
			System.out.println("\tMo ta nay da ton tai trong danh sach");
			return;
		}
//			Them vao danh sach
//			tao mot doi tuong Type moi
		Type newtype = new Type(autoId++, name, description);
//			them vao danh sach
		list.add(newtype);
		System.out.println("\tThem mot loai hang thanh cong");
		
		
	}

	public static void edit() {
		System.out.println("\n==============SUA THONG TIN LOAI HANG===========");
		System.out.println("\tNhap id loai hang: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfType(id);
		if(index == -1) {
			System.out.println("\tLoai hang khong duoc de trong");
			return;
		}
		
		System.out.print("\tNhap ten loai hang: ");
		String name = sc.nextLine();
		
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen loai hang khong duoc de trong");
			return;
		}
//			kiem tra ten loai hang moi khong trung voi ten loai hang da co trang danh sach
		if (nameIsExist(name)) {
			System.out.println("\tTen nay da ton tai trong danh sach");
			return;
		}

		
		System.out.print("\tNhap mo ta loai hang: ");
		String description = sc.nextLine();
		
		if(description == null || description.trim().length() == 0) {
			System.out.println("\tMo ta loai hang khong duoc de trong");
			return;
		}
//			kiem tra mo ta loai hang moi khong trung voi mo ta loai hang da co trang danh sach
		if (descriptionIsExist(description)) {
			System.out.println("\tMo ta nay da ton tai trong danh sach");
			return;
		}
//			thay the mo ta cu thanh mo ta moi
		list.get(index).setName(name);
		list.get(index).setDescription(description);
		System.out.println("\tSua ten loai hang thanh cong");
	}

	public static void remove() {
		System.out.println("\n==============XOA THONG TIN LOAI HANG===========");
		System.out.println("\tNhap id loai hang: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfType(id);
		if(index == -1) {
			System.out.println("\tLoai hang khong duoc de trong");
			return;
		}
		// co trong danh sach thi ta xoa
		list.remove(index);
		System.out.println("\tXoa ten loai hang thanh cong");
		
	}
	
	public static boolean nameIsExist(String name) {
		for (Type type : list) {
			if(type.getName().trim().equalsIgnoreCase(name.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean descriptionIsExist(String description) {
		for (Type type : list) {
			if(type.getDescription().trim().equalsIgnoreCase(description.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static int indexOfType(int id) {
		for (int index = 0; index < list.size(); index++) {
			if(list.get(index).getId() == id)
				return index;
		}
		return -1;
	}
	
	public static String getnameById(int id) {
		for (Type type : list) {
			if (type.getId() == id) {
				return type.getName();
			}
		}
		return null;
	}
}
