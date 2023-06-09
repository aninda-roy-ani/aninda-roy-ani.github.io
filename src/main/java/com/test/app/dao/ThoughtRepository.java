package com.test.app.dao;

import com.test.app.model.Thought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThoughtRepository extends JpaRepository<Thought,Long> {
}
