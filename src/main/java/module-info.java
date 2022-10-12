module com.snakegame.snakegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires lombok;
    requires junit;

    opens com.snakegame.snakegame to javafx.fxml;
    exports com.snakegame.snakegame;
}