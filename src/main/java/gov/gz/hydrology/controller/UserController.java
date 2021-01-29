package gov.gz.hydrology.controller;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.write.User;
import gov.gz.hydrology.service.common.CommonService;
import gov.gz.hydrology.service.read.ZvarlService;
import gov.gz.hydrology.service.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cms/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private StationService stationService;

	@Autowired
	private UserStationService userStationService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ZvarlService zvarlService;

	@Autowired
	private DrainageService drainageService;

	@Autowired
	private LineService lineService;

//	@GetMapping("test")
//	public String test() {
//		return "views/index";
//	}

//	@GetMapping("view")
//	public String view(@PathParam("folder") String folder, @PathParam("file") String file) {
//		return url;
//	}

	@GetMapping("login")
	public String login() {
		List<BigDecimal> Z_CUR = new ArrayList<>();
		List<BigDecimal> V_CUR = new ArrayList<>();
		zvarlService.selectList("62302330", "2013-04-01 08:00:00.000", Z_CUR, V_CUR);
		List<BigDecimal> Z0 = new ArrayList<>();
		List<BigDecimal> HCOQ = new ArrayList<>();
		drainageService.selectList("62302330", Z0, HCOQ);
		List<BigDecimal> line = lineService.selectList("62303650");
		return "LoginView";
	}

	@PostMapping("login")
	public String login(HttpServletRequest request, ModelMap model, @RequestParam("userId") String userId, @RequestParam("userPsd") String userPsd) {
		HttpSession session = request.getSession();
		if( session.getAttribute(CommonConst.SESSION_KEY_USER) != null ){
			return "redirect:/cms/station";
		}
		User user = userService.selectByPrimaryKey(userId);
		if( user != null ){
			if( userPsd.equals(user.getUserPsd()) ){
				user.setUserLevelName(commonService.userLevel(user.getUserLevel()));
				session.setAttribute(CommonConst.SESSION_KEY_USER, user);
				return "redirect:/cms/station";
			}else{
				model.put("userId", userId);
				model.put("reason", "手机号或密码错误");
			}
		}else {
			model.put("reason", "手机号或密码错误");
		}
		return "LoginView";
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(CommonConst.SESSION_KEY_USER);
		return "redirect:/cms/user/login";
	}
	
//	@GetMapping("register")
//	public String register() {
//		return "RegisterView";
//	}
//
//	@PostMapping("register")
//	public String register(HttpServletRequest request, @RequestParam("userId") Long userId, @RequestParam("userPsd") String userPsd, @RequestParam("psdCfm") String psdCfm, ModelMap model) {
//		Staff staff = staffService.selectByPrimaryKey(userId);
//		if( staff != null ){
//			User user = userService.selectByPrimaryKey(userId);
//			if( user == null ) {
//				if (!"".equals(userPsd) && userPsd.equals(psdCfm)) {
//					User newUser = new User();
//					newUser.setUserId(userId);
//					newUser.setUserPsd(userPsd);
//					newUser.setUserName(staff.getName());
//					newUser.setUserLevel(0);
//					newUser.setUserTime(0);
//					newUser.setUserHead("/assets/images/avatar.png");
//					userService.insertSelective(newUser);
//
//					HttpSession session = request.getSession();
//					newUser.setUserLevelName(commonService.userLevel(newUser.getUserLevel()));
//					session.setAttribute(CommonConst.SESSION_KEY_USER, newUser);
//
//					return "redirect:/cms/user/init";
//				} else {
//					model.put("userId", userId);
//					model.put("reason", "两次密码输入不一致");
//				}
//			}else{
//				model.put("reason", "用户已注册");
//			}
//		}else{
//			model.put("reason", "用户不存在");
//		}
//		return "RegisterView";
//	}
	
//	@GetMapping("init")
//	public String init() {
//		return "InitView";
//	}
//
//	@PostMapping("init")
//	public String init(HttpServletRequest request, @RequestParam("stcd") String[] stcd, ModelMap model) {
//		boolean error = true;
//		List<UserStation> userStationList = new ArrayList<>();
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//		for (int i = 0; i < stcd.length; i++) {
//			if( !"".equals(stcd[i]) ){
//				error = false;
//				//
//				UserStation userStation = new UserStation();
//				userStation.setUserId(user.getUserId());
//				userStation.setUserStcd(stcd[i]);
//				userStationList.add(userStation);
//			}
//		}
//		if( !error ) {
//			userStationService.deleteByUserId(user.getUserId());
//			userStationService.insertBatch(userStationList);
//			return "redirect:/cms/station";
//		}else{
//			model.put("reason", "请至少选择一个站");
//		}
//		return "InitView";
//	}
	
