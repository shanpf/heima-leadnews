package com.heima.article.controller.v1;

import com.heima.article.apis.ArticleHomeControllerApi;
import com.heima.article.service.IAppArticleService;
import com.heima.common.constans.article.ArticleCostans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class AritcleHomeController implements ArticleHomeControllerApi {

    @Autowired
    private IAppArticleService appArticleService;
    /**
     * 加载首页文章
     *
     * @param dto
     * @return
     */
    @Override
    @GetMapping("/load")
    public ResponseResult load(ArticleHomeDto dto) {
        return appArticleService.load(dto, ArticleCostans.LOAD_MORE);
    }

    /**
     * 加载更多文章
     *
     * @param dto
     * @return
     */
    @Override
    @GetMapping("/loadmore")
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleCostans.LOAD_MORE);
    }

    /**
     * 加载最新的文章
     *
     * @param dto
     * @return
     */
    @Override
    @GetMapping("/loadew")
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleCostans.LOAD_NEW);
    }
}
