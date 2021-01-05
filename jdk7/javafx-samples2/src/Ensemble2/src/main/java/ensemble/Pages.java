package ensemble;

import ensemble.pages.AllPagesPage;
import ensemble.pages.CategoryPage;
import ensemble.pages.SamplePage;

/**
 * Description:  Pages
 * Author: LuDaShi
 * Date: 2021-01-05-13:16
 * UpdateDate: 2021-01-05-13:16
 * FileName: Pages
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class Pages {

    public static final String SAMPLES = "SAMPLES";
    public static final String API_DOCS = "API DOCUMENTATION";
    public static final String NEW = "NEW!";
    public static final String HIGHLIGHTS = "HIGHLIGHTS";
    private AllPagesPage root;
    private CategoryPage samples;
    private CategoryPage docs;
    private CategoryPage newSamples;
    private CategoryPage highlightedSamples;

    public Pages() {
        // create all the pages
        root = new AllPagesPage();
        samples = new CategoryPage(SAMPLES);
        docs = new CategoryPage(API_DOCS);
        newSamples = new CategoryPage(NEW);
        highlightedSamples = new CategoryPage(HIGHLIGHTS);
        root.getChildren().addAll(highlightedSamples, newSamples);
        root.getChildren().add(samples);
        root.getChildren().add(docs);
    }

    public void parseSamples() {
        SampleHelper.getSamples(samples);
        // ADD PAGES TO HIGHLIGHTS CATEGORY
        highlightedSamples.getChildren().addAll(
            new SamplePage((SamplePage) getPage("SAMPLES/Web/Web View")),
            new SamplePage((SamplePage) getPage("SAMPLES/Web/H T M L Editor")),
            new SamplePage((SamplePage) getPage("SAMPLES/Graphics 3d/Cube")),
            new SamplePage((SamplePage) getPage("SAMPLES/Graphics 3d/Cube System")),
            new SamplePage((SamplePage) getPage("SAMPLES/Graphics 3d/Xylophone")),
            new SamplePage((SamplePage) getPage("SAMPLES/Media/Advanced Media")),
            new SamplePage((SamplePage) getPage("SAMPLES/Graphics/Digital Clock")),
            new SamplePage((SamplePage) getPage("SAMPLES/Graphics/Display Shelf")),
            new SamplePage((SamplePage) getPage("SAMPLES/Charts/Area/Adv Area Audio Chart")),
            new SamplePage((SamplePage) getPage("SAMPLES/Charts/Bar/Adv Bar Audio Chart")),
            new SamplePage((SamplePage) getPage("SAMPLES/Charts/Line/Advanced Stock Line Chart")),
            new SamplePage((SamplePage) getPage("SAMPLES/Charts/Custom/Adv Candle Stick Chart")),
            new SamplePage((SamplePage) getPage("SAMPLES/Charts/Scatter/Advanced Scatter Chart"))
        );
        // ADD PAGES TO NEW CATEGORY
        newSamples.getChildren().addAll(
            new SamplePage((SamplePage) getPage("SAMPLES/Canvas/Fireworks")),
            new SamplePage((SamplePage) getPage("SAMPLES/Controls/Pagination")),
            new SamplePage((SamplePage) getPage("SAMPLES/Controls/Color Picker")),
            new SamplePage((SamplePage) getPage("SAMPLES/Controls/List/List View Cell Factory")),
            new SamplePage((SamplePage) getPage("SAMPLES/Controls/Table/Table Cell Factory")),
            new SamplePage((SamplePage) getPage("SAMPLES/Graphics/Images/Image Operator")),
            new SamplePage((SamplePage) getPage("SAMPLES/Scenegraph/Events/Multi Touch"))
        );
    }

    public Page getPage(String name) {
        Page page = root.getChild(name);
//        if (page == null) {
//            System.err.print("Can not load page named '" + name + "'");
//        }
        return page;
    }

    public Page getHighlighted() {
        return highlightedSamples;
    }

    public Page getNew() {
        return newSamples;
    }

    public Page getSamples() {
        return samples;
    }

    public Page getDocs() {
        return docs;
    }

    public Page getRoot() {
        return root;
    }
}

