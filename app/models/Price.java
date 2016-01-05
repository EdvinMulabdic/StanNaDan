package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;

/**
 * Created by ajla on 05-Jan-16.
 */
@Entity
public class Price extends Model {
    public Integer id;
    public Apartment apartment;
    public Integer personsNo;
    public Integer nightsNo;


}
