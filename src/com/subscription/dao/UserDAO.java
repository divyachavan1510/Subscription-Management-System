package com.subscription.dao;
import com.subscription.model.User;
import com.subscription.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class UserDAO {

        //add user
        public boolean addUser(User user) {
            String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                int rows = ps.executeUpdate();
                if (rows > 0) System.out.println("User added successfully!");
                return rows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Check if user exists (used for subscription creation)
        public boolean userExists(int userId) {
            String sql = "SELECT user_id FROM users WHERE user_id=?";
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                return rs.next(); // true if exists

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        //user by id
        public User getUserById(int userId) {

            String sql = "SELECT user_id, name, email FROM users WHERE user_id=?";

            if(!userExists(userId)){
                System.out.println("User does not exists");
                return null;
            }

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));

                    return user;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        //update

        public boolean updateUser(int userId, String name, String email) {

            String sql = "UPDATE users SET name=?, email=? WHERE user_id=?";
            if(!userExists(userId)){
                System.out.println("User does not exists");
                return false;
            }


            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setInt(3, userId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("User updated successfully!");
                }

                return rows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

    }

    //delete user
        public boolean deleteuser(int userid){
            String sql="delete from users where user_id=?";
            if(!userExists(userid)){
                System.out.println("User does not exists");
                return false;
            }
            try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){

                ps.setInt(1,userid);
                int rows = ps.executeUpdate();
                if(rows>0){
                    System.out.println("User deleted successfully!");
                }
                return rows>0;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                return false;
            }

        }

        //get all users
        public List<User> getallusers(){
            String sql="select * from users";
            List<User> list=new ArrayList<>();
            try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

              while(rs.next()) {

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));

                  list.add(user);
                }
              return list;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                return new ArrayList<>();
            }
        }
}
