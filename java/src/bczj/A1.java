/** 
 * 对于Java中集中不同集合类的功能实现与比较，体会不同方式的优缺点
 */

package bczj; 
import java.util.*;
public class A1 {
	 public static void main(String[] args) throws NullPointerException {
		 try{
		 HashSet<String> set = new HashSet<String>();//HashSet
		 set.add("Java");
		 set.add("C++");
		 
		 Iterator<String> it=set.iterator();
		 System.out.println("hashset:");
		 while(it.hasNext()){
			 String str=it.next();
			 System.out.println(str);
		 }
		 
		 TreeSet<String> tree = new TreeSet<String>();
		 tree.add("C");
		 tree.addAll(set);
		 tree.add("C..");
		 tree.add("C#");
		 
		 Iterator<String> it1 = tree.iterator();
		 System.out.println("TreeSet:");
		 while(it1.hasNext()){
			 String str1=it1.next();
			 System.out.println(str1);
		 }
		 
		 HashMap<String,String> hashmap=new HashMap<String,String>();
		 hashmap.put("005","C");
		 hashmap.put("003","Java");
		 hashmap.put("004","C++");
		 hashmap.put("002","C#");
		 
		 Collection<String> hashKey=hashmap.keySet();
		 Iterator<String> it2=hashKey.iterator();
		 System.out.println("HashMap:");
		 while(it2.hasNext()){
			 System.out.println(it2.next());
		 }
		 
		 TreeMap<String,String> tree2=new TreeMap<String,String>();
		 tree2.put("005","C");
		 tree2.put("003","Java");
		 tree2.put("004","C++");
		 tree2.put("002","C#");
		 
		Collection<String> treeKey = tree2.keySet();
		Iterator<String> it3=treeKey.iterator();
		System.out.println("TreeMap:");
		while(it3.hasNext()){
			String k=it3.next();
			System.out.print(k);
			System.out.println(" "+tree2.get(k));
		}
		
		LinkedList<String> list=new LinkedList<String>();
		list.add("apple");
		list.add("banana");
		list.add("peal");
		
		String next=list.get(2);
		System.out.println(next);
		list.add(2,"lemo");
		Iterator<String> it4=list.iterator();
		while(it4.hasNext()) {
			System.out.println(it4.next()); 
		} 
		System.out.println(it4.next());
	 }catch(NullPointerException e){
		 e.getMessage();
		 e.toString();
	 }
	 }
}