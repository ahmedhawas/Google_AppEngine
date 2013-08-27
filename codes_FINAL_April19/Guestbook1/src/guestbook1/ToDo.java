package guestbook1;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;


@Entity(name = "ToDo")
public class ToDo {
    @Id
    public Key content;
    
    //private String content;
    private User user;

    //private int copyrightYear;
    //private Date authorBirthdate;
    

    public ToDo(String content) {
    	this.content = KeyFactory.createKey("ToDo",content);
    }

    public String getToDoID() {
        return KeyFactory.keyToString(content);
    }

    public void setToDoID(com.google.appengine.api.datastore.Key content) {
		this.content = content;
	}

	//public void setToDoContent(String content) {
      //  this.content = content;
    //}
    //public String getToDoContent() {
      //  return content;
    //}

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}