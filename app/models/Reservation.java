package models;

import com.avaje.ebean.Model;
import play.data.DynamicForm;
import play.data.Form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ajla on 26-Dec-15.
 */
@Entity
public class Reservation extends Model {
    @Id
    public Integer id;

    @Column(columnDefinition = "DATE")
    public Date dateFrom;

    @Column(columnDefinition = "DATE")
    public Date dateTo;

    public String visitorName;
    public String visitorLastname;
    public String visitorEmail;
    public String capacity;
    public String phone;
    public String comment;
    public Integer cost;
    @ManyToOne
    public Apartment apartment;


    public Reservation() {
    }
    public Reservation(Apartment apartment, Date dateTo, Date dateFrom, String visitorName, String visitorLastname, String visitorEmail,String capacity, String phone, String comment, Integer cost) {
        this.apartment = apartment;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.visitorName = visitorName;
        this.visitorLastname = visitorLastname;
        this.visitorEmail = visitorEmail;
        this.capacity = capacity;
        this.phone = phone;
        this.comment = comment;
        this.cost = cost;
    }

//    private static Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

    public static void saveReservation(Integer apartmentId, String name, String email, String phone, Date checkInDate, Date checkOutDate, String numOfPersons, String comment){
        DynamicForm form = Form.form().bindFromRequest();

        Apartment apartment = Apartment.getApartmentById(apartmentId);

        Reservation reservation = new Reservation();
        reservation.apartment = apartment;
        reservation.comment = comment;
        reservation.phone = phone;
        reservation.visitorEmail = email;
        reservation.capacity = numOfPersons;
        reservation.visitorName = name;

        if(reservation.visitorName.contains(" ")) {
            reservation.visitorName = name.split(" ")[0];
            reservation.visitorLastname = name.split(" ")[1];
        }else{
            reservation.visitorName = name;
            reservation.visitorLastname = " ";
        }
        reservation.dateFrom = checkInDate;
        reservation.dateTo = checkOutDate;

        reservation.save();
    }

    public static List<String> getReservationsByApartmentId(Integer apartmentId) throws ParseException {
        Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

        List<Reservation> reservations = finder.where().eq("apartment_id", apartmentId).findList();
        List<String> dates = new ArrayList<>();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

        for (int i=0; i < reservations.size(); i ++){
            String date = DATE_FORMAT.format(reservations.get(i).dateFrom);
            dates.add(date);

        }

        return dates;
    }
}
