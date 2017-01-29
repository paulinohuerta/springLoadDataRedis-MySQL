# springLoadDataRedis-MySQL
Example of a simple application using springframework for loading some data in Redis server and MySQL server.

### Create database and table contact

_**Script in folder sql**_
_**Init Redis server**_

### Building     
    mvn clean dependency:copy-dependencies package

### Executing     
    java -cp target/dependency/*:target/Spring-RedisTemplate-JDBCTemplate.jar aula114.springmvc.SpringRedisJDBCExample

#### Errors. Handling Exceptions with DataAccessException

_**Trying DataAccessException:**_        


     try {
         String sqlDelete = "DELETE FROM contact1 where name=?";
         jdbcTemplate.update(sqlDelete, "Silvia");
     } catch (DataAccessException ex) {
         ex.printStackTrace();
     }

_**Trying BeanPropertyRowMapper:**_

    List<Contact> listContact = jdbcTemplate.query(sqlSelect, new BeanPropertyRowMapper<Contact>(Contact.class)); 
