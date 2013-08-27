package guestbook1;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;

import java.io.Serializable;

@Entity(name = "Contact")
public class Contact implements Serializable{
    @Id
    Key contactName;
    //private String isbn;

    private String phoneNumber;
    private String emailAddress;
    private String streetName;
    private String city;
    private String province;
    private String postal;
    private User user;

    //private int copyrightYear;
    //private Date authorBirthdate;
    

    public Contact(String contactName) {
        this.contactName = KeyFactory.createKey("Contact",contactName);
    }

    public String getContactID() {
        return KeyFactory.keyToString(contactName);
    }

    public void setContactID(com.google.appengine.api.datastore.Key contactName) {
		this.contactName = contactName;
	}

	public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getStreetName() {
        return streetName;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    public String getProvince() {
        return province;
    }
    
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public String getPostal() {
        return postal;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

//    public void setCopyrightYear(int copyrightYear) {
//        this.copyrightYear = copyrightYear;
//    }
//    public int getCopyrightYear() {
//        return copyrightYear;
//    }
//
//    public void setAuthorBirthdate(Date authorBirthdate) {
//        this.authorBirthdate = authorBirthdate;
//    }
//    public Date getAuthorBirthdate() {
//        return authorBirthdate;
//    }
}