package com.polesskiy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by polesskiy on 18.03.16.
 */
@Controller
public class SensorsController {
    @RequestMapping(value = "/{user}/sensors.html", method = RequestMethod.GET)
    public String pathVariable(@PathVariable String user, Model model) {
        return "sensors.jsp";
    }
}
