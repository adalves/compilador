package compiler;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private VirtualizedScrollPane editorScrollPane;
    @FXML private TextArea messageArea, statusArea;
    @FXML private CodeArea editorArea;
    @FXML private HBox hBox;
    @FXML private VBox vBox;
    @FXML private Button newBtn, openBtn, saveBtn, copyBtn, pasteBtn, cutBtn, compileBtn, teamBtn;

    private Stage primaryStage;
    private File currFile = null;
    private Compiler compiler = new Compiler();

    private final FileChooser fileChooser = new FileChooser();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editorArea.setParagraphGraphicFactory(LineNumberFactory.get(editorArea));
        editorScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        editorScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        messageArea.setFocusTraversable(false);
        //messageArea.setMouseTransparent(true);
        statusArea.setFocusTraversable(false);
        statusArea.setMouseTransparent(true);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos .txt","*.txt"));
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupShortcuts();
    }

    private void setupShortcuts(){
        KeyCombination kc = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        Runnable rn = this::newBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        rn = this::openBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        rn = this::saveBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
        rn = this::copyBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
        rn = this::pasteBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
        rn = this::cutBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.F9);
        rn = this::compileBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
        kc = new KeyCodeCombination(KeyCode.F1);
        rn = this::teamBtnPress;
        primaryStage.getScene().getAccelerators().put(kc, rn);
    }

    public void newBtnPress() {
        currFile = null;
        editorArea.clear();
        messageArea.clear();
        statusArea.clear();
    }

    public void openBtnPress() {
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            currFile = file;
            editorArea.clear();
            editorArea.appendText(FileUtils.readFile(currFile));
            messageArea.clear();
            statusArea.setText(currFile.getAbsolutePath());
        }
    }

    public void saveBtnPress() {
        if (currFile == null) {
            currFile = fileChooser.showSaveDialog(primaryStage);
        }
        if (currFile != null) {
            FileUtils.writeToFile(currFile, editorArea.getText());
            messageArea.clear();
            statusArea.setText(currFile.getAbsolutePath());
        }
    }

    public void copyBtnPress() {
        editorArea.copy();
    }

    public void pasteBtnPress() {
        editorArea.paste();
    }

    public void cutBtnPress() {
        editorArea.cut();
    }

    public void compileBtnPress() {
        String str = compiler.compile(editorArea.getText());
        messageArea.setText(str);
    }

    public void teamBtnPress() {
        messageArea.setText("Equipe: Ana Carolina da Rocha Santos Alves.");
    }

}
