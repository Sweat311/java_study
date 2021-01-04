package com.yc.sanguosha;
/*
*初始化类*
*读取武将名称
*读取牌面内容
*/
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Initial {
	public static String[] name = new String[5];                  //武将名字的string数组
	public static String[] cardKey = new String[88];             //牌面内容的string数组
	public static Card[] card1 = new Card[88];                      //88张牌
	public static List<Card> listCard = new ArrayList<Card>();      //牌堆集合
	public static List<Card> throwlistCard = new ArrayList<Card>(); //废弃牌堆
	public static Card tmpCheckCard;                                //存放判定牌的临时变量
	//TODO 要构造四个AI
	public static Character cAI ;                     //AI新建武将---测试 
	public static PlayerAI p2 ;   //AI玩家构造
	public static boolean who = true;                                      //决定轮流次序，T为玩家，F为AI；初始为T
	
	public static HashMap<String, String> hmp = new HashMap<String, String>();    //牌的键值对<Card.name,index>
	public static HashMap<String,Character> hm = new HashMap<String,Character>(); //人物的键值对

	
	public static void setCardKey() throws Exception{                 //从cardkey.cdk文件中读取牌面内容
		FileInputStream fis = new FileInputStream("./cardkey.cdk");
		byte[] buff = new byte[1024];
		int r = 0;
		String str = new String();
		while((r=fis.read(buff))>0){                                  //将读取的内容存放在str临时字符串里
			str=new String(buff,0,r);
		}
		cardKey = str.split("，");                                    //用split方法，分割str赋值给静态数组cardkey
		fis.close();
	}
	 // 静态方法，初始化牌组
	 // 先给88张牌赋值，然后洗牌
	public static void cardInit() throws Exception {                                    
		Initial.setCardKey();
		for (int i = 0; i < 88; i++) {
			Initial.card1[i] = new Card();
			Initial.card1[i].key=Initial.cardKey[i];
			Initial.listCard.add(Initial.card1[i]);
		}
		//88张牌赋值
			int tmp = 0;
			for(int k = 0;k<2;k++){
				for (int i = 0; i < 11; i++) {
					for (int j = 0; j < 4; j++) {
						switch (j) {
						case 0:
							Initial.card1[tmp].setColor("黑桃");
							break;
						case 1:
							Initial.card1[tmp].setColor("红心");
							break;
						case 2:
							Initial.card1[tmp].setColor("梅花");
							break;
						case 3:
							Initial.card1[tmp].setColor("方块");
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
			/*//打印出来,测试用
			for (int i = 0; i < Initial.listCard.size(); i++) {
				
				System.out.println("第" + (i + 1) + "张" + "\t"
						+ Initial.listCard.toArray()[i]);
			}*/
	}
	public static void list(){ //********************************     //打印武将列表以供选择
		Initial.line();
		System.out.println("=======欢迎来到简易版控制台三国杀=======");
		Initial.line();
		name[0]= "曹操";
		name[1]= "张飞";
		name[2]= "关羽";
		name[3]= "吕蒙";
		name[4]= "黄忠";
		System.out.println("请输入序号选择武将...");
		for(int i=0;i<name.length ;i++){
			System.out.println((i+1)+"--"+name[i]);
		}
		System.out.print("=>");
	} 
	
	public static void setKeyMap(){//键值对hmp中put进牌的key
		//TODO index在这 牌的键值对<Card.name,index>
		hmp.put("杀", "1");
		hmp.put("闪", "2");
		hmp.put("桃", "3");
		hmp.put("决斗", "4");
		hmp.put("无懈可击", "5");
		hmp.put("桃园结义", "6");
		hmp.put("万箭齐发", "7");
		hmp.put("南蛮入侵", "8");
		hmp.put("无中生有", "9");
		hmp.put("过河拆桥", "10");
		hmp.put("顺手牵羊", "11");
		
		//TODO 青虹剑,仁王盾
		hmp.put("青虹剑", "12");
		hmp.put("诸葛连弩", "13");
		hmp.put("贯石斧", "14");
		hmp.put("丈八蛇矛", "15");
		hmp.put("方天画戟", "16");
		hmp.put("仁王盾", "17");
		
		
		hmp.put("赤兔", "18");
		hmp.put("大宛", "19");
		hmp.put("的卢", "20");
		
	}
	
	//**********Map 武将和对象的键值表，供玩家用来选择*******************
	public static void putCharacter(){
		hm.put("曹操", new CaoCao());
		hm.put("张飞", new ZhangFei());
		hm.put("关羽", new GuanYu());
		hm.put("吕蒙", new LvMeng());
		hm.put("黄忠", new HuangZhong());
	
	//...
	//...
	//****************************************************************
	}
	//打印分割线
	public static void line(){
		System.out.println("========================================");
	}
}
	