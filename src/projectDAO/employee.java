package projectDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class employee {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      EmployeeDAO employeeDAO = new EmployeeDAO();

      while (true) {
         System.out.println("----------------------------------------");
         System.out.println("1. 출근 기록 등록");
         System.out.println("2. 퇴근 기록 등록");
         System.out.println("3. 출퇴근 시간 조회");
         System.out.println("4. 첫 화면으로 돌아가기");
         System.out.println("----------------------------------------");
         System.out.print("메뉴를 선택하세요: ");

         int menu = sc.nextInt();
         sc.nextLine();

         switch (menu) {
         case 1:
            System.out.print("사원 번호를 입력하세요: ");
            String id = sc.nextLine();
            card card = new card(id);
            employeeDAO.register(card);
            break;
         case 2:
            System.out.print("사원 번호를 입력하세요: ");
            String id2 = sc.nextLine();
            if(employeeDAO.checkID(id2)) {
            while (true) {
               System.out.print("정말 퇴근하시겠습니까?(y/n)\n");
               String answer = sc.nextLine();
               if (answer.equalsIgnoreCase("y")) {
                  employeeDAO.update(id2);
                  break;
               } else if (answer.equalsIgnoreCase("n")) {
                  System.out.println("퇴근 처리를 취소합니다.");
                  break;
               } else {
                  System.out.println("y나 n 중에서 선택해주세요.");
               }
            }
            } else {
               System.out.println("사원 정보가 없습니다.");

            }

            break;
         case 3:
            System.out.println("사원 번호를 입력하세요: ");
            String id3 = sc.nextLine();
            employeeDAO.list(id3);
            break;
         case 4:
            System.out.println("첫 화면으로 돌아갑니다.");
            return;// employee 클래스의 main 메소드 종료하여 Main 클래스의 루프로 복귀

         default:
            System.out.println("잘못된 메뉴를 선택했습니다.");
         }
      }
   }
}