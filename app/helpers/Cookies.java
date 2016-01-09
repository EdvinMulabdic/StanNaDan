package helpers;

import models.Apartment;
import play.mvc.Security;

import static play.mvc.Controller.response;

/**
 * Created by User on 1/6/2016.
 */
public class Cookies extends Security.Authenticator {

    public static void setCookies(Integer apartmentId) {
        Apartment apartment = Apartment.getApartmentById(apartmentId);
        if (apartment.name.contains(" ")) {
            String apartmentName = apartment.name.split(" ")[1];
            response().setCookie(apartmentName, apartmentId.toString() + "");
        } else {
            response().setCookie(apartment.name, apartmentId.toString() + "");
        }
    }


}