package com.we.pray.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.we.pray.dao.IDao;
import com.we.pray.dto.BDto;


@Controller
public class BController {
	

	@Autowired
	private SqlSession sqlSession;
	

	@RequestMapping("/list")

	public String list(Model model){
		System.out.println("list()");
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("list", dao.listDao());

		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model){
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model){
		System.out.println("write()");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.writeDao(request.getParameter("bName"), request.getParameter("bTitle"),request.getParameter("bContent"));

		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String contant_wiew(HttpServletRequest request, Model model){
		System.out.println("content_view()");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.upHit(request.getParameter("bId"));
		model.addAttribute("content_view", dao.viewDao(request.getParameter("bId")));
		
		return"content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/modify")
	public String modify(BDto bdto){
		System.out.println("modify()");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.modify(bdto);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model){
		System.out.println("reply_view()");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("reply_view", dao.replyview(request.getParameter("bId")));
		
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(BDto bdto){
		System.out.print("reply()");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.replyShape(bdto);
		dao.reply(bdto);
		
			
		
		
		//model.addAttribute("request",request); //model(보낼정보)리퀘스트 담기
	
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model){
		System.out.println("delete()");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.deleteDao(request.getParameter("bId"));
	
		return "redirect:list";
	}
	
	

}
