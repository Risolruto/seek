package yook.member.join;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;

@Controller
public class JoinController {
	
	@Autowired
    private BCryptPasswordEncoder pwEncoder;
	
	@Resource(name="joinService")
	private JoinService joinService;
	
	
	@RequestMapping(value="/join.do")
	public ModelAndView openJoinForm(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("joinForm");
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/member/insertJoin.do")
	public ModelAndView insertJoin(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/LoginForm.do");
		

		String pw = commandMap.getMap().get("MEM_PW").toString();
		String encryptPassword = pwEncoder.encode(pw);
		
		commandMap.getMap().put("MEM_PW", encryptPassword);
		
		joinService.insertJoin(commandMap.getMap());
		
		
		return mv;
	}
	
	@RequestMapping(value = "/member/idCheck.do") 
	   public @ResponseBody String idCheck(CommandMap commandMap) throws Exception {
	      
	      System.out.println(commandMap.getMap());
	      
	      String idCheck = String.valueOf(joinService.selectIdCheck(commandMap.getMap()));
	      
	      System.out.println(idCheck);

	      return idCheck;
	   }
	
	@RequestMapping(value="/member/openAgree.do")
	   public ModelAndView openAgree(CommandMap commandMap)throws Exception{
	      ModelAndView mv = new ModelAndView("/member/agree");
	      
	      return mv;
	   }
	
	
	
}
