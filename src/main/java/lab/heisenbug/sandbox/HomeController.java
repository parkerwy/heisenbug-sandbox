package lab.heisenbug.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by parker on 19/11/2016.
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    //@RequestMapping("/")
    public ResponseEntity<String> home() {
        LOGGER.info("processing bootstrap request.");
        return new ResponseEntity<>("Hello, world!", HttpStatus.OK);
    }
}
