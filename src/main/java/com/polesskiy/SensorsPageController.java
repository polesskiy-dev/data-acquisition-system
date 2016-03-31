package com.polesskiy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by polesskiy on 18.03.16.
 */
@Controller
public class SensorsPageController {
    //@Autowired
    UserService userService = new UserServiceImp();

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleClientErrorException(HttpClientErrorException e) {
        return new ResponseEntity<>(e.getStatusCode().getReasonPhrase(), e.getStatusCode());
    }

    @RequestMapping(value = "/users/{usersLogin}/sensors.html", method = RequestMethod.GET)
    public ModelAndView pathVariable(@PathVariable String usersLogin) throws HttpClientErrorException {
        ModelAndView mav = new ModelAndView("sensors.jsp");

        User user = userService.findUser(usersLogin);

        if (user != null)
            try {
                ObjectMapper mapper = new ObjectMapper();
                mav.addObject("sensorsJSON", String.format("\'%s\'", mapper.writeValueAsString(user.getSensors())));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

        return mav;
    }
}
