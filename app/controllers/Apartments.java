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

    public Result apartment() {

        return ok(apartment.render());
    }

    public Result createApartment() {
        Boolean success = Apartment.createApartment();
        if (success) {
            flash("success", "Uspješno ste kreirali apartman.");
            return ok(apartment.render());
        } else {
            flash("error", "Desila se greška, apartman nije kreiran.");
            return ok(createapartment.render());
        }
    }


}
