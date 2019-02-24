package ex3;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.File;

public class Controller {
    @FXML
    ChoiceBox choiceBox1;

    public void choice() {
        File[] disks = File.listRoots();
        for (File f: disks) {
            choiceBox1.setValue(f);
        }
    }
}
