/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Sportsman;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Анюта
 */
@Remote
public interface ISportsmenBean {
    public void addSportsman(Sportsman s);    
    public void editSportsman(Sportsman s);
    public void deleteSportsman(Sportsman s);
    public Sportsman findSportsman(Object id);
    public List<Sportsman> findAllSportsmen();
    
}
