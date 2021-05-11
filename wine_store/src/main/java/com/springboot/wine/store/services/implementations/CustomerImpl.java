package com.springboot.wine.store.services.implementations;


import com.springboot.wine.store.common.Utils.CommonUtils;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.CustomerDTO;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.mappers.CustomerMapper;
import com.springboot.wine.store.mappers.WineMapper;
import com.springboot.wine.store.repositories.CustomerRepository;
import com.springboot.wine.store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDTO registerCustomer(Customer customer) {
        if (CommonUtils.isNullOrEmpty(customer))
            throw new BusinessCaseException(Constants.CUSTOMER_DETAIL_NOT_COMPLETED, this.getClass().toString());
        else {
            return CustomerMapper.INSTANCE.CustomerToDto(customerRepository.save(customer));
        }

    }

    @Override
    public CustomerDTO getCustomerFindByEmail(String email) {
        return CustomerMapper.INSTANCE.CustomerToDto(customerRepository.findCustomerByEmail(email));
    }

    @Override
    public CustomerDTO getCustomerFindById(Long id) {
        return CustomerMapper.INSTANCE.CustomerToDto(customerRepository.findById(id).get());
    }

    @Override
    public List<CustomerDTO> getCustomerFindAll() {
        List<Customer> customerList =  customerRepository.findAll();
       return convertListOfCustomerToCustomerDto(customerList);
    }

    @Override
    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private List<CustomerDTO> convertListOfCustomerToCustomerDto(List<Customer> customerList)
    {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.CustomerToDto(customer);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
