package Dunamu.SearchArticles.controller;

import Dunamu.SearchArticles.Model.ArticlesVO;

import java.util.List;

public interface MdFileWriterService {

    String writeFile(String date, List<ArticlesVO> voList);
}
