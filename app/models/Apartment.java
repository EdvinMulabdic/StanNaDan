package models;

import com.avaje.ebean.Model;
import helpers.Authenticator;
import play.Logger;
import play.api.mvc.MultipartFormData;
import play.data.Form;
import play.mvc.Security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;
import java.util.List;

/**
 * Created by ajla on 22-Dec-15.
 */
@Entity
public class Apartment extends Model {
    @Id
    public Integer id;
    public String name;
    public String title;
    public String neighborhood;
    public String address;
    public Integer price;
    public Integer capacity;
    public Integer beds;
    public Integer rooms;
    public Integer area;
    public Integer floor;
    @Column(columnDefinition = "TEXT")
    public String description;
    public String lat;
    public String lng;

    /**
     * Default constructor
     * @param id
     * @param name
     * @param title
     * @param neighborhood
     * @param address
     * @param price
     * @param capacity
     * @param beds
     * @param rooms
     * @param area
     * @param floor
     * @param description
     * @param lat
     * @param lng
     */
    public Apartment(Integer id, String name, String title, String neighborhood, String address, Integer price, Integer capacity,
                     Integer beds, Integer rooms, Integer area, Integer floor, String description, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.neighborhood = neighborhood;
        this.address = address;
        this.price = price;
        this.capacity = capacity;
        this.beds = beds;
        this.rooms = rooms;
        this.area = area;
        this.floor = floor;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", beds=" + beds +
                ", rooms=" + rooms +
                ", area=" + area +
                ", floor=" + floor +
                ", description=" + description +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    private static Form<Apartment> form = Form.form(Apartment.class);
    private static Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);

    /**
     * Creates an Apartment and saves it in the DB
     * @return
     */
    /* --------------- create apartment ---------------*/
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public static Apartment createApartment() {
        Form<Apartment> boundForm = form.bindFromRequest();
        Apartment apartment = null;
        try {
            apartment = boundForm.get();
            apartment.save();

            return apartment;
        } catch (Exception e) {
            Logger.debug("Nisam uspio spasiti apartman :(");
            return apartment;
        }
    }
    /* --------------- update apartment ---------------*/
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public static Apartment updateApartment(Integer apartmentId) {
        Form<Apartment> boundForm = form.bindFromRequest();

        Apartment apartment = Apartment.getApartmentById(apartmentId);
        try {
            String name = boundForm.field("name").value();
            String title = boundForm.field("title").value();
            String neighborhood = boundForm.field("neighborhood").value();
            String address = boundForm.field("address").value();
            Integer price = Integer.parseInt(boundForm.field("price").value());
            Integer capacity = Integer.parseInt(boundForm.field("capacity").value());
            Integer beds = Integer.parseInt(boundForm.field("beds").value());
            Integer rooms = Integer.parseInt( boundForm.field("rooms").value());
            Integer area = Integer.parseInt(boundForm.field("area").value());
            Integer floor = Integer.parseInt( boundForm.field("floor").value());
            String description = boundForm.field("description").value();
            String lat = boundForm.field("lat").value();
            String lng = boundForm.field("lng").value();

            apartment.name = name;
            apartment.title = title;
            apartment.neighborhood = neighborhood;
            apartment.address = address;
            apartment.price = price;
            apartment.capacity = capacity;
            apartment.beds = beds;
            apartment.rooms = rooms;
            apartment.area = area;
            apartment.floor = floor;
            apartment.description = description;
            apartment.lat = lat;
            apartment.lng = lng;

            apartment.update();

            return apartment;
        } catch (Exception e) {
            Logger.debug("Nisam uspio spasiti apartman :(");
            return apartment;
        }
    }
    /* --------------- delete apartment ---------------*/
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public static void deleteApartment(Integer apartmentId){
        Apartment apartment = finder.where().eq("id", apartmentId).findUnique();
        apartment.delete();
    }

        /* --------------- retrieves all apartments ---------------*/
    public static List<Apartment> getAllApartments(){
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.all();
        return apartments;
    }

    /**
     * Retrieves an apartment by provided id.
     * @param apartmentId
     * @return
     */
    public static Apartment getApartmentById(Integer apartmentId) {
        return finder.where().eq("id", apartmentId).findUnique();
    }
        /* --------------- retrieves apartments with location centar ---------------*/

    public static List<Apartment> apartmentsCentar(){
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.where().eq("neighborhood", "Centar").findList();
        return apartments;
    }
        /* --------------- retrieves apartments with location novo sarajevo ---------------*/

    public static List<Apartment> apartmentsNSarajevo(){
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.where().eq("neighborhood", "Novo Sarajevo").findList();
        return apartments;
    }
        /* --------------- retrieves apartments with location novi grad ---------------*/

    public static List<Apartment> apartmentsNGrad(){
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.where().eq("neighborhood", "Novi Grad").findList();
        return apartments;
    }
        /* --------------- retrieves apartments with location stari grad ---------------*/

    public static List<Apartment> apartmentsSGrad(){
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.where().eq("neighborhood", "Stari Grad").findList();
        return apartments;
    }
        /* --------------- retrieves apartments with location ilidza ---------------*/

    public static List<Apartment> apartmentsIlidza(){
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.where().eq("neighborhood", "Ilidza").findList();
        return apartments;
    }

    /**
     * Retrieves a list of apartments for homepage.
     * TODO Odluciti i implementirati nacin na koji ce se odlucivati koji se apartmani prikazivati na pocetnoj stranici
     * @return
     */
    public static List<Apartment> apartmentsForHomepage() {
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.all();
        return apartments;
    }

}
