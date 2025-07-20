package com.easybank.cards.service;


import com.easybank.cards.dto.CardsDto;

public interface CardsServiceI {

    void createCard(CardsDto cardsDto);

    CardsDto fetchCard(String cardNumber);

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardsDto cardsDto);


    boolean deleteCard(String accountNumber);

}
