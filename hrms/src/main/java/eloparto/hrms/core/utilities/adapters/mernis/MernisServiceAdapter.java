package eloparto.hrms.core.utilities.adapters.mernis;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import eloparto.hrms.mernisService.FakeMernisService;

@Service
public class MernisServiceAdapter {

	public static boolean checkIfRealPerson(String tcNo) {
       
		FakeMernisService fakeMernisService = new FakeMernisService();
		
		return fakeMernisService.TCKimlikDogrula(tcNo);

	}



}
