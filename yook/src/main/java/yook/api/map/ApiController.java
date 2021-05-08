package yook.api.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;


@Controller

public class ApiController {
	
	@RequestMapping(value="/openKakaoMap.do")
	public ModelAndView openApi(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/api/kakoMap");
		
		return mv;
	}
	
	@RequestMapping(value="/payment.do")
	public ModelAndView openPayment(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/api/payment");
		
		return mv;
	}

}
