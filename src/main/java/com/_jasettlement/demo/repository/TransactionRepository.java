package com._jasettlement.demo.repository;


import com._jasettlement.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByWalletIdAndIdempotencyKey(
            Long walletId,
            String idempotencyKey
    );



}
