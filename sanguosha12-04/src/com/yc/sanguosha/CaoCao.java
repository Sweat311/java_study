package com.yc.sanguosha;
import java.util.ArrayList;

//曹操、关羽、黄忠、吕蒙、张飞英雄集合


//曹操
public class CaoCao extends Character {
	public CaoCao() {
		this.setName("曹操");
		this.setmaxHp(4);
		this.setHp(this.getmaxHp());
	}

	// 重写受伤触发事件，获取伤害牌，获取废弃牌堆里的最后一张？
	public void afterHurt(Player p, Player pSha) {
		System.out.println("（曹操：宁教我负天下人，休教天下人负我！）");
		System.out.println(p.toString() + "获得了：" + pSha.getTmpCard());
		// 收牌
		p.addhandCard(pSha.getTmpCard());
		// 牌堆中最后一张移除
		Initial.throwlistCard.remove(Initial.throwlistCard.size() - 1);
	}
}

//关羽
class GuanYu extends Character {
	public GuanYu() {
		this.setName("关羽");
		this.setmaxHp(4);
		this.setHp(this.getmaxHp());
	}

	// (武圣)重写变牌，如果要求出杀，并且牌面为红，返回真
	public boolean changeCard(Card c, String key) {
		if (key.equals("杀")) {
			if (c.getColor().equals("红心") || c.getColor().equals("方块")) {
				return true;
			}
		}
		return false;
	}

	// 变牌出杀（与赵云共享）
	public void useSkill(Player p, Player top) {
		// 无手牌直接退出
		if (p.getHandcard().size() == 0) {
			System.out.println("无手牌，无法发动技能");
			return;
		}
		if (p.getCharacter().getName().equals("关羽")) {
			System.out.println("（关羽：观尔乃插标卖首！）");//控制台输出
			//TODO 语音输出 还有许多地方要添加语言，对代码熟悉后，在需要添加语音的地方打上【todo 语音输出】
			
		}
		// 建立一个集合，作为预出的牌，打印显示以供选择
		ArrayList<Card> tmpCard = new ArrayList<Card>();
		// 进入技能出牌循环，直到无手牌，或者选择取消
		while (true) {
			// 遍历手牌，将符合条件的牌放入集合，
			for (int i = 0; i < p.getHandcard().size(); i++) {
				if (p.getCharacter().changeCard(p.getHandcard().get(i), "杀")) {
					// 将符合的牌放入集合
					tmpCard.add(p.getHandcard().get(i));
					// 手牌删除 ，若取消则将临时集合的牌再放回
					p.removeCard(i);
					i--;
				}
			}
			// 如果遍历完毕后，没有符合条件的，退出
			if (tmpCard.size() == 0) {
				System.out.println("没有可作技能用的牌，结束技能。");
				return;
			}
			// 将满足条件的牌打印出来
			System.out.print("可以当做杀使用的牌：");
			for (int i = 0; i < tmpCard.size(); i++) {
				// 打印显示
				System.out.print((i + 1) + "--[" + tmpCard.get(i).toString()
						+ "]");
			}
			System.out.println();
			// 接受输入参数 （返回值已做过-1处理，直接可用）
			int r = p.selectCardIndex(tmpCard.size());
			if (r == -1) {// 即输入的是0
				System.out.println("玩家结束技能");
				// 将临时集合的牌放回手牌
				p.getHandcard().addAll(tmpCard);
				return;
			}
			try {
				// 调用杀方法
				if (p.sha(p, top)) {
					// 删除第r张牌
					tmpCard.remove(r);
					p.getCharacter().noCard(p);
				}
			} catch (Exception e) {
				System.out.println("输入有误，重新输入！");
			}
		}
	}
}

//张飞，重写杀方法************
class ZhangFei extends Character {
	public ZhangFei() { // 构造方法
		this.setName("张飞");
		this.setmaxHp(4);
		this.setHp(this.getmaxHp());
	}

	// 是否出过杀，在杀之后调用，张飞重写此方法
	public boolean haveYouSha() {
		System.out.println("（张飞：燕人张飞在此！！）");
		return false;
	}
}

//吕蒙
class LvMeng extends Character {
	public LvMeng() {
		this.setName("吕蒙");
		this.setmaxHp(4);
		this.setHp(this.getmaxHp());
	}

	// 重写弃牌方法
	public void ThrowCard(Player who) {
		if (who.isSha() == false) {
			System.out.println("（吕蒙：克己！不用弃牌）");
			return;
		} else {
			super.ThrowCard(who);
		}
	}
}



//TODO 黄忠
class HuangZhong extends Character{
	
	public HuangZhong(){ 
			this.setName("黄忠");
			this.setmaxHp(4);
			this.setHp(this.getmaxHp());
		
	}
	public boolean haveYouSha() {
		System.out.println("（黄忠：在此！！）");
		return false;
	}
	
	public void ThrowCard(Player who) {
		if (who.isSha() == false) {
			System.out.println("（黄忠：克己！不用弃牌）");
			return;
		} else {
			super.ThrowCard(who);
		}
	}
	
	
}