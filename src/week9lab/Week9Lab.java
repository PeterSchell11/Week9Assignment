//Peter Schellhorn
package week9lab;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Week9Lab extends Application {  
    public static void main(String[] args) {
    launch(args);
  }
    
  private final double radius = 10;
  private final Circle[] circle = {new Circle(40, 40, 10),
    new Circle(140, 40, 10), new Circle(60, 140, 10)};
  private final Line line1 = new Line();
  private final Line line2 = new Line();
  private final Line line3 = new Line();  
  private final Text[] text = {new Text(), new Text(), new Text()};
  private final Circle crl1 = new Circle(200, 125, 100);
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {  
    Pane pane = new Pane();    
    setLines();
    pane.getChildren().addAll(crl1, circle[0], circle[1], circle[2],
      line1, line2, line3, text[0], text[1], text[2]);
    //
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Week 9 Lab Assignment"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage 
    circle[0].setFill(Color.RED);
    circle[1].setFill(Color.RED);
    circle[2].setFill(Color.RED);
    crl1.setFill(Color.TRANSPARENT);
    crl1.setStroke(Color.BLACK);
    crl1.setStrokeWidth(2);
    
    for (int i = 0; i < circle.length; i++) {
    setRandomLocation(circle[i], crl1);
    setLines();
    final int j =i;
    circle[j].setOnMouseDragged(e -> { 
      
      double radian = Math.atan2(e.getY() - crl1.getCenterY(), e.getX() - crl1.getCenterX());
                double x = crl1.getCenterX() + crl1.getRadius() * Math.cos(radian);
                double y = crl1.getCenterY() + crl1.getRadius() * Math.sin(radian);
                circle[j].setCenterX(x);
                circle[j].setCenterY(y);
                setLines();
    });
    }
    /*
    circle[0].setOnMouseDragged(e -> { 
      
      double radian = Math.atan2(e.getY() - crl1.getCenterY(), e.getX() - crl1.getCenterX());
                double x = crl1.getCenterX() + crl1.getRadius() * Math.cos(radian);
                double y = crl1.getCenterY() + crl1.getRadius() * Math.sin(radian);
                circle[0].setCenterX(x);
                circle[0].setCenterY(y);
                setLines();
    });
    
    //for(int i = 0; i < circle.length; i++){
    circle[1].setOnMouseDragged(e -> { 
      if (circle[1].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        circle[1].setCenterX(e.getX());
        circle[1].setCenterY(e.getY());
        /*
         
        
        setLines();
      }
    });
  
    circle[2].setOnMouseDragged(e -> { 
      if (circle[2].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        circle[2].setCenterX(e.getX());
        circle[2].setCenterY(e.getY());
        setLines();
      }
    });
*/
  }

  private void setLines() {
    line1.setStartX(circle[0].getCenterX());
    line1.setStartY(circle[0].getCenterY());
    line1.setEndX(circle[1].getCenterX());
    line1.setEndY(circle[1].getCenterY());
    line2.setStartX(circle[0].getCenterX());
    line2.setStartY(circle[0].getCenterY());
    line2.setEndX(circle[2].getCenterX());
    line2.setEndY(circle[2].getCenterY());
    line3.setStartX(circle[1].getCenterX());
    line3.setStartY(circle[1].getCenterY());
    line3.setEndX(circle[2].getCenterX());
    line3.setEndY(circle[2].getCenterY());
    
    // Compute angles
    double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[1].getCenterX(), circle[1].getCenterY());
    double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());
    double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());     
    double[] angle = new double[3];
    angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
    angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
    angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

    for (int i = 0; i < 3; i++) {
      text[i].setX(circle[i].getCenterX());
      text[i].setY(circle[i].getCenterY() - radius);
      text[i].setText(String.format("%.0f", Math.toDegrees(angle[i])));
    }
  }
  
    private void setRandomLocation(Circle tPoint, Circle c) {

        double angle = Math.random() * 360;
        double x = c.getCenterX() + c.getRadius() * Math.cos(Math.toRadians(angle));
        double y = c.getCenterY() + c.getRadius() * Math.sin(Math.toRadians(angle));
        tPoint.setCenterX(x);
        tPoint.setCenterY(y);
}
} 