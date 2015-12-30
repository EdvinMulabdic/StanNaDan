package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.apartment;
import views.html.createapartment;

/**
 * Created by User on 12/29/2015.
 */
public class Apartments extends Controller {

    public Result renderApartment(){
        return ok(createapartment.render());
    }
    public Result apartment(){
        return ok(apartment.render());
    }
}
