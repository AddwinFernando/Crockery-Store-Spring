//package com.restapi.repository;
//
//
//import com.restapi.model.Address;
//import com.restapi.model.AppUser;
//import com.restapi.model.Role;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//public class AddressRepositoryTest {
//    @Autowired
//    AddressRepository addressRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void testSaveAddress() {
//        Role role = Role.builder().name("USER").build();
//        AppUser user = AppUser.builder().username("test").password("test").roles(role).build();
//        Address address = Address.builder().city("city").address("Test-Address").zipcode(600045).appUser(user).build();
//        Address address1 = addressRepository.save(address);
//
//        assertEquals(address1, address);
//    }
//
//}
