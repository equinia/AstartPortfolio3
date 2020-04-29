package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.util.Stack;

public class Controller {

    private final Astargraph model;
    private final AstarView View;

    public Controller (Astargraph GraphModel, AstarView astarView){
        this.model = GraphModel;
        this.View = astarView;
        this.View.model = GraphModel;
        View.ExitBtn.setOnAction(event -> Platform.exit());


        EventHandler<ActionEvent> PrintRequestHndl
                = event -> HandlePrintRequest(View.EndComb.getValue(),View.PathTA);
        View.Euclidean.setOnAction(PrintRequestHndl);
    }

    public void HandlePrintRequest(Vertex destination, TextArea TArea) {
        if (model.A_Star(View.StartComb.getValue(),View.EndComb.getValue())) {
            TArea.appendText("To " + destination.getid() + " Shortest path: " + destination.getNeighbourDistance() + " \n");
            Vertex pvertex = destination;
            Stack<Vertex> Path = new Stack<Vertex>();
            int limit = 0;
            while (pvertex != null) {
                Path.push(pvertex);
                pvertex = pvertex.getPrev();
            }
            if (!Path.isEmpty())
                limit = Path.size();
            for (int i = 0; i < limit - 1; i++)
                TArea.appendText(Path.pop().getid() + " ->  \n");
            if (limit > 0)
                TArea.appendText(Path.pop().getid() + "\n");


        }
    }


}
