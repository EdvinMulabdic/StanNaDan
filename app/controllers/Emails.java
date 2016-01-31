package controllers;

import helpers.ConfigProvider;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createuser;

/**
 * Created by User on 1/8/2016.
 */
public class Emails extends Controller {
    public Result sendMail(Integer apartmentId) {
        //taking values from input fields
        DynamicForm form = Form.form().bindFromRequest();
        String name = form.field("name").value();
        String mail = form.field("mail").value();
        String phone = form.field("phone").value();
        String checkIndate = form.field("checkIndate").value();
        String checkOutdate = form.field("checkOutdate").value();
        String numOfPersons = form.field("numOfPersons").value();
        String comment = form.field("comment").value();

        /* sending an email*/
        SimpleEmail email = new SimpleEmail();
        email.setHostName(ConfigProvider.SMTP_HOST);
        email.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
        try {
                /*Configuring mail*/
            email.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            email.setFrom(ConfigProvider.MAIL_FROM);
            email.setStartTLSEnabled(true);
            email.addTo(ConfigProvider.SMTP_USER);
            email.setSubject("Rezervacija");
            email.setMsg("Ime i prezime:  " + name + "\n"+
                        "Email:  " + mail + "\n" +
                        "Telefon:  " + phone + "\n" +
                        "Datum dolaska:  " + checkIndate + "\n" +
                        "Datum odlaska:  " + checkOutdate + "\n" +
                        "Broj osoba:  " + numOfPersons + "\n\n" +
                        "Komentar:  " + comment);

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
            /*If mail is sent flash appears and user is redirected to index page */
        flash("success", "Vasa poruka je poslana. Potrudit cemo se da odgovorimo u najkracem mogucem roku. Zahvaljujemo!");
        return redirect(routes.Apartments.apartment(apartmentId));

    }

     /* ------------------- send mail to user when his account is created ------------------ */

    public static void sendMail(String mail, String password){


        SimpleEmail email = new SimpleEmail();
        email.setHostName(ConfigProvider.SMTP_HOST);
        email.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
        try {
                /*Configuring mail*/
            email.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            email.setFrom(ConfigProvider.MAIL_FROM);
            email.setStartTLSEnabled(true);
            email.addTo(mail);
            email.setSubject("Uspjesno kreiran profil");
            email.setMsg("Postovani, " + "\n" +
                    "Zelimo Vas obavijestiti da je Vas profil napravljen." + "\n" +
                    "Profilu mozete pristupiti uz sljedece podatke:" + "\n" +
                    "Username: " + mail + "\n" +
                    "Password: " + password + "\n" +

                    "Zelimo Vam puno uspjeha." + "\n" +
                    "Vas," + "\n" +
                    "StanNaDan");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
