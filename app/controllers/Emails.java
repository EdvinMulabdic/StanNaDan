package controllers;

import models.Email;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 1/8/2016.
 */
public class Emails extends Controller {
    public Result sendMail(Integer apartmentId) throws ParseException {


            //taking values from input fields
            DynamicForm form = Form.form().bindFromRequest();
            String name = form.field("name").value();
            String mail = form.field("mail").value();
            String phone = form.field("phone").value();
            Integer cost =Integer.parseInt(form.field("price").value());



            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date checkIndate = formatter.parse(form.field("checkIndate").value());
                Date checkOutdate = formatter.parse(form.field("checkOutdate").value());

            String numOfPersons = form.field("numOfPersons").value();
            String comment = form.field("comment").value();

            Email.sendMailReservation(name, mail, phone, checkIndate, checkOutdate, numOfPersons, comment,cost, apartmentId);
            /*If mail is sent flash appears and user is redirected to index page */
            flash("success", "Vasa poruka je poslana. Potrudit cemo se da odgovorimo u najkracem mogucem roku. Zahvaljujemo!");
            return redirect(routes.Apartments.apartment(apartmentId));
    }


}
