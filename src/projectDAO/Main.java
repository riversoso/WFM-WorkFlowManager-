package projectDAO;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      while (true) {

         System.out.println("=============권한을 선택해주세요=============");
         System.out.println("1. 직원");
         System.out.println("2. 관리자");
         System.out.println("3. 종료");
         System.out.println("=======================================");
         System.out.print("선택: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); // 개행 문자 소비

         switch (choice) {
         case 1:
            System.out.println("직원용 프로그램을 실행합니다.");
            employee.main(args);
            break;
         case 2:
            System.out.print("비밀번호를 입력하세요: ");
            String password = scanner.nextLine();
            if (password.equals("admin123")) {
               System.out.println("관리자용 프로그램을 실행합니다.");
               manager.main(args);
            } else {
               System.out.println("비밀번호가 틀렸습니다.");
               System.out.println("첫 화면으로 돌아갑니다.");
            }
            break;
         case 3:
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
            break;
         default:
            System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
         }
      }

   }
}
