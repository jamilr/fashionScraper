package org.fscraper.data.sql.model;

import java.io.Serializable;

/**
 * Created by jr on 3/21/2015.
 */

public interface IEntity extends Serializable {

    public Integer getId();

    public void setId(Integer id);
}
