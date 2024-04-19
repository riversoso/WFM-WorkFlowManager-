package projectDAO;

import java.sql.Date;

public class card {
   private String id;
   private String name;
   private String department; 
   private String position;
   private String loc;
   private String mobile;
   private String email;
   private String arrivalTime;
   private String departureTime;

   public card() {}

   // 매개변수 생성자
   public card(String id, String name, String department, String position, String loc, String mobile, String email) {
      this.id = id;
      this.name = name;
      this.department = department;
      this.position = position;
      this.loc = loc;
      this.mobile = mobile;
      this.email = email;

   }


   public card(String id) {
      this.id = id;

   }

   // getter/setter
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDepartment() {
      return department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }

   public String getPosition() {
      return position;
   }

   public void setPosition(String position) {
      this.position = position;
   }

   public String getLoc() {
      return loc;
   }

   public void setLoc(String loc) {
      this.loc = loc;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getArrivalTime() {
      return arrivalTime;
   }

   public void setArrivalTime(String arrivalTime) {
      this.arrivalTime = arrivalTime;
   }

   public String getDepartureTime() {
      return departureTime;
   }

   public void setDepartureTime(String departureTime) {
      this.departureTime = departureTime;
   }

}