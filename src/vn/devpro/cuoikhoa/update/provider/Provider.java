package vn.devpro.cuoikhoa.update.provider;

public class Provider {
	private int id;
	private String name;
	private String adress;
	
	public void display() {
		System.out.printf("%-5d %-30s %-50s%n", this.id, this.name, this.adress);
	}
	
	
	public Provider() {
		super();
	}
	public Provider(int id, String name, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
}
