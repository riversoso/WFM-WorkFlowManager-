
package projectDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class namecardDAO {
   // 데이터베이스 연결 정보
   private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
   private static final String USERNAME = "ADAM";
   private static final String PASSWORD = "1234";

   public void listCard() { // 목록보기
      Connection conn = null;
      try {

         Class.forName("oracle.jdbc.driver.OracleDriver");

         String sql = "SELECT * FROM namecard ORDER BY ID";

         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql);
         /* data 읽어오기 */
         while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String dep = rs.getString("dep");
            String pos = rs.getString("pos");
            String loc = rs.getString("loc");
            String mob = rs.getString("mob");
            String email = rs.getString("email");

            System.out.printf("%-8s%-8s%-8s%-8s%-8s%-18s%-20s\n", id, name, dep, pos, loc, mob, email);
         }

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {

      }

   }

   public void register(card card) {// 등록
      Connection conn = null;
      PreparedStatement ps = null;

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");

         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "INSERT INTO namecard (id, name,dep,pos,loc,mob,email) VALUES (?, ?, ?, ?, ?, ?, ?)";
         ps = conn.prepareStatement(sql);

         ps.setString(1, card.getId());
         ps.setString(2, card.getName());
         ps.setString(3, card.getDepartment());
         ps.setString(4, card.getPosition());
         ps.setString(5, card.getLoc());
         ps.setString(6, card.getMobile());
         ps.setString(7, card.getEmail());

         if (ps.executeUpdate() == 1) {
            System.out.println("성공적으로 회원 정보를 저장하였습니다.");
         } else {
            System.out.println("회원정보 저장에 실패 했습니다.");
         }

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (ps != null) {
            try {
               ps.close();
            } catch (Exception ex) {
            }
         }
         if (conn != null) {
            try {
               ps.close();
            } catch (Exception ex) {
            }
         }
      }

   }

   public void update(String id, String name, String dep, String pos, String loc, String mob, String email) { // 수정하기
      Connection conn = null;
      PreparedStatement ps = null;// SQL 해석 객체
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "UPDATE namecard SET name=?, dep=?, pos=?, loc=?, mob=?, email=? WHERE id=?";
         ps = conn.prepareStatement(sql); // SQL 해석

         ps.setString(1, name);
         ps.setString(2, dep);
         ps.setString(3, pos);
         ps.setString(4, loc);
         ps.setString(5, mob);
         ps.setString(6, email);
         ps.setString(7, id);

         int updatedRows = ps.executeUpdate();
         if (updatedRows > 0) {
            System.out.println("회원 정보를 성공적으로 수정했습니다.");
         } else {
            System.out.println("회원 정보 수정에 실패했습니다.");
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }

   public void delete(String id) { // 삭제하기
      Connection conn = null;
      PreparedStatement ps = null;// SQL 해석 객체
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "DELETE FROM namecard WHERE id=?";
         ps = conn.prepareStatement(sql); // SQL 해석
         ps.setString(1, id);

         int deletedRows = ps.executeUpdate();
         if (deletedRows > 0) {
            System.out.println("회원 정보를 성공적으로 삭제했습니다.");
         } else {
            System.out.println("회원 정보 삭제에 실패했습니다.");
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }

   public void attendance() { // 목록보기
      Connection conn = null;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");

         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "SELECT n.id,n.name,n.dep,n.pos,a.ARRIVALTIME,a.DEPARTURETIME FROM attendance a RIGHT OUTER JOIN namecard n ON a.ID = n.ID ORDER BY ID";
//TO_CHAR(a.ARRIVALTIME,'yyyy-mm-dd hh24:mi:ss') as ARRIVALTIME

         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql);

         while (rs.next()) {
            String id = rs.getString("ID");
            String name = rs.getString("NAME");
            String dep = rs.getString("DEP");
            String pos = rs.getString("POS");
            String arrivalTime = rs.getString("ARRIVALTIME");

            String departureTime = rs.getString("DEPARTURETIME");

            System.out.printf("%-10s%-10s%-10s%-10s%s\t%s\n", id, name, dep, pos, arrivalTime, departureTime);
         }

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {

      }

   }
}