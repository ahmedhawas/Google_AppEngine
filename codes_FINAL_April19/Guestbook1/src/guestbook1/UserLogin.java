package guestbook1;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import com.google.appengine.api.users.User;


@Entity(name = "UserLogin")
public class UserLogin {
    @Id
    private String userId;

    @Basic
    private User user;

    public UserLogin(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static UserLogin getPrefsForUser(User user) {
        UserLogin userPrefs = null;

        EntityManager em = EMF.get().createEntityManager();
        try {
            userPrefs = em.find(UserLogin.class, user.getUserId());
            if (userPrefs == null) {
                userPrefs = new UserLogin(user.getUserId());
                userPrefs.setUser(user);
                em.persist(userPrefs);
            }
        } finally {
            em.close();
        }

        return userPrefs;
    }

    public void save() {
        EntityManager em = EMF.get().createEntityManager();
        try {
            em.merge(this);
        } finally {
            em.close();
        }
    }
}
