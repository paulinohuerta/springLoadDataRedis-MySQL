package aula114.springmvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.dao.DataAccessException;

import aula114.springmvc.domain.Contact;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRedisJDBCExample {

	public static void main(String[] args) throws SQLException {
             ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:META-INF/spring/applicationContext.xml");
             RedisTemplate<String, String>  redisTemplate = ctx.getBean("redisTemplate",RedisTemplate.class);
    
             String KEY = "Contact";
             
             List<Contact> list_contact = new ArrayList<Contact>();
             list_contact.add(new Contact("juan","juan@domain.com","Ave María 23, 3A","925695632"));
             list_contact.add(new Contact("silvia","silvia@domain.com","Ave María 25, 3H","966595547"));
             list_contact.add(new Contact("alejandro","alejandro@domain.com","Ave María 23, 2F","925695927"));
             list_contact.add(new Contact("carmen","carment@domain.com","Ave María 47, Primero","933889003"));
             list_contact.add(new Contact("javier","javier@domain.com","Ave María 11, 1H","954528830"));
             int incr = 0;
             for (Contact a_contact : list_contact) {
                incr++;
                redisTemplate.opsForHash().put(KEY,String.valueOf(incr), a_contact);
             }
             
             Set<Object> set =  redisTemplate.opsForHash().keys(KEY);

	     SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
       	     dataSource.setDriver(new com.mysql.jdbc.Driver());
	     dataSource.setUrl("jdbc:mysql://localhost/db1");
	     dataSource.setUsername("root");
	     dataSource.setPassword("root");
		
	     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	     String sqlInsert = "INSERT INTO contact (name, email, address, telephone)"
				+ " VALUES (?, ?, ?, ?)";
    
	     for(Object ob : set) {	
                   Contact c1 = (Contact) redisTemplate.opsForHash().get(KEY,ob.toString());
		   jdbcTemplate.update(sqlInsert, c1.getName(), c1.getEmail(), c1.getAddress(), c1.getTelephone());
             }

	     String sqlSelect = "SELECT * FROM contact";
	     List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {
			public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
				Contact contact = new Contact();
				contact.setName(result.getString("name"));
				contact.setEmail(result.getString("email"));
				contact.setAddress(result.getString("address"));
				contact.setTelephone(result.getString("telephone"));
				
				return contact;
			}
		});
		
		for (Contact aContact : listContact) {
			System.out.println(aContact);
		}
                
                try {
                   String sqlDelete = "DELETE FROM contact1 where name=?";
                   jdbcTemplate.update(sqlDelete, "Silvia");
                } catch (DataAccessException ex) {
                    ex.printStackTrace();
                }
	}
}
