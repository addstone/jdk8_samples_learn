package ensemble;

import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * Description:  Base class for all Ensemble 3D samples
 * Description:  所有Ensemble 3D样本的基类
 * Author: LuDaShi
 * Date: 2021-01-05-15:15
 * UpdateDate: 2021-01-05-15:15
 * FileName: Sample3D
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public abstract class Sample3D extends Sample {

    protected Sample3D(double width, double height) {
        super(width, height);
        Group group3d = new Group(create3dContent());
        group3d.setDepthTest(DepthTest.ENABLE);
        group3d.getTransforms().addAll(
            new Translate(width / 2, height / 2),
            new Rotate(180, Rotate.X_AXIS)
        );
        getChildren().add(group3d);
    }

    /**
     * Called to create 3D content for this sample. The origin for the 3D content is the center of the sample area
     * defined by the width and height passed into the Sample3D constructor. Also the scene is rotated so that positive
     * Y is up. This is because most 3D work is done in a coordinate space where origin is center and Y is up.
     *
     * @return The nodes to display in 3D area with depth test on
     */
    public abstract Node create3dContent();

}