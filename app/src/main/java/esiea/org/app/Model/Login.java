package esiea.org.app.Model;

/**
 * Created by Nicolas Gossart le plus meilleur !!! bouh Ayoub bouthoukine on 07/11/2016.
 */
public class Login {

    private int id ;
    private String username;
    private String password;
    private int type;

    private Profile profile;


    //constructor
    public Login(String username, String password, int type)
    {
        this.username = username;
        this.password = password;
        this.type = type;
        this.id = 0;
        profile = new Profile();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.profile.setId(id);

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
