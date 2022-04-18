package Dunamu.SearchArticles.controller;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
class SearchKeywordTest {
    @Test
    public void searchTest(){
        //given
        SearchArticlesController srch = new SearchArticlesController();

        //when
        String result = srch.searchArticles("두나무");

        //then
        assertThat(result).isEqualTo("ok");

    }
}
