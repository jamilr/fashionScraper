package org.fscraper.data.sql.model;

/**
 * Created by jr on 3/22/2015.
 */

public class EmptyEntity implements IEntity {

    public EmptyEntity() {

    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }
}
