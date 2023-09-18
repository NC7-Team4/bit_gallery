package bitcamp.myapp.service;

import bitcamp.myapp.vo.AuctionArticle;
import bitcamp.myapp.vo.Board;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuctionArticleService {
  List<AuctionArticle> list() throws Exception;
  List<AuctionArticle> findAuctionArticlesByDate(String date) throws Exception;
}
