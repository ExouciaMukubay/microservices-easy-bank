package com.easybank.cards.repository;


import com.easybank.cards.entity.Cards;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByAccountNumber(String accountNumber);

    Optional<Cards> findByCardNumber(String cardNumber);

    @Transactional
    @Modifying
    boolean deleteByAccountNumber(String accountNumber);

}
