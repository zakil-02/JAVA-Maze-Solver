package tp2;
public class Test {
	public static void main(String[] args) {
		student st = new student(1024,"Zakaria","AKIL") ;
		System.out.println(st);
		
		Promo promo = new Promo();
		promo.add("ilias", "lasri");
		promo.add("zakaria", "akil");
		promo.add("antonin", "meunier");
		promo.add("leo", "messi");
		promo.printToConsole();
		promo.selectionSort();
		promo.printToConsole();
		}
	

}
