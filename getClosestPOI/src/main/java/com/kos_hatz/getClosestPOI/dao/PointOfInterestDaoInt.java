/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.dao;

import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import java.util.List;

/**
 *
 * @author K. Chatzistamatis
 */
interface PointOfInterestDaoInt {

    public PointOfInterest getById(String id);

    public PointOfInterest update(PointOfInterest poi);

    public void deleteById(String id);

    public void delete(PointOfInterest poi);

    public List<PointOfInterest> getAll();
    
    public String addNewPoi (PointOfInterest poi);
}
