package com.example.dao;

import com.example.data.Address;
import com.example.data.Student;
import com.example.util.DbConnection;

import java.sql.*;


public class StudentDAO {
    public int insertIntoStudent(Student student) throws SQLException,ClassNotFoundException{
        int rows = 0;


        Connection connection = DbConnection.getConnection();
         String sql = "Insert into class_tbl (name, rollNo,email,address,standard) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement
                (sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setInt(2,student.getRollNo());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setObject(4, student.getAddress());

        preparedStatement.setObject(5,student.getStandard());
        rows = preparedStatement.executeUpdate();
        int generatedId = 0;
        if(rows == 1){
            //get generated id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
            }
        }
        return generatedId;
    }
}
