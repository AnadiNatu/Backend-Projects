package com.transaction.system.repository;

import com.transaction.system.model.AmountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AmountRepository extends JpaRepository<AmountEntity,Long> {
}
