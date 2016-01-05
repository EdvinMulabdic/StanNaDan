package controllers;

import models.Apartment;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.apartment;
import views.html.createapartment;

/**
 * Created by User on 12/29/2015.
 */
public class Apartments extends Controller {

    public Result renderApartment() {
        return ok(createapartment.render());
    }

    public Result apartment(Integer apartmentId) {
        Apartment apartment = Apartment.getApartmentById(apartmentId);
        return ok(apartment.render(apartment));
    }

    public Result createApartment() {
        Apartment apartment = Apartment.createApartment();
        if (apartment != null) {
            flash("success", "Uspješno ste kreirali apartman.");
            return ok(apartment.render(apartment));
        } else {
            flash("error", "Desila se greška, apartman nije kreiran.");
            return ok(createapartment.render());
        }
    }
}
