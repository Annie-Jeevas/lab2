/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Team;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Анюта
 */
@Stateless

public class TeamFacade implements ITeamFacade, Serializable {

    @PersistenceContext(unitName = "Lab2RPS-ejbPU")
    private EntityManager em;
    
    @Override
    public void addTeam(Team s) {        
        em.persist(s);
    }
    @Override
    public void editTeam(Team s) {
        em.merge(s);
    }
    @Override
    public void deleteTeam(Team s) {
        em.remove(em.merge(s));
    }
    @Override
    public Team findTeam(Object id) {
        return em.find(Team.class, id);
    }
    @Override
    public List<Team> findAllTeams() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Team.class));        
        return em.createQuery(cq).getResultList();
    }
    
}
