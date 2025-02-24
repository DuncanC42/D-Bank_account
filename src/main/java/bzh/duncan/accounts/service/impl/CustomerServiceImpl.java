package bzh.duncan.accounts.service.impl;

import bzh.duncan.accounts.dto.AccountsDto;
import bzh.duncan.accounts.dto.CardsDto;
import bzh.duncan.accounts.dto.CustomerDetailsDto;
import bzh.duncan.accounts.dto.LoansDto;
import bzh.duncan.accounts.entity.Accounts;
import bzh.duncan.accounts.entity.Customer;
import bzh.duncan.accounts.exception.ResourceNotFoundException;
import bzh.duncan.accounts.mapper.AccountsMapper;
import bzh.duncan.accounts.mapper.CustomerMapper;
import bzh.duncan.accounts.repository.AccountsRepository;
import bzh.duncan.accounts.repository.CustomerRepository;
import bzh.duncan.accounts.service.ICustomerService;
import bzh.duncan.accounts.service.client.CardsFeignClient;
import bzh.duncan.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));


        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
