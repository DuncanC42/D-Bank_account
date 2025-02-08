package bzh.duncan.accounts.service;

import bzh.duncan.accounts.dto.AccountsDto;
import bzh.duncan.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);


    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
