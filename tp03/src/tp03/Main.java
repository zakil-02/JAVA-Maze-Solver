package tp03;
import java.io.PrintStream;
import structures.Heap;
public class Main {

	public static void main(String[] args) {
		PrintStream out = System.out;
		int n=args.length;
		for (int i=0;i<n;i++) {
			out.println(args[i]);
		
		}
		Heap h= new Heap(args);
		String[] sortedlist= h.sort();
		out.println("-------------------------------");
		for (String arg : sortedlist) {
			out.println(arg);
		}
	}

}
