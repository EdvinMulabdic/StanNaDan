package controllers;

import models.Apartment;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.adminpage;
import views.html.login;

import java.util.List;

/**
 * Created by User on 1/5/2016.
 */
public class Login extends Controller {
        /* --------------- login page render ---------------*/

    public Result loginIndex(){
        return ok(login.render());
    }

    /* --------------- admin page list of apartments render ---------------*/

    public Result renderAdminPage(){
        List<Apartment> apartments = Apartment.getAllApartments();
        return ok(adminpage.render(apartments));
    }
}
