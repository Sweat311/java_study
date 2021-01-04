package com.yc.sanguosha;
import java.util.ArrayList;
import java.util.Scanner;

//************玩家Player类*******************
public class Player {
	public  Character character; // 玩家选择的人物 ，存放各种方法
	private String type; // 玩家类型，AI或者人,仅用于打印
	//TODO 人物身份
	
	private int hp; // 人物的当前血量，不能超过上限character.maxhp
	private ArrayList<Card> handcard = new ArrayList<Card>();// 集合，存储手牌
	private Card[] eqiup = new Card[4]; // 装备区域
	private int disAtt; // 攻击距离
	private int disDef; // 防御距离
	private boolean isSha; // 判断是否出过杀
	private boolean hasUsedSkill;// 判断是否用过主动技能（针对某些一回合放一次的）
	private boolean isLuoYi; // 是否处于裸衣状态
	private boolean isLeBuSiShu;// 是否中乐
	private boolean isShanDian;// 是否中闪电
	private boolean isKongCheng;// 是否空城
	private Card tmpCard; // 临时区  每次出牌，临时区都放一下
	private ArrayList<Card> magicCard = new ArrayList<Card>();; // 存放延时性锦囊
	
	
	public Player() {
	}

	// 带参数构造方法，设置所选武将,血量上限,攻击距离,类型
	public Player(Character character, int hp) {
		this.character = character;
		this.hp = hp;
		this.disAtt = this.getCharacter().getDisAtt();
		this.type = "(玩家)"; // AI会重写
	}

	//TODO 回合制要改成五人
	// 回合中的6阶段，参数1为出牌者，参数2为目标对象
	public void turn(Player p, Player toP) throws InterruptedException {
		this.getCharacter().Start(p, toP);
		Thread.sleep(999);
		Initial.line();
		this.getCharacter().Check(p, toP);
		Thread.sleep(999);
		Initial.line();
		this.getCharacter().AddCard(p, 2, toP);
		Thread.sleep(999);
		Initial.line();
		this.getCharacter().UseCard(p, toP);
		Thread.sleep(999);
		Initial.line();
		this.getCharacter().ThrowCard(p);
		Thread.sleep(999);
		Initial.line();
		this.getCharacter().End(p, toP);
		Thread.sleep(999);
	}

	// 杀 ，pSha表示出杀者；p表示杀的目标******************************************
	public boolean sha(Player pSha, Player p) {
		// 如果有连弩则取消开关
		if (pSha.getEqiup(0) != null && pSha.getEqiup(0).key.equals("诸葛连弩")) {
			this.isSha = false;
		}
		// 如果已经出过杀，则无效
		if (this.isSha && this.getCharacter().haveYouSha()) {
			if(this.getType().equals("(玩家)")){
				System.out.println("你已经出过杀，不能重复出杀");
			}
			return false;
		}
		// 如果对方免疫（空城），则无效
		if (p.isKongCheng()) {
			if(this.getType().equals("(玩家)")){
			System.out.println("对方空城，无法杀或决斗");
			}
			return false;
		}
		// 如果距离不够，则无效
		if (pSha.disAtt < p.disDef ) {
			if(this.getType().equals("(玩家)")){
			System.out.println("距离不够，杀不到");
			}
			return false;
		}
		// 符合出杀条件，显示玩家出杀
		System.out.println(pSha.toString() + "对" + p.toString() + "出杀...");
		// 先进行强制伤害判定（马超和贯石斧）
		// 再进入对方响应闪
		// 最后是额外出闪条件（吕布）
		if (pSha.getCharacter().ignoreShan(pSha, p)
				|| p.requestshan(p, pSha) == false
				|| pSha.getCharacter().shaWithOtherCheck(pSha, p) == false) { // 如果出闪的结果requestshan()返回为false，目标扣1点血
			// 若杀起效果，进入扣血阶段，则调用伤害加成技能触发
			int dam = pSha.getCharacter().hurtAdd(pSha, p) + 1;
			p.setHp(p.getHp() - dam);
			// 打印扣血信息
			System.out.println(p.toString() + "扣血" + dam + "点");
			System.out.print(p.toString() + "当前血量：");
			for (int i = 0; i < p.getHp(); i++) {
				System.out.print("●");
			}
			System.out.println(); // 打印出血量显示
			// check对方血量；注意 之后凡是写到扣血的地方 ，都做血量检测
			p.checkHPNull();
			// 对方扣血后触发技能,第一个参数是受伤者，第二个参数是凶手，注意顺序
			p.getCharacter().afterHurt(p, pSha);
		}

		pSha.isSha = pSha.getCharacter().haveYouSha(); // 开关，限制出杀

		return true;
	}

