package org.jrebirth.af.core.ui.annotation;

import javafx.scene.Node;

import org.jrebirth.af.core.test.AbstractTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <strong>AnnotationTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class AnnotationTest extends AbstractTest {

    private AnnotationModel model;

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
        this.model = AbstractTest.globalFacade.uiFacade().retrieve(AnnotationModel.class);
    }

    @Test
    public void triggerSwipe() {

        fireAllSwipeEvents(this.model.view().getSwipeVerticalButton());
        fireAllSwipeEvents(this.model.view().getSwipeHorizontalButton());
        fireAllSwipeEvents(this.model.view().getSwipeAllButton());
    }

    @Test
    public void triggerRotate() {

        fireAllRotateEvents(this.model.view().getRotateAllButton());
        fireAllRotateEvents(this.model.view().getRotateButton());
        fireAllRotateEvents(this.model.view().getRotateStartFinishButton());
    }

    /**
     * To complete.
     */
    private void fireAllSwipeEvents(final Node node) {
        // node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.ANY,
        // 0, 0, 0, 0, 2, false, false, false, false, false));
        // node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_UP,
        // 0, 0, 0, 0, 2, false, false, false, false, false));
        // node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_DOWN,
        // 0, 0, 0, 0, 2, false, false, false, false, false));
        // node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_LEFT,
        // 0, 0, 0, 0, 2, false, false, false, false, false));
        // node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_RIGHT,
        // 0, 0, 0, 0, 2, false, false, false, false, false));
    }

    /**
     * To complete.
     */
    private void fireAllRotateEvents(final Node node) {
        // node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ANY,
        // 0.0, 0.0, 0.0, 0.0, 2.0, 0, false, false, false, false, false, false));
        //
        // node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ROTATION_STARTED,
        // 0, 0, 0, 0, 2, 0, false, false, false, false, false, false));
        //
        // node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ROTATE,
        // 0, 0, 0, 0, 2, 0, false, false, false, false, false, false));
        //
        // node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ROTATION_FINISHED,
        // 0, 0, 0, 0, 2, 0, false, false, false, false, false, false));
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

}
