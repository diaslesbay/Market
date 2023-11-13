package com.example.test.repository;

import com.example.test.model.Category;
import com.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

}
