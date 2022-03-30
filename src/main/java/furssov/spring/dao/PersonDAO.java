package furssov.spring.dao;

import furssov.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;



    private int ID;
        @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> getPeople(){
       return jdbcTemplate.query("SELECT * FROM users",
               new BeanPropertyRowMapper<>(Person.class));
      }

      public Person getById(int id) {
          return jdbcTemplate.query("SELECT * FROM users WHERE id=?",
                          new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                  .stream().findAny().orElse(null);
      }

      public void save(Person person){
         jdbcTemplate.update("INSERT INTO users (name, email, age) " +
                 "VALUES (?,?,?)",person.getName(),person.getEmail(),person.getAge());
      }

      public void update(int id, Person person){
        jdbcTemplate.update("UPDATE users SET name=?, email=?, age=? WHERE id=?",
                person.getName(),person.getEmail(),person.getAge(), id);
      }
      public void delete(int id){
         jdbcTemplate.update("DELETE FROM users WHERE id=?",id);

      }
}
