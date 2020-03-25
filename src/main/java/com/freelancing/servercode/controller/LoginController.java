package com.freelancing.servercode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.servercode.service.TestService;
import com.freelancing.servercode.table.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController
{
    @Autowired
    private TestService testService;

    @PostMapping(value = "/registration")
    public void createNewUser(@RequestBody User user)
    {
        testService.saveUser(user);
    }
    /*
     * @RequestMapping(value = "/admin/home", method = RequestMethod.GET) public ModelAndView home()
     * { ModelAndView modelAndView = new ModelAndView(); Authentication auth =
     * SecurityContextHolder.getContext().getAuthentication(); User user =
     * userService.findUserByUserName(auth.getName()); modelAndView.addObject("userName", "Welcome "
     * + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" +
     * user.getEmail() + ")"); modelAndView.addObject("adminMessage",
     * "Content Available Only for Users with Admin Role"); modelAndView.setViewName("admin/home");
     * return modelAndView; }
     */
}
