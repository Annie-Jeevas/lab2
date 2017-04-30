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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
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

    public void saveTeamToAdd() {
        tf.addTeam(teamToAdd);
        teamToAdd = new Team();
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
//
//    @FacesConverter(forClass = Team.class)
//    public static class TeamControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            Controller controller = (Controller) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "controller");
//            return controller.getSportsmanToAdd().getTeam().getTeamName();
//        }
//        
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof Team) {
//                Team o = (Team) object;
//                return o.toString();
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Team.class.getName());
//            }
//        }

//    }
}
