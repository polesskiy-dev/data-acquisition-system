package com.polesskiy.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by polesskiy on 25.03.16.
 */
public final class EMF {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("SENSORS-TRANSACTIONS");

    private EMF() {
    }

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}