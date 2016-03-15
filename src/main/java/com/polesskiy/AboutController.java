package com.polesskiy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by polesskiy on 15.03.16.
 */
@Controller
public class AboutController {
    @RequestMapping(value= "/about.html", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "about.jsp";
    }
}