	// 初始发的4张牌
	public void giveCard(Player who) {
		System.out.println("发牌给：" + who.toString());
		for (int i = 0; i < 4; i++) {
			ArrayList<Card> tmpc = new ArrayList<Card>();
			tmpc.addAll(0, this.getHandcard());
			tmpc.add(Initial.listCard.get(0));
			System.out.print("[手牌" + (i + 1) + "]" + "\t");
			Initial.listCard.remove(0);
			who.setHandcard(tmpc);

		}
		System.out.println();
		// 这里都是测试时候用的 （限量版的红心S！） 
		//  
		//this.setEqiup(new Card("红心", "S", "八卦阵"), 1);
		/*
		 * this.addhandCard(new Card("黑桃","S","无懈可击")); this.addhandCard(new
		 * Card("红心","S","决斗")); this.setEqiup(new Card("红心","S","八卦阵"), 1);
		 * this.magicCard.add(new Card("红心","S","乐不思蜀"));
		 */
	}

	// 被要求出闪 ，进行检测
	public boolean requestshan(Player p, Player top) {
		// 检查是否有八卦
		if (this.getEqiup(1) != null ) {
			boolean b = this.useBaGua(this, top);
			if (b == true) {
				System.out.println("八卦阵生效！");
				return b;
			} else {
				System.out.println("八卦阵无效，需要出闪。");
			}
		}
		// 检查是否有技能，是则调用技能方法，成功返回true
		// 检查是否有闪，遍历手牌，询问是否出该张闪，直到没有
		for (int i = 0; i < this.handcard.size(); i++) {
			if (this.getHandcard().get(i).key.equals("闪")
					|| this.getCharacter().changeCard(
							this.getHandcard().get(i), "闪")) {// 此处加一个or
				// 是否技能变牌有效果
				System.out.println(this.getHandcard().get(i).toString());
				System.out.println("是否打出此张牌作闪？（1为确定；0为取消）");
				Scanner sc = new Scanner(System.in);
				int r = sc.nextInt();
				if (r == 0) {
					continue;
				}
				if (r == 1) {
					this.setTmpCard(this.getHandcard().get(i));
					System.out.println(this.toString()+"出闪："+this.getTmpCard().toString());
					Initial.throwlistCard.add(this.getHandcard().get(i));
					this.removeCard(i);
					// 无牌检测
					if (this.getHandcard().size() == 0) {
						this.getCharacter().noCard(this);
					}
					return true;
				}
			}
		}
		// 上述都无，返回false
		System.out.println(this.toString() + "不出闪...");
		return false;
	}

	// 被要求出杀，进行检测
	public boolean requestSha() {
		System.out.println(this.getType() + this.getCharacter().getName()
				+ "需要出杀");
		for (int i = 0; i < this.handcard.size(); i++) {
			if (this.getHandcard().get(i).key.equals("杀") // 若牌面key值为“杀”
					|| this.getCharacter().changeCard(
							this.getHandcard().get(i), "杀")) {// 或者
				// 人物有变牌替代效果
				System.out.println("是否出该牌作杀？"
						+ this.getHandcard().get(i).toString()
						+ "（0表示取消，1表示确定）");
				Scanner sc = new Scanner(System.in);
				int r = sc.nextInt();
				if (r == 0) {
					continue;
				}
				if (r == 1) {
					// 确认出牌，将要出的牌，放入临时区
					this.setTmpCard(this.getHandcard().get(i));
					System.out.println(this.toString()+"出杀："+this.getTmpCard().toString());
					Initial.throwlistCard.add(this.getHandcard().get(i));
					this.removeCard(i);
					// 无牌检测
					if (this.getHandcard().size() == 0) {
						this.getCharacter().noCard(this);
					}
					return true;
				}
			}
		}
		System.out.println(this.toString() + "不出杀...");
		return false;
	}

	// 响应无懈可击，参数为被否决的目标
	public boolean requestWuXie(Player top) {
		for (int i = 0; i < this.handcard.size(); i++) {
			if (this.getHandcard().get(i).key.equals("无懈可击")) {// 此处加一个or
																// 是否技能变牌有效果
				System.out.println("是否出该张无懈可击？"
						+ this.getHandcard().get(i).toString()
						+ "（0表示取消，1表示确定）");
				Scanner sc = new Scanner(System.in);
				int r = sc.nextInt();
				if (r == 0) {
					continue;
				}
				if (r == 1) {
					Initial.throwlistCard.add(this.getHandcard().get(i));
					this.removeCard(i);
					// 无牌检测
					if (this.getHandcard().size() == 0) {
						this.getCharacter().noCard(this);
					}
					if (top.requestWuXie(this) == true) {
						System.out.println(this.toString() + "的无懈可击被"
								+ top.toString() + "的无懈可击否决！");
						return false;
					}
					System.out.println(this.toString()+"打出：无懈可击！");
					return true;
				}
			}
		}
		return false;
	}

