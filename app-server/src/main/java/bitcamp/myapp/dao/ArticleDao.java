package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDao {
  int insert(Article article);

  List<Article> findAll(int status);

  Article findBy(int articleNo);

  int update(Article article);

  int delete(int articleNo);

  int updateViewCount(int articleNo);

  int bid(@Param("current_price") int currentPrice, @Param("bid_count") int bidCount);
}