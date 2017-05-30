/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ISportsmenBean;
import entities.Sportsman;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import lab3.entities.SportsmanDetails;
import lab3.facades.SportsmanDetailsFacadeLocal;

/**
 *
 * @author Анюта
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TransactionBean {

    @EJB
    ISportsmenBean sb;
    @EJB
    SportsmanDetailsFacadeLocal sdf;
    @Resource
    SessionContext scontext;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String SuccessTransaction() {
        Sportsman s = sb.findAllSportsmen().get(0);
        s.setAccuracy(s.getAccuracy() - 0.01F); //исключение, если не найден
        reduceAccuracy(s);
        sb.editSportsman(s);
        return "Success1";
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    private void reduceAccuracy(Sportsman s) {
        SportsmanDetails details = sdf.find(s.getId());
        details.setAccuracy(s.getAccuracy());
        sdf.edit(details);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String secondTransaction() {
        Sportsman s = sb.findAllSportsmen().get(0);
        s.setAccuracy(s.getAccuracy() - 0.01F);
        reduceAccuracy(s);
        sb.editSportsman(s);
        scontext.setRollbackOnly();
        return "Success2";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String thirdTransaction() {
        Sportsman s = sb.findAllSportsmen().get(0);
        s.setAccuracy(s.getAccuracy() - 0.01F);
        sb.editSportsman(s);
        try {
            reduceAccuracyWithException(s);
        } catch (EJBException ex) {
            return "Success3";
        }
        return "Not-success3";
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    private void reduceAccuracyWithException(Sportsman s) throws EJBException {
        SportsmanDetails details = sdf.find(s.getId());
        details.setAccuracy(s.getAccuracy());
        sdf.edit(details);
        throw new EJBException();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String fourthTransaction() {

        Sportsman s = sb.findAllSportsmen().get(0);
        s.setAccuracy(s.getAccuracy() - 0.01F);
        sb.editSportsman(s);
        reduceAccuracyNotSupported(s);
        scontext.setRollbackOnly();
        return "Success4";

    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private void reduceAccuracyNotSupported(Sportsman s) {
        SportsmanDetails details = sdf.find(s.getId());
        details.setAccuracy(s.getAccuracy());
        sdf.edit(details);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String fifthTransaction() {

        Sportsman s = sb.findAllSportsmen().get(0);
        s.setAccuracy(s.getAccuracy() - 0.01F);
        sb.editSportsman(s);
        reduceAccuracyRequiresNew(s);
        scontext.setRollbackOnly();
        return "Success5";

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void reduceAccuracyRequiresNew(Sportsman s) {
        reduceAccuracy(s);
    }

}
