package com.tengen.shiftkeeper.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tengen.shiftkeeper.common.UserObject;

public class UserFilter {
	private Pattern filterPattern;
	
	public UserFilter(String name)
	{
		filterPattern = Pattern.compile(".*" + name + ".*",Pattern.CASE_INSENSITIVE);
	}
	
	public List<UserObject> filter(List<UserObject> in)
	{
		List<UserObject> out = new ArrayList<UserObject>();
		
		for (int i=0; i<in.size(); i++)
		{
			Matcher matcher = filterPattern.matcher(in.get(i).getDisplayName());
			if (matcher.matches()) {
				out.add(in.get(i));
			}
		}
		
		return out;
	}
}
