package models;

import com.avaje.ebean.Model;
import play.Logger;
import play.data.Form;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.Id;

/**
 * Created by ajla on 22-Dec-15.
 */
@Entity
public class Apartment extends Model {
    @Id
    public Integer id;
    public String name;
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
    public Apartment(Integer id, String name, String neighborhood, String address, Integer price, Integer capacity,
                     Integer beds, Integer rooms, Integer area, Integer floor, String description, String lat, String lng) {
        this.id = id;
        this.name = name;
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
    public static List<Apartment> apartmentsForHomepage() {
        Model.Finder<String, Apartment> finder = new Model.Finder<>(Apartment.class);
        List<Apartment> apartments = finder.all();
        return apartments;
    }

    public static Apartment getApartmentById(Integer apartmentId) {
        return finder.where().eq("id", apartmentId).findUnique();
    }
}

