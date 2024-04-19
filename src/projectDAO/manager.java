package projectDAO;

import java.util.Scanner;

public class manager {
   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      namecardDAO dao = new namecardDAO();

      while (true) {
         System.out.println("-------------관리자용 프로그램---------------");
         System.out.println("1. 직원정보 조회");
         System.out.println("2. 직원정보 등록");
         System.out.println("3. 직원정보 수정");
         System.out.println("4. 직원정보 삭제");
         System.out.println("5. 직원 출퇴근 조회");
         System.out.println("6. 첫 화면으로 돌아가기");
         System.out.println("----------------------------------------");
         System.out.print("메뉴를 입력하세요 : ");
         int menu = sc.nextInt();
         sc.nextLine();

         if (menu == 6) {
            System.out.println("첫 화면으로 돌아갑니다.");
            break;
         } else if (menu == 1) {
            dao.listCard();
         } else if (menu == 2) {

            System.out.print("사원번호를 입력하세요: ");
            String id = sc.nextLine();
            System.out.print("이름을 입력하세요: ");
            String name = sc.nextLine();
            System.out.print("부서를 입력하세요: ");
            String department = sc.nextLine();
            System.out.print("직책을 입력하세요: ");
            String position = sc.nextLine();
            System.out.print("주소를 입력하세요: ");
            String loc = sc.nextLine();
            System.out.print("전화번호를 입력하세요: ");
            String mobile = sc.nextLine();
            System.out.print("이메일을 입력하세요: ");
            String email = sc.nextLine();
            card card = new card(id, name, department, position, loc, mobile, email);
            dao.register(card);
         } else if (menu == 3) {

            System.out.print("수정할 사원번호: ");
            String id = sc.nextLine();
            System.out.print("이름을 입력하세요: ");
            String name = sc.nextLine();
            System.out.print("부서를 입력하세요: ");
            String dep = sc.nextLine();
            System.out.print("직책을 입력하세요: ");
            String pos = sc.nextLine();
            System.out.print("주소를 입력하세요: ");
            String loc = sc.nextLine();
            System.out.print("전화번호를 입력하세요: ");
            String mob = sc.nextLine();
            System.out.print("이메일을 입력하세요: ");
            String email = sc.nextLine();
            dao.update(id, name, dep, pos, loc, mob, email);
            System.out.println("직원정보가 수정되었습니다.");
         } else if (menu == 4) {

            System.out.print("삭제할 사원번호를 입력하세요: ");
            String id = sc.nextLine();
            dao.delete(id);
            System.out.println("직원정보가 삭제되었습니다.");

         } else if (menu == 5) {
            dao.attendance();

         } else {
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }

   }

}