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
import java.util.*;

/**
 * Created by ajla on 26-Dec-15.
 */
@Entity
public class Reservation extends Model {
    private static Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

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
    public Boolean approved;
    public Boolean declined;


    public Reservation() {
    }

    public Reservation(Apartment apartment, Date dateTo, Date dateFrom, String visitorName, String visitorLastname, String visitorEmail,String capacity, String phone, String comment, Integer cost, Boolean approved, Boolean declined) {
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
        this.approved = false;
        this.declined = false;
    }

//    private static Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

    public static void saveReservation(Integer apartmentId, String name, String email, String phone, Date checkInDate, Date checkOutDate, String numOfPersons, String comment) {
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
        } else {
            reservation.visitorName = name;
            reservation.visitorLastname = " ";
        }
        reservation.dateFrom = checkInDate;
        reservation.dateTo = checkOutDate;

        reservation.approved = false;
        reservation.declined = false;

        reservation.save();
    }

    public static List<String> getReservationsByApartmentId(Integer apartmentId) throws ParseException {
        Model.Finder<String, Reservation> finder = new Model.Finder<>(Reservation.class);

        List<Reservation> reservations = finder.where().eq("apartment_id", apartmentId).findList();
        List<String> dates = new ArrayList<>();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = new GregorianCalendar();

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).approved == true) {
                String dateFrom = DATE_FORMAT.format(reservations.get(i).dateFrom);
                Date dateToPlusOne =  new Date(reservations.get(i).dateTo.getTime()+(24*60*60*1000));
                String dateTo = DATE_FORMAT.format(dateToPlusOne);

                calendar.setTime(DATE_FORMAT.parse(dateFrom));

                while (calendar.getTime().before(DATE_FORMAT.parse(dateTo))) {
                    Date result = calendar.getTime();
                    dates.add("\"" + DATE_FORMAT.format(result).toString() + "\"");
                    calendar.add(Calendar.DATE, 1);
                }
            }
        }

        return dates;
    }

    public static List<Reservation> getReservationsByApartmentIdReservation(Integer apartmentId) {
        return finder.where().eq("apartment_id", apartmentId).findList();
    }

    public static Reservation getReservationById(Integer reservationId) {
        return finder.where().eq("id", reservationId).findUnique();
    }

    public static Apartment getApartmentByReservationId(Integer reservationId) {
        Reservation reservation = finder.where().eq("id", reservationId).findUnique();
        return reservation.apartment;
    }

    public static void approveReservation(Integer reservationId) {
        Reservation reservation = getReservationById(reservationId);
        reservation.approved = true;
        reservation.declined = false;
        reservation.save();
    }

    public static void declineReservation(Integer reservationId) {
        Reservation reservation = getReservationById(reservationId);
        reservation.declined = true;
        reservation.approved = false;
        reservation.save();
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", visitorName='" + visitorName + '\'' +
                ", visitorLastname='" + visitorLastname + '\'' +
                ", visitorEmail='" + visitorEmail + '\'' +
                ", capacity='" + capacity + '\'' +
                ", phone='" + phone + '\'' +
                ", comment='" + comment + '\'' +
                ", cost=" + cost +
                ", apartment=" + apartment +
                '}';
    }
}
