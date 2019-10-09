import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Window;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;

public class LearningController extends Window {

    @FXML
    TextField txtField;

    @FXML
    private Button btBackspace;

    @FXML
    private Button btClear;

    @FXML
    private AudioClip audioClip;

    @FXML
    private Button btPlay;

    public static HashSet<Randomize> luckyNumbers = new HashSet();

    public void buttonClicked(ActionEvent actionEvent) {

        Button bt = (Button) actionEvent.getSource();

        if (isNumeric(bt.getText())) {
            txtField.setText(txtField.getText() + bt.getText());
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
                    startLearning();
                    break;
                case "btPlay":
                    luckyNumbers.stream().forEach(s -> System.out.println(s.getRandomLong()));
                    Optional<Randomize> firstResult = luckyNumbers.stream().filter(s -> s.getNumberHasBeenPlayed().equals(false)).findFirst();
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

    private void startLearning() {
        while (luckyNumbers.size()<20) {
            luckyNumbers.add(new Randomize(Long.parseLong(SettingController.stxtFrom), Long.parseLong(SettingController.stxtTo)));
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
