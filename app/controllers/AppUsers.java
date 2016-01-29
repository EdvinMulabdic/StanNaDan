package controllers;

import models.AppUser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.allusers;
import views.html.createuser;

import java.util.List;

/**
 * Created by User on 1/29/2016.
 */
public class AppUsers extends Controller {
    public Result createUserRender(){
        return ok(createuser.render());
    }

    public Result createUser(){
        AppUser.createUser();
        return redirect(routes.AppUsers.listOfUsers());
    }

    public Result listOfUsers(){
        List<AppUser> users = AppUser.getAllAppUsers();
        return ok(allusers.render(users));
    }
    public Result deleteUser(Integer userId){
        AppUser.deleteUser(userId);
        List<AppUser> users = AppUser.getAllAppUsers();

        return status(200,allusers.render(users));
    }


}
