package com.Initial;


public class Card {
	
	private String color;//0-黑桃；1-红心；2-梅花；3-方块
	private String num;  //数字0-12
	String key;          //key值 映射牌面内容           ****因为牌面涉及到有技能可以转换，暂定默认修饰符，以方便之后可能的操作*****
//----属性--------------------------------------------------------------
	//构造
	public Card(){}
	public Card(String color,String num,String key){
		this.color = color;
		this.num = num;
		this.key = key;
	}
	//Card的Ues方法，通过switch判断key对应的值，调用相对应的方法
	public boolean Use(Player p,Player top){        
		//变量n，对应hmp键值对中的key的值；具体对应表参见Initial类	
		int n = Integer.valueOf(Initial.hmp.get(new String(key))); 
			
		//先把牌扔进废弃堆，再判断，如果合法就算了；若是不合法，牌堆删掉最后一张
		//之所以要这么蛋疼的做，主要是为了曹操能够收牌，同时也为了实现延时性锦囊
			switch(n){
			case 1:  //调用杀方法，并获取返回值，再返回给上一层
				Initial.throwlistCard.add(this);
				if(p.sha(p, top)){
					//成功出杀，丢进牌堆
					return true;
				}else{
					//牌堆中最后一张移除
					Initial.throwlistCard.remove(Initial.throwlistCard.size()-1);
					return false; 
				}
			case 2: //闪不能主动出
				if(p.getType().equals("(玩家)")){
					System.out.println("闪不能主动出");
				}
				return false;
			case 3: //血量不满则加血，否则无效
				Initial.throwlistCard.add(this);
				if(p.getHp()<p.getCharacter().getmaxHp()){   
					p.tao();
					//成功出桃，丢进牌堆
				    return true;
				}else {
					//牌堆中最后一张移除
					Initial.throwlistCard.remove(Initial.throwlistCard.size()-1);
					if(p.getType().equals("(玩家)")){
						System.out.println("血量满，不能用桃");
					}
					return false;
				}
			default://调用相应的锦囊方法
				
				if((n>3 &&  n< 16)){
					Initial.throwlistCard.add(this);
					//System.out.println("调用相应的锦囊方法");
					JinNang jn = new JinNang(n);   //创建一个锦囊类的实例，参数为n，用来判断调用哪个锦囊的方法	
				    boolean b =jn.useJinNang(p,top,this);  //调用方法，完成操作返回真；否则返回假。
				    if(b){
				    		//如果是闪电或者乐不思蜀，不直接扔掉，放对面的判定区里
				    	if(n==5 || n==10){
				    		//牌堆中最后一张移除
							Initial.throwlistCard.remove(Initial.throwlistCard.size()-1);
				    	}
				    	p.getCharacter().useJinNang(p);//成功使用锦囊则调用触发事件（黄月英）
				    }else{
				    	//牌堆中最后一张移除
						Initial.throwlistCard.remove(Initial.throwlistCard.size()-1);
				    }
					return b;
				    //【待处理】锦囊牌扔牌堆，需具体判断，有的延时性锦囊需要放玩家门前。
				}
				if(n>=16 && n<31){//调用相应的武器的加载事件
					this.install(p, top, n);
					//System.out.println("调用相应的武器事件");
					return true;
				}
			}
		return false;
	}
	
	//装备牌装载事件的触发。
	public void install(Player p,Player top,int index){ 
		//如果该格子上有武器，先卸载，触发卸载事件；再装载
		if(p.getEqiup(0)!= null){
			p.getEqiup(0).uninstall(p, top, index);
		}
			
		    //武器类，根据型号增加攻击距离
			if(index>=12 && index <=16){
				p.setDisAtt(p.getDisAtt()+1);
				p.setEqiup(this, 0);
			}
			//TODO 仁王盾
			
			//TODO 青刚剑+1
			
			//-1马
			if(index>=25 && index<=27){
				p.setEqiup(this, 2);
				p.setDisAtt(p.getDisAtt()+1);
			}
			//+1马
			if(index>=28 && index<=30){
				p.setEqiup(this, 3);
				p.setDisDef(p.getDisDef()+1);
			}
			System.out.println(p.toString()+"装备了"+this.key );
	}
	 //装备牌卸载时候的触发。
	public void uninstall(Player p,Player top,int index){
		//武器类，根据型号增加攻击距离
		if(index>=16 && index <=23){
			p.setDisAtt(p.getDisAtt()-1);
		}
		//-1马
		if(index>=25 && index<=27){
			p.setDisAtt(p.getDisAtt()-1);
		}
		//+1马
		if(index>=28 && index<=30){
			p.setDisDef(p.getDisDef()-1);
		}
		Initial.throwlistCard.add(this);
		//调用人物装备卸载事件
		p.getCharacter().eqiupUnload(p,top);
	}
	//*********************************【set get】*************************************
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getNum() {
		return num;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setNum(String num) {
		this.num = num;
	}
	//---------------------------------------------------------------------------------
	public String toString(){       //重写toString方法，返回出花色+数字+牌型
		return this.color+this.num+"--"+this.key ;
	}
}

