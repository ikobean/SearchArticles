package Dunamu.SearchArticles.Service;

import Dunamu.SearchArticles.Model.ArticlesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class MdFileWriterServiceImpl implements MdFileWriterService {
    @Override
    public String writeFile(String keyword, String date, List<ArticlesVO> param) {
        try {
            //파일객체 생성
            File file = new File("D:\\github\\TIL\\TIL\\Article\\" + date + keyword + ".md");

            file.createNewFile();
            FileWriter fw = new FileWriter("D:\\github\\TIL\\TIL\\Article\\" + date + keyword + ".md");

            for (ArticlesVO atc : param) {
                fw.write("[" + atc.getTitle() + "](" + atc.getUrl() + ")" + "  " + "\r\n");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
