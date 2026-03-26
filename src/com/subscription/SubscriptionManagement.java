package com.subscription;
import com.subscription.dao.*;
import com.subscription.model.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class SubscriptionManagement {

    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static ServiceDao serviceDAO = new ServiceDao();
    static SubscriptionDao subDAO = new SubscriptionDao();


    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("====== Subscription Management System ======");
            System.out.println("1. User Management");
            System.out.println("2. Service Management");
            System.out.println("3. Subscription Management");
            System.out.println("4. Exit");

            System.out.print("Select Module: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    userMenu();
                    break;

                case 2:
                    serviceMenu();
                    break;

                case 3:
                    subscriptionMenu();
                    break;

                case 4:
                    System.out.println("System Closed.");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 4);
    }

    // USER MENU
    public static void userMenu() {

        int choice;

        do {

            System.out.println("\n------ User Management ------");
            System.out.println("1. Add User");
            System.out.println("2. Get All Users");
            System.out.println("3. Get User By ID");
            System.out.println("4. Update User");
            System.out.println("5. delete User");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    User user = new User(name, email);

                    userDAO.addUser(user);
                    break;

                case 2:
                {
                    List<User> list=userDAO.getallusers();
                    for(User users:list){
                        System.out.println(users);
                    }
                    break;
                }

                case 3:

                    System.out.print("Enter User ID: ");
                    int id = sc.nextInt();

                    User u = userDAO.getUserById(id);

                    if (u != null)
                        System.out.println(u);

                    break;

                case 4:

                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    if(!userDAO.userExists(uid)){
                        System.out.println("User does not exits");
                        break;
                    }
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();

                    userDAO.updateUser(uid, newName, newEmail);

                    break;
                case 5:

                    System.out.println("Enter id of user");
                    int userid=sc.nextInt();
                    userDAO.deleteuser(userid);

                    break;
            }

        } while (choice != 6);
    }

    // SERVICE MENU
    public static void serviceMenu() {

        int choice;

        do {

            System.out.println("\n------ Service Management ------");
            System.out.println("1. Add Service");
            System.out.println("2.Get All Services");
            System.out.println("3. Get Service By ID");

            System.out.println("4. Update Service");

            System.out.println("5. Delete Service");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Service Name: ");
                    String name = sc.next();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    Service service = new Service(name, price);

                    serviceDAO.addservice(service);

                    break;
                case 2:

                    List<Service> list=serviceDAO.getAllservices();
                    for(Service service1:list){
                        System.out.println(service1);
                    }
                    break;

                case 3:

                    System.out.print("Enter Service ID: ");
                    int id = sc.nextInt();

                    Service s = serviceDAO.getServiceById(id);

                    if (s != null)
                        System.out.println(s);

                    break;

                case 4:
                    System.out.println("Enter service id");
                    int service_id=sc.nextInt();
                    if (!serviceDAO.serviceExists(service_id)) {
                        System.out.println("Service does not exist!");
                        break;
                    }
                    System.out.print("Enter Service Name: ");
                    String newname = sc.nextLine();

                    System.out.print("Enter New Price: ");
                    double newprice = sc.nextDouble();

                    Service updatedservice = new Service(service_id,newname,newprice);

                    serviceDAO.updateService(updatedservice);

                    break;



                case 5:

                    System.out.print("Enter Service ID: ");
                    int delId = sc.nextInt();
                    serviceDAO.deleteService(delId);

                    break;

            }

        } while (choice != 6);
    }

    // SUBSCRIPTION MENU
    public static void subscriptionMenu() {

        int choice;

        do {

            System.out.println("\n------ Subscription Management ------");
            System.out.println("1. Add Subscription");
            System.out.println("2. View All Subscriptions");
            System.out.println("3. View Subscriptions By User");
            System.out.println("4. View Expired Subscription");
            System.out.println("5. View Active Subscription");
            System.out.println("6.Clear Expired Subscription Record");
            System.out.println("7. Back");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();

                    if(!userDAO.userExists(userId)){
                        System.out.println("User does not exists");
                        break;
                    }

                    System.out.print("Enter Service ID: ");
                    int serviceId = sc.nextInt();

                    if (!serviceDAO.serviceExists(serviceId)) {
                        System.out.println("Service does not exist!");
                        break;
                    }

                    LocalDate today = LocalDate.now();
                    Date start = Date.valueOf(today);

                    //End date = after 1 month
                    LocalDate nextMonth = today.plusMonths(1);
                    Date end = Date.valueOf(nextMonth);

                    System.out.print("Enter Status: ");
                    String status = sc.next();

                    Subscription sub = new Subscription(userId, serviceId, start, end, status);

                    subDAO.addSubscription(sub);

                    break;

                case 2:

                    List<Subscription> list = subDAO.getAllSubscriptions();

                    for (Subscription s : list)
                        System.out.println(s);

                    break;

                case 3:

                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    if(!userDAO.userExists(uid)){
                        System.out.println("User does not exists");
                        break;
                    }

                    List<Subscription> subs = subDAO.getSubscriptionsByUser(uid);

                    for (Subscription s : subs)
                        System.out.println(s);

                    break;

                case 4:

                   List<Subscription> expired=subDAO.viewexpiredSubscription();
                   for(Subscription ex:expired){
                       System.out.println(ex);
                   }
                   break;

                case 5:

                    List<Subscription> activesub=subDAO.vieweactiveSubscription();
                    for(Subscription active:activesub){
                        System.out.println(active);
                    }
                case 6:
                    subDAO.deleteexpiredSubscription();
            }

        } while (choice != 7);
    }
}