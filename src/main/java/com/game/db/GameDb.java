package com.game.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GameDb {
    public static Connection getDbConnection()
    {
        String db_connection_url = "jdbc:mysql://localhost:3306/game_record_db";

        String db_connection_username = "root";
        String db_connection_pass = "srmcem@123";
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(db_connection_url, db_connection_username, db_connection_pass);
            System.out.println("✅ Connected to the database successfully!");

            return conn;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public void insertPlayerData(String player_name,String player_username)
    {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = getDbConnection();
            conn.setAutoCommit(false);


            String query = "INSERT INTO player_info (player_name, player_username)" +
                    " VALUES (?, ?)";;


            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,player_name);
            pstmt.setString(2,player_username);



            int row = pstmt.executeUpdate();

            if(row>0)
            {
                System.out.println("Record Inserted Successfully...");
            }
            else{
                System.out.println("Record Not Saved");
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(conn!=null)
                {
                    conn.commit();
                    conn.close();
                }
                if(pstmt!=null)
                {
                    pstmt.close();
                }
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {


    }
}
