package com.vbs.demo.controller;

import com.vbs.demo.dto.DisplayDto;
import com.vbs.demo.dto.LoginDto;
import com.vbs.demo.dto.UpdatDto;
import com.vbs.demo.models.History;
import com.vbs.demo.models.User;
import com.vbs.demo.repositories.HistoryRepo;
import com.vbs.demo.repositories.UserRepo;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    HistoryRepo historyRepo;
    History h1 = new History();

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userRepo.save(user);
        h1.setDescription("User Self created: "+user.getUsername());
        historyRepo.save(h1);
        return "Sign up successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto u){
        User user = userRepo.findByUsername(u.getUsername());
        if(user == null){
            return "user not found";
        }
        if(!(u.getPassword().equals(user.getPassword())))
        {
            return "Password incorrect";
        }
        if(!(u.getRole().equals(user.getRole()))){
            return "Incorrect Role";
        }
        return String.valueOf(user.getId());
    }

    @GetMapping("/get-details/{id}")
    public DisplayDto display(@PathVariable int id){ //the pathvariable is the variable given to us in the path which was returned in ;ogin
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        DisplayDto displayDto = new DisplayDto();
        displayDto.setUsername(user.getUsername());
        displayDto.setBalance(user.getBalance());

        return displayDto;
    }

    @PostMapping("/update")
    public String update(@RequestBody UpdatDto obj){
        User user = userRepo.findById(obj.getId()).orElseThrow(()->new RuntimeException("user not found"));

        if(obj.getKey().equalsIgnoreCase("name")){
            if(obj.getValue().equalsIgnoreCase(user.getName())) return "Cannot be same";
            h1.setDescription("User updated name from "+user.getName()+" to "+obj.getValue());
            user.setName(obj.getValue());
        }
        else if(obj.getKey().equalsIgnoreCase("password")){
            if(obj.getValue().equalsIgnoreCase(user.getPassword())) return "Cannot be same";
            h1.setDescription("User updated password to "+obj.getValue());
            user.setPassword(obj.getValue());
        }
        else if(obj.getKey().equalsIgnoreCase("email")){
            if(obj.getValue().equalsIgnoreCase(user.getEmail())) return "Cannot be same";
            User user2 = userRepo.findByEmail(obj.getValue());
            if(user2 != null) return "Email already exists";
            h1.setDescription("User updated Email from "+user.getEmail()+" to "+obj.getValue());
            user.setEmail(obj.getValue());
        }
        else{
            return "Invalid value";
        }
        historyRepo.save(h1);
        userRepo.save(user);
        return "Update done Successfully";
    }

    @PostMapping("/add/{adminId}")
    public String add(@RequestBody User user,@PathVariable int adminId){
        h1.setDescription("User: "+user.getId()+" Created by "+adminId);
        historyRepo.save(h1);
        userRepo.save(user);
        return "Successfully added";
    }

    @GetMapping("/users")
    public List<User> getallUser(@RequestParam String sortBy,@RequestParam String order){
        Sort sort;
        if(order.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }
        else  {
            sort = Sort.by(sortBy).ascending();
        }
        return userRepo.findAllByRole("customer",sort);
    }

    @GetMapping("/users/{keyword}")
    public List<User> Search(@PathVariable String keyword){
        return userRepo.findByUsernameContainingIgnoreCaseAndRole(keyword,"customer");
    }

    @DeleteMapping("/delete-user/{userId}/admin/{adminId}")
    public String delete(@PathVariable int userId,@PathVariable int adminId){
        User user  = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getBalance()>0){
            return "Balance should be zero";
        }
        h1.setDescription("User :"+userId+" deleted by admin: "+adminId);
        historyRepo.save(h1);
        userRepo.delete(user);
        return "User deleted Successfully";
    }
}



















