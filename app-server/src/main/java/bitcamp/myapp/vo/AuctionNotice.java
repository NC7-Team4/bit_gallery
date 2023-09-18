package bitcamp.myapp.vo;

import java.io.Serializable;

public class AuctionNotice implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private AuctionMember user;
  private String title;
  private String content;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public AuctionMember getUser() {
    return user;
  }

  public void setUser(AuctionMember user) {
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
