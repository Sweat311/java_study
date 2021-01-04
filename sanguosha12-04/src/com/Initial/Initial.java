package com.Initial;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 初始化类
 * 
 * @author 浪痞小霸王队
 *
 */
public class Initial {
	public static String[] name = new String[5]; // 武将名字的string数组
	public static String[] cardKey = new String[88]; // 牌面内容的string数组
	public static Card[] card1 = new Card[88]; // 88张牌
	public static List<Card> listCard = new ArrayList<Card>(); // 牌堆集合
	public static List<Card> throwlistCard = new ArrayList<Card>(); // 废弃牌堆
	public static Card tmpCheckCard; // 存放判定牌的临时变量
	// 四个AI
	public static Character cAI1;
	public static Character cAI2;
	public static Character cAI3;
	public static Character cAI4;
	
	public static Player p1;
	// 四个AI角色
	public static PlayerAI p2; // AI玩家构造，p1被玩家占据
	public static PlayerAI p3;
	public static PlayerAI p4;
	public static PlayerAI p5;
	// 决定轮流次序，T为玩家，F为AI；初始为T
	public static boolean who = true;

	public static HashMap<String, String> hmp = new HashMap<String, String>(); // 牌的键值对<Card.index,index>
	public static HashMap<String, Character> hm = new HashMap<String, Character>(); // 人物的键值对
	

	/**
	 * 读取卡片内容
	 * 
	 * @throws Exception
	 */
	public static void setCardKey() throws Exception {
		// 从cardkey.cdk文件中读取牌面内容
		FileInputStream fis = new FileInputStream("cardkey1.cdk");
		byte[] buff = new byte[1024];
		int r = 0;
		// 将读取的内容存放在str临时字符串里
		String str = new String();
		while ((r = fis.read(buff)) > 0) {
			str += new String(buff, 0, r);
		}
		// 分割str赋值给牌面内容的数组
		cardKey = str.split("，");
//		for (String c:cardKey) {
//			System.out.println(c);
//		}
		fis.close();
	}

	/**
	 * 静态方法，初始化牌组 先给88张牌赋值，然后洗牌,牌堆是listCard
	 * 
	 * @throws Exception
	 */
	public static void cardInit() throws Exception {
		Initial.setCardKey();
		for (int i = 0; i < 88; i++) {
			Initial.card1[i] = new Card();
			Initial.card1[i].key = Initial.cardKey[i];
			Initial.listCard.add(Initial.card1[i]);
		}
		// 88张牌赋值
		int tmp = 0;
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 4; j++) {
					switch (j) {
					case 0:
						//黑桃
						Initial.card1[tmp].setColor("/com/yc/image/color_heitao.png");
						break;
					case 1:
						//红心
						Initial.card1[tmp].setColor("/com/yc/image/color_hongxin.png");
						break;
					case 2:
						//梅花
						Initial.card1[tmp].setColor("/com/yc/image/color_meihua.png");
						break;
					case 3:
						//方块
						Initial.card1[tmp].setColor("/com/yc/image/color_fangkuai.png");
						break;
					}
					switch (i) {
					case 0:
						Initial.card1[tmp].setNum("A");
						break;
					case 10:
						Initial.card1[tmp].setNum("J");
						break;
					default:
						Initial.card1[tmp].setNum(java.lang.Integer.toString(i + 1));
						break;
					}
					tmp++;
				}
			}
		}
		for (int i = 0; i < Initial.card1.length; i++) {// 打乱顺序
			Random r = new Random();
			Card cardtmp = Initial.listCard.get(i);
			Initial.listCard.remove(i);
			Initial.listCard.add(r.nextInt(Initial.listCard.size()), cardtmp);
		}
		
		// 打印出来,测试用
		for (int i = 0; i < Initial.listCard.size(); i++) {
			System.out.println("第" + (i + 1) + "张" + "\t" + Initial.listCard.toArray()[i]);
		}
	}

	/**
	 * 牌的键值对hmp，，<card.image,index>
	 */
	public static void setKeyMap() {
		/**
		 * K_类
		 */
		// 杀---1
		hmp.put("/com/yc/image/K_sha.png", "1");
		// 闪---2
		hmp.put("/com/yc/image/K_shan.png", "2");
		// 桃---3
		hmp.put("/com/yc/image/K_tao.png", "3");

		/**
		 * J_类
		 */
		// 决斗---4
		hmp.put("/com/yc/image/J_juedou.png", "4");
		// 无懈可击---5
		hmp.put("/com/yc/image/J_wuxiekeji.png", "5");
		// 桃园结义---6
		hmp.put("/com/yc/image/J_taoyuan.png", "6");
		// 万箭齐发---7
		hmp.put("/com/yc/image/J_wanjian.png", "7");
		// 南蛮入侵---8
		hmp.put("/com/yc/image/J_nanman.png", "8");
		// 无中生有---9
		hmp.put("/com/yc/image/J_wuzhong.png", "9");
		// 过河拆桥---10
		hmp.put("/com/yc/image/J_guohe.png", "10");
		// 顺手牵羊---11
		hmp.put("/com/yc/image/J_shunshou.png", "11");
		/**
		 * L_类
		 */
		// 青刚剑---12
		hmp.put("/com/yc/image/L_qinggang.png", "12");
		// 诸葛连弩---13
		hmp.put("/com/yc/image/L_zugelingnu.png", "13");
		// 贯石斧---14
		hmp.put("/com/yc/image/L_guanshi.png", "14");
		// 丈八蛇矛---15
		hmp.put("/com/yc/image/L_zhangbashemao.png", "15");
		// 方天画戟---16
		hmp.put("/com/yc/image/L_fangtianhuaji.png", "16");
		// 仁王盾---17
		hmp.put("/com/yc/image/L_renwangdun.jpg", "17");
		/**
		 * G_类
		 */
		// 赤兔---18
		hmp.put("/com/yc/image/G_chitu.jpg", "18");
		// 大宛---19
		hmp.put("/com/yc/image/G_dawan.png", "19");
		// 的卢---20
		hmp.put("/com/yc/image/G_dilu.png", "20");

	}

	/**
	 * Map 武将和对象的键值表，供玩家用来选择
	 */
	public static void putCharacter() {
		// 曹操
		hm.put("/com/yc/image/曹操.jpg", new CaoCao());
		// 张飞
		hm.put("/com/yc/image/张飞.jpg", new ZhangFei());
		// 关羽
		hm.put("/com/yc/image/黄忠.jpg", new HuangZhong());
		// 吕蒙
		hm.put("/com/yc/image/吕蒙.jpg", new LvMeng());
		// 黄忠
		hm.put("/com/yc/image/关羽.jpg", new GuanYu());
	}

}