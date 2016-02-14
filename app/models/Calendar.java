package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by User on 2/14/2016.
 */
@Entity
public class Calendar extends Model {

    @Id
    public Integer id;

    public String title;
    public String startdate;
    public String enddate;
    public String allDay;

    public Calendar(){

    }

    public Calendar(String title, String startdate, String enddate, String allDay) {
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.allDay = allDay;
    }
}
