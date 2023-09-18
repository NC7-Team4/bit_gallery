package bitcamp.myapp.dao;

import bitcamp.myapp.vo.AuctionArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuctionArticleDao {
  int insert(AuctionArticle article);
  List<AuctionArticle> findAll();
  AuctionArticle findBy(int articleNo);

  //@Select("SELECT * FROM auction_article WHERE start_date <= #{date} AND end_date >= #{date}")
  List<AuctionArticle> findAuctionArticlesByDate(String date);

//  int update(Article article);
//  int delete(int articleNo);
}
