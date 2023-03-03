package tp2;
import java.util.ArrayList;

public class Promo {
	private ArrayList<student> studentList;
//le constructeur de la classe arraylist
	public Promo() {
		studentList = new ArrayList<student>();
	}
	public int newId() {
		int n = studentList.size();
		int id0=0;
		if (n!=0) {
			for (student i : studentList) {
				int ids= i.getId();
				if (ids > id0) {
					id0= (int)ids;
				}
			}
			id0+=1;
		}
		return id0; 
	}
	public int add(String firstName, String lastName) {
		int id= newId();
		studentList.add(new student(id,firstName,lastName));
		return id;
	}
	public void printToConsole() {
		System.out.println(studentList);
			
	}
	private void swap(int i, int j) {
		student x = (student) studentList.get(i);
		studentList.set(i, studentList.get(j));
		studentList.set(j, x);
		
	}
	public void selectionSort() {
		int c=studentList.size();
		for (int i=0;i<c-1;i++) {
			int ipetit=i;
			student min=studentList.get(i);
			for (int k=i+1 ; k<c ;k++) {
				student sj= (student)studentList.get(k);
				if(sj.compareto(min)<0) {
					ipetit = k;
					min=sj;
				}
			}
			swap(ipetit,i);
		}
	}
	private int partition(int g, int d) {
		student key= studentList.get(g);
		int i =g+1;
		int j =d;
		while(i<=j) {
			student ti=studentList.get(i);
			student tj=studentList.get(j);
			while (key.compareto(ti)>=0) {
				i++;
			}
			while (key.compareto(tj)>=0) {
				i--;
			}
			if (i<j) {
				swap(i,j);
				i++;
				j--;
			}
		}
		swap(g,j);
		return j;
		
	}
	private void quickSort(int g, int d) {
		if (g<d) {
			int j=partition(g,d);
			quickSort(g,j-1);
			quickSort(j+1, d);
		}
	}
	public void quickSort() {
		int n=studentList.size();
		quickSort(0, n-1);
	}
}
