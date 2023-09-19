package bitcamp.myapp.service;

import bitcamp.myapp.vo.AuctionArticle;

import java.util.List;

public interface AuctionArticleService {
  int add(AuctionArticle article) throws Exception;

  List<AuctionArticle> list(int status) throws Exception;

  AuctionArticle get(int articleNo) throws Exception;

  int update(AuctionArticle article) throws Exception;

  int delete(int articleNo) throws Exception;

  int increaseViewCount(int articleNo) throws Exception;

  int bid(int currentPrice, int bidCount);
}