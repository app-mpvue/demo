package hello.mall.mpvue.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import hello.mall.mpvue.controller.utils.utils;
import hello.mall.mpvue.dao.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/store/storeuserinfo")
public class UserController {

	@Value("${pro.classify}")
	private String file;
	@Value("${pro.user}")
	private String typeFile;
	@Value("${pro.order}")
	private String typeFile1;

	@RequestMapping(path = "login", method = RequestMethod.POST)
	public String pro(String userName, String password) throws Exception {
		File f = new File(typeFile);
		String printuser = null;
		if (!f.exists()) {
			throw new Exception("no file:" + f.toString());
		}

		String strJon = utils.FileToString(f);
		List<User> userList = new ArrayList<User>();
		JSONArray jsonArray = JSONArray.fromObject(strJon);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			User user = (User) JSONObject.toBean(jsonObject, User.class);
			userList.add(user);
		}

		System.out.println(userName);
		System.out.println(password);
		for (User user : userList) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				printuser = JSON.toJSONString(user);
				break;
			} else {
				printuser = "";
			}
		}
		System.out.println("---"+printuser);
		return printuser;
	}

}