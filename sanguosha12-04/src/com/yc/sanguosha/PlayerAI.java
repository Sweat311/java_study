package com.yc.sanguosha;
import java.util.Random;


//****************AI玩家类*********************
public class PlayerAI extends Player{ 
	//TODO AI优化，减少无效出牌
	public PlayerAI(){}
	public PlayerAI(Character character,int hp){
		super(character,hp);
		this.setType("(AI)");
	}
//---【各种重写的方法】---------------------------------------------------------------
	public void printHandCard(){  //重写打印手牌，隐藏手牌显示，只告诉玩家有多少张牌
		System.out.print(this.toString() + "当前手牌：");
		for (int i = 0; i < this.getHandcard().size(); i++) {
			System.out.print("[手牌" + (i+1) + "]");
		}
		System.out.println();
	}
	//AI重写选择对方手牌的方法，从玩家手中随机选1张
	public int selectCard(Player top){
		Random r = new Random();
		return r.nextInt(top.getHandcard().size());
	}
	//AI重写选牌  返回值域为0-size
	public int selectCardIndex(int size) {
		Random r=new Random();
		return r.nextInt(size);

	}
	//--------------AI重写要求出杀、要求出闪的方法
	//被要求出闪 
	public boolean requestshan(Player p,Player top){    
    	//检查是否有八卦，是则调用八卦阵技能方法，成功返回true
		if (this.getEqiup(1) != null) {
			boolean b = this.useBaGua(this, top);
			if(b==true){
				System.out.println("八卦阵生效！");
				return b;
			}else{
				System.out.println("八卦阵无效，需要出闪。");
			}
		}
    	//检查是否有闪，是则出闪，成功返回true
		//遍历所有手牌，有符合条件的就出
		for (int i = 0; i < this.getHandcard().size(); i++) {
			if (this.getHandcard().get(i).key.equals("闪")|| this.getCharacter().changeCard(
					this.getHandcard().get(i), "闪")) {// 此处加一个or 是否技能变牌有效果
				System.out.println("AI出闪："+this.getHandcard().get(i).toString());
				this.setTmpCard(this.getHandcard().get(i));
				Initial.throwlistCard.add(this.getHandcard().get(i));
				this.removeCard(i);
				return true;
			}
		}
		// 上述都无，返回false
		System.out.println(this.toString()+"不出闪...");
		return false;
    }
	 //被要求出杀，进行检测
    public boolean requestSha(){    
    	System.out.println(this.toString()+"需要出杀");
    	//遍历所有手牌，有符合条件的就出
    	for (int i = 0; i < this.getHandcard().size(); i++) {
			if (this.getHandcard().get(i).key.equals("杀")|| this.getCharacter().changeCard(
					this.getHandcard().get(i), "杀")) {// 此处加一个or 是否技能变牌有效果
				System.out.println("AI出杀："+this.getHandcard().get(i).toString());
				this.setTmpCard(this.getHandcard().get(i));
				Initial.throwlistCard.add(this.getHandcard().get(i));
				this.removeCard(i);
				return true;
			}
		}
    	System.out.println(this.toString()+"不出杀...");
    	return false;
    }
    //响应无懈可击
    public boolean requestWuXie(Player top){    
    	for (int i = 0; i < this.getHandcard().size(); i++) {
			if (this.getHandcard().get(i).key.equals("无懈可击")) {// 此处加一个or 是否技能变牌有效果
				System.out.println("（AI）出牌："+this.getHandcard().get(i).toString());
				Initial.throwlistCard.add(this.getHandcard().get(i));
				this.removeCard(i);
				if(top.requestWuXie(this)==true){
					System.out.println(this.toString()+"的无懈可击被"+top.toString()+"的无懈可击否决！");
					return false;
				}
				return true;
			}
		}
    	return false;
    }
    //----------------------------------------------------------------------------
}
