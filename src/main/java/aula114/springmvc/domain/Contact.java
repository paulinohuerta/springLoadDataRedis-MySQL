package aula114.springmvc.domain;
import java.io.Serializable;

/**
 * 
 * @author www.codejava.net
 *
 */

public class Contact implements Serializable{
        private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String address;
	private String telephone;

        public Contact() { }
        public Contact(String n, String e, String a, String p) {
          name=n;
          email=e;
          address=a;
          telephone=p;
        }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String toString() {
		return String.format("[%s - %s - %s - %s]", name, email, address, telephone);
	}
}
