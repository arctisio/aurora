package gg.acai.aurora.image;

/**
 * @author Clouke
 * @since 24.01.2023 13:41
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface Image {

    String draw();

    default void print() {
        String graph = draw();
        System.out.println(graph);
    }

    default String description() {
        return "Visualisation of a data graph";
    }

}
