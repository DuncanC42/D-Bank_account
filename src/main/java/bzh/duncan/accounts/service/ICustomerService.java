package bzh.duncan.accounts.service;

import bzh.duncan.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {


    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
