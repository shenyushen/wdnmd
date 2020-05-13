package com.kitchen.recommend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Step;
import com.entity.label;
import com.entity.menu;

public interface labelmapper {
	public List<menu> findallbylabel(@Param("type")String type,@Param("labels")String[] labels);

	public List<Step> findstepbymenuid(String menuid);

	public List<menu> findbyfind(String menuname);

	public int addlabelmenu(@Param("labels")List<label> labels, @Param("menuid")int menuid);

}
