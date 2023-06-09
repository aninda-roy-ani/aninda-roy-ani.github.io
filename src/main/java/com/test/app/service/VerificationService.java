package com.test.app.service;

import com.test.app.dao.AccountRepository;
import com.test.app.model.Account;
import com.test.app.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationService {

    @Autowired
    private AccountRepository accRepo;


    public Account verifyLoginAccountCredentials(Account inputAccount) {
        List<Account> accounts = accRepo.findAccountsByEmailAndPassword(inputAccount.getEmail(),inputAccount.getPassword());
        if (accounts.size()>0){
            return accounts.get(0);
        }
        return new Account("NULL");
    }

    public boolean verifyEmail(String author) {
        List<Account> accounts = accRepo.findAccountsByEmail(author);
        if (accounts.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean verifyMsgHandlers(Message newMsg) {
        if (verifyEmail(newMsg.getSender()) && verifyEmail(newMsg.getReceiver())
                && !newMsg.getSender().equals(newMsg.getReceiver())){
            return true;
        }
        return false;
    }
}
