package com.it.academy.repositories;

import com.it.academy.models.S3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3Repository extends JpaRepository<S3, Long> {

}
