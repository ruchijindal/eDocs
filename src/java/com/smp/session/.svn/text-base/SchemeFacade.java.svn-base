/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.session;

import com.smp.entity.Scheme;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class SchemeFacade extends AbstractFacade<Scheme> {
    @PersistenceContext(unitName = "SMPDocManPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public SchemeFacade() {
        super(Scheme.class);
    }

}
