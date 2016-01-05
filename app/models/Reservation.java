package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by ajla on 26-Dec-15.
 */
@Entity
public class Reservation extends Model {
    public Integer id;
    public Apartment apartment;
    public Date dateFrom;
    public Date dateTo;
    public String visitorName;
    public String visitorLastname;
    public String visitorEmail;
    public String visitorIdentificationNo;
    public String phone;
    public String comment;
    public String arrival;
    public Integer cost;

   // public Integer

}
