import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Window;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

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

    public void buttonClicked(ActionEvent actionEvent) throws MalformedURLException {

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
                        playLuckyNumbers(firstResult.get().getRandomLong());
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

    private void playLuckyNumbers(Long randomLong) throws MalformedURLException {
        final String str = "src/main/resources/media/";
        ArrayList<String> list = new ArrayList();
        final String bilione = "bilione.mp3";
        final String bilioni = "bilioni.mp3";
        final String miliardo = "miliardo.mp3";
        final String miliardi = "miliardi.mp3";
        final String milione = "milione.mp3";
        final String milioni = "milioni.mp3";
        final String mille = "mille.mp3";
        final String mila = "mila.mp3";
        final String cento = "100.mp3";

        Integer luckyNumbersLength = randomLong.toString().length();
        Integer fullThreeDigitRanges = luckyNumbersLength/3;
        Integer extraLength = luckyNumbersLength - fullThreeDigitRanges*3;

        String sParam1="";
        String sParam2="";
        String sParam3="";
        String sParam4="";

        for (int i = fullThreeDigitRanges; i>0; i--) {
            switch (i) {
                case 1:
                    sParam1 = mille;
                    sParam2 = mila;

                    break;
                case 2:
                    sParam1 = milione;
                    sParam2 = milioni;
                    sParam3 = mila;
                    sParam4 = mille;
                    break;
                case 3:
                    sParam1 = miliardo;
                    sParam2 = miliardi;
                    sParam3 = milioni;
                    sParam4 = milione;
                    break;
                case 4:
                    sParam1 = bilione;
                    sParam2 = bilioni;
                    sParam3 = miliardi;
                    sParam4 = miliardo;
                    break;

            }

            if (extraLength>0 && i == fullThreeDigitRanges) {
                String extraNumber = randomLong.toString().substring(0, extraLength);
                if (Integer.parseInt(extraNumber) == 1) {
                    list.add(str + sParam1);
                } else {
                    list.add(str + extraNumber + ".mp3");
                    list.add(str + sParam2);
                }
            }

            Integer currentRange = Integer.parseInt(randomLong.toString().substring(luckyNumbersLength-i*3, luckyNumbersLength-i*3+3));

            if (currentRange > 0) {
                if (currentRange > 1 && currentRange < 101) {
                    list.add(str + currentRange.toString() + ".mp3");
                    list.add(str + sParam3);
                } else if (currentRange > 100) {
                    list.add(str + String.valueOf(currentRange/100*100) + ".mp3");
                    if ((currentRange - currentRange/100*100) > 0) {
                        list.add(str + String.valueOf(currentRange - currentRange / 100 * 100) + ".mp3");
                        list.add(str + sParam3);
                    }

                } else if (currentRange == 1) {
                    list.add(str + sParam4);
                }
            }

            sParam1="";
            sParam2="";
            sParam3="";
            sParam4="";

        }


            List<String> filteredList = list.stream()
                    .filter(s->s.contains("mp3"))
                    .collect(Collectors.toList());

            filteredList.forEach(s->{

                try {
                    audioClip = new AudioClip(new File(s).toURI().toURL().toString());
                    audioClip.play();
                    while (audioClip.isPlaying()) {
                        ;
                    }



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }



            });


        }
    }


