/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.session;

import com.smp.entity.Master;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class MasterFacade extends AbstractFacade<Master> {
    @PersistenceContext(unitName = "SMPDocManPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MasterFacade() {
        super(Master.class);
    }

}
