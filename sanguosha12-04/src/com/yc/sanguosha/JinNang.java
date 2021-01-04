package com.yc.sanguosha;

//锦囊类，存储各个锦囊的方法；
//创造一个带参数index的实例，根据index,调用相应的锦囊方法
public class JinNang {
	private int index;

	public JinNang() {
	}

	public JinNang(int index) {
		this.index = index;
	}

	public boolean useJinNang(Player p, Player top,Card card) {
		switch (this.index) { // index 是从上一层方法传来的参数，值域为4-15
		case 4: //决斗
			this.juedou(p, top);
			break;
		case 5://无懈可击
			this.wuxxiekeji(p, top);
			break;
		case 6://桃园结义
			this.taoyuanjieyi(p, top);
			break;
		case 7: // 万箭齐发
			this.wanjianqifa(p, top);
			break;
		case 8: // 南蛮入侵
			this.nanmanruqin(p, top);
			break;
		case 9: // 无中生有
			this.wuzhongshengyou(p, top);
			break;
		case 10: // 过河拆桥
			//目标没有手牌
			if(top.getHandcard().size()==0){
				if(p.getType().equals("(玩家)")){
					System.out.println("目标无手牌，不能使用！");
				}
				return false;
			}
			this.guohechaiqiao(p, top);
			break;
		case 11: // 顺手牵羊
			if(top.getHandcard().size()==0){
				if(p.getType().equals("(玩家)")){
					System.out.println("目标无手牌，不能使用！");
				}
				return false;
			}
			
				this.shunshouqianyang(p, top);
				break;
			
		}
		return true;
	}

	// ---------------------------------------------------------------------------------------------------------
	// 各个锦囊技能的具体方法
	// ---------------------------------------------------------------------------------------------------------
	// 决斗
	public void juedou(Player p, Player top) {
		System.out.println("");
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("决斗被否决！");
			return;
		}
		Player pTurn = top;//被决斗的玩家先出杀
		boolean b = false;
		while(pTurn.requestSha()==true){
			b=!b;
			pTurn = b?p:top;
		}
		System.out.println(pTurn.toString()+"扣血1点...");
		pTurn.setHp(pTurn.getHp()-1);
		pTurn.printHp();
		pTurn.checkHPNull();
		//交换出牌
		Player tmpPlayer = new Player();
		if(pTurn == p){
			tmpPlayer = top;
		}else{
			tmpPlayer = p;
		}
		pTurn.getCharacter().afterHurt(pTurn, tmpPlayer);
	}


	// 无懈可击
	public void wuxxiekeji(Player p, Player top) {

	}

	// 桃园结义
	public void taoyuanjieyi(Player p, Player top) {
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("桃园结义被否决！");
			return;
		}
		System.out.println("桃园结义，所有人加血...");
		if (p.getHp() < p.getCharacter().getmaxHp()) {
			p.addHp();
			p.printHp();
		}
		if (top.getHp() < top.getCharacter().getmaxHp()) {
			top.addHp();
			top.printHp();
		}
	}

	// 万箭齐发
	public void wanjianqifa(Player p, Player top) {
		System.out.println(top.getType() + top.getCharacter().getName()
				+ "需要出闪...");
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("万箭齐发被否决！");
			return;
		}
		if (top.requestshan(p,top) == false) {
			top.setHp(top.getHp() - 1);
			System.out.println(top.getType() + top.getCharacter().getName()
					+ "扣血...");
			top.printHp();
			top.checkHPNull();
			top.getCharacter().afterHurt(top, p);
		}
	}

	

	// 南蛮入侵
	public void nanmanruqin(Player p, Player top) {
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("南蛮入侵被否决！");
			return;
		}
		if (top.requestSha() == false) {
			top.setHp(top.getHp() - 1);
			System.out.println(top.getType() + top.getCharacter().getName()
					+ "扣血...");
			top.printHp();
			top.checkHPNull();
			top.getCharacter().afterHurt(top, p);
		}
	}

	// 无中生有
	public void wuzhongshengyou(Player p, Player top) {
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("无中生有被否决！");
			return;
		}
		p.getCharacter().AddCard(p, 2, top);
		System.out.println(p.getType() + p.getCharacter().getName() + "获得2张牌");
	}

	
	// 过河拆桥
	public void guohechaiqiao(Player p, Player top) {
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("过河拆桥被否决！");
			return;
		}
		int index = p.selectCard(top)-1 ;
		System.out.println(top.toString()+"的：["+top.getHandcard().get(index).toString() + "]被丢弃...");
		top.removeCard(index);
		//无牌检测
		if(top.getHandcard().size() == 0){
			top.getCharacter().noCard(top);
		}
	}

	// 顺手牵羊
	public void shunshouqianyang(Player p, Player top) {
		//询问对手是否出无懈
		if(top.requestWuXie(p)==true){
			System.out.println("顺手牵羊被否决！");
			return;
		}
		int index = p.selectCard(top) - 1;// 注意-1操作；
		p.addhandCard(top.getHandcard().get(index));
		System.out.println(p.getType() + p.getCharacter().getName() + "从"
				+ top.getType() + top.getCharacter().getName() + "手中获得了："
				+ top.getHandcard().get(index).toString());
		top.removeCard(index);
		//无牌检测
		if(top.getHandcard().size() == 0){
			top.getCharacter().noCard(top);
		}
	}
	// -----------get set-----------------------------
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
