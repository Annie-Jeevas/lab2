/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Sportsman;
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
public class SportsmenBean implements ISportsmenBean, Serializable {

    @PersistenceContext(unitName = "Lab2RPS-ejbPU")
    private EntityManager em;

    public void addSportsman(Sportsman s) {
        if (s.getTeam() == null) s.setTeam(new Team());
        em.persist(s);
    }
    public void editSportsman(Sportsman s) {
        em.merge(s);
    }
    public void deleteSportsman(Sportsman s) {
        em.remove(em.merge(s));
    }
    public Sportsman findSportsman(Object id) {
        return em.find(Sportsman.class, id);
    }
    public List<Sportsman> findAllSportsmen() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Sportsman.class));
        return em.createQuery(cq).getResultList();
    }
}
