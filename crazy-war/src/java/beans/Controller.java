/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ISportsmenBean;
import dao.ITeamFacade;
import entities.Sportsman;
import entities.Team;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

/**
 *
 * @author Анюта
 */
@ManagedBean(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    @EJB
    private ISportsmenBean spb;
    @EJB
    private ITeamFacade tf;
    private Sportsman sportsmanToAdd;
    private Long sportsmanTeamID;
    private Team teamToAdd = new Team();
    private Team teamToShow = new Team();

    public Controller() {
    }

    public Sportsman getSportsmanToAdd() {
        if (sportsmanToAdd == null) {
            sportsmanToAdd = new Sportsman();
        }
        return sportsmanToAdd;
    }

    public Team getTeamToAdd() {
        return teamToAdd;
    }

    public Team getTeamToShow() {
        return teamToShow;
    }
    

    public void setSportsmanToAdd(Sportsman sportsmanToAdd) {
        this.sportsmanToAdd = sportsmanToAdd;
    }

    public void setTeamToAdd(Team teamToAdd) {
        this.teamToAdd = teamToAdd;
    }

    public Long getSportsmanTeamID() {
        return sportsmanTeamID;
    }

    public void setSportsmanTeamID(Long sportsmanTeamID) {
        this.sportsmanTeamID = sportsmanTeamID;
    }

    public String saveSportsmanToAdd() {

        sportsmanToAdd.setTeam(tf.findTeam(sportsmanTeamID));
        spb.addSportsman(sportsmanToAdd);
        sportsmanToAdd = new Sportsman();
        return "sportsmanAdded";
    }

    public String saveTeamToAdd() {
        tf.addTeam(teamToAdd);
        teamToAdd = new Team();
        return "successTeamSave";
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(tf.findAllTeams(), true);
    }

    public List<Team> AllTeams() {
        return tf.findAllTeams();
    }

    public List<Sportsman> AllSportsmen() {
        return spb.findAllSportsmen();
    }

    public String showTeam(Team t){
        teamToShow = t;
        return "showTeam";
    }
}
