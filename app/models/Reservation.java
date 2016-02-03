package models;

import com.avaje.ebean.Model;
import play.data.DynamicForm;
import play.data.Form;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajla on 26-Dec-15.
 */
@Entity
public class Reservation extends Model {
    @Id
    public Integer id;

//    @Column(columnDefinition = "datetime")

    public String dateFrom;
//    @Column(columnDefinition = "datetime")

    public String dateTo;
    public String visitorName;
    public String visitorLastname;
    public String visitorEmail;
    public String phone;
    public String comment;
    public Integer cost;
    @ManyToOne
    public Apartment apartment;


    public Reservation() {
    }
    public Reservation(Apartment apartment, String dateTo, String dateFrom, String visitorName, String visitorLastname, String visitorEmail, String phone, String comment, Integer cost) {
        this.apartment = apartment;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.visitorName = visitorName;
        this.visitorLastname = visitorLastname;
        this.visitorEmail = visitorEmail;
        this.phone = phone;
        this.comment = comment;
        this.cost = cost;
    }

//    private static Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

    public static void saveReservation(Integer apartmentId){
        DynamicForm form = Form.form().bindFromRequest();
        String checkIn = form.field("checkIndate").value();
        String checkOut = form.field("checkOutdate").value();
        String capacity = form.field("numOfPersons").value();
        String name = form.field("name").value();
        String email = form.field("mail").value();
        String phone = form.field("phone").value();
        String comment = form.field("comment").value();

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Apartment apartment = Apartment.getApartmentById(apartmentId);

        Reservation reservation = new Reservation();
        reservation.apartment = apartment;
        reservation.comment = comment;
        reservation.phone = phone;
        reservation.visitorEmail = email;
        reservation.visitorName = name.split(" ")[0];
        reservation.visitorLastname = name.split(" ")[1];
        reservation.dateFrom = checkIn;
        reservation.dateTo = checkOut;

        reservation.save();
    }

    public static List<String> getReservationsByApartmentId(Integer apartmentId) {
        Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

        List<Reservation> reservations = finder.where().eq("apartment_id", apartmentId).findList();
        List<String> dates = new ArrayList<>();

        for (int i=0; i < reservations.size(); i ++){
            dates.add(reservations.get(i).dateFrom.toString()+" do " + reservations.get(i).dateTo.toString() );
        }

        return dates;
    }
}
