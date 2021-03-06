package Dunamu.SearchArticles.controller;

import Dunamu.SearchArticles.Model.ArticlesVO;
import Dunamu.SearchArticles.Service.MdFileWriterServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class MdFileWriterTest {
    @Autowired
    MdFileWriterServiceImpl md = new MdFileWriterServiceImpl();

    @Test
    public void fileMakeTest(){
        ArticlesVO vo = new ArticlesVO();
        List<ArticlesVO> voList = new ArrayList<>();
        vo.setDate("20220418");
        vo.setTitle("test");
        vo.setUrl("test");
        voList.add(vo);


        String result = md.writeFile("블록체인", "20220418", voList);
        Assertions.assertThat(result).isEqualTo("ok");
    }
}