
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SettingController extends Window {

    @FXML
    private TextField txtPath;

    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;

    @FXML
    private TextField txtPlaybackSpeed;

    @FXML TextField txtField;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public AnchorPane mainForm;

    @FXML
    private ToggleGroup groupLevelOfCommand;

    @FXML
    private ToggleGroup groupYesOrNo;

    @FXML
    private Button btBackspace;

    @FXML private Button btClear;

    @FXML
    private AudioClip audioClip;

    @FXML
    private Button btPlay;

    public static HashSet<Randomize>  luckyNumbers = new HashSet();

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
                txtTo.setText("1000000000000");
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

        Button bt = (Button) actionEvent.getSource();

        if (isNumeric(bt.getText())) {
            txtField.setText(txtField.getText()+bt.getText());
        } else {
            switch (bt.getId()) {
                case "btBackspace":
                    txtField.setText(txtField.getText(0, Math.max(0, txtField.getLength() - 1)));
                    break;
                case "btClear":
                    txtField.clear();
                    break;
                case "btStart":
                    bt.setDisable(true);
                    startLearning(Long.parseLong(txtFrom.getText()), Long.parseLong(txtTo.getText()));
                    break;
                case "btPlay":
                    luckyNumbers.stream().forEach(s->System.out.println(s.getRandomLong()));
                    Optional<Randomize> firstResult = luckyNumbers.stream().filter(s->s.getNumberHasBeenPlayed().equals(false)).findFirst();
                    if (firstResult.isPresent()) {
                        firstResult.get().setNumberHasBeenPlayed(true);
                        System.out.println(firstResult.get().getRandomLong());
                    } else {
                        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                        newAlert.setHeaderText("Skończyły się liczby");
                        newAlert.show();

                    }
                    break;
            }


    }
    }

    private void startLearning(Long lBound, Long uBound) {
        while (luckyNumbers.size()<20) {
            luckyNumbers.add(new Randomize(lBound, uBound));
        }


    }

    private static boolean isNumeric(String txt) {
        try {Long longFromString = Long.parseLong(txt);}
        catch (NumberFormatException e) {return false;}
        return true;
    }



    private void playNumbers(Integer number) throws MalformedURLException {
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
    }
}