package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.vcs.cs.Lukashev.ConnectGroupFlask;
import ru.vcs.cs.Lukashev.Game;



public class Main extends Application {
    Game game;
    Color colorContainer;
    Group prevRoot = new Group();
    Group curRoot = new Group();
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        game= new Game();

        newGame();
        repaint();

        scene = new Scene(curRoot, 1300, 800);
        primaryStage.setScene(scene);

        curRoot.addEventHandler(MouseEvent.MOUSE_CLICKED, createEvent(primaryStage));
        prevRoot = curRoot;
        primaryStage.show();
    }



    private EventHandler<MouseEvent> createEvent(Stage primaryStage)
    {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                int x =(int) e.getX();
                int y =(int) e.getY();
                int whichFlask = game.isPointFlask(x, y);
                if (e.getButton() == MouseButton.PRIMARY)
                {
                    if (whichFlask != -1 && colorContainer == null)
                    {
                        pressLeftMouseButton(whichFlask);
                        scene= new Scene(curRoot, 1300, 800);
                        primaryStage.setScene(scene);
                        curRoot.addEventHandler(MouseEvent.MOUSE_CLICKED, createEvent(primaryStage));
                        prevRoot =  curRoot;
                    }
                }

                if (e.getButton() == MouseButton.SECONDARY)
                {

                    if (whichFlask != -1 && colorContainer != null)
                    {
                        pressRightMouseButton(whichFlask);
                        scene= new Scene(curRoot, 1300, 800);
                        primaryStage.setScene(scene);
                        curRoot.addEventHandler(MouseEvent.MOUSE_CLICKED,createEvent(primaryStage));
                        prevRoot = curRoot;
                    }

                    if (game.checkWinner())
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("The end Game");
                        alert.setHeaderText(null);
                        alert.setContentText("You are winner!!!");

                        alert.showAndWait();
                        newGame();
                        repaint();
                        scene= new Scene(curRoot, 1300, 800);

                        primaryStage.setScene(scene);
                        curRoot.addEventHandler(MouseEvent.MOUSE_CLICKED,createEvent(primaryStage));
                        prevRoot = curRoot;
                    }
                }
            }
        };
        return eventHandler;
    }


    private void newGame() {
        game.updateFlaskList();

    }

    private void repaint()
    {
        curRoot = ConnectGroupFlask.connect(game.getFlasksList().getN(), game.getFlasksList().getFlaskList());
    }

    private void pressLeftMouseButton(int whichFlask)
    {

        colorContainer = game.getFromWhichFlask(whichFlask);
        repaint();
    }

    private void pressRightMouseButton(int whichFlask)
    {
        game.setIntoWhichFlask(whichFlask, colorContainer);
        repaint();
        colorContainer = null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
