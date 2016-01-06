package controllers;

import models.Apartment;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.apartment;
import views.html.createapartment;
import views.html.updateapartment;

/**
 * Created by User on 12/29/2015.
 */
public class Apartments extends Controller {

    // Apartment details
    public Result apartment(Integer apartmentId) {
        Apartment apart = Apartment.getApartmentById(apartmentId);
        return ok(apartment.render(apart));
    }

    // Create apartment
    public Result renderApartment() {
        return ok(createapartment.render());
    }

    public Result createApartment() {
        Apartment apart = Apartment.createApartment();
        if (apart != null) {
            flash("success", "Uspješno ste kreirali apartman.");
            return ok(apartment.render(apart));
        } else {
            flash("error", "Desila se greška, apartman nije kreiran.");
            return ok(createapartment.render());
        }
    }

    // Update apartment
    public Result renderUpdateApartment(Integer apartmentId) {
        Apartment apart = Apartment.getApartmentById(apartmentId);

        Logger.debug(apart.toString());

        return ok(updateapartment.render(apart));
    }

    public Result updateApartment(Integer apartmentId) {
        Apartment apart = Apartment.updateApartment(apartmentId);
        if (apart != null) {
            flash("success", "Uspješno ste ažurirali podatke o  apartmanu.");
            return ok(apartment.render(apart));
        } else {
            flash("error", "Desila se greška, podaci o apartmanu nisu ažurirani.");
            return ok(createapartment.render());
        }
    }

}
