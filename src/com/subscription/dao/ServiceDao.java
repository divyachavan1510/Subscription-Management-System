package com.subscription.dao;
import com.subscription.model.Service;
import com.subscription.util.DBConnection;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {

    //service exists
    public boolean serviceExists(int serviceId) {

        String sql = "SELECT id FROM services WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, serviceId);

            ResultSet rs = ps.executeQuery();

            return rs.next();   //true if record exists

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //get details

    public Service getServiceById(int serviceId) {

        String sql = "SELECT id, service_name, price FROM services WHERE id=?";
        if (!serviceExists(serviceId)) {
            System.out.println("Service does not exist!");
            return null;
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, serviceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {   // moves cursor to first row

                Service service = new Service();

                service.setServiceId(rs.getInt("id"));
                service.setServiceName(rs.getString("service_name"));
                service.setPrice(rs.getDouble("price"));

                return service;
            } else {
                System.out.println("Service not found!");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //get all services
    public List<Service> getAllservices() {
        List<Service> list=new ArrayList<>();
        String sql = "SELECT * FROM services";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

           while(rs.next()) {   // moves cursor to first row

                Service service = new Service();

                service.setServiceId(rs.getInt("id"));
                service.setServiceName(rs.getString("service_name"));
                service.setPrice(rs.getDouble("price"));

                list.add(service);
            }
           if(list.isEmpty()){
                   System.out.println("Services not found!");
           }
           return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    //add service
    public boolean addservice(Service service){
        String sql="Insert into services(service_name,price) values(?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, service.getServiceName());
            ps.setDouble(2, service.getPrice());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("service added successfully!");
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("error occurred during adding");
            e.getMessage();
            return false;
        }
    }

    //update service name
    public boolean updateService(Service service) {

        String sql = "UPDATE services SET service_name=? where id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, service.getServiceName());
            ps.setInt(2, service.getServiceId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Service updated successfully!");
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    //delete service;
    public boolean deleteService(int serviceId) {

        if (!serviceExists(serviceId)) {
            System.out.println("Service does not exist!");
            return false;
        }

        String sql = "DELETE FROM services WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, serviceId);

            int rows = ps.executeUpdate();
            if(rows>0){
                System.out.println("Service Deleted Successfully");
                return true;
            }else return false;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                System.out.println("Service cannot be deleted because it is linked to subscriptions.");
            }else{
                System.out.println("Try Again");
            }
            return false;
        }
    }



}
