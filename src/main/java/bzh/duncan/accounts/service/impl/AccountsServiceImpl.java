package bzh.duncan.accounts.service.impl;

import bzh.duncan.accounts.constants.AccountsConstants;
import bzh.duncan.accounts.dto.AccountsDto;
import bzh.duncan.accounts.dto.CustomerDto;
import bzh.duncan.accounts.entity.Accounts;
import bzh.duncan.accounts.entity.Customer;
import bzh.duncan.accounts.exception.CustomerAlreadyExistsException;
import bzh.duncan.accounts.exception.ResourceNotFoundException;
import bzh.duncan.accounts.mapper.AccountsMapper;
import bzh.duncan.accounts.mapper.CustomerMapper;
import bzh.duncan.accounts.repository.AccountsRepository;
import bzh.duncan.accounts.repository.CustomerRepository;
import bzh.duncan.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(existingCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customer.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccounts(savedCustomer));
    }

    private Accounts createNewAccounts(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100000000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


    /**
     * @param mobileNumber 
     * @return
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "AccountsNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);

            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber 
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
