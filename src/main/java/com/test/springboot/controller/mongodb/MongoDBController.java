package com.test.springboot.controller.mongodb;

import com.test.springboot.bean.MongoUser;
import com.test.springboot.service.mongodb.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mongouser")
public class MongoDBController {
    @Autowired
    private MongoUserService mongoUserService;

    @GetMapping
    public List<MongoUser> getUsers() {
        return mongoUserService.getUsers();
    }


    @PostMapping
    public MongoUser createUser(MongoUser mongoUser) {
        return mongoUserService.createUser(mongoUser);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        mongoUserService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, MongoUser mongoUser) {
        System.out.println("updateUser id：" + id);
        System.out.println("updateUser mongoUser：" + mongoUser);
        mongoUserService.updateUser(id, mongoUser);
    }

    /**
     * 根据用户 id查找
     * 存在返回，不存在返回 null
     */
    @GetMapping("/{id}")
    public MongoUser getUser(@PathVariable String id) {
        return mongoUserService.getUser(id).orElse(null);
    }


    /**
     * 多条件查询
     */
    @PostMapping("/muti")
    public List<MongoUser> getUserBetween(@RequestParam String from, @RequestParam String to) {
        System.out.println("from:" + from + "   to:" + to);
        return this.mongoUserService.getUserBetween(Integer.valueOf(from), Integer.valueOf(to));
    }

    @PostMapping("/mutilike")
    public List<MongoUser> getUserLike(@RequestParam String from, @RequestParam String to, @RequestParam String name, @RequestParam String description) {
        System.out.println("from:" + from + "   to:" + to+"   name:" + name+"   description:" + description);
        return this.mongoUserService.getUserLike(Integer.valueOf(from), Integer.valueOf(to), name, description);
    }


    /**
     * 分页
     */
    @GetMapping("/condition")
    public Page<MongoUser> getUserByCondition(int size, int page, MongoUser mongoUser) {
        System.out.println("mongoUser:"+mongoUser.toString());
        return this.mongoUserService.getUserByCondition(size, page, mongoUser);
    }

}

