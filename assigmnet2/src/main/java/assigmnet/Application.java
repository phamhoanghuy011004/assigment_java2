package assigmnet;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {



    public static void main(String[] args) {
        ArticleController articleController = new ArticleController();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Crawl thông tin từ Vnexpress");
            System.out.println("2. Crawl thông tin từ nguồn của tôi");
            System.out.println("3. Hiển thị danh sách tin hiện có");
            System.out.println("4. Thoát chương trình");
            System.out.print("Chọn một tùy chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    articleController.crawlFromVnexpress();
                    break;
                case 2:
                    articleController.crawlFromMySource();
                    break;
                case 3:
                    articleController.displayArticles();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
