package com.example.errors.web.GlobalApplicationExceptionHandling;

public class ObjectNotFoundExceptionGE extends RuntimeException {

    private final Long objectId;

    public ObjectNotFoundExceptionGE(Long objectId) {

        super("Object with id " + objectId + " not found!");
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }
}
