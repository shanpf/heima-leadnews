package com.heima.article.test;

import com.heima.article.ArticalJarApplication;
import com.heima.article.service.IAppArticleService;
import com.heima.common.constans.article.ArticleCostans;
import com.heima.model.common.dtos.ResponseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ArticalJarApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {
    @Autowired
    private IAppArticleService iAppArticleService;

    @Test
    public void testArticle(){
        ResponseResult result = iAppArticleService.load(null, ArticleCostans.LOAD_MORE);
        System.out.println(result.getData());
    }
}
