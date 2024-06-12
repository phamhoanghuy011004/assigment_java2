package assigmnet;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {
    private static final VnexpressArticleService vnexpressService = new VnexpressArticleService();
    private static final MyArticleService myArticleService = new MyArticleService();
    private static final MySqlArticleRepository repository = new MySqlArticleRepository();

    public void crawlFromVnexpress () {
        String url = "https://vnexpress.net/";
        ArrayList<String> links = vnexpressService.getLinks(url);
        for (String link : links) {
            Article article = vnexpressService.getArticle(link);
            if (article != null) {
                repository.save(article);
            }
        }
        System.out.println("Đã lấy thông tin từ Vnexpress.");
    }

    public void crawlFromMySource() {
        String url = "https://your-custom-news-source.com/";
        ArrayList<String> links = myArticleService.getLinks(url);
        for (String link : links) {
            Article article = myArticleService.getArticle(link);
            if (article != null) {
                repository.save(article);
            }
        }
        System.out.println("Đã lấy thông tin từ nguồn của bạn.");
    }

     public void displayArticles() {
        ArrayList<Article> articles = repository.findAll();
        if (articles.isEmpty()) {
            System.out.println("Không có tin tức nào.");
            return;
        }

        for (Article article : articles) {
            System.out.println("ID: " + article.getId() + ", baseUrl: " + article.getBaseUrl() + ", Title: " + article.getTitle());
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Nhập mã tin cần xem chi tiết hoặc 'exit' để thoát: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                return;
            }

            try {
                Long id = Long.parseLong(input);
                Article article = articles.stream()
                        .filter(a -> a.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                if (article != null) {
                    displayArticleDetails(article);
                } else {
                    System.out.println("Mã tin không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Mã tin không hợp lệ.");
            }
        }
    }

    public void displayArticleDetails(Article article) {
        System.out.println("Title: " + article.getTitle());
        System.out.println("Description: " + article.getDescription());
        System.out.println("Content: " + article.getContent());
        System.out.println("Thumbnail: " + article.getThumbnail());
        System.out.println("Created At: " + article.getCreatedAt());
        System.out.println("Updated At: " + article.getUpdatedAt());
        System.out.println("Status: " + article.getStatus());
        System.out.println("-------------");
    }
}