	//TODO 优化桃可以救濒死
	// 求桃,只问自己，不提供他救方法
	public boolean requestTao() {
		for (int i = 0; i < this.handcard.size(); i++) {
			if (this.getHandcard().get(i).key.equals("桃")
					|| this.getCharacter().changeCard(
							this.getHandcard().get(i), "桃")) {// 此处加一个or
																// 是否技能变牌有效果
				// AI处理
				if (this.getType().equals("(AI)")) {
					this.tao();
					Initial.throwlistCard.add(this.getHandcard().get(i));
					this.removeCard(i);
					// 无牌检测
					if (this.getHandcard().size() == 0) {
						this.getCharacter().noCard(this);
					}
					return true;
				}
				System.out.println("是否出这张牌作桃："
						+ this.getHandcard().get(i).toString()
						+ "（0表示取消，1表示确定）");
				// 玩家处理
				Scanner sc = new Scanner(System.in);
				int r = sc.nextInt();
				if (r == 0) {
					continue;
				}
				if (r == 1) {
					this.tao();
					Initial.throwlistCard.add(this.getHandcard().get(i));
					this.removeCard(i);
					// 无牌检测
					if (this.getHandcard().size() == 0) {
						this.getCharacter().noCard(this);
					}
					return true;
				}
			}
		}
		return false;
	}

	// 桃 index表示手牌序号
	public void tao() {
		System.out.println(this.toString() + "用桃成功，增加1点血量！");
		this.setHp(this.getHp() + 1);
		this.printHp();
	}

	// 手牌删除方法
	public void removeCard(int index) {
		this.handcard.remove(index); // 删除要丢的手牌
		if (this.handcard.size() == 0) {
			this.getCharacter().noCard(this); // 手牌删除后为0，则调用无牌方法，在某些场合会触发技能
		}
	}

	// 打印手牌
	public void printHandCard() {
		System.out.println(this.toString() + "当前手牌：");
		for (int i = 0; i < this.getHandcard().size(); i++) {
			System.out.print((i + 1) + "--" + "["
					+ this.getHandcard().toArray()[i] + "]" + " \t ");
		}
		System.out.println();
	}

	// 打印血量方法
	public void printHp() {
		System.out.print(this.toString() + "当前血量：");
		for (int i = 0; i < this.getHp(); i++) {
			System.out.print("●");
		}
		System.out.println(); // 打印出血量显示
	}

	// 打印信息方法***************
	public void printInfo() {
		Initial.line();
		// 打印血量
		this.printHp();
		// 打印手牌
		this.printHandCard();
		// 打印装备
		for (int i = 0; i < 4; i++) {
			if (this.getEqiup(i) == null) {
				System.out.print("装备" + (i + 1) + ":无" + "；");
			} else {
				System.out.print("装备" + (i + 1) + ":"
						+ this.getEqiup(i).toString() + "；");
			}
		}
		System.out.println();
		// 打印判定牌
		if (this.getMagicCard().size() == 0) {
			System.out.println("判定牌：无");
		} else {
			System.out.print("判定牌：");
			for (int i = 0; i < this.getMagicCard().size(); i++) {
				System.out.print(this.getMagicCard(i).toString() + " ");
			}
			System.out.println();
		}
		Initial.line();
		System.out.println("");
	}

	// 血量增加方法，无参数，调用即+1
	public void addHp() {
		this.hp = this.hp + 1;
		this.checkHPNull();
	}

	// 空血检测，若为真则结束游戏;在每次造成伤害的情况下，调用此方法
	public void checkHPNull() {
		while (this.getHp() <= 0) {
			System.out.println("需要出" + (Math.abs(this.getHp()) + 1) + "个桃");
			if (this.requestTao() == false) { // 若求桃成功
				System.out.println(this.toString() + "无桃！");
				System.out.println(this.toString() + "阵亡...");
				Initial.line();
				System.out.println("游戏结束，感谢您的参与！");
				System.exit(0);
			}
		}
	}

	// 选择对面手牌的方法，返回int，AI重写
	public int selectCard(Player top) {
		System.out.println("请选择" + top.toString() + "手牌序号：");
		top.printHandCard();
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		return r;
	}

	// 要求选牌
	// 返回的整数已经做过-1处理 ，可以直接用
	public int selectCardIndex(int size) {
		System.out.println("请选择，输入1到" + size + "之间的数字，0表示取消（五谷丰登不能取消）");
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt() - 1;
		return r;

	}

