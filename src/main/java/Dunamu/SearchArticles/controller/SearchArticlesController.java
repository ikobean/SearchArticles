package Dunamu.SearchArticles.controller;

import Dunamu.SearchArticles.Model.ArticlesVO;
import Dunamu.SearchArticles.SearchArticlesApplication;
import ch.qos.logback.core.util.DatePatternToRegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Slf4j
//@RequestMapping("/searchArticles")
//@RestController
@Controller
public class SearchArticlesController {
    private WebElement element;

    public MdFileWriter fileWriter = new MdFileWriter();
    //@GetMapping("/search")
    public String searchArticles(String keyword){

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        keyword = "업비트";
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
                    System.out.println(title);
                    vo.setUrl(url);
                    voList.add(vo);
                    System.out.println(voList);

                }

            }
            System.out  .println("today:"+today+"voList"+voList);
            //파일만들기
            fileWriter.writeFile(today, voList);

        } finally {
           //driver.quit();
        }
        return "ok";
    }

    public static void main(String[] args) {
        SearchArticlesController srch = new SearchArticlesController();
        String result = srch.searchArticles("두나무");
        System.out.println("result = " + result);
    }


}
