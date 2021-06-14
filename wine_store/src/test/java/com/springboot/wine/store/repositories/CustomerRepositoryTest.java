package com.springboot.wine.store.repositories;

import com.springboot.wine.store.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testHappyPathForFindCustomerByEmail() {

        String email = "tayyaba@yahoo.com";

        Customer customer = new Customer();
        customer.setFirstName("Tayyaba");
        customer.setLastName("Razi");
        customer.setEmail(email);
        customerRepository.save(customer);

        Customer newCustomer = customerRepository.findCustomerByEmail(email);

        assertThat(newCustomer).isNotNull();

    }

    @Test
    void testFailurePathForFindCustomerByEmail() {

        String email = "tayyaba@yahoo.com";

        Customer newCustomer = customerRepository.findCustomerByEmail(email);

        assertThat(newCustomer).isNull();

    }
}