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
	public int deletelabel(int menuid) {
		return this.labelmapper.deletelabel(menuid);
	}
	public int deletemenu(int menu_id) {
		menumapper.deletesteps(menu_id);
		return this.menumapper.deletemenu(menu_id);
		
	}
	public int editmenu(menu menu) {
		return this.menumapper.editmenu(menu);
	}
	public int editlabel(List<label> labels, int menu_id) {
		return this.labelmapper.editlabel(labels,menu_id);
	}
	public int addstep(Step newstep) {
		return this.menumapper.addstep(newstep);
		
	}
	public int deletestep(int step_id) {
		return this.menumapper.deletestep(step_id);
	}
	public int editstep(Step step) {
		return this.menumapper.editstep(step);
	}
}
