package com.zucc.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zucc.demo.dao.MessageDAO;

import com.zucc.demo.dao.RankDAO;
import com.zucc.demo.dao.UserDAO;
import com.zucc.demo.model.MessageVO;
import com.zucc.demo.model.UserVO;
import com.zucc.demo.service.MessageManager;

@Controller
@SessionAttributes({"uname"})  
@RequestMapping("/User")
public class UserController {
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView User() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("UserLogin");
        return mav;
}
    @Autowired
    UserDAO dao;
    @Autowired
    MessageManager tdao;
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView Login(UserVO user,ModelMap map) {
        ModelAndView mav = new ModelAndView();
        String pwd=dao.Login(user.getUname()).getPwd();
        if(pwd.equals(user.getPwd()))
        {
        	map.addAttribute("uname", user.getUname()); 
        	tdao.getNewMessage("ChatRoom1", user.getUname());
        	mav.setViewName("ChatRoom");
        }else {      	
        	map.remove("user");
        	mav.setViewName("LoginFailed");
        }
        
        return mav;
}
	 @Autowired
	    RankDAO rankdao;
		 @RequestMapping(value = "/RankList",method = RequestMethod.GET)
	    public String GetRank(Model model) {
		        model.addAttribute("Ranks", rankdao.GetRank());
		        return "RankList";
	}
		 
		 	@Autowired
		    MessageDAO messagedao;
		    @RequestMapping("/PushMessage")
		    @ResponseBody
		    public String PushMessage(@ModelAttribute("uname") String uname,String pushMsg) {
		    	MessageVO Msg =new MessageVO();
		    	Msg.setText(pushMsg);
		    	Msg.setUname(uname);
		    	Msg.setTimestamp(Long.toString(System.currentTimeMillis()));
		    	messagedao.PushMessage("ChatRoom1", Msg);
		       return uname;
		    }
		 	@Autowired
		    MessageManager pulldao;
		    @RequestMapping("/PullMessage")
		    
		    public @ResponseBody List<MessageVO> PullMessage(@ModelAttribute("uname") String uname) {
		    	List<MessageVO> result=new ArrayList<>(pulldao.getNewMessage("ChatRoom1", uname));
		    	return result;
		    }
}