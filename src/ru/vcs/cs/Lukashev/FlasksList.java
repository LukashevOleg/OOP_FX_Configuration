package ru.vcs.cs.Lukashev;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;


public class FlasksList
{

    int xLeftUpper=200;
    int yLeftUpper=170;

    @Override
    public String toString() {
        return "FlasksList{" +
                "flaskList=" + flaskList +
                '}';
    }

    //    private List<Paint> f = new ArrayList<>();
    private List<Flask> flaskList= new ArrayList<>();
    private Color[] lowLayerColor;
    private  Color[] middleLayerColor;
    private Color[] upLayerColor;
    private final int n=8;


    public FlasksList(int xLeftUpper, int yLeftUpper, Color[] lowLayerColor, Color[] middleLayerColor, Color[] upLayerColor)
    {
        this.xLeftUpper = xLeftUpper;
        this.yLeftUpper = yLeftUpper;
        this.lowLayerColor = lowLayerColor;
        this.middleLayerColor = middleLayerColor;
        this.upLayerColor = upLayerColor;
    }


    public Color getFromListLastColor(int i){return flaskList.get(i).getListLastColor();}
    public void setIntoListLastColor(Color color, int i)
    {
        flaskList.get(i).setListColor(color);

    }




    public java.util.List<Flask> getFlaskList() {
        return flaskList;
    }
    public Flask getFlaskFromList(int i)
    {
        return flaskList.get(i);
    }

    public void addFlaskList()
    {
        int xStep=300;
        int yStep=300;
        int x=xLeftUpper;
        int y=yLeftUpper;
        for(int k=0; k<n/4;k++)
        {
            for(int i=0;i<n/2;i++)
            {
                flaskList.add(i, new Flask(x, y, getLowLayerColor(),
                        getMiddleLayerColor(), getUpLayerColor()));
                x+=xStep;
            }
            x=xLeftUpper;
            y+=yStep;
        }
    }




    public Color getLowLayerColor() {
        int count=0;
        Color color;
        for (Color value : lowLayerColor) {
            if (value == Color.GRAY) {
                count++;
            }
        }
        int i= (int) (Math.random() * (lowLayerColor.length-count));
        Color[] newArrColor= new Color[lowLayerColor.length];
        color= lowLayerColor[i];
        for(int t = 0, k = 0; t< lowLayerColor.length; t++)
        {
            if(t!=i)
            {
                newArrColor[k]= lowLayerColor[t];
                k++;
            }
        }
        newArrColor[lowLayerColor.length-1]=Color.GRAY;
        lowLayerColor=newArrColor;
        return color;
    }


    public Color getMiddleLayerColor()
    {
        int count=0;
        Color color;
        for (Color value : middleLayerColor) {
            if (value == Color.GRAY) {
                count++;
            }
        }
        int i= (int) (Math.random() * (middleLayerColor.length-count));
        Color[] newArrColor= new Color[middleLayerColor.length];
        color= middleLayerColor[i];
        for(int t = 0, k = 0; t< middleLayerColor.length; t++)
        {
            if(t!=i)
            {
                newArrColor[k]= middleLayerColor[t];
                k++;
            }
        }
        newArrColor[middleLayerColor.length-1]=Color.GRAY;
        middleLayerColor=newArrColor;
        return color;
    }


    public Color getUpLayerColor()
    {
        int count=0;
        Color color;
        for (Color value : upLayerColor) {
            if (value == Color.GRAY) {
                count++;
            }
        }
        int i= (int) (Math.random() * (upLayerColor.length-count));
        Color[] newArrColor= new Color[upLayerColor.length];
        color= upLayerColor[i];
        for(int t = 0, k = 0; t< upLayerColor.length; t++)
        {
            if(t!=i)
            {
                newArrColor[k]= upLayerColor[t];
                k++;
            }
        }
        newArrColor[upLayerColor.length-1]=Color.GRAY;
        upLayerColor=newArrColor;
        return color;
    }

    public int getN() {
        return n;
    }


    /**
     *
     *
     *
     *
     *
     */

    static class Flask
    {
        private final int xFlask;
        private final int yFlask;
        private Color lowLayer;
        private Color middleLayer;
        private Color upLayer;
        private final Color DEFAULT_COLOR=new Colors().getDEFAULT_COLOR();
        private Group group;

        public Group getGroup() {
            return group;
        }

        private List<Color> listColor=new ArrayList<>();

        List<Polygon> listPolygonColor = new ArrayList<>();


        public void listColorAddColor()
        {
            listColor.add(0,lowLayer);
            listColor.add(1, middleLayer);
            listColor.add(2, upLayer);
        }

        public List<Color> getListColor()
        { return listColor;
        }

        public Color getListLastColor()
        {
            Color colorContainer;
            int count=2;
            for (Color color : listColor) {
                if (color.equals(DEFAULT_COLOR) || color.equals(Color.GRAY))
                {
                    count--;
                }
            }
            colorContainer=listColor.get(count);
            listColor.set(count, DEFAULT_COLOR);
            updateFlask();
            return colorContainer;
        }


        public int getxFlask() {
            return xFlask;
        }

        public int getyFlask() {
            return yFlask;
        }

        public void setListColor(Color color)
        {
            int count=0;
            for(int i=0;i<listColor.size();i++)
            {
                if(!listColor.get(listColor.size()-1-i).equals(DEFAULT_COLOR)&&!listColor.get(listColor.size()-1-i).equals(Color.GRAY))
                {
                    count++;
                }
            }
            if(count<3)
            {
                listColor.set(count, color);
            }
            updateFlask();
        }

