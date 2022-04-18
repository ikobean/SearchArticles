package Dunamu.SearchArticles.controller;

import Dunamu.SearchArticles.Model.ArticlesVO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
@Service
public class MdFileWriter implements MdFileWriterService{
    @Override
    public String writeFile(String date, List<ArticlesVO> param){
/*
        ArticlesVO vo = new ArticlesVO();
        List<ArticlesVO> voList = new ArrayList<>();
        vo.setDate("20220418");
        vo.setTitle("test");
        vo.setUrl("test");
        voList.add(vo);
*/
        try {
            //파일객체 생성
            File file = new File("D:\\github\\TIL\\TIL\\Article\\"+date+".md");

            file.createNewFile();
            FileWriter fw = new FileWriter("D:\\github\\TIL\\TIL\\Article\\"+date+".md");

           for (ArticlesVO atc: param){
               fw.write("["+atc.getTitle()+"]("+atc.getUrl()+")"+"  "+"\r\n");

           }

           fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
