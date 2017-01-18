package esiea.org.app.Model;

/**
 * Created by Ayoub Bouthoukine on 17/11/2016.
 */
public class Profile {


    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String city;
    private String job;
    private String isFilled;
    private String competence;

    public Profile() {
        isFilled = "NO";
    }

    public  Profile(String firstname, String lastname, String email, int age, String city,String job)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.city = city;
        this.job = job;
        this.isFilled = "YES";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(String isFilled) {
        this.isFilled = isFilled;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }
}
