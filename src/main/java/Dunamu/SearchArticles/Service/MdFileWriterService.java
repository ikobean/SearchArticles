package Dunamu.SearchArticles.Service;

import Dunamu.SearchArticles.Model.ArticlesVO;

import java.util.List;

public interface MdFileWriterService {

    String writeFile(String keyword, String date, List<ArticlesVO> voList);
}
