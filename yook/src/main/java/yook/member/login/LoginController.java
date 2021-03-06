package yook.member.login;


import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.member.join.JoinService;



@Controller
public class LoginController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="mailService")
	private MailService mailService;
	
	@Autowired
    private BCryptPasswordEncoder pwEncoder;
	
	@Inject
	private JavaMailSender mailSender;

	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Autowired
	private JoinService joinService;
	
	
	/*
	 * @RequestMapping(value = "/main.do", method = RequestMethod.GET) public String
	 * hello(Locale locale, Model model) {
	 * 
	 * return "main"; }
	 */

	@RequestMapping(value = "/LoginForm.do") // loginForm.jsp, post랑 get 선언 안되어있으면 get이 default
	public ModelAndView loginForm(HttpServletRequest request) throws Exception {
		//session
		HttpSession session = request.getSession();
		
		System.out.println("session : " + session.getAttribute("session_MEM_ID"));
		
		if(session.getAttribute("session_MEM_ID")!=null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/main.do"); 
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("loginForm");
			return mv;
		}
	}
	
	@PostMapping("/login.do")
	public ModelAndView login(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		String message = "";
		String url = "";
		String pw = commandMap.getMap().get("MEM_PW").toString();
		String encryptPassword = pwEncoder.encode(pw);
		
		HttpSession session = request.getSession();

		//아이디 확인문
		Map<String, Object> chk = loginService.login(commandMap.getMap());
		if (chk==null) { // 아이디가 있는지 없는지를 확인
			message = "해당 아이디가 존재하지 않습니다.";
		} else {
			if (chk.get("MEM_PW").equals(commandMap.get("MEM_PW"))) {
				session.setAttribute("session_MEM_ID", commandMap.get("MEM_ID"));
				session.setAttribute("session_MEM_NUM", commandMap.get("MEM_NUM"));
				session.setAttribute("session_MEMBER", chk);//일허게 만들면 commandMap에다가 MEM_PW,ID,NUM을 다 넣고 MEMBER로 비교하는건데 필요없다.
			} else if(pwEncoder.matches(pw, encryptPassword)) {
			  session.setAttribute("session_MEM_ID", commandMap.get("MEM_ID"));
			} else {
				message = "비밀번호가 맞지 않습니다.";
			}
			
		System.out.println(chk);
		mv.addObject("message",message);
		mv.addObject("url",url);
		
		}
		return mv;
	}
	
	@RequestMapping(value="/logout.do") // 로그아웃
    public ModelAndView logout(CommandMap commandMap, HttpServletRequest request) throws Exception {
       HttpSession session = request.getSession();
       if (session != null)
          session.invalidate();
       ModelAndView mv = new ModelAndView();
       mv.setViewName("redirect:/main/logoutSc.do");
       return mv;
    }
 
	 @RequestMapping(value="/main/logoutSc.do")
	 public ModelAndView openJoinForm(CommandMap commandMap)throws Exception{
	    ModelAndView mv = new ModelAndView("logout");
	    
	    return mv;
	 }
	

	@RequestMapping(value = "/findId.do") // 아이디 찾기 폼을 보여주는 메소드
	public ModelAndView findId(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("memberFind");
		int ran = new Random().nextInt(900000) + 100000;
		
		mv.addObject("random",ran);
		
		return mv;
	}

	@RequestMapping(value = "/member/openFindIdResult.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public  String findIdResult(CommandMap commandMap) throws Exception {
		String id = String.valueOf(loginService.findId(commandMap.getMap()));
		
		if(id.equals("1")) {
			String findId = (String)(loginService.findIdWithEmail(commandMap.getMap()).get("MEM_ID"));
			System.out.println(findId);
			return findId;
			
		}else {
			return id;
		}
	}
	
	
	@RequestMapping(value = "/member/findPw.do") // 비밀번호 찾기 폼을 보여주는 메소드
	public ModelAndView findPw(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/member/findAccount");
		int ran = new Random().nextInt(900000) + 100000;
		
		mv.addObject("random",ran);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/member/openFindPwResult.do", method=RequestMethod.GET) //鍮꾨�踰덊샇 李얘린
	@ResponseBody
	public boolean findPwEmail(CommandMap commandMap,@RequestParam String MEM_ID, @RequestParam String MEM_EMAIL, @RequestParam int random, HttpServletRequest req) throws Exception {
		
		String emailCheck = String.valueOf(loginService.findPwWithEmail(commandMap.getMap()));
		System.out.println(emailCheck);
		if(emailCheck.equals("1")) {
			int ran = new Random().nextInt(900000) + 100000;
			   HttpSession session = req.getSession(true);
			   String authCode = String.valueOf(ran);
			   session.setAttribute("authCode", authCode);
			   session.setAttribute("random", random);
			   String subject = "식육증가 비밀번호 변경 코드 안내 입니다.";
			   StringBuilder sb = new StringBuilder();
			   sb.append("귀하의 임시 비밀번호는 " + authCode + "입니다.");
			   
			   commandMap.put("MEM_ID", MEM_ID);
			   commandMap.put("MEM_EMAIL", MEM_EMAIL);
			   commandMap.put("authCode", authCode);
			   loginService.updateTempPw(commandMap.getMap());
			   return mailService.send(subject, sb.toString(),"dnwlaos23@gmail.com", MEM_EMAIL, null);
		}else {
			  return false;
		}
	}
	
}