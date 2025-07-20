package com.easybank.cards.service.impl;


import com.easybank.cards.dto.CardsDto;
import com.easybank.cards.exeception.CardAlreadyExistsException;
import com.easybank.cards.exeception.ResourceNotFoundException;
import com.easybank.cards.mapper.CardsMapper;
import com.easybank.cards.repository.CardsRepository;
import com.easybank.cards.service.CardsServiceI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsServiceI {

    private final CardsRepository cardsRepository;

    @Override
    public void createCard(CardsDto cardsDto) {
        var optionalCards = cardsRepository.findByCardNumber(cardsDto.getCardNumber());
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given cardNumber " + cardsDto.getCardNumber());
        }
        cardsRepository.save(CardsMapper.mapToCards(cardsDto));
    }

    @Override
    public CardsDto fetchCard(String cardNumber) {
        var optionalCards = cardsRepository.findByCardNumber(cardNumber);

        if (!optionalCards.isPresent()) {
            throw new ResourceNotFoundException("Card", "cardNumber", cardNumber);
        }

        return CardsMapper.mapToCardsDto(optionalCards.get());
    }

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        var optionalCards = cardsRepository.findByCardNumber(cardsDto.getCardNumber());

        if (!optionalCards.isPresent()) {
            throw new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber());
        }

        CardsMapper.updateCardsAndMapTopCard(cardsDto, optionalCards.get());
        cardsRepository.save(optionalCards.get());
        return true;
    }

    @Override
    public boolean deleteCard(String accountNumber) {
        if (!cardsRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new ResourceNotFoundException("Card", "accountNumber", accountNumber);
        }
        return cardsRepository.deleteByAccountNumber(accountNumber);
    }
}
