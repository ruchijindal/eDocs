/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.session;

import com.smp.entity.Userrole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class UserroleFacade extends AbstractFacade<Userrole> {
    @PersistenceContext(unitName = "SMPDocManPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UserroleFacade() {
        super(Userrole.class);
    }

}
