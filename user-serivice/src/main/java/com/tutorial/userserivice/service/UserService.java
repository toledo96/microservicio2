package com.tutorial.userserivice.service;

import java.util.List;

import com.tutorial.userserivice.model.Bike;
import com.tutorial.userserivice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.userserivice.entity.User;
import com.tutorial.userserivice.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
	

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    
    public User save(User user){
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }

}
