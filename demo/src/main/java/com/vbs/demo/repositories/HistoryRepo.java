package com.vbs.demo.repositories;

import com.vbs.demo.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //its controller will be made in user controller bcz History table will be filled when userController is activated
public interface HistoryRepo extends JpaRepository<History,Integer> {
}
