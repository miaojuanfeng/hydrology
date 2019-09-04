package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.entity.write.Staff;
import gov.gz.hydrology.entity.write.User;
import gov.gz.hydrology.service.write.StaffService;
import gov.gz.hydrology.service.write.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("cms/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@GetMapping("login")
	public String login() {
		return "LoginView";
	}

	@PostMapping("login")
	public String login(@RequestParam("userId") String userId, @RequestParam("userPsd") String userPsd, ModelMap model) {
		User user = userService.selectByPrimaryKey(userId);
		if( user != null ){
			if( userPsd.equals(user.getUserPsd()) ){
				return "redirect:/cms/station/1";
			}else{
				model.put("reason", "手机号或密码错误");
			}
		}else {
			model.put("reason", "手机号或密码错误");
		}
		return "LoginView";
	}
	
	@GetMapping("register")
	public String register() {
		return "RegisterView";
	}

	@PostMapping("register")
	public String register(@RequestParam("userId") String userId, @RequestParam("userPsd") String userPsd, @RequestParam("psdCfm") String psdCfm, ModelMap model) {
		Staff staff = staffService.selectByPrimaryKey(userId);
		if( staff != null ){
			if( !"".equals(userPsd) && userPsd.equals(psdCfm) ){
				User user = new User();
				user.setUserId(userId);
				user.setUserPsd(userPsd);
				user.setUserName(staff.getName());
				user.setUserLevel(0);
				user.setUserTime(0);
				userService.insertSelective(user);

				return "redirect:/cms/user/init";
			}else{
				model.put("reason", "两次密码输入不一致");
			}
		}else{
			model.put("reason", "用户不存在");
		}
		return "RegisterView";
	}
	
	@RequestMapping("init")
	public String init() {
		return "InitView";
	}
	
	@RequestMapping("info")
	public String info() {
		return "InfoView";
	}
	
	@RequestMapping(value="setting",method=RequestMethod.GET)
	public String settingGet() {
		return "SettingView";
	}
	
	@RequestMapping(value="setting",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String settingPost() {
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		JSONObject t = new JSONObject();
		int count = 100;
		for(int i=1;i<=count;i++) {
			t.put("id", i);
			t.put("code", "AABB");
			t.put("name", "宁都站");
			t.put("type", "水库水文站");
			t.put("area", "赣州章贡");
			temp.add(t);
		}
		retval.put("code", 0);
		retval.put("count", count);
		retval.put("data", temp);
		return retval.toString();
	}
	
//	@RequestMapping("register")
//	public String register() {
//		return "user/RegisterView";
//	}
	
//	@RequestMapping("dashboard")
//	public String dashboard() {
//		return "IndexView";
//	}
//	
//	@RequestMapping("welcome")
//	public String welcome() {
//		return "iframe/WelcomeView";
//	}
//	
//	@RequestMapping("button")
//	public String button() {
//		return "demo/ButtonView";
//	}
//	
//	@RequestMapping("form")
//	public String form() {
//		return "demo/FormView";
//	}
//	
//	@RequestMapping("table")
//	public String table() {
//		return "demo/TableView";
//	}
//	
//	@RequestMapping("tabcard")
//	public String tabcard() {
//		return "demo/TabCardView";
//	}
//	
//	@RequestMapping("progressbar")
//	public String progressbar() {
//		return "demo/ProgressBarView";
//	}
//	
//	@RequestMapping("foldingpanel")
//	public String foldingpanel() {
//		return "demo/FoldingPanelView";
//	}
//	
//	@RequestMapping("auxiliar")
//	public String auxiliar() {
//		return "demo/AuxiliarView";
//	}
//	
//	@RequestMapping(value="login",method=RequestMethod.GET)
//	public String getLogin() {
//		return "LoginView";
//	}
//	
//	@RequestMapping("register")
//	public String register() {
//		return "demo/RegisterView";
//	}
//	
//	@RequestMapping("login2")
//	public String login2() {
//		return "demo/LoginView2";
//	}
//	
//	@RequestMapping("map")
//	public String map() {
//		return "demo/MapView";
//	}
//	
//	@RequestMapping("addedit")
//	public String addedit() {
//		return "demo/AddEditView";
//	}
//	
//	@RequestMapping("datatable")
//	public String datatable() {
//		return "demo/DataTableView";
//	}
//	
//	@RequestMapping("tabletree")
//	public String tabletree() {
//		return "demo/TableTreeView";
//	}
//	
//	@RequestMapping("error404")
//	public String error404() {
//		return "demo/error404";
//	}
//	
//	@RequestMapping("tips")
//	public String tips() {
//		return "demo/TipsView";
//	}
	
}
