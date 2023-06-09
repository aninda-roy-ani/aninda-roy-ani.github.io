package com.test.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.model.Account;
import com.test.app.payload.AUTHOR_NOTES_VIEW;
import com.test.app.payload.InputModel;
import com.test.app.payload.NOTE_VIEW;
import com.test.app.service.MainService;
import com.test.app.service.WebClientService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Value("${baseURL}") private String baseURL;
    @Value("${readAccount}") private String readAccount;
    @Value("${readAllNotes}") private String readNotes;
    @Value("${updateNote}") private String updateNote;

    @Autowired private MainService service;

    @GetMapping({"/","/index","/login"})
    public String front(){
        return "index";
    }

    @PostMapping("/login")
    public String login(ModelMap model, InputModel inputModel,
                        HttpServletRequest request){
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject loginMap = new JSONObject();
        loginMap.put("email",inputModel.getEmail());
        loginMap.put("password",inputModel.getPassword());
        ResponseEntity<Account> res = WebClientService.PostHTTPForAccount(baseURL+readAccount,loginMap);
        if (res.hasBody() && res.getStatusCodeValue() == 200){
            Account account = res.getBody();
            account.setBirthday(service.getBirthday(account.getBirthday()));
            model.addAttribute("acc", account);
            ResponseEntity<AUTHOR_NOTES_VIEW> resN = WebClientService.PostHTTPForNote(baseURL+readNotes,loginMap);
            if (resN.getStatusCodeValue() == 200){
                AUTHOR_NOTES_VIEW authorNotesView = resN.getBody();
                model.addAttribute("AuthorNotes",authorNotesView);
            }
            return "profile";
        }
        return "redirect:/login";
    }

    @PostMapping("/updateNote")
    public String scoring(@RequestParam("thoughtText") String thoughtText,
            InputModel inputModel, ModelMap model, HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String thot = request.getParameter("thoughtText");
        System.out.println("----------------------");
        System.out.println(thot);
        System.out.println(thoughtText);
        System.out.println("----------------------");
        JSONObject loginMap = new JSONObject();
        loginMap.put("email",inputModel.getEmail());
        loginMap.put("password",inputModel.getPassword());
        ResponseEntity<Account> res = WebClientService.PostHTTPForAccount(baseURL+readAccount,loginMap);
        if (res.hasBody() && res.getStatusCodeValue() == 200){
            Account account = res.getBody();
            account.setBirthday(service.getBirthday(account.getBirthday()));
            model.addAttribute("acc", account);
            Double score = (double) ((inputModel.getScorePresentation()+ inputModel.getScoreStructure())/2);
            loginMap.put("note_no1",inputModel.getNote_no1());
            loginMap.put("score",score);
            loginMap.put("thoughtText",thoughtText);
            ResponseEntity<AUTHOR_NOTES_VIEW> resN = WebClientService.PostHTTPForNote(baseURL+updateNote,loginMap);
            if (resN.getStatusCodeValue() == 200){
                AUTHOR_NOTES_VIEW authorNotesView = resN.getBody();
                model.addAttribute("AuthorNotes",authorNotesView);
            }
            return "profile";
        }
        return login(model,inputModel,request);
    }




}
