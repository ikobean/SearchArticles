package Dunamu.SearchArticles.controller;

import Dunamu.SearchArticles.Model.ArticlesVO;
import Dunamu.SearchArticles.Service.MdFileWriterService;
import Dunamu.SearchArticles.Service.MdFileWriterServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SearchArticlesController {

    private final MdFileWriterService mdFileWriterService;

    private WebElement element;

    public void searchArticles(String keyword) {

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);

        try {
            // 매일 경제 페이지
            driver.get("https://find.mk.co.kr/new/search.php");
            driver.findElement(By.xpath("/html/body/center/div[1]/form/ul/li[2]")).click();

            element = driver.findElement(By.id("s_keyword"));
            element.sendKeys(keyword);

            driver.findElement(By.className("search_img"));
            element.submit();

            String today = LocalDate.now().toString();
            String[] date = today.split("-");

            List<WebElement> titleList = driver.findElements(By.className("sub_list"));


            List<ArticlesVO> voList = new ArrayList<>();

            for (WebElement ele : titleList){
                String time =  ele.findElement(By.className("art_time")).getText();
                boolean year = time.contains(date[0] + "년");
                boolean month = time.contains(date[1] + "월");
                boolean day = time.contains(date[2]+"일");
                if(!(year && month && day)) continue;
                if(year && month && day){
                    ArticlesVO vo = new ArticlesVO();

                    String url = ele.findElement(By.className("art_tit")).findElement(By.tagName("a")).getAttribute("href");
                    String title = ele.findElement(By.className("art_tit")).findElement(By.tagName("a")).getText();

                    vo.setDate(today);
                    vo.setTitle(title);
                    vo.setUrl(url);

                    voList.add(vo);
                }
            }
            //파일만들기
            mdFileWriterService.writeFile(today, voList);

            log.info(keyword, ":: ", today, " ", voList.size(), "건 생성 완료");

        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {

    }

}
