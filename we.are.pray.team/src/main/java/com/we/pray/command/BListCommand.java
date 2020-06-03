package com.we.pray.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.we.pray.dao.BDao;
import com.we.pray.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BDao dao = new BDao(); //dao 생성
		ArrayList<BDto> dtos =dao.list();  //BDao에서 list()메소드에서 작업하고  BDto형으로 리스트로 만들어와
		
		model.addAttribute("list",dtos);  //작업한 데이터를 "list"로 키값 주고 model에 넣어
	}

}
