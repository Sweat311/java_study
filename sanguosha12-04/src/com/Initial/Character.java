package com.Initial;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//人物（武将）类，继承玩家Player类，同时又是Player类的属性，这样的设计可能有不合理处
//只要不出现致命问题，就先坚持这样的结构

public class Character extends Player {
	private String name;// 名字
	private int maxhp; // 人物血量的上限，文3武4
	private String shengFe;//人物身份
	public int getMaxhp() {
		return maxhp;
	}

	private boolean skipUseCard; // 是否跳过出牌

	// *****************************【6个阶段，不同的子类将根据技能原因分别重写其中的某些】****************************************
	// 回合开始阶段
	public void Start(Player who, Player towho) {
		System.out.println("   ****** 轮到" + who.toString() + "出牌 ******");
		
		System.out.println("回合开始阶段..." + "\n");
		// 允许出杀
		who.setSha(false);
		// 允许使用主动技能
		who.setHasUsedSkill(false);
		// 清除裸衣效果
		who.setLuoYi(false);
	}

	// 判定阶段
	public void Check(Player who, Player towho) {
		System.out.println("判定阶段...");
		System.out.println();
		// 洛神判定
		who.getCharacter().checkOnAddCard(who, towho);
		// 延时锦囊判定
		for (int i = 0; i < who.getMagicCard().size(); i++) {
			// 闪电
			if (who.getMagicCard(i).key.equals("闪电")) {
				System.out.println("判定闪电：");
				// 从牌堆中翻出第一张，放入临时集合
				Initial.tmpCheckCard = Initial.listCard.get(0);
				Initial.listCard.remove(0);// 从牌堆中移除；
				// 此处添加替代牌效果（司马懿） 将tmp传进去处理 ，双方人物都触发一次
				Initial.tmpCheckCard = who.getCharacter().insteadCard(who,
						Initial.tmpCheckCard);
				Initial.tmpCheckCard = towho.getCharacter().insteadCard(towho,
						Initial.tmpCheckCard);
				System.out.println("闪电的判定牌：" + Initial.tmpCheckCard.toString());
				// 花色+数字的判定
				boolean succeed;
				try {
					int numOfCard = Integer.parseInt(Initial.tmpCheckCard
							.getNum());
					succeed = Initial.tmpCheckCard.getColor().equals("黑桃")
							&& numOfCard >= 2 && numOfCard <= 9;
					if (succeed == true) {
						System.out.println("判定生效！" + who.toString() + "扣3点血！");
						who.setHp(who.getHp() - 3);
						who.checkHPNull();
						who.printHp();
					} else {
						System.out.println("判定无效，闪电移交给" + towho.toString());
						towho.addMagicCard(who.getMagicCard(i));
					}
					// 这里之所以写个catch，是因为如果遇到JQKA，转化整数时候会出异常
					// 因此一旦出现异常，意味着JQKA出现，即不符合判定（2-9）
				} catch (Exception e) {
					System.out.println("判定无效，闪电移交给" + towho.toString());
					towho.addMagicCard(who.getMagicCard(i));
					towho.setShanDian(true);
				}
				// 如何处理判定牌
				who.getCharacter().doWithCheckCard(who, Initial.tmpCheckCard,
						true);

			}
			// 乐不思蜀
			if (who.getMagicCard(i).key.equals("乐不思蜀")
					|| who.isLeBuSiShu() == true) {
				System.out.println("判定乐不思蜀：");
				if (who.requestWuXie(towho) == true) {
					System.out.println("乐不思蜀被否决！");
					this.setSkipUseCard(false);
					continue;
				} else if (who.checkColor(who, "红心", null, towho) == true) {
					System.out.println("乐不思蜀无效，可以出牌！");
					this.setSkipUseCard(false);
				} else {
					this.setSkipUseCard(true);
					System.out.println("乐不思蜀生效，跳过出牌！");
				}
			}
		}
		// 清空
		who.setLeBuSiShu(false);
		who.setShanDian(false);
		who.magicCardCls();
	}

