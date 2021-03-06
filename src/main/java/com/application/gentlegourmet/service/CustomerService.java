package com.application.gentlegourmet.service;

import com.application.gentlegourmet.entity.Customer;
import com.application.gentlegourmet.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    //////////////////////////////////////////////////////////////////////////


    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    

}
