package com.test.springboot.datasource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.bean.RedisBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Configuration
@EnableCaching //启用cache缓存
public class CacheConfig {


    /**
     *  自定义 KeyGenerator
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return (Object target, Method method, Object... params)-> {
            StringBuffer sb= new StringBuffer();
            sb.append(method.getName() + "-");
            for (Object param : params) {
                if(param instanceof RedisBean){
                    System.out.println("参数转String");
                    sb.append(((RedisBean) param).getId()+"_");
                }else {
                    System.out.println("String类型");
                    sb.append(param.toString()).append("_");
                }
            }
            return sb.toString();
        };
    }



//    @Bean
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        System.out.println("序列化方式");
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = serializer();
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        // key采用String的序列化方式
//        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
//        // value序列化方式采用jackson
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
//        // hash的key也采用String的序列化方式
//        redisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
//        //hash的value序列化方式采用jackson
//        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        return redisTemplate;
//    }


    /**
     * 自定义配置 RedisTemplate
     * @Primary注解 默认加载此配置 忽略 RedisAutoConfiguration 的 stringRedisTemplate 配置
     * @param factory
     * @return
     */
    @Bean
    @Primary
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 我们为了自己开发方便，一般直接使用 <String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        // Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    /**
     * 此方法不能用@Ben注解，避免替换Spring容器中的同类型对象
     */
    public GenericJackson2JsonRedisSerializer serializer() {
        return new GenericJackson2JsonRedisSerializer();
    }






    @Bean
    RedisCacheWriter writer(RedisTemplate<String, Object> redisTemplate) {
        return RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
    }


    @Bean
    CacheManager cacheManager(RedisCacheWriter writer) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))//time
                 .computePrefixWith(cacheName -> cacheName+":") //变双冒号为单冒号
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        //此处可以自定义缓存空间的缓存的过期时间，可以根据自己实际情况进行设置，也可以不设置，用统一过期时间
        configurationMap.put("test", config.entryTtl(Duration.ofSeconds(200)));

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        return RedisCacheManager.builder(writer)
                .initialCacheNames(configurationMap.keySet())
                .withInitialCacheConfigurations(configurationMap)
                //其他缓存空间缓存过期时间为500S
                .cacheDefaults(config.entryTtl(Duration.ofSeconds(500)))
                .build();
    }
}
