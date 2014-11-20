package webParser;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

 




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Taobao {
	public  String taoBao(String url) throws IOException {
		String result = new String();
		File input = new File(url);
		Document doc = Jsoup.parse(input, "GBK");
		String[][] tmp = new String[100][30];
		int x = 0,y = 0;
		for(Element ele : doc.select("table").select("tr")){
			boolean flag = false;
			for(Element ele2 : ele.select("td")){
				if(!ele2.select("td").toString().equals("")){
					//String url = ele.select("td").get(0).select("a").attr("href");
					String text = ele2.select("td").get(0).text();
					
					//String count = ele.select("td").get(0).select("span").text();
					if(text.equals("全选 合并付款 合并代付 批量确认收货 如想使用信用卡付款，请只选择带有 标识的宝贝 下一页") || text.equals("全选 合并付款 合并代付 批量确认收货 1 2 3 4 5 ... 共 15 页， 到第 页 确定")) {
						flag = true;
						break;
					} else if(text.equals("分享 标记 删除") || text.equals("分享 转卖 标记 删除") || text.equals("查看店铺活动 分享 转卖 标记 删除")) {
						continue;
					}
					if(text.equals("投诉卖家") || text.equals("申请售后") || text.equals("申请售后 投诉卖家") || text.equals("申请售后 投诉卖家 运费险失效") || text.equals("电器城服务台 申请售后") || text.equals("电器城服务台 申请售后 投诉卖家")) {
						continue;
					}
					if(text.equals("交易关闭 订单详情") || text.equals("交易成功 订单详情") || text.equals("交易成功 订单详情 双方已评") || text.equals("交易成功 订单详情 查看物流 双方已评")) {
						continue;
					}
					if(text.equals("追加评论 再次购买") || text.equals("追加评论") || text.equals("再次购买")) {
						continue;
					}
					if(text.equals("电器城服务台")) {
						continue;
					}
					if(text.equals("保险服务")) {
						continue;
					}
					if(text.length() > 0) {
						tmp[x][y++] = text;
						flag = true;
					}
				}
			}
			if(!flag) {
				x++;
				y = 0;
			}
		}
		String[][] t = new String[100][30];
		x = y = 0;
		for (int i=0; i<20; i++) {
			if (tmp[i][0] == null) continue;
			if (tmp[i][8] == null) { // one good
				for (int j=0; j<6; j++) {
					if (j == 0) {
						t[x][y++] = tmp[i][j].substring(0, 10);
						t[x][y++] = tmp[i][j].substring(10);
					}
					else
						t[x][y++] = tmp[i][j];
				}
				x++;
				y = 0;
				
			} else {// more than one goods
				t[x][y++] = tmp[i][0].substring(0, 10);
				t[x][y++] = tmp[i][0].substring(10);
				t[x][y++] = tmp[i][1];
				t[x][y++] = "|                            宝贝名                                                                              |";
				t[x][y++] = "|      单价               |";
				t[x][y++] = "|      数量               |";
				t[x++][y++] = tmp[i][5];
				y = 0;
				t[x][y++] = "    -     ";
				t[x][y++] = "         -         ";
				t[x][y++] = "    -     ";
				t[x][y++] = tmp[i][2];
				t[x][y++] = tmp[i][3];
				t[x][y++] = tmp[i][4];
				t[x++][y++] = "    -     ";
				y = 0;
				for (int j=0; tmp[i][6+j*3]!=null; j++) {
					t[x][y++] = "    -     ";
					t[x][y++] = "         -         ";
					t[x][y++] = "    -     ";
					t[x][y++] = tmp[i][6+j*3];
					t[x][y++] = tmp[i][6+j*3+1];
					t[x][y++] = tmp[i][6+j*3+2];
					t[x++][y++] = "    -     ";
					y = 0;
				}
			}
		}
		result = null;
		for(int xx = 0;xx<25;xx++) {
			for(int yy = 0;yy<6;yy++) { 
				if (yy == 2) {
					if (t[xx][yy].length() < 6) {
//						result += ("%-8s\t\t");
//						result+= (t[xx][yy]);
//						result += ("\n");
						System.out.printf("%-8s\t\t", t[xx][yy]);
					} else{
//						result += ("%-8s\t");
//						result += (t[xx][yy]);
//						result += ("\n");
//					}
						System.out.printf("%-8s\t", t[xx][yy]);
					}
				} else{
//					result += (t[xx][yy]);
//					result += ("\t\t\n");
//				}
					System.out.printf(t[xx][yy]+"\t\t");
				}
			}
//			result += ("\n");
			System.out.println("");
		}
		return result;
	}
}