	// 摸牌阶段
	public void AddCard(Player who, int num, Player towho) {
		// System.out.println("摸牌阶段...");
		System.out.print(who.toString() + "摸牌：");
		for (int i = 0; i < num; i++) {
			ArrayList<Card> tmpc = new ArrayList<Card>();// 创建临时集合tmpc
			tmpc.addAll(0, who.getHandcard()); // 将手牌全部放到临时集合中
			// 牌堆检测，如果没牌了，将废弃牌堆中的牌add进去
			if (Initial.listCard.size() == 0) {
				Initial.listCard.addAll(Initial.throwlistCard);
				Initial.throwlistCard.clear();
			}
			tmpc.add(Initial.listCard.get(0)); // 获取牌堆中的第一张，放进临时集合
			System.out.print("[" + Initial.listCard.get(0) + "]"); // 显示摸到的牌
			Initial.listCard.remove(0); // 牌堆移除摸到的牌
			who.setHandcard(tmpc); // 将临时集合tmpc设置为当前手牌
			who.reSetKongCheng();
		}

		System.out.println("\n");
	}

	// 出牌阶段
	@SuppressWarnings("resource")
	public void UseCard(Player who, Player towho) {
		System.out.println("出牌阶段...");
		// 乐不思蜀判定
		if (who.getCharacter().isSkipUseCard() == true) {
			who.getCharacter().setSkipUseCard(false);
			return;
		}
		// 显示操作列表
		// 接受输入参数
		// 检查是否有效；无效则返回
		// 执行所对应的方法 switch
		// 循环，直到选择弃牌；
		if (who.getType() == "(AI)") {
			// ****如果轮到AI，此处调用AI的方法。****
			int n = who.getHandcard().size();
			for (int i = 0; i < n; i++) {
				if (n == 0) {
					return;
				}
				// 预打出牌，临时变量，tmpCard，存储打出的牌，无效则放回
				int r = new Random().nextInt(who.getHandcard().size());
				who.setTmpCard(who.getHandcard().get(r));
				// 这里如此复杂，是因为直接调用手牌删除方法会触发某些技能
				ArrayList<Card> tmp = new ArrayList<Card>();
				tmp.addAll(who.getHandcard());
				tmp.remove(r);
				who.setHandcard(tmp);
				// 调用Card的use方法
				System.out.println(who.toString() + "出牌："
						+ who.getTmpCard().toString());
				if (who.getTmpCard().Use(who, towho)) {
					// 成功则继续
					System.out.println();
					try {
						Thread.sleep(999);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				} else {
					// 若不能打，将预打出的牌放回
					System.out.println("无效出牌 - -！");
					who.addhandCard(who.getTmpCard());//放回手牌里
				}
				try {
					Thread.sleep(999);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return;
		}
		while (true) { // 玩家出牌过程，一直可以出牌，直到选择弃牌或者无牌

			System.out.println(who.toString() + "出牌..." + "\n");
			who.printHandCard();
			System.out
					.println("\n"+"输入序号来出牌"+"\n"+"0为不出牌pass，-1为发动武器技能，-2为发动人物主动技能，10086查询双方状态");
			System.out.print("=>");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			try { // 参数输入需做异常处理
				int r = sc.nextInt();
				// int参数，表示输入的序列号[！！由于显示序列时候0作为弃牌，所有序号都+1显示，在处理时要-1]
				if (r == 0) { // 参数0为不出牌
					return;
				}
				if (r == -1) {
					System.out.println("目前没有可用的武器技能");
				} else if (r == -2) {
					who.getCharacter().useSkill(who, towho);
				} else if (r == 10086) {
					who.printInfo();
					towho.printInfo();
				} else {
					System.out.println(who.toString() + "出牌："
							+ who.getHandcard().get(r - 1).toString());
					// 预打出牌，临时变量，tmpCard，存储打出的牌，无效则放回
					who.setTmpCard(who.getHandcard().get(r - 1));
					// 这里如此复杂，是因为直接调用手牌删除方法会触发某些技能
					ArrayList<Card> tmp = new ArrayList<Card>();
					tmp.addAll(who.getHandcard());
					tmp.remove(r - 1);
					who.setHandcard(tmp);
					// 调用Card的use方法
					if (who.getTmpCard().Use(who, towho)) {
						// 成功则不做处理
						System.out.println();
					} else {
						// 若不能打，将预打出的牌放回
						who.addhandCard(who.getTmpCard());
						System.out.println("此牌现在不能出！");
					}
				}

			} catch (Exception e) {// 输入出现异常，无效处理
				System.out.println("错误的输入，请重新输入！");
				e.printStackTrace();
			}
			// 无牌的时候，触发无牌的技能，若没有触发效果，返回true，表示确实无牌，退出
			if (who.getHandcard().size() <= 0
					&& who.getCharacter().noCard(who) == true) {
				System.out.println("无牌，回合结束...");
				return;
			}
		}

	}

	// *********弃牌阶段
	public void ThrowCard(Player who) {
		// 血量和手牌数检查，合法则跳过；
		if (who.getHp() >= who.getHandcard().size()) {
			System.out.println("不需要弃牌");
			return;
		}
		// 提示需要丢掉 (this.getHandcard().size()-this.hp)张手牌；
		int n = who.getHandcard().size() - who.getHp();
		System.out.println(who.toString() + "弃牌阶段..." + "需要丢掉" + n + "张手牌");
		// 开始一张张弃牌
		for (int i = 0; i < n; i++) {
			// --AI自动随机弃牌---
			if (who.getType().equals("(AI)")) {
				int r = new Random().nextInt(who.getHandcard().size() - 1);// 创建随机数0~手牌上限
				System.out.println(who.getCharacter().getName() + "弃牌："
						+ who.getHandcard().get(r));
				Card ctmp = new Card(); // 新建一个临时card
				ctmp = who.getHandcard().get(r); // 临时card存放要移除的手牌
				// 删除要丢的手牌
				who.removeCard(r);
				Initial.throwlistCard.add(ctmp); // 将丢掉的手牌放入废弃牌堆
				continue;
			}
			// ------玩家手动输入选择弃牌（一张一张丢）------------
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("输入要丢掉的手牌序号：");
				who.printHandCard(); // 将手牌打印出来，显示序号，以方便输入，序号显示都+1处理；
				int r = sc.nextInt();
				System.out.println(who.toString() + "弃牌："
						+ who.getHandcard().get(r - 1));
				Initial.throwlistCard.add(who.getHandcard().get(r - 1)); // 将丢掉的手牌放入废弃牌堆
				who.removeCard(r - 1); // 显示序号有+1；所以处理时候参数要-1
			} catch (Exception e) {
				i--;
				System.out.println("输入错误！重新输入！");
				e.printStackTrace();
			}
		}
	}

	// 回合结束
	public void End(Player who, Player towho) {
		Initial.who = !(Initial.who);
		System.out.println(this.name + "的回合结束");
		System.out.println();
	}

	// 人物主动技能，默认为空，某些人物重写
	public void useSkill(Player who, Player towho) {
		System.out.println("目前没有可用的主动技能");
	}

	// 某些可以变牌的被动技能方法，默认为空，具体的人物会重写；【可以用个参数？】
	public boolean changeCard(Card c, String key) {
		return false;
	}

	// 无牌方法，用于给诸葛亮，陆逊等人重写，作为技能用
	public boolean noCard(Player p) {
		return false; // 子类重写发动技能效果后会返回false
	}

	// 是否出过杀，默认为true,在杀之后调用，张飞重写此方法
	public boolean haveYouSha() {
		return true;
	}

	// 在闪过之后的额外条件，主要用来给吕布重写
	public boolean shaWithOtherCheck(Player p, Player top) {
		return true;
	}

	// 额外伤害方法,返回加成数值； 许褚重写
	public int hurtAdd(Player p, Player pHurt) {
		return 0;
	}

	// 强杀事件，马超重写
	public boolean ignoreShan(Player p, Player top) {
		return false;
	}

	// 成功使用锦囊后的触发，黄月英重写
	public void useJinNang(Player p) {

	}

	// 受伤后触发事件，第一个参数为受伤者，第二个为造成伤害来源
	public void afterHurt(Player p, Player pSha) {

	}

	// 摸牌判定方法，甄姬重写
	public void checkOnAddCard(Player p, Player top) {

	}

	// 处理判定牌，默认空，有些人物可以获得
	// 参数1 判定者 ；参数2 判定牌 ；参数3 是否判定成功 默认丢进废弃堆
	public void doWithCheckCard(Player p, Card c, boolean succeed) {
		Initial.throwlistCard.add(c);
	}

	// 人物替换判定牌,司马懿重写
	// 将判定牌作为参数传进来，默认不作任何改变传回去
	public Card insteadCard(Player p, Card c) {
		return c;
	}

	// 装备卸载事件
	public void eqiupUnload(Player p, Player top) {
		
	}

	// ***********************************【属性的get和set】*******************************************************
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getmaxHp() {
		return maxhp;
	}

	public void setmaxHp(int hp) {
		this.maxhp = hp;
	}

	public String getShengFe() {
		return shengFe;
	}

	public void setShengFe(String shengFe) {
		this.shengFe = shengFe;
	}


	public boolean isSkipUseCard() {
		return skipUseCard;
	}

	public void setSkipUseCard(boolean skipUseCard) {
		this.skipUseCard = skipUseCard;
	}
}

