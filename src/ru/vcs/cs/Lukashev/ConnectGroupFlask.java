package ru.vcs.cs.Lukashev;

import javafx.scene.Group;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class ConnectGroupFlask
{
    public static Group connect(int n, List<FlasksList.Flask> flaskList )
    {
        Group group = new Group();
        for(int i=0; i<n; i++)
        {
            group.getChildren().add(0, flaskList.get(i).getGroup());
//            flaskList.get(i).paint(stage);
        }
        return group;
    }
}
