package com.kitchen.recommend.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Step;
import com.entity.label;
import com.entity.menu;
import com.kitchen.recommend.dao.labelmapper;
import com.kitchen.recommend.dao.menumapper;
@Service
public class  menuservices {
	@Resource
	private menumapper menumapper;
	@Resource
	private labelmapper labelmapper;
	public List<menu> findall(){
		return menumapper.findall();
	}
	public List<menu> findallbylabel(String type,String[] labels){
		return labelmapper.findallbylabel(type,labels);
	}
	public List<Step> findstepbymenuid(String menuid) {
		return labelmapper.findstepbymenuid(menuid);
	}
	public List<menu> findbyfind(String menuname) {
		// TODO Auto-generated method stub
		return labelmapper.findbyfind(menuname);
	}
	public int addmenu(menu menu) {
		 menumapper.addmenu(menu);
		 System.out.println("id"+menu.getMenu_id());
		 return menu.getMenu_id();
		
	}
	public int addlabelmenu(List<label> labels, int menuid) {
		return this.labelmapper.addlabelmenu(labels,menuid);
		
	}
	public int addsteps(int menuid, List<Step> steps) {
		// TODO Auto-generated method stub
		return this.menumapper.addsteps(menuid,steps);
	}
}
