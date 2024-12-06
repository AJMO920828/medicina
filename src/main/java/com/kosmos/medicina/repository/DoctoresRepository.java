package com.kosmos.medicina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosmos.medicina.entity.Doctor;

@Repository
public interface DoctoresRepository extends JpaRepository<Doctor, Long>{

}
