package com.example.secure.services;

import com.example.secure.models.Person;
import com.example.secure.repositories.PeopleRepository;
import com.example.secure.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    @Autowired
    private PeopleRepository peopleRepository;


    @Override
    public UserDetails loadUserByUsername(String us) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(us);
        if (person.isEmpty()){
            throw new UsernameNotFoundException("username not found!!");
        }
        return new PersonDetails(person.get());
    }
}
