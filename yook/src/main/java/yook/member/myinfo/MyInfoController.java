package yook.member.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.member.myinfo.MyInfoService;
import yook.member.myorder.MyOrderService;

@Controller
public class MyInfoController {
	
	Logger log = Logger.getLogger(this.getClass());
	   
	   @Resource(name="myInfoService")
	   private MyInfoService myInfoService;
	   
	   @Resource(name="myOrderService")
	   private MyOrderService myOrderService;
	   
	   @RequestMapping(value="/openMyInfoForm.do") //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
	   public ModelAndView openMyInfoForm(CommandMap commandMap, HttpServletRequest request) throws Exception{
	      ModelAndView mv = new ModelAndView("myInfoForm");
	      HttpSession session = request.getSession();
	      commandMap.put("MEM_ID", session.getAttribute("session_MEM_ID"));
	      commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
	      Map<String, Object> map = myInfoService.selectMyInfoForm(commandMap.getMap()); // �뿬湲곗꽌 �삤�뜑由ъ뒪�듃源뚯� �꽌鍮꾩뒪異붽�
	      Map<String, Object> map1 = myOrderService.myOrderStatus(commandMap.getMap());
	     
	      
	      List<Map<String, Object>> list = myOrderService.openMyOrderList(commandMap.getMap());
	      mv.addObject("list", list);
	      
	      
	      mv.addObject("map", map); 
	      mv.addObject("map1", map1);
	      
	      
	    
	      
	      return mv;
	   }
	   
//	   �굹�뒗 �뾽�뜲�씠�듃�씤�룷�뤌+ �뾽�뜲�씠�듃�씤�룷瑜� �빀移좎삁�젙 ( �뾽�뜲�씠�듃�뿉 �쐞�뿉�뒗 �뾽�뜲�씠�듃�뤌 �굹���굹寃�? �븘 �굹�뒗 �씠嫄� �뤌 �븘�슂�뾾�떎 �뼱吏쒗뵾 myinfoForm �뿉�꽌 �떎 留뚮뱾�뼱�넦湲� �븣臾몄뿉 �븘�옒 �뾽�뜲�씠�듃 留덉씠�씤�룷留� �엲�쑝硫대릪
	//�씠嫄� 醫��깮媛곹빐遊먯빞寃잙떎. updateMyInfo 瑜� �뵲�씪媛�硫� dao媛� �뾾�꽕 ? 寃곌뎅�뿏 �뿬湲� �뾽�뜲�씠�듃�뤌�뿉�꽌 �떎�삤瑜� �뵲���빞 �븷�벏   
	   //�궡媛� �엫�뤃�듃 �빐�빞�븷 �꽌鍮꾩뒪紐⑸줉- 
//	   @RequestMapping(value="/member/updateMyInfoForm.do") //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
//	   public ModelAndView updateMyInfoForm(CommandMap commandMap, HttpServletRequest request) throws Exception{
//	      ModelAndView mv = new ModelAndView("/member/myInfoModifyForm");
//	      HttpSession session = request.getSession();
//	      commandMap.put("MEM_ID", session.getAttribute("session_MEM_ID"));
//	      Map<String, Object> map = myInfoService.updateMyInfoForm(commandMap.getMap());
//	      mv.addObject("map", map);   
//	      
//	      return mv;
//	   }
	   
	   @RequestMapping(value="/updateMyInfo.do") //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	   public ModelAndView updateMyInfo(CommandMap commandMap, HttpServletRequest request)throws Exception{
	      ModelAndView mv = new ModelAndView("redirect:/openMyInfoForm.do");      
	      HttpSession session = request.getSession();
	      commandMap.put("MEM_ID", session.getAttribute("session_MEM_ID"));
	      myInfoService.updateMyInfo(commandMap.getMap());
	      
	      return mv;
	   }
	   	
	    @RequestMapping(value="/deleteMyInfo.do") //회占쏙옙탈占쏙옙
	    public ModelAndView deleteMyInfo(CommandMap commandMap, HttpServletRequest request)throws Exception{
	       ModelAndView mv = new ModelAndView("redirect:/main.do");
	       HttpSession session = request.getSession();
	       commandMap.put("MEM_ID", session.getAttribute("session_MEM_ID"));
	       myInfoService.deleteMyInfo(commandMap.getMap());
	       session.invalidate();
	       
	       return mv;
	     }

}
