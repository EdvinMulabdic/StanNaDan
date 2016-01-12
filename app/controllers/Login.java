package controllers;

import helpers.Cookies;
import helpers.Session;
import helpers.UserAccessLevel;
import models.Apartment;
import models.AppUser;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.adminpage;
import views.html.adminpanel;
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
    /* ---------------  admin page list of apartments render ---------------*/

    public Result renderAdminPage() {
        List<Apartment> apartments = Apartment.getAllApartments();

        return ok(adminpage.render(apartments));
    }

    public Result showAdminPanel(String email){
        AppUser user = AppUser.findUserByEmail(email);

        return ok(adminpanel.render(user));
    }

    /* --------------- admin panel ---------------*/

    public Result renderAdminPanel(){
        DynamicForm form = Form.form().bindFromRequest();

        String email = form.field("email").value();
        String password = form.field("password").value();

        AppUser user = AppUser.authenticate(email, password);

        if (user == null) {
            flash("login-error", "Incorrect email or password! Try again.");
        }else if(user.userAccessLevel == UserAccessLevel.ADMIN){
            Cookies.setUserCookies(user);
            Session.setUserSessionData(user);
            return ok(adminpanel.render(user));
        }
        return redirect(routes.Application.index());
    }

    /* --------------- admin page update password ---------------*/

    public Result updateUser(String email){
        AppUser user = AppUser.findUserByEmail(email);
        DynamicForm form = Form.form().bindFromRequest();
        String password = form.field("password").value();

        boolean isUpdated = AppUser.updateUser(user, password);

        if(isUpdated){
            flash("success", "Vas password je azuriran");
            return redirect(routes.Login.showAdminPanel(user.email));
        }
        flash("error-search", "Neuspjelo azuriranje.");
        return redirect(routes.Login.renderAdminPanel());
        }

    }

