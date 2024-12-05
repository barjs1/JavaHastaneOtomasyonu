package helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class helper {
	
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButton","İptal");
		UIManager.put("OptionPane.noButton","Hayır");
		UIManager.put("OptionPane.okButton","Tamam");
		UIManager.put("OptionPane.yesButton","Evet");

	}
	
	
	
	
	
	//her defasında yazmamak için hata metni oluşturduk
		public static void showMsg(String str) {
		
		String msg;
		
		optionPaneChangeButtonText();
		switch(str) {
		case "fill":
			msg ="Lütfen tüm alanları doldurunuz.";
			break;
			
		case "success":
			msg= "İşlem başarılı.";
			break;
		case "error":
			msg="bir hata gerçekleşti.";
			break;
			
	    default:
	    	msg=str;
	    	
		}
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean confirm (String str) {
		String msg;
		optionPaneChangeButtonText();

		switch(str){
		case "sure":
			msg="Bu işlemi gerçekleştirmek istiyor musun ?";
			break;
			default:
				msg=str;
				break;
		}
		
		int res=JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
		
		if(res==0) {
			return true;
		}else {
			return false;
		}
	}

}
