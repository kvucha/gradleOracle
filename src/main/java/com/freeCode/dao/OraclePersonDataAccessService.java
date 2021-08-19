package com.freeCode.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.freeCode.model.Person;

@Repository
@Qualifier("oracleDao")
public class OraclePersonDataAccessService implements PersonDao {
	
  private final JdbcTemplate jdbcTemplate;
  
  @Autowired
  public OraclePersonDataAccessService(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
  }
	
   private static List<Person> DB = new ArrayList<>();
   
   
	@Override
	public void insertPerson(Person person) {
		
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
		    CallableStatement callableStatement = connection.prepareCall("{call INSRT_PERSON(?, ?, ?,?)}");
			callableStatement.setInt(1, person.getId());
			callableStatement.setString(2, person.getName());
			callableStatement.setString(3, person.getCourse());
			//callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.executeUpdate();
			System.out.println("Value of return value is " + callableStatement.getInt(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
        //        .withProcedureName("READ_EMPLOYEE");
		
		
		//DB.add(person);
	}

	@Override
	public List<Person> getAllPersons() {
		final String sql = "SELECT ID,NAME,COURSE FROM PERSON";
		
	    return jdbcTemplate.query(
	            sql,
	            (rs, rowNum) ->
	                    new Person(
	                            rs.getInt("id"),
	                            rs.getString("name"),
	                            rs.getString("course")
	                    )
	    );
	    


	}

	@Override
	public void updatePerson(Person person) {
		int outval = 0;
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("UPDT_PERSON");

				Map<String, Object> inParamMap = new HashMap<String, Object>();
				inParamMap.put("PID", person.getId());
				inParamMap.put("PNAME", person.getName());
				inParamMap.put("PCOURSE", person.getCourse());
				inParamMap.put("PSTATUS", outval);
				SqlParameterSource in = new MapSqlParameterSource(inParamMap);


				Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
				System.out.println(simpleJdbcCallResult);
		
		
		/*
        for (int i = 0; i < DB.size(); i++) {
			if (DB.get(i).getId() == person.getId()) {
				 DB.get(i).setCourse( person.getCourse());
				 DB.get(i).setName( person.getName());
				 break;
			}
        }
	  */	
	}

	@Override
	public void removePersonByID(int id) {
		 System.out.println(" DELETE METHOD CALLED");
		 String deleteSql = "DELETE FROM PERSON WHERE ID = ?";
		 int rows = jdbcTemplate.update(deleteSql, new Object[]{id});
		 System.out.println(rows + " row(s) deleted.");
		/*
		   System.out.println("Hello the id outer is  : " +id  + " " +DB.size());
	       for (int i = 0; i < DB.size(); i++) {
	    	   System.out.println("Hello inner i 001  : " +i);
				if (DB.get(i).getId() == id) {
					  System.out.println("Hello inner i 002  : " +i);
					  DB.remove(i);
				}
	        }
	        
	        */
		
	}


	
	@Override
	public Person getPersonByID(int id) {
		String sql = "SELECT ID,NAME,COURSE FROM PERSON WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new PersonMapper());
	}
	
	
	
}
