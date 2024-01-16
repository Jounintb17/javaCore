package vn.devpro.cuoikhoa.update.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.cuoikhoa.update.customer.Customer;
import vn.devpro.cuoikhoa.update.type.TypeManagement;

public class ProductManagement {
	static 	Scanner sc = new Scanner(System.in);
	private static List<Product> list = new ArrayList<Product>();

	public static List<Product> getList() {
		return list;
	}

	public static void setList(List<Product> list) {
		ProductManagement.list = list;
	}
	public static int autoId = 1;
	
	public static void init() {
		list.add(new Product(autoId++, 1, 1, "Ao thun", 12300000, 10));
		list.add(new Product(autoId++, 2, 2, "Quan bo",50000000, 20));
		list.add(new Product(autoId++, 3, 3, "Mu coi", 400000, 50));
		list.add(new Product(autoId++, 4, 4,"Giay the thao", 50000000, 10));
		list.add(new Product(autoId++, 5, 5,"Tui LuonVuiTuoi", 700000, 30));
		
	}

	public static void productUpdate() {
		TypeManagement.init();
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n=============CAP NHAT THONG TIN SAN PHAM=============");
			System.out.println("Cac chuc nang cap nhat");
			System.out.println("\t1. Xem danh sach hang hoa");
			System.out.println("\t2. Them mot hang hoa moi vao danh sach");
			System.out.println("\t3. Sua thong tin hang hoa trong danh sach");
			System.out.println("\t4. Xoa mot hang hoa trong danh sach");
			System.out.println("\t5. Tim kiem san pham");
			System.out.println("\t0. Quay lai");
			
			
			System.out.print("Lua chon chuc nang cap nhat: ");
			int chon = Integer.parseInt(sc.nextLine());
			
			switch (chon) {
			case 1:display();break;
			case 2:add();break;
			case 3:edit();break;
			case 4:remove();break;
			case 5:searchProduct(); break;
			case 0: return;
			default: System.out.println("Lua chon khong hop le");
			}
		}
		while (true);
	}
	


	public static void display() {
		System.out.println("\n\t=========================DANH SACH SAN PHAM==========================");
		System.out.printf("%6s %-30s %-30s %-30s %-15s %-12s%n", "Id", "Provider", "Ten loai hang", "Ten hang hoa", "Don gia", "So luong");
		for (Product p : list) {
			p.display();
		}	
	}
	

	public static void add() {
		System.out.println("\n=====================THEM MOT HANG MOI VAO DANH SACH=================");
		
		System.out.println("\tChon provider id: ");
		
		int providerId = Integer.parseInt(sc.nextLine());
//		kiem tra providerId vua nhap co trong danh sach providerId khong
		if (ProductManagement.indexOfProduct(providerId) == -1) {
			System.out.println("\tChon provider khong hop le");
			return;
		}
		
		System.out.println("\tChon type id: ");
		
		int typeId = Integer.parseInt(sc.nextLine());
//		kiem tra typeId vua nhap co trong danh sach types khong
		if (TypeManagement.indexOfType(typeId) == -1) {
			System.out.println("\tChon type khong hop le");
			return;
		}
		
		System.out.print("\tNhap ten hang hoa: ");
		String name = sc.nextLine();
		if(name == null || name.trim().length() == 0) {
			System.out.println("\tTen loai hang khong duoc de trong");		
			return;
		}
		
		System.out.println("\tNhap don gia: ");
		double price = Double.parseDouble(sc.nextLine());
		if (price < 0) {
			System.out.println("\tDon gia khong duoc la so am");
			return;
		}
		
		System.out.println("\tNhap so/khoi luong: ");
		double amount = Double.parseDouble(sc.nextLine());
		if (amount < 0) {
			System.out.println("\tSo hoac khoi luong khong duoc la so am");
			return;
		}
		
		
//		them hang hoa moi vao danh sach
		list.add(new Product(autoId++,providerId, typeId, name, price, amount));
		System.out.println("\tThem hang hoa moi thanh cong!");
		
	}
	
	//chinh sua san pham
	public static void edit() {
		System.out.println("\n========================SUA THONG TIN SAN PHAM=====================");
		System.out.println("\tNhap id hang hoa");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProduct(id);
		if(index == -1) {
			System.out.println("\tHang hoa khong ton tai trong danh sach");
			return;
		}
		
//		co trong danh sachs thi sua
		do {
			System.out.println("\n===========================SUA THONG TIN SAN PHAM===========================");
			System.out.println("Chon thong tin can sua");
			System.out.println("\t1 Sua ma (id) loai hang");
			System.out.println("\t2 Sua ten hang hoa");
			System.out.println("\t3 Sua so luong hang hoa");
			System.out.println("\t4 Sua don gia hang hoa");
			System.out.println("\t0 Quay lai");
			
			System.out.print("Lua chon cua ban ?: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				System.out.println("\tChon type id: ");
				int typeId = Integer.parseInt(sc.nextLine());
//				kiem tra categoriId vua nhap co trong danh sach categories khong
				if (TypeManagement.indexOfType(typeId) == -1) {
					System.out.println("\tChon type khong hop le");
					return;	
				}
				list.get(index).setTypeId(typeId);
				System.out.println("\t sua ma loai hang thanh cong");
				break;		
			case 2:
				System.out.print("\tNhap ten hang hoa");
				String name = sc.nextLine();
				if(name == null || name.trim().length() == 0) {
					System.out.println("\tTen loai hang khong duoc de trong");					
					return;
				}
				list.get(index).setName(name);
				System.out.println("\tSua ten hang hoa thanh cong");
				break;	
			case 3:
				System.out.println("\tNhap so luong");
				double amount = Double.parseDouble(sc.nextLine());
				if (amount < 0) {
					System.out.println("\tSo hoac khoi luong khong duoc la so am");
					return;
				}
				list.get(index).setAmount(amount);
				System.out.println("\tSua khoi luong thanh cong");
				break;
			case 4:
				System.out.println("\tNhap don gia");
				double price = Double.parseDouble(sc.nextLine());
				if (price < 0) {
					System.out.println("\tSo hoac khoi luong khong duoc la so am");
					return;
				}
				list.get(index).setPrice(price);
				System.out.println("\tSua don gia thanh cong");
				break;
			case 0: return;
			default:System.out.println("Lua chon khong hop le ");
			}
		} while (true);
		
	}
	
	//xoa san pham
	private static void remove() {
		System.out.println("\n=========================XOA THONG TIN SAN PHAM========================");
		System.out.println("\tNhap id loai hang");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProduct(id);
		if(index == -1) {
			System.out.println("\tHang hoa khong ton tai trong danh sach");
			return;
		}
		list.remove(index);
		System.out.println("\tXoa hang hoa thanh cong");
		
	}
	
	//tim kiem san pham theo ten
	
	private static void searchProduct() {
	System.out.println("\n=================TIM KIEM SAN PHAM================");
//	System.out.println("Nhap ma don hang (id product): ");
	System.out.println("Nhap tu khoa can tim: ");
	
	String seach = sc.nextLine();
//	int productId = Integer.parseInt(sc.nextLine());
	boolean result = false;
	
	for (Product product : list) {
		if (product.getName().equals(seach)) {
			System.out.println("Da tim thay tu khoa");
			System.out.printf("%6s %-30s %-30s %-30s %-15s %-12s%n", "Id", "Provider", "Ten loai hang", "Ten hang hoa", "Don gia", "So luong");
			product.display();
			result = true;
		}
	}
	if (result == false) {
		System.out.println("Khong tim thay ket qua nao");
	}
}
	
	public static int indexOfProduct(int id) {
		for (int index = 0; index < list.size(); index++) {
			if(list.get(index).getId() == id)
				return index;
		}
		return -1;
	}
	
//	ham de tra ve doi tong hang hoa
	public static Product getProductById(int id) {
		for (Product p : list) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public static String getnameByIdProduct(int id) {
		for (Product product : list) {
			if (product.getId() == id) {
				return product.getName();
			}
		}
		return null;
	}
}
