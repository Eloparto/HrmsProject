package eloparto.hrms.mernisService;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FakeMernisService {

	public static boolean TCKimlikDogrula(String tcNo) {
		
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tcNo);
		if(tcNo.length()>11) {
			return false;
		}else if(matcher.matches() && !tcNo.startsWith("0")) {
			return true;
		}else {
			return false;
		}
	}
}

