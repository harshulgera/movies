package com.sapient.controller;

import com.sapient.dao.UserDao;
import com.sapient.entity.User;
import com.sapient.service.UserService;
import com.sapient.utils.JwtUtil;
import com.sapient.vob.ReviewVob;
import com.sapient.vob.UserLoginVob;
import com.sapient.vob.UserVob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    UserDao dao;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<Object> handleLoginPost(@RequestBody UserLoginVob userLoginVob) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.login(userLoginVob.getEmail(), userLoginVob.getPassword());
            map.put("status", 200);
            map.put("id", user.getUserId());
            map.put("name", user.getName());
            map.put("token", jwtUtil.createToken(user.getUserId(), user.getName(), user.getEmail()));

            return ResponseEntity.ok(map);

        }
        catch(Exception ex){
            map.put("status", 401);
            map.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> handleVerifyGet(@RequestParam String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> payload = jwtUtil.verify(token);
            map.put("status", 200);
            map.put("payload", payload);
            return ResponseEntity.ok(map);
        }
        catch(Exception ex){
            map.put("status", 401);
            map.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getByUserId(@RequestParam String token, @PathVariable Integer id) {

        Map<String, Object> map = new HashMap<>();
        try {
            ResponseEntity<Object> response = handleVerifyGet(token);
            Map<String, Object> payload = jwtUtil.verify(token);
            if (payload.containsKey("id") && payload.get("id") == id) {
                UserVob userVob = userService.getUserById(id);
                return ResponseEntity.ok(userVob);
            }
            else
            {
                map.put("status", 401);
                map.put("message", "You are Unauthorized to view this Content!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
            }
        }
        catch(Exception ex){
            map.put("status", 401);
            map.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }

    }

    @GetMapping({"/movie/{id}"})
    public ResponseEntity<Object> getByMovieId(@PathVariable Integer id) {
        List<ReviewVob> reviewVob= userService.getReviewsByMovieId(id);
        return ResponseEntity.ok(reviewVob);
    }




    @GetMapping
    public Iterable<User> getAll(){
        return dao.findAll();
    }


}
