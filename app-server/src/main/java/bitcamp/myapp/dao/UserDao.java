package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
  int insert(User user);
  User findBy(int userNo);
  List<User> findAll() throws Exception;
  User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
  int update(User user);
  int updatePoint(User user);
  int delete(int userNo);
}