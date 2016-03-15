package com.polesskiy.dao;

/**
 * Created by polesskiy on 11.03.16.
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMFService {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("SENSORS-TRANSACTIONS");

    private EMFService() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}