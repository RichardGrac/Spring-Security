package com.udemy.backendninja2.repository;

import com.udemy.backendninja2.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable>{


}
