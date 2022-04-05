package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class calchistory {
	@FXML
	private  ListView<String> historylistview; 

	public void initializecalculation(ArrayList<String> calculhist) {
		calculhist.forEach((calculation) -> {
			historylistview.getItems().add(calculation);
		});
}
}
