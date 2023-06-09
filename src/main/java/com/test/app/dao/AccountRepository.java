package com.test.app.dao;

import com.test.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByEmail(String email);

    List<Account> findAccountsByEmailAndPassword(String email, String password);
}
