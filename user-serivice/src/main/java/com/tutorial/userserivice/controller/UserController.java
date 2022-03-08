package com.tutorial.userserivice.controller;

import java.util.List;
import java.util.Map;

import com.tutorial.userserivice.model.Bike;
import com.tutorial.userserivice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.userserivice.entity.User;
import com.tutorial.userserivice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	 @Autowired
	    private UserService userService;

	    @GetMapping
	    public ResponseEntity<List<User>> getAll(){
	        List<User> users = userService.getAll();
	        if(users.isEmpty()){
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(users);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<User> getById(@PathVariable("id") int id){
	        User user = userService.getUserById(id);
	        if(user == null){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(user);
	    }

	    @PostMapping()
	    public ResponseEntity<User> save(@RequestBody User user){
	        User userNew = userService.save(user);
	        return ResponseEntity.ok(userNew);
	    }

	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId) {
		User user = userService.getUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
		List<Car> cars = userService.getCars(userId);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId) {
		User user = userService.getUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
		List<Bike> bikes = userService.getBikes(userId);
		return ResponseEntity.ok(bikes);
	}

	@PostMapping("/savecar/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
		if(userService.getUserById(userId) == null)
			return ResponseEntity.notFound().build();
		Car carNew = userService.saveCar(userId, car);
		return ResponseEntity.ok(car);
	}

	@PostMapping("/savebike/{userId}")
	public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
		if(userService.getUserById(userId) == null)
			return ResponseEntity.notFound().build();
		Bike bikeNew = userService.saveBike(userId, bike);
		return ResponseEntity.ok(bike);
	}

	@GetMapping("/getAll/{userId}")
	public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) {
		Map<String, Object> result = userService.getUserAndVehicles(userId);
		return ResponseEntity.ok(result);
	}

}
