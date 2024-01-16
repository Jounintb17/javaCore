package vn.devpro.cuoikhoa.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.cuoikhoa.update.customer.CustomerManagement;
import vn.devpro.cuoikhoa.update.product.ProductManagement;



public class Cart {
	private int id;
	private int customerId;
	private List<CartProduct> list = new ArrayList<CartProduct>();
	
	

	public double cartTotal() {
		double total = 0;
		for(CartProduct cp : list) {
			total += cp.total();			
		}
		return total;
	}
	
	
	
	public void display() {// hien thi gio hang cua khach hang
		System.out.println("\n=====================================GIO HANG CUA BAN===================================");

		System.out.println("\t\t\tTen khach hang: " + CustomerManagement.getnameById(customerId));
		System.out.println("\t\t\tSo dien thoai: " + CustomerManagement.getphoneById(customerId));
		System.out.println("\t\t\tGio hang co " + list.size() + " loai hang hoa");
		
		System.out.printf("%3s %-30s %-15s %-12s %-15s%n",
				"STT", "Ten hang hoa", "Don gia", "So luong", "Thanh tien");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%-3d ", i + 1);
			list.get(i).display();
		}
		System.out.printf("\t\tCong thanh tien: %,.2f%n", cartTotal());
	}
	
	Scanner sc = new Scanner(System.in);
	
	public void add() {// them mot hang hoa vao gio hang
		System.out.println("\n=========THEM HANG HOA VAO GIO HANG==========");		
		System.out.println("Nhap vao id khach hang: ");
		customerId = Integer.parseInt(sc.nextLine());
		
		System.out.println("Nhap ma id muon mua");
		int productId = Integer.parseInt(sc.nextLine());
		
//		kiem tra xem productId co trong danh sach van hang hay khong
		int index = ProductManagement.indexOfProduct(productId);
		if(index == -1) {
			System.out.println("Hang hoa khong ton tai trong danh sach xin chon lai");
			return;
		}
		System.out.println("Nhap so luong can mua: ");
//		co thi nhap so luong can mua
		double amount = Double.parseDouble(sc.nextLine());
		if (amount <= 0) {
			System.out.println("So luong mua phai lon hon 0");
			return;
		}
		
//		tim xem hang dinh mua
		int cartIndex = indexOfCartProduct(productId);
		if (cartIndex != -1) {// hang dinh m ua do co trong gio
			amount += list.get(cartIndex).getAmount();//cong so luong trong gio voi so luong moi chon them
			
		}
		
		//kiem tra tong so luong mua co vuot qua tong so luong dang co ban
		System.out.println("Nhap so luong muon mua: ");
		if (amount > ProductManagement.getList().get(index).getAmount()) {
			System.out.println("So luong can mua vuot qua kha nang, xin chon lai");
			return;
		}
		
		//Them hang vao gio: 2 truong hop
		if (cartIndex != -1) {//hang da co trong gio
			list.get(cartIndex).setAmount(amount);//set lai so luong moi
		}
		else {//hang chua co trong gio
			list.add(new CartProduct(productId, amount));//them hang moi vao gio
		}
		System.out.println("Them hang vao gio thanh cong");
		
}	
		
	
	public void update() {//su ahng hoa trong gio
		System.out.println("\n===============SUA THONG TIN HANG HOA TRONG GIO============");
		System.out.print("Nhap ma hang dinh mua");
//		kiem tra hang ho trong gi0 khong
		int productId = Integer.parseInt(sc.nextLine());
		int cartIndex = indexOfCartProduct(productId);
		if (cartIndex == -1) {// hang dinh m ua do co trong gio
			System.out.println("Hang khong co trong gio");	
		}
		System.out.print("Nhap so luong moi");
		double amount = Double.parseDouble(sc.nextLine());
		if (amount <= 0) {
			System.out.println("So luong mua phai lon hon 0");
			return;
		}
		
//		kiem traso luong vuot gioi han
		int index = ProductManagement.indexOfProduct(productId);
		if (amount > ProductManagement.getList().get(index).getAmount()) {
			System.out.println("So luong can mua vuot qua kha nang, xin chon lai");
			return;
		}
		
//		thay so luong thanh so moi
		list.get(cartIndex).setAmount(amount);
		System.out.println("Sua thong tin hang hoa thanh cong");
	}
	
//		xoa hang trong gio
	public void remove() {//xoa hang hoa khoi gio
		System.out.println("\n===============XOA HANG TRONG GIO======");
		System.out.print("Nhap ma hang dinh xoa");
//		kiem tra hang ho trong gi0 khong
		int productId = Integer.parseInt(sc.nextLine());
		int cartIndex = indexOfCartProduct(productId);
		if (cartIndex == -1) {// hang dinh m ua do co trong gio
			System.out.println("Hang khong co trong gio");	
		}
		System.out.print("Nhap so luong moi");
		double amount = Double.parseDouble(sc.nextLine());
		if (amount <= 0) {
			System.out.println("So luong mua phai lon hon 0");
			return;
		}
		
//		kiem traso luong vuot gioi han
		int index = ProductManagement.indexOfProduct(productId);
		if (amount > ProductManagement.getList().get(index).getAmount()) {
			System.out.println("So luong can mua vuot qua kha nang, xin chon lai");
			return;
		}
		
//		thay so luong thanh so moi
		list.remove(cartIndex);
		System.out.println("Xoa hang hoa thanh cong");
	}
	
	
//	phuong thuc kiem tra mot hang do ton tai trong gio hang chua
	public int indexOfCartProduct(int ProductId) {
		for (int index = 0; index <list.size(); index++) {
			if (list.get(index).getProductId() == ProductId){
				return index;
			}
		}
		return -1;	
	}
	
	public Cart() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<CartProduct> getList() {
		return list;
	}

	public void setList(List<CartProduct> list) {
		this.list = list;
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public Cart(int id, int customerId, List<CartProduct> list, Scanner sc) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.list = list;
		this.sc = sc;
	}
	
}
