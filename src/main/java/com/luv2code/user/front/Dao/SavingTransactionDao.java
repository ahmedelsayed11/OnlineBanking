package com.luv2code.user.front.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.SavingsTransaction;

public interface SavingTransactionDao extends CrudRepository<SavingsTransaction, Long> {
        List<SavingsTransaction> findAll();
}
