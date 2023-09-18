package bitcamp.myapp.controller;

import bitcamp.myapp.service.AuctionArticleService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.AuctionArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auctionArticle")
public class AuctionArticleController {

  {
    System.out.println("AuctionArticleController 생성됨!");
  }

  @Autowired
  AuctionArticleService auctionArticleService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public void form() {
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", auctionArticleService.list());
  }

  @ResponseBody
  @GetMapping("/getEvent")
  public List<AuctionArticle> getAuctionArticlesByDate(@RequestParam String date) throws Exception {
    return auctionArticleService.findAuctionArticlesByDate(date);
  }
}











