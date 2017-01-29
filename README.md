# springLoadDataRedis-MySQL
Example of a simple application using springframework for loading some data in Redis server and MySQL server.

### Building     
    mvn clean dependency:copy-dependencies package

### Executing     
    java -cp target/dependency/*:target/Spring-RedisTemplate-JDBCTemplate.jar aula114.springmvc.SpringRedisJDBCExample

#### Errors

_*_Trying DataAccessException _*_       


     try {
         String sqlDelete = "DELETE FROM contact1 where name=?";
         jdbcTemplate.update(sqlDelete, "Silvia");
     } catch (DataAccessException ex) {
         ex.printStackTrace();
     }

_*_Trying BeanPropertyRowMapper _*_

   List<Contact> listContact = jdbcTemplate.query(sqlSelect, new BeanPropertyRowMapper<Contact>(Contact.class)); 
