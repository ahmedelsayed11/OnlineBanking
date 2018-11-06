package com.luv2code.user.front.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luv2code.user.front.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {
    List<PrimaryTransaction> findAll(); 
}
