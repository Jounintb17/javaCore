package vn.devpro.cuoikhoa;

import java.util.Scanner;

import vn.devpro.cuoikhoa.sale.CartManagement;
import vn.devpro.cuoikhoa.saleoder.SaleoderManagement;
import vn.devpro.cuoikhoa.update.SystemInfomationManagement;
import vn.devpro.cuoikhoa.update.customer.CustomerManagement;
import vn.devpro.cuoikhoa.update.product.ProductManagement;
import vn.devpro.cuoikhoa.update.provider.ProviderManagement;
import vn.devpro.cuoikhoa.update.type.TypeManagement;

public class StoreManagement {

	public static void main(String[] args) {
		TypeManagement.init();
		ProviderManagement.init();
		ProductManagement.init();
		CustomerManagement.init();
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n=============CHUONG TRINH QUAN LY CUA HANG=============");
			System.out.println("Cac chuc nang quan ly chinh");
			System.out.println("\t1. Cap nhat thong tin he thong");
			System.out.println("\t2. Quan ly ban hang");
			System.out.println("\t3. Quan ly don hang");
			System.out.println("\t0. Thoat");
			
			System.out.print("Lua chon chuc nang quan ly: ");
			int chon = Integer.parseInt(sc.nextLine());
			
			switch (chon) {
			case 1: SystemInfomationManagement.execute();break;
			case 2: CartManagement.cartManagement();break;
			case 3: SaleoderManagement.saleOderManagement(); break;
			case 0:
				System.out.println("Thoat khoi ung dung");
				System.exit(0);
				
			default: System.out.println("Lua chon khong hop le");
			}
		}
		while (true);
	}
}
