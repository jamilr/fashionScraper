package org.fscraper.data.sql.model;/**
 * Created by jr on 5/23/2015.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Jamil Rzayev
 */

@Entity
@Table(name = "Season")
public class Season implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    public Season(){

    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
