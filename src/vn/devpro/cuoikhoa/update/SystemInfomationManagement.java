package vn.devpro.cuoikhoa.update;

import java.util.Scanner;

import vn.devpro.cuoikhoa.update.customer.CustomerManagement;
import vn.devpro.cuoikhoa.update.product.ProductManagement;
import vn.devpro.cuoikhoa.update.provider.ProviderManagement;
import vn.devpro.cuoikhoa.update.type.TypeManagement;

public class SystemInfomationManagement {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n====================CAP NHAT THONG TIN HE THONG===================");
			System.out.println("Cac chuc nang cap nhat he thong");
			System.out.println("\t1. Cap nhat thong tin nha cung cap");
			System.out.println("\t2. Cap nhat thong tin loai hang");
			System.out.println("\t3. Cap nhat thong tin san pham");
			System.out.println("\t4. Cap nhat thong tin khach hang");	
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon chuc nang quan ly: ");
			int chon = Integer.parseInt(sc.nextLine());
			
			switch (chon) {
			case 1:ProviderManagement.ProviderUpdate();break;
			case 2:TypeManagement.TypeUpdate(); break;
			case 3:ProductManagement.productUpdate(); break;
			case 4:CustomerManagement.CustomerUpdate();break;
			case 0: return;
				
			default: System.out.println("Lua chon khong hop le");
			}
			
		}
		while (true);
	}
}
