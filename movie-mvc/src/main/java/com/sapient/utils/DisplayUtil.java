package com.sapient.utils;

import java.util.Arrays;
import java.util.List;

public class DisplayUtil 
{
	
	public static List<String> converToList(String str)
	{
		return Arrays.asList(str.split("\n"));
	}

}
