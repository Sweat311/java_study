package com.Biz;

import org.eclipse.swt.widgets.Button;

import com.Initial.Card;
import com.Initial.Character;
import com.Initial.Initial;
import com.Initial.Player;
import com.Initial.PlayerAI;
import com.Initial.SetHeroDao;
import com.MainTest.MainTest;
import com.Util.YcUtil;

/**
 * 初始化业务
 * 
 * @author 浪痞小霸王队
 *
 */
public class SanGuoBiz {

	SetHeroDao shd; // 设置属性
	Player p1; // 玩家
	Character c;
	PlayerAI p2; // AI

	/**
	 * 初始化构造方法
	 */
	public void init() {
		try {
			Initial.cardInit();// 初始化牌组
			Initial.putCharacter();// Map 武将和对象的键值表，供玩家用来选择
			Initial.setKeyMap();// 牌的键值对hmp
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置英雄的基本属性
	 */
	public void setHero(String[] heros) {
		shd = new SetHeroDao(heros);// 传参
		shd.set();// 设置英雄的基本属性
	}

	/**
	 * 发牌给玩家
	 */
	public void faPai_1(String hero) {
		c = Initial.hm.get(hero);
		System.out.println(c.getName());
		p1 = new Player(c, c.getmaxHp(), c.getShengFe());
		System.out.println(p1);
		p1.giveCard(p1);
	}

	/**
	 * 发牌给AI
	 */
	public void faPai_2(String hero) {
		c = Initial.hm.get(hero);
		System.out.println(c.getName());
		p2 = new PlayerAI(c, c.getmaxHp(), c.getShengFe());
		System.out.println(p2);
		p1.giveCard(p2);
	}

	/**
	 * 
	 * 移动手牌图片
	 * 
	 * @param button
	 */
	public void YiDoPai() {
		if (MainTest.naPai == 1) {
			if (MainTest.x >= 1 && MainTest.button_1_2.getImage().toString().isEmpty() == false) {
				MainTest.button_1_1.setImage(MainTest.button_1_2.getImage());
				YcUtil.change_sex(MainTest.button_1_1);
				MainTest.button_1_1.setVisible(true);
				MainTest.button_1_2.setVisible(false);
				MainTest.button_1_2.setImage(null);
			}
			if (MainTest.x >= 2 && MainTest.button_1_3.getImage().toString().isEmpty() == false) {
				MainTest.button_1_2.setImage(MainTest.button_1_3.getImage());
				YcUtil.change_sex(MainTest.button_1_2);
				MainTest.button_1_2.setVisible(true);
				MainTest.button_1_3.setVisible(false);
				MainTest.button_1_3.setImage(null);
			}
			if (MainTest.x >= 3 && MainTest.button_1_4.getImage().toString().isEmpty() == false) {
				MainTest.button_1_3.setImage(MainTest.button_1_4.getImage());
				YcUtil.change_sex(MainTest.button_1_3);
				MainTest.button_1_3.setVisible(true);
				MainTest.button_1_4.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 4 && MainTest.button_1_5.getImage().toString().isEmpty() == false) {
				MainTest.button_1_4.setImage(MainTest.button_1_5.getImage());
				YcUtil.change_sex(MainTest.button_1_4);
				MainTest.button_1_4.setVisible(true);
				MainTest.button_1_5.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 5 && MainTest.button_1_6.getImage().toString().isEmpty() == false) {
				MainTest.button_1_5.setImage(MainTest.button_1_6.getImage());
				YcUtil.change_sex(MainTest.button_1_5);
				MainTest.button_1_5.setVisible(true);
				MainTest.button_1_6.setVisible(false);
				MainTest.button_1_5.setImage(null);
			}
			if (MainTest.x >= 6 && MainTest.button_1_7.getImage().toString().isEmpty() == false) {
				MainTest.button_1_6.setImage(MainTest.button_1_7.getImage());
				YcUtil.change_sex(MainTest.button_1_6);
				MainTest.button_1_6.setVisible(true);
				MainTest.button_1_7.setVisible(false);
				MainTest.button_1_6.setImage(null);
			}
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
		if (MainTest.naPai == 2) {
			if (MainTest.x >= 2 && MainTest.button_1_3.getImage().toString().isEmpty() == false) {
				MainTest.button_1_2.setImage(MainTest.button_1_3.getImage());
				YcUtil.change_sex(MainTest.button_1_2);
				MainTest.button_1_2.setVisible(true);
				MainTest.button_1_3.setVisible(false);
				MainTest.button_1_3.setImage(null);
			}
			if (MainTest.x >= 3 && MainTest.button_1_4.getImage().toString().isEmpty() == false) {
				MainTest.button_1_3.setImage(MainTest.button_1_4.getImage());
				YcUtil.change_sex(MainTest.button_1_3);
				MainTest.button_1_3.setVisible(true);
				MainTest.button_1_4.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 4 && MainTest.button_1_5.getImage().toString().isEmpty() == false) {
				MainTest.button_1_4.setImage(MainTest.button_1_5.getImage());
				YcUtil.change_sex(MainTest.button_1_4);
				MainTest.button_1_4.setVisible(true);
				MainTest.button_1_5.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 5 && MainTest.button_1_6.getImage().toString().isEmpty() == false) {
				MainTest.button_1_5.setImage(MainTest.button_1_6.getImage());
				YcUtil.change_sex(MainTest.button_1_5);
				MainTest.button_1_5.setVisible(true);
				MainTest.button_1_6.setVisible(false);
				MainTest.button_1_5.setImage(null);
			}
			if (MainTest.x >= 6 && MainTest.button_1_7.getImage().toString().isEmpty() == false) {
				MainTest.button_1_6.setImage(MainTest.button_1_7.getImage());
				YcUtil.change_sex(MainTest.button_1_6);
				MainTest.button_1_6.setVisible(true);
				MainTest.button_1_7.setVisible(false);
				MainTest.button_1_6.setImage(null);
			}
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
		if (MainTest.naPai == 3) {
			if (MainTest.x >= 3 && MainTest.button_1_4.getImage().toString().isEmpty() == false) {
				MainTest.button_1_3.setImage(MainTest.button_1_4.getImage());
				YcUtil.change_sex(MainTest.button_1_3);
				MainTest.button_1_3.setVisible(true);
				MainTest.button_1_4.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 4 && MainTest.button_1_5.getImage().toString().isEmpty() == false) {
				MainTest.button_1_4.setImage(MainTest.button_1_5.getImage());
				YcUtil.change_sex(MainTest.button_1_4);
				MainTest.button_1_4.setVisible(true);
				MainTest.button_1_5.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 5 && MainTest.button_1_6.getImage().toString().isEmpty() == false) {
				MainTest.button_1_5.setImage(MainTest.button_1_6.getImage());
				YcUtil.change_sex(MainTest.button_1_5);
				MainTest.button_1_5.setVisible(true);
				MainTest.button_1_6.setVisible(false);
				MainTest.button_1_5.setImage(null);
			}
			if (MainTest.x >= 6 && MainTest.button_1_7.getImage().toString().isEmpty() == false) {
				MainTest.button_1_6.setImage(MainTest.button_1_7.getImage());
				YcUtil.change_sex(MainTest.button_1_6);
				MainTest.button_1_6.setVisible(true);
				MainTest.button_1_7.setVisible(false);
				MainTest.button_1_6.setImage(null);
			}
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
		if (MainTest.naPai == 4) {
			if (MainTest.x >= 4 && MainTest.button_1_5.getImage().toString().isEmpty() == false) {
				MainTest.button_1_4.setImage(MainTest.button_1_5.getImage());
				YcUtil.change_sex(MainTest.button_1_4);
				MainTest.button_1_4.setVisible(true);
				MainTest.button_1_5.setVisible(false);
				MainTest.button_1_4.setImage(null);
			}
			if (MainTest.x >= 5 && MainTest.button_1_6.getImage().toString().isEmpty() == false) {
				MainTest.button_1_5.setImage(MainTest.button_1_6.getImage());
				YcUtil.change_sex(MainTest.button_1_5);
				MainTest.button_1_5.setVisible(true);
				MainTest.button_1_6.setVisible(false);
				MainTest.button_1_5.setImage(null);
			}
			if (MainTest.x >= 6 && MainTest.button_1_7.getImage().toString().isEmpty() == false) {
				MainTest.button_1_6.setImage(MainTest.button_1_7.getImage());
				YcUtil.change_sex(MainTest.button_1_6);
				MainTest.button_1_6.setVisible(true);
				MainTest.button_1_7.setVisible(false);
				MainTest.button_1_6.setImage(null);
			}
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
		if (MainTest.naPai == 5) {
			if (MainTest.x >= 5 && MainTest.button_1_6.getImage().toString().isEmpty() == false) {
				MainTest.button_1_5.setImage(MainTest.button_1_6.getImage());
				YcUtil.change_sex(MainTest.button_1_5);
				MainTest.button_1_5.setVisible(true);
				MainTest.button_1_6.setVisible(false);
				MainTest.button_1_5.setImage(null);
			}
			if (MainTest.x >= 6 && MainTest.button_1_7.getImage().toString().isEmpty() == false) {
				MainTest.button_1_6.setImage(MainTest.button_1_7.getImage());
				YcUtil.change_sex(MainTest.button_1_6);
				MainTest.button_1_6.setVisible(true);
				MainTest.button_1_7.setVisible(false);
				MainTest.button_1_6.setImage(null);
			}
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
		if (MainTest.naPai == 6) {
			if (MainTest.x >= 6 && MainTest.button_1_7.getImage().toString().isEmpty() == false) {
				MainTest.button_1_6.setImage(MainTest.button_1_7.getImage());
				YcUtil.change_sex(MainTest.button_1_6);
				MainTest.button_1_6.setVisible(true);
				MainTest.button_1_7.setVisible(false);
				MainTest.button_1_6.setImage(null);
			}
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
		if (MainTest.naPai == 7) {
			if (MainTest.x >= 7 && MainTest.button_1_8.getImage().toString().isEmpty() == false) {
				MainTest.button_1_7.setImage(MainTest.button_1_8.getImage());
				YcUtil.change_sex(MainTest.button_1_7);
				MainTest.button_1_7.setVisible(true);
				MainTest.button_1_8.setVisible(false);
				MainTest.button_1_7.setImage(null);
			}
		}
	}

	/**
	 * 出牌方法
	 * 
	 * @param i
	 */
	public void chuPai(int i) {
		MainTest.p1ShouPai[i] = null;
		/**
		 * 更新p1ShouPai
		 */
		for(int j = i+1;j<=MainTest.x;j++) {
			MainTest.p1ShouPai[j-1] = MainTest.p1ShouPai[j];
			MainTest.p1ShouPai[j] = null; 
		}
	}
	/**
	 * 
	 * 使用手牌
	 * 
	 * @param p
	 * @param top
	 */
	public void use(String p,String top) {
		System.out.println(p1.getCharacter().getName());
		System.out.println(p2.getCharacter().getName());
	}
	
	/**
	 * 
	 * 结束出牌(后台)
	 * 
	 * @param p
	 */
	public void jieShuChpai(String p) {
		//运行人机出牌
		
		
	}
}