        public Flask(int xFlask, int yFlask, Color lowLayer, Color middleLayer, Color upLayer)
        {
            this.xFlask = xFlask;
            this.yFlask = yFlask;
            this.lowLayer = lowLayer;
            this.middleLayer = middleLayer;
            this.upLayer = upLayer;
            this.group = new Group();
            listColorAddColor();
            updateFlask();
        }


        public void drawFlask( )
        {

            if (listColor.get(0) == null || listColor.get(0) == Color.GRAY)
            {
                listColor.set(0, DEFAULT_COLOR); //lowLayer
            }
            if (listColor.get(1) == null || listColor.get(1) == Color.GRAY)
            {
                listColor.set(1, DEFAULT_COLOR);//middleLayer
            }
            if (listColor.get(2) == null || listColor.get(2) == Color.GRAY)
            {
                listColor.set(2, DEFAULT_COLOR); // upLayer
            }

            if (listColor.get(0) == listColor.get(1) && listColor.get(2) == listColor.get(1))
            {
                double[] arrColorAll = {xFlask + 120,yFlask + 170, xFlask - 70,yFlask + 170, xFlask, yFlask + 80,
                        xFlask,yFlask + 20, xFlask + 50,yFlask + 20, xFlask + 50, yFlask + 80};

                Polygon polygonAll = new Polygon(arrColorAll);
                polygonAll.setFill(listColor.get(0));
                group.getChildren().add(0, polygonAll);
            }
            else if (listColor.get(2) == listColor.get(1))
            {
                double[] arrColorMU = {xFlask + 90, yFlask + 130,xFlask - 40,yFlask + 130, xFlask, yFlask + 80,
                        xFlask, yFlask + 20,xFlask + 50, yFlask + 20, xFlask + 50,yFlask + 80};
                double[] arrColorL = {xFlask + 120,yFlask + 170, xFlask - 70,yFlask + 170,
                        xFlask - 40,yFlask + 130,  xFlask + 90, yFlask + 130};

                Polygon polygonMU = new Polygon(arrColorMU);
                Polygon polygonL = new Polygon(arrColorL);

                polygonL.setFill(listColor.get(0));
                polygonMU.setFill(listColor.get(1));

                listPolygonColor.add(0, polygonMU);
                listPolygonColor.add(1, polygonL);


                Line l = new Line(xFlask-38, yFlask+130, xFlask+88, yFlask+130);
                l.setStroke(Color.BLACK);

                group.getChildren().addAll(polygonL, polygonMU, l);

            }
            else if (listColor.get(0) == listColor.get(1))
            {
                double[] arrColorLM = {xFlask + 120,yFlask + 170, xFlask - 70, yFlask + 170,
                        xFlask,yFlask + 80, xFlask + 50, yFlask + 80};
                double[] arrColorU = {xFlask + 50, yFlask + 80,xFlask,yFlask + 80,
                        xFlask,yFlask + 20,  xFlask + 50,yFlask + 20};

                Polygon polygonLM = new Polygon(arrColorLM);
                Polygon polygonU = new Polygon(arrColorU);

                polygonLM.setFill(listColor.get(0));
                polygonU.setFill(listColor.get(2));

                Line l = new Line(xFlask, yFlask+80, xFlask+50, yFlask+80);
                l.setStroke(Color.BLACK);

                group.getChildren().addAll(polygonLM, polygonU, l);
            }
            else {
                double[] arrColorL = {xFlask + 120, yFlask + 170, xFlask - 70, yFlask + 170,
                        xFlask - 40, yFlask + 130, xFlask + 90, yFlask + 130};
                double[] arrColorM = {xFlask + 90, yFlask + 130, xFlask - 40, yFlask + 130,
                        xFlask, yFlask + 80, xFlask + 50, yFlask + 80};
                double[] arrColorU = {xFlask + 50, yFlask + 80, xFlask, yFlask + 80,
                        xFlask, yFlask + 20, xFlask + 50, yFlask + 20};

                Polygon polygonL = new Polygon(arrColorL);
                polygonL.setFill(listColor.get(0));

                Polygon polygonM = new Polygon(arrColorM);
                polygonM.setFill(listColor.get(1));

                Polygon polygonU = new Polygon(arrColorU);
                polygonU.setFill(listColor.get(2));

                Line l_1 = new Line(xFlask, yFlask+80, xFlask+50, yFlask+80);
                l_1.setStroke(Color.BLACK);
                Line l_2 = new Line(xFlask-38, yFlask+130, xFlask+88, yFlask+130);
                l_2.setStroke(Color.BLACK);
                group.getChildren().addAll(polygonL, polygonM, polygonU, l_1, l_2);
            }
        }


        public void updateFlask()
        {
            group.getChildren().clear();
            drawFlask( );
            double[] arrFlask={xFlask, yFlask,xFlask+50, yFlask, xFlask+50, yFlask+80,
                    xFlask+120, yFlask+170, xFlask-70, yFlask+170, xFlask, yFlask+80};

            Polygon polygon = new Polygon(arrFlask);
            polygon.setFill(new Color(0,0,0,0));
            polygon.setStroke(Color.BLACK);
            polygon.setStrokeWidth(4);
            group.getChildren().add(polygon);
        }
    }
}
