package ensemble.pages;

import ensemble.Page;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-05-14:37
 * UpdateDate: 2021-01-05-14:37
 * FileName: CategoryPage
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class CategoryPage extends Page {

    public CategoryPage(String name, Page... pages) {
        super(name);
        getChildren().addAll(Arrays.asList(pages));
    }

    @Override
    public Node createView() {
        // split children
        List<SamplePage> directChildren = new ArrayList<SamplePage>();
        List<CategoryPage> categoryChildren = new ArrayList<CategoryPage>();
        for (TreeItem child : getChildren()) {
            Page page = (Page) child;
            if (page instanceof SamplePage) {
                directChildren.add((SamplePage) page);
            } else if (page instanceof CategoryPage) {
                categoryChildren.add((CategoryPage) page);
            }
        }
        // create main column
        VBox main = new VBox(8) {
            // stretch to allways fill height of scrollpane
            @Override
            protected double computePrefHeight(double width) {
                return Math.max(
                    super.computePrefHeight(width),
                    getParent().getBoundsInLocal().getHeight()
                );
            }
        };
        main.getStyleClass().add("category-page");
        // create header
        Label header = new Label(getName());
        header.setMaxWidth(Double.MAX_VALUE);
        header.setMinHeight(Control.USE_PREF_SIZE); // Workaround for RT-14251
        header.getStyleClass().add("page-header");
        main.getChildren().add(header);
        // add direct children
        if (!directChildren.isEmpty()) {
            TilePane directChildFlow = new TilePane(8, 8);
            directChildFlow.setPrefColumns(1);
            directChildFlow.getStyleClass().add("category-page-flow");
            for (SamplePage samplePage : directChildren) {
                directChildFlow.getChildren().add(samplePage.createTile());
            }
            main.getChildren().add(directChildFlow);
        }
        // add sub sections
        for (CategoryPage categoryPage : categoryChildren) {
            // create header
            Label categoryHeader = new Label(categoryPage.getName());
            categoryHeader.setMaxWidth(Double.MAX_VALUE);
            categoryHeader.setMinHeight(Control.USE_PREF_SIZE); // Workaround for RT-14251
            categoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(categoryHeader);
            // add direct children
            TilePane directChildFlow = new TilePane(8, 8);
            directChildFlow.setPrefColumns(1);
            directChildFlow.getStyleClass().add("category-page-flow");
            addAllCategoriesSampleTiles(categoryPage, directChildFlow);
            main.getChildren().add(directChildFlow);
        }
        // wrap in scroll pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("noborder-scroll-pane");
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(main);
        return scrollPane;
    }

    private void addAllCategoriesSampleTiles(CategoryPage categoryPage, TilePane pane) {
        for (TreeItem child : categoryPage.getChildren()) {
            Page page = (Page) child;
            if (page instanceof SamplePage) {
                pane.getChildren().add(((SamplePage) page).createTile());
            } else if (page instanceof CategoryPage) {
                addAllCategoriesSampleTiles((CategoryPage) page, pane);
            }
        }
    }

}
