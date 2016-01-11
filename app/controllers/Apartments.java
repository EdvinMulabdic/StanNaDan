package controllers;

import helpers.Authenticator;
import helpers.Cookies;
import models.Apartment;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
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
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public Result renderApartment() {
        return ok(createapartment.render());
    }
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public Result createApartment() {
        Apartment apart = Apartment.createApartment();
        if (apart != null) {
            flash("success", "Uspješno ste kreirali apartman.");
            return redirect(routes.Apartments.apartment(apart.id));
        } else {
            flash("error", "Desila se greška, apartman nije kreiran.");
            return status(404, createapartment.render());
        }
    }

    // Update apartment
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public Result renderUpdateApartment(Integer apartmentId) {
        Apartment apart = Apartment.getApartmentById(apartmentId);

        return ok(updateapartment.render(apart));
    }

    @Security.Authenticated(Authenticator.AdminFilter.class)
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
//        String apartment = ctx.session().get(" ");
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
        /* --------------- delete apartment ---------------*/

    public Result deleteApartment(Integer apartmentId){
        Apartment.deleteApartment(apartmentId);
        List<Apartment> apartments = Apartment.getAllApartments();
        return status(200, adminpage.render(apartments));
    }
            /* --------------- admin page render ---------------*/
    @Security.Authenticated(Authenticator.AdminFilter.class)
    public Result adminPageRender(){
        List<Apartment> apartments = Apartment.getAllApartments();
        return ok(adminpage.render(apartments));
    }

}
