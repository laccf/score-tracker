package br.com.itengine.score.controller;

import br.com.itengine.score.Application;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Thiago Almeida on 30/05/2016.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value="",method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(Application.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
            }
            catch (Exception e) {
            }
        }
        else {
        }
    }

}
