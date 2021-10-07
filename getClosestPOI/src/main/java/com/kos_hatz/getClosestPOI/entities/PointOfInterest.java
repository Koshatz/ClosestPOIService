/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.locationtech.jts.geom.Point;

/**
 *
 * @author K. Chatzistamatis
 */
@Entity
@Table(name = "poi")
public class PointOfInterest implements Serializable{

    @Id
    private String id;

    private String name;
    private String description;
    @Column(columnDefinition = "geometry")
    private Point location;
    private int counter;
    private transient double distance;

    public PointOfInterest() {
    }

    public PointOfInterest(String id, String name, String description, Point location, int counter) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.counter = counter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    

}
