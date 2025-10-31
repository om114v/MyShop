package com.shop.ui.animations;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 - fadeIn(Node node, Duration duration)
- fadeOut(Node node, Duration duration)
- slideInFromLeft(Node node)
- slideInFromRight(Node node)
- scaleOnHover(Node node)
- bounceEffect(Node node)
- loadingSpinner()
- successCheckmark()
- errorShake(Node node)
 */


public class UIAnimations {

    public static void scaleOnHover(Node node) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), node);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), node);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        node.setOnMouseEntered(e -> scaleIn.play());
        node.setOnMouseExited(e -> scaleOut.play());
    }

}
