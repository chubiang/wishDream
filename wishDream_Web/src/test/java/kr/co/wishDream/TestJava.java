package kr.co.wishDream;

import java.util.Calendar;
import java.util.Date;

public class TestJava {

	 public static String solution(String[] participant, String[] completion) {
        String answer = "";
		String[][] map = new String[participant.length][1];
        
		for (int i = 0; i < participant.length; i++) {
			for (int j = 0; j < completion.length; j++) {
				if (participant[i] == completion[j]) {
					map[i][0] = completion[j];
				} else if (map[i][0] == null 
					&& participant[i] != completion[j]) {
					map[i][0] = "0";
				}
			}
//			System.out.println("map["+i+"][0] = "+map[i][0]);
		}
		
		for (int i = 0; i < participant.length; i++) {
			if (map[i][0].equals("0")) {
				return answer = participant[i];
			} else {
				if (participant[0] == participant[i+1]) {
					return answer = participant[i+1];
				}
			}
		}
        
        return answer;
    }
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] arr2 = {"josipa", "filipa", "marina", "nikola"};
		
//		System.out.println(solution(arr,arr2));
		
		String str = "2016-06-04";
		String[] date = str.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(new Integer(date[0]), new Integer(date[1]), new Integer(date[2]));
		
		System.out.println(cal.getTime()+ " /// ");
		
		
	}

}
