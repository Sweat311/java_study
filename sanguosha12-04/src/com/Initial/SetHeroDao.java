package com.Initial;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
/**
 * 
 * 设置属性
 *
 */
public class SetHeroDao {

	private String [] heros = new String[10];
	
	public SetHeroDao() {}
	//接受到武将名称
	public SetHeroDao(String[] heros) {
		this.heros = heros;
	}

	public void set() {
		//heros[5]是玩家,hm是存人物的HashMap
		Character c1 = Initial.hm.get(heros[5]);
		//身份赋值
		String shengfe = "主公";
		Initial.p1 = new Player(c1, c1.getmaxHp(), shengfe); // 创建玩家，为其武将属性赋值
		
		String[] str1 = new String[4]; // 创建一个剩余身份数组str1
		
		// 给str1赋值
		for (int i = 0; i < 4; i++) {
			str1[0] = "忠臣";
			str1[1] = "内奸";
			str1[2] = "反贼";
			str1[3] = "反贼";
		}
		// 身份随机分配
		rd(str1);
		
		// AI获取武将对象Character
		int h = 0;
		
		// 创建AI
		Initial.cAI1 = Initial.hm.get(heros[1]);
		Initial.cAI2 = Initial.hm.get(heros[2]);
		Initial.cAI3 = Initial.hm.get(heros[3]);
		Initial.cAI4 = Initial.hm.get(heros[4]);
		Initial.p2 = new PlayerAI(Initial.cAI1, Initial.cAI1.getmaxHp(), str1[h++]);
		Initial.p3 = new PlayerAI(Initial.cAI2, Initial.cAI2.getmaxHp(), str1[h++]);
		Initial.p4 = new PlayerAI(Initial.cAI3, Initial.cAI3.getmaxHp(), str1[h++]);
		Initial.p5 = new PlayerAI(Initial.cAI4, Initial.cAI4.getmaxHp(), str1[h]);
	}	
	
	/**
	 *确定的字符串随机不重复方法rd
	 */
	public static void rd(String[] str) {
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		int[] arr = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			hm.put(i + 1, str[i]);
		}

		Random r = new Random();
		int j = 0;
		int a = 0;
		while (j < str.length) {
			a = r.nextInt(str.length) + 1;
			if (unContain(arr, a)) {
				arr[j++] = a;
			}
		}
		
		for (int i = 0; i < str.length; i++) {
			str[i] = hm.get(arr[i]);
		}
	}
	
	public static boolean unContain(int[] arr, int a) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == a) {
				return false;
			}
		}

		return true;
	}
	
	
}
