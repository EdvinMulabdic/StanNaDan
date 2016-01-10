package controllers;

import helpers.Cookies;
import models.Apartment;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import java.util.List;


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
    public Result cookies(Integer apartmentId){
        Cookies.setCookies(apartmentId);
        return redirect(routes.Apartments.apartment(apartmentId));
    }

    public Result favouriteApartments(){

        return ok(favourite.render());
    }
     /* --------------- apartments with location centar ---------------*/

    public Result centarApartments(){
        List<Apartment> apartments = Apartment.apartmentsCentar();
        return ok(searchApartments.render(apartments));
    }
        /* --------------- apartments with location novo sarajevo ---------------*/

    public Result nsarajevoApartments(){
        List<Apartment> apartments = Apartment.apartmentsNSarajevo();
        return ok(searchApartments.render(apartments));
    }
        /* --------------- apartments with location novi grad ---------------*/

    public Result ngradApartments(){
        List<Apartment> apartments = Apartment.apartmentsNGrad();
        return ok(searchApartments.render(apartments));
    }
        /* --------------- apartments with location stari grad ---------------*/

    public Result sgradApartments(){
        List<Apartment> apartments = Apartment.apartmentsSGrad();
        return ok(searchApartments.render(apartments));
    }
        /* --------------- apartments with location ilizda ---------------*/

    public Result ilidzaApartments(){
        List<Apartment> apartments = Apartment.apartmentsIlidza();
        return ok(searchApartments.render(apartments));
    }



}
