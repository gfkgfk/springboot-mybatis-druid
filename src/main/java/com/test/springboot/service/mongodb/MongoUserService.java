package com.test.springboot.service.mongodb;

import com.test.springboot.bean.MongoUser;
import com.test.springboot.mapper.mongodb.MongoUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Optional;

@Service
public class MongoUserService {
    @Autowired
    private MongoUserDao mongoUserDao;

    public List<MongoUser> getUsers() {
        return this.mongoUserDao.findAll();
    }


    public Optional<MongoUser> getUser(String id) {
        return this.mongoUserDao.findById(id);
    }


    /**
     * 新增和修改都是 save方法，
     * id 存在为修改，id 不存在为新增
     */
    public MongoUser createUser(MongoUser user){
        user.setId(null);
        return this.mongoUserDao.save(user);
    }


    public void deleteUser(String id) {
        this.mongoUserDao.findById(id).ifPresent(mongoUser -> {
            this.mongoUserDao.delete(mongoUser);
        });
    }


    public void updateUser(String id,MongoUser mongoUser) {
        this.mongoUserDao.findById(id).ifPresent(mongoUserUpdate->{
            mongoUserUpdate.setName(mongoUser.getName());
            mongoUserUpdate.setAge(mongoUser.getAge());
            mongoUserUpdate.setDescription(mongoUser.getDescription());
            this.mongoUserDao.save(mongoUserUpdate);
        });
    }


    /**
     * 多条件查询
     * @param from
     * @param to
     * @return
     */
    public List<MongoUser> getUserBetween(int from, int to) {
        return this.mongoUserDao.findByAgeBetween(from, to);
    }


    /**
     * 多条件查询
     * @param from
     * @param to
     * @param name
     * @param description
     * @return
     */
    public List<MongoUser> getUserLike(int from, int to,String name, String description) {
        return this.mongoUserDao.findByAgeBetweenAndNameEqualsAndDescriptionIsLike(from, to,name,description);
    }



    @Autowired
    private MongoTemplate mongoTemplate;

    public Page<MongoUser> getUserByCondition(int size, int page, MongoUser mongoUser) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(mongoUser.getName())) {
            criteria.and("name").is(mongoUser.getName());
        }
        if (!StringUtils.isEmpty(mongoUser.getDescription())) {
            criteria.and("description").regex(mongoUser.getDescription());
        }

        query.addCriteria(criteria);

        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<MongoUser> users = mongoTemplate.find(query.with(pageable), MongoUser.class);
        System.out.println("List<MongoUser> :"+users.toString());
        return PageableExecutionUtils.getPage(users, pageable, () -> mongoTemplate.count(query, MongoUser.class));
    }

}
