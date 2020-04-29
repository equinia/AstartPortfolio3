package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class AstarView {

    private GridPane Startview;
    public  Astargraph model;
    Button ExitBtn = new Button("Exit");
    Label SelectBtn = new Label("Select start point:");
    ComboBox<Vertex> StartComb = new ComboBox<Vertex>();
    Label DestinationBtn = new Label("Select destination:");
    ComboBox<Vertex> EndComb = new ComboBox<Vertex>();
    Label Shorstest = new Label("press to print path:");
    //Button Manhatten = new Button("Manhatten");
    Button Euclidean = new Button("Print path");




    TextArea PathTA = new TextArea();


    public AstarView(Astargraph GraphModel) {
        this.model = GraphModel;
        Startview = new GridPane();
        Startview.setMinSize(300, 200);
        Startview.setPadding(new Insets(10, 10, 10, 10));
        Startview.setVgap(5);
        Startview.setHgap(1);

        ObservableList<Vertex> VertexList = FXCollections.observableArrayList(model.vertices);
        Callback<ListView<Vertex>, ListCell<Vertex>> VertexcellFactory = new Callback<ListView<Vertex>, ListCell<Vertex>>() {
            @Override
            public ListCell<Vertex> call(ListView<Vertex> vertexListView) {
                return new ListCell<Vertex>() {
                    @Override
                    protected void updateItem(Vertex vertex, boolean empty) {
                        super.updateItem(vertex, empty);// to call method from parent class
                        if (vertex == null || empty) {
                            setText(null);
                        } else {
                            setText(vertex.getid());
                        }
                    }
                };
            }

        };



        StartComb.setItems(VertexList);
        StartComb.setButtonCell(VertexcellFactory.call(null));
        StartComb.setCellFactory(VertexcellFactory);
        StartComb.setValue(model.vertices.get(0));
        EndComb.setItems(VertexList);
        EndComb.setButtonCell(VertexcellFactory.call(null));
        EndComb.setCellFactory(VertexcellFactory);
        EndComb.setValue(model.vertices.get(0));

        Startview.add(SelectBtn,1,1);
        Startview.add(StartComb,15,2);
        Startview.add(DestinationBtn,1,3);
        Startview.add(EndComb,15,4);
        Startview.add(Shorstest,1,5);
        //Startview.add(Manhatten,1,6);
        Startview.add(Euclidean,15,6);
        Startview.add(PathTA,15,7);
        Startview.add(ExitBtn,20,9);



    }
    public Parent asParnet () {
        return Startview;
    }

}