package bitcamp.myapp.dao;

import bitcamp.myapp.vo.AuctionMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionMemberDao {
  int insert(AuctionMember user);
  AuctionMember findBy(int userNo);
  int update(AuctionMember user);
  int updatePoint(AuctionMember user);
  int delete(int userNo);
}