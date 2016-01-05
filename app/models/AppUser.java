package models;

import com.avaje.ebean.Model;

import javax.persistence.Id;

/**
 * Created by ajla on 05-Jan-16.
 */
public class AppUser extends Model {
    @Id
    public Integer id;
}
