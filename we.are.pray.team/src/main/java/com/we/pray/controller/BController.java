package com.we.pray.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.we.pray.command.BCommand;
import com.we.pray.command.BContentCommand;
import com.we.pray.command.BDeletCommand;
import com.we.pray.command.BListCommand;
import com.we.pray.command.BModifyCommand;
import com.we.pray.command.BReplyCommand;
import com.we.pray.command.BReplyViewCommand;
import com.we.pray.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command;
	
	@RequestMapping("/list")
	public String list(Model model){
		System.out.println("list()");
		
		command = new BListCommand();
		command.execute(model); //여기로가서 할일해
		
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
		
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String contant_wiew(HttpServletRequest request, Model model){
		System.out.println("content_view()");
		
		model.addAttribute("request",request);
		command = new BContentCommand();
		command.execute(model);
		
		return"content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model){
		System.out.println("modify()");
			
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model){
		System.out.println("reply_view()");
		
		model.addAttribute("request",request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model){
		System.out.print("reply()");
		
		model.addAttribute("request",request); //model(보낼정보)리퀘스트 담기
		command = new BReplyCommand(); //command 인터페이스 생성 (command=서비스임)
		command.execute(model); //execute메소드에 model 담기
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model){
		System.out.print("delete()");
		
		model.addAttribute("request",request); //model(보낼정보)리퀘스트 담기
		command = new BDeletCommand(); //command 인터페이스 생성 (서비스)
		command.execute(model); //execute메소드에 model 담기
		
		return "redirect:list";
	}
	
	

}
