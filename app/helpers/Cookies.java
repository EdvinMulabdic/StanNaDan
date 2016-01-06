package helpers;

import play.Logger;
import play.mvc.Security;

import static play.mvc.Controller.response;

/**
 * Created by User on 1/6/2016.
 */
public class Cookies extends Security.Authenticator {

    public static void setCookies(Integer apartmentId){
        Logger.info(apartmentId.toString());
        response().setCookie("apartment", apartmentId.toString());

    }
}