/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.dao;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author K. Chatzistamatis
 */
@Repository
public class PointOfInterestDao implements PointOfInterestDaoInt {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public PointOfInterest getById(String id) {
        try {
            return manager.find(PointOfInterest.class, id);
        } catch (Exception e) {
            Logger.getLogger(PointOfInterestDao.class.getName()).log(Level.SEVERE, StringConstants.DB_EXCEPTION_MSG, e);
        }
        return null;
    }

    @Override
    @Transactional
    public PointOfInterest update(PointOfInterest poi) {
        try {
            return manager.merge(poi);
        } catch (Exception e) {
            Logger.getLogger(PointOfInterestDao.class.getName()).log(Level.SEVERE, StringConstants.DB_EXCEPTION_MSG, e);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(PointOfInterest poi) {
        try {
            manager.remove(poi);
        } catch (Exception e) {
            Logger.getLogger(PointOfInterestDao.class.getName()).log(Level.SEVERE, StringConstants.DB_EXCEPTION_MSG, e);
        }
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        try {
            Query theQuery = manager.createQuery("Delete from PointOfInterest where id=:id");
            theQuery.setParameter("id", id);
            theQuery.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(PointOfInterestDao.class.getName()).log(Level.SEVERE, StringConstants.DB_EXCEPTION_MSG, e);
        }
    }

    @Override
    @Transactional
    @Cacheable("allPois")
    public List<PointOfInterest> getAll() {
        Query getAllPois = null;
        try {
            getAllPois = manager.createQuery("Select p from PointOfInterest as p");
            Thread.sleep(3000);
        } catch (Exception ex) {
            Logger.getLogger(PointOfInterestDao.class.getName()).log(Level.SEVERE, StringConstants.DB_EXCEPTION_MSG, ex);
        }
        return getAllPois != null ? getAllPois.getResultList() : null;

    }

    @Transactional
    @Cacheable("poisWithHitsNum")
    public List<PointOfInterest> getAllWithHitsNum(int hits) {
        Query getAllHittedPois = null;
        try {
            getAllHittedPois = manager.createQuery("SELECT p FROM PointOfInterest AS p WHERE p.counter > :hits");
            getAllHittedPois.setParameter("hits", hits);
            Thread.sleep(3000);
        } catch (Exception ex) {
            Logger.getLogger(PointOfInterestDao.class.getName()).log(Level.SEVERE, StringConstants.DB_EXCEPTION_MSG, ex);
        }
        return getAllHittedPois != null ? getAllHittedPois.getResultList() : null;
    }

    @Override
    @Transactional
    public String addNewPoi(PointOfInterest poi) {
        String id = UUID.randomUUID().toString();
        poi.setId(id);
        manager.persist(poi);
        return id;
    }

}
