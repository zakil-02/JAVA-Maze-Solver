package structures;
public class Heap {
	private  String[] heap ;

	public Heap(String[] args) {
		//heap = new String[n];
		//heap = args;
		//la derniere instruction ne va pas marcher si 
		//on fait de heap un attribut final
		//si on veut le rendre final on remplie chaque 
		//position seule 
		//heap = Arrays.copyOf(args, n);
		heap=args.clone();
		
	}
	private void swap(int i, int j) {
		String m=heap[i];
		heap[i]=heap[j];
		heap[j]=m;
	}
	private void pullUp(int k) {
		int i=k-1;
		int pere=(i-1)/2;
		while (i>=1 && heap[i].compareTo(heap[pere])>0) {
			swap(i, pere);
			i=pere;
			pere=(i-1)/2;
			
			
		}
	}
	private void buildHeap() {
		for (int i=1;i<=heap.length;i++) { //on peut commencer juste par i=2
			pullUp(i);
			
		}
	}
	private void pullDown(int i, int k) {
		int fg=2*i+1;//fg =  fils gauche
		if (fg>=k) {
			return ;
		}
		int fd=fg+1; //fils droit
		if (fd>=k) {
			if (heap[fg].compareTo(heap[i])>=0) {
				swap(i, fg);
			}
			return;
		}
		String vg = heap[fg];//valeur du fils gauche
		String vd = heap[fd];//valeur du fils droit
		if (vg.compareTo(vd)<0) {
			if (heap[i].compareTo(vd)<0) {
				swap(i, fd);
				pullDown(fd, k); //appel recursive
				return;
			}
		}
		else {
			if (heap[i].compareTo(vg)<0) {
				swap(i, fg);
				pullDown(fg, k);//appel recursive 
				return;
			}
		}
	}
	private void sortHeap() {
		for(int k=heap.length ; k>=2 ; k--) {
			swap(0, k-1); 
			pullDown(0, k-1);
		}
	}
	public String[] sort() {
		buildHeap();
		sortHeap();
		return heap.clone();
	}
}
