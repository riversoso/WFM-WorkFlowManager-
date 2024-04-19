package projectDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
   // 데이터베이스 연결 정보
   final public static String DB_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
   private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
   private static final String USERNAME = "ADAM";
   private static final String PASSWORD = "1234";

   public void register(card card) { // 출근하기
      delete(card.getId());

      Connection conn = null;
      PreparedStatement ps = null;

      try {

         Class.forName(DB_DRIVER_NAME);
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "INSERT INTO attendance (id,arrivalTime,departureTime) VALUES (?, sysdate,?)";
         ps = conn.prepareStatement(sql);

         ps.setString(1, card.getId());
         ps.setString(2, card.getDepartureTime());
         if (ps.executeUpdate() == 1) {
            System.out.println("정상적으로 출근처리가 되었습니다.");
         } else {
            System.out.println("정상적으로 출근처리가 되지않았습니다.");
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

   public void update(String id) { // 퇴근하기
      delete(id);
      //System.out.println("퇴근~~~~!");
      Connection conn = null;
      int updatedRows = 0;
      PreparedStatement ps = null;// SQL 해석 객체
      try {
         Class.forName(DB_DRIVER_NAME);

         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         //System.out.println("db연결성공");

         String sql = "UPDATE attendance SET DEPARTURETIME = CURRENT_TIMESTAMP WHERE id = ? AND TO_CHAR(ARRIVALTIME, 'YYYY-MM-DD') = TO_CHAR(SYSDATE, 'YYYY-MM-DD')";
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);

         updatedRows = ps.executeUpdate();
         if (updatedRows > 0) {
            System.out.println("정상적으로 퇴근처리가 되었습니다.");
         } else {
            System.out.println("정상적으로 퇴근처리가 되지않았습니다.");
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

   public boolean checkID(String id) {
      Connection conn = null;
      PreparedStatement ps = null;
      int cnt = 0;
      try {
         Class.forName(DB_DRIVER_NAME);
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "SELECT Count(*) as cnt FROM attendance WHERE id=?";
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);

         ResultSet rs = ps.executeQuery();

         while (rs.next()) {
            cnt = rs.getInt("CNT");

         }

         return (cnt > 0) ? true : false;

      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   public static void delete(String id) { // 삭제하기
      Connection conn = null;
      PreparedStatement ps = null;
      try {

         Class.forName(DB_DRIVER_NAME);
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "DELETE FROM attendance WHERE id=? and To_date(ARRIVALTIME, 'yyyy-mm-dd') <> TO_date(SYSDATE, 'yyyy-mm-dd')";
         ps = conn.prepareStatement(sql); // SQL 해석
         ps.setString(1, id);

         int deletedRows = ps.executeUpdate();
         if (deletedRows > 0) {
            System.out.println("회원 정보를 성공적으로 삭제했습니다.");
         } else {
            //System.out.println("회원 정보 삭제에 실패했습니다.");
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

   public void list(String id) { // 출퇴근 시간 조회
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         Class.forName(DB_DRIVER_NAME);
         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         String sql = "SELECT a.id, n.name,  a.arrivalTime, a.departureTime FROM attendance a JOIN namecard n on n.id = a.id WHERE a.id=?";
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);

         ResultSet rs = ps.executeQuery();
         boolean Records = false;
         while (rs.next()) {
            String Id = rs.getString("id");
            String name = rs.getString("name");
            String arrivalTime = rs.getString("arrivalTime");
            String departureTime = rs.getString("departureTime");
            System.out.println("사원 번호: " + Id);
            System.out.println("사원 이름: " + name);
            System.out.println("출근 시간: " + arrivalTime);
            System.out.println("퇴근 시간: " + (departureTime != null ? departureTime : "아직 퇴근하지 않음"));
            Records = true;
         }
         if (!Records) {
            System.out.println("출근 기록이 없습니다.");
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
      }
   }

}