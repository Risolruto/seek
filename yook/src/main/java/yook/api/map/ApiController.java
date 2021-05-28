package yook.api.map;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.shop.order.OrderService;


@Controller

public class ApiController {
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@RequestMapping(value="/openKakaoMap.do")
	public ModelAndView openApi(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/api/kakoMap");
		
		return mv;
	}
	
	@RequestMapping(value="/payment.do")
	public ModelAndView openPayment(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/api/payment");
		
		Object MEM_NUM = ""; //���ǰ� �������� 
		HttpSession session = request.getSession(); 
		commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
		//commandMap.put("ORDER_PRICE", Integer.parseInt((String)commandMap.get("ORDER_cost")));
		
		//System.out.println(commandMap.get("ORDER_cost"));
		//System.out.println(commandMap.get("ORDER_PRICE"));
		System.out.println(commandMap.get("ORDER_PRICE"));
		System.out.println(commandMap.get("ORDER_TCOST"));
		
		
		
		orderService.insertOrder(commandMap, request);
		Map<String,Object> map = orderService.selectOrder(commandMap, request);
		
		mv.addObject("map", map);
		
		
		 map.put("ORDER_TCOST", commandMap.get("ORDER_TCOST"));
		 map.put("MEM_NAME", commandMap.get("MEM_NAME"));
		 map.put("MEM_PHONE", commandMap.get("MEM_PHONE"));
		 map.put("ORDER_DZIPCODE", commandMap.get("ORDER_DZIPCODE"));
		 map.put("ORDER_DADD1", commandMap.get("ORDER_DADD1"));
		 map.put("ORDER_DADD2", commandMap.get("ORDER_DADD2"));
		 map.put("ORDER_DMEMO", commandMap.get("ORDER_DMEMO"));	
		
		
		
		return mv;
	}

}
