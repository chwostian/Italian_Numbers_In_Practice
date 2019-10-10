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
    private Button btStart;

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
                        luckyNumbers.clear();
                        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                        newAlert.setHeaderText("Skończyły się liczby");
                        newAlert.show();
                        btStart.setDisable(false);

                    }
                    break;
            }
        }
    }

    private void startLearning() {
            String user = SettingController.stxtGroupLevelOfCommand;
            int loop = 1;
            int counter = 1;
        while (luckyNumbers.size()<20) {
            luckyNumbers.add(new Randomize(0L, getUsersUpperBoundWhenCycledThroughTheLoop(loop)));
            if (counter%4==0) {
                loop++;
            }
            counter++;
        }
    }

    private static boolean isNumeric(String txt) {
        try {Long longFromString = Long.parseLong(txt);}
        catch (NumberFormatException e) {return false;}
        return true;
    }

    private Long getUsersUpperBoundWhenCycledThroughTheLoop(int loop) {
        String user = SettingController.stxtGroupLevelOfCommand;
        Long uBound=0L;
        switch (loop) {
            case 1:
                uBound = (user.equals("Beginner")) ? 201L : ((user.equals("Intermediate")) ? 1001L : 10001L);
                break;
            case 2:
                uBound = (user.equals("Beginner")) ? 401L : ((user.equals("Intermediate")) ? 10001L : 100001L);
                break;
            case 3:
                uBound = (user.equals("Beginner")) ? 601L : ((user.equals("Intermediate")) ? 100001L : 1000001L);
                break;
            case 4:
                uBound = (user.equals("Beginner")) ? 801L : ((user.equals("Intermediate")) ? 500001L : 1000000001L);
                break;
            case 5:
                uBound = (user.equals("Beginner")) ? 1001L : ((user.equals("Intermediate")) ? 1000001L : 1000000000001L);
                break;
            default:
                uBound = (user.equals("Beginner")) ? 1001L : ((user.equals("Intermediate")) ? 1000001L : 1000000000001L);
                break;
        }

        return uBound;
    }

    private void playLuckyNumbers(Long number) throws MalformedURLException {
        StringBuilder str = new StringBuilder();
        URL path = null;

        switch (number.toString().length()) {
            case 1:
                //ones
                break;
            case 2:
                //tens
                break;
            case 3:
                //hundreds
                break;
            case 4:
                //thousands
                break;
            case 5:
                //thousands
                break;
            case 6:
                //thousands
                break;
            case 7:
                //milions
                break;
            case 8:
                //milions
                break;
            case 9:
                //milions
                break;
            case 10:
                //bilions
                break;
            case 11:
                //bilions
                break;
            case 12:
                //billions
                break;
            case 13:
                //trilions
                break;

        }





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
