package bitcamp.myapp.service;

import bitcamp.myapp.dao.AuctionArticleDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.AuctionArticle;
import bitcamp.myapp.vo.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultAuctionArticleService implements AuctionArticleService {
  {
    System.out.println("DefaultAuctionArticleService 생성됨!");
  }

  AuctionArticleDao auctionArticleDao;

  public DefaultAuctionArticleService(AuctionArticleDao auctionArticleDao) {
    this.auctionArticleDao = auctionArticleDao;
  }

  @Override
  public List<AuctionArticle> list() throws Exception {
    return auctionArticleDao.findAll();
  }

  @Override
  public List<AuctionArticle> findAuctionArticlesByDate(String date) throws Exception{
    return auctionArticleDao.findAuctionArticlesByDate(date);
  }


}
