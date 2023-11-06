package com.example.parse_xml1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUploader {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Parse_xml";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";

    public void uploadData(List<AddressObjectDTO> addressObjects) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO address_object (id, object_id, object_guid, change_id, name, type_name, level, oper_type_id, prev_id, next_id, update_date, start_date, end_date, is_actual, is_active) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            int batchSize = 10000;
            int count = 0;

            for (AddressObjectDTO addressObject : addressObjects) {
                statement.setString(1, addressObject.getId());
                statement.setString(2, addressObject.getObjectId());
                statement.setString(3, addressObject.getObjectGuid());
                statement.setString(4, addressObject.getChangeId());
                statement.setString(5, addressObject.getName());
                statement.setString(6, addressObject.getTypeName());
                statement.setString(7, addressObject.getLevel());
                statement.setString(8, addressObject.getOperTypeId());
                statement.setString(9, addressObject.getPrevId());
                statement.setString(10, addressObject.getNextId());
                statement.setString(11, addressObject.getUpdateDate().toString());
                statement.setString(12, addressObject.getStartDate().toString());
                statement.setString(13, addressObject.getEndDate().toString());
                statement.setString(14, addressObject.getIsActual());
                statement.setString(15, addressObject.getIsActive());

                statement.addBatch();

                if (++count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();

            System.out.println("Data uploaded successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
