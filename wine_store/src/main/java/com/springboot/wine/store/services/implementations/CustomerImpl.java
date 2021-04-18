package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        if(CommonUtils.isNullOrEmpty(customer))
            throw new BusinessCaseException(Constants.CUSTOMER_DETAIL_NOT_COMPLETED,this.getClass().toString());
        else
        {
            return customerRepository.save(customer);
        }

    }
    @Override
    public Customer getCustomerFindByEmail(String email) {
      return customerRepository.findCustomerByEmail(email);
    }
    @Override
    public Customer getCustomerFindById(Long id) {
        return customerRepository.findById(id).get();
    }
    @Override
    public List<Customer> getCustomerFindAll() {
        return customerRepository.findAll();
    }
    @Override
    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
