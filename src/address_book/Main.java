package address_book;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Group group = new Group();
		
		Scene root = new Scene(group); 
		arg0.setTitle("test");
		arg0.setScene(root);
		arg0.setHeight(800);
		arg0.setWidth(600);
		arg0.show();
	}
}
