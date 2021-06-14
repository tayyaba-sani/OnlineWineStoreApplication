package com.springboot.wine.store.mappers;

import com.springboot.wine.store.dtos.CustomerDTO;
import com.springboot.wine.store.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer dtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO CustomerToDto(Customer customer);

    default List<CustomerDTO> convertListOfCustomerToCustomerDto(List<Customer> customerList) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.CustomerToDto(customer);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
