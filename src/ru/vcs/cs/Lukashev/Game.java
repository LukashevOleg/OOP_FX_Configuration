package ru.vcs.cs.Lukashev;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.List;

public class Game
{
    Colors colors=new Colors();
    int xLeftUpper=175;
    int yLeftUpper=170;
    private FlasksList flasksList;



    public Game() {
    }

    public FlasksList getFlasksList() {
        return flasksList;
    }




    public void updateFlaskList()
    {
        flasksList=new FlasksList(xLeftUpper, yLeftUpper, colors.getArrColors(),colors.getArrColors(),colors.getArrColors());
        flasksList.addFlaskList();
    }

    public Color getFromWhichFlask(int whichFlask)
    {
        return flasksList.getFromListLastColor(whichFlask);
    }

    public void setIntoWhichFlask(int whichFlask, Color colorContainer )
    {
        flasksList.setIntoListLastColor(colorContainer, whichFlask);
    }

    public boolean checkWinner()
    {
        List<Color> listColor;
        Color colorContainer;
        for(int i=0;i<flasksList.getN();i++)
        {
            listColor=flasksList.getFlaskFromList(i).getListColor();
            colorContainer=listColor.get(0);
            for(Color color: listColor)
            {
                if (!colorContainer.equals(color))
                {
                    return false;
                }
            }
        }
        return true;
    }



    public int isPointFlask(int x, int y )
    {
        int xFlaskContainer;
        int yFlaskContainer;
        for(int i=0;i<flasksList.getN();i++)
        {
            xFlaskContainer=flasksList.getFlaskFromList(i).getxFlask();
            yFlaskContainer=flasksList.getFlaskFromList(i).getyFlask();
            if(x>xFlaskContainer-20&&x<xFlaskContainer+120
                    &&y>yFlaskContainer-20&&y<yFlaskContainer+200)
            {
                return i;
            }
        }

        return -1;
    }
}

