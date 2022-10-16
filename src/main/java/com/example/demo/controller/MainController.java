package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    private ObjectDAO objectDAO;

    @Autowired
    private ObjectsDAOImpl objectDAOI;

    @Autowired
    private ParamsDAO paramsDAO;

    @Autowired
    private ParamsDAOImpl paramsDAOI;

    @Autowired
    private ListValuesDAO listValuesDAO;

    @Autowired
    private ListValuesDAOImpl listValuesDAOI;

    @Autowired
    private ReferencesDAO referencesDAO;

    @Autowired
    private ReferencesDAOImpl referencesDAOI;

    @Autowired
    private AttributesDAOImpl attributesDAOImpl;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "welcomePage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        checkColumns(model, userInfo);
        return "userInfoPage";
    }

    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public String userDetails(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        checkColumns(model, userInfo);
        return "userDetailsPage";
    }

    @GetMapping("/userEdit")
    public String userEdit(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        checkColumns(model, userInfo);
        ArrayList<ListValues> sexList = listValuesDAO.getListValues(1L);
        model.addAttribute("sexList", sexList);
        ArrayList<Objects> cityList = objectDAO.findByObjectTypeId(3L);
        model.addAttribute("cityList", cityList);
        return "userEditPage";
    }

    @PostMapping("/userEdit")
    public String blogPostEdit(Model model, Principal principal,
                               @RequestParam(name = "FirstName", required = false) String f_name,
                               @RequestParam(name = "LastName", required = false) String l_name,
                               @RequestParam(name = "Patronymic", required = false) String patronymic,
                               @RequestParam(name = "Sex", required = false) Long sex,
                               @RequestParam(name = "Birthday", required = false) Date birthday,
                               @RequestParam(name = "Email", required = false) String email,
                               @RequestParam(name = "Phone", required = false) String phone,
                               @RequestParam(name = "City", required = false) Long city,
                               @RequestParam(name = "Symptoms", required = false) String symptoms) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        ArrayList<Params> params = paramsDAO.getValues(userInfo.getObject_id());
        ArrayList<References> references = referencesDAO.getReferences(userInfo.getObject_id());
        for (Params par : params) {
            if (par.getAttr_id() == 1L)
                par.setValue(f_name);
            if (par.getAttr_id() == 2L)
                par.setValue(l_name);
            if (par.getAttr_id() == 3L)
                par.setValue(patronymic);
            if (par.getAttr_id() == 4L)
                par.setList_value_id(sex);
            if (par.getAttr_id() == 5L)
                par.setDate_value(birthday);
            if (par.getAttr_id() == 6L)
                par.setValue(email);
            if (par.getAttr_id() == 7L)
                par.setValue(phone);
            if (par.getAttr_id() == 14L)
                par.setValue(symptoms);
        }
        for (References ref : references) {
            if (ref.getAttr_id() == 8L)
                ref.setReference(city);
        }
        paramsDAOI.saveAll(params);
        referencesDAOI.saveAll(references);
        return "redirect:/userInfo";
    }

    @PostMapping("/userDelete")
    public String userDelete(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        referencesDAO.delReference(userInfo.getObject_id());
        paramsDAO.delValues(userInfo.getObject_id());
        objectDAOI.deleteById(userInfo.getObject_id());
        return "redirect:/logout";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        return "adminPage";
    }

    @RequestMapping(value = "/doctorPage", method = RequestMethod.GET)
    public String doctorPage(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        return "doctorPage";
    }

    @RequestMapping(value = "/nursePage", method = RequestMethod.GET)
    public String nursePage(Model model, Principal principal) {
        Objects userInfo = objectDAO.findUserAccount(principal.getName());
        return "nursePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/sign_up")
    public String getSign_up(Model model) {
        return "sign_upPage";
    }

    @PostMapping("/sign_up")
    public String postSign_up(@RequestParam String username, @RequestParam String password, Model model) {
        if (!objectDAO.createUserAccount(username))
            return "redirect:/sign_up?error=true";
        else {
            Objects user = objectDAO.findUserAccount(username);
            Iterable<Attributes> attributes = attributesDAOImpl.findAll();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            paramsDAO.createUserAccount(user, attributes);
            ArrayList<Params> params = paramsDAO.getValues(user.getObject_id());
            for (Params par : params) {
                if (par.getAttr_id() == 11L) {
                    par.setValue(encoder.encode(password));
                }
                if (par.getAttr_id() == 12L) {
                    par.setList_value_id(8L);
                }
                if (par.getAttr_id() == 15L) {
                    par.setDate_value(Date.valueOf(LocalDate.now()));
                }

            }
            paramsDAOI.saveAll(params);
            referencesDAO.createUserAccount(user, attributes);
        }
        return "redirect:/login?success=true";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("title", "О нас!");
        return "aboutPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() + "<br> You do have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

    private boolean checkColumns(Model model, Objects userInfo) {
        ArrayList<Params> params = paramsDAO.getValues(userInfo.getObject_id());
        ArrayList<References> references = referencesDAO.getReferences(userInfo.getObject_id());
        ArrayList<Attributes> attributes = (ArrayList<Attributes>) attributesDAOImpl.findAll();
        ArrayList<Attributes> copyAttr = (ArrayList<Attributes>) attributesDAOImpl.findAll();
        for (Attributes attr : attributes) {
            for (Params par : params) {
                if (par.getAttr_id() == attr.getAttr_id() & (attr.getAttr_type_id() == 0L | attr.getAttr_type_id() == 1L | attr.getAttr_type_id() == 3L)) {
                    model.addAttribute(attr.getName(), par.getValue());
                    copyAttr.remove(attr);
                    break;
                }
                if (par.getAttr_id() == attr.getAttr_id() & attr.getAttr_type_id() == 2L) {
                    model.addAttribute(attr.getName(), par.getDate_value());
                    copyAttr.remove(attr);
                    break;
                }

                if (par.getAttr_id() == attr.getAttr_id() & attr.getAttr_type_id() == 4L) {
                    if (par.getList_value_id() != null)
                        model.addAttribute(attr.getName(), listValuesDAOI.findById(par.getList_value_id()).orElseThrow().getName());
                    copyAttr.remove(attr);
                    break;
                }
            }
            for (References ref : references) {
                if (ref.getAttr_id() == attr.getAttr_id() & attr.getAttr_type_id() == 5L) {
                    if (ref.getReference() != null)
                        model.addAttribute(attr.getName(), objectDAO.findById(ref.getReference()).getName());
                    copyAttr.remove(attr);
                    break;
                }
            }
        }
        paramsDAO.createUserAccount(userInfo, copyAttr);
        referencesDAO.createUserAccount(userInfo, copyAttr);
        return true;
    }
}