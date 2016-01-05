package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.createapartment;
import views.html.login;

/**
 * Created by User on 1/5/2016.
 */
public class Login extends Controller {
    public Result loginIndex(){
        return ok(login.render());
    }

    public Result login(){
        return ok(createapartment.render());
    }
}
