package vn.devpro.cuoikhoa.update.product;

import vn.devpro.cuoikhoa.update.provider.ProviderManagement;
import vn.devpro.cuoikhoa.update.type.TypeManagement;

public class Product {
	private int id;
	private int providerID;
	private int typeId;
	private String name;
	private double price;
	private double amount;
	
	public void display() {
		System.out.printf("%6d %-30s %-30s %-30s %,15.2f %,12.2f%n",
				this.id, ProviderManagement.getnameById(providerID), TypeManagement.getnameById(typeId),
				this.name, this.price, this.amount);
	}

	public Product() {
		super();
	}

	public Product(int id, int providerID, int typeId, String name, double price, double amount) {
		super();
		this.id = id;
		this.providerID = providerID;
		this.typeId = typeId;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
