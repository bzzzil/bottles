package io.bzzzil.bottles.database;

import com.google.firebase.firestore.DocumentSnapshot;

import java.io.Serializable;

/**
 * BottleDocument is Firebase package.
 * Since we store document id and document data in a separate way we should not mix 'em in on structure
 */
public class BottleDocument implements Serializable {
    private final String id;
    private final Bottle data;

    public BottleDocument(String id, Bottle data) {
        this.id = id;
        this.data = data;
    }

    public BottleDocument(DocumentSnapshot document) {
        this.id = document.getId();
        this.data = document.toObject(Bottle.class);
    }

    public String getId() {
        return id;
    }

    public Bottle getData() {
        return data;
    }

    @Override
    public boolean equals(Object other) {
        BottleDocument oth = (BottleDocument)other;
        return this.id.equals(oth.id);
    }
}
