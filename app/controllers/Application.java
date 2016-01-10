package controllers;

import models.Apartment;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cooperation;
import views.html.index;

import java.util.List;

public class Application extends Controller {

    public Result index() {
            List<Apartment> apartments = Apartment.apartmentsForHomepage();
        return ok(index.render(apartments));
    }
    public Result cooperation(){
        return ok(cooperation.render());
    }

}
