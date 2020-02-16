package application;

import java.util.ArrayList;

import Entities.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResultsPane extends Pane {
	
	private int height;
	private int width;
	private class ProfileEvent implements EventHandler<ActionEvent> {
		private Professor prof;
		private HBox layout;
		
		public ProfileEvent(Professor prof, HBox layout) {
			this.prof = prof;
			this.layout = layout;
		}
		@Override
		public void handle(ActionEvent event) {
			if (layout.getChildren().size() >1) {
				layout.getChildren().remove(1);
			}
			StackPane profile = new StackPane();
			Text profileText = new Text();
			profile.setPrefSize(width*0.7,height);
			profile.setStyle("-fx-background-color: #FFFFFF");
			profileText.setText(prof.getDepartment());
			profile.getChildren().add(profileText);
			layout.getChildren().add(profile);
		}
		
	}
	
	public ResultsPane(String department, ArrayList<String> interests, int w, int h) {
		super();
		this.height = h;
		this.width = w;
		
		HBox layout = new HBox(8);	
		ScrollPane resultBox = new ScrollPane();
		VBox resultList = new VBox(8);
		resultList.setPrefSize(width*0.3, height);
		resultBox.setContent(resultList);
		layout.getChildren().add(resultBox);
		
		ArrayList<Professor> matches = new ArrayList<Professor>();
		for (int i = 0; i < 5; i ++) {
			matches.add(new Professor("a"+i, new ArrayList<String>(),"a"+i, "a"+i, "a"+i));
		}
		
		Button temp = new Button();
		
		for (int i = 0; i < matches.size(); i++ ) {
			temp = new Button();
			temp.setBackground(null);
			temp.setText(matches.get(i).getName());
			temp.setOnAction(new ProfileEvent(matches.get(i),layout));
			resultList.getChildren().add(temp);
		};
		
		
		Button btn = new Button("Back");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene sc = Main.getScene();
				sc.setRoot(new SearchPane(ResultsPane.this.width, ResultsPane.this.height));
			}
		});
		
		this.getChildren().addAll(layout,btn);
		this.setStyle("-fx-background-color: #00000f ");
	}
}