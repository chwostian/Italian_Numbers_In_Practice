
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Controller extends Window {

    @FXML
    private TextField txtPath;

    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;

    @FXML
    private TextField txtPlaybackSpeed;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private ToggleGroup groupLevelOfCommand;

    @FXML
    private ToggleGroup groupYesOrNo;

    @FXML
    private Button btBackspace;

    @FXML
    private AudioClip audioClip;

    @FXML
    void showMessage(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("cos");
        alert.show();

    }

    @FXML
    void openSecondForm(ActionEvent event) throws IOException, URISyntaxException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondForm.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Italian numbers in practice");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("italian_flag.png")));
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        StringBuilder str = new StringBuilder();
        URL path = null;

        for (int i=1; i<4; i++){
            str = new StringBuilder();
            str.append("src/main/resources/media/");
            str.append(i);
            str.append("00");
            str.append(".mp3");

            path = new File(str.toString()).toURI().toURL();

            audioClip = new AudioClip(path.toString());
            audioClip.play();
            while (audioClip.isPlaying()) {
                ;
            }

        }


//        mediaView.setMediaPlayer(mediaPlayer);






    }

    @FXML
    void initialize() {
        assert mainForm != null : "fx:id=\"mainForm\" was not injected: check your FXML file 'MainForm.fxml'.";


    }
    @FXML
    public void levelOfCommand(MouseEvent mouseEvent) {
        RadioButton radioButton = (RadioButton) groupLevelOfCommand.getSelectedToggle();

        switch (radioButton.getText()) {
            case "Beginner":
                txtFrom.setText(String.valueOf(0));
                txtTo.setText(String.valueOf(1000));
                txtPlaybackSpeed.setText("75%");
                break;
            case "Intermediate":
                txtFrom.setText(String.valueOf(0));
                txtTo.setText(String.valueOf(1000000));
                txtPlaybackSpeed.setText("90%");
                break;
            case "Advanced":
                txtFrom.setText(String.valueOf(0));
                txtTo.setText("INFINITY");
                txtPlaybackSpeed.setText("100%");
                break;

        }
    }


    public void showFolderPicker(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedDirectory = directoryChooser.showDialog(this);
        if (!selectedDirectory.equals(null)) {
            this.txtPath.setText(selectedDirectory.getAbsolutePath());
        }
    }


    public void buttonClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(actionEvent.getSource().toString());
        alert.show();
    }
}