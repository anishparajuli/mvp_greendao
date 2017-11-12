package uk.co.ribot.androidboilerplate.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by User on 11/12/2017.
 */
@Entity
public class User {

    @Id
    private Long userID;
    private String name;

    public User(){}

    public  User(String name){
        this.name=name;
    }

    @Generated(hash = 490979870)
    public User(Long userID, String name) {
        this.userID = userID;
        this.name = name;
    }



    
    public long getId() {
        return this.userID;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.userID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getID() {
        return this.userID;
    }

    public void setID(Long ID) {
        this.userID = ID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
