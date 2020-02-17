package cn.com.smer.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Users;
import cn.com.smer.dao.inter.UsersMapper;
import cn.com.smer.dtoform.UsersForm;

@Service
public class UsersService {
	@Autowired
	private UsersMapper usersMapper;

	public boolean select(UsersForm form){
		Users user = new Users();
		BeanUtils.copyProperties(form,user);
		//System.out.println(user);
		if (usersMapper.selectBySelective(user) != null) {
			return true;
		} else {
			return false;
		}
	}

}
