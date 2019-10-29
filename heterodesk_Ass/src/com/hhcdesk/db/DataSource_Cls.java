package com.hhcdesk.db;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DataSource_Cls {

    private static DataSource_Cls     datasource;
    private BoneCP connectionPool;

    public DataSource_Cls() throws IOException, SQLException, PropertyVetoException {
        try {
            // load the database driver (make sure this is in your classpath!)
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            // setup the connection pool using BoneCP Configuration
            BoneCPConfig config = new BoneCPConfig();
            // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
            config.setJdbcUrl("jdbc:mysql://192.168.30.223:3306/");
            config.setUsername("hcluser");
            config.setPassword("test!@#");
            config.setMinConnectionsPerPartition(1);
            config.setMaxConnectionsPerPartition(2);
            config.setPartitionCount(1);
            // setup the connection pool
            connectionPool = new BoneCP(config);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public static DataSource_Cls getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource_Cls();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }

}