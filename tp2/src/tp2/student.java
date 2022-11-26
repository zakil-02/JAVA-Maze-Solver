package tp2; 
public class student {
	private final int id;
	private       String firstName;
	private       String lastName;
	public student(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public final String getFirstName() {
		return firstName;
	}
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public final String getLastName() {
		return lastName;
	}
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public final int getId() {
		return id;
	}
	@Override
	public String toString() {
		return firstName + " " + lastName +" ("+ id +")";
	}
	public int compareto(student other) {
		int comparaison=this.lastName.compareTo(other.lastName);
		if (comparaison!=0) {
			return comparaison;
		}
		comparaison=this.firstName.compareTo(other.firstName);
		if (comparaison!=0) {
			return comparaison;
		}
		if (this.id > other.id) {
			return 1;
		}
		else if (this.id < other.id) {
			return -1;
		}
		else {
			return 0;
		}
	}
}

