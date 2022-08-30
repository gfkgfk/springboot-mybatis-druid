package com.test.springboot.mapper.mongodb;

import com.test.springboot.bean.MongoUser;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface MongoUserDao extends MongoRepository<MongoUser, String> {
    /**
     * 根据年龄段来查找
     *
     * @param from from
     * @param to   to
     * @return List<User>
     */
    //MongoUserDao通过继承MongoRepository已经具有了JPA的特性，我们可以通过方法名来构建多查询条件的SQL。比如通过用户的年龄段来查询：(在输入findBy后，IDEA会根据实体对象的属性和SQL的各种关键字自动组合提示??)
    List<MongoUser> findByAgeBetween(Integer from, Integer to);


    /**
     * 通过年龄段，用户名，描述（模糊查询）
     *
     * @param from        from
     * @param to          to
     * @param name        name
     * @param description description
     * @return List<User>
     */
    //方法参数个数需要和方法名中所需要的参数个数对应上。
    List<MongoUser> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer from, Integer to, String name, String description);


}
