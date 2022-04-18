package Dunamu.SearchArticles.controller;

import Dunamu.SearchArticles.Model.ArticlesVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MdFileWriterTest {
    @Autowired
    MdFileWriter md = new MdFileWriter();

    @Test
    public void fileMakeTest(){
        ArticlesVO vo = new ArticlesVO();
        List<ArticlesVO> voList = new ArrayList<>();
        vo.setDate("20220418");
        vo.setTitle("test");
        vo.setUrl("test");
        voList.add(vo);


        String result = md.writeFile("20220418", voList);
        Assertions.assertThat(result).isEqualTo("ok");
    }
}