//	@GetMapping("info")
//	public String info(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		List<UserStation> userStation = userStationService.selectByUserId(user.getUserId());
//		String userStationName = "";
//		for(UserStation station : userStation) {
//			if( userStationName.isEmpty() ){
//				userStationName = station.getStation().getStname();
//			}else {
//				userStationName = userStationName + "、" + station.getStation().getStname();
//			}
//		}
//		model.put("userStationName", userStationName);
//		model.put("userlevelProcess", commonService.levelProgress(user.getUserLevel()));
//		return "InfoView";
//	}
//
//	@PostMapping("info")
//	public String info(HttpServletRequest request, ModelMap model, @RequestParam("psd") String psd, @RequestParam("newPsd") String newPsd, @RequestParam("cfmPsd") String cfmPsd) {
//		if( !newPsd.isEmpty() || !cfmPsd.isEmpty() ) {
//			if( newPsd.equals(cfmPsd) ) {
//				HttpSession session = request.getSession();
//				User user = (User) session.getAttribute(CommonConst.SESSION_KEY_USER);
//				if (user.getUserPsd().equals(psd)) {
//					user.setUserPsd(newPsd);
//					userService.updateSelective(user);
//					model.put("reason", "修改成功");
//				} else {
//					model.put("reason", "原始密码不正确");
//				}
//			}else{
//				model.put("reason", "两次密码输入不一致");
//			}
//		}else {
//			model.put("reason", "新密码不能为空");
//		}
//		return "InfoView";
//	}

//	@RequestMapping(value="setting",method=RequestMethod.GET)
//	public String settingGet(HttpServletRequest request, ModelMap model) {
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		List<UserStation> userStation = userStationService.selectByUserId(user.getUserId());
//		String userStationName = "";
//		for(UserStation station : userStation) {
//			if( userStationName.isEmpty() ){
//				userStationName = station.getStation().getStname();
//			}else {
//				userStationName = userStationName + "、" + station.getStation().getStname();
//			}
//		}
//		model.put("userStationName", userStationName);
//		model.put("userlevelProcess", commonService.levelProgress(user.getUserLevel()));
//		return "SettingView";
//	}
//
//	@RequestMapping(value="setting",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
//	@ResponseBody
//	public String settingPost(HttpServletRequest request, ModelMap map) {
//
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		List<UserStation> userStation = userStationService.selectByUserId(user.getUserId());
//		List<String> stcdStation = new ArrayList<>();
//		for(UserStation station : userStation) {
//			stcdStation.add(station.getUserStcd());
//		}
//
//		List<Station> stationList = stationService.selectStationByType("基本站");
//
//		JSONObject retval = new JSONObject();
//		JSONArray temp = new JSONArray();
//		int num = 1;
//		for(Station station : stationList) {
//			JSONObject t = new JSONObject();
//			t.put("id", num++);
//			t.put("stcd", station.getStcd());
//			t.put("stname", station.getStname());
//			t.put("type", station.getType());
//			t.put("area", "赣州市章贡区");
//			if( stcdStation.contains(station.getStcd()) ) {
//				t.put("LAY_CHECKED", true);
//			}else{
//				t.put("LAY_CHECKED", false);
//			}
//			temp.add(t);
//		}
//		retval.put("code", 0);
//		retval.put("count", stationList.size());
//		retval.put("data", temp);
//		return retval.toString();
//	}

//	@RequestMapping("setting/update")
//	@ResponseBody
//	public String updateSetting(HttpServletRequest request, @RequestParam("stcds") String stcds) {
//		JSONObject retval = new JSONObject();
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		List<UserStation> userStationList = new ArrayList<>();
//		for(String s : stcds.split(",")){
//			UserStation userStation = new UserStation();
//			userStation.setUserId(user.getUserId());
//			userStation.setUserStcd(s);
//			userStationList.add(userStation);
//		}
//		userStationService.deleteByUserId(user.getUserId());
//		userStationService.insertBatch(userStationList);
//		return retval.toString();
//	}
	
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
