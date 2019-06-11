/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import utils.PuSelector;

/**
 *
 * @author mwn
 */
public class Facade {

    public static void saveHistory(int week, String address) {
        EntityManager em = PuSelector.getEntityManagerFactory("pu_production").createEntityManager();
        History his = new History(week, address);

        try {
            em.getTransaction().begin();
            em.persist(his);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static List<History> getHistory() {
        List<History> histories = null;
        EntityManager em = PuSelector.getEntityManagerFactory("pu_production").createEntityManager();

        try {
            
            Query query = em.createQuery("SELECT a FROM History a");
            histories = query.getResultList();
            return histories;
        } finally {
            em.close();
        }
    }
}
