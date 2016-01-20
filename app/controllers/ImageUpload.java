package controllers;

import com.google.common.io.Files;
import models.Apartment;
import play.Logger;
import play.mvc.Result;
import play.mvc.Controller;
import play.mvc.Http;
import views.html.upload;
import views.html.index;

import java.io.File;
import java.nio.file.*;
import java.util.List;

/**
 * Created by ajla on 13-Jan-16.
 */
public class ImageUpload extends Controller {

    public Result uploadRender() {
        return ok(upload.render());
    }

    public Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");

        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();

            file.renameTo(new File("C:\\Users\\ajla\\Documents\\DTProjects\\StanNaDan\\public\\apartmentPhotos", fileName));

            Logger.debug(file.getPath());

            List<Apartment> apartments = Apartment.apartmentsForHomepage();
            Logger.debug("uspjesno");
            return ok(index.render(apartments));

        } else {
            flash("error", "Missing file");
            List<Apartment> apartments = Apartment.apartmentsForHomepage();
            Logger.debug("neuspjesno!!!!!!");
            return ok(index.render(apartments));
        }
    }
}
