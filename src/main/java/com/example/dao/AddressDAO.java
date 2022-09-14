package com.example.dao;

import com.example.data.Address;
import com.example.util.DbConnection;

import java.sql.*;


public class AddressDAO {

    public int insertIntoAddress(Address address) throws SQLException,ClassNotFoundException

    {
        int rows = 0;

        Connection connection = DbConnection.getConnection();
        String sql = "Insert into address (flat_no,buildingName,Street,city,state,pincode,country ) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement
                (sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,address.getFlatNo());
        preparedStatement.setString(2,address.getBuildingName());
        preparedStatement.setString(3, address.getStreet());
        preparedStatement.setString(4, address.getCity());
        preparedStatement.setString(5, address.getState());
        preparedStatement.setInt(6,address.getPinCode());
        preparedStatement.setString(7,address.getCountry());

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


