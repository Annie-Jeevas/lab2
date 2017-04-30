/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Team;
import java.util.List;
import javax.ejb.Local;


@Local
public interface ITeamFacade {
    public void addTeam(Team s);    
    public void editTeam(Team s);
    public void deleteTeam(Team s);
    public Team findTeam(Object id);
    public List<Team> findAllTeams();
}
