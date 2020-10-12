package com.heima.article.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.heima.article.service.IAppArticleService;
import com.heima.common.constans.article.ArticleCostans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author shanpf
 */
@Service
public class AppArticleServiceImpl implements IAppArticleService {
    @Autowired
    private ApArticleMapper apArticleMapper;
    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    /**
     * 加载文章总参数
     *
     * @param dto
     * @param type
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        // 定义文章列表
        List<ApArticle> articleList;

        // 参数校验
        if (null == dto) {
            dto = new ArticleHomeDto();
        }
        // 时间赋值
        // 最大时间
        dto.setMaxBehotTime(null == dto.getMaxBehotTime() ? new Date() : dto.getMaxBehotTime());
        // 最小时间
        dto.setMinBehotTime(null == dto.getMinBehotTime() ? new Date() : dto.getMinBehotTime());

        // 分页参数
        // 判空
        Integer size = (null == dto.getSize() || dto.getSize() <= 0) ? ArticleCostans.PAGE_SIZE : dto.getSize();
        // 如果大于50取50
        size = Math.min(size, ArticleCostans.PAGE_SIZE_MAX);
        dto.setSize(size);

        // 文章频道参数校验
        dto.setTag(StringUtils.isEmpty(dto.getTag()) ? ArticleCostans.DEFAULT_TAG : dto.getTag());
        type = !ArticleCostans.LOAD_MORE.equals(type) || !ArticleCostans.LOAD_NEW.equals(type) ? ArticleCostans.LOAD_MORE : type;

        // 获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        // 判断用户是否存在
        if (null != user) {
            // 存在 已登录 加载推荐的文章
            articleList = this.getUserArticle(user, dto, type);
        } else {
            // 不存在 未登录 加载默认文章列表
            articleList = this.getDefaultArticle(dto, type);
        }

        return ResponseResult.okResult(articleList);
    }

    /**
     * 加载默认的文章信息
     *
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto, type);
    }

    /**
     * 先从用户推荐表中查找文章信息，如果没有再从默认的文章信息获取数据
     *
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApArticle> returnArticle;

        List<ApUserArticleList> userArticles = apUserArticleListMapper.loadArticleIdListByUser(user, dto, type);

        if (!userArticles.isEmpty()) {
            returnArticle = apArticleMapper.loadArticleListByIdList(userArticles);
        } else {
            returnArticle = this.getDefaultArticle(dto, type);
        }
        return returnArticle;
    }
}