	// 手牌增加方法
	public void addhandCard(Card c) {
		this.handcard.add(c);
		this.reSetKongCheng();
	}
	// 手牌插入式增加方法
	public void addhandCard(Card c,int index) {
		this.handcard.add(index,c);
		this.reSetKongCheng();
	}
	// 花色判定方法,翻开显示-->判断花色-->返回结果；
	// 由于判定牌可能需要被玩家获得，所以此处先不从牌堆删除，延迟到后面的方法里执行
	public boolean checkColor(Player p, String color, String color2, Player top) {
		// 从牌堆中取出第一张，放入临时集合
		Initial.tmpCheckCard = Initial.listCard.get(0);
		Initial.listCard.remove(0);// 从牌堆中移除；
		// 此处添加替代牌效果（司马懿） 将tmp传进去处理 ，双方人物都触发一次
		Initial.tmpCheckCard = p.getCharacter().insteadCard(p,
				Initial.tmpCheckCard);
		Initial.tmpCheckCard = top.getCharacter().insteadCard(top,
				Initial.tmpCheckCard);
		System.out.println("判定牌：" + Initial.tmpCheckCard.toString());
		boolean succeed = Initial.tmpCheckCard.getColor().equals(color)
				|| Initial.tmpCheckCard.getColor().equals(color2);
		// 如何处理判定牌
		p.getCharacter().doWithCheckCard(p, Initial.tmpCheckCard, succeed);
		return succeed;
	}

	// 触发八卦判定
	public boolean useBaGua(Player p, Player top) {
		System.out.println(p.toString() + "发动八卦阵");
		return p.checkColor(p, "红心", "方块", top);
	}

	// 重写toString方法,返回"(玩家/AI)"
	public String toString() {
		return this.getType() + this.getCharacter().getName();
	}

	// 设置手牌
	public void setHandcard(ArrayList<Card> handcard) {
		this.handcard.clear();
		this.handcard.addAll(handcard);
		//this.handcard.trimToSize();
	}
	//重置空城状态，在任何获得手牌的情况下调用
	public void reSetKongCheng(){
		if(this.getHandcard().size()>0){
			this.setKongCheng(false);
		}
	}
	
	// ***************************************【属性的获取和设置】************************************************************
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public ArrayList<Card> getHandcard() {
		return handcard;
	}

	public Card getEqiup(int index) {
		return eqiup[index];
	}

	public void setEqiup(Card eqiup, int index) {
		this.eqiup[index] = eqiup;
	}

	public int getDisAtt() {
		return disAtt;
	}

	public void setDisAtt(int disAtt) {
		this.disAtt = disAtt;
	}

	public int getDisDef() {
		return disDef;
	}

	public void setDisDef(int disDef) {
		this.disDef = disDef;
	}

	public boolean isSha() {
		return isSha;
	}

	public void setSha(boolean isSha) {
		this.isSha = isSha;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasUsedSkill() {
		return hasUsedSkill;
	}

	public void setHasUsedSkill(boolean hasUsedSkill) {
		this.hasUsedSkill = hasUsedSkill;
	}

	public Card[] getEqiup() {
		return eqiup;
	}

	public void setEqiup(Card[] eqiup) {
		this.eqiup = eqiup;
	}

	public boolean isLuoYi() {
		return isLuoYi;
	}

	public void setLuoYi(boolean isLuoYi) {
		this.isLuoYi = isLuoYi;
	}

	public boolean isLeBuSiShu() {
		return isLeBuSiShu;
	}

	public void setLeBuSiShu(boolean isLeBuSiShu) {
		this.isLeBuSiShu = isLeBuSiShu;
	}

	public boolean isShanDian() {
		return isShanDian;
	}

	public void setShanDian(boolean isShanDian) {
		this.isShanDian = isShanDian;
	}

	public boolean isKongCheng() {
		return isKongCheng;
	}

	public void setKongCheng(boolean isKongCheng) {
		this.isKongCheng = isKongCheng;
	}

	public Card getTmpCard() {
		return tmpCard;
	}

	public void addMagicCard(Card c) {
		this.magicCard.add(c);
	}

	public void setTmpCard(Card tmpCard) {
		this.tmpCard = tmpCard;
	}

	public Card getMagicCard(int index) {
		return magicCard.get(index);
	}

	public void setMagicCard(Card magicCard, int index) {
		this.magicCard.set(index, magicCard);
	}

	public ArrayList<Card> getMagicCard() {
		return magicCard;
	}

	public void setMagicCard(ArrayList<Card> magicCard) {
		this.magicCard = magicCard;
	}

	public void removeMagicCard(int index) {
		this.magicCard.remove(index);
	}

	public void magicCardCls() {
		this.magicCard.clear();
	}

}
