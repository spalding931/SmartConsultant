package esiea.org.app.Model;

/**
 * Created by Nicolas on 08/11/2016.
 */

public class UserCompetence {

    private int id;
    private int id_user;
    private int id_competence;

    public UserCompetence(int id, int id_user, int id_competence) {
        this.id = id;
        this.id_user = id_user;
        this.id_competence = id_competence;
    }

    public UserCompetence() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_competence() {
        return id_competence;
    }

    public void setId_competence(int id_competence) {
        this.id_competence = id_competence;
    }
}
