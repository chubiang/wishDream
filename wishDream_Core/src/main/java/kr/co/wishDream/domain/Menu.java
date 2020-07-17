package kr.co.wishDream.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

	
	Integer id;
	
	Integer pid;
	
	String name;
	
	Integer menuType;
	
	Integer menuOrder;
	
	String menuPath;
	
	public static Menu menus(List<?> list) {
		Menu m = new Menu();
		m.setId((Integer) list.get(0));
		m.setPid((Integer) list.get(1));
		m.setName((String) list.get(2));
		m.setMenuType((Integer) list.get(3));
		return m;
	}
	
	public static List<Menu> sortHierarchyMenus(List<Menu> list) {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPid() == null) {
				Integer id = list.get(i).getId();
				for (int j = 0; j < list.size(); j++) {
					if (id.equals(list.get(j).getPid())) {
						Menu m = list.get(j);
						list.add(i+1, m);
						list.remove(j+1);
					}
				}
			}
		}
		
		return list;
	}
}
