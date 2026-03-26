package com.subscription.dao;
import com.subscription.util.DBConnection;
import com.subscription.model.Subscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;




public class SubscriptionDao {

    //add subscription
    public boolean addSubscription(Subscription sub) {



        String sql = "INSERT INTO subscriptions (user_id, service_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sub.getUserId());
            ps.setInt(2, sub.getServiceId());
            ps.setDate(3, sub.getStartDate());
            ps.setDate(4, sub.getEndDate());
            ps.setString(5,sub.getStatus());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //get all subscription

    public List<Subscription> getAllSubscriptions() {
        updateSubscriptionStatus();
        List<Subscription> list = new ArrayList<>();
        String sql = "select * FROM subscriptions";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("service_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );

                list.add(sub);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //get by userid
    public List<Subscription> getSubscriptionsByUser(int userId) {
        updateSubscriptionStatus();
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("service_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );
                list.add(sub);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    //update subscription

    public boolean updateSubscriptionStatus() {
        String sql="update subscriptions set status='inactive' where end_date<curDate() and status='active'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Subscription> viewexpiredSubscription(){
        if(!isexpiredexists()){
            System.out.println("Expired Subscriptions not exists!");
            return new ArrayList<>();
        }
        String sql="SELECT * FROM subscriptions WHERE status='inactive'";
        ArrayList<Subscription> list=new ArrayList<>();
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs =ps.executeQuery();
            while(rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("service_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );
                list.add(sub);
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public List<Subscription> vieweactiveSubscription(){
        String sql="SELECT * FROM subscriptions WHERE status='active'";
        ArrayList<Subscription> list=new ArrayList<>();
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs =ps.executeQuery();
            while(rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("service_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );
                list.add(sub);
            }

            return list;
        }catch(SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public boolean deleteexpiredSubscription(){
        if(!isexpiredexists()){
            System.out.println("Expired Subscriptions not exists!");
            return false;
        }
        String sql="delete from subscriptions where status='inactive'";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Expired Subscriptions cleared successfully!");
                return true;
            }else return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isexpiredexists(){
        String sql="select *from subscriptions where status='inactive'";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }else return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }








}
