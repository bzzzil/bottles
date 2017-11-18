package io.bzzzil.bottles.database;

import java.io.Serializable;

/**
 * BottleDocument is Firebase package.
 * Since we store document id and document data in a separate way we should not mix 'em in on structure
 */
public class BottleDocument implements Serializable {
    private String id;
    private Bottle data;

    public BottleDocument(String id, Bottle data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Bottle getData() {
        return data;
    }
}
