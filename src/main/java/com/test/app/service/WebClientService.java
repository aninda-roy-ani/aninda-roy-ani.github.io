package com.test.app.service;

import com.test.app.model.Account;
import com.test.app.model.Note;
import com.test.app.payload.AUTHOR_NOTES_VIEW;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebClientService {

    public static ResponseEntity<Account> PostHTTPForAccount(String url, JSONObject loginMap){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<Account> res = null;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(loginMap.toString(), headers);
        try {
            res = restTemplate.exchange(url, HttpMethod.POST, entity, Account.class);
            System.out.println(res);
        } catch (Exception e) {

        }
        return res;
    }

    public static ResponseEntity<AUTHOR_NOTES_VIEW> PostHTTPForNote(String url, JSONObject loginMap){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<AUTHOR_NOTES_VIEW> res = null;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(loginMap.toString(), headers);
        try {
            res = restTemplate.exchange(url, HttpMethod.POST, entity, AUTHOR_NOTES_VIEW.class);
            System.out.println(res);
        } catch (Exception e) {

        }
        return res;
    }
}
